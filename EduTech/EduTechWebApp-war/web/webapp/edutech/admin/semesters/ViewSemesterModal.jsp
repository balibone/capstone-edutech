<%@page import="java.util.ArrayList"%>
<%
                                    //Extracting field values from ArrayList passed from servlet to jsp.
                                    ArrayList semesterInfo = (ArrayList)request.getAttribute("semesterInfo");
                                    String id,title,startDate,endDate;
                                    id = title = startDate = endDate = "";
                                    ArrayList modules = new ArrayList();
                                    int moduleCount = -1;
                                    //ArrayList exists and is not empty. 
                                    if(semesterInfo!=null && !semesterInfo.isEmpty()){
                                        id = String.valueOf(semesterInfo.get(0));
                                        title = (String)semesterInfo.get(1);
                                        startDate = (String)semesterInfo.get(2);
                                        endDate = (String)semesterInfo.get(3);
                                        modules = (ArrayList)semesterInfo.get(4);
                                        moduleCount = (Integer) semesterInfo.get(5);
                                    }
%>
<%@include file="/webapp/edutech/admin/EduTechAdminSessionCheck.jspf"
 %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - View Semester</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/commoninfrastructure/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/baselayout/CommonAdminBaseCSS.css" rel="stylesheet" type="text/css">

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
                <div class="right_col" role="main">
                    <div class="row">
                        <div class="x_panel">
                            <div class="x_content">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-xs-2 control-label required">ID: </label>
                                    <div class="col-xs-4">
                                        <input type="text" readonly value="<%=id%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 control-label required">Title</label>
                                    <div class="col-xs-4">
                                        <input type="text" readonly value="<%=title%>" class="form-control" name="email"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 control-label required">Start Date:</label>
                                    <div class="col-xs-4">
                                        <input type="text" readonly value="<%=startDate%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 control-label required">End Date:</label>
                                    <div class="col-xs-4">
                                        <input type="text" readonly value="<%=endDate%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <h3 class="text-center"><%=moduleCount%> Modules in this Semester</h3>
                                        <table id="modulesTable" class="table table-condensed" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Module Code</th>
                                                    <th>Module Name</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    if(modules!=null && !modules.isEmpty()){
                                                        for(Object o:modules){
                                                            ArrayList moduleInfo = (ArrayList) o;
                                                            String moduleCode = (String)moduleInfo.get(0);
                                                            String name = (String)moduleInfo.get(1);
                                                %>
                                                <tr>
                                                    <td><%=moduleCode%></td>
                                                    <td><%=name%></td>
                                                </tr>
                                                <% }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                </div>
                            </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <!-- Datatables -->
        <script src="js/commoninfrastructure/admin/webjs/dataTables/jquery.dataTables.min.js"></script>
        <script src="js/commoninfrastructure/admin/webjs/dataTables/dataTables.bootstrap.min.js"></script>
        <!--System Admin Base JS-->
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
        <!--Custom Scripts-->
        <script>
            $(function(){
                $('#modulesTable').DataTable();
            });
        </script>
    </body>
</html>
