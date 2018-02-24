<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
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
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="container-fluid">
        <a class="navbar-brand" href="CommonInfra?pageTransit=goToCommonLanding">
            <!--username variable is in SessionCheck.jspf-->
            Welcome To EduBox, <%=loggedInUsername%>!        
        </a>
        <!-- Top Navigation -->
        <div class="collapse navbar-collapse">
            <div id="pageAnnouncement">
                <div class="ccr-last-update">
                    <div class="update-ribon"><strong>Notification:</strong></div>
                    <span class="update-ribon-right"></span>
                    <div class="update-news-text">
                        <ul id="latestUpdate" class="newsticker">
                            <li><strong>System maintenance will be carried out at 15 Jan 2018, 00:00:00 (SGT).</strong></li>
                            <li><strong>Welcome to Integrated Student System!</strong></li>
                            <li><strong>Stay tune to our latest update via "Latest System Update".</strong></li>
                        </ul>
                    </div>
                    <div class="update-right-border">
                        <i class="fa fa-clock-o"></i>&nbsp;&nbsp;<strong><span id="clock"></span></strong>
                    </div>
                </div>
            </div>
            <ul class="nav navbar-top-links navbar-right">
                <li><a href="CommonInfra?pageTransit=goToProfile"><i class="fa fa-user"></i>&nbsp;&nbsp;My Profile</a></li>
                <li class="divider"></li>
                <li><a href="CommonInfra?pageTransit=goToLogout"><i class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout</a></li>
            </ul>
        </div>           
    </div>
</nav>
        <div class="login-wrapper login-main has-reg user_type" style="top: 108.5px;">
            <header style="background-color: #ffffff" class="login-header clearfix">
                <div id="page-brand">
                    <div> <img src = "images/edubox-logo.png" style="max-width: 260px; max-height: 130px"></div>
                </div>
            </header>
            <div class="page-title-wrapper">
                <h1 class="page-title">Welcome to EduBox!</h1>
            </div>
            <div id="wherechoices"> 
                <a class="employers" href="EduTechAdmin?pageTransit=EduTechAdminDashboard">
                    <span class="user-container"><span class="mainpage-button-text">EduTech Admin</span></span></a>
                
                <a class="faculty" href="CommonInfra?pageTransit=goToEdutechPortal">
                    <span class="user-container"><span class="mainpage-button-text">EduTech Portal</span></span></a>
                <br>
                <a class="students" href="CommonInfra?pageTransit=goToUnifyAdmin">
                    <span class="user-container"><span class="mainpage-button-text">Unify Admin</span></span></a>
                <a class="students" href="ProfileSysUser?pageTransit=goToUnifyUserAccount&userID=<%= loggedInUsername %>">
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
