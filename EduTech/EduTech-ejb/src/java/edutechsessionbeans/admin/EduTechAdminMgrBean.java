/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans.admin;

import edutechentities.ModuleEntity;
import edutechentities.SemesterEntity;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public boolean createSemester(String title, String startDate, String endDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startD = dateFormat.parse(startDate);
            Date endD = dateFormat.parse(endDate);
            SemesterEntity sem = new SemesterEntity(title,startD,endD);
            em.persist(sem);
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(EduTechAdminMgrBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList getAllSemesters() {
        ArrayList semesterList = new ArrayList<ArrayList>();
        SemesterEntity sem = new SemesterEntity();
        //format Date to String in format of e.g. 05 July 2019 
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        //Find all active semesters
        Query q1 = em.createQuery("SELECT s FROM Semester s WHERE s.activeStatus=1");
        //Find the number of unique modules under this semester
        Query q2 = em.createQuery("SELECT COUNT(m) FROM Semester s, Module m WHERE m.semester = :semester");
        for(Object o : q1.getResultList()){
            sem = (SemesterEntity) o;
            ArrayList semInfo = new ArrayList();
            semInfo.add(sem.getId());
            semInfo.add(sem.getTitle());
            q2.setParameter("semester", sem);
            semInfo.add(q2.getSingleResult());
            semInfo.add(sdf.format(sem.getStartDate()));
            semInfo.add(sdf.format(sem.getEndDate()));
            semesterList.add(semInfo);
        }
        return semesterList;
    }

    @Override
    public ArrayList getSemesterInfo(String id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        ArrayList semInfo = new ArrayList();
        SemesterEntity sem = em.find(SemesterEntity.class, Long.parseLong(id));
        semInfo.add(sem.getId());
        semInfo.add(sem.getTitle());
        semInfo.add(sdf.format(sem.getStartDate()));
        semInfo.add(sdf.format(sem.getEndDate()));
        
        //get list of modules in this semester
        List modules = sem.getModules();
        //store module information for all modules in this semester
        ArrayList moduleInfoList = new ArrayList();
        for(Object o : modules){
            ModuleEntity mod = (ModuleEntity)o;
            //only extract module info if mod is active.
            if(mod.getActiveStatus()){
                //store module information for each module
                ArrayList moduleInfo = new ArrayList();
                
                moduleInfo.add(mod.getModuleCode());
                moduleInfo.add(mod.getName());
                moduleInfoList.add(moduleInfo);
            }
        }
        
        semInfo.add(moduleInfoList);
        semInfo.add(moduleInfoList.size());
        return semInfo;
    }

    @Override
    public void deactivateSemester(String id) {
        SemesterEntity sem = em.find(SemesterEntity.class, Long.valueOf(id));
        sem.setActiveStatus(Boolean.FALSE);
    }

    @Override
    public boolean editSemester(String title, String startDate, String endDate, String id) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startD = dateFormat.parse(startDate);
            Date endD = dateFormat.parse(endDate);
            SemesterEntity sem = em.find(SemesterEntity.class,Long.valueOf(id));
            sem.setTitle(title);
            sem.setStartDate(startD);
            sem.setEndDate(endD);
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(EduTechAdminMgrBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean createModule(String moduleCode, String name, Long modularCredit, String description, Long semID) {
        try{
            SemesterEntity sem = em.find(SemesterEntity.class, semID);
            ModuleEntity mod = new ModuleEntity(moduleCode,name,modularCredit,description,sem);
            em.persist(mod);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }        
    }

    @Override
    public ArrayList getAllModules() {
        ArrayList moduleList = new ArrayList<ArrayList>();
        ModuleEntity mod = new ModuleEntity();

        //Find all active modules
        Query q1 = em.createQuery("SELECT m FROM Module m WHERE m.activeStatus=1");

        for(Object o : q1.getResultList()){
            mod = (ModuleEntity) o;
            ArrayList modInfo = new ArrayList();
            modInfo.add(mod.getModuleCode());
            modInfo.add(mod.getName());
            modInfo.add(String.valueOf(mod.getModularCredit()));
            modInfo.add(String.valueOf(mod.getSemester().getId()));
            modInfo.add(String.valueOf(mod.getSemester().getTitle()));
            moduleList.add(modInfo);
        }
        return moduleList;
    }

    @Override
    public void deactivateModule(String id) {
        em.find(ModuleEntity.class,id).setActiveStatus(Boolean.FALSE);
    }
}
