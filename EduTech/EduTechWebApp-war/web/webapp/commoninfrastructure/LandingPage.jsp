<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Landing Page</title>
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/dashboard/baselayout/LandingPageCSS.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/dashboard/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/dashboard/baselayout/basetemplate.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/dashboard/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/dashboard/baselayout/CommonCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body onload="establishTime(); setInterval('updateTime()', 1000)" class="wk wk3" id="page" style="background-image: url('images/homepage.jpg');">
        <%@include file="SystemTopNav.jspf" %>
        <div class="login-wrapper login-main has-reg user_type" style="top: 108.5px;">
            <header style="background-color: #ffffff" class="login-header clearfix">
                <div id="page-brand">
                    <a href="www.nus.edu.sg/cfg" target="_blank" class="brand-link">
                        <span class="sr-only">Visit Site</span>
                        <div> <img src = "images/EduBox Logo.png" style="max-width: 260px; max-height: 130px"></div>
                    </a>
                    <div class="login-actions">
                        <div class="actions-menu-wrapper actions-menu-right">
                            <%--<div class="actions-toggle-wrapper">
                            <button type="button" class="actions-toggle" aria-haspopup="true" aria-expanded="false" aria-label="Open Actions Menu"></button>	                
                            <div class="actions-menu" role="menu" aria-label="Actions Menu">
                            <div class="main-action" role="menuitem">
                                <a href="?simplify_interface=1" title="You are in Default Mode. Some browsers may have difficulty accessing enhanced content. Click this link to switch to Accessibility Mode." class="accessibility_mode acc_mode-off forcefg" onclick="return confirm('Accessibility Mode is only recommended for users of assistive technologies. Functionality and content is equivalent in Accessibility Mode, but some interface elements will be formatted differently for optimal compatibility. Are you sure you want to switch to Accessibility Mode?');">Accessibility Mode: Off<span style="position:absolute;left:-9000px;"> use this link to improve screen reader compatibility.</span></a></div>														</div>		
                            </div> --%>
                        </div></div>
                </div>
            </header>
            <div class="page-title-wrapper">
                <h1 class="page-title">Welcome to EduBox!</h1>
            </div>
            <a name="content"></a>
            <div id="wherechoices"> 
                <a class="students" href="CommonInfra?pageTransit=goToSystemAdmin">
                    <span class="user-container"><span class="mainpage-button-text">System Admin</span></span></a>
                <a class="employers" href="CommonInfra?pageTransit=goToEdutechAdmin">
                    <span class="user-container"><span class="mainpage-button-text">EduTech Admin</span></span></a>
                <a class="faculty" href="CommonInfra?pageTransit=goToEdutechPortal">
                    <span class="user-container"><span class="mainpage-button-text">EduTech Portal</span></span></a>
                <br>
                <a class="students" href="CommonInfra?pageTransit=goToUnifyAdmin">
                    <span class="user-container"><span class="mainpage-button-text">Unify Admin</span></span></a>
                <a class="students" href="ProfileSysUser?pageTransit=goToUserProfile&emailID=<%= request.getAttribute("emailID") %>">
                    <span class="user-container"><span class="mainpage-button-text">Unify Portal</span></span></a>
            </div> 
        </div>
        <!-- JavaScript (JS) -->
        <script src="js/commoninfrastructure/dashboard/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/dashboard/basejs/bootstrap.min.js" type="text/javascript"></script>      
        <script src="js/commoninfrastructure/dashboard/basejs/metisMenu.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/dashboard/basejs/jquery.newsTicker.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/dashboard/basejs/CommonJS.js" type="text/javascript"></script>
    </body>
</html>
