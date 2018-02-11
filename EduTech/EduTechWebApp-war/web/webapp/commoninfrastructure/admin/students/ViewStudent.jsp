<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - View Student</title>
        
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
                    <h3>View Student</h3>
                    </div>
                    <hr>
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
                                <%
                                    //Extracting field values from ArrayList passed from servlet to jsp.
                                    ArrayList userInfo = (ArrayList)request.getAttribute("userInfo");
                                    String salutation,firstName,lastName,email,password,creationDate,type,imageFile;
                                    salutation = firstName = lastName = email = password = creationDate = type = imageFile = "";
                                    //ArrayList exists and is not empty. 
                                    if(userInfo!=null && !userInfo.isEmpty()){
                                        salutation = (String)userInfo.get(0);
                                        firstName = (String)userInfo.get(1);
                                        lastName = (String)userInfo.get(2);
                                        email = (String)userInfo.get(3);
                                        password = (String)userInfo.get(4);
                                        creationDate = (String)userInfo.get(5);
                                        type = (String)userInfo.get(6);
                                        imageFile = (String)userInfo.get(7);
                                    }
                                %>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Salutation: </label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=salutation%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">First Name:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=firstName%>" class="form-control" name="email"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Last Name:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=lastName%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Username:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=email%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Password:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=password%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Created On:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=creationDate%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">User Type:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=type%>" class="form-control" />
                                    </div>
                                </div>                               
                            </div>
                            <div class="col-md-4">
                                <img class="img-responsive" src="uploads/commoninfrastructure/admin/images/<%= imageFile%>"/>                                                             
                            </div>
                            <div class="col-md-8">
                                <a href="SystemAdmin?pageTransit=StudentList"><button type="button" class="btn btn-default">Go Back To Student List</button></a>                            
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
