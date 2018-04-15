package unifyentities.event;

import unifyentities.shouts.*;
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

@Entity(name = "EventRSVP")
public class EventRSVPEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rsvpID;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date rsvpDate;
    
    @ManyToOne
    private EventEntity eventEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.rsvpDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addRSVP(UserEntity userEntity, EventEntity eventEntity) {
        this.userEntity = userEntity;
        this.eventEntity = eventEntity;
        
        return true;
    }
    
    /* GETTER METHODS */
    public Long getRsvpID() { return rsvpID; }
    public Date getRsvpDate() { return rsvpDate; }
    
    public EventEntity getEventEntity() { return eventEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setBookmarkID(Long rsvpID) { this.rsvpID = rsvpID; }
    public void setRsvpDate(Date rsvpDate) { this.rsvpDate = rsvpDate; }
    
    public void setEventEntity(EventEntity eventEntity) { this.eventEntity = eventEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}