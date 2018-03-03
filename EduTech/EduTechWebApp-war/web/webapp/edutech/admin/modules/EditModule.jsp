<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/webapp/edutech/admin/SessionCheck.jspf" %>
<%
    //Extracting field values from ArrayList passed from servlet to jsp.
    ArrayList moduleInfo = (ArrayList)request.getAttribute("moduleInfo");
    String moduleCode,name,credits,description, semester;
    moduleCode=semester=name=credits=description= "";
    
    //Used in table below.
    ArrayList eventList = (ArrayList) request.getAttribute("eventList");
    
    //ArrayList exists and is not empty. 
    if(moduleInfo!=null && !moduleInfo.isEmpty()){
        name = (String) moduleInfo.get(1);
        credits = (String)moduleInfo.get(2);
        description = (String)moduleInfo.get(4);
        moduleCode = (String)moduleInfo.get(0);
        semester = (String)moduleInfo.get(3);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - Edit Module</title>
        
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
                        <h3>Edit <%=moduleCode%></h3>
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
                                    <label class="col-xs-4 control-label required">Name:</label>
                                    <div class="col-xs-8">
                                        <input type="text" value="<%=name%>" required class="form-control" name="name" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Modular Credits (MCs):</label>
                                    <div class="col-xs-8">
                                        <input type="number" min="1" value="<%=credits%>" required class="form-control" name="modularCredit" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Description:</label>
                                    <div class="col-xs-8">
                                        <textarea id="description" required class="form-control" name="description"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Assigned To Semester:</label>
                                    <div class="col-xs-8">
                                        <input type="text" required readonly class="form-control" value="<%=semester%>" name="semester"/>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-xs-12">
                                <div class="col-xs-4"></div>
                                <div class="col-xs-8"> 
                                     <!-- Pass this to servlet to handle user creation -->
                                    <input type="hidden" name="pageTransit" value="editModule"/>
                                    <input type="hidden" name="id" value="<%=moduleCode%>"/>
                                    <button type="submit" class="btn btn-primary" value="submit">Edit Module</button>
                                    <button type='reset' class='btn btn-warning'>Reset</button>
                                    <a href="EduTechAdmin?pageTransit=ModuleList"><button type="button" class="btn btn-default">Go Back To Module List</button></a>
                                </div>                              
                            </div>
                        </form>
                        
                        <!--Recurring events table-->
                        <div class="row">
                            <div class="col-xs-3">
                                <h3>Recurring Events</h3>
                            </div>
                            <div class="col-xs-9 btn-group">
                                <button type="button" class="btn btn-default pull-right" data-toggle="modal" data-target="#newEventModal"><span class="fas fa-plus"></span>&nbsp;&nbsp;Add</button>
                            </div>
                        </div>
                        <table class="table table-condensed table-striped">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Description</th>
                                    <th>Location</th>
                                    <th>Day</th>
                                    <th>From</th>
                                    <th>To</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if(eventList != null){
                                        for(Object o : eventList){
                                            ArrayList eventInfo = (ArrayList) o;
                                            String title = (String) eventInfo.get(0);
                                            String eventDescription = (String) eventInfo.get(1);
                                            String dayOfWeek = (String) eventInfo.get(2);
                                            String startTime = (String) eventInfo.get(3);
                                            String endTime = (String) eventInfo.get(4);
                                            String eventId = (String) eventInfo.get(5);
                                            String location = (String) eventInfo.get(6);
                                %>
                                <tr>
                                    <td><%=title%></td>
                                    <td><%=eventDescription%></td>
                                    <td><%=location%></td>
                                    <td><%=dayOfWeek%></td>
                                    <td><%=startTime%></td>
                                    <td><%=endTime%><a href="EduTechAdmin?pageTransit=removeEvent&eventId=<%=eventId%>&id=<%=moduleCode%>"><i class="pull-right fas fa-trash-alt"></i></a></td>
                                </tr>
                                <%
                                    }
}
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--NEW EVENT MODAL-->                    
                <div class="modal fade" id="newEventModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">New Event</h4>
                            </div>
                            <div class="modal-body">
                                <form id="eventForm" class="form-horizontal">
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">Title:</label>
                                        <input type="text" required class="form-control" name="title"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">Location:</label>
                                        <input type="text" required class="form-control" name="location"/>
                                    </div>
                                    <br>
                                    <div class="form-inline">
                                        <div class="form-group">
                                            <label for="message-text" class="control-label">Day:</label>
                                            <select class="form-control" required name="day">
                                                <option value="Monday">Monday</option>
                                                <option value="Tuesday">Tuesday</option>
                                                <option value="Wednesday">Wednesday</option>
                                                <option value="Thursday">Thursday</option>
                                                <option value="Friday">Friday</option>
                                                <option value="Saturday">Saturday</option>
                                                <option value="Sunday">Sunday</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="startTime" class="control-label">From:</label>
                                            <input type="time" required class="form-control" name="startTime"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="message-text" class="control-label">To:</label>
                                            <input type="time" required class="form-control" name="endTime"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="form-group">
                                        <label>Description:</label>
                                        <textarea class="form-control" required name="description"></textarea>
                                    </div>
                                    <input type="hidden" name="id" value="<%=moduleCode%>">
                                    <input type="hidden" name="pageTransit" value="addEventToMod">
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" form="eventForm" class="btn btn-primary">Create Event</button>
                            </div>
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
        <script>
            //jQuery func to load textarea default value.        
            $(function(){
                $("textarea#description").val("<%=description%>");
            });
        </script>
        <script src="js/edutech/admin/webjs/modules/EditModuleJS.js"></script>
    </body>
</html>
