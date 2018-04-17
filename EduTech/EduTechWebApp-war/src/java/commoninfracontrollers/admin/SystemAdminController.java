/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commoninfracontrollers.admin;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import commoninfrasessionsbeans.admin.SystemAdminMgrBeanRemote;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class SystemAdminController extends HttpServlet {
    @EJB
    private SystemAdminMgrBeanRemote sam;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            if(pageAction == null){
                pageAction = "placeHolderAction";
            }
            String loggedInUsername = "";

            //instantiate variables used in switch statement
            String id = "";
            ArrayList userInfo = new ArrayList();
            String msg = "";
            boolean success = false;
            //Convention: EditStudent is for page redirect. editStudent is for database update handling
            switch (pageAction) {
                case "SystemAdminDashboard":
                    request.setAttribute("studentCount", sam.getUserCount("student"));
                    request.setAttribute("instructorCount", sam.getUserCount("instructor"));
                    request.setAttribute("edutechCount", sam.getUserCount("edutechadmin"));
                    request.setAttribute("unifyCount", sam.getUserCount("unifyadmin"));
                    request.setAttribute("dualCount", sam.getUserCount("dualadmin"));
                    request.setAttribute("superCount", sam.getUserCount("superadmin"));
                    pageAction = "SystemAdminDashboard";
                    break;
                case "StudentList":
                    request.setAttribute("studentList", sam.getAllStudents());
                    pageAction = "StudentList";
                    break;
                case "NewStudent":
                    pageAction = "NewStudent";
                    break;
                case "ViewStudent":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "ViewStudent";
                    break;
                case "EditStudent":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "EditStudent";
                    break;
                case "createStudent"://create new student
                    try{
                        success = processNewUser(request,response, "student");//pass request to helper method for parsing & store success boolean
                        if (success){
                            msg = "User account created successfully.";
                        } else{
                            msg = "Failed to create user account. Please make sure email & contact number are valid and try again.";
                        }
                    }catch(Exception e){
                        msg = "User account with this username already exists. Please pick a different username.";
                    }                    
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    request.setAttribute("studentList", sam.getAllStudents());
                    pageAction = "StudentList";
                    break;
                case "editStudent"://create new student
                    id = request.getParameter("id");
                    success = processEditUser(request,response);//pass request to helper method for parsing & store success boolean
                    msg = "";//confirmation msg
                    if (success){
                        msg = "User profile edited successfully.";
                    }else{
                        msg = "Failed to edit user profile. Please try again.";
                    }
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction="EditStudent";//response is same page. 
                    break;              
                case "toggleStudent":
                    sam.toggleUserStatus(request.getParameter("id"));
                    response.sendRedirect("SystemAdmin?pageTransit=StudentList");
                    break;
                case "InstructorList":
                    ArrayList<ArrayList> instructorList = sam.getAllInstructors();
                    request.setAttribute("instructorList", instructorList);
                    pageAction="InstructorList";
                    break;
                case "NewInstructor":
                    pageAction = "NewInstructor";
                    break;
                case "ViewInstructor":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "ViewInstructor";
                    break;
                case "EditInstructor":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "EditInstructor";
                    break;
                case "createInstructor":
                    try{
                        success = processNewUser(request,response, "instructor");//pass request to helper method for parsing & store success boolean
                        if (success){
                            msg = "Instructor account created successfully.";
                        } else{
                            msg = "Failed to create instructor account. Please make sure email & contact number are valid and try again.";
                        }
                    }catch(Exception e){
                        msg = "Instructor account with this username already exists. Please pick a different username.";
                    }                    
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation         
                    request.setAttribute("instructorList", sam.getAllInstructors());
                    pageAction="InstructorList";
                    break;
                case "editInstructor":
                    id = request.getParameter("id");
                    
                    success = processEditUser(request,response);//pass request to helper method for parsing & store success boolean
                    msg = "";
                    if (success){
                        msg = "Instructor profile edited successfully.";
                    }else{
                        msg = "Failed to edit instructor profile. Please try again.";
                    }
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "EditInstructor";
                    break;
                case "toggleInstructor":
                    sam.toggleUserStatus(request.getParameter("id"));
                    response.sendRedirect("SystemAdmin?pageTransit=InstructorList");
                    break; 
                case "AllAdminList":
                    ArrayList<ArrayList> adminList = sam.getAllAdmins();
                    request.setAttribute("adminList", adminList);
                    pageAction = "AllAdminList";
                    break;
                case "NewAdmin":
                    pageAction = "NewAdmin";
                    break;
                case "ViewAdmin":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "ViewAdmin";
                    break;
                case "EditAdmin":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "EditAdmin";
                    break;
                case "createAdmin"://create new student
                    try{
                        success = processNewUser(request,response, "admin");//pass request to helper method for parsing & store success boolean
                        if (success){
                            msg = "Administrator account created successfully.";
                        } else{
                            msg = "Failed to create administrator account. Please make sure email & contact number are valid and try again.";
                        }
                    }catch(Exception e){
                        msg = "Administrator account with this username already exists. Please pick a different username.";
                    }                    
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    request.setAttribute("adminList", sam.getAllAdmins());
                    pageAction = "AllAdminList";
                    break;
                case "editAdmin":
                    id = request.getParameter("id");
                    success = processEditUser(request,response);//pass request to helper method for parsing & store success boolean
                    msg = "";
                    if (success){
                        msg = "Administrator profile edited successfully.";
                    }else{
                        msg = "Failed to edit administrator profile. Please try again.";
                    }
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "EditAdmin";
                    break;
                case "toggleAdmin":
                    id = request.getParameter("id");
                    String prevStatus = request.getParameter("prevStatus");
                    //if curr user is getting toggled to inactive, send him to logout.
                    Cookie[] reqCookies = request.getCookies();
                    if(reqCookies != null){
                        for(Cookie c : reqCookies){
                            //if username cookie is valid, extract cookie value.
                            if("username".equals(c.getName()) && !c.getValue().equals("")){
                                loggedInUsername = c.getValue();
                                if(id.equals(loggedInUsername) && prevStatus.equalsIgnoreCase("active")){
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
                                }
                            }
                        }
                    }
                    
                    sam.toggleUserStatus(id);
                    response.sendRedirect("SystemAdmin?pageTransit=AllAdminList");
                    break;
            }
            if(pageAction != null && pageAction.equals("placeHolderAction")){
                dispatcher = servletContext.getNamedDispatcher(pageAction);
                if(dispatcher!=null)
                    dispatcher.forward(request, response);
                else//if dispatcher is null, means pageAction refers to an invalid jsp. 
                    response.sendError(404);
            } 
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

    private boolean processNewUser(HttpServletRequest request, HttpServletResponse response, String userType) throws IOException, ServletException{
        String appPath = request.getServletContext().getRealPath("");
        String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
        //Directory path to save image to
        String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                + "uploads" + File.separator + "commoninfrastructure" + File.separator + "admin" + File.separator + "images"
                + File.separator;
        //creates directory path if not present.
        Files.createDirectories(Paths.get(imageDir));
        
        Part imagePart = request.getPart("profileImage");
        final String fileName;
        //so that file name is unique
        fileName= LocalDateTime.now().withNano(0).toString().replaceAll("-", "").replaceAll(":", "")+"qup"+getFileName(imagePart);
 
        FileOutputStream out = null;
        InputStream fileContent = null;
        try {
            out = new FileOutputStream(new File(imageDir + File.separator + fileName));
            fileContent = imagePart.getInputStream();
            
            int bytesRead = 0;
            final byte[] bytes = new byte[1024];
            //read image bytes from input stream until finish.
            while ((bytesRead = fileContent.read(bytes)) != -1) {
                
                out.write(bytes, 0, bytesRead);
            }
        } catch (FileNotFoundException fne) {
            System.out.println("***********FILE NOT FOUND EXCEPTION");
                fne.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
        } 
        String salutation = request.getParameter("salutation");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String contactNum = request.getParameter("contactNum");
        int localPort = request.getLocalPort();
        if(userType.equals("admin")) {
            String adminType = request.getParameter("adminType");
            return sam.createNewAdmin(localPort,salutation,firstName,lastName, email, contactNum,username, fileName,adminType);
        } else if(userType.equals("student")){ 
            return sam.createNewStudent(localPort,salutation,firstName,lastName,email, contactNum, username, fileName);
        } else {
            return sam.createNewInstructor(localPort,salutation, firstName, lastName, email, contactNum, username, fileName);
        }
    }
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
    private boolean processEditUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //pull submitted user type
        String newType = request.getParameter("type");
        //pull original user type
        String originalType = request.getParameter("originalType");
        //instantiate user type string to be used for DB persisting
        String userType = "";
        //if type has been changed, take that new type for persisting
        if(!newType.equalsIgnoreCase(originalType)){
            userType=newType;
        }else{
            userType=originalType;
        }
        //pull original file name 
        String fileName = request.getParameter("originalImage");
        //if image has been replaced, upload new file and change file name. 
        if(request.getParameter("imageReplacement").equalsIgnoreCase("yes")){           
            // Save image to offline folder.
            // Create path components to save the file
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            //Directory path to save image to
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "commoninfrastructure" + File.separator + "admin" + File.separator + "images"
                    + File.separator;
            //creates directory path if not present.
            Files.createDirectories(Paths.get(imageDir));
            Part imagePart = request.getPart("profileImage");
            //so that file name is unique
            fileName= LocalDateTime.now().withNano(0).toString().replaceAll("-", "").replaceAll(":", "")+"qup"+getFileName(imagePart);
            
            FileOutputStream out = null;
            InputStream fileContent = null;
            try {
                out = new FileOutputStream(new File(imageDir + File.separator
                        + fileName));
                fileContent = imagePart.getInputStream();
                
                int bytesRead = 0;
                final byte[] bytes = new byte[1024];
                //read image bytes from input stream until finish.
                while ((bytesRead = fileContent.read(bytes)) != -1) {
                    //write image bytes to output stream incrementally, until bytesRead = total file size --> means full image written.
                    out.write(bytes, 0, bytesRead);
                }
            } catch (FileNotFoundException fne) {
                System.out.println("***********FILE NOT FOUND EXCEPTION");
                fne.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (fileContent != null) {
                    fileContent.close();
                }
            }
        }
        
        String salutation = request.getParameter("salutation");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");       
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String contactNum = request.getParameter("contactNum");
        return sam.editUser(username, salutation, firstName, lastName, userType, fileName, email, contactNum);   
    }
}
