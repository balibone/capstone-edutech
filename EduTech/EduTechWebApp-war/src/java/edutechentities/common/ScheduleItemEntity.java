/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.common;

import commoninfraentities.UserEntity;
import edutechentities.group.MeetingMinuteEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Derian
 */
@Entity(name = "ScheduleItem")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScheduleItemEntity implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Lob
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String location;
    @OneToMany
    private Collection<UserEntity> assignedTo;
    private String itemType;
    private String moduleCode;
    private int groupId;
    @ManyToOne
    private UserEntity createdBy;
    private LocalDateTime createdAt;
    @XmlElement
    @OneToOne
    private MeetingMinuteEntity meetingMinute;
    
    public ScheduleItemEntity() {
        this.title = "";
        this.description = "";
        this.startDate = LocalDateTime.now();
        this.endDate = LocalDateTime.now();
        this.location = "";
        this.assignedTo = new ArrayList<>();
        this.itemType = "";
        this.moduleCode = "";
        this.groupId = 0;
        this.createdBy = null;
        this.createdAt = LocalDateTime.now();
        this.meetingMinute = null;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduleItemEntity)) {
            return false;
        }
        ScheduleItemEntity other = (ScheduleItemEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.ScheduleItemEntity[ id=" + getId() + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Collection<UserEntity> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Collection<UserEntity> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MeetingMinuteEntity getMeetingMinute() {
        return meetingMinute;
    }

    public void setMeetingMinute(MeetingMinuteEntity meetingMinute) {
        this.meetingMinute = meetingMinute;
    }
    
}
