/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechentities.common;

import commoninfraentities.UserEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tengkuhafidz
 */
@Entity(name = "Post")
@XmlRootElement
public class PostEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String pageId;
    private String message;
    @OneToMany(cascade=CascadeType.PERSIST)
    private Collection<PostEntity> replies;
    private Long replyTo = null;
    private boolean isPinned = false;
    @OneToMany
    private Collection<UserEntity> likedBy;
    @ManyToOne
    private UserEntity createdBy;
    private String createdAt;
    private String modifiedAt;

    public PostEntity() {
        this.pageId = "";
        this.message = "";
        this.replies = new ArrayList<>();
        this.likedBy = new ArrayList<>();
        this.createdBy = new UserEntity();
        this.createdAt = "";
        this.modifiedAt = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<PostEntity> getReplies() {
        return replies;
    }

    public void setReplies(Collection<PostEntity> replies) {
        this.replies = replies;
    }
     
    public Long getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Long replyTo) {
        this.replyTo = replyTo;
    }

    public boolean getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(boolean isPinned) {
        this.isPinned = isPinned;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    
}
