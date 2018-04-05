/***************************************************************************************
*   Title:                  VoicesSysUserController.java
*   Purpose:                SERVLET FOR UNIFY COMPANY REVIEW - SYSUSER (EDUBOX)
*   Created & Modified By:  ZHU XINYI
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
import java.sql.Array;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import unifysessionbeans.systemuser.VoicesSysUserMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class VoicesSysUserController extends HttpServlet {
    @EJB
    private VoicesSysUserMgrBeanRemote vsmr;
    String responseMessage = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            System.out.println(pageAction);
            
            switch (pageAction) {
                case "goToViewCompanyListingSYS":
                    request.setAttribute("companyListSYS", (ArrayList) vsmr.viewCompanyList());
                    request.setAttribute("industryListSYS", (ArrayList) vsmr.populateCompanyIndustry());
                    request.setAttribute("industryStrSYS", vsmr.populateCompanyIndustryString());
                    pageAction = "ViewCompanyListingSYS";
                    break;
                case "goToNewReviewSYS":
                    String companyImage = request.getParameter("hiddenCompanyImage");
                    String companyName = request.getParameter("hiddenCompanyName");
                    String companyIndustry = request.getParameter("hiddenCompanyIndustry");
                    request.setAttribute("reviewedCompanyImage", companyImage);
                    request.setAttribute("reviewedCompanyName", companyName);
                    request.setAttribute("reviewedCompanyIndustry", companyIndustry);
                    pageAction = "NewReviewSYS";
                    break;
                case "createCompanyReviewSYS":
                    responseMessage = createCompanyReview(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    request.setAttribute("companyListSYS", (ArrayList) vsmr.viewCompanyList());
                    request.setAttribute("industryListSYS", (ArrayList) vsmr.populateCompanyIndustry());
                    pageAction = "ViewCompanyListingSYS";
                    break;
                case "goToViewCompanyDetailsSYS":
                    long companyID = Long.parseLong(request.getParameter("hiddenCompanyID"));
                    String username = request.getParameter("hiddenUsername");
                    request.setAttribute("companyDetailsSYS", vsmr.viewCompanyDetails(companyID));
                    request.setAttribute("associatedReviewListSYS", vsmr.viewAssociatedReviewList(companyID, username));
                    request.setAttribute("companyListInIndustrySYS", vsmr.viewCompanyInSameIndustry(companyID));
                    pageAction="ViewCompanyDetailsSYS";
                    break;
                case "goToViewReviewListSYS":
                    long companyID_ = Long.parseLong(request.getParameter("hiddenCompanyID"));
                    String username_ = request.getParameter("hiddenUsername");
                    String type = request.getParameter("type");
                    request.setAttribute("companyDetailsSYS", vsmr.viewCompanyDetails(companyID_));
                    request.setAttribute("associatedReviewListSYS", vsmr.viewAssociatedReviewList(companyID_, username_));
                    request.setAttribute("companyListInIndustrySYS", vsmr.viewCompanyInSameIndustry(companyID_));
                    request.setAttribute("tabType", type);
                    pageAction="ViewCompanyDetailsSYS";
                    break;
                case "goToNewCompanyRequestSYS":
                    request.setAttribute("industryStrSYS", vsmr.populateCompanyIndustryString());
                    pageAction="NewCompanyRequestSYS";
                    break;
                case "createRequestSYS":
                    responseMessage = createCompanyRequest(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    request.setAttribute("companyListSYS", (ArrayList) vsmr.viewCompanyList());
                    request.setAttribute("industryListSYS", (ArrayList) vsmr.populateCompanyIndustry());
                    pageAction = "ViewCompanyListingSYS";
                    break;
                case "goToNewReviewReportSYS":
                    String reviewReporter = request.getParameter("hiddenReviewReporter");
                    long hiddenReviewID = Long.parseLong(request.getParameter("hiddenReviewID"));
                    request.setAttribute("hiddenCompanyID", request.getParameter("hiddenCompanyID"));
                    request.setAttribute("hiddenReviewID", request.getParameter("hiddenReviewID"));
                    request.setAttribute("hiddenReviewPoster", request.getParameter("hiddenReviewPoster"));
                    request.setAttribute("hiddenRequest", vsmr.lookupReviewReport(reviewReporter, hiddenReviewID));
                    pageAction = "NewReviewReportSYS";
                    break;
                case "createReviewReportSYS":
                    responseMessage = createReviewReport(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    long returnCompanyID = Long.parseLong(request.getParameter("hiddenCompanyID"));
                    String returnUsername = request.getParameter("username");
                    request.setAttribute("companyDetailsSYS", vsmr.viewCompanyDetails(returnCompanyID));
                    request.setAttribute("associatedReviewListSYS", vsmr.viewAssociatedReviewList(returnCompanyID, returnUsername));
                    request.setAttribute("companyListInIndustrySYS", vsmr.viewCompanyInSameIndustry(returnCompanyID));
                    pageAction="ViewCompanyDetailsSYS";
                    break;
                case "likeReviewListingSYS":
                    long reviewIDHid = Long.parseLong(request.getParameter("reviewIDHid"));
                    String usernameHid = request.getParameter("usernameHid");
                    
                    responseMessage = vsmr.likeUnlikeReview(reviewIDHid, usernameHid);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToNewResumeSYS":
                    pageAction = "NewResumeSYS";
                    break;
                case "createResumeSYS":
                    responseMessage = createResume(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    pageAction = "NewResumeSYS";
                    break;
                case "goToReviewDetails":
                    pageAction = "ReviewDetails";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);       
        }
        catch(Exception ex) {
            log("Exception in VoicesSysUserController: processRequest()");
            ex.printStackTrace();
        }
    
    }
    
    private String createCompanyReview(HttpServletRequest request) {
        String companyIndustry = request.getParameter("hiddenCategoryName");
        String companyName = request.getParameter("hiddenCompanyName");
        String reviewTitle = request.getParameter("reviewTitle");
        String reviewPros = request.getParameter("companyPros");
        String reviewCons = request.getParameter("companyCons");
        String reviewRating = request.getParameter("companyRating");
        String employmentStatus = request.getParameter("employmentStatus");
        String salaryRange = request.getParameter("salaryRange");
        String reviewPoster = request.getParameter("username");
        
        System.out.println(companyName + "     " + employmentStatus + "    " + salaryRange);
        
        responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
                reviewPros, reviewCons, reviewRating, employmentStatus, salaryRange, reviewPoster);
        return responseMessage;
    }
    
    private String createCompanyRequest(HttpServletRequest request) {
        String companyIndustry = request.getParameter("companyIndustry");
        String otherIndustry = request.getParameter("otherIndustry");
        String requestCompany = request.getParameter("requestCompany");
        String requestComment = request.getParameter("requestComment");
        String requestPoster = request.getParameter("username");
        
        if(companyIndustry.equals("otherIndustry")) {
            companyIndustry = otherIndustry;
        }
        
        responseMessage = vsmr.createCompanyRequest(requestCompany, companyIndustry, requestComment, requestPoster);
        return responseMessage;
    }
    
    private String createReviewReport(HttpServletRequest request) {
        String reporter = request.getParameter("username");
        String reportDescription = request.getParameter("reportDescription");
        String reviewPoster = request.getParameter("hiddenReviewPoster");
        String reviewID = request.getParameter("hiddenReviewID");
        
        responseMessage = vsmr.createReviewReport(reviewID, reviewPoster, reportDescription, reporter);
        return responseMessage;
    }
    
    private String createResume(HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("userImage");
            fileName = (String) getFileName(filePart);
            if(fileName.contains("\\")) {
                fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
            }
                    
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "voices"
                    + File.separator + "resume" + File.separator;
            
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
        
        String userFullName = request.getParameter("userFullName");
        String contactNum = request.getParameter("contactNum");
        String emailAddr = request.getParameter("emailAddr");
        String postalAddr = request.getParameter("postalAddr");
        
        String workExpr = request.getParameter("workExprList");
        String[] workExprList = workExpr.split(",");
        String eduExpr = request.getParameter("eduExprList");
        String[] eduExprList = eduExpr.split(",");
        String proExpr = request.getParameter("proExprList");
        String[] proExprList = proExpr.split(",");
        String skill = request.getParameter("skillList");
        String[] skillList = skill.split(",");
        
        String username = request.getParameter("username");
        
        responseMessage = vsmr.createResume(userFullName, contactNum, emailAddr, postalAddr, workExprList, eduExprList, proExprList, skillList, fileName, username);
        return responseMessage;
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
    public String getServletInfo() { return "Voices (Shout) System User Servlet"; }
}