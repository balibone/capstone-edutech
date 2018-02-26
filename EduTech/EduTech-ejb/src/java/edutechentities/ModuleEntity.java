/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Derian
 */
@Entity(name="Module")
public class ModuleEntity implements Serializable {

    @Id
    private String moduleCode;
    private String name;
    //private Collection<ScheduleItem> keyDates;
    private Long modularCredit;
    private String description;
    //private Collection<UserEntity> instructors;
    //private Collection<UserEntity> students;
    //private Collection<LessonEntity> lessons;
    @ManyToOne
    private SemesterEntity semester;
    private Boolean activeStatus;

    public ModuleEntity() {
    }

    public ModuleEntity(String moduleCode, String name, Long modularCredit, String description, SemesterEntity semester) {
        this.moduleCode = moduleCode;
        this.name = name;
        this.modularCredit = modularCredit;
        this.description = description;
        this.semester = semester;
        this.activeStatus = true;
    }

    public Long getModularCredit() {
        return modularCredit;
    }
    public void setModularCredit(Long modularCredit) {
        this.modularCredit = modularCredit;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public SemesterEntity getSemester() {
        return semester;
    }
    public void setSemester(SemesterEntity semester) {
        this.semester = semester;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public Boolean getActiveStatus() {
        return activeStatus;
    }
    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getModuleCode() != null ? getModuleCode().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the moduleCode fields are not set
        if (!(object instanceof ModuleEntity)) {
            return false;
        }
        ModuleEntity other = (ModuleEntity) object;
        if ((this.getModuleCode() == null && other.getModuleCode() != null) || (this.getModuleCode() != null && !this.moduleCode.equals(other.moduleCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.ModuleEntity[ id=" + getModuleCode() + " ]";
    }


    
}
