/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;

import edutechentities.common.ScheduleItemEntity;
import edutechentities.module.ModuleEntity;
import edutechentities.module.AssignmentEntity;
import java.util.ArrayList;
import java.util.Collection;
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

    public void deleteModule(String id) {
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        em.remove(mod);
    }

    public ModuleEntity getOneModule(String id) {
        return em.find(ModuleEntity.class, id);
    }

    public List<ModuleEntity> getAllModules() {
        Query q1 = em.createQuery("SELECT m FROM Module m");
        return q1.getResultList();
    }

    public List<AssignmentEntity> getAllSubmissions() {
        Query q = em.createQuery("select s from Submission s");
        return q.getResultList();
    }

    public AssignmentEntity getOneSubmission(Long id) {
        return em.find(AssignmentEntity.class, id);
    }

    public AssignmentEntity createSubmission(AssignmentEntity sub) {
        em.persist(sub);
        return sub;
    }

    public void deleteSubmission(Long id) {
        AssignmentEntity ses= em.find(AssignmentEntity.class, id);
        if(ses!=null){
            em.remove(ses);
        }
    }

    public AssignmentEntity editSubmission(Long id, AssignmentEntity replacement) {
        AssignmentEntity sub = em.find(AssignmentEntity.class, id);
        sub = replacement;
        em.merge(sub);
        return sub;
    }

    public List<ScheduleItemEntity> getAllModuleLessons(String id) {
        ArrayList<ScheduleItemEntity> modLessons = new ArrayList<>();
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        Collection<ScheduleItemEntity> lessons = mod.getModuleEvents();
        if(lessons!=null){
            System.out.println("TRY PRINTING......................");
            modLessons.addAll(lessons);
            for(ScheduleItemEntity l : lessons){
                System.out.println("TRY PRINTING ONE......................");
                System.out.println(l);
            }
        }
        return modLessons;
    }

}
