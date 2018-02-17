package unifysessionbeans.admin;

import javax.ejb.Remote;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

@Remote
public interface VoicesAdminMgrBeanRemote {
    public boolean createCompany(String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage);
    public List<Vector> viewCompanyList();
    public List<Vector> viewCompanyInCategoryList(String categoryName, String categoryType);
    public Vector viewCompanyDetails(String companyName);
    public String deactivateCompany(String companyName);
    public boolean activateCompany(String companyName);
    public boolean updateCompany(String oldCompanyName, String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage);
    public List<Vector> viewReviewList(String reviewedCompany);
    public Vector viewReviewDetails(String reviewedCompany, String reviewPosterID);
    public boolean deleteReview(String reviewedCompany, String reviewPosterID);
    public List<Vector> viewCompanyCategoryList();
    public boolean createCompanyCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage);
    public Vector viewCompanyCategoryDetails(String categoryName, String categoryType);
    public String deactivateCompanyCategory(String deactCategoryName, String deactCategoryType);
    public boolean activateCompanyCategory(String actCategoryName, String actCategoryType);
    public boolean updateCompanyCategory(String oldCategoryName, String newCategoryName, String categoryType, 
            String oldCategoryDescription, String newCategoryDescription, String fileName);
    public List<Vector> viewCompanyRequestList();
    public Vector viewCompanyRequestDetails(String requestCompany, String requestPosterID);
    public boolean solveRequest(String requestCompany, String requestPosterID);
    public boolean rejectRequest(String requestCompany, String requestPosterID);
 
    
}