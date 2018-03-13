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
        <title>Unify Company Review - Company Listing Details</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/voices/ViewCompanyListingSYSCSS.css" rel="stylesheet" type="text/css">

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
                            <li class="breadcrumb-item active" aria-current="page">Company Review (Company Listing)</li>
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
                                    <div class="filter-sidebar">
                                        <div class="title"><span>Company Filter</span></div>
                                        <div class="subtitle" style="margin-bottom: 5px">Company Name</div>
                                        <input type="text" data-path=".companyName" class="form-control" placeholder="Search Company..." 
                                               aria-label="Search Company" data-control-type="textbox" id="name-filter"
                                               data-control-name="name-text-filter" data-control-action="filter" />
                                    </div>
                                    <div class="filter-sidebar">
                                        <div class="subtitle" style="margin-bottom: 5px">Company Headquarter</div>
                                        <input type="text" data-path=".companyHQ" class="form-control" placeholder="Serch Location..."
                                               aria-label="Search Location" data-control-type="textbox" id="location-filter"
                                               data-control-name="location-text-filter" data-control-action="filter" />
                                    </div>
                                    <div class="filter-sidebar">
                                        <div class="subtitle" style="margin-bottom: 5px">Industry</div>
                                        <div class="jplist-group-industry" data-control-type="checkbox-group-filter" data-control-action="filter" data-control-name="industry">
                                        <% ArrayList<Vector> industryList = (ArrayList) request.getAttribute("industryListSYS");
                                            for(Object obj: industryList) {
                                                String industryStr = (String) obj;
                                                String industryString = industryStr;
                                                if(industryStr.contains(" ")) {
                                                    industryStr = industryStr.replace(" ","");
                                                }
                                                if(industryStr.contains("&")) {
                                                    industryStr = industryStr.replace("&", "And"); 
                                                }
                                                if(industryString.length()>30) {
                                                    industryString = industryString.substring(0, 29);
                                                    industryString = industryString + "...";
                                                }
                                                String industryStr_path = "." + industryStr; %>
                                            <input data-path="<%= industryStr_path%>" id="<%= industryStr%>" type="checkbox" class="industry-filter" style="border: 2px solid #26a69a; background-color: #26a69a;">
                                            <label for="<%= industryStr%>"><%= industryString%></label><br/>
                                        <%  }
                                        %>
                                        </div>
                                        <button type="button" class="jplist-reset-btn btn-outline-secondary" data-control-type="reset" data-control-name="reset"
                                                data-control-action="reset" style="height:30px;width:100px">Clear Filters &nbsp;
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-9 col-md-8">
                        <div class="title"><span>Company Listing</span>
                            <div class="btn-group btn-group-sm mr-3" style="float: right;">
                                <span class="btn btn-outline-theme" id="newCompanyRequest" role="button">
                                    <input type="hidden" id="username" value="<%= loggedInUsername%>" />
                                    <i class="fa fa-building d-none d-lg-inline-block"></i>&nbsp;Request New Company
                                </span>
                            </div>
                        </div>
                        <div class="jplist-search sorting-bar">
                            <div class="mr-3 jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                 data-control-type="sort-drop-down" data-control-name="sort" data-control-action="sort">
                                <ul>
                                    <li><span data-path=".companyName" data-order="asc" data-type="text">Name Asc</span></li>
                                    <li><span data-path=".companyName" data-order="desc" data-type="text">Name Desc</span></li>
                                    <li><span data-path=".companyRating" data-order="asc" data-type="text">Rating Asc</span></li>
                                    <li><span data-path=".companyRating" data-order="desc" data-type="text">Rating Desc</span></li>
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

                        <!-- ITEM LISTING -->
                        <div id="itemListing" class="row equal-height" add-class-on-xs="no-gutters">
                            <div class="list searchresult-row">
                                <%
                                    ArrayList<Vector> companyListSYS = (ArrayList) request.getAttribute("companyListSYS");
                                    if (!companyListSYS.isEmpty()) {
                                        for (int i = 0; i <= companyListSYS.size() - 1; i++) {
                                            Vector v = companyListSYS.get(i);
                                            String companyID = String.valueOf(v.get(0));
                                            String companyImage = String.valueOf(v.get(1));
                                            String companyName = String.valueOf(v.get(2));
                                            String companyIndustry = String.valueOf(v.get(3));
                                            String companyWebsite = String.valueOf(v.get(4));
                                            String companyHQ = String.valueOf(v.get(5));
                                            String companySize = String.valueOf(v.get(6));
                                            String companyNumOfReview = String.valueOf(v.get(7));
                                            String companyLastPost = String.valueOf(v.get(8));
                                            String companyRating = String.valueOf(v.get(9));
                                            
                                            String industry_path = companyIndustry;
                                            if(companyIndustry.contains(" ")) {
                                                industry_path = industry_path.replace(" ", "");
                                            }
                                            
                                            if(industry_path.contains("&")) {
                                                industry_path = industry_path.replace("&", "And");
                                            }
                                %>
                                <div class="col-xl-12 col-md-12 col-12 d-block d-lg-none d-xl-block list-item">
                                    <div class="card card-product">
                                        <div class="card-content">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-xl-3 col-md-3 col-3">
                                                        <div class="img-wrapper">
                                                            <a href="VoicesSysUser?pageTransit=goToViewCompanyDetailsSYS&hiddenCompanyID=<%= companyID%>&hiddenCategoryName=<%= companyIndustry%>">
                                                                <img class="card-img-top" style="max-width: 150px; max-height: 150px; margin-left: 5px; margin-bottom: 10px;" src="uploads/unify/images/voices/company/<%= companyImage%>" />
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-xl-6 col-md-6 col-6">
                                                        <div class="company-name">
                                                            <span class="card-title companyName" style="color: #007bff; font-size: 25px; line-height: 2.5;"><strong><a href="VoicesSysUser?pageTransit=goToViewCompanyDetailsSYS&hiddenCompanyID=<%= companyID%>&hiddenCategoryName=<%= companyIndustry%>"><%= companyName%></a></strong></span><br/>
                                                        </div>
                                                        <div class="company-industry">
                                                            <span class="card-text <%= industry_path%>" style="line-height: 2.0"><%= companyIndustry%></span>
                                                        </div>
                                                        <div class="company-other-info">
                                                            <i class="fa fa-globe" style="color: #64676d;">&nbsp;</i><span class="card-text companyWebsite" style=" margin-right: 15px; font-size: 12px"><%= companyWebsite%></span>
                                                            <i class="fa fa-map-marker" style="color: #64676d">&nbsp;</i><span class="card-text companyHQ" style=" margin-right: 15px; font-size: 12px"><%= companyHQ%></span>
                                                            <i class="fa fa-users" style="color: #64676d">&nbsp;</i><span class="card-text companySize" style="font-size: 12px"><%= companySize%></span>
                                                        </div>
                                                        <div class="company-latest-post">
                                                            <i class="fa fa-list-alt">&nbsp;</i><span class="float-none" style="font-size: 12px">
                                                                <%  if (companyNumOfReview.equals("0")) {%><%= companyNumOfReview%>&nbsp;Review
                                                                <%  }  else {%><%= companyNumOfReview%>&nbsp;Reviews
                                                            <%  }   %>
                                                            </span>&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <i class="fa fa-clock-o">&nbsp;</i><span class="float-none" style="color: #64676d; font-size: 12px">Latest Post: &nbsp;<%= companyLastPost%></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-xl-3 col-md-3 col-3">
                                                        <div class="new-review">
                                                            <form action="VoicesSysUser" method="POST">
                                                                <input type="hidden" name="pageTransit" value="goToNewReviewSYS"/>
                                                                <input type="hidden" name="hiddenCompanyImage" value="<%= companyImage %>"/>
                                                                <input type="hidden" name="hiddenCompanyName" value="<%= companyName %>"/>
                                                                <input type="hidden" name="hiddenCompanyIndustry" value="<%= companyIndustry %>"/>
                                                                <button class="btn btn-outline btn-primary btn-sm btn-block" type="submit"><i class="fa fa-plus">&nbsp;&nbsp;</i>Add A Review</button>
                                                            </form>
                                                        </div>
                                                        <% if(!companyNumOfReview.equals("0")) { %>
                                                        <br/>
                                                        <button class="btn btn-outline btn-primary btn-sm btn-block"><i class="fa fa-list-alt">&nbsp;&nbsp;</i>View All Reviews</button>
                                                        <% } %>
                                                    </div> 
                                                    
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer text-muted mt-1">
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
            <!-- <div id="unifyFooter"></div> -->
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="itemcard-iframe"></div>
            <div id="newCompanyRequest-iframe"></div>
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
        <script src="js/unify/systemuser/webjs/voices/ViewCompanyListingSYSJS.js" type="text/javascript"></script>

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