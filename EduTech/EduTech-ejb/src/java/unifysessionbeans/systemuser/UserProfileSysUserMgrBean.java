/***************************************************************************************
*   Title:                  UserProfileSysUserMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY DASHBOARD & PROFILE - SYSUSER (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.systemuser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import commoninfrastructureentities.UserEntity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private UserEntity uEntity;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public Vector viewUserProfileDetails(String username) {
        uEntity = lookupUnifyUser(username);
        Vector userProfileDetailsVec = new Vector();

        if (uEntity != null) {
            userProfileDetailsVec.add(uEntity.getUsername());
            userProfileDetailsVec.add(uEntity.getUserFirstName());
            userProfileDetailsVec.add(uEntity.getUserLastName());
            userProfileDetailsVec.add(uEntity.getImgFileName());
            userProfileDetailsVec.add(df.format(uEntity.getUserCreationDate()));
        }
        return userProfileDetailsVec;
    }

    /* MISCELLANEOUS METHODS */
    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try{
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }
}