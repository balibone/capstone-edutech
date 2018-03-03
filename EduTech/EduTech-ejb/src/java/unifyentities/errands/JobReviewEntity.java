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

@Entity(name = "JobReview")
public class JobReviewEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobReviewID;
    private String jobReviewRating;
    private String jobReviewContent;
    private String jobReviewStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobReviewDate;
    
    /* FOREIGN KEY */
    private String jobReceiverID;
    
    @ManyToOne
    private JobEntity jobEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.jobReviewDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getJobReviewID() { return jobReviewID; }
    public String getJobReviewRating() { return jobReviewRating; }
    public String getJobReviewContent() { return jobReviewContent; }
    public Date getJobReviewDate() { return jobReviewDate; }
    public String getJobReceiverID() { return jobReceiverID; }
    public String getJobReviewStatus() { return jobReviewStatus; }
    public JobEntity getJobEntity() { return jobEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setJobReviewID(Long jobReviewID) { this.jobReviewID = jobReviewID; }
    public void setJobReviewRating(String jobReviewRating) { this.jobReviewRating = jobReviewRating; }
    public void setJobReviewContent(String jobReviewContent) { this.jobReviewContent = jobReviewContent; }
    public void setJobReviewDate(Date jobReviewDate) { this.jobReviewDate = jobReviewDate; }
    public void setJobReceiverID(String jobReceiverID) { this.jobReceiverID = jobReceiverID; }
    public void setJobReviewStatus(String jobReviewStatus) { this.jobReviewStatus = jobReviewStatus; }
    public void setJobEntity(JobEntity jobEntity) { this.jobEntity = jobEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}