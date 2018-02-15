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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class MarketplaceAdminController extends HttpServlet {
    @EJB
    private MarketplaceAdminMgrBeanRemote mamr;
    String responseMessage = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");

            switch (pageAction) {
                case "goToViewItemCategoryListing":
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "goToViewItemCategoryDetails":
                    String urlCategoryName = request.getParameter("urlCategoryName");
                    String urlCategoryType = request.getParameter("urlCategoryType");
                    request.setAttribute("itemCategoryName", urlCategoryName);
                    request.setAttribute("itemCategoryDetailsVec", mamr.viewItemCategoryDetails(urlCategoryName, urlCategoryType));
                    request.setAttribute("associateditemList", (ArrayList) mamr.viewAssociatedItemList(urlCategoryName, urlCategoryType));
                    pageAction = "ViewItemCategoryDetails";
                    break;
                case "goToNewItemCategory":
                    request.setAttribute("paramCategoryType", request.getParameter("categoryType"));
                    pageAction = "NewItemCategory";
                    break;
                case "createItemCategory":
                    if (createItemCategory(request)) {
                        request.setAttribute("successMessage", "Item Category has been created successfully.");
                    } else {
                        request.setAttribute("errorMessage", "One or more fields are invalid. Please check again.");
                    }
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "updateItemCategory":
                    if(updateItemCategory(request)) {
                        request.setAttribute("successMessage", "Selected item category has been updated successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected item category cannot be updated. Please check the inventory log.");
                    }
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "deactivateAnItemCategory":
                    String deactCategoryName = request.getParameter("hiddenCategoryName");
                    String deactCategoryType = request.getParameter("hiddenCategoryType");
                    
                    responseMessage = mamr.deactivateAnItemCategory(deactCategoryName, deactCategoryType);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "activateAnItemCategory":
                    String actCategoryName = request.getParameter("hiddenCategoryName");
                    String actCategoryType = request.getParameter("hiddenCategoryType");
                    if (mamr.activateAnItemCategory(actCategoryName, actCategoryType)) {
                        request.setAttribute("successMessage", "Selected item category has been activated successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Selected item category cannot be activated. Please try again later.");
                    }
                    request.setAttribute("itemCategoryList", (ArrayList) mamr.viewItemCategoryList());
                    pageAction = "ViewItemCategoryListing";
                    break;
                case "goToViewItemListing":
                    request.setAttribute("itemList", (ArrayList) mamr.viewItemList());
                    pageAction = "ViewItemListing";
                    break;
                case "goToViewItemListingDetails":
                    String itemName = request.getParameter("itemName");
                    String itemSellerID = request.getParameter("itemSellerID");
                    request.setAttribute("itemDetailsVec", mamr.viewItemDetails(itemName, itemSellerID));
                    pageAction = "ViewItemListingDetails";
                    break;
                case "deleteAnItem":
                    String hiddenItemName = request.getParameter("hiddenItemName");
                    String hiddenSellerID = request.getParameter("hiddenSellerID");
                    if (mamr.deleteAnItem(hiddenItemName, hiddenSellerID)) {
                        mamr.createSystemMessage(hiddenItemName, hiddenSellerID);
                        request.setAttribute("successMessage", "Selected item has been deleted successfully. A system notification has been sent to the seller");
                    } else {
                        request.setAttribute("errorMessage", "Selected item cannot be deleted. Please try again later.");
                    }
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
    public String getServletInfo() {
        return "Marketplace (Item) Admin Servlet";
    }

    private boolean createItemCategory(HttpServletRequest request) {
        boolean itemCategoryCreateStatus = false;
        String fileName = "";
        try {
            Part filePart = request.getPart("itemImage");
            fileName = (String) getFileName(filePart);

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
        String categoryType = request.getParameter("hiddenCategoryType");
        String categoryDescription = request.getParameter("categoryDescription");

        if (mamr.createItemCategory(categoryName, categoryType, categoryDescription, fileName)) {
            itemCategoryCreateStatus = true;
        }
        return itemCategoryCreateStatus;
    }
    
    private boolean updateItemCategory(HttpServletRequest request) {
        boolean itemCategoryUpdateStatus = false;
        String fileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("itemImage");
                fileName = (String) getFileName(filePart);

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
        }
        else { fileName = request.getParameter("oldCategoryImage"); }
        
        String oldCategoryName = request.getParameter("oldCategoryName");
        String newCategoryName = request.getParameter("categoryName");
        String categoryType = request.getParameter("hiddenCategoryType");
        String oldCategoryDescription = request.getParameter("oldCategoryDescription");
        String newCategoryDescription = request.getParameter("categoryDescription");

        if(newCategoryName.equals("")) { newCategoryName = oldCategoryName; }
        if(newCategoryDescription.equals("")) { newCategoryDescription = oldCategoryDescription; }
        
        if (mamr.updateItemCategory(oldCategoryName, newCategoryName, categoryType, oldCategoryDescription, 
                newCategoryDescription, fileName)) {
            itemCategoryUpdateStatus = true;
        }
        return itemCategoryUpdateStatus;
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