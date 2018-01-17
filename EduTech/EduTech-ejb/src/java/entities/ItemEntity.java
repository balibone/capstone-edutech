package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Item")
public class ItemEntity implements Serializable {
    @Id
    private String itemSKU;
    private String itemName;
    private String itemDescription;
    private Double itemQuantity;
    private Double itemReorderLevel;
    private Double itemSellingPrice;
    private String itemImageDirPath;
    private String vendorID;
    private String vendorProductCode;
    private boolean activeStatus;
    private ArrayList<String> subCategories;
    
    /* DEFAULT CONSTRUCTOR */
    public ItemEntity(){ this.activeStatus = true;}
    public ItemEntity(String itemSKU, String itemName, String itemDescription, Double itemQuantity, Double itemReorderLevel, Double itemSellingPrice, String itemImageDirPath, String vendorID, String vendorProductCode) {
        this.activeStatus=true;
        this.itemSKU = itemSKU;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemReorderLevel = itemReorderLevel;
        this.itemSellingPrice = itemSellingPrice;
        this.itemImageDirPath = itemImageDirPath;
        this.vendorID = vendorID;
        this.vendorProductCode = vendorProductCode;
    }
    
    /* GETTER METHODS */
    public String getItemSKU() { return itemSKU; }
    public String getItemName() { return itemName; }
    public String getItemDescription() { return itemDescription; }
    public Double getItemQuantity() { return itemQuantity; }
    public Double getItemReorderLevel() { return itemReorderLevel; }
    public Double getItemSellingPrice() { return itemSellingPrice; }
    public String getItemImageDirPath() { return itemImageDirPath; }
    public String getVendorID() { return vendorID; }
    public String getVendorProductCode() { return vendorProductCode; }
    public boolean getActiveStatus() { return activeStatus; }
    public ArrayList<String> getSubCategories() { return subCategories; }
    
    /* SETTER METHODS */
    public void setItemSKU(String itemSKU) { this.itemSKU = itemSKU; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
    public void setItemQuantity(Double itemQuantity) { this.itemQuantity = itemQuantity; }
    public void setItemReorderLevel(Double itemReorderLevel) { this.itemReorderLevel = itemReorderLevel; }
    public void setItemSellingPrice(Double itemSellingPrice) { this.itemSellingPrice = itemSellingPrice; }
    public void setItemImageDirPath(String itemImageDirPath) { this.itemImageDirPath = itemImageDirPath; }
    public void setVendorID(String vendorID) { this.vendorID = vendorID; }
    public void setVendorProductCode(String vendorProductCode) { this.vendorProductCode = vendorProductCode; }
    public void setActiveStatus(boolean activeStatus) { this.activeStatus = activeStatus; }
    public void setSubCategories(ArrayList<String> subCategories) { this.subCategories = subCategories; }
}