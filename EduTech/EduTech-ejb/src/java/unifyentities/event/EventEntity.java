package unifyentities.event;

import commoninfrastructure.UserEntity;
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

@Entity(name = "Event")
public class EventEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventID;
    private String eventStatus;
    private String eventDescription;
    @Temporal(TemporalType.DATE)
    private Date eventStartDateTime;
    @Temporal(TemporalType.DATE)
    private Date eventEndDateTime;
    private String eventVenue;
    
    @Temporal(TemporalType.DATE)
    private Date eventApprovalDate;
    
    
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.eventApprovalDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public void createEvent(String eventDescription, String eventVenue, 
            Date eventStartDateTime, Date eventEndDateTime, UserEntity eventRequestor) {
        this.eventDescription = eventDescription;
        this.eventVenue = eventVenue;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.eventStatus = "Active";
        this.userEntity = eventRequestor;
    }
    
    /* GETTER METHODS */
    public Long getEventID() { return eventID; }
    public String getEventStatus() { return eventStatus; }
    public String getEventDescription() { return eventDescription; }
    public Date getEventApprovalDate() { return eventApprovalDate; }
    public Date getEventStartDateTime() { return eventStartDateTime; }
    public Date getEventEndDateTime() { return eventEndDateTime; }
    public String getEventVenue() { return eventVenue; }
    
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setEventID(Long eventID) { this.eventID = eventID; }
    public void setEventStatus(String eventStatus) { this.eventStatus = eventStatus; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
    public void setEventDate(Date eventApprovalDate) { this.eventApprovalDate = eventApprovalDate; }
    public void setEventStartDateTime(Date eventStartDateTime) { this.eventStartDateTime = eventStartDateTime; }
    public void setEventEndDateTime(Date eventEndDateTime) { this.eventEndDateTime = eventEndDateTime; }
    public void setEventVenue(String eventVenue) {this.eventVenue = eventVenue; }
    
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}