<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%
    ArrayList userInfo = (ArrayList)request.getAttribute("userInfo");
    String firstName, lastName, username;
    firstName=lastName=username="";
    if(userInfo != null && !userInfo.isEmpty()){
        username = (String) userInfo.get(3);
        firstName = (String) userInfo.get(1);
        lastName = (String) userInfo.get(2);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - Assign Modules</title>
        
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
        
        <!--Font Awesome 5 JS-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
        
        <!--Custom styling-->
        <!--        <style>
        .push-to-bottom{
        position: absolute;
        width: 100%;
        bottom: 5%;
        }
        </style>        -->
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">            
                <%@include file="../SideMenu.jspf"%>
                <%@include file="../TopMenu.jspf"%>               
                <div class="right_col" role="main">
                    <div>
                        <h3>Assign module to <%=firstName+" "+lastName%></h3>
                    </div>
                    <hr>
                    <div class="row pull-left">
                        <%
                            String msg = (String)request.getAttribute("msg");
                            if(msg!=null){
                                Boolean success = (Boolean)request.getAttribute("success");
                                if(success){
                        %>
                        <div class="alert alert-info alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <%=msg%>
                        </div>                    
                            <%
                        }else{
                            %>
                            <div id="errorAlert" class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <%=msg%>
                            </div>                    
                                <%
}}
                                %>
                        <!--Submit form to EduTechAdmin Servlet-->
                        <form action="EduTechAdmin" method="POST" class="form-horizontal">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Assign this module:</label>
                                    <div class="col-xs-8">
                                        <select class="form-control" required name="moduleCode">
                                            <%
                                                ArrayList allModuleList = (ArrayList)request.getAttribute("allModuleList");
                                                if(allModuleList!=null && !allModuleList.isEmpty()){
                                                    for(Object o:allModuleList){
                                                        ArrayList modInfo = (ArrayList) o;%>
                                                        <option value="<%=modInfo.get(0)%>">Module Code: <%=modInfo.get(0)%> | ID: <%=modInfo.get(1)%> </option>
                                            <%        }
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-xs-12">
                                <div class="col-xs-4"></div>
                                <div class="col-xs-8"> 
                                    <input type="hidden" name="pageTransit" value="assignModule"/>
                                    <input type="hidden" name="id" value="<%=username%>"/>
                                    <button type="submit" class="btn btn-primary" value="submit">Assign Module</button>
                                    <a href="EduTechAdmin?pageTransit=StudentList"><button type="button" class="btn btn-default">Go Back To Student List</button></a>
                                </div>                              
                            </div>
                        </form>
                        
                        <!--Your Modules table-->
                        <div class="row">
                            <h4 class="text-center"><strong>Modules assigned to <%=firstName+" "+lastName%></strong></h4>
                            <table class="table table-condensed table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Module Code</th>
                                        <th>Name</th>
                                        <th>Credits</th>
                                        <th>Assigned To Semester</th>
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
                                        <td><%=moduleData.get(0)%></td>
                                        <td><%=moduleData.get(1)%></td>
                                        <td><%=moduleData.get(2)%></td>
                                        <td><%=moduleData.get(4)%> (ID: <%=moduleData.get(3)%>)
                                            <a class="btn btn-default pull-right" onclick="return confirm('Really Unassign Module?')" href="EduTechAdmin?pageTransit=unassignModule&moduleCode=<%=moduleData.get(0)%>&id=<%=username%>"><i class="fas fa-times"></i></a>
                                        </td>
                                    </tr>                                                                                                                                    <%}}%>
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
        <!--System Admin Base JS-->
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
    </body>
</html>
