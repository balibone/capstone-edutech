/***************************************************************************************
*    Title:         ItemTransactionEntity.java
*    Purpose:       LIST OF ITEM TRANSACTIONS PERFORMED BY CAMPUS USERS
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/
package unifyentities.marketplace;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "ItemTransaction")
public class ItemTransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemTransactionID;
    private Double itemTransactionPrice;
    private String itemTransactionStatus;
    
    @Temporal(TemporalType.DATE)
    private Date itemTransactionDate;
    
    /* FOREIGN KEY */
    private Long itemID;
    private String itemSellerID;
    private String itemBuyerID;
    
    @PrePersist
    public void creationDate() { this.itemTransactionDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getItemTransactionID() { return itemTransactionID; }
    public Double getItemTransactionPrice() { return itemTransactionPrice; }
    public String getItemTransactionStatus() { return itemTransactionStatus; }
    public Date getItemTransactionDate() { return itemTransactionDate; }
    public Long getItemID() { return itemID; }
    public String getItemSellerID() { return itemSellerID; }
    public String getItemBuyerID() { return itemBuyerID; }
    
    /* SETTER METHODS */
    public void setItemTransactionID(Long itemTransactionID) { this.itemTransactionID = itemTransactionID; }
    public void setItemTransactionPrice(Double itemTransactionPrice) { this.itemTransactionPrice = itemTransactionPrice; }
    public void setItemTransactionStatus(String itemTransactionStatus) { this.itemTransactionStatus = itemTransactionStatus; }
    public void setItemTransactionDate(Date itemTransactionDate) { this.itemTransactionDate = itemTransactionDate; }
    public void setItemID(Long itemID) { this.itemID = itemID; }
    public void setItemSellerID(String itemSellerID) { this.itemSellerID = itemSellerID; }
    public void setItemBuyerID(String itemBuyerID) { this.itemBuyerID = itemBuyerID; }
}