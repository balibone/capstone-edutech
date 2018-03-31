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

@Entity(name = "ShoutsBookmarks")
public class ShoutsBookmarksEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookmarkID;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookmarkDate;
    
    @ManyToOne
    private ShoutsEntity shoutsEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.bookmarkDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addBookmark(UserEntity userEntity, ShoutsEntity shoutsEntity) {
        this.userEntity = userEntity;
        this.shoutsEntity = shoutsEntity;
        
        return true;
    }
    
    /* GETTER METHODS */
    public Long getBookmarkID() { return bookmarkID; }
    public Date getBookmarkDate() { return bookmarkDate; }
    
    public ShoutsEntity getShoutsEntity() { return shoutsEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setBookmarkID(Long bookmarkID) { this.bookmarkID = bookmarkID; }
    public void setBookmarkDate(Date bookmarkDate) { this.bookmarkDate = bookmarkDate; }
    
    public void setShoutsEntity(ShoutsEntity shoutsEntity) { this.shoutsEntity = shoutsEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}