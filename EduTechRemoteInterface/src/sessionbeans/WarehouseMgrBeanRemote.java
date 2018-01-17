/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

/**
 *
 * @author Derian
 */
@Remote
public interface WarehouseMgrBeanRemote {
    public List<Vector> getItemListingNames();
    public boolean createInventoryLog(String userNRIC, String logDate, String logReason, String logDescription, 
            String[] itemNameArr, String[] itemSKUArr, String[] itemQtyArr, String[] itemQtyAdjustArr);
    public List<Vector> viewInventoryLogList();
    public boolean createCompositeItem(String compositeName, String compositeSKU, String compositeSellPrice, 
            String compositeRebundleLvl,  String compositeDescription, String fileName, String[] itemNameArr, 
            String[] itemSKUArr, String[] itemQtyRequiredArr);
    public List<Vector> viewCompositeItemList();
    public Vector getCompositeItemInfo(String compositeIdentifier);
    public List<Vector> getAssocCompItemInventoryInfo(String compositeIdentifier);
    
    public List<Vector> viewInvoiceList();
    public boolean createInventoryCategory(String newCategoryName,String newCategoryDesc,ArrayList<String> sCats);
    public List<Vector> viewAllInventoryCategories();
    public void modifyInventoryCategory(String categoryName,String updatedCategoryDesc,ArrayList<String> sCats);

    public List<Vector> viewSalesOrderlist();
    public boolean createItem(String itemImageDirPath, String itemSKU, String itemName, String itemDescription, String itemQuantity, String itemReorderLevel, String itemSellingPrice, String vendorID, String vendorProductCode);
    public ArrayList<ArrayList> viewItemList();
    public ArrayList viewItem(String itemSKU);
    public void deleteItem(String itemSKU);
    public boolean editItem(String itemImageDirPath, String itemSKU, String itemName, String itemDescription, String itemQuantity, String itemReorderLevel, String itemSellingPrice, String vendorID, String vendorProductCode);
}
