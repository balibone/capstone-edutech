package unifysessionbeans.admin;

import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import unifyentities.common.CategoryEntity;
import unifyentities.errands.JobEntity;
import unifyentities.errands.JobReviewEntity;
import unifyentities.errands.JobTransactionEntity;


@Stateless
public class ErrandsAdminMgrBean implements ErrandsAdminMgrBeanRemote {
    @PersistenceContext
    EntityManager em;

    public ErrandsAdminMgrBean(){
    }


    public List<Vector> getAllJobListing(){
        Query q = em.createQuery("SELECT j FROM Job j");
        List<Vector> jobList = new ArrayList();
        for (Object o: q.getResultList()){
            JobEntity job = (JobEntity)o;
            Vector jobDetails = new Vector();
            jobDetails.add(job.getJobID());
            jobDetails.add(job.getJobTitle());
            jobDetails.add(job.getJobDescription());
            jobDetails.add(job.getJobPosterID());
            jobDetails.add(job.getJobTakerID());
            jobDetails.add(job.getJobWorkDate());
            jobList.add(jobDetails);
        }
        return jobList;
    }
    
    public Vector getJobDetails(long jobID){
        Vector jobDetails = new Vector();
        Query q = em.createQuery("SELECT j FROM Job j WHERE j.jobID=:id");
        q.setParameter("id", jobID);
        if(q.getSingleResult() != null){
            JobEntity job = (JobEntity)q.getSingleResult();
            jobDetails.add(job.getJobImage());
            jobDetails.add(jobID);
            jobDetails.add(job.getJobCategory().getCategoryName());
            jobDetails.add(job.getJobTitle());
            jobDetails.add(job.getJobDescription());
            jobDetails.add(job.getJobStartLocation());
            jobDetails.add(job.getJobEndLocation());
            jobDetails.add(job.getJobRateType());
            jobDetails.add(job.getJobRate());
            jobDetails.add(job.getJobPosterID());
            jobDetails.add(job.getJobTakerID());
            jobDetails.add(job.getJobWorkDate());
        }
        return jobDetails;
    }
    
    public List<Vector> getJobReviews(long jobID){
        List<Vector> jobReviews = new ArrayList();
        //jobReviews = null;
        Query q = em.createQuery("SELECT j FROM Job j WHERE j.jobID=:id");
        q.setParameter("id", jobID);
        if(q.getSingleResult() != null){
            JobEntity job = (JobEntity)q.getSingleResult();
            if(job.getJobReviews()!=null){
                for(int i=0; i<job.getJobReviews().size(); i++){
                    JobReviewEntity jobReview = job.getJobReviews().get(i);
                    if(jobReview != null){
                        Vector reviewDetails = new Vector();
                        reviewDetails.add(jobReview.getJobReviewID());
                        reviewDetails.add(jobReview.getJobReviewIndex());
                        reviewDetails.add(jobReview.getJobReviewContent());
                        reviewDetails.add(jobReview.getJobReviewerID());
                        reviewDetails.add(jobReview.getJobReceiverID());
                        reviewDetails.add(jobReview.getJobReviewDate());
                        jobReviews.add(reviewDetails);
                    }
                }
            }
        }
        return jobReviews;
    }
    
    public Vector getJobTransaction(long jobID){
        Vector jobTransaction = null;
        Query q = em.createQuery("SELECT j FROM Job j WHERE j.jobID=:id");
        q.setParameter("id", jobID);
        if(q.getSingleResult() != null){
            JobTransactionEntity jobTran = ((JobEntity)q.getSingleResult()).getJobTransaction();
            if(jobTran != null){
                jobTransaction = new Vector();
                jobTransaction.add(jobTran.getJobTransactionID());
                jobTransaction.add(jobTran.getJobTransactionDate());
                jobTransaction.add(jobTran.getJobPosterID());
                jobTransaction.add(jobTran.getJobTakerID());
            }
        }
        return jobTransaction;
    }

    public boolean deleteJobListing(long jobID){
        boolean delete = true;
        Query q = em.createQuery("SELECT j FROM Job j WHERE j.jobID=:id");
        q.setParameter("id", jobID);
        if(q.getSingleResult() == null){
            delete = false;
        }else{
            JobEntity j = (JobEntity)q.getSingleResult();
            em.remove(j);
        }
        
        return delete;
    }

    public List<Vector> getAllJobCategory(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType=:type");
        q.setParameter("type", "errands");
        List<Vector> categoryList = new ArrayList();
        for (Object o: q.getResultList()){
            CategoryEntity c = (CategoryEntity)o;
            Vector categoryDetails = new Vector();
            categoryDetails.add(c.getCategoryImage());
            categoryDetails.add(c.getCategoryID());
            categoryDetails.add(c.getCategoryName());
            categoryDetails.add(c.getCategoryDescription());
            if(c.getCategoryActiveStatus())
                categoryDetails.add("Active");
            else
                categoryDetails.add("Inactive");
            categoryList.add(categoryDetails);
      }
      return categoryList;
    }
    
    public boolean createNewJobCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.createCategory(categoryName, categoryType, categoryDescription, categoryImage);
        em.persist(categoryEntity);
        return true;
    }
    
    public Vector getJobCategoryDetails(long jobCategoryID){
        Vector jobCategoryDetails = new Vector();
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID=:id");
        q.setParameter("id", jobCategoryID);
        if(q.getSingleResult() != null){
            CategoryEntity cEntity = (CategoryEntity)q.getSingleResult();
            jobCategoryDetails.add(cEntity.getCategoryImage());
            jobCategoryDetails.add(jobCategoryID);
            jobCategoryDetails.add(cEntity.getCategoryName());
            jobCategoryDetails.add("errands");
            jobCategoryDetails.add(cEntity.getCategoryDescription());
            if(cEntity.getCategoryActiveStatus()){
                jobCategoryDetails.add("Active");
            }else{
                jobCategoryDetails.add("Inactive");
            }
        }
        return jobCategoryDetails;
    }
    
    public boolean updateJobCategory(long categoryID, String newCategoryName, String newCategoryDescription, String fileName) {
        boolean icUpdateStatus = true;
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID =:id");
        q.setParameter("id", categoryID);
        if (q.getSingleResult() == null) {
            icUpdateStatus = false;
        } else {
            CategoryEntity cEntity = (CategoryEntity) q.getSingleResult();
            cEntity.setCategoryName(newCategoryName);
            cEntity.setCategoryDescription(newCategoryDescription);
            cEntity.setCategoryImage(fileName);
            em.merge(cEntity);
        }
        return icUpdateStatus;
    }

    public boolean deactivateJobCategory(long cID){
      Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID=:id");
      q.setParameter("id", cID);
      Object o = q.getSingleResult();
      CategoryEntity categoryEntity = (CategoryEntity)o;
      categoryEntity.setCategoryActiveStatus(false);
      return true;
    }
    
    public boolean activateJobCategory(long cID){
      Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID=:id");
      q.setParameter("id", cID);
      Object o = q.getSingleResult();
      CategoryEntity categoryEntity = (CategoryEntity)o;
      categoryEntity.setCategoryActiveStatus(true);
      return true;
    }
    

}
