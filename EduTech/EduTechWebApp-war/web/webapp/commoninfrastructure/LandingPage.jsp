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
        <nav class="navbar navbar-light bg-light justify-content-between">
            <a class="navbar-brand" href="#">
                <img src="images/edubox-logo.png" width="auto" height="30" class="d-inline-block align-top" alt="">
            </a>
            <button type="button" class="btn btn-outline-primary" onclick="location.href='CommonInfra?pageTransit=goToLogout'">Log Out</button>
        </nav>
        <div class="splitcontainer">
            <div class="split left">
                <h1>Unify</h1>
                <p>Your campus community.</p>
                <a href="ProfileSysUser?pageTransit=goToUnifyUserAccount&userID=<%= loggedInUsername %>" class="enterButton">Enter</a>
            </div>
            <div class="split right">
                <h1>EduTech</h1>
                <p>Boost your productivity.</p>
                <a href="http://localhost:3000/" class="enterButton">Enter</a>
            </div>
        </div>
        
            
        <!--Javascript-->
        <script src="js/commoninfrastructure/login/jquery.min.js"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js"></script>
        <script src="js/commoninfrastructure/LandingPageJS.js"></script>
    </body>
</html>

