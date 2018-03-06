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

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import unifyentities.common.CategoryEntity;
import unifyentities.common.LikeListingEntity;
import unifyentities.common.TagEntity;
import commoninfrastructureentities.UserEntity;

@Entity(name = "Item")
public class ItemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemID;
    private String itemName;
    private double itemPrice;
    private String itemCondition;
    private String itemDescription;
    private String itemImage;
    private String itemStatus;
    private int itemNumOfLikes;
    private String tradeLocation;
    private String tradeLat;
    private String tradeLong;
    private String tradeInformation;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemPostingDate;
    
    @ManyToOne
    private CategoryEntity categoryEntity;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany(mappedBy = "itemEntity")
    private Collection<ItemOfferEntity> itemOfferSet = new ArrayList<ItemOfferEntity>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "itemEntity")
    private Collection<LikeListingEntity> likeListingSet = new ArrayList<LikeListingEntity>();
    @OneToMany(mappedBy = "itemEntity")
    private Collection<ItemReviewEntity> itemReviewSet = new ArrayList<ItemReviewEntity>();
    @OneToMany(mappedBy = "itemEntity")
    private Collection<ItemTransactionEntity> itemTransactionSet = new ArrayList<ItemTransactionEntity>();
    @ManyToMany(cascade={CascadeType.PERSIST}, mappedBy = "itemSet")
    private Set<TagEntity> tagSet = new HashSet<TagEntity>();
    
    @PrePersist
    public void creationDate() { this.itemPostingDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean createItemListing(String itemName, double itemPrice, String itemCondition, String itemDescription, 
            String itemImagefileName, String tradeLocation, String tradeLat, String tradeLong, String tradeInformation) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCondition = itemCondition;
        this.itemDescription = itemDescription;
        this.itemImage = itemImagefileName;
        this.itemStatus = "Available";
        this.itemNumOfLikes = 0;
        this.tradeLocation = tradeLocation;
        this.tradeLat = tradeLat;
        this.tradeLong = tradeLong;
        this.tradeInformation = tradeInformation;
        return true;
    }
    
    /* GETTER METHODS */
    public Long getItemID() { return itemID; }
    public String getItemName() { return itemName; }
    public double getItemPrice() { return itemPrice; }
    public String getItemCondition() { return itemCondition; }
    public String getItemDescription() { return itemDescription; }
    public String getItemImage() { return itemImage; }
    public String getItemStatus() { return itemStatus; }
    public int getItemNumOfLikes() { return itemNumOfLikes; }
    public String getTradeLocation() { return tradeLocation; }
    public String getTradeLat() { return tradeLat; }
    public String getTradeLong() { return tradeLong; }
    public String getTradeInformation() { return tradeInformation; }
    public Date getItemPostingDate() { return itemPostingDate; }
    
    public CategoryEntity getCategoryEntity() { return categoryEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    public Collection<ItemOfferEntity> getItemOfferSet() { return itemOfferSet; }
    public Collection<LikeListingEntity> getLikeListingSet() { return likeListingSet; }
    public Collection<ItemReviewEntity> getItemReviewSet() { return itemReviewSet; }
    public Collection<ItemTransactionEntity> getItemTransactionSet() { return itemTransactionSet; }
    public Set<TagEntity> getTagSet() { return tagSet; }
    
    /* SETTER METHODS */
    public void setItemID(Long itemID) { this.itemID = itemID; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }
    public void setItemCondition(String itemCondition) { this.itemCondition = itemCondition; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
    public void setItemImage(String itemImage) { this.itemImage = itemImage; }
    public void setItemStatus(String itemStatus) { this.itemStatus = itemStatus; }
    public void setItemNumOfLikes(int itemNumOfLikes) { this.itemNumOfLikes = itemNumOfLikes; }
    public void setTradeLocation(String tradeLocation) { this.tradeLocation = tradeLocation; }
    public void setTradeLat(String tradeLat) { this.tradeLat = tradeLat; }
    public void setTradeLong(String tradeLong) { this.tradeLong = tradeLong; }
    public void setTradeInformation(String tradeInformation) { this.tradeInformation = tradeInformation; }
    public void setItemPostingDate(Date itemPostingDate) { this.itemPostingDate = itemPostingDate; }
    
    public void setCategoryEntity(CategoryEntity categoryEntity) { this.categoryEntity = categoryEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setItemOfferSet(Collection<ItemOfferEntity> itemOfferSet) { this.itemOfferSet = itemOfferSet; }
    public void setLikeListingSet(Collection<LikeListingEntity> likeListingSet) { this.likeListingSet = likeListingSet; }
    public void setItemReviewSet(Collection<ItemReviewEntity> itemReviewSet) { this.itemReviewSet = itemReviewSet; }
    public void setItemTransactionSet(Collection<ItemTransactionEntity> itemTransactionSet) { this.itemTransactionSet = itemTransactionSet; }
    public void setTagSet(Set<TagEntity> tagSet) { this.tagSet = tagSet; }
}