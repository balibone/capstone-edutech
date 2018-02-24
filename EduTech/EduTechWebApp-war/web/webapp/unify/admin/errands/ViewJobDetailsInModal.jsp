<!-- ***************************************************************************************
*   Title:                  ViewJobDetailsInModal.jsp
*   Purpose:                DETAILED INFORMATION OF THE SELECTED JOB LISTING IN MODAL (UNIFY ADMIN)
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
        <title>Unify Admin - Job Listing Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/errands/ViewJobDetailsInModalCSS.css" rel="stylesheet" type="text/css" />

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
        <script type="text/javascript" src="js/unify/admin/basejs/leaflet/leaflet.js"></script>
        <script type="text/javascript" src="js/unify/admin/webjs/errands/ViewJobDetailsInModalJS.js"></script>
    </head>
    <body style="background-color: #FFFFFF;">
        <%
            Vector jobDetailsVec = (Vector) request.getAttribute("jobDetailsVec");
            String jobImage, jobTitle, jobCategory, jobPosterID, jobRate, jobRateType, jobDescription;
            jobImage = jobTitle = jobCategory = jobPosterID = jobRate = jobRateType = jobDescription = "";

            String jobStatus, jobNumOfLikes, jobPostingDate, jobWorkDate;
            jobStatus = jobNumOfLikes = jobPostingDate = jobWorkDate = "";

            String jobStartLocation, jobStartLat, jobStartLong, jobEndLocation, jobEndLat, jobEndLong, jobInformation;
            jobStartLocation = jobStartLat = jobStartLong = jobEndLocation = jobEndLat = jobEndLong = jobInformation = "";
            
            if (jobDetailsVec != null) {
                jobImage = (String) jobDetailsVec.get(0);
                jobTitle = (String) jobDetailsVec.get(1);
                jobCategory = (String) jobDetailsVec.get(2);
                jobPosterID = (String.valueOf(jobDetailsVec.get(3)));
                jobRate = (String.valueOf(jobDetailsVec.get(4)));
                jobRateType = (String) jobDetailsVec.get(5);
                jobDescription = (String) jobDetailsVec.get(6);
                jobStatus = (String) jobDetailsVec.get(7);
                jobNumOfLikes = (String.valueOf(jobDetailsVec.get(8)));
                jobPostingDate = (String.valueOf(jobDetailsVec.get(9)));
                jobWorkDate = (String.valueOf(jobDetailsVec.get(10)));
                jobStartLocation = (String) jobDetailsVec.get(11);
                jobStartLat = (String) jobDetailsVec.get(12);
                jobStartLong = (String) jobDetailsVec.get(13);
                jobEndLocation = (String) jobDetailsVec.get(14);
                jobEndLat = (String) jobDetailsVec.get(15);
                jobEndLong = (String) jobDetailsVec.get(16);
                jobInformation = (String) jobDetailsVec.get(17);
            }
        %>
        <table class="formFields" border="0">
            <tr>
                <td colspan="2" style="text-align: left;">
                    <button class="btn btn-sm" onclick="window.location.href='ErrandsAdmin?pageTransit=goToViewJobCategoryDetails&jobCategoryID=<%= request.getAttribute("urlJobCategoryID")%>'">
                        <i class="fa fa-backward"></i>&nbsp;&nbsp;Back to Job Category Details
                    </button>
                </td>
            </tr>
            <tr><td colspan="2" style="text-align: left;"><h3><strong><%= jobTitle%></strong></h3></td></tr>
            <tr><td colspan="2">&nbsp;</td></tr>
            <tr>
                <td>
                    <div class="form-group">
                        <div class="image-upload">
                            <img id="output-image" src="uploads/unify/images/errands/job/<%= jobImage%>" />
                        </div>
                        <label><%= jobNumOfLikes%>&nbsp;Likes</label>
                    </div>
                </td>
                <td>
                    <table id="itemInfoTD" class="table-no-inner-border">
                        <tr><td>Job Category:</td><td><strong><%= jobCategory%></strong></td></tr>
                        <%  if (jobStatus.equals("Available")) {%>
                        <tr><td>Job Status:</td><td><span class="label label-success"><%= jobStatus%></span></td></tr>
                        <%  } else if (jobStatus.equals("Reserved")) {%>
                        <tr><td>Job Status:</td><td><span class="label label-warning"><%= jobStatus%></span></td></tr>
                        <%  } else if (jobStatus.equals("Completed")) {%>
                        <tr><td>Job Status:</td><td><span class="label label-danger"><%= jobStatus%></span></td></tr>
                        <%  }%>
                        <tr><td>Job Rate:</td><td><strong>$<%= jobRate%>/<%= jobRateType%></strong></td></tr>
                        <tr><td>Job Work Date:</td><td><strong><%= jobWorkDate%></strong></td></tr>
                        <tr><td colspan="2">Job Description:<br/><strong><%= jobDescription%></strong></td></tr>
                    </table>
                </td>
            </tr>
            <tr style="text-align: center;">
                <td colspan="2">
                    <button type="button" class="btn btn-primary" onclick="return window.open('ErrandsAdmin?pageTransit=deleteAJob&jobID=<%= request.getAttribute("urlJobID")%>', '_parent')">Delete Job</button>&nbsp;&nbsp;
                </td>
            </tr>
        </table>

        <div style="margin: 30px 20px 0 20px">
            <div class="tabbable tabbable-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#jobInfo" data-toggle="tab">Errands Information</a></li>
                    <li><a href="#transactionList" data-toggle="tab">Transaction List</a></li>
                    <li><a href="#jobReviews" data-toggle="tab">Job Reviews</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="jobInfo">
                        <table class="table table-hover table-bordered">
                            <tr>
                                <td>Job Poster ID</td>
                                <td><%= jobPosterID%></td>
                            </tr>
                            <tr>
                                <td>Job Posting Date</td>
                                <td><%= jobPostingDate%></td>
                            </tr>
                            <tr>
                                <td>Job Route</td>
                                <td>
                                    <input type="hidden" id="dbJobStartLocation" value="<%= jobStartLocation%>" />
                                    <input type="hidden" id="dbJobStartLat" value="<%= jobStartLat%>" />
                                    <input type="hidden" id="dbJobStartLong" value="<%= jobStartLong%>" />
                                    <input type="hidden" id="dbJobEndLocation" value="<%= jobEndLocation%>" />
                                    <input type="hidden" id="dbJobEndLat" value="<%= jobEndLat%>" />
                                    <input type="hidden" id="dbJobEndLong" value="<%= jobEndLong%>" />
                                    Job Start Location: <strong><%= jobStartLocation%></strong><br/>
                                    Job End Location: <strong><%= jobEndLocation%></strong><br/>
                                    <div id="errandsMap" style="width: auto; height: 300px; margin-top: 10px;"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>Job Information</td>
                                <td><%= jobInformation%></td>
                            </tr>
                        </table>
                    </div>
                    <div class="tab-pane" id="transactionList">
                        <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                            <thead>
                                <tr>
                                    <th data-class="expand">Transaction Date</th>
                                    <th data-class="expand">Job Poster ID</th>
                                    <th data-class="expand">Job Taker ID</th>
                                    <th data-hide="phone">Job Rate</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Vector> jobTransList = (ArrayList) request.getAttribute("jobTransList");
                                    if (!jobTransList.isEmpty()) {
                                        for (int i = 0; i <= jobTransList.size() - 1; i++) {
                                            Vector v = jobTransList.get(i);
                                            String jobTransDate = String.valueOf(v.get(0));
                                            String jobTransPosterID = String.valueOf(v.get(1));
                                            String jobTransTakerID = String.valueOf(v.get(2));
                                            String jobTransRate = String.valueOf(v.get(3));
                                            String jobTransRateType = String.valueOf(v.get(4));
                                %>
                                <tr>
                                    <td><%= jobTransDate%></td>
                                    <td><%= jobTransPosterID%></td>
                                    <td><%= jobTransTakerID%></td>
                                    <td>$<%= jobTransRate%>/<%= jobTransRateType%></td>
                                </tr>
                                <%      }   %>
                                <%  }   %>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="jobReviews">
                        <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                            <thead>
                                <tr>
                                    <th data-class="expand">Review Date</th>
                                    <th data-class="expand">Reviewer ID</th>
                                    <th data-class="expand">Receiver ID</th>
                                    <th data-hide="phone">Review Rating</th>
                                    <th data-hide="phone">Review Content</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Vector> jobReviewList = (ArrayList) request.getAttribute("jobReviewList");
                                    if (!jobReviewList.isEmpty()) {
                                        for (int i = 0; i <= jobReviewList.size() - 1; i++) {
                                            Vector v = jobReviewList.get(i);
                                            String jobReviewDate = String.valueOf(v.get(0));
                                            String jobReviewerID = String.valueOf(v.get(1));
                                            String jobReviewReceiverID = String.valueOf(v.get(2));
                                            String jobReviewRating = String.valueOf(v.get(3));
                                            String jobReviewContent = String.valueOf(v.get(4));
                                %>
                                <tr>
                                    <td><%= jobReviewDate%></td>
                                    <td><%= jobReviewerID%></td>
                                    <td><%= jobReviewReceiverID%></td>
                                    <td><%= jobReviewRating%></td>
                                    <td><%= jobReviewContent%></td>
                                </tr>
                                <%      }   %>
                                <%  }   %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>