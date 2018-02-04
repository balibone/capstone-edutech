package unifysessionbeans.admin;

import unifyentities.voices.CompanyEntity;
import unifyentities.voices.CompanyReviewEntity;
import unifyentities.common.CategoryEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

@Stateless
public class VoicesAdminMgrBean implements VoicesAdminMgrBeanRemote {
  @PersistenceContext
  private EntityManager em;
  private CompanyEntity companyEntity;
  private CompanyReviewEntity companyReviewEntity;
  private CategoryEntity categoryEntity;
  private List<CompanyReviewEntity> companyReviewList;

  @Override
  public boolean createCompany(String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage) {
    companyEntity = new CompanyEntity();
    boolean createCompanyStatus = false;
    if(lookupCategory(companyIndustry) != null) {
        categoryEntity = lookupCategory(companyIndustry);
        companyEntity.create(companyName, companyWebsite, companyHQ, companySize, categoryEntity, companyDescription, companyImage);
        companyReviewList = new ArrayList<CompanyReviewEntity>();
        companyEntity.setCompanyReview(companyReviewList);
        em.persist(companyEntity);
        createCompanyStatus = true;
    }
     return createCompanyStatus;
  }


  @Override
  public List<Vector> viewCompanyList() {
    Query q = em.createQuery("SELECT c from Company c");
    List<Vector> companyList = new ArrayList<Vector>();
    for(Object o: q.getResultList()) {
      CompanyEntity c = (CompanyEntity) o;
      Vector ci = new Vector();
      ci.add(c.getCompanyName());
      ci.add(c.getCompanyAverageRating());
      ci.add(c.getCompanyStatus());
      ci.add(c.getCompanyIndustry().getCategoryName());
      ci.add(c.getCompanyImage());
      companyList.add(ci);
    }
    return companyList;
  }
  
  @Override
  public Vector viewCompanyDetails(String companyName) {
    companyEntity = lookupCompany(companyName);
    Vector companyDetailsVec = new Vector();
    
    if(companyEntity != null) {
        companyDetailsVec.add(companyEntity.getCompanyName());
        companyDetailsVec.add(companyEntity.getCompanyWebsite());
        companyDetailsVec.add(companyEntity.getCompanyHQ());
        companyDetailsVec.add(companyEntity.getCompanyAverageRating());
        companyDetailsVec.add(companyEntity.getCompanySize());
        companyDetailsVec.add(companyEntity.getCompanyStatus());
        companyDetailsVec.add(companyEntity.getCompanyIndustry().getCategoryName());
        companyDetailsVec.add(companyEntity.getCompanyDescription());
        companyDetailsVec.add(companyEntity.getCompanyImage());
        return companyDetailsVec;
    }
    return null;
  }
  

