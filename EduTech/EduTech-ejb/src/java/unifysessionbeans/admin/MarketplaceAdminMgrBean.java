package unifysessionbeans.admin;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import unifyentities.marketplace.ItemEntity;
import unifyentities.marketplace.ItemTransactionEntity;
import unifyentities.common.CategoryEntity;
import unifyentities.common.MessageEntity;

@Stateless
public class MarketplaceAdminMgrBean implements MarketplaceAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private CategoryEntity cEntity;
    private ItemEntity iEntity;
    private ItemTransactionEntity itEntity;
    private MessageEntity mEntity;
    
    private Collection<ItemEntity> itemSet;

    @Override
    public List<Vector> viewItemCategoryList(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'marketplace'");
        List<Vector> itemCategoryList = new ArrayList<Vector>();
        
        for (Object o: q.getResultList()){
            CategoryEntity categoryE = (CategoryEntity) o;
            Vector itemCategoryVec = new Vector();
            
            itemCategoryVec.add(categoryE.getCategoryImage());
            itemCategoryVec.add(categoryE.getCategoryName());
            itemCategoryVec.add(categoryE.getCategoryDescription());
            itemCategoryVec.add(categoryE.getCategoryActiveStatus());
            itemCategoryList.add(itemCategoryVec);
        }
        return itemCategoryList;
    }
    
    @Override
    public Vector viewItemCategoryDetails(String urlCategoryName, String urlCategoryType) {
        cEntity = lookupItemCategory(urlCategoryName, urlCategoryType);
        Vector itemCategoryDetailsVec = new Vector();
        
        if (cEntity != null) {
            itemCategoryDetailsVec.add(cEntity.getCategoryImage());
            itemCategoryDetailsVec.add(cEntity.getCategoryName());
            itemCategoryDetailsVec.add(cEntity.getCategoryType());
            itemCategoryDetailsVec.add(cEntity.getCategoryDescription());
            itemCategoryDetailsVec.add(cEntity.getCategoryActiveStatus());
            return itemCategoryDetailsVec;
        }
        return null;
    }
    
    /* VIEW ASSOCIATED ITEM LIST IN A PARTICULAR ITEM CATEGORY */
    @Override
    public List<Vector> viewAssociatedItemList(String urlCategoryName, String urlCategoryType){
        cEntity = lookupItemCategory(urlCategoryName, urlCategoryType);
        
        Query q = em.createQuery("SELECT i FROM Item i WHERE i.categoryEntity.categoryName = :itemCategoryName");
        q.setParameter("itemCategoryName", cEntity.getCategoryName());
        List<Vector> itemList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            ItemEntity itemE = (ItemEntity) o;
            Vector itemVec = new Vector();
            
            itemVec.add(itemE.getItemImage());
            itemVec.add(itemE.getItemName());
            itemVec.add(itemE.getUserEntity().getUsername());
            itemVec.add(itemE.getItemPrice());
            itemVec.add(itemE.getItemStatus());
            itemList.add(itemVec);
        }
        return itemList;
    }
    
    @Override
    public boolean createItemCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage) {
        cEntity = new CategoryEntity();
        cEntity.createCategory(categoryName, categoryType, categoryDescription, categoryImage);
        itemSet = new ArrayList<ItemEntity>();
        cEntity.setItemSet(itemSet);
        em.persist(cEntity);
        return true;
    }
    
    @Override
    public boolean updateItemCategory(String oldCategoryName, String newCategoryName, String categoryType, 
            String oldCategoryDescription, String newCategoryDescription, String fileName) {
        /* DOES NOT MATTER WHETHER OR NOT THERE IS ITEMS INSIDE THE ITEM CATEGORY, ADMIN CAN JUST UPDATE THE ITEM CATEGORY DETAILS */
        boolean icUpdateStatus = true;
        if (lookupItemCategory(oldCategoryName, categoryType) == null) {
            icUpdateStatus = false;
        } else {
            cEntity = lookupItemCategory(oldCategoryName, categoryType);
            cEntity.setCategoryName(newCategoryName);
            cEntity.setCategoryDescription(newCategoryDescription);
            cEntity.setCategoryImage(fileName);
            em.merge(cEntity);
        }
        return icUpdateStatus;
    }
    
    @Override
    public String deactivateAnItemCategory(String deactCategoryName, String deactCategoryType) {
        /* DON'T CHANGE THE RETURN STRING (USED FOR SERVLET VALIDATION) */
        boolean itemAvailWithinCat = false;
        
        if (lookupItemCategory(deactCategoryName, deactCategoryType) == null) {
            return "Selected item category cannot be found.";
        } else {
            cEntity = em.find(CategoryEntity.class, lookupItemCategory(deactCategoryName, deactCategoryType).getCategoryID());
            itemSet = cEntity.getItemSet();
            
            /* IF THERE IS ITEM INSIDE THE ITEM CATEGORY */
            if (!itemSet.isEmpty()) {
                for (ItemEntity ie : itemSet) {
                    /* IF THE ITEM INSIDE THE ITEM CATEGORY IS "AVAILABLE", THEN CANNOT DEACTIVATE THE ITEM CATEGORY */
                    if((ie.getItemStatus()).equals("Available")) {
                        itemAvailWithinCat = true;
                        break;
                    }
                }
                if (itemAvailWithinCat == true) {
                    return "There are currently active items inside this item category. Cannot be deactivated.";
                } else {
                    return "Selected item category has been deactivated successfully!";
                }
            }
            /* IF THERE IS NO ITEMS INSIDE THE ITEM CATEGORY, PROCEED TO DEACTIVATE THE ITEM CATEGORY */
            else{
                cEntity.setCategoryActiveStatus(false);
                em.merge(cEntity);
                return "Selected item category has been deactivated successfully!";
            }
        }
    }
    
    @Override
    public boolean activateAnItemCategory(String actCategoryName, String actCategoryType) {
        boolean icDeactivateStatus = true;
        if (lookupItemCategory(actCategoryName, actCategoryType) == null) {
            icDeactivateStatus = false;
        } else {
            cEntity = lookupItemCategory(actCategoryName, actCategoryType);
            cEntity.setCategoryActiveStatus(true);
            em.merge(cEntity);
        }
        return icDeactivateStatus;
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
            itemVec.add(itemE.getCategoryEntity().getCategoryName());
            itemVec.add(itemE.getUserEntity().getUsername());
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
            itemDetailsVec.add(iEntity.getCategoryEntity().getCategoryName());
            itemDetailsVec.add(iEntity.getUserEntity().getUsername());
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
    
    @Override
    public boolean deleteAnItem(String hiddenItemName, String hiddenSellerID) {
        /* ADMIN CAN JUST DELETE THE ITEM IMMEDIATELY */
        boolean itemDeleteStatus = true;
        if (lookupItem(hiddenItemName, hiddenSellerID) == null) {
            itemDeleteStatus = false;
        } else {
            iEntity = lookupItem(hiddenItemName, hiddenSellerID);
            em.remove(iEntity);
            em.flush();
            em.clear();
        }
        return itemDeleteStatus;
    }
    
    @Override
    public String getItemTransTodayCount() {
        Query q = em.createQuery("SELECT COUNT(t) FROM ItemTransaction t");
        return String.valueOf(q.getFirstResult());
    }
    @Override
    public String getItemListingCount() {
        Query q = em.createQuery("SELECT COUNT(i) FROM Item i");
        return String.valueOf(q.getFirstResult());
    }
    
    /* MISCELLANEOUS METHODS */
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
    
    public CategoryEntity lookupItemCategory(String categoryName, String categoryType) {
        CategoryEntity ce = new CategoryEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryName AND c.categoryType = :categoryType");
            q.setParameter("categoryName", categoryName);
            q.setParameter("categoryType", categoryType);
            ce = (CategoryEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Category cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Category does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    @Override
    public void createSystemMessage(String hiddenItemName, String hiddenSellerID) {
        mEntity = new MessageEntity();
        String messageContent = "Dear " + hiddenSellerID + ", your '" + hiddenItemName + "' listing has been "
                + "removed due to inappropriate content.";
        mEntity.createSystemMessage("SystemAdmin", hiddenSellerID, messageContent, "System");
        em.persist(mEntity);
    }
}