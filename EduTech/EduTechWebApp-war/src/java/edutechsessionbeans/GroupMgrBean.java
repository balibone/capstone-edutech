/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfraentities.UserEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.common.TaskEntity;
import edutechentities.group.BrainstormEntity;
import edutechentities.group.GroupEntity;
import edutechentities.group.MMAgendaEntity;
import edutechentities.group.MeetingMinuteEntity;
import edutechentities.module.AssignmentEntity;
import edutechentities.module.ModuleEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nanda88
 */

@Stateless
@LocalBean
public class GroupMgrBean {
    @PersistenceContext
    private EntityManager em;
    
    public List<GroupEntity> findUserGroups(String username){
        UserEntity thisUser = em.find(UserEntity.class,username);
        List<GroupEntity> userGroups = new ArrayList();
        List<GroupEntity> allGroups = em.createQuery("SELECT g FROM ProjectGroup g").getResultList();
        for(Object o: allGroups){
            GroupEntity group = (GroupEntity) o;
            if(group.getMembers().contains(thisUser)){
                userGroups.add(group);
            }
        }
        return userGroups;
    }

    public GroupEntity findGroup(String id) {
        GroupEntity group = em.find(GroupEntity.class, Long.valueOf(id));
        group.setMembers(group.getMembers());
        return group;
    }

    public List<UserEntity> findGroupMembers(String id) {
        return (List) em.find(GroupEntity.class, Long.valueOf(id)).getMembers();
    }
    
    public void editGroup(String id, GroupEntity entity) {
        em.merge(entity);
    }

    public List<MeetingMinuteEntity> getAllMeetingMinutes() {
        Query q = em.createQuery("select m from MeetingMinute m");
        return q.getResultList();
    }

    public MeetingMinuteEntity getOneMeetingMinute(Long id) {
        return em.find(MeetingMinuteEntity.class, id);
    }

    public void deleteMeetingMinute(Long id) {
        MeetingMinuteEntity mm = em.find(MeetingMinuteEntity.class, id);
        if(mm!=null){
            em.remove(mm);
        }
    }

    public MeetingMinuteEntity createMeetingMinutes(MeetingMinuteEntity mm) {
        em.persist(mm);
        return mm;
    }

    public MeetingMinuteEntity editMeetingMinute(Long id, MeetingMinuteEntity replacement) {
        MeetingMinuteEntity mm = em.find(MeetingMinuteEntity.class, id);
        mm = replacement;
        em.merge(mm);
        return mm;
    }

    public List<MMAgendaEntity> getAllMMAgendas() {
        Query q = em.createQuery("select mma from MMAgenda mma");
        return q.getResultList();
    }

    public MMAgendaEntity getOneMMAgenda(Long id) {
        return em.find(MMAgendaEntity.class, id);
    }

    public MMAgendaEntity createMMAgenda(MMAgendaEntity agenda) {
        em.persist(agenda);
        return agenda;
    }

    public void deleteMMAgenda(Long id) {
        MMAgendaEntity mma= em.find(MMAgendaEntity.class, id);
        if(mma!=null){
            em.remove(mma);
        }
    }

    public MMAgendaEntity editMMAgenda(Long id, MMAgendaEntity replacement) {
        MMAgendaEntity mma = em.find(MMAgendaEntity.class, id);
        mma = replacement;
        em.merge(mma);
        return mma;
    }

    public List<GroupEntity> getAllGroups() {
        Query q = em.createQuery("select g from ProjectGroup g");
        return q.getResultList();
    }

    public GroupEntity createGroup(GroupEntity group) {
        em.persist(group);
        return group;
    }

    public void deleteGroup(String id) {
        GroupEntity g= em.find(GroupEntity.class, Long.valueOf(id));
        //to detach group from assignments
        Query allAssignments = em.createQuery("SELECT a FROM Assignment a");
        //to detach group from tasks
        Query allTasks = em.createQuery("SELECT t FROM Task t");
        //to detach group from brainstorms
        Query allBrains = em.createQuery("SELECT b FROM Brainstorm b");
        if(g!=null){
            //detach group from all assignments
            for(Object o : allAssignments.getResultList()){
                AssignmentEntity a = (AssignmentEntity) o;
                if(a.getGroups().contains(g))
                    a.getGroups().remove(g);
            }
            //detach group from tasks
            for(Object o : allTasks.getResultList()){
                TaskEntity t = (TaskEntity) o;
                if(t.getGroupId()==g.getId()){
                    t.setGroupId(0);
                    
                }
            }
            //detach group from brainstorm
            for(Object o : allBrains.getResultList()){
                BrainstormEntity b = (BrainstormEntity) o;
                if(b.getGroup().equals(g)){
                    b.setGroup(null);
                }
            }
            //remove group members.
            g.setMembers(null);
            em.remove(g);
        }
    }

    public List<GroupEntity> getAllGroupsForModule(String moduleCode) {
        Query q1 = em.createQuery("SELECT g FROM ProjectGroup g WHERE g.moduleCode = :modCode");
        q1.setParameter("modCode", moduleCode);
        return q1.getResultList();
    }

    public GroupEntity joinGroup(String id, String username) {
        //get all assignments
        Query q1 = em.createQuery("SELECT a FROM Assignment a");
        //get this group
        GroupEntity g = em.find(GroupEntity.class, Long.valueOf(id));
        //get group members
        Collection<UserEntity> gMembers = g.getMembers();
        //get this user.
        UserEntity u = em.find(UserEntity.class, username);
        //boolean to flag if user already inside another group.
        boolean isGrouped = false;
        
        for(Object o : q1.getResultList()){
            AssignmentEntity ass = (AssignmentEntity)o;
            Collection<GroupEntity> assGroups = ass.getGroups();
            //if this assignment contains this group, 
            if(assGroups.contains(g)){
                //search through all its groups
                for(GroupEntity gru : assGroups){
                    //if group has this user
                    if(gru.getMembers().contains(u))
                        //user is already inside group.
                        isGrouped = true;
                }
            }
        }
        
        //IF:
        //1) GROUP STILL HAS SPACE 
        //2) USER ISNT ALREADY INSIDE GROUP
        //3) USER IS NOT ALREADY IN A GROUP FOR THIS ASSIGNMENT
        //, ALLOW JOINING.
        //Collection.add() will still run even though u is inside, and cause PK conflict, so need to check if u is inside first.
        if(gMembers.size() < g.getGroupSize() && !gMembers.contains(u) && !isGrouped){
            gMembers.add(u);
        }
        return g;
    }

    public GroupEntity leaveGroup(String id, String username) {
        GroupEntity g = em.find(GroupEntity.class, Long.valueOf(id));
        UserEntity u = em.find(UserEntity.class, username);
        //REMOVE USER FROM GROUP.
        //Collection.remove() will not run if u is not inside collection.
        g.getMembers().remove(u);
        return g;
    }

    public List<AssignmentEntity> getModuleAssignments(String moduleCode) {
        ModuleEntity mod = em.find(ModuleEntity.class, moduleCode);
        List<AssignmentEntity> modAsses = new ArrayList<>();
        if(mod!=null){
            Query q = em.createQuery("SELECT ass FROM Assignment ass WHERE ass.module= :modCode");
            q.setParameter("modCode", mod);
            modAsses = q.getResultList();
        }
        return modAsses;
    }
    
}
