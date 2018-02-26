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

@Stateless
public class ErrandsSysUserMgrBean implements ErrandsSysUserMgrBeanRemote {
    
    @PersistenceContext
    private EntityManager em;
    
    private CategoryEntity cEntity;
    private JobEntity jEntity;
    private UserEntity uEntity;
    
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
            jobVec.add(jobE.getJobWorkDate());
            jobVec.add(jobE.getJobStartLocation());
            jobVec.add(jobE.getJobRateType());
            jobVec.add(jobE.getJobRate());
            //jobVec.add(jobE.getJobNumOfLikes());
            jobList.add(jobVec);
            dateString = "";
        }
        System.out.println("size: " + jobList.size());
        return jobList;
    }
    
    @Override
    public Vector viewJobDetails(long jobID) {
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
            //jobDetailsVec.add(jEntity.getJobNumOfLikes());
            jobDetailsVec.add(jEntity.getJobPostDate());
            jobDetailsVec.add(jEntity.getJobWorkDate());
            jobDetailsVec.add(jEntity.getUserEntity().getUsername());
            jobDetailsVec.add(jEntity.getUserEntity().getImgFileName());
            jobDetailsVec.add(jEntity.getUserEntity().getUserCreationDate());
            jobDetailsVec.add(jEntity.getCategoryEntity().getCategoryID());
            return jobDetailsVec;
        }
        return null;
    }
    
    @Override
    public List<Vector> viewAssocCategoryJobList(String hiddenCategoryName, long hiddenJobID) {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT j FROM Job i WHERE j.jobStatus = 'Available' AND "
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
            String startLat, String startLong, String endLocation, String endLat, String endLong) {
        if (lookupUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupCategory(categoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            uEntity = lookupUser(username);
            cEntity = lookupCategory(categoryID);
            jEntity = new JobEntity();
            
            if(jEntity.createJobListing(jobTitle, jobDescription, jobImagefileName, jobRateType, jobRate, jobDuration, startLocation, 
                    startLat, startLong, endLocation, endLat, endLong, jobWorkDate)) {
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
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'errands' "
                + "AND c.categoryActiveStatus = '1'");
        List<Vector> jobCategoryList = new ArrayList<Vector>();
        
        for (Object o: q.getResultList()){
            CategoryEntity categoryE = (CategoryEntity) o;
            Vector jobCategoryVec = new Vector();
            
            jobCategoryVec.add(categoryE.getCategoryImage());
            jobCategoryVec.add(categoryE.getCategoryID());
            jobCategoryVec.add(categoryE.getCategoryName());
            jobCategoryList.add(jobCategoryVec);
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
    
    
    
    
}
