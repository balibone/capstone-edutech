/***************************************************************************************
*   Title:                  VoicesAdminMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY COMPANY REVIEW - ADMIN (EDUBOX)
*   Created & Modified By:  ZHU XINYI
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.admin;

import unifyentities.voices.CompanyEntity;
import unifyentities.voices.CompanyReviewEntity;
import unifyentities.common.CategoryEntity;
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
import unifyentities.voices.CompanyRequestEntity;

@Stateless
public class VoicesAdminMgrBean implements VoicesAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    String categoryType = "voices";
    
    private CompanyEntity compEntity;
    private CompanyReviewEntity companyReviewEntity;
    private CompanyRequestEntity companyRequestEntity;
    private CategoryEntity categoryEntity;
    private Collection<CompanyReviewEntity> companyReviewList;
    private Collection<CompanyEntity> companySet;

    @Override
    public boolean createCompany(String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage) {
        compEntity = new CompanyEntity();
        boolean createCompanyStatus = false;
        
        /* companyIndustry = "CATEGORY NAME" */
        if (lookupCompanyCategory(companyIndustry, categoryType) != null) {
            categoryEntity = lookupCompanyCategory(companyIndustry, categoryType);
            compEntity.create(companyName, companyWebsite, companyHQ, companySize, categoryEntity, companyDescription, companyImage);
            
            companyReviewList = new ArrayList<CompanyReviewEntity>();
            compEntity.setCompanyReviewSet(companyReviewList);
            em.persist(compEntity);
            createCompanyStatus = true;
        }
        return createCompanyStatus;
    }

    @Override
    public List<Vector> viewCompanyList() {
        Query q = em.createQuery("SELECT c from Company c");
        List<Vector> companyList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            CompanyEntity ce = (CompanyEntity) o;
            Vector companyVec = new Vector();
            
            companyVec.add(ce.getCompanyName());
            companyVec.add(ce.getCompanyAverageRating());
            companyVec.add(ce.getCompanyStatus());
            companyVec.add(ce.getCategoryEntity().getCategoryName());
            companyVec.add(ce.getCompanyImage());
            companyList.add(companyVec);
        }
        return companyList;
    }
    
    @Override
    public List<Vector> viewCompanyInCategoryList(String categoryName, String categoryType) {
        categoryEntity = lookupCategory(categoryName, categoryType);
        List<Vector> companyList = new ArrayList<Vector>();
        if(categoryEntity != null) {
            Query q = em.createQuery("SELECT c from Company c WHERE c.categoryEntity = :companyIndustry");
            q.setParameter("companyIndustry", categoryEntity);

            for (Object o : q.getResultList()) {
                CompanyEntity ce = (CompanyEntity) o;
                Vector companyVec = new Vector();
            
                companyVec.add(ce.getCompanyName());
                companyVec.add(ce.getCompanyAverageRating());
                companyVec.add(ce.getCompanyStatus());
                companyVec.add(ce.getCategoryEntity().getCategoryName());
                companyVec.add(ce.getCompanyImage());
                companyList.add(companyVec);
            }
        }
        return companyList;
    }

    @Override
    public Vector viewCompanyDetails(String companyName) {
        compEntity = lookupCompany(companyName);
        Vector companyDetailsVec = new Vector();

        if (compEntity != null) {
            companyDetailsVec.add(compEntity.getCompanyName());
            companyDetailsVec.add(compEntity.getCompanyWebsite());
            companyDetailsVec.add(compEntity.getCompanyHQ());
            companyDetailsVec.add(compEntity.getCompanyAverageRating());
            companyDetailsVec.add(compEntity.getCompanySize());
            companyDetailsVec.add(compEntity.getCompanyStatus());
            companyDetailsVec.add(compEntity.getCategoryEntity().getCategoryName());
            companyDetailsVec.add(compEntity.getCompanyDescription());
            companyDetailsVec.add(compEntity.getCompanyImage());
            return companyDetailsVec;
        }
        return null;
    }

    @Override
    public String deactivateCompany(String companyName) {
        if (lookupCompany(companyName) == null) {
            return "Selected company cannot be found.";
        } else {
            compEntity = lookupCompany(companyName);
            companyReviewList = compEntity.getCompanyReviewSet();
            compEntity.setCompanyStatus("Inactive");
            em.merge(compEntity);
            return "Selected company category has been deactivated successfully!";
        }
    }

    @Override
    public boolean activateCompany(String companyName) {
        boolean companyActivateStatus = true;
        if (lookupCompany(companyName) == null) {
            companyActivateStatus = false;
        } else {
            compEntity = lookupCompany(companyName);
            compEntity.setCompanyStatus("Active");
            em.merge(compEntity);
        }
        return companyActivateStatus;
    }

    @Override
    public boolean updateCompany(String oldCompanyName, String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage) {
        boolean companyUpdateStatus = true;
        if (lookupCompany(oldCompanyName) == null) {
            companyUpdateStatus = false;
        } else if (lookupCompanyCategory(companyIndustry, categoryType) == null) {
            companyUpdateStatus = false;
        } else {
            categoryEntity = lookupCompanyCategory(companyIndustry, categoryType);
            compEntity = lookupCompany(oldCompanyName);
            compEntity.setCompanyName(companyName);
            compEntity.setCompanyWebsite(companyWebsite);
            compEntity.setCompanyHQ(companyHQ);
            compEntity.setCompanySize(companySize);
            compEntity.setCategoryEntity(categoryEntity);
            compEntity.setCompanyDescription(companyDescription);
            compEntity.setCompanyImage(companyImage);
            em.merge(compEntity);
        }
        return companyUpdateStatus;
    }

    @Override
    public List<Vector> viewReviewList(String reviewedCompany) {
        compEntity = lookupCompany(reviewedCompany);
        Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.companyEntity = :reviewedCompany");
        q.setParameter("reviewedCompany", compEntity);
        
        List<Vector> reviewList = new ArrayList<Vector>();
        for (Object o : q.getResultList()) {
            CompanyReviewEntity cr = (CompanyReviewEntity) o;
            Vector cri = new Vector();
            cri.add(cr.getReviewTitle());
            cri.add(cr.getCompanyEntity().getCompanyName());
            cri.add(cr.getReviewPosterID());
            cri.add(cr.getReviewDate());
            reviewList.add(cri);
        }
        return reviewList;
    }

    @Override
    public Vector viewReviewDetails(String reviewTitle, String reviewedCompany) {
        companyReviewEntity = lookupReview(reviewTitle, reviewedCompany);
        Vector reviewDetailsVec = new Vector();

        if (companyReviewEntity != null) {
            reviewDetailsVec.add(companyReviewEntity.getReviewID());
            reviewDetailsVec.add(companyReviewEntity.getReviewTitle());
            reviewDetailsVec.add(companyReviewEntity.getReviewRating());
            reviewDetailsVec.add(companyReviewEntity.getReviewPros());
            reviewDetailsVec.add(companyReviewEntity.getReviewCons());
            reviewDetailsVec.add(companyReviewEntity.getReviewEmpType());
            reviewDetailsVec.add(companyReviewEntity.getReviewThumbsUp());
            reviewDetailsVec.add(companyReviewEntity.getReviewSalaryRange());
            reviewDetailsVec.add(companyReviewEntity.getCompanyEntity().getCompanyName());
            reviewDetailsVec.add(companyReviewEntity.getReviewDate());
            reviewDetailsVec.add(companyReviewEntity.getReviewPosterID());
            return reviewDetailsVec;
        }
        return null;
    }

    public CompanyReviewEntity lookupReview(String reviewedCompany, String reviewPosterID) {
        CompanyReviewEntity cre = new CompanyReviewEntity();
        try {
            compEntity = lookupCompany(reviewedCompany);
            Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.companyEntity = :reviewedCompany AND cr.reviewPosterID = :reviewPosterID");
            q.setParameter("reviewedCompany", compEntity);
            q.setParameter("reviewPosterID", reviewPosterID);
            cre = (CompanyReviewEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Company review cannot be found. " + enfe.getMessage());
            em.remove(cre);
            cre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Company review does not exist. " + nre.getMessage());
            em.remove(cre);
            cre = null;
        }
        return cre;
    }

    @Override
    public boolean deleteReview(String reviewedCompany, String reviewPosterID) {
        boolean reviewDeleteStatus = false;
        compEntity = lookupCompany(reviewedCompany);
        companyReviewList = compEntity.getCompanyReviewSet();
        
        for (int i = 0; i < companyReviewList.size(); i++) {
            companyReviewEntity = (CompanyReviewEntity)companyReviewList.toArray()[i];
            if (companyReviewEntity.getReviewPosterID().equals(reviewPosterID)) {
                compEntity.getCompanyReviewSet().remove(companyReviewEntity);
                em.remove(companyReviewEntity);
                em.merge(compEntity);
                reviewDeleteStatus = true;
            }
        }
        return reviewDeleteStatus;
    }

    /* MISCELLANEOUS METHODS */
    public CompanyEntity lookupCompany(String companyName) {
        CompanyEntity ce = new CompanyEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Company c WHERE c.companyName = :companyName");
            q.setParameter("companyName", companyName);
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
    
    public CategoryEntity lookupCompanyCategory(String companyIndustry, String categoryType) {
        /* companyIndustry = CATEGORY NAME */
        CategoryEntity ce = new CategoryEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryName");
            q.setParameter("categoryName", companyIndustry);
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
    
    @Override
    public List<Vector> viewCompanyCategoryList() {
        Query q = em.createQuery("SELECT c from Category c WHERE c.categoryType = 'company' ");
        List<Vector> categoryList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            CategoryEntity ce = (CategoryEntity) o;
            Vector categoryVec = new Vector();
            
            categoryVec.add(ce.getCategoryName());
            categoryVec.add(ce.getCategoryID());
            categoryVec.add(ce.getCategoryDescription());
            categoryVec.add(ce.getCategoryActiveStatus());
            categoryVec.add(ce.getCategoryImage());
            categoryList.add(categoryVec);
        }
        return categoryList;
    }
    
    @Override
    public boolean createCompanyCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage) {
        categoryEntity = new CategoryEntity();
        categoryEntity.createCategory(categoryName, categoryType, categoryDescription, categoryImage);
        em.persist(categoryEntity);
        return true;
    }
    
    @Override
    public Vector viewCompanyCategoryDetails(String categoryName, String categoryType) {
        categoryEntity = lookupCategory(categoryName, categoryType);
        Vector companyCategoryDetailsVec = new Vector();
        
        if (categoryEntity != null) {
            companyCategoryDetailsVec.add(categoryEntity.getCategoryName());
            companyCategoryDetailsVec.add(categoryEntity.getCategoryType());
            companyCategoryDetailsVec.add(categoryEntity.getCategoryDescription());
            companyCategoryDetailsVec.add(categoryEntity.getCategoryActiveStatus());
            companyCategoryDetailsVec.add(categoryEntity.getCategoryImage());
            return companyCategoryDetailsVec;
        }
        return null;
    }
    
    public CategoryEntity lookupCategory(String categoryName, String categoryType) {
        CategoryEntity ce = new CategoryEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryName AND c.categoryType = :categoryType");
            q.setParameter("categoryName", categoryName);
            q.setParameter("categoryType", categoryType);
            ce = (CategoryEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Category cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Category does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    @Override
    public String deactivateCompanyCategory(String deactCategoryName, String deactCategoryType) {
        /* DON'T CHANGE THE RETURN STRING (USED FOR SERVLET VALIDATION) */
        boolean companyAvailWithinCat = false;
        
        if (lookupCategory(deactCategoryName, deactCategoryType) == null) {
            return "Selected company category cannot be found.";
        } else {
            categoryEntity = em.find(CategoryEntity.class, lookupCategory(deactCategoryName, deactCategoryType).getCategoryID());
            companySet = categoryEntity.getCompanySet();
            
            /* IF THERE IS ITEM INSIDE THE ITEM CATEGORY */
            if (!companySet.isEmpty()) {
                for (CompanyEntity ie : companySet) {
                    /* IF THE ITEM INSIDE THE ITEM CATEGORY IS "AVAILABLE", THEN CANNOT DEACTIVATE THE ITEM CATEGORY */
                    if((ie.getCompanyStatus()).equals("Active")) {
                        companyAvailWithinCat = true;
                        break;
                    }
                }
                if (companyAvailWithinCat == true) {
                    return "There are currently active companies inside this company category. Cannot be deactivated.";
                } else {
                    return "Selected company category has been deactivated successfully!";
                }
            }
            /* IF THERE IS NO ITEMS INSIDE THE ITEM CATEGORY, PROCEED TO DEACTIVATE THE ITEM CATEGORY */
            else{
                categoryEntity.setCategoryActiveStatus(false);
                em.merge(categoryEntity);
                return "Selected company category has been deactivated successfully!";
            }
        }
    }
    
    @Override
    public boolean activateCompanyCategory(String actCategoryName, String actCategoryType) {
        boolean ccDeactivateStatus = true;
        if (lookupCategory(actCategoryName, actCategoryType) == null) {
            ccDeactivateStatus = false;
        } else {
            categoryEntity = lookupCategory(actCategoryName, actCategoryType);
            categoryEntity.setCategoryActiveStatus(true);
            em.merge(categoryEntity);
        }
        return ccDeactivateStatus;
    }
    
    @Override
    public boolean updateCompanyCategory(String oldCategoryName, String newCategoryName, String categoryType, 
            String oldCategoryDescription, String newCategoryDescription, String fileName) {
        /* DOES NOT MATTER WHETHER OR NOT THERE IS ITEMS INSIDE THE ITEM CATEGORY, ADMIN CAN JUST UPDATE THE ITEM CATEGORY DETAILS */
        boolean ccUpdateStatus = true;
        if (lookupCategory(oldCategoryName, categoryType) == null) {
            ccUpdateStatus = false;
        } else {
            categoryEntity = lookupCategory(oldCategoryName, categoryType);
            categoryEntity.setCategoryName(newCategoryName);
            categoryEntity.setCategoryDescription(newCategoryDescription);
            categoryEntity.setCategoryImage(fileName);
            em.merge(categoryEntity);
        }
        return ccUpdateStatus;
    }
    
    @Override
    public List<Vector> viewCompanyRequestList() {
        Query q = em.createQuery("SELECT cr from CompanyRequest cr");
        List<Vector> requestList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            CompanyRequestEntity ce = (CompanyRequestEntity) o;
            Vector requestVec = new Vector();
            
            requestVec.add(ce.getRequestDate());
            requestVec.add(ce.getRequestCompany());
            requestVec.add(ce.getRequestPosterID());
            requestVec.add(ce.getRequestStatus());
            requestVec.add(ce.getRequestComment());
            requestVec.add(ce.getRequestIndustry());
            requestList.add(requestVec);
        }
        return requestList;
    }
    
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
    
    @Override
    public boolean solveRequest(String requestCompany, String requestPosterID) {
        boolean requestStatus = false;
        companyRequestEntity = lookupRequest(requestCompany, requestPosterID);
        if(companyRequestEntity != null) {
            companyRequestEntity.setRequestStatus("Solved");
            em.merge(companyRequestEntity);
            requestStatus = true;
        }
        return requestStatus;
    }
    
    @Override
    public boolean rejectRequest(String requestCompany, String requestPosterID) {
        boolean requestStatus = false;
        companyRequestEntity = lookupRequest(requestCompany, requestPosterID);
        if(companyRequestEntity != null) {
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
            companyReviewCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getCompanyReviewCount().getSingleResult()");
            ex.printStackTrace();
        }
        return companyReviewCount;
    }
    
    /* MISCELLANEOUS METHODS */
    public CompanyRequestEntity lookupRequest(String requestCompany, String requestPosterID) {
        CompanyRequestEntity cre = new CompanyRequestEntity();
        try {
            Query q = em.createQuery("SELECT cr from CompanyRequest cr WHERE cr.requestCompany=:requestCompany AND cr.requestPosterID=:requestPosterID");
            q.setParameter("requestCompany", requestCompany);
            q.setParameter("requestPosterID", requestPosterID);
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
}