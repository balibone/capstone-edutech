/***************************************************************************************
*   Title:                  ItemReportEntity.java
*   Purpose:                LIST OF MARKETPLACE-RELATED (ITEM) REPORTS BY THE CAMPUS USERS
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/
package unifyentities.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import unifyentities.marketplace.ItemEntity;
import commoninfrastructureentities.UserEntity;

@Entity(name = "ItemReport")
public class ItemReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemReportID;
    //status is set to 'Unresolved' or 'Resolved'
    private String itemReportStatus;
    private String itemReportCategory;
    private String itemReportDescription;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemReportDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemReviewedDate;
    
    /* FOREIGN KEY */
    private Long itemID;
    private String itemPosterID;
    private String itemReporterID;
    
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private ItemEntity itemEntity;
    
    @PrePersist
    public void creationDate() { this.itemReportDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean createItemReport(String itemReportCategory, String itemReportDescription, String itemSellerID) {
        this.itemReportStatus = "Unresolved";
        this.itemReportCategory = itemReportCategory;
        this.itemReportDescription = itemReportDescription;
        this.itemPosterID = itemSellerID;
        return true;
    }
    
    /* GETTER METHODS */
    public Long getItemReportID() { return itemReportID; }
    public String getItemReportStatus() { return itemReportStatus; }
    public String getItemReportCategory() { return itemReportCategory; }
    public String getItemReportDescription() { return itemReportDescription; }
    public Date getItemReportDate() { return itemReportDate; }
    public Date getItemReviewedDate() { return itemReviewedDate; }
    public Long getItemID() { return itemID; }
    public String getItemPosterID() { return itemPosterID; }
    public String getItemReporterID() { return itemReporterID; }
    public UserEntity getUserEntity() { return userEntity; }
    public ItemEntity getItemEntity() { return itemEntity; }
    
    /* SETTER METHODS */
    public void setItemReportID(Long itemReportID) { this.itemReportID = itemReportID; }
    public void setItemReportStatus(String itemReportStatus) { this.itemReportStatus = itemReportStatus; }
    public void setItemReportCategory(String itemReportCategory) { this.itemReportCategory = itemReportCategory; }
    public void setItemReportDescription(String itemReportDescription) { this.itemReportDescription = itemReportDescription; }
    public void setItemReportDate(Date itemReportDate) { this.itemReportDate = itemReportDate; }
    public void setItemReviewedDate() { this.itemReviewedDate = new Date(); }
    public void setItemID(Long itemID) { this.itemID = itemID; }
    public void setItemPosterID(String itemPosterID) { this.itemPosterID = itemPosterID; }
    public void setItemReporterID(String itemReporterID) { this.itemReporterID = itemReporterID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setItemEntity(ItemEntity itemEntity) { this.itemEntity = itemEntity; }
}