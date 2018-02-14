<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify - Marketplace(Item) Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            Vector itemDetailsSYSVec = (Vector) request.getAttribute("itemDetailsSYSVec");
            String itemImage, itemName, itemCategoryName, itemStatus, itemPrice, itemDescription;
            itemImage = itemName = itemCategoryName = itemStatus = itemPrice = itemDescription = "";

            String itemPostingDate, tradeLocation, tradeLat, tradeLong, itemSellerID;
            itemPostingDate = tradeLocation = tradeLat = tradeLong = itemSellerID = "";

            if (itemDetailsSYSVec != null) {
                itemImage = (String) itemDetailsSYSVec.get(0);
                itemName = (String) itemDetailsSYSVec.get(1);
                itemCategoryName = (String) itemDetailsSYSVec.get(2);
                itemStatus = (String.valueOf(itemDetailsSYSVec.get(3)));
                itemPrice = (String.valueOf(itemDetailsSYSVec.get(4)));
                itemDescription = (String.valueOf(itemDetailsSYSVec.get(5)));
                itemPostingDate = (String.valueOf(itemDetailsSYSVec.get(6)));
                tradeLocation = (String.valueOf(itemDetailsSYSVec.get(7)));
                tradeLat = (String.valueOf(itemDetailsSYSVec.get(8)));
                tradeLong = (String.valueOf(itemDetailsSYSVec.get(9)));
                itemSellerID = (String.valueOf(itemDetailsSYSVec.get(10)));
            }
        %>
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><a href="detail.html" class="text-default"><%= itemName%></a></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col col-sm-4">
                            <div class="owl-carousel owl-theme quickview-slider">
                                <div><img src="uploads/unify/images/marketplace/item/<%= itemImage%>" style="max-width: 151px; min-width: 151px; max-height: 156px; min-height: 156px;" /></div>
                            </div>
                        </div>
                        <div class="col col-sm-8">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td class="border-top-0">Price</td>
                                        <td class="border-top-0">
                                            <ul class="list-inline mb-0">
                                                <li class="list-inline-item"><span class="price">$<%= itemPrice%></span></li>
                                            </ul>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <button class="btn btn-sm btn-theme btn-block btn-add-quickview">Chat with Buyer</button>
                                            <button class="btn btn-sm btn-outline-theme"><i class="fa fa-heart"></i></button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
    </body>
</html>