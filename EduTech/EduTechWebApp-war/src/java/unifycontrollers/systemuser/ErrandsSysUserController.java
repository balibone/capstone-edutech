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
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import javax.servlet.http.Part;

import unifysessionbeans.systemuser.ErrandsSysUserMgrBeanRemote;

public class ErrandsSysUserController extends HttpServlet {
    @EJB
    private ErrandsSysUserMgrBeanRemote esmr;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String responseMessage = "";
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "goToViewJobListingSYS":
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewJobList());
                    pageAction = "ViewJobListingSYS";
                    break;
                case "goToJobDetails":
                    pageAction = "JobDetails";
                    break;
                case "goToNewJobListingSYS":
                    request.setAttribute("jobCategoryListSYS", (ArrayList)esmr.viewJobCategoryList());
                    pageAction = "NewJobListingSYS";
                    break;
                case "createJobListingSYS":
                    responseMessage = createJobListing(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("jobCategoryListSYS", (ArrayList) esmr.viewJobCategoryList());
                    pageAction = "NewJobListingSYS";
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
        
        String jobTitle = request.getParameter("jobTitle");
        String jobRateType = (String)request.getParameter("jobRateType");
        double jobRate = Double.parseDouble(request.getParameter("jobRate"));
        double jobDuration = Double.parseDouble(request.getParameter("jobDuration"));
        String jobDescription = request.getParameter("jobDescription");
        long categoryID = Long.parseLong(request.getParameter("hiddenCategoryID"));
        String username = request.getParameter("username");
        String startLocation = request.getParameter("startLocation");
        String startLat = (request.getParameter("hiddenStartLat"));
        String startLong = (request.getParameter("hiddenStartLong"));
        String endLocation = request.getParameter("endLocation");
        String endLat = (request.getParameter("hiddenEndLat"));
        String endLong = (request.getParameter("hiddenEndLong"));
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date jobWorkDate = new Date();
        try{
            jobWorkDate = df.parse(request.getParameter("workDate"));
            } catch (ParseException e) {
            e.printStackTrace();
            }
        
        return esmr.createJobListing(jobTitle, jobRateType, jobRate, jobDuration, jobDescription, jobWorkDate, jobImagefileName, 
                categoryID, username, startLocation, startLat, startLong, endLocation, endLat, endLong);
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
}