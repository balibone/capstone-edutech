/***************************************************************************************
*   Title:                  UserProfileSysUserMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY DASHBOARD & PROFILE - SYSUSER (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
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
import unifyentities.common.ChatEntity;
import unifyentities.common.LikeListingEntity;
import unifyentities.common.MessageEntity;
import unifyentities.marketplace.ItemEntity;
import unifyentities.marketplace.ItemOfferEntity;
import unifyentities.marketplace.ItemReviewEntity;
import unifyentities.marketplace.ItemTransactionEntity;

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private ChatEntity chEntity;
    private ItemEntity iEntity;
    private ItemOfferEntity ioEntity;
    private ItemReviewEntity irEntity;
    private ItemTransactionEntity itEntity;
    private MessageEntity mEntity;
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
    public List<Vector> viewUserItemWishlist(String username) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT ll FROM LikeListing ll WHERE ll.userEntity.username = :username AND "
                + "ll.itemEntity.categoryEntity.categoryActiveStatus = '1' AND (ll.itemEntity.itemStatus = 'Available' OR "
                + "ll.itemEntity.itemStatus = 'Reserved' OR ll.itemEntity.itemStatus = 'Sold')");
        q.setParameter("username", username);
        List<Vector> userItemWishlist = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            LikeListingEntity likeListE = (LikeListingEntity) o;
            Vector likeListVec = new Vector();
            
            likeListVec.add(likeListE.getItemEntity().getItemID());
            likeListVec.add(likeListE.getItemEntity().getItemImage());
            likeListVec.add(likeListE.getItemEntity().getItemName());
            likeListVec.add(likeListE.getItemEntity().getCategoryEntity().getCategoryName());
            likeListVec.add(likeListE.getItemEntity().getUserEntity().getUsername());
            likeListVec.add(likeListE.getItemEntity().getUserEntity().getImgFileName());

            long diff = currentDate.getTime() - likeListE.getItemEntity().getItemPostingDate().getTime();
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
            if(!dateString.equals("")) {
                likeListVec.add(dateString);
            } else {
                likeListVec.add("Just Now");
            }
            likeListVec.add(df.format(likeListE.getItemEntity().getItemPostingDate()));
            likeListVec.add(String.format ("%,.2f", likeListE.getItemEntity().getItemPrice()));
            likeListVec.add(getItemLikeCount(likeListE.getItemEntity().getItemID()));
            if(lookupLike(likeListE.getItemEntity().getItemID(), username) == null) { likeListVec.add(false);}
            else { likeListVec.add(true); }
            likeListVec.add(likeListE.getItemEntity().getItemCondition());
            likeListVec.add(likeListE.getItemEntity().getItemStatus());
            userItemWishlist.add(likeListVec);
            dateString = "";
        }
        return userItemWishlist;
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
            messageVec.add(messageE.getMessageStatus());
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
            messageVec.add(messageE.getMessageStatus());
            messageList.add(messageVec);
        }
        return messageList;
    }
    
    /* MARKETPLACE CHAT (WITHOUT CHAT HISTORY) -- BUYING TAB */
    @Override
    public List<Vector> viewUserChatBuyingList(String username, String itemID, boolean checkEmptyContent) {
        ArrayList<String> nameItemHolderArrList = new ArrayList<>();
        List<Vector> userChatList = new ArrayList<Vector>();
        
        /* DELETE VIA ONE SIDE CALL ONLY */
        if(checkEmptyContent == true) {
            Query q = em.createQuery("SELECT c FROM Chat c WHERE c.chatContent = '' OR c.chatContent IS NULL");
            for (Object o : q.getResultList()) {
                ChatEntity chatE = (ChatEntity) o;
                uEntity = chatE.getUserEntity();
                iEntity = chatE.getItemEntity();

                em.merge(chatE);
                em.remove(chatE);
                uEntity.getChatSet().remove(chatE);
                iEntity.getChatSet().remove(chatE);

                em.merge(uEntity);
                em.merge(iEntity);
            }
            em.flush();
            em.clear();
        }
        
        if(!itemID.equals("")) {
            if(lookupEmptyChat(username) == null) {
                iEntity = lookupItem(Long.parseLong(itemID));
                ChatEntity newChatE = new ChatEntity();
                if((iEntity.getUserEntity().getUsername()).equals(username)) {
                    newChatE.createChat(iEntity.getUserEntity().getUsername(), "", username, "");
                } else if(!(iEntity.getUserEntity().getUsername()).equals(username)) {
                    newChatE.createChat(iEntity.getUserEntity().getUsername(), username, "", "");
                }
                newChatE.setItemEntity(iEntity);
                newChatE.setUserEntity(lookupUnifyUser(username));
                em.persist(newChatE);
            }
            Query searchQ = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemBuyerID = :username AND c.itemEntity.itemID = :itemID ORDER BY c.chatPostingDate DESC");
            searchQ.setParameter("username", username);
            searchQ.setParameter("itemID", Long.parseLong(itemID));
            for (Object o : searchQ.setMaxResults(1).getResultList()) {
                ChatEntity chatE = (ChatEntity) o;
                Vector userChatVec = new Vector();
                if(!(chatE.getChatReceiverID()).equals(username)) {
                    nameItemHolderArrList.add(chatE.getChatReceiverID() + ";" + chatE.getItemEntity().getItemID());
                    userChatVec.add(lookupUnifyUser(chatE.getChatReceiverID()).getImgFileName());
                    userChatVec.add(lookupUnifyUser(chatE.getChatReceiverID()).getUsername());
                } else {
                    nameItemHolderArrList.add(chatE.getUserEntity().getUsername() + chatE.getItemEntity().getItemID());
                    userChatVec.add(lookupUnifyUser(chatE.getUserEntity().getUsername()).getImgFileName());
                    userChatVec.add(lookupUnifyUser(chatE.getUserEntity().getUsername()).getUsername());
                }
                userChatVec.add(chatE.getItemEntity().getItemName());
                userChatVec.add(chatE.getChatID());
                userChatVec.add(chatE.getChatStatus());
                userChatVec.add(chatE.getChatContent());
                userChatList.add(userChatVec);
            }
        }
        Query viewQ = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemBuyerID = :username ORDER BY c.chatPostingDate DESC");
        viewQ.setParameter("username", username);
        
        for (Object o : viewQ.getResultList()) {
            ChatEntity chatE = (ChatEntity) o;
            Vector userChatVec = new Vector();
            
            if((chatE.getUserEntity().getUsername()).equals(username)) {
                if(!nameItemHolderArrList.contains(chatE.getChatReceiverID() + ";" + chatE.getItemEntity().getItemID())) {
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add("You: " + chatE.getChatContent());
                    userChatList.add(userChatVec);
                    nameItemHolderArrList.add(chatE.getChatReceiverID() + ";" + chatE.getItemEntity().getItemID());
                }
            } else if((chatE.getChatReceiverID()).equals(username)) {
                if(!nameItemHolderArrList.contains(chatE.getUserEntity().getUsername() + ";" + chatE.getItemEntity().getItemID())) {
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    userChatList.add(userChatVec);
                    nameItemHolderArrList.add(chatE.getUserEntity().getUsername() + ";" + chatE.getItemEntity().getItemID());
                }
            }
        }
        return userChatList;
    }
    
    /* MARKETPLACE CHAT (WITHOUT CHAT HISTORY) -- SELLING TAB */
    @Override
    public List<Vector> viewUserChatSellingList(String username) {
        ArrayList<String> nameItemHolderArrList = new ArrayList<>();
        List<Vector> userChatList = new ArrayList<Vector>();
        
        Query q = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemBuyerID <> :username ORDER BY c.chatPostingDate DESC");
        q.setParameter("username", username);
        
        for (Object o : q.getResultList()) {
            ChatEntity chatE = (ChatEntity) o;
            Vector userChatVec = new Vector();
            
            if((chatE.getUserEntity().getUsername()).equals(username)) {
                if(!nameItemHolderArrList.contains(chatE.getChatReceiverID() + ";" + chatE.getItemEntity().getItemID())) {
                    userChatVec.add(lookupUnifyUser(chatE.getChatReceiverID()).getImgFileName());
                    userChatVec.add(chatE.getChatReceiverID());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add("You: " + chatE.getChatContent());
                    userChatList.add(userChatVec);
                    nameItemHolderArrList.add(chatE.getChatReceiverID() + ";" + chatE.getItemEntity().getItemID());
                }
            } else if((chatE.getChatReceiverID()).equals(username)) {
                if(!nameItemHolderArrList.contains(chatE.getUserEntity().getUsername() + ";" + chatE.getItemEntity().getItemID())) {
                    userChatVec.add(chatE.getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    userChatList.add(userChatVec);
                    nameItemHolderArrList.add(chatE.getUserEntity().getUsername() + ";" + chatE.getItemEntity().getItemID());
                }
            }
        }
        return userChatList;
    }
    
    /* MARKETPLACE CHAT (WITH -OR- WITHOUT CHAT HISTORY) -- CHAT HEADER */
    @Override
    public Vector viewChatContentInfo(String username, long chatID) {
        chEntity = lookupChat(chatID);
        uEntity = lookupUnifyUser(username);
        Vector chatContentInfoVec = new Vector();
        
        if(chEntity != null) {
            if(chEntity.getChatReceiverID().equals(username)) {
                chatContentInfoVec.add(chEntity.getUserEntity().getImgFileName());
                chatContentInfoVec.add(chEntity.getUserEntity().getUsername());
            } else {
                chatContentInfoVec.add(lookupUnifyUser(chEntity.getChatReceiverID()).getImgFileName());
                chatContentInfoVec.add(lookupUnifyUser(chEntity.getChatReceiverID()).getUsername());
            }
            chatContentInfoVec.add(uEntity.getImgFileName());
            if(!chEntity.getItemBuyerID().equals(chEntity.getItemEntity().getUserEntity().getUsername())) {
                chatContentInfoVec.add("Item Buyer");
            } else {
                chatContentInfoVec.add("Item Seller");
            }
            chatContentInfoVec.add(chEntity.getItemBuyerID());
            chatContentInfoVec.add(chEntity.getItemEntity().getItemID());
            chatContentInfoVec.add(chEntity.getItemEntity().getItemImage());
            chatContentInfoVec.add(chEntity.getItemEntity().getItemName());
            chatContentInfoVec.add(chEntity.getItemEntity().getItemPrice());
            /* ITEM OFFERID */
            if((lookupBuyerItemOffer(chEntity.getItemEntity().getItemID(), username) == null)) {
                chatContentInfoVec.add("");
            } else {
                chatContentInfoVec.add(lookupBuyerItemOffer(chEntity.getItemEntity().getItemID(), username).getItemOfferID());
            }
            /* ITEM OFFER STATUS */
            if(!username.equals(chEntity.getItemEntity().getUserEntity().getUsername())) {
                /* ITEM BUYER */
                if((lookupBuyerItemOffer(chEntity.getItemEntity().getItemID(), username) == null)) {
                    chatContentInfoVec.add("You have not made an offer on this item yet");
                } else {
                    chatContentInfoVec.add(lookupBuyerItemOffer(chEntity.getItemEntity().getItemID(), username).getBuyerItemOfferStatus());
                }
            } else if(username.equals(chEntity.getItemEntity().getUserEntity().getUsername())) {
                /* ITEM SELLER */
                if((lookupItemOfferSeller(chEntity.getItemEntity().getItemID(), username) == null)) {
                    chatContentInfoVec.add("No Offer Status");
                } else {
                    chatContentInfoVec.add(lookupItemOfferSeller(chEntity.getItemEntity().getItemID(), username).getSellerItemOfferStatus());
                }
            }
            /* CURRENT ITEM OFFER PRICE */
            if((lookupBuyerItemOffer(chEntity.getItemEntity().getItemID(), username) == null)) {
                chatContentInfoVec.add("Current Offer: None");
            } else {
                chatContentInfoVec.add("Current Offer: $" + String.format ("%,.2f", lookupBuyerItemOffer(chEntity.getItemEntity().getItemID(), username).getItemOfferPrice()));
            }
        }
        return chatContentInfoVec;
    }
    
    /* MARKETPLACE CHAT (WITH CHAT HISTORY) */
    @Override
    public List<Vector> viewChatListContent(String username, long chatID) {
        List<Vector> chatListContent = new ArrayList<Vector>();
        chEntity = lookupChat(chatID);
        
        if(chEntity != null) {
            Query q = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemEntity.itemID = :itemID ORDER BY c.chatPostingDate ASC");
            q.setParameter("username", username);
            q.setParameter("itemID", chEntity.getItemEntity().getItemID());
            
            for (Object o : q.getResultList()) {
                ChatEntity chatE = (ChatEntity) o;
                Vector chatContentE = new Vector();

                chatContentE.add(chatE.getChatContent());
                chatContentE.add(chatE.getUserEntity().getUsername());
                chatContentE.add(chatE.getUserEntity().getImgFileName());
                chatContentE.add(chatE.getChatReceiverID());
                chatContentE.add(lookupUnifyUser(chatE.getChatReceiverID()).getImgFileName());
                chatListContent.add(chatContentE);
            }
        }
        return chatListContent;
    }
    
    /* MARKETPLACE CHAT (WITH CHAT HISTORY) -- NEW CHAT CONTENT */
    @Override
    public String addNewChatContent(String senderID, String receiverID, String chatContent, 
            String buyerOrSellerStat, String buyerOrSellerID, long itemID) {
        chEntity = new ChatEntity();
        uEntity = lookupUnifyUser(senderID);
        iEntity = lookupItem(itemID);
        
        if(buyerOrSellerStat.equals("Item Buyer")) {
            chEntity.createChat(receiverID, buyerOrSellerID, "", chatContent);
        } else if(buyerOrSellerStat.equals("Item Seller")) {
            chEntity.createChat(receiverID, "", buyerOrSellerID, chatContent);
        }
        chEntity.setUserEntity(uEntity);
        chEntity.setItemEntity(iEntity);
        
        em.merge(uEntity);
        em.merge(iEntity);
        em.persist(chEntity);
        return "Message has been sent successfully!";
    }
    
    /*  ====================    USER ITEM OFFERS (ACCOUNT)    ==================== */
    @Override
    public List<Vector> viewUserItemAccountList(String username) {
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
            if(!dateString.equals("")) {
                itemOfferVec.add(dateString);
            } else {
                itemOfferVec.add("Just Now");
            }
            itemOfferVec.add(df.format(itemE.getItemPostingDate()));
            itemOfferVec.add(String.format ("%,.2f", itemE.getItemPrice()));
            itemOfferVec.add(getItemLikeCount(itemE.getItemID()));
            if(lookupLike(itemE.getItemID(), username) == null) { itemOfferVec.add(false);}
            else { itemOfferVec.add(true); }
            itemOfferVec.add(getPendingItemOfferCount(itemE.getItemID()));
            itemOfferVec.add(itemE.getItemCondition());
            itemOfferVec.add(itemE.getItemStatus());
            itemOfferVec.add(getTotalItemOfferCount(itemE.getItemID()));
            itemOfferVec.add(getPendingItemOfferCount(itemE.getItemID()));
            itemOfferList.add(itemOfferVec);
            dateString = "";
        }
        return itemOfferList;
    }
    
    /* OFFERS SENT FROM THE MARKETPLACE USERS */
    @Override
    public List<Vector> viewUserMarketplaceOfferList(String username) {
        boolean insertTempOffer = true;
        ArrayList<String> tempMarketplaceOfferList = new ArrayList<>();
        List<Vector> marketplaceOfferList = new ArrayList<Vector>();
        
        Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.itemEntity.userEntity.username = :username");
        q.setParameter("username", username);
        
        for (Object o : q.getResultList()) {
            ItemOfferEntity itemOfferE = (ItemOfferEntity) o;
            
            for(int i = 0; i < tempMarketplaceOfferList.size(); i++) {
                String tempIndexValue = tempMarketplaceOfferList.get(i);
                long tempItemID = Long.parseLong(tempIndexValue.split(";")[0]);
                
                if(itemOfferE.getItemEntity().getItemID() == tempItemID) {
                    int tempPendingCount = Integer.parseInt(tempIndexValue.split(";")[1]);
                    int tempTotalCount = Integer.parseInt(tempIndexValue.split(";")[2]);
                    if((itemOfferE.getSellerItemOfferStatus()).equals("Pending")) {
                        tempPendingCount++;
                    } else {
                        tempTotalCount++;
                    }
                    tempMarketplaceOfferList.set(i, tempItemID + ";" + tempPendingCount + ";" + tempTotalCount + ";" + itemOfferE.getItemEntity().getItemName() + ";" + itemOfferE.getItemEntity().getItemImage() + ";" + String.format ("%,.2f", itemOfferE.getItemEntity().getItemPrice()) + ";" + itemOfferE.getItemEntity().getItemCondition());
                    insertTempOffer = false;
                }
            }
            if(insertTempOffer == true) {
                if((itemOfferE.getSellerItemOfferStatus()).equals("Pending")) {
                    tempMarketplaceOfferList.add(itemOfferE.getItemEntity().getItemID() + ";1;0;" + itemOfferE.getItemEntity().getItemName() + ";" + itemOfferE.getItemEntity().getItemImage() + ";" + String.format ("%,.2f", itemOfferE.getItemEntity().getItemPrice()) + ";" + itemOfferE.getItemEntity().getItemCondition());
                } else {
                    tempMarketplaceOfferList.add(itemOfferE.getItemEntity().getItemID() + ";0;1;" + itemOfferE.getItemEntity().getItemName() + ";" + itemOfferE.getItemEntity().getItemImage() + ";" + String.format ("%,.2f", itemOfferE.getItemEntity().getItemPrice()) + ";" + itemOfferE.getItemEntity().getItemCondition());
                }
            }
            insertTempOffer = true;
        }
        for(int i = 0; i < tempMarketplaceOfferList.size(); i++) {
            Vector marketplaceOfferVec = new Vector();
            marketplaceOfferVec.add((tempMarketplaceOfferList.get(i)).split(";")[0]);   // Item ID
            marketplaceOfferVec.add((tempMarketplaceOfferList.get(i)).split(";")[1]);   // Pending Item Offer Count
            marketplaceOfferVec.add((tempMarketplaceOfferList.get(i)).split(";")[2]);   // Total Item Offer Count
            marketplaceOfferVec.add((tempMarketplaceOfferList.get(i)).split(";")[3]);   // Item Name
            marketplaceOfferVec.add((tempMarketplaceOfferList.get(i)).split(";")[4]);   // Item Image File Path
            marketplaceOfferVec.add((tempMarketplaceOfferList.get(i)).split(";")[5]);   // Item Price
            marketplaceOfferVec.add((tempMarketplaceOfferList.get(i)).split(";")[6]);   // Item Condition
            marketplaceOfferList.add(marketplaceOfferVec);
        }
        return marketplaceOfferList;
    }
    
    @Override
    public List<Vector> viewAnItemOfferUserList(String username, long urlitemID) {
        boolean itemInfoEntry = false;
        Date currentDate = new Date();
        String dateString = "";
        List<Vector> itemOfferUserList = new ArrayList<Vector>();

        Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.itemEntity.userEntity.username = :username "
                + "AND io.itemEntity.itemID = :itemID");
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
            itemOfferUserVec.add(itemOfferE.getSellerItemOfferStatus());
            
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
            itemOfferUserVec.add(checkFeedbackGivenStatus(username, itemOfferE.getItemEntity().getItemID()));
            itemOfferUserList.add(itemOfferUserVec);
            dateString = "";
        }
        return itemOfferUserList;
    }
    
    @Override
    public String acceptAnItemOffer(long itemOfferID, String sellerComments) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else if(sellerComments.equals("")) { return "Seller comment cannot be empty."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            iEntity = ioEntity.getItemEntity();
            
            ioEntity.setSellerItemOfferStatus("Accepted");
            ioEntity.setBuyerItemOfferStatus("Accepted");
            ioEntity.setSellerComments(sellerComments);
            iEntity.setItemStatus("Reserved");
            
            mEntity = new MessageEntity();
            mEntity.createContentMessage(ioEntity.getItemEntity().getUserEntity().getUsername(), ioEntity.getUserEntity().getUsername(), 
                    ioEntity.getItemEntity().getUserEntity().getUsername() + " has just accepted the item offer for the " + ioEntity.getItemEntity().getItemName() + ".", 
                    ioEntity.getItemEntity().getItemID(), "Marketplace (My Item Offer)");
            /* THE SELLER WHO ACCEPTS THE ITEM OFFER IS THE ONE WHO POST THE MESSAGE */
            mEntity.setUserEntity(ioEntity.getItemEntity().getUserEntity());
            (ioEntity.getUserEntity()).getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(iEntity);
            em.merge(ioEntity);
            em.merge(ioEntity.getUserEntity());
            return "This item offer has been accepted!";
        }
    }
    
    @Override
    public String negotiateAnItemOffer(long itemOfferID, String sellerComments) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else if(sellerComments.equals("")) { return "Seller comment cannot be empty."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setSellerItemOfferStatus("Processing");
            ioEntity.setBuyerItemOfferStatus("Pending");
            ioEntity.setSellerComments(sellerComments);
            
            mEntity = new MessageEntity();
            mEntity.createContentMessage(ioEntity.getItemEntity().getUserEntity().getUsername(), ioEntity.getUserEntity().getUsername(), 
                    ioEntity.getItemEntity().getUserEntity().getUsername() + " has just requested for negotiating the item offer for the " + ioEntity.getItemEntity().getItemName() + ".", 
                    ioEntity.getItemEntity().getItemID(), "Marketplace (My Item Offer)");
            /* THE SELLER WHO NEGOTIATES THE ITEM OFFER IS THE ONE WHO POST THE MESSAGE */
            mEntity.setUserEntity(ioEntity.getItemEntity().getUserEntity());
            (ioEntity.getUserEntity()).getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(ioEntity);
            em.merge(ioEntity.getUserEntity());
            return "Negotiation request for this item offer has been sent!";
        }
    }
    
    @Override
    public String rejectAnItemOffer(long itemOfferID) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setSellerItemOfferStatus("Rejected");
            ioEntity.setBuyerItemOfferStatus("Rejected");
            
            mEntity = new MessageEntity();
            mEntity.createContentMessage(ioEntity.getItemEntity().getUserEntity().getUsername(), ioEntity.getUserEntity().getUsername(), 
                    ioEntity.getItemEntity().getUserEntity().getUsername() + " has just rejected the item offer for the " + ioEntity.getItemEntity().getItemName() + ".", 
                    ioEntity.getItemEntity().getItemID(), "Marketplace (My Item Offer)");
            /* THE SELLER WHO REJECTS THE ITEM OFFER IS THE ONE WHO POST THE MESSAGE */
            mEntity.setUserEntity(ioEntity.getItemEntity().getUserEntity());
            (ioEntity.getUserEntity()).getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(ioEntity);
            em.merge(ioEntity.getUserEntity());
            return "This item offer has been rejected!";
        }
    }
    
    @Override
    public String completeAnItemOffer(long itemOfferID, String itemStatus) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setSellerItemOfferStatus("Completed");
            ioEntity.setBuyerItemOfferStatus("Completed");
            iEntity = ioEntity.getItemEntity(); 
            iEntity.setItemStatus(itemStatus);
            
            mEntity = new MessageEntity();
            mEntity.createContentMessage(ioEntity.getItemEntity().getUserEntity().getUsername(), ioEntity.getUserEntity().getUsername(), 
                    "You have just completed the deal (" + ioEntity.getItemEntity().getItemName() + ") with " + ioEntity.getItemEntity().getUserEntity().getUsername(), 
                    ioEntity.getItemEntity().getItemID(), "Marketplace (My Item Offer)");
            /* THE SELLER WHO SETS THE ITEM OFFER AS "COMPLETED" IS THE ONE WHO POST THE MESSAGE */
            mEntity.setUserEntity(ioEntity.getItemEntity().getUserEntity());
            (ioEntity.getUserEntity()).getMessageSet().add(mEntity);
            
            itEntity = new ItemTransactionEntity();
            itEntity.createItemTransaction(ioEntity.getItemOfferPrice(), ioEntity.getUserEntity().getUsername());
            iEntity.getItemTransactionSet().add(itEntity);
            itEntity.setUserEntity(ioEntity.getItemEntity().getUserEntity());
            itEntity.setItemEntity(iEntity);
            
            em.persist(mEntity);
            em.persist(itEntity);
            
            em.merge(ioEntity);
            em.merge(iEntity);
            em.merge(ioEntity.getUserEntity());
            
            return "This item offer has been marked as 'Completed'! Leave a feedback about the buyer here!";
        }
    }
    
    @Override
    public String reopenAnItemOffer(long itemOfferID, String itemStatus) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setSellerItemOfferStatus("Failed");
            ioEntity.setBuyerItemOfferStatus("Failed");
            iEntity = ioEntity.getItemEntity(); 
            iEntity.setItemStatus(itemStatus);
            
            mEntity = new MessageEntity();
            mEntity.createContentMessage(ioEntity.getItemEntity().getUserEntity().getUsername(), ioEntity.getUserEntity().getUsername(), 
                    "Your item offer (" + ioEntity.getItemEntity().getItemName() + ") has been terminated by the seller " + ioEntity.getItemEntity().getUserEntity().getUsername() + ".", 
                    ioEntity.getItemEntity().getItemID(), "Marketplace (My Item Offer)");
            /* THE SELLER WHO SETS THE ITEM OFFER AS "FAILED" IS THE ONE WHO POST THE MESSAGE */
            mEntity.setUserEntity(ioEntity.getItemEntity().getUserEntity());
            (ioEntity.getUserEntity()).getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(ioEntity);
            em.merge(iEntity);
            em.merge(ioEntity.getUserEntity());
            return "This item offer has been marked as 'Failed'! Leave a feedback about the buyer here!";
        }
    }
    
    @Override
    public String provideTransFeedback(String username, long itemOfferID, String transactionRating) {
        String itemReceiverID = "";
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your rating feedback. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            iEntity = ioEntity.getItemEntity();
            uEntity = lookupUnifyUser(username);
            
            if(!(ioEntity.getUserEntity().getUsername()).equals(username)) {
                itemReceiverID = ioEntity.getUserEntity().getUsername();
            } else if(!(ioEntity.getItemEntity().getUserEntity().getUsername()).equals(username)) {
                itemReceiverID = ioEntity.getItemEntity().getUserEntity().getUsername();
            }
            
            irEntity = new ItemReviewEntity();
            if(irEntity.createItemReview(transactionRating, "", itemReceiverID)) {
                if(getReviewCountForEveryItemOffer(username, itemReceiverID, itemOfferID) == 0) {
                    ioEntity.setBuyerItemOfferStatus("Completed");
                    ioEntity.setSellerItemOfferStatus("Completed");
                } else if(getReviewCountForEveryItemOffer(username, itemReceiverID, itemOfferID) == 1) {
                    ioEntity.setBuyerItemOfferStatus("Closed");
                    ioEntity.setSellerItemOfferStatus("Closed");
                }
                irEntity.setItemEntity(iEntity);
                iEntity.getItemReviewSet().add(irEntity);
                irEntity.setUserEntity(uEntity);
                uEntity.getItemReviewSet().add(irEntity);
                irEntity.setItemOfferEntity(ioEntity);
                ioEntity.getItemReviewSet().add(irEntity);
                
                em.persist(irEntity);
                em.merge(ioEntity);
                em.merge(iEntity);
                em.merge(uEntity);
                
                mEntity = new MessageEntity();
                mEntity.createContentMessage(uEntity.getUsername(), itemReceiverID, 
                        uEntity.getUsername() + " has left a rating for you! You may want to leave a rating for " + uEntity.getUsername() + " too!", 
                        ioEntity.getItemOfferID(), "Marketplace (My Marketplace Review)");
                /* EITHER THE ITEM SELLER OR ITEM BUYER CAN BE THE ONE WHO POSTS THE MESSAGE */
                mEntity.setUserEntity(uEntity);
                (lookupUnifyUser(itemReceiverID)).getMessageSet().add(mEntity);
                
                em.persist(mEntity);
                em.merge(lookupUnifyUser(itemReceiverID));
                
                if((iEntity.getUserEntity().getUsername()).equals(username)) {
                    return "Your rating feedback about the buyer has been sent successfully!";
                } else {
                    return "Your rating feedback about the seller has been sent successfully!";
                }
            } else {
                return "Error occured while sending your rating feedback. Please try again.";
            }
        }
    }
    
    /* USERS PERSONAL ITEM OFFER */
    @Override
    public Vector viewUserMarketplaceRatingInfo(String username) {
        uEntity = lookupUnifyUser(username);
        Vector userMarketplaceRatingInfoVec = new Vector();
        
        if (uEntity != null) {
            userMarketplaceRatingInfoVec.add(uEntity.getImgFileName());
            userMarketplaceRatingInfoVec.add(uEntity.getUserFirstName());
            userMarketplaceRatingInfoVec.add(uEntity.getUserLastName());
            userMarketplaceRatingInfoVec.add(uEntity.getUsername());
            userMarketplaceRatingInfoVec.add(getPositiveItemReviewCount(uEntity.getUsername()));
            userMarketplaceRatingInfoVec.add(getNeutralItemReviewCount(uEntity.getUsername()));
            userMarketplaceRatingInfoVec.add(getNegativeItemReviewCount(uEntity.getUsername()));
        }
        return userMarketplaceRatingInfoVec;
    }
    
    @Override
    public List<Vector> viewPersonalBuyerOfferList(String username) {
        Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.userEntity.username = :username ORDER BY io.itemOfferDate DESC");
        q.setParameter("username", username);
        List<Vector> userBuyerOfferList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ItemOfferEntity userBuyerOfferE = (ItemOfferEntity) o;
            Vector userBuyerOfferVec = new Vector();

            /* ITEM SELLER IS THE PERSON WHO CREATED THE ITEM TRANSACTION */
            userBuyerOfferVec.add(userBuyerOfferE.getItemEntity().getItemID());
            userBuyerOfferVec.add(userBuyerOfferE.getItemEntity().getItemImage());
            userBuyerOfferVec.add(userBuyerOfferE.getItemEntity().getItemName());
            userBuyerOfferVec.add(String.format ("%,.2f", userBuyerOfferE.getItemEntity().getItemPrice()));
            userBuyerOfferVec.add(userBuyerOfferE.getItemOfferID());
            userBuyerOfferVec.add(String.format ("%,.2f", userBuyerOfferE.getItemOfferPrice()));
            userBuyerOfferVec.add(userBuyerOfferE.getBuyerItemOfferStatus());
            userBuyerOfferVec.add(userBuyerOfferE.getSellerComments());
            userBuyerOfferVec.add(df.format(userBuyerOfferE.getItemOfferDate()));
            userBuyerOfferVec.add(checkFeedbackGivenStatus(username, userBuyerOfferE.getItemEntity().getItemID()));
            userBuyerOfferList.add(userBuyerOfferVec);
        }
        return userBuyerOfferList;
    }
    
    @Override
    public String cancelPersonalItemOffer(long itemOfferID) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setSellerItemOfferStatus("Cancelled");
            ioEntity.setBuyerItemOfferStatus("Cancelled");
            
            mEntity = new MessageEntity();
            mEntity.createContentMessage(ioEntity.getUserEntity().getUsername(), ioEntity.getItemEntity().getUserEntity().getUsername(), 
                    ioEntity.getUserEntity().getUsername() + " just cancelled the item offer for your " + ioEntity.getItemEntity().getItemName() + ".", 
                    ioEntity.getItemEntity().getItemID(), "Marketplace (Item Offer)");
            /* THE BUYER WHO CANCELS THE ITEM OFFER IS THE ONE WHO POST THE MESSAGE */
            mEntity.setUserEntity(ioEntity.getUserEntity());
            (ioEntity.getItemEntity().getUserEntity()).getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(ioEntity);
            em.merge(ioEntity.getItemEntity().getUserEntity());
            return "Item offer has been cancelled;" + ioEntity.getBuyerItemOfferStatus() + ";!";
        }
    }
    
    @Override
    public String editPersonalItemOffer(long itemOfferID, String revisedOfferPrice) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else if(!isNumeric(revisedOfferPrice)) { return "Please enter a valid item offer price."; }
        else if(Double.parseDouble(revisedOfferPrice) < 0.0 || Double.parseDouble(revisedOfferPrice) > 9999.0) { return "Item offer price must be between 0 to 9999. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setSellerItemOfferStatus("Pending");
            ioEntity.setBuyerItemOfferStatus("Processing");
            ioEntity.setItemOfferPrice(Double.parseDouble(revisedOfferPrice));
            
            mEntity = new MessageEntity();
            mEntity.createContentMessage(ioEntity.getUserEntity().getUsername(), ioEntity.getItemEntity().getUserEntity().getUsername(), 
                    ioEntity.getUserEntity().getUsername() + " just edited the item offer for your " + ioEntity.getItemEntity().getItemName() + ".", 
                    ioEntity.getItemEntity().getItemID(), "Marketplace (Item Offer)");
            /* THE BUYER WHO EDITS THE ITEM OFFER IS THE ONE WHO POST THE MESSAGE */
            mEntity.setUserEntity(ioEntity.getUserEntity());
            (ioEntity.getItemEntity().getUserEntity()).getMessageSet().add(mEntity);
            
            em.persist(mEntity);
            em.merge(ioEntity);
            em.merge(ioEntity.getItemEntity().getUserEntity());
            return "Item offer has been edited;" + ioEntity.getBuyerItemOfferStatus() + ";" + 
                    String.format ("%,.2f", ioEntity.getItemOfferPrice()) + ";" + 
                    String.format ("%,.2f", ioEntity.getItemEntity().getItemPrice()) + ";!";
        }
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
            userItemVec.add(userItemE.getItemStatus());
            userItemList.add(userItemVec);
            dateString = "";
        }
        return userItemList;
    }
    
    /*  ====================    USER ITEM TRANSACTIONS    ==================== */
    @Override
    public List<Vector> viewItemTransaction(String username) {
        Query q = em.createQuery("SELECT t FROM ItemTransaction t WHERE t.userEntity.username = :username OR t.itemBuyerID = :username");
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
    
    /*  ====================    MISCELLANEOUS METHODS (MARKETPLACE CHAT HISTORY)    ==================== */
    public ChatEntity lookupChat(long chatID) {
        ChatEntity che = new ChatEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Chat c WHERE c.chatID = :chatID");
            q.setParameter("chatID", chatID);
            che = (ChatEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: This chat record cannot be found. " + enfe.getMessage());
            em.remove(che);
            che = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: This chat record does not exist. " + nre.getMessage());
            em.remove(che);
            che = null;
        }
        return che;
    }
    
    /*  ====================    MISCELLANEOUS METHODS (MARKETPLACE CHAT LIST)    ==================== */
    public ChatEntity lookupEmptyChat(String username) {
        ChatEntity che = new ChatEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Chat c WHERE c.userEntity.username = :username AND c.itemBuyerID = :username AND c.chatContent IS NULL");
            q.setParameter("username", username);
            che = (ChatEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Empty Chat cannot be found. " + enfe.getMessage());
            em.remove(che);
            che = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Empty Chat does not exist. " + nre.getMessage());
            em.remove(che);
            che = null;
        }
        return che;
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
    
    /* ---> FOR ACCEPTING AND REJECTING THE ITEM OFFER (FROM ITEM OFFER LISTING) <--- */
    public ItemOfferEntity lookupItemOffer(long itemOfferID) {
        ItemOfferEntity ioe = new ItemOfferEntity();
        try {
            Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.itemOfferID = :itemOfferID");
            q.setParameter("itemOfferID", itemOfferID);
            ioe = (ItemOfferEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item Offer cannot be found. " + enfe.getMessage());
            em.remove(ioe);
            ioe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item Offer does not exist. " + nre.getMessage());
            em.remove(ioe);
            ioe = null;
        }
        return ioe;
    }
    
    /* ---> FOR UNIFY CHAT <--- */
    public ItemOfferEntity lookupBuyerItemOffer(long itemID, String username) {
        ItemOfferEntity ioe = new ItemOfferEntity();
        try {
            Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.itemEntity.itemID = :itemID AND io.userEntity.username = :username");
            q.setParameter("itemID", itemID);
            q.setParameter("username", username);
            ioe = (ItemOfferEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Buyer Item Offer Status cannot be found. " + enfe.getMessage());
            em.remove(ioe);
            ioe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Buyer Item Offer Status does not exist. " + nre.getMessage());
            em.remove(ioe);
            ioe = null;
        }
        return ioe;
    }
    
    public ItemOfferEntity lookupItemOfferSeller(long itemID, String username) {
        ItemOfferEntity ioe = new ItemOfferEntity();
        try {
            Query q = em.createQuery("SELECT io FROM ItemOffer io WHERE io.itemEntity.itemID = :itemID AND io.itemEntity.userEntity.username = :username");
            q.setParameter("itemID", itemID);
            q.setParameter("username", username);
            ioe = (ItemOfferEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Seller associated with this Item Offer cannot be found. " + enfe.getMessage());
            em.remove(ioe);
            ioe = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Seller associated with this Item Offer Status does not exist. " + nre.getMessage());
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
    
    public static boolean isNumeric(String strValue) {
        return strValue.matches("-?\\d+(\\.\\d+)?");  // match a number with optional '-' and decimal
    }
    
    /*  ====================    MISCELLANEOUS METHODS (ITEM LIKE)    ==================== */
    public Long getItemLikeCount(long itemID) {
        Long itemLikeCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.likeID) FROM LikeListing l WHERE l.itemEntity.itemID = :itemID");
        q.setParameter("itemID", itemID);
        try {
            itemLikeCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getItemLikeCount().getSingleResult()");
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
    
    /*  ====================    MISCELLANEOUS METHODS (ITEM OFFER)    ==================== */
    public Long getPendingItemOfferCount(long itemID) {
        Long pendingItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.sellerItemOfferStatus = 'Pending'");
        q.setParameter("itemID", itemID);
        try {
            pendingItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getPendingItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return pendingItemOfferCount;
    }
    
    public Long getTotalItemOfferCount(long itemID) {
        Long pendingItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID");
        q.setParameter("itemID", itemID);
        try {
            pendingItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getPendingItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return pendingItemOfferCount;
    }
    
    /*  ====================    MISCELLANEOUS METHODS (ITEM REVIEW)    ==================== */
    public Long getReviewCountForEveryItemOffer(String transUserIDOne, String transUserIDTwo, long itemOfferID) {
        Long reviewCountForEveryItemOffer = new Long(0);
        Query q = em.createQuery("SELECT COUNT(ir.itemReviewID) FROM ItemReview ir WHERE ir.itemOfferEntity.itemOfferID = :itemOfferID AND (ir.userEntity.username = :transUserIDOne AND ir.itemReceiverID = :transUserIDTwo) OR (ir.userEntity.username = :transUserIDTwo AND ir.itemReceiverID = :transUserIDOne)");
        q.setParameter("itemOfferID", itemOfferID);
        q.setParameter("transUserIDOne", transUserIDOne);
        q.setParameter("transUserIDTwo", transUserIDTwo);
        try {
            reviewCountForEveryItemOffer = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getReviewCountForEveryItemOffer().getSingleResult()");
            ex.printStackTrace();
        }
        return reviewCountForEveryItemOffer;
    }
    
    public boolean checkFeedbackGivenStatus(String feedbackProviderID, long itemID) {
        boolean checkStatus = false;
        Long checkFeedbackCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(ir.itemReviewID) FROM ItemReview ir WHERE ir.itemEntity.itemID = :itemID AND ir.userEntity.username = :feedbackProviderID");
        q.setParameter("itemID", itemID);
        q.setParameter("feedbackProviderID", feedbackProviderID);
        try {
            checkFeedbackCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.checkProvideFeedbackStatus().getSingleResult()");
            ex.printStackTrace();
        }
        if(checkFeedbackCount == 1) {
            checkStatus = true;
        } else if(checkFeedbackCount == 0) {
            checkStatus = false;
        }
        return checkStatus;
    }
    
    @Override
    public String markNotification(long msgContentID, String msgSenderID) {
        MessageEntity me = lookupMessage(msgContentID, msgSenderID);
        if(me == null) { return "Unsuccessful"; }
        else {
            me.setMessageStatus("Read");
            em.merge(me);
        }
        return "Successful";
    }
    
    public MessageEntity lookupMessage(long msgContentID, String msgSenderID) {
        MessageEntity me = new MessageEntity();
        try {
            Query q = em.createQuery("SELECT m FROM Message m WHERE m.contentID=:msgContentID AND m.messageSenderID=:msgSenderID");
            q.setParameter("msgContentID",msgContentID);
            q.setParameter("msgSenderID", msgSenderID);
            
            me = (MessageEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Company Review cannot be found. " + enfe.getMessage());
            em.remove(me);
            me = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Company Review does not exist. " + nre.getMessage());
            em.remove(me);
            me = null;
        }
        return me;
    }
}