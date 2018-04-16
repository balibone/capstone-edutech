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
        <title>Unify - My Errands Transaction Details</title>

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
        <link href="css/unify/systemuser/weblayout/userprofile/UserJobTransactionDetailsSYSCSS.css" rel="stylesheet" type="text/css" />
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
                            <li class="breadcrumb-item active" aria-current="page">My Errands Transaction Details</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div id="contentArea" class="container jplist" style="margin-bottom: 30px;">
                <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0; display: none;">
                    <button type="button" class="close" id="closeSuccess">&times;</button>
                    <span id="success"></span>
                </div>
                    
                <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0; display: none;">
                    <button type="button" class="close" id="closeError">&times;</button>
                    <span id="error"></span>
                </div>
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
                                <button type="button" class="list-group-item list-group-item-action marketplaceBtn active">
                                    <i class="fa fa-fw fa-shopping-cart"></i>&nbsp;My Marketplace
                                    <div class="pull-right"><i class="fa fa-fw fa-angle-double-right"></i></div>
                                </button>
                                <button type="button" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-suitcase"></i>&nbsp;My Errands
                                    <div class="pull-right"><i class="fa fa-fw fa-angle-double-right"></i></div>
                                </button>
                                <button type="button" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-comments"></i>&nbsp;My Whispers
                                    <div class="pull-right"><i class="fa fa-fw fa-angle-double-right"></i></div>
                                </button>
                                <button type="button" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-calendar"></i>&nbsp;My Events
                                    <div class="pull-right"><i class="fa fa-fw fa-angle-double-right"></i></div>
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-9 col-md-8">
                        
                        <div class="title"><span>My Errands Transaction Details</span></div>
                        <%
                            Vector jobTransDetailsSYSVec = (Vector) request.getAttribute("jobTransDetailsSYSVec");
                            String jobID, jobTitle, jobCategoryName, jobRateType, jobRate, jobDescription, jobImage, jobStatus, jobNumOfLikes, jobLikeStatus;
                            jobID = jobTitle = jobCategoryName = jobRateType = jobRate = jobDescription = jobImage = jobStatus = jobNumOfLikes = jobLikeStatus = "";

                            String jobPostingDate, startLocation, startLat, startLong, endLocation, endLat, endLong, otherInformation, jobPosterID, jobPosterImage, jobPosterJoinDate;
                            jobPostingDate = startLocation = startLat = startLong = endLocation = endLat = endLong = otherInformation = jobPosterID = jobPosterImage = jobPosterJoinDate = "";

                            String jobTransactionDate, jobTakerID, jobTakerImage, jobTakerJoinDate, jobTransactionRate;
                            jobTransactionDate = jobTakerID = jobTakerImage = jobTakerJoinDate = jobTransactionRate = "";

                            String jobPosterPositive, jobPosterNeutral, jobPosterNegative, jobTakerPositive, jobTakerNeutral, jobTakerNegative, jobTransactionID;
                            jobPosterPositive = jobPosterNeutral = jobPosterNegative = jobTakerPositive = jobTakerNeutral = jobTakerNegative = jobTransactionID = "";

                            if (jobTransDetailsSYSVec != null) {
                                /* JOB INFORMATION */
                                jobID = (String.valueOf(jobTransDetailsSYSVec.get(0)));
                                jobTitle = (String) jobTransDetailsSYSVec.get(1);
                                jobCategoryName = (String) jobTransDetailsSYSVec.get(2);
                                jobRateType = (String.valueOf(jobTransDetailsSYSVec.get(3)));
                                jobRate = (String) jobTransDetailsSYSVec.get(4);
                                jobDescription = (String) jobTransDetailsSYSVec.get(5);
                                jobImage = (String) jobTransDetailsSYSVec.get(6);
                                jobStatus = (String) jobTransDetailsSYSVec.get(7);
                                jobNumOfLikes = (String.valueOf(jobTransDetailsSYSVec.get(8)));
                                jobLikeStatus = (String.valueOf(jobTransDetailsSYSVec.get(9)));
                                jobPostingDate = (String.valueOf(jobTransDetailsSYSVec.get(10)));
                                /* TRADE INFORMATION */
                                startLocation = (String) jobTransDetailsSYSVec.get(11);
                                startLat = (String) jobTransDetailsSYSVec.get(12);
                                startLong = (String) jobTransDetailsSYSVec.get(13);
                                endLocation = (String) jobTransDetailsSYSVec.get(14);
                                endLat = (String) jobTransDetailsSYSVec.get(15);
                                endLong = (String) jobTransDetailsSYSVec.get(16);
                                otherInformation = (String) jobTransDetailsSYSVec.get(17);
                                /* JOB POSTER INFORMATION */
                                jobPosterID = (String.valueOf(jobTransDetailsSYSVec.get(18)));
                                jobPosterImage = (String) jobTransDetailsSYSVec.get(19);
                                jobPosterJoinDate = (String.valueOf(jobTransDetailsSYSVec.get(20)));
                                jobPosterPositive = (String.valueOf(jobTransDetailsSYSVec.get(21)));
                                jobPosterNeutral = (String.valueOf(jobTransDetailsSYSVec.get(22)));
                                jobPosterNegative = (String.valueOf(jobTransDetailsSYSVec.get(23)));
                                /* JOB TRANSACTION + JOB TAKER INFORMATION */
                                jobTransactionDate = (String.valueOf(jobTransDetailsSYSVec.get(24)));
                                jobTakerID = (String.valueOf(jobTransDetailsSYSVec.get(25)));
                                jobTakerImage = (String) jobTransDetailsSYSVec.get(26);
                                jobTakerJoinDate = (String.valueOf(jobTransDetailsSYSVec.get(27)));
                                jobTakerPositive = (String.valueOf(jobTransDetailsSYSVec.get(28)));
                                jobTakerNeutral = (String.valueOf(jobTransDetailsSYSVec.get(29)));
                                jobTakerNegative = (String.valueOf(jobTransDetailsSYSVec.get(30)));
                                jobTransactionRate = (String.valueOf(jobTransDetailsSYSVec.get(31)));
                                jobTransactionID = (String.valueOf(jobTransDetailsSYSVec.get(32)));
                            }
                        %>
                        <form class="form-horizontal" action="ErrandsSysUser" method="POST">
                            <div class="formDiv">
                                <div class="form-row media mb-2">
                                    <img class="img-thumbnail" src="uploads/unify/images/errands/job/<%= jobImage%>" style="width:50px;height:50px;"/>
                                    <div class="media-body ml-3">
                                        <input type="hidden" id="hiddenJobID" value="<%= jobID%>" />
                                        <h5 class="user-name"><strong><%= jobTitle%></strong></h5>
                                        <p class="card-text mb-0">$<%= jobRate%>/<%= jobRateType%></p>
                                    </div>
                                    <div class="media-body ml-3">
                                        <% if(loggedInUsername.equals(jobPosterID)){%>
                                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#reviewToTaker">Leave feedback to the taker! </button>
                                        <%}else{%>
                                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#reviewToPoster">Leave feedback to the poster!</button>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="formDiv">
                                <div class="form-row" style="padding: 20px 0 30px 0;">
                                    <ul class="nav nav-tabs w-100" role="tablist">
                                        <li class="nav-item"><a class="nav-link text-default active" id="tradeInfo-tab" data-toggle="tab" href="#tradeInfoPane" role="tab" aria-controls="tradeInfoPane" aria-selected="true">Job Information</a></li>
                                        <li class="nav-item"><a class="nav-link text-default" id="transactionInfo-tab" data-toggle="tab" href="#transactionInfoPane" role="tab" aria-controls="transactionInfoPane" aria-selected="false">Transaction Information</a></li>
                                    </ul>
                                    <div class="tab-content w-100">
                                        <div class="tab-pane border border-top-0 p-3 show active" id="tradeInfoPane" role="tabpanel" aria-labelledby="tradeInfo-tab">
                                            <table class="table table-bordered mb-0">
                                                <tbody>
                                                    <tr>
                                                        <td class="bg-light w-25">Poster Information</td>
                                                        <td>
                                                            <div class="media mb-2">
                                                                <div class="mr-2">
                                                                    <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= jobPosterImage%>" style="width:50px;height:50px;"  />
                                                                </div>
                                                                <div class="media-body col-md-12">
                                                                    <h5 class="sellerInfo"><%= jobPosterID%></h5>
                                                                    <input type="hidden" id="posterID" value="<%= jobPosterID%>">
                                                                    Joined on <%= jobPosterJoinDate%><br/>
                                                                    <hr/>
                                                                    <div class="rating">
                                                                        <ul class="profileRating">
                                                                            <li><img class="ratingImage" src="images/profilerating/positive.png" /><span class="ratingValue"><%= jobPosterPositive%></span></li>
                                                                            <li><img class="ratingImage" src="images/profilerating/neutral.png" /><span class="ratingValue"><%= jobPosterNeutral%></span></li>
                                                                            <li><img class="ratingImage" src="images/profilerating/negative.png" /><span class="ratingValue"><%= jobPosterNegative%></span></li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Job Category</td>
                                                        <td><%= jobCategoryName%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Job Description</td>
                                                        <td><%= jobDescription%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Job Posted Date</td>
                                                        <td><%= jobPostingDate%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Start Location</td>
                                                        <td>
                                                            <input type="hidden" id="startLocation" value="<%= startLocation%>" />
                                                            <input type="hidden" id="startLat" value="<%= startLat%>" />
                                                            <input type="hidden" id="startLong" value="<%= startLong%>" />
                                                            <%= startLocation%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">End Location</td>
                                                        <td>
                                                            <input type="hidden" id="endLocation" value="<%= endLocation%>" />
                                                            <input type="hidden" id="endLat" value="<%= endLat%>" />
                                                            <input type="hidden" id="endLong" value="<%= endLong%>" />
                                                            <%= endLocation%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Work Location Map</td>
                                                        <td><div id="mapdiv" style="width: auto; height: 300px;"></div></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Other Information</td>
                                                        <td><%= otherInformation%></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="tab-pane border border-top-0 p-3" id="transactionInfoPane" role="tabpanel" aria-labelledby="transactionInfo-tab">
                                            <table class="table table-bordered mb-0">
                                                <tbody>
                                                    <tr>
                                                        <td class="bg-light w-25">Taker Information</td>
                                                        <td>
                                                            <div class="media mb-2">
                                                                <div class="mr-2">
                                                                    <img class="img-thumbnail" src="uploads/commoninfrastructure/admin/images/<%= jobTakerImage%>" style="width:50px;height:50px;" />
                                                                </div>
                                                                <div class="media-body col-md-12">
                                                                    <h5 class="sellerInfo"><%= jobTakerID%></h5>
                                                                    <input type="hidden" id="takerID" value="<%= jobTakerID%>">
                                                                    Joined on <%= jobTakerJoinDate%><br/>
                                                                    <hr/>
                                                                    <div class="rating">
                                                                        <ul class="profileRating">
                                                                            <li><img class="ratingImage" src="images/profilerating/positive.png" /><span class="ratingValue"><%= jobTakerPositive%></span></li>
                                                                            <li><img class="ratingImage" src="images/profilerating/neutral.png" /><span class="ratingValue"><%= jobTakerNeutral%></span></li>
                                                                            <li><img class="ratingImage" src="images/profilerating/negative.png" /><span class="ratingValue"><%= jobTakerNegative%></span></li>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Job Transaction Date
                                                            <input type="hidden" id="jobTransID" value="<%= jobTransactionID%>">
                                                        </td>
                                                        <td><%= jobTransactionDate%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="bg-light w-25">Job Transaction Price</td>
                                                        <td>$<%= jobTransactionRate%>/<%= jobRateType%></td>
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
            <div id="sellNewItem-iframe"></div>
            
            <div id="modal-custom">
                <button data-iziModal-close class="icon-close"><i class="fa fa-times"></i></button>
                <div class="sections">
                    <section>
                        <p class="text-center"><strong>Select one of the following to view.</strong></p>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location='ProfileSysUser?pageTransit=goToUnifyUserAccountSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-shopping-cart display-2"></i></h1>
                                        <h6>My Listings</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location='ProfileSysUser?pageTransit=goToMarketplaceTransSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-book display-2"></i></h1>
                                        <h6>My Transactions</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location='ProfileSysUser?pageTransit=goToUserItemWishlistSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-heart display-2"></i></h1>
                                        <h6>My Wishlist</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location='ProfileSysUser?pageTransit=goToMyBuyerOfferListSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-edit display-2"></i></h1>
                                        <h6>My Offers</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location='ProfileSysUser?pageTransit=goToPendingItemOfferListSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-bullhorn display-2"></i></h1>
                                        <h6>Marketplace Offers</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
            
            <div class="modal fade" id="reviewToTaker" tabindex="-1" role="dialog" aria-labelledby="reviewToTaker" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle">How was your experience?</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <div class="form-group">
                    <label for="reviewRating">Rate your experience: </label>
                    <select class="form-control" id="reviewRatingToTaker">
                      <option>Positive</option>
                      <option>Neutral</option>
                      <option>Negative</option>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="reviewContent">Describe your experience: </label>
                    <textarea class="form-control" id="reviewContentToTaker" rows="3"></textarea>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" id="reviewToTakerBtn">Leave Feedback</button>
                </div>
              </div>
            </div>
          </div>
            
          <div class="modal fade" id="reviewToPoster" tabindex="-1" role="dialog" aria-labelledby="reviewToPoster" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLongTitle">How was your experience?</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <div class="form-group">
                    <label for="reviewRating">Rate your experience: </label>
                    <select class="form-control" id="reviewRatingToPoster">
                      <option>Positive</option>
                      <option>Neutral</option>
                      <option>Negative</option>
                    </select>
                  </div>
                  <div class="form-group">
                    <label for="reviewContent">Describe your experience: </label>
                    <textarea class="form-control" id="reviewContentToPoster" rows="3"></textarea>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" id="reviewToPosterBtn">Leave Feedback</button>
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
        <script src="js/unify/systemuser/webjs/userprofile/UserJobTransactionDetailsSYSJS.js" type="text/javascript"></script>
    </body>
</html>
