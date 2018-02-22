/***************************************************************************************
*   Title:                  MarketplaceAdminController.java
*   Purpose:                SERVLET FOR UNIFY MARKETPLACE - ADMIN (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifycontrollers.admin;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
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

import unifysessionbeans.admin.MarketplaceAdminMgrBeanRemote;
import unifysessionbeans.admin.UserProfileAdminMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class MarketplaceAdminController extends HttpServlet {
    @EJB
    private MarketplaceAdminMgrBeanRemote mamr;
    @EJB
    private UserProfileAdminMgrBeanRemote uamr;
    String responseMessage = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            request.setAttribute("unifyUserCount", uamr.getUnifyUserCount());
            
            switch (pageAction) {
                case "goToViewItemCategoryListing":
                    request.setAttribute("activeItemCategoryListCount", mamr.getActiveItemCategoryListCount());
                    request.setAttribute("inactiveItemCategoryListCount", mamr.getInactiveItemCategoryListCount());
                    request.setAttribute("itemListingCount", mamr.getItemListingCount());
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "goToViewItemCategoryDetails":
                    long urlItemCategoryID = Long.parseLong(request.getParameter("itemCategoryID"));
                    request.setAttribute("urlItemCategoryID", urlItemCategoryID);
                    request.setAttribute("itemCategoryDetailsVec", mamr.viewItemCategoryDetails(urlItemCategoryID));
                    request.setAttribute("associatedItemList", (ArrayList) mamr.viewAssociatedItemList(urlItemCategoryID));
                    pageAction = "ViewItemCategoryDetails";
                    break;
                case "goToNewItemCategory":
                    pageAction = "NewItemCategory";
                    break;
                case "createItemCategory":
                    responseMessage = createItemCategory(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeItemCategoryListCount", mamr.getActiveItemCategoryListCount());
                    request.setAttribute("inactiveItemCategoryListCount", mamr.getInactiveItemCategoryListCount());
                    request.setAttribute("itemListingCount", mamr.getItemListingCount());
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "updateItemCategory":
                    responseMessage = updateItemCategory(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeItemCategoryListCount", mamr.getActiveItemCategoryListCount());
                    request.setAttribute("inactiveItemCategoryListCount", mamr.getInactiveItemCategoryListCount());
                    request.setAttribute("itemListingCount", mamr.getItemListingCount());
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "deactivateAnItemCategory":
                    long deactItemCategoryID = Long.parseLong(request.getParameter("hiddenItemCategoryID"));
                    responseMessage = mamr.deactivateAnItemCategory(deactItemCategoryID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeItemCategoryListCount", mamr.getActiveItemCategoryListCount());
                    request.setAttribute("inactiveItemCategoryListCount", mamr.getInactiveItemCategoryListCount());
                    request.setAttribute("itemListingCount", mamr.getItemListingCount());
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "activateAnItemCategory":
                    long actItemCategoryID = Long.parseLong(request.getParameter("hiddenItemCategoryID"));
                    responseMessage = mamr.activateAnItemCategory(actItemCategoryID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("activeItemCategoryListCount", mamr.getActiveItemCategoryListCount());
                    request.setAttribute("inactiveItemCategoryListCount", mamr.getInactiveItemCategoryListCount());
                    request.setAttribute("itemListingCount", mamr.getItemListingCount());
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "goToViewItemListing":
                    request.setAttribute("availableItemListingCount", mamr.getAvailableItemListingCount());
                    request.setAttribute("reservedItemListingCount", mamr.getReservedItemListingCount());
                    request.setAttribute("soldItemListingCount", mamr.getSoldItemListingCount());
                    request.setAttribute("itemList", (ArrayList) mamr.viewItemList());
                    pageAction = "ViewItemListing";
                    break;
                case "goToViewItemListingDetails":
                    long urlItemID = Long.parseLong(request.getParameter("itemID"));
                    request.setAttribute("urlItemID", urlItemID);
                    request.setAttribute("itemDetailsVec", mamr.viewItemDetails(urlItemID));
                    request.setAttribute("itemTransList", mamr.viewItemTransactionList(urlItemID));
                    request.setAttribute("itemReviewList", mamr.viewItemReviewList(urlItemID));
                    pageAction = "ViewItemListingDetails";
                    break;
                case "goToViewItemListingDetailsInModal":
                    long hidItemID = Long.parseLong(request.getParameter("itemID"));
                    request.setAttribute("urlItemID", hidItemID);
                    long hidItemCategoryID = Long.parseLong(request.getParameter("itemCategoryID"));
                    request.setAttribute("urlItemCategoryID", hidItemCategoryID);
                    
                    request.setAttribute("itemDetailsVec", mamr.viewItemDetails(hidItemID));
                    request.setAttribute("itemTransList", mamr.viewItemTransactionList(hidItemID));
                    request.setAttribute("itemReviewList", mamr.viewItemReviewList(hidItemID));
                    pageAction = "ViewItemListingDetailsInModal";
                    break;
                case "deleteAnItem":
                    long hiddenItemID = Long.parseLong(request.getParameter("itemID"));
                    responseMessage = mamr.deleteAnItem(hiddenItemID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("availableItemListingCount", mamr.getAvailableItemListingCount());
                    request.setAttribute("reservedItemListingCount", mamr.getReservedItemListingCount());
                    request.setAttribute("soldItemListingCount", mamr.getSoldItemListingCount());
                    request.setAttribute("itemList", (ArrayList) mamr.viewItemList());
                    pageAction = "ViewItemListing";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
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

    private String createItemCategory(HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("itemImage");
            fileName = (String) getFileName(filePart);
            if(fileName.contains("\\")) {
                fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
            }
            
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "common"
                    + File.separator + "category" + File.separator;

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(imageDir + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);

                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
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
            fileName = "";
        }
        String categoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("categoryDescription");

        return mamr.createItemCategory(categoryName, "Marketplace", categoryDescription, fileName);
    }
    
    private String updateItemCategory(HttpServletRequest request) {
        String fileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("itemImage");
                fileName = (String) getFileName(filePart);
                if(fileName.contains("\\")) {
                    fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
                }
                
                String appPath = request.getServletContext().getRealPath("");
                String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                        + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
                String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                        + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "common"
                        + File.separator + "category" + File.separator;

                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    File outputFilePath = new File(imageDir + fileName);
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);

                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    fileName = "";
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
                fileName = "";
            }
        } else { fileName = request.getParameter("oldCategoryImage"); }
        
        long itemCategoryID = Long.parseLong(request.getParameter("hiddenItemCategoryID"));
        String categoryName = request.getParameter("oldCategoryName");
        String newCategoryName = request.getParameter("categoryName");
        String categoryDescription = request.getParameter("oldCategoryDescription");
        String newCategoryDescription = request.getParameter("categoryDescription");

        if(!newCategoryName.equals("")) { categoryName = newCategoryName; }
        if(!newCategoryDescription.equals("")) { categoryDescription = newCategoryDescription; }
        return mamr.updateItemCategory(itemCategoryID, categoryName, categoryDescription, fileName);
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
}