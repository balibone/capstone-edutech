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
                    pageAction = "ViewCompanyListingSYS";
                    break;
                case "goToNewReviewSYS":
                    String companyImage = request.getParameter("hiddenCompanyImage");
                    String companyName = request.getParameter("hiddenCompanyName");
                    String companyIndustry = request.getParameter("hiddenCompanyIndustry");
                    System.out.println(companyImage);
                    request.setAttribute("reviewedCompanyImage", companyImage);
                    request.setAttribute("reviewedCompanyName", companyName);
                    pageAction = "NewReviewSYS";
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