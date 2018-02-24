/***************************************************************************************
*   Title:                  ErrandsAdminMgrBeanRemote.java
*   Purpose:                LIST OF REMOTE INTERFACE METHODS FOR UNIFY ERRANDS - ADMIN (EDUBOX)
*   Created By:             CHEN MENG
*   Modified By:            TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.1
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.admin;

import javax.ejb.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Remote
public interface ErrandsAdminMgrBeanRemote {
    public List<Vector> getAllJobCategory();
    public String createJobCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage);
    public Vector getJobCategoryDetails(long jobCategoryID);
    public List<Vector> viewAssociatedJobList(long categoryID);
    public String updateJobCategory(long jobCategoryID, String categoryName, String categoryDescription, String fileName);
    public String deactivateAJobCategory(long jobCategoryID);
    public String activateAJobCategory(long jobCategoryID);
    
    public List<Vector> getAllJobListing();
    public Vector getJobDetails(long jobID);
    public String deleteAJob(long jobID);
    
    public List<Vector> getJobReviewList(long jobID);
    public List<Vector> getJobTransactionList(long jobID);
    public ArrayList<Vector> getAllJobTransactions();
    
    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    public Long getErrandsTransTodayCount();
    public Long getErrandsTransCount();
    public Long getActiveJobCategoryListCount();
    public Long getInactiveJobCategoryListCount();
    public Long getJobListingCount();
    public Long getAvailableJobListingCount();
    public Long getReservedJobListingCount();
    public Long getCompletedJobListingCount();
    
    /* METHODS FOR UNIFY USER PROFILE */
    public List<Vector> viewUserErrandsList(String username);
    public List<Vector> viewUserErrandsTransactionList(String username);
}