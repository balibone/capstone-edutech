/***************************************************************************************
*    Title:         JobEntity.java
*    Purpose:       JOBS POSTED BY CAMPUS USERS
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/
package unifyentities.errands;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import unifyentities.common.CategoryEntity;

@Entity(name = "Job")
public class JobEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobID;
    private String jobTitle;
    private String jobDescription;
    private String jobStartLocation;
    private Double jobStartLat;
    private Double jobStartLong;
    private String jobEndLocation;
    private Double jobEndLat;
    private Double jobEndLong;
    @Temporal(TemporalType.DATE)
    private Date jobWorkDate;
    private CategoryEntity jobCategory;
    private Double jobRate;
    private String jobRateType;
    private String jobImage;
    
    @Temporal(TemporalType.DATE)
    private Date jobPostDate;
    
    /* FOREIGN KEY */
    private Long jobPosterID;
    private Long jobTakerID;
    
    @PrePersist
    public void creationDate() { this.jobPostDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getJobID() { return jobID; }
    public String getJobTitle() { return jobTitle; }
    public String getJobDescription() { return jobDescription; }
    public String getJobStartLocation() { return jobStartLocation; }
    public Double getJobStartLat() { return jobStartLat; }
    public Double getJobStartLong() { return jobStartLong; }
    public String getJobEndLocation() { return jobEndLocation; }
    public Double getJobEndLat() { return jobEndLat; }
    public Double getJobEndLong() { return jobEndLong; }
    public Date getJobWorkDate() { return jobWorkDate; }
    public CategoryEntity getJobCategory() { return jobCategory; }
    public Double getJobRate() { return jobRate; }
    public String getJobRateType() { return jobRateType; }
    public String getJobImage() { return jobImage; }
    public Date getJobPostDate() { return jobPostDate; }
    public Long getJobPosterID() { return jobPosterID; }
    public Long getJobTakerID() { return jobTakerID; }
    
    /* SETTER METHODS */
    public void setJobID(Long jobID) { this.jobID = jobID; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
    public void setJobStartLocation(String jobStartLocation) { this.jobStartLocation = jobStartLocation; }
    public void setJobStartLat(Double jobStartLat) { this.jobStartLat = jobStartLat; }
    public void setJobStartLong(Double jobStartLong) { this.jobStartLong = jobStartLong; }
    public void setJobEndLocation(String jobEndLocation) { this.jobEndLocation = jobEndLocation; }
    public void setJobEndLat(Double jobEndLat) { this.jobEndLat = jobEndLat; }
    public void setJobEndLong(Double jobEndLong) { this.jobEndLong = jobEndLong; }
    public void setJobWorkDate(Date jobWorkDate) { this.jobWorkDate = jobWorkDate; }
    public void setJobCategory(CategoryEntity jobCategory) { this.jobCategory = jobCategory; }
    public void setJobRate(Double jobRate) { this.jobRate = jobRate; }
    public void setJobRateType(String jobRateType) { this.jobRateType = jobRateType; }
    public void setJobImage(String jobImage) { this.jobImage = jobImage; }
    public void setJobPostDate(Date jobPostDate) { this.jobPostDate = jobPostDate; }
    public void setJobPosterID(Long jobPosterID) { this.jobPosterID = jobPosterID; }
    public void setJobTakerID(Long jobTakerID) { this.jobTakerID = jobTakerID; }
}
