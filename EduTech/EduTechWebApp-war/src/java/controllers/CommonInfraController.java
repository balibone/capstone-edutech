package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
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
    String userNRIC = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "loginToSys":
                    String empNRIC = request.getParameter("empNRIC");
                    String empPassword = request.getParameter("empPassword");
                    if(cir.empLogin(empNRIC, empPassword)){
                        userNRIC = empNRIC;
                        request.setAttribute("employeeNRIC", userNRIC);
                        pageAction = "WarehouseDashboard";
                    }
                    else{
                        request.setAttribute("sysMessage", "Incorrect NRIC or password. Please try again.");
                        pageAction = "WarehouseLogin";
                    }   break;
                case "goToLogout":
                    userNRIC = "";
                    pageAction = "WarehouseLogin";
                    break;
                case "goToDashboard":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "WarehouseDashboard";
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
