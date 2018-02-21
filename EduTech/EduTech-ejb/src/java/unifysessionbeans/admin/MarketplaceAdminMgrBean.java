/***************************************************************************************
*   Title:                  MarketplaceAdminMgrBean.java
*   Purpose:                LIST OF MANAGER BEAN METHODS FOR UNIFY MARKETPLACE - ADMIN (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

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
import unifyentities.common.CategoryEntity;
import unifyentities.common.MessageEntity;

@Stateless
public class MarketplaceAdminMgrBean implements MarketplaceAdminMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private CategoryEntity cEntity;
    private ItemEntity iEntity;
    private MessageEntity mEntity;
    
    private Collection<ItemEntity> itemSet;

    @Override
    public List<Vector> viewItemCategoryList(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'Marketplace'");
        List<Vector> itemCategoryList = new ArrayList<Vector>();
        
        for (Object o: q.getResultList()){
            CategoryEntity categoryE = (CategoryEntity) o;
            Vector itemCategoryVec = new Vector();
            
            itemCategoryVec.add(categoryE.getCategoryID());
            itemCategoryVec.add(categoryE.getCategoryImage());
            itemCategoryVec.add(categoryE.getCategoryName());
            itemCategoryVec.add(categoryE.getCategoryDescription());
            itemCategoryVec.add(categoryE.getCategoryActiveStatus());
            itemCategoryList.add(itemCategoryVec);
        }
        return itemCategoryList;
    }
    
    @Override
    public Vector viewItemCategoryDetails(long itemCategoryID) {
        cEntity = lookupItemCategory(itemCategoryID);
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
    public List<Vector> viewAssociatedItemList(long itemCategoryID){
        cEntity = lookupItemCategory(itemCategoryID);
        
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
    public String createItemCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage) {
        cEntity = new CategoryEntity();
        if(cEntity.createCategory(categoryName, categoryType, categoryDescription, categoryImage)) {
            itemSet = new ArrayList<ItemEntity>();
            cEntity.setItemSet(itemSet);
            em.persist(cEntity);
            return "Item category has been listed successfully!";
        } else {
            return "There were some issues encountered while creating the item category. Please try again.";
        }
    }
    
    @Override
    public String updateItemCategory(long itemCategoryID, String categoryName, String categoryDescription, String fileName) {
        /* DOES NOT MATTER WHETHER OR NOT THERE IS ITEMS INSIDE THE ITEM CATEGORY, ADMIN CAN JUST UPDATE THE ITEM CATEGORY DETAILS */
        if (lookupItemCategory(itemCategoryID) == null) {
            return "Selected item category cannot be found. Please try again.";
        } else {
            cEntity = lookupItemCategory(itemCategoryID);
            cEntity.setCategoryName(categoryName);
            cEntity.setCategoryDescription(categoryDescription);
            cEntity.setCategoryImage(fileName);
            em.merge(cEntity);
            return "Selected item category has been updated successfully!";
        }
    }
    
    @Override
    public String deactivateAnItemCategory(long itemCategoryID) {
        /* DON'T CHANGE THE RETURN STRING (USED FOR SERVLET VALIDATION) */
        boolean itemAvailWithinCat = false;
        if (lookupItemCategory(itemCategoryID) == null) {
            return "Selected item category cannot be found. Please try again.";
        } else {
            cEntity = em.find(CategoryEntity.class, lookupItemCategory(itemCategoryID).getCategoryID());
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
                    return "There are active items currently inside this item category. Cannot be deactivated.";
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
    public String activateAnItemCategory(long itemCategoryID) {
        if (lookupItemCategory(itemCategoryID) == null) {
            return "Selected item category cannot be found. Please try again.";
        } else {
            cEntity = lookupItemCategory(itemCategoryID);
            cEntity.setCategoryActiveStatus(true);
            em.merge(cEntity);
            return "Selected item category has been activated successfully!";
        }
    }
    
    @Override
    public List<Vector> viewItemList(){
        Query q = em.createQuery("SELECT i FROM Item i");
        List<Vector> itemList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            ItemEntity itemE = (ItemEntity) o;
            Vector itemVec = new Vector();
            
            itemVec.add(itemE.getItemID());
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
    public Vector viewItemDetails(long urlItemID) {
        iEntity = lookupItem(urlItemID);
        Vector itemDetailsVec = new Vector();
        
        if (iEntity != null) {
            itemDetailsVec.add(iEntity.getItemImage());
            itemDetailsVec.add(iEntity.getItemName());
            itemDetailsVec.add(iEntity.getCategoryEntity().getCategoryName());
            itemDetailsVec.add(iEntity.getUserEntity().getUsername());
            itemDetailsVec.add(iEntity.getItemPrice());
            itemDetailsVec.add(iEntity.getItemCondition());
            itemDetailsVec.add(iEntity.getItemDescription());
            itemDetailsVec.add(iEntity.getItemStatus());
            itemDetailsVec.add(iEntity.getItemNumOfLikes());
            itemDetailsVec.add(iEntity.getItemPostingDate());
            itemDetailsVec.add(iEntity.getTradeLocation());
            itemDetailsVec.add(iEntity.getTradeLat());
            itemDetailsVec.add(iEntity.getTradeLong());
            itemDetailsVec.add(iEntity.getTradeInformation());
            return itemDetailsVec;
        }
        return null;
    }
    
    @Override
    public String deleteAnItem(long urlItemID) {
        /* ADMIN CAN JUST DELETE THE ITEM IMMEDIATELY */
        if (lookupItem(urlItemID) == null) {
            return "Selected item cannot be found. Please try again.";
        } else {
            iEntity = lookupItem(urlItemID);
            em.remove(iEntity);
            em.flush();
            em.clear();
            return "Selected item has been deleted successfully (A system notification has been sent to the seller)!";
        }
    }
    
    /* METHODS FOR UNIFY ADMIN DASHBOARD */
    @Override
    public Long getItemTransTodayCount() {
        Long itemTransTodayCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(t.itemTransactionID) FROM ItemTransaction t");
        try {
            itemTransTodayCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getItemTransTodayCount().getSingleResult()");
            ex.printStackTrace();
        }
        return itemTransTodayCount;
    }
    @Override
    public Long getActiveItemCategoryListCount() {
        Long activeItemCategoryListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.categoryName) FROM Category c WHERE c.categoryType = 'Marketplace' AND c.categoryActiveStatus='1'");
        try {
            activeItemCategoryListCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getActiveItemCategoryListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return activeItemCategoryListCount;
    }
    @Override
    public Long getInactiveItemCategoryListCount() {
        Long inactiveItemCategoryListCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(DISTINCT c.categoryName) FROM Category c WHERE c.categoryType = 'Marketplace' AND c.categoryActiveStatus='0'");
        try {
            inactiveItemCategoryListCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getInactiveItemCategoryListCount().getSingleResult()");
            ex.printStackTrace();
        }
        return inactiveItemCategoryListCount;
    }
    @Override
    public Long getItemListingCount() {
        Long itemListingCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(i.itemID) FROM Item i");
        try {
            itemListingCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getItemListingCount().getSingleResult()");
            ex.printStackTrace();
        }
        return itemListingCount;
    }
    @Override
    public Long getAvailableItemListingCount() {
        Long availableItemListingCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(i.itemID) FROM Item i WHERE i.itemStatus = 'Available'");
        try {
            availableItemListingCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getAvailableItemListingCount().getSingleResult()");
            ex.printStackTrace();
        }
        return availableItemListingCount;
    }
    @Override
    public Long getReservedItemListingCount() {
        Long reservedItemListingCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(i.itemID) FROM Item i WHERE i.itemStatus = 'Reserved'");
        try {
            reservedItemListingCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getReservedItemListingCount().getSingleResult()");
            ex.printStackTrace();
        }
        return reservedItemListingCount;
    }
    @Override
    public Long getSoldItemListingCount() {
        Long soldItemListingCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(i.itemID) FROM Item i WHERE i.itemStatus = 'Sold'");
        try {
            soldItemListingCount = (Long)q.getSingleResult();
        } catch(Exception ex) {
            System.out.println("Exception in MarketplaceAdminMgrBean.getSoldItemListingCount().getSingleResult()");
            ex.printStackTrace();
        }
        return soldItemListingCount;
    }
    
    /* MISCELLANEOUS METHODS */
    public ItemEntity lookupItem(long urlItemID){
        ItemEntity ie = new ItemEntity();
        try{
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemID = :urlItemID");
            q.setParameter("urlItemID", urlItemID);
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
    public CategoryEntity lookupItemCategory(long itemCategoryID) {
        CategoryEntity ce = new CategoryEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID = :itemCategoryID");
            q.setParameter("itemCategoryID", itemCategoryID);
            ce = (CategoryEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Item category cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Item category does not exist. " + nre.getMessage());
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