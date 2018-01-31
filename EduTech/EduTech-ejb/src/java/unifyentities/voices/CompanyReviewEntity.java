/***************************************************************************************
*    Title:         CompanyReviewEntity.java
*    Purpose:       LIST OF COMPANY REVIEWS PROVIDED BY CAMPUS USERS
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/
package unifyentities.voices;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "CompanyReview")
public class CompanyReviewEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewID;
    private String reviewCompanyName;
    private String reviewTitle;
    private Double reviewRating;
    private String reviewPros;
    private String reviewCons;
    private String reviewEmpType;
    private int reviewThumbsUp;
    private String reviewSalaryRange;
    
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    
    /* FOREIGN KEY */
    private String reviewPosterID;
    
    @PrePersist
    public void creationDate() { this.reviewDate = new Date(); }
    
    /* GETTER METHODS */
    public Long getReviewID() { return reviewID; }
    public String getReviewCompanyName() { return reviewCompanyName; }
    public String getReviewTitle() { return reviewTitle; }
    public Double getReviewRating() { return reviewRating; }
    public String getReviewPros() { return reviewPros; }
    public String getReviewCons() { return reviewCons; }
    public String getReviewEmpType() { return reviewEmpType; }
    public int getReviewThumbsUp() { return reviewThumbsUp; }
    public String getReviewSalaryRange() { return reviewSalaryRange; }
    public Date getReviewDate() { return reviewDate; }
    public String getReviewPosterID() { return reviewPosterID; }
    
    /* SETTER METHODS */
    public void setReviewID(Long reviewID) { this.reviewID = reviewID; }
    public void setReviewCompanyName(String reviewCompanyName) { this.reviewCompanyName = reviewCompanyName; }
    public void setReviewTitle(String reviewTitle) { this.reviewTitle = reviewTitle; }
    public void setReviewRating(Double reviewRating) { this.reviewRating = reviewRating; }
    public void setReviewPros(String reviewPros) { this.reviewPros = reviewPros; }
    public void setReviewCons(String reviewCons) { this.reviewCons = reviewCons; }
    public void setReviewEmpType(String reviewEmpType) { this.reviewEmpType = reviewEmpType; }
    public void setReviewThumbsUp(int reviewThumbsUp) { this.reviewThumbsUp = reviewThumbsUp; }
    public void setReviewSalaryRange(String reviewSalaryRange) { this.reviewSalaryRange = reviewSalaryRange; }
    public void setReviewDate(Date reviewDate) { this.reviewDate = reviewDate; }
    public void setReviewPosterID(String reviewPosterID) { this.reviewPosterID = reviewPosterID; }
}