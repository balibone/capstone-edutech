package unifycontrollers.systemuser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import unifysessionbeans.systemuser.MarketplaceSysUserMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class MarketplaceSysUserController extends HttpServlet {
    @EJB
    private MarketplaceSysUserMgrBeanRemote msmr;
    String responseMessage = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {
                case "goToNewItemListingSYS":
                    request.setAttribute("itemCategoryListSYS", (ArrayList) msmr.viewItemCategoryList());
                    pageAction = "NewItemListingSYS";
                    break;
                case "createItemListingSYS":
                    responseMessage = createItemListing(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemCategoryListSYS", (ArrayList) msmr.viewItemCategoryList());
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
    
    /* KEY METHODS */
    private String createItemListing(HttpServletRequest request) {
        String itemImagefileName = "";
        try {
            Part filePart = request.getPart("itemImage");
            itemImagefileName = (String) getFileName(filePart);

            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "marketplace"
                    + File.separator + "item" + File.separator;

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(imageDir + itemImagefileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);

                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                itemImagefileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            itemImagefileName = "";
        }
        
        String itemName = request.getParameter("itemName");
        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
        String itemCondition = request.getParameter("itemCondition");
        String itemDescription = request.getParameter("itemDescription");
        long categoryID = Long.parseLong(request.getParameter("hiddenCategoryID"));
        String username = request.getParameter("username");
        String tradeLocation = request.getParameter("tradeLocation");
        String tradeLat = request.getParameter("hiddenTradeLat");
        String tradeLong = request.getParameter("hiddenTradeLong");
        String tradeInformation = request.getParameter("tradeInformation");
        
        return msmr.createItemListing(itemName, itemPrice, itemCondition, itemDescription, itemImagefileName, 
                categoryID, username, tradeLocation, tradeLat, tradeLong, tradeInformation);
    }
    
    /* MISCELLANEOUS METHODS */
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
}