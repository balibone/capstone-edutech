/***************************************************************************************
*   Title:                  ErrandsAdminMgrBeanRemote.java
*   Purpose:                LIST OF REMOTE INTERFACE METHODS FOR UNIFY ERRANDS - ADMIN (EDUBOX)
*   Created & Modified By:  CHEN MENG
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.admin;

import javax.ejb.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Remote
public interface ErrandsAdminMgrBeanRemote {
    public List<Vector> getAllJobListing();
    public Vector getJobDetails(long jobID);
    public boolean deleteJobListing(long jobID);
    
    public List<Vector> getAllJobCategory();
    public boolean createNewJobCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage);
    public Vector getJobCategoryDetails(long jobCategoryID);
    public boolean updateJobCategory(long jobCategoryID, String newJobCategoryName, String newJobCategoryDescription, String fileName);
    public String deactivateJobCategory(long jobCategoryID);
    public boolean activateJobCategory(long jobCategoryID);
    public ArrayList<Vector> getCategoryJobListing(long categoryID);
    
    public List<Vector> getJobReviews(long jobID);
    public List<Vector> getJobTransaction(long jobID);
    public ArrayList<Vector> getAllJobTransactions();
    
    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    public Long getErrandsTransTodayCount();
    public Long getActiveJobCategoryListCount();
    public Long getInactiveJobCategoryListCount();
    public Long getJobListingCount();
}