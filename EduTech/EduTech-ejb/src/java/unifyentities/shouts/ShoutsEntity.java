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

import commoninfrastructureentities.UserEntity;

@Entity(name = "Shouts")
public class ShoutsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shoutID;
    //status is set to 'Active' or 'Delisted'
    private String shoutStatus;
    private String shoutContent;
    
    //for updating when event has been updated? - see if needed
    @Temporal(TemporalType.TIMESTAMP)
    private Date shoutEditedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date shoutDate;
    
    private String shoutLat;
    private String shoutLong;
    
    @ManyToOne
    private UserEntity shoutUser;
    
    //link to likes entity
    
    //link to bookmarked entity
    
    //link to comments entity?
    
    
    @PrePersist
    public void creationDate() { this.shoutDate = new Date(); }
    
        
    /* GETTER METHODS */
    public Long getShoutID() { return shoutID; }
    public String getShoutStatus() { return shoutStatus; }
    public String getShoutContent() { return shoutContent; }
    public Date getShoutDate() { return shoutDate; }

    public Date getShoutEditedDate() { return shoutEditedDate; }
    public String getShoutLat() { return shoutLat; }
    public String getShoutLong() { return shoutLong; }
    
    public UserEntity getUserEntity() { return shoutUser; }
    
    /* SETTER METHODS */
    public void setShoutID(Long shoutID) { this.shoutID = shoutID; }
    public void setShoutStatus(String shoutStatus) { this.shoutStatus = shoutStatus; }
    public void setShoutContent(String shoutContent) { this.shoutContent = shoutContent; }
    public void setShoutDate(Date shoutDate) { this.shoutDate = shoutDate; }
    
    public void setEventReviewedDate() { this.shoutEditedDate = new Date(); }
    public void setShoutLat(String shoutLat) {this.shoutLat = shoutLat; }
    public void setShoutLong(String shoutLong) {this.shoutLong = shoutLong; }
    
    public void setUserEntity(UserEntity shoutUser) { this.shoutUser = shoutUser; }
}