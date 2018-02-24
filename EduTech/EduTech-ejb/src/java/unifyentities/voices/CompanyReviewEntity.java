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

@Entity(name = "CompanyReview")
public class CompanyReviewEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewID;
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
    private String reviewReceiverID;

    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private CompanyEntity companyEntity;
    
    @PrePersist
    public void creationDate() { this.reviewDate = new Date(); }

    public CompanyReviewEntity() { this.setReviewID(System.nanoTime()); }

    public void create(String reviewTitle, double reviewRating, String reviewPros, String reviewCons, String reviewEmpType, String reviewSalaryRange, String reviewReceiverID) {
        this.reviewTitle = reviewTitle;
        this.reviewRating = reviewRating;
        this.reviewPros = reviewPros;
        this.reviewCons = reviewCons;
        this.reviewEmpType = reviewEmpType;
        this.reviewThumbsUp = 0;
        this.reviewSalaryRange = reviewSalaryRange;
        this.reviewReceiverID = reviewReceiverID;
    }

    /* GETTER METHODS */
    public Long getReviewID() { return reviewID; }
    public String getReviewTitle() { return reviewTitle; }
    public Double getReviewRating() { return reviewRating; }
    public String getReviewPros() { return reviewPros; }
    public String getReviewCons() { return reviewCons; }
    public String getReviewEmpType() { return reviewEmpType; }
    public int getReviewThumbsUp() { return reviewThumbsUp; }
    public String getReviewSalaryRange() { return reviewSalaryRange; }
    public Date getReviewDate() { return reviewDate; }
    public String getReviewReceiverID() { return reviewReceiverID; }
    public UserEntity getUserEntity() { return userEntity; }
    public CompanyEntity getCompanyEntity() { return companyEntity; }

    /* SETTER METHODS */
    public void setReviewID(Long reviewID) { this.reviewID = reviewID; }
    public void setReviewTitle(String reviewTitle) { this.reviewTitle = reviewTitle; }
    public void setReviewRating(Double reviewRating) { this.reviewRating = reviewRating; }
    public void setReviewPros(String reviewPros) { this.reviewPros = reviewPros; }
    public void setReviewCons(String reviewCons) { this.reviewCons = reviewCons; }
    public void setReviewEmpType(String reviewEmpType) { this.reviewEmpType = reviewEmpType; }
    public void setReviewThumbsUp(int reviewThumbsUp) { this.reviewThumbsUp = reviewThumbsUp; }
    public void setReviewSalaryRange(String reviewSalaryRange) { this.reviewSalaryRange = reviewSalaryRange; }
    public void setReviewDate(Date reviewDate) { this.reviewDate = reviewDate; }
    public void setReviewReceiverID(String reviewReceiverID) { this.reviewReceiverID = reviewReceiverID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setCompanyEntity(CompanyEntity companyEntity) { this.companyEntity = companyEntity; }
}