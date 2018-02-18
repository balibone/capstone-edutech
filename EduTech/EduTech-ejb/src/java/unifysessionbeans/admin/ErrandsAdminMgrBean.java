package unifysessionbeans.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import unifyentities.common.CategoryEntity;
import unifyentities.errands.JobEntity;
import unifyentities.errands.JobReviewEntity;
import unifyentities.errands.JobTransactionEntity;

@Stateless
public class ErrandsAdminMgrBean implements ErrandsAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private JobEntity jEntity;
    private CategoryEntity cEntity;
    private Collection<JobEntity> jobSet;
    private Collection<JobReviewEntity> jobReviewSet;
    private Collection<JobTransactionEntity> jobTransactionSet;
    
    @Override
    public List<Vector> getAllJobListing() {
        Query q = em.createQuery("SELECT j FROM Job j");
        List<Vector> jobList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            JobEntity jobE = (JobEntity) o;
            Vector jobVec = new Vector();
            
            jobVec.add(jobE.getJobID());
            jobVec.add(jobE.getJobTitle());
            jobVec.add(jobE.getJobDescription());
            jobVec.add(jobE.getJobPosterID());
            jobVec.add(jobE.getJobTakerID());
            jobVec.add(jobE.getJobWorkDate());
            
            jobList.add(jobVec);
        }
        return jobList;
    }

    @Override
    public Vector getJobDetails(long jobID) {
        jEntity = lookupJob(jobID);
        Vector jobDetailsVec = new Vector();
        
        if (jEntity != null) {
            jobDetailsVec.add(jEntity.getJobImage());
            jobDetailsVec.add(jobID);
            jobDetailsVec.add(jEntity.getJobStatus());
            jobDetailsVec.add(jEntity.getCategoryEntity().getCategoryName());
            jobDetailsVec.add(jEntity.getJobTitle());
            jobDetailsVec.add(jEntity.getJobDescription());
            jobDetailsVec.add(jEntity.getJobStartLocation());
            jobDetailsVec.add(jEntity.getJobEndLocation());
            jobDetailsVec.add(jEntity.getJobRateType());
            jobDetailsVec.add(jEntity.getJobRate());
            jobDetailsVec.add(jEntity.getJobPosterID());
            jobDetailsVec.add(jEntity.getJobWorkDate());
            return jobDetailsVec;
        }
        return null;
    }

    @Override
    public List<Vector> getJobReviews(long jobID) {
        jEntity = lookupJob(jobID);
        List<Vector> jobReviewList = new ArrayList<Vector>();
        
        if (jEntity.getJobReviewSet() != null) {
            jobReviewSet = jEntity.getJobReviewSet();
            if (!jobReviewSet.isEmpty()) {
                for (JobReviewEntity jre : jobReviewSet) {
                    Vector jobReviewDetails = new Vector();
                    jobReviewDetails.add(jre.getJobReviewID());
                    jobReviewDetails.add(jre.getJobReviewIndex());
                    jobReviewDetails.add(jre.getJobReviewContent());
                    jobReviewDetails.add(jre.getJobReviewerID());
                    jobReviewDetails.add(jre.getJobReceiverID());
                    jobReviewDetails.add(jre.getJobReviewDate());
                    jobReviewList.add(jobReviewDetails);
                }
            }
        }
        return jobReviewList;
    }
    
    
    @Override
    public List<Vector> getJobTransaction(long jobID) {
        jEntity = lookupJob(jobID);
        List<Vector> jobTransactionList = new ArrayList<Vector>();
        
        if (jEntity.getJobTransactionSet() != null) {
            jobTransactionSet = jEntity.getJobTransactionSet();
            if (!jobTransactionSet.isEmpty()) {
                for (JobTransactionEntity jte : jobTransactionSet) {
                    Vector jobReviewDetails = new Vector();
                    jobReviewDetails.add(jte.getJobTransactionID());
                    jobReviewDetails.add(jte.getJobTransactionDate());
                    jobReviewDetails.add(jte.getJobPosterID());
                    jobReviewDetails.add(jte.getJobTakerID());
                    jobTransactionList.add(jobReviewDetails);
                }
            }
        }
        System.out.println("Transaction: " + jobTransactionList.size());
        return jobTransactionList;
    }
    
    
    @Override
    public boolean deleteJobListing(long jobID) {
        /* ADMIN CAN JUST DELETE THE JOB IMMEDIATELY */
        boolean jobDeleteStatus = true;
        if (lookupJob(jobID) == null) {
            jobDeleteStatus = false;
        } else {
            jEntity = lookupJob(jobID);
            em.remove(jEntity);
            em.flush();
            em.clear();
        }
        return jobDeleteStatus;
    }

    @Override
    public List<Vector> getAllJobCategory(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'errands'");
        List<Vector> jobCategoryList = new ArrayList<Vector>();
        
        for (Object o: q.getResultList()){
            CategoryEntity categoryE = (CategoryEntity) o;
            Vector jobCategoryVec = new Vector();
            jobCategoryVec.add(categoryE.getCategoryImage());
            jobCategoryVec.add(categoryE.getCategoryID());
            jobCategoryVec.add(categoryE.getCategoryName());
            jobCategoryVec.add(categoryE.getCategoryDescription());
            jobCategoryVec.add(categoryE.getCategoryActiveStatus());
            jobCategoryList.add(jobCategoryVec);
        }
        return jobCategoryList;
    }

    @Override
    public boolean createNewJobCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage) {
        cEntity = new CategoryEntity();
        cEntity.createCategory(categoryName, categoryType, categoryDescription, categoryImage);
        em.persist(cEntity);
        return true;
    }

    @Override
    public Vector getJobCategoryDetails(long jobCategoryID) {
        cEntity = lookupJobCategory(jobCategoryID);
        System.out.println("ID: "+cEntity.getCategoryID());
        Vector jobCategoryDetailsVec = new Vector();
        
        if (cEntity != null) {
            jobCategoryDetailsVec.add(cEntity.getCategoryImage());
            jobCategoryDetailsVec.add(jobCategoryID);
            jobCategoryDetailsVec.add(cEntity.getCategoryName());
            jobCategoryDetailsVec.add("errands");
            jobCategoryDetailsVec.add(cEntity.getCategoryDescription());
            /* CATEGORY ACTIVE STATUS IS DEFAULT TRUE */
            if (cEntity.getCategoryActiveStatus()) { jobCategoryDetailsVec.add("Active"); } 
            else { jobCategoryDetailsVec.add("Inactive"); }
        }
        System.out.println("list size: " + jobCategoryDetailsVec.size());
        return jobCategoryDetailsVec;
    }
    
    @Override
    public ArrayList<Vector> getCategoryJobListing(long categoryID){
        Query q0 = em.createQuery("SELECT c FROM Category c WHERE c.categoryID = :id");
        q0.setParameter("id", categoryID);
        CategoryEntity category = (CategoryEntity) q0.getSingleResult();
        
        Query q = em.createQuery("SELECT j FROM Job j WHERE j.categoryEntity = :category");
        q.setParameter("category", category);
        ArrayList<Vector> jobL = new ArrayList();
        
        if(q.getResultList()!=null){
            Vector jobList = (Vector)q.getResultList();
            for(int i=0; i<jobList.size(); i++){    
                JobEntity jEntity = (JobEntity) jobList.get(i);
                Vector job = new Vector();
                job.add(jEntity.getJobID());
                job.add(jEntity.getJobTitle());
                job.add(jEntity.getJobDescription());
                job.add(jEntity.getJobWorkDate());
                job.add(jEntity.getJobStatus());
                jobL.add(job);
            }
        }
        return jobL;
    }

    @Override
    public boolean updateJobCategory(long jobCategoryID, String newJobCategoryName, String newJobCategoryDescription, String fileName) {
        boolean jcUpdateStatus = true;
        if (lookupJobCategory(jobCategoryID) == null) {
            jcUpdateStatus = false;
        } else {
            cEntity = lookupJobCategory(jobCategoryID);
            cEntity.setCategoryName(newJobCategoryName);
            cEntity.setCategoryDescription(newJobCategoryDescription);
            cEntity.setCategoryImage(fileName);
            em.merge(cEntity);
        }
        return jcUpdateStatus;
    }

    @Override
    public String deactivateJobCategory(long jobCategoryID) {
        /* DON'T CHANGE THE RETURN STRING (USED FOR SERVLET VALIDATION) */
        boolean jobAvailWithinCat = false;
        
        if (lookupJobCategory(jobCategoryID) == null) {
            return "Selected job category cannot be found.";
        } else {
            cEntity = em.find(CategoryEntity.class, lookupJobCategory(jobCategoryID).getCategoryID());
            jobSet = cEntity.getJobSet();
            
            /* IF THERE IS ITEM INSIDE THE JOB CATEGORY */
            if (!jobSet.isEmpty()){
                em.clear();
                for (JobEntity je : jobSet) {
                    /* IF THE JOB INSIDE THE JOB CATEGORY IS "AVAILABLE", THEN CANNOT DEACTIVATE THE JOB CATEGORY */
                    if((je.getJobStatus()).equals("Active")) {
                        jobAvailWithinCat = true;
                        break;
                    }
                }
                if (jobAvailWithinCat == true) {
                    return "There are currently active jobs inside this job category. Cannot be deactivated.";
                } else {
                    return "Selected job category has been deactivated successfully!";
                }
            }
            /* IF THERE IS NO ITEMS INSIDE THE JOB CATEGORY, PROCEED TO DEACTIVATE THE JOB CATEGORY */
            else{
                cEntity.setCategoryActiveStatus(false);
                em.merge(cEntity);
                return "Selected item category has been deactivated successfully!";
            }
        }
    }

    @Override
    public boolean activateJobCategory(long jobCategoryID) {
        boolean jcDeactivateStatus = true;
        if (lookupJobCategory(jobCategoryID) == null) {
            jcDeactivateStatus = false;
        } else {
            cEntity = lookupJobCategory(jobCategoryID);
            cEntity.setCategoryActiveStatus(true);
            em.merge(cEntity);
        }
        return jcDeactivateStatus;
    }
    
    public ArrayList<Vector> getAllJobTransactions(){
        Query q = em.createQuery("SELECT t FROM JobTransaction t");
        ArrayList<Vector> transactionList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            JobTransactionEntity jtEntity = (JobTransactionEntity) o;
            Vector jtVec = new Vector();
            
            jtVec.add(jtEntity.getJobTransactionID());
            jtVec.add(jtEntity.getJobTransactionDate());
            jtVec.add(jtEntity.getJobID());
            jtVec.add(jtEntity.getJobPosterID());
            jtVec.add(jtEntity.getJobTakerID());
            jtVec.add(jtEntity.getJobTransactionRateType());
            jtVec.add(jtEntity.getJobTransactionRate());
            
            transactionList.add(jtVec);
        }
        return transactionList;
    }
    
    @Override
    public String getErrandsTransTodayCount() {
        Query q = em.createQuery("SELECT COUNT(t) FROM JobTransaction t");
        return String.valueOf(q.getFirstResult());
    }
    @Override
    public String getErrandsListingCount() {
        Query q = em.createQuery("SELECT COUNT(j) FROM Job j");
        return String.valueOf(q.getFirstResult());
    }
    
    /* MISCELLANEOUS METHODS */
    public JobEntity lookupJob(long jobID) {
        JobEntity je = new JobEntity();
        try{
            Query q = em.createQuery("SELECT j FROM Job j WHERE j.jobID = :jobID");
            q.setParameter("jobID", jobID);
            je = (JobEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Job cannot be found. " + enfe.getMessage());
            em.remove(je);
            je = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Job does not exist. " + nre.getMessage());
            em.remove(je);
            je = null;
        }
        return je;
    }
    
    public CategoryEntity lookupJobCategory(long jobCategoryID) {
        CategoryEntity ce = new CategoryEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID = :jobCategoryID");
            q.setParameter("jobCategoryID", jobCategoryID);
            ce = (CategoryEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Job Category cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Job Category does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
}