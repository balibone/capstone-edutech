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

@Stateless
public class UserProfileSysUserMgrBean implements UserProfileSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
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
}