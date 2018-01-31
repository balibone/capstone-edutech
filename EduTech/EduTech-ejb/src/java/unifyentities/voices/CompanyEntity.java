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
import javax.persistence.Entity;
import javax.persistence.Id;
import unifyentities.common.CategoryEntity;

@Entity(name = "Company")
public class CompanyEntity implements Serializable {
    @Id
    private String companyName;
    private Double companyAverageRating;
    private String companyImage;
    private String companyWebsite;
    private int companySize;
    private String companyHQ;
    private CategoryEntity companyIndustry;
    
    /* GETTER METHODS */
    public String getCompanyName() { return companyName; }
    public Double getCompanyAverageRating() { return companyAverageRating; }
    public String getCompanyImage() { return companyImage; }
    public String getCompanyWebsite() { return companyWebsite; }
    public int getCompanySize() { return companySize; }
    public String getCompanyHQ() { return companyHQ; }
    public CategoryEntity getCompanyIndustry() { return companyIndustry; }
    
    /* SETTER METHODS */
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setCompanyAverageRating(Double companyAverageRating) { this.companyAverageRating = companyAverageRating; }
    public void setCompanyImage(String companyImage) { this.companyImage = companyImage; }
    public void setCompanyWebsite(String companyWebsite) { this.companyWebsite = companyWebsite; }
    public void setCompanySize(int companySize) { this.companySize = companySize; }
    public void setCompanyHQ(String companyHQ) { this.companyHQ = companyHQ; }
    public void setCompanyIndustry(CategoryEntity companyIndustry) { this.companyIndustry = companyIndustry; }
}