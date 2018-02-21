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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import commoninfrastructureentities.UserEntity;
import unifyentities.marketplace.ItemTransactionEntity;

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private UserEntity uEntity;
    private Collection<ItemTransactionEntity> itemTransactionSet;
    
    @Override
    public List<Vector> viewItemTransaction(String username) {
        List<Vector> itemTransList = new ArrayList<Vector>();
        Vector itemTransDetailsVec = new Vector();
        
        uEntity = lookupUser(username);
        itemTransactionSet = uEntity.getItemTransactionSet();
        
        for (ItemTransactionEntity ite : itemTransactionSet) {
            itemTransDetailsVec.add(ite.getItemBuyerID());
            itemTransDetailsVec.add(ite.getItemSellerID());
            itemTransDetailsVec.add(ite.getItemEntity().getItemName());
            itemTransDetailsVec.add(ite.getItemTransactionDate());
            itemTransDetailsVec.add(ite.getItemTransactionPrice());
            itemTransList.add(itemTransDetailsVec);
        }
        return itemTransList;
    }
    
    /* MISCELLANEOUS METHODS */
    public UserEntity lookupUser(String username){
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