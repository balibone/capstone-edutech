<!-- ***************************************************************************************
*   Title:                  EditTag.jsp
*   Purpose:                EDIT THE DETAILS OF THE TAG (UNIFY ADMIN)
*   Created By:             NIGEL LEE TJON YI
*   Modified By:            TAN CHIN WEE WINSTON
*   Date:                   24 FEBRUARY 2018
*   Code version:           1.1
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
**************************************************************************************** -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - EDIT Tag</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/open-sans-font.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/weblayout/contentadmin/EditTagCSS.css" rel="stylesheet" type="text/css" />

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tagType').val($('#oldTagType').val());
            });
        </script>
    </head>
    
    <body style="background-color: #FFFFFF;">
        <%
            Vector tagDetailsVec = (Vector) request.getAttribute("tagDetailsVec");
            String tagID, tagName, tagType;
            tagID = tagName = tagType = "";

            if (tagDetailsVec != null) {
                tagID = (String.valueOf(tagDetailsVec.get(0)));
                tagName = (String) tagDetailsVec.get(1);
                tagType = (String) tagDetailsVec.get(2);
            }
        %>
        <form action="ContentAdmin" method="POST" target="_parent">
            <table border="0">
                <tr>
                    <td>
                        <div class="form-group">
                            <label class="control-label">Tag Name:</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>
                                <input type="hidden" name="oldTagName" value="<%= tagName%>" />
                                <input type="text" class="form-control" placeholder="<%= tagName%>" name="tagName" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Tag Type:</label>
                            <div class="input-group">
                                <input type="hidden" id="oldTagType" name="oldTagType" value="<%= tagType%>" />
                                <select name="tagType" id="tagType" class="form-control">
                                    <option value="Errands">Errands</option>
                                    <option value="Marketplace">Marketplace</option>
                                    <option value="Voices">Voices</option>
                                </select>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <div class="form-group">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <input type="hidden" name="pageTransit" value="updateTag" />
                                <input type="hidden" name="hiddenTagID" value="<%= tagID%>" />
                                <button type="submit" class="btn btn-primary">Edit Tag</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>