/***************************************************************************************
*    Title:         LikeListingEntity.java
*    Purpose:       LIST OF LIKES FOR ALL LISTINGS IN UNIFY SYSTEM
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
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
import unifyentities.voices.CompanyReviewEntity;

@Entity(name = "LikeListing")
public class LikeListingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeID;
    private String likeType;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;
    
    @ManyToOne
    private ItemEntity itemEntity;
    @ManyToOne
    private CompanyReviewEntity reviewEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.likeDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addNewLike(String likeType) {
        this.likeType = likeType;
        return true;
    }
    
    /* GETTER METHODS */
    public Long getLikeID() { return likeID; }
    public String getLikeType() { return likeType; }
    public Date getLikeDate() { return likeDate; }
    
    public ItemEntity getItemEntity() { return itemEntity; }
    public CompanyReviewEntity getCompanyReviewEntity() { return reviewEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setLikeID(Long likeID) { this.likeID = likeID; }
    public void setLikeType(String likeType) { this.likeType = likeType; }
    public void setLikeDate(Date likeDate) { this.likeDate = likeDate; }
    
    public void setItemEntity(ItemEntity itemEntity) { this.itemEntity = itemEntity; }
    public void setCompanyReviewEntity(CompanyReviewEntity reviewEntity) { this.reviewEntity = reviewEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}