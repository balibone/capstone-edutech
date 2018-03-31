package unifyentities.shouts;

import unifyentities.common.*;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import unifyentities.shouts.ShoutsEntity;
import commoninfrastructureentities.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity(name = "ShoutsComments")
public class ShoutsCommentsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentID;
    private String commentContent;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    
    @ManyToOne
    private ShoutsEntity shoutsEntity;
    @ManyToOne
    private UserEntity userEntity;
    
    
    
    @PrePersist
    public void creationDate() { this.commentDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean addNewComment(String commentContent) {
        this.commentContent = commentContent;
        return true;
    }
    
    /* GETTER METHODS */
    public Long getCommentID() { return commentID; }
    public String getCommentContent() { return commentContent; }
    public Date getCommentDate() { return commentDate; }
    
    public ShoutsEntity getShoutsEntity() { return shoutsEntity; }
    public UserEntity getUserEntity() { return userEntity; }
    
    /* SETTER METHODS */
    public void setCommentID(Long commentID) { this.commentID = commentID; }
    public void setCommentContent(String commentContent) { this.commentContent = commentContent; }
    public void setCommentDate(Date commentDate) { this.commentDate = commentDate; }
    
    public void setShoutsEntity(ShoutsEntity shoutsEntity) { this.shoutsEntity = shoutsEntity; }
    public void setUserEntity(UserEntity userEntity) { this.userEntity = userEntity; }
}