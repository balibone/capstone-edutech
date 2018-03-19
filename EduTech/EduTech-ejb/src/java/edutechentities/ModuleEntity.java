package edutechentities;

import commoninfrastructureentities.UserEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="Module")
public class ModuleEntity implements Serializable {

    @Id
    private String moduleCode;
    private String title;
    @OneToMany
    private Collection<ScheduleItemEntity> moduleEvents;
    private Long modularCredit;
    private String description;
    @OneToMany
    private Collection<UserEntity> members;
    @OneToMany
    private Collection<LessonEntity> lessons;
    @ManyToOne
    private SemesterEntity semester;
    @OneToMany(mappedBy = "module")
    private Collection<RecurringEventEntity> recurringEvents;
    
    public ModuleEntity() {
    }

    public ModuleEntity(String moduleCode, String name, Long modularCredit, String description, SemesterEntity semester) {
        this.moduleCode = moduleCode;
        this.title = name;
        this.modularCredit = modularCredit;
        this.description = description;
        this.semester = semester;
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
        this.members = members;
    }

    public Collection<RecurringEventEntity> getRecurringEvents() {
        return recurringEvents;
    }

    public void setRecurringEvents(Collection<RecurringEventEntity> recurringEvents) {
        this.recurringEvents = recurringEvents;
    }

    public Collection<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(Collection<LessonEntity> lessons) {
        this.lessons = lessons;
    }
    
}
