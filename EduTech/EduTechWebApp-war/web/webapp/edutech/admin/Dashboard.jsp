<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>EduTech Admin - Home</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/commoninfrastructure/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/baselayout/CommonAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/weblayout/nprogress.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/weblayout/daterangepicker.css" rel="stylesheet" type="text/css">
        
        <!--Font Awesome 5-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
    </head>
    
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <%@include file="SideMenu.jspf"%>
                <%@include file="TopMenu.jspf"%>               
                
                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="">
                        <div class="x_panel">
                            <div class="x_title"><h2><i class="fas fa-globe"></i> Active Users</h2>                                        
                                <div class="clearfix"></div>
                            </div>
                            <div class="row top_tiles ">
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("studentCount")%></div>
                                        <h3>Students</h3>
                                    </div>
                                </div>
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("instructorCount")%></div>
                                        <h3>Instructors</h3>
                                    </div>
                                </div>
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("edutechCount")%></div>
                                        <h3>EduTech Admins</h3>
                                    </div>
                                </div>
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("unifyCount")%></div>
                                        <h3>Unify Admins</h3>
                                    </div>
                                </div>
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("dualCount")%></div>
                                        <h3>Dual Admins</h3>
                                    </div>
                                </div>
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("superCount")%></div>
                                        <h3>Super Admins</h3>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--User Types big panel-->
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2> <i class="fas fa-info-circle"></i> User Types</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        
                                        <div class="col-xs-3">
                                            <!-- required for floating -->
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs tabs-left">
                                                <li class="active"><a href="#student" data-toggle="tab">Students</a>
                                                </li>
                                                <li><a href="#instructor" data-toggle="tab">Instructors</a>
                                                </li>
                                                <li><a href="#edutech" data-toggle="tab">EduTech Admins</a>
                                                </li>
                                                <li><a href="#unify" data-toggle="tab">Unify Admins</a>
                                                </li>
                                                <li><a href="#dual" data-toggle="tab">Dual Admins</a>
                                                </li>
                                                <li><a href="#super" data-toggle="tab">Super Admins</a>
                                                </li>
                                            </ul>
                                        </div>
                                        
                                        <div class="col-xs-9">
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="student">
                                                    <p class="lead">Students</p>
                                                    <div class="alert alert-info" role="alert">These users are the registered students.</div>
                                                    <p class="lead">Access Rights:</p>
                                                    <h2><span class="label label-info">EduTech</span>&nbsp;
                                                        <span class="label label-warning">Unify</span>
                                                    </h2>
                                                    
                                                </div>
                                                <div class="tab-pane" id="instructor">
                                                    <p class="lead">Instructors</p>
                                                    <div class="alert alert-info" role="alert">These users are members of teaching staff.</div>
                                                    <p class="lead">Access Rights:</p>
                                                    <h2>
                                                        <span class="label label-info">EduTech</span>&nbsp;
                                                        <span class="label label-warning">Unify</span>
                                                    </h2>
                                                    
                                                </div>
                                                <div class="tab-pane" id="edutech">
                                                    <p class="lead">EduTech Admins</p>
                                                    <div class="alert alert-info" role="alert">These users are administrators of the EduTech portal.</div>
                                                    <p class="lead">Access Rights:</p>
                                                    <h2>
                                                    <span class="label label-primary">EduTech Admin Portal</span>&nbsp;
                                                    <span class="label label-info">EduTech</span>&nbsp;
                                                    <span class="label label-warning">Unify</span>
                                                    </h2>
                                                </div>
                                                <div class="tab-pane" id="unify">
                                                    <p class="lead">Unify Admins</p>
                                                    <div class="alert alert-info" role="alert">These users are administrators of the Unify portal.</div>
                                                    <p class="lead">Access Rights:</p>
                                                    <h2>
                                                    <span class="label label-success">Unify Admin Portal</span>&nbsp;
                                                    <span class="label label-info">EduTech</span>&nbsp;
                                                    <span class="label label-warning">Unify</span>
                                                    </h2>
                                                </div>
                                                <div class="tab-pane" id="dual">
                                                    <p class="lead">Dual Admins</p>
                                                    <div class="alert alert-info" role="alert">These users are administrators of both EduTech and Unify portals.</div>
                                                    <p class="lead">Access Rights:</p>
                                                    <h2>
                                                    <span class="label label-primary">EduTech Admin Portal</span>&nbsp;
                                                    <span class="label label-success">Unify Admin Portal</span>&nbsp;
                                                    <span class="label label-info">EduTech</span>&nbsp;
                                                    <span class="label label-warning">Unify</span>
                                                    </h2>
                                                </div>
                                                <div class="tab-pane" id="super">
                                                    <p class="lead">Super Admins</p>
                                                    <div class="alert alert-info" role="alert">These users have full administrator privileges and can manage user data.</div>
                                                    <p class="lead">Access Rights:</p>
                                                    <h2>
                                                    <span class="label label-default">System Admin Portal</span>&nbsp;
                                                    <span class="label label-primary">EduTech Admin Portal</span>&nbsp;
                                                    <span class="label label-success">Unify Admin Portal</span>&nbsp;
                                                    <span class="label label-info">EduTech</span>&nbsp;
                                                    <span class="label label-warning">Unify</span>
                                                    </h2>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="clearfix"></div>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /page content -->
            </div>
        </div>
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <!-- FastClick -->
        <script src="js/commoninfrastructure/admin/webjs/fastclick.js"></script>
        <!-- NProgress -->
        <script src="js/commoninfrastructure/admin/webjs/nprogress.js"></script>
        <!-- Chart.js -->
        <script src="js/commoninfrastructure/admin/webjs/Chart.min.js"></script>
        <!-- jQuery Sparklines -->
        <script src="js/commoninfrastructure/admin/webjs/jquery.sparkline.min.js"></script>
        <!-- Flot -->
        <script src="js/commoninfrastructure/admin/webjs/jquery.flot.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/jquery.flot.pie.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/jquery.flot.time.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/jquery.flot.stack.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/jquery.flot.resize.js"></script>
        <!-- Flot plugins -->
        <script src="js/commoninfrastructure/admin/webjs/jquery.flot.orderBars.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/jquery.flot.spline.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/curvedLines.js"></script>
        <!-- DateJS -->
        <script src="js/commoninfrastructure/admin/webjs/date.js"></script>
        <!-- bootstrap-daterangepicker -->
        <script src="js/commoninfrastructure/admin/webjs/moment.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/daterangepicker.js"></script>
        <!--Custom Theme Scripts-->
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
    </body>
</html>