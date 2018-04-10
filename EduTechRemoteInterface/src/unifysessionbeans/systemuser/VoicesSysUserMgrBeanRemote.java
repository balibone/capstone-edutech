package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface VoicesSysUserMgrBeanRemote {
    
    public List<Vector> viewCompanyList();
    public Vector viewCompanyDetails(long companyID);
    public List<Vector> viewAssociatedReviewList(long companyID, String username);
    public List<Vector> viewCompanyInSameIndustry(long companyID);
    
    public String createCompanyReview(String companyIndustry, String companyName, String reviewTitle, String reviewPros, 
                String reviewCons, String reviewRating, String employmentStatus, String salaryRange, String reviewPoster);
    public String createCompanyRequest(String requestCompany, String companyIndustry, String requestComment, String requestPoster);
    public String createReviewReport(String reviewID, String reviewPoster, String reportDescription, String reviewReporter);
    public String createResume(String userFullName, String contactNum, String emailAddr, String postalAddr, String summary, String awardStr,
                               String[] eduExprList, String[] proExprList, String[] skillList, String[] workExprList, String[] referenceList, String fileName, String username);
    
    public List<String> populateCompanyIndustry();
    public String populateCompanyIndustryString();
    public boolean deleteReview(long reviewID);
    public boolean deleteResume(long resumeID);
    public boolean cancelRequest(long requestID);
    public String likeUnlikeReview(long reviewIDHid, String usernameHid);
    
    public Vector lookupReviewReport(String username, long reviewID);
    
    public List<Vector> viewUserCompanyReview(String username);
    public List<Vector> viewUserCompanyRequest(String username);
    public List<Vector> viewUserResume(String username);
    public Vector viewResumeBasicDetails(long resumeID);
    public List<Vector> viewEduExprList(long resumeID);
    public List<Vector> viewWorkExprList(long resumeID);
    public List<Vector> viewProjectExprList(long resumeID);
}