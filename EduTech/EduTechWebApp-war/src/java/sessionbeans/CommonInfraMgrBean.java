package sessionbeans;

import entities.ContactEntity;
import entities.EmployeeEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommonInfraMgrBean implements CommonInfraMgrBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    private EmployeeEntity eEntity;
    private ContactEntity cEntity;

    @Override
    public boolean createContact(String contactSalutation, String contactFirstName, String contactLastName, String contactEmail, 
            String contactPhone, String contactType, String contactBillingAttn, String contactBillingAddress, String contactBillingCity, 
            String contactBillingState, String contactBillingZipCode, String contactBillingCountry, String contactBillingFax, 
            String contactBillingPhone, String contactShippingAttn, String contactShippingAddress, String contactShippingCity, 
            String contactShippingState, String contactShippingZipCode, String contactShippingCountry, String contactShippingFax, 
            String contactShippingPhone, String contactUsername, String contactPassword, String suppCompanyName, String suppBillAccNo, 
            String contactNotes) {
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(contactPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }
        
        cEntity = new ContactEntity();
        cEntity.createContact(contactSalutation, contactFirstName, contactLastName, contactEmail, contactPhone, contactType, 
                contactBillingAttn, contactBillingAddress, contactBillingCity, contactBillingState, contactBillingZipCode, 
                contactBillingCountry, contactBillingFax, contactBillingPhone, contactShippingAttn, contactShippingAddress, 
                contactShippingCity, contactShippingState, contactShippingZipCode, contactShippingCountry, contactShippingFax, 
                contactShippingPhone, contactUsername, hashedPassword, suppCompanyName, suppBillAccNo, contactNotes);
        em.persist(cEntity);
        return true;
    }

    @Override
    public List<Vector> viewContactList(){
        Query q = em.createQuery("SELECT c FROM Contact c");
        List<Vector> contactList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            ContactEntity contactE = (ContactEntity) o;
            Vector contactVec = new Vector();
            
            contactVec.add(contactE.getContactSalutation());
            contactVec.add(contactE.getContactFirstName());
            contactVec.add(contactE.getContactLastName());
            contactVec.add(contactE.getContactEmail());
            contactVec.add(contactE.getContactPhone());
            contactVec.add(contactE.getContactType());
            contactVec.add(contactE.getSuppCompanyName());
            contactVec.add(contactE.getContactActiveStatus());
            contactList.add(contactVec);
        }
        return contactList;
    }
    
    @Override
    public Vector getContactInfo(String contactIdentifier) {
        cEntity = lookupContact(contactIdentifier);
        Vector contactInfoVec = new Vector();
        
        if (cEntity != null) {
            DateFormat df = new SimpleDateFormat("dd MMMMM yyyy");
            
            contactInfoVec.add(cEntity.getContactFirstName());
            contactInfoVec.add(cEntity.getContactLastName());
            contactInfoVec.add(cEntity.getContactEmail());
            contactInfoVec.add(cEntity.getContactActiveStatus());
            contactInfoVec.add(df.format(cEntity.getContactCreationDate()));
            
            return contactInfoVec;
        }
        return null;
    }
    
    @Override
    public boolean deactivateMultipleContact(String[] contactEmailListArr) {
        boolean contactDeletionStatus = true;
        for (String contactEmail : contactEmailListArr) {
            if (lookupContact(contactEmail) == null) {
                contactDeletionStatus = false;
            } else {
                cEntity = lookupContact(contactEmail);
                cEntity.setContactActiveStatus(false);
                em.merge(cEntity);
            }
        }
        return contactDeletionStatus;
    }
    
    @Override
    public boolean deactivateAContact(String hiddenContactEmail) {
        boolean contactDeletionStatus = true;
        if (lookupContact(hiddenContactEmail) == null) {
            contactDeletionStatus = false;
        } else {
            cEntity = lookupContact(hiddenContactEmail);
            cEntity.setContactActiveStatus(false);
            em.merge(cEntity);
        }
        return contactDeletionStatus;
    }
    
    @Override
    public boolean activateAContact(String hiddenContactEmail) {
        boolean contactDeletionStatus = true;
        if (lookupContact(hiddenContactEmail) == null) {
            contactDeletionStatus = false;
        } else {
            cEntity = lookupContact(hiddenContactEmail);
            cEntity.setContactActiveStatus(true);
            em.merge(cEntity);
        }
        return contactDeletionStatus;
    }
    
    @Override
    public boolean createEmployee(String empSalutation, String empFirstName, String empLastName, String empEmail, 
            String empPhone, String empUniqueIdentifier, String empDateOfBirth, String empGender, String empRace, 
            String empNationality, String empResidentAddress, String empResidentCity, String empResidentState, 
            String empResidentZipCode, String empResidentCountry, String empJobDepartment, String empJobDesignation, 
            String empUsername, String empPassword, String empNotes) {
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(empPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }
        
        eEntity = new EmployeeEntity();
        eEntity.createEmployee(empSalutation, empFirstName, empLastName, empEmail, empPhone, empUniqueIdentifier, empDateOfBirth, 
                empGender, empRace, empNationality, empResidentAddress, empResidentCity, empResidentState, empResidentZipCode, 
                empResidentCountry, empJobDepartment, empJobDesignation, empUsername, hashedPassword, empNotes);
        em.persist(eEntity);
        return true;
    }
    
    @Override
    public List<Vector> viewEmployeeList(){
        Query q = em.createQuery("SELECT e FROM Employee e");
        List<Vector> employeeList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            EmployeeEntity employeeE = (EmployeeEntity) o;
            Vector employeeVec = new Vector();
            
            employeeVec.add(employeeE.getEmpFirstName());
            employeeVec.add(employeeE.getEmpLastName());
            employeeVec.add(employeeE.getEmpEmail());
            employeeVec.add(employeeE.getEmpPhone());
            employeeVec.add(employeeE.getEmpJobDepartment());
            employeeVec.add(employeeE.getEmpJobDesignation());
            employeeVec.add(employeeE.getEmpActiveStatus());
            employeeList.add(employeeVec);
        }
        return employeeList;
    }
    
    @Override
    public Vector getEmployeeInfo(String employeeIdentifier) {
        eEntity = lookupEmployee(employeeIdentifier);
        Vector employeeInfoVec = new Vector();
        
        if (eEntity != null) {
            DateFormat df = new SimpleDateFormat("dd MMMMM yyyy");
            
            employeeInfoVec.add(eEntity.getEmpFirstName());
            employeeInfoVec.add(eEntity.getEmpLastName());
            employeeInfoVec.add(eEntity.getEmpEmail());
            employeeInfoVec.add(eEntity.getEmpActiveStatus());
            employeeInfoVec.add(df.format(eEntity.getEmpCreationDate()));
            
            return employeeInfoVec;
        }
        return null;
    }
    
    @Override
    public boolean deactivateMultipleEmployee(String[] empEmailListArr) {
        boolean empDeletionStatus = true;
        for (String empEmail : empEmailListArr) {
            if (lookupEmployee(empEmail) == null) {
                empDeletionStatus = false;
            } else {
                eEntity = lookupEmployee(empEmail);
                eEntity.setEmpActiveStatus(false);
                em.merge(eEntity);
            }
        }
        return empDeletionStatus;
    }
    
    @Override
    public boolean deactivateAnEmployee(String hiddenEmpEmail) {
        boolean empDeletionStatus = true;
        if (lookupEmployee(hiddenEmpEmail) == null) {
            empDeletionStatus = false;
        } else {
            eEntity = lookupEmployee(hiddenEmpEmail);
            eEntity.setEmpActiveStatus(false);
            em.merge(eEntity);
        }
        return empDeletionStatus;
    }
    
    @Override
    public boolean activateAnEmployee(String hiddenEmpEmail) {
        boolean empDeletionStatus = true;
        if (lookupEmployee(hiddenEmpEmail) == null) {
            empDeletionStatus = false;
        } else {
            eEntity = lookupEmployee(hiddenEmpEmail);
            eEntity.setEmpActiveStatus(true);
            em.merge(eEntity);
        }
        return empDeletionStatus;
    }

    @Override
    public boolean empLogin(String empUsername, String empPassword) {
        /* Must perform hashing here, not on the servlet side. Otherwise will produce different hash values */
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(empPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }

        eEntity = new EmployeeEntity();
        try{
            Query q = em.createQuery("SELECT e FROM Employee e WHERE e.empUsername = :empUsername");
            q.setParameter("empUsername", empUsername);
            eEntity = (EmployeeEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Employee cannot be found. " + enfe.getMessage());
            em.remove(eEntity);
            eEntity = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Employee does not exist. " + nre.getMessage());
            em.remove(eEntity);
            eEntity = null;
        }
        if(eEntity == null) { return false; }
        // if(eEntity.getEmpPassword().equals(hashedPassword)) { return true; }
        if(eEntity.getEmpPassword().equals(empPassword)) { return true; }
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //HELPER METHODS
    public ContactEntity lookupContact(String emailAddress){
        ContactEntity ce = new ContactEntity();
        try{
            Query q = em.createQuery("SELECT c FROM Contact c WHERE c.contactEmail = :emailAddress");
            q.setParameter("emailAddress", emailAddress);
            ce = (ContactEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Contact cannot be found. " + enfe.getMessage());
            em.remove(ce);
            ce = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Contact does not exist. " + nre.getMessage());
            em.remove(ce);
            ce = null;
        }
        return ce;
    }
    
    public EmployeeEntity lookupEmployee(String emailAddress){
        EmployeeEntity ee = new EmployeeEntity();
        try{
            Query q = em.createQuery("SELECT e FROM Employee e WHERE e.empEmail = :emailAddress");
            q.setParameter("emailAddress", emailAddress);
            ee = (EmployeeEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Employee cannot be found. " + enfe.getMessage());
            em.remove(ee);
            ee = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Employee does not exist. " + nre.getMessage());
            em.remove(ee);
            ee = null;
        }
        return ee;
    }
    
    public String encodePassword(String password) throws NoSuchAlgorithmException {
        String hashedValue = "";
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            hashedValue = sb.toString();
        }      
        return hashedValue;
    }
}
