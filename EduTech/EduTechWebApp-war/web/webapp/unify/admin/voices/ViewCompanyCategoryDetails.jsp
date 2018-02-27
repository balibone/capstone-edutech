<!-- ***************************************************************************************
*   Title:                  ViewCompanyCategoryDetails.jsp
*   Purpose:                DETAILED INFORMATION OF THE SELECTED COMPANY CATEGORY (UNIFY ADMIN)
*   Created By:             ZHU XINYI
*   Modified By:            TAN CHIN WEE WINSTON
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.1
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
        <title>Unify Admin - Company Category Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/weblayout/voices/ViewCompanyCategoryDetailsCSS.css" rel="stylesheet" type="text/css" />

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
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>
        <script src="js/unify/admin/webjs/voices/ViewCompanyCategoryDetailsJS.js" type="text/javascript"></script>
    </head>
    <body style="background-color: #FFFFFF;">
        <%  
            Vector companyCategoryDetailsVec = (Vector) request.getAttribute("companyCategoryDetailsVec");
            String categoryImage, categoryName, categoryType, categoryDescription, categoryActiveStatus, activeStatus;
            categoryImage = categoryName = categoryType = categoryDescription = categoryActiveStatus = activeStatus = "";

            if (companyCategoryDetailsVec != null) {
                categoryImage = (String) companyCategoryDetailsVec.get(0);
                categoryName = (String) companyCategoryDetailsVec.get(1);
                categoryType = (String) companyCategoryDetailsVec.get(2);
                categoryDescription = (String) companyCategoryDetailsVec.get(3);
                categoryActiveStatus = (String.valueOf(companyCategoryDetailsVec.get(4)));
                
                if(categoryName.contains("&")) {
                    categoryName = categoryName.replace("&", "&");
                }
            }
        %>
        <form id="companyCategoryDetailsForm" action="VoicesAdmin" method="POST" enctype="multipart/form-data" target="_parent">
            <table class="formFields" border="0">
                <tr>
                    <td colspan="2" style="vertical-align: middle; text-align: right;">
                        <button class="btn btn-sm" onclick="javascript:NewCompanyInModal(<%= request.getAttribute("urlCompanyCategoryID")%>)">
                            <i class="fa fa-forward"></i>&nbsp;&nbsp;Add New Company
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <div class="image-upload">
                                <img id="output-image" src="uploads/unify/images/common/category/<%= categoryImage%>" />
                            </div>
                            <label for="file-upload" class="btn btn-default btn-sm btn-block" style="margin-top: 10px; width: 151px;">
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
                                <input id="oldCategoryName" type="hidden" name="oldCategoryName" value="<%= categoryName%>" />
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
                        <input type="hidden" name="pageTransit" value="updateCompanyCategory"/>
                        <input type="hidden" name="hiddenCompanyCategoryID" value="<%= request.getAttribute("urlCompanyCategoryID")%>"/>
                        <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                        <input type="hidden" name="oldCategoryImage"  value="<%= categoryImage%>" />
                        <button type="button" class="btn btn-primary" onclick="companyCategoryDetailsForm.submit();">Update Company Category</button>&nbsp;&nbsp;

                        <%  if (activeStatus.equals("Active")) {%>
                        <button type="button" class="btn btn-primary" onclick="javascript:AlertIt(<%= request.getAttribute("urlCompanyCategoryID")%>)">Deactivate Company Category</button>
                        <%--<button type="button" class="btn btn-primary" onclick="window.open('VoicesAdmin?pageTransit=deactivateACompanyCategory&hiddenCompanyCategoryID=<%= request.getAttribute("urlCompanyCategoryID")%>','_parent')">Deactivate Company Category</button>--%>
                        <%  } else if (activeStatus.equals("Inactive")) {%>
                        <button type="button" class="btn btn-primary" onclick="window.open('VoicesAdmin?pageTransit=activateACompanyCategory&hiddenCompanyCategoryID=<%= request.getAttribute("urlCompanyCategoryID")%>','_parent')">Activate Company Category</button>
                        <%  }   %>
                    </td>
                </tr>
            </table>
            
            <div style="margin: 40px 20px 0 20px">
                <h5 style="margin-bottom: 20px;"><strong><u>List of companies under this "<%= categoryName%>" category</u></strong></h5>
                <table id="associatedCompanyList" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                        <tr>
                            <th data-class="expand">Company Image</th>
                            <th data-class="expand">Company Name</th>
                            <th>Company HQ</th>
                            <th>Company Size</th>
                            <th data-hide="phone">Company Average Rating</th>
                            <th data-hide="phone">Company Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Vector> associatedCompanyList = (ArrayList) request.getAttribute("associatedCompanyList");
                            if (!associatedCompanyList.isEmpty()) {
                                for (int i = 0; i <= associatedCompanyList.size() - 1; i++) {
                                    Vector v = associatedCompanyList.get(i);
                                    String companyID = String.valueOf(v.get(0));
                                    String companyCategoryID = String.valueOf(v.get(1));
                                    String companyImage = String.valueOf(v.get(2));
                                    String companyName = String.valueOf(v.get(3));
                                    String companyHQ = String.valueOf(v.get(4));
                                    String companySize = String.valueOf(v.get(5));
                                    String companyAverageRating = String.valueOf(v.get(6));
                                    String companyStatus = String.valueOf(v.get(7));
                        %>
                        <tr>
                            <td><img src="uploads/unify/images/voices/company/<%= companyImage%>" style="max-width: 50px; max-height: 50px;" /></td>
                            <td><%= companyName%><span style="display: none">;<%= companyID%>;<%= companyCategoryID%></span></td>
                            <td><%= companyHQ%></td>
                            <td><%= companySize%></td>
                            <td><%= companyAverageRating%></td>
                            <%  if (companyStatus.equals("Active")) {   %>
                            <td><span class="label label-success"><%= companyStatus%></span></td>
                            <%  } else if (companyStatus.equals("Inactive")) { %>
                            <td><span class="label label-danger"><%= companyStatus%></span></td>
                            <%  }   %>
                        </tr>
                        <%      }   %>
                        <%  }   %>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>