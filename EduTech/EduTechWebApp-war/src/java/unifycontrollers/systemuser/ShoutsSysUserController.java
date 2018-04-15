package unifycontrollers.systemuser;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unifysessionbeans.systemuser.ShoutsSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.UserProfileSysUserMgrBeanRemote;

public class ShoutsSysUserController extends HttpServlet {

    @EJB
    private ShoutsSysUserMgrBeanRemote ssmr;
    @EJB
    private UserProfileSysUserMgrBeanRemote usmr;
    
    String responseMessage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            System.out.println(pageAction);
            
            String loggedInUsername = getCookieUsername(request);

            switch (pageAction) {
                case "goToViewShoutsListingSYS":
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList2(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewShoutsListingSYS";
                    break;
                case "goToViewMyShoutsListingSYS":
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewMyShoutList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewMyShoutsListingSYS";
                    break;
                case "goToViewMyBookmarkedShoutsListingSYS":
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewMyBookmarkedShoutList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewMyBookmarkedShoutsListingSYS";
                    break;
                case "goToSetBookmarkSYS":
                    System.out.println("At (ShoutsSysUserController.goToSetBookmarkSYS");

                    String bookmarkShoutUser = request.getParameter("loggedInUsername");
                    String bookmarkShoutID = request.getParameter("hiddenShoutID");

                    responseMessage = setBookmark(bookmarkShoutUser, bookmarkShoutID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    pageAction = "ViewShoutBookmarkModalSYS";
                    break;
                case "goToRemoveBookmarkSYS":
                    System.out.println("At (ShoutsSysUserController.goToRemoveBookmarkSYS");

                    String unbookmarkShoutUser = request.getParameter("loggedInUsername");
                    String unbookmarkShoutID = request.getParameter("hiddenShoutID");

                    responseMessage = removeBookmark(unbookmarkShoutUser, unbookmarkShoutID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    pageAction = "ViewShoutBookmarkModalSYS";
                    break;
                case "goToSetLikeSYS":
                    System.out.println("At (ShoutsSysUserController.goToSetLikeSYS");

                    String likeShoutUser = request.getParameter("loggedInUsername");
                    String likeShoutID = request.getParameter("hiddenShoutID");

                    responseMessage = setLike(likeShoutUser, likeShoutID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    pageAction = "ViewShoutBookmarkModalSYS";
                    break;
                case "goToRemoveLikeSYS":
                    System.out.println("At (ShoutsSysUserController.goToRemoveLikeSYS");

                    String unlikeShoutUser = request.getParameter("loggedInUsername");
                    String unlikeShoutID = request.getParameter("hiddenShoutID");

                    responseMessage = removeLike(unlikeShoutUser, unlikeShoutID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    pageAction = "ViewShoutBookmarkModalSYS";
                    break;
                case "goToNewShoutSYS":
                    pageAction = "NewShoutSYS";
                    break;
                case "goToNewShoutModalSYS":
                    pageAction = "NewShoutModalSYS";
                    break;
                case "goToCreateShout":
                    System.out.println("At (ShoutsSysUserController.createShoutSYS");
                    responseMessage = createShout(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList2(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewShoutsListingSYS";
                    //pageAction = "NewShoutModalSYS";
                    break;
                case "goToDeleteShoutSYS":
                    System.out.println("At (ShoutsSysUserController.goToDeleteShoutSYS");
                    responseMessage = deleteShout(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    //String usernameAfterShoutDelete = request.getParameter("loggedInUsername");
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList2(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewShoutsListingSYS";
                    break;
                case "goToReportShoutSubmit":
                    System.out.println("At (ShoutsSysUserController.goToReportShoutSubmit");
                    responseMessage = createShoutReport(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList2(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewShoutsListingSYS";
                    //pageAction = "ReportShoutModalSYS";
                    break;
                case "goToReportShoutSYS":
                    String reportShoutID = request.getParameter("hiddenShoutID");
                    request.setAttribute("reportShoutID", reportShoutID);
                    pageAction = "ReportShoutSYS";
                    break;
                case "goToReportShoutCommentSubmit":
                    System.out.println("At (ShoutsSysUserController.goToReportShoutCommentSubmit");
                    responseMessage = createShoutCommentReport(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    String shoutCommentIDAfterReport = request.getParameter("shoutID");
                    request.setAttribute("commentShoutIDModal", shoutCommentIDAfterReport);
                    request.setAttribute("commentShoutContentModal", ssmr.viewShoutContent(shoutCommentIDAfterReport));
                    request.setAttribute("commentListSYS", (ArrayList) ssmr.viewCommentList(shoutCommentIDAfterReport));
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList(shoutCommentIDAfterReport));
                    pageAction = "ViewShoutCommentModalSYS";
                    break;
                case "goToReportShoutCommentSYS":
                    String reportShoutCommentID = request.getParameter("hiddenCommentID");
                    String reportShoutCommentContent = request.getParameter("hiddenCommentContent");
                    String reportShoutCommentShoutID = request.getParameter("hiddenShoutID");
                    request.setAttribute("reportShoutCommentID", reportShoutCommentID);
                    request.setAttribute("reportShoutCommentContent", reportShoutCommentContent);
                    request.setAttribute("reportShoutCommentShoutID", reportShoutCommentShoutID);
                    pageAction = "ReportShoutCommentModalSYS";
                    break;
                case "goToReportShoutModalSYS":
                    String reportShoutIDModal = request.getParameter("hiddenShoutID");
                    String reportShoutContent = request.getParameter("hiddentShoutContent");
                    request.setAttribute("reportShoutID", reportShoutIDModal);
                    request.setAttribute("reportShoutContent", reportShoutContent);
                    pageAction = "ReportShoutModalSYS";
                    break;
                case "goToViewShoutCommentSYS":
                    String commentShoutID = request.getParameter("hiddenShoutID");
                    request.setAttribute("commentListSYS", (ArrayList) ssmr.viewCommentList(commentShoutID));
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList(commentShoutID));
                    pageAction = "ViewShoutCommentSYS";
                    break;
                case "goToViewShoutCommentModalSYS":
                    String commentShoutIDModal = request.getParameter("hiddenShoutID");
                    String commentShoutContentModal = request.getParameter("hiddentShoutContent");
                    System.out.println(commentShoutIDModal);
                    System.out.println(commentShoutContentModal);
                    request.setAttribute("commentShoutIDModal", commentShoutIDModal);
                    //request.setAttribute("commentShoutContentModal", commentShoutContentModal);
                    request.setAttribute("commentListSYS", (ArrayList) ssmr.viewCommentList(commentShoutIDModal));
                    request.setAttribute("commentShoutContentModal", ssmr.viewShoutContent(commentShoutIDModal));
                    //request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList(commentShoutIDModal));
                    pageAction = "ViewShoutCommentModalSYS";
                    break;
                case "goToDeleteShoutCommentSYS":
                    System.out.println("At (ShoutsSysUserController.goToDeleteShoutCommentSYS");
                    responseMessage = deleteShoutComment(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    String shoutIDAfterDelete = request.getParameter("hiddenShoutID");
                    request.setAttribute("commentShoutIDModal", shoutIDAfterDelete);
                    request.setAttribute("commentListSYS", (ArrayList) ssmr.viewCommentList(shoutIDAfterDelete));
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList(shoutIDAfterDelete));
                    request.setAttribute("commentShoutContentModal", ssmr.viewShoutContent(shoutIDAfterDelete));
                    pageAction = "ViewShoutCommentModalSYS";
                    break;
                case "goToNewShoutCommentSYS":
                    String newShoutCommentShoutContent = request.getParameter("hiddenShoutContent");
                    String newShoutCommentShoutID = request.getParameter("hiddenShoutID");
                    System.out.println(newShoutCommentShoutContent);
                    request.setAttribute("newShoutCommentShoutContent", newShoutCommentShoutContent);
                    request.setAttribute("newShoutCommentShoutID", newShoutCommentShoutID);
                    pageAction = "NewShoutCommentModalSYS";
                    break;
                case "goToNewShoutCommentSubmit":
                    System.out.println("At (ShoutsSysUserController.goToNewShoutCommentSubmit");
                    responseMessage = createShoutComment(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    String shoutIDAfterCreate = request.getParameter("shoutID");
                    String shoutContentAfterCreate = request.getParameter("shoutContent");
                    System.out.println(shoutContentAfterCreate);
                    request.setAttribute("commentShoutIDModal", shoutIDAfterCreate);
                    //request.setAttribute("commentShoutContentModal", shoutContentAfterCreate);
                    request.setAttribute("commentShoutContentModal", ssmr.viewShoutContent(shoutIDAfterCreate));
                    request.setAttribute("commentListSYS", (ArrayList) ssmr.viewCommentList(shoutIDAfterCreate));
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList(shoutIDAfterCreate));
                    pageAction = "ViewShoutCommentModalSYS";
                    break;
                case "goToEditShoutCommentSYS":
                    String editShoutCommentID = request.getParameter("hiddenCommentID");
                    String editShoutCommentShoutID = request.getParameter("hiddenShoutID");
                    String editShoutCommentContent = request.getParameter("hiddenCommentContent");
                    String editShoutCommentShoutContent = request.getParameter("hiddenShoutContent");
                    System.out.println("editShoutCommentContent");
                    request.setAttribute("editShoutCommentID", editShoutCommentID);
                    request.setAttribute("editShoutCommentShoutID", editShoutCommentShoutID);
                    request.setAttribute("editShoutCommentContent", editShoutCommentContent);
                    request.setAttribute("editShoutCommentShoutContent", editShoutCommentShoutContent);
                    System.out.println(editShoutCommentShoutContent);
                    pageAction = "EditShoutCommentModalSYS";
                    break;
                case "goToEditShoutCommentSubmit":
                    System.out.println("At (ShoutsSysUserController.goToEditShoutCommentSubmit");
                    responseMessage = editShoutComment(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    String shoutIDAfterEdit = request.getParameter("shoutID");
                    String shoutContentAfterEdit = request.getParameter("shoutContent");
                    System.out.println(shoutContentAfterEdit);
                    //request.setAttribute("commentShoutContentModal", shoutContentAfterEdit);
                    request.setAttribute("commentShoutContentModal", ssmr.viewShoutContent(shoutIDAfterEdit));
                    request.setAttribute("commentShoutIDModal", shoutIDAfterEdit);
                    request.setAttribute("commentListSYS", (ArrayList) ssmr.viewCommentList(shoutIDAfterEdit));
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList(shoutIDAfterEdit));
                    pageAction = "ViewShoutCommentModalSYS";
                    break;

                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            log("Exception in ShoutsSysUserController: processRequest()");
            ex.printStackTrace();
        }

    }

    private String createShout(HttpServletRequest request) {
        String shoutContent = request.getParameter("shoutContent");
        String shoutPoster = request.getParameter("loggedInUsername");

        System.out.println(shoutContent);
        System.out.println(shoutPoster);

        System.out.println("Shout created (ShoutsSysUserController.createShout)");

        //responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
        //reviewPros, reviewCons, reviewRating, reviewDescription, employmentStatus, reviewPoster);
        responseMessage = ssmr.createShout(shoutContent, shoutPoster);

        return responseMessage;
    }
    
    private String deleteShout(HttpServletRequest request) {

        String shoutID = request.getParameter("hiddenShoutID");
        //String shoutUser = request.getParameter("hiddenUsername");


        System.out.println("Shout deleted (ShoutsSysUserController.deleteShout)");

        responseMessage = ssmr.deleteShout(shoutID);

        return responseMessage;
    }

    private String createShoutReport(HttpServletRequest request) {
        String shoutReportContent = request.getParameter("shoutReportContent");
        String shoutPoster = request.getParameter("loggedInUsername");
        String shoutID = request.getParameter("shoutID");

        System.out.println(shoutReportContent);
        System.out.println(shoutPoster);
        System.out.println(shoutID);

        System.out.println("Shout report created (ShoutsSysUserController.createShoutReport)");

        //responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
        //reviewPros, reviewCons, reviewRating, reviewDescription, employmentStatus, reviewPoster);
        responseMessage = ssmr.createShoutReport(shoutReportContent, shoutPoster, shoutID);

        return responseMessage;
    }

    private String createShoutCommentReport(HttpServletRequest request) {
        String shoutReportContent = request.getParameter("shoutReportContent");
        String shoutPoster = request.getParameter("loggedInUsername");
        String shoutID = request.getParameter("shoutID");
        String shoutCommentID = request.getParameter("shoutCommentID");

        System.out.println(shoutReportContent);
        System.out.println(shoutPoster);
        System.out.println(shoutID);
        System.out.println(shoutCommentID);

        System.out.println("Shout report created (ShoutsSysUserController.createShoutCommentReport)");

        //responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
        //reviewPros, reviewCons, reviewRating, reviewDescription, employmentStatus, reviewPoster);
        responseMessage = ssmr.createShoutCommentReport(shoutReportContent, shoutPoster, shoutCommentID);

        return responseMessage;
    }
    
    private String createShoutComment(HttpServletRequest request) {
        String shoutCommentContent = request.getParameter("shoutCommentContent");
        String shoutPoster = request.getParameter("loggedInUsername");
        String shoutID = request.getParameter("shoutID");

        System.out.println(shoutCommentContent);
        System.out.println(shoutPoster);
        System.out.println(shoutID);

        System.out.println("Shout report created (ShoutsSysUserController.createShoutComment)");

        //responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
        //reviewPros, reviewCons, reviewRating, reviewDescription, employmentStatus, reviewPoster);
        responseMessage = ssmr.createShoutComment(shoutCommentContent, shoutPoster, shoutID);

        return responseMessage;
    }
    
    private String editShoutComment(HttpServletRequest request) {
        String shoutCommentContent = request.getParameter("shoutCommentContent");
        String shoutPoster = request.getParameter("loggedInUsername");
        String commentID = request.getParameter("commentID");

        System.out.println(shoutCommentContent);
        System.out.println(shoutPoster);
        System.out.println(commentID);

        System.out.println("Shout report created (ShoutsSysUserController.editShoutComment)");

        //responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
        //reviewPros, reviewCons, reviewRating, reviewDescription, employmentStatus, reviewPoster);
        responseMessage = ssmr.editShoutComment(shoutCommentContent, shoutPoster, commentID);

        return responseMessage;
    }

    private String deleteShoutComment(HttpServletRequest request) {

        String shoutCommentID = request.getParameter("hiddenCommentID");

        System.out.println(shoutCommentID);

        System.out.println("Shout comment deleted (ShoutsSysUserController.deleteShoutCommentReport)");

        //responseMessage = vsmr.createCompanyReview(companyIndustry, companyName, reviewTitle, 
        //reviewPros, reviewCons, reviewRating, reviewDescription, employmentStatus, reviewPoster);
        responseMessage = ssmr.deleteShoutComment(shoutCommentID);

        return responseMessage;
    }

    private String setBookmark(String user, String shoutID) {

        //System.out.println(user);
        //System.out.println(shoutID);

        System.out.println("Shout bookmarked (ShoutsSysUserController.setBookmark)");

        responseMessage = ssmr.bookmarkShout(user, shoutID);

        return responseMessage;
    }
    
    private String removeBookmark(String user, String shoutID) {

        //System.out.println(user);
        //System.out.println(shoutID);

        System.out.println("Shout bookmark removed (ShoutsSysUserController.removeBookmark)");

        responseMessage = ssmr.unbookmarkShout(user, shoutID);

        return responseMessage;
    }
    
    private String setLike(String user, String shoutID) {

        //System.out.println(user);
        //System.out.println(shoutID);

        System.out.println("Shout liked (ShoutsSysUserController.setLike)");

        responseMessage = ssmr.likeShout(user, shoutID);

        return responseMessage;
    }

    private String removeLike(String user, String shoutID) {

        //System.out.println(user);
        //System.out.println(shoutID);

        System.out.println("Shout unliked (ShoutsSysUserController.removeLike)");

        responseMessage = ssmr.unlikeShout(user, shoutID);

        return responseMessage;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private String getCookieUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String loggedInUsername = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username") && !c.getValue().equals("")) {
                    loggedInUsername = c.getValue();
                }
            }
        }
        return loggedInUsername;
    }
    
    @Override
    public String getServletInfo() {
        return "Shouts System User Servlet";
    }
}
