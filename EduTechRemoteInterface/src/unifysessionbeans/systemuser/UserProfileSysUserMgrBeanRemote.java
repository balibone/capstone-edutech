package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface UserProfileSysUserMgrBeanRemote {
    public Vector viewUserProfileDetails(String username);
    public List<Vector> viewMessageListTopFive(String username);
}