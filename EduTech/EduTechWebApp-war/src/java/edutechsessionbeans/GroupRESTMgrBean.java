/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfraentities.UserEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.group.GroupEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
