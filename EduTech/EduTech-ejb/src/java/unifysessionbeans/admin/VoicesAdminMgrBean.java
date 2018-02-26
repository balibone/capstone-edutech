/**
 * *************************************************************************************
 *   Title:                  VoicesAdminMgrBean.java
 *   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY COMPANY REVIEW - ADMIN (EDUBOX)
 *   Created By:             ZHU XINYI
 *   Modified By:            TAN CHIN WEE WINSTON
 *   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
 *   Date:                   19 FEBRUARY 2018
 *   Code version:           1.1
 *   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
 **************************************************************************************
 */
package unifysessionbeans.admin;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

import unifyentities.common.CategoryEntity;
import unifyentities.voices.CompanyEntity;
import unifyentities.voices.CompanyReviewEntity;
import unifyentities.voices.CompanyRequestEntity;
import commoninfrastructureentities.UserEntity;
import java.text.SimpleDateFormat;

@Stateless
public class VoicesAdminMgrBean implements VoicesAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;

    private CategoryEntity cEntity;
    private CompanyEntity compEntity;
    private CompanyReviewEntity companyReviewEntity;
    private UserEntity uEntity;
    private CompanyRequestEntity companyRequestEntity;
    private Collection<CompanyReviewEntity> companyReviewSet;
    private Collection<CompanyEntity> companySet;
    
    @Override
    public List<Vector> viewCompanyCategoryList() {
        Query q = em.createQuery("SELECT c from Category c WHERE c.categoryType = 'Voices' ");
        List<Vector> categoryList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            CategoryEntity ce = (CategoryEntity) o;
            Vector categoryVec = new Vector();

            categoryVec.add(ce.getCategoryID());
            categoryVec.add(ce.getCategoryImage());
            categoryVec.add(ce.getCategoryName());
            categoryVec.add(ce.getCategoryDescription());
            categoryVec.add(ce.getCategoryActiveStatus());
            categoryList.add(categoryVec);
        }
        return categoryList;
    }

    @Override
    public String populateCompanyIndustry() {
        String companyIndustryStr = "";
        Query q = em.createQuery("SELECT c from Category c WHERE c.categoryType = 'Voices' ");

        for (Object o : q.getResultList()) {
            CategoryEntity ce = (CategoryEntity) o;
            companyIndustryStr += ce.getCategoryName() + ";";
        }
        companyIndustryStr = companyIndustryStr.substring(0, companyIndustryStr.length()-1);
        return companyIndustryStr;
    }
    
    @Override
    public String createCompanyCategory(String categoryName, String categoryType, String categoryDescription, String fileName) {
        cEntity = new CategoryEntity();
        if (cEntity.createCategory(categoryName, categoryType, categoryDescription, fileName)) {
            em.persist(cEntity);
            return "Company category has been created successfully!";
        } else {
            return "There were some issues encountered while creating the company category. Please try again.";
        }
    }

    @Override
    public Vector viewCompanyCategoryDetails(long companyCategoryID) {
        cEntity = lookupCompanyCategory(companyCategoryID);
        Vector companyCategoryDetailsVec = new Vector();

        if (cEntity != null) {
            companyCategoryDetailsVec.add(cEntity.getCategoryImage());
            companyCategoryDetailsVec.add(cEntity.getCategoryName());
            companyCategoryDetailsVec.add(cEntity.getCategoryType());
            companyCategoryDetailsVec.add(cEntity.getCategoryDescription());
            companyCategoryDetailsVec.add(cEntity.getCategoryActiveStatus());
        }
        return companyCategoryDetailsVec;
    }

    @Override
    public String deactivateACompanyCategory(long companyCategoryID) {
        /* DON'T CHANGE THE RETURN STRING (USED FOR SERVLET VALIDATION) */
        boolean companyAvailWithinCat = false;

        if (lookupCompanyCategory(companyCategoryID) == null) {
            return "Selected company category cannot be found.";
        } else {
            cEntity = em.find(CategoryEntity.class, lookupCompanyCategory(companyCategoryID).getCategoryID());
            companySet = cEntity.getCompanySet();

            /* IF THERE ARE COMPANIES INSIDE THE COMPANY CATEGORY */
            if (!companySet.isEmpty()) {
                for (CompanyEntity ce : companySet) {
                    /* IF THE COMPANY INSIDE THE COMPANY CATEGORY IS "AVAILABLE", THEN CANNOT DEACTIVATE THE COMPANY CATEGORY */
                    if ((ce.getCompanyStatus()).equals("Active")) {
                        companyAvailWithinCat = true;
                        break;
                    }
                }
                if (companyAvailWithinCat == true) {
                    return "There are active companies currently inside this company category. Cannot be deactivated.";
                } else {
                    return "Selected company category has been deactivated successfully!";
                }
            } /* IF THERE ARE NO COMPANIES INSIDE THE COMPANY CATEGORY, PROCEED TO DEACTIVATE THE COMPANY CATEGORY */ else {
                cEntity.setCategoryActiveStatus(false);
                em.merge(cEntity);
                return "Selected company category has been deactivated successfully!";
            }
        }
    }

    @Override
    public String activateACompanyCategory(long companyCategoryID) {
        if (lookupCompanyCategory(companyCategoryID) == null) {
            return "Selected company category cannot be found. Please try again.";
        } else {
            cEntity = lookupCompanyCategory(companyCategoryID);
            cEntity.setCategoryActiveStatus(true);
            em.merge(cEntity);
            return "Selected company category has been activated successfully!";
        }
    }

    @Override
    public String updateCompanyCategory(long companyCategoryID, String categoryName,
            String categoryDescription, String fileName) {
        /* DOES NOT MATTER WHETHER OR NOT THERE IS COMPANY INSIDE THE COMPANY CATEGORY, ADMIN CAN JUST UPDATE THE COMPANY CATEGORY DETAILS */
        if (lookupCompanyCategory(companyCategoryID) == null) {
            return "Selected company category cannot be found. Please try again.";
        } else {
            cEntity = lookupCompanyCategory(companyCategoryID);
            cEntity.setCategoryName(categoryName);
            cEntity.setCategoryDescription(categoryDescription);
            cEntity.setCategoryImage(fileName);
            em.merge(cEntity);
            return "Selected company category has been updated successfully!";
        }
    }

    @Override
    public List<Vector> viewAssociatedCompanyList(long companyCategoryID) {
        cEntity = lookupCompanyCategory(companyCategoryID);
        List<Vector> companyList = new ArrayList<Vector>();
        
        Query q = em.createQuery("SELECT c from Company c WHERE c.categoryEntity.categoryName = :companyCategoryName");
                q.setParameter("companyCategoryName", cEntity.getCategoryName());
                
                for (Object o : q.getResultList()) {
                    CompanyEntity ce = (CompanyEntity) o;
                    Vector companyVec = new Vector();

                    companyVec.add(ce.getCompanyID());
                    companyVec.add(ce.getCategoryEntity().getCategoryID());
                    companyVec.add(ce.getCompanyImage());
                    companyVec.add(ce.getCompanyName());
                    companyVec.add(ce.getCompanyHQ());
                    companyVec.add(ce.getCompanySize());
                    companyVec.add(ce.getCompanyAverageRating());
                    companyVec.add(ce.getCompanyStatus());
                    companyList.add(companyVec);
                } 

    /*    if (cEntity.getCompanySet() != null) {
            companySet = cEntity.getCompanySet();
            if (!companySet.isEmpty()) {
                for (CompanyEntity ce : companySet) {
                    Vector companyDetails = new Vector();
                    
                    companyDetails.add(ce.getCompanyID());
                    companyDetails.add(ce.getCategoryEntity().getCategoryID());
                    companyDetails.add(ce.getCompanyImage());
                    companyDetails.add(ce.getCompanyName());
                    companyDetails.add(ce.getCompanyHQ());
                    companyDetails.add(ce.getCompanySize());
                    companyDetails.add(ce.getCompanyAverageRating());
                    companyDetails.add(ce.getCompanyStatus());
                    companyList.add(companyDetails);
                }
            } else { 
                /* companyCategoryName = companyIndustry */
                /*Query q = em.createQuery("SELECT c from Company c WHERE c.categoryEntity.categoryName = :companyCategoryName");
                q.setParameter("companyCategoryName", cEntity.getCategoryName());
                
                for (Object o : q.getResultList()) {
                    CompanyEntity ce = (CompanyEntity) o;
                    Vector companyVec = new Vector();

                    companyVec.add(ce.getCompanyID());
                    companyVec.add(ce.getCategoryEntity().getCategoryID());
                    companyVec.add(ce.getCompanyImage());
                    companyVec.add(ce.getCompanyName());
                    companyVec.add(ce.getCompanyHQ());
                    companyVec.add(ce.getCompanySize());
                    companyVec.add(ce.getCompanyAverageRating());
                    companyVec.add(ce.getCompanyStatus());
                    companyList.add(companyVec);
                } 
            }
        } */
        return companyList;
    }
    
    @Override
    public List<Vector> viewCompanyList() {
        Query q = em.createQuery("SELECT c from Company c");
        List<Vector> companyList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            CompanyEntity ce = (CompanyEntity) o;
            Vector companyVec = new Vector();

            companyVec.add(ce.getCompanyID());
            companyVec.add(ce.getCompanyImage());
            companyVec.add(ce.getCompanyName());
            companyVec.add(ce.getCompanyHQ());
            companyVec.add(ce.getCompanySize());
            companyVec.add(ce.getCompanyAverageRating());
            companyVec.add(ce.getCompanyStatus());
            companyList.add(companyVec);
        }
        return companyList;
    }
    
    @Override
    public String createCompany(String companyIndustry, String companyName, int companySize, String companyWebsite, 
            String companyHQ, String companyDescription, String companyAddress, String fileName) {
        compEntity = new CompanyEntity();
        cEntity = lookupCompanyCategory(companyIndustry);
        if (compEntity.createCompany(companyName, companySize, companyWebsite, companyHQ, companyDescription, 
                companyAddress, fileName)) {
            compEntity.setCategoryEntity(cEntity);
            em.persist(compEntity);
            return "Company has been created successfully!";
        } else {
            return "There were some issues encountered while creating the company. Please try again.";
        }
    }
    
    @Override
    public Vector viewCompanyDetails(long companyID) {
        compEntity = lookupCompany(companyID);
        Vector companyDetailsVec = new Vector();

        if (compEntity != null) {
            companyDetailsVec.add(compEntity.getCompanyName());
            companyDetailsVec.add(compEntity.getCompanyImage());
            companyDetailsVec.add(compEntity.getCategoryEntity().getCategoryName());
            companyDetailsVec.add(compEntity.getCompanyAverageRating());
            companyDetailsVec.add(compEntity.getCompanyStatus());
            companyDetailsVec.add(compEntity.getCompanyWebsite());
            companyDetailsVec.add(compEntity.getCompanyHQ());
            companyDetailsVec.add(compEntity.getCompanySize());
            companyDetailsVec.add(compEntity.getCompanyDescription());
            companyDetailsVec.add(compEntity.getCompanyAddress());
        }
        return companyDetailsVec;
    }

    @Override
    public List<Vector> viewAssociatedReviewList(long companyID) {
        compEntity = lookupCompany(companyID);
        List<Vector> companyReviewList = new ArrayList<Vector>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        if (compEntity.getCompanyReviewSet()!= null) {
            companyReviewSet = compEntity.getCompanyReviewSet();
            if (!companyReviewSet.isEmpty()) {
                for (CompanyReviewEntity cre : companyReviewSet) {
                    Vector companyReviewDetails = new Vector();
                    
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
                    companyReviewList.add(companyReviewDetails);
                }
            } else {
                Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.companyEntity.companyID = :companyID");
                q.setParameter("companyID", compEntity.getCompanyID());

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
                    companyReviewList.add(companyReviewVec);
                }
            }
        }
        return companyReviewList;
    }
    
    @Override
    public String updateCompany(long companyID, String companyName, String companyIndustry, String companyWebsite, 
            String companyHQ, int companySize, String companyDescription, String companyAddress, String fileName) {
        /* DOES NOT MATTER WHETHER THE COMPANY IS LISTED OR NOT, ADMIN CAN JUST UPDATE THE COMPANY DETAILS */
        cEntity = lookupCompanyCategory(companyIndustry);
        if (lookupCompany(companyID) == null) {
            return "Selected company cannot be found. Please try again.";
        } else {
            compEntity = lookupCompany(companyID);
            compEntity.setCompanyName(companyName);
            compEntity.setCategoryEntity(cEntity);
            compEntity.setCompanyWebsite(companyWebsite);
            compEntity.setCompanyHQ(companyHQ);
            compEntity.setCompanySize(companySize);
            compEntity.setCompanyDescription(companyDescription);
            compEntity.setCompanyAddress(companyAddress);
            compEntity.setCompanyImage(fileName);
            em.merge(compEntity);
            return "Selected company has been updated successfully!";
        }
    }
    
    @Override
    public String deactivateACompany(long companyID) {
        if (lookupCompany(companyID) == null) {
            return "Selected company cannot be found. Please try again.";
        } else {
            compEntity = lookupCompany(companyID);
            compEntity.setCompanyStatus("Inactive");
            em.merge(compEntity);
            return "Selected company has been deactivated successfully!";
        }
    }

    @Override
    public String activateACompany(long companyID) {
        if (lookupCompany(companyID) == null) {
            return "Selected company cannot be found. Please try again.";
        } else {
            compEntity = lookupCompany(companyID);
            compEntity.setCompanyStatus("Active");
            em.merge(compEntity);
            return "Selected company has been activated successfully!";
        }
    }

    /* HAVEN'T DO YET */
    @Override
    public boolean deleteReview(long reviewedCompanyID, long reviewID) {
        boolean reviewDeleteStatus = false;
        compEntity = lookupCompany(reviewedCompanyID);
        companyReviewSet = compEntity.getCompanyReviewSet();
        
        Query q =em.createQuery("SELECT cr from CompanyReview cr WHERE cr.companyEntity=:companyEntity AND cr.reviewID=:reviewID");
        q.setParameter("companyEntity", compEntity);
        q.setParameter("reviewID", reviewID);
        companyReviewEntity = (CompanyReviewEntity) q.getSingleResult();
        if(companyReviewEntity!=null) {
            compEntity.getCompanyReviewSet().remove(companyReviewEntity);
            em.remove(companyReviewEntity);
            em.merge(compEntity);
            reviewDeleteStatus = true;
        }
        return reviewDeleteStatus;
    }

    /* HAVEN'T DO YET */
    @Override
    public List<Vector> viewCompanyRequestList() {
        Query q = em.createQuery("SELECT cr from CompanyRequest cr");
        List<Vector> requestList = new ArrayList<Vector>();
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (Object o : q.getResultList()) {
            CompanyRequestEntity ce = (CompanyRequestEntity) o;
            Vector requestVec = new Vector();

            requestVec.add(df.format(ce.getRequestDate()));
            requestVec.add(ce.getUserEntity().getUsername());
            requestVec.add(ce.getRequestCompany());
            requestVec.add(ce.getRequestIndustry());
            requestVec.add(ce.getRequestComment());
            requestVec.add(ce.getRequestStatus());
            requestList.add(requestVec);
        }
        return requestList;
    }

    /* HAVEN'T DO YET */
    @Override
    public Vector viewCompanyRequestDetails(String requestCompany, String requestPosterID) {
        companyRequestEntity = lookupRequest(requestCompany, requestPosterID);
        Vector requestDetailsVec = new Vector();
        if (companyRequestEntity != null) {
            requestDetailsVec.add(companyRequestEntity.getRequestID());
            requestDetailsVec.add(companyRequestEntity.getRequestDate());
            requestDetailsVec.add(companyRequestEntity.getRequestPosterID());
            requestDetailsVec.add(companyRequestEntity.getRequestCompany());
            requestDetailsVec.add(companyRequestEntity.getRequestIndustry());
            requestDetailsVec.add(companyRequestEntity.getRequestComment());
            requestDetailsVec.add(companyRequestEntity.getRequestStatus());
        }
        return requestDetailsVec;
    }

    /* HAVEN'T DO YET */
    @Override
    public boolean solveRequest(String requestCompany, String requestPoster) {
        boolean requestStatus = false;
        companyRequestEntity = lookupRequest(requestCompany, requestPoster);
        if (companyRequestEntity != null) {
            companyRequestEntity.setRequestStatus("Solved");
            em.merge(companyRequestEntity);
            requestStatus = true;
        }
        return requestStatus;
    }

    /* HAVEN'T DO YET */
    @Override
    public boolean rejectRequest(String requestCompany, String requestPoster) {
        boolean requestStatus = false;
        companyRequestEntity = lookupRequest(requestCompany, requestPoster);
        if (companyRequestEntity != null) {
            companyRequestEntity.setRequestStatus("Rejected");
            em.merge(companyRequestEntity);
            requestStatus = true;
        }
        return requestStatus;
    }

    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    @Override
    public Long getCompanyReviewCount() {
        Long companyReviewCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(c.reviewID) FROM CompanyReview c");
        try {
            companyReviewCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getCompanyReviewCount().getSingleResult()");
            ex.printStackTrace();
        }
        return companyReviewCount;
    }

    @Override
    public Long getActiveCompanyCategoryListCount() {
        Long activeCompanyCategoryListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.categoryName) FROM Category c WHERE c.categoryType = 'Voices' AND c.categoryActiveStatus='1'");
        try {
            activeCompanyCategoryListCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getActiveCompanyCategoryListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return activeCompanyCategoryListCount;
    }

    @Override
    public Long getInactiveCompanyCategoryListCount() {
        Long inactiveCompanyCategoryListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.categoryName) FROM Category c WHERE c.categoryType = 'Voices' AND c.categoryActiveStatus='0'");
        try {
            inactiveCompanyCategoryListCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getInactiveCompanyCategoryListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return inactiveCompanyCategoryListCount;
    }

    @Override
    public Long getCompanyListingCount() {
        Long companyListingCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(c.companyID) FROM Company c");
        try {
            companyListingCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getCompanyListingCount().getSingleResult()");
            ex.printStackTrace();
        }
        return companyListingCount;
    }
    
    @Override
    public Long getActiveCompanyListingCount() {
        Long activeCompanyListingCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(c.companyID) FROM Company c WHERE c.companyStatus='Active'");
        try {
            activeCompanyListingCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getActiveCompanyListingCount().getSingleResult()");
            ex.printStackTrace();
        }
        return activeCompanyListingCount;
    }
    
    @Override
    public Long getInactiveCompanyListingCount() {
        Long inactiveCompanyListingCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(c.companyID) FROM Company c WHERE c.companyStatus='Inactive'");
        try {
            inactiveCompanyListingCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getInactiveCompanyListingCount().getSingleResult()");
            ex.printStackTrace();
        }
        return inactiveCompanyListingCount;
    }
    
    @Override
    public Long getSolvedCompanyRequestListCount() {
        Long solvedCompanyRequestListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT cr.requestID) FROM CompanyRequest cr WHERE cr.requestStatus = 'Solved'");
        try {
            solvedCompanyRequestListCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getSolvedCompanyCategoryListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return solvedCompanyRequestListCount;
    }
    
    @Override
    public Long getPendingCompanyRequestListCount() {
        Long pendingCompanyRequestListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT cr.requestID) FROM CompanyRequest cr WHERE cr.requestStatus = 'Pending'");
        try {
            pendingCompanyRequestListCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getPendingCompanyRequestListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return pendingCompanyRequestListCount;
    }
    
    @Override
    public Long getRejectedCompanyRequestListCount() {
        Long rejectedCompanyRequestListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT cr.requestID) FROM CompanyRequest cr WHERE cr.requestStatus = 'Rejected'");
        try {
            rejectedCompanyRequestListCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in VoicesAdminMgrBean.getRejectedCompanyRequestListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return rejectedCompanyRequestListCount;
    }

    /* METHODS FOR UNIFY USER PROFILE */
    @Override
    public List<Vector> viewUserCompanyReviewsList(String username) {
        uEntity = lookupSystemUser(username);
        List<Vector> userCompanyReviewsList = new ArrayList<Vector>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Query q = em.createQuery("SELECT r FROM CompanyReview r WHERE r.userEntity.username = :username OR r.reviewReceiverID = :username");
        q.setParameter("username", uEntity.getUsername());

        for (Object o : q.getResultList()) {
            CompanyReviewEntity companyReviewE = (CompanyReviewEntity) o;
            Vector companyReviewVec = new Vector();

            companyReviewVec.add(companyReviewE.getCompanyEntity().getCompanyID());
            companyReviewVec.add(df.format(companyReviewE.getReviewDate()));
            /* WE ASSUME THAT THE PERSON WHO POST THE REVIEW IS THE ONE WHO CREATES THE RECORD */
            companyReviewVec.add(companyReviewE.getUserEntity().getUsername());
            companyReviewVec.add(companyReviewE.getReviewTitle());
            companyReviewVec.add(companyReviewE.getReviewPros());
            companyReviewVec.add(companyReviewE.getReviewCons());
            companyReviewVec.add(companyReviewE.getReviewEmpType());
            companyReviewVec.add(companyReviewE.getReviewSalaryRange());
            companyReviewVec.add(companyReviewE.getReviewRating());
            companyReviewVec.add(companyReviewE.getReviewThumbsUp());
            userCompanyReviewsList.add(companyReviewVec);
        }
        return userCompanyReviewsList;
    }
    
    /* MISCELLANEOUS METHODS */
    public CompanyEntity lookupCompany(long companyID) {
        CompanyEntity ce = new CompanyEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Company c WHERE c.companyID = :companyID");
            q.setParameter("companyID", companyID);
            ce = (CompanyEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    public CategoryEntity lookupCompanyCategory(long companyCategoryID) {
        CategoryEntity ce = new CategoryEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID = :companyCategoryID");
            q.setParameter("companyCategoryID", companyCategoryID);
            ce = (CategoryEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Category cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Category does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    public CategoryEntity lookupCompanyCategory(String companyIndustry) {
        CategoryEntity ce = new CategoryEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :companyIndustry AND c.categoryType='Voices' ");
            q.setParameter("companyIndustry", companyIndustry);
            ce = (CategoryEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Category cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Category does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }

    public CompanyRequestEntity lookupRequest(String requestCompany, String requestPoster) {
        CompanyRequestEntity cre = new CompanyRequestEntity();
        try {
            Query q = em.createQuery("SELECT cr from CompanyRequest cr WHERE cr.requestCompany=:requestCompany AND cr.userEntity.username=:requestPoster");
            q.setParameter("requestCompany", requestCompany);
            q.setParameter("requestPoster", requestPoster);
            cre = (CompanyRequestEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Company request cannot be found. " + enfe.getMessage());
            em.remove(cre);
            cre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Company request does not exist. " + nre.getMessage());
            em.remove(cre);
            cre = null;
        }
        return cre;
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
}