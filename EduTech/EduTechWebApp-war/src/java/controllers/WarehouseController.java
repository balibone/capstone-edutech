package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import javax.ejb.EJB;
import sessionbeans.WarehouseMgrBeanRemote;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class WarehouseController extends HttpServlet {
    @EJB
    private WarehouseMgrBeanRemote wmr;
    String userNRIC = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            // FOR HANDLING THE HIDDEN FIELD
            String pageAction = request.getParameter("pageTransit");
            
            switch (pageAction) {                
                case "goToNewItem":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "NewItem";
                    break;
                case "goToItemList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("itemList", wmr.viewItemList());
                    pageAction = "ItemList";
                    break;
                case "createItem":
                    request.setAttribute("employeeNRIC", userNRIC);
                    if(createItem(request,response)){
                        request.setAttribute("successMessage", "New item created successfully");
                    }else{
                        request.setAttribute("errorMessage", "Error creating new item");
                    }   pageAction = "NewItem";
                    break;
                case "goToViewItem":
                    request.setAttribute("employeeNRIC",userNRIC);
                    request.setAttribute("itemDetails", wmr.viewItem(request.getParameter("itemSKU")));
                    pageAction = "ViewItem";
                    break;
                case "deleteItem":
                    request.setAttribute("employeeNRIC",userNRIC);
                    wmr.deleteItem(request.getParameter("itemSKU"));
                    request.setAttribute("itemList", wmr.viewItemList());
                    pageAction = "ItemList";
                    break;
                case "goToEditItem":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("itemDetails", wmr.viewItem(request.getParameter("itemSKU")));
                    pageAction = "EditItem";
                    break;
                case "editItem":
                    request.setAttribute("employeeNRIC",userNRIC);
                    if(editItem(request,response)){
                        request.setAttribute("successMessage", "Item edited successfully");
                        request.setAttribute("itemDetails", wmr.viewItem(request.getParameter("itemSKU")));
                    }else{
                        request.setAttribute("errorMessage", "Error editing item");
                    }   pageAction = "EditItem";
                    break;
                case "goToNewCompositeItem":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "NewCompositeItem";
                    break;
                case "createCompositeItem":
                    request.setAttribute("employeeNRIC", userNRIC);
                    if(createCompositeItemRecord(request)) {
                        request.setAttribute("successMessage", "Composite item has been created successfully.");
                    }
                    else {
                        request.setAttribute("errorMessage", "One or more fields are invalid. Please check again.");
                    }   pageAction = "NewCompositeItem";
                    break;
                case "goToCompositeItemList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("compositeItemList", (ArrayList)wmr.viewCompositeItemList());
                    pageAction = "CompositeItemList";
                    break;
                case "goToCompositeItemDetails":
                    String compositeIdentifier = request.getParameter("compositeIdentifier");
                    request.setAttribute("compositeItemInfo", wmr.getCompositeItemInfo(compositeIdentifier));   // SPECIAL CASE (VECTOR)
                    request.setAttribute("assocCompItemInventoryList", (ArrayList)wmr.getAssocCompItemInventoryInfo(compositeIdentifier));
                    pageAction = "CompositeItemDetails";
                    break;
                case "goToQuantityAdjustment":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "QuantityAdjustment";
                    break;
                case "adjustQuantity":
                    request.setAttribute("employeeNRIC", userNRIC);
                    if(createItemInventoryLog(userNRIC, request)) {
                        request.setAttribute("successMessage", "Quantity adjustment(s) has been logged successfully.");
                    }
                    else {
                        request.setAttribute("errorMessage", "One or more quantity adjustment records are invalid. Please check the inventory log.");
                    }   pageAction = "QuantityAdjustment";
                    break;
                case "goToInventoryLogList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("inventoryLogList", (ArrayList)wmr.viewInventoryLogList());
                    pageAction = "InventoryLogList";
                    break;
                case "goToSalesOrderList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("salesOrderList", (ArrayList)wmr.viewSalesOrderlist());
                    pageAction = "SalesOrderList";
                    break;
                case "goToInvoiceList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    request.setAttribute("invoiceList", (ArrayList)wmr.viewInvoiceList());
                    pageAction = "InvoiceList";
                    break;
                case "createInvoice":
                    request.setAttribute("employeeNRIC", userNRIC);
                    /*
                    if(createInvoice(request)) {
                    request.setAttribute("successMessage", "Invoice has been created successfully.");
                    }
                    else {
                    request.setAttribute("errorMessage", "One or more fields are invalid. Please check again.");
                    }*/
                    pageAction = "InvoiceList";
                    break;
                case "goToNewInvoice":
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "NewInvoice";
                    break;
                case "goToNewInventoryCategory":
                    System.out.println("Inside goToNewInventoryCategory");
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "NewInventoryCategory";
                    break;
                case "goToItemCategoryList":
                    request.setAttribute("employeeNRIC", userNRIC);
                    viewAllInventoryCategories(request);
                    pageAction = "ViewInventoryCategories";
                    break;
                case "createNewInventoryCategory":
                    request.setAttribute("employeeNRIC", userNRIC);
                    if(createInventoryCategory(request)){
                        viewAllInventoryCategories(request);
                        pageAction = "ViewInventoryCategories";
                    }
                    else{
                        viewAllInventoryCategories(request);
                        pageAction = "ViewInventoryCategories";
                    }   break;
                case "goToViewOneInventoryCategory":
                    request.setAttribute("employeeNRIC", userNRIC);
                    String selectedCategory = request.getParameter("cateName");
                    request.setAttribute("cateName", selectedCategory);
                    request.setAttribute("cateDesc", request.getParameter("cateDesc"));
                    request.setAttribute("cateSubs", request.getParameter("catesubs"));
                    pageAction = "viewOneInventoryCategory";
                    break;
                case "modifyInventoryCategory":
                    request.setAttribute("employeeNRIC", userNRIC);
                    modifyInventoryCategory(request);
                    viewAllInventoryCategories(request);
                    pageAction = "ViewInventoryCategories";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);       
        }
        catch(Exception ex) {
            log("Exception in WarehouseController: processRequest()");
            ex.printStackTrace();
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        try {
            ArrayList<Vector> retrievedItemList = (ArrayList)wmr.getItemListingNames();
            JSONObject responseDetailsJson = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            
            for(int i = 0; i <= retrievedItemList.size()-1; i++){
                Vector v = retrievedItemList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("itemName", String.valueOf(v.get(0)));
                jsonObject.put("itemSKU", String.valueOf(v.get(1)));
                jsonObject.put("itemQuantityAvail", String.valueOf(v.get(2)));
                jsonObject.put("itemSellingPrice", String.valueOf(v.get(3)));
                jsonArray.put(jsonObject);
            }
            responseDetailsJson.put("itemDetails", jsonArray);
            response.setContentType("application/json");
            response.getWriter().print(responseDetailsJson);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "Warehouse Servlet"; }
    
    private boolean createItemInventoryLog(String userNRIC, HttpServletRequest request){
        boolean logCreationStatus = false;
        
        String logDate = request.getParameter("logDate");
        String logReason = request.getParameter("logReason");
        String logDescription = request.getParameter("logDescription");
        String[] itemNameArr = request.getParameterValues("itemName");
        String[] itemSKUArr = request.getParameterValues("itemSKU");
        String[] itemQtyArr = request.getParameterValues("itemQuantityAvail");
        String[] itemQtyAdjustArr = request.getParameterValues("itemQuantityAdjust");
        
        if(wmr.createInventoryLog(userNRIC, logDate, logReason, logDescription, itemNameArr, 
                itemSKUArr, itemQtyArr, itemQtyAdjustArr)) {
            logCreationStatus = true;
        }
        return logCreationStatus;
    }
    
    private boolean createItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean itemCreationStatus = false;
        
        String appPath = request.getServletContext().getRealPath("");
        String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator + 
                    "EduTech" + File.separator + "EduTechWebApp-war_war", "");
        String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator + 
                    "uploads" + File.separator + "images" + File.separator + "warehouse" + File.separator + "Items";
        final Part imagePart = request.getPart("itemImage");
        final String fileName = imagePart.getSubmittedFileName();
 
        FileOutputStream out = null;
        InputStream fileContent = null;
        //final PrintWriter writer = response.getWriter();
        String itemImageDirPath = "";
        try {
            out = new FileOutputStream(new File(imageDir + File.separator + fileName));
            itemImageDirPath = fileName;
            fileContent = imagePart.getInputStream();
            
            int bytesRead = 0;
            final byte[] bytes = new byte[1024];
            //read image bytes from input stream until finish.
            while ((bytesRead = fileContent.read(bytes)) != -1) {
                //write image bytes to output stream incrementally, until bytesRead = total file size --> means full image written.
                out.write(bytes, 0, bytesRead);
            }
            //writer.println("New file " + fileName + " created at " + imageDir);
        } catch (FileNotFoundException fne) {
            /*writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());
            
            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                    new Object[]{fne.getMessage()});*/
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                fileContent.close();
            }
            /*if (writer != null) {
                writer.close();
            }*/
        }
        String itemName = request.getParameter("itemName");
        String itemSKU = request.getParameter("itemSKU");
        String vendorID = request.getParameter("vendorID");
        String vendorProductCode = request.getParameter("vendorProductCode");
        String itemSellingPrice = request.getParameter("itemSellingPrice");
        String itemQuantity = request.getParameter("itemQuantity");
        String itemReorderLevel = request.getParameter("itemReorderLevel");
        String itemDescription = request.getParameter("itemDescription");
        itemCreationStatus = wmr.createItem(itemImageDirPath, itemSKU,itemName,itemDescription,itemQuantity, itemReorderLevel, itemSellingPrice, vendorID, vendorProductCode);
        return itemCreationStatus;
    }
    
    private boolean editItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        boolean itemEditionStatus = false;
        String itemImageDirPath = "";
        if(request.getParameter("imageReplacement").equalsIgnoreCase("yes")) {
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator + 
                    "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator + 
                    "uploads" + File.separator + "images" + File.separator + "warehouse" + File.separator + "Items";
            final Part imagePart = request.getPart("itemImage");
            final String fileName = imagePart.getSubmittedFileName();

            FileOutputStream out = null;
            InputStream fileContent = null;
            final PrintWriter writer = response.getWriter();
            try {
                out = new FileOutputStream(new File(imageDir + File.separator + fileName));
                itemImageDirPath = fileName;
                fileContent = imagePart.getInputStream();
                
                int bytesRead = 0;
                final byte[] bytes = new byte[1024];
                //read image bytes from input stream until finish.
                while ((bytesRead = fileContent.read(bytes)) != -1) {
                    //write image bytes to output stream incrementally, until bytesRead = total file size --> means full image written.
                    out.write(bytes, 0, bytesRead);
                }
                //writer.println("New file " + fileName + " created at " + imageDir);
            } catch (FileNotFoundException fne) {
                /*writer.println("You either did not specify a file to upload or are "
                        + "trying to upload a file to a protected or nonexistent "
                        + "location.");
                writer.println("<br/> ERROR: " + fne.getMessage());
                
                LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
                        new Object[]{fne.getMessage()});*/
            } finally {
                if (out != null) {
                    out.close();
                }
                if (fileContent != null) {
                    fileContent.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }         
        }else{
            itemImageDirPath=request.getParameter("originalItemImage");
        }
        
        String itemName = request.getParameter("itemName");
        String itemSKU = request.getParameter("itemSKU");
        String vendorID = request.getParameter("vendorID");
        String vendorProductCode = request.getParameter("vendorProductCode");
        String itemSellingPrice = request.getParameter("itemSellingPrice");
        String itemQuantity = request.getParameter("itemQuantity");
        String itemReorderLevel = request.getParameter("itemReorderLevel");
        String itemDescription = request.getParameter("itemDescription");
        itemEditionStatus = wmr.editItem(itemImageDirPath, itemSKU,itemName,itemDescription,itemQuantity, itemReorderLevel, itemSellingPrice, vendorID, vendorProductCode);
        return itemEditionStatus;
    }
    
    private boolean createCompositeItemRecord(HttpServletRequest request){
        boolean compCreationStatus = false;
        String fileName = "";
        
        try {
            Part filePart = request.getPart("itemImage");
            fileName = (String) getFileName(filePart);
            
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator + 
                    "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator + 
                    "uploads" + File.separator + "images" + File.separator + "warehouse" + File.separator + "CompositeItems";
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(imageDir + File.separator + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
            
                int read = 0;
                final byte[] bytes = new byte[1024];
                while((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
                fileName = "";
            } finally {
                if(inputStream != null) { inputStream.close(); }
                if(outputStream != null) { outputStream.close(); }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fileName = "";
        }
        String compositeName = request.getParameter("compositeName");
        String compositeSKU = request.getParameter("compositeSKU");
        String compositeSellPrice = request.getParameter("compositeSellPrice");
        String compositeRebundleLvl = request.getParameter("compositeRebundleLvl");
        String compositeDescription = request.getParameter("compositeDescription");
        
        String[] itemNameArr = request.getParameterValues("itemName");
        String[] itemSKUArr = request.getParameterValues("itemSKU");
        String[] itemQtyRequiredArr = request.getParameterValues("itemQuantityRequired");
        
        if(wmr.createCompositeItem(compositeName, compositeSKU, compositeSellPrice, compositeRebundleLvl, 
                compositeDescription, fileName, itemNameArr, itemSKUArr, itemQtyRequiredArr)) {
            compCreationStatus = true;
        }
        return compCreationStatus;
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
    
    private boolean createInventoryCategory(HttpServletRequest request) {
        String newCategoryName = request.getParameter("newInventoryCategoryName");
        String newCategoryDesc = request.getParameter("newInventoryCategoryDesc");
        int subcategoriesCount = Integer.parseInt(request.getParameter("subcategories"));
        ArrayList<String> sCats = new ArrayList();
        for (int i = 1; i <= subcategoriesCount; i++) {
            String variable = "sCat" + i;
            sCats.add(request.getParameter(variable));
        }
        if(wmr.createInventoryCategory(newCategoryName, newCategoryDesc, sCats)){
            return true;
        }else{
            return false;
        }
    }
    
    private void viewAllInventoryCategories(HttpServletRequest request) {
        request.setAttribute("categoryList", (ArrayList) wmr.viewAllInventoryCategories());
    }
    
    private void modifyInventoryCategory(HttpServletRequest request) {
        String categoryName = request.getParameter("cateName");
        String updatedCategoryDesc = request.getParameter("updatedInventoryCategoryDesc");
        int subcategoriesCount = Integer.parseInt(request.getParameter("subcategories"));
        ArrayList<String> sCats = new ArrayList();
        for (int i = 1; i <= subcategoriesCount; i++) {
            String variable = "sCat" + i;
            sCats.add(request.getParameter(variable));
        }
        wmr.modifyInventoryCategory(categoryName,updatedCategoryDesc,sCats);
    }
}
