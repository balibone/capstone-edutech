package unifysessionbeans.systemuser;

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

import commoninfrastructureentities.UserEntity;
import unifyentities.common.CategoryEntity;
import unifyentities.marketplace.ItemEntity;

@Stateless
public class MarketplaceSysUserMgrBean implements MarketplaceSysUserMgrBeanRemote {

    @PersistenceContext
    private EntityManager em;

    private CategoryEntity cEntity;
    private ItemEntity iEntity;
    private UserEntity uEntity;
    
    @Override
    public List<Vector> viewItemList() {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemStatus = 'Available' AND "
                + "i.categoryEntity.categoryActiveStatus = '1'");
        List<Vector> itemList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector itemVec = new Vector();

            itemVec.add(itemE.getItemID());
            itemVec.add(itemE.getItemImage());
            itemVec.add(itemE.getItemName());
            itemVec.add(itemE.getCategoryEntity().getCategoryName());
            itemVec.add(itemE.getUserEntity().getUsername());

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
            itemVec.add(dateString);
            itemVec.add(itemE.getItemPrice());
            itemVec.add(itemE.getItemNumOfLikes());
            itemList.add(itemVec);
            dateString = "";
        }
        return itemList;
    }

    @Override
    public Vector viewItemDetails(long itemID) {
        iEntity = lookupItem(itemID);
        Vector itemDetailsVec = new Vector();

        if (iEntity != null) {
            itemDetailsVec.add(iEntity.getItemID());
            itemDetailsVec.add(iEntity.getItemName());
            itemDetailsVec.add(iEntity.getCategoryEntity().getCategoryName());
            itemDetailsVec.add(iEntity.getItemPrice());
            itemDetailsVec.add(iEntity.getItemCondition());
            itemDetailsVec.add(iEntity.getItemDescription());
            itemDetailsVec.add(iEntity.getItemImage());
            itemDetailsVec.add(iEntity.getItemStatus());
            itemDetailsVec.add(iEntity.getItemNumOfLikes());
            itemDetailsVec.add(iEntity.getItemPostingDate());
            itemDetailsVec.add(iEntity.getTradeLocation());
            itemDetailsVec.add(iEntity.getTradeLat());
            itemDetailsVec.add(iEntity.getTradeLong());
            itemDetailsVec.add(iEntity.getTradeInformation());
            itemDetailsVec.add(iEntity.getUserEntity().getUsername());
            itemDetailsVec.add(iEntity.getUserEntity().getImgFileName());
            itemDetailsVec.add(iEntity.getUserEntity().getUserCreationDate());
            itemDetailsVec.add(iEntity.getCategoryEntity().getCategoryID());
            return itemDetailsVec;
        }
        return null;
    }

    @Override
    public List<Vector> viewAssocCategoryItemList(String hiddenCategoryName, long hiddenItemID) {
        Date currentDate = new Date();
        String dateString = "";

        Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemStatus = 'Available' AND "
                + "i.categoryEntity.categoryActiveStatus = '1' AND "
                + "i.categoryEntity.categoryName = :hiddenCategoryName");
        q.setParameter("hiddenCategoryName", hiddenCategoryName);
        List<Vector> assocCategoryItemList = new ArrayList<Vector>();

        for (Object o : q.getResultList()) {
            ItemEntity itemE = (ItemEntity) o;
            Vector assocCategoryItemVec = new Vector();

            if (itemE.getItemID() != hiddenItemID) {
                assocCategoryItemVec.add(itemE.getItemID());
                assocCategoryItemVec.add(itemE.getItemImage());
                assocCategoryItemVec.add(itemE.getItemName());
                assocCategoryItemVec.add(itemE.getCategoryEntity().getCategoryName());
                assocCategoryItemVec.add(itemE.getUserEntity().getUsername());

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
                assocCategoryItemVec.add(dateString);
                assocCategoryItemVec.add(itemE.getItemPrice());
                assocCategoryItemList.add(assocCategoryItemVec);
                dateString = "";
            }
        }
        return assocCategoryItemList;
    }
    
    @Override
    public String createItemListing(String itemName, double itemPrice, String itemCondition, 
            String itemDescription, String itemImagefileName, long categoryID, String username, 
            String tradeLocation, String tradeLat, String tradeLong, String tradeInformation) {
        if (lookupUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupCategory(categoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            uEntity = lookupUser(username);
            cEntity = lookupCategory(categoryID);
            iEntity = new ItemEntity();
            
            if(iEntity.createItemListing(itemName, itemPrice, itemCondition, itemDescription, itemImagefileName, 
                    tradeLocation, tradeLat, tradeLong, tradeInformation)) {
                iEntity.setCategoryEntity(cEntity);
                iEntity.setUserEntity(uEntity);
                em.persist(iEntity);
                return "Item has been listed successfully!";
            } else {
                return "Error occured while listing the item. Please try again.";
            }
        }
    }
    
    @Override
    public String editItemListing(long itemID, String itemName, double itemPrice, String itemCondition, 
            String itemDescription, String itemImageFileName, long itemCategoryID, String username, 
            String tradeLocation, String tradeLat, String tradeLong, String tradeInformation) {
        if (lookupUser(username) == null) { return "There are some issues with your profile. Please try again."; }
        else if (lookupItem(itemID) == null) { return "There are some issues with your item listing. Please try again."; }
        else if (lookupCategory(itemCategoryID) == null) { return "Selected category cannot be found. Please try again."; }
        else {
            uEntity = lookupUser(username);
            iEntity = lookupItem(itemID);
            cEntity = lookupCategory(itemCategoryID);
            
            iEntity.setItemName(itemName);
            iEntity.setItemPrice(itemPrice);
            iEntity.setItemCondition(itemCondition);
            iEntity.setItemDescription(itemDescription);
            iEntity.setItemImage(itemImageFileName);
            iEntity.setCategoryEntity(cEntity);
            iEntity.setUserEntity(uEntity);
            iEntity.setTradeLocation(tradeLocation);
            iEntity.setTradeLat(tradeLat);
            iEntity.setTradeLong(tradeLong);
            iEntity.setTradeInformation(tradeInformation);
            em.merge(iEntity);
            return "Item listing has been updated successfully!";
        }
    }
    
    @Override
    public String deleteItemListing(long itemIDToDelete) {
        if (lookupItem(itemIDToDelete) == null) { return "There are some issues with your item listing. Please try again."; }
        else {
            iEntity = lookupItem(itemIDToDelete);
            em.remove(iEntity);
            em.flush();
            em.clear();
            return "Item listing has been deleted successfully!";
        }
    }
    
    @Override
    public List<Vector> viewItemCategoryList(){
        Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryType = 'marketplace' "
                + "AND c.categoryActiveStatus = '1'");
        List<Vector> itemCategoryList = new ArrayList<Vector>();
        
        for (Object o: q.getResultList()){
            CategoryEntity categoryE = (CategoryEntity) o;
            Vector itemCategoryVec = new Vector();
            
            itemCategoryVec.add(categoryE.getCategoryImage());
            itemCategoryVec.add(categoryE.getCategoryID());
            itemCategoryVec.add(categoryE.getCategoryName());
            itemCategoryList.add(itemCategoryVec);
        }
        return itemCategoryList;
    }

    /* MISCELLANEOUS METHODS */
    public CategoryEntity lookupCategory(long categoryID) {
        CategoryEntity ce = new CategoryEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryID = :categoryID");
            q.setParameter("categoryID", categoryID);
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
    
    public ItemEntity lookupItem(long itemID) {
        ItemEntity ie = new ItemEntity();
        try {
            Query q = em.createQuery("SELECT i FROM Item i WHERE i.itemID = :itemID");
            q.setParameter("itemID", itemID);
            ie = (ItemEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Item cannot be found. " + enfe.getMessage());
            em.remove(ie);
            ie = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Item does not exist. " + nre.getMessage());
            em.remove(ie);
            ie = null;
        }
        return ie;
    }
    
    public UserEntity lookupUser(String username) {
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