package unifysessionbeans.systemuser;

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
public class MarketplaceSysUserMgrBean implements MarketplaceSysUserMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Vector> viewItemList(){
        Date currentDate = new Date();
        String dateString = "";
        
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemStatus = 'Available'");
        List<Vector> itemList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            ItemEntity itemE = (ItemEntity) o;
            Vector itemVec = new Vector();
            
            itemVec.add(itemE.getItemImage());
            itemVec.add(itemE.getItemName());
            itemVec.add(itemE.getItemCategory());
            itemVec.add(itemE.getItemSellerID());
            
            long diff = currentDate.getTime() - itemE.getItemPostingDate().getTime();
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            
            if(diffDays != 0) { 
                dateString = diffDays + " day";
                if(diffDays == 1) { dateString += " ago";}
                else { dateString += "s ago"; }
            }
            else if(diffHours != 0) { 
                dateString = diffHours + " hour"; 
                if(diffHours == 1) { dateString += " ago";}
                else { dateString += "s ago"; }
            }
            else if(diffMinutes != 0) { 
                dateString = diffMinutes + " minute";
                if(diffMinutes == 1) { dateString += " ago";}
                else { dateString += "s ago"; }
            }
            else if(diffSeconds != 0) { 
                dateString = diffSeconds + " second";
                if(diffSeconds == 1) { dateString += " ago";}
                else { dateString += "s ago"; }
            }
            
            itemVec.add(dateString);
            itemVec.add(itemE.getItemPrice());
            itemList.add(itemVec);
            dateString = "";
        }
        return itemList;
    }
}
