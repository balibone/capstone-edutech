/***************************************************************************************
*    Title:         ItemOfferEntity.java
*    Purpose:       ITEM OFFER PRICE BY UNIFY USERS
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/
package unifyentities.marketplace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import commoninfrastructureentities.UserEntity;

@Entity(name = "ItemOffer")
public class ItemOfferEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemOfferID;
    private double itemOfferPrice;
    private String itemOfferDescription;
    /* ITEM OFFER STATUS: PENDING, PROCESSING, ACCEPTED, REJECTED, CANCELLED, COMPLETED */
    private String sellerItemOfferStatus;
    private String buyerItemOfferStatus;
    private String sellerComments;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemOfferDate;
    
    @ManyToOne
    private ItemEntity itemEntity;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany(mappedBy = "itemOfferEntity")
    private Collection<ItemReviewEntity> itemReviewSet = new ArrayList<ItemReviewEntity>();
    
    @PrePersist
    public void creationDate() { this.itemOfferDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean createItemOffer(double itemOfferPrice, String itemOfferDescription) {
        this.itemOfferPrice = itemOfferPrice;
        this.itemOfferDescription = itemOfferDescription;
        this.sellerItemOfferStatus = "Pending";
        this.buyerItemOfferStatus = "Processing";
        this.sellerComments = "None";
        return true;
    }
    
    /* GETTER METHODS */
    public Long getItemOfferID() { return itemOfferID; }
    public double getItemOfferPrice() { return itemOfferPrice; }
    public String getItemOfferDescription() { return itemOfferDescription; }
    public String getSellerItemOfferStatus() { return sellerItemOfferStatus; }
    public String getBuyerItemOfferStatus() { return buyerItemOfferStatus; }
    public String getSellerComments() { return sellerComments; }
    public Date getItemOfferDate() { return itemOfferDate; }
    
    public ItemEntity getItemEntity() { return itemEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    public Collection<ItemReviewEntity> getItemReviewSet() { return itemReviewSet; }
    
    /* SETTER METHODS */
    public void setItemOfferID(Long itemOfferID) { this.itemOfferID = itemOfferID; }
    public void setItemOfferPrice(double itemOfferPrice) { this.itemOfferPrice = itemOfferPrice; }
    public void setItemOfferDescription(String itemOfferDescription) { this.itemOfferDescription = itemOfferDescription; }
    public void setSellerItemOfferStatus(String sellerItemOfferStatus) { this.sellerItemOfferStatus = sellerItemOfferStatus; }
    public void setBuyerItemOfferStatus(String buyerItemOfferStatus) { this.buyerItemOfferStatus = buyerItemOfferStatus; }
    public void setSellerComments(String sellerComments) { this.sellerComments = sellerComments; }
    public void setItemOfferDate(Date itemOfferDate) { this.itemOfferDate = itemOfferDate; }
    
    public void setItemEntity(ItemEntity itemEntity) { this.itemEntity = itemEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setItemReviewSet(Collection<ItemReviewEntity> itemReviewSet) { this.itemReviewSet = itemReviewSet; }
}