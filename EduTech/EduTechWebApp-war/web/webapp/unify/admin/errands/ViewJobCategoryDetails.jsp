<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - View Job Category Details</title>

        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminCommonCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/errands/Details-iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/errands/ViewJobCategoryDetailsCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body style="background-color: white;">
        <%
            Vector jobCategoryDetailsVec = (Vector) request.getAttribute("jobCategoryDetailsVec");
            String categoryImage, categoryID, categoryName, categoryType, categoryDescription, categoryActiveStatus, activeStatus;
            categoryImage = categoryID = categoryName = categoryType = categoryDescription = categoryActiveStatus = activeStatus = "";
            
            if (jobCategoryDetailsVec != null) {
                categoryImage = (String.valueOf(jobCategoryDetailsVec.get(0)));
                categoryID = (String.valueOf(jobCategoryDetailsVec.get(1)));
                categoryName = (String) jobCategoryDetailsVec.get(2);
                categoryType = (String) jobCategoryDetailsVec.get(3);
                categoryDescription = (String) jobCategoryDetailsVec.get(4);
                categoryActiveStatus = (String)jobCategoryDetailsVec.get(5);
            }
        %>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="ErrandsAdmin" method="POST" enctype="multipart/form-data" target="_parent">
                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                        <div class="image-upload">
                            <img id="output-image" src="uploads/unify/images/common/category/<%= categoryImage %>" />
                        </div>
                        <label id="image-button" for="file-upload" class="btn btn-outline btn-primary btn-sm btn-block" style="margin-top: 10px;">
                            <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                        </label>
                        <input id="file-upload" name="itemImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" />
                    </div>
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                        <div class="form-group">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12">Category Type:&nbsp;&nbsp;
                                <u><%= categoryType %></u>
                            </label>
                       
                        <%
                            if(categoryActiveStatus.equals("Active")) {
                                activeStatus = "Active";
                        %>
                        
                        
                            <label class="control-label col-md-12 col-sm-12 col-xs-12">Category Status:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= activeStatus %></span></u>
                            </label>
                        </div>
                        <%  } else {
                                activeStatus = "Inactive";
                        %>
                            <label class="control-label col-md-12 col-sm-12 col-xs-12">Category Status:&nbsp;&nbsp;
                                <u><span style="color: #FF7878;"><%= activeStatus %></span></u>
                            </label>
                        </div>
                        
                        <%  
                            }
                        %>
                        
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="<%= categoryName %>" name="categoryName" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <textarea rows="5" class="form-control has-feedback-left" placeholder="<%= categoryDescription %>" name="categoryDescription"></textarea>
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                    </div>
            </div>
                            
            <div class="ln_solid"></div>
            <div class="form-group">
                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                    <table border="0" style="margin-left: 65%">
                        <tr>
                            <td>
                                <input type="hidden" name="pageTransit" value="updateJobCategory"/>
                                <input type="hidden" name="hiddenCategoryID" value=<%=categoryID%> />
                                <input type="hidden" name="oldCategoryName" value="<%= categoryName %>" />
                                <input type="hidden" name="hiddenCategoryType" value="<%= categoryType %>" />
                                <input type="hidden" name="oldCategoryDescription" value="<%= categoryDescription %>" />
                                <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                                <input type="hidden" name="oldCategoryImage"  value="<%= categoryImage %>" />
                                <button type="submit" class="btn btn-primary">Update Category</button></form>
                                
                            </td>
                            <% 
                                if(activeStatus.equals("Active")) {
                            %>
                            <td>
                                <form action="ErrandsAdmin" method="POST" target="_parent">
                                    <input type="hidden" name="pageTransit" value="deactivateJobCategory"/>
                                    <input type="hidden" name="hiddenCategoryID" value="<%= categoryID %>"/>
                                    <input type="hidden" name="hiddenCategoryType" value="<%= categoryType %>"/>
                                    <button type="submit" class="btn btn-primary">Deactivate Category</button>
                                </form>
                            </td>
                            <%
                                } else if(activeStatus.equals("Inactive")) {
                            %>
                            <td>
                                <form action="ErrandsAdmin" method="POST" target="_parent">
                                    <input type="hidden" name="pageTransit" value="activateJobCategory"/>
                                    <input type="hidden" name="hiddenCategoryID" value="<%= categoryID %>"/>
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
    

        
        <div class="right_col" role="main">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12" style="margin-top: 8px">
                    <div class="x_content">
                        <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>Job ID</th>
                                    <th>Job Title</th>
                                    <th>Job Description</th>
                                    <th>Work Date</th>
                                    <th>Job Status</th>
                                    <th>Operation</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Vector> jobList = (ArrayList) request.getAttribute("categoryJobList");
                                    if (!jobList.isEmpty()) {
                                %>
                                <%
                                    for (int i = 0; i <= jobList.size() - 1; i++) {
                                        Vector v = jobList.get(i);
                                        String jobID = String.valueOf(v.get(0));
                                        String jobTitle = String.valueOf(v.get(1));
                                        String jobDescription = String.valueOf(v.get(2));
                                        String workDate = String.valueOf(v.get(3));
                                        String jobStatus = String.valueOf(v.get(4));
                                 %>
                                <tr>
                                    <td><%= jobID%></td>
                                    <td><%= jobTitle%></td>
                                    <td><%= jobDescription%></td>
                                    <td><%= workDate%></td>
                                    <td><%= jobStatus%></td>
                                    <td align="center">
                                        <form action="ErrandsAdmin" method="POST" onsubmit="return confirm('Do you really want to delete this job listing?');">
                                            <input type="hidden" name="pageTransit" value="deleteAJobListingViaCategory" />
                                            <input type="hidden" name="hiddenJobID" value="<%= jobID %>" />
                                            <input type="hidden" name="hiddenCategory" value="<%= categoryID%>"/>
                                            <button type="submit" class="btn btn-success">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                                
                                <%      }   
                                %>
                                <%  }
                                %>
                            </tbody>
                        </table>
                       </div>     
                    </div>
                </div>

           
                <div id="modal-iframe" class="iziModal-content">
                    <button data-iziModal-close class="icon-close"></button>
                </div>
                <div id="adminFooter"></div>
            </div>
        

                           
        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/errands/ViewJobCategoryDetailsJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/errands/ViewJobListingInCategoryJS.js" type="text/javascript"></script>
        
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
    </body>
</html>
