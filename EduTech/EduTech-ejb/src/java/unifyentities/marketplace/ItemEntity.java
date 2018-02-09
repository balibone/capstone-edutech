/***************************************************************************************
*    Title:         ItemEntity.java
*    Purpose:       ITEM POSTED IN MARKETPLACE BY CAMPUS USERS (FOR TRADES/DEAL)
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

import unifyentities.common.CategoryEntity;
import commoninfrastructure.UserEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import unifyentities.common.TagEntity;

@Entity(name = "Item")
public class ItemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemID;
    private String itemName;
    private String itemDescription;
    private Double itemPrice;
    private String itemCategory;
    private String itemStatus;
    private String tradeLocation;
    private String tradeLat;
    private String tradeLong;
    private String itemImage;
    
    @Temporal(TemporalType.DATE)
    private Date itemPostingDate;
    
    /* FOREIGN KEY */
    private String itemSellerID;
    
    @ManyToOne
    private CategoryEntity categoryEntity;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany(mappedBy = "itemEntity")
    private Collection<ItemReviewEntity> itemReviewSet = new ArrayList<ItemReviewEntity>();
    @OneToMany(mappedBy = "itemEntity")
    private Set<ItemTransactionEntity> itemTransactionSet = new HashSet<ItemTransactionEntity>();
    @ManyToMany(cascade={CascadeType.PERSIST}, mappedBy = "itemSet")
    private Set<TagEntity> tagSet = new HashSet<TagEntity>();
    
    @PrePersist
    public void creationDate() { this.itemPostingDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getItemID() { return itemID; }
    public String getItemName() { return itemName; }
    public String getItemDescription() { return itemDescription; }
    public Double getItemPrice() { return itemPrice; }
    public String getItemCategory() { return itemCategory; }
    public String getItemStatus() { return itemStatus; }
    public String getTradeLocation() { return tradeLocation; }
    public String getTradeLat() { return tradeLat; }
    public String getTradeLong() { return tradeLong; }
    public String getItemImage() { return itemImage; }
    public Date getItemPostingDate() { return itemPostingDate; }
    public String getItemSellerID() { return itemSellerID; }
    
    public CategoryEntity getCategoryEntity() { return categoryEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    public Collection<ItemReviewEntity> getItemReviewSet() { return itemReviewSet; }
    public Set<ItemTransactionEntity> getItemTransactionSet() { return itemTransactionSet; }
    public Set<TagEntity> getTagSet() { return tagSet; }
    
    /* SETTER METHODS */
    public void setItemID(Long itemID) { this.itemID = itemID; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
    public void setItemPrice(Double itemPrice) { this.itemPrice = itemPrice; }
    public void setItemCategory(String itemCategory) { this.itemCategory = itemCategory; }
    public void setItemStatus(String itemStatus) { this.itemStatus = itemStatus; }
    public void setTradeLocation(String tradeLocation) { this.tradeLocation = tradeLocation; }
    public void setTradeLat(String tradeLat) { this.tradeLat = tradeLat; }
    public void setTradeLong(String tradeLong) { this.tradeLong = tradeLong; }
    public void setItemImage(String itemImage) { this.itemImage = itemImage; }
    public void setItemPostingDate(Date itemPostingDate) { this.itemPostingDate = itemPostingDate; }
    public void setItemSellerID(String itemSellerID) { this.itemSellerID = itemSellerID; }
    
    public void setCategoryEntity(CategoryEntity categoryEntity) { this.categoryEntity = categoryEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setItemReviewSet(Collection<ItemReviewEntity> itemReviewSet) { this.itemReviewSet = itemReviewSet; }
    public void setItemTransactionSet(Set<ItemTransactionEntity> itemTransactionSet) { this.itemTransactionSet = itemTransactionSet; }
    public void setTagSet(Set<TagEntity> tagSet) { this.tagSet = tagSet; }
}