package unifysessionbeans.systemuser;

import commoninfrastructureentities.UserEntity;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import unifyentities.common.CategoryEntity;
import unifyentities.shouts.ShoutsEntity;

@Stateless
public class ShoutsSysUserMgrBean implements ShoutsSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;

    private CategoryEntity categoryEntity;
    private ShoutsEntity shoutsEntity;
    
    public List<Vector> viewShoutList() {
        Query q = em.createQuery("SELECT c FROM Shouts c WHERE c.shoutStatus='Active' ORDER BY c.shoutDate DESC");
        List<Vector> shoutList = new ArrayList<Vector>();
        
        Date currentDate = new Date();
        String dateString = "";
        
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        
        for(Object o: q.getResultList()) {
            ShoutsEntity shoutE = (ShoutsEntity) o;
            
            Vector shoutVec = new Vector();
            
            shoutVec.add(shoutE.getShoutID());
            shoutVec.add(df.format(shoutE.getShoutDate()));
            shoutVec.add(shoutE.getShoutStatus());
            shoutVec.add(shoutE.getShoutContent());
            shoutVec.add(shoutE.getShoutLat());
            shoutVec.add(shoutE.getShoutLong());
            shoutVec.add(shoutE.getUserEntity().getUsername());
            shoutVec.add(df.format(shoutE.getShoutEditedDate()));
            
            //find listing posted time from current time
            long diff = currentDate.getTime() - shoutE.getShoutDate().getTime();
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
            
            shoutVec.add(dateString);
            shoutVec.add(getShoutsLikesCount(shoutE.getShoutID()));
            
            System.out.println(shoutVec.size());
            shoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return shoutList;
    }

    /* MISCELLANEOUS METHODS (ITEM LIKE) */
    public Long getShoutsLikesCount(long shoutID) {
        Long shoutsLikesCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.likeID) FROM ShoutsLikes l WHERE l.shoutsEntity.shoutID = :shoutID");
        q.setParameter("shoutID", shoutID);
        try {
            shoutsLikesCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ShoutsSysUserMgrBean.getShoutsLikesCount().getSingleResult()");
            ex.printStackTrace();
        }
        return shoutsLikesCount;
    }
    
}
