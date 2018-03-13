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
    
    String sessionInvalid = "";
    String sessionExpire = "";
    String unauthorised = "";
    boolean success;
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
                        //set cookie path to "localhost/"
                        newCookie.setPath("/");
                        //Hafidz if your react app cant get the cookie then remove this line
                        //newCookie.setHttpOnly(true);
                        
                        //Insert user type data into "usertype" cookie using systemadminbean's get userinfo method
                        ArrayList userInfo = sam.getUserInfo(enteredUsername);
                        String userType = (String) userInfo.get(6);
                        Cookie userTypeCookie = new Cookie("userType", userType);
                        // non-persistent cookie that will be deleted when browser closes.
                        userTypeCookie.setMaxAge(-1);
                        //set cookie path to "localhost/"
                        userTypeCookie.setPath("/");
                        //Hafidz if your react app cant get the cookie then remove this line
                        //userTypeCookie.setHttpOnly(true);
                        
                        //adds cookies to response
                        response.addCookie(newCookie);
                        response.addCookie(userTypeCookie);
                        /*
                        * This is to prevent a "confirm resubmission" or blank page after pressing back from landing page logout.
                        * Now, app correctly shows session invalid message.
                        * There is also no more need to pass request attributes on first log in as response.sendRedirect triggers a fresh http request,
                        * where the cookies will already be available for checking. 
                        * Working as intended, please do not remove :)
                        */
                        response.sendRedirect("CommonInfra?pageTransit=goToCommonLanding");
                    }
                    else{
                        request.setAttribute("sysMessage", "<strong>Incorrect username or password. Please try again.</strong>");
                        pageAction = "IntegratedSPLogin";                   
                    }
                    break;
                case "goToLogout":
                    //Delete all of client's cookies 
                    //Delete username cookie
                    Cookie username = new Cookie("username","");//overwrite existing cookie  
                    username.setPath("/");
                    username.setMaxAge(0);//changing the maximum age to 0 seconds. AKA deleting cookie  
                    response.addCookie(username);//update this cookie by adding it to response. 
                    //Delete userType cookie
                    Cookie userType = new Cookie("userType","");//overwrite existing cookie  
                    userType.setPath("/");
                    userType.setMaxAge(0);//changing the maximum age to 0 seconds. AKA deleting cookie  
                    response.addCookie(userType);//update this cookie by adding it to response.
                    //remove JSESSIONID cookie
                    HttpSession sesh = request.getSession();
                    sesh.invalidate();
                    
                    sessionInvalid = request.getParameter("sessionInvalid");
                    sessionExpire = request.getParameter("sessionExpire");
                    unauthorised = request.getParameter("unauthorised");
                    //if session is invalid
                    if(sessionInvalid!=null && sessionInvalid.equals("true")){
                        response.sendRedirect("CommonInfra?pageTransit=logout&sessionInvalid=true");
                    }
                    //if session expired
                    if(sessionExpire!=null && sessionExpire.equals("true")){
                        response.sendRedirect("CommonInfra?pageTransit=logout&sessionExpire=true");
                    }
                    if(unauthorised!=null && unauthorised.equals("true")){
                        response.sendRedirect("CommonInfra?pageTransit=logout&unauthorised=true");
                    } 
                    //if just normal logout,
                    response.sendRedirect("CommonInfra?pageTransit=logout");
                    break;
                case "logout":
                    sessionInvalid = request.getParameter("sessionInvalid");
                    sessionExpire = request.getParameter("sessionExpire");
                    unauthorised = request.getParameter("unauthorised");

                    if(sessionInvalid!=null && sessionInvalid.equals("true")){
                        request.setAttribute("sysMessage", "<strong>Invalid session. Please login again.</strong>");
                    }
                    if(sessionExpire!=null && sessionExpire.equals("true")){
                        request.setAttribute("sysMessage", "<strong>You session has expired. Please login again.</strong>");
                    }
                    if(unauthorised!=null && unauthorised.equals("true")){
                        request.setAttribute("sysMessage", "<strong>Unauthorised user type. Please contact site administrator.</strong>");
                    }
                    pageAction = "IntegratedSPLogin";
                    break;
                case "goToCommonLanding":
                    unauthorised = request.getParameter("unauthorised");
                    if(unauthorised != null && unauthorised.equals("true")){
                        request.setAttribute("sysMessage", "You are not authorised to access that page.");
                    }
                    //check logged in user's type and respond with correct landing page.
                    Cookie[] cookies = request.getCookies();
                    String uType = null;
                    if(cookies!=null){
                        for(Cookie c : cookies){
                            if(c.getName().equals("userType") && !c.getValue().equals("")){
                                uType = c.getValue();
                            }
                        }
                    }
                    if(uType != null){
                        switch(uType){
                            case "superadmin":
                                pageAction="SystemAdminLanding";
                                break;
                            case "dualadmin":
                                pageAction="DualAdminLanding";
                                break;
                            case "unifyadmin":
                                pageAction="UnifyAdminLanding";
                                break;
                            case "edutechadmin":
                                pageAction="EduTechAdminLanding";
                                break;
                            case "student":
                            case "instructor":
                                pageAction="SystemUserLanding";
                                break;
                            default:
                                //user type invalid. redirect to logout page
                                response.sendRedirect("CommonInfra?pageTransit=goToLogout&unauthorised=true");
                                break;
                        }
                    }else{
                        //user type cookie is invalid. redirect to logout page
                        response.sendRedirect("CommonInfra?pageTransit=goToLogout&unauthorised=true");
                    }
                    break;
                case "goToSystemAdmin":
                    pageAction = "SystemAdminDashboard";
                    break;
                case "goToEdutechAdmin":
                    pageAction = "EduTechAdminDashboard";
                    break;
                case "goToUnifyAdmin":
                    pageAction = "AdminDashboard";
                    break;
                case "registerUser":
                    try{
                        success = cir.createSysUser(request.getParameter("salutation"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("username"), request.getParameter("password"), request.getParameter("email"), request.getParameter("contactNum"));
                        request.setAttribute("successMsg", "Account successfully created."); 
                        pageAction = "IntegratedSPLogin";
                    }catch(Exception e){
                        request.setAttribute("sysMessage", "User account with this username already exists. Please pick a different username."); 
                        pageAction = "Registration";
                    }                    
                    
                    break;
                case "sendResetEmail":
                    success = cir.sendResetEmail(request.getParameter("username"));
                    if(success){
                        request.setAttribute("successMsg", "Recover email has been sent. Please check your inbox."); 
                        pageAction = "IntegratedSPLogin";
                    }else{
                        request.setAttribute("failMsg", "Reset failed. Please check if your username is correct & your recovery email is valid."); 
                        pageAction = "ForgotPassword";
                    }
                    break;
                case "validateToken":
                    success = cir.validateToken(request.getParameter("username"),request.getParameter("token"));
                    if(!success){
                        response.sendError(400, "The token you have supplied is invalid. Please use the links provided in email and ensure token is correct.");
                    }
                    break;
                case "resetPassword":
                    success = cir.resetPassword(request.getParameter("username"),request.getParameter("password"));
                    if(!success){
                        response.sendError(400, "Please choose a different password than your previous one. ");
                    }
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
