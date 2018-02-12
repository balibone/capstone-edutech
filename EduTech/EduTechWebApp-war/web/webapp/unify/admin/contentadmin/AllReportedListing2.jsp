<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify Admin - Reported Errands Listing</title>

        <!-- CSS -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">
        
        <!-- iCheck -->
        <link href="https://colorlib.com/polygon/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        
        <!-- Datatables -->
        <link href="css/unify/admin/baselayout/dataTable/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="https://colorlib.com/polygon/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="https://colorlib.com/polygon/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="css/unify/admin/baselayout/dataTable/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="https://colorlib.com/polygon/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
    
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div id="header"></div>
                <div id="sidebar"></div>
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
                                            <li><a href="index.html">Item Categories</a></li>
                                            <li><a href="index2.html">Item Listing</a></li>
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
                                            <li><a href="ContentAdmin?pageTransit=goToTagListing">Tag Listing</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-bar-chart-o"></i>&nbsp;Content Administration&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="ContentAdmin?pageTransit=goToReportedMarketplaceListing">Marketplace Reports</a></li>
                                            <li><a href="ContentAdmin?pageTransit=goToReportedErrandsListing">Errands Reports</a></li>
                                            <li><a href="ContentAdmin?pageTransit=goToReportedReviewListing">Company Review Reports</a></li>
                                            <li><a href="ContentAdmin?pageTransit=goToAllReportedListing">All Reports</a></li>
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
                
                
                <%-- BODY --%>
                
                <div class="right_col" role="main">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_title">
                                <h2 class="bodyHeader">Marketplace Reported Listing</h2>
                                <ul class="nav navbar-right panel_toolbox">
                                 
                                <%-- collapse button --%>
                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                </li>
                                <li class="dropdown">
                                <i> </i>
                                <i> </i>
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"></a>
                                <%-- collapse button end --%>
                                    
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                
                                
                                <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox" id="check-all" class="flat"></th>
                                            <th>Report ID</th>
                          <th>Report Status</th>
                          <th>Report Description</th>
                          <th>Report Date</th>
                          <th>Reported Item ID</th>
                          <th>Reported Poster ID</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                        <%
                                             ArrayList<Vector> reportList = (ArrayList) request.getAttribute("reportList");
                                             if (reportList.isEmpty()) {
                                         %>
                        
                             <tr>
                                             <td colspan="7" style="text-align: center;">There are no reported items available.</td>
                                         </tr>
                             <%
                                         } else {
                                             for (int i = 0; i <= reportList.size() - 1; i++) {
                                                 Vector v = reportList.get(i);
                                                 String reportID = String.valueOf(v.get(0));
                                                 String reportStatus = String.valueOf(v.get(1));
                                                 String reportDescription = String.valueOf(v.get(2));
                                                 String reportDate = String.valueOf(v.get(3));
                                                 String reportedItemID = String.valueOf(v.get(4));
                                                 String reportedPosterID = String.valueOf(v.get(5));
                                                 
                                         %>
                                         <tr>
                                             <th><input type="checkbox" id="check-all" class="flat"></th>
                                             <td><%= reportID %></td>
                                             <td><%= reportStatus %></td>
                                             <td><%= reportDescription %></td>
                                             <td><%= reportDate %></td>
                                             <td><%= reportedItemID %></td>
                                             <td><%= reportedPosterID %></td>
                                             
                                          </tr>
                                          <%      }   %>
                                         <%  }%>
                       
                      </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                
                <footer>
                    <div class="pull-right">
                        Unify Admin @ Edubox (2018)
                    </div>
                    <div class="clearfix"></div>
                </footer>
            </div>
        </div>

        <!-- jQuery -->
        <script data-cfasync="false" src="/cdn-cgi/scripts/d07b1474/cloudflare-static/email-decode.min.js"></script><script src="https://colorlib.com/polygon/vendors/jquery/dist/jquery.min.js"></script>                                 
                                         
        <!-- JAVASCRIPT -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
        
        <!-- iCheck -->
        <script src="https://colorlib.com/polygon/vendors/iCheck/icheck.min.js"></script>
        
        <!-- Datatables -->
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="https://colorlib.com/polygon/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/jszip/dist/jszip.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/pdfmake/build/pdfmake.min.js"></script>
        <script src="https://colorlib.com/polygon/vendors/pdfmake/build/vfs_fonts.js"></script>
    </body>
</html>