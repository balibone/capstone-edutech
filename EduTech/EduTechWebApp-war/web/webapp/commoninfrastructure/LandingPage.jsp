<%@page import="commoninfrasessionbeans.CommonInfraMgrBeanRemote"%>
<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to EduBox</title>
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/login/bootstrapv4.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/LandingPageCSS.css" rel="stylesheet" type="text/css">
        <style>
            a {
                color: #FFFFFF;
                text-decoration: none;
            }
            
            a:hover 
            {
                color: #FFFFFF; 
                text-decoration:none; 
                cursor:pointer;  
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-sm navbar-light bg-light justify-content-between">
            <a class="navbar-brand" href="CommonInfra?pageTransit=goToCommonLanding">
                <img src="images/edubox-logo.png" width="auto" height="30" class="d-inline-block align-top" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarsExample03">
                <ul class="navbar-nav mr-auto">
                    <%
                        if(userType.equals("superadmin")){
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="SystemAdmin?pageTransit=SystemAdminDashboard">System Admin Portal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="EduTechAdmin?pageTransit=EduTechAdminDashboard">EduTech Admin Portal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProfileAdmin?pageTransit=goToUnifyAdmin">Unify Admin Portal</a>
                    </li>
                    <%    }else if(userType.equals("dualadmin")){
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="EduTechAdmin?pageTransit=EduTechAdminDashboard">EduTech Admin Portal</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ProfileAdmin?pageTransit=goToUnifyAdmin">Unify Admin Portal</a>
                    </li>
                    <%    }else if(userType.equals("edutechadmin")){
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="EduTechAdmin?pageTransit=EduTechAdminDashboard">EduTech Admin Portal</a>
                    </li>
                    <%    }else if(userType.equals("unifyadmin")){
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="ProfileAdmin?pageTransit=goToUnifyAdmin">Unify Admin Portal</a>
                    </li>
                    <%    }
                    %>
                </ul>
            </div>
            <button type="button" class="btn btn-outline-primary" onclick="location.href='CommonInfra?pageTransit=goToLogout'">Log Out</button>
        </nav>
        <div class="splitcontainer">
            <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS&userID=<%= loggedInUsername %>">
                <div class="split left">
                    <h1>Unify</h1>
                    <p>Your campus community.</p>
                </div>
            </a>
            <a href="http://<%=java.net.InetAddress.getLocalHost().getHostAddress()%>:3000">
                <div class="split right">
                    <h1>EduTech</h1>
                    <p>Boost your productivity.</p>
                </div>
            </a>
        </div>
        
            
        <!--Javascript-->
        <script src="js/commoninfrastructure/login/jquery.min.js"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js"></script>
        <script src="js/commoninfrastructure/LandingPageJS.js"></script>
    </body>
</html>

