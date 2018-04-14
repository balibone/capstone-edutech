
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/commoninfrastructure/admin/SystemAdminSessionCheck.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - New User</title>
        
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
                <%@include file="SideMenu.jspf"%>
                <%@include file="TopMenu.jspf"%>               
                <div class="right_col" role="main">
                    <div>
                    <h3>Mass Create Users</h3>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-12">
                            <form class="form-inline">
                                <div class="form-group">
                                    <label for="csv">User Data CSV</label>
                                    <input type="file" accept=".csv" class="form-control" id="csv" onchange="handleFileUpload(this.files)" placeholder="Upload CSV">
                                </div>
                            </form>
                            <div class="alert alert-info fade in" role="alert" style="font-size: 15px">
                                <b>Attention!</b> Users created through this facility will have <b>a default profile picture assigned to them,</b> which can be changed later. <b>Temporary passwords will also be emailed to them,</b> so ensure that all user emails in CSV are <b>valid.</b>
                            </div>
                        </div>
                            
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <h4>Preview CSV Data:</h4>
                        </div>
                        <div class="col-xs-6" style="display:flex; justify-content: flex-end;">
                            <button type="submit" class="btn btn-success" style="margin: none;">Submit CSV & Perform Mass Creation</button>
                        </div>
                        <div class="col-xs-12" id="previewTableDiv">
                            <table id="previewTable" class="table table-striped table-bordered" style="width:100%">
                                <thead>
                                    <tr>
                                        <th>Salutation</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Contact Number</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>test</td>
                                        <td>test</td>
                                        <td>test</td>
                                        <td>test</td>
                                        <td>test</td>
                                        <td>test</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
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
        <!-- DataTables-->
        <script src="js/commoninfrastructure/admin/basejs/jquery.dataTables.min.js"></script>
        <script src="js/commoninfrastructure/admin/basejs/dataTables.bootstrap.min.js"></script>
        <!-- Papa Parser -->
        <script src="js/commoninfrastructure/admin/basejs/papaparse.min.js"></script>    
        <!--System Admin Base JS-->
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js"></script>
        <!--New User JS-->
        <script src="js/commoninfrastructure/admin/basejs/NewUserJS.js"></script> 
        <!--Mass Upload Preview Table JS-->
        <script src="js/commoninfrastructure/admin/webjs/PreviewTableJS.js"></script>
    </body>
</html>
