package unifycontrollers.systemuser;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unifysessionbeans.systemuser.MarketplaceSysUserMgrBeanRemote;

public class MarketplaceSysUserController extends HttpServlet {
    @EJB
    private MarketplaceSysUserMgrBeanRemote msmr;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "goToNewItemListingSYS":
                    pageAction = "NewItemListingSYS";
                    break;
                case "goToViewItemListingSYS":
                    request.setAttribute("itemListSYS", (ArrayList) msmr.viewItemList());
                    pageAction = "ViewItemListingSYS";
                    break;
                case "goToViewItemDetailsSYS":
                    String hiddenCategoryName = request.getParameter("hiddenCategoryName");
                    long hiddenItemID = Long.parseLong(request.getParameter("hiddenItemID"));
                    request.setAttribute("assocCategoryItemListSYS", (ArrayList) msmr.viewAssocCategoryItemList(hiddenCategoryName, hiddenItemID));
                    request.setAttribute("itemDetailsSYSVec", msmr.viewItemDetails(hiddenItemID));
                    pageAction = "ViewItemDetailsSYS";
                    break;
                case "goToViewItemDetailsModalSYS":
                    long itemID = Long.parseLong(request.getParameter("itemID"));
                    request.setAttribute("itemDetailsSYSVec", msmr.viewItemDetails(itemID));
                    pageAction = "ViewItemDetailsModalSYS";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            log("Exception in MarketplaceSysUserController: processRequest()");
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
    public String getServletInfo() { return "Marketplace (Item) System User Servlet"; }
}