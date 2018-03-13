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
        
        <title>Sign Up</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/bootstrapv4.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/ForgotCSS.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/dashboard/baselayout/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/coming-soon.min.css">
    </head>
    
    <body class="text-center">
        <form id="tokenEntry" method="POST" action="CommonInfra" class="form-signin" style="background-color: #f2f2f2; border-radius: 24px;">
            <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Reset your password.</h1>
            <p>Enter your reset token below.</p>
            
            <div class="form-group">
                <label for="username">Reset Token</label>
                <%
                    if(request.getParameter("token")!=null){
                %>
                <input id="token" value="<%=request.getParameter("token")%>" type="text" class="form-control" id="token" placeholder="Enter the token sent to your email" required name="token">
                <%
                    }else{
                %>
                <input id="token" type="text" class="form-control" id="token" placeholder="Enter the token sent to your email" required name="token">
                <%
}
                %>
                
            </div>
            
            <input type="hidden" name="pageTransit" value="validateToken"/>
            <input type="hidden" id="username" name="username" value="<%=request.getParameter("username")%>"/>
            <%  if(request.getAttribute("failMsg") != null) {   %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("failMsg")%></div>
            <%  }   %>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            <a href="CommonInfra?pageTransit=goToLogout" class="btn btn-lg btn-info btn-block">Back To Login</a>
            <p class="mt-5 mb-3 text-muted">&copy; EduBox 2018</p>
        </form>
            
            <!-- New Password Modal -->
            <div class="modal fade mx-auto" id="newPassword" tabindex="-1" role="dialog" aria-labelledby="newPassword" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="newPassword">Set your new password.</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="resetPassword" method="POST" action="CommonInfra">
                                <div class="form-group row">
                                    <label for="password1" class="col-sm-3 col-form-label">New password</label>
                                    <div class="col-sm-9">
                                        <input required type="password" class="form-control" id="password1" placeholder="New Password" name="password">
                                        <small id="passwordHelpBlock" class="form-text text-muted">
                                            Your password must be at least 8 characters long, alphanumeric, and must not contain spaces, special characters, or emojis.
                                        </small>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="password2" class="col-sm-3 col-form-label">Re-enter</label>
                                    <div class="col-sm-9">
                                        <input required type="password" class="form-control" id="password2" placeholder="Re-enter New Password">
                                    </div>
                                </div>
                                <input type="hidden" name="pageTransit" value="resetPassword"/>
                                <input type="hidden" name="username" value="<%=request.getParameter("username")%>"/>
                                <div class="d-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary">Submit</button>&nbsp;
                                    <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
                                </div>
                                
                            </form>
                        </div>
<!--                        
                        <div class="modal-footer">
                            
                        </div>-->
                    </div>
                </div>
            </div>
            
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
