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

import commoninfrastructureentities.UserEntity;
import unifyentities.common.MessageEntity;
import unifyentities.common.LikeListingEntity;
import unifyentities.marketplace.ItemEntity;
import unifyentities.marketplace.ItemOfferEntity;
import unifyentities.marketplace.ItemTransactionEntity;

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private ItemEntity iEntity;
    private ItemOfferEntity ioEntity;
    private ItemTransactionEntity itEntity;
    private UserEntity uEntity;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public Vector viewUserProfileDetails(String username) {
        uEntity = lookupUnifyUser(username);
        Vector userProfileDetailsVec = new Vector();

        if (uEntity != null) {
            userProfileDetailsVec.add(uEntity.getUsername());
            userProfileDetailsVec.add(uEntity.getUserFirstName());
            userProfileDetailsVec.add(uEntity.getUserLastName());
            userProfileDetailsVec.add(uEntity.getImgFileName());
            userProfileDetailsVec.add(df.format(uEntity.getUserCreationDate()));
        }
        return userProfileDetailsVec;
    }

    @Override
    public List<Vector> viewUserMessageListTopThree(String username) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT m FROM Message m WHERE m.messageReceiverID = :username ORDER BY m.messageDate DESC");
        q.setParameter("username", username);
        List<Vector> messageList = new ArrayList<Vector>();
        
        for (Object o : q.setMaxResults(3).getResultList()) {
            MessageEntity messageE = (MessageEntity) o;
            Vector messageVec = new Vector();
            
            messageVec.add(messageE.getMessageContent());
            messageVec.add(messageE.getContentID());
            messageVec.add(messageE.getMessageType());
            if(messageE.getMessageType().equals("System")) { messageVec.add("System Admin"); }
            else { messageVec.add(messageE.getUserEntity().getUsername()); }
            if(messageE.getMessageType().equals("System")) { messageVec.add("edubox-box.png"); }
            else { messageVec.add(messageE.getUserEntity().getImgFileName()); }
            
            long diff = currentDate.getTime() - messageE.getMessageDate().getTime();
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
            messageVec.add(dateString);
            messageList.add(messageVec);
        }
        return messageList;
    }
    
    @Override
    public List<Vector> viewUserMessageList(String username) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT m FROM Message m WHERE m.messageReceiverID = :username ORDER BY m.messageDate DESC");
        q.setParameter("username", username);
        List<Vector> messageList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            MessageEntity messageE = (MessageEntity) o;
            Vector messageVec = new Vector();
            
            messageVec.add(messageE.getMessageContent());
            messageVec.add(messageE.getContentID());
            messageVec.add(messageE.getMessageType());
            if(messageE.getMessageType().equals("System")) { messageVec.add("System Admin"); }
            else { messageVec.add(messageE.getUserEntity().getUsername()); }
            if(messageE.getMessageType().equals("System")) { messageVec.add("edubox-box.png"); }
            else { messageVec.add(messageE.getUserEntity().getImgFileName()); }
            messageVec.add(df.format(messageE.getMessageDate()));
            
            long diff = currentDate.getTime() - messageE.getMessageDate().getTime();
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
            messageVec.add(dateString);
            messageList.add(messageVec);
        }
        return messageList;
    }
    
    /*  ====================    USER ITEM OFFERS    ==================== */
    @Override
    public List<Vector> viewItemOfferList(String username) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.userEntity.username = :username AND "
                + "i.categoryEntity.categoryActiveStatus = '1' AND (i.itemStatus = 'Available' OR "
                + "i.itemStatus = 'Reserved' OR i.itemStatus = 'Sold')");
        q.setParameter("username", username);
        List<Vector> itemOfferList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector itemOfferVec = new Vector();
            
            itemOfferVec.add(itemE.getItemID());
            itemOfferVec.add(itemE.getItemImage());
            itemOfferVec.add(itemE.getItemName());
            itemOfferVec.add(itemE.getCategoryEntity().getCategoryName());
            itemOfferVec.add(itemE.getUserEntity().getUsername());
            itemOfferVec.add(itemE.getUserEntity().getImgFileName());

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
            itemOfferVec.add(dateString);
            itemOfferVec.add(df.format(itemE.getItemPostingDate()));
            itemOfferVec.add(String.format ("%,.2f", itemE.getItemPrice()));
            itemOfferVec.add(getItemLikeCount(itemE.getItemID()));
            if(lookupLike(itemE.getItemID(), username) == null) { itemOfferVec.add(false);}
            else { itemOfferVec.add(true); }
            itemOfferVec.add(getPendingItemOfferCount(itemE.getItemID()));
            itemOfferVec.add(itemE.getItemCondition());
            itemOfferVec.add(itemE.getItemStatus());
            itemOfferList.add(itemOfferVec);
            dateString = "";
        }
        return itemOfferList;
    }
    
    @Override
    public List<Vector> viewItemOfferUserList(String username, long urlitemID) {
        boolean itemInfoEntry = false;
        Date currentDate = new Date();
        String dateString = "";
        List<Vector> itemOfferUserList = new ArrayList<Vector>();

        Query q = em.createQuery("SELECT o FROM ItemOffer o WHERE o.itemEntity.userEntity.username = :username "
                + "AND o.itemEntity.itemID = :itemID");
        q.setParameter("username", username);
        q.setParameter("itemID", urlitemID);
        
        for (Object o : q.getResultList()) {
            ItemOfferEntity itemOfferE = (ItemOfferEntity) o;
            Vector itemOfferUserVec = new Vector();
            
            if(itemInfoEntry == false) {
                Vector itemUserVec = new Vector();
                itemUserVec.add(itemOfferE.getItemEntity().getItemID());
                itemUserVec.add(itemOfferE.getItemEntity().getItemName());
                itemUserVec.add(itemOfferE.getItemEntity().getItemImage());
                itemUserVec.add(String.format ("%,.2f", itemOfferE.getItemEntity().getItemPrice()));
                itemUserVec.add(itemOfferE.getItemEntity().getItemCondition());
                itemOfferUserList.add(itemUserVec);
                itemInfoEntry = true;
            }
            itemOfferUserVec.add(itemOfferE.getItemOfferID());
            itemOfferUserVec.add(itemOfferE.getUserEntity().getUsername());
            itemOfferUserVec.add(itemOfferE.getUserEntity().getUserFirstName());
            itemOfferUserVec.add(itemOfferE.getUserEntity().getUserLastName());
            itemOfferUserVec.add(itemOfferE.getUserEntity().getImgFileName());
            itemOfferUserVec.add(getPositiveItemReviewCount(itemOfferE.getUserEntity().getUsername()));
            itemOfferUserVec.add(getNeutralItemReviewCount(itemOfferE.getUserEntity().getUsername()));
            itemOfferUserVec.add(getNegativeItemReviewCount(itemOfferE.getUserEntity().getUsername()));
            itemOfferUserVec.add(String.format ("%,.2f", itemOfferE.getItemOfferPrice()));
            itemOfferUserVec.add(itemOfferE.getItemOfferDescription());
            itemOfferUserVec.add(itemOfferE.getItemOfferStatus());
            
            long diff = currentDate.getTime() - itemOfferE.getItemOfferDate().getTime();
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
            itemOfferUserVec.add(dateString);
            itemOfferUserVec.add(df.format(itemOfferE.getItemOfferDate()));
            itemOfferUserList.add(itemOfferUserVec);
            dateString = "";
        }
        return itemOfferUserList;
    }
    
    @Override
    public String acceptAnItemOffer(long itemOfferID) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            iEntity = ioEntity.getItemEntity();
            
            ioEntity.setItemOfferStatus("Accepted");
            iEntity.setItemStatus("Reserved");
            em.merge(ioEntity);
            em.merge(iEntity);
            
            return "Item offer has been accepted!";
        }
    }
    
    @Override
    public String rejectAnItemOffer(long itemOfferID) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setItemOfferStatus("Rejected");
            em.merge(ioEntity);
            return "Item offer has been rejected!";
        }
    }
    
    @Override
    public List<Vector> viewUserBuyerOfferList(String username) {
        Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.userEntity.username = :username ORDER BY io.itemOfferDate DESC");
        q.setParameter("username", username);
        List<Vector> userBuyerOfferList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ItemOfferEntity userBuyerOfferE = (ItemOfferEntity) o;
            Vector userBuyerOfferVec = new Vector();

            /* ITEM SELLER IS THE PERSON WHO CREATED THE ITEM TRANSACTION */
            userBuyerOfferVec.add(userBuyerOfferE.getItemEntity().getItemImage());
            userBuyerOfferVec.add(userBuyerOfferE.getItemEntity().getItemName());
            userBuyerOfferVec.add(String.format ("%,.2f", userBuyerOfferE.getItemEntity().getItemPrice()));
            userBuyerOfferVec.add(userBuyerOfferE.getItemOfferID());
            userBuyerOfferVec.add(String.format ("%,.2f", userBuyerOfferE.getItemOfferPrice()));
            userBuyerOfferVec.add(userBuyerOfferE.getItemOfferStatus());
            userBuyerOfferVec.add(userBuyerOfferE.getSellerComments());
            userBuyerOfferVec.add(df.format(userBuyerOfferE.getItemOfferDate()));
            userBuyerOfferList.add(userBuyerOfferVec);
        }
        return userBuyerOfferList;
    }
    
    /*  ====================    USER ITEM TRANSACTIONS    ==================== */
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
            transactionItemDetailsVec.add(lookupUnifyUser(itEntity.getItemBuyerID()).getImgFileName());
            transactionItemDetailsVec.add(df.format(lookupUnifyUser(itEntity.getItemBuyerID()).getUserCreationDate()));
            transactionItemDetailsVec.add(getPositiveItemReviewCount(lookupUnifyUser(itEntity.getItemBuyerID()).getUsername()));
            transactionItemDetailsVec.add(getNeutralItemReviewCount(lookupUnifyUser(itEntity.getItemBuyerID()).getUsername()));
            transactionItemDetailsVec.add(getNegativeItemReviewCount(lookupUnifyUser(itEntity.getItemBuyerID()).getUsername()));
            transactionItemDetailsVec.add(String.format ("%,.2f", itEntity.getItemTransactionPrice()));
        }
        return transactionItemDetailsVec;
    }
    
    /*  ====================    MISCELLANEOUS METHODS    ==================== */
    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try{
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }
    
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
    
    /* ---> FOR ACCEPTING AND REJECTING THE ITEM OFFER <--- */
    public ItemOfferEntity lookupItemOffer(long itemOfferID) {
        ItemOfferEntity ioe = new ItemOfferEntity();
        try {
            Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.itemOfferID = :itemOfferID");
            q.setParameter("itemOfferID", itemOfferID);
            ioe = (ItemOfferEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item offer cannot be found. " + enfe.getMessage());
            em.remove(ioe);
            ioe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item offer does not exist. " + nre.getMessage());
            em.remove(ioe);
            ioe = null;
        }
        return ioe;
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
    
    /*  ====================    MISCELLANEOUS METHODS (ITEM LIKE)    ==================== */
    public Long getItemLikeCount(long itemID) {
        Long itemLikeCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.likeID) FROM LikeListing l WHERE l.itemEntity.itemID = :itemID");
        q.setParameter("itemID", itemID);
        try {
            itemLikeCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in MarketplaceSysUserMgrBean.getItemLikeCount().getSingleResult()");
            ex.printStackTrace();
        }
        return itemLikeCount;
    }
    
    /*  ====================    MISCELLANEOUS METHODS (USER PROFILE RATING)    ==================== */
    public Long getPositiveItemReviewCount(String username) {
        Long positiveItemReviewCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(r.itemReviewID) FROM ItemReview r WHERE r.itemReceiverID = :username AND r.itemReviewRating = 'Positive'");
        q.setParameter("username", username);
        try {
            positiveItemReviewCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in MarketplaceSysUserMgrBean.getPositiveItemReviewCount().getSingleResult()");
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
            System.out.println("Exception in MarketplaceSysUserMgrBean.getNeutralItemReviewCount().getSingleResult()");
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
            System.out.println("Exception in MarketplaceSysUserMgrBean.getNegativeItemReviewCount().getSingleResult()");
            ex.printStackTrace();
        }
        return positiveItemReviewCount;
    }
    
    /*  ====================    MISCELLANEOUS METHODS (ITEM OFFER)    ==================== */
    public Long getPendingItemOfferCount(long itemID) {
        Long pendingItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.itemOfferStatus = 'Pending'");
        q.setParameter("itemID", itemID);
        try {
            pendingItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in MarketplaceSysUserMgrBean.getPendingItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return pendingItemOfferCount;
    }
}