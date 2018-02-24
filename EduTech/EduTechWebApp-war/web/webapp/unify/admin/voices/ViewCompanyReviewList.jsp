<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify Admin - View Item Listing</title>

        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/UnifyAdminBaseCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/voices/ViewCompanyReviewListCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/dataTable/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">
    </head>

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Unify Admin</span></a>
                        </div>

                        <div class="clearfix"></div>

                        <!-- menu profile quick info -->
                        <div class="profile clearfix">
                            <div class="profile_pic">
                                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                            </div>
                            <div class="profile_info">
                                <span>Welcome,</span>
                                <h2>John Doe</h2>
                            </div>
                        </div>
                        <br />
                        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                            <div class="menu_section">
                                <h3>General</h3>
                                <ul class="nav side-menu">
                                    <li><a><i class="fa fa-home"></i>&nbsp;Marketplace (Trades)&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="MarketplaceAdmin?pageTransit=goToViewItemCategoryListing">Item Categories</a></li>
                                            <li><a href="MarketplaceAdmin?pageTransit=goToViewItemListing">Item Listing</a></li>
                                            <li><a href="MarketplaceAdmin?pageTransit=goToViewItemTransactionList"><i class="fa fa-book"></i>&nbsp;Item Transactions</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-edit"></i>&nbsp;Errands (Jobs)&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="form.html">Job Categories</a></li>
                                            <li><a href="form_advanced.html">Job Listing</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-desktop"></i>&nbsp;Company Reviews&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="VoicesAdmin?pageTransit=goToViewCompanyCategoryList">Company Categories</a></li>
                                            <li><a href="VoicesAdmin?pageTransit=goToViewCompanyList">Company Listing</a></li>
                                            <li><a href="VoicesAdmin?pageTransit=goToViewCompanyRequestList">Request Listing</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-table"></i>&nbsp;Tags&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="ContentAdmin?pageTransit=goToTagListing"><i class="fa fa-tags"></i>&nbsp;Tag List</a></li>
                                        </ul>
                                    </li>
                                    <li><a><i class="fa fa-bar-chart-o"></i>&nbsp;Content Administration&nbsp;<span class="fa fa-chevron-down"></span></a>
                                        <ul class="nav child_menu">
                                            <li><a href="ContentAdmin?pageTransit=goToReportedReviewListing"><i class="fa fa-wpforms"></i>&nbsp;Company Review Reports</a></li>
                                            <li><a href="ContentAdmin?pageTransit=goToReportedErrandsListing"><i class="fa fa-wpforms"></i>&nbsp;Errands Reports</a></li>
                                            <li><a href="ContentAdmin?pageTransit=goToReportedErrandsReviewListing"><i class="fa fa-wpforms"></i>&nbsp;Errands Review Reports</a></li>
                                            <li><a href="ContentAdmin?pageTransit=goToReportedMarketplaceListing"><i class="fa fa-wpforms"></i>&nbsp;Marketplace Reports</a></li>
                                            <li><a href="ContentAdmin?pageTransit=goToEventRequest"><i class="fa fa-calendar"></i>&nbsp;Event Requests</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="sidebar-footer hidden-small">
                            <a data-toggle="tooltip" data-placement="top" title="Settings">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="Lock">
                                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                            </a>
                            <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="top_nav">
                    <div class="nav_menu">
                        <nav>
                            <div class="nav toggle">
                                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                            </div>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="">
                                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        <img src="images/img.jpg" alt="">John Doe
                                        <span class=" fa fa-angle-down"></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                                        <li><a href="javascript:;"> Profile</a></li>
                                        <li>
                                            <a href="javascript:;">
                                                <span class="badge bg-red pull-right">50%</span>
                                                <span>Settings</span>
                                            </a>
                                        </li>
                                        <li><a href="javascript:;">Help</a></li>
                                        <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                                    </ul>
                                </li>

                                <li role="presentation" class="dropdown">
                                    <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                        <i class="fa fa-envelope-o"></i>
                                        <span class="badge bg-green">6</span>
                                    </a>
                                    <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <a>
                                                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                                                <span>
                                                    <span>John Smith</span>
                                                    <span class="time">3 mins ago</span>
                                                </span>
                                                <span class="message">
                                                    Film festivals used to be do-or-die moments for movie makers. They were where...
                                                </span>
                                            </a>
                                        </li>
                                        <li>
                                            <div class="text-center">
                                                <a>
                                                    <strong>See All Alerts</strong>
                                                    <i class="fa fa-angle-right"></i>
                                                </a>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>

                <div class="right_col" role="main">
                    <div class="row">
                        <%
                            Vector companyDetailsVec = (Vector) request.getAttribute("data");
                            String companyName, companyWebsite, companyHQ, companyAverageRating, companySize, companyStatus, companyIndustry, companyDescription, companyImage;
                            companyName = companyWebsite = companyHQ = companyAverageRating = companySize = companyStatus = companyIndustry = companyDescription = companyImage = "";
            
                            if (companyDetailsVec != null) {
                                companyName = (String) companyDetailsVec.get(0);
                                companyWebsite = (String) companyDetailsVec.get(1);
                                companyHQ = (String) companyDetailsVec.get(2);
                                companyAverageRating = (String.valueOf(companyDetailsVec.get(3)));
                                companySize = (String.valueOf(companyDetailsVec.get(4)));
                                companyStatus = (String.valueOf(companyDetailsVec.get(5)));
                                companyIndustry = (String.valueOf(companyDetailsVec.get(6)));
                                companyDescription = (String.valueOf(companyDetailsVec.get(7)));
                                companyImage = (String.valueOf(companyDetailsVec.get(8)));
                            }
                        %>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div class="x_title">
                <h2 class="bodyHeader">Company Details of <%= companyName %></h2>
                <div class="clearfix"></div>
            </div>
            <div id="formContent">
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                        <div class="image-upload">
                            <img id="output-image" src="uploads/unify/images/voices/company/<%= companyImage %>" />
                        </div>
                        <input id="file-upload" name="companyImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" />
                    </div>
                    <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companyName %>" name="companyName" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companyHQ %>" name="companyHQ" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                            </div>    
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <input type="text" class="form-control has-feedback-left" placeholder="<%= companyWebsite %>" name="companyWebsite" />
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companySize %>" name="companySize" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-12 form-group has-feedback">
                                <input type="text" class="form-control has-feedback-left" placeholder="<%= companyIndustry %>" name="companyIndustry" />
                                <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>   
                            </div>
                        </div>
                        <div class="form-group" style="margin-top: 7px;">
                            <label class="control-label col-md-6 col-sm-6 col-xs-12">Company Average Rating:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= companyAverageRating %></span></u>
                            </label>
                            <label class="control-label col-md-6 col-sm-6 col-xs-12">Company Status:&nbsp;&nbsp;
                                <u><span style="color: #32CD32;"><%= companyStatus %></span></u>
                            </label>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 9px;">
                            <textarea rows="5" class="form-control has-feedback-left" placeholder="<%= companyDescription %>" name="companyDescription"></textarea>
                            <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                        </div>
                    </div>
            </div>			
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <div class="x_panel">
                <div class="x_content">
                    <div class role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                            <li role="Review" class="active">
                                <a href="ViewReviewList" id="review-tab" role="tab" data-toggle="tab" aria-expanded="true">Reviews</a>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade active in" id="reviewList" aria-labelledby="review-tab">
                                <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Review Title</th>
                                            <th>Reviewed Company</th>
                                            <th>Review Poster ID</th>
                                            <th>Review Date</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Vector> reviewList = (ArrayList) request.getAttribute("reviewListVec");
                                            if (!reviewList.isEmpty()) {
                                                for (int i = 0; i <= reviewList.size() - 1; i++) {
                                                    Vector v = reviewList.get(i);
                                                    String reviewTitle = String.valueOf(v.get(0));
                                                    String reviewedCompany = String.valueOf(v.get(1));
                                                    String reviewPosterID = String.valueOf(v.get(2));
                                                    String reviewDate = String.valueOf(v.get(3));
                                        %>
                                        <tr>
                                            <td><%= reviewTitle %></td>
                                            <td><%= reviewedCompany %></td>
                                            <td><%= reviewPosterID %></td>
                                            <td><%= reviewDate %></td>
                                            <td>
                                                <form action="VoicesAdmin" method="POST" target="_parent">
                                                    <input type="hidden" name="pageTransit" value="deleteReview"/>
                                                    <input type="hidden" name="hiddenReviewedCompany" value=""/>
                                                    <input type="hidden" name="hiddenReviewPosterID" value="<%= reviewPosterID %>"/>
                                                    <button type="submit" class="btn btn-success btn-xs" style="margin-left: 20px" onClick="return confirm('Do you want to delete the review?')">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                        <%      }   %>
                                        <%  }%>
                                    </tbody> 
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                   </div>
                </div>
                <div id="adminFooter"></div>
            </div>
        </div>

        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/voices/ViewCompanyListJS.js" type="text/javascript"></script>
        
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
    </body>
</html>