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
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/voices/ViewCompanyDetailsSYSCSS.css" rel="stylesheet" type="text/css">
        
        <link href="css/unify/systemuser/baselayout/jplist/jquery-ui.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/jplist/jplist.core.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.filter-toggle-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.pagination-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.history-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.textbox-filter.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.jquery-ui-bundle.min.css" rel="stylesheet" type="text/css" />
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
                                            <img class="img-thumbnail" src="uploads/unify/images/voices/company/<%= companyImage%>" style="width:120px;height:120px; margin-left: 10px; margin-top: 10px;"/>
                                        </div>
                                    </div>
                                    <div class="col-lg-7">
                                        <div class="company-name">
                                            <span class="card-title companyName" style="color: #333; font-size: 25px; line-height: 2.5;"><strong><%= companyName%></strong></span><br/>
                                        </div>
                                        <div class="company-industry">
                                            <label><strong>Industry:&nbsp;</strong></label>
                                            <span class="companyIndustry"><%= companyIndustry%></span><br/>
                                        </div>
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
                                        <div class="new-review">
                                            <form action="VoicesSysUser" method="POST">
                                                <input type="hidden" name="pageTransit" value="goToNewReviewSYS"/>
                                                <input type="hidden" name="hiddenCompanyImage" value="<%= companyImage %>"/>
                                                <input type="hidden" name="hiddenCompanyName" value="<%= companyName %>"/>
                                                <input type="hidden" name="hiddenCompanyIndustry" value="<%= companyIndustry %>"/>
                                                <button class="btn btn-outline btn-primary btn-sm btn-block" type="submit"><i class="fa fa-plus">&nbsp;&nbsp;</i>Add A Review</button>
                                            </form>
                                        </div>
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
                        <%  ArrayList<Vector> associatedCompanyListSYS = (ArrayList) request.getAttribute("companyListInIndustrySYS");
                                if (!associatedCompanyListSYS.isEmpty()) {
                                    for (int i = 0; i <= associatedCompanyListSYS.size() - 1; i++) {
                                        Vector v = associatedCompanyListSYS.get(i);
                                        String associatedCompanyID = String.valueOf(v.get(0));
                                        String associatedCompanyImage = String.valueOf(v.get(1));
                                        String associatedCompanyName = String.valueOf(v.get(2));
                                        String associatedCompanyRating = String.valueOf(v.get(3));
                        %>    
                        <div class="col-xl-12 col-md-12 col-12 d-block d-lg-none d-xl-block list-item">
                            <div class="card card-product">
                                <div class="card-content">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-xl-4 col-md-4 col-4">
                                                <div class="img-wrapper">
                                                    <img class="card-img-top" style="max-width: 100px; max-height: 100px;" src="uploads/unify/images/voices/company/<%= associatedCompanyImage%>" />
                                                </div>
                                            </div>
                                            <div class="col-xl-8 col-md-8 col-8">
                                                <span><strong><%= associatedCompanyName%></strong></span><br/>
                                                <span>Rating:&nbsp;<span class="ml-1 card-text rating companyRating"><%= associatedCompanyRating%></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer " style="padding: 2px">
                                        <div class="row">
                                            <div class="col-xl-6 col-md-6 col-6"></div>
                                            <div class="col-xl-6 col-md-6 col-6">
                                                <a style="text-decoration: none;" href="VoicesSysUser?pageTransit=goToViewCompanyDetailsSYS&hiddenCompanyID=<%= associatedCompanyID%>&hiddenCategoryName=<%= companyIndustry%>">
                                                    <div><span style="margin-left: 20px; font-size: 12px">View More&nbsp;<i class="fa fa-chevron-right"></i></span></div>
                                                </a>
                                            </div>
                                        </div>
                                        
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                       <%      }
                           }
                       %>
                    </div>
                    <div class="col-lg-7 col-md-6">
                        <%  String successMessage = (String) request.getAttribute("successMessage");
                            if (successMessage != null) {
                        %>
                        <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                             <button type="button" class="close" id="closeSuccess">&times;</button>
                             <%= successMessage%>
                        </div>
                        <%  }   %>
                    <%  String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null) {
                    %>
                        <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                            <button type="button" class="close" id="closeError">&times;</button>
                            <%= errorMessage%>
                        </div>
                    <%  }   %>
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item"><a class="nav-link text-default active" id="companyInfoPane-tab" data-toggle="tab" href="#companyInfoPane" role="tab" aria-controls="companyInfoPane" aria-selected="true">Overview</a></li>
                            <li class="nav-item"><a class="nav-link text-default" id="reviewListPane-tab" data-toggle="tab" href="#reviewListPane" role="tab" aria-controls="reviewListPane" aria-selected="false">Review List</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane border border-top-0 p-3 show active" id="companyInfoPane" role="tabpanel" aria-labelledby="companyInfoPane-tab">
                                <div class="infoEntity row">
                                    <div class="col-lg-6 col-md-6">
                                        <label><strong>Company Website:&nbsp;</strong></label>
                                        <span><a href="http://<%= companyWebsite%>" target="_blank" rel="nofollow noreferrer"><%= companyWebsite%></a></span>
                                    </div>
                                    <div class="col-lg-6 col-md-6">
                                    <label><strong>Company Headquarter:&nbsp;</strong></label>
                                    <span><%= companyHQ%></span>
                                    </div>
                                </div>
                                <div class="infoEntity row">
                                    <div class="col-lg-6 col-md-6">
                                        <label><strong>Company size:&nbsp;</strong></label>
                                        <span><%= companySize%>&nbsp;Employees</span>
                                    </div>
                                    <div class="col-lg-6 col-md-6">
                                        <label><strong>Average Rating:&nbsp;</strong></label>
                                        <span><%= companyRating%></span>
                                    </div>
                                </div>
                                <div class="infoEntity" row>
                                    <label><strong>Company Address:&nbsp;</strong></label>
                                    <span><%= companyAddress%></span>
                                </div>
                                <div class="infoEntity" row>
                                    <label><strong>Description:</strong></label><br/>
                                    <span><%= companyDescription%></span>
                                </div>
                            </div>
                            <div class="tab-pane border border-top-0 p-3" id="reviewListPane" role="tabpanel" aria-labelledby="reviewListPane-tab">
                                <div class="jplist-search sorting-bar">
                                    <div class="mr-3 jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                         data-control-type="sort-drop-down" data-control-name="sort" data-control-action="sort">
                                        <ul>
                                            <li><span data-path=".reviewTitle" data-order="asc" data-type="text">Name Asc</span></li>
                                            <li><span data-path=".reviewTitle" data-order="desc" data-type="text">Name Desc</span></li>
                                            <li><span data-path=".companyRating" data-order="asc" data-type="text">Rating Asc</span></li>
                                            <li><span data-path=".companyRating" data-order="desc" data-type="text">Rating Desc</span></li>
                                        </ul>
                                    </div>
                                    <div class="jplist-drop-down" add-class-on-xs="w-100" data-control-type="items-per-page-drop-down" 
                                         data-control-name="paging" data-control-action="paging" data-control-animate-to-top="true">
                                        <ul>
                                            <li><span data-number="2" data-default="true">2 per page</span></li>
                                            <li><span data-number="4">4 per page</span></li>
                                            <li><span data-number="8">8 per page</span></li>
                                            <li><span data-number="12">12 per page</span></li>
                                        </ul>
                                    </div>
                                    <div class="input-group-addon" style="float: right; margin-top: 12px">
                                        <input type="text" data-path=".reviewTitle" class="input-group-addon" placeholder="Search Review..." 
                                               aria-label="Search Review" data-control-type="textbox" id="title-filter"
                                               data-control-name="title-text-filter" data-control-action="filter" />
                                    </div>
                                </div>
                                
                                <div id="itemListing" class="row equal-height" add-class-on-xs="no-gutters">
                                    <div class="list searchresult-row">
                                        <%  ArrayList<Vector> reviewListSYS = (ArrayList) request.getAttribute("associatedReviewListSYS");
                                            if (!reviewListSYS.isEmpty()) {
                                                for (int i = 0; i <= reviewListSYS.size() - 1; i++) {
                                                    Vector v = reviewListSYS.get(i);
                                                    String reviewDate = String.valueOf(v.get(0));
                                                    String reviewPoster = String.valueOf(v.get(1));
                                                    String reviewTitle = String.valueOf(v.get(2));
                                                    String reviewPros = String.valueOf(v.get(3));
                                                    String reviewCons = String.valueOf(v.get(4));
                                                    String reviewEmpType = String.valueOf(v.get(5));
                                                    String reviewSalary = String.valueOf(v.get(6));
                                                    String reviewRating = String.valueOf(v.get(7));
                                                    String reviewThumbs = String.valueOf(v.get(8));
                                                    String reviewID = String.valueOf(v.get(9));
                                                    String reviewLikeStatus = String.valueOf(v.get(10));
                                        %>
                                        <div class="col-xl-12 col-md-12 col-12 d-block d-lg-none d-xl-block list-item">
                                            <div class="card card-product">
                                                <div class="card-content">
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-xl-2 col-md-2 col-2">
                                                                <div class="img-wrapper">
                                                                    <img class="card-img-top" style="max-width: 70px; max-height: 70px; margin-left: 10px; margin-bottom: 10px;" src="uploads/unify/images/voices/company/<%= companyImage%>" />
                                                                </div>
                                                            </div>
                                                            <div class="col-xl-10 col-md-10 col-10">
                                                                <div class="review-title">
                                                                    <span class="card-title reviewTitle" style="color: #333; font-size: 18px; line-height: 1.5;"><strong><%= reviewTitle%></strong></span><br/>
                                                                </div>
                                                                <div class="row" style="margin-left: 0px">
                                                                    <div style="margin-top: 2px"><span class="ml-1 card-text rating companyRating" id="average_rating"><%= reviewRating%></span></div>       
                                                                    <div class="star-rating">
                                                                    <% double review_rating = Double.parseDouble(reviewRating);
                                                                        double review_percent = 0.0;
                                                                        while(review_rating > 0) {
                                                                        if(review_rating>=1) { review_percent += 20; } 
                                                                        else { review_percent += (((review_rating*13.93)/23.02)*100.0)*0.2; }
                                                                        review_rating--;
                                                                        }
                                                                    %>
                                                                        <div class="star-rating-top" id="star_rating_top" style="width: <%= review_percent%>%">
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
                                                                    <div style="margin-left: 10px"><span class="ml-1 card-text companyEmpStatus" style="color: #8a8b8c"><%= reviewEmpType%></span></div>    
                                                                </div>
                                                                <div class="row" style="margin-left: 3px; margin-top: 2px">
                                                                    <i class="fa fa-usd" style="margin-top: 3px">&nbsp;</i><span><%= reviewSalary%></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                    <i class="fa fa-calendar" style="margin-top: 3px">&nbsp;</i><span><%= reviewDate%></span>
                                                                </div>
                                                                <div class="row" style="margin-left: 3px; margin-top: 10px">
                                                                    <div><span><strong>Pros</strong></span></div>
                                                                </div>
                                                                <div class="row" style="margin-left: 3px; margin-top: 3px">
                                                                    <div><%= reviewPros%></div>
                                                                </div>
                                                                <div class="row" style="margin-left: 3px; margin-top: 10px">
                                                                    <div><span><strong>Cons</strong></span></div>
                                                                </div>
                                                                <div class="row" style="margin-left: 3px; margin-top: 3px; margin-bottom: 10px">
                                                                    <div><%= reviewCons%></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-7 col-md-7 col-7"></div>
                                                            <div class="col-lg-2 col-md-2 col-2">
                                                                <input type="hidden" id="companyID" value="<%= companyID%>" />
                                                                <input type="hidden" id="reviewPoster" value="<%= reviewPoster%>" />
                                                                <input type="hidden" id="reviewID" value="<%= reviewID%>" />
                                                                <input type="hidden" id="username" value="<%= loggedInUsername%>" />
                                                                <button onclick="newReviewReport('<%= companyID%>', '<%= reviewPoster%>', '<%= reviewID%>')" class="btn btn-outline-secondary btn-sm btn-block">Report</button>
                                                            </div>
                                                            <div class="col-lg-3 col-md-3 col-3">
                                                                <div class="btn-group" role="group">
                                                                    <%  if(reviewLikeStatus.equals("true")) {   %>
                                                                        <button type="button" id="likeItemBtn-<%= reviewID%>" onclick="likeItemBtn('<%= reviewID%>')" class="btn btn-outline-theme btn-sm likeStatus" data-toggle="tooltip" data-placement="top" >Helpful (<i class="fa fa-thumbs-up">&nbsp;&nbsp;</i><span class="likeCount-<%= reviewID%>"><%= reviewThumbs%>)</span></button>
                                                                    <%  } else if(reviewLikeStatus.equals("false")) {    %>
                                                                    <button type="button" id="likeItemBtn-<%= reviewID%>" onclick="likeItemBtn('<%= reviewID%>')" class="btn btn-outline-theme btn-sm noLikeStatus" data-toggle="tooltip" data-placement="top" >Helpful (<i class="fa fa-thumbs-up">&nbsp;&nbsp;</i><span class="likeCount-<%= reviewID%>"><%= reviewThumbs%>)</span></button>
                                                                    <%  }   %>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row"><br/></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <%      }   %>
                                        <%  }%> 
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
            </a></div>
            <div id="newReviewReport-iframe"></div>
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
        <script src="js/unify/systemuser/webjs/voices/ViewCompanyDetailsSYSJS.js" type="text/javascript"></script>

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
