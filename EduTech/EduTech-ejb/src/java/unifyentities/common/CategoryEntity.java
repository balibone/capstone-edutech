/***************************************************************************************
*    Title:         CategoryEntity.java
*    Purpose:       LIST OF CATEGORIES (FOR NAVIGATION - MARKETPLACE, COMPANY REVIEW, JOB/ERRANDS REVIEW)
*    Author:        TAN CHIN WEE
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          31 JANUARY 2018
*    Code version:  1.0
*    Availability:  RESTRICTED
*
***************************************************************************************/
package unifyentities.common;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import unifyentities.voices.CompanyEntity;
import unifyentities.marketplace.ItemEntity;
import unifyentities.errands.JobEntity;

@Entity(name = "Category")
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryID;
    private String categoryName;
    private String categoryType;
    private String categoryDescription;
    private String categoryImage;
    private Boolean categoryActiveStatus;
    
    // @OneToMany(mappedBy = "categoryEntity")
    // private Set<CompanyEntity> companySet = new HashSet<CompanyEntity>();
    @OneToMany(mappedBy = "categoryEntity")
    private Collection<ItemEntity> itemSet = new ArrayList<ItemEntity>();
    // @OneToMany(mappedBy = "categoryEntity")
    // private Collection<JobEntity> jobSet = new ArrayList<JobEntity>();
    
    /* DEFAULT CONSTRUCTOR */
    public CategoryEntity() { categoryActiveStatus = true; }
    
    /* MISCELLANEOUS METHODS */
    public void createCategory(String categoryName, String categoryType, String categoryDescription, String categoryImage) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.categoryDescription = categoryDescription;
        this.categoryImage = categoryImage;
    }
    
    /* GETTER METHODS */
    public Long getCategoryID() { return categoryID; }
    public String getCategoryName() { return categoryName; }
    public String getCategoryType() { return categoryType; }
    public String getCategoryDescription() { return categoryDescription; }
    public String getCategoryImage() { return categoryImage; }
    public Boolean getCategoryActiveStatus() { return categoryActiveStatus; }
    // public Set<CompanyEntity> getCompanySet() { return companySet; }
    public Collection<ItemEntity> getItemSet() { return itemSet; }
    // public Collection<JobEntity> getJobSet() { return jobSet; }
    
    /* SETTER METHODS */
    public void setCategoryID(Long categoryID) { this.categoryID = categoryID; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setCategoryType(String categoryType) { this.categoryType = categoryType; }
    public void setCategoryDescription(String categoryDescription) { this.categoryDescription = categoryDescription; }
    public void setCategoryImage(String categoryImage) { this.categoryImage = categoryImage; }
    public void setCategoryActiveStatus(Boolean categoryActiveStatus) { this.categoryActiveStatus = categoryActiveStatus; }
    // public void setCompanySet(Set<CompanyEntity> companySet) { this.companySet = companySet; }
    public void setItemSet(Collection<ItemEntity> itemSet) { this.itemSet = itemSet; }
    // public void setJobSet(Collection<JobEntity> jobSet) { this.jobSet = jobSet; }
}
