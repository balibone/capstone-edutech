<!-- ***************************************************************************************
*   Title:                  ViewUnifyUserProfile.jsp
*   Purpose:                UNIFY USER PROFILE, MARKETPLACE+ERRANDS+REVIEWS INFORMATION (UNIFY ADMIN)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.0
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
        <title>Unify Admin - Unify User List</title>

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
        <script type="text/javascript" src="js/unify/admin/webjs/userprofile/ViewUnifyUserProfileJS.js"></script>
    </head>
    <body onload="establishTime(); setInterval('updateTime()', 1000)">
        <%
            Vector userOverviewVec = (Vector) request.getAttribute("userOverviewVec");
            String activeStatus = "";
            String userProfileImage, username, userSalutation, userFirstName, userLastName, userActiveStatus;
            userProfileImage = username = userSalutation = userFirstName = userLastName = userActiveStatus = "";

            if (userOverviewVec != null) {
                userProfileImage = (String) userOverviewVec.get(0);
                username = (String) userOverviewVec.get(1);
                userSalutation = (String) userOverviewVec.get(2);
                userFirstName = (String) userOverviewVec.get(3);
                userLastName = (String) userOverviewVec.get(4);
                userActiveStatus = (String.valueOf(userOverviewVec.get(5)));
            }
        %>
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
                        <li class="current"><a href="ProfileAdmin?pageTransit=goToUnifyAdmin"><i class="fa fa-bar-chart"></i>&nbsp;Dashboard</a></li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-shopping-cart"></i>&nbsp;Marketplace (Trade)</a>
                            <ul class="sub-menu">
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemCategoryListing"><i class="fa fa-bookmark"></i>&nbsp;Item Categories</a></li>
                                <li><a href="MarketplaceAdmin?pageTransit=goToViewItemListing"><i class="fa fa-th-list"></i>&nbsp;Item Listing</a></li>
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
                                <li><a href="#"><i class="fa fa-tags"></i>&nbsp;Tag List</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="javascript:void(0);"><i class="fa fa-file"></i>&nbsp;Content Administration</a>
                            <ul class="sub-menu">
                                <li><a href="#"><i class="fa fa-shopping-cart"></i>&nbsp;Marketplace File Report</a></li>
                                <li><a href="#"><i class="fa fa-shopping-bag"></i>&nbsp;Errands File Report</a></li>
                                <li><a href="#"><i class="fa fa-building"></i>&nbsp;Company Review File Reports</a></li>
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
                            <li><i class="fa fa-user"></i><a href="ProfileAdmin?pageTransit=goToUnifyUserList">Unify User List</a>
                            </li>
                            <li class="current"><i class="fa fa-user-circle"></i><a href="ProfileAdmin?pageTransit=goToUnifyUserProfile">Unify User Profile</a></li>
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
                            <h3>Unify User Profile&nbsp;(<%= userFirstName%>&nbsp;<%= userLastName%>)</h3>
                            <span>Profile Information, Marketplace Transactions, Errands Transactions, Company Reviews</span>
                        </div>
                        <ul class="page-stats">
                            <li>
                                <div class="summary"><span>Active Unify Users</span><h3><%= request.getAttribute("activeUnifyUserCount")%></h3></div>
                                <div id="sparkline-bar2" class="graph sparkline hidden-xs">20,15,8,50,20,40,20,30</div>
                            </li>
                            <li>
                                <div class="summary"><span>Inactive Unify Users</span><h3><%= request.getAttribute("inactiveUnifyUserCount")%></h3></div>
                                <div id="sparkline-bar" class="graph sparkline hidden-xs">20,15,8,50,20,40,20,30</div>
                            </li>
                        </ul>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="tabbable tabbable-custom tabbable-full-width">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#overviewTab" data-toggle="tab">Overview</a></li>
                                    <li><a href="#marketplaceTab" data-toggle="tab">Marketplace</a></li>
                                    <li><a href="#errandsTab" data-toggle="tab">Errands</a></li>
                                    <li><a href="#companyReviewsTab" data-toggle="tab">Company Reviews</a></li>
                                </ul>
                                <div class="tab-content row">
                                    <div class="tab-pane active" id="overviewTab">
                                        <div class="col-md-2">
                                            <div class="list-group">
                                                <li class="list-group-item">
                                                    <img src="uploads/commoninfrastructure/admin/images/<%= userProfileImage%>" style="width: 151px; height: 151px;" />
                                                </li>
                                            </div>
                                        </div>
                                        <div class="col-md-10">
                                            <div class="row profile-info">
                                                <div class="col-md-7">
                                                    <h1><%= userFirstName%>&nbsp;<%= userLastName%>&nbsp;(<%= userSalutation%>)</h1>
                                                    <div class="form-group">
                                                        <label class="col-md-3">Username:</label>
                                                        <label class="col-md-9"><u><%= username%></u></label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-md-3">User Status:</label>
                                                        <%  
                                                            if (userActiveStatus.equals("true")) {
                                                                activeStatus = "Active";
                                                        %>
                                                        <label class="col-md-9"><span class="label label-success"><%= activeStatus%></span></label>
                                                        <%  
                                                            } else if (userActiveStatus.equals("false")) {
                                                                activeStatus = "Inactive";
                                                        %>
                                                        <label class="col-md-9"><span class="label label-danger"><%= activeStatus%></span></label>
                                                        <%  }   %>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="marketplaceTab">
                                        <div class="col-md-2">
                                            <div class="list-group">
                                                <li class="list-group-item">
                                                    <img src="uploads/commoninfrastructure/admin/images/<%= userProfileImage%>" style="width: 151px; height: 151px;" />
                                                </li>
                                            </div>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="row profile-info">
                                                <form class="form-horizontal" action="#">
                                                    <div class="col-md-12">
                                                        <div class="widget">
                                                            <div class="widget-header"><h4>Item Listings</h4></div>
                                                            <div class="widget-content">
                                                                <table id="userItemList" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                                                                    <thead>
                                                                        <tr>
                                                                            <th data-class="expand">Item Image</th>
                                                                            <th>Item Name</th>
                                                                            <th>Item Category</th>
                                                                            <th data-hide="expand">Seller ID</th>
                                                                            <th>Item Price</th>
                                                                            <th data-hide="phone">Item Status</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <%
                                                                            ArrayList<Vector> userItemList = (ArrayList) request.getAttribute("userItemList");
                                                                            if (!userItemList.isEmpty()) {
                                                                                for (int i = 0; i <= userItemList.size() - 1; i++) {
                                                                                    Vector v = userItemList.get(i);
                                                                                    String itemID = String.valueOf(v.get(0));
                                                                                    String itemImage = String.valueOf(v.get(1));
                                                                                    String itemName = String.valueOf(v.get(2));
                                                                                    String itemCategoryName = String.valueOf(v.get(3));
                                                                                    String itemSellerID = String.valueOf(v.get(4));
                                                                                    String itemPrice = String.valueOf(v.get(5));
                                                                                    String itemStatus = String.valueOf(v.get(6));
                                                                        %>
                                                                        <tr>
                                                                            <td><img src="uploads/unify/images/marketplace/item/<%= itemImage%>" style="width: 50px; height: 50px;" /></td>
                                                                            <td><%= itemName%><span style="display: none;">;<%= itemID%></span></td>
                                                                            <td><%= itemCategoryName%></td>
                                                                            <td><%= itemSellerID%></td>
                                                                            <td>$<%= itemPrice%></td>
                                                                            <%  if (itemStatus.equals("Available")) {%>
                                                                            <td><span class="label label-success"><%= itemStatus%></span></td>
                                                                                <%  } else if (itemStatus.equals("Reserved")) {%>
                                                                            <td><span class="label label-warning"><%= itemStatus%></span></td>
                                                                                <%  } else if (itemStatus.equals("Sold")) {%>
                                                                            <td><span class="label label-danger"><%= itemStatus%></span></td>
                                                                                <%  }   %>
                                                                        </tr>
                                                                        <%      }   %>
                                                                        <%  }   %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12 form-vertical no-margin">
                                                        <div class="widget">
                                                            <div class="widget-header"><h4>Item Transactions</h4></div>
                                                            <div class="widget-content">
                                                                <table id="userItemTransactionList" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
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
                                                                            ArrayList<Vector> userItemTransactionList = (ArrayList) request.getAttribute("userItemTransactionList");
                                                                            if (!userItemTransactionList.isEmpty()) {
                                                                                for (int i = 0; i <= userItemTransactionList.size() - 1; i++) {
                                                                                    Vector v = userItemTransactionList.get(i);
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
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="errandsTab">
                                        <div class="col-md-2">
                                            <div class="list-group">
                                                <li class="list-group-item">
                                                    <img src="uploads/commoninfrastructure/admin/images/<%= userProfileImage%>" style="width: 151px; height: 151px;" />
                                                </li>
                                            </div>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="row profile-info">
                                                <form class="form-horizontal" action="#">
                                                    <div class="col-md-12">
                                                        <div class="widget">
                                                            <div class="widget-header"><h4>Errands Listings</h4></div>
                                                            <div class="widget-content">
                                                                <table id="userErrandsList" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
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
                                                                            ArrayList<Vector> userErrandsList = (ArrayList) request.getAttribute("userErrandsList");
                                                                            if (!userErrandsList.isEmpty()) {
                                                                                for (int i = 0; i <= userErrandsList.size() - 1; i++) {
                                                                                    Vector v = userErrandsList.get(i);
                                                                                    String jobID = String.valueOf(v.get(0));
                                                                                    String jobImage = String.valueOf(v.get(1));
                                                                                    String jobTitle = String.valueOf(v.get(2));
                                                                                    String jobCategoryName = String.valueOf(v.get(3));
                                                                                    String jobPosterID = String.valueOf(v.get(4));
                                                                                    String jobTakerID = String.valueOf(v.get(5));
                                                                                    String jobRate = String.valueOf(v.get(6));
                                                                                    String jobRateType = String.valueOf(v.get(7));
                                                                                    String jobStatus = String.valueOf(v.get(8));
                                                                        %>
                                                                        <tr>
                                                                            <td><img src="uploads/unify/images/errands/job/<%= jobImage%>" style="width: 50px; height: 50px;" /></td>
                                                                            <td><%= jobTitle%><span style="display: none;">;<%= jobID%></span></td>
                                                                            <td><%= jobCategoryName%></td>
                                                                            <td><%= jobPosterID%></td>
                                                                            <td><%= jobTakerID%></td>
                                                                            <td>$<%= jobRate%>/<%= jobRateType%></td>
                                                                            <%  if (jobStatus.equals("Available")) {%>
                                                                            <td><span class="label label-success"><%= jobStatus%></span></td>
                                                                            <%  } else if (jobStatus.equals("Reserved")) {%>
                                                                            <td><span class="label label-warning"><%= jobStatus%></span></td>
                                                                            <%  } else if (jobStatus.equals("Completed")) {%>
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
                                                    <div class="col-md-12 form-vertical no-margin">
                                                        <div class="widget">
                                                            <div class="widget-header"><h4>Errands Transactions</h4></div>
                                                            <div class="widget-content">
                                                                <table id="userErrandsTransactionList" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                                                                    <thead>
                                                                        <tr>
                                                                            <th data-class="expand">Transaction Date</th>
                                                                            <th data-class="expand">Job Poster ID</th>
                                                                            <th data-class="expand">Job Taker ID</th>
                                                                            <th data-hide="phone">Job Rate</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <%
                                                                            ArrayList<Vector> userErrandsTransactionList = (ArrayList) request.getAttribute("userErrandsTransactionList");
                                                                            if (!userErrandsTransactionList.isEmpty()) {
                                                                                for (int i = 0; i <= userErrandsTransactionList.size() - 1; i++) {
                                                                                    Vector v = userErrandsTransactionList.get(i);
                                                                                    String jobID = String.valueOf(v.get(0));
                                                                                    String jobTransDate = String.valueOf(v.get(1));
                                                                                    String jobTransPosterID = String.valueOf(v.get(2));
                                                                                    String jobTransTakerID = String.valueOf(v.get(3));
                                                                                    String jobTransRate = String.valueOf(v.get(4));
                                                                                    String jobTransRateType = String.valueOf(v.get(5));
                                                                        %>
                                                                        <tr>
                                                                            <td><%= jobTransDate%><span style="display: none;">;<%= jobID%></span></td>
                                                                            <td><%= jobTransPosterID%></td>
                                                                            <td><%= jobTransTakerID%></td>
                                                                            <td>$<%= jobTransRate%>/<%= jobTransRateType%></td>
                                                                        </tr>
                                                                        <%      }   %>
                                                                        <%  }%>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="companyReviewsTab">
                                        <div class="col-md-2">
                                            <div class="list-group">
                                                <li class="list-group-item">
                                                    <img src="uploads/commoninfrastructure/admin/images/<%= userProfileImage%>" style="width: 151px; height: 151px;" />
                                                </li>
                                            </div>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="row profile-info">
                                                <form class="form-horizontal" action="#">
                                                    <div class="col-md-12">
                                                        <div class="widget">
                                                            <div class="widget-header"><h4>Company Reviews</h4></div>
                                                            <div class="widget-content">
                                                                <table id="userCompanyReviewsList" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                                                                    <thead>
                                                                        <tr>
                                                                            <th data-class="expand">Review Date</th>
                                                                            <th data-class="expand">Poster ID</th>
                                                                            <th>Review Description</th>
                                                                            <th>Employment Type</th>
                                                                            <th data-hide="phone">Salary Range</th>
                                                                            <th data-hide="phone">Review Rating</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <%
                                                                            ArrayList<Vector> userCompanyReviewsList = (ArrayList) request.getAttribute("userCompanyReviewsList");
                                                                            if (!userCompanyReviewsList.isEmpty()) {
                                                                                for (int i = 0; i <= userCompanyReviewsList.size() - 1; i++) {
                                                                                    Vector v = userCompanyReviewsList.get(i);
                                                                                    String reviewCompanyID = String.valueOf(v.get(0));
                                                                                    String reviewDate = String.valueOf(v.get(1));
                                                                                    String reviewPosterID = String.valueOf(v.get(2));
                                                                                    String reviewTitle = String.valueOf(v.get(3));
                                                                                    String reviewPros = String.valueOf(v.get(4));
                                                                                    String reviewCons = String.valueOf(v.get(5));
                                                                                    String reviewEmpType = String.valueOf(v.get(6));
                                                                                    String reviewSalaryRange = String.valueOf(v.get(7));
                                                                                    String reviewRating = String.valueOf(v.get(8));
                                                                                    String reviewThumbsUp = String.valueOf(v.get(9));
                                                                        %>
                                                                        <tr>
                                                                            <td><%= reviewDate%><span style="display: none;">;<%= reviewCompanyID%></span></td>
                                                                            <td><%= reviewPosterID%></td>
                                                                            <td>
                                                                                <strong><%= reviewTitle%></strong><br/>
                                                                                <strong>Pros:</strong>&nbsp;<%= reviewPros%><br/>
                                                                                <strong>Cons:</strong>&nbsp;<%= reviewCons%><br/>
                                                                            </td>
                                                                            <td><%= reviewEmpType%></td>
                                                                            <td><%= reviewSalaryRange%></td>
                                                                            <td><%= reviewRating%>&nbsp;(<i class="fa fa-thumbs-up"></i><%= reviewThumbsUp%>)</td>
                                                                        </tr>
                                                                        <%      }   %>
                                                                        <%  }%>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="itemListingDetails-iframe"></div>
            <div id="jobListingDetails-iframe"></div>
            <div id="companyDetails-iframe"></div>
        </div>
    </body>
</html>