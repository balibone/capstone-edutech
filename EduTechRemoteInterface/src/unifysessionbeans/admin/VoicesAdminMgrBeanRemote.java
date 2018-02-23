/***************************************************************************************
*   Title:                  VoicesAdminMgrBeanRemote.java
*   Purpose:                LIST OF REMOTE INTERFACE METHODS FOR UNIFY COMPANY REVIEW - ADMIN (EDUBOX)
*   Created By:             ZHU XINYI
*   Modified By:            TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.1
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.admin;

import javax.ejb.Remote;
import java.util.List;
import java.util.Vector;

@Remote
public interface VoicesAdminMgrBeanRemote {
    public List<Vector> viewCompanyCategoryList();
    public String populateCompanyIndustry();
    public String createCompanyCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage);
    public Vector viewCompanyCategoryDetails(long companyCategoryID);
    public String deactivateACompanyCategory(long companyCategoryID);
    public String activateACompanyCategory(long companyCategoryID);
    public String updateCompanyCategory(long companyCategoryID, String categoryName, String categoryDescription, 
            String fileName);
    
    public List<Vector> viewCompanyList();
    public String createCompany(String companyIndustry, String companyName, int companySize, String companyWebsite, 
            String companyHQ, String companyDescription, String companyAddress, String fileName);
    public List<Vector> viewAssociatedCompanyList(long companyCategoryID);
    public Vector viewCompanyDetails(String companyName);
    public String deactivateCompany(String companyName);
    public boolean activateCompany(String companyName);
    public boolean updateCompany(String oldCompanyName, String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage);
    public List<Vector> viewReviewList(String reviewedCompany);
    public Vector viewReviewDetails(String reviewedCompany, String reviewPosterID);
    public boolean deleteReview(String reviewedCompany, String reviewPosterID);
    
    public List<Vector> viewCompanyRequestList();
    public Vector viewCompanyRequestDetails(String requestCompany, String requestPosterID);
    public boolean solveRequest(String requestCompany, String requestPosterID);
    public boolean rejectRequest(String requestCompany, String requestPosterID);
    
    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    public Long getCompanyReviewCount();
    public Long getActiveCompanyCategoryListCount();
    public Long getInactiveCompanyCategoryListCount();
    public Long getCompanyListingCount();
    public Long getActiveCompanyListingCount();
    public Long getInactiveCompanyListingCount();
}