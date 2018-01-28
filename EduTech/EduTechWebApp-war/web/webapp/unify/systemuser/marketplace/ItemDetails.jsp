<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify - Marketplace(Item) Details</title>

        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/unify/systemuser/baselayout/bootstrap_v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- JavaScript (JS) -->
        <script src="js/unify/systemuser/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap_v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/ItemDetailsJS.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="header"></div>
        <div class="container">
            <div class="row">
                <div id="sideBar" class="col-lg-3"><div style="padding-right: 20px;"></div></div>
                <div class="col-lg-9">
                    <div style="padding-left: 20px;">
                        <div class="card mt-4">
                            <img class="card-img-top img-fluid" src="../images/viewTextbook.jpeg" alt="">
                            <div class="card-body">
                                <h3 class="card-title d-inline">LSM1001 Textbook</h3>
                                <h4>$24.99</h4>
                                <p class="card-text">2nd Edition Textbook for LSM1001</p> 
                                <ul class="list-inline">
                                    <li class="list-inline-item">Seller: <a href="../dashboard/index.html">Marco Polo Junior</a></li>
                                    <li class="list-inline-item pull-right">Location: School of Computing COM1</li>
                                </ul>
                                <p class="card-text d-inline">Willing To Trade With: </p><button type="button" class="btn btn-outline-primary" disabled>MA1312 Textbook</button>
                            </div>
                        </div>

                        <div class="card card-outline-secondary my-4">
                            <div class="card-header">
                                Product Reviews
                                <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
                                4.0 stars
                            </div>
                            <div class="card-body">
                                <p>Nice seller, but unfortunately couldn't make a deal with him :)</p>
                                <small class="text-muted">Posted by Anonymous on 3/1/17</small>
                                <hr>
                                <p>Traded with seller for an MA1312 Textbook. Friendly guy, even gave me tips on how to study for the module.</p>
                                <small class="text-muted">Posted by Anonymous on 3/1/17</small>
                                <hr>
                                <hr>
                                <a href="#" class="btn btn-success">Leave a Review</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>
