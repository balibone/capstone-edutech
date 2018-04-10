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
import unifyentities.common.MessageEntity;
import unifyentities.voices.CompanyEntity;
import unifyentities.voices.CompanyRequestEntity;
import unifyentities.voices.CompanyReviewEntity;
import unifyentities.voices.EduExprEntity;
import unifyentities.voices.ProjectExprEntity;
import unifyentities.voices.ReferenceEntity;
import unifyentities.voices.ResumeEntity;
import unifyentities.voices.SkillEntity;
import unifyentities.voices.WorkExprEntity;

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
                        companyReviewDetails.add(getReviewLikeCount(cre.getReviewID()));
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
        UserEntity userEntity = lookupUnifyUser(reviewPoster);
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
        UserEntity userEntity = lookupUnifyUser(requestPoster);
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
        UserEntity reporter = lookupUnifyUser(reviewReporter);
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
    public String createResume(String userFullName, String contactNum, String emailAddr, String postalAddr, String summary, String awardStr,
                               String[] eduExprList, String[] proExprList, String[] skillList, String[] workExprList, String[] referenceList, String fileName, String username) {
        
        UserEntity userEntity = lookupUnifyUser(username);
        ResumeEntity resumeEntity = new ResumeEntity();
        Collection<WorkExprEntity> workExprSet = new ArrayList();
        Collection<EduExprEntity> eduExprSet = new ArrayList();
        Collection<ProjectExprEntity> proExprSet = new ArrayList();
        Collection<SkillEntity> skillSet = new ArrayList();
        Collection<ReferenceEntity> referenceSet = new ArrayList();
        
        for(int i=0;i<workExprList.length;i=i+4) {
            WorkExprEntity workExpr = new WorkExprEntity();
            if(!workExprList[i].equals("")) {
                workExpr.create(workExprList[i], workExprList[i+1], workExprList[i+2], workExprList[i+3], resumeEntity);
                workExprSet.add(workExpr);
            }
        }
        
        for(int i=0;i<eduExprList.length;i=i+4) {
            EduExprEntity eduExpr = new EduExprEntity();
            if(!eduExprList[i].equals("")) {
                eduExpr.create(eduExprList[i], eduExprList[i+1], eduExprList[i+2], eduExprList[i+3], resumeEntity);
                eduExprSet.add(eduExpr);
            }
            
        }
        
        for(int i=0;i<proExprList.length;i=i+2) {
            ProjectExprEntity proExpr = new ProjectExprEntity();
            if(!proExprList[i].equals("")) {
               proExpr.create(proExprList[i], proExprList[i+1], resumeEntity);
               proExprSet.add(proExpr); 
            }
        }
        
        for(int i=0;i<skillList.length;i=i+3) {
            SkillEntity skill = new SkillEntity();
            if(!skillList[i].equals("")) {
                skill.create(skillList[i], skillList[i+1], skillList[i+2], resumeEntity);
                skillSet.add(skill);  
            }
        }
        
        for(int i=0;i<referenceList.length;i=i+4) {
            ReferenceEntity reference = new ReferenceEntity();
            if(!referenceList[i].equals("")) {
                reference.create(referenceList[i], referenceList[i+1], referenceList[i+2], referenceList[i+3], resumeEntity);
                referenceSet.add(reference);
            }
        }
        
        if(resumeEntity.createResume(userFullName, contactNum, emailAddr, postalAddr, summary, awardStr, eduExprSet, proExprSet, skillSet, workExprSet, referenceSet, fileName, userEntity)) {
            userEntity.getResumeSet().add(resumeEntity);
            em.persist(resumeEntity);
            return "Resume has been created successfully!";
        } else {
            return "There were some issues encountered while creating your resume. Please try again.";
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
    
    @Override
    public boolean deleteReview(long reviewID) {
        boolean reviewDeleteStatus = false;
        
        Query q =em.createQuery("SELECT cr from CompanyReview cr WHERE cr.reviewID=:reviewID");
        q.setParameter("reviewID", reviewID);
        CompanyReviewEntity cre = (CompanyReviewEntity) q.getSingleResult();
        
        if(cre!=null) {
            CompanyEntity ce = lookupCompany(cre.getCompanyEntity().getCompanyID());
            UserEntity ue = cre.getUserEntity();
            ce.getCompanyReviewSet().remove(cre);
            ue.getCompanyReviewSet().remove(cre);
            em.remove(cre);
            em.merge(ce);
            em.merge(ue);
            reviewDeleteStatus = true;
        }
        return reviewDeleteStatus;
    }
    
    @Override
    public boolean deleteResume(long resumeID) {
        boolean reviewDeleteStatus = false;
        
        Query q =em.createQuery("SELECT r from Resume r WHERE r.resumeID=:resumeID");
        q.setParameter("resumeID", resumeID);
        ResumeEntity re = (ResumeEntity) q.getSingleResult();
        
        if(re!=null) {
            UserEntity ue = re.getUserEntity();
            ue.getResumeSet().remove(re);
            em.remove(re);
            em.merge(ue);
            reviewDeleteStatus = true;
        }
        return reviewDeleteStatus;
    }
    
    @Override
    public boolean cancelRequest(long requestID) {
        boolean requestDeleteStatus = false;
        
        Query q =em.createQuery("SELECT cr from CompanyRequest cr WHERE cr.requestID=:requestID");
        q.setParameter("requestID", requestID);
        CompanyRequestEntity cre = (CompanyRequestEntity) q.getSingleResult();
        if(cre!=null) {
            UserEntity ue = cre.getUserEntity();
            ue.getCompanyRequestSet().remove(cre);
            em.remove(cre);
            em.merge(ue);
            requestDeleteStatus = true;
        }
        return requestDeleteStatus;
    }
    
    @Override
    public String likeUnlikeReview(long reviewIDHid, String usernameHid) {
        if (lookupReview(reviewIDHid) == null) { return "0"; }
        else if(lookupUnifyUser(usernameHid) == null) { return "0"; }
        else {
            reviewEntity = lookupReview(reviewIDHid);
            UserEntity userEntity = lookupUnifyUser(usernameHid);
            if (lookupLike(reviewIDHid, usernameHid) == null) {
                LikeListingEntity llEntity = new LikeListingEntity();
                if(llEntity.addNewLike("Voices")) {
                    llEntity.setCompanyReviewEntity(reviewEntity);
                    llEntity.setUserEntity(userEntity);
                    reviewEntity.getLikeListingSet().add(llEntity);
                    userEntity.getLikeListingSet().add(llEntity);
                    
                    /* MESSAGE SENDER IS THE ANONYMOUS, MESSAGE RECEIVER IS THE REVIEW POSTER */
                    MessageEntity mEntity = new MessageEntity();
                    mEntity.createContentMessage(userEntity.getUsername(), reviewEntity.getUserEntity().getUsername(), 
                            "You receive one like on your review of " + reviewEntity.getCompanyEntity().getCompanyName() + ". Check it out!", 
                            reviewEntity.getReviewID(), "Voices");
                    
                    mEntity.setUserEntity(userEntity);
                    (reviewEntity.getUserEntity()).getMessageSet().add(mEntity);
                    
                    em.persist(llEntity);
                    em.persist(mEntity);
                    em.merge(reviewEntity);
                    em.merge(userEntity);
                }
            } else {
                LikeListingEntity llEntity = lookupLike(reviewIDHid, usernameHid);
                reviewEntity.getLikeListingSet().remove(llEntity);
                userEntity.getLikeListingSet().remove(llEntity);
                
                em.remove(llEntity);
                em.merge(userEntity);
                em.flush();
                em.clear();
            }
        }
        return String.valueOf(getReviewLikeCount(reviewIDHid));
    }
    
    public Long getReviewLikeCount(long reviewID) {
        Long reviewLikeCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.likeID) FROM LikeListing l WHERE l.reviewEntity.reviewID = :reviewID");
        q.setParameter("reviewID", reviewID);
        try {
            reviewLikeCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesSysUserMgrBean.getItemLikeCount().getSingleResult()");
            ex.printStackTrace();
        }
        return reviewLikeCount;
    }
    
    public UserEntity lookupUnifyUser(String username) {
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
    
    public CompanyReviewEntity lookupReview(long reviewID) {
        CompanyReviewEntity cre = new CompanyReviewEntity();
        try {
            Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.reviewID = :reviewID");
            q.setParameter("reviewID", reviewID);
            cre = (CompanyReviewEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Company Review cannot be found. " + enfe.getMessage());
            em.remove(cre);
            cre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Company Review does not exist. " + nre.getMessage());
            em.remove(cre);
            cre = null;
        }
        return cre;
    }
    
    public ResumeEntity lookupResume(long resumeID) {
        ResumeEntity re = new ResumeEntity();
        try {
            Query q = em.createQuery("SELECT r FROM Resume r WHERE r.resumeID = :resumeID");
            q.setParameter("resumeID", resumeID);
            re = (ResumeEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Resume cannot be found. " + enfe.getMessage());
            em.remove(re);
            re = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Resume does not exist. " + nre.getMessage());
            em.remove(re);
            re = null;
        }
        return re;
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
    
    @Override
    public Vector lookupReviewReport(String username, long reviewID) {
        Vector report = new Vector();
        try {
            Query q = em.createQuery("SELECT crr FROM CompanyReviewReport crr WHERE crr.reviewID=:reviewID AND crr.userEntity.username=:username");
            q.setParameter("reviewID", String.valueOf(reviewID));
            q.setParameter("username", username);
            if(q.getSingleResult() != null) {
                CompanyReviewReportEntity cre = (CompanyReviewReportEntity) q.getSingleResult();
                report.add(cre.getReviewReportDate());
                report.add(cre.getReviewReportStatus());
            }
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Like cannot be found. " + enfe.getMessage());
            report = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Like does not exist. " + nre.getMessage());
            report = null;
        }
        
        return report;
    }
    
    @Override
    public List<Vector> viewUserCompanyReview(String username) {
        Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.userEntity.username = :username ORDER BY cr.reviewDate DESC");
        q.setParameter("username", username);
        List<Vector> reviewList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            CompanyReviewEntity cre = (CompanyReviewEntity) o;
            Vector reviewVec = new Vector();

            reviewVec.add(cre.getReviewID());
            reviewVec.add(df.format(cre.getReviewDate()));
            reviewVec.add(cre.getUserEntity().getUsername());
            reviewVec.add(cre.getCompanyEntity().getCompanyName());
            reviewVec.add(cre.getReviewTitle());
            reviewVec.add(getReviewLikeCount(cre.getReviewID()));
            reviewVec.add(cre.getReviewStatus());
            reviewList.add(reviewVec);
        }
        return reviewList;
    }
    
    @Override
    public List<Vector> viewUserCompanyRequest(String username) {
        Query q = em.createQuery("SELECT cr FROM CompanyRequest cr WHERE cr.userEntity.username = :username ORDER BY cr.requestDate DESC");
        q.setParameter("username", username);
        List<Vector> requestList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            CompanyRequestEntity cre = (CompanyRequestEntity) o;
            Vector requestVec = new Vector();

            requestVec.add(cre.getRequestID());
            requestVec.add(df.format(cre.getRequestDate()));
            requestVec.add(cre.getUserEntity().getUsername());
            requestVec.add(cre.getRequestCompany());
            requestVec.add(cre.getRequestIndustry());
            requestVec.add(cre.getRequestComment());
            requestVec.add(cre.getRequestStatus());
            requestList.add(requestVec);
        }
        return requestList;
    }
    
    @Override
    public List<Vector> viewUserResume(String username) {
        Query q = em.createQuery("SELECT r FROM Resume r WHERE r.userEntity.username = :username ORDER BY r.creationDate DESC");
        q.setParameter("username", username);
        List<Vector> resumeList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ResumeEntity re = (ResumeEntity) o;
            Vector resumeVec = new Vector();

            resumeVec.add(re.getResumeID());
            resumeVec.add(df.format(re.getCreationDate()));
            resumeVec.add(re.getUserFullName());
            resumeVec.add(re.getUserEntity().getUsername());
            resumeList.add(resumeVec);
        }
        return resumeList;
    }
    
    @Override
    public Vector viewResumeBasicDetails(long resumeID) {
        ResumeEntity re = lookupResume(resumeID);
        Vector basicVec = new Vector();
        
        if(re!=null) {
            basicVec.add(re.getUserImage());
            basicVec.add(re.getUserFullName());
            basicVec.add(re.getContactNum());
            basicVec.add(re.getEmailAddr());
            basicVec.add(re.getPostalAddr());
        }
        return basicVec;
    }
    
    @Override
    public List<Vector> viewEduExprList(long resumeID) {
        ResumeEntity re = lookupResume(resumeID);
        List<Vector> eduExprList = new ArrayList<Vector>();
        
        Collection<EduExprEntity> eduExprSet = re.getEduSet();
        if(eduExprSet!=null) {
            for(EduExprEntity ee: eduExprSet) {
                Vector eduVec = new Vector();
                
                eduVec.add(ee.getSchoolName());
                eduVec.add(ee.getSchoolPeriod());
                eduVec.add(ee.getSchoolDegree());
                eduVec.add(ee.getSchoolMajor());
                eduExprList.add(eduVec);
            }
        }
        return eduExprList;
    }
    
    @Override
    public List<Vector> viewWorkExprList(long resumeID) {
        ResumeEntity re = lookupResume(resumeID);
        List<Vector> workExprList = new ArrayList<Vector>();
        
        Collection<WorkExprEntity> workExprSet = re.getWorkExprSet();
        if(workExprSet!=null) {
            for(WorkExprEntity we: workExprSet) {
                Vector workVec = new Vector();
                
                workVec.add(we.getWorkCompany());
                workVec.add(we.getWorkTitle());
                workVec.add(we.getWorkPeriod());
                workVec.add(we.getWorkDescription());
                workExprList.add(workVec);
            }
        }
        return workExprList;
    }
    
    @Override
    public List<Vector> viewProjectExprList(long resumeID) {
        ResumeEntity re = lookupResume(resumeID);
        List<Vector> proExprList = new ArrayList<Vector>();
        
        Collection<ProjectExprEntity> proExprSet = re.getProjectExprSet();
        if(proExprSet!=null) {
            for(ProjectExprEntity pe: proExprSet) {
                Vector proVec = new Vector();
                
                proVec.add(pe.getProjectTitle());
                proVec.add(pe.getProjectDescription());
                proExprList.add(proVec);
            }
        }
        return proExprList;
    }
}
