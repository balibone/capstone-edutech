package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface MarketplaceSysUserMgrBeanRemote {
    public List<Vector> viewItemList();
    public List<Vector> viewAssocCategoryItemList(String hiddenCategoryName, long hiddenItemID);
    public Vector viewItemDetails(long itemID);
}