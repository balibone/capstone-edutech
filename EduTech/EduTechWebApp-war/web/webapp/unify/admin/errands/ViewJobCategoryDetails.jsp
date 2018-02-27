<!-- ***************************************************************************************
*   Title:                  ViewJobCategoryDetails.jsp
*   Purpose:                DETAILED INFORMATION OF THE SELECTED JOB CATEGORY (UNIFY ADMIN)
*   Created By:             CHEN MENG
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
        <title>Unify Admin - Job Category Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/weblayout/errands/ViewJobCategoryDetailsCSS.css" rel="stylesheet" type="text/css" />

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
        <script src="js/unify/admin/webjs/errands/ViewJobCategoryDetailsJS.js" type="text/javascript"></script>
        
        
        
    </head>
    <body style="background-color: #FFFFFF;">
        <%
                    String successMessage = (String) request.getAttribute("successMessage");
                    if (successMessage != null) {
                %>
                <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeSuccess">&times;</button><%= successMessage%>
                </div>
                <%  } %>
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeError">&times;</button><%= errorMessage%>
                </div>
                <%  } %>
                
        <%  
            Vector jobCategoryDetailsVec = (Vector) request.getAttribute("jobCategoryDetailsVec");
            String categoryImage, categoryName, categoryType, categoryDescription, categoryActiveStatus;
            categoryImage = categoryName = categoryType = categoryDescription = categoryActiveStatus = "";

            if (jobCategoryDetailsVec != null) {
                categoryImage = (String) jobCategoryDetailsVec.get(0);
                categoryName = (String) jobCategoryDetailsVec.get(1);
                categoryType = (String) jobCategoryDetailsVec.get(2);
                categoryDescription = (String) jobCategoryDetailsVec.get(3);
                categoryActiveStatus = (String.valueOf(jobCategoryDetailsVec.get(4)));
            }
        %>
        <form id="jobCategoryDetailsForm" action="ErrandsAdmin" method="POST" enctype="multipart/form-data" target="_self">
            <table class="formFields" border="0">
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
                        <%  if (categoryActiveStatus.equals("Active")) {    %>
                        <div class="form-group">
                            <div class="input-group">
                                <span>Category Status:&nbsp;&nbsp;<span class="label label-success"><%= categoryActiveStatus%></span>
                            </div>
                        </div>
                        <%  } else if (categoryActiveStatus.equals("Inactive")) {   %>
                        <div class="form-group">
                            <div class="input-group">
                                <span>Category Status:&nbsp;&nbsp;<span class="label label-danger"><%= categoryActiveStatus%></span>
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
                        <input type="hidden" name="pageTransit" value="updateJobCategory" />
                        <input type="hidden" name="hiddenJobCategoryID" value="<%= request.getAttribute("urlJobCategoryID")%>"/>
                        <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                        <input type="hidden" name="oldCategoryImage"  value="<%= categoryImage%>" />
                        <button type="button" class="btn btn-primary" onclick="jobCategoryDetailsForm.submit();">Update Job Category</button>&nbsp;&nbsp;

                        <%  if (categoryActiveStatus.equals("Active")) {%>
                        <button type="button" class="btn btn-primary" onclick="javascript:AlertIt(<%= request.getAttribute("urlJobCategoryID")%>)">Deactivate Job Category</button>
                        
                        <%  } else if (categoryActiveStatus.equals("Inactive")) {%>
                        <button type="button" class="btn btn-primary" onclick="window.open('ErrandsAdmin?pageTransit=activateAJobCategory&hiddenJobCategoryID=<%= request.getAttribute("urlJobCategoryID")%>','_self')">Activate Job Category</button>
                        <%  }   %>
                    </td>
                </tr>
            </table>
            
            <div style="margin: 40px 20px 0 20px">
                <h5 style="margin-bottom: 20px;"><strong><u>List of jobs under this "<%= categoryName%>" category</u></strong></h5>
                
                <table id="associatedJobList" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                        <tr>
                            <th data-class="expand">Job Image</th>
                            <th data-class="expand">Job Name</th>
                            <th>Job Poster ID</th>
                            <th>Job Rate</th>
                            <th data-hide="phone">Job Start Date</th>
                            <th data-hide="phone">Job Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Vector> associatedJobList = (ArrayList) request.getAttribute("associatedJobList");
                            if (!associatedJobList.isEmpty()) {
                                for (int i = 0; i <= associatedJobList.size() - 1; i++) {
                                    Vector v = associatedJobList.get(i);
                                    String jobID = String.valueOf(v.get(0));
                                    String jobCategoryID = String.valueOf(v.get(1));
                                    String jobImage = String.valueOf(v.get(2));
                                    String jobTitle = String.valueOf(v.get(3));
                                    String jobPosterID = String.valueOf(v.get(4));
                                    String jobRate = String.valueOf(v.get(5));
                                    String jobRateType = String.valueOf(v.get(6));
                                    String jobWorkDate = String.valueOf(v.get(7));
                                    String jobStatus = String.valueOf(v.get(8));
                        %>
                        <tr>
                            <td><img src="uploads/unify/images/errands/job/<%= jobImage%>" style="max-width: 50px; max-height: 50px;" /></td>
                            <td><%= jobTitle%><span style="display: none;">;<%= jobID%>;<%= jobCategoryID%></span></td>
                            <td><%= jobPosterID%></td>
                            <td>$<%= jobRate%>/<%= jobRateType%></td>
                            <td><%= jobWorkDate%></td>
                            <%  if (jobStatus.equals("Available")) {   %>
                            <td><span class="label label-success"><%= jobStatus%></span></td>
                            <%  } else if (jobStatus.equals("Reserved")) { %>
                            <td><span class="label label-warning"><%= jobStatus%></span></td>
                            <%  } else if (jobStatus.equals("Completed")) { %>
                            <td><span class="label label-danger"><%= jobStatus%></span></td>
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