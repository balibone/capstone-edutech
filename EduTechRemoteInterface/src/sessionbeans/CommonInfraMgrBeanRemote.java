/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

/**
 *
 * @author Derian
 */
@Remote
public interface CommonInfraMgrBeanRemote {
    public boolean createContact(String contactSalutation, String contactFirstName, String contactLastName, String contactEmail, 
            String contactPhone, String contactType, String contactBillingAttn, String contactBillingAddress, String contactBillingCity, 
            String contactBillingState, String contactBillingZipCode, String contactBillingCountry, String contactBillingFax, 
            String contactBillingPhone, String contactShippingAttn, String contactShippingAddress, String contactShippingCity, 
            String contactShippingState, String contactShippingZipCode, String contactShippingCountry, String contactShippingFax, 
            String contactShippingPhone, String contactUsername, String contactPassword, String suppCompanyName, String suppBillAccNo, 
            String contactNotes);
    public List<Vector> viewContactList();
    public Vector getContactInfo(String contactIdentifier);
    public boolean deactivateMultipleContact(String[] contactEmailListArr);
    public boolean deactivateAContact(String hiddenContactEmail);
    public boolean activateAContact(String hiddenContactEmail);
    
    public boolean createEmployee(String empSalutation, String empFirstName, String empLastName, String empEmail, 
            String empPhone, String empUniqueIdentifier, String empDateOfBirth, String empGender, String empRace, 
            String empNationality, String empResidentAddress, String empResidentCity, String empResidentState, 
            String empResidentZipCode, String empResidentCountry, String empJobDepartment, String empJobDesignation, 
            String empUsername, String empPassword, String empNotes);
    public List<Vector> viewEmployeeList();
    public Vector getEmployeeInfo(String employeeIdentifier);
    public boolean deactivateMultipleEmployee(String[] empEmailListArr);
    public boolean deactivateAnEmployee(String hiddenEmpEmail);
    public boolean activateAnEmployee(String hiddenEmpEmail);   
    public boolean empLogin(String empNRIC, String empPassword);
}
