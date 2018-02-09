/***************************************************************************************
*    Title:         CompanyEntity.java
*    Purpose:       LIST OF COMPANIES (FOR COMPANY REVIEW)
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/
package unifyentities.voices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import unifyentities.common.CategoryEntity;
import unifyentities.common.TagEntity;

@Entity(name = "Company")
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyID;
    private String companyName;
    private Double companyAverageRating;
    private String companyImage;
    private String companyWebsite;
    private String companyHQ;
    private String companyStatus;
    private String companyDescription;
    private int companySize;
    
    @ManyToOne
    private CategoryEntity categoryEntity;
    @OneToMany(mappedBy = "companyEntity")
    private Collection<CompanyReviewEntity> companyReviewSet = new ArrayList<CompanyReviewEntity>();
    @ManyToMany(cascade={CascadeType.PERSIST}, mappedBy = "companySet")
    private Set<TagEntity> tagSet = new HashSet<TagEntity>();
    
    public CompanyEntity() { this.setCompanyID(System.nanoTime()); }

    public void create(String companyName, String companyWebsite, String companyHQ, int companySize, 
            CategoryEntity categoryEntity, String companyDescription, String companyImage) {
      this.companyName = companyName;
      this.companyAverageRating = 0.00;
      this.companyWebsite = companyWebsite;
      this.companyHQ = companyHQ;
      this.companySize = companySize;
      this.categoryEntity = categoryEntity;
      this.companyStatus = "Active";
      this.companyDescription = companyDescription;
      this.companyImage = companyImage;
    }

    /* GETTER METHODS */
    public Long getCompanyID() {return companyID; }
    public String getCompanyName() { return companyName; }
    public Double getCompanyAverageRating() { return companyAverageRating; }
    public String getCompanyImage() { return companyImage; }
    public String getCompanyWebsite() { return companyWebsite; }
    public int getCompanySize() { return companySize; }
    public String getCompanyHQ() { return companyHQ; }
    public String getCompanyStatus() {return companyStatus; }
    public String getCompanyDescription() {return companyDescription; }
    
    public CategoryEntity getCategoryEntity() { return categoryEntity; }
    public Collection<CompanyReviewEntity> getCompanyReviewSet() { return companyReviewSet; }
    public Set<TagEntity> getTagSet() { return tagSet; }
    
    /* SETTER METHODS */
    public void setCompanyID(Long companyID) {this.companyID = companyID; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setCompanyAverageRating(Double companyAverageRating) { this.companyAverageRating = companyAverageRating; }
    public void setCompanyImage(String companyImage) { this.companyImage = companyImage; }
    public void setCompanyWebsite(String companyWebsite) { this.companyWebsite = companyWebsite; }
    public void setCompanySize(int companySize) { this.companySize = companySize; }
    public void setCompanyHQ(String companyHQ) { this.companyHQ = companyHQ; }
    public void setCompanyStatus(String companyStatus) { this.companyStatus = companyStatus; }
    public void setCompanyDescription(String companyDescription) { this.companyDescription = companyDescription; }
    
    public void setCategoryEntity(CategoryEntity categoryEntity) { this.categoryEntity = categoryEntity; }
    public void setCompanyReviewSet(Collection<CompanyReviewEntity> companyReviewSet) { this.companyReviewSet = companyReviewSet; }
    public void setTagSet(Set<TagEntity> tagSet) { this.tagSet = tagSet; }
}