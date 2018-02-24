/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import commoninfraentities.Systemuser;
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
public class EduTechMgrBean {

    @PersistenceContext
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Systemuser> findAllUsers() {
        Query q1 = em.createQuery("SELECT s FROM SystemUser s WHERE s.useractivestatus=1");
        return q1.getResultList();
    }

    public void editUser(Systemuser entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeUser(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Systemuser findUser(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String count() {
        Query q1 = em.createQuery("SELECT COUNT(u) FROM SystemUser u WHERE u.useractivestatus=1");
        return String.valueOf(q1.getSingleResult());
    }
}
