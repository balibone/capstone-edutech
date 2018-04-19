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
        <title>Unify Voices - New Resume</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">
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
                            <li class="breadcrumb-item active" aria-current="page">Resume List (New Resume)</li>
                        </ol>
                    </nav>
                </div>
            </div>
            
            <div class="container" style="margin-bottom: 30px;">
                <div class="row">
                    <div class="resumeForm col-lg-12 col-md-12">
                        <%                                
                            String successMessage = (String) request.getAttribute("successMessage");
                            if (successMessage != null) {
                        %>
                        <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                            <button type="button" class="close" id="closeSuccess">&times;</button>
                            <%= successMessage %>
                        </div>
                        <%  } %>
                        <div class="x_title">
                            <h5>New Resume</h5>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <p>Fill in the form to create your resume. </p>
                            <form class="form-horizontal form-label-left" action="ProfileSysUser" method="POST" name="resumeForm" enctype="multipart/form-data">
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody>
                                        <tr bgcolor="#4D7496">
                                            <td colspan="4" valign="top">
                                                <b><center style="color: #fff">Resume Created By <%= loggedInUsername%></center></b>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#D9DEE4">
                                            <td colspan="4" valign="top">&nbsp;</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody>
                                        <tr bgcolor="#4D7496">
                                            <td colspan="4" valign="top">
                                                <b style="color: #fff">Personal Information</b>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <% 
                                    Vector basicDetailsSYS = (Vector) request.getAttribute("basicDetailsVec");
                                    String image, userFullName, userContactNum, userEmailAddr, userPostalAddr, userSummary, userAwardStr;
                                        image = userFullName = userContactNum = userEmailAddr = userPostalAddr = userSummary = userAwardStr = "";
   
                                        image = String.valueOf(basicDetailsSYS.get(0));
                                        userFullName = String.valueOf(basicDetailsSYS.get(1));
                                        userContactNum = String.valueOf(basicDetailsSYS.get(2));
                                        userEmailAddr = String.valueOf(basicDetailsSYS.get(3));
                                        userPostalAddr = String.valueOf(basicDetailsSYS.get(4));
                                        userSummary = String.valueOf(basicDetailsSYS.get(5));
                                        userAwardStr = String.valueOf(basicDetailsSYS.get(6));
                                        String[] contactNum = userContactNum.split("\\+");
                                %>
                                <div class="row" style="background-color: #D9DEE4; display: table-row; border-spacing: 10px">
                                    <div class="ml-1" style="display: table-cell">
                                        <div class="form-group" style="margin-left: 80px; margin-top: 30px; margin-right: 50px;">
                                            <div class="image-upload">
                                                <img id="output-image" src="uploads/unify/images/voices/resume/<%= image%>"/>
                                                <input type="hidden" name="oldUserImage" value="<%= image%>" />
                                            </div>
                                            <label for="file-upload" class="btn btn-theme btn-sm btn-block" style="margin-top: 10px; width: 151px;">
                                                <i class="fa fa-cloud-upload"></i>&nbsp;&nbsp;Upload Image
                                            </label>
                                            <input id="file-upload" name="userImage" type="file" accept="image/*" onchange="javascript: previewImage(event)" />
                                        </div>
                                    </div>
                                    <div class="col-md-1" style="padding-top: 30px; display: table-cell; vertical-align:middle;">
                                        <div class="form-group">
                                            <label for="userFullName">Full Name&nbsp;<span class="asterik">*</span></label>
                                            <input type="text" class="form-control" name="userFullName" value="<%= userFullName%>" style="width: 50%" required="required"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="contactNum">Contact Number&nbsp;<span class="asterik">*</span></label><br/>
                                            <select class="form-control" name="countryCode" id="countryCode" style="height: 34.67px; width: 9%; float: left" required>
                                                <option value="065" <%if((contactNum[0].equals("065"))){%> selected <%}%>>065</option>
                                                <option value="852" <%if((contactNum[0].equals("852"))){%> selected <%}%>>852</option>
                                                <option value="091" <%if((contactNum[0].equals("091"))){%> selected <%}%>>091</option>
                                            </select>
                                            <input type="text" class="form-control" name="contactNum" id="contactNum" value="<%= contactNum[1]%>" style="width: 41%" required="required"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="emailAddr">Email Address&nbsp;<span class="asterik">*</span></label><br/>
                                            <input type="text" class="form-control" name="emailAddr" value="<%= userEmailAddr%>" style="width: 50%" required="required"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="postalAddr">Postal Address&nbsp;<span class="asterik">*</span></label><br/>
                                            <input type="text" class="form-control" name="postalAddr" value="<%= userPostalAddr%>" required="required"/>
                                        </div>
                                    </div>
                                </div>
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody>
                                        <tr bgcolor="#4D7496">
                                            <td colspan="4" valign="top">
                                                <b style="color: #fff">Summary</b>
                                            </td>
                                        </tr>
                                        <tr bgcolor="#D9DEE4">
                                            <td colspan="4" valign="top" style="padding: 10px">
                                                <textarea type="text" class="form-control" name="summary" rows="3"><%= userSummary%></textarea>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody id="eduExprTable">
                                        <tr bgcolor="#4D7496">
                                            <td colspan="5" valign="top">
                                                <b style="color: #fff">Education Experience</b>
                                           </td>
                                        </tr>
                                        <%
                                            List<Vector> eduExprList = (ArrayList) request.getAttribute("eduExprList");
                                            if(eduExprList.isEmpty()) {
                                        %>
                                        <tr class="eduExpr">
                                            <td valign="middle"><b><center>School Name: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolName[]" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"><b><center>Period: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolPeriod[]" style="margin-right: 10px; width: 50%"/>
                                            <td></td>
                                        </tr>
                                        <tr class="eduExpr">
                                            <td valign="middle"><b><center>Degree: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolDegree[]" style="margin-right: 10px; width: 80%"/>
                                            <td valign="middle"><b><center>Major: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolMajor[]" style="margin-right: 10px; width: 80%"/>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="eduExprRemoveBtn"></i></td>
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="eduExprBtn"></i></td>
                                        </tr>
                                        <% } else {
                                                for(int i=0;i<eduExprList.size();i++) {
                                                    Vector v = eduExprList.get(i);
                                                    String schoolName = String.valueOf(v.get(0));
                                                    String schoolPeriod = String.valueOf(v.get(1));
                                                    String schoolDegree = String.valueOf(v.get(2));
                                                    String schoolMajor = String.valueOf(v.get(3));%>
                                        <tr class="eduExpr">
                                            <td valign="middle"><b><center>School Name: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolName[]" value="<%= schoolName%>" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"><b><center>Period: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolPeriod[]" value="<%= schoolPeriod%>" style="margin-right: 10px; width: 50%"/>
                                            <td></td>
                                        </tr>
                                        <tr class="eduExpr">
                                            <td valign="middle"><b><center>Degree: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolDegree[]" value="<%= schoolDegree%>" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"><b><center>Major: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="schoolMajor[]" value="<%= schoolMajor%>" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <% if(i==0) {%>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="eduExprRemoveBtn"></i></td>
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="eduExprBtn"></i></td>
                                            <% } else {%><td></td>
                                            <% } %>
                                        </tr>
                                        <%      } 
                                            }%>
                                    </tbody>
                                </table>
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody id="proExprTable">
                                        <tr bgcolor="#4D7496">
                                            <td colspan="4" valign="top">
                                                <b style="color: #fff">Project Experience</b>
                                            </td>
                                        </tr>
                                        <%  List<Vector> proExprList = (ArrayList) request.getAttribute("proExprList");
                                            if(proExprList.isEmpty()) {%>
                                        <tr class="proExpr">
                                            <td valign="middle"><b><center>Project Title: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="projectTitle[]" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"></td>
                                        </tr>
                                        <tr class="proExpr">
                                            <td valign="middle"><b><center>Description: </center></b></td>
                                            <td valign="middle">
                                                <textarea type="text" class="form-control" name="projectDes[]" rows="2"></textarea>
                                            </td>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="proExprRemoveBtn"></i></td>
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="proExprBtn"></i></td>
                                        </tr>
                                        <% } else {
                                                for(int i=0;i<proExprList.size();i++) {
                                                    Vector v = proExprList.get(i);
                                                    String proTitle = String.valueOf(v.get(0));
                                                    String proDes = String.valueOf(v.get(1));%>
                                        <tr class="proExpr">
                                            <td valign="middle"><b><center>Project Title: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="projectTitle[]" value="<%= proTitle%>" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"></td>
                                        </tr>
                                        <tr class="proExpr">
                                            <td valign="middle"><b><center>Description: </center></b></td>
                                            <td valign="middle">
                                                <textarea type="text" class="form-control" name="projectDes[]" rows="2"><%= proDes%></textarea>
                                            </td>
                                            <% if(i==0) {%>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="proExprRemoveBtn"></i></td>
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="proExprBtn"></i></td>
                                            <% } else { %><td></td>
                                            <% } %>
                                        </tr>
                                        <%      }
                                           }%>
                                    </tbody>
                                </table>
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody id="awardTable">
                                        <tr bgcolor="#4D7496">
                                            <td colspan="4" valign="top">
                                                <b style="color: #fff">Achievements & Awards</b>
                                            </td>
                                        </tr>
                                        <% String[] award = userAwardStr.split(";&");%>
                                        <% if(award.length == 0) {%>
                                        <tr class="award">
                                            <td valign="middle"><b><center>Achievement: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="award[]" style="margin-right: 10px;"/>
                                            </td>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="awardRemoveBtn"></i></td>
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="awardBtn"></i></td>
                                        </tr>
                                        <% } else {
                                                for(int i=0;i<award.length;i++) {%>
                                        <tr class="award">
                                            <td valign="middle"><b><center>Achievement: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="award[]" value="<%= award[i]%>" style="margin-right: 10px;"/>
                                            </td>
                                            <% if(i==0) {%>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="awardRemoveBtn"></i></td>
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="awardBtn"></i></td>
                                            <% } else {%><td></td>
                                            <% } %>
                                        </tr>        
                                        <%      }
                                            }%>
                                    </tbody>
                                </table>
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody id="skillTable">
                                        <tr bgcolor="#4D7496">
                                            <td colspan="5" valign="top">
                                                <b style="color: #fff">Skill Set</b>
                                            </td>
                                        </tr>
                                        <tr class="skill">
                                            <th style="text-align:center">Professional</th>
                                            <th>(e.g. Java, etc)</th>
                                            <th style="text-align:center">Personal</th>
                                            <th>(e.g. Leadership, etc)</th>
                                            <th></th>
                                        </tr>
                                        <%  List<Vector> proSkillList = (ArrayList) request.getAttribute("proSkillList");
                                            List<Vector> perSkillList = (ArrayList) request.getAttribute("perSkillList");
                                            int count = Math.max(proSkillList.size(), perSkillList.size());
                                            if(count==0) {%>
                                        <tr class="skill">
                                            <td><input type="text" class="form-control" name="proSkillName[]" />
                                            </td>
                                            <td><select class="form-control" name="proSkillLevel[]" style="width: 100%">
                                                <option value="" default>-- Skill Level --</option>
                                                <option value="Beginner">Beginner</option>
                                                <option value="Intermediate">Intermediate</option>
                                                <option value="Advanced">Advanced</option>
                                                </select></td>
                                            <td><input type="text" class="form-control" name="perSkillName[]"/>
                                            </td>
                                            <td><select class="form-control" name="perSkillLevel[]" style="width: 100%">
                                                <option value="" default>-- Skill Level --</option>
                                                <option value="Beginner">Beginner</option>
                                                <option value="Intermediate">Intermediate</option>
                                                <option value="Advanced">Advanced</option>
                                                </select></td>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="skillRemoveBtn"></i></td>    
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="skillBtn"></i></td>
                                        </tr>
                                        <% } else { 
                                                for(int i=0;i<count;i++) {
                                                    String proSkillName, proSkillLevel, perSkillName, perSkillLevel;
                                                    proSkillName = proSkillLevel = perSkillName = perSkillLevel = "";
                                                    if(i<proSkillList.size()) {
                                                        Vector pro = proSkillList.get(i);
                                                        proSkillName = String.valueOf(pro.get(0));
                                                        proSkillLevel = String.valueOf(pro.get(1));
                                                    }
                                                    if(i<perSkillList.size()) {
                                                        Vector per = perSkillList.get(i);
                                                        perSkillName = String.valueOf(per.get(0));
                                                        perSkillLevel = String.valueOf(per.get(1));
                                                    }%>
                                        <tr class="skill">
                                            <td><input type="text" class="form-control" value="<%= proSkillName%>" name="proSkillName[]" />
                                            </td>
                                            <td><select class="form-control" name="proSkillLevel[]" style="width: 100%">
                                                <option value="" <%if((proSkillLevel.equals(""))){%> selected <%}%>>-- Skill Level --</option>
                                                <option value="Beginner" <%if((proSkillLevel.equals("Beginner"))){%> selected <%}%>>Beginner</option>
                                                <option value="Intermediate" <%if((proSkillLevel.equals("Intermediate"))){%> selected <%}%>>Intermediate</option>
                                                <option value="Advanced" <%if((proSkillLevel.equals("Advanced"))){%> selected <%}%>>Advanced</option>
                                                </select></td>
                                            <td><input type="text" class="form-control" value="<%= perSkillName%>" name="perSkillName[]"/>
                                            </td>
                                            <td><select class="form-control" name="perSkillLevel[]" style="width: 100%">
                                                <option value="" <%if((perSkillLevel.equals(""))){%> selected <%}%>>-- Skill Level --</option>
                                                <option value="Beginner" <%if((perSkillLevel.equals("Beginner"))){%> selected <%}%>>Beginner</option>
                                                <option value="Intermediate" <%if((perSkillLevel.equals("Intermediate"))){%> selected <%}%>>Intermediate</option>
                                                <option value="Advanced" <%if((perSkillLevel.equals("Advanced"))){%> selected <%}%>>Advanced</option>
                                                </select></td>
                                            <% if(i==0) {%>
                                            <td style="float: right"><i class="fa fa-minus-circle fa-2x" id="skillRemoveBtn"></i></td>    
                                            <td style="float: right"><i class="fa fa-plus-circle fa-2x" id="skillBtn"></i></td>
                                            <% } else {%><td></td>
                                            <% } %>
                                        </tr>
                                        <%      } 
                                           }%>
                                    </tbody>
                                </table>
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody id="workExprTable">
                                        <tr bgcolor="#4D7496">
                                            <td colspan="4" valign="top">
                                                <b style="color: #fff">Working Experience</b>
                                            </td>
                                        </tr>
                                        <%
                                            List<Vector> workExprList = (ArrayList) request.getAttribute("workExprList");
                                            if(workExprList.isEmpty()) {
                                        %>
                                        <tr class="workExpr">
                                            <td valign="middle"><b><center>Title: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="workTitle[]" style="margin-right: 10px; width: 100%" />
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr class="workExpr">
                                            <td valign="middle"><b><center>Company: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="workCompany[]" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"><b><center>Period: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="workPeriod[]" style="margin-right: 10px; width: 50%"/>
                                            </td>
                                        </tr>
                                        <tr class="workExpr">
                                            <td valign="middle"><b><center>Description: </center></b></td>
                                            <td valign="middle">
                                                <textarea type="text" class="form-control" name="workDes[]" rows="2" style="width: 180%"></textarea>
                                            </td>
                                            <td></td>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-minus-circle fa-2x" id="workExprRemoveBtn"></i></td>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-plus-circle fa-2x" id="workExprBtn"></i></td>
                                        </tr>
                                        <% } else {
                                                for(int i=0;i<workExprList.size();i++) {
                                                    Vector v = workExprList.get(i);
                                                    String workCompany = String.valueOf(v.get(0));
                                                    String workTitle = String.valueOf(v.get(1));
                                                    String workPeriod = String.valueOf(v.get(2));
                                                    String workDes = String.valueOf(v.get(3));%>
                                        <tr class="workExpr">
                                            <td valign="middle"><b><center>Title: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="workTitle[]" value="<%= workTitle%>" style="margin-right: 10px; width: 100%" />
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr class="workExpr">
                                            <td valign="middle"><b><center>Company: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="workCompany[]" value="<%= workCompany%>" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"><b><center>Period: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="workPeriod[]" value="<%= workPeriod%>" style="margin-right: 10px; width: 50%"/>
                                            </td>
                                        </tr>
                                        <tr class="workExpr">
                                            <td valign="middle"><b><center>Description: </center></b></td>
                                            <td valign="middle">
                                                <textarea type="text" class="form-control" name="workDes[]" rows="2" style="width: 180%"><%= workDes%></textarea>
                                            </td>
                                            <td></td>
                                            <% if(i==0) {%>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-minus-circle fa-2x" id="workExprRemoveBtn"></i></td>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-plus-circle fa-2x" id="workExprBtn"></i></td>
                                            <% } else {%><td></td>
                                            <% }%>
                                        </tr>
                                        <%      }
                                            }%>
                                    </tbody>
                                </table>
                                <table border="0" cellspacing="2" cellpadding="3" width="100%">
                                    <tbody id="referenceTable">
                                        <tr bgcolor="#4D7496">
                                            <td colspan="4" valign="top">
                                                <b style="color: #fff">References</b>
                                            </td>
                                        </tr>
                                        <% List<Vector> referenceList = (ArrayList) request.getAttribute("referenceList");
                                            if(referenceList.isEmpty()) {%>
                                        <tr class="reference">
                                            <td valign="middle"><b><center>Referee Name: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="refName[]" style="margin-right: 10px; width: 100%"/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr class="reference">
                                            <td valign="middle"><b><center>Company: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="refCompany[]" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"><b><center>Position: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="refPosition[]" style="margin-right: 10px; width: 50%"/>
                                            </td>
                                        </tr>
                                        <tr class="reference">
                                            <td valign="middle"><b><center>Reference: </center></b></td>
                                            <td valign="middle">
                                                <textarea type="text" class="form-control" name="refDes[]" rows="2" style="width: 190%"></textarea>
                                            </td>
                                            <td></td>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-minus-circle fa-2x" id="referenceRemoveBtn"></i></td>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-plus-circle fa-2x" id="referenceBtn"></i></td>
                                        </tr>
                                        <% } else {
                                                for(int i=0;i<referenceList.size();i++) {
                                                    Vector v = referenceList.get(i);
                                                    String refName = String.valueOf(v.get(0));
                                                    String refCompany = String.valueOf(v.get(1));
                                                    String refPosition = String.valueOf(v.get(2));
                                                    String refContent = String.valueOf(v.get(3));%>
                                        <tr class="reference">
                                            <td valign="middle"><b><center>Referee Name: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="refName[]" value="<%= refName%>" style="margin-right: 10px; width: 100%"/>
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr class="reference">
                                            <td valign="middle"><b><center>Company: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="refCompany[]" value="<%= refCompany%>" style="margin-right: 10px; width: 80%"/>
                                            </td>
                                            <td valign="middle"><b><center>Position: </center></b></td>
                                            <td valign="middle">
                                                <input type="text" class="form-control" name="refPosition[]" value="<%= refPosition%>" style="margin-right: 10px; width: 50%"/>
                                            </td>
                                        </tr>
                                        <tr class="reference">
                                            <td valign="middle"><b><center>Reference: </center></b></td>
                                            <td valign="middle">
                                                <textarea type="text" class="form-control" name="refDes[]" rows="2" style="width: 190%"><%= refContent%></textarea>
                                            </td>
                                            <td></td>
                                            <% if(i==0) {%>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-minus-circle fa-2x" id="referenceRemoveBtn"></i></td>
                                            <td style="float: right; margin-top: 10px"><i class="fa fa-plus-circle fa-2x" id="referenceBtn"></i></td>
                                            <% } else {%><td></td>
                                            <% } %>
                                        </tr>
                                        <%      } 
                                           }%>
                                    </tbody>
                                </table>
                                <br/>
                                <div class="form-row" style="justify-content: center;">
                                    <input type="hidden" name="pageTransit" id="pageTransit" value="updateResumeSYS" />
                                    <input type="hidden" name="hiddenResumeID" id="hiddenResumeID" value="<%= request.getAttribute("hiddenResumeID")%>" />
                                    <input type="hidden" name="fullContactNum" id="fullContactNum" value="" />
                                    <input type="hidden" name="imageUploadStatus" id="imageUploadStatus" value="" />
                                    <input type="hidden" name="eduExprList" id="eduExprList" value="" />
                                    <input type="hidden" name="proExprList" id="proExprList" value="" />
                                    <input type="hidden" name="awardStr" id="awardStr" value="" />
                                    <input type="hidden" name="skillList" id="skillList" value="" />
                                    <input type="hidden" name="workExprList" id="workExprList" value="" />
                                    <input type="hidden" name="referenceList" id="referenceList" value="" />
                                    <button class="btn btn-theme btn-search" type="submit" onclick="createList()">Save</button>
                                </div>
                            </form>
                        </div>
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
                                            
        <!-- #1. jQuery v2.2.4 -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/validator-v1.1.0.js" type="text/javascript"></script>

        <script src="js/unify/systemuser/webjs/marketplace/jquery.smartWizard-v3.3.1.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/marketplace/custom.min.js"></script>
        <script src="js/unify/systemuser/basejs/leaflet/leaflet.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/voices/EditResumeDetailsSYSJS.js" type="text/javascript"></script>                                    
    </body>
</html>
