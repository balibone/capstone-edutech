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
    String responseMessage = "";
    
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
                case "goToNewJobCategory":
                    pageAction = "NewJobCategory";
                    break;
                case "createJobCategory":
                    responseMessage = createJobCategory(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeJobCategoryListCount", eamr.getActiveJobCategoryListCount());
                    request.setAttribute("inactiveJobCategoryListCount", eamr.getInactiveJobCategoryListCount());
                    request.setAttribute("jobListingCount", eamr.getJobListingCount());
                    request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    pageAction = "ViewJobCategoryListing";
                    break;
                case "updateJobCategory":
                    responseMessage = updateJobCategory(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    long jCategoryID = Long.parseLong(request.getParameter("hiddenJobCategoryID"));
                    
                    //request.setAttribute("activeJobCategoryListCount", eamr.getActiveJobCategoryListCount());
                    //request.setAttribute("inactiveJobCategoryListCount", eamr.getInactiveJobCategoryListCount());
                    //request.setAttribute("jobListingCount", eamr.getJobListingCount());
                    //request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    
                    //pageAction = "ViewJobCategoryListing";
                    request.setAttribute("urlJobCategoryID", jCategoryID);
                    request.setAttribute("jobCategoryDetailsVec", eamr.getJobCategoryDetails(jCategoryID));
                    request.setAttribute("associatedJobList", (ArrayList) eamr.viewAssociatedJobList(jCategoryID));
                    pageAction = "ViewJobCategoryDetails";
                    break;
                case "goToViewJobCategoryDetails":
                    long urlJobCategoryID = Long.parseLong(request.getParameter("jobCategoryID"));
                    request.setAttribute("urlJobCategoryID", urlJobCategoryID);
                    request.setAttribute("jobCategoryDetailsVec", eamr.getJobCategoryDetails(urlJobCategoryID));
                    request.setAttribute("associatedJobList", (ArrayList) eamr.viewAssociatedJobList(urlJobCategoryID));
                    pageAction = "ViewJobCategoryDetails";
                    break;
                case "deactivateAJobCategory":
                    long deactJobCategoryID = Long.parseLong(request.getParameter("hiddenJobCategoryID"));
                    responseMessage = eamr.deactivateAJobCategory(deactJobCategoryID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    //request.setAttribute("activeJobCategoryListCount", eamr.getActiveJobCategoryListCount());
                    //request.setAttribute("inactiveJobCategoryListCount", eamr.getInactiveJobCategoryListCount());
                    //request.setAttribute("jobListingCount", eamr.getJobListingCount());
                    //request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    
                    request.setAttribute("urlJobCategoryID", deactJobCategoryID);
                    request.setAttribute("jobCategoryDetailsVec", eamr.getJobCategoryDetails(deactJobCategoryID));
                    request.setAttribute("associatedJobList", (ArrayList) eamr.viewAssociatedJobList(deactJobCategoryID));
                    pageAction = "ViewJobCategoryDetails";
                    break;
                case "activateAJobCategory":
                    long actJobCategoryID = Long.parseLong(request.getParameter("hiddenJobCategoryID"));
                    responseMessage = eamr.activateAJobCategory(actJobCategoryID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    //request.setAttribute("activeJobCategoryListCount", eamr.getActiveJobCategoryListCount());
                    //request.setAttribute("inactiveJobCategoryListCount", eamr.getInactiveJobCategoryListCount());
                    //request.setAttribute("jobListingCount", eamr.getJobListingCount());
                    //request.setAttribute("jobCategoryList", (ArrayList) eamr.getAllJobCategory());
                    //pageAction = "ViewJobCategoryListing";
                    request.setAttribute("urlJobCategoryID", actJobCategoryID);
                    request.setAttribute("jobCategoryDetailsVec", eamr.getJobCategoryDetails(actJobCategoryID));
                    request.setAttribute("associatedJobList", (ArrayList) eamr.viewAssociatedJobList(actJobCategoryID));
                    pageAction = "ViewJobCategoryDetails";
                    break;
                case "goToViewJobListing":
                    request.setAttribute("availableJobListingCount", eamr.getAvailableJobListingCount());
                    request.setAttribute("reservedJobListingCount", eamr.getReservedJobListingCount());
                    request.setAttribute("completedJobListingCount", eamr.getCompletedJobListingCount());
                    request.setAttribute("jobListing", (ArrayList) eamr.getAllJobListing());
                    pageAction = "ViewJobListing";
                    break;
                case "goToViewJobDetails":
                    long urlJobID = Long.parseLong(request.getParameter("jobID"));
                    request.setAttribute("urlJobID", urlJobID);
                    
                    request.setAttribute("jobDetailsVec", eamr.getJobDetails(urlJobID));
                    request.setAttribute("jobReviewList", eamr.getJobReviewList(urlJobID));
                    request.setAttribute("jobTransList", eamr.getJobTransactionList(urlJobID));
                    pageAction = "ViewJobDetails";
                    break;
                case "goToViewJobDetailsInModal":
                    long hidJobID = Long.parseLong(request.getParameter("jobID"));
                    request.setAttribute("urlJobID", hidJobID);
                    long hidJobCategoryID = Long.parseLong(request.getParameter("jobCategoryID"));
                    request.setAttribute("urlJobCategoryID", hidJobCategoryID);
                    
                    request.setAttribute("jobDetailsVec", eamr.getJobDetails(hidJobID));
                    request.setAttribute("jobReviewList", eamr.getJobReviewList(hidJobID));
                    request.setAttribute("jobTransList", eamr.getJobTransactionList(hidJobID));
                    pageAction = "ViewJobDetailsInModal";
                    break;
                case "deleteAJob":
                    long hiddenJobID = Long.parseLong(request.getParameter("jobID"));
                    responseMessage = eamr.deleteAJob(hiddenJobID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("availableJobListingCount", eamr.getAvailableJobListingCount());
                    request.setAttribute("reservedJobListingCount", eamr.getReservedJobListingCount());
                    request.setAttribute("completedJobListingCount", eamr.getCompletedJobListingCount());
                    request.setAttribute("jobListing", (ArrayList) eamr.getAllJobListing());
                    pageAction = "ViewJobListing";
                    break;
                case "deleteAJobInModal":
                    long hiddenJob = Long.parseLong(request.getParameter("jobID"));
                    responseMessage = eamr.deleteAJob(hiddenJob);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    long jobCategoryID = Long.parseLong(request.getParameter("jobCategoryID"));
                    request.setAttribute("urlJobCategoryID", jobCategoryID);
                    request.setAttribute("jobCategoryDetailsVec", eamr.getJobCategoryDetails(jobCategoryID));
                    request.setAttribute("associatedJobList", (ArrayList) eamr.viewAssociatedJobList(jobCategoryID));
                    pageAction = "ViewJobCategoryDetails";
                    break;
                case "goToViewJobTransactions":
                    request.setAttribute("jobTransactionsCount", eamr.getErrandsTransCount());
                    request.setAttribute("jobTransactionsList", (ArrayList) eamr.getAllJobTransactions());
                    pageAction = "ViewJobTransactionsList";
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
    
    private String createJobCategory(HttpServletRequest request) {
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
        
        boolean sameName = false;
        ArrayList<Vector> categoryList = (ArrayList) eamr.getAllJobCategory();
        for(int i=0; i<categoryList.size(); i++){
            if(categoryName.equals(categoryList.get(i).get(2))){
                sameName = true;
                break;
            }
        }
        
        if(sameName){
            return "There is already a category called " + categoryName + ". Please try another name.";
        }
        
        return eamr.createJobCategory(categoryName, "Errands", categoryDescription, fileName);
    }
    
    private String updateJobCategory(HttpServletRequest request) {
        String fileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
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
        }
        else { fileName = request.getParameter("oldCategoryImage"); }
        
        long jobCategoryID = Long.parseLong(request.getParameter("hiddenJobCategoryID"));
        String categoryName = request.getParameter("oldCategoryName");
        String newCategoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("oldCategoryDescription");
        String newCategoryDescription = request.getParameter("categoryDescription");

        if(!newCategoryName.equals("")) { categoryName = newCategoryName; }
        if(!newCategoryDescription.equals("")) { categoryDescription = newCategoryDescription; }
        return eamr.updateJobCategory(jobCategoryID, categoryName, categoryDescription, fileName);
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
