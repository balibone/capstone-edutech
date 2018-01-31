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

@Entity(name = "Category")
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryID;
    private String categoryName;
    private String categoryType;
    private String categoryDescription;
    
    /* GETTER METHODS */
    public Long getCategoryID() { return categoryID; }
    public String getCategoryName() { return categoryName; }
    public String getCategoryType() { return categoryType; }
    public String getCategoryDescription() { return categoryDescription; }
    
    /* SETTER METHODS */
    public void setCategoryID(Long categoryID) { this.categoryID = categoryID; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setCategoryType(String categoryType) { this.categoryType = categoryType; }
    public void setCategoryDescription(String categoryDescription) { this.categoryDescription = categoryDescription; }
}
