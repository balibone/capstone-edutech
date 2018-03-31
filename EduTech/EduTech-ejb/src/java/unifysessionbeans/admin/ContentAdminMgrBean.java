/**
 * *************************************************************************************
 *   Title:                  ContentAdminMgrBean.java
 *   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY CONTENT ADMINISTRATION - ADMIN (EDUBOX)
 *   Created By:             NIGEL LEE TJON YI
 *   Modified By:            TAN CHIN WEE WINSTON
 *   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
 *   Date:                   24 FEBRUARY 2018
 *   Code version:           1.1
 *   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
 **************************************************************************************
 */
package unifysessionbeans.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//imported entities
import commoninfrastructureentities.UserEntity;
import unifyentities.common.TagEntity;
import unifyentities.common.CompanyReviewReportEntity;
import unifyentities.common.ItemReportEntity;
import unifyentities.common.JobReportEntity;
import unifyentities.voices.CompanyReviewEntity;
import unifyentities.marketplace.ItemEntity;
import unifyentities.errands.JobEntity;
import unifyentities.errands.JobReviewEntity;
import unifyentities.common.JobReviewReportEntity;
import unifyentities.common.EventRequestEntity;
import unifyentities.common.MessageEntity;
import unifyentities.event.EventEntity;
import unifyentities.voices.CompanyEntity;

@Stateless
public class ContentAdminMgrBean implements ContentAdminMgrBeanRemote {

    @PersistenceContext
    private EntityManager em;
    private UserEntity uEntity;
    private TagEntity tEntity;
    private ItemReportEntity irEntity;
    private CompanyReviewReportEntity crrEntity;
    private JobReportEntity jrEntity;
    private CompanyReviewEntity crEntity;
    private ItemEntity iEntity;
    private JobEntity jEntity;
    private JobReviewEntity jreEntity;
    private JobReviewReportEntity jrrEntity;
    private EventRequestEntity erEntity;
    private EventEntity eEntity;
    private CompanyEntity cEntity;
    private MessageEntity mEntity;

