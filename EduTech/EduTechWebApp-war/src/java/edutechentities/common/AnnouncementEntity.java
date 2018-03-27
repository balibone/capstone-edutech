/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.common;

import commoninfraentities.UserEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name="Announcement")
@XmlRootElement
public class AnnouncementEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private Collection<UserEntity> assignedTo;
    private String title; 
    private String message; 
    @OneToOne
    private UserEntity createdBy;
    private LocalDateTime createdAt;
    @OneToMany
    private Collection<UserEntity> seenBy;
    private String path;

    public AnnouncementEntity() {
        this.assignedTo = new ArrayList<>();
        this.title = "";
        this.message = "";
        this.createdBy = new UserEntity();
        this.createdAt = LocalDateTime.now();
        this.seenBy = new ArrayList<>();
        this.path = "";
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
        if (!(object instanceof AnnouncementEntity)) {
            return false;
        }
        AnnouncementEntity other = (AnnouncementEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.common.AnnouncementEntity[ id=" + id + " ]";
    }

    public Collection<UserEntity> getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Collection<UserEntity> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Collection<UserEntity> getSeenBy() {
        return seenBy;
    }

    public void setSeenBy(Collection<UserEntity> seenBy) {
        this.seenBy = seenBy;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
