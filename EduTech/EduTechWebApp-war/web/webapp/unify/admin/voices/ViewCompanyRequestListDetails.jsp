<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unify Admin - View Request Details</title>
        
        <!-- CASCADING STYLESHEET (CSS) -->
        <link href="css/unify/admin/baselayout/bootstrap-v3.3.7.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/admin/weblayout/voices/ViewReviewListDetailsCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body style="background: #FFFFFF">
        <%
            Vector requestDetailsVec = (Vector) request.getAttribute("requestDetailsVec");
            String requestID, requestPosterID, requestCompany, requestDate, requestStatus, requestIndustry, requestComment;
            requestID = requestPosterID = requestCompany = requestDate = requestStatus = requestIndustry = requestComment = "";
            
            if (requestDetailsVec != null) {
                requestID = (String.valueOf(requestDetailsVec.get(0)));
                requestDate = (String.valueOf(requestDetailsVec.get(1)));
                requestPosterID = (String.valueOf(requestDetailsVec.get(2))) ;
                requestCompany = (String.valueOf(requestDetailsVec.get(3)));
                requestIndustry = (String.valueOf(requestDetailsVec.get(4)));
                requestComment = (String.valueOf(requestDetailsVec.get(5)));
                requestStatus = (String.valueOf(requestDetailsVec.get(6)));
            }
        %>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">		
            <div class="col-sm-12 col-md-12 product-info-block">
                <div class="product-info">
                    <h1 class="name"><%= requestID %></h1><br/>
                    <div class="stock-container">
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Request Post Date:&nbsp;</span><%= requestDate %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Request Poster ID:&nbsp;</span><%= requestPosterID %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Request Company:&nbsp;</span><%= requestCompany %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Company Industry:&nbsp;</span><%= requestIndustry %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Request Comment:&nbsp;</span><%= requestComment %></div></div>
                        <br/>
                        <div class="col-sm-3"><div class="stock-box"><span class="label">Request Status:&nbsp;</span><%= requestStatus %></div></div>
                    </div>
                </div>
            </div>
        </div>             
        
        <!-- JAVASCRIPT (JS) -->
        <script src="js/unify/admin/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
    </body>
</html>
