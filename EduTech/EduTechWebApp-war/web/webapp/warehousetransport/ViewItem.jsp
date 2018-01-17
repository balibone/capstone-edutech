<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - View Item</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/basetemplate.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/CommonCSS.css" rel="stylesheet" type="text/css">
        <link href="css/warehousetransport/weblayout/NewCompositeItemCSS.css" rel="stylesheet" type="text/css">
        
        <!-- Java Script (JS) -->
        <script src="js/commoninfrastructure/basejs/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/metisMenu.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.newsTicker.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/CommonJS.js" type="text/javascript"></script>
        <script src="js/warehousetransport/webjs/NewItemJS.js" type="text/javascript"></script>
    </head>
    <body onload="establishTime(); setInterval('updateTime()', 1000)">
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0;">
                <a class="navbar-brand" href="SGMapleStore?pageTransit=goToDashboard">
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
                    <li><a href="SGMapleStore?pageTransit=goToProfile"><i class="fa fa-user"></i>&nbsp;&nbsp;My Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="SGMapleStore?pageTransit=goToLogout"><i class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout</a></li>
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
                                            <form action="SGMapleStore" method="POST">
                                                <input type="hidden" name="pageTransit" value="goToProfile"/>
                                                <button type="submit" class="btn btn-success btn-xs">My Profile</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="SGMapleStore" method="POST">
                                                <input type="hidden" name="pageTransit" value="goToProfile"/>
                                                <button type="submit" class="btn btn-primary btn-xs">Contact Us</button>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </li>
                            <li><a href="SGMapleStore?pageTransit=goToDashboard"><i class="fa fa-home fa-fw"></i>&nbsp;&nbsp;Dashboard</a></li>
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i>&nbsp;&nbsp;Contacts<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="SGMapleStore?pageTransit=goToContactList"><i class="fa fa-address-book fa-fw"></i>&nbsp;&nbsp;Contact List</a></li>
                                    <li><a href="SGMapleStore?pageTransit=goToEmployeeList"><i class="fa fa-address-book-o fa-fw"></i>&nbsp;&nbsp;Employee List</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-book fa-fw"></i>&nbsp;&nbsp;Inventory Items<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="SGMapleStore?pageTransit=goToItemList"><i class="fa fa-cube fa-fw"></i>&nbsp;&nbsp;Items</a></li>
                                    <li><a href="SGMapleStore?pageTransit=goToItemCategoryList"><i class="fa fa fa-cubes fa-fw"></i>&nbsp;&nbsp;Item Categories</a></li>
                                    <li><a href="SGMapleStore?pageTransit=goToCompositeItemList"><i class="fa fa-cubes fa-fw"></i>&nbsp;&nbsp;Composite Items</a></li>
                                    <li><a href="SGMapleStore?pageTransit=goToInventoryLogList"><i class="fa fa-book fa-fw"></i>&nbsp;&nbsp;Inventory Log</a></li>
                                </ul>
                            </li>
                            <li>&nbsp;</li>
                            <li><a href="SGMapleStore?pageTransit=goToSalesOrderList"><i class="fa fa-shopping-cart fa-fw"></i>&nbsp;&nbsp;Sales Orders</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-cube fa-fw"></i>&nbsp;&nbsp;Packages</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToInvoiceList"><i class="fa fa-file-text fa-fw"></i>&nbsp;&nbsp;Invoices</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-shopping-bag fa-fw"></i>&nbsp;&nbsp;Purchase Orders</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-list-alt fa-fw"></i>&nbsp;&nbsp;Bills</a></li>
                            <li>&nbsp;</li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-line-chart fa-fw"></i>&nbsp;&nbsp;Reports</a></li>
                        </ul>
                    </div>
                </div>
            </nav>

            <!-- Content Space -->
            <div id="page-wrapper">
                <div class="contentFill contentLayout">
                    <h3>View Item</h3>
                </div>
                <div class="contentFill scroll-y scrollbox">
                    <%
                        //Extracting field values from ArrayList passed from servlet to jsp.
                        ArrayList itemDetails = (ArrayList)request.getAttribute("itemDetails");
                        String itemName, itemSKU, vendorID, vendorProductCode, itemDescription, itemImageDirPath;
                        itemName = itemSKU = vendorID = vendorProductCode = itemDescription = itemImageDirPath = "";
                        Double itemSellingPrice, itemQuantity, itemReorderLevel;
                        itemSellingPrice= itemQuantity= itemReorderLevel=0.0;
                        if(!itemDetails.isEmpty()){
                            itemName = (String)itemDetails.get(0);
                            itemSKU = (String)itemDetails.get(1);
                            vendorID = (String)itemDetails.get(2);
                            vendorProductCode = (String)itemDetails.get(3);
                            itemSellingPrice = (Double)itemDetails.get(4);
                            itemQuantity = (Double) itemDetails.get(5);
                            itemReorderLevel = (Double) itemDetails.get(6);
                            itemDescription = (String) itemDetails.get(7);
                            itemImageDirPath = (String) itemDetails.get(8);
                        }
                    %>
                    <form action="SGMapleStore" method="POST" class="form-horizontal zi-txn-form" enctype="multipart/form-data">
                        <div class="zi-txn-form">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Name</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=itemName%>" class="form-control" name="itemName" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">SKU</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=itemSKU%>" class="form-control" name="itemSKU" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Vendor ID (Contact ID)</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=vendorID%>" class="form-control" name="vendorID" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Vendor Product Code</label>
                                    <div class="col-md-5">
                                        <input type="text" readonly value="<%=vendorProductCode%>"  class="form-control" name="vendorProductCode" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label required">Selling Price</label>
                                    <div class="col-md-2">
                                        <input type="text" readonly value="<%=itemSellingPrice%>"  class="form-control" name="itemSellingPrice" />
                                    </div>
                                    <div class="col-md-1"><br></div>
                                    <label class="col-md-3 control-label required">In Stock Quantity</label>
                                    <div class="col-md-2">
                                        <input type="text" readonly value="<%=itemQuantity.intValue()%>"  class="form-control" name="itemQuantity" />
                                    </div>
                                </div>
                                <div class="form-group">                                 
                                    <label class="col-md-2 control-label required">Reorder Level</label>
                                    <div class="col-md-2">
                                        <input type="text" readonly value="<%=itemReorderLevel.intValue()%>"  class="form-control" name="itemReorderLevel" />
                                    </div>
                                </div>
                                <div class="form-group">                                 
                                    <label class="col-md-2 control-label required">Item Description</label>
                                    <div class="col-md-9">
                                        <input type="text" readonly value="<%=itemDescription%>"  class="form-control input-lg" placeholder="Insert description of item" name="itemDescription"/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">                               
                                    <img class="img-responsive" src="uploads/images/Items/<%= itemImageDirPath%>"/>
                            </div>
                            <div class="col-md-8">
                            <button type="button" class="btn btn-default" onclick="location.href='SGMapleStore?pageTransit=goToItemList'">Go Back To Item List</button>
                        </div>
                        </div>                        
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>