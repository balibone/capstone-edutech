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

public class StorefrontController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "Storefront Servlet"; }
}
