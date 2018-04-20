
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify Events - Requested Event Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/qtip/jquery.qtip-v3.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/weblayout/events/ViewEventDetailsSYSCSS.css" rel="stylesheet" type="text/css" />
    </head>
    <body onload="loadMap()">
        <!-- MOBILE SIDE NAVIGATION -->
        <nav class="offcanvas">
            <div class="offcanvas-content">
                <div id="list-menu" class="list-menu list-group" data-children=".submenu">
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#marketplaceSub" role="button" aria-expanded="false" aria-controls="marketplaceSub"><i class="fa fa-fw fa-file"></i>&nbsp;Marketplace</a>
                        <div id="marketplaceSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="MarketplaceSysUser?pageTransit=goToViewItemListingSYS">Item Listing</a></div>
                    </div>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#errandsSub" role="button" aria-expanded="false" aria-controls="errandsSub"><i class="fa fa-fw fa-file"></i>&nbsp;Errands</a>
                        <div id="errandsSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="ErrandsSysUser?pageTransit=goToViewJobListingSYS">Errands Listing</a></div>
                    </div>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#companyReviewSub" role="button" aria-expanded="false" aria-controls="companyReviewSub"><i class="fa fa-fw fa-user"></i>&nbsp;Company Review</a>
                        <div id="companyReviewSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="VoicesSysUser?pageTransit=goToViewCompanyListingSYS">Company Listing</a></div>
                    </div>
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
                </div>
            </div>
        </nav>
        <div class="content-overlay"></div>

        <!-- PAGE TOP HEADER -->
        <div class="top-header">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="d-flex justify-content-between">
                            <nav class="nav">
                                <a class="nav-item nav-link d-sm-block" href="#">Unify @ EduBox</a>
                            </nav>
                            <ul class="nav">
                                <li class="nav-item d-none d-md-block">
                                    <a href="ProfileSysUser?pageTransit=goToViewChatListSYS&assocItemID=" class="nav-link"><i class="fa fa-comment"></i>&nbsp;&nbsp;My Chats</a>
                                </li>
                                <li class="nav-item d-none d-md-block">
                                    <div class="dropdown-container">
                                        <a href="#" class="nav-link" id="dropdown-cart" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: block;">
                                            <i class="fa fa-bell"></i>&nbsp;&nbsp;Notifications<span class="badge badge-light"><%= request.getAttribute("unreadNotificationCount")%></span>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-cart" aria-labelledby="dropdown-cart">
                                            <%                                                ArrayList<Vector> userMessageListTopThreeSYS = (ArrayList) request.getAttribute("userMessageListTopThreeSYS");
                                                if (!userMessageListTopThreeSYS.isEmpty()) {
                                                    for (int i = 0; i <= userMessageListTopThreeSYS.size() - 1; i++) {
                                                        Vector v = userMessageListTopThreeSYS.get(i);
                                                        String messageContent = String.valueOf(v.get(0));
                                                        String contentID = String.valueOf(v.get(1));
                                                        String messageType = String.valueOf(v.get(2));
                                                        String messageSenderImage = String.valueOf(v.get(4));
                                                        String messageSentDuration = String.valueOf(v.get(5));
                                            %>
                                            <div id="<%= messageType%><%= contentID%>" class="media messageDIV">
                                                <%  if (messageType.equals("System")) {%>
                                                <img class="img-thumbnail d-flex" src="images/<%= messageSenderImage%>" style="width:35px;height:35px;" />
                                                <%  } else {%>
                                                <img class="img-thumbnail d-flex" src="uploads/commoninfrastructure/admin/images/<%= messageSenderImage%>" style="width:35px;height:35px;" />
                                                <%  }%>
                                                <div class="message-content pl-3">
                                                    <div><%= messageContent%></div>
                                                    <small class="font-weight-normal message-content">
                                                        <i class="fa fa-clock-o"></i>&nbsp;<%= messageSentDuration%>&nbsp;(<%= messageType%>)
                                                    </small>
                                                </div>
                                            </div>
                                            <div class="dropdown-divider"></div>
                                            <%      }   %>
                                            <%  } else {    %>
                                            <p style="text-align:center;">There are no notifications.</p>
                                            <div class="dropdown-divider"></div>
                                            <%  }%>
                                            <div class="text-center">
                                                <div class="btn-group btn-group-sm" role="group">
                                                    <a href="ProfileSysUser?pageTransit=goToUserNotificationListSYS" role="button" class="btn btn-outline-theme">
                                                        <i class="fa fa-envelope"></i>&nbsp;&nbsp;See All Notifications
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="dropdown-divider"></div>
                                        </div>
                                    </div>
                                </li>
                                <select class="select-dropdown-nav accountNavigation" data-width="120px">
                                    <option value="#" selected data-before='<i class="fa fa-user align-baseline" /></i>'>&nbsp;&nbsp;<%= loggedInUsername%></option>
                                    <option value="CommonInfra?pageTransit=goToCommonLanding" data-before='<i class="fa fa-external-link align-baseline" /></i>'>&nbsp;&nbsp;Landing Page</option>
                                    <option value="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" data-before='<i class="fa fa-user-circle align-baseline" /></i>'>&nbsp;&nbsp;My Account</option>
                                    <option value="CommonInfra?pageTransit=goToLogout" data-before='<i class="fa fa-sign-out align-baseline" /></i>'>&nbsp;&nbsp;Logout</option>
                                </select>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- PAGE MIDDLE NAVIGATION -->
        <div class="middle-header">
            <div class="container">
                <div class="row py-2 py-lg-0">
                    <div class="col-2 col-sm-1 d-block d-lg-none">
                        <div class="d-flex align-items-center justify-content-center menu-btn-wrapper">
                            <button class="btn btn-lg border-0 btn-link offcanvas-btn p-0" type="button">
                                <i class="fa fa-bars"></i>
                            </button>
                        </div>
                    </div>
                    <div class="col-2 col-sm-1 col-lg-3 pr-0">
                        <div class="d-flex align-items-center logo-wrapper">
                            <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" class="d-lg-none">
                                <img src="images/edubox-unify-logo.png" class="logo" />
                            </a>
                            <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" class="d-none d-lg-flex mb-2 mb-lg-0">
                                <img src="images/edubox-unify-logo.png" class="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="col-8 col-sm-6 col-md-7 col-lg-6 mt-3" style="visibility:hidden">
                        <div class="d-flex align-items-center">
                            <div class="input-group input-group-search">
                                <div class="input-group-prepend d-none d-md-flex">
                                    <select class="select-dropdown">
                                        <option value="all">All Categories</option>
                                        <option value="marketplace">Marketplace</option>
                                        <option value="errands">Errands</option>
                                        <option value="companyReview">Company Review</option>
                                    </select>
                                </div>
                                <input type="text" class="form-control" id="search-input" placeholder="Search here..." aria-label="Search here..." autocomplete="off" />
                                <span class="input-group-append">
                                    <button class="btn btn-theme btn-search" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 col-sm-4 col-md-3 col-lg-3 d-none d-sm-block mt-3">
                        <div class="d-flex align-items-center float-right abg-secondary">
                            <div class="btn-group btn-group-sm mr-3" role="group">
                                <a href="EventsSysUser?pageTransit=goToNewEventSYS"><button type="button" class="btn btn-outline-theme center"><i class="fa fa-calendar-plus-o"></i>&nbsp;&nbsp;Submit New Event Request</button></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%
            Vector eventDetailsVec = (Vector) request.getAttribute("eventDetailsVec");
            String eventRequestID, eventRequestStatus, eventRequestTitle, eventRequestDesc, 
                    eventRequestStart, eventRequestEnd, eventRequestVenue, eventRequestDate, 
                    eventReviewedDate, eventRequestor, eventCreatedID, eventRequestPoster;
            eventRequestID = eventRequestStatus = eventRequestTitle = eventRequestDesc = 
                    eventRequestStart = eventRequestEnd = eventRequestVenue = 
                    eventRequestDate = eventReviewedDate = eventRequestor = 
                    eventCreatedID = eventRequestPoster = "";

            if (eventDetailsVec != null) {
                eventRequestID = String.valueOf(eventDetailsVec.get(0));
                eventRequestStatus = String.valueOf(eventDetailsVec.get(1));
                eventRequestTitle = String.valueOf(eventDetailsVec.get(2));
                eventRequestDesc = String.valueOf(eventDetailsVec.get(3));
                eventRequestStart = String.valueOf(eventDetailsVec.get(4));
                eventRequestEnd = String.valueOf(eventDetailsVec.get(5));
                eventRequestVenue = String.valueOf(eventDetailsVec.get(6));
                eventRequestDate = String.valueOf(eventDetailsVec.get(7));
                eventReviewedDate = String.valueOf(eventDetailsVec.get(8));
                eventRequestor = String.valueOf(eventDetailsVec.get(9));
                eventCreatedID = String.valueOf(eventDetailsVec.get(10));
                eventRequestPoster = String.valueOf(eventDetailsVec.get(11));

            }
        %>

        <div id="container">
            <div id="unifyPageNAV"></div>
            <!-- BREADCRUMB -->
            <div class="breadcrumb-container">
                <div class="container">
                    <nav aria-label="breadcrumb" role="navigation">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS">Unify Home</a></li>
                            <li class="breadcrumb-item"><a href="EventsSysUser?pageTransit=goToViewMyRequestedEventsListingSYS">My Requested Events Listing</a></li>
                            <li class="breadcrumb-item active" aria-current="page"><a href='EventsSysUser?pageTransit=goToViewRequestedEventDetailsSYS&hiddenEventRequestID=<%=eventRequestID%>'>Event Request Details</a></li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="container" style="margin-bottom: 30px;">


                <%
                    String successMessage = (String) request.getAttribute("successMessage");
                    if (successMessage != null) {
                %>
                <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeSuccess">&times;</button>
                    <%= successMessage%>
                </div>
                <%  }   %>
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeError">&times;</button>
                    <%= errorMessage%>
                </div>
                <%  }%>

                <div class="row">
                    <div class="col-12 d-block d-md-none">
                        <div class="title"><span><%= eventRequestTitle%></span></div>
                    </div>
                    <div class="col-xl-4 col-lg-5 col-md-6">
                        <img src="uploads/unify/images/events/<%= eventRequestPoster%>" class="img-fluid mb-2 border w-100 image-detail" style="cursor: pointer;">
                        <div class="title d-none d-md-block"><span>Share to</span></div>
                        <ul class="list-inline d-none d-md-block">
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-facebook"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-info"><i class="fa fa-fw fa-twitter"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-danger"><i class="fa fa-fw fa-google-plus"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-linkedin"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-warning"><i class="fa fa-fw fa-envelope"></i></button></li>
                        </ul>
                    </div>
                    <div class="col-xl-8 col-lg-7 col-md-6">
                        <table class="table table-detail" id="eventDetailsTable" add-class-on-xs="table-sm">
                            <tbody>
                                <tr class="d-none d-md-table-row">
                                    <td class="border-top-0" colspan="2">
                                        
                                        <button type="button" id="qtipItemReportTrigger" class="text-right close"></button>

                                        <div class="pull-left"><h4><%= eventRequestTitle%></h4></div>
                                        <a href="EventsSysUser?pageTransit=goToViewMyRequestedEventsListingSYS"><div class="pull-right">Back to Listing</div></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Event Venue</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0"><%= eventRequestVenue%></h5></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Event Details</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0"><%= eventRequestDesc%></h5></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Event Starts</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0"><%= eventRequestStart%></h5></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Event Ends</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0"><%= eventRequestEnd%></h5></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Event Creator</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0"><%= eventRequestor%></h5></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Request Status</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0">
                                                    <%
                                                        if (eventRequestStatus.equals("Pending")) {
                                                    %>
                                                    <%= eventRequestStatus%>&nbsp;&nbsp;
                                                    <%
                                                    } else if (eventRequestStatus.equals("Approved")) {
                                                    %>
                                                    <span class="badge badge-success custom-badge" style="font-size: 12px">
                                                        Approved
                                                    </span>
                                                    &nbsp;&nbsp;<a href="EventsSysUser?pageTransit=goToViewEventDetailsSYS&hiddenEventID=<%=eventCreatedID%>"><span style="font-size:14">View Event Page</style></a>
                                                    <%
                                                    } else if (eventRequestStatus.equals("Rejected")) {
                                                    %>
                                                    <span class="badge badge-warning custom-badge" style="font-size: 12px">
                                                        Rejected
                                                    </span>
                                                    <%  }%>
                                                    <%
                                                        if (eventRequestor.equals(request.getAttribute("loggedInUsername")) && eventRequestStatus.equals("Pending")) {
                                                    %>
                                                    <a href="EventsSysUser?pageTransit=goToEditEventRequestSYS&hiddenEventRequestID=<%= eventRequestID%>"><span class="badge badge-info custom-badge" style="font-size: 12px">
                                                            Edit this request
                                                        </span></a>
                                                    <a href="EventsSysUser?pageTransit=goToDeleteEventRequestSYS&hiddenEventRequestID=<%= eventRequestID%>"><span class="badge badge-danger custom-badge" style="font-size: 12px" onclick="return confirm('Confirm delete?')">
                                                            Delete this request
                                                        </span></a>
                                                    <%  }%></h5></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Request Reviewed On</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0"><%= eventReviewedDate%></h5></li></ul>
                                    </td>
                                </tr>


                            </tbody>
                        </table>
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item"><a class="nav-link text-default active" id="venuePane-tab" data-toggle="tab" href="#venuePane" role="tab" aria-controls="venuePane" aria-selected="true">Venue Map</a></li>

                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane border border-top-0 p-3 show active" id="venuePane" role="tabpanel" aria-labelledby="venuePane-tab">
                                <table class="table table-bordered mb-0">
                                    <tbody>
                                        <tr>
                                            <td class="bg-light w-25">Venue</td>
                                            <td>
                                                <%= eventRequestVenue%>
                                                <input type="hidden" id="venueLocation" value="<%= eventRequestVenue%>" />
                                                <input type="hidden" id="venueLat" value="TradeLat" />
                                                <input type="hidden" id="venueLong" value="TradeLong" />

                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Venue Map</td>
                                            <td><div id="mapdiv" style="width: auto; height: 300px;"></div></td>
                                        </tr>

                                    </tbody>
                                </table>
                            </div>


                        </div>

                    </div>
                </div>

            </div>

            <div class="chat-main">
                <div class="col-md-12 chat-header">
                    <div class="row header-one text-white p-1">
                        <div class="col-md-6 name pl-2">
                            <i class="fa fa-comment"></i>
                            <h6 class="ml-1 mb-0 mt-1">Unify Bot</h6>
                        </div>
                        <div class="col-md-6 options text-right pr-0">
                            <i class="fa fa-window-minimize hide-chat-box hover text-center"></i>
                        </div>
                    </div>
                </div>
                <div class="chat-content">
                    <div class="col-md-12 chats">
                        <iframe src="ProfileSysUser?pageTransit=goToUnifyBot" width="305" height="285" frameborder="0" ></iframe>
                    </div>
                </div>
            </div>

            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="marketplace-overlay"></div>
        </div>

        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/qtip/jquery.qtip-v3.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/events/ViewEventDetailsSYSJS.js" type="text/javascript"></script>
    </body>
</html>