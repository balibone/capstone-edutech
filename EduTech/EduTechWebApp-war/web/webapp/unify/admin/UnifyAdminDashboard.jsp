<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<!-- ***************************************************************************************
*   Title:                  UnifyAdminDashboard.jsp
*   Purpose:                SUMMARY STATISTICS FOR ALL FUNCTIONS IN UNIFY
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
**************************************************************************************** -->

<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector"%>
<html lang="en"> 
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
        <title>Unify Admin - Dashboard</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/open-sans-font.css" rel="stylesheet" type="text/css" />

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/breakpoints-v1.0.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll.horizontal-v0.6.5.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.sparkline-v2.1.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminAppJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginFormComponentsJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminCommonJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.newsTicker.js"></script>
    </head>
    <body onload="establishTime(); setInterval('updateTime()', 1000)">
        <header class="header navbar navbar-fixed-top" role="banner">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li class="nav-toggle"><a href="javascript:void(0);"><i class="fa fa-th-list"></i></a></li>
                </ul>
                <a class="navbar-brand" href="ProfileAdmin?pageTransit=goToUnifyAdmin">
                    <img src="images/edubox-unify-logo.png" style="width:108px;height:38px;" />
                    <small><strong><sub>ADMIN</sub></strong></small>
                </a>
                <a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation">
                    <i class="fa fa-reorder"></i>
                </a>
                <ul class="nav navbar-nav navbar-left hidden-xs hidden-sm">
                    <li><a href="ProfileAdmin?pageTransit=goToUnifyAdmin">Dashboard</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" class="dropdown-toggle row-bg-toggle"><i class="fa fa-arrows-v"></i></a></li>
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-male"></i>&nbsp;
                            <span class="username"><%= loggedInUsername%></span>&nbsp;
                            <i class="fa fa-caret-down small"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="CommonInfra?pageTransit=goToCommonLanding"><i class="fa fa-external-link"></i>&nbsp;Landing Page</a></li>
                            <li class="divider"></li>
                            <li><a href="CommonInfra?pageTransit=goToLogout"><i class="fa fa-sign-out"></i>&nbsp;Log Out</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </header>
        <div id="container">
            <div id="sidebar" class="sidebar-fixed">
                <div id="sidebar-content">
                    <ul id="nav">
                        <li class="current"><a href="ProfileAdmin?pageTransit=goToUnifyAdmin"><i class="fa fa-bar-chart"></i>&nbsp;Dashboard</a></li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-shopping-cart"></i>&nbsp;Marketplace (Trade)</a>
                            <ul class="sub-menu">
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemCategoryListing"><i class="fa fa-bookmark"></i>&nbsp;Item Categories</a></li>
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemListing"><i class="fa fa-th-list"></i>&nbsp;Item Listing</a></li>
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemTransactionList"><i class="fa fa-book"></i>&nbsp;Item Transactions</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-shopping-bag"></i>&nbsp;Errands (Jobs)</a>
                            <ul class="sub-menu">
                                <li><a href="ErrandsAdmin?pageTransit=goToViewJobCategoryListing"><i class="fa fa-bookmark"></i>&nbsp;Job Categories</a></li>
                                <li><a href="ErrandsAdmin?pageTransit=goToViewJobListing"><i class="fa fa-th-list"></i>&nbsp;Job Listing</a></li>
                                <li><a href="ErrandsAdmin?pageTransit=goToViewJobTransactions"><i class="fa fa-book"></i>&nbsp;Job Transactions</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-building"></i>&nbsp;Company Reviews</a>
                            <ul class="sub-menu">
                                <li><a href="VoicesAdmin?pageTransit=goToViewCompanyCategoryList"><i class="fa fa-bookmark"></i>&nbsp;Company Categories</a></li>
                                <li><a href="VoicesAdmin?pageTransit=goToViewCompanyList"><i class="fa fa-th-list"></i>&nbsp;Company Listing</a></li>
                                <li><a href="VoicesAdmin?pageTransit=goToViewCompanyRequestList"><i class="fa fa-chevron-right"></i>&nbsp;Company Listing Request</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-tag"></i>&nbsp;Tags</a>
                            <ul class="sub-menu">
                                <li><a href="ContentAdmin?pageTransit=goToTagListing"><i class="fa fa-tags"></i>&nbsp;Tag List</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-file"></i>&nbsp;Content Administration</a>
                            <ul class="sub-menu">
                                <li><a href="ContentAdmin?pageTransit=goToAllReportedListing"><i class="fa fa-wpforms"></i>&nbsp;All Reports</a></li>
                                <li><a href="ContentAdmin?pageTransit=goToEventRequest"><i class="fa fa-calendar"></i>&nbsp;Event Requests</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div id="divider" class="resizeable"></div>
            </div>

            <div id="content">
                <div class="container">
                    <div class="crumbs">
                        <ul id="breadcrumbs" class="breadcrumb">
                            <li class="current"><i class="fa fa-home"></i><a href="ProfileAdmin?pageTransit=goToUnifyAdmin">Dashboard</a></li>
                        </ul>
                        <ul class="crumb-buttons">
                            <li class="dropdown"><a href="#" data-toggle="dropdown"><i class="fa fa-user"></i><span>Users<strong>&nbsp;(<%= request.getAttribute("unifyUserCount")%>)</strong></span><i class="icon-angle-down left-padding"></i></a> 
                                <ul class="dropdown-menu pull-right">
                                    <li><a href="ProfileAdmin?pageTransit=goToUnifyUserList"><i class="fa fa-user"></i>View User List</a></li>
                                </ul> 
                            </li>
                            <li><a href="#"><i class="fa fa-clock-o"></i><span id="clock"></span></a></li>
                        </ul>
                    </div>
                    <div class="page-header">
                        <div class="page-title">
                            <h3>Dashboard</h3><span>Good Day to You, <%= loggedInUsername%>!</span>
                        </div>
                        <ul class="page-stats">
                            <li>
                                <div class="summary"><span>Marketplace Listings</span><h3><%= request.getAttribute("itemListingCount")%></h3></div>
                                <div id="sparkline-bar" class="graph sparkline hidden-xs">20,15,8,50,20,40,20,30,20,15,30,20,25,20</div>
                            </li>
                            <li>
                                <div class="summary"><span>Errands Listings</span><h3><%= request.getAttribute("errandsListingCount")%></h3></div>
                                <div id="sparkline-bar2" class="graph sparkline hidden-xs">20,15,8,50,20,40,20,30,20,15,30,20,25,20</div>
                            </li>
                        </ul>
                    </div>
                    <div class="row row-bg">
                        <div class="col-sm-6 col-md-3">
                            <div class="statbox widget box box-shadow">
                                <div class="widget-content">
                                    <div class="visual cyan"><div class="statbox-sparkline">30,20,15,30,22,25,26,30,27</div></div> 
                                    <div class="title">Mktplace Deal Today</div>
                                    <div class="value"><%= request.getAttribute("itemTransTodayCount")%></div>
                                    <a class="more" href="MarketplaceAdmin?pageTransit=goToViewItemTransactionList">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-6 col-md-3">
                            <div class="statbox widget box box-shadow">
                                <div class="widget-content">
                                    <div class="visual green"><div class="statbox-sparkline">20,30,30,29,22,15,20,30,32</div></div>
                                    <div class="title">Errands Deal Today</div>
                                    <div class="value"><%= request.getAttribute("errandsTransTodayCount")%></div>
                                    <a class="more" href="ErrandsAdmin?pageTransit=goToViewJobTransactions">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <%-- content admin reports count --%>
                        <div class="col-sm-6 col-md-3">
                            <div class="statbox widget box box-shadow">
                                <div class="widget-content">
                                    <div class="visual red"><i class="fa fa-file fa-2x"></i></div>
                                    <div class="title">Content Reports Unresolved</div>
                                    <div class="value"><%= request.getAttribute("unresolvedContentReportCount")%></div>
                                    <a class="more" href="ContentAdmin?pageTransit=goToAllReportedListing">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <%-- evetnt requests pending count --%>
                        <div class="col-sm-6 col-md-3">
                            <div class="statbox widget box box-shadow">
                                <div class="widget-content">
                                    <div class="visual yellow"><i class="fa fa-building fa-2x"></i></div>
                                    <div class="title">Event Requests Pending<br/></div>
                                    <div class="value"><%= request.getAttribute("pendingEventRequestCount")%></div>
                                    <a class="more" href="ContentAdmin?pageTransit=goToEventRequest">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- recent reported listings (3 tabs) --%>
                    <%-- add tabs here for each report --%>
                    <div class="row">                        
                        <div class="col-md-7">

                            <div class="widget box">
                                <div class="widget-header-reported-listing">
                                    <h4><i class="fa fa-reorder fa-5x"></i>&nbsp;Recent Reported Listings</h4>
                                </div>
                                <div class="tabbable tabbable-custom-dashboard">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#companyReview" data-toggle="tab">Company Reviews Reported</a></li>
                                        <li><a href="#errandReport" data-toggle="tab">Errands Reported</a></li>
                                        <%--<li><a href="#errandReviewReport" data-toggle="tab">Errand Reviews Reported</a></li>--%>
                                        <li><a href="#marketplaceReport" data-toggle="tab">Marketplace Items Reported</a></li>
                                    </ul>

                                    <div class="tab-content">
                                        <%-- primary tab --%>
                                        <div class="tab-pane active" id="companyReview">
                                            <div class="widget-content no-padding">
                                                <table class="table table-striped table-checkable table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th class="hidden-xs">Report Date</th>
                                                            <th>Review ID</th>
                                                            <th>Report Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<Vector> reportList = (ArrayList) request.getAttribute("reportReviewList");

                                                            for (int i = 0; i <= reportList.size() - 1; i++) {
                                                                Vector v = reportList.get(i);
                                                                String reportID = String.valueOf(v.get(0));
                                                                String reportStatus = String.valueOf(v.get(1));
                                                                String reportDescription = String.valueOf(v.get(2));
                                                                String reportDate = String.valueOf(v.get(3));
                                                                String reportedReviewID = String.valueOf(v.get(4));
                                                                String reportedPosterID = String.valueOf(v.get(5));
                                                                String reportedReporterID = String.valueOf(v.get(6));

                                                        %>
                                                        <tr>
                                                            <td><%= reportDate%></td>
                                                            <td><%= reportedReviewID%></td>
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

                                                        <%  }%>
                                                    </tbody>
                                                </table>
                                                <a class="tableMore" href="ContentAdmin?pageTransit=goToAllReportedListing#companyReview">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                            </div>  
                                        </div>

                                        <%-- second tab --%>
                                        <div class="tab-pane" id="errandReport">
                                            <div class="widget-content no-padding">
                                                <table class="table table-striped table-checkable table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th class="hidden-xs">Report Date</th>
                                                            <th>Errand ID</th>
                                                            <th>Report Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<Vector> reportList2 = (ArrayList) request.getAttribute("reportErrandsList");

                                                            for (int i = 0; i <= reportList2.size() - 1; i++) {
                                                                Vector v = reportList2.get(i);
                                                                String reportID = String.valueOf(v.get(0));
                                                                String reportStatus = String.valueOf(v.get(1));
                                                                String reportDescription = String.valueOf(v.get(2));
                                                                String reportDate = String.valueOf(v.get(3));
                                                                String reportedErrandID = String.valueOf(v.get(4));
                                                                String reportedPosterID = String.valueOf(v.get(5));
                                                                String reportedReporterID = String.valueOf(v.get(6));
                                                        %>
                                                        <tr>
                                                            <td><%= reportDate%></td>
                                                            <td><%= reportedErrandID%></td>                                                           
                                                            <%
                                                                if (reportStatus.equals("Resolved (No Issue Found)")) {
                                                            %>
                                                            <td><span class="label label-success">Resolved (No Issue Found)</span></td>
                                                            <%
                                                            } else if (reportStatus.equals("Resolved (Delisted)")) {
                                                            %>
                                                            <td><span class="label label-success">Resolved (Errand Delisted)</span></td>
                                                            <%
                                                            } else {
                                                            %>
                                                            <td><span class="label label-warning">Unresolved</span></td>
                                                            <%
                                                                }
                                                            %>
                                                        </tr>

                                                        <%  }%>
                                                    </tbody>
                                                </table>
                                                <a class="tableMore" href="ContentAdmin?pageTransit=goToAllReportedListing#errandReport">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                            </div>
                                        </div>

                                        <%--
                                        <%-- third tab -%>
                                        <div class="tab-pane" id="errandReviewReport">
                                            <div class="widget-content no-padding">
                                                <table class="table table-striped table-checkable table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th class="hidden-xs">Report Date</th>
                                                            <th>Errand Review ID</th>
                                                            <th>Report Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<Vector> reportList3 = (ArrayList) request.getAttribute("reportErrandsReviewList");

                                                            for (int i = 0; i <= reportList3.size() - 1; i++) {
                                                                Vector v = reportList3.get(i);
                                                                String reportID = String.valueOf(v.get(0));
                                                                String reportStatus = String.valueOf(v.get(1));
                                                                String reportDescription = String.valueOf(v.get(2));
                                                                String reportDate = String.valueOf(v.get(3));
                                                                String reportedErrandID = String.valueOf(v.get(4));
                                                                String reportedPosterID = String.valueOf(v.get(5));
                                                                String reportedReporterID = String.valueOf(v.get(6));

                                                        %>
                                                        <tr>
                                                            <td><%= reportDate%></td>
                                                            <td><%= reportedErrandID%></td>
                                                            <%
                                                                if (reportStatus.equals("Resolved (No Issue Found)")) {
                                                            %>
                                                            <td><span class="label label-success">Resolved (No Issue Found)</span></td>
                                                            <%
                                                            } else if (reportStatus.equals("Resolved (Delisted)")) {
                                                            %>
                                                            <td><span class="label label-success">Resolved (Errand Review Delisted)</span></td>
                                                            <%
                                                            } else {
                                                            %>
                                                            <td><span class="label label-warning">Unresolved</span></td>
                                                            <%
                                                                }
                                                            %>
                                                        </tr>

                                                        <%  }%>
                                                    </tbody>
                                                </table>
                                                <a class="tableMore" href="ContentAdmin?pageTransit=goToAllReportedListing#errandReviewReport">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                            </div>
                                        </div>
                                        --%>
                                        
                                        <%-- fourth tab --%>
                                        <div class="tab-pane" id="marketplaceReport">
                                            <div class="widget-content no-padding">
                                                <table class="table table-striped table-checkable table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th class="hidden-xs">Report Date</th>
                                                            <th>Marketplace Item ID</th>
                                                            <th>Report Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            ArrayList<Vector> reportList4 = (ArrayList) request.getAttribute("reportList");

                                                            for (int i = 0; i <= reportList4.size() - 1; i++) {
                                                                Vector v = reportList4.get(i);
                                                                String reportID = String.valueOf(v.get(0));
                                                                String reportStatus = String.valueOf(v.get(1));
                                                                String reportDescription = String.valueOf(v.get(2));
                                                                String reportDate = String.valueOf(v.get(3));
                                                                String reportedItemID = String.valueOf(v.get(4));
                                                                String reportedPosterID = String.valueOf(v.get(5));
                                                                String reportedReporterID = String.valueOf(v.get(6));


                                                        %>
                                                        <tr>
                                                            <td><%= reportDate%></td>
                                                            <td><%= reportedItemID%></td>
                                                            <%
                                                                if (reportStatus.equals("Resolved (No Issue Found)")) {
                                                            %>
                                                            <td><span class="label label-success">Resolved (No Issue Found)</span></td>
                                                            <%
                                                            } else if (reportStatus.equals("Resolved (Delisted)")) {
                                                            %>
                                                            <td><span class="label label-success">Resolved (Marketplace Item Delisted)</span></td>
                                                            <%
                                                            } else {
                                                            %>
                                                            <td><span class="label label-warning">Unresolved</span></td>
                                                            <%
                                                                }
                                                            %>

                                                        </tr>

                                                        <%  }%>
                                                    </tbody>
                                                </table>
                                                <a class="tableMore" href="ContentAdmin?pageTransit=goToAllReportedListing#marketplaceReport">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <%-- end of tab contents --%>
                                </div>
                            </div>
                        </div>
                        <%-- recent event requests table --%>
                        <div class="col-md-5">
                            <div class="widget box">
                                <div class="widget-header">
                                    <h4><i class="fa fa-reorder fa-5x"></i>&nbsp;Recent Event Requests</h4>
                                    <div class="toolbar no-padding">
                                        <div class="btn-group">
                                            <span class="btn btn-xs widget-collapse"><i class="fa fa-chevron-down"></i></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content no-padding">
                                    <table class="table table-striped table-checkable table-hover">
                                        <thead>
                                            <tr>
                                                <th class="hidden-xs">Request Date</th>
                                                <th>Requestor</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<Vector> eventRequest = (ArrayList) request.getAttribute("eventRequestList");

                                                for (int i = 0; i <= eventRequest.size() - 1; i++) {
                                                    Vector v = eventRequest.get(i);
                                                    String requestID = String.valueOf(v.get(0));
                                                    String requestStatus = String.valueOf(v.get(1));

                                                    String requestDate = String.valueOf(v.get(2));
                                                    String requesterID = String.valueOf(v.get(3));

                                            %>
                                            <tr>
                                                <td><%= requestDate%></td>
                                                <td><%= requesterID%></td>
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
                                            <%  }%>
                                        </tbody>
                                    </table>
                                    <a class="tableMore" href="ContentAdmin?pageTransit=goToEventRequest">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="widget box">
                                <div class="widget-header">
                                    <h4><i class="fa fa-reorder fa-5x"></i>&nbsp;Recent Marketplace Transactions</h4>
                                    <div class="toolbar no-padding">
                                        <div class="btn-group">
                                            <span class="btn btn-xs widget-collapse"><i class="fa fa-chevron-down"></i></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content no-padding">
                                    <table class="table table-striped table-checkable table-hover" style="font-size: 13px;">
                                        <thead>
                                            <tr>
                                                <th data-class="expand">Transaction Date</th>
                                                <th data-class="expand">Seller ID</th>
                                                <th data-class="expand">Buyer ID</th>
                                                <th data-hide="phone">Listing Price</th>
                                                <th data-hide="phone">Transaction Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<Vector> viewRecentItemTransactionList = (ArrayList) request.getAttribute("viewRecentItemTransactionList");
                                                if (!viewRecentItemTransactionList.isEmpty()) {
                                                    for (int i = 0; i <= viewRecentItemTransactionList.size() - 1; i++) {
                                                        Vector v = viewRecentItemTransactionList.get(i);
                                                        String itemID = String.valueOf(v.get(0));
                                                        String itemTransDate = String.valueOf(v.get(1));
                                                        String itemTransSellerID = String.valueOf(v.get(2));
                                                        String itemTransBuyerID = String.valueOf(v.get(3));
                                                        String itemListingPriceTrans = String.valueOf(v.get(4));
                                                        String itemTransPrice = String.valueOf(v.get(5));
                                            %>
                                            <tr>
                                                <td><%= itemTransDate%><span style="display: none;">;<%= itemID%></span></td>
                                                <td><%= itemTransSellerID%></td>
                                                <td><%= itemTransBuyerID%></td>
                                                <td>$<%= itemListingPriceTrans%></td>
                                                <td>$<%= itemTransPrice%></td>
                                            </tr>
                                            <%      }   %>
                                            <%  }%>
                                        </tbody>
                                    </table>
                                    <a class="tableMore" href="MarketplaceAdmin?pageTransit=goToViewItemTransactionList">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="widget box">
                                <div class="widget-header">
                                    <h4><i class="fa fa-reorder fa-5x"></i>&nbsp;Recent Errands Transactions</h4>
                                    <div class="toolbar no-padding">
                                        <div class="btn-group">
                                            <span class="btn btn-xs widget-collapse"><i class="fa fa-chevron-down"></i></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content no-padding">
                                    <table class="table table-striped table-checkable table-hover" style="font-size: 13px;">
                                        <thead>
                                            <tr>
                                                <th>Job Transaction Date</th>
                                                <th class="hidden-xs">Job Title</th>
                                                <th>Job Poster</th>
                                                <th>Job Taker</th>
                                                <th class="align-center">Transaction Rate</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<Vector> latestTransList = (ArrayList) request.getAttribute("latestTransList");
                                                if (!latestTransList.isEmpty()) {
                                                    for (int i = 0; i <= latestTransList.size() - 1; i++) {
                                                        Vector v = latestTransList.get(i);
                                                        String jobTransDate = String.valueOf(v.get(0));
                                                        String posterID = String.valueOf(v.get(1));
                                                        String takerID = String.valueOf(v.get(2));
                                                        String jobTitle = String.valueOf(v.get(3));
                                                        //String jobID = String.valueOf(v.get(4));
                                                        //String jobRate = String.valueOf(v.get(5));
                                                        //String jobRateType = String.valueOf(v.get(6));
                                                        String jobTransRate = String.valueOf(v.get(4));
                                                        String jobTransRateType = String.valueOf(v.get(5));
                                            %>
                                            <tr>
                                                <td><%= jobTransDate%></td>
                                                <td><%= jobTitle%></td>
                                                <td class="hidden-xs"><%= posterID%></td>
                                                <td><%= takerID%></td>
                                                <td>$<%= jobTransRate%>/<%= jobTransRateType%></td>
                                            </tr>
                                            <%   }%>
                                            <% }%>
                                        </tbody>
                                    </table>
                                    <a class="tableMore" href="ErrandsAdmin?pageTransit=goToViewJobTransactions">View More&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="widget box">
                                <div class="widget-header">
                                    <h4><i class="fa fa-reorder fa-5x"></i>&nbsp;Recent Company Reviews</h4>
                                    <div class="toolbar no-padding">
                                        <div class="btn-group">
                                            <span class="btn btn-xs widget-collapse"><i class="fa fa-chevron-down"></i></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content no-padding">
                                    <table class="table table-striped table-checkable table-hover">
                                        <thead>
                                            <tr>
                                                <th class="hidden-xs">Date</th>
                                                <th>Poster</th>
                                                <th>Company</th>
                                                <th>Review Title</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% ArrayList<Vector> companyReviewList = (ArrayList) request.getAttribute("recentCompanyReviewList");
                                                if (!companyReviewList.isEmpty()) {
                                                    for (int i = 0; i <= companyReviewList.size() - 1; i++) {
                                                        Vector v = companyReviewList.get(i);
                                                        String reviewDate = String.valueOf(v.get(0));
                                                        String reviewPoster = String.valueOf(v.get(1));
                                                        String reviewCompany = String.valueOf(v.get(2));
                                                        String reviewTitle = String.valueOf(v.get(3));
                                            %>
                                            <tr>
                                                <td class="hidden-xs"><%= reviewDate%></td>
                                                <td><%= reviewPoster%></td>
                                                <td><%= reviewCompany%></td>
                                                <td><%= reviewTitle%></td>
                                            </tr>
                                            <% }
                                                }%>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="table-footer">
                                            <div class="col-md-12">
                                                <a href="VoicesAdmin?pageTransit=goToViewCompanyReviewList" style="color: #555; text-decoration: none;">VIEW MORE&nbsp;<i class="pull-right fa fa-chevron-right"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5"></div>
                    </div>
                </div>
            </div>
    </body>
</html>