/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
public class StorefrontController
        extends HttpServlet {

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
            
            // FOR HANDLING THE HIDDEN FIELD
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "goToCheckout":
                    pageAction = "Checkout";
                    break;
                case "goToContactUs":
                    pageAction = "ContactUs";
                    break;
                case "goToErrorPage":
                    pageAction = "ErrorPage";
                    break;
                case "goToHomepage":
                    pageAction = "Homepage";
                    break;
                case "goToProductCategory":
                    pageAction = "ProductCategory";
                    break;
                case "goToProductComparison":
                    pageAction = "ProductComparison";
                    break;
                case "goToProductDetails":
                    pageAction = "ProductDetails";
                    break;
                case "goToProductWishlist":
                    pageAction = "ProductWishlist";
                    break;
                case "goToShoppingCart":
                    pageAction = "ShoppingCart";
                    break;
                case "goToStoreFAQ":
                    pageAction = "StoreFAQ";
                    break;
                case "goToStoreLogin":
                    pageAction = "StoreLogin";
                    break;
                case "goToTermsCondition":
                    pageAction = "TermsCondition";
                    break;
                case "goToTrackOrder":
                    pageAction = "TrackOrder";
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        }catch(Exception ex) {
            log("Exception in WarehouseController: processRequest()");
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

}
