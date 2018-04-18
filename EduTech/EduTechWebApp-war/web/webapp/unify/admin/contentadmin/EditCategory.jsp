<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - Edit Category</title>

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

        </script>
    </head>

    <body style="background-color: #FFFFFF;">
        <%
            Vector catDetailsVec = (Vector) request.getAttribute("catDetailsVec");
            String catID, catName, catStatus, catStatusName;
            catID = catName = catStatus = catStatusName = "";

            if (catDetailsVec != null) {
                catID = (String.valueOf(catDetailsVec.get(0)));
                catName = (String) catDetailsVec.get(1);
                catStatus = (String.valueOf(catDetailsVec.get(2)));

                if (catStatus.equals("true")) {
                    catStatusName = "Active";
                } else {
                    catStatusName = "Inactive";
                }

            }
        %>
        <form action="ContentAdmin" method="POST" target="_parent">
            <table border="0">
                <tr>
                    <td>
                        <div class="form-group">
                            <label class="control-label">Category Name:</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-bookmark"></i></span>

                                <input type="text" class="form-control" value="<%= catName%>" name="catName" required="required" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Category Status:</label>
                            <div class="input-group">

                                <select name="catStatus" id="catStatus" class="form-control" >
                                    <option selected><%=catStatusName%></option>
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                </select>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <div class="form-group">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <input type="hidden" name="pageTransit" value="updateCategory" />
                                <input type="hidden" name="hiddenCatID" value="<%= catID%>" />
                                <button type="submit" class="btn btn-primary">Edit Category</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>