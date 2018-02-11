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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class SystemAdminController
        extends HttpServlet {

    @EJB
    private SystemAdminMgrBeanRemote sam;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            System.out.println(pageAction);
            
            //instantiate variables used in switch statement
            String id = "";
            ArrayList userInfo = new ArrayList();
            String msg = "";
            boolean success = false;
            //Convention: EditStudent is for page redirect. editStudent is for database update handling
            switch (pageAction) {
                case "StudentList":
                    request.setAttribute("studentList", sam.getAllStudents());
                    pageAction="StudentList";
                    break;
                case "NewStudent":
                    pageAction="NewStudent";
                    break;
                case "ViewStudent":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction="ViewStudent";
                    break;
                case "EditStudent":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction="EditStudent";
                    break;
                case "createStudent"://create new student
                    success = processNewUser(request,response, "student");//pass request to helper method for parsing & store success boolean
                    msg = "";//confirmation msg
                    if (success){
                        msg = "User created successfully.";
                    }else{
                        msg = "Failed to create user. Please try again.";
                    }
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    
                    pageAction="NewStudent";//response is same page. 
                    break;
                case "editStudent"://create new student
                    success = processEditUser(request,response);//pass request to helper method for parsing & store success boolean
                    msg = "";//confirmation msg
                    if (success){
                        msg = "User edited successfully.";
                    }else{
                        msg = "Failed to edit user. Please try again.";
                    }
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    
                    pageAction="EditStudent";//response is same page. 
                    break;              
                case "deleteStudent":
                    sam.deleteUser(request.getParameter("id"));
                    request.setAttribute("studentList", sam.getAllStudents());
                    pageAction="StudentList";
                    break;
                case "InstructorList":
                    ArrayList<ArrayList> instructorList = sam.getAllInstructors();
                    request.setAttribute("instructorList", instructorList);
                    pageAction="InstructorList";
                    break;
                case "NewInstructor":
                    pageAction="NewInstructor";
                    break;
                case "ViewInstructor":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction="ViewInstructor";
                    break;
                case "EditInstructor":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction="EditInstructor";
                    break;
                case "createInstructor"://create new instructor
                    boolean successIndex = processNewUser(request,response, "instructor");//pass request to helper method for parsing & store success boolean
                    String message = "";//confirmation msg
                    if (successIndex){
                        message = "User created successfully.";
                    }else{
                        message = "Failed to create user. Please try again.";
                    }
                    request.setAttribute("success", successIndex);//success boolean
                    request.setAttribute("msg", message);//plug in confirmation         
                    pageAction="NewInstructor";//response is same page. 
                    break;
                case "editInstructor":
                    success = processEditUser(request,response);//pass request to helper method for parsing & store success boolean
                    msg = "";//confirmation msg
                    if (success){
                        msg = "User edited successfully.";
                    }else{
                        msg = "Failed to edit user. Please try again.";
                    }
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    pageAction="EditInstructor";
                    break;
                case "deleteInstructor":
                    sam.deleteUser(request.getParameter("id"));
                    request.setAttribute("instructorList", sam.getAllInstructors());
                    pageAction="InstructorList";
                    break; 
                case "AllAdminList":
                    ArrayList<ArrayList> adminList = sam.getAllAdmins();
                    request.setAttribute("adminList", adminList);
                    pageAction="AllAdminList";
                    break;
                case "NewAdmin":
                    pageAction="NewAdmin";
                    break;
                case "ViewAdmin":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction="ViewAdmin";
                    break;
                case "EditAdmin":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction="EditAdmin";
                    break;
                case "createAdmin"://create new student
                    boolean createAdminStatus = processNewUser(request,response, "admin");//pass request to helper method for parsing & store success boolean
                    String adminMsg = "";//confirmation msg
                    if (createAdminStatus){
                        adminMsg = "User created successfully.";
                    }else{
                        adminMsg = "Failed to create user. Please try again.";
                    }
                    request.setAttribute("success", createAdminStatus);//success boolean
                    request.setAttribute("msg", adminMsg);//plug in confirmation
                    
                    pageAction="NewAdmin";//response is same page. 
                    break;
                case "editAdmin":
                    success = processEditUser(request,response);//pass request to helper method for parsing & store success boolean
                    msg = "";//confirmation msg
                    if (success){
                        msg = "User edited successfully.";
                    }else{
                        msg = "Failed to edit user. Please try again.";
                    }
                    request.setAttribute("success", success);//success boolean
                    request.setAttribute("msg", msg);//plug in confirmation
                    pageAction="EditAdmin";
                    break;
                case "deleteAdmin":
                    id = request.getParameter("id");
                    sam.deleteUser(id);
                    request.setAttribute("adminList", sam.getAllAdmins());
                    pageAction="AllAdminList";
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {
        processRequest(request,
                response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException,
            IOException {
        processRequest(request,
                response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private boolean processNewUser(HttpServletRequest request, HttpServletResponse response, String userType) throws IOException, ServletException{
        //Save image to offline folder. 
        // Create path components to save the file
        String appPath = request.getServletContext().getRealPath("");
        String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
        //Directory path to save image to
        String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                + "uploads" + File.separator + "commoninfrastructure" + File.separator + "admin" + File.separator + "images"
                + File.separator;
        Part imagePart = request.getPart("profileImage");
        final String fileName;
        fileName= getFileName(imagePart);
 
        FileOutputStream out = null;
        InputStream fileContent = null;
        //final PrintWriter writer = response.getWriter();
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
            //writer.println("New file " + fileName + " created at " + imageDir);
        } catch (FileNotFoundException fne) {
            /*writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());
            
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});*/
            System.out.println("***********FILE NOT FOUND EXCEPTION");
                fne.printStackTrace();
        }  
        String salutation = request.getParameter("salutation");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(userType.equals("admin")) {
            String adminType = request.getParameter("adminType");
            return sam.createNewAdmin(salutation,firstName,lastName,username, password, fileName,adminType);
        } else if(userType.equals("student")){ 
            return sam.createNewStudent(salutation,firstName,lastName,username, password, fileName);
        } else {
            return sam.createNewInstructor(salutation, firstName, lastName, username, password, fileName);
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
            Part imagePart = request.getPart("profileImage");
            fileName= getFileName(imagePart);
            
            FileOutputStream out = null;
            InputStream fileContent = null;
            //final PrintWriter writer = response.getWriter();
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
            }
        }
        
        String salutation = request.getParameter("salutation");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");       
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return sam.editUser(username, salutation, firstName, lastName, password, userType, fileName);   
    }
}
