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
        <title>EduTech Admin - Modules</title>
            
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
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>Modules</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li>
                                            <a href='EduTechAdmin?pageTransit=NewModule'>
                                            <button id="create" type="button" class="btn btn-default">
                                                
                                                    <i class="fas fa-plus"></i>&nbsp;&nbsp;Create New Module
                                            </button></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <table id="datatable-responsive" class="table table-striped table-bordered table-condensed table-hover nowrap" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Module Code</th>
                                                <th>Name</th>
                                                <th>Credits</th>
                                                <th>Assigned To Semester</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>                                                                                       
                                        <tbody>
                                            <% 
                                                ArrayList moduleList = (ArrayList)request.getAttribute("moduleList");
                                                if(moduleList!=null){
                                                    for(Object o : moduleList){
                                                        ArrayList moduleData = (ArrayList) o;
                                            %>
                                            <tr>
                                                <td id='id'><%=moduleData.get(0)%></td>
                                                <td><%=moduleData.get(1)%></td>
                                                <td><%=moduleData.get(2)%></td>
                                                <td><%=moduleData.get(4)%> (ID: <%=moduleData.get(3)%>)</td>
                                                <td>
                                                    <ul class="list-inline">
                                                        <li>
                                                            <a id='view'><i class="fas fa-eye fa-lg"></i></a>                                                                              </li>
                                                        <li>
                                                            <a href="EduTechAdmin?pageTransit=EditModule&id=<%=moduleData.get(0)%>"><i class="fas fa-edit fa-lg"></i></a>                                                            
                                                        </li>
                                                        <li>
                                                            <a id="delete"><i class="fas fa-trash fa-lg"></i></a> 
                                                        </li>
                                                    </ul>
                                                </td>
                                            </tr>                                                                                                                                                                                           
                                            <%}}%>
                                        </tbody>
                                    </table>
                                </div>
                                        <div id="viewModule-iframe"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <!--iziModal-->
        <script src="js/commoninfrastructure/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <!-- FastClick -->
        <script src="js/commoninfrastructure/admin/webjs/fastclick.js"></script>
        <!-- NProgress -->
        <script src="js/commoninfrastructure/admin/webjs/nprogress.js"></script>
        <!-- iCheck -->
        <script src="js/commoninfrastructure/admin/webjs/icheck.min.js"></script>
        <!-- Datatables -->
        <script src="js/commoninfrastructure/admin/webjs/dataTables/jquery.dataTables.min.js"></script>
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
        <!--Page specific JS-->
        <script src="js/edutech/admin/webjs/modules/ModuleListJS.js"></script>

        <!--System Admin Base JS-->
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
    </body>
</html>