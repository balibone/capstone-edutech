<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin</title>

        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />

    </head>
    <body style="background-color: white;">


        <%            Vector eventRequestVec = (Vector) request.getAttribute("eventRequestVec");
            String requestID, requestStatus, requestDate, requesterID, requestDescription,
                    requestVenue, requestStartDateTime, requestEndDateTime, requestReviewedDate;
            requestID = requestStatus = requestDate = requesterID = requestDescription
                    = requestVenue = requestStartDateTime = requestEndDateTime = requestReviewedDate = "";
            if (eventRequestVec != null) {
                requestID = (String.valueOf(eventRequestVec.get(0)));
                requestStatus = (String.valueOf(eventRequestVec.get(1)));
                requestDate = (String.valueOf(eventRequestVec.get(2)));
                requesterID = (String.valueOf(eventRequestVec.get(3)));

                requestDescription = (String.valueOf(eventRequestVec.get(4)));
                requestVenue = (String.valueOf(eventRequestVec.get(5)));
                requestStartDateTime = (String.valueOf(eventRequestVec.get(6)));
                requestEndDateTime = (String.valueOf(eventRequestVec.get(7)));

                requestReviewedDate = (String.valueOf(eventRequestVec.get(8)));
            }
        %>
        <br>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="ContentAdmin?pageTransit=goToEventRequest" method="GET" enctype="multipart/form-data" target="_parent">

                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <%--- to display content when found --%>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Request ID:&nbsp;&nbsp;<u><%= requestID%></u></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Request Status:&nbsp;&nbsp;<%= requestStatus%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Date of Request Submitted:&nbsp;&nbsp;<%= requestDate%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Requestor ID:&nbsp;&nbsp;<%= requesterID%></label>
                        </div>
                        
                        <hr width="0%">
                        
                        <hr width="80%">
                        
                        <div class="form-group">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12"><b><u>REQUESTED EVENT DETAILS</u></b></font></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Requested Event Description:&nbsp;&nbsp;<%= requestDescription%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Requested Venue of Event:&nbsp;&nbsp;<%= requestVenue%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Requested Start Date & Time of Event:&nbsp;&nbsp;<%= requestStartDateTime%></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Requested End Date & Time of Event:&nbsp;&nbsp;<%= requestEndDateTime%></label>
                        </div>
                        <hr width="0%">
                    </div>

            </div>

            <hr width="80%">
            <div class="ln_solid"></div>

            <div class="form-group">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <table border="0" style="margin: auto;">
                        <tr>
                            <%
                                if (requestStatus.equals("Pending")) {
                            %>
                            <%-- approve request --%>
                            <td>
                                <form action="ContentAdmin?pageTransit=goToEventRequest" method="GET" target="_parent">
                                    <input type="hidden" name="pageTransit" value="approveEventRequest"/>
                                    <input type="hidden" name="requestID" value="<%= requestID%>" />
                                    <input type="hidden" name="requestStatus" value="<%= requestStatus%>" />
                                    <button type="submit" class="btn btn-primary" onclick="return confirm('Confirm approval?')">Approve</button>
                                </form>
                            </td>
                            <%-- reject request --%>
                            <td>
                                <form action="ContentAdmin?pageTransit=goToEventRequest" method="GET" target="_parent">
                                    <input type="hidden" name="pageTransit" value="rejectEventRequest"/>
                                    <input type="hidden" name="requestID" value="<%= requestID%>" />
                                    <input type="hidden" name="requestStatus" value="<%= requestStatus%>" />
                                    <button type="submit" class="btn btn-primary" onclick="return confirm('Confirm rejection?')">Reject</button>
                                </form>
                            </td>
                            <%
                            } else
                            %>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12"><b>NO FURTHER ACTION NEEDED&nbsp;&nbsp;</b></label>
                        </div>
                        <%
                            if (requestStatus.equals("Approved")) {
                        %>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Request Approved On:&nbsp;&nbsp;<%= requestReviewedDate%></label>
                        </div>
                        <%
                        } else if (requestStatus.equals("Rejected")) {
                        %>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Request Rejected On:&nbsp;&nbsp;<%= requestReviewedDate%></label>
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
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>

    </body>
</html>