    @Override
    public List<Vector> viewTagListing() {
        Query q = em.createQuery("SELECT t FROM Tag t");
        List<Vector> tagList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            TagEntity tagE = (TagEntity) o;
            Vector tagVec = new Vector();

            tagVec.add(tagE.getTagID());
            tagVec.add(tagE.getTagName());
            tagVec.add(tagE.getTagType());
            tagList.add(tagVec);
        }
        return tagList;
    }

    @Override
    public String createTag(String tagName, String tagType) {
        tEntity = new TagEntity();
        if (lookupTagNameType(tagName, tagType) == null) {
            if (tEntity.createTag(tagName, tagType)) {
                em.persist(tEntity);
                return "The tag has been created successfully!";
            } else {
                return "There were some issues encountered while creating the tag. Please try again.";
            }
        } else {
            return "Tag name already exists for tag type selected.";
        }
    }

    @Override
    public Vector viewTagDetails(String tagID) {
        tEntity = lookupTag(tagID);
        Vector tagDetailsVec = new Vector();

        if (tEntity != null) {
            tagDetailsVec.add(tEntity.getTagID());
            tagDetailsVec.add(tEntity.getTagName());
            tagDetailsVec.add(tEntity.getTagType());
        }
        return tagDetailsVec;
    }

    @Override
    public String updateTag(String tagID, String tagName, String tagType) {
        if (lookupTag(tagID) == null) {
            return "Selected tag cannot be found. Please try again.";
        } else {
            if (lookupTagNameType(tagName, tagType) == null) {
                tEntity = lookupTag(tagID);
                tEntity.setTagName(tagName);
                tEntity.setTagType(tagType);
                em.merge(tEntity);
                return "Selected tag has been updated successfully!";
            }
            if (tEntity.getTagName().equals(tagName) && tEntity.getTagType().equals(tagType)) {
                return "Selected tag has no changes made.";
            }
            return "Tag name already exists for tag type selected.";
        }
    }

    //reported marketplace items related
    public ItemReportEntity lookupMarketplace(String marketplaceReportID) {
        ItemReportEntity ire = new ItemReportEntity();
        Long itemReportIDNum = Long.valueOf(marketplaceReportID);
        try {
            Query q = em.createQuery("SELECT c FROM ItemReport c WHERE c.itemReportID = :itemReportID");
            q.setParameter("itemReportID", itemReportIDNum);
            ire = (ItemReportEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Marketplace item cannot be found. " + enfe.getMessage());
            em.remove(ire);
            ire = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Marketplace item does not exist. " + nre.getMessage());
            em.remove(ire);
            ire = null;
        }
        return ire;
    }

    public ItemEntity lookupMarketplaceItem(Long itemID) {
        ItemEntity cre = new ItemEntity();
        //Long itemIDNum = Long.valueOf(itemID);
        try {
            Query q = em.createQuery("SELECT c FROM Item c WHERE c.itemID = :itemID");
            q.setParameter("itemID", itemID);
            cre = (ItemEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item cannot be found. " + enfe.getMessage());
            em.remove(cre);
            cre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item does not exist. " + nre.getMessage());
            em.remove(cre);
            cre = null;
        }
        return cre;
    }

    @Override
    public String deleteItem(String itemID) {
        //boolean itemDeleteStatus = false;

        double itemIDNum = Double.parseDouble(itemID);

        try {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemID = :itemID");
            q.setParameter("itemID", itemIDNum);

            iEntity = (ItemEntity) q.getSingleResult();

            em.remove(iEntity);
            em.flush();
            em.clear();
            return "Item has been deleted!";
            //return itemDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item to be deleted cannot be found. " + enfe.getMessage());
            em.remove(iEntity);
            return "Item to be deleted could not be found";
            //return itemDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item to be deleted does not exist. " + nre.getMessage());
            em.remove(iEntity);
            return "Item to be deleted does not exist";
            //return itemDeleteStatus;
        }
    }

    @Override
    public String delistItem(String itemID) {

        double itemIDNum = Double.parseDouble(itemID);

        try {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemID = :itemID");
            q.setParameter("itemID", itemIDNum);

            iEntity = (ItemEntity) q.getSingleResult();

            iEntity.setItemStatus("Delisted");

            //em.remove(iEntity);
            em.flush();
            em.clear();
            return "Item has been delisted!";
            //return itemDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item to be delisted cannot be found. " + enfe.getMessage());
            em.remove(iEntity);
            return "Item to be deleted could not be found";
            //return itemDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item to be delisted does not exist. " + nre.getMessage());
            em.remove(iEntity);
            return "Item to be deleted does not exist";
            //return itemDeleteStatus;
        }
    }

    @Override
    public String resolveMarketplace(String reportID) {
        //boolean success = true;
        try {
            irEntity = lookupMarketplace(reportID);
            irEntity.setItemReportStatus("Resolved");
            irEntity.setItemReviewedDate();
            em.merge(irEntity);
            return "Item report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving item report";
        }
        //return success;
    }

    @Override
    public String resolveOnlyMarketplace(String reportID) {
        //boolean success = true;
        try {
            irEntity = lookupMarketplace(reportID);
            irEntity.setItemReportStatus("Resolved (No Issue Found)");
            irEntity.setItemReviewedDate();
            em.merge(irEntity);
            return "Item report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving item report";
        }
        //return success;
    }

    @Override
    public String resolveDeleteMarketplace(String reportID) {
        //boolean success = true;
        try {
            irEntity = lookupMarketplace(reportID);
            irEntity.setItemReportStatus("Resolved (Deleted)");
            irEntity.setItemReviewedDate();
            em.merge(irEntity);
            return "Item report has been resolved and deleted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving item report";
        }
        //return success;
    }

    @Override
    public String resolveDelistMarketplace(String reportID) {
        //boolean success = true;
        try {
            irEntity = lookupMarketplace(reportID);
            irEntity.setItemReportStatus("Resolved (Delisted)");
            irEntity.setItemReviewedDate();
            em.merge(irEntity);
            return "Item report has been resolved and delisted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving item report";
        }
        //return success;
    }

    @Override
    public String unresolveMarketplace(String reportID) {
        //boolean success = true;
        try {
            irEntity = lookupMarketplace(reportID);
            irEntity.setItemReportStatus("Unresolved");
            irEntity.setItemReviewedDate();
            em.merge(irEntity);
            return "Item report has been unresolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error unresolving item report";
        }
//return success;
    }

    @Override
    public List<Vector> viewReportedMarketplaceListing() {
        Query q = em.createQuery("SELECT i FROM ItemReport i ORDER BY i.itemReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            ItemReportEntity reportedE = (ItemReportEntity) o;
            Vector reportedVec = new Vector();

            reportedVec.add(reportedE.getItemReportID());
            reportedVec.add(reportedE.getItemReportStatus());
            reportedVec.add(reportedE.getItemReportDescription());
            reportedVec.add(df.format(reportedE.getItemReportDate()));
            reportedVec.add(reportedE.getItemID());
            reportedVec.add(reportedE.getItemPosterID());
            reportedVec.add(reportedE.getItemReporterID());
            reportedList.add(reportedVec);
        }
        return reportedList;
    }

    @Override
    public List<Vector> viewReportedMarketplaceListingDashboard() {
        Query q = em.createQuery("SELECT i FROM ItemReport i ORDER BY i.itemReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.setMaxResults(3).getResultList()) {
            ItemReportEntity reportedE = (ItemReportEntity) o;
            Vector reportedVec = new Vector();

            reportedVec.add(reportedE.getItemReportID());
            reportedVec.add(reportedE.getItemReportStatus());
            reportedVec.add(reportedE.getItemReportDescription());
            reportedVec.add(df.format(reportedE.getItemReportDate()));
            reportedVec.add(reportedE.getItemID());
            reportedVec.add(reportedE.getItemPosterID());
            reportedVec.add(reportedE.getItemReporterID());
            reportedList.add(reportedVec);
        }
        return reportedList;
    }

    @Override
    public Vector viewMarketplaceDetails2(String marketplaceReportID) {
        irEntity = lookupMarketplace(marketplaceReportID);
        System.out.println("Looked up marketplace");
        Vector marketplaceDetails = new Vector();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        if (irEntity != null) {
            marketplaceDetails.add(irEntity.getItemReportID());
            marketplaceDetails.add(irEntity.getItemReportStatus());
            marketplaceDetails.add(irEntity.getItemReportDescription());
            marketplaceDetails.add(df.format(irEntity.getItemReportDate()));
            marketplaceDetails.add(irEntity.getItemID());
            marketplaceDetails.add(irEntity.getItemPosterID());
            marketplaceDetails.add(irEntity.getItemReporterID());
            marketplaceDetails.add(df.format(irEntity.getItemReviewedDate()));
            System.out.println("ADDED ITEM REPORT DETAILS");
            //from item entity
            if (lookupMarketplaceItem(irEntity.getItemID()) != null) {
                iEntity = lookupMarketplaceItem(irEntity.getItemID());
                marketplaceDetails.add(iEntity.getItemName());
                marketplaceDetails.add(iEntity.getItemDescription());
                marketplaceDetails.add(iEntity.getItemImage());
                marketplaceDetails.add(iEntity.getItemStatus());
                System.out.println("ADDED ITEM DETAILS");

                return marketplaceDetails;
            }
            System.out.println("ITEM REPORT DETAILS PASSED");
            return marketplaceDetails;
        }
        return null;
    }

    @Override
    public Long getUnresolvedItemReportCount() {
        Long unresolvedItemReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.itemReportID) FROM ItemReport c WHERE c.itemReportStatus='Unresolved'");
        try {
            unresolvedItemReportCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getUnresolvedItemReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return unresolvedItemReportCount;
    }

    @Override
    public Long getResolvedItemReportCount() {
        Long resolvedItemReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.itemReportID) FROM ItemReport c WHERE c.itemReportStatus<>'Unresolved'");
        try {
            resolvedItemReportCount = (Long) q.getSingleResult();
            System.out.println("Resolved Marketplace Item Report Count: " + resolvedItemReportCount);
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getResolvedItemReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return resolvedItemReportCount;
    }

    //reported company reviews related
    @Override
    public List<Vector> viewReportedReviewListing() {
        Query q = em.createQuery("SELECT i FROM CompanyReviewReport i ORDER BY i.reviewReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            CompanyReviewReportEntity reportedE = (CompanyReviewReportEntity) o;
            Vector reportedVec = new Vector();

            reportedVec.add(reportedE.getReviewReportID());
            reportedVec.add(reportedE.getReviewReportStatus());
            reportedVec.add(reportedE.getReviewReportDescription());
            reportedVec.add(df.format(reportedE.getReviewReportDate()));
            reportedVec.add(reportedE.getReviewID());
            reportedVec.add(reportedE.getReviewPosterID());
            reportedVec.add(reportedE.getReviewReporterID());
            reportedList.add(reportedVec);
        }
        return reportedList;
    }

    @Override
    public List<Vector> viewReportedReviewListingDashboard() {
        Query q = em.createQuery("SELECT i FROM CompanyReviewReport i ORDER BY i.reviewReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.setMaxResults(3).getResultList()) {
            CompanyReviewReportEntity reportedE = (CompanyReviewReportEntity) o;
            Vector reportedVec = new Vector();

            reportedVec.add(reportedE.getReviewReportID());
            reportedVec.add(reportedE.getReviewReportStatus());
            reportedVec.add(reportedE.getReviewReportDescription());
            reportedVec.add(df.format(reportedE.getReviewReportDate()));
            reportedVec.add(reportedE.getReviewID());
            reportedVec.add(reportedE.getReviewPosterID());
            reportedVec.add(reportedE.getReviewReporterID());
            reportedList.add(reportedVec);
        }
        return reportedList;
    }

    @Override
    public Vector viewReviewDetails2(String reviewReportID) {
        crrEntity = lookupReportedReview(reviewReportID);
        Vector reviewReportDetails = new Vector();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        if (crrEntity != null) {
            //from reported review entity    
            reviewReportDetails.add(crrEntity.getReviewReportID());
            reviewReportDetails.add(crrEntity.getReviewReportStatus());
            reviewReportDetails.add(crrEntity.getReviewReportDescription());
            reviewReportDetails.add(df.format(crrEntity.getReviewReportDate()));
            reviewReportDetails.add(crrEntity.getReviewID());
            reviewReportDetails.add(crrEntity.getReviewPosterID());
            reviewReportDetails.add(crrEntity.getReviewReporterID());
            reviewReportDetails.add(df.format(crrEntity.getReviewReviewedDate()));
            System.out.println("ADDED REVIEW REPORT DETAILS");
            //from review entity
            if (lookupReview(crrEntity.getReviewID()) != null) {
                crEntity = lookupReview(crrEntity.getReviewID());
                reviewReportDetails.add(crEntity.getCompanyEntity().getCompanyName());
                reviewReportDetails.add(crEntity.getReviewTitle());
                reviewReportDetails.add(crEntity.getReviewPros());
                reviewReportDetails.add(crEntity.getReviewCons());
                reviewReportDetails.add(crEntity.getReviewStatus());
                System.out.println("ADDED REVIEW DETAILS");

                return reviewReportDetails;
            }
            return reviewReportDetails;
        }
        return null;
    }

    public CompanyReviewReportEntity lookupReportedReview(String reviewReportID) {
        CompanyReviewReportEntity ire = new CompanyReviewReportEntity();
        Long reviewReportIDNum = Long.valueOf(reviewReportID);
        try {
            Query q = em.createQuery("SELECT c FROM CompanyReviewReport c WHERE c.reviewReportID = :reviewReportID");
            q.setParameter("reviewReportID", reviewReportIDNum);
            ire = (CompanyReviewReportEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Company review cannot be found. " + enfe.getMessage());
            em.remove(ire);
            ire = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Company review does not exist. " + nre.getMessage());
            em.remove(ire);
            ire = null;
        }
        return ire;
    }

    public CompanyReviewEntity lookupReview(String reviewID) {
        CompanyReviewEntity cre = new CompanyReviewEntity();
        Long reviewIDNum = Long.valueOf(reviewID);
        try {
            Query q = em.createQuery("SELECT c FROM CompanyReview c WHERE c.reviewID = :reviewID");
            q.setParameter("reviewID", reviewIDNum);
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
    public String resolveReview(String reportID) {

        //boolean success = true;
        try {
            crrEntity = lookupReportedReview(reportID);
            crrEntity.setReviewReportStatus("Resolved");
            crrEntity.setReviewReviewedDate();
            em.merge(crrEntity);
            return "Company review report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving company review report";
        }
//return success;
    }

    @Override
    public String resolveOnlyReview(String reportID) {

        try {
            crrEntity = lookupReportedReview(reportID);
            crrEntity.setReviewReportStatus("Resolved (No Issue Found)");
            crrEntity.setReviewReviewedDate();
            em.merge(crrEntity);
            return "Company review report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving company review report";
        }

    }

    @Override
    public String resolveDeleteReview(String reportID) {

        try {
            crrEntity = lookupReportedReview(reportID);
            crrEntity.setReviewReportStatus("Resolved (Deleted)");
            crrEntity.setReviewReviewedDate();
            em.merge(crrEntity);
            return "Company review report has been resolved and deleted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving company review report";
        }

    }

    @Override
    public String resolveDelistReview(String reportID) {

        try {
            crrEntity = lookupReportedReview(reportID);
            crrEntity.setReviewReportStatus("Resolved (Delisted)");
            crrEntity.setReviewReviewedDate();
            em.merge(crrEntity);
            return "Company review report has been resolved and delisted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving company review report";
        }

    }

    @Override
    public String unresolveReview(String reportID) {

        //boolean success = true;
        try {
            crrEntity = lookupReportedReview(reportID);
            crrEntity.setReviewReportStatus("Unresolved");
            crrEntity.setReviewReviewedDate();
            em.merge(crrEntity);
            return "Company review report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving company review report";
        }
        //return success;
    }

    @Override
    public String deleteReview(String reviewID) {
        //boolean reviewDeleteStatus = false;

        double reviewIDNum = Double.parseDouble(reviewID);

        try {
            Query q = em.createQuery("SELECT i FROM CompanyReview i WHERE i.reviewID = :reviewID");
            q.setParameter("reviewID", reviewIDNum);

            crEntity = (CompanyReviewEntity) q.getSingleResult();

            em.remove(crEntity);
            em.flush();
            em.clear();
            return "Company review has been deleted!";
            //return reviewDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Review to be deleted cannot be found. " + enfe.getMessage());
            em.remove(crEntity);
            return "Company review to be deleted could not be found";
            //return reviewDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Review to be deleted does not exist. " + nre.getMessage());
            em.remove(crEntity);
            return "Company review to be deleted does not exist";
            //return reviewDeleteStatus;
        }

    }

    @Override
    public String delistReview(String reviewID) {
        //boolean reviewDeleteStatus = false;

        double reviewIDNum = Double.parseDouble(reviewID);

        try {
            Query q = em.createQuery("SELECT i FROM CompanyReview i WHERE i.reviewID = :reviewID");
            q.setParameter("reviewID", reviewIDNum);

            crEntity = (CompanyReviewEntity) q.getSingleResult();

            crEntity.setReviewStatus("Delisted");

            //em.remove(crEntity);
            em.flush();
            em.clear();
            return "Company review has been delisted!";
            //return reviewDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Review to be delisted cannot be found. " + enfe.getMessage());
            em.remove(crEntity);
            return "Company review to be delisted could not be found";
            //return reviewDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Review to be delisted does not exist. " + nre.getMessage());
            em.remove(crEntity);
            return "Company review to be delisted does not exist";
            //return reviewDeleteStatus;
        }

    }

    @Override
    public Long getUnresolvedCompanyReviewReportCount() {
        Long unresolvedCompanyReviewReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.reviewReportID) FROM CompanyReviewReport c WHERE c.reviewReportStatus='Unresolved'");
        try {
            unresolvedCompanyReviewReportCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getUnresolvedCompanyReviewReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return unresolvedCompanyReviewReportCount;
    }

    @Override
    public Long getResolvedCompanyReviewReportCount() {
        Long resolvedCompanyReviewReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.reviewReportID) FROM CompanyReviewReport c WHERE c.reviewReportStatus<>'Unresolved'");
        try {
            resolvedCompanyReviewReportCount = (Long) q.getSingleResult();
            System.out.println("Resolved Company Review Report Count: " + resolvedCompanyReviewReportCount);
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getResolvedCompanyReviewReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return resolvedCompanyReviewReportCount;
    }

    //reported jobs related
    public JobReportEntity lookupReportedErrand(String jobReportID) {
        JobReportEntity jre = new JobReportEntity();
        Long jobReportIDNum = Long.valueOf(jobReportID);
        try {
            Query q = em.createQuery("SELECT c FROM JobReport c WHERE c.jobReportID = :jobReportID");
            q.setParameter("jobReportID", jobReportIDNum);
            jre = (JobReportEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Errand cannot be found. " + enfe.getMessage());
            em.remove(jre);
            jre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Errand does not exist. " + nre.getMessage());
            em.remove(jre);
            jre = null;
        }
        return jre;
    }

    public JobEntity lookupJob(Long jobID) {
        JobEntity jre = new JobEntity();
        //Long jobIDNum = Long.valueOf(jobID);
        try {
            Query q = em.createQuery("SELECT c FROM Job c WHERE c.jobID = :jobID");
            q.setParameter("jobID", jobID);
            jre = (JobEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job cannot be found. " + enfe.getMessage());
            em.remove(jre);
            jre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job does not exist. " + nre.getMessage());
            em.remove(jre);
            jre = null;
        }
        return jre;
    }

    @Override
    public String deleteJob(String jobID) {
        //boolean jobDeleteStatus = false;

        double jobIDNum = Double.parseDouble(jobID);

        try {
            Query q = em.createQuery("SELECT i FROM Job i WHERE i.jobID = :jobID");
            q.setParameter("jobID", jobIDNum);

            jEntity = (JobEntity) q.getSingleResult();

            em.remove(jEntity);
            em.flush();
            em.clear();
            //return jobDeleteStatus = true;
            return "Job has been deleted!";
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job to be deleted cannot be found. " + enfe.getMessage());
            em.remove(jEntity);
            return "Job to be deleted could not be found";
            //return jobDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job to be deleted does not exist. " + nre.getMessage());
            em.remove(jEntity);
            return "Job to be deleted does not exist";
            //return jobDeleteStatus;
        }

    }

    @Override
    public String delistJob(String jobID) {
        //boolean jobDeleteStatus = false;

        double jobIDNum = Double.parseDouble(jobID);

        try {
            Query q = em.createQuery("SELECT i FROM Job i WHERE i.jobID = :jobID");
            q.setParameter("jobID", jobIDNum);

            jEntity = (JobEntity) q.getSingleResult();

            jEntity.setJobStatus("Delisted");

            //em.remove(jEntity);
            em.flush();
            em.clear();
            //return jobDeleteStatus = true;
            return "Job has been delisted!";
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job to be delisted cannot be found. " + enfe.getMessage());
            em.remove(jEntity);
            return "Job to be delisted could not be found";
            //return jobDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job to be delisted does not exist. " + nre.getMessage());
            em.remove(jEntity);
            return "Job to be delisted does not exist";
            //return jobDeleteStatus;
        }

    }

    @Override
    public String resolveErrand(String reportID) {

        //boolean success = true;
        try {
            jrEntity = lookupReportedErrand(reportID);
            jrEntity.setJobReportStatus("Resolved");
            jrEntity.setJobReviewedDate();
            em.merge(jrEntity);
            return "Errand report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand report";
        }
        //return success;
    }

    @Override
    public String resolveOnlyErrand(String reportID) {

        try {
            jrEntity = lookupReportedErrand(reportID);
            jrEntity.setJobReportStatus("Resolved (No Issue Found)");
            jrEntity.setJobReviewedDate();
            em.merge(jrEntity);
            return "Errand report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand report";
        }
        //return success;
    }

    @Override
    public String resolveDeleteErrand(String reportID) {

        try {
            jrEntity = lookupReportedErrand(reportID);
            jrEntity.setJobReportStatus("Resolved (Deleted)");
            jrEntity.setJobReviewedDate();
            em.merge(jrEntity);
            return "Errand report has been resolved and deleted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand report";
        }
        //return success;
    }

    @Override
    public String resolveDelistErrand(String reportID) {

        try {
            jrEntity = lookupReportedErrand(reportID);
            jrEntity.setJobReportStatus("Resolved (Delisted)");
            jrEntity.setJobReviewedDate();
            em.merge(jrEntity);
            return "Errand report has been resolved and delisted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand report";
        }
        //return success;
    }

    @Override
    public String unresolveErrand(String reportID) {

        //boolean success = true;
        try {
            jrEntity = lookupReportedErrand(reportID);
            jrEntity.setJobReportStatus("Unresolved");
            jrEntity.setJobReviewedDate();
            em.merge(jrEntity);
            return "Errand has been unresolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error unresolving errand";
        }

        //return success;
    }

    @Override
    public List<Vector> viewReportedErrandsListing() {
        Query q = em.createQuery("SELECT i FROM JobReport i ORDER BY i.jobReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            JobReportEntity reportedE = (JobReportEntity) o;
            Vector reportedVec = new Vector();

            reportedVec.add(reportedE.getJobReportID());
            reportedVec.add(reportedE.getJobReportStatus());
            reportedVec.add(reportedE.getJobReportDescription());
            reportedVec.add(df.format(reportedE.getJobReportDate()));
            reportedVec.add(reportedE.getJobID());
            reportedVec.add(reportedE.getJobPosterID());
            reportedVec.add(reportedE.getJobReporterID());
            reportedList.add(reportedVec);
        }
        return reportedList;
    }

    @Override
    public List<Vector> viewReportedErrandsListingDashboard() {
        Query q = em.createQuery("SELECT i FROM JobReport i ORDER BY i.jobReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.setMaxResults(3).getResultList()) {
            JobReportEntity reportedE = (JobReportEntity) o;
            Vector reportedVec = new Vector();

            reportedVec.add(reportedE.getJobReportID());
            reportedVec.add(reportedE.getJobReportStatus());
            reportedVec.add(reportedE.getJobReportDescription());
            reportedVec.add(df.format(reportedE.getJobReportDate()));
            reportedVec.add(reportedE.getJobID());
            reportedVec.add(reportedE.getJobPosterID());
            reportedVec.add(reportedE.getJobReporterID());
            reportedList.add(reportedVec);
        }
        return reportedList;
    }

    @Override
    public Vector viewErrandDetails2(String errandReportID) {
        jrEntity = lookupReportedErrand(errandReportID);
        Vector errandDetails = new Vector();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        if (jrEntity != null) {
            //from reported errand entity    
            errandDetails.add(jrEntity.getJobReportID());
            errandDetails.add(jrEntity.getJobReportStatus());
            errandDetails.add(jrEntity.getJobReportDescription());
            errandDetails.add(df.format(jrEntity.getJobReportDate()));
            errandDetails.add(jrEntity.getJobID());
            errandDetails.add(jrEntity.getJobPosterID());
            errandDetails.add(jrEntity.getJobReporterID());
            errandDetails.add(df.format(jrEntity.getJobReviewedDate()));
            System.out.println("ADDED ERRAND REPORT DETAILS");
            //from job entity
            if (lookupJob(jrEntity.getJobID()) != null) {
                jEntity = lookupJob(jrEntity.getJobID());
                errandDetails.add(jEntity.getJobTitle());
                errandDetails.add(jEntity.getJobDescription());
                errandDetails.add(jEntity.getJobImage());
                errandDetails.add(jEntity.getJobStatus());
                System.out.println("ADDED ERRAND DETAILS");

                return errandDetails;
            }
            return errandDetails;
        }
        return null;
    }

    @Override
    public Long getUnresolvedErrandsReportCount() {
        Long unresolvedErrandsReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.jobReportID) FROM JobReport c WHERE c.jobReportStatus='Unresolved'");
        try {
            unresolvedErrandsReportCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getUnresolvedErrandsReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return unresolvedErrandsReportCount;
    }

    @Override
    public Long getResolvedErrandsReportCount() {
        Long resolvedErrandsReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.jobReportID) FROM JobReport c WHERE c.jobReportStatus<>'Unresolved'");
        try {
            resolvedErrandsReportCount = (Long) q.getSingleResult();
            System.out.println("Resolved Errands Report Count: " + resolvedErrandsReportCount);
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getResolvedErrandsReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return resolvedErrandsReportCount;
    }

    //reported jobs review related
    @Override
    public List<Vector> viewReportedErrandsReviewListing() {
        Query q = em.createQuery("SELECT i FROM JobReviewReport i ORDER BY i.jobReviewReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            JobReviewReportEntity reportedE = (JobReviewReportEntity) o;
            Vector reportedVec = new Vector();
            reportedVec.add(reportedE.getJobReviewReportID());
            reportedVec.add(reportedE.getJobReviewReportStatus());
            reportedVec.add(reportedE.getJobReviewReportDescription());
            reportedVec.add(df.format(reportedE.getJobReviewReportDate()));
            reportedVec.add(reportedE.getJobReviewID());
            reportedVec.add(reportedE.getJobReviewPosterID());
            reportedVec.add(reportedE.getJobReviewReporterID());
            reportedList.add(reportedVec);

        }
        return reportedList;
    }

    @Override
    public List<Vector> viewReportedErrandsReviewListingDashboard() {
        Query q = em.createQuery("SELECT i FROM JobReviewReport i ORDER BY i.jobReviewReportDate DESC");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.setMaxResults(3).getResultList()) {
            JobReviewReportEntity reportedE = (JobReviewReportEntity) o;
            Vector reportedVec = new Vector();
            reportedVec.add(reportedE.getJobReviewReportID());
            reportedVec.add(reportedE.getJobReviewReportStatus());
            reportedVec.add(reportedE.getJobReviewReportDescription());
            reportedVec.add(df.format(reportedE.getJobReviewReportDate()));
            reportedVec.add(reportedE.getJobReviewID());
            reportedVec.add(reportedE.getJobReviewPosterID());
            reportedVec.add(reportedE.getJobReviewReporterID());
            reportedList.add(reportedVec);

        }
        return reportedList;
    }

    @Override
    public Vector viewErrandReviewDetails(String errandReviewReportID) {
        jrrEntity = lookupReportedErrandReview(errandReviewReportID);
        Vector errandReviewDetails = new Vector();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        if (jrrEntity != null) {
            //from reported errand entity    
            errandReviewDetails.add(jrrEntity.getJobReviewReportID());
            errandReviewDetails.add(jrrEntity.getJobReviewReportStatus());
            errandReviewDetails.add(jrrEntity.getJobReviewReportDescription());
            errandReviewDetails.add(df.format(jrrEntity.getJobReviewReportDate()));
            errandReviewDetails.add(jrrEntity.getJobReviewID());
            errandReviewDetails.add(jrrEntity.getJobReviewPosterID());
            errandReviewDetails.add(jrrEntity.getJobReviewReporterID());
            errandReviewDetails.add(df.format(jrrEntity.getJobReviewReviewedDate()));
            System.out.println("ADDED ERRAND REVIEW REPORT DETAILS");
            //from job entity
            if (lookupJobReview(jrrEntity.getJobReviewID()) != null) {
                jreEntity = lookupJobReview(jrrEntity.getJobReviewID());
                errandReviewDetails.add(jreEntity.getJobReviewRating());
                errandReviewDetails.add(jreEntity.getJobReviewContent());
                errandReviewDetails.add(jreEntity.getJobReviewStatus());
                System.out.println("ADDED ERRAND REVIEW DETAILS");

                return errandReviewDetails;
            }
            return errandReviewDetails;
        }
        return null;
    }

    public JobReviewEntity lookupJobReview(Long jobReviewID) {
        JobReviewEntity jre = new JobReviewEntity();
        try {
            Query q = em.createQuery("SELECT c FROM JobReview c WHERE c.jobReviewID = :jobReviewID");
            q.setParameter("jobReviewID", jobReviewID);
            jre = (JobReviewEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job Review cannot be found. " + enfe.getMessage());
            em.remove(jre);
            jre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job Review does not exist. " + nre.getMessage());
            em.remove(jre);
            jre = null;
        }
        return jre;
    }

    public JobReviewReportEntity lookupReportedErrandReview(String jobReviewReportID) {
        JobReviewReportEntity jrre = new JobReviewReportEntity();
        Long jobReviewReportIDNum = Long.valueOf(jobReviewReportID);
        try {
            Query q = em.createQuery("SELECT c FROM JobReviewReport c WHERE c.jobReviewReportID = :jobReviewReportID");
            q.setParameter("jobReviewReportID", jobReviewReportIDNum);
            jrre = (JobReviewReportEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Errand Review cannot be found. " + enfe.getMessage());
            em.remove(jrre);
            jrre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Errand Review does not exist. " + nre.getMessage());
            em.remove(jrre);
            jrre = null;
        }
        return jrre;
    }

    @Override
    public String resolveErrandReview(String reportID) {

        //boolean success = true;
        try {
            jrrEntity = lookupReportedErrandReview(reportID);
            jrrEntity.setJobReviewReportStatus("Resolved");
            jrrEntity.setJobReviewReviewedDate();
            em.merge(jrrEntity);
            return "Errand review report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand review report";
        }

        //return success;
    }

    @Override
    public String resolveOnlyErrandReview(String reportID) {

        try {
            jrrEntity = lookupReportedErrandReview(reportID);
            jrrEntity.setJobReviewReportStatus("Resolved (No Issue Found)");
            jrrEntity.setJobReviewReviewedDate();
            em.merge(jrrEntity);
            return "Errand review report has been resolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand review report";
        }

    }

    @Override
    public String resolveDeleteErrandReview(String reportID) {

        try {
            jrrEntity = lookupReportedErrandReview(reportID);
            jrrEntity.setJobReviewReportStatus("Resolved (Deleted)");
            jrrEntity.setJobReviewReviewedDate();
            em.merge(jrrEntity);
            return "Errand review report has been resolved and deleted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand review report";
        }

    }

    @Override
    public String resolveDelistErrandReview(String reportID) {

        try {
            jrrEntity = lookupReportedErrandReview(reportID);
            jrrEntity.setJobReviewReportStatus("Resolved (Delisted)");
            jrrEntity.setJobReviewReviewedDate();
            em.merge(jrrEntity);
            return "Errand review report has been resolved and delisted!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error resolving errand review report";
        }

    }

    @Override
    public String unresolveErrandReview(String reportID) {

        //boolean success = true;
        try {
            jrrEntity = lookupReportedErrandReview(reportID);
            jrrEntity.setJobReviewReportStatus("Unresolved");
            jrrEntity.setJobReviewReviewedDate();
            em.merge(jrrEntity);
            return "Errand review report has been unresolved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error unresolving errand review report";
        }
        //return success;
    }

    @Override
    public String deleteJobReview(String jobReviewID) {
        //boolean jobDeleteStatus = false;

        double jobReviewIDNum = Double.parseDouble(jobReviewID);

        try {
            Query q = em.createQuery("SELECT i FROM JobReview i WHERE i.jobReviewID = :jobReviewID");
            q.setParameter("jobReviewID", jobReviewIDNum);

            jreEntity = (JobReviewEntity) q.getSingleResult();

            em.remove(jreEntity);
            em.flush();
            em.clear();
            return "Job review has been deleted!";
            //return jobDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job review to be deleted cannot be found. " + enfe.getMessage());
            em.remove(jreEntity);
            return "Job review to be deleted could not be found";
            //return jobDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job review to be deleted does not exist. " + nre.getMessage());
            em.remove(jreEntity);
            return "Job review to be deleted does not exist";
            //return jobDeleteStatus;
        }

    }

    @Override
    public String delistJobReview(String jobReviewID) {
        //boolean jobDeleteStatus = false;

        double jobReviewIDNum = Double.parseDouble(jobReviewID);

        try {
            Query q = em.createQuery("SELECT i FROM JobReview i WHERE i.jobReviewID = :jobReviewID");
            q.setParameter("jobReviewID", jobReviewIDNum);

            jreEntity = (JobReviewEntity) q.getSingleResult();

            jreEntity.setJobReviewStatus("Delisted");

            //em.remove(jreEntity);
            em.flush();
            em.clear();
            return "Job review has been delisted!";
            //return jobDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job review to be delisted cannot be found. " + enfe.getMessage());
            em.remove(jreEntity);
            return "Job review to be delisted could not be found";
            //return jobDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job review to be delisted does not exist. " + nre.getMessage());
            em.remove(jreEntity);
            return "Job review to be delisted does not exist";
            //return jobDeleteStatus;
        }

    }

    @Override
    public String sendAlertReport(String messageSenderID, String messageReceiverID, String itemReported) {

        try {
            /* MESSAGE SENDER IS THE ADMIN, MESSAGE RECEIVER IS THE REPORTED ENTITY CREATOR */
            mEntity = new MessageEntity();
          
            mEntity.createContentMessage(messageSenderID, messageReceiverID,
                    itemReported + " was delisted due to violation of posting guidelines.",
                    Long.parseLong(itemReported), "System");

            UserEntity user = lookupUnifyUser(messageSenderID);

            mEntity.setUserEntity(user);

            em.persist(mEntity);

            return "Message successfully sent!";
            
        } catch (Exception nre) {
            System.out.println("ERROR: Message cannot be sent. " + nre.getMessage());
            
            return "Error sending message to user";
        }

    }
    
    @Override
    public String sendAlertEventRequest(String messageSenderID, String messageReceiverID, String eventID, String status) {

        try {
            /* MESSAGE SENDER IS THE ADMIN, MESSAGE RECEIVER IS THE REPORTED ENTITY CREATOR */
            mEntity = new MessageEntity();
          
            String eventTitle = erEntity.getEventRequestTitle(Long.parseLong(eventID));
            
            mEntity.createContentMessage(messageSenderID, messageReceiverID,
                    eventTitle + " (Request ID: " + eventID + ") was " + status + " by the administrator.",
                    Long.parseLong(eventID), "System");

            UserEntity user = lookupUnifyUser(messageSenderID);

            mEntity.setUserEntity(user);

            em.persist(mEntity);

            return "Message successfully sent!";
            
        } catch (Exception nre) {
            System.out.println("ERROR: Message cannot be sent. " + nre.getMessage());
            
            return "Error sending message to user";
        }

    }

    @Override
    public Long getUnresolvedErrandsReviewReportCount() {
        Long unresolvedErrandsReviewReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.jobReviewReportID) FROM JobReviewReport c WHERE c.jobReviewReportStatus='Unresolved'");
        try {
            unresolvedErrandsReviewReportCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getUnresolvedErrandsReviewReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return unresolvedErrandsReviewReportCount;
    }

    @Override
    public Long getResolvedErrandsReviewReportCount() {
        Long resolvedErrandsReviewReportCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.jobReviewReportID) FROM JobReviewReport c WHERE c.jobReviewReportStatus<>'Unresolved'");
        try {
            resolvedErrandsReviewReportCount = (Long) q.getSingleResult();
            System.out.println("Resolved Errands Review Report Count: " + resolvedErrandsReviewReportCount);
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getResolvedErrandsReviewReportCount().getSingleResult()");
            ex.printStackTrace();
        }
        return resolvedErrandsReviewReportCount;
    }

    @Override
    public String deleteTag(String tagID) {
        if (lookupTag(tagID) == null) {
            return "Selected tag cannot be found. Please try again.";
        } else {
            tEntity = lookupTag(tagID);
            em.remove(tEntity);
            em.flush();
            em.clear();
            return "Selected tag has been deleted successfully!";
        }
    }

    //event related
    @Override
    public List<Vector> viewEventRequestListing() {
        Query q = em.createQuery("SELECT i FROM EventRequest i ORDER BY i.eventRequestDate DESC");
        List<Vector> requestList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            EventRequestEntity requestE = (EventRequestEntity) o;
            Vector requestVec = new Vector();

            uEntity = requestE.getUserEntity();
            String username = uEntity.getUsername();

            requestVec.add(requestE.getEventRequestID());
            requestVec.add(requestE.getEventRequestStatus());

            requestVec.add(df.format(requestE.getEventRequestDate()));
            requestVec.add(username);

            requestVec.add(requestE.getEventRequestTitle());

            requestList.add(requestVec);
        }
        return requestList;
    }

    @Override
    public List<Vector> viewEventRequestListingDashboard() {
        Query q = em.createQuery("SELECT i FROM EventRequest i ORDER BY i.eventRequestDate DESC");
        List<Vector> requestList = new ArrayList<Vector>();

        System.out.println("Queried Event Request Table");

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.setMaxResults(3).getResultList()) {
            EventRequestEntity requestE = (EventRequestEntity) o;
            Vector requestVec = new Vector();

            uEntity = requestE.getUserEntity();
            String username = uEntity.getUsername();

            requestVec.add(requestE.getEventRequestID());
            requestVec.add(requestE.getEventRequestStatus());

            requestVec.add(df.format(requestE.getEventRequestDate()));
            requestVec.add(username);

            requestList.add(requestVec);
        }
        return requestList;
    }

    @Override
    public Vector viewEventRequestDetails(String requestID) {
        erEntity = lookupRequestedEvent(requestID);
        Vector requestDetails = new Vector();

        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        //DateFormat df2 = new SimpleDateFormat("d MMMM yyyy");

        if (erEntity != null) {
            uEntity = erEntity.getUserEntity();
            String username = uEntity.getUsername();

            requestDetails.add(erEntity.getEventRequestID());
            requestDetails.add(erEntity.getEventRequestStatus());
            requestDetails.add(df.format(erEntity.getEventRequestDate()));
            requestDetails.add(username);

            requestDetails.add(erEntity.getEventRequestDescription());
            requestDetails.add(erEntity.getEventRequestVenue());
            requestDetails.add(df.format(erEntity.getEventRequestStartDateTime()));
            requestDetails.add(df.format(erEntity.getEventRequestEndDateTime()));

            requestDetails.add(df.format(erEntity.getEventReviewedDate()));

            requestDetails.add(erEntity.getEventRequestVenueLat());
            requestDetails.add(erEntity.getEventRequestVenueLong());

            requestDetails.add(erEntity.getEventRequestTitle());

            System.out.println("ADDED EVENT REQUEST DETAILS");

            return requestDetails;
        }
        return null;
    }

    public EventRequestEntity lookupRequestedEvent(String requestID) {
        EventRequestEntity jre = new EventRequestEntity();
        Long eventRequestIDNum = Long.valueOf(requestID);
        try {
            Query q = em.createQuery("SELECT c FROM EventRequest c WHERE c.eventRequestID = :eventRequestID");
            q.setParameter("eventRequestID", eventRequestIDNum);
            jre = (EventRequestEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Event requested cannot be found. " + enfe.getMessage());
            em.remove(jre);
            jre = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Event requested does not exist. " + nre.getMessage());
            em.remove(jre);
            jre = null;
        }
        return jre;
    }

    @Override
    public String approveEventRequest(String requestID) {

        //boolean success = true;
        //set event request to approved
        try {
            erEntity = lookupRequestedEvent(requestID);
            erEntity.setEventRequestStatus("Approved");
            erEntity.setEventReviewedDate();
            em.merge(erEntity);
            System.out.println("Event Request Approved");

            //create event in event entity
            eEntity = new EventEntity();
            eEntity.createEvent(erEntity.getEventRequestDescription(), erEntity.getEventRequestVenue(),
                    erEntity.getEventRequestStartDateTime(), erEntity.getEventRequestEndDateTime(), erEntity.getUserEntity());
            em.persist(eEntity);
            System.out.println("Event Created");
            return "Event has been approved!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error approving event request";
        }
        //return success;
    }

    @Override
    public String rejectEventRequest(String requestID) {

        //boolean success = true;
        //set event request to approved
        try {
            erEntity = lookupRequestedEvent(requestID);
            erEntity.setEventRequestStatus("Rejected");
            erEntity.setEventReviewedDate();
            em.merge(erEntity);
            System.out.println("Event Request Rejected");
            return "Event has been rejected!";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error rejecting event request";
        }
        //return success;
    }

    @Override
    public Long getPendingEventRequestCount() {
        Long pendingEventRequestCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.eventRequestID) FROM EventRequest c WHERE c.eventRequestStatus='Pending'");
        try {
            pendingEventRequestCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getPendingEventRequestCount().getSingleResult()");
            ex.printStackTrace();
        }
        return pendingEventRequestCount;
    }

    @Override
    public Long getApprovedEventRequestCount() {
        Long approvedEventRequestCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.eventRequestID) FROM EventRequest c WHERE c.eventRequestStatus='Approved'");
        try {
            approvedEventRequestCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getApprovedEventRequestCount().getSingleResult()");
            ex.printStackTrace();
        }
        return approvedEventRequestCount;
    }

    @Override
    public Long getRejectedEventRequestCount() {
        Long rejectedEventRequestCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.eventRequestID) FROM EventRequest c WHERE c.eventRequestStatus='Rejected'");
        try {
            rejectedEventRequestCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getRejectedEventRequestCount().getSingleResult()");
            ex.printStackTrace();
        }
        return rejectedEventRequestCount;
    }

    /* MISCELLANEOUS METHODS */
    public TagEntity lookupTag(String tagID) {
        TagEntity te = new TagEntity();
        Long tagIDNum = Long.valueOf(tagID);

        try {
            Query q = em.createQuery("SELECT c FROM Tag c WHERE c.tagID = :tagID");
            q.setParameter("tagID", tagIDNum);
            te = (TagEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Tag cannot be found. " + enfe.getMessage());
            em.remove(te);
            te = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Tag does not exist. " + nre.getMessage());
            em.remove(te);
            te = null;
        }
        return te;
    }

    public TagEntity lookupTagNameType(String tagName, String tagType) {
        TagEntity te = new TagEntity();
        //Long tagIDNum = Long.parseLong(tagID);

        try {
            Query q = em.createQuery("SELECT c FROM Tag c WHERE upper(c.tagName) = :tagName AND c.tagType = :tagType");
            q.setParameter("tagName", tagName.toUpperCase());
            q.setParameter("tagType", tagType);
            te = (TagEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Tag cannot be found. " + enfe.getMessage());
            em.remove(te);
            te = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Tag does not exist. " + nre.getMessage());
            em.remove(te);
            te = null;
        }
        return te;
    }

    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try {
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }

    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    @Override
    public Long getTagsListCount() {
        Long tagsListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(t.tagID) FROM Tag t");
        try {
            tagsListCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ContentAdminMgrBean.getTagsListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return tagsListCount;
    }
}
