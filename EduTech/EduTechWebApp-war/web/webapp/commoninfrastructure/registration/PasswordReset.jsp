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
        
        <title>Password Reset</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/bootstrapv4.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/ForgotCSS.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/dashboard/baselayout/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/coming-soon.min.css">
    </head>
    
    <body class="text-center">
        <form id="resetPassword" method="POST" action="CommonInfra" class="form-signin" style="background-color: #f2f2f2; border-radius: 24px;">
            <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Reset your password.</h1>
            
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" type="text" class="form-control" placeholder="Enter your username" required name="username">
            </div>
            <div class="form-group">
                <label for="username">Current Password</label>
                <input id="oldpass" type="text" class="form-control" placeholder="Enter your old password" required>
            </div>
            <div class="form-group">
                <label for="username">New Password</label>
                <input id="password1" type="text" class="form-control" placeholder="Enter your new password" required name="password">
                <small id="passwordHelpBlock" class="form-text text-muted">
                    Your password must be at least 8 characters long, alphanumeric, and must not contain spaces, special characters, or emojis.<br> <strong>It must be different from your current password.</strong>
                </small>
            </div>
            <div class="form-group">
                <label for="username">Confirm New Password</label>
                <input id="password2" type="text" class="form-control" placeholder="Re-enter your new password" required>
            </div>
            
            <input type="hidden" name="pageTransit" value="resetPassword"/>
            <input type="hidden" id="username" name="username" value="<%=request.getParameter("username")%>"/>
            <%  if(request.getAttribute("failMsg") != null) {   %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("failMsg")%></div>
            <%  }   %>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            <a href="CommonInfra?pageTransit=goToLogout" class="btn btn-lg btn-info btn-block">Back To Login</a>
            <p class="mt-5 mb-3 text-muted">&copy; EduBox 2018</p>
        </form>
        <!-- JavaScript (JS) -->
        <script src="js/commoninfrastructure/login/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js" type="text/javascript"></script>
        <!--Vide plugin JS-->
        <script src="js/commoninfrastructure/login/jquery.vide.min.js" type="text/javascript"></script>
        <!--Custom JS-->
        <script src="js/commoninfrastructure/login/coming-soon.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/login/PasswordResetJS.js" type="text/javascript"></script>
        
    </body>
</html>
