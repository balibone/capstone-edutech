<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - View Item Details</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/voices/ViewReviewListDetailsCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body style="background: #FFFFFF">
        <%
            Vector reviewDetailsVec = (Vector) request.getAttribute("reviewDetailsVec");
            String reviewID, reviewTitle, reviewRating, reviewPros, reviewCons, reviewEmpType, reviewThumbsUp, reviewSalaryRange, reviewedCompany, reviewDate, reviewPosterID;
            reviewID = reviewTitle = reviewRating = reviewPros = reviewCons = reviewEmpType = reviewThumbsUp = reviewSalaryRange = reviewedCompany = reviewDate = reviewPosterID = "";
            
            if (reviewDetailsVec != null) {
                reviewID = (String.valueOf(reviewDetailsVec.get(0)));
                reviewTitle = (String.valueOf(reviewDetailsVec.get(1)));
                reviewRating = (String.valueOf(reviewDetailsVec.get(2))) ;
                reviewPros = (String.valueOf(reviewDetailsVec.get(3)));
                reviewCons = (String.valueOf(reviewDetailsVec.get(4)));
                reviewEmpType = (String.valueOf(reviewDetailsVec.get(5)));
                reviewThumbsUp = (String.valueOf(reviewDetailsVec.get(6)));
                reviewSalaryRange = (String.valueOf(reviewDetailsVec.get(7)));
                reviewedCompany = (String.valueOf(reviewDetailsVec.get(8)));
                reviewDate = (String.valueOf(reviewDetailsVec.get(9)));
                reviewPosterID = (String.valueOf(reviewDetailsVec.get(10)));
            }
        %>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">		
            <div class="col-sm-12 col-md-12 product-info-block">
                <div class="product-info">
                    <h1 class="name"><%= reviewID %></h1><br/>
                    <div class="stock-container">
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Title:&nbsp;</span><%= reviewTitle %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Post Date:&nbsp;</span><%= reviewDate %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Poster ID:&nbsp;</span><%= reviewPosterID %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Reviewed Company:&nbsp;</span><%= reviewedCompany %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Rating:&nbsp;</span><%= reviewRating %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Pros:&nbsp;</span><%= reviewPros %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Cons:&nbsp;</span><%= reviewCons %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Employment Type:&nbsp;</span><%= reviewEmpType %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Thumbs Up:&nbsp;</span><%= reviewThumbsUp %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Review Salary Range:&nbsp;</span><%= reviewSalaryRange %></div></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group" style="margin-top: 20px;">
            <div class="col-md-12 col-sm-12 col-xs-12 col-md-offset-3">
                <form action="VoicesAdmin" method="POST" target="_parent">
                    <input type="hidden" name="pageTransit" value="deleteReview"/>
                    <input type="hidden" name="hiddenReviewedCompany" value="<%= reviewedCompany %>"/>
                    <input type="hidden" name="hiddenReviewPosterID" value="<%= reviewPosterID %>"/>
                    <button type="submit" class="btn btn-primary" style="margin-left: 210px" onClick="return confirm('Do you want to delete the review?')">Delete Review</button>
                </form>
            </div>
        </div>             
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
    </body>
</html>
