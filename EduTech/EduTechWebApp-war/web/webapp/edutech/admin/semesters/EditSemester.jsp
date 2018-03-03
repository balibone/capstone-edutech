<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%
                                    //Extracting field values from ArrayList passed from servlet to jsp.
                                    ArrayList semesterInfo = (ArrayList)request.getAttribute("semesterInfo");
                                    String id,title,startDate,endDate;
                                    id = title = startDate = endDate = "";
                                    ArrayList modules = new ArrayList();
                                    int moduleCount = -1;
                                    
                                    SimpleDateFormat toDate = new SimpleDateFormat("dd MMMM yyyy");
                                    SimpleDateFormat toString = new SimpleDateFormat("yyyy-MM-dd");
                                    
                                    //ArrayList exists and is not empty. 
                                    if(semesterInfo!=null && !semesterInfo.isEmpty()){
                                        id = String.valueOf(semesterInfo.get(0));
                                        title = (String)semesterInfo.get(1);
                                        startDate = toString.format(toDate.parse((String)semesterInfo.get(2)));
                                        endDate = toString.format(toDate.parse((String)semesterInfo.get(3)));
                                        modules = (ArrayList)semesterInfo.get(4);
                                        moduleCount = (Integer) semesterInfo.get(5);
                                    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/edutech/admin/SessionCheck.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>EduTech Admin - New Semester</title>
        
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
                        <h3>Edit Semester</h3>
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
                        <form id="thisForm" action="EduTechAdmin" method="POST" class="form-horizontal">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label class="col-xs-2 control-label required">Title</label>
                                    <div class="col-xs-8">
                                        <input type="text" value='<%=title%>' required class="form-control" name="title" />
                                    </div>
                                </div>
                                <div  class="form-group">
                                    <label class="col-xs-2 control-label required">Start Date</label>
                                    <div class="col-xs-8">
                                        <input id="startDate" type="date" value='<%=startDate%>' required class="form-control" name="startDate" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-2 control-label required">End Date</label>
                                    <div class="col-xs-8">
                                        <input id="endDate" type="date" value='<%=endDate%>' required class="form-control" name="endDate" />
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-xs-12">
                                <div class="col-xs-2"></div>
                                <div class="col-xs-8"> 
                                    <!-- Pass this to servlet to handle user creation -->
                                    <input type='hidden' name='id' value='<%=id%>'/>
                                    <input type="hidden" name="pageTransit" value="editSemester"/>
                                    <button type="submit" class="btn btn-primary" value="submit">Edit Semester</button>
                                    <button type='reset' class='btn btn-warning'>Reset</button>
                                    <a href="EduTechAdmin?pageTransit=SemesterList"><button type="button" class="btn btn-default">Go Back To Semester List</button></a>
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
        <script>
            $("#thisForm").on("submit", function(event){
                var startDate = document.getElementById("startDate").value;
                var endDate = document.getElementById("endDate").value;
                if(startDate >= endDate){
                    event.preventDefault();
                    alert("Invalid date range!");
                }
            });
            
        </script>
    </body>
</html>
