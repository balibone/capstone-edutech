/***************************************************************************************
*   Title:                  JobReportEntity.java
*   Purpose:                LIST OF JOB-RELATED REPORTS BY THE CAMPUS USERS
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/
package unifyentities.common;

import commoninfrastructureentities.UserEntity;
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

@Entity(name = "JobReport")
public class JobReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobReportID;
    private String jobReportStatus;
    private String jobReportDescription;
    
    @Temporal(TemporalType.DATE)
    private Date jobReportDate;
    
    /* FOREIGN KEY */
    private Long jobID;
    private String jobPosterID;
    
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.jobReportDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getJobReportID() { return jobReportID; }
    public String getJobReportStatus() { return jobReportStatus; }
    public String getJobReportDescription() { return jobReportDescription; }
    public Date getJobReportDate() { return jobReportDate; }
    public Long getJobID() { return jobID; }
    public String getJobPosterID() { return jobPosterID; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setJobReportID(Long jobReportID) { this.jobReportID = jobReportID; }
    public void setJobReportStatus(String jobReportStatus) { this.jobReportStatus = jobReportStatus; }
    public void setJobReportDescription(String jobReportDescription) { this.jobReportDescription = jobReportDescription; }
    public void setJobReportDate(Date jobReportDate) { this.jobReportDate = jobReportDate; }
    public void setJobID(Long jobID) { this.jobID = jobID; }
    public void setJobPosterID(String jobPosterID) { this.jobPosterID = jobPosterID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}