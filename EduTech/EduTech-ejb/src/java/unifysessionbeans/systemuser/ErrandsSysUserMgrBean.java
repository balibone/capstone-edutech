/***************************************************************************************
*   Title:                  ErrandsSysUserMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY ERRANDS - SYSUSER (EDUBOX)
*   Created & Modified By:  CHEN MENG
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.systemuser;

import javax.ejb.Stateless;
import commoninfrastructureentities.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import unifyentities.common.CategoryEntity;
import unifyentities.errands.JobEntity;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import unifyentities.common.LikeListingEntity;
import unifyentities.common.MessageEntity;

@Stateless
public class ErrandsSysUserMgrBean implements ErrandsSysUserMgrBeanRemote {
    
    @PersistenceContext
    private EntityManager em;
    
    private CategoryEntity cEntity;
    private JobEntity jEntity;
    private UserEntity uEntity;
    private LikeListingEntity llEntity;
    private MessageEntity mEntity;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");
    DecimalFormat rateFormat = new DecimalFormat("0.00");
    
    @Override
    public List<Vector> viewJobList() {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT j FROM Job j WHERE j.jobStatus = 'Available' AND "
                + "j.categoryEntity.categoryActiveStatus = '1'");
        List<Vector> jobList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            JobEntity jobE = (JobEntity) o;
            Vector jobVec = new Vector();

            jobVec.add(jobE.getJobID());
            jobVec.add(jobE.getJobImage());
            jobVec.add(jobE.getJobTitle());
            jobVec.add(jobE.getCategoryEntity().getCategoryName());
            jobVec.add(jobE.getUserEntity().getUsername());

            long diff = currentDate.getTime() - jobE.getJobPostDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }
            jobVec.add(dateString);
            jobVec.add(sdf.format(jobE.getJobWorkDate()));
            jobVec.add(jobE.getJobStartLocation());
            jobVec.add(jobE.getJobRateType());
            jobVec.add(jobE.getJobRate());
            jobVec.add(jobE.getJobNumOfLikes());
            jobList.add(jobVec);
            dateString = "";
        }
        System.out.println("size: " + jobList.size());
        return jobList;
    }
    
    public ArrayList<String> getJobCategoryList(){
        
        ArrayList<String> categoryNameList = new ArrayList();
        
        Query q = em.createQuery("SELECT c.categoryName FROM Category c WHERE c.categoryType = 'Errands'");
        if(q.getResultList()!=null){
            Vector categoryList = (Vector) q.getResultList();
            for(int i=0; i<categoryList.size(); i++){
                String name = (String) categoryList.get(i);
                categoryNameList.add(name);
            }
        }
        return categoryNameList;
    }
    
    /*@Override
    public boolean likeAJob(long jobID, String userName){
        
        jEntity = lookupJob(jobID);
        if(jEntity!=null){
            jEntity.getLikeList().add(userName);
            int likeCount = jEntity.getJobNumOfLikes();
            jEntity.setJobNumOfLikes(likeCount+1);
            
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean unlikeAJob(long jobID, String userName){
        
        jEntity = lookupJob(jobID);
        if(jEntity!=null){
            jEntity.getLikeList().remove(userName);
            int likeCount = jEntity.getJobNumOfLikes();
            jEntity.setJobNumOfLikes(likeCount-1);
            
            return true;
        }else{
            return false;
        }
    }*/
    
    @Override
    public Vector viewJobDetails(long jobID) {
        
        Date currentDate = new Date();
        String dateString = "";
        
        jEntity = lookupJob(jobID);
        Vector jobDetailsVec = new Vector();

        if (jEntity != null) {
            jobDetailsVec.add(jEntity.getJobID());
            jobDetailsVec.add(jEntity.getJobTitle());
            jobDetailsVec.add(jEntity.getCategoryEntity().getCategoryName());
            jobDetailsVec.add(jEntity.getJobRateType());
            jobDetailsVec.add(jEntity.getJobRate());
            jobDetailsVec.add(jEntity.getJobDescription());
            jobDetailsVec.add(jEntity.getJobStartLocation());
            jobDetailsVec.add(jEntity.getJobStartLat());
            jobDetailsVec.add(jEntity.getJobStartLong());
            jobDetailsVec.add(jEntity.getJobEndLocation());
            jobDetailsVec.add(jEntity.getJobEndLat());
            jobDetailsVec.add(jEntity.getJobEndLong());
            jobDetailsVec.add(jEntity.getJobImage());
            jobDetailsVec.add(jEntity.getJobStatus());
            jobDetailsVec.add(jEntity.getJobNumOfLikes());
            
            
            
            long diff = currentDate.getTime() - jEntity.getJobPostDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }
            
            jobDetailsVec.add(dateString);
            jobDetailsVec.add(sdf.format(jEntity.getJobWorkDate()));
            jobDetailsVec.add(jEntity.getUserEntity().getUsername());
            jobDetailsVec.add(jEntity.getUserEntity().getImgFileName());
            jobDetailsVec.add(df.format(jEntity.getUserEntity().getUserCreationDate()));
            jobDetailsVec.add(jEntity.getCategoryEntity().getCategoryID());
            jobDetailsVec.add(jEntity.getJobDuration());
            jobDetailsVec.add(jEntity.getJobInformation());
            return jobDetailsVec;
        }
        return null;
    }
    
    @Override
    public List<Vector> viewAssocCategoryJobList(String hiddenCategoryName, long hiddenJobID) {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT j FROM Job j WHERE j.jobStatus = 'Available' AND "
                + "j.categoryEntity.categoryActiveStatus = '1' AND "
                + "j.categoryEntity.categoryName = :hiddenCategoryName");
        q.setParameter("hiddenCategoryName", hiddenCategoryName);
        List<Vector> assocCategoryJobList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            JobEntity jobE = (JobEntity) o;
            Vector assocCategoryJobVec = new Vector();

            if (jobE.getJobID() != hiddenJobID) {
                assocCategoryJobVec.add(jobE.getJobID());
                assocCategoryJobVec.add(jobE.getJobImage());
                assocCategoryJobVec.add(jobE.getJobTitle());
                assocCategoryJobVec.add(jobE.getCategoryEntity().getCategoryName());
                assocCategoryJobVec.add(jobE.getUserEntity().getUsername());

                long diff = currentDate.getTime() - jobE.getJobPostDate().getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays != 0) {
                    dateString = diffDays + " day";
                    if (diffDays == 1) {
                        dateString += " ago";
                    } else {
                        dateString += "s ago";
                    }
                } else if (diffHours != 0) {
                    dateString = diffHours + " hour";
                    if (diffHours == 1) {
                        dateString += " ago";
                    } else {
                        dateString += "s ago";
                    }
                } else if (diffMinutes != 0) {
                    dateString = diffMinutes + " minute";
                    if (diffMinutes == 1) {
                        dateString += " ago";
                    } else {
                        dateString += "s ago";
                    }
                } else if (diffSeconds != 0) {
                    dateString = diffSeconds + " second";
                    if (diffSeconds == 1) {
                        dateString += " ago";
                    } else {
                        dateString += "s ago";
                    }
                }
                assocCategoryJobVec.add(dateString);
                assocCategoryJobVec.add(jobE.getJobRateType());
                assocCategoryJobVec.add(jobE.getJobRate());
                assocCategoryJobList.add(assocCategoryJobVec);
                dateString = "";
            }
        }
        return assocCategoryJobList;
    }
   
    
    @Override
    public String createJobListing(String jobTitle, String jobRateType, double jobRate, double jobDuration, String jobDescription, 
            Date jobWorkDate, String jobImagefileName, long categoryID, String username, String startLocation, 
            String startLat, String startLong, String endLocation, String endLat, String endLong, String jobInformation) {
        if (lookupUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupCategory(categoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            System.out.println("bean: ready to create job");
            uEntity = lookupUser(username);
            cEntity = lookupCategory(categoryID);
            jEntity = new JobEntity();
            
            if(jEntity.createJobListing(jobTitle, jobDescription, jobImagefileName, jobRateType, jobRate, jobDuration, startLocation, 
                    startLat, startLong, endLocation, endLat, endLong, jobWorkDate, jobInformation)) {
                jEntity.setCategoryEntity(cEntity);
                jEntity.setUserEntity(uEntity);
                em.persist(jEntity);
                return "Job has been listed successfully!";
            } else {
                return "Error occured while listing the job. Please try again.";
            }
        }
    }
    
    @Override
    public String editJobListing(long jobID, String jobTitle, String jobRateType, double jobRate, double jobDuration, String jobDescription, 
            Date jobWorkDate, String jobImagefileName, String startLocation, String startLat, String startLong, 
            String endLocation, String endLat, String endLong, long jobCategoryID, String username) {
        if (lookupUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupJob(jobID) == null) { return "There are some issues with your job listing. Please try again."; }
        else if (lookupCategory(jobCategoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            uEntity = lookupUser(username);
            jEntity = lookupJob(jobID);
            cEntity = lookupCategory(jobCategoryID);
            
            jEntity.setJobTitle(jobTitle);
            jEntity.setJobDescription(jobDescription);
            jEntity.setJobRateType(jobRateType);
            jEntity.setJobRate(jobRate);
            jEntity.setJobWorkDate(jobWorkDate);
            jEntity.setJobImage(jobImagefileName);
            jEntity.setJobStartLocation(startLocation);
            jEntity.setJobStartLat(startLat);
            jEntity.setJobStartLong(startLong);
            jEntity.setJobEndLocation(endLocation);
            jEntity.setJobEndLat(endLat);
            jEntity.setJobEndLong(endLong);
            jEntity.setCategoryEntity(cEntity);
            jEntity.setUserEntity(uEntity);
            em.merge(jEntity);
            return "Job listing has been updated successfully!";
        }
    }
    
    @Override
    public String deleteJobListing(long jobIDToDelete) {
        if (lookupJob(jobIDToDelete) == null) { return "There are some issues with your job listing. Please try again."; }
        else {
            jEntity = lookupJob(jobIDToDelete);
            em.remove(jEntity);
            em.flush();
            em.clear();
            return "Job listing has been deleted successfully!";
        }
    }
    
    @Override
    public List<Vector> viewJobCategoryList(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'Errands' "
                + "AND c.categoryActiveStatus = '1'");
        List<Vector> jobCategoryList = new ArrayList<Vector>();
        
        if(q.getResultList().size()!=0){
            for (Object o: q.getResultList()){
                CategoryEntity categoryE = (CategoryEntity) o;
                Vector jobCategoryVec = new Vector();
            
                jobCategoryVec.add(categoryE.getCategoryImage());
                jobCategoryVec.add(categoryE.getCategoryID());
                jobCategoryVec.add(categoryE.getCategoryName());
                jobCategoryList.add(jobCategoryVec);
            }
        }
            System.out.println("cat id: " + jobCategoryList.get(0).get(1));
            return jobCategoryList;
    }
    
    
    
    /* MISCELLANEOUS METHODS */
    public CategoryEntity lookupCategory(long categoryID) {
        CategoryEntity ce = new CategoryEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID = :categoryID");
            q.setParameter("categoryID", categoryID);
            ce = (CategoryEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Category cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Category does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    public JobEntity lookupJob(long jobID) {
        JobEntity je = new JobEntity();
        try {
            Query q = em.createQuery("SELECT i FROM Job i WHERE i.jobID = :jobID");
            q.setParameter("jobID", jobID);
            je = (JobEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job cannot be found. " + enfe.getMessage());
            em.remove(je);
            je = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job does not exist. " + nre.getMessage());
            em.remove(je);
            je = null;
        }
        return je;
    }
    
    public UserEntity lookupUser(String username) {
        UserEntity ue = new UserEntity();
        try{
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }
    
    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try{
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }
    
    public LikeListingEntity lookupLike(long itemID, String username) {
        LikeListingEntity lle = new LikeListingEntity();
        try {
            Query q = em.createQuery("SELECT l FROM LikeListing l WHERE l.itemEntity.itemID = :itemID AND l.userEntity.username = :username");
            q.setParameter("itemID", itemID);
            q.setParameter("username", username);
            lle = (LikeListingEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Like cannot be found. " + enfe.getMessage());
            em.remove(lle);
            lle = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Like does not exist. " + nre.getMessage());
            em.remove(lle);
            lle = null;
        }
        return lle;
    }
    
    public MessageEntity lookupContentMessage(long itemID, String username) {
        MessageEntity me = new MessageEntity();
        try {
            Query q = em.createQuery("SELECT m FROM Message m WHERE m.contentID = :itemID AND m.userEntity.username = :username");
            q.setParameter("itemID", itemID);
            q.setParameter("username", username);
            me = (MessageEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Content Message cannot be found. " + enfe.getMessage());
            em.remove(me);
            me = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Content Message does not exist. " + nre.getMessage());
            em.remove(me);
            me = null;
        }
        return me;
    }
    
}
