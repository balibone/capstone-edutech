<%@page import="java.util.ArrayList"%>
<%
                                    //Extracting field values from ArrayList passed from servlet to jsp.
                                    ArrayList moduleInfo = (ArrayList)request.getAttribute("moduleInfo");
                                    String moduleCode,name,credit,semester,description;
                                    moduleCode = name = credit = semester = description = "";
                                    ArrayList users = new ArrayList();
                                    ArrayList events = new ArrayList();
                                    int userCount = -1;
                                    //ArrayList exists and is not empty. 
                                    if(moduleInfo!=null && !moduleInfo.isEmpty()){
                                        moduleCode = (String)(moduleInfo.get(0));
                                        name = (String)moduleInfo.get(1);
                                        credit = (String)moduleInfo.get(2);
                                        semester = (String)moduleInfo.get(3);
                                        description = (String)moduleInfo.get(4);
                                        events = (ArrayList)moduleInfo.get(7);
                                        users = (ArrayList)moduleInfo.get(5);
                                        userCount = (Integer) moduleInfo.get(6);
                                        
                                    }
%>
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        
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
                                <form class="form-horizontal col-xs-6">
                                    <div class="form-group">
                                        <label class="col-xs-4 control-label required">Module Code:</label>
                                        <div class="col-xs-8">
                                            <input type="text" readonly value="<%=moduleCode%>" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-4 control-label required">Name</label>
                                        <div class="col-xs-8">
                                            <input type="text" readonly value="<%=name%>" class="form-control" name="email"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-4 control-label required">Credits:</label>
                                        <div class="col-xs-8">
                                            <input type="text" readonly value="<%=credit%>" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-4 control-label required">Semester:</label>
                                        <div class="col-xs-8">
                                            <input type="text" readonly value="<%=semester%>" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-xs-4 control-label required">Description:</label>
                                        <div class="col-xs-8">
                                            <input type="text" readonly value="<%=description%>" class="form-control" />
                                        </div>
                                    </div>
                                </form>
                                    <div class="col-xs-6">
                                        <h4>Recurring Events:</h4>
                                        <table class="table table-condensed table-striped">
                                        <thead>
                                            <tr>
                                                <th>Title</th>
                                                <th>Day</th>
                                                <th>Start</th>
                                                <th>End</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for(Object o: events){
                                                    ArrayList eventInfo = (ArrayList) o;
                                                    String title = (String)eventInfo.get(0);
                                                    String day = (String)eventInfo.get(1);
                                                    String startTime = (String)eventInfo.get(2);
                                                    String endTime = (String)eventInfo.get(3);
                                            %>
                                            <tr>    
                                                <td><%=title%></td>
                                                <td><%=day%></td>
                                                <td><%=startTime%></td>
                                                <td><%=endTime%></td>
                                            </tr>
                                            <%    
                                                }
                                                
                                            %>
                                        </tbody>
                                        </table>
                                    </div>        
                                <div class="col-xs-12">
                                    <h3 class="text-center"><%=userCount%> User(s) in this Module</h3>
                                    <table id="modulesTable" class="table table-condensed" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Username</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for(Object o:users){
                                                    ArrayList userInfo = (ArrayList) o;
                                                    String uname = (String)userInfo.get(0);
                                                    String username = (String)userInfo.get(1);
                                            %>
                                            <tr>
                                                <td><%=uname%></td>
                                                <td><%=username%></td>
                                            </tr>
                                            <% }
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
