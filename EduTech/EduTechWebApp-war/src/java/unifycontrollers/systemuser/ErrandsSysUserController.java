/***************************************************************************************
*   Title:                  ErrandsSysUserController.java
*   Purpose:                SERVLET FOR UNIFY ERRANDS - SYSUSER (EDUBOX)
*   Created & Modified By:  CHEN MENG
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifycontrollers.systemuser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ejb.EJB;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import unifysessionbeans.systemuser.ErrandsSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.UserProfileSysUserMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class ErrandsSysUserController extends HttpServlet {
    @EJB
    private ErrandsSysUserMgrBeanRemote esmr;
    @EJB
    private UserProfileSysUserMgrBeanRemote usmr;
    String responseMessage = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String pageAction = request.getParameter("pageTransit");
            String loggedInUsername = getCookieUsername(request);
            
            switch (pageAction) {
                case "goToViewJobListingSYS":
                    request.setAttribute("categoryList", (ArrayList)esmr.getJobCategoryList());
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewJobList(request.getParameter("username")));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobListingSYS";
                    break;
                case "goToViewJobDetailsSYS":
                    long jobID = Long.parseLong(request.getParameter("hiddenJobID"));
                    String categoryName = request.getParameter("hiddenCategoryName");
                    String loggedinUsername = request.getParameter("loggedinUser");
                    request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(jobID, loggedinUsername));
                    request.setAttribute("assocCategoryJobListSYS", esmr.viewAssocCategoryJobList(categoryName, jobID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobDetailsSYS";
                    break;
                case "goToNewJobListingSYS":
                    request.setAttribute("jobCategoryListSYS", (ArrayList)esmr.viewJobCategoryList());
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "NewJobListingSYS";
                    break;
                case "createJobListingSYS":
                    responseMessage = createJobListing(request);
                    System.out.println(responseMessage);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("jobCategoryListSYS", (ArrayList) esmr.viewJobCategoryList());
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "NewJobListingSYS";
                    break;
                case "goToEditJobListing":
                    long jID = Long.parseLong(request.getParameter("hiddenJobID"));
                    String loginUser = request.getParameter("loginUser");
                    request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(jID, loginUser));
                    request.setAttribute("jobCategoryListSYS", (ArrayList)esmr.viewJobCategoryList());
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "EditJobListingSYS";
                    break;
                case "editJobListingSYS":
                    responseMessage = editJobListing(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    long job = Long.parseLong(request.getParameter("hiddenJobID"));
                    String user = request.getParameter("username");
                    request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(job, user));
                    request.setAttribute("jobCategoryListSYS", (ArrayList)esmr.viewJobCategoryList());
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "EditJobListingSYS";
                    break;
                case "deleteJobListingSYS":
                    long jobIDToDelete = Long.parseLong(request.getParameter("hiddenJobID"));
                    responseMessage = esmr.deleteJobListing(jobIDToDelete);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("categoryList", (ArrayList)esmr.getJobCategoryList());
                    request.setAttribute("jobListSYS", (ArrayList) esmr.viewJobList(request.getParameter("username")));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobListingSYS";
                    break;
                case "likeJobListingDetails":
                    long jobIDHid = Long.parseLong(request.getParameter("jobIDHid"));
                    String usernameHid = request.getParameter("usernameHid");
                    
                    responseMessage = esmr.likeUnlikeJob(jobIDHid, usernameHid);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToJobLikeList":
                    long jobKey = Long.parseLong(request.getParameter("jobID"));
                    request.setAttribute("jobLikeListSYS", esmr.viewJobLikeList(jobKey));
                    pageAction = "jobLikeListSYS";
                    break;
                case "reportJobListing":
                    long jobIDToReport = Long.parseLong(request.getParameter("jobID"));
                    String username = request.getParameter("username");
                    String reportReason = request.getParameter("reportReason");
                    
                    responseMessage = esmr.reportJobListing(jobIDToReport, username, reportReason);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "sendJobOfferPrice":
                    long jobIDHidden = Long.parseLong(request.getParameter("jobIDHidden"));
                    String usernameHidden = request.getParameter("usernameHidden");
                    String jobOfferPrice = request.getParameter("jobOfferPrice");
                    String jobOfferDescription = request.getParameter("jobOfferDescription");
                    
                    responseMessage = esmr.sendJobOfferPrice(jobIDHidden, usernameHidden, jobOfferPrice, jobOfferDescription);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToViewJobOfferList":
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(request.getParameter("username")));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferListSYS";
                    break;
                case "goToViewJobOfferDetails":
                    String userName = request.getParameter("hiddenUserName");
                    long hiddenJobID = Long.parseLong(request.getParameter("jobID"));
                    System.out.println("parameter: " + userName + hiddenJobID);
                    request.setAttribute("message", "");
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(userName));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(userName, hiddenJobID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS";
                    break;
                case "acceptJobOffer":
                    String userID = request.getParameter("username");
                    long relevantJob = Long.parseLong(request.getParameter("jobId"));
                    
                    request.setAttribute("message", returnMessage);
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(userID));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(userID, relevantJob));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS"; 
                    break;
                case "rejectJobOffer":
                    String logInUser = request.getParameter("username");
                    long relatedJob = Long.parseLong(request.getParameter("jobId"));
                    
                    request.setAttribute("message", returnMsg);
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(logInUser));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(logInUser, relatedJob));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS"; 
                    break;
                case "negotiateJobOffer":
                    String loggedInUser = request.getParameter("username");
                    long jobRelated = Long.parseLong(request.getParameter("jobID"));
                    
                    request.setAttribute("message", returnStr);
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUser));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(loggedInUser, jobRelated));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS"; 
                    break;
                case "goToSignaturePad":
                    long jobIDToComplete = Long.parseLong(request.getParameter("jobID"));
                    String takerID = request.getParameter("username");
                    String msg = esmr.completeAJob(takerID, jobIDToComplete);
                    pageAction = "SignaturePadSYS";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);       
        }
        catch(Exception ex) {
            log("Exception in ErrandsSysUserController: processRequest()");
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
    public String getServletInfo() { return "Errands (Job) System User Servlet"; }
    
    /* KEY METHODS */
    private String createJobListing(HttpServletRequest request) {
        String jobImagefileName = "";
        try {
            Part filePart = request.getPart("jobImage");
            jobImagefileName = (String) getFileName(filePart);

            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "errands"
                    + File.separator + "job" + File.separator;
            Files.createDirectories(Paths.get(imageDir));
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(imageDir + jobImagefileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);

                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                jobImagefileName = "";
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
            jobImagefileName = "";
        }
        
        double jobDuration = Double.parseDouble(request.getParameter("jobDuration"));
        String jobTitle = request.getParameter("jobTitle");
        String jobRateType = (String)request.getParameter("jobRateType");
        double jobRate = Double.parseDouble(request.getParameter("jobRate"));
        
        String jobDescription = request.getParameter("jobDescription");
        long categoryID = Long.parseLong(request.getParameter("hiddenCategoryID"));
        String username = request.getParameter("username");
        String startLocation = request.getParameter("startLocation");
        String startLat = (request.getParameter("hiddenStartLat"));
        String startLong = (request.getParameter("hiddenStartLong"));
        String endLocation = request.getParameter("endLocation");
        String endLat = (request.getParameter("hiddenEndLat"));
        String endLong = (request.getParameter("hiddenEndLong"));
        int numOfHelpers = Integer.parseInt(request.getParameter("numOfHelpers"));
        boolean checking = false;
        if(request.getParameter("checking")==null){
            checking = false;
        }else{
            checking = true;
        }
        Date jobWorkDate = new Date();
        try{
            jobWorkDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(request.getParameter("workDate") + " " + request.getParameter("workTime"));
        }catch(ParseException e){
            e.printStackTrace();
        }
        return esmr.createJobListing(jobTitle, jobRateType, jobRate, jobDuration, jobDescription, jobWorkDate, jobImagefileName, 
                categoryID, username, startLocation, startLat, startLong, endLocation, endLat, endLong, "N.A.", numOfHelpers, checking);
    }
    
    private String editJobListing(HttpServletRequest request) {
        String itemImageFileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("jobImage");
                itemImageFileName = (String) getFileName(filePart);

                String appPath = request.getServletContext().getRealPath("");
                String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator 
                        + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
                String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator 
                        + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "errands" 
                        + File.separator + "job" + File.separator;
                Files.createDirectories(Paths.get(imageDir));
                
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(imageDir + itemImageFileName);
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);

                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    itemImageFileName = "";
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
                itemImageFileName = "";
            }
        } else { itemImageFileName = request.getParameter("hiddenJobImage"); }
        
        long jobID = Long.parseLong(request.getParameter("hiddenJobID"));
        
        String jobTitle = request.getParameter("jobTitle");
        if(jobTitle.equals("")) { jobTitle = request.getParameter("hiddenJobTitle"); }
        
        long jobCategoryID = Long.parseLong(request.getParameter("category"));
        
        Date jobWorkDate = new Date();
        try{
            jobWorkDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(request.getParameter("workDate") + " " + request.getParameter("workTime"));
        }catch(ParseException e){
            e.printStackTrace();
        }
        String jobRateType = request.getParameter("jobRateType");
        String jobRate = request.getParameter("jobRate");
        if(jobRate.equals("")) { jobRate = request.getParameter("hiddenJobRate"); }
        
        double jobDuration = Double.parseDouble(request.getParameter("hiddenJobDuration"));
        //if(!((request.getParameter("jobDuration")).equals(""))) { jobDuration = Double.parseDouble(request.getParameter("jobDuration")); }
        System.out.println(jobDuration);
        
        int numOfHelpers = Integer.parseInt(request.getParameter("hiddenNumOfHelpers"));
        if(!((request.getParameter("numOfHelpers")).equals(""))){ numOfHelpers = Integer.parseInt(request.getParameter("numOfHelpers")); }
        
        String jobDescription = request.getParameter("jobDescription");
        if(jobDescription.equals("")) { jobDescription = request.getParameter("hiddenJobDescription"); }
        
        String username = request.getParameter("username");
        
        String startLocation = request.getParameter("startLocation");
        if(startLocation.equals("")) { startLocation = request.getParameter("hiddenStartLocation"); }
        String startLat = request.getParameter("hiddenStartLat");
        String startLong = request.getParameter("hiddenStartLong");
        
        String endLocation = request.getParameter("endLocation");
        if(endLocation.equals("")) { endLocation = request.getParameter("hiddenEndLocation"); }
        String endLat = request.getParameter("hiddenEndLat");
        String endLong = request.getParameter("hiddenEndLong");
        
        boolean checking = false;
        if(request.getParameter("checking")==null){
            checking = false;
        }else{
            checking = true;
        } 
        
        return esmr.editJobListing(jobID, jobTitle, jobRateType, Double.parseDouble(jobRate), jobDuration, jobDescription, 
                jobWorkDate, itemImageFileName, startLocation, startLat, startLong, endLocation, endLat, endLong, jobCategoryID, username, numOfHelpers, checking);
    }
    
    /* MISCELLANEOUS METHODS */
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
    
    private String getCookieUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String loggedInUsername = null;
        if(cookies!=null){
            for(Cookie c : cookies){
                if(c.getName().equals("username") && !c.getValue().equals("")){
                    loggedInUsername = c.getValue();
                }
            }
        }
        return loggedInUsername;
    }
}