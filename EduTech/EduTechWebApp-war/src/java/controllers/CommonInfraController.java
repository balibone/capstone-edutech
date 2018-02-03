package controllers;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbeans.CommonInfraMgrBeanRemote;

public class CommonInfraController extends HttpServlet {
    @EJB
    private CommonInfraMgrBeanRemote cir;
    String emailID = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "loginToSys":
                    String userEmail = request.getParameter("userEmail");
                    String userPassword = request.getParameter("userPassword");
                    if(cir.empLogin(userEmail, userPassword)){
                        emailID = userEmail;
                        request.setAttribute("emailID", userEmail);
                        pageAction = "IntegratedSPLanding";
                    }
                    else{
                        request.setAttribute("sysMessage", "Incorrect email or password. Please try again.");
                        pageAction = "IntegratedSPLogin";
                    }   break;
                case "goToLogout":
                    emailID = "";
                    pageAction = "IntegratedSPLogin";
                    break;
                case "goToCommonLanding":
                    request.setAttribute("emailID", emailID);
                    pageAction = "IntegratedSPLanding";
                    break;
                case "goToSystemAdmin":
                    request.setAttribute("emailID", emailID);
                    pageAction = "SystemAdminDashboard";
                    break;
                case "goToUnifyAdmin":
                    request.setAttribute("emailID", emailID);
                    pageAction = "AdminDashboard";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);       
        }
        catch(Exception ex) {
            log("Exception in CommonInfraController: processRequest()");
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
    public String getServletInfo() { return "Common Infrastructure Servlet"; }
}
