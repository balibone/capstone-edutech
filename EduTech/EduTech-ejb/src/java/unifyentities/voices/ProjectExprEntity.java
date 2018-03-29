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

@Entity(name="ProjectExpr")
public class ProjectExprEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectID;
    private String projectTitle;
    private String projectDescription;
    private ResumeEntity resumeEntity;
    
    public boolean create(String projectTitle, String projectDescription, ResumeEntity resumeEntity) {
        this.setProjectID(System.nanoTime());
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.resumeEntity = resumeEntity;
        return true;
    }
    
    public Long getProjectID() { return projectID; }
    public String getProjectTitle() { return projectTitle; }
    public String getProjectDescription() { return projectDescription; }
    public ResumeEntity getResumeEntity() { return resumeEntity; }
    
    public void setProjectID(Long projectID) { this.projectID = projectID; }
    public void setProjectTitke(String projectTitle) { this.projectTitle = projectTitle; }
    public void setProjectDescription(String projectDescription) { this.projectDescription = projectDescription; }
    public void setResumeEntity(ResumeEntity resumeEntity) { this.resumeEntity = resumeEntity; }
    
}
