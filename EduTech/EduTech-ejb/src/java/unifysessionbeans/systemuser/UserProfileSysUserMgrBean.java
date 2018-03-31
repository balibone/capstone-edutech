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
import unifyentities.marketplace.ItemTransactionEntity;

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private ChatEntity chEntity;
    private ItemEntity iEntity;
    private ItemOfferEntity ioEntity;
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
    
    @Override
    public List<Vector> viewUserChatBuyingList(String username, String itemID) {
        ArrayList<String> nameHolderArrList = new ArrayList<>();
        chEntity = lookupEmptyChat(username);
        
        if(!itemID.equals("")) {
            if (chEntity != null) {
                uEntity = chEntity.getUserEntity();
                uEntity.getChatSet().remove(chEntity);
                iEntity = chEntity.getItemEntity();
                iEntity.getChatSet().remove(chEntity);

                em.merge(uEntity);
                em.merge(iEntity);
                em.remove(chEntity);
                em.flush();
                em.clear();
            }
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
        } else if(itemID.equals("")) {
            if (chEntity != null) {
                uEntity = chEntity.getUserEntity();
                uEntity.getChatSet().remove(chEntity);
                iEntity = chEntity.getItemEntity();
                iEntity.getChatSet().remove(chEntity);

                em.merge(uEntity);
                em.merge(iEntity);
                em.remove(chEntity);
                em.flush();
                em.clear();
            }
        }
        
        Query q = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemBuyerID = :username ORDER BY c.chatPostingDate DESC");
        q.setParameter("username", username);
        List<Vector> userChatList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ChatEntity chatE = (ChatEntity) o;
            Vector userChatVec = new Vector();
            
            if((chatE.getUserEntity().getUsername()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getChatReceiverID())) {
                    userChatVec.add(chatE.getChatID()+1);
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getChatReceiverID());
                }
            } else if((chatE.getChatReceiverID()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getUserEntity().getUsername())) {
                    userChatVec.add(chatE.getChatID()+1);
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getUserEntity().getUsername());
                }
            }
            userChatList.add(userChatVec);
        }
        return userChatList;
    }
    
    @Override
    public List<Vector> viewUserChatSellingList(String username, String itemID) {
        ArrayList<String> nameHolderArrList = new ArrayList<>();
        chEntity = lookupEmptyChat(username);
        
        if(!itemID.equals("")) {
            if (chEntity != null) {
                uEntity = chEntity.getUserEntity();
                uEntity.getChatSet().remove(chEntity);
                iEntity = chEntity.getItemEntity();
                iEntity.getChatSet().remove(chEntity);

                em.merge(uEntity);
                em.merge(iEntity);
                em.remove(chEntity);
                em.flush();
                em.clear();
            }
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
        } else if(itemID.equals("")) {
            if (chEntity != null) {
                uEntity = chEntity.getUserEntity();
                uEntity.getChatSet().remove(chEntity);
                iEntity = chEntity.getItemEntity();
                iEntity.getChatSet().remove(chEntity);

                em.merge(uEntity);
                em.merge(iEntity);
                em.remove(chEntity);
                em.flush();
                em.clear();
            }
        }
        
        Query q = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemSellerID = :username ORDER BY c.chatPostingDate DESC");
        q.setParameter("username", username);
        List<Vector> userChatList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ChatEntity chatE = (ChatEntity) o;
            Vector userChatVec = new Vector();
            
            if((chatE.getUserEntity().getUsername()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getChatReceiverID())) {
                    userChatVec.add(chatE.getChatID()+1);
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getChatReceiverID());
                }
            } else if((chatE.getChatReceiverID()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getUserEntity().getUsername())) {
                    userChatVec.add(chatE.getChatID()+1);
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getUserEntity().getUsername());
                }
            }
            userChatList.add(userChatVec);
        }
        return userChatList;
    }
    
    /* PACKAGED X4 */
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
            if(!chEntity.getItemBuyerID().equals("")) {
                chatContentInfoVec.add("Item Buyer");
                chatContentInfoVec.add(chEntity.getItemBuyerID());
            } else {
                chatContentInfoVec.add("Item Seller");
                chatContentInfoVec.add(chEntity.getItemSellerID());
            }
            chatContentInfoVec.add(chEntity.getItemEntity().getItemID());
        }
        return chatContentInfoVec;
    }
    
    @Override
    public List<Vector> viewChatListContent(long chatID) {
        List<Vector> chatListContent = new ArrayList<Vector>();
        chEntity = lookupChat(chatID);
        
        if(chEntity != null) {
            if(!(chEntity.getItemBuyerID()).equals("")) {
                Query q = em.createQuery("SELECT c FROM Chat c WHERE c.itemEntity.itemID = :itemID AND c.itemBuyerID = :chatAssocID ORDER BY c.chatPostingDate ASC");
                q.setParameter("itemID", chEntity.getItemEntity().getItemID());
                q.setParameter("chatAssocID", chEntity.getItemBuyerID());

                for (Object o : q.getResultList()) {
                    ChatEntity chatE = (ChatEntity) o;
                    Vector chatContentE = new Vector();
                    
                    chatContentE.add(chatE.getChatContent());
                    chatContentE.add(chatE.getUserEntity().getUsername());
                    chatContentE.add(chatE.getUserEntity().getImgFileName());
                    chatContentE.add(chatE.getChatReceiverID());
                    chatContentE.add(lookupUnifyUser(chatE.getChatReceiverID()).getImgFileName());
                    chatContentE.add(chatE.getItemBuyerID());
                    chatListContent.add(chatContentE);
                }
            } else if(!(chEntity.getItemSellerID()).equals("")) {
                Query q = em.createQuery("SELECT c FROM Chat c WHERE c.itemEntity.itemID = :itemID AND c.itemSellerID = :chatAssocID ORDER BY c.chatPostingDate ASC");
                q.setParameter("itemID", chEntity.getItemEntity().getItemID());
                q.setParameter("chatAssocID", chEntity.getItemSellerID());
                
                for (Object o : q.getResultList()) {
                    ChatEntity chatE = (ChatEntity) o;
                    Vector chatContentE = new Vector();
                    
                    chatContentE.add(chatE.getChatContent());
                    chatContentE.add(chatE.getUserEntity().getUsername());
                    chatContentE.add(chatE.getUserEntity().getImgFileName());
                    chatContentE.add(chatE.getChatReceiverID());
                    chatContentE.add(lookupUnifyUser(chatE.getChatReceiverID()).getImgFileName());
                    chatContentE.add(chatE.getItemSellerID());
                    chatListContent.add(chatContentE);
                }
            }
        }
        return chatListContent;
    }
    
    @Override
    public List<Vector> viewAssocBuyingList(String username, String itemID) {
        ArrayList<String> nameHolderArrList = new ArrayList<>();
        
        Query q = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemBuyerID = :username ORDER BY c.chatPostingDate DESC");
        q.setParameter("username", username);
        List<Vector> userChatList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ChatEntity chatE = (ChatEntity) o;
            Vector userChatVec = new Vector();
            
            if((chatE.getUserEntity().getUsername()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getChatReceiverID())) {
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getChatReceiverID());
                }
            } else if((chatE.getChatReceiverID()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getUserEntity().getUsername())) {
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getUserEntity().getUsername());
                }
            }
            userChatList.add(userChatVec);
        }
        return userChatList;
    }
    
    @Override
    public List<Vector> viewAssocSellingList(String username, String itemID) {
        ArrayList<String> nameHolderArrList = new ArrayList<>();
        
        Query q = em.createQuery("SELECT c FROM Chat c WHERE (c.userEntity.username = :username OR c.chatReceiverID = :username) AND c.itemSellerID = :username ORDER BY c.chatPostingDate DESC");
        q.setParameter("username", username);
        List<Vector> userChatList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            ChatEntity chatE = (ChatEntity) o;
            Vector userChatVec = new Vector();
            
            if((chatE.getUserEntity().getUsername()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getChatReceiverID())) {
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getChatReceiverID());
                }
            } else if((chatE.getChatReceiverID()).equals(username)) {
                if(!nameHolderArrList.contains(chatE.getUserEntity().getUsername())) {
                    userChatVec.add(chatE.getChatID());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getImgFileName());
                    userChatVec.add(chatE.getItemEntity().getUserEntity().getUsername());
                    userChatVec.add(chatE.getItemEntity().getItemName());
                    userChatVec.add(chatE.getChatStatus());
                    userChatVec.add(chatE.getChatContent());
                    nameHolderArrList.add(chatE.getUserEntity().getUsername());
                }
            }
            userChatList.add(userChatVec);
        }
        return userChatList;
    }
    
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
                    tempMarketplaceOfferList.set(i, tempItemID + ";" + tempPendingCount + ";" + tempTotalCount);
                    insertTempOffer = false;
                }
            }
            if(insertTempOffer == true) {
                if((itemOfferE.getSellerItemOfferStatus()).equals("Pending")) {
                    tempMarketplaceOfferList.add(itemOfferE.getItemEntity().getItemID() + ";1;0;" + itemOfferE.getItemEntity().getItemName() + ";" + itemOfferE.getItemEntity().getItemImage() + ";" + itemOfferE.getItemEntity().getItemPrice() + ";" + itemOfferE.getItemEntity().getItemCondition());
                } else {
                    tempMarketplaceOfferList.add(itemOfferE.getItemEntity().getItemID() + ";0;1;" + itemOfferE.getItemEntity().getItemName() + ";" + itemOfferE.getItemEntity().getItemImage() + ";" + itemOfferE.getItemEntity().getItemPrice() + ";" + itemOfferE.getItemEntity().getItemCondition());
                }
            }
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
            return "Item offer has been accepted!";
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
            return "Item offer has been rejected!";
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
            
            em.persist(mEntity);
            em.merge(ioEntity);
            em.merge(iEntity);
            em.merge(ioEntity.getUserEntity());
            return "Item offer has been marked as completed! Leave a feedback about the buyer here!";
        }
    }
    
    /* USERS PERSONAL ITEM OFFER */
    @Override
    public List<Vector> viewPersonalBuyerOfferList(String username) {
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
            userBuyerOfferVec.add(userBuyerOfferE.getBuyerItemOfferStatus());
            userBuyerOfferVec.add(userBuyerOfferE.getSellerComments());
            userBuyerOfferVec.add(df.format(userBuyerOfferE.getItemOfferDate()));
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
    public String editPersonalItemOffer(long itemOfferID, double revisedOfferPrice) {
        if (lookupItemOffer(itemOfferID) == null) { return "Some errors occured while processing your item offer. Please try again."; }
        else {
            ioEntity = lookupItemOffer(itemOfferID);
            ioEntity.setSellerItemOfferStatus("Pending");
            ioEntity.setBuyerItemOfferStatus("Processing");
            ioEntity.setItemOfferPrice(revisedOfferPrice);
            
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
    
    public ChatEntity lookupEmptyChat(String username) {
        ChatEntity che = new ChatEntity();
        try {
            Query q = em.createQuery("SELECT c FROM Chat c WHERE c.userEntity.username = :username AND c.itemBuyerID = :username AND c.chatContent IS NOT NULL");
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
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.sellerItemOfferStatus = 'Pending'");
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