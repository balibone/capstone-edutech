<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - Contact List</title>
        
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
        <script src="js/commoninfrastructure/webjs/ContactListJS.js" type="text/javascript"></script>
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
                <form action="SGMapleStore" method="POST" onsubmit="return confirm('Confirm deactivate the selected contact(s)?');">
                    <div class="contentFill contentLayout" style="padding-top: 15px; height: 65px;">
                        <h3 style="display: inline;">Contact List</h3>
                        <input type="hidden" name="pageTransit" value="deactivateMultipleContact"/>
                        <button type="submit" class="btn btn-primary pull-right" style="margin-right: 20px;" id="deactivateContact" disabled>
                            <i class="fa fa-ban"></i>&nbsp;&nbsp;Deactivate Contact
                        </button>
                        <button type="button" class="btn btn-primary pull-right" style="margin-right: 20px;" onclick="location.href='SGMapleStore?pageTransit=goToNewContact'">
                            <i class="fa fa-plus"></i>&nbsp;&nbsp;New Contact
                        </button>
                    </div>
                    
                    <%
                        String successMessage = (String)request.getAttribute("successMessage");
                        if (successMessage != null) {
                    %>
                    <div class="alert alert-success" id="successPanel" style="margin: 10px 0 10px 0;">
                        <button type="button" class="close" id="closeSuccess">&times;</button>
                        <%= successMessage %>
                    </div>
                    <%  } %>
                    <%
                        String errorMessage = (String)request.getAttribute("errorMessage");
                        if (errorMessage != null) {
                    %>
                    <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 10px 0;">
                        <button type="button" class="close" id="closeError">&times;</button>
                        <%= errorMessage %>
                    </div>
                    <%  } %>
                    
                    <table class="table zi-table table-hover" id="contactList">
                        <thead>
                            <tr>
                                <th class="bulk-selection-cell"><input type="checkbox" class="selectAll"/></th>
                                <th style="width: 17%;" class="sortable text-left">
                                    <div class="placeholder-container">
                                        <div class="pull-left over-flow">Name</div>
                                    </div>
                                </th>
                                <th style="width: 21%;" class="sortable text-left">
                                    <div class="placeholder-container">
                                        <div class="pull-left over-flow">Email</div>
                                    </div>
                                </th>
                                <th style="width: 17%;" class="sortable text-left">
                                    <div class="placeholder-container">
                                        <div class="pull-left over-flow">Contact No.</div>
                                    </div>
                                </th>
                                <th style="width: 22%;" class="sortable text-left">
                                    <div class="placeholder-container">
                                        <div class="pull-left over-flow">Contact Type</div>
                                    </div>
                                </th>
                                <th style="width: 17%;" class="sortable text-left">
                                    <div class="placeholder-container">
                                        <div class="pull-left over-flow">Active Status</div>
                                    </div>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Vector> contactList = (ArrayList) request.getAttribute("contactList");
                                String dpActiveStatus = "";
                                if(contactList.isEmpty()){
                            %>
                            <tr>
                                <td colspan="6" style="text-align: center;">There are no contact records available.</td>
                            </tr>
                            <%
                                }
                                else {
                                    for(int i = 0; i <= contactList.size()-1; i++){
                                        Vector v = contactList.get(i);
                                        String contactSalutation = String.valueOf(v.get(0));
                                        String contactFirstName = String.valueOf(v.get(1));
                                        String contactLastName = String.valueOf(v.get(2));
                                        String contactEmail = String.valueOf(v.get(3));
                                        String contactPhone = String.valueOf(v.get(4));
                                        String contactType = String.valueOf(v.get(5));
                                        String suppCompanyName = String.valueOf(v.get(6));
                                        String contactActiveStatus = String.valueOf(v.get(7));
                                        if(contactActiveStatus.equals("false")) { dpActiveStatus = "Inactive"; }
                                        else if (contactActiveStatus.equals("true")) { dpActiveStatus = "Active"; }
                            %>
                            <tr tabindex="-1" class="active">
                                <td class="bulk-selection-cell"><input type="checkbox" class="contactCheck" name="contactEmailList" value="<%= contactEmail %>" /></td>
                                <td><%= contactSalutation %>&nbsp;<%= contactFirstName %>&nbsp;<%= contactLastName %></td>
                                <td><%= contactEmail %></td>
                                <td><%= contactPhone %></td>
                                <td><%= contactType %><br/>(<%= suppCompanyName %>)</td>
                                <td><%= dpActiveStatus %></td>
                                <%      }   %>
                                <%  }   %>
                            </tr>
                        </tbody>
                    </table>
                    <div id="modal-iframe"></div>
                </form>
            </div>
        </div>
    </body>
</html>