package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface VoicesSysUserMgrBeanRemote {
    
    public List<Vector> viewCompanyList();
    
    public List<String> populateCompanyIndustry();
    
}