<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify - Marketplace(Item) Listing</title>

        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">

        <!-- JavaScript (JS) -->
        <script src="js/unify/systemuser/basejs/jquery-v3.1.0.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/ItemListingJS.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="header"></div>
        <div class="container">
            <div class="row">
                <div id="sideBar" class="col-lg-3"><div style="padding-right: 35px;"></div></div>
                <div class="col-lg-9">
                    <div style="padding-left: 35px;">
                        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner" role="listbox">
                                <div class="carousel-item active">
                                    <img class="d-block img-fluid" src="../images/books.jpeg" alt="First slide">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block img-fluid" src="../images/equipment.jpeg" alt="Second slide">
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block img-fluid" src="../images/stationery.jpeg" alt="Third slide">
                                </div>
                            </div>
                            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>		  
                        <div class="row">
                            <div class="col-lg-9 mb-2">
                                <form class="form-inline">
                                    <div class="input-group mb-2 mr-sm-2">
                                        <input type="text" style="width:500px" class="form-control" placeholder="Search Listings">
                                    </div>
                                    <button type="submit" class="btn btn-primary mb-2">Submit</button>
                                </form>
                            </div>
                        </div>
                        <div class="row">			
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="MarketplaceSysUser?pageTransit=goToItemDetails"><img class="card-img-top" src="../images/textbook.jpeg" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="MarketplaceSysUser?pageTransit=goToItemDetails">LSM1001 Textbook</a>
                                        </h4>
                                        <h5>$12.99</h5>
                                        <p class="card-text">LSM1001 textbook, pre-loved</p>
                                    </div>
                                    <div class="card-footer">
                                        <p>&#9733; &#9733; &#9733; &#9733; &#9734;</p>
                                        <button type="button" class="btn btn-primary btn-sm">Chat With Seller</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="MarketplaceSysUser?pageTransit=goToItemDetails"><img class="card-img-top" src="../images/gc.png" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="MarketplaceSysUser?pageTransit=goToItemDetails">TI-84 Graphic Calculator </a>
                                        </h4>
                                        <h5>$24.99</h5>
                                        <p class="card-text">Used GC for sale!</p>
                                    </div>
                                    <div class="card-footer">
                                        <p>&#9733; &#9733; &#9733; &#9733; &#9734;</p>
                                        <button type="button" class="btn btn-primary btn-sm">Chat With Seller</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="MarketplaceSysUser?pageTransit=goToItemDetails"><img class="card-img-top" src="../images/pump.jpeg" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="MarketplaceSysUser?pageTransit=goToItemDetails">Beto Ball Pump</a>
                                        </h4>
                                        <h5>$10.99</h5>
                                        <p class="card-text">Brand New In Box, was a birthday gift but didn't use.</p>
                                    </div>
                                    <div class="card-footer">
                                        <p>&#9733; &#9733; &#9733; &#9733; &#9734;</p>
                                        <button type="button" class="btn btn-primary btn-sm">Chat With Seller</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="MarketplaceSysUser?pageTransit=goToItemDetails"><img class="card-img-top" src="../images/gc.png" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="MarketplaceSysUser?pageTransit=goToItemDetails">TI-84 Graphic Calculator </a>
                                        </h4>
                                        <h5>$24.99</h5>
                                        <p class="card-text">Used GC for sale!</p>
                                    </div>
                                    <div class="card-footer">
                                        <p>&#9733; &#9733; &#9733; &#9733; &#9734;</p>
                                        <button type="button" class="btn btn-primary btn-sm">Chat With Seller</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="MarketplaceSysUser?pageTransit=goToItemDetails"><img class="card-img-top" src="../images/textbook.jpeg" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="MarketplaceSysUser?pageTransit=goToItemDetails">LSM1001 Textbook</a>
                                        </h4>
                                        <h5>$12.99</h5>
                                        <p class="card-text">LSM1001 textbook, pre-loved</p>
                                    </div>
                                    <div class="card-footer">
                                        <p>&#9733; &#9733; &#9733; &#9733; &#9734;</p>
                                        <button type="button" class="btn btn-primary btn-sm">Chat With Seller</button>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <a href="MarketplaceSysUser?pageTransit=goToItemDetails"><img class="card-img-top" src="../images/pump.jpeg" alt=""></a>
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="MarketplaceSysUser?pageTransit=goToItemDetails">Beto Ball Pump</a>
                                        </h4>
                                        <h5>$10.99</h5>
                                        <p class="card-text">Brand New In Box, was a birthday gift but didn't use.</p>
                                    </div>
                                    <div class="card-footer">
                                        <p>&#9733; &#9733; &#9733; &#9733; &#9734;</p>
                                        <button type="button" class="btn btn-primary btn-sm">Chat With Seller</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>
