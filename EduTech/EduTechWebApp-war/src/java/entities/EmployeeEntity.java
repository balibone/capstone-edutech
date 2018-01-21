package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Employee")
public class EmployeeEntity implements Serializable {
    private String empSalutation;
    private String empFirstName;
    private String empLastName;
    private String empEmail;
    private String empPhone;
    
    @Id
    private String empUniqueIdentifier;
    private String empDateOfBirth;
    private String empGender;
    private String empRace;
    private String empNationality;
    
    private String empResidentAddress;
    private String empResidentCity;
    private String empResidentState;
    private String empResidentZipCode;
    private String empResidentCountry;
    
    private String empJobDepartment;
    private String empJobDesignation;
    private String empUsername;
    private String empPassword;
    private String empNotes;
    private Boolean empActiveStatus;
    
    @Temporal(TemporalType.DATE)
    private Date empCreationDate;
    
    @PrePersist
    public void creationDate() {
        this.empCreationDate = new Date();
    }
    
    /* DEFAULT CONSTRUCTOR */
    public EmployeeEntity() { empActiveStatus = true; }
    
    /* MISCELLANEOUS METHODS */
    public void createEmployee(String empSalutation, String empFirstName, String empLastName, String empEmail, 
            String empPhone, String empUniqueIdentifier, String empDateOfBirth, String empGender, String empRace, 
            String empNationality, String empResidentAddress, String empResidentCity, String empResidentState, 
            String empResidentZipCode, String empResidentCountry, String empJobDepartment, String empJobDesignation, 
            String empUsername, String empHashedPassword, String empNotes) {
        this.empSalutation = empSalutation;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empUniqueIdentifier = empUniqueIdentifier;
        this.empDateOfBirth = empDateOfBirth;
        this.empGender = empGender;
        this.empRace = empRace;
        this.empNationality = empNationality;
        this.empResidentAddress = empResidentAddress;
        this.empResidentCity = empResidentCity;
        this.empResidentState = empResidentState;
        this.empResidentZipCode = empResidentZipCode;
        this.empResidentCountry = empResidentCountry;
        this.empJobDepartment = empJobDepartment;
        this.empJobDesignation = empJobDesignation;
        this.empUsername = empUsername;
        this.empPassword = empHashedPassword;
        this.empNotes = empNotes;
    }
    
    /* GETTER METHODS */
    public String getEmpSalutation() { return empSalutation; }
    public String getEmpFirstName() { return empFirstName; }
    public String getEmpLastName() { return empLastName; }
    public String getEmpEmail() { return empEmail; }
    public String getEmpPhone() { return empPhone; }
    public String getEmpUniqueIdentifier() { return empUniqueIdentifier; }
    public String getEmpDateOfBirth() { return empDateOfBirth; }
    public String getEmpGender() { return empGender; }
    public String getEmpRace() { return empRace; }
    public String getEmpNationality() { return empNationality; }
    public String getEmpResidentAddress() { return empResidentAddress; }
    public String getEmpResidentCity() { return empResidentCity; }
    public String getEmpResidentState() { return empResidentState; }
    public String getEmpResidentZipCode() { return empResidentZipCode; }
    public String getEmpResidentCountry() { return empResidentCountry; }
    public String getEmpJobDepartment() { return empJobDepartment; }
    public String getEmpJobDesignation() { return empJobDesignation; }
    public String getEmpUsername() { return empUsername; }
    public String getEmpPassword() { return empPassword; }
    public String getEmpNotes() { return empNotes; }
    public Boolean getEmpActiveStatus() { return empActiveStatus; }
    public Date getEmpCreationDate() { return empCreationDate; }
    
    /* SETTER METHODS */
    public void setEmpSalutation(String empSalutation) { this.empSalutation = empSalutation; }
    public void setEmpFirstName(String empFirstName) { this.empFirstName = empFirstName; }
    public void setEmpLastName(String empLastName) { this.empLastName = empLastName; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }
    public void setEmpPhone(String empPhone) { this.empPhone = empPhone; }
    public void setEmpUniqueIdentifier(String empUniqueIdentifier) { this.empUniqueIdentifier = empUniqueIdentifier; }
    public void setEmpDateOfBirth(String empDateOfBirth) { this.empDateOfBirth = empDateOfBirth; }
    public void setEmpGender(String empGender) { this.empGender = empGender; }
    public void setEmpRace(String empRace) { this.empUniqueIdentifier = empRace; }
    public void setEmpNationality(String empNationality) { this.empNationality = empNationality; }
    public void setEmpResidentAddress(String empResidentAddress) { this.empResidentAddress = empResidentAddress; }
    public void setEmpResidentCity(String empResidentCity) { this.empResidentCity = empResidentCity; }
    public void setEmpResidentState(String empResidentState) { this.empResidentState = empResidentState; }
    public void setEmpResidentZipCode(String empResidentZipCode) { this.empResidentZipCode = empResidentZipCode; }
    public void setEmpResidentCountry(String empResidentCountry) { this.empResidentCountry = empResidentCountry; }
    public void setEmpJobDepartment(String empJobDepartment) { this.empJobDepartment = empJobDepartment; }
    public void setEmpJobDesignation(String empJobDesignation) { this.empJobDesignation = empJobDesignation; }
    public void setEmpUsername(String empUsername) { this.empUsername = empUsername; }
    public void setEmpPassword(String empPassword) { this.empPassword = empPassword; }
    public void setEmpNotes(String empNotes) { this.empNotes = empNotes; }
    public void setEmpActiveStatus(Boolean empActiveStatus) { this.empActiveStatus = empActiveStatus; }
    public void setEmpCreationDate(Date empCreationDate) { this.empCreationDate = empCreationDate; }
}