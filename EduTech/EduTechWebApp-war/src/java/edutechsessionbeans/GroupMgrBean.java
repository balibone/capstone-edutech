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
import java.util.Collections;
import java.util.Iterator;
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
            mm.setAgendas(null);
            mm.setAttachments(null);
            mm.setAttendees(null);
            mm.getMeeting().setMeetingMinute(null);
            mm.setMeeting(null);
            em.remove(mm);
        }
    }

    public MeetingMinuteEntity createMeetingMinute(MeetingMinuteEntity mm) {
        ScheduleItemEntity meeting = em.find(ScheduleItemEntity.class, mm.getMeeting().getId());
        ArrayList<UserEntity> attendees = (ArrayList<UserEntity>) mm.getAttendees();
        ArrayList<UserEntity> toSet = new ArrayList<>();
        
        if(meeting!=null){
            //extract usernames and populate with real user data.
            for(UserEntity u : attendees){
                UserEntity targetUser = em.find(UserEntity.class,u.getUsername());
                toSet.add(targetUser);
            }
            mm.setAttendees(toSet);
            //assign meeting to meeting minute
            mm.setMeeting(meeting);
            //assign meeting minute to meeting
            meeting.setMeetingMinute(mm);
            em.persist(mm);
        }
        return mm;
    }
    //NEED TO SUPPLY entity ID in JSON.
    public MeetingMinuteEntity editMeetingMinute(Long id, MeetingMinuteEntity replacement) {
        ScheduleItemEntity meeting = em.find(ScheduleItemEntity.class, replacement.getMeeting().getId());
        MeetingMinuteEntity mm = em.find(MeetingMinuteEntity.class, id);
        ArrayList<UserEntity> attendees = (ArrayList<UserEntity>) replacement.getAttendees();
        ArrayList<UserEntity> toSet = new ArrayList<>();
        if(meeting!=null && mm!=null){
            //extract usernames and populate with real user data.
            for(UserEntity u : attendees){
                UserEntity targetUser = em.find(UserEntity.class,u.getUsername());
                toSet.add(targetUser);
            }
            replacement.setAttendees(toSet);
            //assign meeting to meeting minute
            replacement.setMeeting(meeting);
            //assign meeting minute to meeting
            meeting.setMeetingMinute(replacement);
            em.merge(replacement);
        }
        return mm;
    }

    public List<MMAgendaEntity> getAllMMAgendas() {
        Query q = em.createQuery("select mma from MMAgenda mma");
        return q.getResultList();
    }

    public MMAgendaEntity getOneMMAgenda(Long id) {
        return em.find(MMAgendaEntity.class, id);
    }

    public MMAgendaEntity createMMAgenda(MMAgendaEntity agenda, String mmId) {
        MeetingMinuteEntity mm = em.find(MeetingMinuteEntity.class,Long.valueOf(mmId));
        if(mm!=null){
            //assign agendas to mm;
            mm.getAgendas().add(agenda);
            em.persist(agenda);
        }else{
            agenda.setTitle("WARNING: MEETING MINUTE ID IS INCORRECT! AGENDA NOT CREATED.");
        }
        return agenda;
    }

    public void deleteMMAgenda(Long id) {
        MMAgendaEntity mma= em.find(MMAgendaEntity.class, id);
        Query q1 = em.createQuery("SELECT m FROM MeetingMinute m");
        if(mma!=null){
            //detach agenda from all meeting minutes.
            for(Object o : q1.getResultList()){
                MeetingMinuteEntity mm = (MeetingMinuteEntity) o;
                Collection<MMAgendaEntity> agendas = mm.getAgendas();
                //if this meeting minute contains this agenda,
                if(agendas.contains(mma)){
                    //detach agenda.
                    agendas.remove(mma);
                }
            }
            em.remove(mma);
        }
    }

    public MMAgendaEntity editMMAgenda(Long id, MMAgendaEntity replacement) {
        MMAgendaEntity mma = em.find(MMAgendaEntity.class, id);
        em.merge(replacement);
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

    public List<GroupEntity> autoAssignMembers(String assignmentId) {
        
        AssignmentEntity ass = em.find(AssignmentEntity.class, Long.valueOf(assignmentId));
        ModuleEntity mod = ass.getModule();
        ArrayList<GroupEntity> assGroups = new ArrayList<>(ass.getGroups());
        
        //if mod and assignment exists and assignment belong to this mod, run auto assign
        if(mod != null && ass != null && ass.getModule().equals(mod)){
            
            ArrayList<UserEntity> studentsToAdd = new ArrayList<>();
            for(UserEntity u : mod.getMembers()){
                if(u.getUserType().equalsIgnoreCase("student")){
                    studentsToAdd.add(u);
                }
            }
            int studentsToAddIndex = 0;
            
            //randomise contents of array list.
            Collections.shuffle(studentsToAdd);
            
            //remove those users which already have group in this assignment.
            //Use iterator to prevent ConcurrentModificationException
            for (Iterator<UserEntity> iterator = studentsToAdd.iterator(); iterator.hasNext();) {
                UserEntity member = iterator.next();
                for(GroupEntity g : ass.getGroups()){
                    if(g.getMembers().contains(member)){
                        iterator.remove();
                    }
                }
            }
            
            //go through all groups in this assignment
            for(GroupEntity g : ass.getGroups()){
                Collection<UserEntity> groupMembers = g.getMembers();
                //keep adding to group if group still has space AND there are still members to add.
                while(groupMembers.size()<g.getGroupSize() && studentsToAddIndex < studentsToAdd.size()){
                    groupMembers.add(studentsToAdd.get(studentsToAddIndex));
                    studentsToAddIndex++;
                }
            }
        }
        return assGroups;
    }

    public List<UserEntity> getMembersWithoutGroup(String assignmentId) {
        ArrayList<UserEntity> grouplessStudents = new ArrayList<>();
        AssignmentEntity ass = em.find(AssignmentEntity.class, Long.valueOf(assignmentId));
        ArrayList<GroupEntity> assGroups = new ArrayList<>(ass.getGroups());
        
        if(ass!=null){
            ArrayList<UserEntity> modStudents = new ArrayList<>();
            for(UserEntity u : ass.getModule().getMembers()){
                if(u.getUserType().equalsIgnoreCase("student")){
                    modStudents.add(u);
                }
            }
            grouplessStudents.addAll(modStudents);
            for(UserEntity u : modStudents){
                //label to break out of inner loop
                innerloop:
                for(GroupEntity g : assGroups){
                    if(g.getMembers().contains(u)){
                        grouplessStudents.remove(u);
                        break innerloop;
                    }
                }
                //when break hits it will continue here, aka next loop iteration will run.
            }
        }
        return grouplessStudents;
    }
    
}
