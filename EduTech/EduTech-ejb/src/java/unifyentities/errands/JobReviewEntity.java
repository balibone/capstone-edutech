/***************************************************************************************
*    Title:         JobReviewEntity.java
*    Purpose:       CAMPUS USERS CAN REVIEW JOBS (INTERN/FULLTIME/PARTIME) OF A PARTICULAR COMPANY
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/
package unifyentities.errands;

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

@Entity(name = "JobReview")
public class JobReviewEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobReviewID;
    private String jobReviewIndex;
    private String jobReviewContent;
    
    @Temporal(TemporalType.DATE)
    private Date jobReviewDate;
    
    /* FOREIGN KEY */
    private Long jobID;
    private String jobReviewerID;
    private String jobReceiverID;
    
    @ManyToOne
    private JobEntity jobEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.jobReviewDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getJobReviewID() { return jobReviewID; }
    public String getJobReviewIndex() { return jobReviewIndex; }
    public String getJobReviewContent() { return jobReviewContent; }
    public Date getJobReviewDate() { return jobReviewDate; }
    public Long getJobID() { return jobID; }
    public String getJobReviewerID() { return jobReviewerID; }
    public String getJobReceiverID() { return jobReceiverID; }
    public JobEntity getJobEntity() { return jobEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setJobReviewID(Long jobReviewID) { this.jobReviewID = jobReviewID; }
    public void setJobReviewIndex(String jobReviewIndex) { this.jobReviewIndex = jobReviewIndex; }
    public void setJobReviewContent(String jobReviewContent) { this.jobReviewContent = jobReviewContent; }
    public void setJobReviewDate(Date jobReviewDate) { this.jobReviewDate = jobReviewDate; }
    public void setJobID(Long jobID) { this.jobID = jobID; }
    public void setJobReviewerID(String jobReviewerID) { this.jobReviewerID = jobReviewerID; }
    public void setJobReceiverID(String jobReceiverID) { this.jobReceiverID = jobReceiverID; }
    public void setJobEntity(JobEntity jobEntity) { this.jobEntity = jobEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}