package unifysessionbeans.admin;

import javax.ejb.Remote;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

@Remote
public interface VoicesAdminMgrBeanRemote {
    public boolean createCompany(String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage);
    public List<Vector> viewCompanyList();
    public Vector viewCompanyDetails(String companyName);
    public boolean deactivateCompany(String companyName);
    public boolean activateCompany(String companyName);
    public boolean updateCompany(String oldCompanyName, String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage);
    public List<Vector> viewReviewList(String reviewedCompany);
    public Vector viewReviewDetails(String reviewedCompany, String reviewPosterID);
    public boolean deleteReview(String reviewedCompany, String reviewPosterID);
}
