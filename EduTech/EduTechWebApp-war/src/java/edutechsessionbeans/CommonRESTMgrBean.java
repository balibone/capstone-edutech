/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfraentities.Systemuser;
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
    
    public void createUser(Systemuser entity) {
        entity.setUsercreationdate(new Date());
        entity.setUseractivestatus(new Integer(1).shortValue());
        em.persist(entity);
    }

    public void editUser(String id, Systemuser entity) {
        //pull current entity based on id (entity is now detached)
        Systemuser old = em.find(Systemuser.class, id);
        //instantiate curr entity into new entity
        old = entity;
        //update curr entity in database. (reattach entity)
        em.merge(entity);
    }

    public List<Systemuser> findAllUsers() {
        return em.createQuery("SELECT s FROM Systemuser s WHERE s.useractivestatus=1").getResultList();
    }

    public Systemuser findUser(String id) {
        return em.find(Systemuser.class, id);
    }

    public void removeUser(String id) {
        em.remove(em.find(Systemuser.class, id));
    }

    public String countUsers() {
        return  String.valueOf(em.createQuery("SELECT COUNT(s) FROM Systemuser s WHERE s.useractivestatus=1").getSingleResult());
    }
}
