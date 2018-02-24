<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - New Item Category</title>

        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/marketplace/ViewItemCategoryDetailsCSS.css" rel="stylesheet" type="text/css">
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
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div class="formContent">
                <form action="MarketplaceAdmin" method="POST" enctype="multipart/form-data" target="_parent">
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                        <div class="image-upload">
                            <img id="output-image" src="uploads/unify/images/common/category/<%= categoryImage%>" />
                        </div>
                        <label for="file-upload" class="btn btn-outline btn-primary btn-sm btn-block" style="margin-top: 10px; width: 151px;">
                            <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                        </label>
                        <input id="file-upload" name="itemImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" />
                    </div>
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Category Type:&nbsp;&nbsp;<u><%= categoryType%></u></label>
                        </div>
                        <%
                            if (categoryActiveStatus.equals("true")) {
                                activeStatus = "Active";
                        %>
                        <div class="form-group" style="margin-top: 7px;">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Category Status:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= activeStatus%></span></u>
                            </label>
                        </div>
                        <%  } else {
                            activeStatus = "Inactive";
                        %>
                        <div class="form-group" style="margin-top: 7px;">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Category Status:&nbsp;&nbsp;
                                <u><span style="color: #FF7878;"><%= activeStatus%></span></u></label>
                        </div>
                        <%  }%>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="<%= categoryName%>" name="categoryName" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <textarea rows="5" class="form-control has-feedback-left" placeholder="<%= categoryDescription%>" name="categoryDescription"></textarea>
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>

                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3" style="margin: 5px 0 5px 0;">
                                <table border="0" style="margin: auto;">
                                    <tr>
                                        <td>
                                            <input type="hidden" name="pageTransit" value="updateItemCategory"/>
                                            <input type="hidden" name="oldCategoryName" value="<%= categoryName%>" />
                                            <input type="hidden" name="hiddenCategoryType" value="<%= categoryType%>" />
                                            <input type="hidden" name="oldCategoryDescription" value="<%= categoryDescription%>" />
                                            <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                                            <input type="hidden" name="oldCategoryImage"  value="<%= categoryImage%>" />
                                            <button type="submit" class="btn btn-primary">Update Category</button></form>
                                        </td>
                                        <%
                                            if (activeStatus.toUpperCase().equals("ACTIVE")) {
                                        %>
                                        <td>
                                            <form action="MarketplaceAdmin" method="POST" target="_parent">
                                                <input type="hidden" name="pageTransit" value="deactivateAnItemCategory"/>
                                                <input type="hidden" name="hiddenCategoryName" value="<%= categoryName%>"/>
                                                <input type="hidden" name="hiddenCategoryType" value="<%= categoryType%>"/>
                                                <button type="submit" class="btn btn-primary">Deactivate Category</button>
                                            </form>
                                        </td>
                                        <%
                                        } else if (activeStatus.toUpperCase().equals("INACTIVE")) {
                                        %>
                                        <td>
                                            <form action="MarketplaceAdmin" method="POST" target="_parent">
                                                <input type="hidden" name="pageTransit" value="activateAnItemCategory"/>
                                                <input type="hidden" name="hiddenCategoryName" value="<%= categoryName%>"/>
                                                <input type="hidden" name="hiddenCategoryType" value="<%= categoryType%>"/>
                                                <button type="submit" class="btn btn-primary">Activate Category</button>
                                            </form>
                                        </td>
                                        <%
                                            }
                                        %>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
            </div>
            <div class="ln_solid"></div>
            <div class="formContent">
                <h4 class="iziHeader">
                    <strong>List of items under the "<%= request.getAttribute("itemCategoryName") %>" category:</strong>
                </h4>
                <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Item Image</th>
                            <th>Item Name</th>
                            <th>Seller ID</th>
                            <th>Item Price</th>
                            <th>Item Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Vector> associateditemList = (ArrayList) request.getAttribute("associateditemList");
                            if (!associateditemList.isEmpty()) {
                                for (int i = 0; i <= associateditemList.size() - 1; i++) {
                                    Vector v = associateditemList.get(i);
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
                        <%  }%>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/marketplace/ViewItemCategoryDetailsJS.js" type="text/javascript"></script>
    </body>
</html>
