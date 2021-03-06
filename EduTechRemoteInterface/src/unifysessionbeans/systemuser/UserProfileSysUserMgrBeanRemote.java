package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface UserProfileSysUserMgrBeanRemote {
    /*  ====================    MISCELLANEOUS METHODS (USER PROFILE)    ==================== */
    public Vector viewUserProfileDetails(String username);
    public List<Vector> viewUserItemWishlist(String username);
    
    /*  ====================    MISCELLANEOUS METHODS (USER NOTIFICATION)    ==================== */
    public List<Vector> viewUserMessageListTopThree(String username);
    public List<Vector> viewUserMessageList(String username);
    public List<Vector> viewUserChatBuyingList(String username, String itemID, boolean checkEmptyContent);
    public List<Vector> viewUserChatSellingList(String username);
    
    public Vector viewChatContentInfo(String username, long chatID);
    public List<Vector> viewChatListContent(String username, long chatID);
    public String addNewChatContent(String senderID, String receiverID, String chatContent, 
            String buyerOrSellerStat, String buyerOrSellerID, long itemID);
    public String getChatAvailability(long itemID, String itemBuyerID);
    
    /*  ====================    MISCELLANEOUS METHODS (USER ITEM OFFER)    ==================== */
    public List<Vector> viewUserItemAccountList(String username);
    public List<Vector> viewUserMarketplaceOfferList(String username);
    /* OFFER MADE BY THE UNIFY SYSTEM USERS TO THE SELLER */
    public List<Vector> viewAnItemOfferUserList(String username, long urlitemID);
    public String acceptAnItemOffer(long itemOfferID, String sellerComments);
    public String negotiateAnItemOffer(long itemOfferID, String sellerComments);
    public String rejectAnItemOffer(long itemOfferID);
    public String completeAnItemOffer(String username, long itemOfferID, String itemStatus);
    public String reopenAnItemOffer(long itemOfferID, String itemStatus);
    public String provideTransFeedback(String username, long itemOfferID, String transactionRating);
    
    /* OFFERS MADE BY THE USER (BUYER) HIMSELF */ 
    public Vector viewUserMarketplaceRatingInfo(String username);
    public List<Vector> viewPersonalBuyerOfferList(String username);
    public String cancelPersonalItemOffer(long itemOfferID);
    public String editPersonalItemOffer(long itemOfferID, String revisedOfferPrice);
    
    /*  ====================    MISCELLANEOUS METHODS (USER PROFILE)    ==================== */
    public List<Vector> viewUserItemList(String username, String itemSellerID);
    
    /*  ====================    MISCELLANEOUS METHODS (USER ITEM TRANSACTION)    ==================== */
    public List<Vector> viewItemTransaction(String username);
    public Vector viewTransactionItemDetails(long itemID, long itemTransID, String username);
    
    public String markNotification(long msgID);
    public Long getUnreadNotificationCount(String username);
}