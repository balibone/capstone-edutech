/***************************************************************************************
*   Title:                  UserProfileSysUserMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY DASHBOARD & PROFILE - SYSUSER (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.systemuser;

import commoninfrastructureentities.UserEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import unifyentities.common.LikeListingEntity;

import unifyentities.marketplace.ItemEntity;
import unifyentities.marketplace.ItemTransactionEntity;

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private ItemEntity iEntity;
    private ItemTransactionEntity itEntity;
    
    /* USER ACCOUNT */
    @Override
    public List<Vector> viewItemTransaction(String username) {
        Query q = em.createQuery("SELECT t FROM ItemTransaction t WHERE t.userEntity.username = :username");
        q.setParameter("username", username);
        List<Vector> itemTransList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ItemTransactionEntity itemTransE = (ItemTransactionEntity) o;
            Vector itemTransVec = new Vector();

            /* ITEM SELLER IS THE PERSON WHO CREATED THE ITEM TRANSACTION */
            itemTransVec.add(itemTransE.getItemEntity().getItemID());
            itemTransVec.add(itemTransE.getItemTransactionID());
            itemTransVec.add(df.format(itemTransE.getItemTransactionDate()));
            itemTransVec.add(itemTransE.getUserEntity().getUsername());
            itemTransVec.add(itemTransE.getItemBuyerID());
            itemTransVec.add(itemTransE.getItemEntity().getItemImage());
            itemTransVec.add(itemTransE.getItemEntity().getItemName());
            itemTransVec.add(String.format ("%,.2f", itemTransE.getItemEntity().getItemPrice()));
            itemTransVec.add(String.format ("%,.2f", itemTransE.getItemTransactionPrice()));
            itemTransList.add(itemTransVec);
        }
        return itemTransList;
    }
    
    @Override
    public Vector viewTransactionItemDetails(long itemID, long itemTransID, String username) {
        iEntity = lookupItem(itemID);
        itEntity = lookupItemTransaction(itemTransID);
        Vector transactionItemDetailsVec = new Vector();
        
        if (iEntity != null) {
            /* ITEM INFORMATION */
            transactionItemDetailsVec.add(iEntity.getItemID());
            transactionItemDetailsVec.add(iEntity.getItemName());
            transactionItemDetailsVec.add(iEntity.getCategoryEntity().getCategoryName());
            transactionItemDetailsVec.add(String.format ("%,.2f", iEntity.getItemPrice()));
            transactionItemDetailsVec.add(iEntity.getItemCondition());
            transactionItemDetailsVec.add(iEntity.getItemDescription());
            transactionItemDetailsVec.add(iEntity.getItemImage());
            transactionItemDetailsVec.add(iEntity.getItemStatus());
            transactionItemDetailsVec.add(getItemLikeCount(itemID));
            if(lookupLike(itemID, username) == null) { transactionItemDetailsVec.add(false);}
            else { transactionItemDetailsVec.add(true); }
            transactionItemDetailsVec.add(df.format(iEntity.getItemPostingDate()));
            /* TRADE INFORMATION */
            transactionItemDetailsVec.add(iEntity.getTradeLocation());
            transactionItemDetailsVec.add(iEntity.getTradeLat());
            transactionItemDetailsVec.add(iEntity.getTradeLong());
            transactionItemDetailsVec.add(iEntity.getTradeInformation());
            /* ITEM SELLER INFORMATION */
            transactionItemDetailsVec.add(iEntity.getUserEntity().getUsername());
            transactionItemDetailsVec.add(iEntity.getUserEntity().getImgFileName());
            transactionItemDetailsVec.add(df.format(iEntity.getUserEntity().getUserCreationDate()));
            transactionItemDetailsVec.add(getPositiveItemReviewCount(iEntity.getUserEntity().getUsername()));
            transactionItemDetailsVec.add(getNeutralItemReviewCount(iEntity.getUserEntity().getUsername()));
            transactionItemDetailsVec.add(getNegativeItemReviewCount(iEntity.getUserEntity().getUsername()));
            /* ITEM TRANSACTION + ITEM BUYER INFORMATION */
            transactionItemDetailsVec.add(df.format(itEntity.getItemTransactionDate()));
            transactionItemDetailsVec.add(itEntity.getItemBuyerID());
            transactionItemDetailsVec.add(lookupSystemUser(itEntity.getItemBuyerID()).getImgFileName());
            transactionItemDetailsVec.add(df.format(lookupSystemUser(itEntity.getItemBuyerID()).getUserCreationDate()));
            transactionItemDetailsVec.add(getPositiveItemReviewCount(lookupSystemUser(itEntity.getItemBuyerID()).getUsername()));
            transactionItemDetailsVec.add(getNeutralItemReviewCount(lookupSystemUser(itEntity.getItemBuyerID()).getUsername()));
            transactionItemDetailsVec.add(getNegativeItemReviewCount(lookupSystemUser(itEntity.getItemBuyerID()).getUsername()));
            transactionItemDetailsVec.add(String.format ("%,.2f", itEntity.getItemTransactionPrice()));
            
            return transactionItemDetailsVec;
        }
        return null;
    }
    
    @Override
    public List<Vector> viewItemOfferList(String username) {
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.userEntity.username = :username");
        q.setParameter("username", username);
        List<Vector> itemOfferList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector itemOfferVec = new Vector();

            itemOfferVec.add(itemE.getItemID());
            itemOfferVec.add(itemE.getItemImage());
            itemOfferVec.add(itemE.getItemName());
            itemOfferVec.add(String.format ("%,.2f", itemE.getItemPrice()));
            itemOfferVec.add(getPendingItemOfferCount(itemE.getItemID()));
            itemOfferVec.add(getRejectedItemOfferCount(itemE.getItemID()));
            itemOfferList.add(itemOfferVec);
        }
        return itemOfferList;
    }
    
    /* USER PROFILE */
    @Override
    public List<Vector> viewUserItemListing(String username) {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.userEntity.username = :username AND i.categoryEntity.categoryActiveStatus = '1'");
        q.setParameter("username", username);
        List<Vector> userItemList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector userItemVec = new Vector();

            userItemVec.add(itemE.getItemID());
            userItemVec.add(itemE.getItemImage());
            userItemVec.add(itemE.getItemName());
            userItemVec.add(itemE.getCategoryEntity().getCategoryName());
            userItemVec.add(itemE.getUserEntity().getUsername());

            long diff = currentDate.getTime() - itemE.getItemPostingDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }
            userItemVec.add(dateString);
            userItemVec.add(String.format ("%,.2f", itemE.getItemPrice()));
            userItemVec.add(itemE.getItemNumOfLikes());
            userItemList.add(userItemVec);
            dateString = "";
        }
        return userItemList;
    }
    
    /* MISCELLANEOUS METHODS */
    public ItemEntity lookupItem(long itemID) {
        ItemEntity ie = new ItemEntity();
        try {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemID = :itemID");
            q.setParameter("itemID", itemID);
            ie = (ItemEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item cannot be found. " + enfe.getMessage());
            em.remove(ie);
            ie = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item does not exist. " + nre.getMessage());
            em.remove(ie);
            ie = null;
        }
        return ie;
    }
    
    public ItemTransactionEntity lookupItemTransaction(long itemTransID) {
        ItemTransactionEntity ite = new ItemTransactionEntity();
        try {
            Query q = em.createQuery("SELECT t FROM ItemTransaction t WHERE t.itemTransactionID = :itemTransID");
            q.setParameter("itemTransID", itemTransID);
            ite = (ItemTransactionEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item Transaction cannot be found. " + enfe.getMessage());
            em.remove(ite);
            ite = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item Transaction does not exist. " + nre.getMessage());
            em.remove(ite);
            ite = null;
        }
        return ite;
    }
    
    public LikeListingEntity lookupLike(long itemID, String username) {
        LikeListingEntity lle = new LikeListingEntity();
        try {
            Query q = em.createQuery("SELECT l FROM LikeListing l WHERE l.itemEntity.itemID = :itemID AND l.userEntity.username = :username");
            q.setParameter("itemID", itemID);
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
    
    /* MISCELLANEOUS METHODS (ITEM OFFER) */
    public Long getPendingItemOfferCount(long itemID) {
        Long pendingItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.itemOfferStatus = 'Pending'");
        q.setParameter("itemID", itemID);
        try {
            pendingItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getPendingItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return pendingItemOfferCount;
    }
    
    public Long getRejectedItemOfferCount(long itemID) {
        Long rejectedItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.itemOfferStatus = 'Pending'");
        q.setParameter("itemID", itemID);
        try {
            rejectedItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getRejectedItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return rejectedItemOfferCount;
    }
    
    /* MISCELLANEOUS METHODS (ITEM LIKE) */
    public Long getItemLikeCount(long itemID) {
        Long likeCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.likeID) FROM LikeListing l WHERE l.itemEntity.itemID = :itemID");
        q.setParameter("itemID", itemID);
        try {
            likeCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in MarketplaceSysUserMgrBean.getItemLikeCount().getSingleResult()");
            ex.printStackTrace();
        }
        return likeCount;
    }
    
    /* MISCELLANEOUS METHODS (PROFILE RATING) */
    public Long getPositiveItemReviewCount(String username) {
        Long positiveItemReviewCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(r.itemReviewID) FROM ItemReview r WHERE r.itemReceiverID = :username AND r.itemReviewRating = 'Positive'");
        q.setParameter("username", username);
        try {
            positiveItemReviewCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getPositiveItemReviewCount().getSingleResult()");
            ex.printStackTrace();
        }
        return positiveItemReviewCount;
    }
    
    public Long getNeutralItemReviewCount(String username) {
        Long neutralItemReviewCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(r.itemReviewID) FROM ItemReview r WHERE r.itemReceiverID = :username AND r.itemReviewRating = 'Neutral'");
        q.setParameter("username", username);
        try {
            neutralItemReviewCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getNeutralItemReviewCount().getSingleResult()");
            ex.printStackTrace();
        }
        return neutralItemReviewCount;
    }
    
    public Long getNegativeItemReviewCount(String username) {
        Long positiveItemReviewCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(r.itemReviewID) FROM ItemReview r WHERE r.itemReceiverID = :username AND r.itemReviewRating = 'Negative'");
        q.setParameter("username", username);
        try {
            positiveItemReviewCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getNegativeItemReviewCount().getSingleResult()");
            ex.printStackTrace();
        }
        return positiveItemReviewCount;
    }
}