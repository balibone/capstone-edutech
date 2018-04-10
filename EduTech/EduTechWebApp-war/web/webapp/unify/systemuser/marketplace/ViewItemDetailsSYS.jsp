<%-- 
  - Author(s):          TAN CHIN WEE WINSTON
  - Date:               6th April 2018
  - Version:            1.0
  - Credits to:         NIL
  - Description:        View Marketplace Listing Details (Marketplace)
  --%>
  
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
        <title>Unify - Marketplace Listing Details</title>
        
        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/qtip/jquery.qtip-v3.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/weblayout/marketplace/ViewItemDetailsSYSCSS.css" rel="stylesheet" type="text/css" />
    </head>
    <body onload="loadMap()">
        <!-- MOBILE SIDE NAVIGATION -->
        <nav class="offcanvas">
            <div class="offcanvas-content">
                <div id="list-menu" class="list-menu list-group" data-children=".submenu">
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#marketplaceSub" role="button" aria-expanded="false" aria-controls="marketplaceSub"><i class="fa fa-fw fa-file"></i>&nbsp;Marketplace</a>
                        <div id="marketplaceSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="MarketplaceSysUser?pageTransit=goToViewItemListingSYS">Item Listing</a></div>
                    </div>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#errandsSub" role="button" aria-expanded="false" aria-controls="errandsSub"><i class="fa fa-fw fa-file"></i>&nbsp;Errands</a>
                        <div id="errandsSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="ErrandsSysUser?pageTransit=goToViewJobListingSYS">Errands Listing</a></div>
                    </div>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#companyReviewSub" role="button" aria-expanded="false" aria-controls="companyReviewSub"><i class="fa fa-fw fa-user"></i>&nbsp;Company Review</a>
                        <div id="companyReviewSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="VoicesSysUser?pageTransit=goToViewCompanyListingSYS">Company Listing</a></div>
                    </div>
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
                </div>
            </div>
        </nav>
        <div class="content-overlay"></div>

        <!-- PAGE TOP HEADER -->
        <div class="top-header">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="d-flex justify-content-between">
                            <nav class="nav">
                                <a class="nav-item nav-link d-sm-block" href="#">Unify @ EduBox</a>
                            </nav>
                            <ul class="nav">
                                <li class="nav-item d-none d-md-block">
                                    <a href="ProfileSysUser?pageTransit=goToViewChatListSYS&assocItemID=" class="nav-link"><i class="fa fa-comment"></i>&nbsp;&nbsp;My Chats</a>
                                </li>
                                <li class="nav-item d-none d-md-block">
                                    <div class="dropdown-container">
                                        <a href="#" class="nav-link" id="dropdown-cart" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: block;">
                                            <i class="fa fa-bell"></i>&nbsp;&nbsp;Notifications
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-cart" aria-labelledby="dropdown-cart">
                                            <% 
                                                ArrayList<Vector> userMessageListTopThreeSYS = (ArrayList) request.getAttribute("userMessageListTopThreeSYS");
                                                if (!userMessageListTopThreeSYS.isEmpty()) {
                                                    for (int i = 0; i <= userMessageListTopThreeSYS.size() - 1; i++) {
                                                        Vector v = userMessageListTopThreeSYS.get(i);
                                                        String messageContent = String.valueOf(v.get(0));
                                                        String contentID = String.valueOf(v.get(1));
                                                        String messageType = String.valueOf(v.get(2));
                                                        String messageSenderImage = String.valueOf(v.get(4));
                                                        String messageSentDuration = String.valueOf(v.get(5));
                                            %>
                                            <div id="<%= messageType%><%= contentID%>" class="media messageDIV">
                                                <%  if (messageType.equals("System")) {%>
                                                <img class="img-thumbnail d-flex" src="images/<%= messageSenderImage%>" style="width:35px;height:35px;" />
                                                <%  } else {%>
                                                <img class="img-thumbnail d-flex" src="uploads/commoninfrastructure/admin/images/<%= messageSenderImage%>" style="width:35px;height:35px;" />
                                                <%  }%>
                                                <div class="message-content pl-3">
                                                    <div><%= messageContent%></div>
                                                    <small class="font-weight-normal message-content">
                                                        <i class="fa fa-clock-o"></i>&nbsp;<%= messageSentDuration%>&nbsp;(<%= messageType%>)
                                                    </small>
                                                </div>
                                            </div>
                                            <div class="dropdown-divider"></div>
                                            <%      }   %>
                                            <%  } else {    %>
                                            <p style="text-align:center;">There are no notifications.</p>
                                            <div class="dropdown-divider"></div>
                                            <%  }   %>
                                            <div class="text-center">
                                                <div class="btn-group btn-group-sm" role="group">
                                                    <a href="ProfileSysUser?pageTransit=goToUserNotificationListSYS" role="button" class="btn btn-outline-theme">
                                                        <i class="fa fa-envelope"></i>&nbsp;&nbsp;See All Notifications
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="dropdown-divider"></div>
                                        </div>
                                    </div>
                                </li>
                                <select class="select-dropdown-nav accountNavigation" data-width="120px">
                                    <option value="#" selected data-before='<i class="fa fa-user align-baseline" /></i>'>&nbsp;&nbsp;<%= loggedInUsername%></option>
                                    <option value="CommonInfra?pageTransit=goToCommonLanding" data-before='<i class="fa fa-external-link align-baseline" /></i>'>&nbsp;&nbsp;Landing Page</option>
                                    <option value="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" data-before='<i class="fa fa-user-circle align-baseline" /></i>'>&nbsp;&nbsp;My Account</option>
                                    <option value="CommonInfra?pageTransit=goToLogout" data-before='<i class="fa fa-sign-out align-baseline" /></i>'>&nbsp;&nbsp;Logout</option>
                                </select>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- PAGE MIDDLE NAVIGATION -->
        <div class="middle-header">
            <div class="container">
                <div class="row py-2 py-lg-0">
                    <div class="col-2 col-sm-1 d-block d-lg-none">
                        <div class="d-flex align-items-center justify-content-center menu-btn-wrapper">
                            <button class="btn btn-lg border-0 btn-link offcanvas-btn p-0" type="button">
                                <i class="fa fa-bars"></i>
                            </button>
                        </div>
                    </div>
                    <div class="col-2 col-sm-1 col-lg-3 pr-0">
                        <div class="d-flex align-items-center logo-wrapper">
                            <a href="index.html" class="d-lg-none">
                                <img src="images/edubox-logo.png" class="logo" />
                            </a>
                            <a href="index.html" class="d-none d-lg-flex mb-2 mb-lg-0">
                                <img src="images/edubox-logo.png" class="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="col-8 col-sm-6 col-md-7 col-lg-6 mt-3">
                        <div class="d-flex align-items-center">
                            <div class="input-group input-group-search">
                                <div class="input-group-prepend d-none d-md-flex">
                                    <select class="select-dropdown">
                                        <option value="all">All Categories</option>
                                        <option value="marketplace">Marketplace</option>
                                        <option value="errands">Errands</option>
                                        <option value="companyReview">Company Review</option>
                                    </select>
                                </div>
                                <input type="text" class="form-control" id="search-input" placeholder="Search here..." aria-label="Search here..." autocomplete="off" />
                                <span class="input-group-append">
                                    <button class="btn btn-theme btn-search" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 col-sm-4 col-md-3 col-lg-3 d-none d-sm-block mt-3">
                        <div class="d-flex align-items-center float-right abg-secondary">
                            <div class="btn-group btn-group-sm mr-3" role="group">
                                <button type="button" class="btn btn-outline-theme newItemListingBtn">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Sell An Item
                                </button>
                                <a class="btn btn-outline-theme" href="ErrandsSysUser?pageTransit=goToNewJobListingSYS" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Post A Job
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="container">
            <div id="unifyPageNAV"></div>
            <!-- BREADCRUMB -->
            <div class="breadcrumb-container">
                <div class="container">
                    <nav aria-label="breadcrumb" role="navigation">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS">Unify Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Marketplace (Item Details)</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="container" style="margin-bottom: 30px;">
                <%
                    Vector itemDetailsSYSVec = (Vector) request.getAttribute("itemDetailsSYSVec");
                    String itemID, itemName, itemCategoryName, itemPrice, itemCondition, itemDescription, itemImage, itemStatus, itemNumOfLikes, itemLikeStatus;
                    itemID = itemName = itemCategoryName = itemPrice = itemCondition = itemDescription = itemImage = itemStatus = itemNumOfLikes = itemLikeStatus = "";

                    String itemPostingDate, tradeLocation, tradeLat, tradeLong, tradeInformation, itemSellerID, itemSellerImage, itemSellerJoinDate;
                    itemPostingDate = tradeLocation = tradeLat = tradeLong = tradeInformation = itemSellerID = itemSellerImage = itemSellerJoinDate = "";

                    String itemSellerPositive, itemSellerNeutral, itemSellerNegative, chatCount;
                    itemSellerPositive = itemSellerNeutral = itemSellerNegative = chatCount = "";
                    
                    if (itemDetailsSYSVec != null) {
                        itemID = (String.valueOf(itemDetailsSYSVec.get(0)));
                        itemName = (String) itemDetailsSYSVec.get(1);
                        itemCategoryName = (String) itemDetailsSYSVec.get(2);
                        itemPrice = (String.valueOf(itemDetailsSYSVec.get(3)));
                        itemCondition = (String) itemDetailsSYSVec.get(4);
                        itemDescription = (String) itemDetailsSYSVec.get(5);
                        itemImage = (String) itemDetailsSYSVec.get(6);
                        itemStatus = (String) itemDetailsSYSVec.get(7);
                        itemNumOfLikes = (String.valueOf(itemDetailsSYSVec.get(8)));
                        itemLikeStatus = (String.valueOf(itemDetailsSYSVec.get(9)));
                        itemPostingDate = (String.valueOf(itemDetailsSYSVec.get(10)));
                        tradeLocation = (String) itemDetailsSYSVec.get(11);
                        tradeLat = (String) itemDetailsSYSVec.get(12);
                        tradeLong = (String) itemDetailsSYSVec.get(13);
                        tradeInformation = (String) itemDetailsSYSVec.get(14);
                        itemSellerID = (String.valueOf(itemDetailsSYSVec.get(15)));
                        itemSellerImage = (String) itemDetailsSYSVec.get(16);
                        itemSellerJoinDate = (String.valueOf(itemDetailsSYSVec.get(17)));
                        itemSellerPositive = (String.valueOf(itemDetailsSYSVec.get(18)));
                        itemSellerNeutral = (String.valueOf(itemDetailsSYSVec.get(19)));
                        itemSellerNegative = (String.valueOf(itemDetailsSYSVec.get(20)));
                        chatCount = (String.valueOf(itemDetailsSYSVec.get(21)));
                    }
                %>
                <div class="row">
                    <div class="col-12 d-block d-md-none">
                        <div class="title"><span><%= itemName%></span></div>
                    </div>
                    <div class="col-xl-4 col-lg-5 col-md-6">
                        <img src="uploads/unify/images/marketplace/item/<%= itemImage%>" class="img-fluid mb-2 border w-100 image-detail" style="cursor: pointer;">
                        <div class="title d-none d-md-block"><span>Share to</span></div>
                        <ul class="list-inline d-none d-md-block">
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-facebook"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-info"><i class="fa fa-fw fa-twitter"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-danger"><i class="fa fa-fw fa-google-plus"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-linkedin"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-warning"><i class="fa fa-fw fa-envelope"></i></button></li>
                        </ul>
                    </div>
                    <div class="col-xl-8 col-lg-7 col-md-6">
                        <table class="table table-detail" id="itemListingDetailsTable" add-class-on-xs="table-sm">
                            <tbody>
                                <tr class="d-none d-md-table-row">
                                    <td class="border-top-0" colspan="2">
                                        <button id="settingsBtn" type="button" class="text-right close settingsBtn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding-top:7px;">
                                            <img src="images/unifyimages/sidebar-divider-dots.png" />
                                        </button>
                                        <button type="button" id="qtipItemReportTrigger" class="text-right close"></button>
                                        <div class="dropdown-menu dropdown-menu-reportListing" aria-labelledby="settingsBtn">
                                            <button id="reportItemListingBtn<%= itemID%>" type="button" class="dropdown-item itemListingDetailsBtn">Report Listing</button>
                                        </div>
                                        <div class="pull-left"><h4><%= itemName%></h4></div>
                                    </td>
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
                                    <%  if (itemStatus.equals("Available")) {%>
                                    <td><span class="badge badge-success custom-badge arrowed-left"><%= itemStatus%></span></td>
                                    <%  } else if (itemStatus.equals("Sold")) {%>
                                    <td><span class="badge badge-danger custom-badge arrowed-left"><%= itemStatus%></span></td>
                                    <%  }   %>
                                </tr>
                                <tr>
                                    <td>Number of Likes</td>
                                    <td>
                                        <ul class="list-inline mb-0">
                                            <li class="list-inline-item itemLikes">
                                                <%  if (itemNumOfLikes.equals("0") || itemNumOfLikes.equals("1")) {%>
                                                <a href="#"><span class="price"><h5 class="mb-0"><span class="likeCount"><%= itemNumOfLikes%></span>&nbsp;<span class="likeWording">Like</span></h5></span></a>
                                                <%  } else {%>
                                                <a href="#"><span class="price"><h5 class="mb-0"><span class="likeCount"><%= itemNumOfLikes%></span>&nbsp;<span class="likeWording">Likes</span></h5></span></a>
                                                <%  }   %>
                                            </li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <%  if (itemSellerID.equals(request.getAttribute("loggedInUsername"))) {%>
                                            <button type="button" class="btn btn-outline-theme" onclick="location.href='MarketplaceSysUser?pageTransit=goToEditItemListingSYS&urlItemID=<%= itemID%>'"><i class="fa fa-edit"></i>&nbsp;&nbsp;Edit Listing</button>
                                            <%  } else if(Integer.parseInt(chatCount) == 0) {    %>
                                            <button type="button" class="btn btn-outline-theme" onclick="location.href='ProfileSysUser?pageTransit=goToViewChatListSYS&assocItemID=<%= itemID%>'"><i class="fa fa-comment"></i>&nbsp;&nbsp;Chat with Seller</button>
                                            <button id="makeOfferBtn" type="button" class="btn btn-outline-theme"><i class="fa fa-star"></i>&nbsp;&nbsp;Make Offer</button>
                                            <%  } else if(Integer.parseInt(chatCount) > 0) { %>
                                            <button type="button" class="btn btn-outline-theme" onclick="location.href='ProfileSysUser?pageTransit=goToViewChatListSYS&assocItemID='"><i class="fa fa-comments"></i>&nbsp;&nbsp;View Chat History</button>
                                            <button id="makeOfferBtn" type="button" class="btn btn-outline-theme"><i class="fa fa-star"></i>&nbsp;&nbsp;Make Offer</button>
                                            <%  }   %>
                                            <%  if(itemLikeStatus.equals("true")) {   %>
                                            <button type="button" id="likeItemBtn" class="btn btn-outline-theme likeStatus itemListingDetailsBtn" data-toggle="tooltip" data-placement="top" title="Like this item"><i class="fa fa-heart"></i></button>
                                            <%  } else if(itemLikeStatus.equals("false")) {    %>
                                            <button type="button" id="likeItemBtn" class="btn btn-outline-theme noLikeStatus itemListingDetailsBtn" data-toggle="tooltip" data-placement="top" title="Like this item"><i class="fa fa-heart"></i></button>
                                            <%  }   %>
                                            
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item"><a class="nav-link text-default active" id="itemTradePane-tab" data-toggle="tab" href="#itemTradePane" role="tab" aria-controls="itemTradePane" aria-selected="true">Trade Information</a></li>
                            <li class="nav-item"><a class="nav-link text-default" id="itemDescriptionPane-tab" data-toggle="tab" href="#itemDescriptionPane" role="tab" aria-controls="itemDescriptionPane" aria-selected="false">Item Information</a></li>
                            <li class="nav-item">
                                <a class="nav-link text-default" id="itemSellerPane-tab" data-toggle="tab" href="#itemSellerPane" role="tab" aria-controls="itemSellerPane" aria-selected="false">Seller Info</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane border border-top-0 p-3 show active" id="itemTradePane" role="tabpanel" aria-labelledby="itemTradePane-tab">
                                <table class="table table-bordered mb-0">
                                    <tbody>
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
                            <div class="tab-pane border border-top-0 p-3" id="itemDescriptionPane" role="tabpanel" aria-labelledby="itemDescriptionPane-tab">
                                <table class="table table-bordered mb-0">
                                    <tbody>
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
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane border border-top-0 p-3" id="itemSellerPane" role="tabpanel" aria-labelledby="itemSellerPane-tab">
                                <h5 class="sellerInfo">Seller Information:</h5>
                                <div class="media mb-2 mt-3">
                                    <div class="mr-2">
                                        <a href="ProfileSysUser?pageTransit=goToUserProfileSYS&itemSellerID=<%= itemSellerID%>">
                                            <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= itemSellerImage%>" style="width:50px;height:50px;" />
                                        </a>
                                    </div>
                                    <div class="media-body col-md-6">
                                        <div style="cursor: pointer;" onclick="window.location='ProfileSysUser?pageTransit=goToUserProfileSYS&itemSellerID=<%= itemSellerID%>';">
                                            <h5 class="user-name"><strong><%= itemSellerID%></strong></h5>
                                            Joined on <%= itemSellerJoinDate%><br/>
                                        </div>
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
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-5">
                    <div class="col-12">
                        <div class="title">
                            <span>Other items within the same category:</span>
                        </div>
                    </div>
                    <div class="col pl-sm-0 pr-sm-0">
                        <div class="owl-carousel owl-theme product-slider">
                            <%
                                ArrayList<Vector> assocCategoryItemListSYS = (ArrayList) request.getAttribute("assocCategoryItemListSYS");
                                if (!assocCategoryItemListSYS.isEmpty()) {
                                    for (int i = 0; i <= assocCategoryItemListSYS.size()-1; i++) {
                                        Vector v = assocCategoryItemListSYS.get(i);
                                        String assocCategoryItemID = String.valueOf(v.get(0));
                                        String assocCategoryItemImage = String.valueOf(v.get(1));
                                        String assocCategoryItemName = String.valueOf(v.get(2));
                                        String assocCategoryName = String.valueOf(v.get(3));
                                        String assocCategoryItemSellerID = String.valueOf(v.get(4));
                                        String assocCategoryItemPostedTime = String.valueOf(v.get(5));
                                        String assocCategoryItemPrice = String.valueOf(v.get(6));
                                        String assocCategoryItemLike = String.valueOf(v.get(7));
                            %>
                            <div class="product-slider-item">
                                <div class="card card-product">
                                    <div class="card-header" style="font-size: 13px;">
                                        <%= assocCategoryItemSellerID%><br/><%= assocCategoryItemPostedTime%>
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <div class="img-wrapper">
                                                <a href="MarketplaceSysUser?pageTransit=goToViewItemDetailsSYS&hiddenItemID=<%= assocCategoryItemID%>&hiddenCategoryName=<%= assocCategoryName%>">
                                                    <img class="card-img-top" src="uploads/unify/images/marketplace/item/<%= assocCategoryItemImage%>" />
                                                </a>
                                                <div class="tools tools-bottom" data-animate-in="fadeInDown" data-animate-out="flipOutX">
                                                    <div class="btn-group" role="group" aria-label="card-product-tools">
                                                        <button class="btn btn-link btn-sm">Chat with Seller</button>
                                                        <button class="btn btn-link btn-sm d-none d-md-inline-block"><i class="fa fa-heart"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                            <span class="card-title"><strong><a href="MarketplaceSysUser?pageTransit=goToViewItemDetailsSYS&hiddenItemID=<%= assocCategoryItemID%>&hiddenCategoryName=<%= assocCategoryName%>"><%= assocCategoryItemName%></a></strong></span><br/>
                                            <span class="card-text"><%= assocCategoryName%></span>
                                        </div>
                                    </div>
                                    <div class="card-footer text-muted mt-1">
                                        <span class="float-left"><span class="ml-1 price">$<%= assocCategoryItemPrice%></span></span>
                                        <%  if (assocCategoryItemLike.equals("0") || assocCategoryItemLike.equals("1")) {%>
                                        <span class="float-right"><i class="fa fa-heart-o"></i>&nbsp;<%= assocCategoryItemLike%>&nbsp;Like</span>
                                        <%  } else {%>
                                        <span class="float-right"><i class="fa fa-heart-o"></i>&nbsp;<%= assocCategoryItemLike%>&nbsp;Likes</span>
                                        <%  }   %>
                                    </div>
                                </div>
                            </div>
                            <%      }   %>
                            <%  }   %>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="chat-main">
                <div class="col-md-12 chat-header">
                    <div class="row header-one text-white p-1">
                        <div class="col-md-6 name pl-2">
                            <i class="fa fa-comment"></i>
                            <h6 class="ml-1 mb-0 mt-1">Unify Bot</h6>
                        </div>
                        <div class="col-md-6 options text-right pr-0">
                            <i class="fa fa-window-minimize hide-chat-box hover text-center"></i>
                        </div>
                    </div>
                </div>
                <div class="chat-content">
                    <div class="col-md-12 chats">
                        <iframe src="ProfileSysUser?pageTransit=goToUnifyBot" width="305" height="285" frameborder="0" ></iframe>
                    </div>
                </div>
            </div>
            
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="sellNewItem-iframe"></div>
            <div id="itemLikeList-iframe"></div>
            
            <div style="display:none;" id="offerTooltip">
                Offer Price&nbsp;<span style="color:#FF0000;">*</span><br/>
                <input type="text" id="itemOfferPrice" class="itemOfferFields" placeholder="Please omit the $ for your item offer price" style="margin-bottom: 7px;" /><br/>
                Buyer Comments<br/>
                <textarea rows="3" id="itemOfferDescription" class="itemOfferFields" placeholder="e.g. Meetup Location, Open for Negotiation"></textarea><br/>
                <button type="button" id="sendOfferBtn" class="itemListingDetailsBtn" style="margin:7px 0 7px 0;">Send Offer</button><br/>
                <input type="hidden" id="itemIDHidden" value="<%= itemID%>" />
                <span id="successOfferResponse"></span><span id="failedOfferResponse"></span>
            </div>
            <div style="display:none;" id="reportItemListingTip">
                Report Category&nbsp;<span style="color:#FF0000;">*</span><br/>
                <select id="itemReportCategory" class="itemReportFields" style="margin-bottom: 7px;">
                    <option value="" disabled="disabled" selected="selected">- Please Select -</option>
                    <option value="Counterfeit Item">Counterfeit Item</option>
                    <option value="Prohibited Item">Prohibited Item</option>
                    <option value="Wrong Item Category">Wrong Item Category</option>
                    <option value="Irrelevant Keywords">Irrelevant Keywords</option>
                    <option value="Duplicate Post">Duplicate Post</option>
                    <option value="Offensive Content">Offensive Content</option>
                    <option value="Mispriced Listing">Mispriced Listing</option>
                </select><br/>
                Report Description&nbsp;<span style="color:#FF0000;">*</span><br/>
                <textarea rows="3" id="itemReportDescription" class="itemReportFields" placeholder="e.g. This listing contains inappropriate content ..."></textarea><br/>
                <button type="button" id="sendItemReportBtn" class="itemListingDetailsBtn" style="margin:7px 0 7px 0;">Send Item Report</button><br/>
                <input type="hidden" id="itemHiddenID" />
                <span id="successReportResponse"></span><span id="failedReportResponse"></span>
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
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/qtip/jquery.qtip-v3.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/ViewItemDetailsSYSJS.js" type="text/javascript"></script>
    </body>
</html>