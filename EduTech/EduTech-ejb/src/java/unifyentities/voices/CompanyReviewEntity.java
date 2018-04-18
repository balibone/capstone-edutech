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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import unifyentities.common.LikeListingEntity;

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
    private String reviewStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    /* FOREIGN KEY */
    private String reviewReceiverID;

    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private CompanyEntity companyEntity;
    
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "reviewEntity")
    private Collection<LikeListingEntity> likeListingSet = new ArrayList<LikeListingEntity>();
    
    @PrePersist
    public void creationDate() { this.reviewDate = new Date(); }

    public CompanyReviewEntity() { this.setReviewID(System.nanoTime()); }

    public boolean createReview(CompanyEntity companyEntity, String reviewTitle,String reviewPros, String reviewCons, double reviewRating, String employmentStatus, String salaryRange, UserEntity userEntity) {
        this.reviewTitle = reviewTitle;
        this.reviewRating = reviewRating;
        this.reviewPros = reviewPros;
        this.reviewCons = reviewCons;
        this.reviewEmpType = employmentStatus;
        this.reviewThumbsUp = 0;
        this.reviewSalaryRange = salaryRange;
        this.userEntity = userEntity;
        this.companyEntity = companyEntity;
        this.reviewDate = new Date();
        this.setReviewID(System.nanoTime());
        this.reviewStatus = "Active";
        return true;
    }

    /* GETTER METHODS */
    public Long getReviewID() { return reviewID; }
    public String getReviewTitle() { return reviewTitle; }
    public String getReviewTitle(Long reviewID) { return reviewTitle; }
    public Double getReviewRating() { return reviewRating; }
    public String getReviewPros() { return reviewPros; }
    public String getReviewCons() { return reviewCons; }
    public String getReviewEmpType() { return reviewEmpType; }
    public int getReviewThumbsUp() { return reviewThumbsUp; }
    public String getReviewSalaryRange() { return reviewSalaryRange; }
    public Date getReviewDate() { return reviewDate; }
    public String getReviewReceiverID() { return reviewReceiverID; }
    public String getReviewStatus() { return reviewStatus; }
    public UserEntity getUserEntity() { return userEntity; }
    public CompanyEntity getCompanyEntity() { return companyEntity; }
    public Collection<LikeListingEntity> getLikeListingSet() { return likeListingSet; }

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
    public void setReviewStatus(String reviewStatus) { this.reviewStatus = reviewStatus; }
    public void setReviewReceiverID(String reviewReceiverID) { this.reviewReceiverID = reviewReceiverID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
    public void setCompanyEntity(CompanyEntity companyEntity) { this.companyEntity = companyEntity; }
    public void setLikeListingSet(Collection<LikeListingEntity> likeListingSet) { this.likeListingSet = likeListingSet; }
}