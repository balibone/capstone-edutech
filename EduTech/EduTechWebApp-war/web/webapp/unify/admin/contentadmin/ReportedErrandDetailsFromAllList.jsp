<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - Reported Errand Details</title>

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


        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/lodash.compat-v2.0.0.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/breakpoints-v1.0.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll.horizontal-v0.6.5.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.sparkline-v2.1.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminAppJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginFormComponentsJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/leaflet/leaflet.js"></script>

    </head>
    <body style="background-color: #FFFFFF;">
        <%            Vector reportListVec = (Vector) request.getAttribute("reportedErrandsVec");
            String reportID, reportStatus, reportDescription, reportDate, reportedErrandID,
                    reportedPosterID, reportedReporterID, jobTitle, jobDescription, jobImage, jobReviewedDate;
            reportID = reportStatus = reportDescription = reportDate = reportedErrandID = reportedPosterID = reportedReporterID
                    = jobTitle = jobDescription = jobImage = jobReviewedDate = "";
            if (reportListVec != null) {
                reportID = (String.valueOf(reportListVec.get(0)));
                reportStatus = (String.valueOf(reportListVec.get(1)));
                reportDescription = (String.valueOf(reportListVec.get(2)));
                reportDate = (String.valueOf(reportListVec.get(3)));
                reportedErrandID = (String.valueOf(reportListVec.get(4)));
                reportedPosterID = (String.valueOf(reportListVec.get(5)));
                reportedReporterID = (String.valueOf(reportListVec.get(6)));
                jobReviewedDate = (String.valueOf(reportListVec.get(7)));
                if (reportListVec.size() > 8) {
                    jobTitle = (String.valueOf(reportListVec.get(8)));
                    jobDescription = (String.valueOf(reportListVec.get(9)));
                    jobImage = (String.valueOf(reportListVec.get(10)));
                }
            }
        %>




        <div style="margin: 30px 20px 0 20px">
            <div class="tabbable tabbable-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#reportInfo" data-toggle="tab">Report Details</a></li>
                    <li><a href="#reportedErrand" data-toggle="tab">Details of Errand Reported</a></li>
                </ul>
                <%-- primary tab --%>
                <div class="tab-content">
                    <div class="tab-pane active" id="reportInfo">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report ID:&nbsp;&nbsp;<u><%= reportID%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Status:&nbsp;&nbsp;<%= reportStatus%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Description:&nbsp;&nbsp;<%= reportDescription%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Date:&nbsp;&nbsp;<%= reportDate%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Reported Errand ID:&nbsp;&nbsp;<%= reportedErrandID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">User ID of Errand Poster:&nbsp;&nbsp;<%= reportedPosterID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">User ID of Reporter&nbsp;&nbsp;<%= reportedReporterID%></label>
                        </div>
                    </div>

                    <%-- secondary tab --%>
                    <div class="tab-pane" id="reportedErrand">

                        <%--- to display errand review when found --%>
                        <%
                            if (reportListVec.size() > 8) {
                        %>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Job Title:&nbsp;&nbsp;<%= jobTitle%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Job Description:&nbsp;&nbsp;<%= jobDescription%></label>
                        </div>
                        <%
                        } else {
                        %>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"><font color = "red"><b>ERRAND HAS BEEN DELETED FROM SYSTEM&nbsp;&nbsp;</b></font></label>
                        </div>
                        <%
                            }
                        %> 

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
                            if (reportStatus.equals("Unresolved") && !jobTitle.equals("")) {
                        %>
                        <%-- resolve and do nothing --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToAllReportedListing#errandReport" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="resolveErrandReportFromAllList"/>
                                <input type="hidden" name="reportID" value="<%= reportID%>" />
                                <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                <button type="submit" class="btn btn-primary">Resolved (Do Nothing)</button>
                            </form>
                        </td>
                        <%-- resolve and delete errand --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToAllReportedListing#errandReport" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="resolveDeleteErrandReportFromAllList"/>
                                <input type="hidden" name="reportID" value="<%= reportID%>" />
                                <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                <input type="hidden" name="reportedErrandID" value="<%= reportedErrandID%>" />
                                <button type="submit" class="btn btn-primary" onclick="return confirm('Confirm deletion?')">Delete Errand & Resolve</button>
                            </form>
                        </td>
                        <%
                        } else if (reportStatus.equals("Unresolved") && jobTitle.equals("")) {
                        %>
                        <%-- resolve and do nothing --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToAllReportedListing#errandReport" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="resolveErrandReportFromAllList"/>
                                <input type="hidden" name="reportID" value="<%= reportID%>" />
                                <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                <button type="submit" class="btn btn-primary">Resolved (Do Nothing)</button>
                            </form>
                        </td>

                        <%
                        } else {
                        %>
                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12"><b>NO FURTHER ACTION NEEDED&nbsp;&nbsp;</b></label>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Resolved On:&nbsp;&nbsp;<%= jobReviewedDate%></label>
                    </div>
                    <%
                        }
                    %>
                    </tr>
            </div>
        </div>


    </body>
</html>