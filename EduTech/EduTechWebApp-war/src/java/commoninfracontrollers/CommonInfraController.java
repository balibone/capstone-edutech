package commoninfracontrollers;

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
    String username = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "loginToSys":
                    String enteredUsername = request.getParameter("username");
                    String userPassword = request.getParameter("userPassword");
                    if(cir.empLogin(username, userPassword)){//login successful
                        username = enteredUsername;
                        request.setAttribute("username", username);
                        pageAction = "IntegratedSPLanding";
                    }
                    else{
                        request.setAttribute("sysMessage", "Incorrect email or password. Please try again.");
                        pageAction = "IntegratedSPLogin";
                    }   break;
                case "goToLogout":
                    username = "";
                    pageAction = "IntegratedSPLogin";
                    break;
                case "goToCommonLanding":
                    request.setAttribute("username", username);
                    pageAction = "IntegratedSPLanding";
                    break;
                case "goToSystemAdmin":
                    request.setAttribute("username", username);
                    pageAction = "SystemAdminDashboard";
                    break;
                case "goToUnifyAdmin":
                    request.setAttribute("username", username);
                    pageAction = "AdminDashboard";
                    break;
                case "goToNewLandingPage":
                    pageAction = "LandingPage";
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
