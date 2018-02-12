package unifysessionbeans.admin;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface ContentAdminMgrBeanRemote {
public boolean empLogin(String empNRIC, String empPassword);
public List<Vector> viewReportedMarketplaceListing();
public List<Vector> viewReportedReviewListing();    
public List<Vector> viewReportedErrandsListing();    
public List<Vector> viewTagListing();   

//jobs related
public Vector viewErrandDetails(String errandReportID);
public Vector viewErrandDetails2(String errandReportID);
public boolean resolveErrand(String reportID);
public boolean unresolveErrand(String reportID);
public boolean deleteJob(String jobID);
//marketplace related
public Vector viewMarketplaceDetails(String marketplaceReportID);
public Vector viewMarketplaceDetails2(String marketplaceReportID);
public boolean resolveMarketplace(String reportID);
public boolean unresolveMarketplace(String reportID);
public boolean deleteItem(String itemID);
//company reviews related
public Vector viewReviewDetails(String reviewReportID);
public Vector viewReviewDetails2(String reviewReportID);
public boolean resolveReview(String reportID);
public boolean unresolveReview(String reportID);
public boolean deleteReview(String reviewID);
//tags related
public Vector viewTagDetails2(String tagID);
public boolean createTag(String tagName, String tagType);
public boolean deleteTag(String tagID);
public boolean updateTag(String tagID, String tagName, String tagType);
}

