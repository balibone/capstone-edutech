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
        <title>Unify Voices - New Resume</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/leaflet/leaflet.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/voices/NewResumeSYSCSS.css" rel="stylesheet" type="text/css">
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
                            <li class="breadcrumb-item active" aria-current="page">Marketplace (New Item Listing)</li>
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
                            
                            <div class="x_title">
                                <h5>New Resume</h5>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <p>Fill in the form to create your resume. </p>
                                <div id="wizard" class="form_wizard wizard_horizontal">
                                    <ul class="wizard_steps nav nav-tabs" role="tablist" style="padding-bottom: 20px;">
                                        <li>
                                            <a href="#step-1">
                                                <span class="step_no">1</span>
                                                <span class="step_descr">Step 1<br /><small>Enter Basic Information</small></span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#step-2">
                                                <span class="step_no">2</span>
                                                <span class="step_descr">Step 2<br /><small>Enter Work Experiences</small></span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#step-3">
                                                <span class="step_no">3</span>
                                                <span class="step_descr">Step 3<br /><small>Enter Edu Experiences</small></span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#step-4">
                                                <span class="step_no">4</span>
                                                <span class="step_descr">Step 4<br /><small>Enter Project Experiences</small></span>
                                            </a>
                                        </li>
                                    </ul>
                                    <form class="form-horizontal form-label-left" action="VoicesSysUser" method="POST" name="resumeForm" enctype="multipart/form-data">
                                        <div id="step-1">
                                            <div class="form-row" style="justify-content: center;">
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <div class="image-upload">
                                                            <img id="output-image" />
                                                        </div>
                                                        <label for="file-upload" class="btn btn-theme btn-sm btn-block" style="margin-top: 10px; width: 151px;">
                                                            <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                                                        </label>
                                                        <input id="file-upload" name="userImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" required="required" />
                                                    </div>
                                                </div>
                                                <div class="col-md-4 ml-2">
                                                    <div class="form-group">
                                                        <label for="userFullName">Full Name&nbsp;<span class="asterik">*</span></label>
                                                        <input type="text" class="form-control" name="userFullName" placeholder="Enter your full name" required="required" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="contactNum">Contact Number&nbsp;<span class="asterik">*</span></label><br/>
                                                        <input type="text" class="form-control" name="contactNum" placeholder="Enter your contact number" required="required" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="emailAddr">Email Address&nbsp;<span class="asterik">*</span></label><br/>
                                                        <input type="text" class="form-control" name="emailAddr" placeholder="Enter your email address" required="required" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="postalAddr">Postal Address&nbsp;<span class="asterik">*</span></label><br/>
                                                        <input type="text" class="form-control" name="postalAddr" placeholder="Enter your postal address" required="required" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="step-2">
                                            <div class="form-row" style="justify-content: center;">
                                                <table id="workExprTable" >
                                                    <tr>
                                                        <th style="text-align:center">Work Title</th>
                                                        <th style="text-align:center">Work Company</th>
                                                        <th style="text-align:center">Work Period</th>
                                                        <th style="text-align:center">Work Description</th>
                                                        <th></th>
                                                    </tr>
                                                    <tr>
                                                        <td><input type="text" class="form-control" name="workTitle[]" style="margin-right: 10px"/></td>
                                                        <td><input type="text" class="form-control" name="workCompany[]" /></td>
                                                        <td><input type="text" class="form-control" name="workPeriod[]" /></td>
                                                        <td><textarea class="form-control" name="workDescription[]" rows="1"></textarea></td>
                                                        <td>&nbsp;&nbsp;<i class="fa fa-plus-circle fa-2x" onclick="addWorkExprRow()"></i></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                        <div id="step-3">
                                            <div class="form-row" style="justify-content: center;">
                                                <table id="eduExprTable" >
                                                    <tr>
                                                        <th style="text-align:center">School Name</th>
                                                        <th style="text-align:center">Degree</th>
                                                        <th style="text-align:center">Major</th>
                                                        <th style="text-align:center">Education Period</th>
                                                        <th></th>
                                                    </tr>
                                                    <tr>
                                                        <td><input type="text" class="form-control" name="schoolName[]" style="margin-right: 10px"/></td>
                                                        <td><input type="text" class="form-control" name="schoolDegree[]" /></td>
                                                        <td><input type="text" class="form-control" name="schoolMajor[]" /></td>
                                                        <td><input type="text" class="form-control" name="schoolPeriod[]" /></td>
                                                        <td>&nbsp;&nbsp;<i class="fa fa-plus-circle fa-2x" onclick="addEduExprRow()"></i></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </div>
                                        <div id="step-4">
                                            <div class="form-row">
                                                <div class="col-md-6" id="projectExprTable">
                                                    <div class="form-group row">
                                                        <div class="form-group col-2"></div>
                                                        <div class="form-group col-8">
                                                            <label for="projectTitle">Project Title&nbsp;</label>
                                                            <input type="text" class="form-control" name="projectTitle[]" />
                                                            <label for="projectDes">Project Description&nbsp;</label>
                                                            <textarea type="text" class="form-control" name="projectDes[]" rows="3"></textarea>
                                                        </div>
                                                        <div class="col-2" style="justify-content: left;">
                                                            <label></label><br/><br/>
                                                            <i class="fa fa-plus-circle fa-2x" onclick="addProjectExprRow()"></i>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-row">
                                                        <div class="form-group col-2"></div>
                                                        <table id="skillSetTable">
                                                            <tr>
                                                                <th style="text-align:center">Skill</th>
                                                                <th style="text-align:center">Skill Level</th>
                                                                <th></th>
                                                            </tr>
                                                            <tr>
                                                                <td><input type="text" class="form-control" name="skillName[]" /></td>
                                                                <td><select class="form-control" name="skillLevel[]">
                                                                        <option value="" default>-- Select Skill Level --</option>
                                                                        <option value="Beginner">Beginner</option>
                                                                        <option value="Intermediate">Intermediate</option>
                                                                        <option value="Advanced">Advanced</option>
                                                                    </select></td>
                                                                <td>&nbsp;&nbsp;<i class="fa fa-plus-circle fa-2x" onclick="addSkillSetRow()"></i></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-row" style="justify-content: center;">
                                                 <input type="hidden" name="pageTransit" value="createResumeSYS" />
                                                 <input type="hidden" name="username" value="<%= loggedInUsername%>" />
                                                 <input type="hidden" name="workExprList" id="workExprList" value="" />
                                                 <input type="hidden" name="eduExprList" id="eduExprList" value="" />
                                                 <input type="hidden" name="proExprList" id="proExprList" value="" />
                                                 <input type="hidden" name="skillList" id="skillList" value="" />
                                                 <button class="btn btn-theme btn-search" type="submit" onclick="createWorkExprList()"></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>
            
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
        <script src="js/unify/systemuser/webjs/voices/NewResumeSYSJS.js" type="text/javascript"></script>                                    
    </body>
</html>
