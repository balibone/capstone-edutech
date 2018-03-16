package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface UserProfileSysUserMgrBeanRemote {
    /*  ====================    MISCELLANEOUS METHODS (USER PROFILE)    ==================== */
    public Vector viewUserProfileDetails(String username);
    
    /*  ====================    MISCELLANEOUS METHODS (USER NOTIFICATION)    ==================== */
    public List<Vector> viewUserMessageListTopThree(String username);
    public List<Vector> viewUserMessageList(String username);
    
    /*  ====================    MISCELLANEOUS METHODS (USER ITEM OFFER)    ==================== */
    public List<Vector> viewItemOfferList(String username);
    /* OFFER MADE BY THE UNIFY SYSTEM USERS TO THE SELLER */
    public List<Vector> viewItemOfferUserList(String username, long urlitemID);
    public String acceptAnItemOffer(long itemOfferID);
    public String rejectAnItemOffer(long itemOfferID);
    /* OFFERS MADE BY THE USER (BUYER) HIMSELF */ 
    public List<Vector> viewPersonalBuyerOfferList(String username);
    public String cancelPersonalItemOffer(long itemOfferID);
    
    /*  ====================    MISCELLANEOUS METHODS (USER ITEM TRANSACTION)    ==================== */
    public List<Vector> viewItemTransaction(String username);
    public Vector viewTransactionItemDetails(long itemID, long itemTransID, String username);
}