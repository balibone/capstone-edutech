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
    public boolean updateJobCategory(long categoryID, String newCategoryName, String newCategoryDescription, String fileName);
    public boolean deactivateJobCategory(long cID);
    public boolean activateJobCategory(long cID);
    public List<Vector> getJobReviews(long jobID);
    public Vector getJobTransaction(long jobID);
}
