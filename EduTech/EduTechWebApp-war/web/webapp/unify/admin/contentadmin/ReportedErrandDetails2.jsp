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
    <body style="background-color: white;">


        <%
            Vector reportListVec = (Vector) request.getAttribute("reportedErrandsVec");
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
        <br>
        <%-- display image of errand posted --%>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">

            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
                <div id="formContent">
                    <form action="ContentAdmin?pageTransit=goToReportedErrandsListing" method="GET" enctype="multipart/form-data" target="_parent">

                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <%--- to display item when found --%>
                            <%
                                if (reportListVec.size() > 8) {
                            %>
                            <div class="col-sm-5 col-md-5 gallery-holder">
                                <div class="single-product-gallery">
                                    <div class="owl-item" style="width: 336px;">
                                        <div class="single-product-gallery-item">
                                            <img src="uploads/unify/images/errands/job/<%= jobImage%>" style="max-width: 251px; min-width: 251px; max-height: 256px; min-height: 256px;" />
                                        </div>
                                    </div>
                                </div>
                            </div>

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
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">User ID of Reporter:&nbsp;&nbsp;<%= reportedReporterID%></label>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-12 col-sm-12 col-xs-12"><b><u>RELATED ERRAND DETAILS</u></b></font></label>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Job Title:&nbsp;&nbsp;<%= jobTitle%></label>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Job Description:&nbsp;&nbsp;<%= jobDescription%></label>
                            </div>
                            <%
                            } else {
                            %>
                            <%--- to display when errand not found --%>
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

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12"><font color = "red"><b>ERRAND HAS BEEN DELETED FROM SYSTEM&nbsp;&nbsp;</b></font></label>
                            </div>
                            <%
                                }
                            %>
                        </div>
                </div>
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
                                    <form action="ContentAdmin?pageTransit=goToReportedErrandsListing" method="GET" target="_parent">
                                        <input type="hidden" name="pageTransit" value="resolveErrandReport"/>
                                        <input type="hidden" name="reportID" value="<%= reportID%>" />
                                        <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                        <button type="submit" class="btn btn-primary">Resolved (Do Nothing)</button>
                                    </form>
                                </td>
                                <%-- resolve and delete errand --%>
                                <td>
                                    <form action="ContentAdmin?pageTransit=goToReportedErrandsListing" method="GET" target="_parent">
                                        <input type="hidden" name="pageTransit" value="resolveDeleteErrandReport"/>
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
                                    <form action="ContentAdmin?pageTransit=goToReportedErrandsListing" method="GET" target="_parent">
                                        <input type="hidden" name="pageTransit" value="resolveErrandReport"/>
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
