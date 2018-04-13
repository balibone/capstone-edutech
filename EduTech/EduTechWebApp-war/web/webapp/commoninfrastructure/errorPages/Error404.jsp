<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to EduBox</title>
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/login/bootstrapv4.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/ErrorPagesCSS.css" rel="stylesheet" type="text/css">
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
            <button type="button" class="btn btn-outline-primary" onclick="location.href='CommonInfra?pageTransit=goToLogout'">Log Out</button>
        </nav>
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="errorCol col-xs-12">
                    <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
                    <h1 class="h1 mb-3 font-weight-normal">404 Not Found</h1>
                    <p>The page you were looking for could not be found.</p>
                    <a href="CommonInfra?pageTransit=goToCommonLanding" class="btn btn-primary">Go Back Home</a>
                </div>
            </div>
        </div>
                
        <!--Javascript-->
        <script src="js/commoninfrastructure/login/jquery.min.js"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js"></script>
    </body>
</html>

