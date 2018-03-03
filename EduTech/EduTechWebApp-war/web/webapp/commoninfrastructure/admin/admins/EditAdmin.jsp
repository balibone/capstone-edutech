<%@page import="java.util.ArrayList"%>
<%@include file="/webapp/commoninfrastructure/admin/SystemAdminSessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - Edit Admin</title>

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
                        <h3>Edit Admin</h3>
                    </div>
                    <hr>
                    <%                        String msg = (String) request.getAttribute("msg");
                        if (msg != null) {
                            Boolean success = (Boolean) request.getAttribute("success");
                            if (success) {
                    %>
                    <div class="alert alert-info" role="alert"><%=msg%></div>                    
                    <%
                    } else {
                    %>
                    <div class="alert alert-danger" role="alert"><%=msg%></div>                    
                    <%
                            }
                        }
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
                                <%
                                    //Extracting field values from ArrayList passed from servlet to jsp.
                                    ArrayList userInfo = (ArrayList) request.getAttribute("userInfo");
                                    String salutation, firstName, lastName, username, password, creationDate, type, imageFile, displayType;
                                    salutation = firstName = lastName = username = password = creationDate = type = imageFile = displayType = "";
                                    //ArrayList exists and is not empty. 
                                    if (userInfo != null && !userInfo.isEmpty()) {
                                        salutation = (String) userInfo.get(0);
                                        firstName = (String) userInfo.get(1);
                                        lastName = (String) userInfo.get(2);
                                        username = (String) userInfo.get(3);
                                        password = (String) userInfo.get(4);
                                        creationDate = (String) userInfo.get(5);
                                        type = (String) userInfo.get(6);
                                        //parse type for proper display front end.
                                        if (type.trim().equalsIgnoreCase("student")) {
                                            displayType = "Student";
                                        } else if (type.trim().equalsIgnoreCase("instructor")) {
                                            displayType = "Instructor";
                                        } else if (type.trim().equalsIgnoreCase("unifyadmin")) {
                                            displayType = "Unify Admin";
                                        } else if (type.trim().equalsIgnoreCase("edutechadmin")) {
                                            displayType = "EduTech Admin";
                                        } else if (type.trim().equalsIgnoreCase("dualadmin")) {
                                            displayType = "Dual Admin (EduTech + Unify)";
                                        } else if (type.trim().equalsIgnoreCase("superadmin")) {
                                            displayType = "Super Admin";
                                        }
                                        imageFile = (String) userInfo.get(7);
                                    }
                                %>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Salutation: </label>
                                    <div class="col-md-5">
                                        <select required autofocus class="form-control" name="salutation">
                                            <option selected value="Mr.">Mr.</option>
                                            <option value="Ms.">Ms.</option>
                                            <option value="Madam.">Madam</option>
                                            <option value="Dr.">Dr.</option>
                                        </select>
                                        Current: <%=salutation%>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">First Name:</label>
                                    <div class="col-md-5">
                                        <input type="text" value="<%=firstName%>" class="form-control" name="firstName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Last Name:</label>
                                    <div class="col-md-5">
                                        <input type="text" value="<%=lastName%>" class="form-control" name="lastName"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Username:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=username%>" class="form-control" name="username"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Password:</label>
                                    <div class="col-md-5">
                                        <input type="text" value="<%=password%>" class="form-control" name="password"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Created On:</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=creationDate%>" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">User Type:</label>
                                    <div class="col-md-5">
                                        <select required class="form-control" name="type">
                                            <option value="unifyadmin">Unify Admin</option>
                                            <option value="edutechadmin">EduTech Admin</option>
                                            <option selected value="dualadmin">Dual Admin (EduTech + Unify)</option>
                                            <option value="superadmin">Super Admin (Full Access Rights)</option>
                                        </select>
                                        Current: <%=displayType%>
                                    </div>
                                </div>                               
                            </div>
                            <div class="col-md-4">
                                <label for="output-image">Profile Image:</label>
                                <div class="image-upload">
                                    <img id="output-image" src="uploads/commoninfrastructure/admin/images/<%=imageFile%>"/>
                                </div>
                                <br>
                                <input type="hidden" id="imageReplacement" name="imageReplacement" value="no"/>
                                <input id="file-upload" name="profileImage" type="file" accept="image/*" onchange="javascript: previewImage(event); window.imageReplacement()"/>
                            </div>
                            <div class="col-md-8">
                                <div class="col-md-2"></div>
                                <div class="col-md-6">
                                    <!-- Pass this to servlet to handle user creation -->
                                    <a href="SystemAdmin?pageTransit=AllAdminList"><button type="button" class="btn btn-default">Go Back To Admin List</button></a>
                                    <input type="hidden" name="originalImage" value="<%=imageFile%>"/>
                                    <input type="hidden" name="originalType" value="<%=type%>"/>
                                    <input type="hidden" name="pageTransit" value="editAdmin"/>
                                    <input type="hidden" name="id" value="<%=username%>"/>
                                    <button type="reset" class="btn btn-warning">Reset</button>
                                    <button type="submit" class="btn btn-primary" value="submit">Edit Admin</button> 
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
