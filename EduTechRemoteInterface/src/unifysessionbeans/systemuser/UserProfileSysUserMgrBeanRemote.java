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
    public List<Vector> viewUserChatBuyingList(String username, String itemID);
    public List<Vector> viewUserChatSellingList(String username, String itemID);
    
    public Vector viewChatContentInfo(String username, long chatID);
    public List<Vector> viewChatListContent(long chatID);
    public List<Vector> viewAssocBuyingList(String username, String itemID);
    public List<Vector> viewAssocSellingList(String username, String itemID);
    public String addNewChatContent(String senderID, String receiverID, String chatContent, 
            String buyerOrSellerStat, String buyerOrSellerID, long itemID);
    
    /*  ====================    MISCELLANEOUS METHODS (USER ITEM OFFER)    ==================== */
    public List<Vector> viewItemOfferList(String username);
    /* OFFER MADE BY THE UNIFY SYSTEM USERS TO THE SELLER */
    public List<Vector> viewItemOfferUserList(String username, long urlitemID);
    public String acceptAnItemOffer(long itemOfferID, String sellerComments);
    public String negotiateAnItemOffer(long itemOfferID, String sellerComments);
    public String rejectAnItemOffer(long itemOfferID);
    public String completeAnItemOffer(long itemOfferID, String itemStatus);
    /* OFFERS MADE BY THE USER (BUYER) HIMSELF */ 
    public List<Vector> viewPersonalBuyerOfferList(String username);
    public String cancelPersonalItemOffer(long itemOfferID);
    public String editPersonalItemOffer(long itemOfferID, double revisedOfferPrice);
    
    /*  ====================    MISCELLANEOUS METHODS (USER PROFILE)    ==================== */
    public List<Vector> viewUserItemList(String username, String itemSellerID);
    
    /*  ====================    MISCELLANEOUS METHODS (USER ITEM TRANSACTION)    ==================== */
    public List<Vector> viewItemTransaction(String username);
    public Vector viewTransactionItemDetails(long itemID, long itemTransID, String username);
}