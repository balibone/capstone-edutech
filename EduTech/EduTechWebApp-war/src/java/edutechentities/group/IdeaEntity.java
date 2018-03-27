/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.group;

import commoninfraentities.UserEntity;
import java.io.Serializable;
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
@Entity(name="Idea")
@XmlRootElement
public class IdeaEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @OneToOne
    private UserEntity createdBy;
    @OneToMany
    private Collection<UserEntity> votedBy;
    @OneToOne
    private BrainstormEntity brainstormSession;

    public IdeaEntity() {
        this.title = "";
        this.createdBy = new UserEntity();
        this.votedBy = new ArrayList<>();
        this.brainstormSession = new BrainstormEntity();
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
        if (!(object instanceof IdeaEntity)) {
            return false;
        }
        IdeaEntity other = (IdeaEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edutechentities.group.BrainstormIdeaEntity[ id=" + id + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public Collection<UserEntity> getVotedBy() {
        return votedBy;
    }

    public void setVotedBy(Collection<UserEntity> votedBy) {
        this.votedBy = votedBy;
    }

    public BrainstormEntity getBrainstormSession() {
        return brainstormSession;
    }

    public void setBrainstormSession(BrainstormEntity brainstormSession) {
        this.brainstormSession = brainstormSession;
    }
    
}
