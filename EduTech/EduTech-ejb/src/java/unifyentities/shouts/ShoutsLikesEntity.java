package unifyentities.shouts;

import unifyentities.common.*;
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

import unifyentities.shouts.ShoutsEntity;
import commoninfrastructureentities.UserEntity;

@Entity(name = "ShoutsLikes")
public class ShoutsLikesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeID;
    private String likeType;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;
    
    @ManyToOne
    private ShoutsEntity shoutsEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.likeDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addNewLike(UserEntity userEntity, ShoutsEntity shoutsEntity) {
        this.userEntity = userEntity;
        this.shoutsEntity = shoutsEntity;
        return true;
    }
    
    /* GETTER METHODS */
    public Long getLikeID() { return likeID; }
    public String getLikeType() { return likeType; }
    public Date getLikeDate() { return likeDate; }
    
    public ShoutsEntity getShoutsEntity() { return shoutsEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setLikeID(Long likeID) { this.likeID = likeID; }
    public void setLikeType(String likeType) { this.likeType = likeType; }
    public void setLikeDate(Date likeDate) { this.likeDate = likeDate; }
    
    public void setShoutsEntity(ShoutsEntity shoutsEntity) { this.shoutsEntity = shoutsEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}