  public CompanyEntity lookupCompany(String companyName){
        CompanyEntity ce = new CompanyEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Company c WHERE c.companyName = :companyName");
            q.setParameter("companyName", companyName);
            ce = (CompanyEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Item cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Item does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
  
  
  @Override
  public boolean deactivateCompany(String companyName) {
      boolean companyDeactivateStatus = true;
      if(lookupCompany(companyName) == null) {
          companyDeactivateStatus = false;
      } else {
          companyEntity = lookupCompany(companyName);
          companyEntity.setCompanyStatus("Inactive");
          em.merge(companyEntity);
      }
      return companyDeactivateStatus;
  } 
  
  @Override
  public boolean activateCompany(String companyName) {
      boolean companyActivateStatus = true;
      if(lookupCompany(companyName) == null) {
          companyActivateStatus = false;
      } else {
          companyEntity = lookupCompany(companyName);
          companyEntity.setCompanyStatus("Active");
          em.merge(companyEntity);
      }
      return companyActivateStatus;
  }
  
  @Override 
  public boolean updateCompany(String oldCompanyName, String companyName, String companyWebsite, String companyHQ, int companySize, String companyIndustry, String companyDescription, String companyImage) {
      boolean companyUpdateStatus = true;
      if(lookupCompany(oldCompanyName) == null) {
          companyUpdateStatus = false;    
      } else {
          if(lookupCategory(companyIndustry) == null) {
              companyUpdateStatus = false;
          } else {
              categoryEntity = lookupCategory(companyIndustry);
              companyEntity = lookupCompany(oldCompanyName);
              companyEntity.setCompanyName(companyName);
              companyEntity.setCompanyWebsite(companyWebsite);
              companyEntity.setCompanyHQ(companyHQ);
              companyEntity.setCompanySize(companySize);
              companyEntity.setCompanyIndustry(categoryEntity);
              companyEntity.setCompanyDescription(companyDescription);
              companyEntity.setCompanyImage(companyImage);
              em.merge(companyEntity);
          }     
      }
      return companyUpdateStatus;
  } 
  
  @Override
  public List<Vector> viewReviewList(String reviewedCompany) {
    companyEntity = lookupCompany(reviewedCompany);
    Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.reviewedCompany = :reviewedCompany");
    q.setParameter("reviewedCompany", companyEntity);
    List<Vector> reviewList = new ArrayList<Vector>();
    for(Object o: q.getResultList()) {
      CompanyReviewEntity cr = (CompanyReviewEntity) o;
      Vector cri = new Vector();
      cri.add(cr.getReviewTitle());
      cri.add(cr.getReviewedCompany().getCompanyName());
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
    
    if(companyReviewEntity != null) {
        reviewDetailsVec.add(companyReviewEntity.getReviewID());
        reviewDetailsVec.add(companyReviewEntity.getReviewTitle());
        reviewDetailsVec.add(companyReviewEntity.getReviewRating());
        reviewDetailsVec.add(companyReviewEntity.getReviewPros());
        reviewDetailsVec.add(companyReviewEntity.getReviewCons());
        reviewDetailsVec.add(companyReviewEntity.getReviewEmpType());
        reviewDetailsVec.add(companyReviewEntity.getReviewThumbsUp());
        reviewDetailsVec.add(companyReviewEntity.getReviewSalaryRange());
        reviewDetailsVec.add(companyReviewEntity.getReviewedCompany().getCompanyName());
        reviewDetailsVec.add(companyReviewEntity.getReviewDate());
        reviewDetailsVec.add(companyReviewEntity.getReviewPosterID());   
        return reviewDetailsVec;
    }
    return null;
  }
  
  public CompanyReviewEntity lookupReview(String reviewedCompany, String reviewPosterID){
        CompanyReviewEntity cre = new CompanyReviewEntity();
        try{
            companyEntity = lookupCompany(reviewedCompany);
            Query q = em.createQuery("SELECT cr FROM CompanyReview cr WHERE cr.reviewedCompany = :reviewedCompany AND cr.reviewPosterID = :reviewPosterID");
            q.setParameter("reviewedCompany", companyEntity);
            q.setParameter("reviewPosterID", reviewPosterID);
            cre = (CompanyReviewEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Item cannot be found. " + enfe.getMessage());
            em.remove(cre);
            cre = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Item does not exist. " + nre.getMessage());
            em.remove(cre);
            cre = null;
        }
        return cre;
    }
  
  @Override
  public boolean deleteReview(String reviewedCompany, String reviewPosterID) {
      boolean reviewDeleteStatus = false;
      companyEntity = lookupCompany(reviewedCompany);
      companyReviewList = companyEntity.getCompanyReviewList();
      for (int i=0; i<companyReviewList.size(); i++) {
          companyReviewEntity = companyReviewList.get(i);
          if(companyReviewEntity.getReviewPosterID().equals(reviewPosterID)) {
              companyEntity.getCompanyReviewList().remove(i);
              em.remove(companyReviewEntity);
              em.merge(companyEntity);
              reviewDeleteStatus = true;
          }
      }
      return reviewDeleteStatus;
  }
  
    public CategoryEntity lookupCategory(String companyIndustry){
        CategoryEntity ce = new CategoryEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryName");
            q.setParameter("categoryName", companyIndustry);
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
}
