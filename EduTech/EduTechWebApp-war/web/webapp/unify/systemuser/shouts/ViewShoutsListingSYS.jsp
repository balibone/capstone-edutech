<%@page import="java.util.Date"%>
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
        <title>Unify Shouts!</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/shouts/ViewShoutsListingSYSCSS.css" rel="stylesheet" type="text/css">

        <link href="css/unify/systemuser/baselayout/jplist/jquery-ui.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/jplist/jplist.core.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.filter-toggle-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.pagination-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.history-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.textbox-filter.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.jquery-ui-bundle.min.css" rel="stylesheet" type="text/css" />
        <style>
            .star-rating {
                unicode-bidi: bidi-override;
                width: 120px;
                color: #ddd;
                font-size: 0;
                position: relative;
                display: table;
                text-shadow: 0px 1px 0 #a2a2a2;
            }
            .star-rating span {
                padding: 3px;
                font-size: 15px;
            }

            .star-rating-top {
                color: #FFD700;
                padding: 0;
                position: absolute;
                z-index: 1;
                display: block;
                top: 0;
                left: 0;
                overflow: hidden;
                white-space: nowrap;
            }

            .star-rating-bottom {
                padding: 0;
                display: block;
                z-index: 0;
            }
        </style>   

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
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#shoutsSub" role="button" aria-expanded="false" aria-controls="shoutsSub"><i class="fa fa-fw fa-bullhorn"></i>&nbsp;Shouts!</a>
                        <div id="shoutsSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="ShoutsSysUser?pageTransit=goToViewShoutsListingSYS">Shouts!</a></div>
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
                                    <a href="ProfileSysUser?pageTransit=goToViewChatListSYS&assocItemID=" class="nav-link"><i class="fa fa-comment"></i>&nbsp;&nbsp;My Chats</a>
                                </li>
                                <li class="nav-item d-none d-md-block">
                                    <div class="dropdown-container">
                                        <a href="#" class="nav-link" id="dropdown-cart" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: block;">
                                            <i class="fa fa-bell"></i>&nbsp;&nbsp;Notifications
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-cart" aria-labelledby="dropdown-cart">
                                            <%                                                ArrayList<Vector> userMessageListTopThreeSYS = (ArrayList) request.getAttribute("userMessageListTopThreeSYS");
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
                                            <%  }%>
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
                                        <option value="shouts">Shouts</option>
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
                                <%-- deleted
                                <a class="btn btn-outline-theme" href="MarketplaceSysUser?pageTransit=goToNewItemListingSYS" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Sell An Item
                                </a>
                                <a class="btn btn-outline-theme" href="ProfileSysUser?pageTransit=goToUserAccount" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Post A Job
                                </a>
                                --%>
                                <a OnClick="newShout()"><button type="button" class="btn btn-outline-theme center"><i class="fa fa-bullhorn"></i>&nbsp;&nbsp;Create A New Shout</button></a>   
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
                            <li class="breadcrumb-item active" aria-current="page"><a href="ShoutsSysUser?pageTransit=goToViewShoutsListingSYS&loggedInUsername=<%=loggedInUsername%>">Shouts Listing</a></li>
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
                <%  }   %>
                <div class="row">
                    <div class="col-lg-3 col-md-4 mb-3 jplist-search">
                        <button class="btn btn-outline-theme btn-block dropdown-toggle d-md-none" type="button" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="false" aria-controls="collapseFilter">
                            Search Filter
                        </button>
                        <div class="collapse d-md-block pt-3 pt-md-0" id="collapseFilter">
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-12">

                                    <%-- search by shout content --%>
                                    <div class="filter-sidebar">
                                        <div class="title"><span>Search Filter</span></div>
                                        <div class="subtitle" style="margin-bottom: 5px">Search Shout Content</div>
                                        <input type="text" data-path=".shoutContent" class="form-control" placeholder="Enter keyword" 
                                               aria-label="Search Content" data-control-type="textbox" id="name-filter"
                                               data-control-name="content-text-filter" data-control-action="filter" />
                                    </div>

                                    <%-- search by shout user *removed - to keep anonymity
                                    <div class="filter-sidebar">
                                        <div class="subtitle" style="margin-bottom: 5px">Shout User</div>
                                        <input type="text" data-path=".shoutUsername" class="form-control" placeholder="Search User" 
                                               aria-label="Search User" data-control-type="textbox" id="user-filter"
                                               data-control-name="user-text-filter" data-control-action="filter" />
                                    </div>
                                    --%>

                                    <%-- additional filters --%>
                                </div>
                            </div>
                        </div>
                        <%-- deleted
                        <a OnClick="newShout()"><button type="button" class="btn btn-outline-info center"><i class="fa fa-bullhorn"></i>&nbsp;&nbsp;Create A New Shout</button></a>   
                        --%>
                    </div>

                    <div class="col-lg-9 col-md-8">
                        <div class="title"><span>Shouts Listing</span></div>
                        <div class="jplist-search sorting-bar">

                            <div class="dateSort jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                 data-control-type="sort-drop-down" data-control-name="sort" data-control-action="sort"
                                 data-datetime-format="{year}-{month}-{day} {hour}:{min}:{sec}">
                                <ul>
                                    <li><span data-path=".shoutDate" data-order="desc" data-type="datetime" data-default="true">Newest Post First</span></li>
                                    <li><span data-path=".shoutDate" data-order="asc" data-type="datetime">Oldest Post First</span></li>
                                    <li><span data-path=".shoutLike" data-order="desc" data-type="number">Most Liked Post</span></li>
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
                            <div class="jplist-panel">
                                <button type="button" class="jplist-reset-btn" data-control-type="reset" 
                                        data-control-name="reset" data-control-action="reset"><i class="fa fa-retweet">&nbsp;&nbsp;Reset</i>
                                </button>
                            </div>
                            <%-- removed
                            <div class="jplist-panel">
                                <a OnClick="newShout()"><button type="button" class="btn btn-outline-info center"><i class="fa fa-plus"></i>&nbsp;&nbsp;New Shout</button></a>
                            </div>
                            --%>
                        </div>

                        <!-- SHOUTS LISTING -->
                        <div id="shoutsListing" class="row equal-height" add-class-on-xs="no-gutters">
                            <div class="list searchresult-row">
                                <%
                                    ArrayList<Vector> shoutsListSYS = (ArrayList) request.getAttribute("shoutsListSYS");
                                    if (!shoutsListSYS.isEmpty()) {
                                        for (int i = 0; i <= shoutsListSYS.size() - 1; i++) {
                                            Vector v = shoutsListSYS.get(i);
                                            String shoutID = String.valueOf(v.get(0));
                                            String shoutDate = String.valueOf(v.get(1));
                                            String shoutStatus = String.valueOf(v.get(2));
                                            String shoutContent = String.valueOf(v.get(3));
                                            String shoutLat = String.valueOf(v.get(4));
                                            String shoutLong = String.valueOf(v.get(5));
                                            String shoutUsername = String.valueOf(v.get(6));
                                            String shoutEditedDate = String.valueOf(v.get(7));
                                            String shoutDuration = String.valueOf(v.get(8));
                                            String shoutLikes = String.valueOf(v.get(9));
                                            String shoutComments = String.valueOf(v.get(10));
                                            String shoutBookmarkStatus = String.valueOf(v.get(11));
                                            String shoutLikeStatus = String.valueOf(v.get(12));
                                %>
                                <div class="col-xl-6 col-md-6 col-6 d-block d-lg-none d-xl-block list-item">
                                    <div class="card card-product">
                                        <div class="card-content">
                                            <div class="card-body">
                                                <div class="row">

                                                    <div class="col-xl-8 col-md-8 col-8">
                                                        <%-- removed
                                                        <div class="shout-ID">
                                                            <span class="card-header shoutCategory" style="color: #007bff; font-size: 10px; line-height: 2.5;">Categories, Type</span>
                                                        </div>
                                                        
                                                        
                                                        
                                                        <div class="shout-ID">
                                                            <span class="card-title shoutID" style="color: #2b3233; font-size: 25px; line-height: 2.5;"><strong>#<%= shoutID%></strong></span>
                                                        </div>
                                                        --%>

                                                        <%
                                                            if (shoutUsername.equals(request.getAttribute("loggedInUsername"))) {
                                                        %>

                                                        <div class="shout-user-info">
                                                            <span class="card-header shoutUsername" style="font-size: 12px">Posted by you</span>
                                                        </div>

                                                        <%  }%>

                                                        <div class="shout-content">
                                                            <span class="card-content shoutContent" style="line-height: 2.0; color: #2b3233; font-size: 18px;"><%= shoutContent%></span>
                                                        </div>
                                                        <br>
                                                        <%-- like & comment --%>
                                                        <div class="shout-likes-info">
                                                            <span class="card-text shoutLike" style="font-size: 12px"><%= shoutLikes%> likes &nbsp;
                                                            </span>
                                                            <%
                                                                if (shoutLikeStatus.equals("false")) {
                                                            %>
                                                            <a onClick ="likeAlert('<%= shoutID%>,<%= loggedInUsername%>')" class="likeThis">
                                                                <i class="fa fa-thumbs-up icon"></i>
                                                                <i class="fa fa-thumbs-o-up icon"></i>
                                                            </a>
                                                            <%
                                                            } else if (shoutLikeStatus.equals("true")) {
                                                            %>
                                                            <a onClick ="unlikeAlert('<%= shoutID%>,<%= loggedInUsername%>')" class="likedThis">
                                                                <i class="fa fa-thumbs-up icon"></i>
                                                                <i class="fa fa-thumbs-o-up icon"></i>
                                                            </a>
                                                            <%  }%>   
                                                            &nbsp;&nbsp;
                                                            <i class="viewComment">
                                                                <a onClick ="viewComment('<%= shoutID%>,<%= shoutContent%>')" textDecoration = "underline"><span class="float-none" style="color: #64676d; font-size: 12px"><%= shoutComments%> comments &nbsp;</span>
                                                                    <i class="fa fa-comment icon"></i>
                                                                    <i class="fa fa-comment-o icon"></i>
                                                                </a>
                                                            </i>
                                                        </div>
                                                    </div>
                                                    <div class="col-xl-4 col-md-4 col-4 float-right">
                                                        <div class="bookmark-Shout">
                                                            <%

                                                                if (shoutBookmarkStatus.equals("false")) {
                                                            %>
                                                            <span class = "float-right"><a onClick ="bookmarkAlert('<%= shoutID%>,<%= loggedInUsername%>')" class="bookmarkThis">
                                                                    <i class="fa fa-bookmark icon"></i>
                                                                    <i class="fa fa-bookmark-o icon"></i>
                                                                    <span class="bookmark" style="color: #64676d; font-size: 12px">Bookmark This</span>
                                                                </a></span>
                                                                <%

                                                                } else if (shoutBookmarkStatus.equals("true")) {
                                                                %>     
                                                            <span class = "float-right"><a onClick ="unbookmarkAlert('<%= shoutID%>,<%= loggedInUsername%>')" class="bookmarkedThis">
                                                                    <i class="fa fa-bookmark icon"></i>
                                                                    <i class="fa fa-bookmark-o icon"></i>
                                                                    <span class="bookmark" style="color: #64676d; font-size: 12px">Bookmarked</span>
                                                                </a></span>
                                                                <%  }%>
                                                        </div>

                                                        <%-- updated with delete-Shout2
                                                        <div class="delete-Shout">
                                                            <%

                                                                if (shoutUsername.equals(request.getAttribute("loggedInUsername"))) {
                                                            %>
                                                            <span class = "float-right"><a href = "ShoutsSysUser?pageTransit=goToDeleteShoutSYS&hiddenUsername=<%=loggedInUsername%>&hiddenShoutID=<%=shoutID%>" onclick="return confirm('Confirm delete?')" class="deleteThis">
                                                                    <i class="fa fa-trash icon"></i>
                                                                    <i class="fa fa-trash-o icon"></i>
                                                                    <span class="float-none shoutDelete" style="color: #64676d; font-size: 12px">&nbsp;Delete Shout</a></span>
                                                                    <%  }%>
                                                        </div>
                                                        --%>

                                                        <div class="delete-Shout2">
                                                            <%
                                                                if (shoutUsername.equals(request.getAttribute("loggedInUsername"))) {
                                                            %>
                                                            <span class = "float-right"><a onClick = "deleteAlert(<%=shoutID%>)" class="deleteThis">
                                                                    <i class="fa fa-trash icon"></i>
                                                                    <i class="fa fa-trash-o icon"></i>
                                                                    <span class="float-none shoutDelete" style="color: #64676d; font-size: 12px">&nbsp;Delete Shout</a></span>
                                                                    <%  }%>
                                                        </div>
                                                        <%-- bookmark alert in iframe 
                                                            <div class="bookmark-Shout">
                                                            <span class = "float-right"><a onClick ="bookmarkFrame('<%= shoutID%>,<%=loggedInUsername%>')"><i class="fa fa-bookmark">&nbsp;&nbsp;</i><span class="bookmark" style="color: #64676d; font-size: 12px">Bookmark This</span></a></span>
                                                        </div>
                                                        --%>

                                                    </div> 

                                                </div>
                                            </div>
                                        </div>

                                        <%-- card footer --%>
                                        <div class="card-footer text-muted mt-1">
                                            <%--
                                            <%
                                                                if (shoutUsername.equals(request.getAttribute("loggedInUsername"))) {
                                                            %>
                                            
                                            <div class="shout-user-info">
                                                <i class="fa fa-users" style="color: #64676d">&nbsp;</i><span class="card-text shoutUsername" style="font-size: 12px">Posted by you</span>
                                            </div>
                                            
                                            <%  }%>
                                            --%>
                                            <div class="shout-post-date">
                                                <i class="fa fa-clock-o">&nbsp;</i><span class="float-none shoutDuration" style="color: #64676d; font-size: 12px">Posted <%= shoutDuration%>
                                                    <%--    on <%= shoutDate%> --%>
                                                </span>

                                                <span class="float-none shoutDate" hidden="true" style="color: #64676d; font-size: 12px"><%= shoutDate%>
                                                </span>

                                                <span class = "float-right"><a onClick ="reportShout('<%= shoutID%>,<%= shoutContent%>')" class="reportThis">
                                                        <i class="fa fa-flag icon"></i>
                                                        <i class="fa fa-flag-o icon"></i>
                                                        <span class="report" style="color: #64676d; font-size: 12px">Report Post</span>
                                                    </a></span>

                                            </div>

                                        </div>

                                    </div>
                                </div>
                                <%      }   %>
                                <%  }%>
                            </div>
                        </div>
                        <%-- end of card contents --%>
                        <div class="box jplist-no-results text-shadow align-center">
                            <p><strong>No results found. Please refine your search.</strong></p>
                        </div>
                        <div class="jplist-search">
                            <div class="jplist-label" data-type="Displaying {end} of all {all} results" 
                                 data-control-type="pagination-info" data-control-name="paging" data-control-action="paging">
                            </div>

                            <div class="jplist-label" data-type="Page {current} of {pages}" 
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

            <!-- <div id="unifyFooter"></div> -->
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="itemcard-iframe"></div>
        </div>

        <div id="reportShout-iframe"></div>                    
        <div id="viewComment-iframe"></div>
        <div id="newShout-iframe"></div>
        <div id="deleteShout-alert"></div> 
        <div id="bookmark-alert"></div> 
        <div id="unbookmark-alert"></div> 
        <div id="like-alert"></div>
        <div id="unlike-alert"></div> 


        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/shouts/ViewShoutsListingSYSJS.js" type="text/javascript"></script>

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
