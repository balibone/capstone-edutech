package commoninfracontrollers;

import commoninfrasessionsbeans.admin.SystemAdminMgrBeanRemote;
import java.io.IOException;
import java.util.ArrayList;
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
    @EJB
    private SystemAdminMgrBeanRemote sam;
    
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
                        //Insert user type data into "usertype" cookie using systemadminbean's get userinfo method
                        ArrayList userInfo = sam.getUserInfo(enteredUsername);
                        String userType = (String) userInfo.get(6);
                        Cookie userTypeCookie = new Cookie("userType", userType);
                        //adds cookies to response
                        response.addCookie(newCookie);
                        response.addCookie(userTypeCookie);
                        /*
                            Passes username as string to landing page as there is no cookie to be used for checking yet.
                            Cookie is being returned in the same response object as the jsp file.
                            Cookie can only be used for session management after landing page.
                        */
                        request.setAttribute("startUsername", enteredUsername);
                        request.setAttribute("userType", userType);
                        pageAction = "LandingPage";
                    }
                    else{
                        request.setAttribute("sysMessage", "Incorrect username or password. Please try again.");
                        pageAction = "IntegratedSPLogin";                   
                    }
                    break;
                case "goToLogout":
                    //Delete all of client's cookies 
                    //Delete username cookie
                    Cookie username=new Cookie("username","");//overwrite existing cookie  
                    username.setMaxAge(0);//changing the maximum age to 0 seconds. AKA deleting cookie  
                    response.addCookie(username);//update this cookie by adding it to response. 
                    //Delete userType cookie
                    Cookie userType=new Cookie("userType","");//overwrite existing cookie  
                    userType.setMaxAge(0);//changing the maximum age to 0 seconds. AKA deleting cookie  
                    response.addCookie(userType);//update this cookie by adding it to response.
                    
                    String sessionInvalid = request.getParameter("sessionInvalid");
                    if(sessionInvalid!=null && sessionInvalid.equals("true")){
                        request.setAttribute("sysMessage", "Invalid session, please login again.");
                    }
                    pageAction = "IntegratedSPLogin";
                    break;
                case "goToCommonLanding":
                    pageAction = "LandingPage";
                    break;
                case "goToSystemAdmin":
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
