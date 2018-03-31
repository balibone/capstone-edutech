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

@Entity(name = "ShoutsCommentsReport")
public class ShoutsCommentsReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shoutCommentReportID;
    private String shoutCommentReportContent;
    //status is set to 'Unresolved' or 'Resolved'
    private String shoutCommentReportStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date shoutCommentReportDate;
    
     @Temporal(TemporalType.TIMESTAMP)
    private Date shoutCommentReportReviewedDate;
    
    @ManyToOne
    private ShoutsCommentsEntity shoutsCommentEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.shoutCommentReportDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addNewReport(String reportContent) {
        this.shoutCommentReportContent = reportContent;
        this.shoutCommentReportStatus = "Unresolved";
        return true;
    }
    
    /* GETTER METHODS */
    public Long getShoutCommentReportID() { return shoutCommentReportID; }
    public String getShoutCommentReportContent() { return shoutCommentReportContent; }
    public String getShoutCommentReportStatus() { return shoutCommentReportStatus; }
    public Date getShoutCommentReportDate() { return shoutCommentReportDate; }
    public Date getShoutCommentReportReviewedDate() { return shoutCommentReportReviewedDate; }
    
    public ShoutsCommentsEntity getShoutsCommentsEntity() { return shoutsCommentEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setShoutCommentReportID(Long shoutCommentReportID) { this.shoutCommentReportID = shoutCommentReportID; }
    public void setShoutCommentReportContent(String shoutCommentReportContent) { this.shoutCommentReportContent = shoutCommentReportContent; }
    public void setShoutCommentReportStatus(String shoutCommentReportStatus) { this.shoutCommentReportStatus = shoutCommentReportStatus; }
    public void setShoutCommentReportDate(Date shoutCommentReportDate) { this.shoutCommentReportDate = shoutCommentReportDate; }
    public void setShoutCommentReportReviewedDate(Date shoutCommentReportReviewedDate) { this.shoutCommentReportReviewedDate = shoutCommentReportReviewedDate; }
    
    public void setShoutsCommentsEntity(ShoutsCommentsEntity shoutsCommentEntity) { this.shoutsCommentEntity = shoutsCommentEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}