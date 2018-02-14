package commoninfracontrollers;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                    System.out.println(enteredUsername);
                    String userPassword = request.getParameter("userPassword");
                    if(cir.empLogin(enteredUsername, userPassword)) {
                        // Creates new Cookie for the logged in user. 
                        Cookie newCookie = new Cookie("username",enteredUsername);
                        // non-persistent cookie that will be deleted when browser closes.
                        newCookie.setMaxAge(-1);
                        //adds cookie to response
                        response.addCookie(newCookie);
                        /*
                            Passes username as string to landing page as there is no cookie to be used for checking yet.
                            Cookie is being returned in the same response object as the jsp file.
                            Cookie can only be used for session management after landing page.
                        */
                        username = enteredUsername;
                        request.setAttribute("startUsername", username);
                        pageAction = "LandingPage";
                    }
                    else{
                        request.setAttribute("sysMessage", "Incorrect username or password. Please try again.");
                        pageAction = "IntegratedSPLogin";                   
                    }
                    break;
                case "goToLogout":
                    Cookie toBeDeleted = new Cookie("username", "");    // overwrite existing cookie  
                    toBeDeleted.setMaxAge(0);                           // changing the maximum age to 0 seconds. AKA deleting cookie  
                    response.addCookie(toBeDeleted);                    // update this cookie by adding it to response. 
                    String sessionInvalid = request.getParameter("sessionInvalid");
                    if(sessionInvalid != null && sessionInvalid.equals("true")){
                        request.setAttribute("sysMessage", "Your session has expired, please login again.");
                    }
                    pageAction = "IntegratedSPLogin";
                    break;
                case "goToCommonLanding":
                    request.setAttribute("username", username);
                    pageAction = "LandingPage";
                    break;
                case "goToSystemAdmin":
                    request.setAttribute("username", username);
                    pageAction = "SystemAdminDashboard";
                    break;
                case "goToUnifyAdmin":
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
