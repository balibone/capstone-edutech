<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - EduTech Admins</title>

        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/commoninfrastructure/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/baselayout/CommonAdminBaseCSS.css" rel="stylesheet" type="text/css">

        <!--Font Awesome 5-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <%@include file="SideMenu.jspf"%>
                <%@include file="TopMenu.jspf"%>               

                <div class="right_col" role="main">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2 class="bodyHeader">EduTech Admins</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li>
                                            <a href="SystemAdmin?pageTransit=NewEduTechAdmin">
                                            <button type="button" class="btn btn-default">
                                                
                                                    <i class="fas fa-plus"></i>&nbsp;&nbsp;Create New EduTech Admin
                                            </button>
                                            </a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <table id="datatable" class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th class="w_20">Photo</th>
                                                <th>Name</th>
                                                <th>Username(E-mail)</th>
                                                <th>Date Created</th>
                                            </tr>
                                        </thead>                                                                                       
                                        <tbody>
                                            <% 
                                                ArrayList eduTechAdminList = (ArrayList)request.getAttribute("eduTechAdminList");
                                                for(Object o : eduTechAdminList){
                                                    ArrayList eduTechAdminData = (ArrayList) o;
                                            %>
                                            <tr>
                                                <td><img src="uploads/commoninfrastructure/admin/images/<%= eduTechAdminData.get(0) %>" style="max-width: 50px; max-height: 50px;" /></td>
                                                <td><%=eduTechAdminData.get(1)%></td>
                                                <td><%=eduTechAdminData.get(2)%></td>
                                                <td><%=eduTechAdminData.get(3)%></td>
                                            </tr>                                                                                                                                               
                                            <%  }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
    </body>
</html>