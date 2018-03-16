package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface MarketplaceSysUserMgrBeanRemote {
    public List<Vector> viewItemList(String username);
    public List<Vector> viewAssocCategoryItemList(String hiddenCategoryName, long hiddenItemID);
    public Vector viewItemDetails(long itemID);
    public Vector viewItemDetails(long itemID, String username);
    public String createItemListing(String itemName, double itemPrice, String itemCondition, 
            String itemDescription, String itemImagefileName, long categoryID, String username, 
            String tradeLocation, String tradeLat, String tradeLong, String tradeInformation);
    public String editItemListing(long itemID, String itemName, double itemPrice, String itemCondition, 
            String itemDescription, String itemImageFileName, long itemCategoryID, String username, 
            String tradeLocation, String tradeLat, String tradeLong, String tradeInformation);
    public String deleteItemListing(long itemIDToDelete);
    
    public String sendItemOfferPrice(long itemID, String username, String itemOfferPrice, String itemOfferDescription);
    public String reportItemListing(long itemID, String username, String itemReportCategory, String itemReportDescription);
    public String likeUnlikeItem(long itemID, String username);
    public List<Vector> viewItemLikeList(long itemID);
    public List<Vector> viewItemCategoryList();
    
    /*  ====================    MISCELLANEOUS METHODS    ==================== */
    public String populateItemCategory();
}