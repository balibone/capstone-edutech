<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Marketplace - Item Likes List</title>

        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/datatable/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/datatable/dataTables.responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/datatable/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/weblayout/marketplace/ItemLikeListSYSCSS.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <table id="itemLikeTable" class="table table-striped table-bordered dt-responsive nowrap" data-height="300" cellspacing="0" width="100%">
            <thead style="display:none;">
                <tr><th>Item Likers</th></tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Vector> itemLikeListSYS = (ArrayList) request.getAttribute("itemLikeListSYS");
                    if (!itemLikeListSYS.isEmpty()) {
                        for (int i = 0; i <= itemLikeListSYS.size()-1; i++) {
                            Vector v = itemLikeListSYS.get(i);
                            String username = String.valueOf(v.get(0));
                            String usernameIMG = String.valueOf(v.get(1));
                            String userFirstName = String.valueOf(v.get(2));
                            String userLastName = String.valueOf(v.get(3));
                            String userItemPositiveRatingCount = String.valueOf(v.get(4));
                            String userItemNeutralRatingCount = String.valueOf(v.get(5));
                            String userItemNegativeRatingCount = String.valueOf(v.get(6));
                %>
                <tr>
                    <td>
                        <div class="media mb-2 mt-3">
                            <div class="mr-2">
                                <a href="ProfileSysUser?pageTransit=goToUserProfileSYS&itemSellerID=<%= username%>" target="_parent">
                                    <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= usernameIMG%>" style="width:40px;height:40px;" />
                                </a>
                            </div>
                            <div class="media-body" style="cursor: pointer;" onclick="window.open('ProfileSysUser?pageTransit=goToUserProfileSYS&itemSellerID=<%= username%>', '_parent');">
                                <h5 style="font-size:15px;"><strong><%= userFirstName%>&nbsp;<%= userLastName%></strong></h5>
                                <div class="rating">
                                    <ul class="profileRating">
                                        <li>@<%= username%></li>
                                        <li>[&nbsp;&nbsp;<img class="ratingImage" src="images/profilerating/positive.png" /><span class="ratingValue"><%= userItemPositiveRatingCount%></span></li>
                                        <li><img class="ratingImage" src="images/profilerating/neutral.png" /><span class="ratingValue"><%= userItemNeutralRatingCount%></span></li>
                                        <li><img class="ratingImage" src="images/profilerating/negative.png" /><span class="ratingValue"><%= userItemNegativeRatingCount%></span>&nbsp;&nbsp;]</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
                <%      }   %>
                <%  }   %>
            </tbody>
        </table>

        <script src="js/unify/systemuser/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/dataTables.responsive.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/jquery.dataTables-v1.10.16.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/ItemLikeListSYSJS.js" type="text/javascript"></script>
    </body>
</html>