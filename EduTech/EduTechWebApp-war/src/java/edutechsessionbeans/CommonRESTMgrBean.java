/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;


import commoninfraentities.UserEntity;
import edutechentities.common.PostEntity;
import edutechentities.common.RecurringEventEntity;
import edutechentities.group.GroupEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.common.SemesterEntity;
import edutechentities.common.TaskEntity;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
public class CommonRESTMgrBean {

    @PersistenceContext
    private EntityManager em;
    
    // HELPER METHOD 
    public String getCurrentISODate() {
        // get current time in ISO 8601 format
//        TimeZone tz = TimeZone.getTimeZone("UTC");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); 
//        df.setTimeZone(tz);
//        String nowAsISO = df.format(new Date());
        return LocalDateTime.now().toString();
    }
    
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
        em.merge(old);
    }

    public List<UserEntity> findAllUsers() {
        return em.createQuery("SELECT s FROM SystemUser s WHERE s.useractivestatus=1").getResultList();
    }

    public UserEntity findUser(String username) {
        return em.find(UserEntity.class, username);
    }

    public void removeUser(String id) {
        em.remove(em.find(UserEntity.class, id));
    }

    public String countUsers() {
        return  String.valueOf(em.createQuery("SELECT COUNT(s) FROM SystemUser s WHERE s.useractivestatus=1").getSingleResult());
    }
    
    public List<ScheduleItemEntity> findAllScheduleItems(){
        return em.createQuery("SELECT s FROM ScheduleItem s").getResultList();
    }

    public void createScheduleItem(ScheduleItemEntity entity) {
        entity.setCreatedAt(LocalDateTime.parse(getCurrentISODate()));
        // get proper User - initially json createdBy only contains a username key
        UserEntity user = (UserEntity) entity.getCreatedBy();
        //Set assignedTo
        Collection<UserEntity> assignedTo = new ArrayList(); 
        switch(entity.getType()) {
            case "personal":
                assignedTo.add(user); 
                break;
            case "meeting":
                GroupEntity group = em.find(GroupEntity.class, Long.valueOf(entity.getGroupId()));
                assignedTo = group.getMembers();
                break;
            default:
                assignedTo.add(user); 
                break;
        }
        entity.setAssignedTo(assignedTo);
        //persist
        em.persist(entity);
    }

    public void editScheduleItem(String id, ScheduleItemEntity entity) {
        ScheduleItemEntity toEdit = em.find(ScheduleItemEntity.class, Long.valueOf(id));
        toEdit = entity;
        em.merge(toEdit);
    }

    public void removeScheduleItem(String id) {
        em.remove(em.find(ScheduleItemEntity.class, Long.valueOf(id)));
    }

    public ScheduleItemEntity findScheduleItem(String id) {
        return em.find(ScheduleItemEntity.class, Long.valueOf(id));
    }
    
    public List<ScheduleItemEntity> findGroupScheduleItems(int groupId){
        Collection<UserEntity> members = em.find(GroupEntity.class, Long.valueOf(groupId)).getMembers();
        List<ScheduleItemEntity> membersScheduleItem = new ArrayList();
        List<ScheduleItemEntity> temp = new ArrayList();
        for(UserEntity member: members){
            temp = findUserScheduleItems(member.getUsername());
            temp.removeAll(membersScheduleItem);
            membersScheduleItem.addAll(temp);
//            membersScheduleItem.addAll();
        }
        return membersScheduleItem;
    }
    
    public List<ScheduleItemEntity> findUserScheduleItems(String username) {
        UserEntity user = em.find(UserEntity.class, username);
        //to store all schedule items for this user. (immediate schedule items + converted recurring events)
        List<ScheduleItemEntity> userScheduleItems = new ArrayList();
        
        //GET ALL SCHEDULE ITEMS
        List<ScheduleItemEntity> allScheduleItems = em.createQuery("SELECT s FROM ScheduleItem s").getResultList();
        //GET IMMEDIATE SCHEDULE ITEMS
        for(ScheduleItemEntity scheduleItem: allScheduleItems){
            if(scheduleItem.getAssignedTo().contains(user)){
                userScheduleItems.add(scheduleItem);
            }
        }
        //GET CURRENT SEMESTER
        List<SemesterEntity> sems = em.createQuery("SELECT s FROM Semester s").getResultList();
        LocalDate currDate = LocalDate.now();
        SemesterEntity currSem = null;
        for(SemesterEntity sem : sems){
            LocalDate startDate = sem.getStartDate();
            LocalDate endDate = sem.getEndDate();
            //if semester starts before or on today's date and end after or on today's date, then it is current semester
            //ASSUMPTION : there are no 2 sems with overlapping dates.
            if( (startDate.isBefore(currDate) || endDate.isEqual(currDate)) && (endDate.isAfter(currDate) || endDate.isEqual(currDate)) ){
                currSem = sem;
            }
        }
        
        List<RecurringEventEntity> allRecurringEvents = new ArrayList();
        //GET ALL RECURRING EVENTS OF THIS USER (AKA RECURRING EVENTS OF MODULES THIS USER IS IN)
        Query q1 = em.createQuery("SELECT r FROM RecurringEvent r");
        //For all modules which the user is in, get the recurring events and add in to his list of recurring events. 
        for(Object o: q1.getResultList()){
            RecurringEventEntity event = (RecurringEventEntity) o;
            if(event.getModule().getMembers().contains(user)){
                allRecurringEvents.add(event);
            }
        }
        //convert recurring event to schedule item here, by checking against currSem's start date and end date.
        /*
        Create 1st schedule item on the first DayOfWeek on or after the sem start date. 
        Subsequently create one event every week (+7 days), and stop when end date of latest created schedule item 
        overshoots sem end date. 
        */
        if(currSem!=null){
            DayOfWeek semStartDay = currSem.getStartDate().getDayOfWeek();//get sem start day.
            System.out.println(Arrays.toString(allRecurringEvents.toArray()));
            for(RecurringEventEntity event : allRecurringEvents){
                //conversion = first schedule item to create.
                ScheduleItemEntity conversion = new ScheduleItemEntity();
                //get difference in days between sem start day and event day.
                int difference = event.getDayOfWeek().compareTo(semStartDay);
                LocalDate eventStartDate = null;
                if(difference == 0){//event day is same as sem start day
                    //sets start date of new schedule to start date of semester, combined with start time in recurring event.
                    eventStartDate = currSem.getStartDate();
                    conversion.setStartDate(eventStartDate.atTime(event.getStartTime()));
                    conversion.setEndDate(eventStartDate.atTime(event.getEndTime()));
                }else if(difference < 0 ){//event day is before sem start day
                    //set first start date next week instead.
                    eventStartDate = currSem.getStartDate().plusDays(7+difference);
                    conversion.setStartDate(eventStartDate.atTime(event.getStartTime()));
                    conversion.setEndDate(eventStartDate.atTime(event.getEndTime()));
                }else if(difference > 0){//event day is after sem start day
                    //set first start date this week.
                    eventStartDate = currSem.getStartDate().plusDays(difference);
                    conversion.setStartDate(eventStartDate.atTime(event.getStartTime()));
                    conversion.setEndDate(eventStartDate.atTime(event.getEndTime()));
                }
                //set description
                conversion.setDescription(event.getDescription());
                //set location
                conversion.setLocation(event.getLocation());
                //set title
                conversion.setTitle(event.getTitle());
                //set type
                conversion.setType("timetable");
                //set moduleCode
                conversion.setModuleCode(event.getModule().getModuleCode());
                userScheduleItems.add(conversion);
                //stop this while loop only when the (endDate+1 week) of new schedule entity is later than sem end date 2359.
                while(!conversion.getEndDate().plusWeeks(1).isAfter(currSem.getEndDate().atTime(23, 59))){
                    //temp is used to temporarily store new schedule item entity (the next week's one)
                    ScheduleItemEntity temp = conversion;
                    //set start date as 1 week after (time is actually already inside because this value is actually timestamp)
                    temp.setStartDate(conversion.getStartDate().plusWeeks(1));
                    //do same for end date
                    temp.setEndDate(conversion.getEndDate().plusWeeks(1));
                    conversion = temp;
                    userScheduleItems.add(conversion);
                }
            }
        }
        return userScheduleItems;
    }
    
    public List<ScheduleItemEntity> findGroupMeetings(int groupId) {
        List<ScheduleItemEntity> groupScheduleItems = new ArrayList();
        List<ScheduleItemEntity> allScheduleItems = em.createQuery("SELECT s FROM ScheduleItem s").getResultList();
        for(ScheduleItemEntity scheduleItem: allScheduleItems){
            if(scheduleItem.getGroupId() == groupId){
                groupScheduleItems.add(scheduleItem);
            }
        }
        return groupScheduleItems;    
    }

    public String countScheduleItems() {
        return String.valueOf(em.createQuery("SELECT COUNT(s) FROM ScheduleItem s").getSingleResult());
    }

    public List<PostEntity> findPagePosts(String pageId) {
        return em.createQuery("SELECT p FROM Post p WHERE p.pageId="+pageId).getResultList(); //To change body of generated methods, choose Tools | Templates.
    }

    public void createPost(PostEntity entity) {
        entity.setCreatedAt(getCurrentISODate());
        // persist
        em.persist(entity);
    }

    public void removePost(String id) {
        PostEntity entity = em.find(PostEntity.class, Long.valueOf(id));
        //if post is a reply, remove Post from parent Post first
        if(entity.getReplyTo() != null) {
            PostEntity parentPost = em.find(PostEntity.class, entity.getReplyTo());
            Collection<PostEntity> replies = parentPost.getReplies();
            replies.remove(entity);
            parentPost.setReplies(replies);
        }
        em.remove(entity);
    }

    public void togglePinPost(String id) {
        PostEntity post = em.find(PostEntity.class, Long.valueOf(id));
        post.setIsPinned(!post.getIsPinned());
    }

    public void replyPost(String id, PostEntity entity) {
        entity.setCreatedAt(getCurrentISODate());
        entity.setReplyTo(Long.valueOf(id));
        // set pageId to null so it wont show as main post on the feed page
        entity.setPageId(null);
        // find the parent post to include this post reply in
        PostEntity post = em.find(PostEntity.class, Long.valueOf(id));
        Collection<PostEntity> replies = post.getReplies();
        replies.add(entity);
        post.setReplies(replies);
    }
    
    public List<TaskEntity> findUserTasks(String username){
        UserEntity user = em.find(UserEntity.class, username);
        List<TaskEntity> userTasks = new ArrayList();
        List<TaskEntity> allTasks = em.createQuery("SELECT t FROM Task t").getResultList();
        for(TaskEntity task: allTasks){
            if(task.getAssignedTo().contains(user)){
                userTasks.add(task);
            }
        }
        return userTasks;
    }
    
    public List<TaskEntity> findGroupTasks(int groupId) {
        List<TaskEntity> groupTasks = new ArrayList();
        List<TaskEntity> allTasks = em.createQuery("SELECT t FROM Task t").getResultList();
        for(TaskEntity task: allTasks){
            if(task.getGroupId() == groupId){
                groupTasks.add(task);
            }
        }
        return groupTasks;
    }

    public void createTask(TaskEntity entity) {
        //set CreatedAt
        entity.setCreatedAt(getCurrentISODate());
        // set assignedTo
        Collection<UserEntity> assignedTo = new ArrayList(); 
        switch(entity.getType()) {
            case "group":
                break;
            case "personal":
            default:
                UserEntity taskOwner = (UserEntity) entity.getCreatedBy(); 
                assignedTo.add(taskOwner);
                entity.setAssignedTo(assignedTo);
        }            
        // persist
        em.persist(entity);    
    }

    public void removeTask(String id) {
        em.remove(em.find(TaskEntity.class, Long.valueOf(id)));
    }

    public void editTask(String id, TaskEntity entity) {
        TaskEntity thisEntity = em.find(TaskEntity.class, Long.valueOf(id));
        thisEntity = entity;
        //set modifiedAt
        thisEntity.setModifiedAt(getCurrentISODate());
        em.merge(thisEntity);    
    }

    public void updateTaskProgress(String id, int progressCode) {
        TaskEntity task = em.find(TaskEntity.class, Long.valueOf(id)); 
        task.setProgressCode(progressCode);
    }

    public void verifyTask(String id, String username) {
        TaskEntity task = em.find(TaskEntity.class, Long.valueOf(id)); 
        task.setProgressCode(3);
        // set verifiedBy & verifiedAt
        UserEntity verifier = em.find(UserEntity.class, username);
        task.setVerifiedBy(verifier);
        task.setVerifiedAt(getCurrentISODate());
    }

    public void createRecurringEvent(RecurringEventEntity entity) {
        em.persist(entity);
    }
    public void editRecurringEvent(Long id, RecurringEventEntity entity) {
        RecurringEventEntity old = em.find(RecurringEventEntity.class, id);
        old = entity;
        em.merge(old);
    }

    public void removeRecurringEvent(Long id) {
        em.remove(em.find(RecurringEventEntity.class, id));
    }

    public RecurringEventEntity findRecurringEvent(Long id) {
        return em.find(RecurringEventEntity.class, id);
    }

    public List<RecurringEventEntity> findAllRecurringEvents() {
        Query q1 = em.createQuery("SELECT r FROM RecurringEvent r");
        return q1.getResultList() ;
    }

    public void createSemester(SemesterEntity entity) {
        em.persist(entity);
    }

    public void editSemester(Long id, SemesterEntity entity) {
        SemesterEntity old = em.find(SemesterEntity.class, id);
        old = entity;
        em.merge(old);
    }

    public void removeSemester(Long id) {
        em.remove(em.find(SemesterEntity.class, id));
    }

    public SemesterEntity findSemester(Long id) {
        return em.find(SemesterEntity.class, id);    
    }

    public List<SemesterEntity> findAllSemesters() {
        Query q1 = em.createQuery("SELECT s FROM Semester s");
        return q1.getResultList() ;
    }

}
