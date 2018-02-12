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

import commoninfrastructureentities.UserEntity;
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

@Entity(name = "ItemReport")
public class ItemReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemReportID;
    private String itemReportStatus;
    private String itemReportDescription;
    
    @Temporal(TemporalType.DATE)
    private Date itemReportDate;
    
    /* FOREIGN KEY */
    private Long itemID;
    private String itemPosterID;
    private String itemReporterID;
    
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.itemReportDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getItemReportID() { return itemReportID; }
    public String getItemReportStatus() { return itemReportStatus; }
    public String getItemReportDescription() { return itemReportDescription; }
    public Date getItemReportDate() { return itemReportDate; }
    public Long getItemID() { return itemID; }
    public String getItemPosterID() { return itemPosterID; }
    public UserEntity getUserEntity() { return userEntity; }
    public String getItemReporterID() { return itemReporterID; }
    
    /* SETTER METHODS */
    public void setItemReportID(Long itemReportID) { this.itemReportID = itemReportID; }
    public void setItemReportStatus(String itemReportStatus) { this.itemReportStatus = itemReportStatus; }
    public void setItemReportDescription(String itemReportDescription) { this.itemReportDescription = itemReportDescription; }
    public void setItemReportDate(Date itemReportDate) { this.itemReportDate = itemReportDate; }
    public void setItemID(Long itemID) { this.itemID = itemID; }
    public void setItemPosterID(String itemPosterID) { this.itemPosterID = itemPosterID; }
    public void setItemReporterID(String itemReporterID) { this.itemReporterID = itemReporterID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}