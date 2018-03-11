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
        <title>Unify - My Account</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/qtip/jquery.qtip-v3.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/marketplace/PendingItemOfferSYSCSS.css" rel="stylesheet" type="text/css" />

        <link href="css/unify/systemuser/baselayout/jplist/jquery-ui.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/jplist/jplist.core.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.filter-toggle-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.pagination-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.history-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.textbox-filter.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.jquery-ui-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.checkbox-dropdown.min.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <!-- MOBILE SIDE NAVIGATION -->
        <nav class="offcanvas">
            <div class="offcanvas-content">
                <div id="list-menu" class="list-menu list-group" data-children=".submenu">
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccount"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
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
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccount"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
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
                                    <div class="dropdown-container">
                                        <a href="#" class="nav-link" id="dropdown-cart" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: block;">
                                            <i class="fa fa-envelope"></i>&nbsp;&nbsp;Messages
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
                                            <%  }   %>
                                            <div class="text-center">
                                                <div class="btn-group btn-group-sm" role="group">
                                                    <a href="ProfileSysUser?pageTransit=goToUserNotificationList" role="button" class="btn btn-outline-theme">
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
                                    <option value="ProfileSysUser?pageTransit=goToUnifyUserAccount" data-before='<i class="fa fa-user-circle align-baseline" /></i>'>&nbsp;&nbsp;My Account</option>
                                    <option value="ProfileSysUser?pageTransit=goToLogout" data-before='<i class="fa fa-sign-out align-baseline" /></i>'>&nbsp;&nbsp;Logout</option>
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
                                <a class="btn btn-outline-theme" href="MarketplaceSysUser?pageTransit=goToNewItemListingSYS" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Sell An Item
                                </a>
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
                            <li class="breadcrumb-item"><a href="ProfileSysUser?pageTransit=goToUnifyUserAccount">Unify Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Pending Item Offer</li>
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
                    <div class="col-lg-3 col-md-4 mb-4 mb-md-0">
                        <div class="card user-card">
                            <%
                                Vector userAccountVec = (Vector) request.getAttribute("userAccountVec");
                                String username, userFirstName, userLastName, userImage, userCreationDate;
                                username = userFirstName = userLastName = userImage = userCreationDate = "";

                                if (userAccountVec != null) {
                                    username = (String) userAccountVec.get(0);
                                    userFirstName = (String) userAccountVec.get(1);
                                    userLastName = (String) userAccountVec.get(2);
                                    userImage = (String) userAccountVec.get(3);
                                    userCreationDate = (String.valueOf(userAccountVec.get(4)));
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
                                <a href="ProfileSysUser?pageTransit=goToMarketplaceTrans" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-user"></i>&nbsp;Marketplace Transaction
                                </a>
                                <a href="account-address.html" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-map-marker"></i>&nbsp;Errands Transaction
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-9 col-md-8">
                        <div class="x_panel">
                            <div class="title"><span>Your Pending Item Offer</span></div>
                            <div class="x_content">
                                <form class="form-horizontal" action="MarketplaceSysUser" method="POST">
                                    <%
                                        ArrayList<Vector> itemOfferUserListSYS = (ArrayList) request.getAttribute("itemOfferUserListSYS");
                                        if (!itemOfferUserListSYS.isEmpty()) {
                                            String itemID = String.valueOf(itemOfferUserListSYS.get(0).get(0));
                                            String itemName = String.valueOf(itemOfferUserListSYS.get(0).get(1));
                                            String itemImage = String.valueOf(itemOfferUserListSYS.get(0).get(2));
                                            String itemPrice = String.valueOf(itemOfferUserListSYS.get(0).get(3));
                                            String itemCondition = String.valueOf(itemOfferUserListSYS.get(0).get(4));
                                    %>
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
                                            <h5 class="user-name" style="font-size: 15px;"><strong>List of item offers</strong></h5>
                                            <div class="jplist-search sorting-bar">
                                                <div class="mr-3 jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                                     data-control-type="sort-drop-down" data-control-name="sort" data-control-action="sort" 
                                                     data-datetime-format="{year}-{month}-{day} {hour}:{min}:{sec}">
                                                    <ul>
                                                        <li><span data-path=".itemOfferDate" data-order="desc" data-type="datetime" data-default="true">Recently Posted</span></li>
                                                        <li><span data-path=".itemOfferPrice" data-order="asc" data-type="number">Offer Price Asc</span></li>
                                                        <li><span data-path=".itemOfferPrice" data-order="desc" data-type="number">Offer Price Desc</span></li>
                                                    </ul>
                                                </div>
                                                <div class="jplist-drop-down" add-class-on-xs="w-100" data-control-type="items-per-page-drop-down" 
                                                     data-control-name="paging" data-control-action="paging" data-control-animate-to-top="true">
                                                    <ul>
                                                        <li><span data-number="2" data-default="true">2 per page</span></li>
                                                        <li><span data-number="4">4 per page</span></li>
                                                        <li><span data-number="8">8 per page</span></li>
                                                    </ul>
                                                </div>
                                                <div class="jplist-checkbox-dropdown" data-control-type="checkbox-dropdown" 
                                                     data-control-name="category-checkbox-dropdown" data-control-action="filter" 
                                                     data-no-selected-text="Filter by:" data-one-item-text="{num} selected" 
                                                     data-many-items-text="{num} selected">
                                                    <ul>
                                                        <li>
                                                            <input data-path=".Accepted" id="Accepted" type="checkbox" />
                                                            <label for="Accepted">Accepted</label>
                                                        </li>
                                                        <li>
                                                            <input data-path=".Pending" id="Pending" type="checkbox" />
                                                            <label for="Pending">Pending</label>
                                                        </li>
                                                        <li>
                                                            <input data-path=".Rejected" id="Rejected" type="checkbox" />
                                                            <label for="Rejected">Rejected</label>
                                                        </li>
                                                        <li>
                                                            <input data-path=".Negotiating" id="Negotiating" type="checkbox" />
                                                            <label for="Negotiating">Negotiating</label>
                                                        </li>
                                                        <li>
                                                            <input data-path=".Completed" id="Completed" type="checkbox" />
                                                            <label for="Completed">Completed</label>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="jplist-panel">
                                                    <button type="button" class="jplist-reset-btn" data-control-type="reset" 
                                                            data-control-name="reset" data-control-action="reset"><i class="fa fa-retweet">&nbsp;&nbsp;Reset</i>
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="list searchresult-row">
                                                <%
                                                    for (int i = 1; i <= itemOfferUserListSYS.size()-1; i++) {
                                                        Vector v = itemOfferUserListSYS.get(i);
                                                        String itemOfferID = String.valueOf(v.get(0));
                                                        String itemOfferUserID = String.valueOf(v.get(1));
                                                        String itemOfferUserFirstName = String.valueOf(v.get(2));
                                                        String itemOfferUserLastName = String.valueOf(v.get(3));
                                                        String itemOfferUserImage = String.valueOf(v.get(4));
                                                        String itemOfferUserPositiveCount = String.valueOf(v.get(5));
                                                        String itemOfferUserNeutralCount = String.valueOf(v.get(6));
                                                        String itemOfferUserNegativeCount = String.valueOf(v.get(7));
                                                        String itemOfferPrice = String.valueOf(v.get(8));
                                                        String itemOfferDescription = String.valueOf(v.get(9));
                                                        String itemOfferStatus = String.valueOf(v.get(10));
                                                        String itemOfferPostedDuration = String.valueOf(v.get(11));
                                                        String itemOfferDate = String.valueOf(v.get(12));
                                                %>
                                                <div class="col-sm-6 pl-1 pr-1 list-item">
                                                    <div class="card">
                                                        <div class="card-body media">
                                                            <img class="img-circle pull-left mr-3 d-flex align-self-start" src="uploads/commoninfrastructure/admin/images/<%= itemOfferUserImage%>" style="width:50px;height:50px;" />
                                                            <div class="media-body">
                                                                <span class="text-muted pull-right mt-0">
                                                                    <span class="itemOfferDate" style="display:none;"><%= itemOfferDate%></span>
                                                                    <small class="text-muted"><%= itemOfferPostedDuration%></small>
                                                                </span>
                                                                <strong class="text-primary"><%= itemOfferUserFirstName%>&nbsp;<%= itemOfferUserLastName%></strong>
                                                                <div class="rating">
                                                                    <ul class="profileRating">
                                                                        <li><span class="card-text text-muted">@<%= itemOfferUserID%></span></li>
                                                                        <li>[&nbsp;<img class="ratingImage" src="images/profilerating/positive.png" /><span class="ratingValue"><%= itemOfferUserPositiveCount%></span></li>
                                                                        <li><img class="ratingImage" src="images/profilerating/neutral.png" /><span class="ratingValue"><%= itemOfferUserNeutralCount%></span></li>
                                                                        <li><img class="ratingImage" src="images/profilerating/negative.png" /><span class="ratingValue"><%= itemOfferUserNegativeCount%></span>&nbsp;]</li>
                                                                    </ul>
                                                                </div>
                                                                <p class="card-text mb-0 pt-3" style="font-size:12px;">
                                                                    Offer Status:&nbsp;
                                                                    <%  if(itemOfferStatus.equals("Accepted")) {   %>
                                                                    <span class="badge badge-info custom-badge arrowed-left <%= itemOfferStatus%>"><%= itemOfferStatus%></span>
                                                                    <%  } else if(itemOfferStatus.equals("Pending")) {  %>
                                                                    <span class="badge badge-success custom-badge arrowed-left <%= itemOfferStatus%>"><%= itemOfferStatus%></span>
                                                                    <%  } else if(itemOfferStatus.equals("Rejected")) { %>
                                                                    <span class="badge badge-danger custom-badge arrowed-left <%= itemOfferStatus%>"><%= itemOfferStatus%></span>
                                                                    <%  } else if(itemOfferStatus.equals("Negotiating")) { %>
                                                                    <span class="badge badge-warning custom-badge arrowed-left <%= itemOfferStatus%>"><%= itemOfferStatus%></span>
                                                                    <%  } else if(itemOfferStatus.equals("Completed")) { %>
                                                                    <span class="badge badge-primary custom-badge arrowed-left <%= itemOfferStatus%>"><%= itemOfferStatus%></span>
                                                                    <%  }   %>
                                                                </p>
                                                                <p class="card-text text-success mb-0 itemOfferPrice" style="font-size:16px;">
                                                                    <i class="fa fa-bullhorn"></i>&nbsp;&nbsp;<strong>$<%= itemOfferPrice%></strong>
                                                                </p>
                                                                <p class="card-text pt-2"><%= itemOfferDescription%></p>
                                                                <div class="rating">
                                                                    <ul class="offerAction">
                                                                        <li><button type="button" id="acceptOfferBtn<%= itemOfferID%>" class="btn btn-theme btn-sm itemOfferBtn">Accept</button></li>
                                                                        <li><button type="button" class="btn btn-theme btn-sm">Negotiate</button></li>
                                                                        <li><button type="button" id="rejectOfferBtn<%= itemOfferID%>" class="btn btn-theme btn-sm itemOfferBtn">Reject</button></li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <%      }   %>
                                                <%  }%>
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
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="unifyFooter"></div>
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
        <script src="js/unify/systemuser/basejs/qtip/jquery.qtip-v3.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/PendingItemOfferSYSJS.js" type="text/javascript"></script>

        <script src="js/unify/systemuser/basejs/jplist/jquery-ui.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.core.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.filter-dropdown-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.filter-toggle-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.history-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.jquery-ui-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.pagination-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.sort-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.textbox-filter.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.checkbox-dropdown.min.js"></script>
    </body>
</html>