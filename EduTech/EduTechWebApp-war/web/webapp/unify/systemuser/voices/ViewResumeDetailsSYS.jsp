<%-- 
    Document   : ViewResumeDeatilsSYS
    Created on : 31 Mar, 2018, 12:35:10 PM
    Author     : Zhu Xinyi
--%>

<%@page import="java.util.List"%>
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
        <title>Unify - My Resume List</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/weblayout/voices/ViewResumeDetailsSYSCSS.css" rel="stylesheet" type="text/css" />
        
        <link href="css/unify/systemuser/baselayout/datatable/dataTables.bootstrap.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/datatable/dataTables.responsive.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/datatable/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <!-- MOBILE SIDE NAVIGATION -->
        <nav class="offcanvas">
            <div class="offcanvas-content">
                <div id="list-menu" class="list-menu list-group" data-children=".submenu">
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
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
                    <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a>
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
                                    <a href="ProfileSysUser?pageTransit=goToViewChatListSYS&assocItemID=" class="nav-link"><i class="fa fa-comment"></i>&nbsp;&nbsp;My Chats</a>
                                </li>
                                <li class="nav-item d-none d-md-block">
                                    <div class="dropdown-container">
                                        <a href="#" class="nav-link" id="dropdown-cart" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="display: block;">
                                            <i class="fa fa-bell"></i>&nbsp;&nbsp;Notifications<span class="badge badge-light"><%= request.getAttribute("unreadNotificationCount")%></span>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-cart" aria-labelledby="dropdown-cart">
                                            <% 
                                                ArrayList<Vector> userMessageListTopThreeSYS = (ArrayList) request.getAttribute("userMessageListTopThreeSYS");
                                                if (!userMessageListTopThreeSYS.isEmpty()) {
                                                    for (int i = 0; i <= userMessageListTopThreeSYS.size() - 1; i++) {
                                                        Vector v = userMessageListTopThreeSYS.get(i);
                                                        String messageContent = String.valueOf(v.get(0));
                                                        String contentID = String.valueOf(v.get(1));
                                                        String messageType = String.valueOf(v.get(2));
                                                        String messageSenderImage = String.valueOf(v.get(4));
                                                        String messageSentDuration = String.valueOf(v.get(5));
                                            %>
                                            <div id="<%= messageType%><%= contentID%>" class="media messageDIV">
                                                <%  if (messageType.equals("System")) {%>
                                                <img class="img-thumbnail d-flex" src="images/<%= messageSenderImage%>" style="width:35px;height:35px;" />
                                                <%  } else {%>
                                                <img class="img-thumbnail d-flex" src="uploads/commoninfrastructure/admin/images/<%= messageSenderImage%>" style="width:35px;height:35px;" />
                                                <%  }%>
                                                <div class="message-content pl-3">
                                                    <div><%= messageContent%></div>
                                                    <small class="font-weight-normal message-content">
                                                        <i class="fa fa-clock-o"></i>&nbsp;<%= messageSentDuration%>&nbsp;(<%= messageType%>)
                                                    </small>
                                                </div>
                                            </div>
                                            <div class="dropdown-divider"></div>
                                            <%      }   %>
                                            <%  } else {    %>
                                            <p style="text-align:center;">There are no notifications.</p>
                                            <div class="dropdown-divider"></div>
                                            <%  }   %>
                                            <div class="text-center">
                                                <div class="btn-group btn-group-sm" role="group">
                                                    <a href="ProfileSysUser?pageTransit=goToUserNotificationListSYS" role="button" class="btn btn-outline-theme">
                                                        <i class="fa fa-envelope"></i>&nbsp;&nbsp;See All Notifications
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="dropdown-divider"></div>
                                        </div>
                                    </div>
                                </li>
                                <select class="select-dropdown-nav accountNavigation" data-width="120px">
                                    <option value="#" selected data-before='<i class="fa fa-user align-baseline" /></i>'>&nbsp;&nbsp;<%= loggedInUsername%></option>
                                    <option value="CommonInfra?pageTransit=goToCommonLanding" data-before='<i class="fa fa-external-link align-baseline" /></i>'>&nbsp;&nbsp;Landing Page</option>
                                    <option value="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" data-before='<i class="fa fa-user-circle align-baseline" /></i>'>&nbsp;&nbsp;My Account</option>
                                    <option value="CommonInfra?pageTransit=goToLogout" data-before='<i class="fa fa-sign-out align-baseline" /></i>'>&nbsp;&nbsp;Logout</option>
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
                            <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" class="d-lg-none">
                                <img src="images/edubox-unify-logo.png" class="logo" />
                            </a>
                            <a href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS" class="d-none d-lg-flex mb-2 mb-lg-0">
                                <img src="images/edubox-unify-logo.png" class="logo" />
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
                                <button type="button" class="btn btn-outline-theme newItemListingBtn">
                                    <i class="fa fa-user-plus d-none d-lg-inline-block"></i>&nbsp;Sell An Item
                                </button>
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
                            <li class="breadcrumb-item active" aria-current="page">User Resume Details</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div id="contentArea" class="container jplist mb-3">
                <div class="row">
                    <div class="col-lg-2 col-md-1" ></div>
                    <!--RESUME CONTENT-->
                    <% 
                        Vector basicDetailsSYS = (Vector) request.getAttribute("basicDetailsVec");
                        String image, userFullName, userContactNum, userEmailAddr, userPostalAddr, userSummary, userAwardStr;
                        image = userFullName = userContactNum = userEmailAddr = userPostalAddr = userSummary = userAwardStr = "";
    
                        if(basicDetailsSYS!=null) {
                            image = String.valueOf(basicDetailsSYS.get(0));
                            userFullName = String.valueOf(basicDetailsSYS.get(1));
                            userContactNum = String.valueOf(basicDetailsSYS.get(2));
                            userEmailAddr = String.valueOf(basicDetailsSYS.get(3));
                            userPostalAddr = String.valueOf(basicDetailsSYS.get(4));
                            userSummary = String.valueOf(basicDetailsSYS.get(5));
                            userAwardStr = String.valueOf(basicDetailsSYS.get(6));
                        }
                    %>
                    
                    <div class="resumeContent col-lg-9 col-md-8" >
                        <div class="input-group-addon" style="float: right; margin-top: 10px; margin-right: 50px">
                            <button onclick="download()" class="btn btn-sm btn-theme">&nbsp;Download</button>
                        </div>
                        <div class="wrapper" id="wrapper">
                            <!--SIDEBAR WRAPPER-->
                            <div class="sidebar-wrapper">
                                <!--PROFILE CONTAINER-->
                                <div class="profile-container">
                                    <img class="profile" src="uploads/unify/images/voices/resume/<%= image%>" alt="" />
                                        <h1 class="name"><%= userFullName%></h1>
                                </div>
                                
                                <!--CONTACT CONTAINER-->
                                <div class="contact-container container-block">
                                    <ul class="list-unstyled contact-list">
                                        <li class="email"><i class="fa fa-envelope"></i><%= userEmailAddr%></li>
                                        <li class="phone"><i class="fa fa-phone"></i><%= userContactNum%></li>
                                        <li class="home"><i class="fa fa-home"></i><%= userPostalAddr%></li>
                                    </ul>
                                </div>
                                
                                <!--EDUCATION CONTAINER-->
                                <%
                                    List<Vector> eduExprList = (ArrayList) request.getAttribute("eduExprList");
                                    if(eduExprList!=null) {
                                %>
                                <div class="education-container container-block">
                                    <h2 class="container-block-title">Education</h2>
                                    <% 
                                        for(int i=0;i<eduExprList.size();i++) {
                                            Vector v = eduExprList.get(i);
                                            String schoolName = String.valueOf(v.get(0));
                                            String schoolPeriod = String.valueOf(v.get(1));
                                            String schoolDegree = String.valueOf(v.get(2));
                                            String schoolMajor = String.valueOf(v.get(3));
                                    %>
                                    <div class="item">
                                        <h4 class="degree"><%= schoolDegree%> in <%= schoolMajor%></h4>
                                        <h5 class="meta"><%= schoolName%></h5>
                                        <div class="time"><%= schoolPeriod%></div>
                                    </div>
                                    <%      } 
                                        }%>
                                </div>
                                
                                <!--ACHIEVEMENTS-->
                                <% String[] award = userAwardStr.split(";&");%>
                                <div class="awards-container container-block">
                                    <% if(award.length != 0) {%>
                                    <h2 class="container-block-title">Awards & Achievements</h2>
                                    <ul class="list-unstyled awards-list">
                                        <% for(int i=0;i<award.length;i++) {%>
                                        <li><%= award[i]%></li>
                                        <% } %>
                                    </ul>
                                    <% } %>
                                </div>
                            </div>
                                
                            <!--MAIN WRAPPER-->
                            <div class="main-wrapper">
                                <!--SUMMARY SECTION-->
                                <section class="section summary-section">
                                    <h2 class="section-title"><i class="fa fa-user"></i>Career Profile</h2>
                                    <div class="summary">
                                        <p><%= userSummary%></p>
                                    </div>
                                </section>
                                    
                                <!--EXPERIENCE SECTION-->
                                <%
                                    List<Vector> workExprList = (ArrayList) request.getAttribute("workExprList");
                                    if(!workExprList.isEmpty()) {
                                %>
                                <section class="section experiences-section">
                                    <h2 class="section-title"><i class="fa fa-briefcase"></i>Working Experiences</h2>
                                    <% 
                                        for(int i=0;i<workExprList.size();i++) {
                                            Vector v = workExprList.get(i);
                                            String workCompany = String.valueOf(v.get(0));
                                            String workTitle = String.valueOf(v.get(1));
                                            String workPeriod = String.valueOf(v.get(2));
                                            String workDes = String.valueOf(v.get(3));
                                    %>
                                    <div class="item">
                                        <div class="meta">
                                            <div class="upper-row">
                                                <h3 class="job-title"><%= workTitle%></h3>
                                                <div class="time"><%= workPeriod%></div>
                                            </div>
                                            <div class="company"><%= workCompany%></div>
                                        </div>
                                        <div class="details">
                                            <p><%= workDes%></p>
                                        </div>
                                    </div>
                                    <% } %>
                                </section>
                                <% } %>
                                
                                <!--PROJECT SECTION-->
                                <%  
                                    List<Vector> proExprList = (ArrayList) request.getAttribute("proExprList");
                                    if(!proExprList.isEmpty()) {
                                %>
                                <section class="section projects-section">
                                    <h2 class="section-title"><i class="fa fa-archive"></i>Projects</h2>
                                    <%
                                        for(int i=0;i<proExprList.size();i++) {
                                            Vector v = proExprList.get(i);
                                            String proTitle = String.valueOf(v.get(0));
                                            String proDes = String.valueOf(v.get(1));
                                    %>
                                    <div class="item">
                                        <div class="project-title"><%= proTitle%></div>
                                        <div class="details"><%= proDes%></div>
                                    </div>
                                    <% } %>
                                </section>
                                <% } %>
                                
                                <!--REFERENCE SECTION-->
                                <%
                                    List<Vector> referenceList = (ArrayList) request.getAttribute("referenceList");
                                    if(!referenceList.isEmpty()) {
                                %>
                                <section class="section reference-section">
                                    <h2 class="section-title"><i class="fa fa-comment"></i>References</h2>
                                    <% 
                                        for(int i=0;i<referenceList.size();i++) {
                                            Vector v = referenceList.get(i);
                                            String refName = String.valueOf(v.get(0));
                                            String refCompany = String.valueOf(v.get(1));
                                            String refPosition = String.valueOf(v.get(2));
                                            String refContent = String.valueOf(v.get(3));
                                    %>
                                    <div class="item">
                                        <div class="meta">
                                            <div class="upper-row">
                                                <div class="content">"<%= refContent%>"</div><br/>
                                                <div class="reference">--&nbsp;<%= refName%>; &nbsp;<%= refPosition%>&nbsp;at&nbsp;<%= refCompany%></div>
                                            </div>
                                        </div>
                                    </div>
                                    <% } %>
                                </section>
                                <% } %>
                                
                                <!--SKILL SECTION-->
                                <%  
                                    List<Vector> proSkillList = (ArrayList) request.getAttribute("proSkillList");
                                    List<Vector> perSkillList = (ArrayList) request.getAttribute("perSkillList");
                                    int count = Math.max(proSkillList.size(), perSkillList.size());
                                    if(count!=0) {
                                %>
                                <section class="skills-section section">
                                    <h2 class="section-title"><i class="fa fa-rocket"></i>Skills &amp; Proficiency</h2>
                                    <%
                                        for(int i=0;i<count;i++) {
                                            String proSkillName, proSkillLevel, perSkillName, perSkillLevel;
                                            int pro_proficiency = 0, per_proficiency = 0;
                                            proSkillName = proSkillLevel = perSkillName = perSkillLevel = "";
                                            if(i<proSkillList.size()) {
                                                Vector pro = proSkillList.get(i);
                                                proSkillName = String.valueOf(pro.get(0));
                                                proSkillLevel = String.valueOf(pro.get(1));
                                                if(proSkillLevel.equals("Beginner")) { pro_proficiency = 25; }
                                                else if(proSkillLevel.equals("Intermediate")) { pro_proficiency = 50; }
                                                else { pro_proficiency = 75; }
                                            }
                                            if(i<perSkillList.size()) {
                                                Vector per = perSkillList.get(i);
                                                perSkillName = String.valueOf(per.get(0));
                                                perSkillLevel = String.valueOf(per.get(1));
                                                if(perSkillLevel.equals("Beginner")) { per_proficiency = 25; }
                                                else if(perSkillLevel.equals("Intermediate")) { per_proficiency = 50; }
                                                else { per_proficiency = 75; }
                                            }
                                    %>
                                    <div class="skillset">
                                        
                                        <div class="item">
                                            <% if(!proSkillName.equals("")) { %>
                                            <h3 class="level-title"><%= proSkillName%></h3>
                                            <div class="level-bar">
                                                <div class="level-bar-inner" data-level="25%" style="width: <%= pro_proficiency%>%;"></div>                                      
                                            </div><!--//level-bar--> 
                                            <% } %>
                                        </div>
                                        
                                        
                                        <div class="item">
                                            <% if(!perSkillName.equals("")) { %>
                                            <h3 class="level-title"><%= perSkillName%></h3>
                                            <div class="level-bar">
                                                <div class="level-bar-inner" data-level="50%" style="width: <%= per_proficiency%>%;"></div>                                      
                                            </div><!--//level-bar-->
                                            <% } %>
                                        </div>
                                        
                                    </div>
                                    <% } %>
                                </section>
                                <% } %>
                            </div>
                        </div>         
                    </div>                
                </div>
            </div>
                                    
            <div class="chat-main">
                <div class="col-md-12 chat-header">
                    <div class="row header-one text-white p-1">
                        <div class="col-md-6 name pl-2">
                            <i class="fa fa-comment"></i>
                            <h6 class="ml-1 mb-0 mt-1">Unify Bot</h6>
                        </div>
                        <div class="col-md-6 options text-right pr-0">
                            <i class="fa fa-window-minimize hide-chat-box hover text-center"></i>
                        </div>
                    </div>
                </div>
                <div class="chat-content">
                    <div class="col-md-12 chats">
                        <iframe src="ProfileSysUser?pageTransit=goToUnifyBot" width="305" height="285" frameborder="0" ></iframe>
                    </div>
                </div>
            </div>
            
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="marketplace-overlay"></div>
            
            <!-- Modal -->
            <div class="modal fade" id="errandsModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="width: 1200px;">
              <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Select one of the following to view.</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                     <div class="row">
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToMyJobListing';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-shopping-cart display-2"></i></h1>
                                        <h6>My Listings</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToErrandsTrans';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-book display-2"></i></h1>
                                        <h6>My Transactions</h6>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToViewMyJobOfferSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-edit display-2"></i></h1>
                                        <h6>My Offers</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ErrandsSysUser?pageTransit=goToViewJobOfferList';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-bullhorn display-2"></i></h1>
                                        <h6>Errands Offers</h6>
                                    </div>
                                </div>
                            </div>
                  </div>
                </div>
              </div>
            </div>
            
        </div>
        
        <!-- Voices Modal-->
        <div class="modal fade" id="voicesModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="width: 1200px;">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle" style="font-size: 15px"><strong>Select one of the following to view.</strong></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToCompanyReview';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-building"></i></h1>
                                        <h6>My Company Reviews</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToCompanyRequest';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-question-circle"></i></h1>
                                        <h6>My Company Requests</h6>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToResume';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-file"></i></h1>
                                        <h6>My Resume List</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
    
        <script src="js/unify/systemuser/basejs/datatable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/dataTables.responsive.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/datatable/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/voices/ViewResumeDetailsSYSJS.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/voices/html2canvas.min.js" type="text/javascript"></script>
        <script src="https://unpkg.com/jspdf@latest/dist/jspdf.min.js"></script>
    </body>
</html>
