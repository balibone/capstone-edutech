/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.module;

import commoninfraentities.UserEntity;
import edutechentities.common.RecurringEventEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechentities.common.SemesterEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Derian
 */
@Entity(name="Module")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ModuleEntity implements Serializable {

    @Id
    private String moduleCode;
    private String title;
    @OneToMany
    private Collection<ScheduleItemEntity> moduleEvents;
    private Long modularCredit;
    @Lob
    private String description;
    @OneToMany
    private Collection<UserEntity> members;
    @XmlElement
    @ManyToOne
    private SemesterEntity semester;
    @XmlElement
    @XmlInverseReference(mappedBy="module")
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<RecurringEventEntity> recurringEvents;

    public ModuleEntity() {
        this.moduleCode = "";
        this.title = "";
        this.moduleEvents = new ArrayList<>();
        this.modularCredit = Long.valueOf(4);
        this.description = "";
        this.members = new ArrayList<>();
        this.semester = null;
        this.recurringEvents = new ArrayList<>();
    }
    
    public ModuleEntity(String moduleCode, String name, Long modularCredit, String description, SemesterEntity semester) {
        this.moduleCode = moduleCode;
        this.title = name;
        this.moduleEvents = new ArrayList<>();
        this.modularCredit = modularCredit;
        this.description = description;
        this.members = new ArrayList<>();
        this.semester = semester;
        this.recurringEvents = new ArrayList<>();
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
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

    public Collection<ScheduleItemEntity> getModuleEvents() {
        return moduleEvents;
    }

    public void setModuleEvents(Collection<ScheduleItemEntity> moduleEvents) {
        this.moduleEvents = moduleEvents;
    }

    public Collection<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(Collection<UserEntity> members) {
        this.members = (Collection<UserEntity>) members;
    }

    
    public Collection<RecurringEventEntity> getRecurringEvents() {
        return recurringEvents;
    }

    public void setRecurringEvents(Collection<RecurringEventEntity> recurringEvents) {
        this.recurringEvents = recurringEvents;
    }
    
}
