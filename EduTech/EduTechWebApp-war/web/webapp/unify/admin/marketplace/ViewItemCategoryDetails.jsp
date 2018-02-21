<!-- ***************************************************************************************
*   Title:                  ViewItemCategoryDetails.jsp
*   Purpose:                DETAILED INFORMATION OF THE SELECTED ITEM CATEGORY (UNIFY ADMIN)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
**************************************************************************************** -->

<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - Item Category Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/weblayout/marketplace/ViewItemCategoryDetailsCSS.css" rel="stylesheet" type="text/css" />

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/lodash.compat-v2.0.0.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/breakpoints-v1.0.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll.horizontal-v0.6.5.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.sparkline-v2.1.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/dataTable/jquery.dataTables-v1.9.4.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/dataTable/dataTables.responsive-v0.1.2.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminAppJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginFormComponentsJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminCommonJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>
        <script src="js/unify/admin/webjs/marketplace/ViewItemCategoryDetailsJS.js" type="text/javascript"></script>
    </head>
    <body style="background-color: #FFFFFF;">
        <%  
            Vector itemCategoryDetailsVec = (Vector) request.getAttribute("itemCategoryDetailsVec");
            String categoryImage, categoryName, categoryType, categoryDescription, categoryActiveStatus, activeStatus;
            categoryImage = categoryName = categoryType = categoryDescription = categoryActiveStatus = activeStatus = "";

            if (itemCategoryDetailsVec != null) {
                categoryImage = (String.valueOf(itemCategoryDetailsVec.get(0)));
                categoryName = (String) itemCategoryDetailsVec.get(1);
                categoryType = (String) itemCategoryDetailsVec.get(2);
                categoryDescription = (String) itemCategoryDetailsVec.get(3);
                categoryActiveStatus = (String.valueOf(itemCategoryDetailsVec.get(4)));
            }
        %>
        <form id="itemCategoryDetailsForm" action="MarketplaceAdmin" method="POST" enctype="multipart/form-data" target="_parent">
            <table class="formFields" border="0">
                <tr>
                    <td>
                        <div class="form-group">
                            <div class="image-upload">
                                <img id="output-image" src="uploads/unify/images/common/category/<%= categoryImage%>" />
                            </div>
                            <label for="file-upload" class="btn btn-outline btn-theme btn-sm btn-block" style="margin-top: 10px; width: 151px;">
                                <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                            </label>
                            <input id="file-upload" name="itemImage" type="file" accept="image/*" required="required" onchange="javascript: previewImage(event)" />
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span>Category Type:&nbsp;&nbsp;<strong><%= categoryType%></strong></span>
                            </div>
                        </div>
                        <%
                            if (categoryActiveStatus.equals("true")) {
                                activeStatus = "Active";
                        %>
                        <div class="form-group">
                            <div class="input-group">
                                <span>Category Status:&nbsp;&nbsp;<span class="label label-success"><%= activeStatus%></span>
                            </div>
                        </div>
                        <%  } else {
                            activeStatus = "Inactive";
                        %>
                        <div class="form-group">
                            <div class="input-group">
                                <span>Category Status:&nbsp;&nbsp;<span class="label label-danger"><%= activeStatus%></span>
                            </div>
                        </div>
                        <%  }%>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                <input type="hidden" name="oldCategoryName" value="<%= categoryName%>" />
                                <input type="text" class="form-control" placeholder="<%= categoryName%>" name="categoryName" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                <input type="hidden" name="oldCategoryDescription" value="<%= categoryDescription%>" />
                                <textarea rows="5" class="form-control" placeholder="<%= categoryDescription%>" name="categoryDescription"></textarea>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr style="text-align: center;">
                    <td colspan="2">
                        <input type="hidden" name="pageTransit" value="updateItemCategory"/>
                        <input type="hidden" name="hiddenItemCategoryID" value="<%= request.getAttribute("urlItemCategoryID")%>"/>
                        <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                        <input type="hidden" name="oldCategoryImage"  value="<%= categoryImage%>" />
                        <button type="button" class="btn btn-primary" onclick="itemCategoryDetailsForm.submit();">Update Category</button>&nbsp;&nbsp;

                        <%  if (activeStatus.equals("Active")) {%>
                        <button type="button" class="btn btn-primary" onclick="window.open('MarketplaceAdmin?pageTransit=deactivateAnItemCategory&hiddenItemCategoryID=<%= request.getAttribute("urlItemCategoryID")%>','_parent')">Deactivate Category</button>
                        <%  } else if (activeStatus.equals("Inactive")) {%>
                        <button type="button" class="btn btn-primary" onclick="window.open('MarketplaceAdmin?pageTransit=activateAnItemCategory&hiddenItemCategoryID=<%= request.getAttribute("urlItemCategoryID")%>','_parent')">Activate Category</button>
                        <%  }   %>
                    </td>
                </tr>
            </table>
            
            <div style="margin: 40px 20px 0 20px">
                <h5 style="margin-bottom: 20px;"><strong><u>List of items under this "<%= categoryName%>" category</u></strong></h5>
                <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                        <tr>
                            <th data-class="expand">Item Image</th>
                            <th>Item Name</th>
                            <th>Seller ID</th>
                            <th>Item Price</th>
                            <th data-hide="phone">Item Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Vector> associatedItemList = (ArrayList) request.getAttribute("associatedItemList");
                            if (!associatedItemList.isEmpty()) {
                                for (int i = 0; i <= associatedItemList.size() - 1; i++) {
                                    Vector v = associatedItemList.get(i);
                                    String itemImage = String.valueOf(v.get(0));
                                    String itemName = String.valueOf(v.get(1));
                                    String itemSellerID = String.valueOf(v.get(2));
                                    String itemPrice = String.valueOf(v.get(3));
                                    String itemStatus = String.valueOf(v.get(4));
                        %>
                        <tr>
                            <td><img src="uploads/unify/images/marketplace/item/<%= itemImage%>" style="max-width: 50px; max-height: 50px;" /></td>
                            <td><%= itemName%></td>
                            <td><%= itemSellerID%></td>
                            <td>$<%= itemPrice%></td>
                            <td><%= itemStatus%></td>
                        </tr>
                        <%      }   %>
                        <%  }   %>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>