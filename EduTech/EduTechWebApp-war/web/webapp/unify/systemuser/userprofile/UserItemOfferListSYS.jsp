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
        <title>Unify - My Item Offer List</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/qtip/jquery.qtip-v3.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/weblayout/userprofile/UserItemOfferListSYSCSS.css" rel="stylesheet" type="text/css" />
        
        <link href="css/unify/systemuser/baselayout/datatable/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/datatable/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/datatable/dataTables.responsive.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/datatable/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/datatable/select.dataTables.min.css" rel="stylesheet" type="text/css" />
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
                            <li class="breadcrumb-item active" aria-current="page">User Account</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div id="contentArea" class="container jplist mb-3">
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
                                <a href="ProfileSysUser?pageTransit=goToMarketplaceTransSYS" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-user"></i>&nbsp;My Marketplace Transaction
                                </a>
                                <a href="ProfileSysUser?pageTransit=goToMyBuyerOfferListSYS" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-user"></i>&nbsp;My Item Offer List
                                </a>
                                <a href="account-address.html" class="list-group-item list-group-item-action">
                                    <i class="fa fa-fw fa-map-marker"></i>&nbsp;Errands Transaction
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-9 col-md-8">
                        <div class="title"><span>My Item Offers</span></div>
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
                        <div class="table-responsive">
                            <table id="userItemOfferTable" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th>Item Image</th>
                                        <th>Item Name</th>
                                        <th>My Offer Price<br/>(Item Price)</th>
                                        <th>Offer Status</th>
                                        <th>Offer Details</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<Vector> userBuyerOfferListSYS = (ArrayList) request.getAttribute("userBuyerOfferListSYS");
                                        if (!userBuyerOfferListSYS.isEmpty()) {
                                            for (int i = 0; i <= userBuyerOfferListSYS.size()-1; i++) {
                                                Vector v = userBuyerOfferListSYS.get(i);
                                                String itemImage = String.valueOf(v.get(0));
                                                String itemName = String.valueOf(v.get(1));
                                                String itemPrice = String.valueOf(v.get(2));
                                                String itemOfferID = String.valueOf(v.get(3));
                                                String itemOfferPrice = String.valueOf(v.get(4));
                                                String itemOfferStatus = String.valueOf(v.get(5));
                                                String sellerComments = String.valueOf(v.get(6));
                                                String itemOfferDate = String.valueOf(v.get(7));
                                    %>
                                    <tr id="itemOfferRow<%= itemOfferID%>">
                                        <td><img src="uploads/unify/images/marketplace/item/<%= itemImage%>" style="width: 50px; height: 50px;" /></td>
                                        <td><%= itemName %></td>
                                        <td class="offerPriceTD">$<%= itemOfferPrice %><span style="display:none">;</span><br/>($<%= itemPrice %>)</td>
                                        <td>
                                            <%  if(itemOfferStatus.equals("Pending")) {  %>
                                            <span class="badge badge-primary custom-badge"><%= itemOfferStatus%></span>
                                            <%  } else if(itemOfferStatus.equals("Processing")) { %>
                                            <span class="badge badge-warning custom-badge"><%= itemOfferStatus%></span>
                                            <%  } else if(itemOfferStatus.equals("Accepted")) {   %>
                                            <span class="badge badge-info custom-badge"><%= itemOfferStatus%></span>
                                            <%  } else if(itemOfferStatus.equals("Rejected")) { %>
                                            <span class="badge badge-danger custom-badge"><%= itemOfferStatus%></span>
                                            <%  } else if(itemOfferStatus.equals("Cancelled")) { %>
                                            <span class="badge badge-danger custom-badge"><%= itemOfferStatus%></span>
                                            <%  } else if(itemOfferStatus.equals("Completed")) { %>
                                            <span class="badge badge-success custom-badge"><%= itemOfferStatus%></span>
                                            <%  }   %>
                                        </td>
                                        <td>
                                            <strong>Offer Date:</strong>&nbsp;&nbsp;<%= itemOfferDate%><br/>
                                            <strong>Seller Comments:</strong><br/><%= sellerComments%>
                                        </td>
                                        <td>
                                            <ul class="list-inline">
                                                <%  if(itemOfferStatus.equals("Pending") || itemOfferStatus.equals("Processing")) {  %>
                                                <li class="list-inline-item"><button id="editItemOfferPanel<%= itemOfferID%>" type="button" class="userItemOfferListBtn qtipEditButton"><i class="fa fa-edit fa-lg"></i></button></li>
                                                <li class="list-inline-item"><button id="cancelItemOffer<%= itemOfferID%>" type="button" class="userItemOfferListBtn"><i class="fa fa-trash fa-lg"></i></button></li>
                                                <%  } else {    %>
                                                <li>&nbsp;</li>
                                                <%  }  %>
                                            </ul>
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
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="sellNewItem-iframe"></div>
            <div id="unifyFooter"></div>
            
            <div style="display:none;" id="editItemOfferTip">
                Previous Offer Price:&nbsp;
                <strong style="color:#FF0000;"><u><span id="previousOfferPrice" /></u></strong><br/><br/>
                
                Your Revised Offer Price&nbsp;<span style="color:#FF0000;">*</span><br/>
                <input type="text" id="revisedItemOffer" class="revisedOfferFields" placeholder="e.g. 12.55 (without the $)" style="margin-bottom: 4px;" />
                <button type="button" id="editItemOfferBtn" class="userItemOfferListBtn" style="margin:7px 0 7px 0;">Revise My Offer</button><br/>
                <input type="hidden" id="itemOfferHiddenID" />
                <span id="successItemOfferResponse"></span><span id="failedItemOfferResponse"></span>
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
        <script src="js/unify/systemuser/basejs/qtip/jquery.qtip-v3.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/userprofile/UserItemOfferListSYSJS.js" type="text/javascript"></script>
        
        <script src="js/unify/systemuser/basejs/datatable/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/dataTables.buttons-v1.5.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/dataTables.responsive.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/dataTables.select-v1.2.5.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/jquery.dataTables-v1.10.16.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/jszip-v3.1.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/pdfmake-v0.1.32.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/vfs_fonts-v0.1.32.js" type="text/javascript"></script>
    </body>
</html>