<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - New Company Category</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/open-sans-font.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/weblayout/voices/NewCompanyCategoryCSS.css" rel="stylesheet" type="text/css" />

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script src="js/unify/admin/basejs/validator-v1.1.0.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/voices/NewCompanyRequestSYSJS.js" type="text/javascript"></script>  
    </head>
    <body style="background-color: #FFFFFF;">
        <form action="VoicesSysUser" method="POST" enctype="multipart/form-data" target="_parent">
            <table border="0">
                <tr>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-book"></i></span>
                                <textarea rows="5" cols="30" class="form-control" placeholder="Report Reason" name="reportDescription"></textarea>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <div class="form-group">
                            <div class="col-md-12 col-sm-12 col-xs-12" style="text-align: center;">
                                <input type="hidden" name="pageTransit" value="createReviewReportSYS"/>
                                <input type="hidden" name="username" value="<%= loggedInUsername%>" />
                                <input type="hidden" name="hiddenCompanyID" value="<%= request.getAttribute("hiddenCompanyID")%>" />
                                <input type="hidden" name="hiddenReviewPoster" value="<%= request.getAttribute("hiddenReviewPoster")%>" />
                                <input type="hidden" name="hiddenReviewID" value="<%= request.getAttribute("hiddenReviewID")%>" />
                                <button type="submit" class="btn btn-primary">Send Report</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>