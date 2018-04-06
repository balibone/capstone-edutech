/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unifyentities.voices;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="EduExpr")
public class EduExprEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eduID;
    private String schoolName;
    private String schoolPeriod;
    private String schoolDegree;
    private String schoolMajor;
    private ResumeEntity resumeEntity;
    
    public boolean create(String schoolName, String schoolDegree, String schoolMajor, String schoolPeriod, ResumeEntity resumeEntity) {
        this.setEduID(System.nanoTime());
        this.schoolName = schoolName;
        this.schoolDegree= schoolDegree;
        this.schoolMajor = schoolMajor;
        this.schoolPeriod = schoolPeriod;
        this.resumeEntity = resumeEntity;
        return true;
    }
    
    public Long getEduID() { return eduID; }
    public String getSchoolName() { return schoolName; }
    public String getSchoolPeriod() { return schoolPeriod; }
    public String getSchoolDegree() { return schoolDegree; }
    public String getSchoolMajor() { return schoolMajor; }
    public ResumeEntity getResumeEntity() { return resumeEntity; }
    
    public void setEduID(Long eduID) { this.eduID = eduID; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
    public void setSchoolPeriod(String schoolPeriod) { this.schoolPeriod = schoolPeriod; }
    public void setSchoolDegree(String schoolDegree) { this.schoolDegree = schoolDegree; }
    public void setSchoolMajor(String schoolMajor) { this.schoolMajor = schoolMajor; }
    public void setResumeEntity(ResumeEntity resumeEntity) { this.resumeEntity = resumeEntity; }
   
}
