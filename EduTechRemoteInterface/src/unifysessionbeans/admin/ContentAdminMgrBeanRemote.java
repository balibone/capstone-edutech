package unifysessionbeans.admin;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface ContentAdminMgrBeanRemote {
    /* TAGS */
    public List<Vector> viewTagListing();
    public String createTag(String tagName, String tagType);
    public Vector viewTagDetails(String tagID);
    public String updateTag(String tagID, String tagName, String tagType);
    public String deleteTag(String tagID);
    
    public List<Vector> viewReportedMarketplaceListing();
    public List<Vector> viewReportedReviewListing();
    public List<Vector> viewReportedErrandsListing();
    public List<Vector> viewEventRequestListing();
//jobs related
public Vector viewErrandDetails(String errandReportID);
public Vector viewErrandDetails2(String errandReportID);
public boolean resolveErrand(String reportID);
public boolean unresolveErrand(String reportID);
public boolean deleteJob(String jobID);
public Long getUnresolvedErrandsReportCount();
public Long getResolvedErrandsReportCount();
//jobs review related
public List<Vector> viewReportedErrandsReviewListing();
public Vector viewErrandReviewDetails(String errandReviewReportID);
public boolean resolveErrandReview(String reportReviewID);
public boolean unresolveErrandReview(String reportReviewID);
public boolean deleteJobReview(String jobReviewID);
public Long getUnresolvedErrandsReviewReportCount();
public Long getResolvedErrandsReviewReportCount();
//marketplace related
public Vector viewMarketplaceDetails(String marketplaceReportID);
public Vector viewMarketplaceDetails2(String marketplaceReportID);
public boolean resolveMarketplace(String reportID);
public boolean unresolveMarketplace(String reportID);
public boolean deleteItem(String itemID);
public Long getUnresolvedItemReportCount();
public Long getResolvedItemReportCount();
//company reviews related
public Vector viewReviewDetails(String reviewReportID);
public Vector viewReviewDetails2(String reviewReportID);
public boolean resolveReview(String reportID);
public boolean unresolveReview(String reportID);
public boolean deleteReview(String reviewID);
public Long getUnresolvedCompanyReviewReportCount();
public Long getResolvedCompanyReviewReportCount();
//event related
public Vector viewEventRequestDetails(String requestID);
public boolean approveEventRequest(String requestID);
public boolean rejectEventRequest(String requestID);
public Long getPendingEventRequestCount();
public Long getApprovedEventRequestCount();
public Long getRejectedEventRequestCount();

    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    public Long getTagsListCount();
}
