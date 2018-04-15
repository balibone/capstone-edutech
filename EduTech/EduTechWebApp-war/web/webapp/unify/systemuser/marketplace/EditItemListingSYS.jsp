<%-- 
  - Author(s):          TAN CHIN WEE WINSTON
  - Date:               6th April 2018
  - Version:            1.0
  - Credits to:         NIL
  - Description:        Edit Item Listing (Marketplace)
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
        <title>Unify Marketplace - Edit My Marketplace Listing</title>

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
        <link href="css/unify/systemuser/weblayout/marketplace/EditItemListingSYSCSS.css" rel="stylesheet" type="text/css" />
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
                            <li class="breadcrumb-item active" aria-current="page">Marketplace (Item Listing Around Me)</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="container" style="margin-bottom: 30px;">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
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

                            <div class="x_title">
                                <h5>Edit My Item Listing</h5>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <p>Edit the item information here. (<span class="asterik">*</span>) fields are mandatory.</p>

                                <form class="form-horizontal form-label-left" action="MarketplaceSysUser" method="POST" enctype="multipart/form-data">
                                    <%
                                        Vector itemDetailsSYSVec = (Vector) request.getAttribute("itemDetailsSYSVec");
                                        String itemID, itemName, itemPrice, itemCondition, itemDescription, itemImage;
                                        itemID = itemName = itemPrice = itemCondition = itemDescription = itemImage = "";

                                        String tradeLocation, tradeLat, tradeLong, tradeInformation, itemCategoryID;
                                        tradeLocation = tradeLat = tradeLong = tradeInformation = itemCategoryID = "";

                                        if (itemDetailsSYSVec != null) {
                                            itemID = (String.valueOf(itemDetailsSYSVec.get(0)));
                                            itemName = (String) itemDetailsSYSVec.get(1);
                                            itemPrice = (String.valueOf(itemDetailsSYSVec.get(3)));
                                            itemCondition = (String) itemDetailsSYSVec.get(4);
                                            itemDescription = (String) itemDetailsSYSVec.get(5);
                                            itemImage = (String) itemDetailsSYSVec.get(6);
                                            tradeLocation = (String) itemDetailsSYSVec.get(10);
                                            tradeLat = (String) itemDetailsSYSVec.get(11);
                                            tradeLong = (String) itemDetailsSYSVec.get(12);
                                            tradeInformation = (String) itemDetailsSYSVec.get(13);
                                            itemCategoryID = (String.valueOf(itemDetailsSYSVec.get(17)));
                                        }
                                    %>
                                    <div class="form-row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <div class="image-upload">
                                                    <img id="output-image" src="uploads/unify/images/marketplace/item/<%= itemImage%>" />
                                                </div>
                                                <label for="file-upload" class="btn btn-theme btn-sm btn-block" style="margin-top: 10px; width: 151px;">
                                                    <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                                                </label>
                                                <input id="file-upload" name="itemImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" />
                                                <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" />
                                                <input type="hidden" name="hiddenItemImage" value="<%= itemImage%>" />
                                            </div>
                                        </div>

                                        <div class="col-md-5 ml-2">
                                            <div class="form-group">
                                                <label for="itemName">Item Name&nbsp;<span class="asterik">*</span></label>
                                                <input type="text" class="form-control" name="itemName" placeholder="<%= itemName%>" />
                                                <input type="hidden" name="hiddenItemID" value="<%= itemID%>" />
                                                <input type="hidden" name="hiddenItemName" value="<%= itemName%>" />
                                            </div>
                                            <div class="form-group">
                                                <label for="itemCondition">Item Condition&nbsp;<span class="asterik">*</span></label><br/>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="conditionNew" name="itemCondition" value="New" class="custom-control-input" />
                                                    <label class="custom-control-label" for="conditionNew">New</label>
                                                </div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <input type="radio" id="conditionUsed" name="itemCondition" value="Used" class="custom-control-input" />
                                                    <label class="custom-control-label" for="conditionUsed">Used</label>
                                                </div>
                                                <input type="hidden" name="hiddenItemCondition" id="dbItemCondition" value="<%= itemCondition%>" />
                                            </div>
                                            <div class="form-group">
                                                <label for="itemDescription">Item Description</label>
                                                <textarea class="form-control" name="itemDescription" rows="5" placeholder="<%= itemDescription%>"></textarea>
                                                <input type="hidden" name="hiddenItemDescription" value="<%= itemDescription%>" />
                                            </div>
                                        </div>
                                        <div class="col-md-4 ml-2">
                                            <div class="form-group">
                                                <label for="itemPrice">Item Price&nbsp;<span class="asterik">*</span></label>
                                                <div class="input-group input-group-sm input-group-qty">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-outline-theme btn-down" type="button"><i class="fa fa-minus"></i></button>
                                                    </div>
                                                    <input type="text" class="form-control" name="itemPrice" aria-label="itemPrice" data-min="1" data-max="9999" placeholder="<%= itemPrice%>" />
                                                    <input type="hidden" name="hiddenItemPrice" value="<%= itemPrice%>" />
                                                    <div class="input-group-append">
                                                        <button class="btn btn-outline-theme btn-up" type="button"><i class="fa fa-plus"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div><hr/>
                                    <div class="form-row">
                                        <h5 class="pt-3 pb-3" style="margin:0 auto;">You may Re-Select the Marketplace Category for this Product Listing</h5>
                                        <input type="hidden" name="hiddenItemCategoryID" id="dbItemCategoryID" value="<%= itemCategoryID%>" />
                                        <div class="owl-carousel owl-theme">
                                            <%
                                                ArrayList<Vector> itemCategoryListSYS = (ArrayList) request.getAttribute("itemCategoryListSYS");
                                                if (!itemCategoryListSYS.isEmpty()) {
                                                    for (int i = 0; i <= itemCategoryListSYS.size()-1; i++) {
                                                        Vector v = itemCategoryListSYS.get(i);
                                                        String categoryImage = String.valueOf(v.get(0));
                                                        String categoryID = String.valueOf(v.get(1));
                                                        String categoryName = String.valueOf(v.get(2));
                                            %>
                                            <div class="assocItemCategory">
                                                <div class="card" id="<%= categoryID%>">
                                                    <div class="card-body" style="margin: 0 auto; text-align: center;">
                                                        <img src="uploads/unify/images/common/category/<%= categoryImage%>" style="width: 100px; height: 100px;" />
                                                        <h5 class="card-title"><%= categoryName%></h5>
                                                    </div>
                                                </div>
                                            </div>
                                            <%      }   %>
                                            <%  }%>
                                        </div>
                                    </div><hr/>
                                    <div class="form-row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <div id="mapdiv" style="width: auto; height: 300px;"></div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 ml-2">
                                            <div class="form-group" style="position: relative;">
                                                <label for="tradeLocation">Trade Location&nbsp;<span class="asterik">*</span></label>
                                                <input type="text" class="form-control" id="tradeLocation" name="tradeLocation" placeholder="<%= tradeLocation%>" />
                                                <input type="hidden" name="hiddenTradeLocation" id="dbTradeLocation" value="<%= tradeLocation%>" />
                                                <input type="hidden" name="hiddenTradeLat" id="dbTradeLat" value="<%= tradeLat%>" />
                                                <input type="hidden" name="hiddenTradeLong" id="dbTradeLong" value="<%= tradeLong%>" />
                                                <div id="searchResults"></div>
                                            </div>
                                            <div class="form-group">
                                                <label for="tradeInformation">Trade Information</label>
                                                <textarea class="form-control" name="tradeInformation" rows="5" placeholder="<%= tradeInformation%>"></textarea>
                                                <input type="hidden" name="hiddenTradeInformation" value="<%= tradeInformation%>" />
                                            </div>
                                            <div class="form-group" style="text-align: center;">
                                                <!-- FOR FORM SUBMIT UPDATE OPERATION -->
                                                <input type="hidden" name="pageTransit" value="editItemListingSYS" />
                                                <button type="submit" class="btn btn-theme">Edit Item Listing</button>&nbsp;&nbsp;
                                                <button type="button" class="btn btn-primary" onclick="javascript:deleteAlert(<%= itemID%>)">Delete Item Listing</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
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
            
            <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
            <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/bootbox.min.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
            <script src="js/unify/systemuser/webjs/marketplace/EditItemListingSYSJS.js" type="text/javascript"></script>
        </div>
    </body>
</html>