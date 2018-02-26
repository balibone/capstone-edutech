<!-- ***************************************************************************************
*   Title:                  ViewJobListing.jsp
*   Purpose:                LIST OF JOB LISTINGS (UNIFY ADMIN)
*   Created By:             CHEN MENG
*   Modified By:            TAN CHIN WEE WINSTON
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.1
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
**************************************************************************************** -->

<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector"%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
        <title>Unify Admin - Job Listings</title>
        
        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/iziModal.min.css" rel="stylesheet" type="text/css" />

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
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminCommonJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.newsTicker.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/iziModal.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/webjs/errands/ViewJobListingJS.js"></script>
    </head>
    <body onload="establishTime(); setInterval('updateTime()', 1000)">
        <header class="header navbar navbar-fixed-top" role="banner">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li class="nav-toggle"><a href="javascript:void(0);"><i class="fa fa-th-list"></i></a></li>
                </ul>
                <a class="navbar-brand" href="index.html">
                    <img src="images/edubox-unify-logo.png" style="width:108px;height:38px;" />
                    <small><strong><sub>ADMIN</sub></strong</small>
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
                        <li><a href="ProfileAdmin?pageTransit=goToUnifyAdmin"><i class="fa fa-bar-chart"></i>&nbsp;Dashboard</a></li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-shopping-cart"></i>&nbsp;Marketplace (Trade)</a>
                            <ul class="sub-menu">
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemCategoryListing"><i class="fa fa-bookmark"></i>&nbsp;Item Categories</a></li>
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemListing"><i class="fa fa-th-list"></i>&nbsp;Item Listing</a></li>
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemTransactionList"><i class="fa fa-book"></i>&nbsp;Item Transactions</a></li>
                            </ul>
                        </li>
                        <li class="current">
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
                                <li><a href="ContentAdmin?pageTransit=goToReportedReviewListing"><i class="fa fa-wpforms"></i>&nbsp;Company Review Reports</a></li>
                                <li><a href="ContentAdmin?pageTransit=goToReportedErrandsListing"><i class="fa fa-wpforms"></i>&nbsp;Errands Reports</a></li>
                                <li><a href="ContentAdmin?pageTransit=goToReportedErrandsReviewListing"><i class="fa fa-wpforms"></i>&nbsp;Errands Review Reports</a></li>
                                <li><a href="ContentAdmin?pageTransit=goToReportedMarketplaceListing"><i class="fa fa-wpforms"></i>&nbsp;Marketplace Reports</a></li>
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
                            <li><i class="fa fa-home"></i><a href="ProfileAdmin?pageTransit=goToUnifyAdmin">Dashboard</a></li>
                            <li class="current">
                                <i class="fa fa-bookmark"></i>
                                <a href="ErrandsAdmin?pageTransit=goToViewJobListing">Errands (Job Listings)</a>
                            </li>
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
                            <h3>Job Listings</h3><span>Click on each job to view the associated job information.</span>
                        </div>
                        <ul class="page-stats">
                            <li>
                                <div class="summary"><span>Available Listings</span><h3><%= request.getAttribute("availableJobListingCount")%></h3></div>
                                <div id="sparkline-bar2" class="graph sparkline hidden-xs">20,15,8,50,20,40,20,30</div>
                            </li>
                            <li>
                                <div class="summary"><span>Reserved Listings</span><h3><%= request.getAttribute("reservedJobListingCount")%></h3></div>
                                <div id="sparkline-bar4" class="graph sparkline hidden-xs">20,15,8,50,20,40,20,30</div>
                            </li>
                            <li>
                                <div class="summary"><span>Completed Listings</span><h3><%= request.getAttribute("completedJobListingCount")%></h3></div>
                                <div id="sparkline-bar" class="graph sparkline hidden-xs">20,15,8,50,20,40,20,30</div>
                            </li>
                        </ul>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <%
                                String successMessage = (String) request.getAttribute("successMessage");
                                if (successMessage != null) {
                            %>
                            <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                                <button type="button" class="close" id="closeSuccess">&times;</button><%= successMessage%>
                            </div>
                            <%  } %>
                            <%
                                String errorMessage = (String) request.getAttribute("errorMessage");
                                if (errorMessage != null) {
                            %>
                            <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                                <button type="button" class="close" id="closeError">&times;</button><%= errorMessage%>
                            </div>
                            <%  } %>

                            <div class="widget box">
                                <div class="widget-header">
                                    <h4><i class="fa fa-reorder"></i>&nbsp;Job Listing</h4>
                                    <div class="toolbar no-padding">
                                        <div class="btn-group">
                                            <!-- SUPPORT BAR --><span>&nbsp;</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="widget-content no-padding">
                                    <table id="jobListing" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                                        <thead>
                                            <tr>
                                                <th data-class="expand">Job Image</th>
                                                <th data-class="expand">Job Title</th>
                                                <th>Job Category</th>
                                                <th>Job Poster ID</th>
                                                <th>Job Taker ID</th>
                                                <th data-hide="phone">Job Rate</th>
                                                <th data-hide="phone">Job Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%  
                                                ArrayList<Vector> jobListing = (ArrayList) request.getAttribute("jobListing");
                                                if (!jobListing.isEmpty()) {
                                                    for (int i = 0; i <= jobListing.size() - 1; i++) {
                                                        Vector v = jobListing.get(i);
                                                        String jobID = String.valueOf(v.get(0));
                                                        String jobImage = String.valueOf(v.get(1));
                                                        String jobTitle = String.valueOf(v.get(2));
                                                        String jobCategory = String.valueOf(v.get(3));
                                                        String jobPosterID = String.valueOf(v.get(4));
                                                        String jobTakerID = String.valueOf(v.get(5));
                                                        String jobRate = String.valueOf(v.get(6));
                                                        String jobRateType = String.valueOf(v.get(7));
                                                        String jobStatus = String.valueOf(v.get(8));
                                            %>
                                            <tr>
                                                <td><img src="uploads/unify/images/errands/job/<%= jobImage%>" style="width: 50px; height: 50px;" /></td>
                                                <td><%= jobTitle%><span style="display: none;">;<%= jobID%></span></td>
                                                <td><%= jobCategory%></td>
                                                <td><%= jobPosterID%></td>
                                                <td><%= jobTakerID%></td>
                                                <td>$<%= jobRate%>/<%= jobRateType%></td>
                                                <%  if (jobStatus.equals("Available")) {   %>
                                                <td><span class="label label-success"><%= jobStatus%></span></td>
                                                <%  } else if (jobStatus.equals("Reserved")) { %>
                                                <td><span class="label label-warning"><%= jobStatus%></span></td>
                                                <%  } else if (jobStatus.equals("Completed")) { %>
                                                <td><span class="label label-danger"><%= jobStatus%></span></td>
                                                <%  }   %>
                                            </tr>
                                            <%      }   %>
                                            <%  }   %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="viewJobDetails-iframe"></div>
    </body>
</html>