/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="Resume")
public class ResumeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resumeID;
     @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private String userFullName;
    private String userImage;
    private String contactNum;
    private String emailAddr;
    private String postalAddr;
    private UserEntity userEntity;

    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy = "resumeEntity")
    private Collection<WorkExprEntity> workExprSet = new ArrayList<WorkExprEntity>();
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy = "resumeEntity")
    private Collection<EduExprEntity> eduExprSet = new ArrayList<EduExprEntity>();
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy = "resumeEntity")
    private Collection<ProjectExprEntity> projectExprSet = new ArrayList<ProjectExprEntity>();
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy = "resumeEntity")
    private Collection<SkillEntity> skillSet = new ArrayList<SkillEntity>();

    @PrePersist
    public void creationDate() { this.creationDate = new Date(); }
    public ResumeEntity() { this.setResumeID(System.nanoTime()); }
    
    public boolean createResume(String userFullName, String contactNum, String emailAddr, String postalAddr, Collection<WorkExprEntity> workExprSet, 
                                Collection<EduExprEntity> eduExprSet, Collection<ProjectExprEntity> proExprSet, Collection<SkillEntity> skillSet, String fileName, UserEntity userEntity) {
        this.setResumeID(System.nanoTime());
        this.creationDate = new Date();
        this.userFullName = userFullName;
        this.userImage = fileName;
        this.contactNum = contactNum;
        this.emailAddr = emailAddr;
        this.postalAddr = postalAddr;
        this.workExprSet = workExprSet;
        this.eduExprSet = eduExprSet;
        this.projectExprSet = proExprSet;
        this.skillSet = skillSet;
        this.userEntity = userEntity;
        return true;
    }

    public Long getResumeID() { return resumeID; }
    public Date getCreationDate() { return creationDate; }
    public String getUserFullName() { return userFullName; }
    public String getUserImage() { return userImage; }
    public String getContactNum() { return contactNum; }
    public String getEmailAddr() { return emailAddr; }
    public String getPostalAddr() { return postalAddr; }
    public Collection<WorkExprEntity> getWorkExprSet() { return workExprSet; }
    public Collection<EduExprEntity> getEduSet() { return eduExprSet; }
    public Collection<ProjectExprEntity> getProjectExprSet() { return projectExprSet; }
    public Collection<SkillEntity> getSkillSet() { return skillSet; }
    public UserEntity getUserEntity() { return userEntity; }

    public void setResumeID(Long resumeID) { this.resumeID = resumeID; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    public void setUserFullName(String userFullName) { this.userFullName = userFullName; }
    public void setUserImage(String userImage) { this.userImage = userImage; }
    public void setContactNum(String contactNum) {  this.contactNum = contactNum; }
    public void setEmailAddr(String emailAddr) { this.emailAddr = emailAddr; }
    public void setPostalAddr(String postalAddr) { this.postalAddr = postalAddr; }
    public void setWorkExprSet(Collection<WorkExprEntity> workExprSet) { this.workExprSet = workExprSet; }
    public void setEduSet(Collection<EduExprEntity> eduSet) { this.eduExprSet = eduSet; }
    public void setProjectExprSet(Collection<ProjectExprEntity> projectExprSet) { this.projectExprSet = projectExprSet; }
    public void setSkillSet(Collection<SkillEntity> skillSet) { this.skillSet = skillSet; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }

}

