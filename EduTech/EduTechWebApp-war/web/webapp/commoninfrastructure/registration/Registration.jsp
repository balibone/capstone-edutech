<%  
//    String loggedInUsername=null; 
//    String userType = null; 
// 
//    /* 
//    HttpServletRequest object is already available to JSP page by default as variable "request" 
//    See https://www.tutorialspoint.com/jsp/jsp_implicit_objects.htm 
//    */ 
//    Cookie[] reqCookies = request.getCookies(); 
//    if(reqCookies != null){ 
//        for(Cookie c : reqCookies){ 
//            //if username cookie is valid, extract cookie value. 
//            if("username".equals(c.getName()) && !c.getValue().equals("")){ 
//                loggedInUsername = c.getValue(); 
//            } 
//            //if userType cookie is valid, extract cookie value. 
//            else if("userType".equals(c.getName()) && !c.getValue().equals("")){ 
//                userType = c.getValue(); 
//            } 
//        } 
//    }  
//    if (loggedInUsername != null) { 
//        response.sendRedirect("CommonInfra?pageTransit=goToCommonLanding"); 
//    } 
 
%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        
        <title>Registration</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/bootstrapv4.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/dashboard/baselayout/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/coming-soon.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/RegistrationCSS.css">
    </head>
    
    <body class="text-center">
        <form id="newUser" method="POST" action="CommonInfra" class="form-signin" style="background-color: #f2f2f2; border-radius: 24px;">
            <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Register a new account.</h1>
            <div class="form-row">
                <div class="form-group col-sm-2">
                    <label for="salutation">Salutation</label>
                    <select class="form-control" required name="salutation">
                        <option value="Mr.">Mr.</option>
                        <option value="Ms.">Ms.</option>
                        <option value="Mrs.">Mrs.</option>
                        <option value="Mdm.">Mdm.</option>
                        <option value="Dr.">Dr.</option>
                    </select>
                </div>
                <div class="form-group col-sm-5">
                    <label for="firstName">First Name</label>
                    <input type="text" class="form-control" id="firstName" placeholder="First Name" required name="firstName">
                </div>
                <div class="form-group col-sm-5">
                    <label for="lastName">Last Name</label>
                    <input type="text" class="form-control" id="lastName" placeholder="Last Name" required name="lastName">
                </div>
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Username" required name="username">
                <small id="usernameHelpBlock" class="form-text text-muted">
                    Your username must be at least 5 characters long.
                </small>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div class="input-group mb-1">
                    <input id="password1" type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" class="form-control" placeholder="Enter your password" required name="password">
                    <div class="input-group-append" style="height: 38px;">
                        <button class="btn btn-outline-secondary" type="button" onmousedown="showPassword1()" onmouseup="showPassword1()"><i class="fas fa-eye"></i></button>
                    </div>
                </div>
                <div id="passwordHint" style="font-size: medium; ">
                    <strong>Password must contain the following:</strong>
                    <br>
                    <div id="letter" class="invalid">A <b>lowercase</b> letter</div>
                    <div id="capital" class="invalid">An <b>uppercase</b> letter</div>
                    <div id="number" class="invalid">A <b>number</b></div>
                    <div id="length" class="invalid">Minimum <b>8 characters</b></div>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-sm-6">
                    <label for="email">E-mail</label>
                    <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" class="form-control" placeholder="E-mail" required name="email">
                </div>
                <div class="form-group col-sm-6">
                    <label for="contactNum">Contact Number</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text">+65</div>
                        </div>
                        <input type="tel" pattern="[0-9]{8}" maxlength="8" class="form-control" id="contactNum" placeholder="Contact Number" required name="contactNum">
                    </div>
                </div>
            </div>
            <input type="hidden" name="pageTransit" value="registerUser"/>
            <%  if(request.getAttribute("sysMessage") != null) {   %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("sysMessage")%></div>
            <%  }   %>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
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
        <script src="js/commoninfrastructure/login/RegistrationJS.js"></script>
        <!--Font Awesome 5-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
    </body>
</html>
