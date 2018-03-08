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
        <title>Unify - My Transaction Item Details</title>
        
        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/marketplace/ViewItemDetailsInModalSYSCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body onload="loadMap()">
        <div id="container">
            <div style="margin-right:auto;margin-left:auto;width:100%;padding:20px 20px 0 20px;">
                <%
                    Vector transItemDetailsSYSVec = (Vector) request.getAttribute("transItemDetailsSYSVec");
                    String itemID, itemName, itemCategoryName, itemPrice, itemCondition, itemDescription, itemImage, itemStatus, itemNumOfLikes, itemLikeStatus;
                    itemID = itemName = itemCategoryName = itemPrice = itemCondition = itemDescription = itemImage = itemStatus = itemNumOfLikes = itemLikeStatus = "";

                    String itemPostingDate, tradeLocation, tradeLat, tradeLong, tradeInformation, itemSellerID, itemSellerImage, itemSellerJoinDate;
                    itemPostingDate = tradeLocation = tradeLat = tradeLong = tradeInformation = itemSellerID = itemSellerImage = itemSellerJoinDate = "";

                    String itemTransactionDate, itemBuyerID, itemBuyerImage, itemBuyerJoinDate, itemTransactionPrice;
                    itemTransactionDate = itemBuyerID = itemBuyerImage = itemBuyerJoinDate = itemTransactionPrice = "";
                    
                    String itemSellerPositive, itemSellerNeutral, itemSellerNegative, itemBuyerPositive, itemBuyerNeutral, itemBuyerNegative;
                    itemSellerPositive = itemSellerNeutral = itemSellerNegative = itemBuyerPositive = itemBuyerNeutral = itemBuyerNegative = "";
                    
                    if (transItemDetailsSYSVec != null) {
                        /* ITEM INFORMATION */
                        itemID = (String.valueOf(transItemDetailsSYSVec.get(0)));
                        itemName = (String) transItemDetailsSYSVec.get(1);
                        itemCategoryName = (String) transItemDetailsSYSVec.get(2);
                        itemPrice = (String.valueOf(transItemDetailsSYSVec.get(3)));
                        itemCondition = (String) transItemDetailsSYSVec.get(4);
                        itemDescription = (String) transItemDetailsSYSVec.get(5);
                        itemImage = (String) transItemDetailsSYSVec.get(6);
                        itemStatus = (String) transItemDetailsSYSVec.get(7);
                        itemNumOfLikes = (String.valueOf(transItemDetailsSYSVec.get(8)));
                        itemLikeStatus = (String.valueOf(transItemDetailsSYSVec.get(9)));
                        itemPostingDate = (String.valueOf(transItemDetailsSYSVec.get(10)));
                        /* TRADE INFORMATION */
                        tradeLocation = (String) transItemDetailsSYSVec.get(11);
                        tradeLat = (String) transItemDetailsSYSVec.get(12);
                        tradeLong = (String) transItemDetailsSYSVec.get(13);
                        tradeInformation = (String) transItemDetailsSYSVec.get(14);
                        /* ITEM SELLER INFORMATION */
                        itemSellerID = (String.valueOf(transItemDetailsSYSVec.get(15)));
                        itemSellerImage = (String) transItemDetailsSYSVec.get(16);
                        itemSellerJoinDate = (String.valueOf(transItemDetailsSYSVec.get(17)));
                        itemSellerPositive = (String.valueOf(transItemDetailsSYSVec.get(18)));
                        itemSellerNeutral = (String.valueOf(transItemDetailsSYSVec.get(19)));
                        itemSellerNegative = (String.valueOf(transItemDetailsSYSVec.get(20)));
                        /* ITEM TRANSACTION + ITEM BUYER INFORMATION */
                        itemTransactionDate = (String.valueOf(transItemDetailsSYSVec.get(21)));
                        itemBuyerID = (String.valueOf(transItemDetailsSYSVec.get(22)));
                        itemBuyerImage = (String) transItemDetailsSYSVec.get(23);
                        itemBuyerJoinDate = (String.valueOf(transItemDetailsSYSVec.get(24)));
                        itemBuyerPositive = (String.valueOf(transItemDetailsSYSVec.get(25)));
                        itemBuyerNeutral = (String.valueOf(transItemDetailsSYSVec.get(26)));
                        itemBuyerNegative = (String.valueOf(transItemDetailsSYSVec.get(27)));
                        itemTransactionPrice = (String.valueOf(transItemDetailsSYSVec.get(28)));
                    }
                %>
                <div class="row">
                    <div class="col-12 d-block d-md-none">
                        <div class="title"><span><%= itemName%></span></div>
                    </div>
                    <div class="col-xl-4 col-lg-5 col-md-6 col-sm-4">
                        <img src="uploads/unify/images/marketplace/item/<%= itemImage%>" class="img-fluid border image-detail" style="cursor: pointer;">
                        <div class="title d-none d-md-block"><span>Share to</span></div>
                        <ul class="list-inline d-none d-md-block">
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-facebook"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-info"><i class="fa fa-fw fa-twitter"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-danger"><i class="fa fa-fw fa-google-plus"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-linkedin"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-warning"><i class="fa fa-fw fa-envelope"></i></button></li>
                        </ul>
                    </div>
                    <div class="col-xl-8 col-lg-7 col-md-6 col-sm-8">
                        <table class="table table-detail" add-class-on-xs="table-sm">
                            <tbody>
                                <tr class="d-none d-md-table-row">
                                    <td class="border-top-0" colspan="2"><h5><%= itemName%></h5></td>
                                </tr>
                                <tr>
                                    <td>Item Condition</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><h5 class="mb-0"><%= itemCondition%></h5></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Selling Price</td>
                                    <td>
                                        <ul class="list-inline mb-0"><li class="list-inline-item"><span class="price"><h5 class="mb-0">$<%= itemPrice%></h5></span></li></ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Availability</td>
                                    <td><span class="badge badge-danger custom-badge arrowed-left"><%= itemStatus%></span></td>
                                </tr>
                                <tr>
                                    <td>Number of Likes</td>
                                    <td>
                                        <ul class="list-inline mb-0">
                                            <li class="list-inline-item">
                                                <%  if (itemNumOfLikes.equals("0") || itemNumOfLikes.equals("1")) {%>
                                                <span class="price"><h5 class="mb-0"><span class="likeCount"><%= itemNumOfLikes%></span>&nbsp;<span class="likeWording">Like</span></h5></span>
                                                <%  } else {%>
                                                <span class="price"><h5 class="mb-0"><span class="likeCount"><%= itemNumOfLikes%></span>&nbsp;<span class="likeWording">Likes</span></h5></span>
                                                <%  }   %>
                                            </li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <input type="hidden" id="itemIDHidden" value="<%= itemID%>" />
                                            <input type="hidden" id="usernameHidden" value="<%= loggedInUsername%>" />
                                            <%  if(itemLikeStatus.equals("true")) {   %>
                                            <button type="button" id="likeItemBtn" class="btn btn-outline-theme likeStatus" data-toggle="tooltip" data-placement="top" title="Like this item"><i class="fa fa-heart"></i>&nbsp;Like</button>
                                            <%  } else if(itemLikeStatus.equals("false")) {    %>
                                            <button type="button" id="likeItemBtn" class="btn btn-outline-theme noLikeStatus" data-toggle="tooltip" data-placement="top" title="Like this item"><i class="fa fa-heart"></i>&nbsp;Like</button>
                                            <%  }   %>
                                            
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item"><a class="nav-link text-default active" id="tradeInfo-tab" data-toggle="tab" href="#tradeInfoPane" role="tab" aria-controls="tradeInfoPane" aria-selected="true">Trade Info</a></li>
                            <li class="nav-item"><a class="nav-link text-default" id="transactionInfo-tab" data-toggle="tab" href="#transactionInfoPane" role="tab" aria-controls="transactionInfoPane" aria-selected="false">Transaction Info</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane border border-top-0 p-3 show active" id="tradeInfoPane" role="tabpanel" aria-labelledby="tradeInfo-tab">
                                <table class="table table-bordered mb-0">
                                    <tbody>
                                        <tr>
                                            <td class="bg-light w-25">Seller Information</td>
                                            <td>
                                                <div class="media mb-2">
                                                    <div class="mr-2">
                                                        <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= itemSellerImage%>" />
                                                    </div>
                                                    <div class="media-body col-md-6">
                                                        <h5 class="sellerInfo"><%= itemSellerID%></h5>
                                                        Joined on <%= itemSellerJoinDate%><br/>
                                                        <hr/>
                                                        <div class="rating">
                                                            <ul class="profileRating">
                                                                <li><img class="ratingImage" src="images/profilerating/positive.png" /><span class="ratingValue"><%= itemSellerPositive%></span></li>
                                                                <li><img class="ratingImage" src="images/profilerating/neutral.png" /><span class="ratingValue"><%= itemSellerNeutral%></span></li>
                                                                <li><img class="ratingImage" src="images/profilerating/negative.png" /><span class="ratingValue"><%= itemSellerNegative%></span></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Item Category</td>
                                            <td><%= itemCategoryName%></td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Item Description</td>
                                            <td><%= itemDescription%></td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Item Posted Date</td>
                                            <td><%= itemPostingDate%></td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Trade Location</td>
                                            <td>
                                                <input type="hidden" id="tradeLocation" value="<%= tradeLocation%>" />
                                                <input type="hidden" id="tradeLat" value="<%= tradeLat%>" />
                                                <input type="hidden" id="tradeLong" value="<%= tradeLong%>" />
                                                <%= tradeLocation%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Trade Location Map</td>
                                            <td><div id="mapdiv" style="width: auto; height: 300px;"></div></td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Trade Information</td>
                                            <td><%= tradeInformation%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane border border-top-0 p-3" id="transactionInfoPane" role="tabpanel" aria-labelledby="transactionInfo-tab">
                                <table class="table table-bordered mb-0">
                                    <tbody>
                                        <tr>
                                            <td class="bg-light w-25">Buyer Information</td>
                                            <td>
                                                <div class="media mb-2">
                                                    <div class="mr-2">
                                                        <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= itemBuyerImage%>" style="width:50px;height:50px;" />
                                                    </div>
                                                    <div class="media-body col-md-6">
                                                        <h5 class="sellerInfo"><%= itemBuyerID%></h5>
                                                        Joined on <%= itemBuyerJoinDate%><br/>
                                                        <hr/>
                                                        <div class="rating">
                                                            <ul class="profileRating">
                                                                <li><img class="ratingImage" src="images/profilerating/positive.png" /><span class="ratingValue"><%= itemBuyerPositive%></span></li>
                                                                <li><img class="ratingImage" src="images/profilerating/neutral.png" /><span class="ratingValue"><%= itemBuyerNeutral%></span></li>
                                                                <li><img class="ratingImage" src="images/profilerating/negative.png" /><span class="ratingValue"><%= itemBuyerNegative%></span></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Item Transaction Date</td>
                                            <td><%= itemTransactionDate%></td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Item Transaction Price</td>
                                            <td>$<%= itemTransactionPrice%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
        </div>

        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/ViewItemDetailsSYSInModalJS.js" type="text/javascript"></script>
    </body>
</html>