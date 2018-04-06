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

@Entity(name="Skill")
public class SkillEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long skillID;
    private String skillName;
    private String skillLevel;
    private ResumeEntity resumeEntity;
    
    public boolean create(String skillName, String skillLevel, ResumeEntity resumeEntity) {
        this.setSkillID(System.nanoTime());
        this.skillName = skillName;
        this.skillLevel = skillLevel;
        this.resumeEntity = resumeEntity;
        return true;
    }

    public Long getSkillID() { return skillID; }
    public String getSkillName() { return skillName; }
    public String getSkillLevel() { return skillLevel; }
    public ResumeEntity getResumeEntity() { return resumeEntity; }
    
    public void setSkillID(Long skillID) { this.skillID = skillID; }
    public void setSkillName(String skillName) { this.skillName = skillName; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }
    public void setResumeEntity(ResumeEntity resumeEntity) { this.resumeEntity = resumeEntity;}
    
}
