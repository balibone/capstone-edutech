/***************************************************************************************
*   Title:                  JobEntity.java
*   Purpose:                JOBS POSTED BY CAMPUS USERS
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/
package unifyentities.errands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import unifyentities.common.CategoryEntity;
import commoninfrastructureentities.UserEntity;
import unifyentities.common.LikeListingEntity;
import unifyentities.common.TagEntity;
import unifyentities.common.JobReportEntity;

@Entity(name = "Job")
public class JobEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobID;
    private String jobTitle;
    private String jobDescription;
    private String jobStartLocation;
    private String jobStartLat;
    private String jobStartLong;
    private String jobEndLocation;
    private String jobEndLat;
    private String jobEndLong;
    private String jobInformation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobWorkDate;
    private Double jobRate;
    private String jobRateType;
    private Double jobDuration;
    private String jobImage;
    private String jobStatus;
    private int jobNumOfLikes;
    private int numOfHelpers;
    private boolean checking;

    @Temporal(TemporalType.TIMESTAMP)
    private Date jobPostDate;

    /* FOREIGN KEY */
    private String jobTakerID;

    @ManyToOne
    private CategoryEntity categoryEntity;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany(mappedBy = "jobEntity")
    private Collection<JobOfferEntity> jobOfferSet = new ArrayList<JobOfferEntity>();
    @OneToMany(mappedBy = "jobEntity")
    private Collection<JobReportEntity> jobReportSet = new ArrayList<JobReportEntity>();
    @OneToMany(mappedBy = "jobEntity")
    private Collection<JobReviewEntity> jobReviewSet = new ArrayList<JobReviewEntity>();
    @OneToMany(mappedBy = "jobEntity")
    private Collection<JobTransactionEntity> jobTransactionSet = new ArrayList<JobTransactionEntity>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "jobEntity")
    private Collection<LikeListingEntity> likeListingSet = new ArrayList<LikeListingEntity>();
    
    @ManyToMany(cascade={CascadeType.PERSIST}, mappedBy = "jobSet")
    private Set<TagEntity> tagSet = new HashSet<TagEntity>();
    
    @PrePersist
    public void creationDate() { this.jobPostDate = new Date(); }
    
    /* DEFAULT CONSTRUCTOR */
    public JobEntity() { 
        
        setJobID(System.nanoTime());
        jobStatus = "Available";
    }
    
    public boolean createJobListing(String jobTitle, String jobDescription, String jobImagefileName, String jobRateType, 
            double jobRate, double jobDuration, String startLocation, String startLat, String startLong, String endLocation, String endLat, String endLong, Date workDate, String jobInformation, int numOfHelpers, boolean checking){
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobImage = jobImagefileName;
        this.jobRateType = jobRateType;
        this.jobRate = jobRate;
        this.jobDuration = jobDuration;
        this.jobStartLocation = startLocation;
        this.jobStartLat = startLat;
        this.jobStartLong = startLong;
        this.jobEndLocation = endLocation;
        this.jobEndLat = endLat;
        this.jobEndLong = endLong;
        this.jobWorkDate = workDate;
        this.jobInformation = jobInformation;
        this.numOfHelpers = numOfHelpers;
        this.checking = checking;
        
        return true;
    }

    /* GETTER METHODS */
    public Long getJobID() { return jobID; }
    public String getJobTitle() { return jobTitle; }
    public String getJobDescription() { return jobDescription; }
    public String getJobStartLocation() { return jobStartLocation; }
    public String getJobStartLat() { return jobStartLat; }
    public String getJobStartLong() { return jobStartLong; }
    public String getJobEndLocation() { return jobEndLocation; }
    public String getJobEndLat() { return jobEndLat; }
    public String getJobEndLong() { return jobEndLong; }
    public String getJobInformation() { return jobInformation; }
    public Date getJobWorkDate() { return jobWorkDate; }
    public Double getJobRate() { return jobRate; }
    public Double getJobDuration() { return jobDuration; }
    public String getJobRateType() { return jobRateType; }
    public String getJobImage() { return jobImage; }
    public String getJobStatus() { return jobStatus; }
    public int getJobNumOfLikes() { return jobNumOfLikes; }
    public Date getJobPostDate() { return jobPostDate; }
    public String getJobTakerID() { return jobTakerID; }
    public int getNumOfHelpers() { return numOfHelpers; }
    public boolean getChecking() { return checking;}
    
    public CategoryEntity getCategoryEntity() { return categoryEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    public Collection<JobOfferEntity> getJobOfferSet() { return jobOfferSet; }
    public Collection<JobReportEntity> getJobReportSet() { return jobReportSet; }
    public Collection<JobReviewEntity> getJobReviewSet() { return jobReviewSet; }
    public Collection<JobTransactionEntity> getJobTransactionSet() { return jobTransactionSet; }
    public Collection<LikeListingEntity> getLikeListingSet() { return likeListingSet; }
    public Set<TagEntity> getTagSet() { return tagSet; }

    /* SETTER METHODS */
    public void setJobID(Long jobID) { this.jobID = jobID; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
    public void setJobStartLocation(String jobStartLocation) { this.jobStartLocation = jobStartLocation; }
    public void setJobStartLat(String jobStartLat) { this.jobStartLat = jobStartLat; }
    public void setJobStartLong(String jobStartLong) { this.jobStartLong = jobStartLong; }
    public void setJobEndLocation(String jobEndLocation) { this.jobEndLocation = jobEndLocation; }
    public void setJobEndLat(String jobEndLat) { this.jobEndLat = jobEndLat; }
    public void setJobEndLong(String jobEndLong) { this.jobEndLong = jobEndLong; }
    public void setJobInformation(String jobInformation) { this.jobInformation = jobInformation; }
    public void setJobWorkDate(Date jobWorkDate) { this.jobWorkDate = jobWorkDate; }
    public void setJobRate(Double jobRate) { this.jobRate = jobRate; }
    public void setJobDuration(Double jobDuration) { this.jobDuration = jobDuration; }
    public void setJobRateType(String jobRateType) { this.jobRateType = jobRateType; }
    public void setJobImage(String jobImage) { this.jobImage = jobImage; }
    public void setJobStatus(String jobStatus) { this.jobStatus = jobStatus; }
    public void setJobNumOfLikes(int jobNumOfLikes) { this.jobNumOfLikes = jobNumOfLikes; }
    public void setJobPostDate(Date jobPostDate) { this.jobPostDate = jobPostDate; }
    public void setJobTakerID(String jobTakerID) { this.jobTakerID = jobTakerID; }
    public void setNumOfHelpers(int numOfHelpers) { this.numOfHelpers = numOfHelpers; }
    public void setChecking(boolean checking) { this.checking = checking; }
    
    public void setCategoryEntity(CategoryEntity categoryEntity) { this.categoryEntity = categoryEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setJobOfferSet(Collection<JobOfferEntity> jobOfferSet) { this.jobOfferSet = jobOfferSet; }
    public void setJobReportSet(Collection<JobReportEntity> jobReportSet) { this.jobReportSet = jobReportSet; }
    public void setJobReviewSet(Collection<JobReviewEntity> jobReviewSet) { this.jobReviewSet = jobReviewSet; }
    public void setJobTransactionSet(Collection<JobTransactionEntity> jobTransactionSet) { this.jobTransactionSet = jobTransactionSet; }
    public void setLikeListingSet(Collection<LikeListingEntity> likeListingSet) { this.likeListingSet = likeListingSet; }
    public void setTagSet(Set<TagEntity> tagSet) { this.tagSet = tagSet; }
}