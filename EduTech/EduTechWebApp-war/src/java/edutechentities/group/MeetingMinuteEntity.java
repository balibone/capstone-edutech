/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.group;

import commoninfraentities.UserEntity;
import edutechentities.common.AttachmentEntity;
import edutechentities.common.ScheduleItemEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Derian
 */
@Entity(name = "MeetingMinute")
@XmlRootElement
public class MeetingMinuteEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @XmlElement
    @XmlInverseReference(mappedBy = "meetingMinute")
    @OneToOne(mappedBy = "meetingMinute") 
    private ScheduleItemEntity meeting;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @OneToMany
    private Collection<UserEntity> attendees;
    @OneToMany
    private Collection<MMAgendaEntity> agendas;
    private LocalDateTime createdAt;
    @OneToMany
    private Collection<AttachmentEntity> attachments; 

    public MeetingMinuteEntity() {
        this.meeting = null;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
        this.attendees = new ArrayList<>();
        this.agendas = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.attachments = new ArrayList<>();
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeetingMinuteEntity)) {
            return false;
        }
        MeetingMinuteEntity other = (MeetingMinuteEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.group.MeetingMinuteEntity[ id=" + id + " ]";
    }

    public ScheduleItemEntity getMeeting() {
        return meeting;
    }

    public void setMeeting(ScheduleItemEntity meeting) {
        this.meeting = meeting;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Collection<UserEntity> getAttendees() {
        return attendees;
    }

    public void setAttendees(Collection<UserEntity> attendees) {
        this.attendees = attendees;
    }

    public Collection<MMAgendaEntity> getAgendas() {
        return agendas;
    }

    public void setAgendas(Collection<MMAgendaEntity> agendas) {
        this.agendas = agendas;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Collection<AttachmentEntity> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<AttachmentEntity> attachments) {
        this.attachments = attachments;
    }
    
}
