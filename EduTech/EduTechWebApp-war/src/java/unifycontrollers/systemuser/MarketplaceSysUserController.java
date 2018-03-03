/***************************************************************************************
*   Title:                  MarketplaceSysUserController.java
*   Purpose:                SERVLET FOR UNIFY MARKETPLACE - SYSUSER (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

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
                case "goToEditItemListingSYS":
                    long urlItemID = Long.parseLong(request.getParameter("urlItemID"));
                    request.setAttribute("itemDetailsSYSVec", msmr.viewItemDetails(urlItemID));
                    request.setAttribute("itemCategoryListSYS", (ArrayList) msmr.viewItemCategoryList());
                    pageAction = "EditItemListingSYS";
                    break;
                case "editItemListingSYS":
                    responseMessage = editItemListing(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    long itemIDToUpdate = Long.parseLong(request.getParameter("hiddenItemID"));
                    request.setAttribute("itemDetailsSYSVec", msmr.viewItemDetails(itemIDToUpdate));
                    request.setAttribute("itemCategoryListSYS", (ArrayList) msmr.viewItemCategoryList());
                    pageAction = "EditItemListingSYS";
                    break;
                case "deleteItemListingSYS":
                    long itemIDToDelete = Long.parseLong(request.getParameter("hiddenItemID"));
                    responseMessage = msmr.deleteItemListing(itemIDToDelete);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemListSYS", (ArrayList) msmr.viewItemList());
                    pageAction = "ViewItemListingSYS";
                    break;
                case "goToViewItemListingSYS":
                    request.setAttribute("itemListSYS", (ArrayList) msmr.viewItemList());
                    pageAction = "ViewItemListingSYS";
                    break;
                case "goToViewItemDetailsSYS":
                    String hiddenCategoryName = request.getParameter("hiddenCategoryName");
                    long hiddenItemID = Long.parseLong(request.getParameter("hiddenItemID"));
                    String hiddenUsername = request.getParameter("hiddenUsername");
                    request.setAttribute("assocCategoryItemListSYS", (ArrayList) msmr.viewAssocCategoryItemList(hiddenCategoryName, hiddenItemID));
                    request.setAttribute("itemDetailsSYSVec", msmr.viewItemDetails(hiddenItemID, hiddenUsername));
                    pageAction = "ViewItemDetailsSYS";
                    break;
                case "sendItemOfferPrice":
                    long itemIDHidden = Long.parseLong(request.getParameter("itemIDHidden"));
                    String usernameHidden = request.getParameter("usernameHidden");
                    String itemOfferPrice = request.getParameter("itemOfferPrice");
                    String itemOfferDescription = request.getParameter("itemOfferDescription");
                    
                    responseMessage = msmr.sendItemOfferPrice(itemIDHidden, usernameHidden, itemOfferPrice, itemOfferDescription);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "likeItemListingDetails":
                    long itemIDHid = Long.parseLong(request.getParameter("itemIDHid"));
                    String usernameHid = request.getParameter("usernameHid");
                    
                    responseMessage = msmr.likeUnlikeItem(itemIDHid, usernameHid);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToViewItemDetailsInModalSYS":
                    long transItemID = Long.parseLong(request.getParameter("itemID"));
                    request.setAttribute("transItemDetailsSYSVec", msmr.viewItemDetails(transItemID));
                    pageAction = "ViewItemDetailsInModalSYS";
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
    
    private String editItemListing(HttpServletRequest request) {
        String itemImageFileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("itemImage");
                itemImageFileName = (String) getFileName(filePart);

                String appPath = request.getServletContext().getRealPath("");
                String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator 
                        + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
                String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator 
                        + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "marketplace" 
                        + File.separator + "item" + File.separator;

                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(imageDir + itemImageFileName);
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);

                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    itemImageFileName = "";
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
                itemImageFileName = "";
            }
        } else { itemImageFileName = request.getParameter("hiddenItemImage"); }
        
        long itemID = Long.parseLong(request.getParameter("hiddenItemID"));
        String itemName = request.getParameter("itemName");
        if(itemName.equals("")) { itemName = request.getParameter("hiddenItemName"); }
        String itemPrice = request.getParameter("itemPrice");
        if(itemPrice.equals("")) { itemPrice = request.getParameter("hiddenItemPrice"); }
        String itemCondition = request.getParameter("itemCondition");
        if(itemCondition.equals("")) { itemCondition = request.getParameter("hiddenItemCondition"); }
        String itemDescription = request.getParameter("itemDescription");
        if(itemDescription.equals("")) { itemDescription = request.getParameter("hiddenItemDescription"); }
        long itemCategoryID = Long.parseLong(request.getParameter("hiddenItemCategoryID"));
        String username = request.getParameter("username");
        String tradeLocation = request.getParameter("tradeLocation");
        if(tradeLocation.equals("")) { tradeLocation = request.getParameter("hiddenTradeLocation"); }
        String tradeLat = request.getParameter("hiddenTradeLat");
        String tradeLong = request.getParameter("hiddenTradeLong");
        String tradeInformation = request.getParameter("tradeInformation");
        if(tradeInformation.equals("")) { tradeInformation = request.getParameter("hiddenTradeInformation"); }
        
        return msmr.editItemListing(itemID, itemName, Double.parseDouble(itemPrice), itemCondition, itemDescription, 
                itemImageFileName, itemCategoryID, username, tradeLocation, tradeLat, tradeLong, tradeInformation);
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