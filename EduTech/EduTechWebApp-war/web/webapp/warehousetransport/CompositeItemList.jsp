<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - Composite Item List</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/basetemplate.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/CommonCSS.css" rel="stylesheet" type="text/css">
        
        <!-- Java Script (JS) -->
        <script src="js/commoninfrastructure/basejs/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/metisMenu.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.newsTicker.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/CommonJS.js" type="text/javascript"></script>
        <script src="js/warehousetransport/webjs/CompositeItemListJS.js" type="text/javascript"></script>
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
                <div class="contentFill contentLayout" style="padding-top: 15px; height: 65px;">
                    <h3 style="display: inline;">Composite Item List</h3>
                    <button class="btn btn-primary pull-right" style="margin-right: 20px;" onclick="location.href='Warehouse?pageTransit=goToNewCompositeItem'">
                        <i class="fa fa-plus"></i>&nbsp;&nbsp;New Composite Item
                    </button>
                </div>
                <table class="table zi-table table-hover" id="compositeItemList">
                    <thead>
                        <tr>
                            <th style="width: 15%; padding-left: 20px;" class="sortable text-left">
                                <div class="placeholder-container">
                                    <div class="pull-left over-flow">Image</div>
                                </div>
                            </th>
                            <th style="width: 17%;" class="sortable text-left">
                                <div class="placeholder-container">
                                    <div class="pull-left over-flow">Name</div>
                                </div>
                            </th>
                            <th style="width: 16%;" class="sortable text-left">
                                <div class="placeholder-container">
                                    <div class="pull-left over-flow">SKU</div>
                                </div>
                            </th>
                            <th style="width: 18%;" class="sortable text-left">
                                <div class="placeholder-container">
                                    <div class="pull-left over-flow">Quantity</div>
                                </div>
                            </th>
                            <th style="width: 15%;" class="sortable text-left">
                                <div class="placeholder-container">
                                    <div class="pull-left over-flow">Selling Price</div>
                                </div>
                            </th>
                            <th style="width: 15%;" class="sortable text-left">
                                <div class="placeholder-container">
                                    <div class="pull-left over-flow">Rebundle Level</div>
                                </div>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Vector> compositeItemList = (ArrayList)request.getAttribute("compositeItemList");
                            if(compositeItemList.isEmpty()){
                        %>
                        <tr>
                            <td colspan="6" style="text-align: center;">There are no composite item records available.</td>
                        </tr>
                        <%
                            }
                            else {
                                for(int i = 0; i <= compositeItemList.size()-1; i++){
                                    Vector v = compositeItemList.get(i);
                                    String compositeImagePath = String.valueOf(v.get(0));
                                    String compositeName = String.valueOf(v.get(1));
                                    String compositeSKU = String.valueOf(v.get(2));
                                    String compositeQuantity = String.valueOf(v.get(3));
                                    String compositeSellPrice = String.valueOf(v.get(4));
                                    String compositeRebundleLvl = String.valueOf(v.get(5));
                        %>
                        <tr tabindex="-1" class="active">
                            <td style="padding-left: 20px;"><img src="uploads/images/warehouse/CompositeItems/<%= compositeImagePath %>" style="max-width: 70px; max-height: 70px;" /></td>
                            <td><%= compositeName %></td>
                            <td><%= compositeSKU %></td>
                            <td><%= compositeQuantity %></td>
                            <td>$<%= compositeSellPrice %></td>
                            <td><%= compositeRebundleLvl %></td>
                        </tr>
                        <%      }   %>
                        <%  }   %>
                    </tbody>
                </table>
                <div id="modal-iframe"></div>
            </div>
        </div>
    </body>
</html>