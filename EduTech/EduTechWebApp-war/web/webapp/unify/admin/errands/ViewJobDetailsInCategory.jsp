<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - View Job Details</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/marketplace/ViewItemListingDetailsCSS.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/errands/ViewTransactionDetailsCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%
            Vector jobDetailsVec = (Vector) request.getAttribute("jobDetails");
            String jobImage, jobID, jobStatus, jobCategory, jobTitle, jobDescription, jobStartLoc, jobEndLoc, jobRateType, jobRate, jobPosterID, jobTakerID, jobWorkDate;
            jobImage = jobID = jobStatus = jobCategory = jobTitle = jobDescription = jobStartLoc = jobEndLoc = jobRateType = jobRate = jobPosterID = jobTakerID = jobWorkDate= "";
            
            if (jobDetailsVec != null) {
                jobImage = (String) jobDetailsVec.get(0);
                jobID = (String.valueOf(jobDetailsVec.get(1)));
                jobStatus = (String)jobDetailsVec.get(2);
                jobCategory = (String.valueOf(jobDetailsVec.get(3)));
                jobTitle = (String) jobDetailsVec.get(4);
                jobDescription = (String)jobDetailsVec.get(5);
                jobStartLoc = (String)jobDetailsVec.get(6);
                jobEndLoc = (String)jobDetailsVec.get(7);
                jobRateType = (String)jobDetailsVec.get(8);
                jobRate = (String.valueOf(jobDetailsVec.get(9)));
                jobPosterID = (String.valueOf(jobDetailsVec.get(10)));
                jobWorkDate = (String.valueOf(jobDetailsVec.get(11)));
            }
        %>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">
          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="bodyContent">
            <%--<div class="col-sm-2 col-md-2 gallery-holder">--%>
            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                <div class="single-product-gallery">
                    <div class="owl-item" style="width: 36px;">
                        <div class="single-product-gallery-item">
                            <img src="uploads/unify/images/errands/job/<%= jobImage %>" style="max-width: 151px; min-width: 151px; max-height: 156px; min-height: 156px;" />
                        </div>
                    </div>
                </div>
            </div>   
            <%--<div class="col-sm-10 col-md-10 product-info-block">--%>
            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                <div class="product-info">
                    <h1 class="name"><%= jobTitle %></h1><br/>
                    <div class="stock-container">
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Job ID:&nbsp;<%= jobID %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobID %></div></div>--%>
                        
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Job Status:&nbsp;<%= jobStatus %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobStatus %></div></div>--%>
                        
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Category:&nbsp;<%= jobCategory %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobCategory %></div></div>--%>
                        
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Rate Type:&nbsp;<%= jobRateType %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobRateType %></div></div>--%>
                        
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Start Location:&nbsp;<%= jobStartLoc %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobStartLoc %></div></div>--%>
                        
                        <div class="col-sm-3"><div class="stock-box"><span class="label">End Location:&nbsp;<%= jobEndLoc %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobEndLoc %></div></div>--%>
                        
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Poster ID:&nbsp;<%= jobPosterID %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobPosterID %></div></div>--%>
                        
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Work Date:&nbsp;<%= jobWorkDate %></span></div></div>
                        <%--<div class="col-sm-9"><div class="stock-box"><%= jobWorkDate %></div></div>--%>
                    </div>
                    <div class="description-container m-t-20">
                        <%= jobDescription %><br/>
                    </div>
                    <div class="price-container m-t-20">
                        <div class="col-sm-6">
                            <div class="price-box"><span class="price">$<%= jobRate %></span></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
                        

    <div class="tab">
    <button class="tablinks" onclick="openCity(event, 'Transaction')">Transaction</button>
    <button class="tablinks" onclick="openCity(event, 'Reviews')">Reviews</button>
     </div>
 
  <div id="Transaction" class="tabcontent" style="margin-left: 20px; margin-right: 20px" >
      <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="90%" >
                    <thead>
                        <tr>
                            <th>Transaction ID</th>
                            <th>Transaction Date</th>
                            <th>Poster ID</th>
                            <th>Taker ID</th>
                        </tr>
                    </thead>
         <%    ArrayList<Vector> transactionList = (ArrayList)request.getAttribute("jobTransaction");
        String transactionID, transactionDate, posterID, takerID;
        transactionID = transactionDate = posterID = takerID ="";
        if(transactionList.size() > 0){
            for(int i=0; i<transactionList.size(); i++){
                Vector transaction = (Vector)transactionList.get(i);
                transactionID = String.valueOf(transaction.get(0));
                transactionDate = String.valueOf(transaction.get(1));
                posterID = String.valueOf(transaction.get(2));
                takerID = String.valueOf(transaction.get(3));
        %>    
                <tbody>
                                        <tr>
                                            <td><%= transactionID%></td>
                                            <td><%= transactionDate%></td>
                                            <td><%= posterID%></td>
                                            <td><%= takerID%></td>
                                        </tr>
               
       <%  }
                }else{
       %>

        <% }
        %>
        </tbody>
       </table>
      </div>
      
       <div id="Reviews" class="tabcontent">
        <%--<div id="datatable-responsive_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
           <div class="row">
               <div class="col-sm-6">
                   <div class="dataTables_length" id="datatable-responsive_length">
                       <label>Show <select name="datatable-responsive_length" aria-controls="datatable-responsive" class="form-control input-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div></div>
               <div class="col-sm-6">
                   <div id="datatable-responsive_filter" class="dataTables_filter">
                       <label>Search:<input type="search" class="form-control input-sm" placeholder="" aria-controls="datatable-responsive"></label></div></div>
           </div> --%>
            <div id="search-box">
                <span id="search-text"> Search: </span> 
                <input type="text" id="myInput" onkeyup="myFunction()" placeholder="" title="Type in a name">
            </div>
            <table id="datatable-table" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Review ID</th>
                            <th>Review Index</th>
                            <th>Review Content</th>
                            <th>Reviewer ID</th>
                            <th>Receiver ID</th>
                            <th>Review Date</th>
                        </tr>
                    </thead>
           <%
               String reviewID, reviewIndex, reviewContent, reviewerID, receiverID, reviewDate;
               reviewID = reviewIndex = reviewContent = reviewerID = receiverID = reviewDate = "";
               ArrayList<Vector> reviews = (ArrayList)request.getAttribute("jobReviews");
               if(reviews.size()>0){
                   for(int i=0; i<reviews.size(); i++){
                       Vector v = reviews.get(i);       
                       reviewID = String.valueOf(v.get(0));
                       reviewIndex = String.valueOf(v.get(1));
                       reviewContent = (String) v.get(2);
                       reviewerID = String.valueOf(v.get(3));
                       receiverID = String.valueOf(v.get(4));
                       reviewDate = String.valueOf(v.get(5));
            %>

                                    <tbody>
                                        <tr>
                                            <td><%= reviewID%></td>
                                            <td><%= reviewIndex%></td>
                                            <td><%= reviewContent%></td>
                                            <td><%= reviewerID%></td>
                                            <td><%= receiverID%></td>
                                            <td><%= reviewDate%></td>
                                        </tr>
                                      
           <%
                   }
               }
               else{
            %>
 
               <%
            }
            
           %>
           </tbody>
       </table>
    </div>

                        
        <div class="form-group" style="margin-top: 12px; margin-left: 40%; margin-bottom: 20px">
            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <table border="0" style="margin-left: 50;">
                    <tr>
                        <td>
                            <form action="ErrandsAdmin" method="POST" target="_parent" onsubmit="return confirm('Do you really want to delete this job listing?');">
                                <input type="hidden" name="pageTransit" value="deleteAJobListing" />
                                <input type="hidden" name="hiddenJobID" value="<%= jobID %>" />
                                <button type="submit" class="btn btn-primary">Delete Item</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div> 
        
       
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/bootstrap-v3.3.6.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminBaseJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/UnifyAdminCommonJS.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/webjs/errands/ViewTransactionDetailsJS.js" type="text/javascript"></script>
        
        <script src="https://colorlib.com/polygon/vendors/datatables.net/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.bootstrap.js" type="text/javascript"></script>
        <script src="js/unify/admin/basejs/dataTable/dataTables.responsive.min.js" type="text/javascript"></script>
        
        <script>
        function myFunction() {
            var input, filter, table, tr, td, i, j, found;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("datatable-table");
            tr = table.getElementsByTagName("tr");
  
            for (i = 1; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td");
                found = false;
                for (j = 0; j < td.length; j++) {
                    var cell = td[j];
                    if (cell) {
                        if (cell.innerHTML.toUpperCase().indexOf(filter) > -1) {
                            found = true;
                        } else {
                        }   
                    }
                }
                if(found==true){
                    tr[i].style.display = "";
                }else{
                    tr[i].style.display = "none";
                }
            }
        }
</script>
    </body>
</html>
