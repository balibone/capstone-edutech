package unifysessionbeans.admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import unifyentities.marketplace.ItemEntity;
import unifyentities.common.CategoryEntity;

@Stateless
public class MarketplaceAdminMgrBean implements MarketplaceAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private ItemEntity iEntity;
    
    @Override
    public List<Vector> viewItemCategoryList(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'marketplace'");
        List<Vector> categoryList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            CategoryEntity categoryE = (CategoryEntity) o;
            Vector categoryVec = new Vector();
            
            categoryVec.add(categoryE.getCategoryImage());
            categoryVec.add(categoryE.getCategoryName());
            categoryVec.add(categoryE.getCategoryType());
            categoryVec.add(categoryE.getCategoryDescription());
            categoryList.add(categoryVec);
        }
        return categoryList;
    }
    
    @Override
    public List<Vector> viewItemList(){
        Query q = em.createQuery("SELECT i FROM Item i");
        List<Vector> itemList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            ItemEntity itemE = (ItemEntity) o;
            Vector itemVec = new Vector();
            
            itemVec.add(itemE.getItemImage());
            itemVec.add(itemE.getItemName());
            itemVec.add(itemE.getItemCategory());
            itemVec.add(itemE.getItemSellerID());
            itemVec.add(itemE.getItemPrice());
            itemVec.add(itemE.getItemStatus());
            itemList.add(itemVec);
        }
        return itemList;
    }
    
    @Override
    public Vector viewItemDetails(String itemName, String itemSellerID) {
        iEntity = lookupItem(itemName, itemSellerID);
        Vector itemDetailsVec = new Vector();
        
        if (iEntity != null) {
            itemDetailsVec.add(iEntity.getItemImage());
            itemDetailsVec.add(iEntity.getItemName());
            itemDetailsVec.add(iEntity.getItemCategory());
            itemDetailsVec.add(iEntity.getItemSellerID());
            itemDetailsVec.add(iEntity.getItemPrice());
            itemDetailsVec.add(iEntity.getItemDescription());
            itemDetailsVec.add(iEntity.getItemStatus());
            itemDetailsVec.add(iEntity.getItemPostingDate());
            itemDetailsVec.add(iEntity.getTradeLocation());
            itemDetailsVec.add(iEntity.getTradeLat());
            itemDetailsVec.add(iEntity.getTradeLong());
            return itemDetailsVec;
        }
        return null;
    }
    
    public ItemEntity lookupItem(String itemName, String itemSellerID){
        ItemEntity ie = new ItemEntity();
        try{
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemName = :itemName AND i.itemSellerID = :itemSellerID");
            q.setParameter("itemName", itemName);
            q.setParameter("itemSellerID", itemSellerID);
            ie = (ItemEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Item cannot be found. " + enfe.getMessage());
            em.remove(ie);
            ie = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Item does not exist. " + nre.getMessage());
            em.remove(ie);
            ie = null;
        }
        return ie;
    }
}
