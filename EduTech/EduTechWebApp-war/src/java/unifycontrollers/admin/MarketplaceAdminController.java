package unifycontrollers.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unifysessionbeans.admin.MarketplaceAdminMgrBeanRemote;

public class MarketplaceAdminController extends HttpServlet {
    @EJB
    private MarketplaceAdminMgrBeanRemote mamr;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "goToViewItemCategoryListing":
                    request.setAttribute("itemCategoryList", (ArrayList)mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "goToViewItemListing":
                    request.setAttribute("itemList", (ArrayList)mamr.viewItemList());
                    pageAction = "ViewItemListing";
                    break;
                case "goToViewItemListingDetails":
                    String itemName = request.getParameter("itemName");
                    String itemSellerID = request.getParameter("itemSellerID");
                    request.setAttribute("itemDetailsVec", mamr.viewItemDetails(itemName, itemSellerID));
                    pageAction = "ViewItemListingDetails";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);       
        }
        catch(Exception ex) {
            log("Exception in MarketplaceAdminController: processRequest()");
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
    public String getServletInfo() { return "Marketplace (Item) Admin Servlet"; }
}