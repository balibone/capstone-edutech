/***************************************************************************************
*   Title:                  UserProfileAdminMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY DASHBOARD & PROFILE - ADMIN (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifysessionbeans.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import commoninfrastructure.UserEntity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

@Stateless
public class UserProfileAdminMgrBean implements UserProfileAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    private UserEntity uEntity;
    
    @Override
    public List<Vector> viewUnifyUserList() {
        Query q = em.createQuery("SELECT u FROM SystemUser u");
        List<Vector> unifyUserList = new ArrayList<Vector>();
        
        for (Object o: q.getResultList()){
            UserEntity userE = (UserEntity) o;
            Vector userVec = new Vector();
            
            userVec.add(userE.getUsername());
            userVec.add(userE.getUserSalutation());
            userVec.add(userE.getUserFirstName());
            userVec.add(userE.getUserLastName());
            userVec.add(userE.getUserActiveStatus());
            userVec.add(userE.getImgFileName());
            unifyUserList.add(userVec);
        }
        return unifyUserList;
    }
    
    @Override
    public Long getUnifyUserCount() {
        Long unifyUserCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(u.username) FROM SystemUser u");
        try {
            unifyUserCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in UserProfileAdminMgrBean.getUnifyUserCount().getSingleResult()");
            ex.printStackTrace();
        }
        return unifyUserCount;
    }
    @Override
    public Long getActiveUnifyUserCount() {
        Long activeUnifyUserCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(u.username) FROM SystemUser u WHERE u.userActiveStatus='1'");
        try {
            activeUnifyUserCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in UserProfileAdminMgrBean.getActiveUnifyUserCount().getSingleResult()");
            ex.printStackTrace();
        }
        return activeUnifyUserCount;
    }
    @Override
    public Long getInactiveUnifyUserCount() {
        Long inactiveUnifyUserCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(u.username) FROM SystemUser u WHERE u.userActiveStatus='0'");
        try {
            inactiveUnifyUserCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in UserProfileAdminMgrBean.getInactiveUnifyUserCount().getSingleResult()");
            ex.printStackTrace();
        }
        return inactiveUnifyUserCount;
    }
    
    /* METHODS FOR UNIFY USER PROFILE */
    @Override
    public Vector viewUserOverviewDetails(String username) {
        uEntity = lookupUnifyUser(username);
        Vector userOverviewDetailsVec = new Vector();

        if (uEntity != null) {
            userOverviewDetailsVec.add(uEntity.getImgFileName());
            userOverviewDetailsVec.add(uEntity.getUsername());
            userOverviewDetailsVec.add(uEntity.getUserSalutation());
            userOverviewDetailsVec.add(uEntity.getUserFirstName());
            userOverviewDetailsVec.add(uEntity.getUserLastName());
            userOverviewDetailsVec.add(uEntity.getUserActiveStatus());
        }
        return userOverviewDetailsVec;
    }
    
    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try {
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: System User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: System User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }
}