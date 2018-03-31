/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfraentities.UserEntity;
import edutechentities.common.AttachmentEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.common.TaskEntity;
import edutechentities.group.GroupEntity;
import edutechentities.module.ModuleEntity;
import edutechentities.module.AssignmentEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Derian
 */
@Stateless
@LocalBean
public class ModuleMgrBean {

    @PersistenceContext
    private EntityManager em;

    public void createModule(ModuleEntity entity) {
        em.persist(entity);
    }

    public void editModule(String id, ModuleEntity entity) {
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        mod = entity;
        em.merge(mod);
    }

    public void deleteModule(String id) {
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        em.remove(mod);
    }

    public ModuleEntity getOneModule(String id) {
        return em.find(ModuleEntity.class, id);
    }

    public List<ModuleEntity> getAllModules() {
        Query q1 = em.createQuery("SELECT m FROM Module m");
        return q1.getResultList();
    }

    public List<AssignmentEntity> getAllAssignments() {
        Query q = em.createQuery("select s from Assignment s");
        return q.getResultList();
    }

    public AssignmentEntity getOneAssignment(Long id) {
        return em.find(AssignmentEntity.class, id);
    }

    public AssignmentEntity createIndividualAssignment(AssignmentEntity ass) {
        //NEED TO MANUALLY PULL & ASSIGN USER ENTITY FROM DATABASE 
        UserEntity creator = em.find(UserEntity.class, ass.getCreatedBy().getUsername());
        ass.setCreatedBy(creator);
        System.out.println("creator is "+creator);
        //NEED TO MANUALLY PULL & ASSIGN MODULE ENTITY FROM DATABASE
        ModuleEntity assignedMod = em.find(ModuleEntity.class, ass.getModule().getModuleCode());
        ass.setModule(assignedMod);
        System.out.println("module is "+assignedMod.getModuleCode()+" "+assignedMod.getTitle());
        System.out.println("module members are :"+Arrays.toString(assignedMod.getMembers().toArray()));
        //create assignment tasks for all students.
        createAssignmentTasks(ass);
        //return created entity with ID.
        em.persist(ass);
        return ass;
    }
    
    public AssignmentEntity createGroupAssignment(AssignmentEntity ass, String numOfGroups, String groupSize) {
        //NEED TO MANUALLY PULL & ASSIGN USER ENTITY FROM DATABASE 
        UserEntity creator = em.find(UserEntity.class, ass.getCreatedBy().getUsername());
        ass.setCreatedBy(creator);
        System.out.println("creator is "+creator);
        //NEED TO MANUALLY PULL & ASSIGN MODULE ENTITY FROM DATABASE
        ModuleEntity assignedMod = em.find(ModuleEntity.class, ass.getModule().getModuleCode());
        ass.setModule(assignedMod);
        System.out.println("module is "+assignedMod.getModuleCode()+" "+assignedMod.getTitle());
        System.out.println("module members are :"+Arrays.toString(assignedMod.getMembers().toArray()));
        //create assignment tasks for all students.
        createAssignmentTasks(ass);
        
        
        //create groups
        Collection<GroupEntity> assGroups = new ArrayList<>();
        //initially null because JSON value not filled.
        ass.setGroups(assGroups);
        int gSize = Integer.valueOf(groupSize);
        int numGroups = Integer.valueOf(numOfGroups);
        for(int i = 1 ; i <= numGroups ; i++){
            GroupEntity group = new GroupEntity();
            group.setCreatedBy(creator.getUsername());
            group.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            group.setDescription("Created for "+ass.getModule().getTitle()+" Assignment :"+ass.getTitle());
            group.setGroupSize(gSize);
            group.setModuleCode(assignedMod.getModuleCode());
            group.setTitle(ass.getModule().getModuleCode()+" "+ass.getTitle()+" G"+i);
            //assign group to assignment.
            assGroups.add(group);
            //persist new group
            em.persist(group);
        }
        //persist assignment
        em.persist(ass);
        
        //return created entity with ID.
        return ass;
    }

