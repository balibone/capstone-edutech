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
import unifyentities.errands.JobEntity;
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
    //status is set to 'Unresolved' or 'Resolved'
    private String jobReportStatus;
    private String jobReportDescription;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobReportDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobReviewedDate;    
    
    @ManyToOne
    private UserEntity userEntity; //reporter
    @ManyToOne
    private JobEntity jobEntity;
    
    @PrePersist
    public void creationDate() { this.jobReportDate = new Date(); }
    
    public JobReportEntity(){
        
        setJobReportID(System.nanoTime());
        jobReportStatus = "Unresolved";
    }
    
    public boolean createJobReport(String reportReason){
        this.setJobReportDescription(reportReason);
        this.setJobReportDate(new Date());
        return true;
    }
    
    /* GETTER METHODS */
    public Long getJobReportID() { return jobReportID; }
    public String getJobReportStatus() { return jobReportStatus; }
    public String getJobReportDescription() { return jobReportDescription; }
    public Date getJobReportDate() { return jobReportDate; }
    public UserEntity getUserEntity() { return userEntity; }
    public JobEntity getJobEntity() { return jobEntity; }
    public Date getJobReviewedDate() { return jobReviewedDate; }
    
    /* SETTER METHODS */
    public void setJobReportID(Long jobReportID) { this.jobReportID = jobReportID; }
    public void setJobReportStatus(String jobReportStatus) { this.jobReportStatus = jobReportStatus; }
    public void setJobReportDescription(String jobReportDescription) { this.jobReportDescription = jobReportDescription; }
    public void setJobReportDate(Date jobReportDate) { this.jobReportDate = jobReportDate; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setJobEntity(JobEntity jobEntity) { this.jobEntity = jobEntity; }
    public void setJobReviewedDate() { this.jobReviewedDate = new Date(); }
}