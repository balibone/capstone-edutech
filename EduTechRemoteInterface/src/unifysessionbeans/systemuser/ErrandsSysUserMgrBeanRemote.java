package unifysessionbeans.systemuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface ErrandsSysUserMgrBeanRemote {
    public List<Vector> viewJobList(String username);
    public Vector viewJobDetails(long jobID, String username);
    public List<Vector> viewAssocCategoryJobList(String hiddenCategoryName, long hiddenJobID);
    public String createJobListing(String jobTitle, String jobRateType, double jobRate, double jobDuration, String jobDescription, 
            Date jobWorkDate, String jobImagefileName, long categoryID, String username, String startLocation, 
            String startLat, String startLong, String endLocation, String endLat, String endLong, String jobInformation, int numOfHelpers, boolean checking);
    public String editJobListing(long jobID, String jobTitle, String jobRateType, double jobRate, double jobDuration, String jobDescription, 
            Date jobWorkDate, String jobImagefileName, String startLocation, String startLat, String startLong, 
            String endLocation, String endLat, String endLong, long jobCategoryID, String username, int numOfHelpers, boolean checking);
    public String deleteJobListing(long jobIDToDelete);
    public String likeUnlikeJob(long jobIDHid, String username);
    public List<Vector> viewJobLikeList(long jobID);
    public List<Vector> viewJobCategoryList();
    public ArrayList<String> getJobCategoryList();
    public String sendJobOfferPrice(long jobID, String username, String jobOfferPrice, String jobOfferDescription);
    public String editJobOfferPrice(long jobID, String username, String jobOfferPrice, String jobOfferDescription);
    public String deleteJobOffer(long jobOfferIDToDelete);
    public List<Vector> viewOfferListOfAJob(String username, long urljobID);
    public String acceptJobOffer(long jobOfferID, String username);
    public String rejectJobOffer(long jobOfferID, String username);
    public String negotiateJobOffer(long jobOfferID, String username, String negotiateMessage);
    public String completeAJob(String username, long jobID);
    public Vector viewTransactionJobDetails(long jobID, long jobTransID, String username);
    public List<Vector> viewJobTransaction(String username);
    public List<Vector> viewUserJobWishlist(String username);
    public List<Vector> viewUserJobList(String username);
    public List<Vector> viewMyJobOffer(String username);
    public String reportJobListing(long jobID, String username, String reportReason);
}