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
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import unifyentities.common.MessageEntity;
import commoninfrastructureentities.UserEntity;

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

    @Override
    public List<Vector> viewUserMessageListTopFive(String username) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT m FROM Message m WHERE m.messageReceiverID = :username ORDER BY m.messageDate DESC");
        q.setParameter("username", username);
        List<Vector> messageList = new ArrayList<Vector>();
        
        for (Object o : q.setMaxResults(5).getResultList()) {
            MessageEntity messageE = (MessageEntity) o;
            Vector messageVec = new Vector();
            
            messageVec.add(messageE.getMessageContent());
            messageVec.add(messageE.getContentID());
            messageVec.add(messageE.getMessageType());
            if(messageE.getMessageType().equals("System")) { messageVec.add("System Admin"); }
            else { messageVec.add(messageE.getUserEntity().getUsername()); }
            if(messageE.getMessageType().equals("System")) { messageVec.add("edubox-box.png"); }
            else { messageVec.add(messageE.getUserEntity().getImgFileName()); }
            
            long diff = currentDate.getTime() - messageE.getMessageDate().getTime();
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
            messageVec.add(dateString);
            messageList.add(messageVec);
        }
        return messageList;
    }
    
    @Override
    public List<Vector> viewUserMessageList(String username) {
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT m FROM Message m WHERE m.messageReceiverID = :username ORDER BY m.messageDate DESC");
        q.setParameter("username", username);
        List<Vector> messageList = new ArrayList<Vector>();
        
        for (Object o : q.getResultList()) {
            MessageEntity messageE = (MessageEntity) o;
            Vector messageVec = new Vector();
            
            messageVec.add(messageE.getMessageContent());
            messageVec.add(messageE.getContentID());
            messageVec.add(messageE.getMessageType());
            if(messageE.getMessageType().equals("System")) { messageVec.add("System Admin"); }
            else { messageVec.add(messageE.getUserEntity().getUsername()); }
            if(messageE.getMessageType().equals("System")) { messageVec.add("edubox-box.png"); }
            else { messageVec.add(messageE.getUserEntity().getImgFileName()); }
            messageVec.add(df.format(messageE.getMessageDate()));
            
            long diff = currentDate.getTime() - messageE.getMessageDate().getTime();
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
            messageVec.add(dateString);
            messageList.add(messageVec);
        }
        return messageList;
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