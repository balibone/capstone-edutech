/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.module;

import edutechentities.common.AttachmentEntity;
import edutechentities.common.RecurringEventEntity;
import edutechentities.common.ScheduleItemEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Derian
 */
@Entity(name="Lesson")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LessonEntity extends ScheduleItemEntity implements Serializable {

    @XmlElement
    @XmlInverseReference(mappedBy = "lesson")
    private SessionEntity session;
    @XmlTransient
    @ManyToOne
    private RecurringEventEntity recurringEvent;
    @OneToMany
    private Collection<AttachmentEntity> resources; 

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        if (!(object instanceof LessonEntity)) {
            return false;
        }
        LessonEntity other = (LessonEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.module.LessonEntity[ id=" + id + " ]";
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public RecurringEventEntity getRecurringEvent() {
        return recurringEvent;
    }

    public void setRecurringEvent(RecurringEventEntity recurringEvent) {
        this.recurringEvent = recurringEvent;
    }

    public Collection<AttachmentEntity> getResources() {
        return resources;
    }

    public void setResources(Collection<AttachmentEntity> resources) {
        this.resources = resources;
    }
    
}
