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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import unifyentities.marketplace.ItemEntity;
import unifyentities.marketplace.ItemTransactionEntity;

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /* USER ACCOUNT */
    @Override
    public List<Vector> viewItemTransaction(String username) {
        Query q = em.createQuery("SELECT t FROM ItemTransaction t WHERE t.userEntity.username = :username");
        q.setParameter("username", username);
        List<Vector> itemTransList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemTransactionEntity itemTransE = (ItemTransactionEntity) o;
            Vector itemTransVec = new Vector();

            /* ITEM SELLER IS THE PERSON WHO CREATED THE ITEM TRANSACTION */
            itemTransVec.add(itemTransE.getItemEntity().getItemID());
            itemTransVec.add(df.format(itemTransE.getItemTransactionDate()));
            itemTransVec.add(itemTransE.getUserEntity().getUsername());
            itemTransVec.add(itemTransE.getItemBuyerID());
            itemTransVec.add(itemTransE.getItemEntity().getItemImage());
            itemTransVec.add(itemTransE.getItemEntity().getItemName());
            itemTransVec.add(String.format ("%,.2f", itemTransE.getItemEntity().getItemPrice()));
            itemTransVec.add(String.format ("%,.2f", itemTransE.getItemTransactionPrice()));
            itemTransList.add(itemTransVec);
        }
        return itemTransList;
    }
    
    @Override
    public List<Vector> viewItemOfferList(String username) {
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.userEntity.username = :username");
        q.setParameter("username", username);
        List<Vector> itemOfferList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector itemOfferVec = new Vector();

            itemOfferVec.add(itemE.getItemID());
            itemOfferVec.add(itemE.getItemImage());
            itemOfferVec.add(itemE.getItemName());
            itemOfferVec.add(String.format ("%,.2f", itemE.getItemPrice()));
            itemOfferVec.add(getPendingItemOfferCount(itemE.getItemID()));
            itemOfferVec.add(getRejectedItemOfferCount(itemE.getItemID()));
            itemOfferList.add(itemOfferVec);
        }
        return itemOfferList;
    }
    
    /* USER PROFILE */
    @Override
    public List<Vector> viewUserItemListing(String username) {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.userEntity.username = :username AND i.categoryEntity.categoryActiveStatus = '1'");
        q.setParameter("username", username);
        List<Vector> userItemList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector userItemVec = new Vector();

            userItemVec.add(itemE.getItemID());
            userItemVec.add(itemE.getItemImage());
            userItemVec.add(itemE.getItemName());
            userItemVec.add(itemE.getCategoryEntity().getCategoryName());
            userItemVec.add(itemE.getUserEntity().getUsername());

            long diff = currentDate.getTime() - itemE.getItemPostingDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays != 0) {
                dateString = diffDays + " day";
                if (diffDays == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffHours != 0) {
                dateString = diffHours + " hour";
                if (diffHours == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffMinutes != 0) {
                dateString = diffMinutes + " minute";
                if (diffMinutes == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            } else if (diffSeconds != 0) {
                dateString = diffSeconds + " second";
                if (diffSeconds == 1) {
                    dateString += " ago";
                } else {
                    dateString += "s ago";
                }
            }
            userItemVec.add(dateString);
            userItemVec.add(String.format ("%,.2f", itemE.getItemPrice()));
            userItemVec.add(itemE.getItemNumOfLikes());
            userItemList.add(userItemVec);
            dateString = "";
        }
        return userItemList;
    }
    
    /* MISCELLANEOUS METHODS */
    public Long getPendingItemOfferCount(long itemID) {
        Long pendingItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.itemOfferStatus = 'Pending'");
        q.setParameter("itemID", itemID);
        try {
            pendingItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getPendingItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return pendingItemOfferCount;
    }
    
    public Long getRejectedItemOfferCount(long itemID) {
        Long rejectedItemOfferCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(o.itemOfferID) FROM ItemOffer o WHERE o.itemEntity.itemID = :itemID AND o.itemOfferStatus = 'Pending'");
        q.setParameter("itemID", itemID);
        try {
            rejectedItemOfferCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in UserProfileSysUserMgrBean.getRejectedItemOfferCount().getSingleResult()");
            ex.printStackTrace();
        }
        return rejectedItemOfferCount;
    }
}