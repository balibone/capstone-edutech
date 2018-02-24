<%-- 
    Document   : NewModule
    Created on : 22 Feb, 2018, 8:05:15 PM
    Author     : Derian
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - New Module</title>
        
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
                        <h3>New Module</h3>
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
                                    <label class="col-xs-4 control-label required">Module Code:</label>
                                    <div class="col-xs-8">
                                        <input type="text" required class="form-control" name="moduleCode" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Name:</label>
                                    <div class="col-xs-8">
                                        <input type="text" required class="form-control" name="name" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Modular Credits (MCs):</label>
                                    <div class="col-xs-8">
                                        <input type="number" min="1" required class="form-control" name="modularCredit" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Description:</label>
                                    <div class="col-xs-8">
                                        <textarea required class="form-control" name="description"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Assigned To Semester:</label>
                                    <div class="col-xs-8">
                                        <select class="form-control" name="semID">
                                            <%
                                                ArrayList semesterList = (ArrayList)request.getAttribute("semesterList");
                                                if(semesterList!=null && !semesterList.isEmpty()){
                                                    for(Object o:semesterList){
                                                        ArrayList semInfo = (ArrayList) o;%>
                                                        <option value="<%=semInfo.get(0)%>">Semester: <%=semInfo.get(1)%> | ID: <%=semInfo.get(0)%> </option>
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
                                     <!-- Pass this to servlet to handle user creation -->
                                    <input type="hidden" name="pageTransit" value="createModule"/>
                                    <button type="submit" class="btn btn-primary" value="submit">Create Module</button>
                                    <button type='reset' class='btn btn-warning'>Reset</button>
                                    <a href="EduTechAdmin?pageTransit=ModuleList"><button type="button" class="btn btn-default">Go Back To Module List</button></a>
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
        
    </body>
</html>
