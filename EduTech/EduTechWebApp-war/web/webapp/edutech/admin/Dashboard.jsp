<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%
    ArrayList semInfo = (ArrayList)request.getAttribute("semInfo");
    String semTitle = "";
    String semMods = "";
    if(semInfo!=null && !semInfo.isEmpty()){
        semTitle =(String) semInfo.get(0);
        semMods =(String) semInfo.get(1);
    }


%>
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
                        
                        <!--Active Users-->
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
                            </div>
                        </div>
                        <!--Current Semester-->
                        <div class="x_panel">
                                <div class="x_title">
                                    <h2><i class="fas fa-calendar-alt"></i> Current Semester</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="bs-example" data-example-id="simple-jumbotron">
                                        <div class="jumbotron">
                                            <h1><%=semTitle%></h1>
                                            <p class="pull-right"><%=semMods%> active module(s) this semester.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <!--Semester & Modules-->
                        <div class="x_panel">
                            <div class="x_title"><h2><i class="fas fa-book"></i> Semesters & Modules</h2>                                        
                                <div class="clearfix"></div>
                            </div>
                            <div class="row top_tiles ">
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("semesterCount")%></div>
                                        <h3>Semesters</h3>
                                    </div>
                                </div>
                                <div class="animated flipInY col-lg-3 col-md-3 col-sm-6 col-xs-12">
                                    <div class="tile-stats">
                                        <div class="count"><%=request.getAttribute("moduleCount")%></div>
                                        <h3>Modules</h3>
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