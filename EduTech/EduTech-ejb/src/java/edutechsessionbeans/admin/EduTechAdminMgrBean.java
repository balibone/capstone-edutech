/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans.admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Derian
 */
@Stateless
public class EduTechAdminMgrBean implements EduTechAdminMgrBeanRemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Object getModuleCount() {
        return em.createQuery("SELECT COUNT(DISTINCT m.moduleCode) FROM Module m WHERE m.activeStatus=1").getSingleResult();
        
    }

    @Override
    public Object getSemesterCount() {
        return em.createQuery("SELECT COUNT(DISTINCT s.id) FROM Semester s WHERE s.activeStatus=1").getSingleResult();
    }
}
