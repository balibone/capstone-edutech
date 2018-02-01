package unifysessionbeans.admin;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface MarketplaceAdminMgrBeanRemote {
    public List<Vector> viewItemCategoryList();
    public Vector viewItemCategoryDetails(String categoryName, String categoryType);
    public boolean createItemCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage);
    public List<Vector> viewItemList();
    public Vector viewItemDetails(String itemName, String itemSellerID);
}
