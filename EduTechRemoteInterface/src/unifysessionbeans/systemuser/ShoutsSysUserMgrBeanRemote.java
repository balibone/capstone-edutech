package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface ShoutsSysUserMgrBeanRemote {
    
    public List<Vector> viewShoutList();
     public String createShout(String shoutContent, String shoutPoster);
    
}