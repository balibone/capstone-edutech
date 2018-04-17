/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;


import commoninfraentities.UserEntity;
import commoninfrasessionbeans.CommonInfraMgrBeanRemote;
import edutechentities.common.AnnouncementEntity;
import edutechentities.common.AttachmentEntity;
import edutechentities.common.PostEntity;
import edutechentities.common.RecurringEventEntity;
import edutechentities.group.GroupEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.common.SemesterEntity;
import edutechentities.common.TaskEntity;
import edutechentities.group.BrainstormEntity;
import edutechentities.group.IdeaEntity;
import edutechentities.group.MeetingMinuteEntity;
import edutechentities.module.LessonEntity;
import edutechentities.module.ModuleEntity;
import edutechentities.module.AssignmentEntity;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Derian
 */
@Stateless
@LocalBean
public class CommonMgrBean {

    @PersistenceContext
    private EntityManager em;
    @EJB
    CommonInfraMgrBeanRemote cmb;
    
    //for mail
    private Properties mailServerProperties;
    private Session mailSession;
    private MimeMessage mailMessage;
    
    // HELPER METHOD 
    public String getCurrentISODate() {
        return LocalDateTime.now().toString();
    }
    
    public UserEntity createUser(UserEntity entity) {
        entity.setUserCreationDate(new Date());
        entity.setUserActiveStatus(true);
        em.persist(entity);
        return entity;
    }

    public UserEntity editUser(String id, UserEntity entity) {
        //pull current entity based on id (entity is now detached)
        UserEntity old = em.find(UserEntity.class, id);
        //instantiate curr entity into new entity
        old = entity;
        //update curr entity in database. (reattach entity)
        em.merge(old);
        return old;
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> allUsers = em.createQuery("SELECT s FROM SystemUser s WHERE s.userActiveStatus=1").getResultList();
        if(allUsers!=null){
            for(UserEntity u : allUsers){
                em.detach(u);//detach from persistence context so that password removal is not reflected in database.
                u.setUserPassword("hidden");
            }
        }
        return allUsers;
    }

    public UserEntity getOneUser(String username) {
        UserEntity u = em.find(UserEntity.class, username);
        em.detach(u);
        u.setUserPassword("hidden");
        return u;
    }

    public void deleteUser(String id) {
        em.remove(em.find(UserEntity.class, id));
    }

    public String countUsers() {
        return  String.valueOf(em.createQuery("SELECT COUNT(s) FROM SystemUser s WHERE s.userActiveStatus=1").getSingleResult());
    }
    
