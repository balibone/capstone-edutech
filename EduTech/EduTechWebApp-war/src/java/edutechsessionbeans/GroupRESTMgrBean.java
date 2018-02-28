/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfrastructureentities.UserEntity;
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
    
    public List<GroupEntity> getUserGroups(String username){
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
    
}
