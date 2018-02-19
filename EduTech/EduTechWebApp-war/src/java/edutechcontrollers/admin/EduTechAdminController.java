/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechcontrollers.admin;

import commoninfrasessionsbeans.admin.SystemAdminMgrBeanRemote;
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
            String msg = "";
            boolean success = false;
            
            switch(pageAction){
                case "EduTechAdminDashboard":
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
