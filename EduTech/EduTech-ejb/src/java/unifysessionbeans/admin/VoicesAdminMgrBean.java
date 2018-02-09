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

@Stateless
public class VoicesAdminMgrBean implements VoicesAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    String categoryType = "voices";
    
    private CompanyEntity compEntity;
    private CompanyReviewEntity companyReviewEntity;
    private CategoryEntity categoryEntity;
    private Collection<CompanyReviewEntity> companyReviewList;

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
    public boolean deactivateCompany(String companyName) {
        boolean companyDeactivateStatus = true;
        if (lookupCompany(companyName) == null) {
            companyDeactivateStatus = false;
        } else {
            compEntity = lookupCompany(companyName);
            compEntity.setCompanyStatus("Inactive");
            em.merge(compEntity);
        }
        return companyDeactivateStatus;
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
        Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.reviewedCompany = :reviewedCompany");
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
            Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.reviewedCompany = :reviewedCompany AND cr.reviewPosterID = :reviewPosterID");
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
        /*
        for (int i = 0; i < companyReviewList.size(); i++) {
            companyReviewEntity = companyReviewList.get(i);
            if (companyReviewEntity.getReviewPosterID().equals(reviewPosterID)) {
                compEntity.getCompanyReviewList().remove(i);
                em.remove(companyReviewEntity);
                em.merge(compEntity);
                reviewDeleteStatus = true;
            }
        }*/
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
}