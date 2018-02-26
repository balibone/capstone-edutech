/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechcontrollers.admin;

import commoninfrasessionsbeans.admin.SystemAdminMgrBeanRemote;
import edutechsessionbeans.admin.EduTechAdminMgrBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Derian
 */
public class EduTechAdminController extends HttpServlet {

    @EJB
    private EduTechAdminMgrBeanRemote eam;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = null;
        ServletContext context = getServletContext();
        String pageAction = request.getParameter("pageTransit");
        try{
            //instantiate variables used in switch statement
            String id = "";
            ArrayList userInfo = new ArrayList();
            ArrayList semesterInfo = new ArrayList();
            ArrayList moduleInfo = new ArrayList();
            String msg = "";
            boolean success = false;
            
            switch(pageAction){
                case "EduTechAdminDashboard":
                    request.setAttribute("studentCount", sam.getUserCount("student"));
                    request.setAttribute("instructorCount", sam.getUserCount("instructor"));
                    request.setAttribute("moduleCount", eam.getModuleCount());
                    request.setAttribute("semesterCount", eam.getSemesterCount());
                    pageAction = "EduTechAdminDashboard";
                    break;
                case "StudentList":
                    request.setAttribute("userList", sam.getAllStudents());
                    pageAction = "ETAStudentList";
                    break;
                case "ViewStudent":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "ViewUserModal";
                    break;
                case "InstructorList":
                    request.setAttribute("userList", sam.getAllInstructors());
                    pageAction = "ETAInstructorList";
                    break;
                case "ViewInstructor":
                    id = request.getParameter("id");
                    userInfo = sam.getUserInfo(id);
                    request.setAttribute("userInfo", userInfo);
                    pageAction = "ViewUserModal";
                    break;
                case "ModuleList":
                    request.setAttribute("moduleList", eam.getAllModules());
                    pageAction = "ModuleList";
                    break;
                case "ViewModule":
                    id = request.getParameter("id");
                    moduleInfo = eam.getModuleInfo(id);
                    request.setAttribute("moduleInfo", moduleInfo);
                    pageAction = "ViewModuleModal";
                    break;
                case "NewModule":
                    request.setAttribute("semesterList", eam.getAllSemesters());
                    pageAction = "NewModule";
                    break;
                case "createModule":
                    String moduleCode = request.getParameter("moduleCode");
                    String name = request.getParameter("name");
                    Long modularCredit = Long.valueOf(request.getParameter("modularCredit"));
                    String description = request.getParameter("description");
                    Long semID = Long.valueOf(request.getParameter("semID"));
                    try{
                        if(eam.createModule(moduleCode,name,modularCredit,description,semID)){
                            request.setAttribute("msg", "Module successfully created.");
                            request.setAttribute("success", true);
                        }else{
                            request.setAttribute("msg", "Error creating Module.");
                            request.setAttribute("success", false);
                        }
                    }catch(Exception e){
                        request.setAttribute("msg", "Error creating Module. Module Code already exists.");
                        request.setAttribute("success", false);
                        System.out.println(e.getMessage());
                    }
                    request.setAttribute("semesterList", eam.getAllSemesters());
                    pageAction = "NewModule";
                    break;
                case "EditModule":
                    id = request.getParameter("id");
                    moduleInfo = eam.getModuleInfo(id);
                    request.setAttribute("moduleInfo", moduleInfo);
                    pageAction = "EditModule";
                    break;
                case "editModule":
                    id = request.getParameter("id");
                    if(eam.editModule(id,request.getParameter("name"),
                            request.getParameter("modularCredit")
                            ,request.getParameter("description"))){
                        request.setAttribute("moduleInfo", eam.getModuleInfo(id));
                        request.setAttribute("msg", "Module successfully edited.");
                        request.setAttribute("success", true);
                    }else{
                        request.setAttribute("moduleInfo", eam.getModuleInfo(id));
                        request.setAttribute("msg", "Error editing Module.");
                        request.setAttribute("success", false);
                    }
                    pageAction = "EditModule";
                    break;
                case "deleteModule":
                    id = request.getParameter("id");
                    eam.deleteModule(id);
                    request.setAttribute("moduleList", eam.getAllModules());
                    pageAction = "ModuleList";
                    break;
                case "SemesterList":
                    request.setAttribute("semesterList", eam.getAllSemesters());
                    pageAction = "SemesterList";
                    break;
                case "ViewSemester":
                    id = request.getParameter("id");
                    moduleInfo = eam.getModuleInfo(id);
                    request.setAttribute("moduleInfo", moduleInfo);
                    pageAction = "ViewModuleModal";
                    break;
                case "NewSemester":
                    pageAction = "NewSemester";
                    break;
                case "createSemester":
                    if(eam.createSemester(request.getParameter("title"),request.getParameter("startDate"),request.getParameter("endDate"))){
                        request.setAttribute("msg", "Semester successfully created.");
                        request.setAttribute("success", true);
                    }else{
                        request.setAttribute("msg", "Error creating Semester.");
                        request.setAttribute("success", false);
                    }
                    pageAction = "NewSemester";
                    break;
                
                case "EditSemester":
                    id = request.getParameter("id");
                    semesterInfo = eam.getSemesterInfo(id);
                    request.setAttribute("semesterInfo", semesterInfo);
                    pageAction = "EditSemester";
                    break;
                case "editSemester":
                    id = request.getParameter("id");
                    
                    if(eam.editSemester(request.getParameter("title"),request.getParameter("startDate")
                            ,request.getParameter("endDate"),request.getParameter("id"))){
                        request.setAttribute("msg", "Semester successfully edited.");
                        request.setAttribute("success", true);
                        request.setAttribute("semesterInfo", eam.getSemesterInfo(id));
                    }else{
                        request.setAttribute("msg", "Error editing Semester.");
                        request.setAttribute("success", false);
                        request.setAttribute("semesterInfo", eam.getSemesterInfo(id));
                    }
                    pageAction = "EditSemester";
                    break;
                case "deleteSemester":
                    id = request.getParameter("id");
                    eam.deleteSemester(id);
                    request.setAttribute("semesterList", eam.getAllSemesters());
                    pageAction = "SemesterList";
                    break;
            }
            dispatcher = context.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        }catch(Exception e){
            System.out.println("********************Exception in EduTechAdminController!");
            e.printStackTrace();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

}
