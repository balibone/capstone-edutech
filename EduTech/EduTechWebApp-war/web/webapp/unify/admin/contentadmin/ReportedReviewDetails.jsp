<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin</title>

        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">

    </head>
    <body style="background-color: transparent;">


        <%
            Vector reportListVec = (Vector) request.getAttribute("reportedReviewVec");
            String reportID, reportStatus, reportDescription, reportDate, reportedReviewID, reportedPosterID, reportedReporterID;
            reportID = reportStatus = reportDescription = reportDate = reportedReviewID = reportedPosterID = reportedReporterID = "";
            if (reportListVec != null) {
                reportID = (String.valueOf(reportListVec.get(0)));
                reportStatus = (String.valueOf(reportListVec.get(1)));
                reportDescription = (String.valueOf(reportListVec.get(2)));
                reportDate = (String.valueOf(reportListVec.get(3)));
                reportedReviewID = (String.valueOf(reportListVec.get(4)));
                reportedPosterID = (String.valueOf(reportListVec.get(5)));
                reportedReporterID = (String.valueOf(reportListVec.get(6)));
            }
        %>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="ContentAdmin?pageTransit=goToReportedErrandsListing" method="GET" enctype="multipart/form-data" target="_parent">
                    
                    <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report ID:&nbsp;&nbsp;<u><%= reportID%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Status:&nbsp;&nbsp;<u><%= reportStatus%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Description&nbsp;&nbsp;<u><%= reportDescription%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Date&nbsp;&nbsp;<u><%= reportDate%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Reported Review ID&nbsp;&nbsp;<u><%= reportedReviewID%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Reporter User ID&nbsp;&nbsp;<u><%= reportedPosterID%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Reported User ID&nbsp;&nbsp;<u><%= reportedReporterID%></u></label>
                        </div>

                    </div>
            </div>
            <div class="ln_solid"></div>
            <div class="form-group">
                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                    <table border="0" style="margin: auto;">
                        <tr>
                            <%
                                if (reportStatus.equals("Resolved")) {
                            %>
                            <td>
                                <form action="ContentAdmin" method="POST" target="_parent">
                                    <input type="hidden" name="pageTransit" value="unresolveReviewReport"/>
                                    <input type="hidden" name="reportID" value="<%= reportID%>" />
                                    <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                    <button type="submit" class="btn btn-primary">Revert to Unresolved</button>
                                </form>
                            </td>
                            <%
                            } else if (reportStatus.equals("Unresolved")) {
                            %>
                            <%-- resolve and do nothing --%>
                            <td>
                                    <input type="hidden" name="pageTransit" value="resolveReviewReport"/>
                                    <input type="hidden" name="reportID" value="<%= reportID%>" />
                                    <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                    <button type="submit" class="btn btn-primary">Resolved (Do Nothing)</button>
                                
                            </td>
                            <%-- resolve and delete review --%>
                            <td>
                                    <input type="hidden" name="pageTransit" value="resolveDeleteReviewReport"/>
                                    <input type="hidden" name="reportID" value="<%= reportID%>" />
                                    <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                    <button type="submit" class="btn btn-primary">Resolved (Delete Review)</button>
                                
                            </td>
                            <%
                                }
                            %>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>

    </body>
</html>
