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
import edutechentities.module.LessonEntity;
import edutechentities.module.ModuleEntity;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        List<UserEntity> allUsers = em.createQuery("SELECT s FROM SystemUser s WHERE s.useractivestatus=1").getResultList();
        if(allUsers!=null){
            for(UserEntity u : allUsers){
                em.detach(u);//detach from persistence context so that password removal is not reflected in database.
                u.setUserpassword("hidden");
            }
        }
        return allUsers;
    }

    public UserEntity findUser(String username) {
        UserEntity u = em.find(UserEntity.class, username);
        em.detach(u);
        u.setUserpassword("hidden");
        return u;
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
        
//        //GET ALL LESSONS
//        List<LessonEntity> allLessons = em.createQuery("SELECT ; FROM Lesson l").getResultList();
//        //GET IMMEDIATE LESSONS
//        for(LessonEntity lesson: allLessons){
//            if(lesson.getAssignedTo().contains(user)){
//                userScheduleItems.add(lesson);
//            }
//        }
        
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
        Query q = em.createQuery("select a from Attachment a");
        return q.getResultList();
    }
    
    public AttachmentEntity getOneAttachment(Long id) {
        return em.find(AttachmentEntity.class, id);
    }

    public AttachmentEntity createAttachment(AttachmentEntity attachment) {
        em.persist(attachment);
        return attachment;
    }

    public AttachmentEntity editAttachment(String id, AttachmentEntity replacement) {
        AttachmentEntity att = em.find(AttachmentEntity.class, Long.valueOf(id));
        att = replacement;
        em.merge(att);
        return att;
    }

    public void deleteAttachment(String id) {
        AttachmentEntity att = em.find(AttachmentEntity.class, Long.valueOf(id));
        if(att!=null){
            em.remove(att);
        }
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

    public List<LessonEntity> getAllLessons() {
        Query q = em.createQuery("select l from Lesson l");
        return q.getResultList();    
    }
    
    public LessonEntity getOneLesson(Long id) {
        return em.find(LessonEntity.class, id);
    }

    public LessonEntity createLesson(LessonEntity lesson) {
        em.persist(lesson);
        return lesson;
    }

    public void deleteLesson(String id) {
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        if(lesson!=null){
            em.remove(lesson);
        }
    }

    public LessonEntity editLesson(String id, LessonEntity replacement) {
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        lesson = replacement;
        em.merge(lesson);
        return lesson;
    }

    public List<AttachmentEntity> downloadAllLessonAttachments(String id) {
        List<AttachmentEntity> attList = new ArrayList<>();
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        Collection<AttachmentEntity> resources = lesson.getResources();
        //if resources of this lesson is empty or null, then attList will remain empty.
        if(resources != null){
            attList.addAll(lesson.getResources());
        }
        return attList;
    }

    public List<AttachmentEntity> uploadLessonAttachment(String id, AttachmentEntity att) {
        List<AttachmentEntity> attList = new ArrayList<>();
        LessonEntity lesson = em.find(LessonEntity.class, Long.valueOf(id));
        Collection<AttachmentEntity> resources = lesson.getResources();
        resources.add(att);
        //if resources of this lesson is empty or null, then attList will remain empty.
        if(resources != null){
            attList.addAll(lesson.getResources());
        }
        return attList;
    }

    public AttachmentEntity downloadAttachment(Long id) {
        AttachmentEntity att = em.find(AttachmentEntity.class,id);
        return att;
    }

    

}
