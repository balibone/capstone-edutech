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

@Entity(name = "ItemTransaction")
public class ItemTransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemTransactionID;
    private Double itemTransactionPrice;
    
    @Temporal(TemporalType.DATE)
    private Date itemTransactionDate;
    
    /* FOREIGN KEY */
    private String itemSellerID;
    private String itemBuyerID;
    
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private ItemEntity itemEntity;
    
    @PrePersist
    public void creationDate() { this.itemTransactionDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getItemTransactionID() { return itemTransactionID; }
    public Double getItemTransactionPrice() { return itemTransactionPrice; }
    public Date getItemTransactionDate() { return itemTransactionDate; }
    public String getItemSellerID() { return itemSellerID; }
    public String getItemBuyerID() { return itemBuyerID; }
    
    public UserEntity getUserEntity() { return userEntity; }
    public ItemEntity getItemEntity() { return itemEntity; }
    
    /* SETTER METHODS */
    public void setItemTransactionID(Long itemTransactionID) { this.itemTransactionID = itemTransactionID; }
    public void setItemTransactionPrice(Double itemTransactionPrice) { this.itemTransactionPrice = itemTransactionPrice; }
    public void setItemTransactionDate(Date itemTransactionDate) { this.itemTransactionDate = itemTransactionDate; }
    public void setItemSellerID(String itemSellerID) { this.itemSellerID = itemSellerID; }
    public void setItemBuyerID(String itemBuyerID) { this.itemBuyerID = itemBuyerID; }
    
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setItemEntity(ItemEntity itemEntity) { this.itemEntity = itemEntity; }
}