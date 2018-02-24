<!-- ***************************************************************************************
*   Title:                  NewTag.jsp
*   Purpose:                CREATION OF NEW TAG (UNIFY ADMIN)
*   Created By:             NIGEL LEE TJON YI
*   Modified By:            TAN CHIN WEE WINSTON
*   Date:                   24 FEBRUARY 2018
*   Code version:           1.1
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
**************************************************************************************** -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - New Tag</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/open-sans-font.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/weblayout/contentadmin/NewTagCSS.css" rel="stylesheet" type="text/css" />

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script src="js/unify/admin/basejs/validator-v1.1.0.js" type="text/javascript"></script>
    </head>
    
    <body style="background-color: #FFFFFF;">
        <form action="ContentAdmin" method="POST" target="_parent">
            <table border="0">
                <tr>
                    <td>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                <input type="text" class="form-control" placeholder="Tag Name (Required)" required="required" name="tagName" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <select name="tagType" class="form-control" required="required">
                                    <option value="" disabled selected>-- Select Tag Type --</option>
                                    <option value="Errands">Errands</option>
                                    <option value="Marketplace">Marketplace</option>
                                    <option value="Voices">Voices</option>
                                </select>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="form-group" style="text-align: center;">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <input type="hidden" name="pageTransit" value="createTag"/>
                                <button type="submit" class="btn btn-primary">Create Tag</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
