/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfraentities.UserEntity;
import edutechentities.common.ScheduleItemEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Derian
 */
@Stateless
@LocalBean
public class CommonRESTMgrBean {

    @PersistenceContext
    private EntityManager em;
    
    public void createUser(UserEntity entity) {
        entity.setUsercreationdate(new Date());
        entity.setUseractivestatus(new Integer(1).shortValue());
        em.persist(entity);
    }

    public void editUser(String id, UserEntity entity) {
        //pull current entity based on id (entity is now detached)
        UserEntity old = em.find(UserEntity.class, id);
        //instantiate curr entity into new entity
        old = entity;
        //update curr entity in database. (reattach entity)
        em.merge(entity);
    }

    public List<UserEntity> findAllUsers() {
        return em.createQuery("SELECT s FROM Systemuser s WHERE s.useractivestatus=1").getResultList();
    }

    public UserEntity findUser(String id) {
        return em.find(UserEntity.class, id);
    }

    public void removeUser(String id) {
        em.remove(em.find(UserEntity.class, id));
    }

    public String countUsers() {
        return  String.valueOf(em.createQuery("SELECT COUNT(s) FROM Systemuser s WHERE s.useractivestatus=1").getSingleResult());
    }
    
    public List<ScheduleItemEntity> findAllScheduleItems(){
        return em.createQuery("SELECT s FROM ScheduleItem s").getResultList();
    }

    public void createScheduleItem(ScheduleItemEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editScheduleItem(String id, ScheduleItemEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeScheduleItem(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ScheduleItemEntity findScheduleItem(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String countScheduleItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
