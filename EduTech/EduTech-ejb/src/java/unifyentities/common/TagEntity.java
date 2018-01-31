/***************************************************************************************
*    Title:         TagEntity.java
*    Purpose:       LIST OF TAGS (FOR SEARCH FILTER - MARKETPLACE, COMPANY REVIEW, JOB/ERRANDS REVIEW)
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

@Entity(name = "Tag")
public class TagEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagID;
    private String tagName;
    private String tagType;
    
    /* GETTER METHODS */
    public Long getTagID() { return tagID; }
    public String getTagName() { return tagName; }
    public String getTagType() { return tagType; }
    
    /* SETTER METHODS */
    public void setTagID(Long tagID) { this.tagID = tagID; }
    public void setTagName(String tagName) { this.tagName = tagName; }
    public void setTagType(String tagType) { this.tagType = tagType; }
}
