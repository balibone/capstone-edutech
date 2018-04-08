/***************************************************************************************
*    Title:         JobTransactionEntity.java
*    Purpose:       LIST OF JOB TRANSACTIONS PERFORMED BY CAMPUS USERS
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
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;

@Entity(name = "JobTransaction")
public class JobTransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobTransactionID;
    private String jobCategory;
    private Double jobTransactionRate;
    private String jobTransactionRateType;
    private String signatureImg;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobTransactionDate;

    /* FOREIGN KEY */
    private String jobTakerID;

    @ManyToOne
    private JobEntity jobEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.jobTransactionDate = new Date(); }

    public JobTransactionEntity(){
        setJobTransactionID(System.nanoTime());
    }
    
    public boolean createJobTransaction(String jobCategory, double transRate, String transRateType, String takerID){
        
        this.jobCategory = jobCategory;
        this.jobTransactionRate = transRate;
        this.jobTransactionRateType = transRateType;
        this.jobTakerID = takerID;
        
        return true;
    }
    
    /* GETTER METHODS */
    public Long getJobTransactionID() { return jobTransactionID; }
    public String getJobCategory() { return jobCategory; }
    public Double getJobTransactionRate() { return jobTransactionRate; }
    public String getJobTransactionRateType() { return jobTransactionRateType; }
    public String getSignatureImg() { return signatureImg; }
    public Date getJobTransactionDate() { return jobTransactionDate; }
    public String getJobTakerID() { return jobTakerID; }
    public JobEntity getJobEntity() { return jobEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setJobTransactionID(Long jobTransactionID) { this.jobTransactionID = jobTransactionID; }
    public void setJobCategory(String jobCategory) { this.jobCategory = jobCategory; }
    public void setJobTransactionRate(Double jobTransactionRate) { this.jobTransactionRate = jobTransactionRate; }
    public void setJobTransactionRateType(String jobTransactionRateType) { this.jobTransactionRateType = jobTransactionRateType; }
    public void setSignatureImg(String signatureImg) { this.signatureImg = signatureImg; }
    public void setJobTransactionDate(Date jobTransactionDate) { this.jobTransactionDate = jobTransactionDate; }
    public void setJobTakerID(String jobTakerID) { this.jobTakerID = jobTakerID; }
    public void setJobEntity(JobEntity jobEntity) { this.jobEntity = jobEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}