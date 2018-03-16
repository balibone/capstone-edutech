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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Derian
 */
@Entity
public class QuestionEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    @OneToMany
    private Collection<AnswerEntity> answers;
    @OneToMany
    private Collection<UserEntity> likedBy;
    @ManyToOne
    private SessionEntity classSession;
    @OneToOne
    private UserEntity createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

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
        if (!(object instanceof QuestionEntity)) {
            return false;
        }
        QuestionEntity other = (QuestionEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.module.QuestionEntity[ id=" + id + " ]";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Collection<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<AnswerEntity> answers) {
        this.answers = answers;
    }

    public Collection<UserEntity> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Collection<UserEntity> likedBy) {
        this.likedBy = likedBy;
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

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public SessionEntity getClassSession() {
        return classSession;
    }

    public void setClassSession(SessionEntity classSession) {
        this.classSession = classSession;
    }
    
}
