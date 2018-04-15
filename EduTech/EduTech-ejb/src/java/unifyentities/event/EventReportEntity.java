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

import unifyentities.event.EventEntity;
import commoninfrastructureentities.UserEntity;

@Entity(name = "EventReport")
public class EventReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventReportID;
    private String eventReportContent;
    //status is set to 'Unresolved' or 'Resolved'
    private String eventReportStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventReportDate;
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date eventReportReviewedDate;
    
    @ManyToOne
    private EventEntity eventEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.eventReportDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addNewReport(String reportContent) {
        this.eventReportContent = reportContent;
        this.eventReportStatus = "Unresolved";
        return true;
    }
    
    /* GETTER METHODS */
    public Long getEventReportID() { return eventReportID; }
    public String getEventReportContent() { return eventReportContent; }
    public String getEventReportStatus() { return eventReportStatus; }
    public Date getEventReportDate() { return eventReportDate; }
    public Date getEventReportReviewedDate() { return eventReportReviewedDate; }
    
    public EventEntity getEventEntity() { return eventEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setEventReportID(Long eventReportID) { this.eventReportID = eventReportID; }
    public void setEventReportContent(String eventReportContent) { this.eventReportContent = eventReportContent; }
    public void setEventReportStatus(String eventReportStatus) { this.eventReportStatus = eventReportStatus; }
    public void setEventReportDate(Date eventReportDate) { this.eventReportDate = eventReportDate; }
    public void setEventReportReviewedDate(Date eventReportReviewedDate) { this.eventReportReviewedDate = eventReportReviewedDate; }
    public void setEventReportReviewedDate() { this.eventReportReviewedDate = new Date(); }
    
    public void setEventEntity(EventEntity eventEntity) { this.eventEntity = eventEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}