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
        <title>Unify Errands - New Job Listing</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/errands/NewJobListingSYSCSS.css" rel="stylesheet" type="text/css">
        
    </head>

    <body class="nav-md">
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
                                <a class="btn btn-outline-theme" href="ProfileSysUser?pageTransit=goToUserAccount" role="button">
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
                            <li class="breadcrumb-item active" aria-current="page">Errands (New Job Listing)</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="container" style="margin-bottom: 30px;">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <%                                
                                String successMessage = (String) request.getAttribute("successMessage");
                                if (successMessage != null) {
                            %>
                            <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                                <button type="button" class="close" id="closeSuccess">&times;</button>
                                <%= successMessage %>
                            </div>
                            <%  } %>
                            <%
                                String errorMessage = (String) request.getAttribute("errorMessage");
                                if (errorMessage != null) {
                            %>
                            <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                                <button type="button" class="close" id="closeError">&times;</button>
                                <%= errorMessage %>
                            </div>
                            <%  } %>
                            
                            
                            
                                <p>Enter the job information here. (<span class="asterik">*</span>) fields are mandatory.</p>
                                <div id="accordion" >
                                    <div class="card border-0">
                                        <div class="card-header" id="headingOne" style="background-color: white;">
                                            <span class="step-title"><strong>1. Choose a category</strong></span> &nbsp;
                                            <i class="fa fa-check-circle" aria-hidden="true"></i>
                                            Selected: <span id="selected-category"> </span>
                                            
                                                <button class="btn float-right mt-3" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                            
                                        </div>

                                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                                            <div class="card-body">
                                                <div class="choose-category">
                                                    
                                                    
                                                    <div id="category-pool">
                                                        <div class="row category-list">
                                                            <%
                                                                ArrayList<Vector> jobCategoryListSYS = (ArrayList) request.getAttribute("jobCategoryListSYS");
                                                                if (!jobCategoryListSYS.isEmpty()) {
                                                                for (int i = 0; i <= jobCategoryListSYS.size() - 1; i++) {
                                                                    Vector v = jobCategoryListSYS.get(i);
                                                                    String categoryImage = String.valueOf(v.get(0));
                                                                    String categoryID = String.valueOf(v.get(1));
                                                                    String categoryName = String.valueOf(v.get(2));
                                                            %>
                                                            <div class="col-sm-4 mt-2">
                                                                <div class="card" id="<%=categoryID%>" name="<%=categoryName%>">
                                                                    <div class="card-body">
                                                                        <div class="row">
                                                                            <div class="col-3"><img src="uploads/unify/images/common/category/<%= categoryImage%>" style="width: 60px; height: 60px; " /></div>
                                                                            <div class="col-9"><span><strong><%= categoryName%></strong></span> <br/> test</div>
                                                                        </div>
        					
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <%      }   %>
                                                            <%  }%>
                                                        </div>
                                                    </div>
                                                    <h5 class="mb-0">
                                                    <button class="btn float-right next-button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">Continue
                                                    </button>
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card border-0">
                                        <div class="card-header" id="headingTwo" style="background-color: white;">
                                            <span class="step-title"><strong>2. Fill up job details</strong></span>
                                            <button class="btn float-right collapsed mt-3" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            <span><i class="fa fa-pencil-square-o" aria-hidden="true"></i></span>
                                            </button>
                                        </div>
                                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                            <div class="card-body">
                                                <div class="form-row" style="justify-content: center;">
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <div class="image-upload">
                                                                <img id="output-image" />
                                                            </div>
                                                            <label for="file-upload" class="btn btn-theme btn-sm btn-block" style="margin-top: 10px; width: 171px;">
                                                                <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                                                            </label>
                                                            <input id="file-upload" name="jobImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" required="required" />
                                                        </div>
                                                    </div>
                                                    <div class="col-md-8 ml-3">
                                                        <div class="form-group row">
                                                            <label for="jobTitle" class="col-sm-3 col-form-label"><strong>Job Title</strong>&nbsp;<span class="asterik">*</span></label>
                                                            <div class="col-sm-9">
                                                              <input type="text" class="form-control" name="jobTitle" placeholder="Enter the job title">
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="form-group row">
                                                            <label for="workDate" class="col-sm-3 col-form-label"><strong>Work Date & Time</strong>&nbsp;<span class="asterik">*</span></label>
                                                            <div class="col-sm-5">
                                                                <input type="date" class="form-control" name="workDate" required="required" />
                                                            </div>
                                                            <div class="col-sm-4">
                                                              <input type="time" class="form-control" name="workTime" />
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="jobDescription" class="col-sm-3 col-form-label"><strong>Job Description</strong></label>
                                                            <div class="col-sm-9">
                                                                <textarea class="form-control" name="jobDescription" rows="5" placeholder="Enter the job description" required="required"></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="workDate" class="col-sm-5 col-form-label"><strong>What is your budget for the job? </strong>&nbsp;<span class="asterik">*</span></label>
                                                            
                                                            <div class="col-sm-3">
                                                                <select class="select-dropdown" name="jobRateType" id="jobRateType" data-width="100%" onchange="javascript: displayDuration()">
                                                                    <option value="Fixed">Fixed Price</option>
                                                                    <option value="HR">Hourly Rate</option>
                                                                </select>
                                                            </div>
                                                           
                                                            <div class="col-sm-3">
                                                                <input type="number" class="form-control" name="jobRate" placeholder="Job Rate(S$)"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="jobDuration" class="col-sm-3 col-form-label"><strong>How big is the task? </strong>&nbsp;<span class="asterik">*</span></label>
                                                            <div class="col-sm-3 mt-2">
                                                                <div class="form-check form-check-inline">
                                                                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                                                    <label class="form-check-label" for="inlineRadio1">Small: <1hr</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-3 mt-2">
                                                                <div class="form-check form-check-inline">
                                                                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                                                    <label class="form-check-label" for="inlineRadio1">Medium: 2-3hrs</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-3 mt-2">
                                                                <div class="form-check form-check-inline">
                                                                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                                                    <label class="form-check-label" for="inlineRadio1">Large: >4hrs</label>
                                                                </div>
                                                            </div>
                                                            
                                                        </div>
                                                        <div class="form-group row">
                                                            <label for="numOfHelpers" class="col-sm-6 col-form-label"><strong>How many helpers do you need for the job? </strong>&nbsp;<span class="asterik">*</span></label>
                                                            <div class="col-sm-3">
                                                                <input type="number" class="form-control" name="numOfHelpers"  placeholder="number of helpers" required="required"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                <button class="btn next-button float-right collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                Continue
                                                </button>
                                            </div>
                                            
                                        </div>
                                    </div>
                                    <div class="card border-0">
                                        <div class="card-header" id="headingThree" style="background-color: white;">
                                            <span class="step-title"><strong>3. Select work location</strong></span>
                                           
                                        </div>
                                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                                            <div class="card-body">
                                                <div class="form-row" style="justify-content: center;">
                                                    <div class="col-md-4">
                                                        <div class="form-group">
                                                            <div id="mapdiv" style="width: auto; height: 300px;"> </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6 ml-2">
                                                        <div class="form-group" style="position: relative;">
                                                            <label for="startLocation"><strong>Start Location&nbsp;<span class="asterik">*</span></strong></label>
                                                            <input type="text" class="form-control" id="startLocation" name="startLocation" placeholder="Enter the start location" required="required" />
                                                            <div id="searchResults_start"></div>
                                                        </div>
                                                        <div class="form-group" style="position: relative;">
                                                            <label for="endLocation"><strong>End Location&nbsp;<span class="asterik">*</span></strong></label>
                                                            <input type="text" class="form-control" id="endLocation" name="endLocation" placeholder="Enter the end location" required="required" />
                                                            <div id="searchResults_end"></div>
                                                        </div>
                                                          
                                                    </div>
                                                    
                                            </div>
                                        </div>
                                        <input type="hidden" name="pageTransit" value="createJobListingSYS" />
                                        <input type="hidden" name="username" value="<%= loggedInUsername%>" />
                                        <input type="hidden" name="hiddenStartLat" id="hiddenStartLat" />
                                        <input type="hidden" name="hiddenStartLong" id="hiddenStartLong" />
                                        <input type="hidden" name="hiddenEndLat" id="hiddenEndLat" />
                                        <input type="hidden" name="hiddenEndLong" id="hiddenEndLong" />
                                        <input type="hidden" name="hiddenCategoryID" id="hiddenCategoryID" />
                                        <div class="form-group row" style="text-align: center;">
                                            <div class="col-sm-12 mt-1">
                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                                    <label class="form-check-label" for="inlineRadio1">(Optional) <strong>I want to have a checking before job helpers complete the job.</strong></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group" style="text-align: center;">
                                            <button type="submit" class="btn btn-theme">Create Job Listing</button>
                                        </div>
                                    </div>
                                </div>
                                
                            
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div id="unifyFooter"></div> -->

            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
        </div>

        <!-- #1. jQuery v2.2.4 -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v2.2.4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/validator-v1.1.0.js" type="text/javascript"></script>

        <script src="js/unify/systemuser/webjs/marketplace/jquery.smartWizard-v3.3.1.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/custom.min.js"></script>
        <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/errands/NewJobListingSYSJS.js" type="text/javascript"></script>
    </body>
</html>