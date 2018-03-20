/***************************************************************************************
*    Title:         CompanyRequestEntity.java
*    Purpose:       CAMPUS USERS CAN REVIEW JOBS (INTERN/FULLTIME/PARTIME) OF A PARTICULAR COMPANY
*    Created By:    ZHU XINYI
*    Modified By:   TAN CHIN WEE WINSTON
*    Credits:       CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE, ZHU XINYI
*    Date:          24 FEBRUARY 2018
*    Code version:  1.1
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity(name = "CompanyRequest")
public class CompanyRequestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestID;
    private String requestCompany;
    private String requestIndustry;
    private String requestStatus;
    private String requestComment;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    
    private String requestPosterID;
    
    @ManyToOne
    private UserEntity userEntity;
    
    @PrePersist
    public void creationDate() { this.requestDate = new Date(); }

    public CompanyRequestEntity() { this.setRequestID(System.nanoTime()); }
    
    public boolean createRequest(String requestCompany, String companyIndustry, String requestComment, UserEntity requestPoster) {
        this.setRequestID(System.nanoTime());
        this.requestDate = new Date();
        this.requestCompany = requestCompany;
        this.requestIndustry = companyIndustry;
        this.requestStatus = "Pending";
        this.requestComment = requestComment;
        this.userEntity = requestPoster;
        return true;
    }

    public Long getRequestID() { return requestID; }
    public String getRequestCompany() { return requestCompany; }
    public String getRequestIndustry() { return requestIndustry; }
    public String getRequestStatus() { return requestStatus; }
    public String getRequestComment() { return requestComment; }
    public Date getRequestDate() { return requestDate; }
    public String getRequestPosterID() { return requestPosterID; }
    public UserEntity getUserEntity() { return userEntity; }

    public void setRequestID(Long requestID) {  this.requestID = requestID; }
    public void setRequestCompany(String requestCompany) { this.requestCompany = requestCompany; }
    public void setRequestIndustry(String requestIndustry) { this.requestIndustry = this.requestIndustry; }
    public void setRequestStatus(String requestStatus) { this.requestStatus = requestStatus; }
    public void setRequestComment(String requestComment) {this.requestComment = requestComment; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }
    public void setRequestPosterID(String requestPosterID) { this.requestPosterID = requestPosterID; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }  
}