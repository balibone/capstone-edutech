<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
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
        <link href="css/unify/admin/weblayout/voices/ViewCompanyListDetailsCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
    </head>
    <body style="background-color: #FFFFFF;">
        <%
            Vector companyDetailsVec = (Vector) request.getAttribute("data");
            String companyName, companyWebsite, companyHQ, companyAverageRating, companySize, companyStatus, companyIndustry, companyDescription, companyImage;
            companyName = companyWebsite = companyHQ = companyAverageRating = companySize = companyStatus = companyIndustry = companyDescription = companyImage = "";
            
            if (companyDetailsVec != null) {
                companyName = (String) companyDetailsVec.get(0);
                companyWebsite = (String) companyDetailsVec.get(1);
                companyHQ = (String) companyDetailsVec.get(2);
                companyAverageRating = (String.valueOf(companyDetailsVec.get(3)));
                companySize = (String.valueOf(companyDetailsVec.get(4)));
                companyStatus = (String.valueOf(companyDetailsVec.get(5)));
                companyIndustry = (String.valueOf(companyDetailsVec.get(6)));
                companyDescription = (String.valueOf(companyDetailsVec.get(7)));
                companyImage = (String.valueOf(companyDetailsVec.get(8)));
            }
        %>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="VoicesAdmin" method="POST" enctype="multipart/form-data" target="_parent">
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                        <div class="image-upload">
                            <img id="output-image" src="uploads/unify/images/voices/company/<%= companyImage %>" />
                        </div>
                        <label for="file-upload" class="btn btn-outline btn-primary btn-sm btn-block" style="margin-top: 10px;">
                            <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                        </label>
                        <input id="file-upload" name="companyImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" />
                    </div>
                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companyName %>" name="companyName" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companyHQ %>" name="companyHQ" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                            </div>    
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="<%= companyWebsite %>" name="companyWebsite" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companySize %>" name="companySize" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companyIndustry %>" name="companyIndustry" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>   
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 7px;">
                            <label class="control-label col-md-6 col-sm-6 col-xs-12">Company Average Rating:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= companyAverageRating %></span></u>
                            </label>
                            <label class="control-label col-md-6 col-sm-6 col-xs-12">Company Status:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= companyStatus %></span></u>
                            </label>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <textarea rows="5" class="form-control has-feedback-left" placeholder="<%= companyDescription %>" name="companyDescription"></textarea>
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                    </div>
            </div>			
        </div>
        <div class="form-group" style="margin-top: 20px;">
            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <table border="0" style="margin: auto;">
                    <tr>
                        <td>
                                <input type="hidden" name="pageTransit" value="updateCompany"/>
                                <input type="hidden" name="oldCompanyName" value="<%= companyName %>" />
                                <input type="hidden" name="oldCompanyWebsite" value="<%= companyWebsite %>" />
                                <input type="hidden" name="oldCompanyHQ" value="<%= companyHQ %>" />
                                <input type="hidden" name="oldCompanySize" value="<%= companySize %>" />
                                <input type="hidden" name="oldCompanyIndustry" value="<%= companyIndustry %>" />
                                <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                                <input type="hidden" name="oldCompanyImage"  value="<%= companyImage %>" />
                                <input type="hidden" name="oldCompanyDescription"  value="<%= companyDescription %>" />
                                <button type="submit" class="btn btn-primary">Update Company</button></form>
                            </td>  
                        <% if(companyStatus.equals("Active")) {
                            %>
                            <td>
                                <form action="VoicesAdmin" method="POST" target="_parent">
                                    <input type="hidden" name="pageTransit" value="deactivateCompany"/>
                                    <input type="hidden" name="hiddenCompanyName" value="<%= companyName %>"/>
                                    <button type="submit" class="btn btn-primary">Deactivate Company</button>
                                </form>
                            </td>
                            <%
                                } else if(companyStatus.equals("Inactive")) {
                            %>
                            <td>
                                <form action="VoicesAdmin" method="POST" target="_parent">
                                    <input type="hidden" name="pageTransit" value="activateCompany"/>
                                    <input type="hidden" name="hiddenCompanyName" value="<%= companyName %>"/>
                                    <button type="submit" class="btn btn-primary">Activate Company</button>
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
                            <li role="Review" class="active">
                                <a href="ViewReviewList" id="review-tab" role="tab" data-toggle="tab" aria-expanded="true">Reviews</a>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade active in" id="reviewList" aria-labelledby="review-tab">
                                <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Review Title</th>
                                            <th>Reviewed Company</th>
                                            <th>Review Poster ID</th>
                                            <th>Review Date</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Vector> reviewList = (ArrayList) request.getAttribute("reviewListVec");
                                            if (!reviewList.isEmpty()) {
                                                for (int i = 0; i <= reviewList.size() - 1; i++) {
                                                    Vector v = reviewList.get(i);
                                                    String reviewTitle = String.valueOf(v.get(0));
                                                    String reviewedCompany = String.valueOf(v.get(1));
                                                    String reviewPosterID = String.valueOf(v.get(2));
                                                    String reviewDate = String.valueOf(v.get(3));
                                        %>
                                        <tr>
                                            <td><%= reviewTitle %></td>
                                            <td><%= reviewedCompany %></td>
                                            <td><%= reviewPosterID %></td>
                                            <td><%= reviewDate %></td>
                                            <td>
                                                <form action="VoicesAdmin" method="POST">
                                                    <input type="hidden" name="pageTransit" value="deleteReview"/>
                                                    <input type="hidden" name="hiddenReviewedCompany" value="<%= reviewedCompany %>"/>
                                                    <input type="hidden" name="hiddenReviewPosterID" value="<%= reviewPosterID %>"/>
                                                    <button id="delete" type="submit" class="btn btn-success btn-xs" style="margin-left: 15px" id="deleteReview" onClick="return confirm('Do you want to delete the review?')">Delete</button>
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
        <div id="reviewDetails-iframe" class="iziModal-content">
            <button data-iziModal-close class="icon-close"></button>
        </div>
        <div id="adminFooter"></div>                            
        
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/voices/ViewCompanyListDetailsJS.js" type="text/javascript"></script>
        
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
    </body>
</html>
