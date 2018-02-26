/***************************************************************************************
*    Title:         EventRequestEntity.java
*    Purpose:       LIST OF EVENTS REQUESTS SUBMITTED BY SYSTEM USERS
*    Author:        NIGEL LEE TJON YI
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          25 JANUARY 2018
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

import commoninfrastructureentities.UserEntity;

@Entity(name = "EventRequest")
public class EventRequestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventRequestID;
    //status is set to 'Pending', 'Approved' or 'Rejected'
    private String eventRequestStatus;
    private String eventRequestDescription;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventRequestStartDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventRequestEndDateTime;
    private String eventRequestVenue;
    //for updating when request has been approved or rejected
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventReviewedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventRequestDate;
    
    
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.eventRequestDate = new Date(); }
    
        
    /* GETTER METHODS */
    public Long getEventRequestID() { return eventRequestID; }
    public String getEventRequestStatus() { return eventRequestStatus; }
    public String getEventRequestDescription() { return eventRequestDescription; }
    public Date getEventRequestDate() { return eventRequestDate; }
    public Date getEventRequestStartDateTime() { return eventRequestStartDateTime; }
    public Date getEventRequestEndDateTime() { return eventRequestEndDateTime; }
    public String getEventRequestVenue() { return eventRequestVenue; }
    public Date getEventReviewedDate() { return eventReviewedDate; }
    
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setEventRequestID(Long eventRequestID) { this.eventRequestID = eventRequestID; }
    public void setEventRequestStatus(String eventRequestStatus) { this.eventRequestStatus = eventRequestStatus; }
    public void setEventRequestDescription(String eventRequestDescription) { this.eventRequestDescription = eventRequestDescription; }
    public void setEventRequestDate(Date eventRequestDate) { this.eventRequestDate = eventRequestDate; }
    public void setEventRequestStartDateTime(Date eventRequestStartDateTime) { this.eventRequestStartDateTime = eventRequestStartDateTime; }
    public void setEventRequestEndDateTime(Date eventRequestEndDateTime) { this.eventRequestEndDateTime = eventRequestEndDateTime; }
    public void setEventRequestVenue(String eventRequestVenue) {this.eventRequestVenue = eventRequestVenue; }
    public void setEventReviewedDate() { this.eventReviewedDate = new Date(); }
    
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}