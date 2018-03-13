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
        <title>Unify Marketplace - New Item Listing</title>

        <link href="css/unify/systemuser/baselayout/wizardform/bootstrap-v3.3.5.min.css" rel="stylesheet" />
        <link href="css/unify/systemuser/baselayout/wizardform/gsdk-base.css" rel="stylesheet" />
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/weblayout/marketplace/NewItemListingSYSCSS.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="wizard-container">
            <div class="card wizard-card ct-wizard-blue" id="wizard">
                <form id="newItemListingForm" action="MarketplaceSysUser" method="POST" enctype="multipart/form-data" target="_parent">
                    <ul>
                        <li><a href="#about" data-toggle="tab">Step 1<br/><small>Enter Item Information</small></a></li>
                        <li><a href="#account" data-toggle="tab">Step 2<br/><small>Select Item Category</small></a></li>
                        <li><a href="#address" data-toggle="tab">Step 3<br/><small>Enter Trade Information</small></a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane" id="about">
                            <div class="row">
                                <div class="col-sm-4 col-sm-offset-1">
                                    <div class="picture-container">
                                        <div class="picture">
                                            <img src="/wizard/assets/img/default-avatar.png" class="picture-src" id="wizardPicturePreview" />
                                            <input type="file" id="wizard-picture" name="itemImage" />
                                        </div>
                                        <h6>Choose Item Image</h6>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <label>Item Name <small>(required)</small></label>
                                        <input name="itemName" type="text" class="form-control" placeholder="e.g. IS2102 Textbook" />
                                    </div>
                                    <div class="form-group">
                                        <label>Item Condition <small>(required)</small></label>
                                        <select name="itemCondition" class="form-control">
                                            <option value="" selected="selected" disabled="disabled">- Please Select -</option>
                                            <option value="New">New</option>
                                            <option value="Used">Used</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Item Price <small>(required)</small></label>
                                        <input name="itemPrice" type="text" class="form-control" placeholder="e.g. 5.60 (without the $)" />
                                    </div>
                                </div>
                                <div class="col-sm-10 col-sm-offset-1">
                                    <div class="form-group">
                                        <label>Item Description <small>(required)</small></label>
                                        <textarea name="itemDescription" rows="3" class="form-control" placeholder="Enter the item description"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="account">
                            <h4 class="info-text">Choose the Item Category</h4>
                            <div class="row">
                                <div class="col-sm-10 col-sm-offset-1 owl-carousel owl-theme">
                                    <%
                                        ArrayList<Vector> itemCategoryListSYS = (ArrayList) request.getAttribute("itemCategoryListSYS");
                                        if (!itemCategoryListSYS.isEmpty()) {
                                            for (int i = 0; i <= itemCategoryListSYS.size() - 1; i++) {
                                                Vector v = itemCategoryListSYS.get(i);
                                                String categoryImage = String.valueOf(v.get(0));
                                                String categoryID = String.valueOf(v.get(1));
                                                String categoryName = String.valueOf(v.get(2));
                                    %>
                                    <div id="<%= categoryID%>" class="choice" data-toggle="wizard-radio">
                                        <input type="radio" name="itemCategory" />
                                        <div class="icon"><img src="uploads/unify/images/common/category/<%= categoryImage%>" style="width:85px;height:85px;padding-top:25px;margin:0 auto;" /></div>
                                        <h6><%= categoryName%></h6>
                                    </div>
                                    <%      }   %>
                                    <%  }   %>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="address">
                            <div class="row">
                                <div class="col-sm-5 col-sm-offset-1">
                                    <div id="mapdiv" style="width: auto; height: 275px;"></div>
                                </div>
                                <div class="col-sm-5">
                                    <div class="form-group">
                                        <label>Trade Location <small>(required)</small></label>
                                        <input name="tradeLocation" id="tradeLocation" type="text" class="form-control" placeholder="e.g. Central Library" />
                                        <div id="searchResults"></div>
                                    </div>
                                    <div class="form-group">
                                        <label>Trade Information <small>(required)</small></label>
                                        <textarea name="tradeInformation" rows="5" class="form-control" placeholder="e.g. Please meet at the foyer."></textarea>
                                    </div>
                                </div>
                                <input type="hidden" name="pageTransit" value="createItemListingSYS" />
                                <input type="hidden" name="hiddenTradeLat" id="hiddenTradeLat" />
                                <input type="hidden" name="hiddenTradeLong" id="hiddenTradeLong" />
                                <input type="hidden" name="hiddenCategoryID" id="hiddenCategoryID" />
                            </div>
                        </div>
                    </div>
                    <div class="wizard-footer">
                        <div class="pull-right">
                            <input type='button' class='btn btn-next btn-fill btn-primary btn-wd btn-sm' name='next' value='Next' />
                            <input type='button' class='btn btn-finish btn-fill btn-success btn-wd btn-sm finish' name='finish' value='Create Item Listing' />

                        </div>
                        <div class="pull-left">
                            <input type='button' class='btn btn-previous btn-fill btn-primary btn-wd btn-sm' name='previous' value='Previous' />
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </form>
            </div>
        </div>
    </body>

    <script src="js/unify/systemuser/basejs/wizardform/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/wizardform/bootstrap-v3.3.5.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/wizardform/jquery.bootstrap.wizard.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/wizardform/jquery.validate-v1.14.0.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/wizardform/newitemlisting-wizard.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
    <script src="js/unify/systemuser/webjs/marketplace/NewItemListingSYSJS.js" type="text/javascript"></script>
</html>
