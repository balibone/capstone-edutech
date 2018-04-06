/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unifyentities.voices;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="WorkExpr")
public class WorkExprEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workID;
    private String workTitle;
    private String workCompany;
    private String workPeriod;
    private String workDescription;
    private ResumeEntity resumeEntity;  
    
    public boolean create(String workTitle, String workCompany, String workPeriod, String workDescription, ResumeEntity resumeEntity) {
        this.setWorkID(System.nanoTime());
        this.workTitle = workTitle;
        this.workCompany = workCompany;
        this.workPeriod = workPeriod;
        this.workDescription = workDescription;
        this.resumeEntity = resumeEntity;
        return true;
    }
    
    public Long getWorkID() { return workID; }
    public String getWorkTitle() { return workTitle;}
    public String getWorkCompany() { return workCompany; }
    public String getWorkPeriod() { return workPeriod; }
    public String getWorkDescription() { return workDescription; }
    public ResumeEntity getResumeEntity() { return resumeEntity; }
    
    public void setWorkID(Long workID) { this.workID = workID; }
    public void setWorkTitle(String workTitle) { this.workTitle = workTitle; }
    public void setWorkCompany(String workCompany) { this.workCompany = workCompany; }
    public void setWorkPeriod(String workPeriod) { this.workPeriod = workPeriod; }
    public void setWorkDescription(String workDescription) { this.workDescription = workDescription; }
    public void setResumeEntity(ResumeEntity resumeEntity) { this.resumeEntity = resumeEntity; }
    
}
