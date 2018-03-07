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

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unifysessionbeans.systemuser.VoicesSysUserMgrBeanRemote;

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
                    break;
                case "goToReviewListing":
                    pageAction = "ReviewListing";
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
        String reviewDescription = request.getParameter("companyDescription");
        String employmentStatus = request.getParameter("employmentStatus");
        String reviewPoster = request.getParameter("username");
        
        //responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
                //reviewPros, reviewCons, reviewRating, reviewDescription, employmentStatus, reviewPoster);
        return responseMessage;
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