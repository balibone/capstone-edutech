<%-- 
  - Author(s):          TAN CHIN WEE WINSTON
  - Date:               6th April 2018
  - Version:            1.0
  - Credits to:         NIL
  - Description:        Unify User Profile Page (Marketplace)
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
        <title>Unify - User Profile</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/qtip/jquery.qtip-v3.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/userprofile/UserProfileSYSCSS.css" rel="stylesheet" type="text/css">

        <link href="css/unify/systemuser/baselayout/jplist/jquery-ui.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/jplist/jplist.core.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.filter-toggle-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.pagination-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.history-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.textbox-filter.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.jquery-ui-bundle.min.css" rel="stylesheet" type="text/css" />
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
                            <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div id="contentArea" class="container jplist mb-3">
                <div class="row">
                    <div class="col-lg-3 col-md-4 mb-4 mb-md-0">
                        <div class="card user-card">
                            <%
                                Vector userProfileVec = (Vector) request.getAttribute("userProfileVec");
                                String username, userFirstName, userLastName, userImage, userCreationDate;
                                username = userFirstName = userLastName = userImage = userCreationDate = "";

                                if (userProfileVec != null) {
                                    username = (String) userProfileVec.get(0);
                                    userFirstName = (String) userProfileVec.get(1);
                                    userLastName = (String) userProfileVec.get(2);
                                    userImage = (String) userProfileVec.get(3);
                                    userCreationDate = (String.valueOf(userProfileVec.get(4)));
                                }
                            %>
                            <div class="card-body p-2 mb-3 mb-md-0 mb-xl-3">
                                <div class="media">
                                    <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= userImage%>" style="width:50px;height:50px;"/>
                                    <div class="media-body ml-2">
                                        <h5 class="user-name"><strong><%= userFirstName%>&nbsp;<%= userLastName%></strong></h5>
                                        <p>@<%= username%></p>
                                        <small class="card-text text-muted mt-2">Joined <%= userCreationDate%></small>
                                    </div>
                                </div>
                            </div>
                            <div class="list-group list-group-flush">
                                <a href="ProfileSysUser?pageTransit=goToUserProfileSYS&itemSellerID=<%= username%>" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-user"></i>&nbsp;Marketplace Listing
                                </a>
                                <a href="ProfileSysUser?pageTransit=goToUserProfile&itemSellerID=<%= username%>" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-map-marker"></i>&nbsp;Errands Listing
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9 col-md-8">
                        <div class="title"><span><%= username%>'s Item Listing</span></div>
                        <div class="jplist-search sorting-bar">
                            <button class="btn btn-outline-theme btn-block dropdown-toggle d-md-none mt-2" type="button" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="false" aria-controls="collapseFilter">
                                Search Filter
                            </button>
                            <div class="collapse d-md-block pt-3 pt-md-0" id="collapseFilter">
                                <div class="mt-2" remove-class-on-xs="mt-2" add-class-on-xs="w-100">
                                    <span>Item Name:</span>
                                    <input type="text" data-path=".itemName" class="searchInputText w-25" placeholder="Search Item ..." 
                                           remove-class-on-xs="w-25" add-class-on-xs="w-100" aria-label="Search Item ..." 
                                           data-control-type="textbox" data-control-action="filter" />
                                </div>
                                <div class="mr-3 jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100">
                                    <input type="hidden" id="dbItemCategory" value="<%= request.getAttribute("itemCategoryStr")%>" />
                                    <div id="itemCategoryDropdown" class="jplist-drop-down-search" add-class-on-xs="w-100" 
                                         data-control-type="filter-drop-down" data-control-name="category-filter" data-control-action="filter">
                                        <ul></ul>
                                    </div>
                                </div>
                                <div class="mr-3 jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                     data-control-type="filter-drop-down" data-control-name="category-filter" 
                                     data-control-action="filter">
                                        <ul>
                                            <li><span data-path="default">All Item Conditions</span></li>
                                            <li><span data-path=".New">New</span></li>
                                            <li><span data-path=".Used">Used</span></li>
                                        </ul>
                                </div>
                                <div class="mr-3 jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                     data-control-type="sort-drop-down" data-control-name="sort" data-control-action="sort" 
                                     data-datetime-format="{year}-{month}-{day} {hour}:{min}:{sec}">
                                    <ul>
                                        <li><span data-path=".itemPostingDate" data-order="desc" data-type="datetime" data-default="true">Recently Posted</span></li>
                                        <li><span data-path=".itemNumOfLikes" data-order="desc" data-type="number">Popularity</span></li>
                                        <li><span data-path=".itemName" data-order="asc" data-type="text">Name Asc</span></li>
                                        <li><span data-path=".itemName" data-order="desc" data-type="text">Name Desc</span></li>
                                        <li><span data-path=".itemPrice" data-order="asc" data-type="number">Price Asc</span></li>
                                        <li><span data-path=".itemPrice" data-order="desc" data-type="number">Price Desc</span></li>
                                    </ul>
                                </div>
                                <div class="jplist-drop-down" add-class-on-xs="w-100" data-control-type="items-per-page-drop-down" 
                                     data-control-name="paging" data-control-action="paging" data-control-animate-to-top="true">
                                    <ul>
                                        <li><span data-number="4">4 per page</span></li>
                                        <li><span data-number="8">8 per page</span></li>
                                        <li><span data-number="12" data-default="true">12 per page</span></li>
                                        <li><span data-number="16">16 per page</span></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- ITEM LISTING -->
                        <div class="row equal-height" add-class-on-xs="no-gutters">
                            <div class="list searchresult-row">
                                <%
                                    ArrayList<Vector> userItemListSYS = (ArrayList) request.getAttribute("userItemListSYS");
                                    if (!userItemListSYS.isEmpty()) {
                                        for (int i = 0; i <= userItemListSYS.size()-1; i++) {
                                            Vector v = userItemListSYS.get(i);
                                            String itemID = String.valueOf(v.get(0));
                                            String itemImage = String.valueOf(v.get(1));
                                            String itemName = String.valueOf(v.get(2));
                                            String itemCategoryName = String.valueOf(v.get(3));
                                            String itemSellerID = String.valueOf(v.get(4));
                                            String itemSellerImage = String.valueOf(v.get(5));
                                            String itemPostedDuration = String.valueOf(v.get(6));
                                            String itemPostingDate = String.valueOf(v.get(7));
                                            String itemPrice = String.valueOf(v.get(8));
                                            String itemNumOfLikes = String.valueOf(v.get(9));
                                            String itemLikeStatus = String.valueOf(v.get(10));
                                            String itemCondition = String.valueOf(v.get(11));
                                            String itemStatus = String.valueOf(v.get(12));
                                %>
                                <div class="col-xl-3 col-md-3 col-6 d-block d-lg-none d-xl-block list-item">
                                    <div class="card card-product">
                                        <div class="card-header" style="font-size: 13px;">
                                            <button id="settingsBtn<%= itemID%>" type="button" class="text-right close settingsBtn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding-top:7px;">
                                                <img src="images/unifyimages/sidebar-divider-dots.png" />
                                            </button>
                                            <button type="button" id="qtipItemReportTrigger<%= itemID%>" class="text-right close qtipItemReportTrigger"></button>
                                            <div class="dropdown-menu dropdown-menu-reportListing" aria-labelledby="settingsBtn<%= itemID%>">
                                                <%  if(!loggedInUsername.equals(itemSellerID)) { %>
                                                <button id="reportItemListingBtn<%= itemID%>" type="button" class="dropdown-item userProfileBtn">Report Listing</button>
                                                <%  }   %>
                                            </div>
                                            <div class="pull-left" style="padding-right: 10px;">
                                                <div class="profilePicBorder">
                                                    <img class="profilePic" src="uploads/commoninfrastructure/admin/images/<%= itemSellerImage%>" />
                                                </div>
                                            </div>
                                            <div class="profileContent">
                                                <h3 class="profileHeader"><%= itemSellerID%></h3>
                                                <time class="profileTime"><i class="fa fa-clock-o" style="margin-right: 5px;"></i><%= itemPostedDuration%></time>
                                            </div>
                                        </div>
                                        <div class="card-content">
                                            <div class="card-body mb-2">
                                                <div class="img-wrapper mb-2">
                                                    <a href="MarketplaceSysUser?pageTransit=goToViewItemDetailsSYS&hiddenItemID=<%= itemID%>&hiddenCategoryName=<%= itemCategoryName%>&hiddenUsername=<%= loggedInUsername%>">
                                                        <img class="card-img-top" style="width: 130px; height: 130px;" src="uploads/unify/images/marketplace/item/<%= itemImage%>" />
                                                    </a>
                                                    <div class="tools tools-left" data-animate-in="fadeInLeft" data-animate-out="fadeOutUp">
                                                        <div class="btn-group-vertical" role="group" aria-label="card-product-tools">
                                                            <%  if(itemLikeStatus.equals("true")) {   %>
                                                            <button type="button" id="likeItemBtn<%= itemID%>" class="btn btn-link btn-sm userProfileBtn likeStatus" data-toggle="tooltip" data-placement="top" title="Unlike this item"><i class="fa fa-heart"></i></button>
                                                            <%  } else if(itemLikeStatus.equals("false")) {    %>
                                                            <button type="button" id="likeItemBtn<%= itemID%>" class="btn btn-link btn-sm userProfileBtn noLikeStatus" data-toggle="tooltip" data-placement="top" title="Like this item"><i class="fa fa-heart"></i></button>
                                                            <%  }   %>
                                                        </div>
                                                    </div>
                                                    <%  if (itemStatus.equals("Reserved")) { %>
                                                    <span class="badge badge-warning custom-badge arrowed-left label label-top-right">Reserved</span>
                                                    <%  } else if (itemStatus.equals("Sold")) { %>
                                                    <span class="badge badge-danger custom-badge arrowed-left label label-top-right">Sold</span>
                                                    <%  }%>
                                                </div>
                                                <span class="card-title itemName"><strong><a href="MarketplaceSysUser?pageTransit=goToViewItemDetailsSYS&hiddenItemID=<%= itemID%>&hiddenCategoryName=<%= itemCategoryName%>"><%= itemName%></a></strong></span><br/>
                                                <span class="card-text itemPostingDate" style="display:none;"><%= itemPostingDate%></span>
                                                <span class="card-text <%= itemCategoryName.replaceAll(" ", "")%>" style="display:none;"><%= itemCategoryName%></span>
                                                <span class="card-text" style="font-size: 11px;">Condition:&nbsp;<span class="<%= itemCondition%>"><strong><%= itemCondition%></strong></span></span>
                                            </div>
                                        </div>
                                        <div class="card-footer text-muted mt-1">
                                            <span class="float-left"><span class="ml-1 price itemPrice">$<%= itemPrice%></span></span>
                                            <%  if (itemNumOfLikes.equals("0") || itemNumOfLikes.equals("1")) {%>
                                            <span class="float-right">
                                                <i class="fa fa-heart-o"></i>&nbsp;
                                                <span class="itemNumOfLikes" id="itemNumOfLikes<%= itemID%>"><%= itemNumOfLikes%></span>&nbsp;<span id="likeWording<%= itemID%>">Like</span>
                                            </span>
                                            <%  } else {%>
                                            <span class="float-right">
                                                <i class="fa fa-heart-o"></i>&nbsp;
                                                <span class="itemNumOfLikes" id="itemNumOfLikes<%= itemID%>"><%= itemNumOfLikes%></span>&nbsp;<span id="likeWording<%= itemID%>">Likes</span>
                                            </span>
                                            <%  }   %>
                                        </div>
                                    </div>
                                </div>
                                <%      }   %>
                                <%  }%>
                            </div>
                        </div>
                            
                        <div class="box jplist-no-results text-shadow align-center">
                            <p><strong>No results found. Please refine your search.</strong></p>
                        </div>
                        <div class="jplist-search">
                            <div class="jplist-label" data-type="Displaying {end} of all {all} results" 
                                 data-control-type="pagination-info" data-control-name="paging" data-control-action="paging">
                            </div>
                            <div class="jplist-pagination" data-control-animate-to-top="true" 
                                 data-control-type="pagination" data-control-name="paging" data-control-action="paging">
                            </div>
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
            <div id="unifyFooter"></div>
            
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
                <button type="button" id="sendItemReportBtn" style="margin:7px 0 7px 0;">Send Item Report</button><br/>
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
        <script src="js/unify/systemuser/basejs/qtip/jquery.qtip-v3.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/userprofile/UserProfileSYSJS.js" type="text/javascript"></script>

        <script src="js/unify/systemuser/basejs/jplist/jquery-ui.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.core.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.filter-dropdown-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.filter-toggle-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.history-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.jquery-ui-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.pagination-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.sort-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.textbox-filter.min.js"></script>
    </body>
</html>