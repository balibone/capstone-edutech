/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechsessionbeans;


import commoninfraentities.UserEntity;
import edutechentities.common.PostEntity;
import edutechentities.group.GroupEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.common.TaskEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); 
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
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
        em.merge(entity);
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
        entity.setCreatedAt(getCurrentISODate());
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
    
    public List<ScheduleItemEntity> findUserScheduleItems(String username) {
        UserEntity user = em.find(UserEntity.class, username);
        List<ScheduleItemEntity> userScheduleItems = new ArrayList();
        List<ScheduleItemEntity> allScheduleItems = em.createQuery("SELECT s FROM ScheduleItem s").getResultList();
        for(ScheduleItemEntity scheduleItem: allScheduleItems){
            if(scheduleItem.getAssignedTo().contains(user)){
                userScheduleItems.add(scheduleItem);
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

}
