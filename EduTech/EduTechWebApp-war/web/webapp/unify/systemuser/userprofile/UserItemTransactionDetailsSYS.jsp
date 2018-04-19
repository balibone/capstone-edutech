<%-- 
  - Author(s):          TAN CHIN WEE WINSTON
  - Date:               6th April 2018
  - Version:            1.0
  - Credits to:         NIL
  - Description:        User Marketplace Transaction Details (Marketplace)
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
        <title>Unify - My Marketplace Transaction Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/weblayout/userprofile/UserItemTransactionDetailsSYSCSS.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
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
                                            <i class="fa fa-bell"></i>&nbsp;&nbsp;Notifications<span class="badge badge-light"><%= request.getAttribute("unreadNotificationCount")%></span>
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
                            <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" class="d-lg-none">
                                <img src="images/edubox-unify-logo.png" class="logo" />
                            </a>
                            <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" class="d-none d-lg-flex mb-2 mb-lg-0">
                                <img src="images/edubox-unify-logo.png" class="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="col-8 col-sm-6 col-md-7 col-lg-6 mt-3" style="visibility:hidden">
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
                                <a class="btn btn-outline-theme" href="ProfileSysUser?pageTransit=goToUserAccount" role="button">
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
                            <li class="breadcrumb-item active" aria-current="page">My Marketplace Transaction Details</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div id="contentArea" class="container jplist" style="margin-bottom: 30px;">
                <%
                    String successMessage = (String) request.getAttribute("successMessage");
                    if (successMessage != null) {
                %>
                <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeSuccess">&times;</button>
                    <%= successMessage%>
                </div>
                <%  }   %>
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeError">&times;</button>
                    <%= errorMessage%>
                </div>
                <%  }%>
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="title"><span>My Marketplace Transaction Details</span></div>
                        <%
                            Vector itemTransDetailsSYSVec = (Vector) request.getAttribute("itemTransDetailsSYSVec");
                            String itemID, itemName, itemCategoryName, itemPrice, itemCondition, itemDescription, itemImage, itemStatus, itemNumOfLikes, itemLikeStatus;
                            itemID = itemName = itemCategoryName = itemPrice = itemCondition = itemDescription = itemImage = itemStatus = itemNumOfLikes = itemLikeStatus = "";

                            String itemPostingDate, tradeLocation, tradeLat, tradeLong, tradeInformation, itemSellerID, itemSellerImage, itemSellerJoinDate;
                            itemPostingDate = tradeLocation = tradeLat = tradeLong = tradeInformation = itemSellerID = itemSellerImage = itemSellerJoinDate = "";

                            String itemTransactionDate, itemBuyerID, itemBuyerImage, itemBuyerJoinDate, itemTransactionPrice;
                            itemTransactionDate = itemBuyerID = itemBuyerImage = itemBuyerJoinDate = itemTransactionPrice = "";

                            String itemSellerPositive, itemSellerNeutral, itemSellerNegative, itemBuyerPositive, itemBuyerNeutral, itemBuyerNegative;
                            itemSellerPositive = itemSellerNeutral = itemSellerNegative = itemBuyerPositive = itemBuyerNeutral = itemBuyerNegative = "";

                            if (itemTransDetailsSYSVec != null) {
                                /* ITEM INFORMATION */
                                itemID = (String.valueOf(itemTransDetailsSYSVec.get(0)));
                                itemName = (String) itemTransDetailsSYSVec.get(1);
                                itemCategoryName = (String) itemTransDetailsSYSVec.get(2);
                                itemPrice = (String.valueOf(itemTransDetailsSYSVec.get(3)));
                                itemCondition = (String) itemTransDetailsSYSVec.get(4);
                                itemDescription = (String) itemTransDetailsSYSVec.get(5);
                                itemImage = (String) itemTransDetailsSYSVec.get(6);
                                itemStatus = (String) itemTransDetailsSYSVec.get(7);
                                itemNumOfLikes = (String.valueOf(itemTransDetailsSYSVec.get(8)));
                                itemLikeStatus = (String.valueOf(itemTransDetailsSYSVec.get(9)));
                                itemPostingDate = (String.valueOf(itemTransDetailsSYSVec.get(10)));
                                /* TRADE INFORMATION */
                                tradeLocation = (String) itemTransDetailsSYSVec.get(11);
                                tradeLat = (String) itemTransDetailsSYSVec.get(12);
                                tradeLong = (String) itemTransDetailsSYSVec.get(13);
                                tradeInformation = (String) itemTransDetailsSYSVec.get(14);
                                /* ITEM SELLER INFORMATION */
                                itemSellerID = (String.valueOf(itemTransDetailsSYSVec.get(15)));
                                itemSellerImage = (String) itemTransDetailsSYSVec.get(16);
                                itemSellerJoinDate = (String.valueOf(itemTransDetailsSYSVec.get(17)));
                                itemSellerPositive = (String.valueOf(itemTransDetailsSYSVec.get(18)));
                                itemSellerNeutral = (String.valueOf(itemTransDetailsSYSVec.get(19)));
                                itemSellerNegative = (String.valueOf(itemTransDetailsSYSVec.get(20)));
                                /* ITEM TRANSACTION + ITEM BUYER INFORMATION */
                                itemTransactionDate = (String.valueOf(itemTransDetailsSYSVec.get(21)));
                                itemBuyerID = (String.valueOf(itemTransDetailsSYSVec.get(22)));
                                itemBuyerImage = (String) itemTransDetailsSYSVec.get(23);
                                itemBuyerJoinDate = (String.valueOf(itemTransDetailsSYSVec.get(24)));
                                itemBuyerPositive = (String.valueOf(itemTransDetailsSYSVec.get(25)));
                                itemBuyerNeutral = (String.valueOf(itemTransDetailsSYSVec.get(26)));
                                itemBuyerNegative = (String.valueOf(itemTransDetailsSYSVec.get(27)));
                                itemTransactionPrice = (String.valueOf(itemTransDetailsSYSVec.get(28)));
                            }
                        %>
                        <form class="form-horizontal" action="MarketplaceSysUser" method="POST">
                            <div class="formDiv">
                                <div class="form-row media">
                                    <img class="img-thumbnail" src="uploads/unify/images/marketplace/item/<%= itemImage%>" style="width:50px;height:50px;"/>
                                    <div class="media-body ml-3">
                                        <input type="hidden" id="hiddenItemID" value="<%= itemID%>" />
                                        <h5 class="user-name"><strong><%= itemName%></strong></h5>
                                        <p class="card-text mb-0">$<%= itemPrice%></p>
                                        <p class="card-text mb-3">Item Condition:&nbsp;&nbsp;
                                            <%  if (itemCondition.equals("New")) { %>
                                            <span class="badge badge-success custom-badge"><%= itemCondition%></span>
                                            <%  } else if (itemCondition.equals("Used")) { %>
                                            <span class="badge badge-danger custom-badge"><%= itemCondition%></span>
                                            <%  }   %>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="formDiv">
                                <div class="form-row" style="padding: 20px 0 30px 0;">
                                    <ul class="nav nav-tabs w-100" role="tablist">
                                        <li class="nav-item"><a class="nav-link text-default active" id="tradeInfo-tab" data-toggle="tab" href="#tradeInfoPane" role="tab" aria-controls="tradeInfoPane" aria-selected="true">Trade Information</a></li>
                                        <li class="nav-item"><a class="nav-link text-default" id="transactionInfo-tab" data-toggle="tab" href="#transactionInfoPane" role="tab" aria-controls="transactionInfoPane" aria-selected="false">Transaction Information</a></li>
                                    </ul>
                                    <div class="tab-content w-100">
                                        <div class="tab-pane border border-top-0 p-3 show active" id="tradeInfoPane" role="tabpanel" aria-labelledby="tradeInfo-tab">
                                            <table class="table table-bordered mb-0">
                                                <tbody>
                                                    <tr>
                                                        <td class="bg-light w-25">Seller Information</td>
                                                        <td>
                                                            <div class="media mb-2">
                                                                <div class="mr-2">
                                                                    <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= itemSellerImage%>" style="width:50px;height:50px;"  />
                                                                </div>
                                                                <div class="media-body col-md-12">
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
                                                                <div class="media-body col-md-12">
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
                        </form>
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
            <div id="marketplace-overlay"></div>
            <div id="sellNewItem-iframe"></div>
            
            <div class="modal fade" id="voicesModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="width: 1200px;">
              <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle" style="font-size: 15px"><strong>Select one of the following to view.</strong></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                     <div class="row">
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToCompanyReview';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-building"></i></h1>
                                        <h6>My Company Reviews</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToCompanyRequest';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-question-circle"></i></h1>
                                        <h6>My Company Requests</h6>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToResume';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-file"></i></h1>
                                        <h6>My Resume List</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
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
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/userprofile/UserItemTransactionDetailsSYSJS.js" type="text/javascript"></script>
    </body>
</html>