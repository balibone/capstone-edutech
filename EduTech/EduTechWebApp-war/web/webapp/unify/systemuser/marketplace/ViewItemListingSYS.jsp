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
    </head>
    <body>
        <nav class="offcanvas">
            <div class="offcanvas-content">
                <div id="list-menu" class="list-menu list-group" data-children=".sub-menu1">
                    <a href="index.html"><i class="fa fa-fw fa-home"></i> Home</a>
                    <a href="products.html"><i class="fa fa-fw fa-tags"></i> Product List</a>
                    <a href="cart.html"><i class="fa fa-fw fa-shopping-cart"></i> Shopping Cart <span class="badge badge-secondary badge-pill float-right mt-1">4</span></a>
                    <a href="checkout.html"><i class="fa fa-fw fa-check"></i> Checkout</a>
                    <div class="sub-menu1">
                        <a data-toggle="collapse" href="#" data-target="#sub1" role="button" aria-expanded="false" aria-controls="sub1"><i class="fa fa-fw fa-file"></i> Pages</a>
                        <div id="sub1" class="collapse" data-parent="#list-menu" role="tabpanel">
                            <a href="about.html">About Us</a>
                            <a href="blog.html">Blog</a>
                            <a href="blog-detail.html">Blog Detail</a>
                            <a href="compare.html">Compare</a>
                            <a href="contact.html">Contact Us</a>
                            <a href="cart-empty.html">Empty Shopping Cart</a>
                            <a href="404.html">Error 404</a>
                            <a href="faq.html">FAQ</a>
                            <a href="login.html">Login</a>
                            <a href="detail.html">Product Detail</a>
                            <a href="register.html">Register</a>
                            <div class="list-submenu" id="list-submenu" data-children=".sub-menu2">
                                <div class="sub-menu2">
                                    <a data-toggle="collapse" href="#" data-target="#sub3" role="button" aria-expanded="false" aria-controls="sub3">Submenu</a>
                                    <div id="sub3" class="collapse" data-parent="#list-submenu" role="tabpanel">
                                        <a href="#">Submenu 1</a>
                                        <a href="#">Submenu 2</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="sub-menu1">
                        <a data-toggle="collapse" href="#" data-target="#sub2" role="button" aria-expanded="false" aria-controls="sub2"><i class="fa fa-fw fa-user"></i> My Account</a>
                        <div id="sub2" class="collapse" data-parent="#list-menu" role="tabpanel">
                            <a href="account-order.html">Orders</a>
                            <a href="account-profile.html">Profile</a>
                            <a href="account-address.html">Addresses</a>
                            <a href="account-wishlist.html">Wishlist</a>
                            <a href="account-password.html">Change Password</a>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <div class="content-overlay"></div>

        <!-- Top Header -->
        <div class="top-header">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="d-flex justify-content-between">
                            <nav class="nav">
                                <a class="nav-item nav-link d-none d-sm-block" href="#"><i class="fa fa-question-circle"></i> Help</a>
                                <a class="nav-item nav-link d-none d-sm-block" href="#"><i class="fa fa-phone"></i> +123-456-789</a>
                                <a class="nav-item nav-link d-none d-sm-block" href="#"><i class="fa fa-apple"></i> Download App</a>
                            </nav>
                            <ul class="nav">
                                <select class="select-dropdown-nav" data-width="95px">
                                    <option value="en" data-before='<img src="images/en.jpg" class="align-baseline" /> '>English</option>
                                    <option value="fr" data-before='<img src="images/fr.jpg" class="align-baseline" /> '>French</option>
                                </select>
                                <select class="select-dropdown-nav" data-width="70px">
                                    <option value="USD">$ USD</option>
                                    <option value="EUR">? EUR</option>
                                </select>
                                <li class="nav-item d-none d-md-block">
                                    <a href="#" class="nav-link"><i class="fa fa-align-left"></i> Track Order</a>
                                </li>
                                <li class="nav-item d-sm-none">
                                    <a href="#" class="nav-link" data-toggle="modal" data-target="#LoginModal"><i class="fa fa-sign-in"></i> Login</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Middle Header -->
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
                            <a href="index.html" class="d-lg-none"><img alt="Logo" src="images/logo-teal-small.png" class="img-fluid"/></a>
                            <a href="index.html" class="d-none d-lg-flex mb-2 mb-lg-0"><img alt="Logo" src="images/logo-teal.png" class="img-fluid"/></a>
                        </div>
                    </div>
                    <div class="col-8 col-sm-6 col-md-7 col-lg-6">
                        <div class="d-flex align-items-center h-100">
                            <div class="input-group input-group-search">
                                <div class="input-group-prepend d-none d-md-flex">
                                    <select class="select-dropdown">
                                        <option value="all">All Categories</option>
                                        <option value="1">Dresses</option>
                                        <option value="2">Tops</option>
                                        <option value="3">Bottoms</option>
                                        <option value="4">Jackets / Coats</option>
                                        <option value="5">Sweaters</option>
                                        <option value="6">Gym Wear</option>
                                        <option value="7">Others</option>
                                    </select>
                                </div>
                                <input type="text" class="form-control" id="search-input" placeholder="Search here..." aria-label="Search here..." autocomplete="off">
                                <span class="input-group-append">
                                    <button class="btn btn-theme btn-search" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 col-sm-4 col-md-3 col-lg-3 d-none d-sm-block">
                        <div class="d-flex align-items-center h-100 float-right abg-secondary">
                            <div class="btn-group btn-group-sm mr-3" role="group" aria-label="Login Sign Up">
                                <button type="button" class="btn btn-outline-theme" data-toggle="modal" data-target="#LoginModal"><i class="fa fa-sign-in d-none d-lg-inline-block"></i> Login</button>
                                <a class="btn btn-outline-theme" href="register.html" role="button"><i class="fa fa-user-plus d-none d-lg-inline-block"></i> Sign Up</a>
                            </div>
                            <div class="navbar p-0 mr-3" hidden>
                                <div class="dropdown">
                                    <button class="btn btn-light active btn-sm dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Hi, John Thor
                                    </button>
                                    <div class="dropdown-menu dropdown-menu-right smooth" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="account-order.html"><i class="fa fa-fw fa-shopping-bag"></i> Orders</a>
                                        <a class="dropdown-item" href="account-profile.html"><i class="fa fa-fw fa-user"></i> Profile</a>
                                        <a class="dropdown-item" href="account-wishlist.html"><i class="fa fa-fw fa-heart"></i> Wishlist (3)</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#"><i class="fa fa-fw fa-sign-out"></i> Logout</a>
                                    </div>
                                </div>
                            </div>
                            <a href="account-wishlist.html" class="d-none d-xl-block pos-r mr-3"><img src="images/wishlist.png" alt="" width="31"><span class="badge badge-counter badge-theme">3</span></a>
                            <a href="cart.html" class="d-lg-none pos-r"><img src="images/cart.png" alt="" width="31"><span class="badge badge-counter badge-theme">4</span></a>
                            <div class="navbar p-0 d-none d-lg-block"> <!-- Disable Popper.js for Dropdown in Navbar, this will make it easier to animate -->
                                <div class="dropdown dropdown-cart">
                                    <a class="d-block pos-r" href="#" role="button" id="dropdown-cart" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <img src="images/cart.png" alt="" width="31"><span class="badge badge-counter badge-theme">4</span>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-cart smooth" aria-labelledby="dropdown-cart">
                                        <div class="media">
                                            <a href="detail.html"><img class="img-thumbnail d-flex mr-2" src="https://mimity31.netlify.com/images/product/polo1-small.jpg" alt="product" width="50"></a>
                                            <div class="media-body">
                                                <div><a href="detail.html" class="mt-0 text-default">U.S. Polo Assn. Green Solid Slim Fit</a></div>
                                                <small>x1</small> <span class="price font-weight-normal">$13.50</span> <a href="#" class="float-right" data-toggle="tooltip" title="Remove"><span class="badge badge-light"><i class="fa fa-remove"></i></span></a>
                                            </div>
                                        </div>
                                        <div class="media">
                                            <a href="detail.html"><img class="img-thumbnail d-flex mr-2" src="https://mimity31.netlify.com/images/product/polo2-small.jpg" alt="product" width="50"></a>
                                            <div class="media-body">
                                                <div><a href="detail.html" class="mt-0 text-default">U.S. Polo Assn. Red Solid Slim Fit</a></div>
                                                <small>x1</small> <span class="price font-weight-normal">$13.50</span> <a href="#" class="float-right" data-toggle="tooltip" title="Remove"><span class="badge badge-light"><i class="fa fa-remove"></i></span></a>
                                            </div>
                                        </div>
                                        <div class="media">
                                            <a href="detail.html"><img class="img-thumbnail d-flex mr-2" src="https://mimity31.netlify.com/images/product/polo3-small.jpg" alt="product" width="50"></a>
                                            <div class="media-body">
                                                <div><a href="detail.html" class="mt-0 text-default">U.S. Polo Assn. Yellow Solid</a></div>
                                                <small>x1</small> <span class="price font-weight-normal">$13.50</span> <a href="#" class="float-right" data-toggle="tooltip" title="Remove"><span class="badge badge-light"><i class="fa fa-remove"></i></span></a>
                                            </div>
                                        </div>
                                        <div class="media">
                                            <a href="detail.html"><img class="img-thumbnail d-flex mr-2" src="https://mimity31.netlify.com/images/product/polo4-small.jpg" alt="product" width="50"></a>
                                            <div class="media-body">
                                                <div><a href="detail.html" class="mt-0 text-default">Red Tape Blue Solid Slim Fit</a></div>
                                                <small>x1</small> <span class="price font-weight-normal">$13.50</span> <a href="#" class="float-right" data-toggle="tooltip" title="Remove"><span class="badge badge-light"><i class="fa fa-remove"></i></span></a>
                                            </div>
                                        </div>
                                        <div class="dropdown-divider"></div>
                                        Subtotal: <span class="price">$54.00</span>
                                        <div class="dropdown-divider"></div>
                                        <div class="text-center">
                                            <div class="btn-group btn-group-sm" role="group" aria-label="View Cart and Checkout Button">
                                                <a href="#" role="button" class="btn btn-outline-theme"><i class="fa fa-shopping-cart"></i> View Cart</a>
                                                <a href="#" role="button" class="btn btn-outline-theme"><i class="fa fa-check"></i> Checkout</a>
                                            </div>
                                        </div>
                                        <div class="dropdown-divider"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="container">
            <!-- Navigation Bar -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light p-0 d-none d-lg-flex navbar-theme">
                <div class="container">
                    <div class="pos-r d-flex w-100">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item"><a class="nav-link" href="index.html">Home</a></li>
                            <li class="nav-item active"><a class="nav-link" href="products.html">Product List</a></li>
                            <li class="nav-item"><a class="nav-link" href="cart.html">Shopping Cart</a></li>
                            <li class="nav-item"><a class="nav-link" href="checkout.html">Checkout</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Pages
                                </a>
                                <div class="dropdown-menu t-90 smooth" aria-labelledby="navbarDropdownMenuLink">
                                    <a class="dropdown-item" href="about.html">About Us</a>
                                    <a class="dropdown-item" href="blog.html">Blog</a>
                                    <a class="dropdown-item" href="blog-detail.html">Blog Detail</a>
                                    <a class="dropdown-item" href="compare.html">Compare</a>
                                    <a class="dropdown-item" href="contact.html">Contact Us</a>
                                    <a class="dropdown-item" href="cart-empty.html">Empty Shopping Cart</a>
                                    <a class="dropdown-item" href="404.html">Error 404</a>
                                    <a class="dropdown-item" href="faq.html">FAQ</a>
                                    <a class="dropdown-item" href="login.html">Login</a>
                                    <a class="dropdown-item" href="detail.html">Product Detail</a>
                                    <a class="dropdown-item" href="register.html">Register</a>
                                    <div class="dropdown-submenu">
                                        <a href="" class="dropdown-item">My Account</a>
                                        <ul class="dropdown-menu smooth">
                                            <a href="account-order.html" class="dropdown-item">Orders</a>
                                            <a href="account-profile.html" class="dropdown-item">Profile</a>
                                            <a href="account-address.html" class="dropdown-item">Addresses</a>
                                            <a href="account-wishlist.html" class="dropdown-item">Wishlist</a>
                                            <a href="account-password.html" class="dropdown-item">Change Password</a>
                                            <div class="dropdown-submenu">
                                                <a href="" class="dropdown-item">Submenu</a>
                                                <ul class="dropdown-menu smooth">
                                                    <a href="#" class="dropdown-item">Submenu1</a>
                                                    <a href="#" class="dropdown-item">Submenu2</a>
                                                </ul>
                                            </div>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <span class="navbar-text">
                            <a><i class="fa ml-4 fa-truck"></i> Free Shipping</a>
                            <a><i class="fa ml-4 fa-money"></i> Cash on Delivery</a>
                            <a class="d-none d-xl-inline-block"><i class="fa ml-4 fa-lock"></i> Secure Payment</a>
                        </span>
                    </div>
                </div>
            </nav>

            <!-- Breadcrumb -->
            <div class="breadcrumb-container">
                <div class="container">
                    <nav aria-label="breadcrumb" role="navigation">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Products</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <!-- Main Container -->
            <div class="container">
                <div class="row">

                    <!-- Filter Sidebar -->
                    <div class="col-lg-3 col-md-4 mb-3">
                        <button class="btn btn-outline-theme btn-block dropdown-toggle d-md-none" type="button" data-toggle="collapse" data-target="#collapseFilter" aria-expanded="false" aria-controls="collapseFilter">
                            Search Filter
                        </button>
                        <div class="collapse d-md-block pt-3 pt-md-0" id="collapseFilter">
                            <div class="filter-sidebar">
                                <div class="title"><span>Categories</span></div>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="filterCategory1" checked="checked">
                                    <label class="custom-control-label" for="filterCategory1">T-Shirts (10)</span>
                                </div>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="filterCategory2">
                                    <label class="custom-control-label" for="filterCategory2">Polo T-Shirts (11)</span>
                                </div>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="filterCategory3">
                                    <label class="custom-control-label" for="filterCategory3">Round Neck T-Shirts (12)</span>
                                </div>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="filterCategory4">
                                    <label class="custom-control-label" for="filterCategory4">V Neck T-Shirts (13)</span>
                                </div>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="filterCategory5">
                                    <label class="custom-control-label" for="filterCategory5">Hooded T-Shirts (14)</span>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-12">
                                    <div class="filter-sidebar">
                                        <div class="title"><span>Options</span></div>
                                        <ul>
                                            <li>
                                                <select class="select-dropdown" data-width="100%">
                                                    <option value="all">All Options</option>
                                                    <option value="1">Option 1</option>
                                                    <option value="2">Option 2</option>
                                                </select>
                                            </li>
                                            <li>
                                                <select class="select-dropdown" data-width="100%">
                                                    <option value="all">All Options</option>
                                                    <option value="1">Option 1</option>
                                                    <option value="2">Option 2</option>
                                                </select>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-6 col-md-12">
                                    <div class="filter-sidebar">
                                        <div class="title"><span>Options 2</span></div>
                                        <div class="custom-controls-stacked">
                                            <div class="custom-control custom-radio">
                                                <input type="radio" id="filterOption1" name="radio-stacked" class="custom-control-input" checked="checked">
                                                <label class="custom-control-label" for="filterOption1">All Options 2</label>
                                            </div>
                                            <div class="custom-control custom-radio">
                                                <input type="radio" id="filterOption2" name="radio-stacked" class="custom-control-input">
                                                <label class="custom-control-label" for="filterOption2">Option 2.1</label>
                                            </div>
                                            <div class="custom-control custom-radio">
                                                <input type="radio" id="filterOption3" name="radio-stacked" class="custom-control-input">
                                                <label class="custom-control-label" for="filterOption3">Option 2.2</label>
                                            </div>
                                            <div class="custom-control custom-radio">
                                                <input type="radio" id="filterOption4" name="radio-stacked" class="custom-control-input">
                                                <label class="custom-control-label" for="filterOption4">Option 2.3</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-12">
                                    <div class="filter-sidebar">
                                        <div class="title"><span>Price Range</span></div>
                                        <div class="input-group input-group-sm mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text">Min : $</span>
                                            </div>
                                            <input type="text" class="form-control" name="min-price" id="min-price" value="" />
                                            <div class="input-group-append input-group-prepend">
                                                <span class="input-group-text">Max : $</span>
                                            </div>
                                            <input type="text" class="form-control" name="max-price" id="max-price" value="" />
                                        </div>
                                        <div class="price-range">
                                            <div id="price"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-9 col-md-8">
                        <div class="title"><span>Item Listings</span></div>

                        <!-- Sorting Bar -->
                        <div class="sorting-bar">
                            <div class="mr-5" remove-class-on-xs="mr-5" add-class-on-xs="w-100">
                                <span>Sort By</span>
                                <select name="sortby" class="select-dropdown" data-width="180px" data-size="sm">
                                    <option value="recomended">Popular</option>
                                    <option value="best-selling">Best Selling</option>
                                    <option value="low">Low Price &rarr; High Price</option>
                                    <option value="hight">High Price &rarr; High Price</option>
                                </select>
                            </div>
                            <div>
                                <span>Show</span>
                                <select name="show" class="select-dropdown" data-width="60px" data-size="sm">
                                    <option value="8">8</option>
                                    <option value="12">12</option>
                                    <option value="16">16</option>
                                </select> Items per page
                            </div>
                        </div>

                        <!-- Product Listing -->
                        <div class="row equal-height" add-class-on-xs="no-gutters">
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
                            <div class="col-xl-3 col-lg-4 col-6 d-block d-lg-none d-xl-block">
                                <div class="card card-product">
                                    <div class="card-header" style="font-size: 13px;">
                                        <%= itemSellerID %><br/><%= itemPostedDuration %>
                                    </div>
                                    <div class="img-wrapper">
                                        <a href="detail.html"><img class="card-img-top" style="max-width: 130px; max-height: 130px;" src="uploads/unify/images/marketplace/item/<%= itemImage %>" /></a>
                                        <div class="tools tools-left" data-animate-in="fadeInLeft" data-animate-out="fadeOutUp">
                                            <div class="btn-group-vertical" role="group" aria-label="card-product-tools">
                                                <button class="btn btn-link btn-sm d-none d-sm-inline-block quick-view"><i class="fa fa-search-plus"></i></button>
                                                <button class="btn btn-link btn-sm"><i class="fa fa-heart"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text"><a href="detail.html"><%= itemName %></a></p>
                                        <ul class="card-text list-inline">
                                            <li class="list-inline-item">$<%= itemPrice %></li>
                                        </ul>
                                        <div class="rating">
                                            <i class="fa fa-star"></i>
                                            <i class="fa fa-star-half-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <i class="fa fa-star-o"></i>
                                            <a href="#" class="d-none d-sm-inline-block">(2 reviews)</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%      }   %>
                            <%  }   %>
                            
                            <!-- Pagination -->
                            <div class="col-12">
                                <nav aria-label="Product Listing Page" class="d-flex justify-content-center mt-3">
                                    <ul class="pagination">
                                        <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">&laquo;</a></li>
                                        <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">&lsaquo;</a></li>
                                        <li class="page-item active"><span class="page-link" href="#">1</span></li>
                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                                        <li class="page-item"><a class="page-link" href="#">&rsaquo;</a></li>
                                        <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="unifyFooter"></div>

            <!-- Quick View Modal -->
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
        <script src="js/unify/systemuser/basejs/instantsearch-v1.11.15.min.js" type="text/javascript"></script>
        
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/ViewItemListingSYSJS.js" type="text/javascript"></script>
    </body>
</html>