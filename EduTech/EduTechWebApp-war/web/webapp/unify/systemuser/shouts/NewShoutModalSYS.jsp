<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify Marketplace - New Shout</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/marketplace/NewItemListingSYSCSS.css" rel="stylesheet" type="text/css">
    </head>

    <body class="nav-md">

        <br>
        <div class="content-overlay"></div>

        <div class="container" style="margin-bottom: 30px;">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="x_panel">
                        <%                                String successMessage = (String) request.getAttribute("successMessage");
                            if (successMessage != null) {
                        %>
                        <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                            <button type="button" class="close" id="closeSuccess">&times;</button>
                            <%= successMessage%>
                        </div>
                        <%  } %>
                        <%
                            String errorMessage = (String) request.getAttribute("errorMessage");
                            if (errorMessage != null) {
                        %>
                        <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                            <button type="button" class="close" id="closeError">&times;</button>
                            <%= errorMessage%>
                        </div>
                        <%  }%>



                        <div class="x_title">
                            <h5>What do you want to shout about?</h5>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p>Share what is on your mind! <br>Just remember to keep it civil and friendly :)</p>
                            <p>(<span class="asterik">*</span>) Your shout is kept anonymous</p>

                            <form class="form-horizontal form-label-left" action="ShoutsSysUser" method="POST" enctype="form-data">

                                <div class="form-row" style="justify-content: left;">

                                    <div class="col-md-6 ml-6">

                                        <div class="form-group">
                                            <label for="shoutContent">Write what you want to shout below</label>
                                            <textarea class="form-control" name="shoutContent" rows="5" placeholder="Shout here!" required="required"></textarea>
                                        </div>

                                    </div>

                                </div>
                                <div class="form-row" >

                                    <div class="col-md-2" style="justify-content: center">
                                        <input type="hidden" name="pageTransit" value="goToCreateShout" />
                                        <input type="hidden" name="loggedInUsername" value= "<%= loggedInUsername%>" />
                                        <button class="btn btn-outline btn-primary btn-sm btn-block" type="submit">Submit</button>
                                    </div>
                                </div>  

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- <div id="unifyFooter"></div> -->

        <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
            <i class="fa fa-angle-double-up"></i>
        </a>
    </div>

    <!-- #1. jQuery v2.2.4 -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
    <script src="js/unify/systemuser/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/validator-v1.1.0.js" type="text/javascript"></script>

    <script src="js/unify/systemuser/webjs/marketplace/jquery.smartWizard-v3.3.1.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/webjs/marketplace/custom.min.js"></script>
    <script src="js/unify/systemuser/webjs/marketplace/NewItemListingSYSJS.js" type="text/javascript"></script>
</body>
</html>