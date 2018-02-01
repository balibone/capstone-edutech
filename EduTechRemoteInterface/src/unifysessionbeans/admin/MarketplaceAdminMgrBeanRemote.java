package unifysessionbeans.admin;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface MarketplaceAdminMgrBeanRemote {
    public List<Vector> viewItemCategoryList();
    public List<Vector> viewItemList();
    public Vector viewItemDetails(String itemName, String itemSellerID);
}
