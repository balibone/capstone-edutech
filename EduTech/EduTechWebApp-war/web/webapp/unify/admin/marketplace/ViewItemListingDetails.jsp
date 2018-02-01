<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - View Item Details</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/marketplace/ViewItemListingDetailsCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            Vector itemDetailsVec = (Vector) request.getAttribute("itemDetailsVec");
            String itemImage, itemName, itemCategory, itemSellerID, itemPrice, itemDescription;
            itemImage = itemName = itemCategory = itemSellerID = itemPrice = itemDescription = "";
            
            String itemStatus, itemPostingDate, tradeLocation, tradeLat, tradeLong;
            itemStatus = itemPostingDate = tradeLocation = tradeLat = tradeLong = "";
            
            if (itemDetailsVec != null) {
                itemImage = (String) itemDetailsVec.get(0);
                itemName = (String) itemDetailsVec.get(1);
                itemCategory = (String) itemDetailsVec.get(2);
                itemSellerID = (String.valueOf(itemDetailsVec.get(3)));
                itemPrice = (String.valueOf(itemDetailsVec.get(4)));
                itemDescription = (String.valueOf(itemDetailsVec.get(5)));
                itemStatus = (String.valueOf(itemDetailsVec.get(6)));
                itemPostingDate = (String.valueOf(itemDetailsVec.get(7)));
                tradeLocation = (String.valueOf(itemDetailsVec.get(8)));
                tradeLat = (String.valueOf(itemDetailsVec.get(9)));
                tradeLong = (String.valueOf(itemDetailsVec.get(10)));
            }
        %>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">
            <div class="col-sm-5 col-md-5 gallery-holder">
                <div class="single-product-gallery">
                    <div class="owl-item" style="width: 336px;">
                        <div class="single-product-gallery-item">
                            <img src="uploads/unify/images/marketplace/item/<%= itemImage %>" style="max-width: 251px; min-width: 251px; max-height: 256px; min-height: 256px;" />
                        </div>
                    </div>
                </div>
            </div>   			
            <div class="col-sm-7 col-md-7 product-info-block">
                <div class="product-info">
                    <h1 class="name"><%= itemName %></h1><br/>
                    <div class="stock-container">
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Item Category:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= itemCategory %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Seller ID:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= itemSellerID %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Item Status:&nbsp;</span></div></div>
                        <div class="col-sm-9">
                            <div class="stock-box">
                                <%
                                    if(itemStatus.equals("Available")) {
                                %>
                                <span class="available"><%= itemStatus %></span>
                                <%
                                    } else if (itemStatus.equals("Sold")) {
                                %>
                                <span class="notavailable"><%= itemStatus %></span>
                                <%  }   %>
                            </div>
                        </div>
                    </div>
                    <div class="description-container m-t-20">
                        <%= itemDescription %><br/>
                        (Listed on: <%= itemPostingDate %>)
                    </div>
                    <div class="price-container m-t-20">
                        <div class="col-sm-6">
                            <div class="price-box"><span class="price">$<%= itemPrice %></span></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
    </body>
</html>
