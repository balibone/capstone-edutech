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

@Entity(name = "ShoutsReport")
public class ShoutsReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shoutReportID;
    private String shoutReportContent;
    //status is set to 'Unresolved' or 'Resolved'
    private String shoutReportStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date shoutReportDate;
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date shoutReportReviewedDate;
    
    @ManyToOne
    private ShoutsEntity shoutsEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.shoutReportDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addNewReport(String reportContent) {
        this.shoutReportContent = reportContent;
        this.shoutReportStatus = "Unresolved";
        return true;
    }
    
    /* GETTER METHODS */
    public Long getShoutReportID() { return shoutReportID; }
    public String getShoutReportContent() { return shoutReportContent; }
    public String getShoutReportStatus() { return shoutReportStatus; }
    public Date getShoutReportDate() { return shoutReportDate; }
    public Date getShoutReportReviewedDate() { return shoutReportReviewedDate; }
    
    public ShoutsEntity getShoutsEntity() { return shoutsEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setShoutReportID(Long shoutReportID) { this.shoutReportID = shoutReportID; }
    public void setShoutReportContent(String shoutReportContent) { this.shoutReportContent = shoutReportContent; }
    public void setShoutReportStatus(String shoutReportStatus) { this.shoutReportStatus = shoutReportStatus; }
    public void setShoutReportDate(Date shoutReportDate) { this.shoutReportDate = shoutReportDate; }
    public void setShoutReportReviewedDate(Date shoutReportReviewedDate) { this.shoutReportReviewedDate = shoutReportReviewedDate; }
    public void setShoutReportReviewedDate() { this.shoutReportReviewedDate = new Date(); }
    
    public void setShoutsEntity(ShoutsEntity shoutsEntity) { this.shoutsEntity = shoutsEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}