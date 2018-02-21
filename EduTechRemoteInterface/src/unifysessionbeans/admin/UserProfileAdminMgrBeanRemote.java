/***************************************************************************************
*   Title:                  UserProfileAdminMgrBeanRemote.java
*   Purpose:                LIST OF REMOTE INTERFACE METHODS FOR UNIFY DASHBOARD & PROFILE - ADMIN (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.admin;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface UserProfileAdminMgrBeanRemote {
    public List<Vector> viewUnifyUserList();
    
    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    public Long getUnifyUserCount();
    public Long getActiveUnifyUserCount();
    public Long getInactiveUnifyUserCount();
}