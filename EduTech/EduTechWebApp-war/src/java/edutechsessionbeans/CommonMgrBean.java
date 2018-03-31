/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;


import commoninfraentities.UserEntity;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class CommonMgrBean {

    @PersistenceContext
    private EntityManager em;
    
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

    public ScheduleItemEntity createScheduleItem(ScheduleItemEntity entity) {
        entity.setCreatedAt(LocalDateTime.parse(getCurrentISODate()));
        // get proper User - initially json createdBy only contains a username key
        UserEntity user = (UserEntity) entity.getCreatedBy();
        //Set assignedTo
        Collection<UserEntity> assignedTo = new ArrayList(); 
        switch(entity.getItemType()) {
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
        return entity;
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
            List<ScheduleItemEntity> temp = new ArrayList();    
            temp = findUserScheduleItems(member.getUsername());
            temp.removeAll(membersScheduleItem);
            membersScheduleItem.addAll(temp);
        }
        
        for(ScheduleItemEntity scheduleItem: membersScheduleItem) {
            if(scheduleItem.getItemType().equals("timetable")) {
                String moduleCode = scheduleItem.getModuleCode();
                ModuleEntity module = em.find(ModuleEntity.class, moduleCode);
                Collection<UserEntity> moduleMembers = module.getMembers();
                moduleMembers.retainAll(members);
                scheduleItem.setAssignedTo(moduleMembers);
            }
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
                if(t.getAssignedTo().contains(user) && t.getDeadline()!=null){
                    //convert task to schedule item and add it to userScheduleItems.
                    ScheduleItemEntity convert = new ScheduleItemEntity();
                    convert.setAssignedTo(t.getAssignedTo());
                    convert.setCreatedAt(LocalDateTime.parse(t.getCreatedAt()));
                    convert.setCreatedBy(t.getCreatedBy());
//                    convert.setDescription("please dont show this field if type=task");
                    convert.setStartDate(LocalDateTime.parse(t.getDeadline()));
                    convert.setEndDate(LocalDateTime.parse(t.getDeadline()));
                    convert.setGroupId(t.getGroupId());
                    convert.setItemType("task");
//                    convert.setLocation("please dont show this field if type=task");
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

    public AnnouncementEntity createAnnouncement(AnnouncementEntity ann) {
        em.persist(ann);
        return ann;
    }

    public void deleteAnnouncement(String id) {
        AnnouncementEntity ann = em.find(AnnouncementEntity.class, Long.valueOf(id));
        if(ann!=null){
            em.remove(ann);
        }
    }

    public AnnouncementEntity editAnnouncement(String id, AnnouncementEntity replacement) {
        AnnouncementEntity ann = em.find(AnnouncementEntity.class, Long.valueOf(id));
        ann = replacement;
        em.merge(ann);
        return ann;
    }

    public AnnouncementEntity addUserToSeenBy(String id, UserEntity user) {
        AnnouncementEntity ann = em.find(AnnouncementEntity.class, Long.valueOf(id));
        if(ann!=null && user!=null){
            ann.getSeenBy().add(user);
        }
        return ann;
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

    public String deleteAttachment(String id) {
        String fileName = "";
        AttachmentEntity att = em.find(AttachmentEntity.class, Long.valueOf(id));
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
            //detach att from all submission
            Query q3 = em.createQuery("SELECT s FROM Submission s");
            for(Object o: q3.getResultList()){
                AssignmentEntity s = (AssignmentEntity)o;
                Collection<AttachmentEntity> attachments = s.getSubmissions();
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
            lesson.setMeetingMinutes(null);
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

    public void uploadLessonAttachment(String id, AttachmentEntity att) {
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        //get all attachments with the same file name. If this lesson already has attachment with this file name,
        //edit the table record instead of adding a new one. 
        Query q1 = em.createQuery("SELECT a FROM Attachment a WHERE a.fileName= :attFileName");
        q1.setParameter("attFileName", att.getFileName());
        if(lesson!=null){
            Collection<AttachmentEntity> resources = lesson.getResources();
            List sameNameAttachments = q1.getResultList();
            //if there are no attachments with the same name, proceed normally.
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
                        System.out.println("existing attachment's title renamed");
                        break;
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

    

}
