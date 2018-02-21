/***************************************************************************************
*   Title:                  ErrandsAdminController.java
*   Purpose:                SERVLET FOR UNIFY ERRANDS - ADMIN (EDUBOX)
*   Created & Modified By:  CHEN MENG
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
import javax.servlet.annotation.MultipartConfig;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import unifysessionbeans.admin.ErrandsAdminMgrBeanRemote;
import unifysessionbeans.admin.UserProfileAdminMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class ErrandsAdminController extends HttpServlet {
    @EJB
    private ErrandsAdminMgrBeanRemote eamr;
    @EJB
    private UserProfileAdminMgrBeanRemote uamr;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            request.setAttribute("unifyUserCount", uamr.getUnifyUserCount());
            
            switch (pageAction) {
                case "goToViewJobCategoryListing":
                    request.setAttribute("activeJobCategoryListCount", eamr.getActiveJobCategoryListCount());
                    request.setAttribute("inactiveJobCategoryListCount", eamr.getInactiveJobCategoryListCount());
                    request.setAttribute("jobListingCount", eamr.getJobListingCount());
                    request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    pageAction = "ViewJobCategoryListing";
                    break;
                case "goToCreateNewJobCategory":
                    pageAction = "CreateNewJobCategory";
                    break;
                case "goToViewJobListing":
                    request.setAttribute("jobListing", (ArrayList) eamr.getAllJobListing());
                    pageAction = "ViewJobListing";
                    break;
                case "goToViewJobDetails":
                    long jobId = Long.parseLong(request.getParameter("jobID"));
                    request.setAttribute("jobDetails", eamr.getJobDetails(jobId));
                    request.setAttribute("jobReviews", eamr.getJobReviews(jobId));
                    request.setAttribute("jobTransaction", eamr.getJobTransaction(jobId));
                    pageAction = "ViewJobDetails";
                    break;
                case "deleteAJobListing":
                    String hiddenJobId = request.getParameter("hiddenJobID");
                    long jobID = Long.parseLong(hiddenJobId);
                    if (eamr.deleteJobListing(jobID)) {
                        request.setAttribute("successMessage", "Selected job listing has been deleted successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected job listing cannot be deleted. Please try again later.");
                    }
                    request.setAttribute("jobListing", (ArrayList) eamr.getAllJobListing());
                    pageAction = "ViewJobListing";
                    break;
                case "newJobCategory":
                    if (createJobCategory(request)) {
                        request.setAttribute("successMessage", "Job Category has been created successfully.");
                    } else {
                        request.setAttribute("errorMessage", "One or more fields are invalid. Please check again.");
                    }
                    request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    pageAction = "ViewJobCategoryListing";
                    break;
                case "goToViewJobCategoryDetails":
                    String urlCategoryID = request.getParameter("urlCategoryID");
                    long categoryID = Long.parseLong(urlCategoryID);
                    request.setAttribute("jobCategoryDetailsVec", (Vector) eamr.getJobCategoryDetails(categoryID));
                    request.setAttribute("categoryJobList", (ArrayList) eamr.getCategoryJobListing(categoryID));
                    pageAction = "ViewJobCategoryDetails";
                    break;
                case "updateJobCategory":
                    if(updateJobCategory(request)) {
                        request.setAttribute("successMessage", "Selected job category has been updated successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected job category cannot be updated. Please check the inventory log.");
                    }
                    request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    pageAction = "ViewJobCategoryListing";
                    break;
                case "deactivateJobCategory":
                    String deactCategoryID = request.getParameter("hiddenCategoryID");
                    long deactivateCategoryID = Long.parseLong(deactCategoryID);
                    
                    String icReturnMsg = eamr.deactivateJobCategory(deactivateCategoryID);
                    if (icReturnMsg.endsWith("!")) { request.setAttribute("successMessage", icReturnMsg); } 
                    else { request.setAttribute("errorMessage", icReturnMsg); }
                    
                    request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    pageAction = "ViewJobCategoryListing";
                    break;
                case "activateJobCategory":
                    String actCategoryID = request.getParameter("hiddenCategoryID");
                    long activateCategoryID = Long.parseLong(actCategoryID);
                    if (eamr.activateJobCategory(activateCategoryID)) {
                        request.setAttribute("successMessage", "Selected item category has been activated successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected item category cannot be activated. Please try again later.");
                    }
                    request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    pageAction = "ViewJobCategoryListing";
                    break;
                case "deleteAJobListingViaCategory":
                    long thejobID = Long.parseLong(request.getParameter("hiddenJobID"));
                    long theJobCategoryID = Long.parseLong(request.getParameter("hiddenCategory"));
                    if (eamr.deleteJobListing(thejobID)) {
                        request.setAttribute("successMessage", "Selected job listing has been deleted successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected job listing cannot be deleted. Please try again later.");
                    }
                    request.setAttribute("jobCategoryDetailsVec", (Vector) eamr.getJobCategoryDetails(theJobCategoryID));
                    request.setAttribute("categoryJobList", (ArrayList) eamr.getCategoryJobListing(theJobCategoryID));
                    pageAction = "ViewJobCategoryDetails";
                    break;
                case "goToViewJobDetailsInCategory":
                    long theJobId = Long.parseLong(request.getParameter("jobID"));
                    request.setAttribute("jobDetails", eamr.getJobDetails(theJobId));
                    request.setAttribute("jobReviews", eamr.getJobReviews(theJobId));
                    request.setAttribute("jobTransaction", eamr.getJobTransaction(theJobId));
                    pageAction = "ViewJobDetailsInCategory";
                    break;
                case "goToViewJobTransactions":
                    request.setAttribute("jobTransactionListing", (ArrayList) eamr.getAllJobTransactions());
                    pageAction = "ViewJobTransactionListing";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            log("Exception in ErrandsAdminController: processRequest()");
            ex.printStackTrace();
        }
    }
    
    private boolean createJobCategory(HttpServletRequest request) {
        boolean itemCategoryCreateStatus = false;
        String fileName = "";
        try {
            Part filePart = request.getPart("itemImage");
            fileName = (String) getFileName(filePart);

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
        //String categoryType = request.getParameter("hiddenCategoryType");
        String categoryDescription = request.getParameter("categoryDescription");

        if (eamr.createNewJobCategory(categoryName, "errands", categoryDescription, fileName)) {
            itemCategoryCreateStatus = true;
        }
        return itemCategoryCreateStatus;
    }
    
    private boolean updateJobCategory(HttpServletRequest request) {
        boolean itemCategoryUpdateStatus = false;
        String fileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("itemImage");
                fileName = (String) getFileName(filePart);

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
        
        String oldCategoryName = request.getParameter("oldCategoryName");
        String newCategoryName = request.getParameter("categoryName");
        long categoryID = Long.parseLong((String)request.getParameter("hiddenCategoryID"));
        String oldCategoryDescription = request.getParameter("oldCategoryDescription");
        String newCategoryDescription = request.getParameter("categoryDescription");

        if(newCategoryName.equals("")) { newCategoryName = oldCategoryName; }
        if(newCategoryDescription.equals("")) { newCategoryDescription = oldCategoryDescription; }
        
        if (eamr.updateJobCategory(categoryID, newCategoryName, newCategoryDescription, fileName)) {
            itemCategoryUpdateStatus = true;
        }
        return itemCategoryUpdateStatus;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "Errands (Job) Admin Servlet"; }
}
