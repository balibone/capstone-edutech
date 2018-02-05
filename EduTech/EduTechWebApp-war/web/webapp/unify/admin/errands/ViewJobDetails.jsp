<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
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
            String jobImage, jobID, jobCategory, jobTitle, jobDescription, jobStartLoc, jobEndLoc, jobRateType, jobRate, jobPosterID, jobTakerID, jobWorkDate;
            jobImage = jobID = jobCategory = jobTitle = jobDescription = jobStartLoc = jobEndLoc = jobRateType = jobRate = jobPosterID = jobTakerID = jobWorkDate= "";
            
            if (jobDetailsVec != null) {
                jobImage = (String) jobDetailsVec.get(0);
                jobID = (String.valueOf(jobDetailsVec.get(1)));
                jobCategory = (String.valueOf(jobDetailsVec.get(2)));
                jobTitle = (String) jobDetailsVec.get(3);
                jobDescription = (String)jobDetailsVec.get(4);
                jobStartLoc = (String)jobDetailsVec.get(5);
                jobEndLoc = (String)jobDetailsVec.get(6);
                jobRateType = (String)jobDetailsVec.get(7);
                jobRate = (String.valueOf(jobDetailsVec.get(8)));
                jobPosterID = (String.valueOf(jobDetailsVec.get(9)));
                jobTakerID = (String.valueOf(jobDetailsVec.get(10)));
                jobWorkDate = (String.valueOf(jobDetailsVec.get(11)));
            }
        %>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">
            <div class="col-sm-5 col-md-5 gallery-holder">
                <div class="single-product-gallery">
                    <div class="owl-item" style="width: 336px;">
                        <div class="single-product-gallery-item">
                            <img src="uploads/unify/images/errands/job/<%= jobImage %>" style="max-width: 251px; min-width: 251px; max-height: 256px; min-height: 256px;" />
                        </div>
                    </div>
                </div>
            </div>   
            <div class="col-sm-7 col-md-7 product-info-block">
                <div class="product-info">
                    <h1 class="name"><%= jobTitle %></h1><br/>
                    <div class="stock-container">
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Job ID:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobID %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Category:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobCategory %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Rate Type:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobRateType %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Start Location:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobStartLoc %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">End Location:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobEndLoc %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Poster ID:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobPosterID %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Taker ID:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobTakerID %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Work Date:&nbsp;</span></div></div>
                        <div class="col-sm-9"><div class="stock-box"><%= jobWorkDate %></div></div>
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
 
  <div class="tab">
  <button class="tablinks" onclick="openCity(event, 'Transaction')">Transaction</button>
  <button class="tablinks" onclick="openCity(event, 'Reviews')">Reviews</button>
  </div>
  <div id="Transaction" class="tabcontent" class="active">
         <%    Vector transaction = (Vector)request.getAttribute("jobTransaction");
        String transactionID, transactionDate, posterID, takerID;
        transactionID = transactionDate = posterID = takerID ="";
        if(transaction != null){
            transactionID = String.valueOf(transaction.get(0));
            transactionDate = String.valueOf(transaction.get(1));
            posterID = String.valueOf(transaction.get(2));
            takerID = String.valueOf(transaction.get(3));
        %>    
       
                <div class="col-sm-3"><div class="tab-box"><span class="tab-info">Transaction ID:&nbsp;</span></div></div>
                <div class="col-sm-9"><div class="tab-box"><%= transactionID%></div></div>
                <br/>
                <div class="col-sm-3"><div class="stock-box"><span class="tab-info">Transaction Date:&nbsp;</span></div></div>
                <div class="col-sm-9"><div class="tab-box"><%= transactionDate%></div></div>
                <br/>
                <div class="col-sm-3"><div class="stock-box"><span class="tab-info">Poster ID:&nbsp;</span></div></div>
                <div class="col-sm-9"><div class="tab-box"><%= posterID%></div></div>
                <br/>
                <div class="col-sm-3"><div class="stock-box"><span class="tab-info">Taker ID:&nbsp;</span></div></div>
                <div class="col-sm-9"><div class="tab-box"><%= takerID%></div></div>
                <br/>           
       <% }else{
       %>
        <p>There is no transaction data available. </p>
        <% }
        %>
      </div>
      
       <div id="Reviews" class="tabcontent">
            <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
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
               List<Vector> reviews = (List)request.getAttribute("jobReviews");
               if(reviews!=null){
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
               if(reviews.size()==0){
            %>
            <tbody> <tr> <td>No review data available. </td></tr>
               <%
            }
            
           %>
           </tbody>
       </table>
    </div>

                        
        <div class="form-group" style="margin-top: 20px;">
            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <table border="0" style="margin: auto;">
                    <tr>
                        <td>
                            <form action="ErrandsAdmin" method="POST" target="_parent">
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
        <script src="js/unify/admin/webjs/errands/ViewTransactionDetailsJS.js" type="text/javascript"></script>
    </body>
</html>