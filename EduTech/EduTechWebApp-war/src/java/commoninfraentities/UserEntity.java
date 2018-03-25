/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commoninfraentities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Derian
 */
@Entity(name="SystemUser")
@XmlRootElement
public class UserEntity implements Serializable {

    @Id
    private String username;
    private String userSalutation;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userType;
    //active by default
    private Boolean userActiveStatus = true;
    private String imgFileName = "defaultPhoto.jpg";
    private String email;
    private String contactNum;
    //to store latest password reset token
    private String resetToken;
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreationDate;
    
    @PrePersist
    public void creationDate() { 
        this.userCreationDate = new Date(); }
    
    /* DEFAULT CONSTRUCTOR */
    public UserEntity() { userActiveStatus = true; }

    public UserEntity(String username, String userSalutation, String userFirstName, String userLastName, String userPassword, String userType, String imgFileName, String email, String contactNum) {
        this.username = username;
        this.userSalutation = userSalutation;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.email = email;
        this.contactNum = contactNum;
        this.userType = userType;
        this.imgFileName = imgFileName;
        this.userActiveStatus = true;
        this.userCreationDate = new Date();
    }
    //for front end registration page
    public UserEntity(String salutation, String firstName, String lastName, String username, String password, String email, String contactNum) {
        this.username = username;
        this.userSalutation = salutation;
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.userPassword = password;
        this.email = email;
        this.contactNum = contactNum;
        this.userType = "student";
        this.imgFileName = "defaultPhoto.jpg";
        this.userActiveStatus = true;
        this.userCreationDate = new Date();
    }
    
    public String getUsername() { return username; }
    public String getUserPassword() { return userPassword; }
    public String getUserType() { return userType; }
    public Boolean getUserActiveStatus() { return userActiveStatus; }
    public Date getUserCreationDate() { return userCreationDate; }
    public String getImgFileName() { return imgFileName; }
    public String getUserSalutation() { return userSalutation; }
    public String getUserLastName() { return userLastName;}
    public String getUserFirstName() { return userFirstName; }
   
    
    public void setUsername(String username) { this.username = username; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setUserActiveStatus(Boolean userActiveStatus) { this.userActiveStatus = userActiveStatus; }
    public void setUserCreationDate(Date userCreationDate) { this.userCreationDate = userCreationDate; }
    public void setImgFileName(String imgFileName) {this.imgFileName = imgFileName;}
    public void setUserSalutation(String userSalutation) {this.userSalutation= userSalutation;}
    public void setUserLastName(String userLastName) {this.userLastName= userLastName;}
    public void setUserFirstName(String userFirstName) {this.userFirstName= userFirstName;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
