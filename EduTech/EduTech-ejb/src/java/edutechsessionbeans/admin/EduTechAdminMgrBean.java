/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans.admin;

import commoninfrastructureentities.UserEntity;
import edutechentities.ModuleEntity;
import edutechentities.RecurringEventEntity;
import edutechentities.ScheduleItemEntity;
import edutechentities.SemesterEntity;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
    public void deleteSemester(String id) {
        SemesterEntity sem = em.find(SemesterEntity.class, Long.valueOf(id));
        em.remove(sem);
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
    public void deleteModule(String id) {
        em.remove(em.find(ModuleEntity.class,id));
    }

    @Override
    public ArrayList getModuleInfo(String id) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        
        ArrayList modInfo = new ArrayList();
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        modInfo.add(String.valueOf(mod.getModuleCode()));
        modInfo.add(String.valueOf(mod.getName()));
        modInfo.add(String.valueOf(mod.getModularCredit()));
        modInfo.add(String.valueOf(mod.getSemester().getTitle()+" | ID: "+mod.getSemester().getId()));
        modInfo.add(String.valueOf(mod.getDescription()));
        //get list of users for this module
        Collection users = mod.getUsers();
        //store information of all users in this module
        ArrayList userInfoList = new ArrayList();
        for(Object o : users){
            UserEntity user = (UserEntity)o;
            //only extract module info if user is active.
            if(user.getUserActiveStatus()){
                ArrayList userInfo = new ArrayList();
                userInfo.add(String.valueOf(user.getUsername()));
                userInfo.add(String.valueOf(user.getUserSalutation()+" "+user.getUserFirstName()+" "+user.getUserLastName()));
                userInfoList.add(userInfo);
            }
        }

        modInfo.add(userInfoList);
        modInfo.add(userInfoList.size());
        
        Collection recurringEvents = mod.getRecurringEvents();
        //store information of all recurring events in this module
        ArrayList eventInfoList = new ArrayList();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        for(Object o : recurringEvents){
            RecurringEventEntity event = (RecurringEventEntity)o;
            ArrayList eventInfo = new ArrayList();
            eventInfo.add(String.valueOf(event.getTitle()));
            eventInfo.add(event.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            eventInfo.add(event.getStartTime().format(timeFormat));
            eventInfo.add(event.getEndTime().format(timeFormat));
            
            eventInfoList.add(eventInfo);
        }
        modInfo.add(eventInfoList);
        return modInfo;
    }

    @Override
    public boolean editModule(String id, String name, String credits, String description) {
        try{
            ModuleEntity mod = em.find(ModuleEntity.class, id);
            mod.setName(name);
            mod.setModularCredit(Long.valueOf(credits));
            mod.setDescription(description);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error in editModule() **********************************");
            return false;
        }
    }

    @Override
    public void addEventToMod(String title, String location, String day, String startTime, String endTime, String description, String id) {
        //creates new event and adds in the attribute values
        RecurringEventEntity event = new RecurringEventEntity();
        event.setTitle(title);
        event.setLocation(location);
        event.setDayOfWeek(DayOfWeek.valueOf(day.toUpperCase()));
        System.out.println("START TIME IS "+startTime);
        event.setStartTime(LocalTime.parse(startTime));
        System.out.println("END TIME IS "+endTime);
        event.setEndTime(LocalTime.parse(endTime));
        event.setDescription(description);
        //persist new event
        em.persist(event);
        //adds new event to this module.
        em.find(ModuleEntity.class,id).getRecurringEvents().add(event);
    }

    @Override
    public ArrayList getAllModulesOfUser(String id) {
        ArrayList moduleList = new ArrayList<ArrayList>();
        ModuleEntity mod = new ModuleEntity();
        //get user with this username
        UserEntity user = em.find(UserEntity.class, id);

        //Find all active modules
        Query q1 = em.createQuery("SELECT m FROM Module m WHERE m.activeStatus=1");
        
        for(Object o : q1.getResultList()){
            mod = (ModuleEntity) o;
            //if the user is one of the users in this module extract module info.
            if(mod.getUsers().contains(user)){
                ArrayList modInfo = new ArrayList();
                modInfo.add(mod.getModuleCode());
                modInfo.add(mod.getName());
                modInfo.add(String.valueOf(mod.getModularCredit()));
                modInfo.add(String.valueOf(mod.getSemester().getId()));
                modInfo.add(String.valueOf(mod.getSemester().getTitle()));
                moduleList.add(modInfo);
            }
        }
        return moduleList;
    }

    @Override
    public boolean addUserToMod(String id, String mod) {
        UserEntity user = em.find(UserEntity.class, id);
        ModuleEntity module = em.find(ModuleEntity.class, mod);
        //if module doesn't already contain user, add in.
        if(!module.getUsers().contains(user)){
            module.getUsers().add(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void unassignModule(String id, String mod) {
        UserEntity user = em.find(UserEntity.class, id);
        ModuleEntity module = em.find(ModuleEntity.class, mod);
        //if module doesn't already contain user, add in.
        if(module.getUsers().contains(user)){
            module.getUsers().remove(user);
        }
    }

    @Override
    public ArrayList getCurrentSemester() {
        Date currDate = new Date();
        ArrayList semInfo = new ArrayList();
        Query q1 = em.createQuery("SELECT s FROM Semester s");
        for(Object o:q1.getResultList()){
            SemesterEntity s = (SemesterEntity) o;
            Date startDate = s.getStartDate();
            Date endDate = s.getEndDate();
            //if semester starts before or on today's date and end after or on today's date, then it is current semester
            //assumption : there are no 2 sems which dates overlap.
            if( (startDate.before(currDate) || endDate.equals(currDate)) && (endDate.after(currDate) || endDate.equals(currDate)) ){
                semInfo.add(s.getTitle());//get title
                semInfo.add(String.valueOf(s.getModules().size()));//get number of modules
            }
        }
        return semInfo;
    }
}
