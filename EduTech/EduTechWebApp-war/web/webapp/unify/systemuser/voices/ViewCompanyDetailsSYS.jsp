<%-- 
    Document   : ViewCompanyListingDetailsSYS
    Created on : 10 Mar, 2018, 4:29:44 PM
    Author     : Zhu Xinyi
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
        <title>Unify Company Review - Company Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/marketplace/NewItemListingSYSCSS.css" rel="stylesheet" type="text/css">
        
        <link href="css/unify/systemuser/weblayout/voices/ViewCompanyListingSYSCSS.css" rel="stylesheet" type="text/css">
    </head>

    <body class="nav-md">
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
                                <a class="nav-item nav-link d-none d-sm-block" href="#">Unify @ EduBox</a>
                                <a class="nav-item nav-link d-none d-sm-block" href="#"><i class="fa fa-phone"></i> +123-456-789</a>
                            </nav>
                            <ul class="nav">
                                <li class="nav-item d-none d-md-block">
                                    <a href="#" class="nav-link">
                                        <i class="fa fa-heart-o"></i>&nbsp;&nbsp;Likes
                                    </a>
                                </li>
                                <li class="nav-item d-none d-md-block">
                                    <a href="#" class="nav-link">
                                        <i class="fa fa-envelope"></i>&nbsp;&nbsp;Messages
                                    </a>
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
                            <li class="breadcrumb-item"><a href="ProfileSysUser?pageTransit=goToUnifyUserAccount">Unify Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Company Review (Company Details)</li>
                        </ol>
                    </nav>
                </div>
            </div>
            <%  Vector companyDetailsSYS = (Vector) request.getAttribute("companyDetailsSYS");
                String companyID,companyImage, companyName, companyIndustry, companyWebsite, companyHQ, companySize,
                       companyRating, companyDescription, companyAddress;
                companyID= companyImage = companyName = companyIndustry = companyWebsite = companyHQ = companySize =
                companyRating = companyDescription = companyAddress = "";
                
                if(companyDetailsSYS != null) {
                    companyID = String.valueOf(companyDetailsSYS.get(0));
                    companyImage = String.valueOf(companyDetailsSYS.get(1));
                    companyName = String.valueOf(companyDetailsSYS.get(2));
                    companyIndustry = String.valueOf(companyDetailsSYS.get(3));
                    companyWebsite = String.valueOf(companyDetailsSYS.get(4));
                    companyHQ = String.valueOf(companyDetailsSYS.get(5));
                    companySize = String.valueOf(companyDetailsSYS.get(6));
                    companyRating = String.valueOf(companyDetailsSYS.get(7));
                    companyDescription = String.valueOf(companyDetailsSYS.get(8));
                    companyAddress = String.valueOf(companyDetailsSYS.get(9));
                }
            %>
            <div class="container jplist mb-3" id="contentArea">
                <div class="row">
                    <div class="col-lg-1 col-md-3"></div>
                    <div class="col-lg-10 col-md-6">
                        <div class="card user-card">
                            <div class="card-body p-4 mb-3 mb-md-0 mb-xl-3">
                                <div class="row">
                                    <div class="col-lg-2">
                                        <div class="media">
                                            <img class="img-thumbnail" src="uploads/unify/images/voices/company/<%= companyImage%>" style="width:80px;height:80px;"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-7">
                                        <div class="company-name">
                                            <span class="card-title companyName" style="color: #333; font-size: 25px; line-height: 2.5;"><strong>Barclays</strong></span><br/>
                                        </div>
                                        <div><span class="float-left">Average Rating: <span class="ml-1 card-text rating companyRating" id="average_rating"><%= companyRating%></span></span></div>
                                            <div class="star-rating">
                                                <% double rating = Double.parseDouble(companyRating);
                                                   double percent = 0.0;
                                                    while(rating > 0) {
                                                        if(rating>=1) {
                                                            percent += 20;
                                                        } else {
                                                            percent += (((rating*13.93)/23.02)*100.0)*0.2;
                                                        }
                                                        rating = rating-1;
                                                    }%>
                                                <div class="star-rating-top" id="star_rating_top" style="width: <%= percent%>%">
                                                    <span class="vote-star"><i class="fa fa-star">&nbsp;</i></span>
                                                    <span class="vote-star"><i class="fa fa-star">&nbsp;</i></span>
                                                    <span class="vote-star"><i class="fa fa-star">&nbsp;</i></span>
                                                    <span class="vote-star"><i class="fa fa-star">&nbsp;</i></span>
                                                    <span class="vote-star"><i class="fa fa-star">&nbsp;</i></span>
                                                </div>
                                                <div class="star-rating-bottom">
                                                    <span><i class="fa fa-star">&nbsp;</i></span>
                                                    <span><i class="fa fa-star">&nbsp;</i></span>
                                                    <span><i class="fa fa-star">&nbsp;</i></span>
                                                    <span><i class="fa fa-star">&nbsp;</i></span>
                                                    <span><i class="fa fa-star">&nbsp;</i></span>
                                                </div>
                                            </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <button class="btn btn-outline btn-primary btn-sm btn-block"><i class="fa fa-list-alt">&nbsp;&nbsp;</i>Add A Review</button><br/>
                                        <button class="btn btn-outline btn-primary btn-sm btn-block"><i class="fa fa-list-alt">&nbsp;&nbsp;</i>Send Resume</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-3"></div>
                </div>
                <div class="row"><br/></div>
                <div class="row">
                    <div class="col-lg-1 col-md-3"></div>
                    <div class="col-lg-3 col-md-6">
                        <div class="collapse d-md-block pt-3 pt-md-0" id="collapseFilter">
                            <div class="filter-sidebar">
                                <div class="title"><span style="font-size: 15px"><strong>You may also like...</strong></span></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-7 col-md-6">
                        <div class="card user-card">
                            <div class="card-body p-4 mb-3 mb-md-0 mb-xl-3">
                                <div>
                                    <div class="tabbable tabbable-custom">
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a href="#companyInfo" data-toggle="tab">Overview</a></li>
                                            <li><a href="#reviewList" data-toggle="tab">Review List</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-1 col-md-3"></div>
                </div>
            </div>
            <!-- <div id="unifyFooter"></div> -->

            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
        </div>

        <!-- #1. jQuery v2.2.4 -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>

        <script src="js/unify/systemuser/webjs/voices/NewReviewSYSJS.js" type="text/javascript"></script>
    </body>
</html>
