/***************************************************************************************
*   Title:                  VoicesSysUserMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY COMPANY REVIEW - SYSUSER (EDUBOX)
*   Created & Modified By:  ZHU XINYI
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.systemuser;

import commoninfrastructureentities.UserEntity;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import unifyentities.common.CategoryEntity;
import unifyentities.common.CompanyReviewReportEntity;
import unifyentities.common.LikeListingEntity;
import unifyentities.voices.CompanyEntity;
import unifyentities.voices.CompanyRequestEntity;
import unifyentities.voices.CompanyReviewEntity;

@Stateless
public class VoicesSysUserMgrBean implements VoicesSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;

    private CompanyEntity companyEntity;
    private CategoryEntity categoryEntity;
    private CompanyReviewEntity reviewEntity;
    private CompanyRequestEntity requestEntity;
    private CompanyReviewReportEntity reportEntity;
    private Collection<CompanyReviewEntity> companyReviewSet;
    private Collection<LikeListingEntity> reviewLikes;
    
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    DecimalFormat df_num = new DecimalFormat("0.00");
    
    @Override
    public List<Vector> viewCompanyList() {
        Query q = em.createQuery("SELECT c FROM Company c WHERE c.companyStatus='Active' AND c.categoryEntity.categoryActiveStatus = '1'");
        List<Vector> companyList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()) {
            CompanyEntity companyE = (CompanyEntity) o;
            Collection<CompanyReviewEntity> reviewList = companyE.getCompanyReviewSet();
            Vector companyVec = new Vector();
            
            companyVec.add(companyE.getCompanyID());
            companyVec.add(companyE.getCompanyImage());
            companyVec.add(companyE.getCompanyName());
            companyVec.add(companyE.getCategoryEntity().getCategoryName());
            companyVec.add(companyE.getCompanyWebsite());
            companyVec.add(companyE.getCompanyHQ());
            companyVec.add(companyE.getCompanySize());
            companyVec.add(reviewList.size());
            if (reviewList.size()!=0) {
                List<Date> reviewDateList = new ArrayList<Date>();
                double aveReviewRating = 0.0;
                for(Object obj: reviewList) {
                    CompanyReviewEntity cre = (CompanyReviewEntity) obj;
                    reviewDateList.add(cre.getReviewDate());
                    aveReviewRating += cre.getReviewRating();
                }
                
                Collections.sort(reviewDateList, new Comparator<Date>(){
                    public int compare(Date o1, Date o2) {
                        return o1.compareTo(o2);
                    }
                });
                
                companyVec.add(df.format(reviewDateList.get(reviewDateList.size()-1)));
                companyVec.add(df_num.format(aveReviewRating/reviewDateList.size()));
            } else {
                companyVec.add("----/--/--");
                companyVec.add(0.0);
            }
            
            companyList.add(companyVec);
        }
        return companyList;
    }
    
    @Override
    public Vector viewCompanyDetails(long companyID) {
        CompanyEntity compEntity = lookupCompany(companyID);
        Vector companyDetailsVec = new Vector();

        if (compEntity != null) {
            companyDetailsVec.add(compEntity.getCompanyID());
            companyDetailsVec.add(compEntity.getCompanyImage());
            companyDetailsVec.add(compEntity.getCompanyName());
            companyDetailsVec.add(compEntity.getCategoryEntity().getCategoryName());
            companyDetailsVec.add(compEntity.getCompanyWebsite());
            companyDetailsVec.add(compEntity.getCompanyHQ());
            companyDetailsVec.add(compEntity.getCompanySize());
            if(compEntity.getCompanyReviewSet().isEmpty()) {
                companyDetailsVec.add(0.00);
            } else {
                companyReviewSet = compEntity.getCompanyReviewSet();
                double rating = 0.00;
                for(Object o: companyReviewSet) {
                    CompanyReviewEntity companyReview = (CompanyReviewEntity) o;
                    rating += companyReview.getReviewRating();
                }
                rating = rating/companyReviewSet.size();
                companyDetailsVec.add(df_num.format(rating));
            }
            companyDetailsVec.add(compEntity.getCompanyDescription());
            companyDetailsVec.add(compEntity.getCompanyAddress());
        }
        return companyDetailsVec;
        
    }
    
    @Override
    public List<Vector> viewAssociatedReviewList(long companyID, String username) {
        companyEntity = lookupCompany(companyID);
        List<Vector> companyReviewList = new ArrayList<Vector>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        if (companyEntity.getCompanyReviewSet()!= null) {
            companyReviewSet = companyEntity.getCompanyReviewSet();
            if (!companyReviewSet.isEmpty()) {
                for (CompanyReviewEntity cre : companyReviewSet) {
                    Vector companyReviewDetails = new Vector();
                    
                    if(cre.getReviewStatus().equals("Active")) {
                        companyReviewDetails.add(df.format(cre.getReviewDate()));
                        /* WE ASSUME THAT THE PERSON WHO POST THE REVIEW IS THE ONE WHO CREATES THE RECORD */
                        companyReviewDetails.add(cre.getUserEntity().getUsername());
                        companyReviewDetails.add(cre.getReviewTitle());
                        companyReviewDetails.add(cre.getReviewPros());
                        companyReviewDetails.add(cre.getReviewCons());
                        companyReviewDetails.add(cre.getReviewEmpType());
                        companyReviewDetails.add(cre.getReviewSalaryRange());
                        companyReviewDetails.add(cre.getReviewRating());
                        companyReviewDetails.add(cre.getReviewThumbsUp());
                        companyReviewDetails.add(cre.getReviewID());
                        if(lookupLike(cre.getReviewID(), username) == null) { companyReviewDetails.add(false);}
                        else { companyReviewDetails.add(true); }
                        companyReviewList.add(companyReviewDetails);
                    }
                }
            } else {
                Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.companyEntity.companyID = :companyID AND cr.reviewStatus='Active'");
                q.setParameter("companyID", companyEntity.getCompanyID());

                for (Object o : q.getResultList()) {
                    CompanyReviewEntity cre = (CompanyReviewEntity) o;
                    Vector companyReviewVec = new Vector();
                    
                    companyReviewVec.add(cre.getReviewDate());
                    /* WE ASSUME THAT THE PERSON WHO POST THE REVIEW IS THE ONE WHO CREATES THE RECORD */
                    companyReviewVec.add(cre.getUserEntity().getUsername());
                    companyReviewVec.add(cre.getReviewTitle());
                    companyReviewVec.add(cre.getReviewPros());
                    companyReviewVec.add(cre.getReviewCons());
                    companyReviewVec.add(cre.getReviewEmpType());
                    companyReviewVec.add(cre.getReviewSalaryRange());
                    companyReviewVec.add(cre.getReviewRating());
                    companyReviewVec.add(cre.getReviewThumbsUp());
                    companyReviewVec.add(cre.getReviewID());
                    companyReviewList.add(companyReviewVec);
                }
            }
        }
        return companyReviewList;
    }
    
    @Override
    public List<Vector> viewCompanyInSameIndustry(long companyID) {
        companyEntity = lookupCompany(companyID);
        String companyIndustry = companyEntity.getCategoryEntity().getCategoryName();
        
        Query q = em.createQuery("SELECT c FROM Company c WHERE c.companyStatus='Active' AND c.categoryEntity.categoryName = :companyIndustry");
        q.setParameter("companyIndustry", companyIndustry);
        
        List<Vector> companyList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()) {
            CompanyEntity companyE = (CompanyEntity) o;
            Collection<CompanyReviewEntity> reviewList = companyE.getCompanyReviewSet();
            Vector companyVec = new Vector();
            
            if(companyE.getCompanyID()!= companyID) {
                companyVec.add(companyE.getCompanyID());
            companyVec.add(companyE.getCompanyImage());
            companyVec.add(companyE.getCompanyName());
            if (reviewList.size()!=0) {
                double aveReviewRating = 0.0;
                for(Object obj: reviewList) {
                    CompanyReviewEntity cre = (CompanyReviewEntity) obj;
                    aveReviewRating += cre.getReviewRating();
                }
                companyVec.add(df_num.format(aveReviewRating/reviewList.size()));
            } else {
                companyVec.add(0.0);
            }
            companyList.add(companyVec);
            }
        }
        return companyList;
    }
    
    @Override
    public String createCompanyReview(String companyIndustry, String companyName, String reviewTitle, String reviewPros, 
                String reviewCons, String reviewRating, String employmentStatus, String salaryRange, String reviewPoster) {
        
        reviewEntity = new CompanyReviewEntity();
        UserEntity userEntity = lookupSystemUser(reviewPoster);
        companyEntity = lookupCompany(companyName, companyIndustry);
        if (reviewEntity.createReview(companyEntity, reviewTitle,reviewPros, reviewCons, Double.parseDouble(reviewRating), employmentStatus, salaryRange, userEntity)) {
                em.persist(reviewEntity);
                userEntity.getCompanyReviewSet().add(reviewEntity);
                companyEntity.getCompanyReviewSet().add(reviewEntity);
                em.merge(userEntity);
                em.merge(companyEntity);
                return "Company review has been created successfully!";
            } else {
                return "There were some issues encountered while creating the company review. Please try again.";
            }
    } 
    
    @Override
    public String createCompanyRequest(String requestCompany, String companyIndustry, String requestComment, String requestPoster) {
        
        requestEntity = new CompanyRequestEntity();
        UserEntity userEntity = lookupSystemUser(requestPoster);
        if (requestEntity.createRequest(requestCompany, companyIndustry, requestComment, userEntity)) {
                em.persist(requestEntity);
                userEntity.getCompanyRequestSet().add(requestEntity);
                em.merge(userEntity);
                return "Your request has been sent successfully!";
            } else {
                return "There were some issues encountered while sending the request. Please try again.";
            }
    }
    
    @Override
    public String createReviewReport(String reviewID, String reviewPoster, String reportDescription, String reviewReporter) {
        reportEntity = new CompanyReviewReportEntity();
        UserEntity reporter = lookupSystemUser(reviewReporter);
        System.out.println(reviewReporter);
        if (reportEntity.createReport(reviewPoster, reportDescription, reviewID, reporter)) {
                em.persist(reportEntity);
                reporter.getCompanyReviewReportSet().add(reportEntity);
                em.merge(reporter);
                return "Your report has been sent successfully!";
            } else {
                return "There were some issues encountered while sending the report. Please try again.";
            }
    }
    
    @Override
    public List<String> populateCompanyIndustry() {
        List<String> companyIndustryList = new ArrayList<String>();
            Query q = em.createQuery("SELECT c from Category c WHERE c.categoryType = 'Voices' AND c.categoryActiveStatus = '1' ORDER BY c.categoryName ASC");

        for (Object o : q.getResultList()) {
            CategoryEntity ce = (CategoryEntity) o;
            companyIndustryList.add(ce.getCategoryName());
        }
        
        return companyIndustryList;
    }
    
    @Override
    public String populateCompanyIndustryString() {
        String companyIndustryStr = "";
            Query q = em.createQuery("SELECT c from Category c WHERE c.categoryType = 'Voices' AND c.categoryActiveStatus = '1'");

        for (Object o : q.getResultList()) {
            CategoryEntity ce = (CategoryEntity) o;
            companyIndustryStr += ce.getCategoryName() + ";";
        }
        if(companyIndustryStr.length()!=0) {
            companyIndustryStr = companyIndustryStr.substring(0, companyIndustryStr.length()-1);
        }
        return companyIndustryStr;
    }
    
    public UserEntity lookupSystemUser(String username) {
        UserEntity ue = new UserEntity();
        try {
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: System User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: System User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }
    
    public CompanyEntity lookupCompany(long companyID) {
        CompanyEntity ce = new CompanyEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Company c WHERE c.companyID = :companyID");
            q.setParameter("companyID", companyID);
            ce = (CompanyEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Company cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Company does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    public CompanyEntity lookupCompany(String companyName, String companyIndustry) {
        CompanyEntity ce = new CompanyEntity();
        System.out.println(companyIndustry);
        try {
            Query q = em.createQuery("SELECT c from Company c WHERE c.companyName=:companyName And c.categoryEntity.categoryName=:companyIndustry");
            q.setParameter("companyName", companyName);
            q.setParameter("companyIndustry",companyIndustry);
            ce = (CompanyEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Company cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Company does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    public LikeListingEntity lookupLike(long reviewID, String username) {
        LikeListingEntity lle = new LikeListingEntity();
        try {
            Query q = em.createQuery("SELECT l FROM LikeListing l WHERE l.reviewEntity.reviewID = :reviewID AND l.userEntity.username = :username");
            q.setParameter("reviewID", reviewID);
            q.setParameter("username", username);
            lle = (LikeListingEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Like cannot be found. " + enfe.getMessage());
            em.remove(lle);
            lle = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Like does not exist. " + nre.getMessage());
            em.remove(lle);
            lle = null;
        }
        return lle;
    }
}