    public void massCreateUsers(List<UserEntity> userList) {
        for(UserEntity u : userList){
            try {
                UserEntity newUser = new UserEntity();
                newUser.setContactNum(u.getContactNum());
                newUser.setEmail(u.getEmail());
                newUser.setUserFirstName(u.getUserFirstName());
                newUser.setUserLastName(u.getUserLastName());
                newUser.setUserSalutation(u.getUserSalutation());
                newUser.setUserType(u.getUserType());
                newUser.setUsername(u.getUsername());
                newUser.setUserPassword(cmb.encodePassword(u.getUsername(), String.valueOf(Math.random())));
                em.persist(newUser);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CommonMgrBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CommonMgrBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ScheduleItemEntity createScheduleItem(ScheduleItemEntity thisSchedItem) {
        thisSchedItem.setCreatedAt(LocalDateTime.parse(getCurrentISODate()));
        // get proper User - initially json createdBy only contains a username key
        UserEntity user = em.find(UserEntity.class,thisSchedItem.getCreatedBy().getUsername());
        thisSchedItem.setCreatedBy(user);
        //Set assignedTo
        switch(thisSchedItem.getItemType()) {
            case "meeting":
                GroupEntity group = em.find(GroupEntity.class, Long.valueOf(thisSchedItem.getGroupId()));
                if(group!=null){
                    thisSchedItem.setAssignedTo(group.getMembers());
                    MeetingMinuteEntity mm = new MeetingMinuteEntity();
                    //set bidirectional relationship
                    thisSchedItem.setMeetingMinute(mm);
                    mm.setMeeting(thisSchedItem);
                    mm.setCreatedAt(LocalDateTime.now());
                    mm.setStartTime(thisSchedItem.getStartDate());
                    mm.setEndTime(thisSchedItem.getEndDate());
                    //persist
                    em.persist(mm);
                    em.persist(thisSchedItem);
                }
                break;
            case "assessment":
                ModuleEntity mod = em.find(ModuleEntity.class, thisSchedItem.getModuleCode());
                if(mod!=null){
                    thisSchedItem.setAssignedTo(mod.getMembers());
                    
                    //if there is already a recurring event for this location at this time, disallow creation.
                    Query q1 = em.createQuery("SELECT s FROM ScheduleItem s WHERE s.itemType='assessment' OR s.itemType='timetable'");
                    for(Object o : q1.getResultList()){
                        ScheduleItemEntity checkingAgainst = (ScheduleItemEntity) o;
                        LocalTime receivedStartTime = thisSchedItem.getStartDate().toLocalTime();
                        LocalTime receivedEndTime = thisSchedItem.getEndDate().toLocalTime();
                        //if new sched items falls in the same day as an existing event.
                        if(checkingAgainst.getStartDate().equals(thisSchedItem.getStartDate())){
                            LocalTime thisStartTime = checkingAgainst.getStartDate().toLocalTime();
                            LocalTime thisEndTime = checkingAgainst.getEndDate().toLocalTime();
                            //if recurring event has same location with the one to be created,
                            //disallow creation if there is overlap
                            if(checkingAgainst.getLocation().equalsIgnoreCase(thisSchedItem.getLocation().trim())){
                                //Case 1 : new event start time is in between start and end.
                                if(receivedStartTime.equals(thisStartTime)
                                        || (receivedStartTime.isAfter(thisStartTime) && receivedStartTime.isBefore(thisEndTime))
                                        || (receivedStartTime.equals(thisEndTime))
                                        ){
                                    thisSchedItem = null;
                                }
                                //Case 2 : new event end time is between start and end.
                                else if(receivedEndTime.equals(thisStartTime)
                                        || (receivedEndTime.isAfter(thisStartTime) && receivedEndTime.isBefore(thisEndTime))
                                        || (receivedEndTime.equals(thisEndTime))
                                        ){
                                    thisSchedItem = null;
                                }
                                //Case 3 : new event start is before start & new event end is after end
                                else if(receivedStartTime.isBefore(thisStartTime) && receivedEndTime.isAfter(thisEndTime)
                                        ){
                                    thisSchedItem = null;
                                }
                            }
                        }
                    }
                    if(thisSchedItem!=null){
                        em.persist(thisSchedItem);
                    }
                }
                break;
            default://for personal, (task, timetable)<--not using this endpoint.
                thisSchedItem.getAssignedTo().add(user);
                //persist
                em.persist(thisSchedItem);
                break;
        }
        return thisSchedItem;
    }

    public ScheduleItemEntity editScheduleItem(String id, ScheduleItemEntity entity) {
        ScheduleItemEntity toEdit = em.find(ScheduleItemEntity.class, Long.valueOf(id));
        toEdit = entity;
        em.merge(toEdit);
        return entity;
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
        for(UserEntity member: members){
            List<ScheduleItemEntity> eachMemberSchedItems = new ArrayList();    
            eachMemberSchedItems = findUserScheduleItems(member.getUsername());
            eachMemberSchedItems.removeAll(membersScheduleItem);
            membersScheduleItem.addAll(eachMemberSchedItems);
        }
        
        return membersScheduleItem;
    }
    
    public List<ScheduleItemEntity> findUserScheduleItems(String username) {
        UserEntity user = em.find(UserEntity.class, username);
        //to store all schedule items for this user. (schedule items , lessons and converted tasks)
        List<ScheduleItemEntity> userScheduleItems = new ArrayList();
        if(user!=null){
            //GET ALL SCHEDULE ITEMS
            List<ScheduleItemEntity> allScheduleItems = em.createQuery("SELECT s FROM ScheduleItem s").getResultList();
            //GET IMMEDIATE SCHEDULE ITEMS
            for(ScheduleItemEntity scheduleItem: allScheduleItems){
                if(scheduleItem.getAssignedTo().contains(user)){
                    userScheduleItems.add(scheduleItem);
                }
            }
            //Get all tasks assigned to this user.
            Query q1 = em.createQuery("SELECT t FROM Task t");
            for(Object o : q1.getResultList()){
                TaskEntity t = (TaskEntity) o;
                //if task has been assigned to this user and task has deadline,
                if(t.getAssignedTo().contains(user) && t.getDeadline()!=null && t.getProgressCode()<2){
                    //convert task to schedule item and add it to userScheduleItems.
                    ScheduleItemEntity convert = new ScheduleItemEntity();
                    convert.setId(Long.sum(t.getId(), new Random().nextLong()));
                    convert.setAssignedTo(t.getAssignedTo());
                    convert.setCreatedAt(LocalDateTime.parse(t.getCreatedAt()));
                    convert.setCreatedBy(t.getCreatedBy());
                    convert.setStartDate(LocalDateTime.parse(t.getDeadline()));
                    convert.setEndDate(LocalDateTime.parse(t.getDeadline()));
                    convert.setGroupId(t.getGroupId());
                    convert.setItemType("task");
                    convert.setModuleCode(t.getModuleCode());
                    convert.setTitle(t.getTitle());
                    //add converted schedule item into list.
                    userScheduleItems.add(convert);
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
        Query q1 = em.createQuery("SELECT p FROM Post p WHERE p.pageId= :Id ORDER BY p.createdAt DESC"); //To change body of generated methods, choose Tools | Templates.
        q1.setParameter("Id", pageId);
        return q1.getResultList();
    }

    public PostEntity createPost(PostEntity entity) {
        entity.setCreatedAt(getCurrentISODate());
        // persist
        em.persist(entity);
        return entity;
    }

    public void deletePost(String id) {
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

    public PostEntity togglePinPost(String id) {
        PostEntity post = em.find(PostEntity.class, Long.valueOf(id));
        post.setIsPinned(!post.getIsPinned());
        return post;
    }

    public PostEntity replyPost(String id, PostEntity entity) {
        entity.setCreatedAt(getCurrentISODate());
        entity.setReplyTo(Long.valueOf(id));
        // set pageId to null so it wont show as main post on the feed page
        entity.setPageId(null);
        // find the parent post to include this post reply in
        PostEntity post = em.find(PostEntity.class, Long.valueOf(id));
        post.getReplies().add(entity);
        return post;
    }
    
    public List<PostEntity> getAllPosts() {
        return em.createQuery("SELECT p FROM Post p ORDER BY p.createdAt DESC").getResultList();
    }

    public PostEntity getOnePost(String postId) {
        return em.find(PostEntity.class, Long.valueOf(postId));
    }

    public PostEntity editPost(PostEntity editedPost) {
        em.merge(editedPost);
        editedPost.setModifiedAt(getCurrentISODate());
        return editedPost;
    }
    
    public List<TaskEntity> findUserTasks(String username){
        UserEntity user = em.find(UserEntity.class, username);
        List<TaskEntity> userTasks = new ArrayList();
        List<TaskEntity> allTasks = em.createQuery("SELECT t FROM Task t ORDER BY t.deadline, t.createdAt DESC").getResultList();
        for(TaskEntity task: allTasks){
            if(task.getAssignedTo().contains(user)){
                userTasks.add(task);
            }
        }
        return userTasks;
    }
    
    public List<TaskEntity> findGroupTasks(int groupId) {
        List<TaskEntity> groupTasks = new ArrayList();
        List<TaskEntity> allTasks = em.createQuery("SELECT t FROM Task t ORDER BY t.deadline, t.createdAt DESC").getResultList();
        for(TaskEntity task: allTasks){
            if(task.getGroupId() == groupId){
                groupTasks.add(task);
            }
        }
        return groupTasks;
    }

    public TaskEntity createTask(TaskEntity entity) {
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
                break;
        }            
        // persist
        em.persist(entity);    
        return entity;
    }

    public void deleteTask(String id) {
        em.remove(em.find(TaskEntity.class, Long.valueOf(id)));
    }

    public TaskEntity editTask(String id, TaskEntity entity) {
        TaskEntity thisEntity = em.find(TaskEntity.class, Long.valueOf(id));
        thisEntity = entity;
        //set modifiedAt
        thisEntity.setModifiedAt(getCurrentISODate());
        em.merge(thisEntity);    
        return thisEntity;
    }

    public TaskEntity updateTaskProgress(String id, int progressCode) {
        TaskEntity task = em.find(TaskEntity.class, Long.valueOf(id)); 
        task.setProgressCode(progressCode);
        return task;
    }

    public TaskEntity verifyTask(String id, String username) {
        TaskEntity task = em.find(TaskEntity.class, Long.valueOf(id)); 
        task.setProgressCode(3);
        // set verifiedBy & verifiedAt
        UserEntity verifier = em.find(UserEntity.class, username);
        task.setVerifiedBy(verifier);
        task.setVerifiedAt(getCurrentISODate());
        return task;
    }

    public RecurringEventEntity createRecurringEvent(RecurringEventEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public RecurringEventEntity editRecurringEvent(Long id, RecurringEventEntity entity) {
        RecurringEventEntity old = em.find(RecurringEventEntity.class, id);
        old = entity;
        em.merge(old);
        return old;
    }

    public void removeRecurringEvent(Long id) {
        em.remove(em.find(RecurringEventEntity.class, id));
    }

    public RecurringEventEntity getOneRecurringEvent(Long id) {
        return em.find(RecurringEventEntity.class, id);
    }

    public List<RecurringEventEntity> getAllRecurringEvents() {
        Query q1 = em.createQuery("SELECT r FROM RecurringEvent r");
        return q1.getResultList() ;
    }

    public SemesterEntity createSemester(SemesterEntity entity) {
        em.persist(entity);
        return entity;
    }

    public SemesterEntity editSemester(Long id, SemesterEntity entity) {
        SemesterEntity old = em.find(SemesterEntity.class, id);
        old = entity;
        em.merge(old);
        return old;
    }

    public void deleteSemester(Long id) {
        em.remove(em.find(SemesterEntity.class, id));
    }

    public SemesterEntity getOneSemester(Long id) {
        return em.find(SemesterEntity.class, id);    
    }

    public List<SemesterEntity> getAllSemester() {
        Query q1 = em.createQuery("SELECT s FROM Semester s");
        return q1.getResultList() ;
    }

    public List<String> suggestFreeSlots(int groupId, String date) {
        //get group schedule items
        List<ScheduleItemEntity> groupScheduleItems = findGroupScheduleItems(groupId);
        
        //sort schedule items with bubble sort
        boolean isSorted = false;
        for(int i = 0 ; i < groupScheduleItems.size()-1 && !isSorted  ; i++){
            isSorted = true;
            for(int j = 0 ; j <= groupScheduleItems.size()-i-1 ; j++ ){
                //if this item's start time is after next item's start time, bubble it up.
                if(groupScheduleItems.get(j).getStartDate().toLocalTime().isAfter(
                groupScheduleItems.get(j+1).getStartDate().toLocalTime())){
                    ScheduleItemEntity temp = groupScheduleItems.get(j+1);
                    groupScheduleItems.set(j+1, groupScheduleItems.get(j));
                    groupScheduleItems.set(j, temp);
                    isSorted = false;
                }
            }
        }
        
        List<String> freeSlots = new ArrayList<>();
        int index = 0;
        ScheduleItemEntity currSched = null;
        LocalTime start = LocalTime.of(6, 00);// Must use LocalTime.parse(start) to turn this into LocalTime
        LocalTime end = null;
        while(index<groupScheduleItems.size()){
            currSched = groupScheduleItems.get(index);
            if(!currSched.getEndDate().toLocalTime().isBefore(start) && !currSched.getEndDate().toLocalTime().equals(start)){
                if(currSched.getStartDate().toLocalTime().isAfter(start)){
                    end = currSched.getStartDate().toLocalTime();
                    freeSlots.add(start.toString()+" - "+end.toString());
                }
                start = currSched.getEndDate().toLocalTime();
            }
            index++;
        }
        if(!end.isAfter(LocalTime.of(23, 58))){
            end = LocalTime.of(23, 59);
            freeSlots.add(start.toString()+" - "+end.toString());
        }
        return freeSlots;
    }

    public List<AnnouncementEntity> getAllAnnouncements() {
        Query q = em.createQuery("select a from Announcement a");
        return q.getResultList();
    }
    
    public AnnouncementEntity getOneAnnouncement(Long id) {
        return em.find(AnnouncementEntity.class, id);
    }

    public List<AnnouncementEntity> getUserAnnouncements(String id) {
        List<AnnouncementEntity> anns = new ArrayList<>();
        UserEntity u = em.find(UserEntity.class, id);
        Query q = em.createQuery("select a from Announcement a");
        for(Object o : q.getResultList()){
            AnnouncementEntity ann = (AnnouncementEntity) o;
            if(u!=null && ann.getAssignedTo().contains(u)){
                anns.add(ann);
            }
        }
        return anns;
    }

    public AnnouncementEntity createAnnouncement(AnnouncementEntity ann, int localPort) {
        //populate createdBy from JSON username
        UserEntity creator = em.find(UserEntity.class, ann.getCreatedBy().getUsername());
        System.out.println("*******************CREATOR IS "+creator.getUsername());
        
        if(creator != null){
            ann.setCreatedBy(creator);
            System.out.println("*******************CREATOR SET");
            //populate assignedTo from JSON usernames
            if(ann.getAssignedTo()!=null){
                Collection<UserEntity> assignedTo = new ArrayList<>();
                for(UserEntity assigned : ann.getAssignedTo()){
                    //assigned to everyone except the creator
                    //need to check usernames because the assigned to user entities have not been populated correctly yet.
                    if(!assigned.getUsername().equals(creator.getUsername())){
                        //correctly populate user entity to be assigned this announcement
                        assigned = em.find(UserEntity.class,assigned.getUsername());
                        //add the proper user inside announcement
                        assignedTo.add(assigned);
                        //send email
                        sendAnnouncementEmail(assigned, ann, localPort);
                        //send push notification
                        sendPushNotification(assigned, ann);
                    }
                }
                ann.setAssignedTo(assignedTo);
                System.out.println("*******************ASSIGN TO MODIFIED");
            }
            em.persist(ann);
        }
        return ann;
    }

    public void deleteAnnouncement(String id) {
        AnnouncementEntity ann = em.find(AnnouncementEntity.class, Long.valueOf(id));
        if(ann!=null){
            //remove announcement from join table.
            ann.setAssignedTo(null);
            em.remove(ann);
        }
    }

    public AnnouncementEntity editAnnouncement(String id, AnnouncementEntity replacement) {
        AnnouncementEntity ann = em.find(AnnouncementEntity.class, Long.valueOf(id));
        if(ann!=null){
            //populate assignedTo from JSON usernames
            if(replacement.getAssignedTo()!=null){
                Collection<UserEntity> assignedTo = new ArrayList<>();
                for(UserEntity assigned : replacement.getAssignedTo()){
                    assignedTo.add(em.find(UserEntity.class,assigned.getUsername()));
                }
                replacement.setAssignedTo(assignedTo);
                System.out.println("*******************ASSIGN TO MODIFIED");
            }
            //populate createdBy from JSON username
            UserEntity creator = em.find(UserEntity.class, replacement.getCreatedBy().getUsername());
            System.out.println("*******************CREATOR IS "+creator.getUsername());
            if(creator != null){
                replacement.setCreatedBy(creator);
                System.out.println("*******************CREATOR SET");
            }
            ann.setMessage(replacement.getMessage());
            ann.setPath(replacement.getPath());
            ann.setTitle(replacement.getTitle());
        }
        
        em.merge(replacement);
        return replacement;
    }

    public List<AttachmentEntity> getAllAttachments() {
        Query q = em.createQuery("SELECT a FROM Attachment a");
        return q.getResultList();
    }
    
    public AttachmentEntity getOneAttachment(Long id) {
        return em.find(AttachmentEntity.class, id);
    }

    public AttachmentEntity editAttachment(String id, AttachmentEntity replacement) {
        AttachmentEntity att = em.find(AttachmentEntity.class, Long.valueOf(id));
        att = replacement;
        em.merge(att);
        return att;
    }

    public String deleteAttachment(String attachmentId) {
        String fileName = "";
        AttachmentEntity att = em.find(AttachmentEntity.class, Long.valueOf(attachmentId));
        if(att!=null){
            fileName = att.getFileName();
            //detach att from all lessons
            Query q1 = em.createQuery("SELECT l FROM Lesson l");
            for(Object o: q1.getResultList()){
                LessonEntity l = (LessonEntity)o;
                Collection<AttachmentEntity> attachments = l.getResources();
                if(attachments.contains(att)){
                    attachments.remove(att);
                }
            }
            //detach att from all meeting minutes
            Query q2 = em.createQuery("SELECT m FROM MeetingMinute m");
            for(Object o: q2.getResultList()){
                MeetingMinuteEntity m = (MeetingMinuteEntity)o;
                Collection<AttachmentEntity> attachments = m.getAttachments();
                if(attachments.contains(att)){
                    attachments.remove(att);
                }
            }
            //detach att from all assignments
            Query q3 = em.createQuery("SELECT a FROM Assignment a");
            for(Object o: q3.getResultList()){
                AssignmentEntity a = (AssignmentEntity)o;
                Collection<AttachmentEntity> attachments = a.getSubmissions();
                if(attachments.contains(att)){
                    attachments.remove(att);
                }
            }
            
            em.remove(att);
        }
        return fileName;
    }

    public List<BrainstormEntity> getAllBrainstorms() {
        Query q = em.createQuery("select b from Brainstorm b");
        return q.getResultList();
    }
    
    public BrainstormEntity getOneBrainstorm(Long id) {
        return em.find(BrainstormEntity.class, id);
    }

    public BrainstormEntity createBrainstorm(BrainstormEntity brainstorm) {
        em.persist(brainstorm);
        return brainstorm;
    }

    public void deleteBrainstorm(String id) {
        BrainstormEntity brain = em.find(BrainstormEntity.class, Long.valueOf(id));
        if(brain!=null){
            em.remove(brain);
        }
    }

    public BrainstormEntity editBrainstorm(String id, BrainstormEntity replacement) {
        BrainstormEntity brain = em.find(BrainstormEntity.class, Long.valueOf(id));
        brain = replacement;
        em.merge(brain);
        return brain;
    }

    public List<IdeaEntity> getAllIdeas() {
        Query q = em.createQuery("select i from Idea i");
        return q.getResultList();
    }
    
    public IdeaEntity getOneIdea(Long id) {
        return em.find(IdeaEntity.class, id);
    }

    public IdeaEntity createIdea(IdeaEntity idea) {
        em.persist(idea);
        return idea;
    }

    public void deleteIdea(String id) {
        IdeaEntity idea = em.find(IdeaEntity.class, Long.valueOf(id));
        if(idea!=null){
            em.remove(idea);
        }
    }

    public IdeaEntity editIdea(String id, IdeaEntity replacement) {
        IdeaEntity idea = em.find(IdeaEntity.class, Long.valueOf(id));
        idea = replacement;
        em.merge(idea);
        return idea;
    }

    public List<ScheduleItemEntity> getAllLessons() {
        Query q = em.createQuery("select l from Lesson l");
        return q.getResultList();    
    }
    
    public ScheduleItemEntity getOneLesson(Long id) {
        return em.find(ScheduleItemEntity.class, id);
    }

    public ScheduleItemEntity createLesson(LessonEntity lesson) {
        em.persist(lesson);
        return lesson;
    }

    public void deleteLesson(String id) {
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        Query q1 = em.createQuery("SELECT m FROM Module m");
        Query q2 = em.createQuery("SELECT r FROM RecurringEvent r");
        if(lesson!=null){
            //DETACH LESSON FROM MODULE EVENTS.
            for(Object o: q1.getResultList()){
                ModuleEntity mod = (ModuleEntity) o;
                Collection<ScheduleItemEntity> modEvents = mod.getModuleEvents();
                if(modEvents!=null && modEvents.contains(lesson)){
                    modEvents.remove(lesson);
                }
            }
            //DETACH LESSON FROM RECURRING EVENT
            for(Object o: q2.getResultList()){
                RecurringEventEntity r = (RecurringEventEntity) o;
                Collection<LessonEntity> lessons = r.getLessons();
                if(lessons!=null && lessons.contains(lesson)){
                    lessons.remove(lesson);
                }
            }
            lesson.setMeetingMinute(null);
            lesson.setCreatedBy(null);
            lesson.setAssignedTo(null);
            lesson.setResources(null);
            lesson.setRecurringEvent(null);
            em.remove(lesson);
        }
    }

    public ScheduleItemEntity editLesson(String id, ScheduleItemEntity replacement) {
        ScheduleItemEntity lesson = em.find(ScheduleItemEntity.class, Long.valueOf(id));
        if(lesson!=null){
            lesson=em.merge(replacement);
        }
        return lesson;
    }

    public List<AttachmentEntity> downloadAllLessonAttachments(String id) {
        List<AttachmentEntity> attList = new ArrayList<>();
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        if(lesson!=null){
            Collection<AttachmentEntity> resources = lesson.getResources();
            //if resources of this lesson is empty or null, then attList will remain empty.
            if(resources != null){
                attList.addAll(lesson.getResources());
            }
        }
        return attList;
    }

//assumption: each attachment in this lesson will have different file name. i.e. proper naming convention is enforced, e.g. (IS1001_Lecture1.pptx)
    public void uploadLessonAttachment(String id, AttachmentEntity att, String username) {
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        UserEntity u = em.find(UserEntity.class, username);
        //get all attachments with the same file name. If this lesson already has attachment with this file name,
        //edit the table record instead of adding a new one. 
        Query q1 = em.createQuery("SELECT a FROM Attachment a WHERE a.fileName= :attFileName");
        q1.setParameter("attFileName", att.getFileName());
        if(lesson!=null && u!=null){
            att.setCreatedBy(u);
            Collection<AttachmentEntity> resources = lesson.getResources();
            List sameNameAttachments = q1.getResultList();
            //if there are no attachments with the same name for this lesson, proceed normally.
            if(sameNameAttachments.isEmpty()){
                resources.add(att);
                em.persist(att);
                System.out.println("new attachment persisted");
            }else{
                for(Object o : sameNameAttachments){
                    AttachmentEntity sameName = (AttachmentEntity)o;
                    //lesson already contains resource with this name. update row.
                    if(resources.contains(sameName)){
                        sameName.setTitle(att.getTitle());
                        sameName.setCreatedBy(u);
                        sameName.setCreatedAt(LocalDateTime.now());
                        System.out.println("existing attachment's title renamed");
                        break;
                    }else{//else, allow upload of resource with same file name as this is for different lesson.
                        resources.add(att);
                        em.persist(att);
                        System.out.println("new attachment persisted");
                    }
                }
            }
        }
    }

    public List<ScheduleItemEntity> getAllScheduleItems() {
        Query q1 = em.createQuery("SELECT s FROM ScheduleItem s");
        return q1.getResultList();
    }

    public List<TaskEntity> getAllTasks() {
        return em.createQuery("SELECT t FROM Task t").getResultList();
    }

    public List<AttachmentEntity> getAllLessonAttachments(Long id) {
        return (List<AttachmentEntity>) em.find(LessonEntity.class, id).getResources();
    }

    public List<ScheduleItemEntity> getModuleKeyDates(String moduleCode) {
        ArrayList<ScheduleItemEntity> keyDates = new ArrayList<>();
        ModuleEntity mod = em.find(ModuleEntity.class, moduleCode);
        //get assignments
        if(mod!=null){
            Query q1 = em.createQuery("SELECT ass FROM Assignment ass WHERE ass.module= :mod");
            q1.setParameter("mod", mod);
            for(Object o : q1.getResultList()){
                //CONVERT EACH ASSIGNMENT TO SCHEDULE ITEM.
                AssignmentEntity ass = (AssignmentEntity) o;
                ScheduleItemEntity conversion = new ScheduleItemEntity();
                conversion.setItemType("task");
                conversion.setTitle(ass.getTitle());
                conversion.setStartDate(ass.getOpenDate());
                conversion.setEndDate(ass.getCloseDate());
                keyDates.add(conversion);
            }
            //get assessments
            Query q2 = em.createQuery("SELECT s FROM ScheduleItem s WHERE s.itemType='assessment'");
            for(Object o : q2.getResultList()){
                //ADD EACH ASSESSMENT TO KEY DATES
                ScheduleItemEntity sched = (ScheduleItemEntity) o;
                keyDates.add(sched);
            }
        }
        return keyDates;
    }

    public SemesterEntity getCurrentSemester() {
        LocalDate currDate = LocalDate.now();
        SemesterEntity sem = new SemesterEntity();
        Query q1 = em.createQuery("SELECT s FROM Semester s");
        for(Object o:q1.getResultList()){
            SemesterEntity s = (SemesterEntity) o;
            LocalDate startDate = s.getStartDate();
            LocalDate endDate = s.getEndDate();
            //if semester starts before or on today's date and end after or on today's date, then it is current semester
            if( (startDate.isBefore(currDate) || startDate.isEqual(currDate)) && (endDate.isAfter(currDate) || endDate.isEqual(currDate)) ){
                sem = s;
            }
        }
        return sem;
    }
    
//helper method to send announcement email
    private void sendAnnouncementEmail(UserEntity assigned, AnnouncementEntity ann, int localPort) {
        try{
            // Step1
            System.out.println("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            //mail server settings
            mailServerProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");
            
            // Step2
            System.out.println("\n\n 2nd ===> get Mail Session..");
            mailSession = Session.getDefaultInstance(mailServerProperties, null);
            mailMessage = new MimeMessage(mailSession);
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(assigned.getEmail()));
            mailMessage.setSubject("Greetings from EduBox");
//            String hostAndPort = java.net.InetAddress.getLocalHost().getHostAddress()+":"+localPort;
//            System.out.println("HOST AND PORT IS "+hostAndPort);
            String emailBody = "Hey "+ assigned.getUsername() + ",<br><br>"
                    + "You have a new notification on EduTech!<br><br>"
                    + "Here is your notification: <br><br>"
                    + "<b>------------------START-----------------</b><br><br>"
                    + "Title: <i>"+ann.getTitle()+"</i><br><br>"
                    + "Message: "+ann.getMessage()+"<br><br>"
                    + "<a href='http://"+java.net.InetAddress.getLocalHost().getHostAddress()+":3000"+ann.getPath()+"'><button>Click here to see event in EduTech</button></a><br><br>"
                    + "<b>------------------END-----------------</b><br><br>"
                    + "<br><br>Cheers,<br>EduBox Team";
            mailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");
            
            // Step3
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = mailSession.getTransport("smtp");
            
            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password
            transport.connect("smtp.gmail.com", "41capstone03@gmail.com", "8mccapstone");
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();
        }catch(Exception e){
            System.out.println("Error sending user creation email!");
        }
    }
    
    //helper method to send creation emails
    public void sendCreationEmails(List<UserEntity> userList){
        //send email to each new user
        for(UserEntity u : userList){
            cmb.sendCreateEmail(u.getUsername(), 8080);
        }
    }
    private void sendPushNotification(UserEntity assigned, AnnouncementEntity ann) {
        try {
            String jsonResponse;
            
            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic M2NlNmYwYjItMGZkYy00MTg1LTk2NjktNTE0ZWRmNjBmZTNi");
            con.setRequestMethod("POST");
            
            String strJsonBody = "{"
                    + "\"app_id\": \"7c25a78b-f0ea-4190-a170-b04ecd514446\","
                    //dont change this
                    + "\"filters\": [{\"field\": \"tag\", \"key\": \"username\", \"relation\": \"=\", \"value\": \""+assigned.getUsername()+"\"}],"
                    //heading is notification title
                    + "\"headings\": {\"en\": \"EduTech : "+ann.getTitle()+"\"},"
                    //contents is notification body
                    + "\"contents\": {\"en\": \""+ann.getMessage()+"\"}"
                    + ",\"url\": \"http://"+java.net.InetAddress.getLocalHost().getHostAddress()+":3000/"+ann.getPath()+"\""
                    + "}";
            
            //for debugging
            System.out.println("strJsonBody:\n" + strJsonBody);
            
            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);
            
            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);
            
            int httpResponse = con.getResponseCode();
            System.out.println("httpResponse: " + httpResponse);
            
            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            System.out.println("jsonResponse:\n" + jsonResponse);
            
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

}
