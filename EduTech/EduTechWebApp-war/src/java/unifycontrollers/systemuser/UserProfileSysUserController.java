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
    boolean firstProfile = true;
    String username = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "goToUserAccount":
                    request.setAttribute("username", username);
                    pageAction = "UserAccount";
                    break;
                case "goToMarketplaceTrans":
                    request.setAttribute("username", username);
                    request.setAttribute("itemTransList", (ArrayList) usmr.viewItemTransaction(username));
                    pageAction = "UserItemTransaction";
                    break;
                case "goToUserProfile":
                    if(firstProfile == true) { 
                        username = request.getParameter("username");
                        firstProfile = false;
                    }
                    request.setAttribute("username", username);
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