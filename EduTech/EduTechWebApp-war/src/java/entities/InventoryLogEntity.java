package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "InventoryLog")
public class InventoryLogEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long logID;
    private String logCreatorID;
    private String logDate;
    private String logReason;
    private String logDescription;
    private String itemName;
    private String itemSKU;
    private Double itemQtyBeforeAdjust;
    private Double itemQtyAfterAdjust;
    private String itemQtyAdjustValue;
    
    @Temporal(TemporalType.DATE)
    private Date logCreationDate;
    
    @PrePersist
    public void creationDate() {
        this.logCreationDate = new Date();
    }
    
    /* MISCELLANEOUS METHODS */
    public void createInventoryLog(String logCreatorID, String logDate, String logReason, String logDescription, 
            String itemName, String itemSKU, Double itemQtyBeforeAdjust, Double itemQtyAfterAdjust, String itemQtyAdjustValue) {
        this.logCreatorID = logCreatorID;
        this.logDate = logDate;
        this.logReason = logReason;
        this.logDescription = logDescription;
        this.itemName = itemName;
        this.itemSKU = itemSKU;
        this.itemQtyBeforeAdjust = itemQtyBeforeAdjust;
        this.itemQtyAfterAdjust = itemQtyAfterAdjust;
        this.itemQtyAdjustValue = itemQtyAdjustValue;
    }
    
    /* GETTER METHODS */
    public Long getLogID() { return logID; }
    public String getLogCreatorID() { return logCreatorID; }
    public String getLogDate() { return logDate; }
    public String getLogReason() { return logReason; }
    public String getLogDescription() { return logDescription; }
    public String getItemName() { return itemName; }
    public String getItemSKU() { return itemSKU; }
    public Double getItemQtyBeforeAdjust() { return itemQtyBeforeAdjust; }
    public Double getItemQtyAfterAdjust() { return itemQtyAfterAdjust; }
    public String getItemQtyAdjustValue() { return itemQtyAdjustValue; }
    public Date getLogCreationDate() { return logCreationDate; }
    
    /* SETTER METHODS */
    public void setLogID(Long logID) { this.logID = logID; }
    public void setLogCreatorID(String logCreatorID) { this.logCreatorID = logCreatorID; }
    public void setLogDate(String logDate) { this.logDate = logDate; }
    public void setLogReason(String logReason) { this.logReason = logReason; }
    public void setLogDescription(String logDescription) { this.logDescription = logDescription; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setItemSKU(String itemSKU) { this.itemSKU = itemSKU; }
    public void setItemQtyBeforeAdjust(Double itemQtyBeforeAdjust) { this.itemQtyBeforeAdjust = itemQtyBeforeAdjust; }
    public void setItemQtyAfterAdjust(Double itemQtyAfterAdjust) { this.itemQtyAfterAdjust = itemQtyAfterAdjust; }
    public void setItemQtyAdjustValue(String itemQtyAdjustValue) { this.itemQtyAdjustValue = itemQtyAdjustValue; }
    public void setLogCreationDate(Date logCreationDate) { this.logCreationDate = logCreationDate; }
}