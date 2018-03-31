package unifyentities.errands;

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

@Entity(name = "JobOffer")
public class JobOfferEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobOfferID;
    private double jobOfferPrice;
    private String jobOfferDescription;
    /* JOB OFFER STATUS: PENDING, ACCEPTED, REJECTED */
    private String jobOfferStatusForSender;
    private String jobOfferStatusForPoster;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobOfferDate;
    
    @ManyToOne
    private JobEntity jobEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.jobOfferDate = new Date(); }
    
    /* DEFAULT CONSTRUCTOR */
    public JobOfferEntity() { 
        
        setJobOfferID(System.nanoTime());
    }
    
    /* MISCELLANEOUS METHODS */
    public boolean createJobOffer(double jobOfferPrice, String jobOfferDescription) {
        this.jobOfferPrice = jobOfferPrice;
        this.jobOfferDescription = jobOfferDescription;
        this.jobOfferStatusForSender = "Processing";
        this.jobOfferStatusForPoster = "Pending";
        return true;
    }
    
    /* GETTER METHODS */
    public Long getJobOfferID() { return jobOfferID; }
    public double getJobOfferPrice() { return jobOfferPrice; }
    public String getJobOfferDescription() { return jobOfferDescription; }
    public String getJobOfferStatusForSender() { return jobOfferStatusForSender; }
    public String getJobOfferStatusForPoster() { return jobOfferStatusForPoster; }
    public Date getJobOfferDate() { return jobOfferDate; }
    
    public JobEntity getJobEntity() { return jobEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setJobOfferID(Long jobOfferID) { this.jobOfferID = jobOfferID; }
    public void setJobOfferPrice(double jobOfferPrice) { this.jobOfferPrice = jobOfferPrice; }
    public void setJobOfferDescription(String jobOfferDescription) { this.jobOfferDescription = jobOfferDescription; }
    public void setJobOfferStatusForSender(String jobOfferStatusForSender) { this.jobOfferStatusForSender = jobOfferStatusForSender; }
    public void setJobOfferStatusForPoster(String jobOfferStatusForPoster) { this.jobOfferStatusForPoster = jobOfferStatusForPoster; }
    public void setJobOfferDate(Date jobOfferDate) { this.jobOfferDate = jobOfferDate; }
    
    public void setJobEntity(JobEntity jobEntity) { this.jobEntity = jobEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}