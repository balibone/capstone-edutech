<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - Contact Details</title>
        
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/ContactDetailsCSS.css" rel="stylesheet" type="text/css">
        
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/ContactDetailsJS.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            ArrayList<String> contactInfoArr = (ArrayList) request.getAttribute("contactInfo");
            String contactFirstName, contactLastName, contactEmail, contactActiveStatus, contactCreationDate;
            contactFirstName = contactLastName = contactEmail = contactActiveStatus = contactCreationDate = "";

            if (contactInfoArr != null) {
                contactFirstName = (String)contactInfoArr.get(0);
                contactLastName = (String)contactInfoArr.get(1);
                contactEmail = (String)contactInfoArr.get(2);
                contactActiveStatus = (String)contactInfoArr.get(3);
                contactCreationDate = (String)contactInfoArr.get(4);
            }
        %>
        <section class="dashboard section">
            <div class="container">
                <div class="row">
                    <div class="col-sm-4 col-md-4 col-lg-4">
                        <div class="sidebar">
                            <div class="widget user-dashboard-profile">
                                <div class="profile-thumb">
                                    <img src="images/user/user-thumb.jpg" class="rounded-circle">
                                </div>
                                <h5 class="text-center"><%= contactFirstName %>&nbsp;<%= contactLastName %></h5>
                                <p>Joined&nbsp;<%= contactCreationDate %></p>
                                <a href="user-profile.html" class="btn btn-main-sm">Edit Profile</a>
                            </div>
                            <div class="widget user-dashboard-menu">
                                <ul class="sidebar-menu">
                                    <li class="active"><a href="#contactAddressPane"><i class="fa fa-address-book"></i>&nbsp;Contact Address</a></li>
                                    <li><a href="#transactionsPane"><i class="fa fa-exchange"></i>&nbsp;Transactions</a></li>
                                    <li><a href="#recentHistoryPane"><i class="fa fa-history"></i>&nbsp;Recent History</a></li>
                                    <%
                                        if(contactActiveStatus.equals("true")) {
                                    %>
                                    <form action="SGMapleStore" method="POST" target="_parent">
                                        <li onclick="deactivateCheck();">
                                            <input type="hidden" name="hiddenContactEmail" value="<%= contactEmail %>" />
                                            <input type="hidden" name="pageTransit" value="deactivateAContact" />
                                            <a href="#">
                                                <i class="fa fa-trash"></i>&nbsp;Deactivate Contact
                                            </a>
                                        </li>
                                    </form>
                                    <%  } %>
                                    <%
                                        if(contactActiveStatus.equals("false")) {
                                    %>
                                    <form action="SGMapleStore" method="POST" target="_parent">
                                        <li onclick="activateCheck();">
                                            <input type="hidden" name="hiddenContactEmail" value="<%= contactEmail %>" />
                                            <input type="hidden" name="pageTransit" value="activateAContact" />
                                            <a href="#">
                                                <i class="fa fa-trash"></i>&nbsp;Activate Contact
                                            </a>
                                        </li>
                                    </form>
                                    <%  } %>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-8 col-md-8 col-lg-8 contentArea" style="margin-left: 22px;">
                        <div class="widget dashboard-container active" id="contactAddressPane">
                            <h3 class="widget-header">Contact Address</h3>
                        </div>
                        <div class="widget dashboard-container subContentArea" id="transactionsPane">
                            <h3 class="widget-header">Transactions</h3>
                        </div>
                        <div class="widget dashboard-container subContentArea" id="recentHistoryPane">
                            <h3 class="widget-header">Recent History</h3>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>