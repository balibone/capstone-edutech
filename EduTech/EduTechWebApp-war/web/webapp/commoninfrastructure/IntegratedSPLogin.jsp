<%  
    String loggedInUsername=null; 
    String userType = null; 
 
    /* 
    HttpServletRequest object is already available to JSP page by default as variable "request" 
    See https://www.tutorialspoint.com/jsp/jsp_implicit_objects.htm 
    */ 
    Cookie[] reqCookies = request.getCookies(); 
    if(reqCookies != null){ 
        for(Cookie c : reqCookies){ 
            //if username cookie is valid, extract cookie value. 
            if("username".equals(c.getName()) && !c.getValue().equals("")){ 
                loggedInUsername = c.getValue(); 
            } 
            //if userType cookie is valid, extract cookie value. 
            else if("userType".equals(c.getName()) && !c.getValue().equals("")){ 
                userType = c.getValue(); 
            } 
        } 
    }  
    if (loggedInUsername != null) { 
        response.sendRedirect("CommonInfra?pageTransit=goToCommonLanding"); 
    } 
 
%> 
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
        
        <!--Font Awesome 5-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
    </head>
    
    <body class="text-center">
        <form action="CommonInfra" method="POST" class="form-signin" style="background-color: #f2f2f2; border-radius: 24px;">
            <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
            <h1 class="h4 mb-3 font-weight-normal"><i>Your campus, online.</i></h1>
            <label for="username">Username</label>
            <input type="text" class="form-control mb-1" name="username" autofocus required/>
            <label for="password">Password</label>
            <div class="input-group mb-3">
                <input id="password" type="password" class="form-control" name="userPassword"/>
                <div class="input-group-append" style="height: 46px;">
                    <button class="btn btn-outline-secondary" type="button" onmousedown="showPassword()" onmouseup="showPassword()"><i class="fas fa-eye"></i></button>
                </div>
            </div>
            <%  if(request.getAttribute("sysMessage") != null) {   %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("sysMessage")%></div>
            <%  }   %>
            <%  if(request.getAttribute("successMsg") != null) {   %>
            <div class="alert alert-success" role="alert"><%= request.getAttribute("successMsg")%></div>
            <%  }   %> 
            <input type="hidden" name="pageTransit" value="loginToSys"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <br>
            <p>
                Don't have an account? <a href="CommonInfra?pageTransit=Registration">Sign up here.</a>
                <br>
                Forgot your password? <a href="CommonInfra?pageTransit=ForgotPassword">Get a recovery email.</a>
                <br>
                Reset your password? <a href="CommonInfra?pageTransit=PasswordReset">Click here.</a>
            </p>
            <p class="mt-5 mb-3 text-muted">&copy; EduBox 2018</p>
        </form>
        <!-- JavaScript (JS) -->
        <script src="js/commoninfrastructure/login/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js" type="text/javascript"></script>
        <!--Vide plugin JS-->
        <script src="js/commoninfrastructure/login/jquery.vide.min.js" type="text/javascript"></script>
        <!--Custom JS-->
        <script src="js/commoninfrastructure/login/coming-soon.min.js" type="text/javascript"></script>
        <!--Password JS-->
        <script src="js/commoninfrastructure/login/showPassword.js"></script>
    </body>
</html>
