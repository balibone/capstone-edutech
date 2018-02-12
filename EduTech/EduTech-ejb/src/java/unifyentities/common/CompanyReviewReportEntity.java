/***************************************************************************************
*   Title:                  CompanyReviewReportEntity.java
*   Purpose:                LIST OF COMPANYREVIEW-RELATED REPORTS BY THE CAMPUS USERS
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

@Entity(name = "CompanyReviewReport")
public class CompanyReviewReportEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewReportID;
    private String reviewReportStatus;
    private String reviewReportDescription;
    
    @Temporal(TemporalType.DATE)
    private Date reviewReportDate;
    
    /* FOREIGN KEY */
    private String reviewReporterID;
    private String reviewPosterID;
    private String reviewID;
        
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.reviewReportDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getReviewReportID() { return reviewReportID; }
    public String getReviewReportStatus() { return reviewReportStatus; }
    public String getReviewReportDescription() { return reviewReportDescription; }
    public Date getReviewReportDate() { return reviewReportDate; }
    public String getReviewReporterID() { return reviewReporterID; }
    public String getReviewPosterID() { return reviewPosterID; }
    public String getReviewID() { return reviewID; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setReviewReportID(Long reviewReportID) { this.reviewReportID = reviewReportID; }
    public void setReviewReportStatus(String reviewReportStatus) { this.reviewReportStatus = reviewReportStatus; }
    public void setReviewReportDescription(String reviewReportDescription) { this.reviewReportDescription = reviewReportDescription; }
    public void setReviewReportDate(Date reviewReportDate) { this.reviewReportDate = reviewReportDate; }
    public void setReviewReporterID(String reviewReporterID) { this.reviewReporterID = reviewReporterID; }
    public void setReviewPosterID(String reviewPosterID) { this.reviewPosterID = reviewPosterID; }
    public void setReviewID(String reviewID) { this.reviewID = reviewID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}