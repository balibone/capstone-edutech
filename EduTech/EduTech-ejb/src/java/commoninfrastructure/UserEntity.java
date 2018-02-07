package commoninfrastructure;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "SystemUser")
public class UserEntity implements Serializable {
    @Id
    private String userEmail;
    private String userSalutation;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userType;
    private Boolean userActiveStatus;
    private String imgFileName;
    
    @Temporal(TemporalType.DATE)
    private Date userCreationDate;
    
    @PrePersist
    public void creationDate() { 
        this.userCreationDate = new Date(); }
    
    /* DEFAULT CONSTRUCTOR */
    public UserEntity() { userActiveStatus = true; }

    public UserEntity(String userEmail, String userSalutation, String userFirstName, String userLastName, String userPassword, String userType, String imgFileName) {
        this.userEmail = userEmail;
        this.userSalutation = userSalutation;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.userType = userType;
        this.imgFileName = imgFileName;
        this.userActiveStatus = true;
        this.userCreationDate = new Date();
    }
    
    /* GETTER METHODS */
    public String getUserEmail() { return userEmail; }
    public String getUserPassword() { return userPassword; }
    public String getUserType() { return userType; }
    public Boolean getUserActiveStatus() { return userActiveStatus; }
    public Date getUserCreationDate() { return userCreationDate; }
    public String getImgFileName() { return imgFileName; }
    public String getUserSalutation() {return userSalutation;}
    public String getUserLastName() {return userLastName;}
    public String getUserFirstName() {
        return userFirstName;
    }

    /* SETTER METHODS */
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setUserActiveStatus(Boolean userActiveStatus) { this.userActiveStatus = userActiveStatus; }
    public void setUserCreationDate(Date userCreationDate) { this.userCreationDate = userCreationDate; }
    public void setImgFileName(String imgFileName) {this.imgFileName = imgFileName;}
    public void setUserSalutation(String userSalutation) {this.userSalutation= userSalutation;}
    public void setUserLastName(String userLastName) {this.userLastName= userLastName;}
    public void setUserFirstName(String userFirstName) {this.userFirstName= userFirstName;}

}