/***************************************************************************************
*   Title:                  VoicesAdminController.java
*   Purpose:                SERVLET FOR UNIFY COMPANY REVIEW - ADMIN (EDUBOX)
*   Created & Modified By:  ZHU XINYI
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifycontrollers.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.Part;
import unifysessionbeans.admin.UserProfileAdminMgrBeanRemote;

import unifysessionbeans.admin.VoicesAdminMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class VoicesAdminController extends HttpServlet {
    @EJB
    private VoicesAdminMgrBeanRemote vamr;
    @EJB
    private UserProfileAdminMgrBeanRemote uamr;
    String responseMessage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            request.setAttribute("unifyUserCount", uamr.getUnifyUserCount());
            
            switch (pageAction) {
                case "goToViewCompanyCategoryList":
                    request.setAttribute("activeCompanyCategoryListCount", vamr.getActiveCompanyCategoryListCount());
                    request.setAttribute("inactiveCompanyCategoryListCount", vamr.getInactiveCompanyCategoryListCount());
                    request.setAttribute("companyListingCount", vamr.getCompanyListingCount());
                    request.setAttribute("companyCategoryList", (ArrayList) vamr.viewCompanyCategoryList());
                    pageAction = "ViewCompanyCategoryList";
                    break;
                case "goToNewCompanyCategory":
                    pageAction = "NewCompanyCategory";
                    break;
                case "createCompanyCategory":
                    responseMessage = createCompanyCategory(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyCategoryListCount", vamr.getActiveCompanyCategoryListCount());
                    request.setAttribute("inactiveCompanyCategoryListCount", vamr.getInactiveCompanyCategoryListCount());
                    request.setAttribute("companyListingCount", vamr.getCompanyListingCount());
                    request.setAttribute("companyCategoryList", (ArrayList) vamr.viewCompanyCategoryList());
                    pageAction = "ViewCompanyCategoryList";
                    break;
                case "goToViewCompanyCategoryDetails":
                    long urlCompanyCategoryID = Long.parseLong(request.getParameter("companyCategoryID"));
                    request.setAttribute("urlCompanyCategoryID", urlCompanyCategoryID);
                    request.setAttribute("companyCategoryDetailsVec", vamr.viewCompanyCategoryDetails(urlCompanyCategoryID));
                    request.setAttribute("associatedCompanyList", (ArrayList) vamr.viewAssociatedCompanyList(urlCompanyCategoryID));
                    pageAction = "ViewCompanyCategoryDetails";
                    break;
                case "deactivateACompanyCategory":
                    long deactCompanyCategoryID = Long.parseLong(request.getParameter("hiddenCompanyCategoryID"));
                    responseMessage = vamr.deactivateACompanyCategory(deactCompanyCategoryID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyCategoryListCount", vamr.getActiveCompanyCategoryListCount());
                    request.setAttribute("inactiveCompanyCategoryListCount", vamr.getInactiveCompanyCategoryListCount());
                    request.setAttribute("companyListingCount", vamr.getCompanyListingCount());
                    request.setAttribute("companyCategoryList", (ArrayList) vamr.viewCompanyCategoryList());
                    pageAction = "ViewCompanyCategoryList";
                    break;
                case "activateACompanyCategory":
                    long actCompanyCategoryID = Long.parseLong(request.getParameter("hiddenCompanyCategoryID"));
                    responseMessage = vamr.activateACompanyCategory(actCompanyCategoryID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyCategoryListCount", vamr.getActiveCompanyCategoryListCount());
                    request.setAttribute("inactiveCompanyCategoryListCount", vamr.getInactiveCompanyCategoryListCount());
                    request.setAttribute("companyListingCount", vamr.getCompanyListingCount());
                    request.setAttribute("companyCategoryList", (ArrayList) vamr.viewCompanyCategoryList());
                    pageAction = "ViewCompanyCategoryList";
                    break;
                case "updateCompanyCategory":
                    responseMessage = updateCompanyCategory(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyCategoryListCount", vamr.getActiveCompanyCategoryListCount());
                    request.setAttribute("inactiveCompanyCategoryListCount", vamr.getInactiveCompanyCategoryListCount());
                    request.setAttribute("companyListingCount", vamr.getCompanyListingCount());
                    request.setAttribute("companyCategoryList", (ArrayList) vamr.viewCompanyCategoryList());
                    pageAction = "ViewCompanyCategoryList";
                    break;
                case "goToViewCompanyList":
                    request.setAttribute("activeCompanyListingCount", vamr.getActiveCompanyListingCount());
                    request.setAttribute("inactiveCompanyListingCount", vamr.getInactiveCompanyListingCount());
                    request.setAttribute("companyList", (ArrayList) vamr.viewCompanyList());
                    pageAction = "ViewCompanyList";
                    break;
                case "goToNewCompany":
                    request.setAttribute("companyIndustryStr", vamr.populateCompanyIndustry());
                    pageAction = "NewCompany";
                    break;
                case "createCompany":
                    responseMessage = createCompany(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyListingCount", vamr.getActiveCompanyListingCount());
                    request.setAttribute("inactiveCompanyListingCount", vamr.getInactiveCompanyListingCount());
                    request.setAttribute("companyList", (ArrayList) vamr.viewCompanyList());
                    pageAction = "ViewCompanyList";
                    break;
                case "goToNewCompanyInModal":
                    long companyCategoryID = Long.parseLong(request.getParameter("companyCategoryID"));
                    request.setAttribute("companyCategoryID", companyCategoryID);
                    request.setAttribute("companyIndustry", request.getParameter("categoryName"));
                    pageAction = "NewCompanyInModal";
                    break;
                case "createCompanyInModal":
                    responseMessage = createCompanyInModal(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyListingCount", vamr.getActiveCompanyListingCount());
                    request.setAttribute("inactiveCompanyListingCount", vamr.getInactiveCompanyListingCount());
                    request.setAttribute("companyList", (ArrayList) vamr.viewCompanyList());
                    pageAction = "ViewCompanyList";
                    break;
                case "goToViewCompanyListDetails":
                    request.setAttribute("companyIndustryStr", vamr.populateCompanyIndustry());
                    long urlCompanyID = Long.parseLong(request.getParameter("companyID"));
                    request.setAttribute("urlCompanyID", urlCompanyID);
                    request.setAttribute("companyDetailsVec", vamr.viewCompanyDetails(urlCompanyID));
                    request.setAttribute("companyReviewList", (ArrayList) vamr.viewAssociatedReviewList(urlCompanyID));
                    pageAction = "ViewCompanyListDetails";
                    break;
                case "goToViewCompanyListDetailsInModal":
                    request.setAttribute("companyIndustryStr", vamr.populateCompanyIndustry());
                    long hidCompanyID = Long.parseLong(request.getParameter("companyID"));
                    request.setAttribute("urlCompanyID", hidCompanyID);
                    long hidCompanyCategoryID = Long.parseLong(request.getParameter("companyCategoryID"));
                    request.setAttribute("urlCompanyCategoryID", hidCompanyCategoryID);
                    
                    request.setAttribute("companyDetailsVec", vamr.viewCompanyDetails(hidCompanyID));
                    request.setAttribute("companyReviewList", (ArrayList) vamr.viewAssociatedReviewList(hidCompanyID));
                    pageAction = "ViewCompanyListDetailsInModal";
                    break;
                case "deactivateACompany":
                    long deactCompanyID = Long.parseLong(request.getParameter("hiddenCompanyID"));
                    responseMessage = vamr.deactivateACompany(deactCompanyID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyListingCount", vamr.getActiveCompanyListingCount());
                    request.setAttribute("inactiveCompanyListingCount", vamr.getInactiveCompanyListingCount());
                    request.setAttribute("companyList", (ArrayList) vamr.viewCompanyList());
                    pageAction = "ViewCompanyList";
                    break;
                case "activateACompany":
                    long actCompanyID = Long.parseLong(request.getParameter("hiddenCompanyID"));
                    responseMessage = vamr.activateACompany(actCompanyID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyListingCount", vamr.getActiveCompanyListingCount());
                    request.setAttribute("inactiveCompanyListingCount", vamr.getInactiveCompanyListingCount());
                    request.setAttribute("companyList", (ArrayList) vamr.viewCompanyList());
                    pageAction = "ViewCompanyList";
                    break;
                case "updateCompany":
                    responseMessage = updateCompany(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeCompanyListingCount", vamr.getActiveCompanyListingCount());
                    request.setAttribute("inactiveCompanyListingCount", vamr.getInactiveCompanyListingCount());
                    request.setAttribute("companyList", (ArrayList) vamr.viewCompanyList());
                    pageAction = "ViewCompanyList";
                    break;
                /*
                case "goToAddCompany":
                    String posterID = (String) request.getParameter("requestPosterID");
                    String addRequestCompany = (String) request.getParameter("requestCompany");
                    String addRequestIndustry = (String) request.getParameter("requestIndustry");
                    request.setAttribute("requestPosterID", posterID);
                    request.setAttribute("requestCompany", addRequestCompany);
                    request.setAttribute("requestIndustry", addRequestIndustry);
                    pageAction = "AddCompany";
                    break;
                 case "addCompany":
                    String newRequestCompany = (String) request.getParameter("hiddenRequestCompany");
                    String newRequestPosterID = (String) request.getParameter("hiddenRequestPosterID");
                    
                    if (addCompany(request)) {
                        request.setAttribute("successMessage", "Company has been added successfully.");
                    } else {
                        request.setAttribute("errorMessage", "One or more fields are invalid. Please check again.");
                    }
                    vamr.solveRequest(newRequestCompany, newRequestPosterID);
                    request.setAttribute("requestListVec", (ArrayList) vamr.viewCompanyRequestList());
                    pageAction = "ViewCompanyRequestList";
                    break;
                case "viewReviewList":
                    String reviewedCompanyName = request.getParameter("hiddenCompanyName");
                    request.setAttribute("reviewListVec", (ArrayList) vamr.viewReviewList(reviewedCompanyName));
                    pageAction = "ViewReviewList";
                    break; 
                case "goToViewReviewListDetails":
                    String reviewedCompany = request.getParameter("reviewedCompany");
                    String reviewPosterID = request.getParameter("reviewPosterID");
                    request.setAttribute("reviewDetailsVec", vamr.viewReviewDetails(reviewedCompany, reviewPosterID));
                    pageAction = "ViewReviewListDetails";
                    break; 
                case "deleteReview":
                    String DelReviewedCompany = request.getParameter("hiddenReviewedCompany");
                    System.out.println(DelReviewedCompany);
                    String DelReviewPosterID = request.getParameter("hiddenReviewPosterID");
                    System.out.println(DelReviewPosterID);
                    if (vamr.deleteReview(DelReviewedCompany, DelReviewPosterID)) {
                        request.setAttribute("successMessage", "Selected Review has been deleted successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected Review cannot be deleted. Please try again later.");
                    }
                    request.setAttribute("data", vamr.viewCompanyDetails(DelReviewedCompany));
                    request.setAttribute("reviewListVec", (ArrayList) vamr.viewReviewList(DelReviewedCompany));
                    pageAction = "ViewCompanyListDetails";
                    break;
                case "goToViewCategoryCompanyReviewList":
                    String categoryCompanyName = request.getParameter("hiddenCategoryCompany");
                    request.setAttribute("data", vamr.viewCompanyDetails(categoryCompanyName));
                    request.setAttribute("reviewListVec", (ArrayList) vamr.viewReviewList(categoryCompanyName));
                    pageAction = "ViewCompanyReviewList";
                    break;
                case "goToViewCompanyRequestList":
                    data = (ArrayList) vamr.viewCompanyRequestList();
                    request.setAttribute("requestListVec", data);
                    pageAction = "ViewCompanyRequestList";
                    break;
                case "rejectRequest":
                    String requestCompany = request.getParameter("hiddenRequestCompany");
                    System.out.println(requestCompany);
                    String requestPosterID = request.getParameter("hiddenRequestPosterID");
                    System.out.println(requestPosterID);
                    if (vamr.rejectRequest(requestCompany, requestPosterID)) {
                        request.setAttribute("successMessage", "Selected Request has been rejected successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected Request cannot be rejected. Please try again later.");
                    }
                    request.setAttribute("requestListVec", (ArrayList) vamr.viewCompanyRequestList());
                    pageAction = "ViewCompanyRequestList";
                    break; 
                case "goToViewCompanyRequestListDetails":
                    String viewRequestCompany = request.getParameter("requestCompany");
                    String viewRequestPosterID = request.getParameter("requestPosterID");
                    request.setAttribute("requestDetailsVec", vamr.viewCompanyRequestDetails(viewRequestCompany, viewRequestPosterID));
                    pageAction = "ViewCompanyRequestListDetails";
                    break;
                */
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            log("Exception in VoicesAdminController: processRequest()");
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("VoicesAdminController: doPost()");
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "Voices (Shout) Admin Servlet"; }
    
    private String createCompanyCategory(HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("itemImage");
            fileName = (String) getFileName(filePart);
            if(fileName.contains("\\")) {
                fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
            }
                    
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "common"
                    + File.separator + "category" + File.separator;
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            try {
                File outputFilePath = new File(imageDir + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);

                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fileName = "";
        }
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");
        
        return vamr.createCompanyCategory(categoryName, "Voices", categoryDescription, fileName);
    }
    
    private String updateCompanyCategory(HttpServletRequest request) {
        String fileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("categoryImage");
                fileName = (String) getFileName(filePart);
                if(fileName.contains("\\")) {
                    fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
                }
                
                String appPath = request.getServletContext().getRealPath("");
                String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                        + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
                String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                        + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "common"
                        + File.separator + "category" + File.separator;

                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(imageDir + fileName);
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);

                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    fileName = "";
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
            }
        }
        else { fileName = request.getParameter("oldCategoryImage"); }
        
        long companyCategoryID = Long.parseLong(request.getParameter("hiddenCompanyCategoryID"));
        String categoryName = request.getParameter("oldCategoryName");
        String newCategoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("oldCategoryDescription");
        String newCategoryDescription = request.getParameter("categoryDescription");

        if(!newCategoryName.equals("")) { categoryName = newCategoryName; }
        if(!newCategoryDescription.equals("")) { categoryDescription = newCategoryDescription; }
        return vamr.updateCompanyCategory(companyCategoryID, categoryName, categoryDescription, fileName);
    }
    
    private String createCompany(HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("companyImage");
            fileName = (String) getFileName(filePart);
            if(fileName.contains("\\")) {
                fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
            }
                    
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "voices"
                    + File.separator + "company" + File.separator;
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            try {
                File outputFilePath = new File(imageDir + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);

                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fileName = "";
        }
        
        String companyIndustry = request.getParameter("companyIndustry");
        String companyName = request.getParameter("companyName");
        String companySize = request.getParameter("companySize");
        String companyWebsite = request.getParameter("companyWebsite");
        String companyHQ = request.getParameter("companyHQ");
        String companyDescription = request.getParameter("companyDescription");
        String companyAddress = request.getParameter("companyAddress");
        
        return vamr.createCompany(companyIndustry, companyName, Integer.parseInt(companySize), 
                companyWebsite, companyHQ, companyDescription, companyAddress, fileName);
    }
    
    private String createCompanyInModal(HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("companyImage");
            fileName = (String) getFileName(filePart);
            if(fileName.contains("\\")) {
                fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
            }
                    
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "voices"
                    + File.separator + "company" + File.separator;
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            try {
                File outputFilePath = new File(imageDir + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);

                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fileName = "";
        }
        
        String companyIndustry = request.getParameter("urlCompanyIndustry");
        String companyName = request.getParameter("companyName");
        String companySize = request.getParameter("companySize");
        String companyWebsite = request.getParameter("companyWebsite");
        String companyHQ = request.getParameter("companyHQ");
        String companyDescription = request.getParameter("companyDescription");
        String companyAddress = request.getParameter("companyAddress");
        
        return vamr.createCompany(companyIndustry, companyName, Integer.parseInt(companySize), 
                companyWebsite, companyHQ, companyDescription, companyAddress, fileName);
    }
    
    private String updateCompany(HttpServletRequest request) {
        String fileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("companyImage");
                fileName = (String) getFileName(filePart);
                if(fileName.contains("\\")) {
                    fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
                }

                String appPath = request.getServletContext().getRealPath("");
                String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                        + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
                String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                        + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "voices"
                        + File.separator + "company" + File.separator;

                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(imageDir + fileName);
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);

                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    fileName = "";
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
            }
        }
        else { fileName = request.getParameter("oldCompanyImage"); }
        
        long companyID = Long.parseLong(request.getParameter("hiddenCompanyID"));
        String companyName = request.getParameter("oldCompanyName");
        String newCompanyName = request.getParameter("companyName");
        String companyIndustry = request.getParameter("companyIndustry");
        String companyWebsite = request.getParameter("oldCompanyWebsite");
        String newCompanyWebsite = request.getParameter("companyWebsite");
        String companyHQ = request.getParameter("oldCompanyHQ");
        String newCompanyHQ = request.getParameter("companyHQ");
        String companySize = request.getParameter("oldCompanySize");
        String newCompanySize = request.getParameter("companySize");
        String companyDescription = request.getParameter("oldCompanyDescription");
        String newCompanyDescription = request.getParameter("companyDescription");
        String companyAddress = request.getParameter("oldCompanyAddress");
        String newCompanyAddress = request.getParameter("companyAddress");

        if(!newCompanyName.equals("")) { companyName = newCompanyName; }
        if(!newCompanyWebsite.equals("")) { companyWebsite = newCompanyWebsite; }
        if(!newCompanyHQ.equals("")) { companyHQ = newCompanyHQ; }
        if(!newCompanySize.equals("")) { companySize = newCompanySize; }
        if(!newCompanyDescription.equals("")) { companyDescription = newCompanyDescription; }
        if(!newCompanyAddress.equals("")) { companyAddress = newCompanyAddress; }
        
        return vamr.updateCompany(companyID, companyName, companyIndustry, companyWebsite, companyHQ, 
                Integer.parseInt(companySize), companyDescription, companyAddress, fileName);
    }
    
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}