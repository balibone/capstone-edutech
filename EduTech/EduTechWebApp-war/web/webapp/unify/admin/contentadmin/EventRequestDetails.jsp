<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - Event Request Details</title>

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
        <link href="css/unify/admin/weblayout/contentadmin/EventRequestDetailsCSS.css" rel="stylesheet" type="text/css" />


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
        <script type="text/javascript" src="js/unify/admin/webjs/contentadmin/EventRequestDetailsJS.js"></script>
    </head>
    <body style="background-color: #FFFFFF;">
        <%            Vector eventRequestVec = (Vector) request.getAttribute("eventRequestVec");
            String requestID, requestStatus, requestDate, requesterID, requestDescription,
                    requestVenue, requestStartDateTime, requestEndDateTime, requestReviewedDate, requestVenueLat, requestVenueLong, requestTitle;
            requestID = requestStatus = requestDate = requesterID = requestDescription
                    = requestVenue = requestStartDateTime = requestEndDateTime = requestReviewedDate = requestVenueLat = requestVenueLong = requestTitle = "";
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

                requestVenueLat = (String.valueOf(eventRequestVec.get(9)));
                requestVenueLong = (String.valueOf(eventRequestVec.get(10)));
                
                requestTitle = (String.valueOf(eventRequestVec.get(11)));
            }
        %>


        <div style="margin: 30px 20px 0 20px">
            <div class="tabbable tabbable-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#reportInfo" data-toggle="tab"">Request Details</a></li>
                    <li><a href="#eventRequest" data-toggle="tab">Details of Event Requested</a></li>
                </ul>
                <%-- holder for all tabs --%>
                <div class="tab-content">
                    <%-- tab 1 --%>
                    <div class="tab-pane" id="eventRequest">
                        <table class="table table-hover table-bordered">
                            <tr>
                                <td>Requested Event Description</td>
                                <td><div style="width:300px;overflow: auto;"><%= requestDescription%></div></td>
                            </tr>
                            <tr>
                                <td>Requested Venue of Event</td>
                                <td><div style="width:300px;overflow: auto;"><%= requestVenue%></div></td>
                            </tr>
                            <tr>
                                <td>Requested Start Date & Time of Event</td>
                                <td><div style="width:300px;overflow: auto;"><%= requestStartDateTime%></div></td>
                            </tr>
                            <tr>
                                <td>Requested End Date & Time of Event</td>
                                <td><div style="width:300px;overflow: auto;"><%= requestEndDateTime%></div></td>
                            </tr>
                            <tr>
                                <td>Venue Map</td>
                                <td>
                                    <div style="width:300px;overflow: auto;">
                                        <input type="hidden" id="requestVenue" value="<%= requestVenue%>" />
                                        <input type="hidden" id="requestVenueLat" value="<%= requestVenueLat%>" />
                                        <input type="hidden" id="requestVenueLong" value="<%= requestVenueLong%>" />
                                        Requested Location: <strong><%= requestVenue%></strong><br/>
                                        <div id="venueMap" style="width: auto; height: 300px; margin-top: 10px;"></div>
                                    </div>
                                </td>
                            </tr>

                        </table>
                    </div>                   

                    <%-- next tab --%>
                    <div class="tab-pane active" id="reportInfo">
                        <table class="table table-hover table-bordered">
                            <tr>
                                <td>Request ID</td>
                                <td><%= requestID%></td>
                            </tr>
                            <tr>
                                <td>Request Title</td>
                                <td><%= requestTitle%></td>
                            </tr>
                            <tr>
                                <td>Request Status</td>
                                <%
                                    if (requestStatus.equals("Approved")) {
                                %>
                                <td><span class="label label-success">Approved</span></td>

                                <%
                                } else if (requestStatus.equals("Rejected")) {
                                %>
                                <td><span class="label label-danger">Rejected</span></td>
                                <%
                                } else {
                                %>
                                <td><span class="label label-warning">Pending</span></td>
                                <%
                                    }
                                %>
                            </tr>
                            <tr>
                                <td>Date of Request Submitted</td>
                                <td><%= requestDate%></td>
                            </tr>
                            <tr>
                                <td>Requestor ID</td>
                                <td><%= requesterID%></td>
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
                            if (requestStatus.equals("Pending")) {
                        %>
                        <%-- approve request --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToEventRequest" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="approveEventRequest"/>
                                <input type="hidden" name="requestID" value="<%= requestID%>" />
                                <input type="hidden" name="requestStatus" value="<%= requestStatus%>" />
                                <input type="hidden" name="requesterID" value="<%= requesterID%>" />
                                <input type="hidden" name="loggedInUsername" value="<%= loggedInUsername%>" />
                                <button type="submit" class="btn btn-primary" onclick="return confirm('Confirm approval?')">Approve</button>
                            </form>
                        </td>
                        <%-- reject request --%>
                        <td>
                            <form action="ContentAdmin?pageTransit=goToEventRequest" method="GET" target="_parent">
                                <input type="hidden" name="pageTransit" value="rejectEventRequest"/>
                                <input type="hidden" name="requestID" value="<%= requestID%>" />
                                <input type="hidden" name="requestStatus" value="<%= requestStatus%>" />
                                <input type="hidden" name="requesterID" value="<%= requesterID%>" />
                                <input type="hidden" name="loggedInUsername" value="<%= loggedInUsername%>" />
                                <button type="submit" class="btn btn-primary" onclick="return confirm('Confirm rejection?')">Reject</button>
                            </form>
                        </td>
                        <%
                        } else if (requestStatus.equals("Approved")) {
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
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Request Approved On:&nbsp;&nbsp;<%= requestReviewedDate%></label>
                            </div>
                        </td>
                    </tr>
                    <%
                    } else if (requestStatus.equals("Rejected")) {
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
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Request Rejected On:&nbsp;&nbsp;<%= requestReviewedDate%></label>
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