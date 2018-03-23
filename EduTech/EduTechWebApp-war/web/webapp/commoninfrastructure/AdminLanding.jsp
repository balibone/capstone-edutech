<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to EduBox</title>
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/login/bootstrapv4.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/LandingPageCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="splitcontainer">
            <div class="collapse" id="navbarToggleExternalContent">
                <div class="bg-dark p-4">
                    <h4 class="text-white">Collapsed content</h4>
                    <span class="text-muted">Toggleable via the navbar brand.</span>
                </div>
            </div>
            <nav class="navbar navbar-dark">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"> Admin Portals</span>
                </button>
            </nav>
            <div class="split left">
                <h1>Unify</h1>
                <p>Your campus community.</p>
                <a href="ProfileSysUser?pageTransit=goToUnifyUserAccount&userID=<%= loggedInUsername %>" class="button">Enter</a>
            </div>
            <div class="split right">
                <h1>EduTech</h1>
                <p>Boost your productivity.</p>
                <a href="http://localhost:3000/" class="button">Enter</a>
            </div>
        </div>
        
        
        <!--Javascript-->
        <script src="js/commoninfrastructure/login/jquery.min.js"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js"></script>
        <script src="js/commoninfrastructure/LandingPageJS.js"></script>
    </body>
</html>

