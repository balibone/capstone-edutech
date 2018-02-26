<!-- ***************************************************************************************
*   Title:                  ViewCompanyListDetailsInModal.jsp
*   Purpose:                DETAILED INFORMATION OF THE SELECTED COMPANY IN MODAL (UNIFY ADMIN)
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
        <title>Unify Admin - Company Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/easy-autocomplete/easy-autocomplete.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/voices/ViewCompanyListDetailsInModalCSS.css" rel="stylesheet" type="text/css" />

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
        <script type="text/javascript" src="js/unify/admin/basejs/easy-autocomplete/jquery.easy-autocomplete.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/easy-autocomplete/jquery.easy-autocomplete.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/leaflet/leaflet.js"></script>
        <script type="text/javascript" src="js/unify/admin/webjs/voices/ViewCompanyListDetailsInModalJS.js"></script>
    </head>
    <body style="background-color: #FFFFFF;">
        <%
            Vector companyDetailsVec = (Vector) request.getAttribute("companyDetailsVec");
            String companyName, companyImage, companyIndustry, companyAverageRating, companyStatus;
            companyName = companyImage = companyIndustry = companyAverageRating = companyStatus = "";
            
            String companyWebsite, companyHQ, companySize, companyDescription, companyAddress;
            companyWebsite = companyHQ = companySize = companyDescription = companyAddress = "";
            
            if (companyDetailsVec != null) {
                companyName = (String) companyDetailsVec.get(0);
                companyImage = (String) companyDetailsVec.get(1);
                companyIndustry = (String) companyDetailsVec.get(2);
                companyAverageRating = (String.valueOf(companyDetailsVec.get(3)));
                companyStatus = (String) companyDetailsVec.get(4);
                companyWebsite = (String) companyDetailsVec.get(5);
                companyHQ = (String) companyDetailsVec.get(6);
                companySize = (String.valueOf(companyDetailsVec.get(7)));
                companyDescription = (String) companyDetailsVec.get(8);
                companyAddress = (String) companyDetailsVec.get(9);
            }
        %>
        <form id="companyDetailsForm" action="VoicesAdmin" method="POST" enctype="multipart/form-data" target="_parent">
            <table class="formFields" border="0">
                <tr>
                    <td colspan="2" style="text-align: left;">
                        <button class="btn btn-sm" onclick="window.location.href='VoicesAdmin?pageTransit=goToViewCompanyCategoryDetails&companyCategoryID=<%= request.getAttribute("urlCompanyCategoryID")%>'">
                            <i class="fa fa-backward"></i>&nbsp;&nbsp;Back to Industry Details
                        </button>
                    </td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr>
                    <td colspan="2" style="text-align: left;">
                        <input type="hidden" name="oldCompanyName" value="<%= companyName%>" />
                        <input type="text" name="companyName" placeholder="<%= companyName%>" style="font-weight: bold; font-size: 24px;" />
                    </td>
                </tr>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr>
                    <td rowspan="2">
                        <div class="form-group">
                            <div class="image-upload">
                                <img id="output-image" src="uploads/unify/images/voices/company/<%= companyImage%>" />
                            </div>
                            <label for="file-upload" class="btn btn-default btn-sm btn-block" style="margin-top: 10px; width: 151px;">
                                <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                            </label>
                            <input id="file-upload" name="companyImage" type="file" accept="image/*" required="required" onchange="javascript: previewImage(event)" />
                        </div>
                    </td>
                    <td>
                        <table id="itemInfoTD" class="table-no-inner-border">
                            <tr>
                                <td style="vertical-align: middle;">Company Industry:&nbsp;</td>
                                <td>
                                    <input type="hidden" id="oldCompanyIndustry" name="oldCompanyIndustry" value="<%= companyIndustry%>" />
                                    <input type="hidden" id="dbCompanyIndustry" value="<%= request.getAttribute("companyIndustryStr")%>" />
                                    <select class="form-control" id="companyIndustry" name="companyIndustry">
                                    </select>
                                </td>
                            </tr>
                            <%  if (Double.parseDouble(companyAverageRating) >= 4.0) {%>
                            <tr><td>Company Rating:&nbsp;</td><td><span class="label label-success"><%= companyAverageRating%></span></td></tr>
                            <%  } else if (Double.parseDouble(companyAverageRating) >= 2.5 && Double.parseDouble(companyAverageRating) < 4.0) {%>
                            <tr><td>Company Rating:&nbsp;</td><td><span class="label label-warning"><%= companyAverageRating%></span></td></tr>
                            <%  } else if (Double.parseDouble(companyAverageRating) < 2.5) {%>
                            <tr><td>Company Rating:&nbsp;</td><td><span class="label label-danger"><%= companyAverageRating%></span></td></tr>
                            <%  }%>
                            <%  if (companyStatus.equals("Active")) {%>
                            <tr><td>Company Status:&nbsp;</td><td><span class="label label-success"><%= companyStatus%></span></td></tr>
                            <%  } else if (companyStatus.equals("Inactive")) {%>
                            <tr><td>Company Status:&nbsp;</td><td><span class="label label-danger"><%= companyStatus%></span></td></tr>
                            <%  }%>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="pageTransit" value="updateCompany"/>
                        <input type="hidden" name="hiddenCompanyID" value="<%= request.getAttribute("urlCompanyID")%>"/>
                        <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                        <input type="hidden" name="oldCompanyImage"  value="<%= companyImage%>" />
                        <button type="button" class="btn btn-primary" onclick="companyDetailsForm.submit();">Update Company</button>&nbsp;&nbsp;

                        <%  if (companyStatus.equals("Active")) {%>
                        <button type="button" class="btn btn-primary" onclick="javascript:AlertIt(<%= request.getAttribute("urlCompanyID")%>)">Deactivate Company</button>
                        <%--<button type="button" class="btn btn-primary" onclick="window.open('VoicesAdmin?pageTransit=deactivateACompany&hiddenCompanyID=<%= request.getAttribute("urlCompanyID")%>','_parent')">Deactivate Company</button>--%>
                        <%  } else if (companyStatus.equals("Inactive")) {%>
                        <button type="button" class="btn btn-primary" onclick="window.open('VoicesAdmin?pageTransit=activateACompany&hiddenCompanyID=<%= request.getAttribute("urlCompanyID")%>','_parent')">Activate Company</button>
                        <%  }   %>
                    </td>
                </tr>
            </table>

            <div style="margin: 30px 20px 0 20px">
                <div class="tabbable tabbable-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#companyInfo" data-toggle="tab">Company Information</a></li>
                        <li><a href="#companyReviews" data-toggle="tab">Company Reviews</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="companyInfo">
                            <table class="table table-hover table-bordered">
                                <tr>
                                    <td>Company Website</td>
                                    <td>
                                        <input type="hidden" name="oldCompanyWebsite" value="<%= companyWebsite%>" />
                                        <input type="text" name="companyWebsite" placeholder="<%= companyWebsite%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Company HQ</td>
                                    <td>
                                        <input type="hidden" name="oldCompanyHQ" value="<%= companyHQ%>" />
                                        <input id="companyHQ" type="text" name="companyHQ" placeholder="<%= companyHQ%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Company Manpower Size</td>
                                    <td>
                                        <input type="hidden" name="oldCompanySize" value="<%= companySize%>" />
                                        <input type="text" name="companySize" placeholder="<%= companySize%>" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Company Description</td>
                                    <td>
                                        <input type="hidden" name="oldCompanyDescription" value="<%= companyDescription%>" />
                                        <textarea name="companyDescription" placeholder="<%= companyDescription%>"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Company Address</td>
                                    <td>
                                        <input type="hidden" name="oldCompanyAddress" value="<%= companyAddress%>" />
                                        <textarea name="companyAddress" placeholder="<%= companyAddress%>"></textarea>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="tab-pane" id="companyReviews">
                            <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                                <thead>
                                    <tr>
                                        <th data-class="expand">Review Date</th>
                                        <th data-class="expand">Poster ID</th>
                                        <th>Review Description</th>
                                        <th>Employment Type</th>
                                        <th data-hide="phone">Salary Range</th>
                                        <th data-hide="phone">Review Rating</th>
                                        <th data-hide="phone">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<Vector> companyReviewList = (ArrayList) request.getAttribute("companyReviewList");
                                        if (!companyReviewList.isEmpty()) {
                                            for (int i = 0; i <= companyReviewList.size() - 1; i++) {
                                                Vector v = companyReviewList.get(i);
                                                String reviewDate = String.valueOf(v.get(0));
                                                String reviewPosterID = String.valueOf(v.get(1));
                                                String reviewTitle = String.valueOf(v.get(2));
                                                String reviewPros = String.valueOf(v.get(3));
                                                String reviewCons = String.valueOf(v.get(4));
                                                String reviewEmpType = String.valueOf(v.get(5));
                                                String reviewSalaryRange = String.valueOf(v.get(6));
                                                String reviewRating = String.valueOf(v.get(7));
                                                String reviewThumbsUp = String.valueOf(v.get(8));
                                                String reviewID = String.valueOf(v.get(9));
                                    %>
                                    <tr>
                                        <td><%= reviewDate%></td>
                                        <td><%= reviewPosterID%></td>
                                        <td>
                                            <strong><%= reviewTitle%></strong><br/>
                                            <strong>Pros:</strong>&nbsp;<%= reviewPros%><br/>
                                            <strong>Cons:</strong>&nbsp;<%= reviewCons%><br/>
                                        </td>
                                        <td><%= reviewEmpType%></td>
                                        <td><%= reviewSalaryRange%></td>
                                        <td><%= reviewRating%>&nbsp;(<i class="fa fa-thumbs-up"></i><%= reviewThumbsUp%>)</td>
                                        <td>
                                             <button type="submit" class="btn btn-danger btn-xs" id="deleteReview">
                                                 <a href="VoicesAdmin?pageTransit=goToDeleteReviewInModal&hiddenCompanyID=<%= request.getAttribute("urlCompanyID")%>&hiddenReviewID=<%= reviewID%>" 
                                                    style="color:#FFFFFF;text-decoration:none;">Delete</a>
                                             </button>
                                        </td>
                                    </tr>
                                    <%      }   %>
                                    <%  }   %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>