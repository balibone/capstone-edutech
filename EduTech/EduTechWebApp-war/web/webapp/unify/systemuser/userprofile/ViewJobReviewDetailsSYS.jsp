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
        <title>Unify Errands - Job Review Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/baselayout/qtip/jquery.qtip-v3.0.3.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/unify/systemuser/weblayout/errands/ViewJobDetailsSYSCSS.css" rel="stylesheet" type="text/css"/>

        <link href="css/unify/systemuser/weblayout/errands/ViewJobOfferDetailsSYSCSS" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="css/unify/systemuser/weblayout/errands/datatables.min.css"/>

        <link href="css/unify/systemuser/baselayout/jplist/jquery-ui.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/jplist/jplist.core.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.filter-toggle-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.pagination-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.history-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.textbox-filter.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.jquery-ui-bundle.min.css" rel="stylesheet" type="text/css" />

        <!--<link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css" />-->
        <link href="css/unify/admin/baselayout/UnifyAdminPlugins.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/plugin/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/admin/baselayout/icons.css" rel="stylesheet" type="text/css" />

    </head>
    <body onload="loadMap()">
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
                            <li class="breadcrumb-item active" aria-current="page">Job Offers (Details)</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="row justify-content-center" >
                <div class="col-lg-3 col-md-3 ">
                    <div id="contentArea" class="container jplist">
                        <div class="jplist-search">
                            <div class="title"><span>Job List</span></div>
                            <input type="text" data-path=".jobTitle" class="form-control" placeholder="Search Job ..." 
                                   aria-label="Search Job ..." data-control-type="textbox" 
                                   data-control-name="transmission-text-filter" data-control-action="filter" />
                        </div>
                        <br/>
                        <div id="itemListing" class="row equal-height" add-class-on-xs="no-gutters">
                            <div class="list searchresult-row">
                                <%
                                    ArrayList<Vector> jobList = (ArrayList) request.getAttribute("jobListSYS");
                                    if (jobList.size() != 0) {
                                        for (int i = 0; i <= jobList.size() - 1; i++) {
                                            Vector v = jobList.get(i);

                                            String jobID = String.valueOf(v.get(0));
                                            String jobImage = String.valueOf(v.get(1));
                                            String jobTitle = String.valueOf(v.get(2));
                                            String jobCategoryName = String.valueOf(v.get(3));
                                            String jobPosterName = String.valueOf(v.get(4));
                                            //String jobPostDate = String.valueOf(v.get(5));
                                            //String jobWorkDate = String.valueOf(v.get(6));
                                            //String startLocation = String.valueOf(v.get(7));
                                            String jobRateType = String.valueOf(v.get(8));
                                            String jobRate = String.valueOf(v.get(9));
                                            //String numOfLikes = String.valueOf(v.get(10));
%>
                                <div class="col-xl-12 col-md-12 col-12 d-block d-lg-none d-xl-block list-item">
                                    <div class="card card-product">
                                        <div class="card-body">
                                            <div class="row">
                                                <%--<a href="ErrandsSysUser?pageTransit=goToViewJobOfferDetails&hiddenUserName=<%= jobPosterName%>&jobID=<%= jobID%>">--%>

                                                <div class="col-xl-4 col-md-4 col-4">
                                                    <img src="uploads/unify/images/errands/job/<%= jobImage%>" style="height: 70px; width: 70px;">
                                                </div>

                                                <div class="col-xl-8 col-md-8 col-8">
                                                    <a href="ProfileSysUser?pageTransit=goToViewJobReviewDetails&jobID=<%= jobID%>">
                                                        <span class="jobTitle"><strong><%= jobTitle%></strong></span>
                                                    </a>
                                                    <p class="card-text"><%= jobCategoryName%></p>
                                                    <p class="card-text">S$<%= jobRate%>/<%= jobRateType%></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>

                        <div class="box jplist-no-results text-shadow align-center">
                            <p><strong>No results found. Please refine your search.</strong></p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7 col-md-7 ml-1">
                    <div class="title"><span>Job Info</span></div>
                    <%
                        ArrayList<Vector> reviewList = (ArrayList) request.getAttribute("jobReviewList");
                        if (reviewList.size() != 0) {
                            Vector jobInfo = (Vector) reviewList.get(0);

                            long jobID = (Long) jobInfo.get(0);
                            String jobTitle = (String) jobInfo.get(1);
                            String jobImg = (String) jobInfo.get(2);
                            String jobRate = (String) jobInfo.get(3);
                            String jobRateType = (String) jobInfo.get(4);
                            String category = (String) jobInfo.get(5);
                            int numOfHelpers = (Integer) jobInfo.get(6);
                            String jobStatus = (String) jobInfo.get(7);
                    %>
                    <div class="row">
                        <div class="col-xl-4 col-lg-5 col-md-6">
                            <img src="uploads/unify/images/errands/job/<%= jobImg%>" class="img-fluid mb-2 border w-100 image-detail" style="cursor: pointer; height: 180px;">
                        </div>
                        <div class="col-xl-8 col-lg-7 col-md-6" >
                            <span style="font-size: 20px; line-height: 24px;"><strong><%= jobTitle%></strong></span><br/>
                            <span style="font-size: 16px;"><i class="fa fa-anchor" aria-hidden="true"></i>&nbsp;&nbsp; Status: <%= jobStatus%></span><br/>
                            <span style="font-size: 16px;"><i class="fa fa-book" aria-hidden="true"></i>&nbsp;&nbsp; Category: <%= category%></span><br/>
                            <span style="font-size: 16px;"><i class="fa fa-users" aria-hidden="true"></i>&nbsp;&nbsp; Helpers required: <%= numOfHelpers%></span><br/>
                            <span style="font-size: 16px;"><i class="fa fa-tag" aria-hidden="true"></i>&nbsp;&nbsp; Job Rate: S$<%= jobRate%>/<%= jobRateType%></span><br/><br/>
                            <a role="button" href="ErrandsSysUser?pageTransit=goToViewJobDetailsSYS&hiddenJobID=<%= jobID%>&hiddenCategoryName=<%= category%>" class="btn btn-primary" >View Job Details</a>
                        </div>


                        <div id="offerList">    
                            <div class="col-12 mt-5">
                                <div class="list">
                                    <table class="table table-striped" id="offer-table" style="font-size: 13px;">
                                        <col width="120">
                                        <col width="70">
                                        <col width="130">
                                        <col width="55">
                                        <thead>
                                            <tr>
                                                <th scope="col">The Review is Given by</th>
                                                <th scope="col">Rating</th>
                                                <th scope="col">Review Content</th>
                                                <th scope="col">Review Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (int i = 1; i < reviewList.size(); i++) {
                                                    Vector reviewDetails = (Vector) reviewList.get(i);

                                                    String username = (String) reviewDetails.get(0);
                                                    String firstName = (String) reviewDetails.get(1);
                                                    String lastName = (String) reviewDetails.get(2);
                                                    String userImg = (String) reviewDetails.get(3);
                                                    String rating = (String) reviewDetails.get(4);
                                                    String reviewContent = (String) reviewDetails.get(5);
                                                    String reviewDate = (String.valueOf(reviewDetails.get(6)));
                                                    long jobTransID = (Long) reviewDetails.get(7);
                                            %>

                                            <tr class="list-item">

                                                <td>
                                                    <div class="row">
                                                        <div class="col-2">
                                                            <img src="uploads/commoninfrastructure/admin/images/<%= userImg%>" style="width:35px; height:35px;"/>
                                                        </div>
                                                        <div class="col-10">
                                                            <span class="senderName"><strong><%= username%></strong></span><br/>
                                                            <span class="senderName"><%= firstName%> <%= lastName%></span>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td><span class="offerPrice"><%= rating%></span></td>
                                                <td><%= reviewContent%></td>
                                                <td><%= reviewDate%></td>
                                            </tr>


                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table> 
                                </div>
                            </div><br/>

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
        <div id="marketplace-overlay"></div>
        <div id="sellNewItem-iframe"></div>

        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/qtip/jquery.qtip-v3.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/errands/ViewJobOfferDetailsSYSJS.js" type="text/javascript"></script>

        <script src="js/unify/systemuser/webjs/errands/datatables.min.js" type="text/javascript"></script>

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
