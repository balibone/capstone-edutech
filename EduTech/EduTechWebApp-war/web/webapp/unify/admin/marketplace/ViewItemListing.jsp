<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector"%>
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
        <link href="css/unify/admin/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Unify Admin</span></a>
                        </div>

                        <div class="clearfix"></div>

                        <!-- menu profile quick info -->
                        <div class="profile clearfix">
                            <div class="profile_pic">
                                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                            </div>
                            <div class="profile_info">
                                <span>Welcome,</span>
                                <h2>John Doe</h2>
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
                                            <li><a href="form.html">Job Categories</a></li>
                                            <li><a href="form_advanced.html">Job Listing</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-desktop"></i>&nbsp;Company Reviews&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="general_elements.html">Company Listing</a></li>
                                            <li><a href="media_gallery.html">Review Listing</a></li>
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

                        <div class="sidebar-footer hidden-small">
                            <a data-toggle="tooltip" data-placement="top" title="Settings">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="Lock">
                                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                            </a>
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
                                        <img src="images/img.jpg" alt="">John Doe
                                        <span class=" fa fa-angle-down"></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                                        <li><a href="javascript:;"> Profile</a></li>
                                        <li>
                                            <a href="javascript:;">
                                                <span class="badge bg-red pull-right">50%</span>
                                                <span>Settings</span>
                                            </a>
                                        </li>
                                        <li><a href="javascript:;">Help</a></li>
                                        <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                                    </ul>
                                </li>

                                <li role="presentation" class="dropdown">
                                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                        <i class="fa fa-envelope-o"></i>
                                        <span class="badge bg-green">6</span>
                                    </a>
                                    <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <div class="text-center">
                                                <a>
                                                    <strong>See All Alerts</strong>
                                                    <i class="fa fa-angle-right"></i>
                                                </a>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <div class="right_col" role="main">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_title">
                                <h2 class="bodyHeader">Item Listing</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <%
                                    String successMessage = (String) request.getAttribute("successMessage");
                                    if (successMessage != null) {
                                %>
                                <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                                    <button type="button" class="close" id="closeSuccess">&times;</button>
                                    <%= successMessage %>
                                </div>
                                <%  } %>
                                <%
                                    String errorMessage = (String) request.getAttribute("errorMessage");
                                    if (errorMessage != null) {
                                %>
                                <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                                    <button type="button" class="close" id="closeError">&times;</button>
                                    <%= errorMessage %>
                                </div>
                                <%  } %>
                                
                                <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Item Image</th>
                                            <th>Item Name</th>
                                            <th>Item Category</th>
                                            <th>Seller ID</th>
                                            <th>Item Price</th>
                                            <th>Item Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Vector> itemList = (ArrayList) request.getAttribute("itemList");
                                            if (!itemList.isEmpty()) {
                                                for (int i = 0; i <= itemList.size() - 1; i++) {
                                                    Vector v = itemList.get(i);
                                                    String itemImage = String.valueOf(v.get(0));
                                                    String itemName = String.valueOf(v.get(1));
                                                    String itemCategory = String.valueOf(v.get(2));
                                                    String itemSellerID = String.valueOf(v.get(3));
                                                    String itemPrice = String.valueOf(v.get(4));
                                                    String itemStatus = String.valueOf(v.get(5));
                                        %>
                                        <tr>
                                            <td><img src="uploads/unify/images/marketplace/item/<%= itemImage %>" style="max-width: 50px; max-height: 50px;" /></td>
                                            <td><%= itemName %></td>
                                            <td><%= itemCategory %></td>
                                            <td><%= itemSellerID %></td>
                                            <td>$<%= itemPrice %></td>
                                            <td><%= itemStatus %></td>
                                        </tr>
                                        <%      }   %>
                                        <%  }%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="modal-iframe"></div>
                <div id="adminFooter"></div>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/marketplace/ViewItemListingJS.js" type="text/javascript"></script>
        
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
    </body>
</html>