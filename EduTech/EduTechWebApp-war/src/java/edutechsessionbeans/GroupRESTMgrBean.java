/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfraentities.UserEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.group.GroupEntity;
import edutechentities.group.MMAgendaEntity;
import edutechentities.group.MeetingMinuteEntity;
import java.util.ArrayList;
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
public class GroupRESTMgrBean {
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
        //pull current entity based on id (entity is now detached)
        GroupEntity old = em.find(GroupEntity.class, Long.valueOf(id));
        //instantiate curr entity into new entity
        old = entity;
        //update curr entity in database. (reattach entity)
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
    
}
