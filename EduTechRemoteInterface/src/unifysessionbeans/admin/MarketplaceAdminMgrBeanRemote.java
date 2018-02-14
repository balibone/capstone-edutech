package unifysessionbeans.admin;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface MarketplaceAdminMgrBeanRemote {
    public List<Vector> viewItemCategoryList();
    public Vector viewItemCategoryDetails(String urlCategoryName, String urlCategoryType);
    public List<Vector> viewAssociatedItemList(String urlCategoryName, String urlCategoryType);
    
    public boolean createItemCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage);
    public boolean updateItemCategory(String oldCategoryName, String newCategoryName, String categoryType, 
            String oldCategoryDescription, String newCategoryDescription, String fileName);
    public boolean activateAnItemCategory(String actCategoryName, String actCategoryType);
    public String deactivateAnItemCategory(String deactCategoryName, String deactCategoryType);
    
    public List<Vector> viewItemList();
    public Vector viewItemDetails(String itemName, String itemSellerID);
    public boolean deleteAnItem(String hiddenItemName, String hiddenSellerID);
    
    public void createSystemMessage(String hiddenItemName, String hiddenSellerID);
}