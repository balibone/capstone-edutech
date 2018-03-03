/***************************************************************************************
*   Title:                  UserProfileSysUserController.java
*   Purpose:                SERVLET FOR UNIFY DASHBOARD & PROFILE - SYSUSER (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
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

import unifysessionbeans.systemuser.UserProfileSysUserMgrBeanRemote;

public class UserProfileSysUserController extends HttpServlet {
    @EJB
    private UserProfileSysUserMgrBeanRemote usmr;
    boolean firstVisit = true;
    String username = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "goToUnifyUserAccount":
                    if(firstVisit == true) { 
                        username = request.getParameter("userID");
                        firstVisit = false;
                    }
                    pageAction = "UserAccount";
                    break;
                case "goToMarketplaceTrans":
                    request.setAttribute("itemTransList", (ArrayList) usmr.viewItemTransaction(username));
                    pageAction = "UserItemTransaction";
                    break;
                case "goToViewItemDetailsInModalSYS":
                    long itemID = Long.parseLong(request.getParameter("itemID"));
                    long itemTransID = Long.parseLong(request.getParameter("itemTransID"));
                    request.setAttribute("transItemDetailsSYSVec", usmr.viewTransactionItemDetails(itemID, itemTransID, username));
                    pageAction = "ViewItemDetailsInModalSYS";
                    break;
                case "goToUserProfile":
                    pageAction = "UserProfile";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);       
        }
        catch(Exception ex) {
            log("Exception in UserProfileSysUserController: processRequest()");
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
    public String getServletInfo() { return "User Profile System User Servlet"; }
}