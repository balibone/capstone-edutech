<%@page import="java.util.ArrayList"%>
<%@include file="/webapp/edutech/admin/EduTechAdminSessionCheck.jspf"
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Instructors</title>
            
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/commoninfrastructure/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/baselayout/CommonAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <!-- iziModal -->
        <link href="css/unify/admin/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <!-- Datatables -->
        <link href="css/commoninfrastructure/admin/weblayout/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="css/commoninfrastructure/admin/weblayout/scroller.bootstrap.min.css" rel="stylesheet">
        <!--Font Awesome 5 JS-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
    </head>
        
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <%@include file="../SideMenu.jspf"%>
                <%@include file="../TopMenu.jspf"%>               
                <div class="right_col" role="main">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Instructors</h2>
                                    <div style="display: flex; justify-content: flex-end;" >
                                        <a href="SystemAdmin?pageTransit=MassAssign" class="btn btn-default">
                                            <i class="fas fa-users"></i>&nbsp;&nbsp;Assign Multiple Users To Multiple Mods
                                        </a>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <table id="datatable-responsive" class="table table-striped table-bordered table-condensed table-hover dt-responsive nowrap" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th class="w_20">Photo</th>
                                                <th>Name</th>
                                                <th>Username</th>
                                                <th>Date Created</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>                                                                                       
                                        <tbody>
                                            <% 
                                                ArrayList userList = (ArrayList)request.getAttribute("userList");
                                                for(Object o : userList){
                                                    ArrayList instructorData = (ArrayList) o;
                                            %>
                                            <tr>
                                                <td><img src="uploads/commoninfrastructure/admin/images/<%= instructorData.get(0) %>" style="max-width: 50px; max-height: 50px;" /></td>
                                                <td><%=instructorData.get(1)%></td>
                                                <td id="id"><%=instructorData.get(2)%></td>
                                                <td><%=instructorData.get(3)%></td>
                                                <td>
                                                    <ul class="list-inline">
                                                        <li>
                                                            <a id='view'>
                                                                <button class="btn btn-primary">
                                                                <i class="fas fa-eye"></i>&nbsp;&nbsp;View User
                                                                </button>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a class="btn btn-primary" href="EduTechAdmin?pageTransit=AssignModule&id=<%=instructorData.get(2)%>"><i class="fas fa-pencil-alt"></i>&nbsp;&nbsp;View & Assign Modules</a> 
                                                        </li>
                                                    </ul>
                                                </td>
                                            </tr>                                                                                                                                                                                           
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                                        <div id="viewInstructor-iframe"></div>
            </div>
        </div>
        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/edutech/admin/webjs/users/InstructorListJS.js" type="text/javascript"></script>

        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/dataTables.bootstrap.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/dataTables.buttons.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/buttons.bootstrap.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/buttons.flash.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/buttons.html5.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/buttons.print.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/dataTables.fixedHeader.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/dataTables.keyTable.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/dataTables.responsive.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/responsive.bootstrap.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/dataTables.scroller.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/jszip.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/pdfmake.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/vfs_fonts.js"></script>
    </body>
</html>