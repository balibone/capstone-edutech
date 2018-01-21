package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Contact")
public class ContactEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contactID;
    private String contactSalutation;
    private String contactFirstName;
    private String contactLastName;
    private String contactEmail;
    private String contactPhone;
    private String contactType;
    
    private String contactBillingAttn;
    private String contactBillingAddress;
    private String contactBillingCity;
    private String contactBillingState;
    private String contactBillingZipCode;
    private String contactBillingCountry;
    private String contactBillingFax;
    private String contactBillingPhone;
    
    private String contactShippingAttn;
    private String contactShippingAddress;
    private String contactShippingCity;
    private String contactShippingState;
    private String contactShippingZipCode;
    private String contactShippingCountry;
    private String contactShippingFax;
    private String contactShippingPhone;
    
    private String contactUsername;
    private String contactPassword;
    private String suppCompanyName;
    private String suppBillAccNo;
    private String contactNotes;
    private Boolean contactActiveStatus;
    
    @Temporal(TemporalType.DATE)
    private Date contactCreationDate;
    
    @PrePersist
    public void creationDate() {
        this.contactCreationDate = new Date();
    }
    
    /* DEFAULT CONSTRUCTOR */
    public ContactEntity() { contactActiveStatus = true; }
    
    /* MISCELLANEOUS METHODS */
    public void createContact(String contactSalutation, String contactFirstName, String contactLastName, String contactEmail, 
            String contactPhone, String contactType, String contactBillingAttn, String contactBillingAddress, String contactBillingCity, 
            String contactBillingState, String contactBillingZipCode, String contactBillingCountry, String contactBillingFax, 
            String contactBillingPhone, String contactShippingAttn, String contactShippingAddress, String contactShippingCity, 
            String contactShippingState, String contactShippingZipCode, String contactShippingCountry, String contactShippingFax, 
            String contactShippingPhone, String contactUsername, String contactHashedPassword, String suppCompanyName, String suppBillAccNo, 
            String contactNotes) {
        this.contactSalutation = contactSalutation;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.contactType = contactType;
        this.contactBillingAttn = contactBillingAttn;
        this.contactBillingAddress = contactBillingAddress;
        this.contactBillingCity = contactBillingCity;
        this.contactBillingState = contactBillingState;
        this.contactBillingZipCode = contactBillingZipCode;
        this.contactBillingCountry = contactBillingCountry;
        this.contactBillingFax = contactBillingFax;
        this.contactBillingPhone = contactBillingPhone;
        this.contactShippingAttn = contactShippingAttn;
        this.contactShippingAddress = contactShippingAddress;
        this.contactShippingCity = contactShippingCity;
        this.contactShippingState = contactShippingState;
        this.contactShippingZipCode = contactShippingZipCode;
        this.contactShippingCountry = contactShippingCountry;
        this.contactShippingFax = contactShippingFax;
        this.contactShippingPhone = contactShippingPhone;
        this.contactUsername = contactUsername;
        this.contactPassword = contactHashedPassword;
        this.suppCompanyName = suppCompanyName;
        this.suppBillAccNo = suppBillAccNo;
        this.contactNotes = contactNotes;
    }
    
    /* GETTER METHODS */
    public Long getContactID() { return contactID; }
    public String getContactSalutation() { return contactSalutation; }
    public String getContactFirstName() { return contactFirstName; }
    public String getContactLastName() { return contactLastName; }
    public String getContactEmail() { return contactEmail; }
    public String getContactPhone() { return contactPhone; }
    public String getContactType() { return contactType; }
    public String getContactBillingAttn() { return contactBillingAttn;}
    public String getContactBillingAddress() { return contactBillingAddress; }
    public String getContactBillingCity() { return contactBillingCity; }
    public String getContactBillingState() { return contactBillingState; }
    public String getContactBillingZipCode() { return contactBillingZipCode; }
    public String getContactBillingCountry() { return contactBillingCountry; }
    public String getContactBillingFax() { return contactBillingFax; }
    public String getContactBillingPhone() { return contactBillingPhone; }
    public String getContactShippingAttn() { return contactShippingAttn; }
    public String getContactShippingAddress() { return contactShippingAddress; }
    public String getContactShippingCity() { return contactShippingCity; }
    public String getContactShippingState() { return contactShippingState; }
    public String getContactShippingZipCode() { return contactShippingZipCode; }
    public String getContactShippingCountry() { return contactShippingCountry; }
    public String getContactShippingFax() { return contactShippingFax; }
    public String getContactShippingPhone() { return contactShippingPhone; }
    public String getContactUsername() { return contactUsername; }
    public String getContactPassword() { return contactPassword; }
    public String getSuppCompanyName() { return suppCompanyName; }
    public String getSuppBillAccNo() { return suppBillAccNo; }
    public String getContactNotes() { return contactNotes; }
    public Boolean getContactActiveStatus() { return contactActiveStatus; }
    public Date getContactCreationDate() { return contactCreationDate; }
    
    /* SETTER METHODS */
    public void setContactID(Long contactID) { this.contactID = contactID; }
    public void setContactSalutation(String contactSalutation) { this.contactSalutation = contactSalutation; }
    public void setContactFirstName(String contactFirstName) { this.contactFirstName = contactFirstName; }
    public void setContactLastName(String contactLastName) { this.contactLastName = contactLastName; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public void setContactType(String contactType) { this.contactType = contactType; }
    public void setContactBillingAttn(String contactBillingAttn) { this.contactBillingAttn = contactBillingAttn; }
    public void setContactBillingAddress(String contactBillingAddress) { this.contactBillingAddress = contactBillingAddress; }
    public void setContactBillingCity(String contactBillingCity) { this.contactBillingCity = contactBillingCity; }
    public void setContactBillingState(String contactBillingState) { this.contactBillingState = contactBillingState; }
    public void setContactBillingZipCode(String contactBillingZipCode) { this.contactBillingZipCode = contactBillingZipCode; }
    public void setContactBillingCountry(String contactBillingCountry) { this.contactBillingCountry = contactBillingCountry; }
    public void setContactBillingFax(String contactBillingFax) { this.contactBillingFax = contactBillingFax; }
    public void setContactBillingPhone(String contactBillingPhone) { this.contactBillingPhone = contactBillingPhone; }
    public void setContactShippingAttn(String contactShippingAttn) { this.contactShippingAttn = contactShippingAttn; }
    public void setContactShippingAddress(String contactShippingAddress) { this.contactShippingAddress = contactShippingAddress; }
    public void setContactShippingCity(String contactShippingCity) { this.contactShippingCity = contactShippingCity; }
    public void setContactShippingState(String contactShippingState) { this.contactShippingState = contactShippingState;}
    public void setContactShippingZipCode(String contactShippingZipCode) { this.contactShippingZipCode = contactShippingZipCode; }
    public void setContactShippingCountry(String contactShippingCountry) { this.contactShippingCountry = contactShippingCountry; }
    public void setContactShippingFax(String contactShippingFax) { this.contactShippingFax = contactShippingFax; }
    public void setContactShippingPhone(String contactShippingPhone) { this.contactShippingPhone = contactShippingPhone; }
    public void setContactUsername(String contactUsername) { this.contactUsername = contactUsername; }
    public void setContactPassword(String contactPassword) { this.contactPassword = contactPassword; }
    public void setSuppCompanyName(String suppCompanyName) { this.suppCompanyName = suppCompanyName; }
    public void setSuppBillAccNo(String suppBillAccNo) { this.suppBillAccNo = suppBillAccNo; }
    public void setContactNotes(String contactNotes) { this.contactNotes = contactNotes; }
    public void setContactActiveStatus(Boolean contactActiveStatus) { this.contactActiveStatus = contactActiveStatus; }
    public void setContactCreationDate(Date contactCreationDate) { this.contactCreationDate = contactCreationDate; }
}