<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin</title>

        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">

    </head>
    <body style="background-color: white;">


        <%
            Vector tagDetailsVec = (Vector) request.getAttribute("tagDetailsVec2");
            String tagID, tagName, tagType;
            tagID = tagName = tagType = "";
            if (tagDetailsVec != null) {
                tagID = (String.valueOf(tagDetailsVec.get(0)));
                tagName = (String.valueOf(tagDetailsVec.get(1)));
                tagType = (String.valueOf(tagDetailsVec.get(2)));
            }
        %>



        <%-- start of form --%>
    <body style="background-color: transparent;">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div id="formContent">
                <form action="ContentAdmin?pageTransit=goToTagListing" method="GET" enctype="multipart/form-data" target="_parent">

                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

                        <%-- form field 1 --%>
                        <div class="col-md-20 col-sm-20 col-xs-20 form-group disabled" style="margin-top: 7px;">
                            <label class="control-label col-md-12 col-sm-12 col-xs-12">Tag ID:&nbsp;&nbsp;<u><%= tagID%></u></label>
                        </div>
                    
                        <%-- field 2 --%> 
                        <div class="col-md-3 col-sm-3 col-xs-12 form-group" style="margin-top: 7px;">
                            <label>Tag Name:&nbsp;&nbsp;</label>
                            <input type="text" font color = "black" value="<%= tagName%>" name="tagName" />
                        </div>
             
                        <%-- dropdown --%>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            
                                <%-- field 1 --%>
                                <label for="tagTypeTitle">Type of Tag: </label>
                                
                                &nbsp;&nbsp;
                                
                                <%-- field 2 --%>
                                <select id="tagType" name="tagType" style="height: 25px;">
                                    <option selected disabled hidden><%= tagType%></option>
                                    <option value="Errand">Errand</option>
                                    <option value="Marketplace">Marketplace</option>
                                    <option value="Voice">Voice</option>
                                </select>
                            
                        </div>

                    </div>
            </div>

            <div class="ln_solid"></div>
            <div class="form-group">
                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                    <table border="0" style="margin: auto;">
                        <tr>
                            <td>
                                <input type="hidden" name="pageTransit" value="updateTag"/>
                                <input type="hidden" name="tagID" value="<%= tagID%>"/>
                                <input type="hidden" name="tagName" value="<%= tagName%>"/>
                                <input type="hidden" name="tagType" value="<%= tagType%>"/>
                                <button type="submit" class="btn btn-primary">Update Tag</button></form>
                            </td>

                        </tr>
                    </table>
                </div>
            </div>


            <!-- JAVASCRIPT (JS) -->
            <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
            <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
            <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>

    </body>
</html>
