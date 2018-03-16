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
import commoninfrastructureentities.UserEntity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

@Stateless
public class ShoutsSysUserMgrBean implements ShoutsSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;

    private CategoryEntity categoryEntity;
    private ShoutsEntity shoutsEntity;
    private UserEntity userEntity;
    
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
            shoutVec.add(getShoutsCommentsCount(shoutE.getShoutID()));
            
            System.out.println(shoutVec.size());
            shoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return shoutList;
    }

    @Override
    public String createShout(String shoutContent, String shoutPoster) {
        
        shoutsEntity = new ShoutsEntity();
        
        //System.out.println(shoutPoster);
        
        userEntity = lookupUnifyUser(shoutPoster);
        
            if (shoutsEntity.createShout(shoutContent)) {
                shoutsEntity.setUserEntity(userEntity);
                
                //temp
                shoutsEntity.setShoutEditedDate();
                shoutsEntity.setShoutLat("0");
                shoutsEntity.setShoutLong("0");
                
                em.persist(shoutsEntity);
                System.out.println("Shout created (ShoutsSysUserMgrBean.createShout)");
                return "Shout has been posted successfully!";
            } else {
                return "System is not feeling well and cannot shout at the moment :'(' Please try again later.";
            }
        
    }
    
    // SHOUTS LIKES COUNT
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
    
    // SHOUTS COMMENTS COUNT
    public Long getShoutsCommentsCount(long shoutID) {
        Long shoutsLikesCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.commentID) FROM ShoutsComments l WHERE l.shoutsEntity.shoutID = :shoutID");
        q.setParameter("shoutID", shoutID);
        try {
            shoutsLikesCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in ShoutsSysUserMgrBean.getShoutsCommentsCount().getSingleResult()");
            ex.printStackTrace();
        }
        return shoutsLikesCount;
    }
 
    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try{
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity)q.getSingleResult();
            System.out.println("FOUND USER");
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
