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
        <title>Unify Errands - Job Listing Details</title>
        
        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/qtip/jquery.qtip-v3.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/errands/ViewJobDetailsSYSCSS.css" rel="stylesheet" type="text/css">
        
    </head>
    <body onload="loadMap()">
        <!-- MOBILE SIDE NAVIGATION -->
        <nav class="offcanvas">
            <div class="offcanvas-content">
                <div id="list-menu" class="list-menu list-group" data-children=".submenu">
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccount"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#marketplaceSub" role="button" aria-expanded="false" aria-controls="marketplaceSub"><i class="fa fa-fw fa-file"></i>&nbsp;Marketplace</a>
                        <div id="marketplaceSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="MarketplaceSysUser?pageTransit=goToViewItemListingSYS">Item Listing</a></div>
                    </div>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#errandsSub" role="button" aria-expanded="false" aria-controls="errandsSub"><i class="fa fa-fw fa-file"></i>&nbsp;Errands</a>
                        <div id="errandsSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="ErrandsSysUser?pageTransit=goToViewJobListingSYS">Errands Listing</a></div>
                    </div>
                    <div class="submenu">
                        <a data-toggle="collapse" href="#" data-target="#companyReviewSub" role="button" aria-expanded="false" aria-controls="companyReviewSub"><i class="fa fa-fw fa-user"></i>&nbsp;Company Review</a>
                        <div id="companyReviewSub" class="collapse" data-parent="#list-menu" role="tabpanel"><a href="VoicesSysUser?pageTransit=goToViewCompanyListingSYS">Company Listing</a></div>
                    </div>
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccount"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
                </div>
            </div>
        </nav>
        <div class="content-overlay"></div>

        <!-- PAGE TOP HEADER -->
        <div class="top-header">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="d-flex justify-content-between">
                            <nav class="nav">
                                <a class="nav-item nav-link d-sm-block" href="#">Unify @ EduBox</a>
                            </nav>
                            <ul class="nav">
                                <li class="nav-item d-none d-md-block">
                                    <a href="#" class="nav-link">
                                        <i class="fa fa-heart-o"></i>&nbsp;&nbsp;Likes
                                    </a>
                                </li>
                                <li class="nav-item d-none d-md-block">
                                    <a href="#" class="nav-link">
                                        <i class="fa fa-envelope"></i>&nbsp;&nbsp;Messages
                                    </a>
                                </li>
                                <select class="select-dropdown-nav accountNavigation" data-width="120px">
                                    <option value="#" selected data-before='<i class="fa fa-user align-baseline" /></i>'>&nbsp;&nbsp;<%= loggedInUsername%></option>
                                    <option value="CommonInfra?pageTransit=goToCommonLanding" data-before='<i class="fa fa-external-link align-baseline" /></i>'>&nbsp;&nbsp;Landing Page</option>
                                    <option value="ProfileSysUser?pageTransit=goToUnifyUserAccount" data-before='<i class="fa fa-user-circle align-baseline" /></i>'>&nbsp;&nbsp;My Account</option>
                                    <option value="ProfileSysUser?pageTransit=goToLogout" data-before='<i class="fa fa-sign-out align-baseline" /></i>'>&nbsp;&nbsp;Logout</option>
                                </select>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- PAGE MIDDLE NAVIGATION -->
        <div class="middle-header">
            <div class="container">
                <div class="row py-2 py-lg-0">
                    <div class="col-2 col-sm-1 d-block d-lg-none">
                        <div class="d-flex align-items-center justify-content-center menu-btn-wrapper">
                            <button class="btn btn-lg border-0 btn-link offcanvas-btn p-0" type="button">
                                <i class="fa fa-bars"></i>
                            </button>
                        </div>
                    </div>
                    <div class="col-2 col-sm-1 col-lg-3 pr-0">
                        <div class="d-flex align-items-center logo-wrapper">
                            <a href="index.html" class="d-lg-none">
                                <img src="images/edubox-logo.png" class="logo" />
                            </a>
                            <a href="index.html" class="d-none d-lg-flex mb-2 mb-lg-0">
                                <img src="images/edubox-logo.png" class="logo" />
                            </a>
                        </div>
                    </div>
                    <div class="col-8 col-sm-6 col-md-7 col-lg-6 mt-3">
                        <div class="d-flex align-items-center">
                            <div class="input-group input-group-search">
                                <div class="input-group-prepend d-none d-md-flex">
                                    <select class="select-dropdown">
                                        <option value="all">All Categories</option>
                                        <option value="marketplace">Marketplace</option>
                                        <option value="errands">Errands</option>
                                        <option value="companyReview">Company Review</option>
                                    </select>
                                </div>
                                <input type="text" class="form-control" id="search-input" placeholder="Search here..." aria-label="Search here..." autocomplete="off" />
                                <span class="input-group-append">
                                    <button class="btn btn-theme btn-search" type="button"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-4 col-sm-4 col-md-3 col-lg-3 d-none d-sm-block mt-3">
                        <div class="d-flex align-items-center float-right abg-secondary">
                            <div class="btn-group btn-group-sm mr-3" role="group">
                                <a class="btn btn-outline-theme" href="MarketplaceSysUser?pageTransit=goToNewItemListingSYS" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Sell An Item
                                </a>
                                <a class="btn btn-outline-theme" href="ErrandsSysUser?pageTransit=goToNewJobListingSYS" role="button">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Post A Job
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="container">
            <div id="unifyPageNAV"></div>
            <!-- BREADCRUMB -->
            <div class="breadcrumb-container">
                <div class="container">
                    <nav aria-label="breadcrumb" role="navigation">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="ProfileSysUser?pageTransit=goToUnifyUserAccount">Unify Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Errands (Job Details)</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="container" style="margin-bottom: 30px;">
                <%
                    Vector jobDetailsSYSVec = (Vector) request.getAttribute("jobDetailsSYSVec");
                    
                    String jobID = String.valueOf(jobDetailsSYSVec.get(0));
                    String jobTitle = String.valueOf(jobDetailsSYSVec.get(1));
                    String categoryName = String.valueOf(jobDetailsSYSVec.get(2));
                    String jobRateType = String.valueOf(jobDetailsSYSVec.get(3));
                    String jobRate = String.valueOf(jobDetailsSYSVec.get(4));
                    String jobDescription = String.valueOf(jobDetailsSYSVec.get(5));
                    String jobStartLocation = String.valueOf(jobDetailsSYSVec.get(6));
                    String jobStartLat = String.valueOf(jobDetailsSYSVec.get(7));
                    String jobStartLong = String.valueOf(jobDetailsSYSVec.get(8));
                    String jobEndLocation = String.valueOf(jobDetailsSYSVec.get(9));
                    String jobEndLat = String.valueOf(jobDetailsSYSVec.get(10));
                    String jobEndLong = String.valueOf(jobDetailsSYSVec.get(11));
                    String jobImage = String.valueOf(jobDetailsSYSVec.get(12));
                    String jobStatus = String.valueOf(jobDetailsSYSVec.get(13));
                    String numOfLikes = String.valueOf(jobDetailsSYSVec.get(14));
                    String jobPostDate = String.valueOf(jobDetailsSYSVec.get(15));
                    String jobWorkDate = String.valueOf(jobDetailsSYSVec.get(16));
                    String posterName = String.valueOf(jobDetailsSYSVec.get(17));
                    String posterImage = String.valueOf(jobDetailsSYSVec.get(18));
                    String posterJoinDate = String.valueOf(jobDetailsSYSVec.get(19));
                    String categoryID = String.valueOf(jobDetailsSYSVec.get(20));
                    String jobDuration = String.valueOf(jobDetailsSYSVec.get(21));
                    String otherInformation = String.valueOf(jobDetailsSYSVec.get(22));
                    String numOfHelpers = (String.valueOf(jobDetailsSYSVec.get(23)));
                    String checking = (String.valueOf(jobDetailsSYSVec.get(24)));
                    String likeStatus = (String.valueOf(jobDetailsSYSVec.get(25)));
                %>
                <input type="hidden" id="priceType" value="<%=jobRateType%>"/>
                <div class="row">
                    <div class="col-12 d-block d-md-none">
                        <div class="title"><span><%= jobTitle%></span></div>
                    </div>
                    <div class="col-xl-4 col-lg-5 col-md-6">
                        <img src="uploads/unify/images/errands/job/<%= jobImage%>" class="img-fluid mb-2 border w-100 image-detail" style="cursor: pointer; height: 300px;">
                        
                        
                        <ul class="list-inline d-none d-md-block" style="margin-top: 8px;">
                            
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-facebook"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-info"><i class="fa fa-fw fa-twitter"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-danger"><i class="fa fa-fw fa-google-plus"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-primary"><i class="fa fa-fw fa-linkedin"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-warning"><i class="fa fa-fw fa-envelope"></i></button></li>
                            <li class="list-inline-item"><button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#reportJobModal"><i class="fa fa-flag" aria-hidden="true"></i> &nbsp; Report</button></li>
                        </ul>
                        
                        <!-- Modal -->
                        <div class="modal fade" id="reportJobModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                          <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Report Job Listing</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                  <span aria-hidden="true">&times;</span>
                                </button>
                              </div>
                              <div class="modal-body" >
                                <span><strong>Why are you reporting this job listing?</strong></span><br/>
                                <br/>
                                <select class="select-dropdown" name="reportReason" id="reportReason" onchange="javascript: otherReason()">
                                    <option value="wrongCategory">The job is wrongly categorized.</option>
                                    <option value="inappropirateListing">Inappropriate content. </option>
                                    <option value="fakeEvent">Fake event/job.</option>
                                    <option value="others">Other reasons. </option>
                                </select>
                                <div class="mt-3" id="otherReason" ></div>
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="reportJobBtn">Report</button>
                              </div>
                            </div>
                          </div>
                        </div>
                    </div>
                        
                    <div class="col-xl-8 col-lg-7 col-md-6" >
                        <span class="border-top-0" id="job_title" style="margin-left: 10px;"><strong><%= jobTitle%></strong></span><br/>
                        <table class="job-information" style="margin-left: 10px;">
                            <col width="150px" />
                            <col width="560px" />
                            <tr>
                                <td colspan="2" style="font-size: 14px;"><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;&nbsp;Posted <%=jobPostDate%> by <span><%= posterName%></span><br/></td> 
                            </tr>
                            <%
                                if(jobRateType.equals("Fixed")){
                            %>
                            <tr>
                                <td><i class="fa fa-tag" aria-hidden="true"></i><span><strong>&nbsp;&nbsp;Job Rate: </strong></span></td>
                                <td><ul class="list-inline mb-0"><li class="list-inline-item">S$<%= jobRate%>/Fixed Rate</li></ul></td>
                            </tr>
                            <%
                                }else{
                            %>
                            <tr>
                                <td><i class="fa fa-tag" aria-hidden="true"></i><span><strong>&nbsp;&nbsp;Job Rate: </strong></span></td>
                                <td><ul class="list-inline mb-0"><li class="list-inline-item">S$<%= jobRate%>/hour</li></ul></td>
                            </tr>
                            <%
                                }
                            %>
                            <tr>   
                                <td><i class="fa fa-heart" aria-hidden="true"></i><span><strong>&nbsp;&nbsp;Likes: </strong></span></td>
                                <%  if (posterName.equals(request.getAttribute("loggedInUsername"))) {%>
                                <td><a href="#"><ul class="list-inline mb-0"><li id="likeList" class="list-inline-item likeCount"><%=numOfLikes%> </li></ul></a></td>
                                <% }else{ %>
                                <td><ul class="list-inline mb-0"><li class="list-inline-item likeCount"><%=numOfLikes%> </li></ul></td>
                                <% }%>
                            </tr>
                            <tr>   
                                <td><i class="fa fa-sticky-note" aria-hidden="true"></i><span><strong>&nbsp;&nbsp;Est. Duration: </strong></span></td>
                                <td><ul class="list-inline mb-0"><li class="list-inline-item"><%=jobDuration%> hours</li></ul></td>
                            </tr>
                            <tr>   
                                <td><i class="fa fa-book" aria-hidden="true"></i><span><strong>&nbsp;&nbsp;Category: </strong></span></td>
                                <td><ul class="list-inline mb-0"><li class="list-inline-item"><%=categoryName%></li></ul></td>
                            </tr>
                            <tr>
                                <td><i class="fa fa-calendar" aria-hidden="true"></i><span><strong>&nbsp;&nbsp;Work Date: </strong></span></td>
                                <td><ul class="list-inline mb-0"><li class="list-inline-item"><%= jobWorkDate%></li></ul></td>
                            </tr>
                            <tr>
                                <td valign="top"><i class="fa fa-info-circle" aria-hidden="true"></i><span><strong>&nbsp;&nbsp;Description: </strong></span></td>
                                <td><ul class="list-inline mb-0"><li class="list-inline-item"><%= jobDescription%></li></ul></td>
                            </tr>
                        </table>
                        <br/>   
                        <div id="job-button" class="btn-group" role="group">
                            <%  if (posterName.equals(request.getAttribute("loggedInUsername"))) {%>
                            <button id="edit-button" type="button" class="btn btn-outline-theme" onclick="location.href = 'ErrandsSysUser?pageTransit=goToEditJobListing&hiddenJobID=<%= jobID%>&loginUser=<%= loggedInUsername%>'"><i class="fa fa-edit"></i>&nbsp;&nbsp;Edit Listing</button>
                            <button type="button" class="btn btn-outline-theme" onclick="javascript:deleteAlert(<%= jobID%>)">Delete Job Listing</button>
                            
                            <%  } else {    %>
                            <button type="button" class="btn btn-outline-theme"><i class="fa fa-comment"></i>&nbsp;&nbsp;Chat with Seller</button>
                            <button id="makeOfferBtn" type="button" class="btn btn-outline-theme" data-toggle="modal" data-target="#offerModal"><i class="fa fa-star"></i>&nbsp;&nbsp;Make Offer</button>
                            
                            
                            <div class="modal fade" id="offerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                              <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                  <div class="modal-header">
                                    <span class="modal-title" id="exampleModalLabel" style="color: #4D7496; font-size: 18px;"><strong><i class="fa fa-usd" aria-hidden="true"></i>&nbsp;Make offer to <u><%=posterName%></u></strong></span>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                                  </div>
                                    <span id="successOfferResponse"></span><span id="failedOfferResponse"></span>
                                 <form id="offerDetails">
                                   <div class="modal-body">
                                      <div class="form-group row">
                                        <label for="offerPrice" class="col-form-label col-3"><strong>Offer Price * : </strong></label>
                                        <div class="col-3">
                                            <input type="number" class="form-control" id="jobOfferPrice" name="jobOfferPrice" required>
                                        </div>
                                        <div class="col-4 mt-2 float-left">
                                            <span id="offerPriceType"></span>
                                        </div>
                                      </div>
                                      <div class="form-group">
                                        <label for="comment" class="col-form-label"><strong>Other comments:</strong></label>
                                        <textarea class="form-control" id="jobOfferDescription" name="jobOfferDescription"></textarea>
                                      </div>
                                   </div>
                                   <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="button" id="sendOfferBtn" class="btn btn-primary">Send offer</button>
                                    <br/>
                                    
                                   </div>
                                 </form>
                                </div>
                              </div>
                            </div>
                            
                            <%  }%>
                            <%if(likeStatus.equals("true")) {   %>
                            <button type="button" id="likeJobBtn" class="btn btn-outline-theme likeStatus" data-toggle="tooltip" data-placement="top" title="Unlike this job"><i class="fa fa-heart"></i>&nbsp; <span class="likeCount"><%= numOfLikes%></span></button>
                            <%  } else if(likeStatus.equals("false")) {    %>
                            <button type="button" id="likeJobBtn" class="btn btn-outline-theme noLikeStatus" data-toggle="tooltip" data-placement="top" title="Like this job"><i class="fa fa-heart"></i>&nbsp; <span class="likeCount"><%= numOfLikes%></span></button>
                            <%  }   %>
                          
                        </div>
                        <span id="test1"></span>
                    </div>
                </div>
                <div class="row" style="margin-top: 20px;">
                    <div class="col-12">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item"><a class="nav-link text-default active" id="itemTradePane-tab" data-toggle="tab" href="#itemTradePane" role="tab" aria-controls="itemTradePane" aria-selected="true">Work Information</a></li>
                            <%--<li class="nav-item"><a class="nav-link text-default" id="itemDescriptionPane-tab" data-toggle="tab" href="#itemDescriptionPane" role="tab" aria-controls="itemDescriptionPane" aria-selected="false">Item Information</a></li>--%>
                            <li class="nav-item">
                                <a class="nav-link text-default" id="itemSellerPane-tab" data-toggle="tab" href="#itemSellerPane" role="tab" aria-controls="itemSellerPane" aria-selected="false">Meet the Poster</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane border border-top-0 p-3 show active" id="itemTradePane" role="tabpanel" aria-labelledby="itemTradePane-tab">
                                <table class="table table-bordered mb-0">
                                    <tbody>
                                        <tr>
                                            <td class="bg-light w-25">Start Location</td>
                                            <td>
                                                <input type="hidden" id="startLocation" value="<%= jobStartLocation%>" />
                                                <input type="hidden" id="startLat" value="<%= jobStartLat%>" />
                                                <input type="hidden" id="startLong" value="<%= jobStartLong%>" />
                                                <%= jobStartLocation%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">End Location</td>
                                            <td>
                                                <input type="hidden" id="endLocation" value="<%= jobEndLocation%>" />
                                                <input type="hidden" id="endLat" value="<%= jobEndLat%>" />
                                                <input type="hidden" id="endLong" value="<%= jobEndLong%>" />
                                                <%= jobEndLocation%>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Work Location Map</td>
                                            <td><div id="mapdiv" style="width: auto; height: 300px;"></div></td>
                                        </tr>
                                        <tr>
                                            <td class="bg-light w-25">Other Information</td>
                                            <td><%= otherInformation%></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane border border-top-0 p-3" id="itemSellerPane" role="tabpanel" aria-labelledby="itemSellerPane-tab">
                               
                                <div class="media mb-2 mt-3">
                                    <div class="mr-0">
                                        <a href="ProfileSysUser?pageTransit=goToUserProfile&posterID=<%= posterName%>">
                                            <img class="user-img" src="uploads/commoninfrastructure/admin/images/<%= posterImage%>" height="50" width="50" />
                                        </a>
                                    </div>
                                    <div class="media-body col-md-12">
                                        <div style="cursor: pointer;" onclick="window.location='ProfileSysUser?pageTransit=goToUserProfile&posterID=<%= posterName%>';">
                                            <h5 class="user-name"><strong><%= posterName%></strong></h5>
                                            
                                        </div>
                                        <br/>
                                        <div class="rating">
                                            <ul class="profileRating">
                                                <li><img class="ratingImage" src="images/profilerating/positive.png" /><span class="ratingValue">0<span></li>
                                                <li><img class="ratingImage" src="images/profilerating/neutral.png" /><span class="ratingValue">0</span></li>
                                                <li><img class="ratingImage" src="images/profilerating/negative.png" /><span class="ratingValue">0</span></li>
                                            </ul>
                                        </div>
                                        <br/>
                                        <div class="summary-container">
                                            <div class="summary-column">
                                                <span class="count-title">LISTING ITEMS</span><br/>
                                                <span class="count"><strong>12</strong></span>
                                            </div>
                                            <div class="summary-column">
                                                <span class="count-title">LISTING JOBS</span><br/>
                                                <span class="count"><strong>5</strong></span>
                                            </div>
                                            <div class="summary-column">
                                                <span class="count-title">JOINED ON</span><br/>
                                                <span class="count"><strong><%=posterJoinDate%></strong></span>
                                            </div>
                                            <div class="summary-col">
                                                <span class="count-title">TITLE</span><br/>
                                                <span class="count"><strong>5</strong></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-5">
                    <div class="col-12">
                        <div class="title">
                            <span>You may also like</span>
                        </div>
                    </div>
                    <div class="col pl-sm-0 pr-sm-0">
                        <div class="owl-carousel owl-theme product-slider">
                            <%
                                ArrayList<Vector> assocCategoryJobListSYS = (ArrayList) request.getAttribute("assocCategoryJobListSYS");
                                if (!assocCategoryJobListSYS.isEmpty()) {
                                    for (int i = 0; i <= assocCategoryJobListSYS.size()-1; i++) {
                                        Vector v = assocCategoryJobListSYS.get(i);
                                        String assocCategoryJobID = String.valueOf(v.get(0));
                                        String assocCategoryJobImage = String.valueOf(v.get(1));
                                        String assocCategoryJobTitle = String.valueOf(v.get(2));
                                        String assocCategoryName = String.valueOf(v.get(3));
                                        String assocCategoryJobPosterID = String.valueOf(v.get(4));
                                        String assocCategoryJobPostedTime = String.valueOf(v.get(5));
                                        String assocCategoryJobRateType = String.valueOf(v.get(6));
                                        String assocCategoryJobRate = String.valueOf(v.get(7));
                            %>
                            <div class="product-slider-item">
                                <div class="card card-product">
                                    <div class="card-header" style="font-size: 13px;">
                                        <%= assocCategoryJobPosterID%><br/>
                                        <i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;<%= assocCategoryJobPostedTime%>
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <div class="img-wrapper">
                                                <a href="ErrandsSysUser?pageTransit=goToViewJobDetailsSYS&hiddenJobID=<%= assocCategoryJobID%>&hiddenCategoryName=<%= assocCategoryName%>">
                                                    <img class="card-img-top" src="uploads/unify/images/errands/job/<%= assocCategoryJobImage%>" />
                                                </a>
                                                <div class="tools tools-bottom" data-animate-in="fadeInDown" data-animate-out="flipOutX">
                                                    <div class="btn-group" role="group" aria-label="card-product-tools">
                                                        <button class="btn btn-link btn-sm">Chat with Seller</button>
                                                        <button class="btn btn-link btn-sm d-none d-md-inline-block"><i class="fa fa-heart"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                            <span class="card-title"><strong><a href="MarketplaceSysUser?pageTransit=goToViewItemDetailsSYS&hiddenItemID=<%= assocCategoryJobID%>&hiddenCategoryName=<%= assocCategoryName%>"><%= assocCategoryJobTitle%></a></strong></span><br/>
                                            <span class="card-text"><%= assocCategoryName%></span>
                                        </div>
                                    </div>
                                    <div class="card-footer text-muted mt-1">
                                        <span class="float-left"><span class="ml-1 price">$<%= assocCategoryJobRate%></span></span>
                                        <%--  if (assocCategoryItemLike.equals("0") || assocCategoryItemLike.equals("1")) {%>
                                        <span class="float-right"><i class="fa fa-heart-o"></i>&nbsp;<%= assocCategoryItemLike%>&nbsp;Like</span>
                                        <%  } else {%>
                                        <span class="float-right"><i class="fa fa-heart-o"></i>&nbsp;<%= assocCategoryItemLike%>&nbsp;Likes</span>
                                        <%  }   --%>
                                    </div>
                                </div>
                            </div>
                            <%      }   %>
                            <%  }   %>
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div id="unifyFooter"></div> -->
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="jobLikeList-iframe"></div>
            
                
                <input type="hidden" id="jobIDHidden" value="<%= jobID%>" />
                <input type="hidden" id="usernameHidden" value="<%= loggedInUsername%>" />
                <span id="successOfferResponse"></span><span id="failedOfferResponse"></span>
            
        </div>

        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/qtip/jquery.qtip-v3.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/errands/ViewJobDetailsSYSJS.js" type="text/javascript"></script>
    </body>
</html>
