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

import commoninfrastructureentities.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity(name = "Shouts")
public class ShoutsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shoutID;
    //status is set to 'Active' or 'Delisted'
    private String shoutStatus;
    private String shoutContent;
    
    //for updating when event has been updated? - see if needed
    @Temporal(TemporalType.TIMESTAMP)
    private Date shoutEditedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date shoutDate;
    
    private String shoutLat;
    private String shoutLong;
    
    @ManyToOne
    private UserEntity shoutUser;
    
    @ManyToOne
    private CategoryEntity categoryEntity;
    
    //link to likes entity
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "shoutsEntity")
    private Collection<ShoutsLikesEntity> shoutsLikesSet = new ArrayList<ShoutsLikesEntity>();
    //link to bookmarked entity
    
    //link to comments entity?
     @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "shoutsEntity")
    private Collection<ShoutsCommentsEntity> shoutsCommentsSet = new ArrayList<ShoutsCommentsEntity>();
     
    //link to bookmarks entity?
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "shoutsEntity")
    private Collection<ShoutsBookmarksEntity> shoutsBookmarksSet = new ArrayList<ShoutsBookmarksEntity>(); 
     
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "shoutsEntity")
    private Collection<ShoutsReportEntity> shoutsReportSet = new ArrayList<ShoutsReportEntity>(); 
    
    //@OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "shoutsEntity")
    //private Collection<ShoutsCommentsReportEntity> shoutsCommentsReportSet = new ArrayList<ShoutsCommentsReportEntity>(); 
        
    @PrePersist
    public void creationDate() { this.shoutDate = new Date(); }
    
    /* MISCELLANEOUS METHODS */
    public boolean createShout(String shoutContent) {
        this.shoutContent = shoutContent;
        this.shoutStatus = "Active";
        return true;
    }
        
    /* GETTER METHODS */
    public Long getShoutID() { return shoutID; }
    public String getShoutStatus() { return shoutStatus; }
    public String getShoutContent() { return shoutContent; }
    public String getShoutContent(Long shoutID) { return shoutContent; }
    public Date getShoutDate() { return shoutDate; }
    public Date getShoutDate(Long shoutID) { return shoutDate; }

    public Date getShoutEditedDate() { return shoutEditedDate; }
    public String getShoutLat() { return shoutLat; }
    public String getShoutLong() { return shoutLong; }
    
    public UserEntity getUserEntity() { return shoutUser; }
    public CategoryEntity getCategoryEntity() { return categoryEntity; }
    
    public Collection<ShoutsLikesEntity> getShoutsLikesSet() { return shoutsLikesSet; }
    public Collection<ShoutsCommentsEntity> getShoutsCommentsSet() { return shoutsCommentsSet; }
    public Collection<ShoutsBookmarksEntity> getShoutsBookmarksSet() { return shoutsBookmarksSet; }
    public Collection<ShoutsReportEntity> getShoutsReportSet() { return shoutsReportSet; }
    //public Collection<ShoutsCommentsReportEntity> getShoutsCommentsReportSet() { return shoutsCommentsReportSet; }
    
    /* SETTER METHODS */
    public void setShoutID(Long shoutID) { this.shoutID = shoutID; }
    public void setShoutStatus(String shoutStatus) { this.shoutStatus = shoutStatus; }
    public void setShoutContent(String shoutContent) { this.shoutContent = shoutContent; }
    public void setShoutDate(Date shoutDate) { this.shoutDate = shoutDate; }
    
    public void setShoutEditedDate() { this.shoutEditedDate = new Date(); }
    public void setShoutLat(String shoutLat) {this.shoutLat = shoutLat; }
    public void setShoutLong(String shoutLong) {this.shoutLong = shoutLong; }
    
    public void setUserEntity(UserEntity shoutUser) { this.shoutUser = shoutUser; }
    public void setCategoryEntity(CategoryEntity categoryEntity) { this.categoryEntity = categoryEntity; }
}