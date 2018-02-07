<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Common Admin - Students</title>

        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/commoninfrastructure/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/admin/baselayout/CommonAdminBaseCSS.css" rel="stylesheet" type="text/css">

        <!--Font Awesome 5-->
        <script defer src="fonts/fa5/fontawesome-all.js"></script>
        <script defer src="fonts/fa5/fa-v4-shims.js"></script>
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <%@include file="SideMenu.jspf"%>
                <%@include file="TopMenu.jspf"%>               

                <div class="right_col" role="main">
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_title">
                                <h2 class="bodyHeader">Students</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/commoninfrastructure/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/admin/basejs/CommonAdminBaseJS.js" type="text/javascript"></script>
    </body>
</html>