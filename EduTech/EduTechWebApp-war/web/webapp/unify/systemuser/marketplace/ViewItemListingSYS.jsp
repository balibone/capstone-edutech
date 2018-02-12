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
        <title>Unify - Marketplace(Item) Listing</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">

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
                <div id="list-menu" class="list-menu list-group" data-children=".sub-menu1">
                    <a href="index.html"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
                    <div class="sub-menu1">
                        <a data-toggle="collapse" href="#" data-target="#marketplaceSub" role="button" aria-expanded="false" aria-controls="sub1">
                            <i class="fa fa-fw fa-file"></i>&nbsp;Marketplace
                        </a>
                        <div id="marketplaceSub" class="collapse" data-parent="#list-menu" role="tabpanel">
                            <a href="about.html">Item Listing</a>
                        </div>
                    </div>
                    <div class="sub-menu1">
                        <a data-toggle="collapse" href="#" data-target="#errandsSub" role="button" aria-expanded="false" aria-controls="sub1">
                            <i class="fa fa-fw fa-file"></i>&nbsp;Errands
                        </a>
                        <div id="errandsSub" class="collapse" data-parent="#list-menu" role="tabpanel">
                            <a href="about.html">Errands Listing</a>
                        </div>
                    </div>
                    <div class="sub-menu1">
                        <a data-toggle="collapse" href="#" data-target="#companyReviewSub" role="button" aria-expanded="false" aria-controls="sub2">
                            <i class="fa fa-fw fa-user"></i>&nbsp;Company Review
                        </a>
                        <div id="companyReviewSub" class="collapse" data-parent="#list-menu" role="tabpanel">
                            <a href="account-order.html">Company Listing</a>
                        </div>
                    </div>
                    <a href="index.html"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
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
                                    <a href="#" class="nav-link"><i class="fa fa-heart-o"></i>&nbsp;&nbsp;Likes</a>
                                </li>
                                <li class="nav-item d-none d-md-block">
                                    <a href="#" class="nav-link"><i class="fa fa-envelope"></i>&nbsp;&nbsp;Messages</a>
                                </li>
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
                        <div class="d-flex align-items-center h-100 justify-content-center menu-btn-wrapper">
                            <button class="btn btn-lg border-0 btn-link offcanvas-btn p-0" type="button">
                                <i class="fa fa-bars"></i>
                            </button>
                        </div>
                    </div>
                    <div class="col-2 col-sm-1 col-lg-3 pr-0">
                        <div class="d-flex align-items-center h-100 logo-wrapper">
                            <a href="index.html" class="d-lg-none">
                                <img src="images/edubox-logo.png" class="logo" />
                            </a>
                            <a href="index.html" class="d-none d-lg-flex mb-2 mb-lg-0">
                                <img src="images/edubox-logo.png" class="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="col-8 col-sm-6 col-md-7 col-lg-6">
                        <div class="d-flex align-items-center h-100">
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
                    <div class="col-4 col-sm-4 col-md-3 col-lg-3 d-none d-sm-block">
                        <div class="d-flex align-items-center h-100 float-right abg-secondary">
                            <div class="btn-group btn-group-sm mr-3" role="group" aria-label="Login Sign Up">
                                <a class="btn btn-outline-theme" href="ProfileSysUser?pageTransit=goToUserProfile" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;My Profile
                                </a>
                                <a class="btn btn-outline-theme" href="ProfileSysUser?pageTransit=goToUserAccount" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;My Account
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
                            <li class="breadcrumb-item"><a href="index.html">Unify Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Marketplace (Item Listing)</li>
                        </ol>
                    </nav>
                </div>
            </div>
            
            <div id="contentArea" class="container jplist" style="margin-bottom: 30px;">
                <div class="row">
                    <div class="col-lg-3 col-md-4 mb-3 jplist-search">
                        <button class="btn btn-outline-theme btn-block dropdown-toggle d-md-none" type="button" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="false" aria-controls="collapseFilter">
                            Search Filter
                        </button>
                        <div class="collapse d-md-block pt-3 pt-md-0" id="collapseFilter">
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-12">
                                    <div class="filter-sidebar">
                                        <div class="title"><span>Item Filter</span></div>
                                        <input type="text" data-path=".itemName" class="form-control" placeholder="Search Item ..." 
                                               aria-label="Search Item ..." data-control-type="textbox" 
                                               data-control-name="transmission-text-filter" data-control-action="filter" />
                                    </div>
                                    <div class="filter-sidebar">
                                        <div class="jplist-range-slider" data-control-type="range-slider" 
                                             data-control-name="range-slider-weight" data-control-action="filter"
                                             data-path=".itemPrice" data-slider-func="priceSlider" data-setvalues-func="priceValues">

                                            <div class="title"><span>Price Filter</span></div>
                                            <div class="input-group input-group-sm mb-3" data-control-type="range-slider" 
                                                 data-control-name="range-slider-weight" data-control-action="filter" data-path=".itemPrice">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Min : $</span>
                                                </div>
                                                <input type="text" class="form-control" name="min-price" id="min-price" />
                                                <div class="input-group-append input-group-prepend">
                                                    <span class="input-group-text">Max : $</span>
                                                </div>
                                                <input type="text" class="form-control" name="max-price" id="max-price" />
                                            </div>
                                            <div class="price-range">
                                                <div data-type="ui-slider"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="jplist-label" data-type="Page {current} of {pages}" 
                             data-control-type="pagination-info" data-control-name="paging" data-control-action="paging">
                        </div>
                        <div class="jplist-pagination" data-control-type="pagination" 
                             data-control-name="paging" data-control-action="paging">
                        </div>
                    </div>

                    <div class="col-lg-9 col-md-8">
                        <div class="title"><span>Item Listing</span></div>
                        <div class="jplist-search sorting-bar">
                            <div class="mr-3 jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                 data-control-type="sort-drop-down" data-control-name="sort" data-control-action="sort">
                                <ul>
                                    <li><span data-path=".itemName default" data-order="asc" data-type="text">Name Asc</span></li>
                                    <li><span data-path=".itemName" data-order="desc" data-type="text">Name Desc</span></li>
                                    <li><span data-path=".itemPrice" data-order="asc" data-type="text">Price Asc</span></li>
                                    <li><span data-path=".itemPrice" data-order="desc" data-type="text">Price Desc</span></li>
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
                                    ArrayList<Vector> itemList = (ArrayList) request.getAttribute("itemList");
                                    if (!itemList.isEmpty()) {
                                        for (int i = 0; i <= itemList.size() - 1; i++) {
                                            Vector v = itemList.get(i);
                                            String itemImage = String.valueOf(v.get(0));
                                            String itemName = String.valueOf(v.get(1));
                                            String itemCategory = String.valueOf(v.get(2));
                                            String itemSellerID = String.valueOf(v.get(3));
                                            String itemPostedDuration = String.valueOf(v.get(4));
                                            String itemPrice = String.valueOf(v.get(5));
                                %>
                                <div class="col-xl-3 col-md-3 col-6 d-block d-lg-none d-xl-block list-item">
                                    <div class="card card-product">
                                        <div class="card-header" style="font-size: 13px;">
                                            <%= itemSellerID%><br/><%= itemPostedDuration%>
                                        </div>
                                        <div class="card-content">
                                            <div class="card-body">
                                                <div class="img-wrapper">
                                                    <a href="detail.html"><img class="card-img-top" style="max-width: 130px; max-height: 130px;" src="uploads/unify/images/marketplace/item/<%= itemImage%>" /></a>
                                                    <div class="tools tools-left" data-animate-in="fadeInLeft" data-animate-out="fadeOutUp">
                                                        <div class="btn-group-vertical" role="group" aria-label="card-product-tools">
                                                            <button class="btn btn-link btn-sm d-none d-sm-inline-block quick-view"><i class="fa fa-search-plus"></i></button>
                                                            <button class="btn btn-link btn-sm"><i class="fa fa-heart"></i></button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <span class="card-title itemName"><strong><%= itemName%></strong></span>
                                                <span class="card-text"><%= itemCategory%></span>
                                            </div>
                                        </div>
                                        <div class="card-footer text-muted mt-1">
                                            <span class="float-left"><span class="ml-1 itemPrice">$<%= itemPrice%></span></span>
                                            <span class="float-right"><i class="fa fa-heart-o"></i>&nbsp;Like</span>
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
                            <div class="jplist-pagination" data-control-animate-to-top="true" 
                                 data-control-type="pagination" data-control-name="paging" data-control-action="paging">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div id="unifyFooter"></div> -->

            <!-- HIDDEN MODAL -->
            <div class="modal fade" id="QuickViewModal" tabindex="-1" role="dialog" aria-labelledby="QuickViewModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-quickview" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title"><a href="detail.html" class="text-default">U.S. Polo Assn. Green Solid Slim Fit</a></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col col-sm-6">
                                        <div class="owl-carousel owl-theme quickview-slider">
                                            <div><img src="https://mimity31.netlify.com/images/product/polo1.jpg" alt="image"></div>
                                            <div><img src="https://mimity31.netlify.com/images/product/polo2.jpg" alt="image"></div>
                                            <div><img src="https://mimity31.netlify.com/images/product/polo3.jpg" alt="image"></div>
                                        </div>
                                    </div>
                                    <div class="col col-sm-6">
                                        <table class="table">
                                            <tbody>
                                                <tr>
                                                    <td class="border-top-0">Price</td>
                                                    <td class="border-top-0">
                                                        <ul class="list-inline mb-0">
                                                            <li class="list-inline-item"><span class="price">$13.50</span></li>
                                                            <li class="list-inline-item"><del class="text-muted">$15.00</del></li>
                                                            <li class="list-inline-item d-none d-sm-inline-block"><span class="badge custom-badge arrowed-left badge-primary">-10%</span></li>
                                                        </ul>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Quantity</td>
                                                    <td>
                                                        <div class="input-group input-group-sm input-group-qty">
                                                            <div class="input-group-prepend">
                                                                <button class="btn btn-theme btn-down" type="button"><i class="fa fa-minus"></i></button>
                                                            </div>
                                                            <input type="text" class="form-control" aria-label="Quantity" value="1" data-min="1" data-max="10">
                                                            <div class="input-group-append">
                                                                <button class="btn btn-theme btn-up" type="button"><i class="fa fa-plus"></i></button>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Size</td>
                                                    <td>
                                                        <select class="select-dropdown" data-width="65px" data-size="sm">
                                                            <option value="S">S</option>
                                                            <option value="M">M</option>
                                                            <option value="L">L</option>
                                                            <option value="XL">XL</option>
                                                            <option value="XXL">XXL</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr class="d-none d-md-table-row">
                                                    <td>Checkbox</td>
                                                    <td>
                                                        <div class="custom-control custom-checkbox">
                                                            <input type="checkbox" class="custom-control-input" id="quickviewCheck">
                                                            <label class="custom-control-label" for="quickviewCheck">Check this</label>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="d-none d-md-table-row">
                                                    <td class="align-middle">Radio Option</td>
                                                    <td>
                                                        <div class="custom-control custom-radio">
                                                            <input type="radio" id="quickviewOption1" name="quickview-option" class="custom-control-input">
                                                            <label class="custom-control-label" for="quickviewOption1">Yes</label>
                                                        </div>
                                                        <div class="custom-control custom-radio">
                                                            <input type="radio" id="quickviewOption2" name="quickview-option" class="custom-control-input">
                                                            <label class="custom-control-label" for="quickviewOption2">No</label>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <button class="btn btn-sm btn-theme btn-block btn-add-quickview">Add to Cart</button>
                                                        <button class="btn btn-sm btn-outline-theme"><i class="fa fa-heart"></i></button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
        <script src="js/unify/systemuser/webjs/marketplace/ViewItemListingSYSJS.js" type="text/javascript"></script>
        
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