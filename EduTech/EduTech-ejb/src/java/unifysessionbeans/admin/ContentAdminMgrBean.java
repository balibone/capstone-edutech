package unifysessionbeans.admin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import commoninfrastructure.UserEntity;
import unifyentities.common.TagEntity;
import unifyentities.common.CompanyReviewReportEntity;
import unifyentities.common.ItemReportEntity;
import unifyentities.common.JobReportEntity;
import unifyentities.voices.CompanyReviewEntity;
import unifyentities.marketplace.ItemEntity;
import unifyentities.errands.JobEntity;

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
    public boolean deleteItem(String itemID) {
        boolean itemDeleteStatus = false;

        double itemIDNum = Double.parseDouble(itemID);

        try {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemID = :itemID");
            q.setParameter("itemID", itemIDNum);

            iEntity = (ItemEntity) q.getSingleResult();

            em.remove(iEntity);
            em.flush();
            em.clear();
            return itemDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item to be deleted cannot be found. " + enfe.getMessage());
            em.remove(iEntity);
            return itemDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item to be deleted does not exist. " + nre.getMessage());
            em.remove(iEntity);
            return itemDeleteStatus;
        }

    }

    @Override
    public boolean resolveMarketplace(String reportID) {

        boolean success = true;

        irEntity = lookupMarketplace(reportID);
        irEntity.setItemReportStatus("Resolved");
        em.merge(irEntity);
        return success;
    }

    @Override
    public boolean unresolveMarketplace(String reportID) {

        boolean success = true;

        irEntity = lookupMarketplace(reportID);
        irEntity.setItemReportStatus("Unresolved");
        em.merge(irEntity);
        return success;
    }

    @Override
    public List<Vector> viewReportedMarketplaceListing() {
        Query q = em.createQuery("SELECT i FROM ItemReport i");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

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
    public Vector viewMarketplaceDetails(String marketplaceReportID) {
        irEntity = lookupMarketplace(marketplaceReportID);
        Vector marketplaceDetails = new Vector();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

        if (irEntity != null) {
            marketplaceDetails.add(irEntity.getItemReportID());
            marketplaceDetails.add(irEntity.getItemReportStatus());
            marketplaceDetails.add(irEntity.getItemReportDescription());
            marketplaceDetails.add(df.format(irEntity.getItemReportDate()));
            marketplaceDetails.add(irEntity.getItemID());
            marketplaceDetails.add(irEntity.getItemPosterID());
            marketplaceDetails.add(irEntity.getItemReporterID());
            return marketplaceDetails;
        }
        return null;
    }

    @Override
    public Vector viewMarketplaceDetails2(String marketplaceReportID) {
        irEntity = lookupMarketplace(marketplaceReportID);
        System.out.println("Looked up marketplace");
        Vector marketplaceDetails = new Vector();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

        if (irEntity != null) {
            marketplaceDetails.add(irEntity.getItemReportID());
            marketplaceDetails.add(irEntity.getItemReportStatus());
            marketplaceDetails.add(irEntity.getItemReportDescription());
            marketplaceDetails.add(df.format(irEntity.getItemReportDate()));
            marketplaceDetails.add(irEntity.getItemID());
            marketplaceDetails.add(irEntity.getItemPosterID());
            marketplaceDetails.add(irEntity.getItemReporterID());
            System.out.println("ADDED ITEM REPORT DETAILS");
            //from item entity
            if (lookupMarketplaceItem(irEntity.getItemID()) != null) {
                iEntity = lookupMarketplaceItem(irEntity.getItemID());
                marketplaceDetails.add(iEntity.getItemName());
                marketplaceDetails.add(iEntity.getItemDescription());
                marketplaceDetails.add(iEntity.getItemImage());
                System.out.println("ADDED ITEM DETAILS");

                return marketplaceDetails;
            }
            System.out.println("ITEM REPORT DETAILS PASSED");
            return marketplaceDetails;
        }
        return null;
    }

    //reported company reviews related
    @Override
    public List<Vector> viewReportedReviewListing() {
        Query q = em.createQuery("SELECT i FROM CompanyReviewReport i");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

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
    public Vector viewReviewDetails(String reviewReportID) {
        crrEntity = lookupReportedReview(reviewReportID);
        Vector reviewDetails = new Vector();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

        if (crrEntity != null) {
            reviewDetails.add(crrEntity.getReviewReportID());
            reviewDetails.add(crrEntity.getReviewReportStatus());
            reviewDetails.add(crrEntity.getReviewReportDescription());
            reviewDetails.add(df.format(crrEntity.getReviewReportDate()));
            reviewDetails.add(crrEntity.getReviewID());
            reviewDetails.add(crrEntity.getReviewPosterID());
            reviewDetails.add(crrEntity.getReviewReporterID());
            return reviewDetails;
        }
        return null;
    }

    @Override
    public Vector viewReviewDetails2(String reviewReportID) {
        crrEntity = lookupReportedReview(reviewReportID);
        Vector reviewReportDetails = new Vector();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

        if (crrEntity != null) {
            //from reported review entity    
            reviewReportDetails.add(crrEntity.getReviewReportID());
            reviewReportDetails.add(crrEntity.getReviewReportStatus());
            reviewReportDetails.add(crrEntity.getReviewReportDescription());
            reviewReportDetails.add(df.format(crrEntity.getReviewReportDate()));
            reviewReportDetails.add(crrEntity.getReviewID());
            reviewReportDetails.add(crrEntity.getReviewPosterID());
            reviewReportDetails.add(crrEntity.getReviewReporterID());
            System.out.println("ADDED REVIEW REPORT DETAILS");
            //from review entity
            if (lookupReview(crrEntity.getReviewID()) != null) {
                crEntity = lookupReview(crrEntity.getReviewID());
                reviewReportDetails.add(crEntity.getReviewCompanyName());
                reviewReportDetails.add(crEntity.getReviewTitle());
                reviewReportDetails.add(crEntity.getReviewPros());
                reviewReportDetails.add(crEntity.getReviewCons());
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
    public boolean resolveReview(String reportID) {

        boolean success = true;

        crrEntity = lookupReportedReview(reportID);
        crrEntity.setReviewReportStatus("Resolved");
        em.merge(crrEntity);
        return success;
    }

    @Override
    public boolean unresolveReview(String reportID) {

        boolean success = true;

        crrEntity = lookupReportedReview(reportID);
        crrEntity.setReviewReportStatus("Unresolved");
        em.merge(crrEntity);
        return success;
    }

    @Override
    public boolean deleteReview(String reviewID) {
        boolean reviewDeleteStatus = false;

        double reviewIDNum = Double.parseDouble(reviewID);

        try {
            Query q = em.createQuery("SELECT i FROM CompanyReview i WHERE i.reviewID = :reviewID");
            q.setParameter("reviewID", reviewIDNum);

            crEntity = (CompanyReviewEntity) q.getSingleResult();

            em.remove(crEntity);
            em.flush();
            em.clear();
            return reviewDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Review to be deleted cannot be found. " + enfe.getMessage());
            em.remove(crEntity);
            return reviewDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Review to be deleted does not exist. " + nre.getMessage());
            em.remove(crEntity);
            return reviewDeleteStatus;
        }

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
    public boolean deleteJob(String jobID) {
        boolean jobDeleteStatus = false;

        double jobIDNum = Double.parseDouble(jobID);

        try {
            Query q = em.createQuery("SELECT i FROM Job i WHERE i.jobID = :jobID");
            q.setParameter("jobID", jobIDNum);

            jEntity = (JobEntity) q.getSingleResult();

            em.remove(jEntity);
            em.flush();
            em.clear();
            return jobDeleteStatus = true;
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Job to be deleted cannot be found. " + enfe.getMessage());
            em.remove(jEntity);
            return jobDeleteStatus;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Job to be deleted does not exist. " + nre.getMessage());
            em.remove(jEntity);
            return jobDeleteStatus;
        }

    }

    @Override
    public boolean resolveErrand(String reportID) {

        boolean success = true;

        jrEntity = lookupReportedErrand(reportID);
        jrEntity.setJobReportStatus("Resolved");
        em.merge(jrEntity);
        return success;
    }

    @Override
    public boolean unresolveErrand(String reportID) {

        boolean success = true;

        jrEntity = lookupReportedErrand(reportID);
        jrEntity.setJobReportStatus("Unresolved");
        em.merge(jrEntity);
        return success;
    }

    @Override
    public List<Vector> viewReportedErrandsListing() {
        Query q = em.createQuery("SELECT i FROM JobReport i");
        List<Vector> reportedList = new ArrayList<Vector>();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

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
    public Vector viewErrandDetails(String errandReportID) {
        jrEntity = lookupReportedErrand(errandReportID);
        Vector errandDetails = new Vector();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

        if (jrEntity != null) {
            errandDetails.add(jrEntity.getJobReportID());
            errandDetails.add(jrEntity.getJobReportStatus());
            errandDetails.add(jrEntity.getJobReportDescription());
            errandDetails.add(df.format(jrEntity.getJobReportDate()));
            errandDetails.add(jrEntity.getJobID());
            errandDetails.add(jrEntity.getJobPosterID());
            errandDetails.add(jrEntity.getJobReporterID());
            return errandDetails;
        }
        return null;
    }

    @Override
    public Vector viewErrandDetails2(String errandReportID) {
        jrEntity = lookupReportedErrand(errandReportID);
        Vector errandDetails = new Vector();

        DateFormat df = new SimpleDateFormat("d MMMM yyyy");

        if (jrEntity != null) {
            //from reported errand entity    
            errandDetails.add(jrEntity.getJobReportID());
            errandDetails.add(jrEntity.getJobReportStatus());
            errandDetails.add(jrEntity.getJobReportDescription());
            errandDetails.add(df.format(jrEntity.getJobReportDate()));
            errandDetails.add(jrEntity.getJobID());
            errandDetails.add(jrEntity.getJobPosterID());
            errandDetails.add(jrEntity.getJobReporterID());
            System.out.println("ADDED ERRAND REPORT DETAILS");
            //from job entity
            if (lookupJob(jrEntity.getJobID()) != null) {
                jEntity = lookupJob(jrEntity.getJobID());
                errandDetails.add(jEntity.getJobTitle());
                errandDetails.add(jEntity.getJobDescription());
                errandDetails.add(jEntity.getJobImage());
                System.out.println("ADDED ERRAND DETAILS");

                return errandDetails;
            }
            return errandDetails;
        }
        return null;
    }

    //tags related
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

    @Override
    public boolean updateTag(String tagID, String tagName, String tagType) {

        boolean success = true;

        tEntity = lookupTag(tagID);
        tEntity.setTagName(tagName);
        tEntity.setTagType(tagType);
        em.merge(tEntity);
        return success;

    }

    @Override
    public boolean deleteTag(String tagID) {
        boolean tagDeleteStatus = true;

        double tagIDNum = Double.parseDouble(tagID);

        Query q = em.createQuery("SELECT i FROM Tag i WHERE i.tagID = :tagID");
        q.setParameter("tagID", tagIDNum);

        tEntity = (TagEntity) q.getSingleResult();

        em.remove(tEntity);
        em.flush();
        em.clear();
        return tagDeleteStatus;
    }

    @Override
    public boolean createTag(String tagName, String tagType) {

        tEntity = new TagEntity();
        tEntity.createTag(tagName, tagType);
        em.persist(tEntity);
        return true;
    }

    @Override
    public Vector viewTagDetails2(String tagID) {
        tEntity = lookupTag(tagID);
        Vector tagDetails = new Vector();

        if (tEntity != null) {
            tagDetails.add(tEntity.getTagID());
            tagDetails.add(tEntity.getTagName());
            tagDetails.add(tEntity.getTagType());

            return tagDetails;
        }
        return null;
    }

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
    public boolean empLogin(String userEmail, String userPassword) {
        String hashedPassword = "";
        try {
            hashedPassword = encodePassword(userPassword);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        uEntity = new UserEntity();
        try {
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.userEmail = :userEmail");
            q.setParameter("userEmail", userEmail);
            uEntity = (UserEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(uEntity);
            uEntity = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(uEntity);
            uEntity = null;
        }
        if (uEntity == null) {
            return false;
        }
        // if(uEntity.getUserPassword().equals(hashedPassword)) { return true; }
        if (uEntity.getUserPassword().equals(userPassword)) {
            return true;
        }
        return false;
    }

    public UserEntity lookupUser(String userEmail) {
        UserEntity ue = new UserEntity();
        try {
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.userEmail = :userEmail");
            q.setParameter("userEmail", userEmail);
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

    public String encodePassword(String password) throws NoSuchAlgorithmException {
        String hashedValue = "";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            hashedValue = sb.toString();
        }
        return hashedValue;
    }
}
