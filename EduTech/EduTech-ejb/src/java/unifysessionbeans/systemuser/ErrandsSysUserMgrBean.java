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
import unifyentities.errands.JobOfferEntity;
import unifyentities.common.JobReportEntity;
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
    private UserEntity jobPosterEntity;
    private UserEntity jobTakerEntity;
    private LikeListingEntity llEntity;
    private JobOfferEntity joEntity;
    private JobReportEntity jrEntity;
    private MessageEntity mEntity;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");
    DecimalFormat rateFormat = new DecimalFormat("0.00");
    
    @Override
    public List<Vector> viewJobList(String username) {
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
            jobVec.add(rateFormat.format(jobE.getJobRate()));
            jobVec.add(getJobLikeCount(jobE.getJobID()));
            if(lookupLike(jobE.getJobID(), username) == null) { jobVec.add(false);}
            else { jobVec.add(true); }
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
    public Vector viewJobDetails(long jobID, String username) {
        
        Date currentDate = new Date();
        String dateString = "";
        
        jEntity = lookupJob(jobID);
        Vector jobDetailsVec = new Vector();

        if (jEntity != null) {
            jobDetailsVec.add(jEntity.getJobID());
            jobDetailsVec.add(jEntity.getJobTitle());
            jobDetailsVec.add(jEntity.getCategoryEntity().getCategoryName());
            jobDetailsVec.add(jEntity.getJobRateType());
            jobDetailsVec.add(rateFormat.format(jEntity.getJobRate()));
            jobDetailsVec.add(jEntity.getJobDescription());
            jobDetailsVec.add(jEntity.getJobStartLocation());
            jobDetailsVec.add(jEntity.getJobStartLat());
            jobDetailsVec.add(jEntity.getJobStartLong());
            jobDetailsVec.add(jEntity.getJobEndLocation());
            jobDetailsVec.add(jEntity.getJobEndLat());
            jobDetailsVec.add(jEntity.getJobEndLong());
            jobDetailsVec.add(jEntity.getJobImage());
            jobDetailsVec.add(jEntity.getJobStatus());
            jobDetailsVec.add(getJobLikeCount(jobID));
            
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
            jobDetailsVec.add(jEntity.getNumOfHelpers());
            jobDetailsVec.add(jEntity.getChecking());
            if(lookupLike(jobID, username) == null) { jobDetailsVec.add(false);}
            else { jobDetailsVec.add(true); }
            //System.out.println("work time " + sdf.format(jEntity.getJobWorkDate()));
            System.out.println("like status: " + jobDetailsVec.get(25));
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
                assocCategoryJobVec.add(rateFormat.format(jobE.getJobRate()));
                assocCategoryJobList.add(assocCategoryJobVec);
                dateString = "";
            }
        }
        return assocCategoryJobList;
    }
   
    @Override
    public String reportJobListing(long jobID, String username, String reportReason){
        
        if (lookupJob(jobID) == null) { return "There are some issues with the job listing. Please try again."; }
        else if(lookupUnifyUser(username) == null) { return "There are some issues with your user profile. Please try again."; }
        else {
            jEntity = lookupJob(jobID);
            UserEntity jobReporterEntity = lookupUnifyUser(username);
            jobPosterEntity = lookupUnifyUser(lookupJob(jobID).getUserEntity().getUsername());
            jrEntity = new JobReportEntity();
            System.out.println("esmr: test1");
            
            if(jrEntity.createJobReport(reportReason)) {
                jrEntity.setJobEntity(jEntity);
                jrEntity.setUserEntity(jobReporterEntity);
                jEntity.getJobReportSet().add(jrEntity);
                jobReporterEntity.getJobReportSet().add(jrEntity);
                jobPosterEntity.getJobReportSet().add(jrEntity);
                
                System.out.println("esmr: test2");
                
                /* MESSAGE SENDER IS THE JOB TAKER WHO REPORT THE JOB, MESSAGE RECEIVER IS THE JOB POSTER */
                mEntity = new MessageEntity();
                mEntity.createContentMessage(jobReporterEntity.getUsername(), jEntity.getUserEntity().getUsername(), 
                        jobReporterEntity.getUsername() + " report your " + jEntity.getJobTitle() + ". Please check", 
                        jEntity.getJobID(), "Errands");
                /* JOB TAKER WHO SENT THE OFFER IS THE USERENTITY_USERNAME */
                mEntity.setUserEntity(jobReporterEntity);
                jobPosterEntity.getMessageSet().add(mEntity);
                
                em.persist(jrEntity);
                em.persist(mEntity);
                em.merge(jEntity);
                em.merge(jobReporterEntity);
                em.merge(jobPosterEntity);
                return "Thanks! We will review your report shortly!";
            } else {
                return "Error occured while sending the report. Please try again.";
            }
        }
    }
    
    @Override
    public String createJobListing(String jobTitle, String jobRateType, double jobRate, double jobDuration, String jobDescription, 
            Date jobWorkDate, String jobImagefileName, long categoryID, String username, String startLocation, 
            String startLat, String startLong, String endLocation, String endLat, String endLong, String jobInformation, int numOfHelpers, boolean checking) {
        if (lookupUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupCategory(categoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            System.out.println("bean: ready to create job");
            uEntity = lookupUser(username);
            cEntity = lookupCategory(categoryID);
            jEntity = new JobEntity();
            
            if(jEntity.createJobListing(jobTitle, jobDescription, jobImagefileName, jobRateType, jobRate, jobDuration, startLocation, 
                    startLat, startLong, endLocation, endLat, endLong, jobWorkDate, jobInformation, numOfHelpers, checking)) {
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
            String endLocation, String endLat, String endLong, long jobCategoryID, String username, int numOfHelpers, boolean checking) {
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
    public String likeUnlikeJob(long jobIDHid, String username) {
        if (lookupJob(jobIDHid) == null) { return "0"; }
        else if(lookupUnifyUser(username) == null) { return "0"; }
        else {
            jEntity = lookupJob(jobIDHid);
            uEntity = lookupUnifyUser(username);
            if (lookupLike(jobIDHid, username) == null) {
                llEntity = new LikeListingEntity();
                if(llEntity.addNewLike("Errands")) {
                    llEntity.setJobEntity(jEntity);
                    llEntity.setUserEntity(uEntity);
                    jEntity.getLikeListingSet().add(llEntity);
                    uEntity.getLikeListingSet().add(llEntity);
                    
                    /* MESSAGE SENDER IS THE JOB LIKER, MESSAGE RECEIVER IS THE JOB POSTER */
                    mEntity = new MessageEntity();
                    mEntity.createContentMessage(uEntity.getUsername(), jEntity.getUserEntity().getUsername(), 
                        uEntity.getUsername() + " likes your " + jEntity.getJobTitle() + ". Check it out!", 
                        jEntity.getJobID(), "Errands");
                    /* JOB LIKER IS THE USERENTITY_USERNAME */
                    mEntity.setUserEntity(uEntity);
                    (jEntity.getUserEntity()).getMessageSet().add(mEntity);
                    
                    em.persist(llEntity);
                    em.persist(mEntity);
                    em.merge(jEntity);
                    em.merge(uEntity);
                }
            } else {
                llEntity = lookupLike(jobIDHid, username);
                jEntity.getLikeListingSet().remove(llEntity);
                uEntity.getLikeListingSet().remove(llEntity);
                
                //mEntity = lookupContentMessage(jobIDHid, username);
                //(jEntity.getUserEntity()).getMessageSet().remove(mEntity);
                
                em.remove(llEntity);
                //em.remove(mEntity);
                em.merge(uEntity);
                em.flush();
                em.clear();
            }
        }
        System.out.println("like count: " + getJobLikeCount(jobIDHid));
        return String.valueOf(getJobLikeCount(jobIDHid));
    }
    
    @Override
    public List<Vector> viewJobLikeList(long jobID) {
        Query q = em.createQuery("SELECT ll FROM LikeListing ll WHERE ll.jobEntity.jobID = :jobID");
        q.setParameter("jobID", jobID);
        List<Vector> jobLikeList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            LikeListingEntity likeListingE = (LikeListingEntity) o;
            Vector likeListingVec = new Vector();

            likeListingVec.add(likeListingE.getUserEntity().getUsername());
            likeListingVec.add(likeListingE.getUserEntity().getUserFirstName());
            likeListingVec.add(likeListingE.getUserEntity().getUserLastName());
            //likeListingVec.add(getPositiveItemReviewCount(likeListingE.getUserEntity().getUsername()));
            //likeListingVec.add(getNeutralItemReviewCount(likeListingE.getUserEntity().getUsername()));
            //likeListingVec.add(getNegativeItemReviewCount(likeListingE.getUserEntity().getUsername()));
            jobLikeList.add(likeListingVec);
        }
        return jobLikeList;
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
    
    @Override
    public String sendJobOfferPrice(long jobID, String username, String jobOfferPrice, String jobOfferDescription){
        
        if (lookupJob(jobID) == null) { return "There are some issues with the job listing. Please try again."; }
        else if(lookupUnifyUser(username) == null) { return "There are some issues with your user profile. Please try again."; }
        else if(lookupJobOffer(jobID, username) != null) { return "You have sent an offer previously. Please go to your profile to check or update your offer."; }
        else if(jobOfferPrice.equals("")) { return "Job offer price cannot be empty."; }
        //else if(!isNumeric(jobOfferPrice)) { return "Please enter a valid job offer price."; }
        else if(Double.parseDouble(jobOfferPrice) < 0.0 || Double.parseDouble(jobOfferPrice) > 9999.0) { return "Job offer price must be between 0 to 9999. Please try again."; }
        else {
            jEntity = lookupJob(jobID);
            jobTakerEntity = lookupUnifyUser(username);
            jobPosterEntity = lookupUnifyUser(lookupJob(jobID).getUserEntity().getUsername());
            joEntity = new JobOfferEntity();
            
            if(joEntity.createJobOffer(Double.parseDouble(jobOfferPrice), jobOfferDescription)) {
                joEntity.setJobEntity(jEntity);
                joEntity.setUserEntity(jobTakerEntity);
                jEntity.getJobOfferSet().add(joEntity);
                jobTakerEntity.getJobOfferSet().add(joEntity);
                jobPosterEntity.getJobOfferSet().add(joEntity);
                
                /* MESSAGE SENDER IS THE JOB TAKER WHO SENT THE OFFER, MESSAGE RECEIVER IS THE JOB POSTER */
                mEntity = new MessageEntity();
                mEntity.createContentMessage(jobTakerEntity.getUsername(), jEntity.getUserEntity().getUsername(), 
                        jobTakerEntity.getUsername() + " sent an offer for your " + jEntity.getJobTitle() + ". Check it out!", 
                        jEntity.getJobID(), "Errands");
                /* JOB TAKER WHO SENT THE OFFER IS THE USERENTITY_USERNAME */
                mEntity.setUserEntity(jobTakerEntity);
                jobPosterEntity.getMessageSet().add(mEntity);
                
                em.persist(joEntity);
                em.persist(mEntity);
                em.merge(jEntity);
                em.merge(jobTakerEntity);
                em.merge(jobPosterEntity);
                return "Your job offer has been sent successfully!";
            } else {
                return "Error occured while sending your job offer. Please try again.";
            }
        }
    }
    
    @Override
    public String editJobOfferPrice(long jobID, String username, String jobOfferPrice, String jobOfferDescription){
        
        System.out.println("esmr: edit offer");
        
        if (lookupJob(jobID) == null) { System.out.println("lookup job"); return "There are some issues with the job listing. Please try again."; }
        else if(lookupUnifyUser(username) == null) { System.out.println("lookup user"); return "There are some issues with your user profile. Please try again."; }
        else if(lookupJobOffer(jobID, username) == null) { System.out.println("lookup offer"); return "There are some issues with your job offer. Please try again."; }
        else if(jobOfferPrice.equals("")) { System.out.println("null price"); return "Job offer price cannot be empty."; }
        //else if(!isNumeric(jobOfferPrice)) { return "Please enter a valid job offer price."; }
        else if(Double.parseDouble(jobOfferPrice) < 0.0 || Double.parseDouble(jobOfferPrice) > 9999.0) { System.out.println("price range"); return "Job offer price must be between 0 to 9999. Please try again."; }
        else {
                jEntity = lookupJob(jobID);
                joEntity = lookupJobOffer(jobID, username);
                jobTakerEntity = lookupUnifyUser(username);
                jobPosterEntity = lookupUnifyUser(lookupJob(jobID).getUserEntity().getUsername());
            
                joEntity.setJobOfferPrice(Double.parseDouble(jobOfferPrice));
                joEntity.setJobOfferDescription(jobOfferDescription);
                joEntity.setJobOfferStatusForPoster("Pending");
                joEntity.setJobOfferStatusForSender("Processing");
                
                /* MESSAGE SENDER IS THE JOB TAKER WHO SENT THE OFFER, MESSAGE RECEIVER IS THE JOB POSTER */
                mEntity = new MessageEntity();
                mEntity.createContentMessage(jobTakerEntity.getUsername(), jEntity.getUserEntity().getUsername(), 
                        jobTakerEntity.getUsername() + " updated the offer for your " + jEntity.getJobTitle() + ". Check it out!", 
                        jEntity.getJobID(), "Errands");
                /* JOB TAKER WHO SENT THE OFFER IS THE USERENTITY_USERNAME */
                mEntity.setUserEntity(jobTakerEntity);
                jobPosterEntity.getMessageSet().add(mEntity);
                
                em.persist(joEntity);
                em.persist(mEntity);
                em.merge(jEntity);
                em.merge(jobTakerEntity);
                em.merge(jobPosterEntity);
                return "Your job offer has been updated successfully!";
        }
    }
    
    @Override
    public String deleteJobOffer(long jobOfferIDToDelete) {
        
        Query q = em.createQuery("SELECT jo FROM JobOffer jo WHERE jo.jobOfferID = :offerID");
        q.setParameter("offerID", jobOfferIDToDelete);
        
        if (q.getSingleResult() == null) { return "There are some issues with your job offer. Please try again."; }
        else {
            JobOfferEntity offerEntity = (JobOfferEntity)q.getSingleResult();
            em.remove(offerEntity);
            em.flush();
            em.clear();
            return "Job Offer has been deleted successfully!";
        }
    }
    
    @Override
    public List<Vector> viewOfferListOfAJob(String username, long urljobID) {
        boolean jobInfoEntry = false;
        Date currentDate = new Date();
        String dateString = "";
        jEntity = lookupJob(urljobID);
        List<Vector> jobOfferList = new ArrayList<Vector>();

        Query q = em.createQuery("SELECT o FROM JobOffer o WHERE o.jobEntity.userEntity.username = :username "
                + "AND o.jobEntity.jobID = :jobID");
        q.setParameter("username", username);
        q.setParameter("jobID", urljobID);
        
        if(q.getResultList().size()==0){
            Vector jobDetails = new Vector();
            jobDetails.add(jEntity.getJobID());
            jobDetails.add(jEntity.getJobTitle());
            jobDetails.add(jEntity.getJobImage());
            jobDetails.add(String.format ("%,.2f", jEntity.getJobRate()));
            jobDetails.add(jEntity.getJobRateType());
            jobDetails.add(jEntity.getCategoryEntity().getCategoryName());
            jobDetails.add(jEntity.getNumOfHelpers());
            jobOfferList.add(jobDetails);
            jobInfoEntry = true;
            
        }else{
        
            for (Object o : q.getResultList()) {
                JobOfferEntity jobOfferE = (JobOfferEntity) o;
                Vector jobOfferDetailsVec = new Vector();

                if(jobInfoEntry == false) {
                    Vector jobDetailsVec = new Vector();
                    jobDetailsVec.add(jobOfferE.getJobEntity().getJobID());
                    jobDetailsVec.add(jobOfferE.getJobEntity().getJobTitle());
                    jobDetailsVec.add(jobOfferE.getJobEntity().getJobImage());
                    jobDetailsVec.add(String.format ("%,.2f", jobOfferE.getJobEntity().getJobRate()));
                    jobDetailsVec.add(jobOfferE.getJobEntity().getJobRateType());
                    jobDetailsVec.add(jobOfferE.getJobEntity().getCategoryEntity().getCategoryName());
                    jobDetailsVec.add(jobOfferE.getJobEntity().getNumOfHelpers());
                    jobOfferList.add(jobDetailsVec);
                    jobInfoEntry = true;
                }
                jobOfferDetailsVec.add(jobOfferE.getUserEntity().getUsername());
                jobOfferDetailsVec.add(jobOfferE.getUserEntity().getUserFirstName());
                jobOfferDetailsVec.add(jobOfferE.getUserEntity().getUserLastName());
                jobOfferDetailsVec.add(jobOfferE.getUserEntity().getImgFileName());
                //jobOfferDetailsVec.add(getPositiveItemReviewCount(itemOfferE.getUserEntity().getUsername()));
                //jobOfferDetailsVec.add(getNeutralItemReviewCount(itemOfferE.getUserEntity().getUsername()));
                //jobOfferDetailsVec.add(getNegativeItemReviewCount(itemOfferE.getUserEntity().getUsername()));
                jobOfferDetailsVec.add(String.format ("%,.2f", jobOfferE.getJobOfferPrice()));
                jobOfferDetailsVec.add(jobOfferE.getJobOfferDescription());
                jobOfferDetailsVec.add(jobOfferE.getJobOfferStatusForPoster());
                

                long diff = currentDate.getTime() - jobOfferE.getJobOfferDate().getTime();
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
                jobOfferDetailsVec.add(dateString);
                jobOfferDetailsVec.add(df.format(jobOfferE.getJobOfferDate()));
                jobOfferDetailsVec.add(jobOfferE.getJobOfferID());
                jobOfferList.add(jobOfferDetailsVec);
                dateString = "";
            }
        }
        System.out.println("offerList size: " + jobOfferList.size());
        return jobOfferList;
    }
    
    @Override
    public String acceptJobOffer(long jobOfferID, String username){
        
        Query q = em.createQuery("SELECT o FROM JobOffer o WHERE o.jobOfferID = :offerID");
        q.setParameter("offerID", jobOfferID);
        
        if(q.getSingleResult()!=null){
            JobOfferEntity offer = (JobOfferEntity)q.getSingleResult();
            offer.setJobOfferStatusForPoster("Accepted");
            offer.setJobOfferStatusForSender("Accepted");
            
            /* MESSAGE SENDER IS THE JOB POSTER WHO ACCEPTS THE OFFER, MESSAGE RECEIVER IS THE JOB OFFER SENDER */
            mEntity = new MessageEntity();
            mEntity.createContentMessage(offer.getJobEntity().getUserEntity().getUsername(), offer.getUserEntity().getUsername(), 
                        offer.getJobEntity().getUserEntity().getUsername() + " accepted your offer of " + offer.getJobEntity().getJobTitle() + ". Check it out!", 
                        offer.getJobOfferID(), "Errands");
            /* JOB POSTER WHO ACCEPTS THE OFFER IS THE USERENTITY_USERNAME */
            mEntity.setUserEntity(offer.getJobEntity().getUserEntity());
            offer.getJobEntity().getUserEntity().getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(offer);
            //em.merge(jobTakerEntity);
            //em.merge(jobPosterEntity);
            
            return "The offer status is updated sucessfully!";
        }else{
            return "There is something wrong with the offer. Please try again.";
        }  
    }
    
    @Override
    public String rejectJobOffer(long jobOfferID, String username){
        
        Query q = em.createQuery("SELECT o FROM JobOffer o WHERE o.jobOfferID = :offerID");
        q.setParameter("offerID", jobOfferID);
        
        if(q.getSingleResult()!=null){
            JobOfferEntity offer = (JobOfferEntity)q.getSingleResult();
            offer.setJobOfferStatusForPoster("Rejected");
            offer.setJobOfferStatusForSender("Rejected");
            
            /* MESSAGE SENDER IS THE JOB POSTER WHO REJECTS THE OFFER, MESSAGE RECEIVER IS THE JOB OFFER SENDER */
            mEntity = new MessageEntity();
            mEntity.createContentMessage(offer.getJobEntity().getUserEntity().getUsername(), offer.getUserEntity().getUsername(), 
                        offer.getJobEntity().getUserEntity().getUsername() + " rejected your offer of " + offer.getJobEntity().getJobTitle() + ". Check it out!", 
                        offer.getJobOfferID(), "Errands");
            /* JOB POSTER WHO ACCEPTS THE OFFER IS THE USERENTITY_USERNAME */
            mEntity.setUserEntity(offer.getJobEntity().getUserEntity());
            offer.getJobEntity().getUserEntity().getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(offer);
            
            return "The offer status is updated sucessfully!";
        }else{
            return "There is something wrong with the offer. Please try again.";
        }  
    }
    
    public String negotiateJobOffer(long jobOfferID, String username, String negotiateMessage){
        
        Query q = em.createQuery("SELECT o FROM JobOffer o WHERE o.jobOfferID = :offerID");
        q.setParameter("offerID", jobOfferID);
        
        if(q.getSingleResult()!=null){
            JobOfferEntity offer = (JobOfferEntity)q.getSingleResult();
            offer.setJobOfferStatusForPoster("Negotiating");
            offer.setJobOfferStatusForSender("Pending");
            
            /* MESSAGE SENDER IS THE JOB POSTER WHO NEGOTIATES THE OFFER, MESSAGE RECEIVER IS THE JOB OFFER SENDER */
            mEntity = new MessageEntity();
            mEntity.createContentMessage(offer.getJobEntity().getUserEntity().getUsername(), offer.getUserEntity().getUsername(), 
                        offer.getJobEntity().getUserEntity().getUsername() + " negotiates on your offer of " + offer.getJobEntity().getJobTitle() + ". Here is his/her message: " + negotiateMessage, 
                        offer.getJobOfferID(), "Errands");
            /* JOB POSTER WHO ACCEPTS THE OFFER IS THE USERENTITY_USERNAME */
            mEntity.setUserEntity(offer.getJobEntity().getUserEntity());
            offer.getJobEntity().getUserEntity().getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(offer);
            
            return "The offer status is updated sucessfully!";
        }else{
            return "There is something wrong with the offer. Please try again.";
        }
        
    }
    
    @Override
    public List<Vector> viewMyJobOffer(String username){
        
        List<Vector> myJobOfferList = new ArrayList<Vector>();
        
        Query q = em.createQuery("SELECT o FROM JobOffer o WHERE o.userEntity.username = :username");
        q.setParameter("username", username);
        
        if(q.getResultList()!=null){
            for(Object o:q.getResultList()){
                Vector offerInfo = new Vector();
                JobOfferEntity joE = (JobOfferEntity) o; 
                offerInfo.add(joE.getJobEntity().getJobID());
                offerInfo.add(joE.getJobEntity().getJobImage());
                offerInfo.add(joE.getJobEntity().getJobTitle());
                offerInfo.add(joE.getJobEntity().getCategoryEntity().getCategoryName());
                offerInfo.add(rateFormat.format(joE.getJobEntity().getJobRate()));
                offerInfo.add(joE.getJobEntity().getJobRateType());
                offerInfo.add(joE.getJobEntity().getUserEntity().getUsername());
                offerInfo.add(joE.getJobOfferID());
                offerInfo.add(joE.getJobOfferPrice());
                offerInfo.add(joE.getJobOfferDescription());
                offerInfo.add(joE.getJobOfferStatusForSender());
                offerInfo.add(df.format(joE.getJobOfferDate()));
                
                myJobOfferList.add(offerInfo);
            }
        }
        
        return myJobOfferList;
    }
    
    /* USER PROFILE */
    @Override
    public List<Vector> viewUserJobList(String username) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT j FROM Job j WHERE j.userEntity.username = :username AND "
                + "j.categoryEntity.categoryActiveStatus = '1' AND (j.jobStatus = 'Available' OR "
                + "j.jobStatus = 'Reserved' OR j.jobStatus = 'Completed')");
        q.setParameter("username", username);
        List<Vector> userJobList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            JobEntity jE = (JobEntity) o;
            Vector userJobVec = new Vector();

            userJobVec.add(jE.getJobID());
            userJobVec.add(jE.getJobImage());
            userJobVec.add(jE.getJobTitle());
            userJobVec.add(jE.getCategoryEntity().getCategoryName());
            userJobVec.add(jE.getUserEntity().getUsername());
            userJobVec.add(jE.getUserEntity().getImgFileName());

            long diff = currentDate.getTime() - jE.getJobPostDate().getTime();
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
            userJobVec.add(dateString);
            userJobVec.add(df.format(jE.getJobPostDate()));
            userJobVec.add(String.format ("%,.2f", jE.getJobRate()));
            //userJobVec.add(getItemLikeCount(jE.getJobID()));
            userJobVec.add(jE.getJobRateType());
            userJobList.add(userJobVec);
            dateString = "";
        }
        System.out.println("size: " + userJobList.size());
        return userJobList;
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
    
    public JobOfferEntity lookupJobOffer(long jobID, String username) {
        JobOfferEntity joe = new JobOfferEntity();
        try {
            Query q = em.createQuery("SELECT jo FROM JobOffer jo WHERE jo.jobEntity.jobID = :jobID AND jo.userEntity.username = :username");
            q.setParameter("jobID", jobID);
            q.setParameter("username", username);
            joe = (JobOfferEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job offer cannot be found. " + enfe.getMessage());
            em.remove(joe);
            joe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job offer does not exist. " + nre.getMessage());
            em.remove(joe);
            joe = null;
        }
        return joe;
    }
    
    public LikeListingEntity lookupLike(long jobID, String username) {
        LikeListingEntity lle = new LikeListingEntity();
        try {
            Query q = em.createQuery("SELECT l FROM LikeListing l WHERE l.jobEntity.jobID = :jobID AND l.userEntity.username = :username");
            q.setParameter("jobID", jobID);
            q.setParameter("username", username);
            if(q.getSingleResult()==null){
                lle = null;
                System.out.println("like: null");
            }else{
                lle = (LikeListingEntity) q.getSingleResult();
            }
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
    
    public MessageEntity lookupContentMessage(long jobID, String username) {
        MessageEntity me = new MessageEntity();
        try {
            Query q = em.createQuery("SELECT m FROM Message m WHERE m.contentID = :jobID AND m.userEntity.username = :username");
            q.setParameter("jobID", jobID);
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
    
    public Long getJobLikeCount(long jobID) {
        Long jobLikeCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.likeID) FROM LikeListing l WHERE l.jobEntity.jobID = :jobID");
        q.setParameter("jobID", jobID);
        try {
            jobLikeCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ErrandsSysUserMgrBean.getJobLikeCount().getSingleResult()");
            ex.printStackTrace();
        }
        return jobLikeCount;
    }
    
}
