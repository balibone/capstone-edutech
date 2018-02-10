<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Unify - Item Details</title>

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
        <div id="container">
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
                                        <div><img src="https://mimity31.netlify.com/images/product/polo1.jpg" /></div>
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

        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
    </body>
</html>