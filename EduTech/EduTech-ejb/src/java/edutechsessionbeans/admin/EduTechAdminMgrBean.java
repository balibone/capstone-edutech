/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package edutechsessionbeans.admin;

import commoninfrastructureentities.UserEntity;
import edutechentities.LessonEntity;
import edutechentities.ModuleEntity;
import edutechentities.RecurringEventEntity;
import edutechentities.ScheduleItemEntity;
import edutechentities.SemesterEntity;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
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
        return em.createQuery("SELECT COUNT(DISTINCT m.moduleCode) FROM Module m").getSingleResult();
        
    }
    
    @Override
    public Object getSemesterCount() {
        return em.createQuery("SELECT COUNT(DISTINCT s.id) FROM Semester s").getSingleResult();
    }
    
    @Override
    public boolean createSemester(String title, String startDate, String endDate) {
        try{
            LocalDate startD = LocalDate.parse(startDate);
            LocalDate endD = LocalDate.parse(endDate);
            
            //if new semester overlaps with an existing semester, disallow creation.
            Query q1 = em.createQuery("SELECT s FROM Semester s");
            for(Object o : q1.getResultList()){
                SemesterEntity sem = (SemesterEntity) o;
                LocalDate thisStartDate = sem.getStartDate();
                LocalDate thisEndDate = sem.getEndDate();
                
                //Case 1 : new sem start date is in between start and end.
                if(startD.equals(thisStartDate)
                        || (startD.isAfter(thisStartDate) && startD.isBefore(thisEndDate)
                        || startD.equals(thisEndDate))
                        ){
                    return false;
                }
                //Case 2 : new sem end date is between start and end.
                else if(endD.equals(thisStartDate)
                        || (endD.isAfter(thisStartDate) && endD.isBefore(thisEndDate)
                        || endD.equals(thisEndDate))
                        ){
                    return false;
                }
                //Case 3 : new event start date is before start & new event end date is on or after end
                else if(startD.isBefore(thisStartDate) && endD.isAfter(thisEndDate))
                {
                    return false;
                }
                
            }
            
            SemesterEntity sem = new SemesterEntity(title,startD,endD);
            em.persist(sem);
            return true;
        }catch(DateTimeParseException e){
            System.out.println("Error parsing date from HTML input!");
            return false;
        }
    }
    
    @Override
    public ArrayList getAllSemesters() {
        ArrayList semesterList = new ArrayList<ArrayList>();
        SemesterEntity sem = new SemesterEntity();
        //format LocalDate to String in format of e.g. 05 July 2019
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        //Find all active semesters
        Query q1 = em.createQuery("SELECT s FROM Semester s");
        //Find the number of unique modules under this semester
        Query q2 = em.createQuery("SELECT COUNT(m) FROM Module m WHERE m.semester = :semester");
        for(Object o : q1.getResultList()){
            sem = (SemesterEntity) o;
            ArrayList semInfo = new ArrayList();
            semInfo.add(sem.getId());
            semInfo.add(sem.getTitle());
            q2.setParameter("semester", sem);
            semInfo.add(q2.getSingleResult());
            semInfo.add(dtf.format(sem.getStartDate()));
            semInfo.add(dtf.format(sem.getEndDate()));
            semesterList.add(semInfo);
        }
        return semesterList;
    }
    
    @Override
    public ArrayList getSemesterInfo(String id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        ArrayList semInfo = new ArrayList();
        SemesterEntity sem = em.find(SemesterEntity.class, Long.parseLong(id));
        semInfo.add(sem.getId());
        semInfo.add(sem.getTitle());
        semInfo.add(dtf.format(sem.getStartDate()));
        semInfo.add(dtf.format(sem.getEndDate()));
        
        //get list of modules in this semester
        List modules = sem.getModules();
        //store module information for all modules in this semester
        ArrayList moduleInfoList = new ArrayList();
        for(Object o : modules){
            ModuleEntity mod = (ModuleEntity)o;
            
            //store module information for each module
            ArrayList moduleInfo = new ArrayList();
            
            moduleInfo.add(mod.getModuleCode());
            moduleInfo.add(mod.getTitle());
            moduleInfoList.add(moduleInfo);
            
        }
        
        semInfo.add(moduleInfoList);
        semInfo.add(moduleInfoList.size());
        return semInfo;
    }
    
    @Override
    public void deleteSemester(String id) {
        SemesterEntity sem = em.find(SemesterEntity.class, Long.valueOf(id));
        
        
        for(ModuleEntity mod : sem.getModules()){
            mod.setSemester(null);
        }
        em.remove(sem);
    }
    
    @Override
    public boolean editSemester(String title, String startDate, String endDate, String id) {
        try {
            LocalDate startD = LocalDate.parse(startDate);
            LocalDate endD = LocalDate.parse(endDate);
            
            //if new semester overlaps with an existing semester, disallow creation.
            Query q1 = em.createQuery("SELECT s FROM Semester s WHERE s.id <> :semID");
            q1.setParameter("semID", Long.valueOf(id));
            for(Object o : q1.getResultList()){
                SemesterEntity sem = (SemesterEntity) o;
                LocalDate thisStartDate = sem.getStartDate();
                LocalDate thisEndDate = sem.getEndDate();
                
                //Case 1 : new sem start date is in between start and end.
                if(startD.equals(thisStartDate)
                        || (startD.isAfter(thisStartDate) && startD.isBefore(thisEndDate)
                        || startD.equals(thisEndDate))
                        ){
                    return false;
                }
                //Case 2 : new sem end date is between start and end.
                else if(endD.equals(thisStartDate)
                        || (endD.isAfter(thisStartDate) && endD.isBefore(thisEndDate)
                        || endD.equals(thisEndDate))
                        ){
                    return false;
                }
                //Case 3 : new event start date is before start & new event end date is on or after end
                else if(startD.isBefore(thisStartDate) && endD.isAfter(thisEndDate))
                {
                    return false;
                }
            }

            SemesterEntity sem = em.find(SemesterEntity.class,Long.valueOf(id));
            sem.setTitle(title);
            sem.setStartDate(startD);
            sem.setEndDate(endD);
            return true;
        } catch (DateTimeParseException ex) {
            System.out.println("Error parsing date from HTML input!");
            return false;
        }
    }
    
    @Override
    public boolean createModule(String moduleCode, String name, Long modularCredit, String description, Long semID) {
        try{
            SemesterEntity sem = em.find(SemesterEntity.class, semID);
            ModuleEntity mod = new ModuleEntity(moduleCode,name,modularCredit,description,sem);
            sem.getModules().add(mod);
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
        Query q1 = em.createQuery("SELECT m FROM Module m");
        
        for(Object o : q1.getResultList()){
            mod = (ModuleEntity) o;
            ArrayList modInfo = new ArrayList();
            modInfo.add(mod.getModuleCode());
            modInfo.add(mod.getTitle());
            modInfo.add(String.valueOf(mod.getModularCredit()));
            if(mod.getSemester() != null){
                modInfo.add(String.valueOf(mod.getSemester().getId()));
                modInfo.add(String.valueOf(mod.getSemester().getTitle()));
            }else{
                modInfo.add("");
                modInfo.add("");
            }
            moduleList.add(modInfo);
        }
        return moduleList;
    }
    
    @Override
    public void deleteModule(String id) {
        ModuleEntity mod = em.find(ModuleEntity.class,id);
        Query q1 = em.createQuery("SELECT s FROM Semester s");
        if(mod!=null){
            for(Object o : q1.getResultList()){
                SemesterEntity s = (SemesterEntity) o;
                Collection<ModuleEntity> mods = s.getModules();
                if(s.getModules().contains(mod)){
                    System.out.println("BEFORE: "+Arrays.toString(mods.toArray()));
                    s.getModules().remove(mod);
                    System.out.println(mod.getTitle()+" removed from "+s.getTitle());
                    System.out.println("AFTER: "+Arrays.toString(mods.toArray()));
                }
            }
            mod.setModuleEvents(null);
            mod.setMembers(null);
            em.flush();
            em.remove(mod);
        }
    }
    
    @Override
    public ArrayList getModuleInfo(String id) {
        
        ArrayList modInfo = new ArrayList();
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        modInfo.add(String.valueOf(mod.getModuleCode()));
        modInfo.add(String.valueOf(mod.getTitle()));
        modInfo.add(String.valueOf(mod.getModularCredit()));
        if(mod.getSemester() != null){
            modInfo.add(String.valueOf(mod.getSemester().getTitle()+" | ID: "+mod.getSemester().getId()));
        }else{
            modInfo.add("");
        }
        modInfo.add(String.valueOf(mod.getDescription()));
        //get list of users for this module
        Collection users = mod.getMembers();
        //store information of all users in this module
        ArrayList userInfoList = new ArrayList();
        for(Object o : users){
            UserEntity user = (UserEntity)o;
            ArrayList userInfo = new ArrayList();
            userInfo.add(String.valueOf(user.getUsername()));
            userInfo.add(String.valueOf(user.getUserSalutation()+" "+user.getUserFirstName()+" "+user.getUserLastName()));
            String userType = user.getUserType();
            switch(userType){
                case "student":
                    userType = "Student";
                    break;
                case "instructor":
                    userType = "Instructor";
                    break;
                case "unifyadmin":
                    userType="Unify Admin";
                    break;
                case "edutechadmin":
                    userType="EduTech Admin";
                    break;
                case "dualadmin":
                    userType="Dual Admin";
                    break;
                case "superadmin":
                    userType="System Admin";
                    break;
                default:
                    break;
            }
            userInfo.add(userType);
            userInfoList.add(userInfo);
            
        }
        
        modInfo.add(userInfoList);
        modInfo.add(userInfoList.size());
        
        Collection recurringEvents = mod.getRecurringEvents();
        //store information of all recurring events in this module
        ArrayList eventInfoList = new ArrayList();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
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
            mod.setTitle(name);
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
    public boolean addEventToMod(String title, String location, String day, String startTime, String endTime, String description, String id) {
        ModuleEntity mod = em.find(ModuleEntity.class,id);
        //creates new event and adds in the attribute values
        RecurringEventEntity event = new RecurringEventEntity();
        event.setTitle(title);
        event.setLocation(location);
        event.setDayOfWeek(DayOfWeek.valueOf(day.toUpperCase()));
        System.out.println("PERSISTED DAY OF WEEK INT VALUE IS "+DayOfWeek.valueOf(day.toUpperCase()).getValue());
        LocalTime receivedStartTime = LocalTime.parse(startTime);
        LocalTime receivedEndTime = LocalTime.parse(endTime);
        
        //if there is already a recurring event for this location at this time, disallow creation.
        Query q1 = em.createQuery("SELECT r FROM RecurringEvent r");
        for(Object o : q1.getResultList()){
            RecurringEventEntity recur = (RecurringEventEntity) o;
            //if new event falls in the same day as existing event.
            if(recur.getDayOfWeek().equals(event.getDayOfWeek())){
                LocalTime thisStartTime = recur.getStartTime();
                LocalTime thisEndTime = recur.getEndTime();
                //if recurring event has same location with the one to be created,
                //disallow creation if there is overlap
                if(recur.getLocation().equalsIgnoreCase(location.trim())){
                    //Case 1 : new event start time is in between start and end.
                    if(receivedStartTime.equals(thisStartTime)
                            || (receivedStartTime.isAfter(thisStartTime) && receivedStartTime.isBefore(thisEndTime))
                            || (receivedStartTime.equals(thisEndTime))
                            ){
                        return false;
                    }
                    //Case 2 : new event end time is between start and end.
                    else if(receivedEndTime.equals(thisStartTime)
                            || (receivedEndTime.isAfter(thisStartTime) && receivedEndTime.isBefore(thisEndTime))
                            || (receivedEndTime.equals(thisEndTime))
                            ){
                        return false;
                    }
                    //Case 3 : new event start is before start & new event end is after end
                    else if(receivedStartTime.isBefore(thisStartTime) && receivedEndTime.isAfter(thisEndTime)
                            ){
                        return false;
                    }
                }
            }
        }
        event.setStartTime(receivedStartTime);
        event.setEndTime(receivedEndTime);
        event.setDescription(description);
        event.setModule(mod);
        //persist new event
        em.persist(event);
        //adds new event to this module.
        mod.getRecurringEvents().add(event);
        
        //CREATES LESSONS for this module according to this recurring event
        //Lesson generation start
        SemesterEntity currSem = mod.getSemester();
        DayOfWeek semStartDay = currSem.getStartDate().getDayOfWeek();
        //conversion = first lesson to create.
        LessonEntity conversion = new LessonEntity();
        conversion.setRecurringEvent(event);
        event.getLessons().add(conversion);
        //get difference in days between sem start day and event day.
        //get value returns 1 to 7. sem start day value = 1 (Monday)
        int difference = event.getDayOfWeek().getValue() - semStartDay.getValue();
        LocalDate eventStartDate = null;
        System.out.println("sem start date: "+currSem.getStartDate());
        
        if(difference == 0){//event day is same as sem start day (Monday)
            //sets start date of new schedule to start date of semester, combined with start time in recurring event.
            eventStartDate = currSem.getStartDate();
            conversion.setStartDate(eventStartDate.atTime(event.getStartTime()));
            conversion.setEndDate(eventStartDate.atTime(event.getEndTime()));
            System.out.println("Event also on monday.");
            System.out.println("event start date: "+conversion.getStartDate());
            System.out.println("event end date: " +conversion.getEndDate());
        }else if(difference > 0){//event day is after sem start day
            //set first start date this week.
            eventStartDate = currSem.getStartDate().plusDays(difference);
            conversion.setStartDate(eventStartDate.atTime(event.getStartTime()));
            conversion.setEndDate(eventStartDate.atTime(event.getEndTime()));
            System.out.println("Event is "+difference+" days after monday.");
            System.out.println("event start date: "+conversion.getStartDate());
            System.out.println("event end date: " +conversion.getEndDate());
        }
        //set description
        conversion.setDescription(event.getDescription());
        //set location
        conversion.setLocation(event.getLocation());
        //set title
        conversion.setTitle(event.getTitle());
        //set type
        conversion.setItemType("timetable");
        //set moduleCode
        conversion.setModuleCode(event.getModule().getModuleCode());
        //set createdAt
        conversion.setCreatedAt(LocalDateTime.now());
        //set assignedTo
        conversion.setAssignedTo(mod.getMembers());
        //add lesson to mod's events
        mod.getModuleEvents().add(conversion);
        //add 1st lesson item into list.
        em.persist(conversion);
        
        LocalDateTime tempStartDate = conversion.getStartDate().plusWeeks(1);
        LocalDateTime tempEndDate = conversion.getEndDate().plusWeeks(1);
        //stop this while loop only when the (endDate+1 week) of new lesson entity is later than sem end date 2359.
        while(!tempEndDate.isAfter(currSem.getEndDate().atTime(23, 59))){
            //temp is used to temporarily store new lesson entity (the next week's one)
            LessonEntity temp = new LessonEntity();
            temp.setRecurringEvent(event);
            event.getLessons().add(temp);
            temp.setDescription(event.getDescription());
            temp.setLocation(event.getLocation());
            temp.setTitle(event.getTitle());
            temp.setItemType("timetable");
            temp.setCreatedAt(LocalDateTime.now());
            temp.setAssignedTo(mod.getMembers());
            temp.setModuleCode(mod.getModuleCode());
            //set start date as 1 week after (time is actually already inside because this value is actually timestamp)
            temp.setStartDate(tempStartDate);
            //do same for end date
            temp.setEndDate(tempEndDate);
            System.out.println("temp lesson start date: "+temp.getStartDate());
            System.out.println("temp lesson end date: "+temp.getEndDate());
            //add lesson to mod events
            mod.getModuleEvents().add(temp);
            em.persist(temp);
            tempStartDate = tempStartDate.plusWeeks(1);
            tempEndDate = tempEndDate.plusWeeks(1);
        }
        // Lesson generation end
        return true;
    }
    
    @Override
    public ArrayList getAllModulesOfUser(String id) {
        ArrayList moduleList = new ArrayList<ArrayList>();
        ModuleEntity mod = new ModuleEntity();
        //get user with this username
        UserEntity user = em.find(UserEntity.class, id);
        
        //Find all active modules
        Query q1 = em.createQuery("SELECT m FROM Module m");
        
        for(Object o : q1.getResultList()){
            mod = (ModuleEntity) o;
            //if the user is one of the users in this module extract module info.
            if(mod.getMembers().contains(user)){
                ArrayList modInfo = new ArrayList();
                modInfo.add(mod.getModuleCode());
                modInfo.add(mod.getTitle());
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
        if(!module.getMembers().contains(user)){
            module.getMembers().add(user);
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
        if(module.getMembers().contains(user)){
            module.getMembers().remove(user);
        }
    }
    
    @Override
    public ArrayList getCurrentSemester() {
        LocalDate currDate = LocalDate.now();
        ArrayList semInfo = new ArrayList();
        Query q1 = em.createQuery("SELECT s FROM Semester s");
        for(Object o:q1.getResultList()){
            SemesterEntity s = (SemesterEntity) o;
            LocalDate startDate = s.getStartDate();
            LocalDate endDate = s.getEndDate();
            //if semester starts before or on today's date and end after or on today's date, then it is current semester
            //assumption : there are no 2 sems which dates overlap.
            if( (startDate.isBefore(currDate) || startDate.isEqual(currDate)) && (endDate.isAfter(currDate) || endDate.isEqual(currDate)) ){
                semInfo.add(s.getTitle());//get title
                //System.out.println("SEM TITLE IS: "+s.getTitle());
                semInfo.add(String.valueOf(s.getModules().size()));//get number of modules
                //System.out.println("No. OF MODULES ARE: "+s.getModules().size());
            }
        }
        return semInfo;
    }
    
    @Override
    public void removeEventFromMod(String eventId, String id) {
        RecurringEventEntity event = em.find(RecurringEventEntity.class, Long.valueOf(eventId));
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        //detach event from module
        if(mod.getRecurringEvents().contains(event)){
            mod.getRecurringEvents().remove(event);
        }
        //detach module from event
        event.setModule(null);
        //detach lessons from module
        mod.setModuleEvents(null);
        //remove event and cascade delete of lessons
        em.remove(event);
    }
    
    @Override
    public ArrayList getModuleRecurringEvents(String id) {
        ArrayList eventList = new ArrayList();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        ModuleEntity mod = em.find(ModuleEntity.class, id);
        if(mod.getRecurringEvents() != null){
            for(RecurringEventEntity event : mod.getRecurringEvents()){
                ArrayList eventInfo = new ArrayList();
                eventInfo.add(event.getTitle());
                eventInfo.add(event.getDescription());
                eventInfo.add(event.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
                eventInfo.add(event.getStartTime().format(timeFormat));
                eventInfo.add(event.getEndTime().format(timeFormat));
                eventInfo.add(String.valueOf(event.getId()));
                eventInfo.add(event.getLocation());
                eventList.add(eventInfo);
            }
        }
        return eventList;
    }
}
