/***************************************************************************************
*    Title:         EventEntity.java
*    Purpose:       LIST OF EVENTS APPROVED BY UNIFYADMIN AND DUALADMIN
*    Author:        NIGEL LEE TJON YI
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          25 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/

package unifyentities.event;

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
import unifyentities.common.EventRequestEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "Event")
public class EventEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventID;
    private String eventStatus;
    private String eventDescription;
    private String eventTitle;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventStartDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventEndDateTime;
    private String eventVenue;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventApprovalDate;
    
    @ManyToOne
    private UserEntity userEntity;
    
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "eventEntity")
    private Collection<EventRSVPEntity> eventRsvpSet = new ArrayList<EventRSVPEntity>();
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "eventEntity")
    private Collection<EventReportEntity> eventReportSet = new ArrayList<EventReportEntity>();
    
    @OneToOne(cascade = {CascadeType.REMOVE})
    private EventRequestEntity eventRequestEntity;
    
    @PrePersist
    public void creationDate() { this.eventApprovalDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public void createEvent(String eventTitle, String eventDescription, String eventVenue, 
            Date eventStartDateTime, Date eventEndDateTime, UserEntity eventRequestor) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventVenue = eventVenue;
        this.eventStartDateTime = eventStartDateTime;
        this.eventEndDateTime = eventEndDateTime;
        this.eventStatus = "Active";
        this.userEntity = eventRequestor;
        //add event title
    }
    
    /* GETTER METHODS */
    public Long getEventID() { return eventID; }
    public String getEventStatus() { return eventStatus; }
    public String getEventDescription() { return eventDescription; }
    public Date getEventApprovalDate() { return eventApprovalDate; }
    public Date getEventStartDateTime() { return eventStartDateTime; }
    public Date getEventEndDateTime() { return eventEndDateTime; }
    public String getEventVenue() { return eventVenue; }
    public String getEventTitle() { return eventTitle; }
    
    public UserEntity getUserEntity() { return userEntity; }
    public EventRequestEntity getEventRequestEntity() { return eventRequestEntity; }
    
    public Collection<EventRSVPEntity> getEventRsvpSet() { return eventRsvpSet; }
    public Collection<EventReportEntity> getEventReportSet() { return eventReportSet; }
    
    /* SETTER METHODS */
    public void setEventID(Long eventID) { this.eventID = eventID; }
    public void setEventStatus(String eventStatus) { this.eventStatus = eventStatus; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
    public void setEventDate(Date eventApprovalDate) { this.eventApprovalDate = eventApprovalDate; }
    public void setEventStartDateTime(Date eventStartDateTime) { this.eventStartDateTime = eventStartDateTime; }
    public void setEventEndDateTime(Date eventEndDateTime) { this.eventEndDateTime = eventEndDateTime; }
    public void setEventVenue(String eventVenue) {this.eventVenue = eventVenue; }
    public void setEventTitle(String eventTitle) {this.eventTitle = eventTitle; }
    
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setEventRequestEntity(EventRequestEntity eventRequestEntity) { this.eventRequestEntity = eventRequestEntity; }
}