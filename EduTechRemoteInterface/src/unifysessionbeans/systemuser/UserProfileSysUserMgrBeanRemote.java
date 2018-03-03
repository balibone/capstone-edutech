package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface UserProfileSysUserMgrBeanRemote {
    /* USER ACCOUNT */
    public List<Vector> viewItemTransaction(String username);
    public List<Vector> viewItemOfferList(String username);
    
    /* USER PROFILE */
    public List<Vector> viewUserItemListing(String username);
}