
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <title>Welcome to EduBox</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/bootstrapv4.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/IntegratedSPLoginCSS.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/dashboard/baselayout/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/coming-soon.min.css">
    </head>
    
    <body class="text-center">
        <form action="CommonInfra" class="form-signin" style="background-color: #f2f2f2; border-radius: 24px;">
            <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Welcome, please sign in.</h1>
            <label for="username">Username</label>
            <input type="text" class="form-control" name="username" autofocus required/>
            <label for="password">Password</label>
            <input type="password" class="form-control" name="userPassword" />
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <%  if(request.getAttribute("sysMessage") != null) {   %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("sysMessage")%></div>
            <%  }   %> 
            <input type="hidden" name="pageTransit" value="loginToSys"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <p>Don't have an account? <a href="CommonInfra?pageTransit=register">Sign up here.</a></p>
            <p class="mt-5 mb-3 text-muted">&copy; EduBox 2018</p>
        </form>
        <!-- JavaScript (JS) -->
        <script src="js/commoninfrastructure/login/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js" type="text/javascript"></script>
        <!--Vide plugin JS-->
        <script src="js/commoninfrastructure/login/jquery.vide.min.js" type="text/javascript"></script>
        <!--Custom JS-->
        <script src="js/commoninfrastructure/login/coming-soon.min.js" type="text/javascript"></script>
    </body>
</html>
