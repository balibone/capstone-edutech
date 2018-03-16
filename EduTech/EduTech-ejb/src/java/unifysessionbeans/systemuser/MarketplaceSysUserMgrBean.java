/***************************************************************************************
*   Title:                  MarketplaceSysUserMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY MARKETPLACE - SYSUSER (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.systemuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import unifyentities.common.CategoryEntity;
import unifyentities.marketplace.ItemEntity;
import unifyentities.marketplace.ItemOfferEntity;
import unifyentities.common.ItemReportEntity;
import unifyentities.common.LikeListingEntity;
import unifyentities.common.MessageEntity;
import commoninfrastructureentities.UserEntity;
import unifyentities.marketplace.ItemTransactionEntity;

@Stateless
public class MarketplaceSysUserMgrBean implements MarketplaceSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;

    private CategoryEntity cEntity;
    private ItemEntity iEntity;
    private ItemReportEntity irEntity;
    private ItemOfferEntity ioEntity;
    private LikeListingEntity llEntity;
    private MessageEntity mEntity;
    private UserEntity uEntity;
    private UserEntity itemBuyerOfferEntity;
    private UserEntity itemSellerEntity;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public List<Vector> viewItemList(String username) {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemStatus = 'Available' AND "
                + "i.categoryEntity.categoryActiveStatus = '1'");
        List<Vector> itemList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector itemVec = new Vector();

            itemVec.add(itemE.getItemID());
            itemVec.add(itemE.getItemImage());
            itemVec.add(itemE.getItemName());
            itemVec.add(itemE.getCategoryEntity().getCategoryName());
            itemVec.add(itemE.getUserEntity().getUsername());
            itemVec.add(itemE.getUserEntity().getImgFileName());

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
            itemVec.add(dateString);
            itemVec.add(df.format(itemE.getItemPostingDate()));
            itemVec.add(String.format ("%,.2f", itemE.getItemPrice()));
            itemVec.add(getItemLikeCount(itemE.getItemID()));
            if(lookupLike(itemE.getItemID(), username) == null) { itemVec.add(false);}
            else { itemVec.add(true); }
            itemVec.add(itemE.getItemCondition());
            itemList.add(itemVec);
            dateString = "";
        }
        return itemList;
    }

    /* FOR EDIT ITEM LISTING */ 
    @Override
    public Vector viewItemDetails(long itemID) {
        iEntity = lookupItem(itemID);
        Vector itemDetailsVec = new Vector();

        if (iEntity != null) {
            itemDetailsVec.add(iEntity.getItemID());
            itemDetailsVec.add(iEntity.getItemName());
            itemDetailsVec.add(iEntity.getCategoryEntity().getCategoryName());
            itemDetailsVec.add(String.format ("%,.2f", iEntity.getItemPrice()));
            itemDetailsVec.add(iEntity.getItemCondition());
            itemDetailsVec.add(iEntity.getItemDescription());
            itemDetailsVec.add(iEntity.getItemImage());
            itemDetailsVec.add(iEntity.getItemStatus());
            itemDetailsVec.add(getItemLikeCount(itemID));
            itemDetailsVec.add(df.format(iEntity.getItemPostingDate()));
            itemDetailsVec.add(iEntity.getTradeLocation());
            itemDetailsVec.add(iEntity.getTradeLat());
            itemDetailsVec.add(iEntity.getTradeLong());
            itemDetailsVec.add(iEntity.getTradeInformation());
            itemDetailsVec.add(iEntity.getUserEntity().getUsername());
            itemDetailsVec.add(iEntity.getUserEntity().getImgFileName());
            itemDetailsVec.add(df.format(iEntity.getUserEntity().getUserCreationDate()));
            itemDetailsVec.add(iEntity.getCategoryEntity().getCategoryID());
        }
        return itemDetailsVec;
    }
    
    @Override
    public Vector viewItemDetails(long itemID, String username) {
        iEntity = lookupItem(itemID);
        Vector itemDetailsVec = new Vector();

        if (iEntity != null) {
            itemDetailsVec.add(iEntity.getItemID());
            itemDetailsVec.add(iEntity.getItemName());
            itemDetailsVec.add(iEntity.getCategoryEntity().getCategoryName());
            itemDetailsVec.add(String.format ("%,.2f", iEntity.getItemPrice()));
            itemDetailsVec.add(iEntity.getItemCondition());
            itemDetailsVec.add(iEntity.getItemDescription());
            itemDetailsVec.add(iEntity.getItemImage());
            itemDetailsVec.add(iEntity.getItemStatus());
            itemDetailsVec.add(getItemLikeCount(itemID));
            if(lookupLike(itemID, username) == null) { itemDetailsVec.add(false);}
            else { itemDetailsVec.add(true); }
            itemDetailsVec.add(df.format(iEntity.getItemPostingDate()));
            itemDetailsVec.add(iEntity.getTradeLocation());
            itemDetailsVec.add(iEntity.getTradeLat());
            itemDetailsVec.add(iEntity.getTradeLong());
            itemDetailsVec.add(iEntity.getTradeInformation());
            itemDetailsVec.add(iEntity.getUserEntity().getUsername());
            itemDetailsVec.add(iEntity.getUserEntity().getImgFileName());
            itemDetailsVec.add(df.format(iEntity.getUserEntity().getUserCreationDate()));
            itemDetailsVec.add(getPositiveItemReviewCount(iEntity.getUserEntity().getUsername()));
            itemDetailsVec.add(getNeutralItemReviewCount(iEntity.getUserEntity().getUsername()));
            itemDetailsVec.add(getNegativeItemReviewCount(iEntity.getUserEntity().getUsername()));
        }
        return itemDetailsVec;
    }

    @Override
    public List<Vector> viewAssocCategoryItemList(String hiddenCategoryName, long hiddenItemID) {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemStatus = 'Available' AND "
                + "i.categoryEntity.categoryActiveStatus = '1' AND "
                + "i.categoryEntity.categoryName = :hiddenCategoryName");
        q.setParameter("hiddenCategoryName", hiddenCategoryName);
        List<Vector> assocCategoryItemList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector assocCategoryItemVec = new Vector();

            if (itemE.getItemID() != hiddenItemID) {
                assocCategoryItemVec.add(itemE.getItemID());
                assocCategoryItemVec.add(itemE.getItemImage());
                assocCategoryItemVec.add(itemE.getItemName());
                assocCategoryItemVec.add(itemE.getCategoryEntity().getCategoryName());
                assocCategoryItemVec.add(itemE.getUserEntity().getUsername());

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
                assocCategoryItemVec.add(dateString);
                assocCategoryItemVec.add(String.format ("%,.2f", itemE.getItemPrice()));
                assocCategoryItemVec.add(getItemLikeCount(itemE.getItemID()));
                assocCategoryItemList.add(assocCategoryItemVec);
                dateString = "";
            }
        }
        return assocCategoryItemList;
    }
    
    @Override
    public String createItemListing(String itemName, double itemPrice, String itemCondition, 
            String itemDescription, String itemImagefileName, long categoryID, String username, 
            String tradeLocation, String tradeLat, String tradeLong, String tradeInformation) {
        if (lookupUnifyUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupCategory(categoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            uEntity = lookupUnifyUser(username);
            cEntity = lookupCategory(categoryID);
            iEntity = new ItemEntity();
            
            if(iEntity.createItemListing(itemName, itemPrice, itemCondition, itemDescription, itemImagefileName, 
                    tradeLocation, tradeLat, tradeLong, tradeInformation)) {
                iEntity.setCategoryEntity(cEntity);
                iEntity.setUserEntity(uEntity);
                em.persist(iEntity);
                return "Item has been listed successfully!";
            } else {
                return "Error occured while listing the item. Please try again.";
            }
        }
    }
    
    @Override
    public String editItemListing(long itemID, String itemName, double itemPrice, String itemCondition, 
            String itemDescription, String itemImageFileName, long itemCategoryID, String username, 
            String tradeLocation, String tradeLat, String tradeLong, String tradeInformation) {
        if (lookupUnifyUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupItem(itemID) == null) { return "There are some issues with your item listing. Please try again."; }
        else if (lookupCategory(itemCategoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            uEntity = lookupUnifyUser(username);
            iEntity = lookupItem(itemID);
            cEntity = lookupCategory(itemCategoryID);
            
            iEntity.setItemName(itemName);
            iEntity.setItemPrice(itemPrice);
            iEntity.setItemCondition(itemCondition);
            iEntity.setItemDescription(itemDescription);
            iEntity.setItemImage(itemImageFileName);
            iEntity.setCategoryEntity(cEntity);
            iEntity.setUserEntity(uEntity);
            iEntity.setTradeLocation(tradeLocation);
            iEntity.setTradeLat(tradeLat);
            iEntity.setTradeLong(tradeLong);
            iEntity.setTradeInformation(tradeInformation);
            em.merge(iEntity);
            return "Item listing has been updated successfully!";
        }
    }
    
    @Override
    public String deleteItemListing(long itemIDToDelete) {
        if (lookupItem(itemIDToDelete) == null) { return "There are some issues with your item listing. Please try again."; }
        else {
            iEntity = lookupItem(itemIDToDelete);
            CategoryEntity categoryE = iEntity.getCategoryEntity();
            UserEntity userE = iEntity.getUserEntity();
            
            categoryE.getItemSet().remove(iEntity);
            userE.getItemSet().remove(iEntity);
            em.merge(categoryE);
            em.merge(userE);
            
            em.remove(iEntity);
            em.flush();
            em.clear();
            return "Item listing has been deleted successfully!";
        }
    }
    
    @Override
    public String sendItemOfferPrice(long itemID, String username, String itemOfferPrice, String itemOfferDescription) {
        if (lookupItem(itemID) == null) { return "There are some issues with the item listing. Please try again."; }
        else if(lookupUnifyUser(username) == null) { return "There are some issues with your user profile. Please try again."; }
        else if(lookupItemOffer(itemID, username) != null) { return "You have sent an offer previously. Please go to your profile to check or update your offer."; }
        else if(itemOfferPrice.equals("")) { return "Item offer price cannot be empty."; }
        else if(!isNumeric(itemOfferPrice)) { return "Please enter a valid item offer price."; }
        else if(Double.parseDouble(itemOfferPrice) < 0.0 || Double.parseDouble(itemOfferPrice) > 9999.0) { return "Item offer price must be between 0 to 9999. Please try again."; }
        else {
            iEntity = lookupItem(itemID);
            itemBuyerOfferEntity = lookupUnifyUser(username);
            itemSellerEntity = lookupUnifyUser(lookupItem(itemID).getUserEntity().getUsername());
            ioEntity = new ItemOfferEntity();
            
            if(ioEntity.createItemOffer(Double.parseDouble(itemOfferPrice), itemOfferDescription)) {
                ioEntity.setItemEntity(iEntity);
                ioEntity.setUserEntity(itemBuyerOfferEntity);
                iEntity.getItemOfferSet().add(ioEntity);
                itemBuyerOfferEntity.getItemOfferSet().add(ioEntity);
                itemSellerEntity.getItemOfferSet().add(ioEntity);
                
                /* MESSAGE SENDER IS THE ITEM BUYER WHO SENT THE OFFER, MESSAGE RECEIVER IS THE ITEM SELLER */
                mEntity = new MessageEntity();
                mEntity.createContentMessage(itemBuyerOfferEntity.getUsername(), iEntity.getUserEntity().getUsername(), 
                        itemBuyerOfferEntity.getUsername() + " sent an offer for your " + iEntity.getItemName() + ". Check it out!", 
                        iEntity.getItemID(), "Marketplace (Item Offer)");
                /* ITEM BUYER WHO SENT THE OFFER IS THE USERENTITY_USERNAME */
                mEntity.setUserEntity(itemBuyerOfferEntity);
                itemSellerEntity.getMessageSet().add(mEntity);
                
                em.persist(ioEntity);
                em.persist(mEntity);
                em.merge(iEntity);
                em.merge(itemBuyerOfferEntity);
                em.merge(itemSellerEntity);
                return "Your item offer has been sent successfully!";
            } else {
                return "Error occured while sending your item offer. Please try again.";
            }
        }
    }
    
    @Override
    public String reportItemListing(long itemID, String username, String itemReportCategory, String itemReportDescription) {
        if (lookupItem(itemID) == null) { return "There are some issues with the item listing. Please try again."; }
        else if(lookupUnifyUser(username) == null) { return "There are some issues with your user profile. Please try again."; }
        else if(lookupPendingItemReport(itemID, username) != null) { return "You have sent a report about this item previously. The administrators are looking into this."; }
        else if(itemReportCategory.equals("") || itemReportDescription.equals("")) { return "Report Category and Report Description cannot be empty."; }
        else {
            iEntity = lookupItem(itemID);
            uEntity = lookupUnifyUser(username);
            irEntity = new ItemReportEntity();
            
            if(irEntity.createItemReport(itemReportCategory, itemReportDescription, iEntity.getUserEntity().getUsername())) {
                irEntity.setItemEntity(iEntity);
                irEntity.setUserEntity(uEntity);
                iEntity.getItemReportSet().add(irEntity);
                uEntity.getItemReportSet().add(irEntity);
                
                em.persist(irEntity);
                em.merge(iEntity);
                em.merge(uEntity);
                return "Your item report has been sent. We will review your item report. Thank you!";
            } else {
                return "Error occured while sending your item report. Please try again.";
            }
        }
    }
    
    @Override
    public String likeUnlikeItem(long itemID, String username) {
        if (lookupItem(itemID) == null) { return "0"; }
        else if(lookupUnifyUser(username) == null) { return "0"; }
        else {
            iEntity = lookupItem(itemID);
            uEntity = lookupUnifyUser(username);
            if (lookupLike(itemID, username) == null) {
                llEntity = new LikeListingEntity();
                if(llEntity.addNewLike("Marketplace")) {
                    llEntity.setItemEntity(iEntity);
                    llEntity.setUserEntity(uEntity);
                    iEntity.getLikeListingSet().add(llEntity);
                    uEntity.getLikeListingSet().add(llEntity);
                    
                    /* MESSAGE SENDER IS THE ITEM LIKER, MESSAGE RECEIVER IS THE ITEM SELLER */
                    mEntity = new MessageEntity();
                    mEntity.createContentMessage(uEntity.getUsername(), iEntity.getUserEntity().getUsername(), 
                        uEntity.getUsername() + " likes your " + iEntity.getItemName() + ". Check it out!", 
                        iEntity.getItemID(), "Marketplace (Item Like)");
                    /* ITEM LIKER IS THE USERENTITY_USERNAME */
                    mEntity.setUserEntity(uEntity);
                    (iEntity.getUserEntity()).getMessageSet().add(mEntity);
                    
                    em.persist(llEntity);
                    em.persist(mEntity);
                    em.merge(iEntity);
                    em.merge(uEntity);
                    em.merge(iEntity.getUserEntity());
                }
            } else {
                llEntity = lookupLike(itemID, username);
                iEntity.getLikeListingSet().remove(llEntity);
                uEntity.getLikeListingSet().remove(llEntity);
                
                mEntity = lookupContentMessage(itemID, username);
                (iEntity.getUserEntity()).getMessageSet().remove(mEntity);
                
                em.remove(llEntity);
                em.remove(mEntity);
                em.merge(uEntity);
                em.merge(iEntity.getUserEntity());
                em.flush();
                em.clear();
            }
        }
        return String.valueOf(getItemLikeCount(itemID));
    }
    
    @Override
    public List<Vector> viewItemLikeList(long itemID) {
        Query q = em.createQuery("SELECT ll FROM LikeListing ll WHERE ll.itemEntity.itemID = :itemID");
        q.setParameter("itemID", itemID);
        List<Vector> itemLikeList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            LikeListingEntity likeListingE = (LikeListingEntity) o;
            Vector likeListingVec = new Vector();

            likeListingVec.add(likeListingE.getUserEntity().getUsername());
            likeListingVec.add(likeListingE.getUserEntity().getUserFirstName());
            likeListingVec.add(likeListingE.getUserEntity().getUserLastName());
            likeListingVec.add(getPositiveItemReviewCount(likeListingE.getUserEntity().getUsername()));
            likeListingVec.add(getNeutralItemReviewCount(likeListingE.getUserEntity().getUsername()));
            likeListingVec.add(getNegativeItemReviewCount(likeListingE.getUserEntity().getUsername()));
            itemLikeList.add(likeListingVec);
        }
        return itemLikeList;
    }
    
    @Override
    public List<Vector> viewItemCategoryList(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'Marketplace' "
                + "AND c.categoryActiveStatus = '1'");
        List<Vector> itemCategoryList = new ArrayList<Vector>();
        
        for (Object o: q.getResultList()){
            CategoryEntity categoryE = (CategoryEntity) o;
            Vector itemCategoryVec = new Vector();
            
            itemCategoryVec.add(categoryE.getCategoryImage());
            itemCategoryVec.add(categoryE.getCategoryID());
            itemCategoryVec.add(categoryE.getCategoryName());
            itemCategoryList.add(itemCategoryVec);
        }
        return itemCategoryList;
    }
    
    /*  ====================    USER PROFILE    ==================== */
    @Override
    public List<Vector> viewUserItemList(String username, String itemSellerID) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.userEntity.username = :username AND "
                + "i.categoryEntity.categoryActiveStatus = '1' AND (i.itemStatus = 'Available' OR "
                + "i.itemStatus = 'Reserved' OR i.itemStatus = 'Sold')");
        q.setParameter("username", itemSellerID);
        List<Vector> userItemList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity userItemE = (ItemEntity) o;
            Vector userItemVec = new Vector();

            userItemVec.add(userItemE.getItemID());
            userItemVec.add(userItemE.getItemImage());
            userItemVec.add(userItemE.getItemName());
            userItemVec.add(userItemE.getCategoryEntity().getCategoryName());
            userItemVec.add(userItemE.getUserEntity().getUsername());
            userItemVec.add(userItemE.getUserEntity().getImgFileName());

            long diff = currentDate.getTime() - userItemE.getItemPostingDate().getTime();
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
            userItemVec.add(df.format(userItemE.getItemPostingDate()));
            userItemVec.add(String.format ("%,.2f", userItemE.getItemPrice()));
            userItemVec.add(getItemLikeCount(userItemE.getItemID()));
            if(lookupLike(userItemE.getItemID(), username) == null) { userItemVec.add(false);}
            else { userItemVec.add(true); }
            userItemVec.add(userItemE.getItemCondition());
            userItemList.add(userItemVec);
            dateString = "";
        }
        return userItemList;
    }
    
    /* MISCELLANEOUS METHODS */
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
    
    public CategoryEntity lookupCategory(long categoryID) {
        CategoryEntity ce = new CategoryEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID = :categoryID");
            q.setParameter("categoryID", categoryID);
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
    
    public ItemOfferEntity lookupItemOffer(long itemID, String username) {
        ItemOfferEntity ioe = new ItemOfferEntity();
        try {
            Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.itemEntity.itemID = :itemID AND io.userEntity.username = :username");
            q.setParameter("itemID", itemID);
            q.setParameter("username", username);
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
    
    public ItemReportEntity lookupPendingItemReport(long itemID, String username) {
        ItemReportEntity ire = new ItemReportEntity();
        try {
            Query q = em.createQuery("SELECT ir FROM ItemReport ir WHERE ir.itemEntity.itemID = :itemID "
                    + "AND ir.userEntity.username = :username AND ir.itemReportStatus='Unresolved'");
            q.setParameter("itemID", itemID);
            q.setParameter("username", username);
            ire = (ItemReportEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item report cannot be found. " + enfe.getMessage());
            em.remove(ire);
            ire = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item report does not exist. " + nre.getMessage());
            em.remove(ire);
            ire = null;
        }
        return ire;
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
    
    public MessageEntity lookupContentMessage(long itemID, String username) {
        MessageEntity me = new MessageEntity();
        try {
            Query q = em.createQuery("SELECT m FROM Message m WHERE m.contentID = :itemID AND m.userEntity.username = :username");
            q.setParameter("itemID", itemID);
            q.setParameter("username", username);
            me = (MessageEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Content Message cannot be found. " + enfe.getMessage());
            em.remove(me);
            me = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Content Message does not exist. " + nre.getMessage());
            em.remove(me);
            me = null;
        }
        return me;
    }
    
    public static boolean isNumeric(String strValue) {
        return strValue.matches("-?\\d+(\\.\\d+)?");  // match a number with optional '-' and decimal
    }
    
    @Override
    public String populateItemCategory() {
        String itemCategoryStr = "";
            Query q = em.createQuery("SELECT c from Category c WHERE c.categoryType = 'Marketplace' AND c.categoryActiveStatus = '1'");

        for (Object o : q.getResultList()) {
            CategoryEntity ce = (CategoryEntity) o;
            itemCategoryStr += ce.getCategoryName() + ";";
        }
        if(itemCategoryStr.length() != 0) {
            itemCategoryStr = itemCategoryStr.substring(0, itemCategoryStr.length()-1);
        }
        return itemCategoryStr;
    }
    
    /* MISCELLANEOUS METHODS (ITEM OFFER) */
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
    
    public Long getRejectedItemOfferCount(long itemID) {
        Long rejectedItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.itemOfferStatus = 'Pending'");
        q.setParameter("itemID", itemID);
        try {
            rejectedItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in MarketplaceSysUserMgrBean.getRejectedItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return rejectedItemOfferCount;
    }
    
    /* MISCELLANEOUS METHODS (ITEM LIKE) */
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
    
    /* MISCELLANEOUS METHODS (PROFILE RATING) */
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
}