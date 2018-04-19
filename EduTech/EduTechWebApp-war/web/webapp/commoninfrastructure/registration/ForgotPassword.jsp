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
        
        <title>Forgot Password</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/bootstrapv4.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/ForgotCSS.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/dashboard/baselayout/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="css/commoninfrastructure/login/coming-soon.min.css">
        <style>
            .capbox {
                background-color: #92D433;
                border: #B3E272 0px solid;
                border-width: 0px 12px 0px 0px;
                display: inline-block;
                *display: inline; zoom: 1; /* FOR IE7-8 */
                padding: 8px 40px 8px 8px;
            }
            
            .capbox-inner {
                font: bold 11px arial, sans-serif;
                color: #000000;
                background-color: #DBF3BA;
                margin: 5px auto 0px auto;
                padding: 3px;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
            }
            
            #CaptchaDiv {
                font: bold 17px verdana, arial, sans-serif;
                font-style: italic;
                color: #000000;
                background-color: #FFFFFF;
                padding: 4px;
                -moz-border-radius: 4px;
                -webkit-border-radius: 4px;
                border-radius: 4px;
            }
            
            #CaptchaInput { margin: 1px 0px 1px 0px; width: 135px; }

        </style>
    </head>
    
    <body class="text-center">
        <form id="resetPassword" method="POST" action="CommonInfra" onsubmit="return checkform(this);" class="form-signin" style="background-color: #f2f2f2; border-radius: 24px;">
            <img class="mb-4" src="images/edubox-logo.png" width="auto" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Forgot your password?</h1>
            <p>Enter your username below and we'll send you an email to reset your password.</p>
                
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" placeholder="Enter your username" required name="username">
            </div>
             <!-- START CAPTCHA -->
            <div class="form-group">
                <div class="capbox">
    
                    <div id="CaptchaDiv"></div>
    
                    <div class="capbox-inner">
                        Type the above number:<br>
    
                        <input type="hidden" id="txtCaptcha">
                        <input type="text" name="CaptchaInput" id="CaptchaInput" size="15"><br>
    
                    </div>
                </div>
            </div>
             <!-- END CAPTCHA -->
            <input type="hidden" name="pageTransit" value="sendResetEmail"/>
            <%  if(request.getAttribute("failMsg") != null) {   %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("failMsg")%></div>
            <%  }   %>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            <a href="CommonInfra?pageTransit=goToLogout" class="btn btn-lg btn-info btn-block">Back To Login</a>
            <p class="mt-5 mb-3 text-muted">&copy; EduBox 2018</p>
        </form>
        <div id="wait" style="display:none;border:1px solid black; border-radius: 15px; position:absolute; padding:10px; background-color: #f5f5f5; text-align: center;">
                 <img src='images/ajax-loader.gif'/><br><b><h3>Sending email..<br>Please do not refresh page!</h3></b>
        </div>
        <!-- JavaScript (JS) -->
        <script src="js/commoninfrastructure/login/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/login/bootstrapv4.min.js" type="text/javascript"></script>
        <!--Vide plugin JS-->
        <script src="js/commoninfrastructure/login/jquery.vide.min.js" type="text/javascript"></script>
        <!--Custom JS-->
        <script src="js/commoninfrastructure/login/coming-soon.min.js" type="text/javascript"></script>
        <script>
            
            // Captcha Script
            function checkform(theform){
                var why = "";
                
                if(theform.CaptchaInput.value == ""){
                    why += "- Please Enter CAPTCHA Code.\n";
                }
                if(theform.CaptchaInput.value != ""){
                    if(ValidCaptcha(theform.CaptchaInput.value) == false){
                        why += "- The CAPTCHA Code Does Not Match.\n";
                    }
                }
                if(why != ""){
                    alert(why);
                    return false;
                }
            }
            
            var a = Math.ceil(Math.random() * 9)+ '';
            var b = Math.ceil(Math.random() * 9)+ '';
            var c = Math.ceil(Math.random() * 9)+ '';
            var d = Math.ceil(Math.random() * 9)+ '';
            var e = Math.ceil(Math.random() * 9)+ '';
            
            var code = a + b + c + d + e;
            document.getElementById("txtCaptcha").value = code;
            document.getElementById("CaptchaDiv").innerHTML = code;
            
            // Validate input against the generated number
            function ValidCaptcha(){
                var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
                var str2 = removeSpaces(document.getElementById('CaptchaInput').value);
                if (str1 == str2){
                    $("#wait").css("display", "block");
                    return true;
                }else{
                    return false;
                }
            }
            
            // Remove the spaces from the entered and generated code
            function removeSpaces(string){
                return string.split(' ').join('');
            }
        </script>
    </body>
</html>
