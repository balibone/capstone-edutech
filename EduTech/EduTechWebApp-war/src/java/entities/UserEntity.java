package entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "SystemUser")
@XmlRootElement
public class UserEntity implements Serializable {
    @Id
    private String userEmail;
    private String userPassword;
    private String userType;
    private Boolean userActiveStatus;
    
    @Temporal(TemporalType.DATE)
    private Date userCreationDate;
    
    @PrePersist
    public void creationDate() { this.userCreationDate = new Date(); }
    
    /* DEFAULT CONSTRUCTOR */
    public UserEntity() { userActiveStatus = true; }
    
    /* GETTER METHODS */
    public String getUserEmail() { return userEmail; }
    public String getUserPassword() { return userPassword; }
    public String getUserType() { return userType; }
    public Boolean getUserActiveStatus() { return userActiveStatus; }
    public Date getUserCreationDate() { return userCreationDate; }
    
    /* SETTER METHODS */
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setUserActiveStatus(Boolean userActiveStatus) { this.userActiveStatus = userActiveStatus; }
    public void setUserCreationDate(Date userCreationDate) { this.userCreationDate = userCreationDate; }
}