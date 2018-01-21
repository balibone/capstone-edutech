package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "CompositeItem")
public class CompositeItemEntity implements Serializable {
    @Id
    private String compositeSKU;
    private String compositeName;
    private Double compositeSellPrice;
    private Double compositeQuantity;
    private Double compositeRebundleLvl;
    private String compositeDescription;
    private String compositeImagePath;
    private ArrayList<Vector> compositeArrList;
    private Vector itemInventorySKUVec;
    
    @Temporal(TemporalType.DATE)
    private Date compositeCreationDate;
    
    @PrePersist
    public void creationDate() {
        this.compositeCreationDate = new Date();
    }
    
    /* MISCELLANEOUS METHODS */
    public void createCompositeItem(String compositeName, String compositeSKU, Double compositeSellPrice, 
            Double compositeRebundleLvl, String compositeDescription, String fileName, List<Vector> packageItemList, Vector itemInventorySKUVec) {
        this.compositeName = compositeName;
        this.compositeSKU = compositeSKU;
        this.compositeSellPrice = compositeSellPrice;
        this.compositeQuantity = 0.0;
        this.compositeRebundleLvl = compositeRebundleLvl;
        this.compositeDescription = compositeDescription;
        this.compositeImagePath = fileName;
        this.compositeArrList = (ArrayList)packageItemList;
        this.itemInventorySKUVec = itemInventorySKUVec;
    }
    
    /* GETTER METHODS */
    public String getCompositeSKU() { return compositeSKU; }
    public String getCompositeName() { return compositeName; }
    public Double getCompositeSellPrice() { return compositeSellPrice; }
    public Double getCompositeQuantity() { return compositeQuantity; }
    public Double getCompositeRebundleLvl() { return compositeRebundleLvl; }
    public String getCompositeDescription() { return compositeDescription; }
    public String getCompositeImagePath() { return compositeImagePath; }
    public ArrayList<Vector> getCompositeArrList() { return compositeArrList; }
    public Vector getItemInventorySKUVec() { return itemInventorySKUVec; }
    public Date getCompositeCreationDate() { return compositeCreationDate; }
    
    /* SETTER METHODS */
    public void setCompositeSKU(String compositeSKU) { this.compositeSKU = compositeSKU; }
    public void setCompositeName(String compositeName) { this.compositeName = compositeName; }
    public void setCompositeSellPrice(Double compositeSellPrice) { this.compositeSellPrice = compositeSellPrice; }
    public void setCompositeQuantity(Double compositeQuantity) { this.compositeQuantity = compositeQuantity; }
    public void setCompositeRebundleLvl(Double compositeRebundleLvl) { this.compositeRebundleLvl = compositeRebundleLvl; }
    public void setCompositeDescription(String compositeDescription) { this.compositeDescription = compositeDescription; }
    public void setCompositeImagePath(String compositeImagePath) { this.compositeImagePath = compositeImagePath; }
    public void setCompositeArrList(ArrayList<Vector> compositeArrList) { this.compositeArrList = compositeArrList; }
    public void setItemInventorySKUVec(Vector itemInventorySKUVec) { this.itemInventorySKUVec = itemInventorySKUVec; }
    public void setCompositeCreationDate(Date compositeCreationDate) { this.compositeCreationDate = compositeCreationDate; }
}