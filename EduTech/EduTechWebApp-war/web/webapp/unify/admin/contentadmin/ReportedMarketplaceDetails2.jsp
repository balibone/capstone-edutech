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
            Vector reportListVec = (Vector) request.getAttribute("reportedMarketplaceVec");
            String reportID, reportStatus, reportDescription, reportDate, reportedItemID,
                    reportedPosterID, reportedReporterID, itemName, itemDescription, itemImage, reviewedDate;
            reportID = reportStatus = reportDescription = reportDate = reportedItemID = reportedPosterID = reportedReporterID
                    = itemName = itemDescription = itemImage = reviewedDate = "";
            if (reportListVec != null) {
                reportID = (String.valueOf(reportListVec.get(0)));
                reportStatus = (String.valueOf(reportListVec.get(1)));
                reportDescription = (String.valueOf(reportListVec.get(2)));
                reportDate = (String.valueOf(reportListVec.get(3)));
                reportedItemID = (String.valueOf(reportListVec.get(4)));
                reportedPosterID = (String.valueOf(reportListVec.get(5)));
                reportedReporterID = (String.valueOf(reportListVec.get(6)));
                reviewedDate = (String.valueOf(reportListVec.get(7)));
                if (reportListVec.size() > 8) {
                    itemName = (String.valueOf(reportListVec.get(8)));
                    itemDescription = (String.valueOf(reportListVec.get(9)));
                    itemImage = (String.valueOf(reportListVec.get(10)));
                }
            }
        %>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">

        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="ContentAdmin?pageTransit=goToReportedMarketplaceListing" method="GET" enctype="multipart/form-data" target="_parent">

                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <%--- to display item when found --%>
                        <%
                            if (reportListVec.size() > 8) {
                        %>
                        <%-- display image of marketplace item --%>
                        <div class="col-sm-5 col-md-5 gallery-holder">
                            <div class="single-product-gallery">
                                <div class="owl-item" style="width: 336px;">
                                    <div class="single-product-gallery-item">
                                        <img src="uploads/unify/images/marketplace/item/<%= itemImage%>" style="max-width: 251px; min-width: 251px; max-height: 256px; min-height: 256px;" />
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Reported Item ID:&nbsp;&nbsp;<%= reportedItemID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">User ID of Item Poster:&nbsp;&nbsp;<%= reportedPosterID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">User ID of Reporter:&nbsp;&nbsp;<%= reportedReporterID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12"><b><u>RELEVANT MARKETPLACE ITEM DETAILS</u></b></font></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Item Name:&nbsp;&nbsp;<%= itemName%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Item Description:&nbsp;&nbsp;<%= itemDescription%></label>
                        </div>

                        <%
                        } else {
                        %>

                        <%--- to display when item not found --%>
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Reported Item ID:&nbsp;&nbsp;<%= reportedItemID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">User ID of Item Poster:&nbsp;&nbsp;<%= reportedPosterID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">User ID of Reporter:&nbsp;&nbsp;<%= reportedReporterID%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12"><b><u>RELEVANT MARKETPLACE ITEM DETAILS</u></b></font></label>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12"><font color = "red"><b>ITEM HAS BEEN DELETED FROM SYSTEM&nbsp;&nbsp;</b></font></label>
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
                                if (reportStatus.equals("Unresolved") && !itemName.equals("")) {
                            %>
                            <%-- resolve and do nothing --%>
                            <td>
                                <form action="ContentAdmin?pageTransit=goToReportedMarketplaceListing" method="GET" target="_parent">
                                    <input type="hidden" name="pageTransit" value="resolveMarketplaceReport"/>
                                    <input type="hidden" name="reportID" value="<%= reportID%>" />
                                    <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                    <button type="submit" class="btn btn-primary">Resolved (Do Nothing)</button>
                                </form>
                            </td>
                            <%-- resolve and delete item --%>
                            <td>
                                <form action="ContentAdmin?pageTransit=goToReportedMarketplaceListing" method="GET" target="_parent">
                                    <input type="hidden" name="pageTransit" value="resolveDeleteMarketplaceReport"/>
                                    <input type="hidden" name="reportID" value="<%= reportID%>" />
                                    <input type="hidden" name="reportStatus" value="<%= reportStatus%>" />
                                    <input type="hidden" name="reportedItemID" value="<%= reportedItemID%>" />
                                    <button type="submit" class="btn btn-primary" onclick="return confirm('Confirm deletion?')">Delete Item & Resolve</button>
                                </form>
                            </td>
                            <%
                            } else if (reportStatus.equals("Unresolved") && itemName.equals("")) {
                            %>
                            <%-- resolve and do nothing --%>
                            <td>
                                <form action="ContentAdmin?pageTransit=goToReportedMarketplaceListing" method="GET" target="_parent">
                                    <input type="hidden" name="pageTransit" value="resolveMarketplaceReport"/>
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
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Report Resolved On:&nbsp;&nbsp;<%= reviewedDate%></label>
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
