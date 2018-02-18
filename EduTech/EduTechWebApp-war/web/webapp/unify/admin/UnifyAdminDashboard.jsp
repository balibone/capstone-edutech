<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify Admin - View Item Listing</title>

        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminCommonCSS.css" rel="stylesheet" type="text/css">
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="index.html" class="site_title"><span>Unify Admin</span></a>
                        </div>

                        <div class="clearfix"></div>
                        <div class="profile clearfix">
                            <div class="profile_pic">
                                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                            </div>
                            <div class="profile_info">
                                <span>Welcome,</span>
                                <h2><%= loggedInUsername%></h2>
                            </div>
                        </div>
                        <br />
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <h3>General</h3>
                                <ul class="nav side-menu">
                                    <li><a><i class="fa fa-home"></i>&nbsp;Marketplace (Trades)&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="MarketplaceAdmin?pageTransit=goToViewItemCategoryListing">Item Categories</a></li>
                                            <li><a href="MarketplaceAdmin?pageTransit=goToViewItemListing">Item Listing</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-edit"></i>&nbsp;Errands (Jobs)&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="ErrandsAdmin?pageTransit=goToViewJobCategoryListing">Job Categories</a></li>
                                            <li><a href="ErrandsAdmin?pageTransit=goToViewJobListing">Job Listing</a></li>
                                            <li><a href="ErrandsAdmin?pageTransit=goToViewJobTransactions">Job Transactions</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-desktop"></i>&nbsp;Company Reviews&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="VoicesAdmin?pageTransit=goToViewCompanyCategoryList">Company Categories</a></li>
                                            <li><a href="VoicesAdmin?pageTransit=goToViewCompanyList">Company Listing</a></li>
                                            <li><a href="VoicesAdmin?pageTransit=goToViewCompanyRequestList">Request Listing</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-table"></i>&nbsp;Tags&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="tables.html">Tag List</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-bar-chart-o"></i>&nbsp;Content Administration&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="chartjs.html">Marketplace File Report</a></li>
                                            <li><a href="chartjs2.html">Errands File Report</a></li>
                                            <li><a href="morisjs.html">Company Review File Reports</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="top_nav">
                    <div class="nav_menu">
                        <nav>
                            <div class="nav toggle">
                                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                            </div>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="">
                                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        <img src="images/img.jpg" /><%=loggedInUsername%>
                                        <span class=" fa fa-angle-down"></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                                        <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="row top_tiles">
                        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile-stats">
                                <div class="icon"><i class="fa fa-caret-square-o-right"></i></div>
                                <div class="count"><%= request.getAttribute("itemTransTodayCount") %></div>
                                <h3 style="font-size: 13px;">Marketplace Transactions Today</h3>
                            </div>
                        </div>
                        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile-stats">
                                <div class="icon"><i class="fa fa-comments-o"></i></div>
                                <div class="count"><%= request.getAttribute("itemListingCount") %></div>
                                <h3 style="font-size: 13px;">Marketplace Listings</h3>
                            </div>
                        </div>
                        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile-stats">
                                <div class="icon"><i class="fa fa-sort-amount-desc"></i></div>
                                <div class="count"><%= request.getAttribute("errandsTransTodayCount") %></div>
                                <h3 style="font-size: 13px;">Errands Transactions Today</h3>
                            </div>
                        </div>
                        <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="tile-stats">
                                <div class="icon"><i class="fa fa-check-square-o"></i></div>
                                <div class="count"><%= request.getAttribute("errandsListingCount") %></div>
                                <h3 style="font-size: 13px;">Errands Listings</h3>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-7 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Summary Statistics</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                            <li role="presentation" class="active"><a href="#topSellers" id="tab1" role="tab" data-toggle="tab" aria-expanded="true">Top Sellers</a></li>
                                            <li role="presentation"><a href="#topJobPosters" role="tab" id="tab2" data-toggle="tab" aria-expanded="false">Top Job Posters</a></li>
                                            <li role="presentation"><a href="#topJobTakers" role="tab" id="tab3" data-toggle="tab" aria-expanded="false">Top Job Takers</a></li>
                                        </ul>
                                        <div id="myTabContent" class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade active in" id="topSellers" aria-labelledby="tab1">
                                                <p>TEST1</p>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="topJobPosters" aria-labelledby="tab2">
                                                <p>TEST2</p>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="topJobTakers" aria-labelledby="tab3">
                                                <p>TEST3</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Recent Reported Listing</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Recent Marketplace Transactions</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Recent Errands Transactions</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
    </body>
</html>