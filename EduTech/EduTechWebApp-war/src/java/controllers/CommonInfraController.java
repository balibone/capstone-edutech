package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbeans.CommonInfraMgrBean;

public class CommonInfraController extends HttpServlet {
    @EJB
    private CommonInfraMgrBean cir;
    String userNRIC = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            // FOR HANDLING THE HIDDEN FIELD
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "loginToSys":
                    String empNRIC = request.getParameter("empNRIC");
                    String empPassword = request.getParameter("empPassword");
                    if(cir.empLogin(empNRIC, empPassword)){
                        userNRIC = empNRIC;
                        request.setAttribute("employeeNRIC", userNRIC);
                        pageAction = "WarehouseDashboard";
                    }
                    else{
                        request.setAttribute("sysMessage", "Incorrect NRIC or password. Please try again.");
                        System.out.println("test3");
                        pageAction = "WarehouseLogin";
                    }   break;
                case "goToLogout":
                    userNRIC = "";
                    pageAction = "WarehouseLogin";
                    break;
                case "goToDashboard":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "WarehouseDashboard";
                    break;
                case "goToNewContact":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "NewContact";
                    break;
                case "createContact":
                    request.setAttribute("employeeNRIC", userNRIC);
                    if(createContact(request)) {
                        request.setAttribute("successMessage", "Contact has been created successfully.");
                    }
                    else {
                        request.setAttribute("errorMessage", "Contact cannot be created. Please try again later.");
                    }   request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                    pageAction = "NewContact";
                    break;
                case "goToContactList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                    pageAction = "ContactList";
                    break;
                case "goToContactDetails":
                    String contactIdentifier = request.getParameter("contactIdentifier");
                    request.setAttribute("contactInfo", getContactDetails(contactIdentifier));
                    pageAction = "ContactDetails";
                    break;
                case "deactivateMultipleContact":
                    request.setAttribute("employeeNRIC", userNRIC);
                    String[] contactEmailListArr = request.getParameterValues("contactEmailList");
                    if(cir.deactivateMultipleContact(contactEmailListArr)) {
                        request.setAttribute("successMessage", "Contact(s) have been deactivated successfully.");
                    }
                    else {
                        request.setAttribute("errorMessage", "One or more contacts cannot be deactivated. Please try again later.");
                    }   request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                    pageAction = "ContactList";
                    break;
                case "deactivateAContact":
                    {
                        request.setAttribute("employeeNRIC", userNRIC);
                        String hiddenContactEmail = request.getParameter("hiddenContactEmail");
                        if(cir.deactivateAContact(hiddenContactEmail)) {
                            request.setAttribute("successMessage", "Selected contact have been deactivated successfully.");
                        }
                        else {
                            request.setAttribute("errorMessage", "Selected contact cannot be deactivated. Please try again later.");
                        }       request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                        pageAction = "ContactList";
                        break;
                    }
                case "activateAContact":
                    {
                        request.setAttribute("employeeNRIC", userNRIC);
                        String hiddenContactEmail = request.getParameter("hiddenContactEmail");
                        if(cir.activateAContact(hiddenContactEmail)) {
                            request.setAttribute("successMessage", "Selected contact have been activated successfully.");
                        }
                        else {
                            request.setAttribute("errorMessage", "Selected contact cannot be activated. Please try again later.");
                        }       request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                        pageAction = "ContactList";
                        break;
                    }
                case "goToNewEmployee":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "NewEmployee";
                    break;
                case "createEmployee":
                    request.setAttribute("employeeNRIC", userNRIC);
                    if(createEmployee(request)) {
                        request.setAttribute("successMessage", "Employee has been created successfully.");
                    }
                    else {
                        request.setAttribute("errorMessage", "Employee cannot be created. Please try again later.");
                    }   pageAction = "NewEmployee";
                    break;
                case "goToEmployeeList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("employeeList", (ArrayList)cir.viewEmployeeList());
                    pageAction = "EmployeeList";
                    break;
                case "goToEmployeeDetails":
                    String employeeIdentifier = request.getParameter("employeeIdentifier");
                    request.setAttribute("employeeInfo", getEmployeeDetails(employeeIdentifier));
                    pageAction = "EmployeeDetails";
                    break;
                case "deactivateMultipleEmp":
                    request.setAttribute("employeeNRIC", userNRIC);
                    String[] empEmailListArr = request.getParameterValues("empEmailList");
                    if(cir.deactivateMultipleEmployee(empEmailListArr)) {
                        request.setAttribute("successMessage", "Employee record(s) have been deactivated successfully.");
                    }
                    else {
                        request.setAttribute("errorMessage", "One or more employee record(s) cannot be deactivated. Please try again later.");
                    }   request.setAttribute("employeeList", (ArrayList)cir.viewEmployeeList());
                    pageAction = "EmployeeList";
                    break;
                case "deactivateAnEmployee":
                    {
                        request.setAttribute("employeeNRIC", userNRIC);
                        String hiddenEmpEmail = request.getParameter("hiddenEmpEmail");
                        if(cir.deactivateAnEmployee(hiddenEmpEmail)) {
                            request.setAttribute("successMessage", "Selected employee have been deactivated successfully.");
                        }
                        else {
                            request.setAttribute("errorMessage", "Selected employee cannot be deactivated. Please try again later.");
                        }       request.setAttribute("employeeList", (ArrayList)cir.viewEmployeeList());
                        pageAction = "EmployeeList";
                        break;
                    }
                case "activateAnEmployee":
                    {
                        request.setAttribute("employeeNRIC", userNRIC);
                        String hiddenEmpEmail = request.getParameter("hiddenEmpEmail");
                        if(cir.activateAnEmployee(hiddenEmpEmail)) {
                            request.setAttribute("successMessage", "Selected employee have been activated successfully.");
                        }
                        else {
                            request.setAttribute("errorMessage", "Selected employee cannot be activated. Please try again later.");
                        }       request.setAttribute("employeeList", (ArrayList)cir.viewEmployeeList());
                        pageAction = "EmployeeList";
                        break;
                    }
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);       
        }
        catch(Exception ex) {
            log("Exception in CommonInfraController: processRequest()");
            ex.printStackTrace();
        }
    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "Common Infrastructure Servlet"; }
    
    //HELPER METHODS
    private boolean createContact(HttpServletRequest request) {
        boolean contactCreationStatus = false;
        String contactSalutation = request.getParameter("contactSalutation");
        String contactFirstName = request.getParameter("contactFirstName");
        String contactLastName = request.getParameter("contactLastName");
        String contactEmail = request.getParameter("contactEmail");
        String contactPhone = request.getParameter("contactPhone");
        String contactType = request.getParameter("contactType");
        
        String contactBillingAttn = request.getParameter("contactBillingAttn");
        String contactBillingAddress = request.getParameter("contactBillingAddress");
        String contactBillingCity = request.getParameter("contactBillingCity");
        String contactBillingState = request.getParameter("contactBillingState");
        String contactBillingZipCode = request.getParameter("contactBillingZipCode");
        String contactBillingCountry = request.getParameter("contactBillingCountry");
        String contactBillingFax = request.getParameter("contactBillingFax");
        String contactBillingPhone = request.getParameter("contactBillingPhone");
        
        String contactShippingAttn = request.getParameter("contactShippingAttn");
        String contactShippingAddress = request.getParameter("contactShippingAddress");
        String contactShippingCity = request.getParameter("contactShippingCity");
        String contactShippingState = request.getParameter("contactShippingState");
        String contactShippingZipCode = request.getParameter("contactShippingZipCode");
        String contactShippingCountry = request.getParameter("contactShippingCountry");
        String contactShippingFax = request.getParameter("contactShippingFax");
        String contactShippingPhone = request.getParameter("contactShippingPhone");
        
        String contactUsername = request.getParameter("contactUsername");
        String contactPassword = request.getParameter("contactPassword");
        String suppCompanyName = request.getParameter("suppCompanyName");
        String suppBillAccNo = request.getParameter("suppBillAccNo");
        String contactNotes = request.getParameter("contactNotes");
        if(suppCompanyName.equals("")) { suppCompanyName = "-"; }
        if(suppBillAccNo.equals("")) { suppBillAccNo = "-"; }
        if(contactNotes.equals("")) { contactNotes = "-"; }
        
        if(cir.createContact(contactSalutation, contactFirstName, contactLastName, contactEmail, contactPhone, contactType, 
                contactBillingAttn, contactBillingAddress, contactBillingCity, contactBillingState, contactBillingZipCode, 
                contactBillingCountry, contactBillingFax, contactBillingPhone, contactShippingAttn, contactShippingAddress, 
                contactShippingCity, contactShippingState, contactShippingZipCode, contactShippingCountry, contactShippingFax, 
                contactShippingPhone, contactUsername, contactPassword, suppCompanyName, suppBillAccNo, contactNotes)) {
            contactCreationStatus = true;
        }
        return contactCreationStatus;
    }
    
    private ArrayList<String> getContactDetails(String contactIdentifier) {
        ArrayList<String> contactDetailsArr = new ArrayList();
        Vector contactInfoVec = cir.getContactInfo(contactIdentifier);
        
        contactDetailsArr.add((String)contactInfoVec.get(0));           // Contact First Name
        contactDetailsArr.add((String)contactInfoVec.get(1));           // Contact Last Name
        contactDetailsArr.add((String)contactInfoVec.get(2));           // Contact Email
        contactDetailsArr.add(String.valueOf(contactInfoVec.get(3)));   // Contact Active Status
        contactDetailsArr.add((String)contactInfoVec.get(4));           // Contact Creation Date
        return contactDetailsArr;
    }
    
    private boolean createEmployee(HttpServletRequest request){
        boolean empCreationStatus = false;
        
        String empSalutation = request.getParameter("empSalutation");
        String empFirstName = request.getParameter("empFirstName");
        String empLastName = request.getParameter("empLastName");
        String empEmail = request.getParameter("empEmail");
        String empPhone = request.getParameter("empPhone");
        
        String empUniqueIdentifier = request.getParameter("empUniqueIdentifier");
        String empDateOfBirth = request.getParameter("empDateOfBirth");
        String empGender = request.getParameter("empGender");
        String empRace = request.getParameter("empRace");
        String empNationality = request.getParameter("empNationality");
        
        String empResidentAddress = request.getParameter("empResidentAddress");
        String empResidentCity = request.getParameter("empResidentCity");
        String empResidentState = request.getParameter("empResidentState");
        String empResidentZipCode = request.getParameter("empResidentZipCode");
        String empResidentCountry = request.getParameter("empResidentCountry");
        
        String empJobDepartment = request.getParameter("empJobDepartment");
        String empJobDesignation = request.getParameter("empJobDesignation");
        String empUsername = request.getParameter("empUsername");
        String empPassword = request.getParameter("empPassword");
        String empNotes = request.getParameter("empNotes");
        
        if(cir.createEmployee(empSalutation, empFirstName, empLastName, empEmail, empPhone, empUniqueIdentifier, 
                empDateOfBirth, empGender, empRace, empNationality, empResidentAddress, empResidentCity, empResidentState, 
                empResidentZipCode, empResidentCountry, empJobDepartment, empJobDesignation, empUsername, empPassword, empNotes)) {
            empCreationStatus = true;
        }
        return empCreationStatus;
    }
    
    private ArrayList<String> getEmployeeDetails(String employeeIdentifier) {
        ArrayList<String> employeeDetailsArr = new ArrayList();
        Vector employeeInfoVec = cir.getEmployeeInfo(employeeIdentifier);
        
        employeeDetailsArr.add((String)employeeInfoVec.get(0));             // Employee First Name
        employeeDetailsArr.add((String)employeeInfoVec.get(1));             // Employee Last Name
        employeeDetailsArr.add((String)employeeInfoVec.get(2));             // Employee Email
        employeeDetailsArr.add(String.valueOf(employeeInfoVec.get(3)));     // Employee Active Status
        employeeDetailsArr.add((String)employeeInfoVec.get(4));             // Employee Creation Date
        
        return employeeDetailsArr;
    }

}
