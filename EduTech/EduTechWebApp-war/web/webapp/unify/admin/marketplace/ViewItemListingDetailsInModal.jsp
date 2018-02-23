<!-- ***************************************************************************************
*   Title:                  ViewItemListingDetailsInModal.jsp
*   Purpose:                DETAILED INFORMATION OF THE SELECTED ITEM LISTING WITHIN MODAL (UNIFY ADMIN)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Date:                   21 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
**************************************************************************************** -->

<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - Item Listing Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.1.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/marketplace/ViewItemListingDetailsInModalCSS.css" rel="stylesheet" type="text/css" />

        <!-- JAVASCRIPT -->
        <script type="text/javascript" src="js/unify/admin/basejs/jquery-v1.10.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/bootstrap-v3.1.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/lodash.compat-v2.0.0.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/breakpoints-v1.0.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll-v1.3.1.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.slimscroll.horizontal-v0.6.5.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/jquery.sparkline-v2.1.2.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/dataTable/jquery.dataTables-v1.9.4.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/dataTable/dataTables.responsive-v0.1.2.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminAppJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminPluginFormComponentsJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/UnifyAdminBaseJS.js"></script>
        <script type="text/javascript" src="js/unify/admin/basejs/leaflet/leaflet.js"></script>
        <script type="text/javascript" src="js/unify/admin/webjs/marketplace/ViewItemListingDetailsInModalJS.js"></script>
    </head>
    <body style="background-color: #FFFFFF;">
        <%
            Vector itemDetailsVec = (Vector) request.getAttribute("itemDetailsVec");
            String itemImage, itemName, itemCategory, itemSellerID, itemPrice, itemCondition, itemDescription;
            itemImage = itemName = itemCategory = itemSellerID = itemPrice = itemCondition = itemDescription = "";

            String itemStatus, itemNumOfLikes, itemPostingDate, tradeLocation, tradeLat, tradeLong, tradeInformation;
            itemStatus = itemNumOfLikes = itemPostingDate = tradeLocation = tradeLat = tradeLong = tradeInformation = "";

            if (itemDetailsVec != null) {
                itemImage = (String) itemDetailsVec.get(0);
                itemName = (String) itemDetailsVec.get(1);
                itemCategory = (String) itemDetailsVec.get(2);
                itemSellerID = (String.valueOf(itemDetailsVec.get(3)));
                itemPrice = (String.valueOf(itemDetailsVec.get(4)));
                itemCondition = (String) itemDetailsVec.get(5);
                itemDescription = (String) itemDetailsVec.get(6);
                itemStatus = (String) itemDetailsVec.get(7);
                itemNumOfLikes = (String.valueOf(itemDetailsVec.get(8)));
                itemPostingDate = (String.valueOf(itemDetailsVec.get(9)));
                tradeLocation = (String) itemDetailsVec.get(10);
                tradeLat = (String) itemDetailsVec.get(11);
                tradeLong = (String) itemDetailsVec.get(12);
                tradeInformation = (String) itemDetailsVec.get(13);
            }
        %>
        <table class="formFields" border="0">
            <tr>
                <td colspan="2" style="text-align: left;">
                    <button class="btn btn-sm" onclick="window.location.href='MarketplaceAdmin?pageTransit=goToViewItemCategoryDetails&itemCategoryID=<%= request.getAttribute("urlItemCategoryID")%>'">
                        <i class="fa fa-backward"></i>&nbsp;&nbsp;Back to Item Category Details
                    </button>
                </td>
            </tr>
            <tr><td colspan="2" style="text-align: left;"><h3><strong><%= itemName%></strong></h3></td></tr>
            <tr><td colspan="2">&nbsp;</td></tr>
            <tr>
                <td>
                    <div class="form-group">
                        <div class="image-upload">
                            <img id="output-image" src="uploads/unify/images/marketplace/item/<%= itemImage%>" />
                        </div>
                        <label><%= itemNumOfLikes%>&nbsp;Likes</label>
                    </div>
                </td>
                <td>
                    <table id="itemInfoTD" class="table-no-inner-border">
                        <tr><td>Item Category:</td><td><strong><%= itemCategory%></strong></td></tr>
                        <%  if (itemStatus.equals("Available")) {%>
                        <tr><td>Item Status:</td><td><span class="label label-success"><%= itemStatus%></span></td></tr>
                        <%  } else if (itemStatus.equals("Reserved")) {%>
                        <tr><td>Item Status:</td><td><span class="label label-warning"><%= itemStatus%></span></td></tr>
                        <%  } else if (itemStatus.equals("Sold")) {%>
                        <tr><td>Item Status:</td><td><span class="label label-danger"><%= itemStatus%></span></td></tr>
                        <%  }%>
                        <tr><td>Item Condition:</td><td><strong><%= itemCondition%></strong></td></tr>
                        <tr><td>Item Price:</td><td><strong>$<%= itemPrice%></strong></td></tr>
                        <tr><td colspan="2">Item Description:<br/><strong><%= itemDescription%></strong></td></tr>
                    </table>
                </td>
            </tr>
            <tr style="text-align: center;">
                <td colspan="2">
                    <button type="button" class="btn btn-primary" onclick="return window.open('MarketplaceAdmin?pageTransit=deleteAnItem&itemID=<%= request.getAttribute("urlItemID")%>', '_parent')">Delete Item</button>&nbsp;&nbsp;
                </td>
            </tr>
        </table>

        <div style="margin: 30px 20px 0 20px">
            <div class="tabbable tabbable-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#tradeInfo" data-toggle="tab">Trade Information</a></li>
                    <li><a href="#transactionList" data-toggle="tab">Transaction List</a></li>
                    <li><a href="#itemReviews" data-toggle="tab">Item Reviews</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tradeInfo">
                        <table class="table table-hover table-bordered">
                            <tr>
                                <td>Seller ID</td>
                                <td><%= itemSellerID%></td>
                            </tr>
                            <tr>
                                <td>Item Posting Date</td>
                                <td><%= itemPostingDate%></td>
                            </tr>
                            <tr>
                                <td>Trade Location</td>
                                <td>
                                    <input type="hidden" id="dbTradeLocation" value="<%= tradeLocation%>" />
                                    <input type="hidden" id="dbTradeLat" value="<%= tradeLat%>" />
                                    <input type="hidden" id="dbTradeLong" value="<%= tradeLong%>" />
                                    <%= tradeLocation%><br/><div id="tradeMap" style="width: auto; height: 300px; margin-top: 10px;"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>Trade Information</td>
                                <td><%= tradeInformation%></td>
                            </tr>
                        </table>
                    </div>
                    <div class="tab-pane" id="transactionList">
                        <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                            <thead>
                                <tr>
                                    <th data-class="expand">Transaction Date</th>
                                    <th data-class="expand">Seller ID</th>
                                    <th data-class="expand">Buyer ID</th>
                                    <th data-hide="phone">Listing Price</th>
                                    <th data-hide="phone">Transaction Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Vector> itemTransList = (ArrayList) request.getAttribute("itemTransList");
                                    if (!itemTransList.isEmpty()) {
                                        for (int i = 0; i <= itemTransList.size() - 1; i++) {
                                            Vector v = itemTransList.get(i);
                                            String itemTransDate = String.valueOf(v.get(0));
                                            String itemTransSellerID = String.valueOf(v.get(1));
                                            String itemTransBuyerID = String.valueOf(v.get(2));
                                            String itemListingPriceTrans = String.valueOf(v.get(3));
                                            String itemTransPrice = String.valueOf(v.get(4));
                                %>
                                <tr>
                                    <td><%= itemTransDate%></td>
                                    <td><%= itemTransSellerID%></td>
                                    <td><%= itemTransBuyerID%></td>
                                    <td>$<%= itemListingPriceTrans%></td>
                                    <td>$<%= itemTransPrice%></td>
                                </tr>
                                <%      }   %>
                                <%  }   %>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="itemReviews">
                        <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                            <thead>
                                <tr>
                                    <th data-class="expand">Review Date</th>
                                    <th data-class="expand">Reviewer ID</th>
                                    <th data-class="expand">Receiver ID</th>
                                    <th data-hide="phone">Review Rating</th>
                                    <th data-hide="phone">Review Content</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<Vector> itemReviewList = (ArrayList) request.getAttribute("itemReviewList");
                                    if (!itemReviewList.isEmpty()) {
                                        for (int i = 0; i <= itemReviewList.size() - 1; i++) {
                                            Vector v = itemReviewList.get(i);
                                            String itemReviewDate = String.valueOf(v.get(0));
                                            String itemReviewerID = String.valueOf(v.get(1));
                                            String itemReviewReceiverID = String.valueOf(v.get(2));
                                            String itemReviewRating = String.valueOf(v.get(3));
                                            String itemReviewContent = String.valueOf(v.get(4));
                                %>
                                <tr>
                                    <td><%= itemReviewDate%></td>
                                    <td><%= itemReviewerID%></td>
                                    <td><%= itemReviewReceiverID%></td>
                                    <td><%= itemReviewRating%></td>
                                    <td><%= itemReviewContent%></td>
                                </tr>
                                <%      }   %>
                                <%  }   %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>