<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - New Contact</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/basetemplate.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/CommonCSS.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/easy-autocomplete/easy-autocomplete.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/easy-autocomplete/easy-autocomplete.min.css" rel="stylesheet" type="text/css">
        <style type="text/css">
            .supplierInfo { display: none; }
        </style>
        
        <!-- Java Script (JS) -->
        <script src="js/commoninfrastructure/basejs/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/metisMenu.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.newsTicker.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/CommonJS.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/NewContactJS.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/easy-autocomplete/jquery.easy-autocomplete.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/easy-autocomplete/jquery.easy-autocomplete.min.js" type="text/javascript"></script>
    </head>
    <body onload="establishTime(); setInterval('updateTime()', 1000)">
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0;">
                <a class="navbar-brand" href="CommonInfra?pageTransit=goToDashboard">
                    SG MapleStore
                </a>
                
                <!-- Top Navigation -->
                <div id="pageAnnouncement">
                    <div class="ccr-last-update">
                        <div class="update-ribon"><strong>Notification:</strong></div>
                        <span class="update-ribon-right"></span>
                        <div class="update-news-text">
                            <ul id="latestUpdate" class="newsticker">
                                <li><strong>System maintenance will be carried out at 15 Jan 2018, 00:00:00 (SGT).</strong></li>
                                <li><strong>Welcome to SG MapleStore!</strong></li>
                                <li><strong>Stay tune to our latest update via "Latest System Update".</strong></li>
                            </ul>
                        </div>
                        <div class="update-right-border">
                            <i class="fa fa-clock-o"></i>&nbsp;&nbsp;<strong><span id="clock"></span></strong>
                        </div>
                    </div>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li><a href="CommonInfra?pageTransit=goToProfile"><i class="fa fa-user"></i>&nbsp;&nbsp;My Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="CommonInfra?pageTransit=goToLogout"><i class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout</a></li>
                </ul>
            
                <!-- Left Navigation -->
                <div class="navbar-default sidebar">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <table border="0" style="margin: 12px 0px 12px 5px;" width="95%">
                                    <tr>
                                        <td rowspan="2" style="text-align: right;"><img src="images/ProfileImage.png" /></td>
                                        <td colspan="2" valign="middle" style="padding-left: 10px;"><strong>Hello <%= request.getAttribute("employeeNRIC")%>!</strong></td>
                                    </tr>
                                    <tr>
                                        <td style="padding-left: 10px;">
                                            <form action="CommonInfra" method="POST">
                                                <input type="hidden" name="pageTransit" value="goToProfile"/>
                                                <button type="submit" class="btn btn-success btn-xs">My Profile</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="CommonInfra" method="POST">
                                                <input type="hidden" name="pageTransit" value="goToProfile"/>
                                                <button type="submit" class="btn btn-primary btn-xs">Contact Us</button>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </li>
                            <li><a href="CommonInfra?pageTransit=goToDashboard"><i class="fa fa-home fa-fw"></i>&nbsp;&nbsp;Dashboard</a></li>
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i>&nbsp;&nbsp;Contacts<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="CommonInfra?pageTransit=goToContactList"><i class="fa fa-address-book fa-fw"></i>&nbsp;&nbsp;Contact List</a></li>
                                    <li><a href="CommonInfra?pageTransit=goToEmployeeList"><i class="fa fa-address-book-o fa-fw"></i>&nbsp;&nbsp;Employee List</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-book fa-fw"></i>&nbsp;&nbsp;Inventory Items<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="Warehouse?pageTransit=goToItemList"><i class="fa fa-cube fa-fw"></i>&nbsp;&nbsp;Items</a></li>
                                    <li><a href="Warehouse?pageTransit=goToItemCategoryList"><i class="fa fa fa-cubes fa-fw"></i>&nbsp;&nbsp;Item Categories</a></li>
                                    <li><a href="Warehouse?pageTransit=goToCompositeItemList"><i class="fa fa-cubes fa-fw"></i>&nbsp;&nbsp;Composite Items</a></li>
                                    <li><a href="Warehouse?pageTransit=goToInventoryLogList"><i class="fa fa-book fa-fw"></i>&nbsp;&nbsp;Inventory Log</a></li>
                                </ul>
                            </li>
                            <li>&nbsp;</li>
                            <li><a href="Warehouse?pageTransit=goToSalesOrderList"><i class="fa fa-shopping-cart fa-fw"></i>&nbsp;&nbsp;Sales Orders</a></li>
                            <li><a href="Warehouse?pageTransit=goToPackageList"><i class="fa fa-cube fa-fw"></i>&nbsp;&nbsp;Packages</a></li>
                            <li><a href="Warehouse?pageTransit=goToInvoiceList"><i class="fa fa-file-text fa-fw"></i>&nbsp;&nbsp;Invoices</a></li>
                            <li><a href="Warehouse?pageTransit=goToPackageList"><i class="fa fa-shopping-bag fa-fw"></i>&nbsp;&nbsp;Purchase Orders</a></li>
                            <li><a href="Warehouse?pageTransit=goToPackageList"><i class="fa fa-list-alt fa-fw"></i>&nbsp;&nbsp;Bills</a></li>
                            <li>&nbsp;</li>
                            <li><a href="Warehouse?pageTransit=goToPackageList"><i class="fa fa-line-chart fa-fw"></i>&nbsp;&nbsp;Reports</a></li>
                        </ul>
                    </div>
                </div>
            </nav>

            <!-- Content Space -->
            <div id="page-wrapper">
                <div class="contentFill contentLayout">
                    <h3>New Contact</h3>
                </div>
                <div class="contentFill scroll-y scrollbox">
                    <%
                        String successMessage = (String)request.getAttribute("successMessage");
                        if (successMessage != null) {
                    %>
                    <div class="alert alert-success" id="successPanel" style="margin: 20px 20px 0 0;">
                        <button type="button" class="close" id="closeSuccess">&times;</button>
                        <%= successMessage %>
                    </div>
                    <%  } %>
                    <%
                        String errorMessage = (String)request.getAttribute("errorMessage");
                        if (errorMessage != null) {
                    %>
                    <div class="alert alert-danger" id="errorPanel" style="margin: 20px 20px 0 0;">
                        <button type="button" class="close" id="closeError">&times;</button>
                        <%= errorMessage %>
                    </div>
                    <%  } %>
                    <form action="CommonInfra" method="POST" class="form-horizontal zi-txn-form">
                        <div class="row">
                            <div class="col-md-9 col-sm-9, col-xs-9">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Contact Name</label>
                                    <div class="col-md-2">
                                        <select class="form-control" name="contactSalutation">
                                            <option value="">-- Salutation --</option>
                                            <option value="Miss">Miss</option>
                                            <option value="Mr.">Mr.</option>
                                            <option value="Mrs.">Mrs.</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3"><input class="form-control" name="contactFirstName" placeholder="First Name" /></div>
                                    <div class="col-md-3"><input class="form-control" name="contactLastName" placeholder="Last Name" /></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Contact Email</label>
                                    <div class="col-md-6"><input class="form-control" name="contactEmail" /></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Contact Phone</label>
                                    <div class="col-md-6"><input class="form-control" name="contactPhone" /></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Contact Type</label>
                                    <div class="col-md-9">
                                        <div class="radio-inline">
                                            <label class="cursor-pointer"><input type="radio" name="contactType" id="customerRadio" value="Customer" />&nbsp;Customer</label>
                                        </div>
                                        <div class="radio-inline">
                                            <label class="cursor-pointer"><input type="radio" name="contactType" id="supplierRadio" value="Supplier" />&nbsp;Supplier</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <ul role="tablist" class="nav section-nav nav-pills nav-pills-flat">
                                <li class="active"><a href="#addressPane" role="tab" data-toggle="tab">Address</a></li>
                                <li><a href="#credentialsPane" role="tab" data-toggle="tab">Credentials</a></li>
                                <li><a href="#notesPane" role="tab" data-toggle="tab">Notes</a></li>
                            </ul>
                        </div>
                        <div class="tab-content">
                            <div class="active" id="addressPane">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-12 text-muted"><strong>Billing Address</strong></label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Attn.</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactBillingAttn" name="contactBillingAttn" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Address</label>
                                            <div class="col-md-7"><textarea class="form-control" id="contactBillingAddress" name="contactBillingAddress"></textarea></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">City</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactBillingCity" name="contactBillingCity" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">State</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactBillingState" name="contactBillingState" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Zip Code</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactBillingZipCode" name="contactBillingZipCode" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Country</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactBillingCountry" name="contactBillingCountry" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Fax</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactBillingFax" name="contactBillingFax" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Phone</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactBillingPhone" name="contactBillingPhone" /></div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-12 text-muted">
                                                <strong>Shipping Address&nbsp;&nbsp;</strong>
                                                <a href="javascript:copyBillingAdd();"><i class="fa fa-copy fa-fw"></i>&nbsp;Copy billing address</a>
                                            </label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Attn.</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactShippingAttn" name="contactShippingAttn" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Address</label>
                                            <div class="col-md-7"><textarea class="form-control" id="contactShippingAddress" name="contactShippingAddress"></textarea></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">City</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactShippingCity" name="contactShippingCity" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">State</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactShippingState" name="contactShippingState" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Zip Code</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactShippingZipCode" name="contactShippingZipCode" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Country</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactShippingCountry" name="contactShippingCountry" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Fax</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactShippingFax" name="contactShippingFax" /></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Phone</label>
                                            <div class="col-md-7"><input type="text" class="form-control" id="contactShippingPhone" name="contactShippingPhone" /></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="credentialsPane">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label col-md-12 text-muted"><strong>System Credentials</strong></label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Username</label>
                                            <div class="col-md-7"><input type="text" class="form-control" name="contactUsername" /></div>
                                        </div>
                                        <div class="form-group supplierInfo">
                                            <label class="control-label col-md-12 text-muted"><strong>Supplier Information</strong></label>
                                        </div>
                                        <div class="form-group supplierInfo">
                                            <label class="control-label col-md-4">Company Name</label>
                                            <div class="col-md-7"><input type="text" class="form-control" name="suppCompanyName" /></div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group"><label class="control-label col-md-12 text-muted">&nbsp;</label></div>
                                        <div class="form-group">
                                            <label class="control-label col-md-4">Password</label>
                                            <div class="col-md-7"><input type="password" class="form-control" name="contactPassword" /></div>
                                        </div>
                                        <div class="form-group supplierInfo"><label class="control-label col-md-12 text-muted">&nbsp;</label></div>
                                        <div class="form-group supplierInfo">
                                            <label class="control-label col-md-4">Billing Account No.</label>
                                            <div class="col-md-7"><input type="text" class="form-control" name="suppBillAccNo" /></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="notesPane">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label class="control-label">Notes<span class="text-muted">&nbsp;(For Internal Use)</span></label>
                                        <br/><br/>
                                        <textarea rows="4" name="contactNotes" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr class="bdr-light" />
                        <div class="row">
                            <div class="btn-toolbar col-md-5">
                                <input type="hidden" name="pageTransit" value="createContact" />
                                <button class="btn btn-primary" type="submit" value="submit">Create Contact</button>&nbsp;&nbsp;
                                <button class="btn btn-default" onclick="location.href='CommonInfra?pageTransit=goToDashboard'" type="button">Cancel</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>