<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - View Item Details</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminCommonCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/voices/ViewCompanyCategoryDetailsCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
    </head>
    <body style="background-color: transparent;">
        <%
            Vector categoryDetailsVec = (Vector) request.getAttribute("categoryDetailsVec");
            String categoryName, categoryType, categoryDescription, categoryActiveStatus, categoryImage;
            categoryName = categoryType = categoryDescription = categoryActiveStatus = categoryImage = "";
            
            if (categoryDetailsVec != null) {
                categoryName = (String) categoryDetailsVec.get(0);
                categoryType = (String) categoryDetailsVec.get(1);
                categoryDescription = (String.valueOf(categoryDetailsVec.get(2)));
                categoryActiveStatus = (String.valueOf(categoryDetailsVec.get(3)));
                categoryImage = (String.valueOf(categoryDetailsVec.get(4)));
            }
        %>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="VoicesAdmin" method="POST" enctype="multipart/form-data" target="_parent">
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                        <div class="image-upload">
                            <img id="output-image" src="uploads/unify/images/voices/category/<%= categoryImage %>" />
                        </div>
                        <label for="file-upload" class="btn btn-outline btn-primary btn-sm btn-block" style="margin-top: 10px;">
                            <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                        </label>
                        <input id="file-upload" name="categoryImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" />
                    </div>
                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="<%= categoryName %>" name="categoryName" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="form-group" style="margin-top: 7px;">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12">Category Type:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= categoryType %></span></u>
                            </label>
                        </div>
                        <%
                            if(categoryActiveStatus.equals("true")) {
                                categoryActiveStatus = "Active";
                        %>
                        <div class="form-group" style="margin-top: 7px;">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12">Category Status:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= categoryActiveStatus %></span></u>
                            </label>
                        </div>
                        <%  } else {
                                categoryActiveStatus = "Inactive";
                        %>
                        <div class="form-group" style="margin-top: 7px;">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12">Category Status:&nbsp;&nbsp;
                                <u><span style="color: #FF7878;"><%= categoryActiveStatus %></span></u>
                            </label>
                        </div>
                        <%  }   %>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <textarea rows="5" class="form-control has-feedback-left" placeholder="<%= categoryDescription %>" name="categoryDescription"></textarea>
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                    </div>
            </div>			
        </div>
        <div class="form-group" style="margin-top: 20px;">
            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <table border="0" style="margin-left: 320px;">
                    <tr>
                        <td>
                                <input type="hidden" name="pageTransit" value="updateCompanyCategory"/>
                                <input type="hidden" name="oldCategoryName" value="<%= categoryName %>" />
                                <input type="hidden" name="hiddenCategoryType" value="<%= categoryType %>" />
                                <input type="hidden" name="oldCategoryDescription" value="<%= categoryDescription %>" />
                                <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                                <input type="hidden" name="oldCategoryImage"  value="<%= categoryImage %>" />
                                <button type="submit" class="btn btn-primary">Update Category</button></form>
                            </td> 
                        <% if(categoryActiveStatus.equals("Active")) {
                            %>
                            <td>
                                <form action="VoicesAdmin" method="POST" target="_parent">
                                    <input type="hidden" name="pageTransit" value="deactivateCategory"/>
                                    <input type="hidden" name="hiddenCategoryName" value="<%= categoryName %>"/>
                                    <input type="hidden" name="hiddenCategoryType" value="<%= categoryType %>"/>
                                    <button type="submit" class="btn btn-primary">Deactivate Category</button>
                                </form>
                            </td>
                            <%
                                } else if(categoryActiveStatus.equals("Inactive")) {
                            %>
                            <td>
                                <form action="VoicesAdmin" method="POST" target="_parent">
                                    <input type="hidden" name="pageTransit" value="activateCategory"/>
                                    <input type="hidden" name="hiddenCategoryName" value="<%= categoryName %>"/>
                                    <input type="hidden" name="hiddenCategoryType" value="<%= categoryType %>"/>
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
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div class="x_panel">
                <div class="x_content">
                    <div class role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                            <li role="Company" class="active">
                                <a href="ViewCompanyList" id="review-tab" role="tab" data-toggle="tab" aria-expanded="true">Companies</a>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade active in" id="companyList" aria-labelledby="company-tab">
                                <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Company Image</th>
                                            <th>Company Name</th>
                                            <th>Company Average Rating</th>
                                            <th>Company Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Vector> companyList = (ArrayList) request.getAttribute("companyListVec");
                                            if (!companyList.isEmpty()) {
                                                for (int i = 0; i <= companyList.size() - 1; i++) {
                                                    Vector v = companyList.get(i);
                                                    String companyName = String.valueOf(v.get(0));
                                                    String companyAverageRating = String.valueOf(v.get(1));
                                                    String companyStatus = String.valueOf(v.get(2));
                                                    String companyImage = String.valueOf(v.get(4));
                                        %>
                                        <tr>
                                            <td><img src="uploads/unify/images/voices/company/<%= companyImage%>" style="max-width: 50px; max-height: 50px;" /></td>
                                            <td><%= companyName %></td>
                                            <td><%= companyAverageRating %></td>
                                            <td><%= companyStatus %></td>
                                            <td><form action="VoicesAdmin" method="POST" target="_parent">
                                                    <input type="hidden" name="pageTransit" value="goToViewCategoryCompanyReviewList"/>
                                                    <input type="hidden" name="hiddenCategoryCompany" value="<%= companyName %>"/>
                                                    <button type="submit" class="btn btn-success btn-xs" style="margin-left: 20px">View</button>
                                                </form>   
                                            </td>
                                        </tr>
                                        <%      }   %>
                                        <%  }%>
                                    </tbody> 
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ln_solid"></div>

        <div id="modal-iframe"></div>
        <div id="adminFooter"></div>                            
        
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/voices/ViewCompanyCategoryDetailsJS.js" type="text/javascript"></script>
        
        
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
    </body>
</html>
