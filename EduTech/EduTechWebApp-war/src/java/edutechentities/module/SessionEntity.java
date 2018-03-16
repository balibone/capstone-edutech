/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.module;

import commoninfraentities.UserEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Derian
 */
@Entity(name="ClassSession")
public class SessionEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @OneToMany
    private Collection<UserEntity> attendedBy;
    @OneToOne
    private LessonEntity lesson;
    @XmlElement
    @XmlInverseReference(mappedBy="classSession")
    private Collection<PollEntity> polls;
    @XmlElement
    @XmlInverseReference(mappedBy="classSession")
    private Collection<QuestionEntity> questions;
    @XmlElement
    @XmlInverseReference(mappedBy="classSession")
    private Collection<FeedbackEntity> feedbacks;
    private boolean isLive;

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
        if (!(object instanceof SessionEntity)) {
            return false;
        }
        SessionEntity other = (SessionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.module.SessionEntity[ id=" + id + " ]";
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

    public Collection<UserEntity> getAttendedBy() {
        return attendedBy;
    }

    public void setAttendedBy(Collection<UserEntity> attendedBy) {
        this.attendedBy = attendedBy;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    public Collection<PollEntity> getPolls() {
        return polls;
    }

    public void setPolls(Collection<PollEntity> polls) {
        this.polls = polls;
    }

    public Collection<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<QuestionEntity> questions) {
        this.questions = questions;
    }

    public boolean isIsLive() {
        return isLive;
    }

    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    public Collection<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Collection<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
}
