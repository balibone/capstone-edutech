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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

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
            System.out.println("pageAction: " + pageAction);
            String loggedInUsername = getCookieUsername(request);
            
            
            switch (pageAction) {
                case "goToViewJobListingSYS":
                    request.setAttribute("categoryList", (ArrayList)esmr.getJobCategoryList());
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewJobList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobListingSYS";
                    break;
                case "goToViewJobDetailsSYS":
                    long jobID = Long.parseLong(request.getParameter("hiddenJobID"));
                    String categoryName = request.getParameter("hiddenCategoryName");
                    
                    request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(jobID, loggedInUsername));
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
                    request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(jID, loggedInUsername));
                    request.setAttribute("jobCategoryListSYS", (ArrayList)esmr.viewJobCategoryList());
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "EditJobListingSYS";
                    break;
                case "editJobListingSYS":
                    responseMessage = editJobListing(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    long job = Long.parseLong(request.getParameter("hiddenJobID"));
                    request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(job, loggedInUsername));
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
                    request.setAttribute("jobListSYS", (ArrayList) esmr.viewJobList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobListingSYS";
                    break;
                case "likeJobListingDetails":
                    long jobIDHid = Long.parseLong(request.getParameter("jobIDHid"));
                    
                    responseMessage = esmr.likeUnlikeJob(jobIDHid, loggedInUsername);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToJobLikeList":
                    long jobKey = Long.parseLong(request.getParameter("jobID"));
                    System.out.println(jobKey);
                    request.setAttribute("jobLikeListSYS", esmr.viewJobLikeList(jobKey));
                    pageAction = "jobLikeListSYS";
                    break;
                case "reportJobListing":
                    long jobIDToReport = Long.parseLong(request.getParameter("jobID"));
                    String reportReason = request.getParameter("reportReason");
                    
                    responseMessage = esmr.reportJobListing(jobIDToReport, loggedInUsername, reportReason);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "sendJobOfferPrice":
                    long jobIDHidden = Long.parseLong(request.getParameter("jobIDHidden"));
                    String jobCategory = request.getParameter("category");
                    String jobOfferPrice = request.getParameter("jobOfferPrice");
                    String jobOfferDescription = request.getParameter("jobOfferDescription");
                    
                    responseMessage = esmr.sendJobOfferPrice(jobIDHidden, loggedInUsername, jobOfferPrice, jobOfferDescription);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    /*request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(jobIDHidden, loggedInUsername));
                    request.setAttribute("assocCategoryJobListSYS", esmr.viewAssocCategoryJobList(jobCategory, jobIDHidden));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    
                    pageAction = "ViewJobDetailsSYS";*/
                    break;
                case "goToViewJobOfferList":
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferListSYS";
                    break;
                case "goToViewJobOfferDetails":
                    String userName = request.getParameter("hiddenUserName");
                    long hiddenJobID = Long.parseLong(request.getParameter("jobID"));
                    System.out.println("parameter: " + userName + hiddenJobID);
                    request.setAttribute("message", "");
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(loggedInUsername, hiddenJobID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS";
                    break;
                case "acceptJobOffer":
                    long offerID = Long.parseLong(request.getParameter("offerID"));
                    long relevantJob = Long.parseLong(request.getParameter("jobId"));
                    String returnMessage = esmr.acceptJobOffer(offerID, loggedInUsername);
                    
                    request.setAttribute("message", returnMessage);
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(loggedInUsername, relevantJob));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS"; 
                    break;
                case "rejectJobOffer":
                    long offer = Long.parseLong(request.getParameter("offerID"));
                    long relatedJob = Long.parseLong(request.getParameter("jobId"));
                    String returnMsg = esmr.rejectJobOffer(offer, loggedInUsername);
                    
                    request.setAttribute("message", returnMsg);
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(loggedInUsername, relatedJob));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS"; 
                    break;
                case "negotiateJobOffer":
                    long offerid = Long.parseLong(request.getParameter("offerID"));
                    long jobRelated = Long.parseLong(request.getParameter("jobID"));
                    String message = request.getParameter("message");
                    String returnStr = esmr.negotiateJobOffer(offerid, loggedInUsername, message);
                    
                    request.setAttribute("message", returnStr);
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("jobOfferList", (ArrayList)esmr.viewOfferListOfAJob(loggedInUsername, jobRelated));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobOfferDetailsSYS"; 
                    
                    break;
                case "goToSignaturePad":
                    long jobIDToComplete = Long.parseLong(request.getParameter("jobID"));
                    
                    request.setAttribute("jobID", jobIDToComplete);
                    request.setAttribute("username", loggedInUsername);
                    pageAction = "SignaturePadSYS";
                    break;
                case "completeJobTransaction":
                    long jobToComplete = Long.parseLong(request.getParameter("jobID"));
                    String category = request.getParameter("category");
                    if(getSignatureImg(request)){ System.out.println("save"); }
                    request.setAttribute("jobDetailsSYSVec", (Vector)esmr.viewJobDetails(jobToComplete, loggedInUsername));
                    request.setAttribute("assocCategoryJobListSYS", esmr.viewAssocCategoryJobList(category, jobToComplete));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewJobDetailsSYS";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            System.out.println("dispatch page action: " + pageAction);
            System.out.println("dispatcher:" + dispatcher);
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
    
    private boolean getSignatureImg(HttpServletRequest request) throws IOException{
        
        String appPath = request.getServletContext().getRealPath("");
        String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
        String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "errands"
                    + File.separator + "transSignature" + File.separator;
        
        String imgData = request.getParameter("signatureImg");
        System.out.println(imgData);
        if(imgData.equals("no")){
            return false;
        }else{    
            String base64Image = imgData.split(",")[1];
            System.out.println(base64Image);
            //byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
            FileOutputStream fileToSave = new FileOutputStream(imageDir + request.getParameter("jobID") + ".png");
            fileToSave.write(imageBytes);
            fileToSave.close();
        
            return true;
        }
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