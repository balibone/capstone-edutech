package unifysessionbeans.systemuser;

import commoninfrastructureentities.UserEntity;
import java.lang.reflect.Array;
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
        Query q = em.createQuery("SELECT c FROM Shouts c WHERE c.shoutStatus='Active'");
        List<Vector> shoutList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()) {
            ShoutsEntity shoutE = (ShoutsEntity) o;
            
            Vector shoutVec = new Vector();
            
            shoutVec.add(shoutE.getShoutID());
            shoutVec.add(shoutE.getShoutDate());
            shoutVec.add(shoutE.getShoutStatus());
            shoutVec.add(shoutE.getShoutContent());
            shoutVec.add(shoutE.getShoutLat());
            shoutVec.add(shoutE.getShoutLong());
            shoutVec.add(shoutE.getUserEntity().getUsername());
            shoutVec.add(shoutE.getShoutEditedDate());
            
            System.out.println(shoutVec.size());
            shoutList.add(shoutVec);
        }
        System.out.println("ViewShoutList retrieved");
        return shoutList;
    }

}
