/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import edutechentities.module.ModuleEntity;
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
public class ModuleRESTMgrBean {

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

    public void removeModule(String id) {
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        em.remove(mod);
    }

    public ModuleEntity findModule(String id) {
        return em.find(ModuleEntity.class, id);
    }

    public List<ModuleEntity> findAllModules() {
        Query q1 = em.createQuery("SELECT m FROM Module m");
        return q1.getResultList();
    }
    
}
