/***************************************************************************************
*   Title:                  TagEntity.java
*   Purpose:                LIST OF TAGS (FOR SEARCH FILTER - MARKETPLACE, COMPANY REVIEW, JOB/ERRANDS REVIEW)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/
package unifyentities.common;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import unifyentities.errands.JobEntity;
import unifyentities.voices.CompanyEntity;
import unifyentities.marketplace.ItemEntity;

@Entity(name = "Tag")
public class TagEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagID;
    private String tagName;
    private String tagType;
    
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name="TAGMMBI_JOBMMBI")
    private Collection<JobEntity> jobSet = new ArrayList<JobEntity>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name="TAGMMBI_COMPANYMMBI")
    private Set<CompanyEntity> companySet = new HashSet<CompanyEntity>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name="TAGMMBI_ITEMMMBI")
    private Collection<ItemEntity> itemSet = new ArrayList<ItemEntity>();
    
    /* GETTER METHODS */
    public Long getTagID() { return tagID; }
    public String getTagName() { return tagName; }
    public String getTagType() { return tagType; }
    public Collection<JobEntity> getJobSet() { return jobSet; }
    public Set<CompanyEntity> getCompanySet() { return companySet; }
    public Collection<ItemEntity> getItemSet() { return itemSet; }
    
    /* SETTER METHODS */
    public void setTagID(Long tagID) { this.tagID = tagID; }
    public void setTagName(String tagName) { this.tagName = tagName; }
    public void setTagType(String tagType) { this.tagType = tagType; }
    public void setJobSet(Collection<JobEntity> jobSet) { this.jobSet = jobSet; };
    public void setCompanySet(Set<CompanyEntity> companySet) { this.companySet = companySet; };
    public void setItemSet(Collection<ItemEntity> itemSet) { this.itemSet = itemSet; };
}