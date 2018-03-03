<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - Reported Review Details</title>

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
        <link href="css/unify/admin/weblayout/errands/ViewJobDetailsCSS.css" rel="stylesheet" type="text/css" />


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

    </head>
    <body style="background-color: #FFFFFF;">
        <%            Vector reportListVec = (Vector) request.getAttribute("reportedReviewVec");
            String reportID, reportStatus, reportDescription, reportDate, reportedReviewID,
                    reportedPosterID, reportedReporterID, reviewCompany, reviewTitle, reviewPro, reviewCon, reviewedDate, reviewStatus;
            reportID = reportStatus = reportDescription = reportDate = reportedReviewID
                    = reportedPosterID = reportedReporterID = reviewCompany = reviewTitle = reviewPro = reviewCon = reviewedDate = reviewStatus = "";
            if (reportListVec != null) {
                reportID = (String.valueOf(reportListVec.get(0)));
                reportStatus = (String.valueOf(reportListVec.get(1)));
                reportDescription = (String.valueOf(reportListVec.get(2)));
                reportDate = (String.valueOf(reportListVec.get(3)));
                reportedReviewID = (String.valueOf(reportListVec.get(4)));
                reportedPosterID = (String.valueOf(reportListVec.get(5)));
                reportedReporterID = (String.valueOf(reportListVec.get(6)));
                reviewedDate = (String.valueOf(reportListVec.get(7)));
                //int reportListVecSize = reportListVec.size();
                //System.out.println("report list size is: "+reportListVecSize);
                if (reportListVec.size() > 8) {
                    reviewCompany = (String.valueOf(reportListVec.get(8)));
                    reviewTitle = (String.valueOf(reportListVec.get(9)));
                    reviewPro = (String.valueOf(reportListVec.get(10)));
                    reviewCon = (String.valueOf(reportListVec.get(11)));
                    reviewStatus = (String.valueOf(reportListVec.get(12)));
                }
            }
        %>



        <div style="margin: 30px 20px 0 20px">
            <div class="tabbable tabbable-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#reportInfo" data-toggle="tab">Request Details</a></li>
                    <li><a href="#reportedReview" data-toggle="tab">Details of Review Reported</a></li>
                </ul>
                <%-- tab 1 --%>
                <div class="tab-content">
                    <div class="tab-pane" id="reportedReview">
                        <table class="table table-hover table-bordered">
                            <%
                                if (reportListVec.size() > 8) {
                            %>
                            <tr>
                                <td>Review Status</td>
                                <%
                                    if (reviewStatus.equals("Delisted")) {
                                %>
                                <td><span class="label label-danger"><%= reviewStatus%></span></td>
                                    <%
                                    } else {
                                    %>
                                <td><span class="label label-success">Listed</span></td>
                                <%
                                    }
                                %>
                            </tr>
                            <tr>
                                <td>Company Reviewed</td>
                                <td><%= reviewCompany%></td>
                            </tr>
                            <tr>
                                <td>Title of Review</td>
                                <td><%= reviewTitle%></td>
                            </tr>
                            <tr>
                                <td>Review Pros</td>
                                <td><%= reviewPro%></td>
                            </tr>
                            <tr>
                                <td>Review Cons</td>
                                <td><%= reviewCon%></td>
                            </tr>
                            <%
                            } else {
                            %>
                            <div class="form-group">
                                <label class="control-label col-md-12 col-sm-12 col-xs-12"><font color = "red"> <b>REVIEW HAS BEEN DELETED FROM SYSTEM&nbsp;&nbsp;</b></font></label>
                            </div>
                            <%
                                }
                            %> 

                        </table>
                    </div>

                    <%-- next tab --%>
                    <div class="tab-pane active" id="reportInfo">
                        <table class="table table-hover table-bordered">
                            <tr>
                                <td>Report ID</td>
                                <td><%= reportID%></td>
                            </tr>
                            <tr>
                                <td>Report Status</td>
                                <%
                                    if (reportStatus.equals("Resolved (No Issue Found)")) {
                                %>
                                <td><span class="label label-success">Resolved (No Issue Found)</span></td>
                                <%
                                } else if (reportStatus.equals("Resolved (Delisted)")) {
                                %>
                                <td><span class="label label-success">Resolved (Company Review Delisted)</span></td>
                                <%
                                } else {
                                %>
                                <td><span class="label label-warning">Unresolved</span></td>
                                <%
                                    }
                                %>

                            </tr>
                            <tr>
                                <td>Report Description</td>
                                <td><%= reportDescription%></td>
                            </tr>
                            <tr>
                                <td>Report Date</td>
                                <td><%= reportDate%></td>
                            </tr>
                            <tr>
                                <td>Reported Review ID</td>
                                <td><%= reportedReviewID%></td>
                            </tr>
                            <tr>
                                <td>User ID of Review Poster</td>
                                <td><%= reportedPosterID%></td>
                            </tr>
                            <tr>
                                <td>User ID of Reporter</td>
                                <td><%= reportedReporterID%></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <hr width="80%">
        <div class="ln_solid"></div>
        <div class="form-group">
            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <table border="0" style="margin: auto;">
                    <tr>
                        <%
                            if (reportStatus.equals("Unresolved") && !reviewCompany.equals("")) {
                        %>
                        <%-- resolve and do nothing --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToAllReportedListing#companyReview" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="resolveReviewReportFromAllList"/>
                                <input type="hidden" name="reportID" value="<%= reportID%>" />
                                <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                <button type="submit" class="btn btn-primary">Resolved (Do Nothing)</button>
                            </form>
                        </td>
                        <%-- resolve and delete review --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToAllReportedListing#companyReview" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="resolveDelistReviewReportFromAllList"/>
                                <input type="hidden" name="reportID" value="<%= reportID%>" />
                                <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                <input type="hidden" name="reportedReviewID" value="<%= reportedReviewID%>" />
                                <button type="submit" class="btn btn-primary" onclick="return confirm('Confirm delisting?')">Delist Review & Resolve</button>
                            </form>
                        </td>
                        <%
                        } else if (reportStatus.equals("Unresolved") && reviewCompany.equals("")) {
                        %>
                        <%-- resolve and do nothing --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToAllReportedListing#companyReview" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="resolveReviewReportFromAllList"/>
                                <input type="hidden" name="reportID" value="<%= reportID%>" />
                                <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                <button type="submit" class="btn btn-primary">Resolved (Do Nothing)</button>
                            </form>
                        </td>

                        <%
                        } else {
                        %>
                    <tr>
                        <td>
                    <center>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"><b>NO FURTHER ACTION NEEDED&nbsp;&nbsp;</b></label>
                        </div>
                    </center>
                    </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Resolved On:&nbsp;&nbsp;<%= reviewedDate%></label>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>


                    </tr>
            </div>
        </div>


    </body>
</html>