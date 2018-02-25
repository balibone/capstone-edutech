
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

@Entity(name = "JobReviewReport")
public class JobReviewReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobReviewReportID;
    //status is set to 'Unresolved' or 'Resolved'
    private String jobReviewReportStatus;
    private String jobReviewReportDescription;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobReviewReportDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobReviewReviewedDate;
    
    /* FOREIGN KEY */
    private Long jobReviewID;
    private String jobReviewPosterID;
    private String jobReviewReporterID;
    
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.jobReviewReportDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getJobReviewReportID() { return jobReviewReportID; }
    public String getJobReviewReportStatus() { return jobReviewReportStatus; }
    public String getJobReviewReportDescription() { return jobReviewReportDescription; }
    public Date getJobReviewReportDate() { return jobReviewReportDate; }
    public Date getJobReviewReviewedDate() { return jobReviewReviewedDate; }
    public Long getJobReviewID() { return jobReviewID; }
    public String getJobReviewPosterID() { return jobReviewPosterID; }
    public String getJobReviewReporterID() { return jobReviewReporterID; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setJobReviewReportID(Long jobReviewReportID) { this.jobReviewReportID = jobReviewReportID; }
    public void setJobReviewReportStatus(String jobReviewReportStatus) { this.jobReviewReportStatus = jobReviewReportStatus; }
    public void setJobReviewReportDescription(String jobReviewReportDescription) { this.jobReviewReportDescription = jobReviewReportDescription; }
    public void setJobReviewReportDate(Date jobReviewReportDate) { this.jobReviewReportDate = jobReviewReportDate; }
    public void setJobReviewReviewedDate() { this.jobReviewReviewedDate = new Date(); }
    public void setJobReviewID(Long jobReviewID) { this.jobReviewID = jobReviewID; }
    public void setJobReviewPosterID(String jobReviewPosterID) { this.jobReviewPosterID = jobReviewPosterID; }
    public void setJobReviewReporterID(String jobReviewReporterID) { this.jobReviewReporterID = jobReviewReporterID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}