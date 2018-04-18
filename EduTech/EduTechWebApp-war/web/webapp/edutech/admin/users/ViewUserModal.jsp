<%@page import="java.util.ArrayList"%>
<%
                                    //Extracting field values from ArrayList passed from servlet to jsp.
                                    ArrayList userInfo = (ArrayList)request.getAttribute("userInfo");
                                    String salutation,firstName,lastName,email,contactNum,username, creationDate,type,imageFile;
                                    salutation = firstName = lastName = email = contactNum = username = creationDate = type = imageFile = "";
                                    //ArrayList exists and is not empty. 
                                    if(userInfo!=null && !userInfo.isEmpty()){
                                        salutation = (String)userInfo.get(0);
                                        firstName = (String)userInfo.get(1);
                                        lastName = (String)userInfo.get(2);
                                        email = (String)userInfo.get(3);
                                        contactNum = (String)userInfo.get(4);
                                        username = (String)userInfo.get(5);
                                        creationDate = String.valueOf(userInfo.get(6));
                                        type = (String)userInfo.get(7);
                                        imageFile = (String)userInfo.get(8);
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
        <title>View Student</title>
        
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
                        <div class="col-xs-3"><img class="img-responsive" src="uploads/commoninfrastructure/admin/images/<%= imageFile%>"/></div>
                        <div class="col-xs-9">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Salutation: </label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="<%=salutation%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">First Name:</label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="<%=firstName%>" class="form-control" name="email"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Last Name:</label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="<%=lastName%>" class="form-control" />
                                    </div>
                                </div>
                                    <div class="form-group">
                                    <label class="col-xs-4 control-label required">Last Name:</label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="<%=username%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">E-mail:</label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="<%=email%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Contact Number:</label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="+65 <%=contactNum%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">Created On:</label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="<%=creationDate%>" class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-4 control-label required">User Type:</label>
                                    <div class="col-xs-8">
                                        <input type="text" readonly value="<%=type%>" class="form-control" />
                                    </div>
                                </div> 
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>

        <!--System Admin Base JS-->
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
        <!--New User JS-->
        <script src="js/commoninfrastructure/admin/basejs/NewUserJS.js"></script> 
    </body>
</html>