    public void deleteAssignment(Long id) {
        AssignmentEntity ass= em.find(AssignmentEntity.class, id);
        if(ass!=null){
            Collection<GroupEntity> groups = ass.getGroups();
            Collection<TaskEntity> tasks = ass.getTasks();
            //detach groups
            ass.setGroups(null);
            //delete assignment groups if any
            for(GroupEntity g : groups){
                g.setMembers(null);
                em.remove(g);
            }
            
            //detach tasks
            ass.setTasks(null);
            //delete tasks if any
            for(TaskEntity t : tasks){
                t.setAssignedTo(null);
                em.remove(t);
            }
            
            //detach submissions
            ass.setSubmissions(null);
            
            em.remove(ass);
        }
    }

    public AssignmentEntity editAssignment(Long id, AssignmentEntity replacement) {
//update assignment
        AssignmentEntity ass = em.find(AssignmentEntity.class, id);
        ass.setCloseDate(replacement.getCloseDate());
        ass.setTitle(replacement.getTitle());
        //update tasks which were created for this assignment.
        for(TaskEntity g : ass.getTasks()){
            g.setTitle(ass.getModule().getModuleCode()+" "+ass.getTitle());
            g.setDeadline(ass.getCloseDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        //update groups which were created for this assignment.
        int i = 1;
        for(GroupEntity g : ass.getGroups()){
            g.setTitle(ass.getModule().getTitle()+" Assignment "+ass.getTitle()+" Group "+i);
            g.setDescription("Created for "+ass.getModule().getTitle()+" Assignment :"+ass.getTitle());
            i++;
        }
        return ass;
    }

    public List<ScheduleItemEntity> getAllModuleLessons(String id) {
        ArrayList<ScheduleItemEntity> modLessons = new ArrayList<>();
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        Collection<ScheduleItemEntity> lessons = mod.getModuleEvents();
        if(lessons!=null){
            System.out.println("TRY PRINTING......................");
            modLessons.addAll(lessons);
            for(ScheduleItemEntity l : lessons){
                System.out.println("TRY PRINTING ONE......................");
                System.out.println(l);
            }
        }
        return modLessons;
    }

    public List<ModuleEntity> getUserModules(String userId) {
        List<ModuleEntity> moduleList = new ArrayList<>();
        Query q1 = em.createQuery("SELECT m FROM Module m");
        UserEntity u = em.find(UserEntity.class, userId);
        if(u != null){
            for(Object o: q1.getResultList()){
                ModuleEntity m = (ModuleEntity)o;
                if(m.getMembers().contains(u)){
                    moduleList.add(m);
                }
            }
        }
        return moduleList;
    }
    
    private void createAssignmentTasks( AssignmentEntity ass){
        ArrayList<TaskEntity> tasks = new ArrayList<>();
        ass.setTasks(tasks);
        
        UserEntity creator = ass.getCreatedBy();
        ModuleEntity assignedMod = ass.getModule();
        
        String modCode = assignedMod.getModuleCode();
        //assign task to all of this module's students
        for(UserEntity u : assignedMod.getMembers()){
            if(u.getUserType().equalsIgnoreCase("student")){
                TaskEntity assignmentTask = new TaskEntity();
                assignmentTask.getAssignedTo().add(u);
                assignmentTask.setCreatedAt(ass.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                assignmentTask.setCreatedBy(creator);
                assignmentTask.setDeadline(ass.getCloseDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                assignmentTask.setModuleCode(modCode);
                assignmentTask.setTitle(ass.getModule().getModuleCode()+" "+ass.getTitle());
                assignmentTask.setType("module");
//                //persist this new task.
                em.persist(assignmentTask);
                tasks.add(assignmentTask);
            }
        }
    }
   
}
