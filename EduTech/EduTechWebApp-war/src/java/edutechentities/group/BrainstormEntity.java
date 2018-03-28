/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.group;

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

/**
 *
 * @author Derian
 */
@Entity(name="Brainstorm")
@XmlRootElement
public class BrainstormEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @OneToOne
    private GroupEntity group;
    @OneToMany
    private Collection<IdeaEntity> ideas;
    private int phaseCode;
    @OneToOne
    private UserEntity createdBy;
    private LocalDateTime createdAt;
    @OneToOne
    private UserEntity modifiedBy;
    private LocalDateTime modifiedAt;

    public BrainstormEntity() {
        this.title = "";
        this.description = "";
        this.group = new GroupEntity();
        this.ideas = new ArrayList<>();
        this.phaseCode = 0;
        this.createdBy = new UserEntity();
        this.createdAt = LocalDateTime.now();
        this.modifiedBy = new UserEntity();
        this.modifiedAt = LocalDateTime.now();
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
        if (!(object instanceof BrainstormEntity)) {
            return false;
        }
        BrainstormEntity other = (BrainstormEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.group.BrainstormEntity[ id=" + id + " ]";
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

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public Collection<IdeaEntity> getIdeas() {
        return ideas;
    }

    public void setIdeas(Collection<IdeaEntity> ideas) {
        this.ideas = ideas;
    }

    public int getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(int phaseCode) {
        this.phaseCode = phaseCode;
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

    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    
}
