/***************************************************************************************
*   Title:                  ContentAdminMgrBeanRemote.java
*   Purpose:                LIST OF REMOTE INTERFACE METHODS FOR UNIFY CONTENT ADMINISTRATION - ADMIN (EDUBOX)
*   Created By:             NIGEL LEE TJON YI
*   Modified By:            TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   24 FEBRUARY 2018
*   Code version:           1.1
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

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
public String resolveErrand(String reportID);
public String unresolveErrand(String reportID);
public String deleteJob(String jobID);
public Long getUnresolvedErrandsReportCount();
public Long getResolvedErrandsReportCount();
//jobs review related
public List<Vector> viewReportedErrandsReviewListing();
public Vector viewErrandReviewDetails(String errandReviewReportID);
public String resolveErrandReview(String reportReviewID);
public String unresolveErrandReview(String reportReviewID);
public String deleteJobReview(String jobReviewID);
public Long getUnresolvedErrandsReviewReportCount();
public Long getResolvedErrandsReviewReportCount();
//marketplace related
public Vector viewMarketplaceDetails(String marketplaceReportID);
public Vector viewMarketplaceDetails2(String marketplaceReportID);
public String resolveMarketplace(String reportID);
public String unresolveMarketplace(String reportID);
public String deleteItem(String itemID);
public Long getUnresolvedItemReportCount();
public Long getResolvedItemReportCount();
//company reviews related
public Vector viewReviewDetails(String reviewReportID);
public Vector viewReviewDetails2(String reviewReportID);
public String resolveReview(String reportID);
public String unresolveReview(String reportID);
public String deleteReview(String reviewID);
public Long getUnresolvedCompanyReviewReportCount();
public Long getResolvedCompanyReviewReportCount();
//event related
public Vector viewEventRequestDetails(String requestID);
public String approveEventRequest(String requestID);
public String rejectEventRequest(String requestID);
public Long getPendingEventRequestCount();
public Long getApprovedEventRequestCount();
public Long getRejectedEventRequestCount();

    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    public Long getTagsListCount();
}
