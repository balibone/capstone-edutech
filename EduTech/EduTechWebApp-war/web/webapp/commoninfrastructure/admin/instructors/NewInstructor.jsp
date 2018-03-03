<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/commoninfrastructure/admin/SystemAdminSessionCheck.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - New Instructor</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/commoninfrastructure/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/baselayout/CommonAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <!-- NProgress -->
        <link href="css/commoninfrastructure/admin/weblayout/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="css/commoninfrastructure/admin/weblayout/green.css" rel="stylesheet">
        <!-- Datatables -->
        <link href="css/commoninfrastructure/admin/weblayout/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/scroller.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/baselayout/NewUserCSS.css" rel="stylesheet">
        
        <!--Font Awesome 5 JS-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <!--Side Menu is 3 col wide-->
                <%@include file="../SideMenu.jspf"%>
                <%@include file="../TopMenu.jspf"%>               
                <div class="right_col" role="main">
                    <div>
                    <h3>New Instructor</h3>
                    </div>
                    <hr>
                    <%
                    String msg = (String)request.getAttribute("msg");
                    if(msg!=null){
                        Boolean success = (Boolean)request.getAttribute("success");
                        if(success){
                    %>
                    <div class="alert alert-info" role="alert"><%=msg%></div>                    
                    <%
                        }else{
                    %>
                    <div class="alert alert-danger" role="alert"><%=msg%></div>                    
                    <%
}}
                    %>
                    <div class="row">
                        <!--Submit form to SystemAdmin Servlet-->
                        <form action="SystemAdmin" method="POST" class="form-horizontal" enctype="multipart/form-data">
                            <div class="col-md-8">
                                <!--
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Name</label>
                                    <div class="col-md-5">
                                        <input type="text" required class="form-control" name="name" />
                                    </div>
                                </div>
                                -->
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Salutation</label>
                                    <div class="col-md-5">
                                        <select required autofocus class="form-control" name="salutation">
                                            <option value="Mr.">Mr.</option>
                                            <option value="Ms.">Ms.</option>
                                            <option value="Madam.">Madam</option>
                                            <option value="Dr.">Dr.</option>
                                        </select>
                                    </div>
                                </div>
                                    
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">First Name</label>
                                    <div class="col-md-5">
                                        <input type="text" required class="form-control" name="firstName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Last Name</label>
                                    <div class="col-md-5">
                                        <input type="text" required class="form-control" name="lastName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Username</label>
                                    <div class="col-md-5">
                                        <input type="text" required class="form-control" name="username" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Password</label>
                                    <div class="col-md-5">
                                        <input type="text" required class="form-control" name="password" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label for="output-image">Profile Image:</label>
                                <div class="image-upload">
                                    <img id="output-image" />
                                </div>
                                <br>
                                <input id="file-upload" required name="profileImage" type="file" accept="image/*" onchange="javascript: previewImage(event)"/>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="col-md-2">                                    
                                </div>
                                <div class="col-md-5"> 
                                    <!-- Pass this to servlet to handle user creation -->
                                    <input type="hidden" name="pageTransit" value="createInstructor"/>
                                    <button type="submit" class="btn btn-primary" value="submit">Create Instructor</button><a href="SystemAdmin?pageTransit=InstructorList"><button type="button" class="btn btn-default">Go Back To Instructor List</button></a>
                                </div>                               
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <!-- FastClick -->
        <script src="js/commoninfrastructure/admin/webjs/fastclick.js"></script>
        <!-- NProgress -->
        <script src="js/commoninfrastructure/admin/webjs/nprogress.js"></script>
        <!-- iCheck -->
        <script src="js/commoninfrastructure/admin/webjs/icheck.min.js"></script>    
        <!--System Admin Base JS-->
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
        <!--New User JS-->
        <script src="js/commoninfrastructure/admin/basejs/NewUserJS.js"></script> 
    </body>
</html>
