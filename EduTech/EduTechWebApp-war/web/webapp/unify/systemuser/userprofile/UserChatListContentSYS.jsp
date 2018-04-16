<%-- 
  - Author(s):          TAN CHIN WEE WINSTON
  - Date:               6th April 2018
  - Version:            1.0
  - Credits to:         NIL
  - Description:        User Marketplace Chat (Marketplace)
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
        <title>Unify - My Marketplace Chats</title>
        
        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/weblayout/userprofile/UserChatListContentSYSCSS.css" rel="stylesheet" type="text/css" />
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
                                                    for (int i = 0; i <= userMessageListTopThreeSYS.size()-1; i++) {
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
                            <li class="breadcrumb-item active" aria-current="page">My Chat List</li>
                        </ol>
                    </nav>
                </div>
            </div>
            <div class="container" style="margin-bottom: 30px;">
                <div id="frame">
                    <div id="sidepanel">
                        <div id="top-bar">
                            <button type="button" onclick="javascript:toggleBuying();">
                                <i class="fa fa-shopping-basket fa-fw" aria-hidden="true"></i>
                                <span>Buying</span>
                            </button>
                            <button type="button" onclick="javascript:toggleSelling();">
                                <i class="fa fa-send-o fa-fw" aria-hidden="true"></i>
                                <span>Selling</span>
                            </button>
                        </div>
                        <div id="search"><input type="text" placeholder="Search Contacts..." /></div>
                        <div id="buyingContacts">
                            <ul class="buyingContacts">
                                <%
                                    ArrayList<Vector> userChatBuyingListSYS = (ArrayList) request.getAttribute("userChatBuyingListSYS");
                                    if (!userChatBuyingListSYS.isEmpty()) {
                                        for (int i = 0; i <= userChatBuyingListSYS.size()-1; i++) {
                                            Vector v = userChatBuyingListSYS.get(i);
                                            String itemBuyerImage = String.valueOf(v.get(0));
                                            String itemBuyerID = String.valueOf(v.get(1));
                                            String itemName = String.valueOf(v.get(2));
                                            String chatID = String.valueOf(v.get(3));
                                            String chatStatus = String.valueOf(v.get(4));
                                            String chatContent = String.valueOf(v.get(5));
                                %>
                                <li id="contact<%= chatID%>" class="contact">
                                    <div class="wrap">
                                        <img src="uploads/commoninfrastructure/admin/images/<%= itemBuyerImage%>" />
                                        <div class="meta">
                                            <%  if(chatStatus.equals("Unread")) {   %>
                                            <p class="name" style="font-weight:bolder;"><%= itemBuyerID%><span style="display:none">;<%= chatID%></span></p>
                                            <p class="name" style="font-weight:bolder;"><%= itemName%></p>
                                            <p class="preview" style="font-weight:bolder;"><%= chatContent%></p>
                                            <%  } else if(chatStatus.equals("Read")) {  %>
                                            <p class="name"><%= itemBuyerID%><span style="display:none">;<%= chatID%></span></p>
                                            <p class="name"><%= itemName%></p>
                                            <p class="preview"><%= chatContent%></p>
                                            <%  }   %>
                                        </div>
                                    </div>
                                </li>
                                <%      }   %>
                                <%  } else {    %>
                                <p class="noChatRecords">There are no buying chats archived.</p>
                                <%  } %>
                            </ul>
                        </div>
                        <div id="sellingContacts">
                            <ul class="sellingContacts">
                                <%
                                    ArrayList<Vector> userChatSellingListSYS = (ArrayList) request.getAttribute("userChatSellingListSYS");
                                    if (!userChatSellingListSYS.isEmpty()) {
                                        for (int i = 0; i <= userChatSellingListSYS.size()-1; i++) {
                                            Vector v = userChatSellingListSYS.get(i);
                                            String itemSellerImage = String.valueOf(v.get(0));
                                            String itemSellerID = String.valueOf(v.get(1));
                                            String itemName = String.valueOf(v.get(2));
                                            String chatID = String.valueOf(v.get(3));
                                            String chatStatus = String.valueOf(v.get(4));
                                            String chatContent = String.valueOf(v.get(5));
                                %>
                                <li id="contact<%= chatID%>" class="contact">
                                    <div class="wrap">
                                        <img src="uploads/commoninfrastructure/admin/images/<%= itemSellerImage%>" />
                                        <div class="meta">
                                            <%  if(chatStatus.equals("Unread")) {   %>
                                            <p class="name" style="font-weight:bolder;"><%= itemSellerID%><span style="display:none">;<%= chatID%></span></p>
                                            <p class="name" style="font-weight:bolder;"><%= itemName%></p>
                                            <p class="preview" style="font-weight:bolder;"><%= chatContent%></p>
                                            <%  } else if(chatStatus.equals("Read")) {   %>
                                            <p class="name"><%= itemSellerID%><span style="display:none">;<%= chatID%></span></p>
                                            <p class="name"><%= itemName%></p>
                                            <p class="preview"><%= chatContent%></p>
                                            <%  }   %>
                                        </div>
                                    </div>
                                </li>
                                <%      }   %>
                                <%  } else {    %>
                                <p class="noChatRecords">There are no selling chats archived.</p>
                                <%  } %>
                            </ul>
                        </div>
                    </div>
                    <div class="content">
                        <%
                            Vector chatContentInfoVecSYS = (Vector) request.getAttribute("chatContentInfoVecSYS");
                            String receiverImage, receiverID, senderImage, buyerOrSellerStat, buyerOrSellerID;
                            receiverImage = receiverID = senderImage = buyerOrSellerStat = buyerOrSellerID = "";
                            
                            String itemID, itemImage, itemName, itemPrice, itemOfferID, buyerItemOfferStatus, currentOfferPrice;
                            itemID = itemImage = itemName = itemPrice = itemOfferID = buyerItemOfferStatus = currentOfferPrice = "";
                            
                            if (chatContentInfoVecSYS != null) {
                                receiverImage = (String) chatContentInfoVecSYS.get(0);
                                receiverID = (String) chatContentInfoVecSYS.get(1);
                                senderImage = (String) chatContentInfoVecSYS.get(2);
                                buyerOrSellerStat = (String) chatContentInfoVecSYS.get(3);
                                buyerOrSellerID = (String) chatContentInfoVecSYS.get(4);
                                itemID = (String.valueOf(chatContentInfoVecSYS.get(5)));
                                itemImage = (String) chatContentInfoVecSYS.get(6);
                                itemName = (String) chatContentInfoVecSYS.get(7);
                                itemPrice = (String.valueOf(chatContentInfoVecSYS.get(8)));
                                itemOfferID = (String.valueOf(chatContentInfoVecSYS.get(9)));
                                buyerItemOfferStatus = (String) chatContentInfoVecSYS.get(10);
                                currentOfferPrice = (String) chatContentInfoVecSYS.get(11);
                            }
                        %>
                        <div class="contact-profile">
                            <img src="uploads/commoninfrastructure/admin/images/<%= receiverImage%>" />
                            <p id="receiverID"><%= receiverID%></p>
                            <input type="hidden" id="senderIMG" value="<%= senderImage%>" />
                            <input type="hidden" id="receiverIMG" value="<%= receiverImage%>" />
                            <input type="hidden" id="contentChatID" value="<%= request.getAttribute("contentChatID")%>" />
                            
                            <input type="hidden" id="buyerOrSellerStat" value="<%= buyerOrSellerStat%>" />
                            <input type="hidden" id="buyerOrSellerID" value="<%= buyerOrSellerID%>" />
                            <input type="hidden" id="hiddenItemID" value="<%= itemID%>" />
                            <input type="hidden" id="hiddenItemOfferID" value="<%= itemOfferID%>" />
                        </div>
                        <div class="item-info" style="cursor:pointer;" onclick="location.href='MarketplaceSysUser?pageTransit=goToMsgViewItemDetailsSYS&itemHidID=<%= itemID%>'">
                            <img src="uploads/unify/images/marketplace/item/<%= itemImage%>" />
                            <p class="item-info-strong"><%= itemName%>&nbsp;($<%= itemPrice%>)<br/>
                                <span id="buyerItemOfferStatus">
                                    <%  if (buyerItemOfferStatus.equals("Pending")) {%>
                                    <span class="badge badge-info custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else if (buyerItemOfferStatus.equals("Processing")) {%>
                                    <span class="badge badge-warning custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else if (buyerItemOfferStatus.equals("Accepted")) {%>
                                    <span class="badge badge-theme custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else if (buyerItemOfferStatus.equals("Rejected")) {%>
                                    <span class="badge badge-danger custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else if (buyerItemOfferStatus.equals("Cancelled")) {%>
                                    <span class="badge badge-danger custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else if (buyerItemOfferStatus.equals("Completed")) {%>
                                    <span class="badge badge-success custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else if (buyerItemOfferStatus.equals("Failed")) {%>
                                    <span class="badge badge-danger custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else if (buyerItemOfferStatus.equals("Closed")) {%>
                                    <span class="badge badge-primary custom-badge"><%= buyerItemOfferStatus%></span>
                                    <%  } else { %>
                                    <%= buyerItemOfferStatus%>
                                    <%  }   %>
                                </span>
                            </p>
                            <div class="noDisplay">
                                <%  
                                    String chatType = request.getParameter("hidChatType");  
                                    if((chatType.equals("Buying")) && (buyerItemOfferStatus.equals("You have not made an offer on this item yet"))) {
                                %>
                                <div id="sendOfferPanel" class="pull-right pt-2 pr-4">
                                    <input type="text" id="itemOfferPrice" />
                                    <button type="button" id="sendOfferBtn" class="btn btn-sm btn-theme chatListContentBtn">Send Offer</button>
                                </div>
                                <%  } else if((chatType.equals("Buying")) && (buyerItemOfferStatus.equals("Processing"))) {   %>
                                <div id="updateOfferPanel" class="pull-right pt-2 pr-4">
                                    <input type="text" id="updatedOfferPrice" placeholder="<%= currentOfferPrice%>" />
                                    <button type="button" id="updateOfferBtn" class="btn btn-sm btn-theme chatListContentBtn">Update Offer</button>
                                </div>
                                <%  } else if((chatType.equals("Selling")) && (buyerItemOfferStatus.equals("No Offer Status"))) {   %>
                                <div id="noOfferPanel" class="pull-right pt-2 pr-4">
                                    <span>No Offer Yet</span>
                                </div>
                                <%  } else if((chatType.equals("Selling")) && (buyerItemOfferStatus.equals("Pending"))) {   %>
                                <div id="acceptRejectOfferPanel" class="pull-right pt-2 pr-4">
                                    <button type="button" id="acceptOfferBtn" class="btn btn-sm btn-theme chatListContentBtn">Accept Offer</button>
                                    <button type="button" id="negotiateOfferBtn" class="btn btn-sm btn-theme chatListContentBtn">Reject Offer</button>
                                </div>
                                <%  } else if((chatType.equals("Selling")) && (buyerItemOfferStatus.equals("Accepted"))) {   %>
                                <div id="acceptedOfferPanel" class="pull-right pt-2 pr-4">
                                    <button type="button" id="markedOfferAsCompletedBtn" class="btn btn-sm btn-theme chatListContentBtn">Marked As Sold</button>
                                    <button type="button" id="reopenListingBtn" class="btn btn-sm btn-theme chatListContentBtn">Reopen Listing</button>
                                </div>
                                <%  } else if((chatType.equals("Selling")) && (buyerItemOfferStatus.equals("Completed"))) {   %>
                                <div id="provideFeedbackPanel" class="pull-right pt-2 pr-4">
                                    <button type="button" id="provideFeedbackBtn" class="btn btn-sm btn-theme chatListContentBtn">Provide Feedback</button>
                                </div>
                                <%  } else {    %>
                                <span>&nbsp;</span>
                                <%  }   %>
                                <input type="hidden" id="tempItemOfferPrice" />
                                <div id="updateOfferPanelInterior" class="pull-right pt-2 pr-4 noDisplay">
                                    <input type="text" id="updatedOfferPriceInterior" placeholder="<%= currentOfferPrice%>" />
                                    <button type="button" id="updateOfferBtnInterior" class="btn btn-sm btn-theme chatListContentBtn">Update Offer</button>
                                </div>
                                <div id="acceptRejectOfferPanelInterior" class="pull-right pt-2 pr-4 noDisplay">
                                    <button type="button" id="acceptOfferBtnInterior" class="btn btn-sm btn-theme chatListContentBtn">Accept Offer</button>
                                    <button type="button" id="negotiateOfferBtnInterior" class="btn btn-sm btn-theme chatListContentBtn">Reject Offer</button>
                                </div>
                                <div id="acceptedOfferPanelInterior" class="pull-right pt-2 pr-4 noDisplay">
                                    <button type="button" id="markedOfferAsCompletedBtnInterior" class="btn btn-sm btn-theme chatListContentBtn">Marked As Sold</button>
                                    <button type="button" id="reopenListingBtnInterior" class="btn btn-sm btn-theme chatListContentBtn">Reopen Listing</button>
                                </div>
                                <div id="provideFeedbackPanelInterior" class="pull-right pt-2 pr-4 noDisplay">
                                    <button type="button" id="provideFeedbackBtnInterior" class="btn btn-sm btn-theme chatListContentBtn">Provide Feedback</button>
                                </div>
                            </div>
                        </div>
                        <div class="messages">
                            <ul class="messageContent">
                                <%
                                    ArrayList<Vector> chatListContentSYS = (ArrayList) request.getAttribute("chatListContentSYS");
                                    if (!chatListContentSYS.isEmpty()) {
                                        for (int i = 0; i <= chatListContentSYS.size()-1; i++) {
                                            Vector v = chatListContentSYS.get(i);
                                            String chatContent = String.valueOf(v.get(0));
                                            String chatSenderID = String.valueOf(v.get(1));
                                            String chatSenderImage = String.valueOf(v.get(2));
                                            String chatReceiverID = String.valueOf(v.get(3));
                                            String chatReceiverImage = String.valueOf(v.get(4));
                                %>
                                <%          if(loggedInUsername.equals(chatSenderID) && !chatContent.equals("")) {  %>
                                <li class="sent">
                                    <img class="chatSenderIMG" src="uploads/commoninfrastructure/admin/images/<%= chatSenderImage%>" />
                                    <p><%= chatContent%></p>
                                </li>
                                <%          } else if(loggedInUsername.equals(chatReceiverID) && !chatContent.equals("")) {   %>
                                <li class="replies">
                                    <img src="uploads/commoninfrastructure/admin/images/<%= chatSenderImage%>" />
                                    <p><%= chatContent%></p>
                                </li>
                                <%          }    %>
                                <%      } %>
                                <%  } %>
                            </ul>
                        </div>
                        <div class="message-input">
                            <div class="wrap">
                                <input type="text" placeholder="Write your message..." />
                                <button class="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="messageSenderID" />
            <input type="hidden" id="loggedInUserID" value="<%= loggedInUsername%>" />
        </div>
        
        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootbox.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/userprofile/UserChatListContentSYSJS.js" type="text/javascript"></script>
    </body>
</html>