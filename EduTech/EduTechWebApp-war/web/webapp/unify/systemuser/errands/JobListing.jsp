<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify - Errands(Help) Listing</title>

        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">

        <!-- JavaScript (JS) -->
        <script src="js/unify/systemuser/basejs/jquery-v3.1.0.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/errands/JobListingJS.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="header"></div>
        <div class="container">
            <div class="row">
                <div id="sideBar" class="col-lg-3"><div style="padding-right: 20px;"></div></div>
                <div class="col-md-9 col-lg-9">
                    <div style="padding-left: 20px;">
                        <div class="table-responsive" style="overflow-y:auto;">
                            <br>
                            <h3 class="text-left">Job Listings</h3>
                            <form class="form-inline">
                                <div class="input-group mb-2 mr-sm-2">
                                    <input type="text" style="width:500px" class="form-control" placeholder="Search Listings">
                                </div>
                                <button type="submit" class="btn btn-primary mb-2">Submit</button>
                            </form>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th><h5>Image</h5></th>
                                        <th><h5>Job Description</h5></th>
                                        <th class="text-center"><h5>Rate</h5></th>
                                        <th class="text-center"><h5>Category</h5></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="../images/products/tables.jpeg" alt="image description"></td>
                                        <td>
                                            <a href="ErrandsSysUser?pageTransit=goToJobDetails"><h5 class="title">Table Shifting</h5></a>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> School of Computing, COM1, SR1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 27, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Moving & Packing</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$5/hr</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://www.mcdonalds.com/content/dam/usa/nutrition/items/evm/h-mcdonalds-Double-Quarter-Pounder-with-Cheese-Extra-Value-Meals.png" alt="image description"></td>
                                        <td>
                                            <h5 class="title">McDonald's Delivery</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> PGP Block B</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Jan 30, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 AM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Food Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$3</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://s3-media4.fl.yelpcdn.com/bphoto/q3O8xKFjx1oDM_hZ1egJDQ/ls.jpg" alt="image description"></td>
                                        <td>
                                            <h5 class="title">Pass parcel to friend</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> UTown Auditorium 1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 1, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:30 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Item Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$4</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="../images/products/tables.jpeg" alt="image description"></td>
                                        <td>
                                            <a href="../help/viewHelp.html"><h5 class="title">Table Shifting</h5></a>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> School of Computing, COM1, SR1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 27, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Moving & Packing</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$5/hr</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://www.mcdonalds.com/content/dam/usa/nutrition/items/evm/h-mcdonalds-Double-Quarter-Pounder-with-Cheese-Extra-Value-Meals.png" alt="image description"></td>
                                        <td>
                                            <h5 class="title">McDonald's Delivery</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> PGP Block B</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Jan 30, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 AM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Food Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$3</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://s3-media4.fl.yelpcdn.com/bphoto/q3O8xKFjx1oDM_hZ1egJDQ/ls.jpg" alt="image description"></td>
                                        <td>
                                            <h5 class="title">Pass parcel to friend</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> UTown Auditorium 1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 1, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:30 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Item Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$4</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="../images/products/tables.jpeg" alt="image description"></td>
                                        <td>
                                            <a href="../help/viewHelp.html"><h5 class="title">Table Shifting</h5></a>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> School of Computing, COM1, SR1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 27, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Moving & Packing</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$5/hr</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://www.mcdonalds.com/content/dam/usa/nutrition/items/evm/h-mcdonalds-Double-Quarter-Pounder-with-Cheese-Extra-Value-Meals.png" alt="image description"></td>
                                        <td>
                                            <h5 class="title">McDonald's Delivery</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> PGP Block B</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Jan 30, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 AM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Food Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$3</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://s3-media4.fl.yelpcdn.com/bphoto/q3O8xKFjx1oDM_hZ1egJDQ/ls.jpg" alt="image description"></td>
                                        <td>
                                            <h5 class="title">Pass parcel to friend</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> UTown Auditorium 1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 1, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:30 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Item Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$4</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr><tr>							
                                        <td>
                                            <img width="80px" height="auto" src="../images/products/tables.jpeg" alt="image description"></td>
                                        <td>
                                            <a href="../help/viewHelp.html"><h5 class="title">Table Shifting</h5></a>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> School of Computing, COM1, SR1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 27, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Moving & Packing</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$5/hr</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://www.mcdonalds.com/content/dam/usa/nutrition/items/evm/h-mcdonalds-Double-Quarter-Pounder-with-Cheese-Extra-Value-Meals.png" alt="image description"></td>
                                        <td>
                                            <h5 class="title">McDonald's Delivery</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> PGP Block B</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Jan 30, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:00 AM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Food Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$3</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                    <tr>							
                                        <td>
                                            <img width="80px" height="auto" src="https://s3-media4.fl.yelpcdn.com/bphoto/q3O8xKFjx1oDM_hZ1egJDQ/ls.jpg" alt="image description"></td>
                                        <td>
                                            <h5 class="title">Pass parcel to friend</h5>
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span> UTown Auditorium 1</span><br>
                                            <i class="fa fa-calendar" aria-hidden="true"></i><span> Feb 1, 2018</span>
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span> 12:30 PM</span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>Item Delivery</b></span>
                                        </td>
                                        <td>
                                            <span class="d-flex justify-content-center"><b>$4</b></span>
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm">Chat With Poster</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>					
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer"></div>
    </body>
</html>
