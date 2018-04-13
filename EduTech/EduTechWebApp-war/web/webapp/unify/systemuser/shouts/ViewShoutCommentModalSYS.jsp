<%@page import="java.util.Date"%>
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
        <title>Unify Shouts - Shout Comments</title>

        <!-- CASCADING STYLESHEET -->
        <link href="css/unify/systemuser/baselayout/bootstrap-v4.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/animate-v3.5.2.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/font-awesome-v4.7.0.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.carousel-v2.2.1.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/owl.theme.default.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/nouislider-v11.0.3.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/style.min.css" rel="stylesheet" type="text/css">

        <link href="css/unify/systemuser/baselayout/jplist/jquery-ui.css" rel="stylesheet" type="text/css">
        <link href="css/unify/systemuser/baselayout/jplist/jplist.core.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.filter-toggle-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.pagination-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.history-bundle.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.textbox-filter.min.css" rel="stylesheet" type="text/css" />
        <link href="css/unify/systemuser/baselayout/jplist/jplist.jquery-ui-bundle.min.css" rel="stylesheet" type="text/css" />

    </head>
    <body>


        <div id="container">


            <div id="contentArea" class="container jplist" style="margin-bottom: 30px;">
                <%                    String successMessage = (String) request.getAttribute("successMessage");
                    if (successMessage != null) {
                %>
                <div class="alert alert-success" id="successPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeSuccess">&times;</button>
                    <%= successMessage%>
                </div>
                <%  }   %>
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <div class="alert alert-danger" id="errorPanel" style="margin: 10px 0 30px 0;">
                    <button type="button" class="close" id="closeError">&times;</button>
                    <%= errorMessage%>
                </div>
                <%  }   %>

                <%
                    String commentShoutIDModal = (String) request.getAttribute("commentShoutIDModal");
                    String commentShoutContentModal = (String) request.getAttribute("commentShoutContentModal");
                %>



                <div class="row">

                    <div class="col-lg-3 col-md-4 mb-3 jplist-search" hidden="true">

                        <div class="collapse d-md-block pt-3 pt-md-0" id="collapseFilter">
                            <div class="row">
                                <div class="col-12 col-sm-6 col-md-12">

                                    <%-- search by shout content --%>
                                    <div class="filter-sidebar" hidden="true">
                                        <div class="title"><span>Search Filter</span></div>
                                        <div class="subtitle" style="margin-bottom: 5px">Shout Content</div>
                                        <input type="text" data-path=".commentContent" class="form-control" placeholder="Search Comment" 
                                               aria-label="Search Comment" data-control-type="textbox" id="name-filter"
                                               data-control-name="content-text-filter" data-control-action="filter" />
                                    </div>

                                    <%-- search by shout user *removed - to keep anonymity
                                    <div class="filter-sidebar">
                                        <div class="subtitle" style="margin-bottom: 5px">Shout User</div>
                                        <input type="text" data-path=".shoutUsername" class="form-control" placeholder="Search User" 
                                               aria-label="Search User" data-control-type="textbox" id="user-filter"
                                               data-control-name="user-text-filter" data-control-action="filter" />
                                    </div>
                                    --%>

                                    <%-- additional filters --%>
                                </div>
                            </div>
                        </div>

                    </div>



                    <div class="col-lg-9 col-md-8">
                        <br>
                        <div class="card card-product">
                            <div class="card-content">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-xl-12 col-md-12 col-12">
                                            <div class="shout-main-content">
                                                <span class="card-content shoutContent" style="line-height: 2.0; color: #2b3233; font-size: 18px;"><center><i><%= commentShoutContentModal%></i></center></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <br>
                        <left><a href="ShoutsSysUser?pageTransit=goToNewShoutCommentSYS&hiddenShoutID=<%=commentShoutIDModal%>&hiddenShoutContent=<%=commentShoutContentModal%>"><button type="button" class="btn btn-outline-info center"  ><i class="fa fa-plus"></i>&nbsp;&nbsp;New Comment</button></a><br></left>

                        <br>
                        <div class="jplist-search sorting-bar" hidden="true">

                            <div class="dateSort jplist-drop-down" remove-class-on-xs="mr-3" add-class-on-xs="w-100" 
                                 data-control-type="sort-drop-down" data-control-name="sort" data-control-action="sort"
                                 data-datetime-format="{year}-{month}-{day} {hour}:{min}:{sec}">
                                <ul>
                                    <li><span data-path=".commentDuration" data-order="desc" data-type="datetime" data-default="true">Newest First</span></li>
                                    <li><span data-path=".commentDuration" data-order="asc" data-type="datetime">Oldest First</span></li>
                                </ul>
                            </div>
                            <div class="jplist-drop-down" add-class-on-xs="w-100" data-control-type="items-per-page-drop-down" 
                                 data-control-name="paging" data-control-action="paging" data-control-animate-to-top="true">
                                <ul>
                                    <li><span data-number="4">4 per page</span></li>
                                    <li><span data-number="8">8 per page</span></li>
                                    <li><span data-number="12" data-default="true">12 per page</span></li>
                                    <li><span data-number="16">16 per page</span></li>
                                </ul>
                            </div>
                            <div class="jplist-panel">
                                <button type="button" class="jplist-reset-btn" data-control-type="reset" 
                                        data-control-name="reset" data-control-action="reset"><i class="fa fa-retweet">&nbsp;&nbsp;Reset</i>
                                </button>
                            </div>
                        </div>

                        <!-- SHOUTS LISTING -->
                        <div id="commentsListing" class="row equal-height" add-class-on-xs="no-gutters">
                            <div class="list searchresult-row">
                                <%
                                    ArrayList<Vector> commentListSYS = (ArrayList) request.getAttribute("commentListSYS");
                                    if (!commentListSYS.isEmpty()) {
                                        for (int i = 0; i <= commentListSYS.size() - 1; i++) {
                                            Vector v = commentListSYS.get(i);
                                            String commentID = String.valueOf(v.get(0));
                                            String commentDate = String.valueOf(v.get(1));
                                            String commentContent = String.valueOf(v.get(2));
                                            String commentUsername = String.valueOf(v.get(3));
                                            String commentDuration = String.valueOf(v.get(4));
                                %>

                                <div class="col-xl-12 col-md-12 col-12 d-block d-lg-none d-xl-block list-item">
                                    <div class="card card-product">
                                        <div class="card-content">
                                            <div class="card-body">
                                                <div class="row">

                                                    <div class="col-xl-12 col-md-12 col-12">
                                                        <%-- removed
                                                        <div class="shout-ID">
                                                            <span class="card-header commentCategory" style="color: #007bff; font-size: 10px; line-height: 2.5;">Categories, Type</span>
                                                        </div>
                                                        
                                                        <div class="shout-ID">
                                                            <span class="card-title commentID" style="color: #2b3233; font-size: 25px; line-height: 2.5;"><strong>#<%= commentID%></strong></span>
                                                        </div>
                                                        --%>
                                                        <div class="shout-content">
                                                            <span class="card-content commentContent" style="line-height: 2.0; color: #2b3233; font-size: 18px;"><%= commentContent%></span>
                                                        </div>
                                                        <br>

                                                    </div>
                                                    <div class="col-xl-12 col-md-12 col-12">
                                                        <div class="new-review">
                                                            <form action="ShoutsSysUser" method="POST">
                                                                <%

                                                                    if (commentUsername.equals(request.getAttribute("loggedInUsername"))) {
                                                                %>
                                                                <div class="shout-edit">
                                                                    <a href = "ShoutsSysUser?pageTransit=goToEditShoutCommentSYS&hiddenCommentID=<%=commentID%>&hiddenShoutID=<%=commentShoutIDModal%>&hiddenCommentContent=<%=commentContent%>&hiddenShoutContent=<%=commentShoutContentModal%>" ><i class="fa fa-edit">&nbsp;</i><span class="float-none commentDelete" style="color: #64676d; font-size: 12px">Edit Comment</a>
                                                                </div>
                                                                <div class="shout-delete">
                                                                    <a href = "ShoutsSysUser?pageTransit=goToDeleteShoutCommentSYS&hiddenCommentID=<%=commentID%>&hiddenShoutID=<%=commentShoutIDModal%>&hiddenShoutContent=<%=commentShoutContentModal%>" onclick="return confirm('Confirm delete?')" ><i class="fa fa-trash">&nbsp;</i><span class="float-none commentDelete" style="color: #64676d; font-size: 12px">Delete Comment</a>
                                                                </div>
                                                                <%  }%>
                                                            </form>
                                                        </div>

                                                    </div> 



                                                </div>
                                            </div>
                                        </div>

                                        <%-- card footer --%>
                                        <div class="card-footer text-muted mt-1">
                                            <%-- keep user anonymous 
                                            <div class="shout-user-info">
                                                <i class="fa fa-users" style="color: #64676d">&nbsp;</i><span class="card-text shoutUsername" style="font-size: 12px"><%= shoutUsername%></span>
                                            </div>
                                            --%>
                                            <div class="shout-post-date">
                                                <i class="fa fa-clock-o">&nbsp;</i><span class="float-none commentDuration" style="color: #64676d; font-size: 12px">Posted <%=commentDuration%>
                                                    <%--    on <%= shoutDate%> --%>
                                                </span>

                                                <span class = "float-right"><a href ="ShoutsSysUser?pageTransit=goToReportShoutCommentSYS&hiddenCommentID=<%=commentID%>&hiddenShoutID=<%=commentShoutIDModal%>&hiddenCommentContent=<%=commentContent%>"><i class="fa fa-flag">&nbsp;&nbsp;</i><span class="report" style="color: #64676d; font-size: 12px">Report Comment</span></a></span>

                                            </div>

                                        </div>

                                    </div>
                                </div>
                                <%      }   %>
                                <%  }%>
                            </div>
                        </div>
                        <%-- end of card contents --%>
                        <div class="box jplist-no-results text-shadow align-center">
                            <center><p><strong>Be the first to comment on this post!</strong></p></center>
                        </div>
                        <div class="jplist-search align-center">
                            <div class="jplist-label" data-type="Displaying {end} of all {all} results" 
                                 data-control-type="pagination-info" data-control-name="paging" data-control-action="paging">
                            </div>

                            <div class="jplist-label" data-type="Page {current} of {pages}" 
                                 data-control-type="pagination-info" data-control-name="paging" data-control-action="paging">
                            </div>

                            <div class="jplist-pagination" data-control-animate-to-top="true" 
                                 data-control-type="pagination" data-control-name="paging" data-control-action="paging">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- <div id="unifyFooter"></div> -->
            <a href="#top" class="back-top text-center" onclick="$('body,html').animate({scrollTop: 0}, 500); return false">
                <i class="fa fa-angle-double-up"></i>
            </a>
            <div id="itemcard-iframe"></div>
        </div>



        <!-- #1. jQuery -> #2. Popper.js -> #3. Bootstrap JS -> #4. Other Plugins -->
        <script src="js/unify/systemuser/basejs/jquery-v3.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/popper.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap-v4.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/bootstrap3-typeahead.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/owl.carousel-v2.2.1.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/nouislider-v11.0.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/style.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/webjs/shouts/ViewShoutsListingSYSJS.js" type="text/javascript"></script>

        <script src="js/unify/systemuser/basejs/jplist/jquery-ui.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.core.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.filter-dropdown-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.filter-toggle-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.history-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.jquery-ui-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.pagination-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.sort-bundle.min.js"></script>
        <script src="js/unify/systemuser/basejs/jplist/jplist.textbox-filter.min.js"></script>
    </body>
</html>