/***************************************************************************************
*   Title:                  UserProfileSysUserController.java
*   Purpose:                SERVLET FOR UNIFY DASHBOARD & PROFILE - SYSUSER (EDUBOX)
*   Created & Modified By:  TAN CHIN WEE WINSTON
*   Credits:                CHEN MENG, NIGEL LEE TJON YI, TAN CHIN WEE WINSTON, ZHU XINYI
*   Date:                   19 FEBRUARY 2018
*   Code version:           1.0
*   Availability:           === NO REPLICATE ALLOWED. YOU HAVE BEEN WARNED. ===
***************************************************************************************/

package unifycontrollers.systemuser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import unifysessionbeans.systemuser.MarketplaceSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.UserProfileSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.VoicesSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.ErrandsSysUserMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class UserProfileSysUserController extends HttpServlet {
    @EJB
    private MarketplaceSysUserMgrBeanRemote msmr;
    @EJB
    private UserProfileSysUserMgrBeanRemote usmr;
    @EJB
    private ErrandsSysUserMgrBeanRemote esmr;
    @EJB
    private VoicesSysUserMgrBeanRemote vsmr;
    String responseMessage = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String pageAction = request.getParameter("pageTransit");
            String loggedInUsername = getCookieUsername(request);
            System.out.println(pageAction);
            
            switch (pageAction) {
                case "goToUnifyBot":
                    pageAction = "UnifyBot";
                    break;
                case "goToViewShoutModalSYS":
                    pageAction = "ViewShoutModalSYS";
                    break;
                case "goToViewEventModalSYS":
                    pageAction = "ViewEventModalSYS";
                    break;
                case "goToUserProfileSYS":
                    String itemSellerID = request.getParameter("itemSellerID");
                    request.setAttribute("userItemListSYS", usmr.viewUserItemList(loggedInUsername, itemSellerID));
                    request.setAttribute("itemCategoryStr", msmr.populateItemCategory());
                    
                    request.setAttribute("userProfileVec", usmr.viewUserProfileDetails(itemSellerID));
                    request.setAttribute("userJobListing", esmr.viewUserJobList(itemSellerID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserProfileSYS";
                    break;
                case "goToUnifyUserAccountSYS":
                    request.setAttribute("userItemAccountListSYS", (ArrayList) usmr.viewUserItemAccountList(loggedInUsername));
                    request.setAttribute("itemCategoryStr", msmr.populateItemCategory());
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserAccountSYS";
                    break;
                case "goToMarketplaceTransSYS":
                    request.setAttribute("itemTransListSYS", (ArrayList) usmr.viewItemTransaction(loggedInUsername));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserItemTransactionSYS";
                    break;
                case "goToErrandsTrans":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("jobTransListSYS", (ArrayList) esmr.viewJobTransaction(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserJobTransaction";
                    break;
                case "goToMarketplaceTransDetailsSYS":
                    long itemID = Long.parseLong(request.getParameter("itemID"));
                    long itemTransID = Long.parseLong(request.getParameter("itemTransID"));
                    
                    request.setAttribute("itemTransDetailsSYSVec", usmr.viewTransactionItemDetails(itemID, itemTransID, loggedInUsername));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserItemTransactionDetailsSYS";
                    break;
                case "goToErrandsTransDetailsSYS":
                    long jobID = Long.parseLong(request.getParameter("jobID"));
                    long jobTransID = Long.parseLong(request.getParameter("jobTransID"));
                    
                    request.setAttribute("jobTransDetailsSYSVec", esmr.viewTransactionJobDetails(jobID, jobTransID, loggedInUsername));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserJobTransactionDetailsSYS";
                    break;
                case "createJobReview":
                    long jobTransactionID = Long.parseLong(request.getParameter("jobTransID"));
                    String reviewReceiverID = request.getParameter("receiver");
                    String reviewRating = request.getParameter("reviewRating");
                    System.out.println(reviewRating);
                    String reviewContent = request.getParameter("reviewContent");
                    System.out.println(reviewContent);
                    responseMessage = esmr.createJobReview(loggedInUsername, reviewReceiverID, jobTransactionID, reviewRating, reviewContent);
                    System.out.println(responseMessage);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToViewJobReviewList":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("allReviewList", (ArrayList)esmr.viewAllReviewsReceived(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "ViewJobReviewListSYS";
                    break;
                case "goToViewJobReviewDetails":
                    long hiddenJobID = Long.parseLong(request.getParameter("jobID"));
                    System.out.println("parameter: " + hiddenJobID);
                    request.setAttribute("message", "");
                    request.setAttribute("jobListSYS", (ArrayList)esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("jobReviewList", (ArrayList)esmr.viewReviewListOfAJob(loggedInUsername, hiddenJobID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "ViewJobReviewDetailsSYS";
                    break;
                case "goToJobListingInUserProfile":
                    String user = request.getParameter("posterName");
                    request.setAttribute("userProfileVec", usmr.viewUserProfileDetails(user));
                    request.setAttribute("userJobListing", esmr.viewUserJobList(user));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "JobListingInUserProfileSYS";
                    break;
                case "goToPendingItemOfferListSYS":
                    request.setAttribute("userMarketplaceOfferListSYS", (ArrayList) usmr.viewUserMarketplaceOfferList(loggedInUsername));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "PendingItemOfferListSYS";
                    break;
                case "goToPendingItemOfferDetailsSYS":
                    long urlitemID = Long.parseLong(request.getParameter("urlitemID"));
                    request.setAttribute("itemOfferUserListSYS", usmr.viewAnItemOfferUserList(loggedInUsername, urlitemID));
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "PendingItemOfferDetailsSYS";
                    break;
                case "acceptAnItemOfferSYS":
                    long itemIDHid = Long.parseLong(request.getParameter("itemIDHidden"));
                    long hidItemOfferID = Long.parseLong(request.getParameter("urlItemOfferID"));
                    String sellerAcceptComments = request.getParameter("sellerAcceptComments");
                    
                    responseMessage = usmr.acceptAnItemOffer(hidItemOfferID, sellerAcceptComments);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemOfferUserListSYS", usmr.viewAnItemOfferUserList(loggedInUsername, itemIDHid));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "PendingItemOfferDetailsSYS";
                    break;
                case "negotiateAnItemOfferSYS":
                    long itemIDHidd = Long.parseLong(request.getParameter("itemIDHidden"));
                    long hiddItemOfferID = Long.parseLong(request.getParameter("urlItemOfferID"));
                    String sellerNegotiateComments = request.getParameter("sellerNegotiateComments");
                    
                    responseMessage = usmr.negotiateAnItemOffer(hiddItemOfferID, sellerNegotiateComments);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemOfferUserListSYS", usmr.viewAnItemOfferUserList(loggedInUsername, itemIDHidd));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "PendingItemOfferDetailsSYS";
                    break;
                case "rejectAnItemOfferSYS":
                    long itemIDHiddd = Long.parseLong(request.getParameter("itemIDHidden"));
                    long hidddItemOfferID = Long.parseLong(request.getParameter("urlItemOfferID"));
                    
                    responseMessage = usmr.rejectAnItemOffer(hidddItemOfferID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemOfferUserListSYS", usmr.viewAnItemOfferUserList(loggedInUsername, itemIDHiddd));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "PendingItemOfferDetailsSYS";
                    break;
                case "completeAnItemOfferSYS":
                    long itemIDComplete = Long.parseLong(request.getParameter("itemIDHidden"));
                    long itemOfferIDComplete = Long.parseLong(request.getParameter("urlItemOfferID"));
                    String itemStatusComplete = request.getParameter("itemStatus");
                    
                    responseMessage = usmr.completeAnItemOffer(itemOfferIDComplete, itemStatusComplete);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemOfferUserListSYS", usmr.viewAnItemOfferUserList(loggedInUsername, itemIDComplete));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "PendingItemOfferDetailsSYS";
                    break;
                case "reopenAnItemOfferSYS":
                    long itemIDReopen = Long.parseLong(request.getParameter("itemIDHidden"));
                    long itemOfferIDReopen = Long.parseLong(request.getParameter("urlItemOfferID"));
                    String itemStatusReopen = request.getParameter("itemStatus");
                    
                    responseMessage = usmr.reopenAnItemOffer(itemOfferIDReopen, itemStatusReopen);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemOfferUserListSYS", usmr.viewAnItemOfferUserList(loggedInUsername, itemIDReopen));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "PendingItemOfferDetailsSYS";
                    break;
                case "provideFeedbackSYS":
                    long itemIDFeedback = Long.parseLong(request.getParameter("itemIDHidden"));
                    long itemOfferIDFeedback = Long.parseLong(request.getParameter("urlItemOfferID"));
                    String ratingReview = request.getParameter("ratingReview");
                    
                    responseMessage = usmr.provideTransFeedback(loggedInUsername, itemOfferIDFeedback, ratingReview);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    
                    if(responseMessage.contains("about the buyer")) {
                        request.setAttribute("itemOfferUserListSYS", usmr.viewAnItemOfferUserList(loggedInUsername, itemIDFeedback));
                        pageAction = "PendingItemOfferDetailsSYS";
                    } else if(responseMessage.contains("about the seller")) {
                        request.setAttribute("userMarketplaceRatingInfoVec", usmr.viewUserMarketplaceRatingInfo(loggedInUsername));
                        request.setAttribute("userBuyerOfferListSYS", (ArrayList) usmr.viewPersonalBuyerOfferList(loggedInUsername));
                        pageAction = "UserItemOfferListSYS";
                    }
                    break;
                case "goToUserItemWishlistSYS":
                    request.setAttribute("userItemWishlistSYS", (ArrayList) usmr.viewUserItemWishlist(loggedInUsername));
                    request.setAttribute("itemCategoryStr", msmr.populateItemCategory());
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserItemWishlistSYS";
                    break;
                case "goToUserJobWishlistSYS":
                    request.setAttribute("jobWishlist", (ArrayList) esmr.viewUserJobWishlist(loggedInUsername));
                    request.setAttribute("jobCategoryStr", esmr.getJobCategoryList());
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserJobWishlistSYS";
                    break;
                case "goToUserNotificationListSYS":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListSYS", usmr.viewUserMessageList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserNotificationListSYS";
                    break;
                case "goToMyBuyerOfferListSYS":
                    request.setAttribute("userMarketplaceRatingInfoVec", usmr.viewUserMarketplaceRatingInfo(loggedInUsername));
                    request.setAttribute("userBuyerOfferListSYS", (ArrayList) usmr.viewPersonalBuyerOfferList(loggedInUsername));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserItemOfferListSYS";
                    break;
                case "cancelPersonalItemOfferSYS":
                    long itemOfferHiddenID = Long.parseLong(request.getParameter("itemOfferHiddenID"));
                    
                    responseMessage = usmr.cancelPersonalItemOffer(itemOfferHiddenID);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "editPersonalItemOfferSYS":
                    long itemOfferHidID = Long.parseLong(request.getParameter("itemOfferHiddenID"));
                    String revisedOfferPrice = request.getParameter("revisedItemOffer");
                    
                    responseMessage = usmr.editPersonalItemOffer(itemOfferHidID, revisedOfferPrice);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToViewChatListSYS":
                    String assocItemID = request.getParameter("assocItemID");
                    request.setAttribute("userChatBuyingListSYS", usmr.viewUserChatBuyingList(loggedInUsername, assocItemID, true));
                    request.setAttribute("userChatSellingListSYS", usmr.viewUserChatSellingList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserChatListSYS";
                    break;
                case "goToViewChatListContentSYS":
                    long hidChatID = Long.parseLong(request.getParameter("hidChatID"));
                    String hidChatType = request.getParameter("hidChatType");
                    request.setAttribute("contentChatID", hidChatID);
                    request.setAttribute("hidChatType", hidChatType);
                    
                    request.setAttribute("chatListContentSYS", usmr.viewChatListContent(loggedInUsername, hidChatID));
                    request.setAttribute("chatContentInfoVecSYS", usmr.viewChatContentInfo(loggedInUsername, hidChatID));
                    request.setAttribute("userChatBuyingListSYS", usmr.viewUserChatBuyingList(loggedInUsername, "", false));
                    request.setAttribute("userChatSellingListSYS", usmr.viewUserChatSellingList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", loggedInUsername);
                    pageAction = "UserChatListContentSYS";
                    break;
                case "addNewChatContent":
                    String receiverID = request.getParameter("receiverID");
                    String chatContent = request.getParameter("chatContent");
                    String buyerOrSellerStat = request.getParameter("buyerOrSellerStat");
                    String buyerOrSellerID = request.getParameter("buyerOrSellerID");
                    long hiddenItemID = Long.parseLong(request.getParameter("hiddenItemID"));
                    
                    responseMessage = usmr.addNewChatContent(loggedInUsername, receiverID, chatContent, 
                            buyerOrSellerStat, buyerOrSellerID, hiddenItemID);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToMyJobListing":
                    request.setAttribute("jobCategoryStr", esmr.getJobCategoryList());
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userJobListing", (ArrayList) esmr.viewUserJobList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserJobListingSYS";
                    break;
                case "goToViewMyJobOfferSYS":
                    request.setAttribute("message", "");
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("myJobOfferList", (ArrayList)esmr.viewMyJobOffer(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "ViewMyJobOfferSYS";
                    break;
                case "editMyJobOfferSYS":
                    String responseMessage = editJobOffer(request, loggedInUsername);
                    request.setAttribute("message", responseMessage);
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("myJobOfferList", (ArrayList)esmr.viewMyJobOffer(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "ViewMyJobOfferSYS";
                    break;
                case "goToDeleteMyJobOfferSYS":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("myJobOfferList", (ArrayList)esmr.viewMyJobOffer(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "ViewMyJobOfferSYS";
                    break;
                case "goToCompanyReview":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("companyReviewListSYS", (ArrayList) vsmr.viewUserCompanyReview(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserCompanyReview";
                    break;
                case "goToDeleteReview":
                    long delReviewID = Long.parseLong(request.getParameter("hiddenReviewID"));
                    if (vsmr.deleteReview(delReviewID)) {
                        System.out.println("Selected Review has been deleted successfully.");
                    } else {
                        System.out.println("Selected Review cannot be deleted. Please try again later.");
                    }
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("companyReviewListSYS", (ArrayList) vsmr.viewUserCompanyReview(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserCompanyReview";
                    break;
                case "goToCompanyRequest":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("companyRequestListSYS", (ArrayList) vsmr.viewUserCompanyRequest(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserCompanyRequest";
                    break;
                case "goToResume":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("resumeListSYS", (ArrayList) vsmr.viewUserResume(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "UserResumeSYS";
                    break;
                case "goToNewResumeSYS":
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "NewResumeSYS";
                    break;
                case "createResumeSYS":
                    responseMessage = createResume(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    pageAction = "NewResumeSYS";
                    break;
                case "updateResumeSYS":
                    responseMessage = updateResume(request);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    request.setAttribute("resumeListSYS", (ArrayList) vsmr.viewUserResume(loggedInUsername));
                    pageAction = "UserResumeSYS";
                    break;
                case "goToViewResumeDetails":
                    long resumeID = Long.parseLong(request.getParameter("hiddenResumeID"));
                    
                    request.setAttribute("hiddenResumeID", resumeID);
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    request.setAttribute("basicDetailsVec", vsmr.viewResumeBasicDetails(resumeID));
                    request.setAttribute("eduExprList", vsmr.viewEduExprList(resumeID));
                    request.setAttribute("proExprList", vsmr.viewProjectExprList(resumeID));
                    request.setAttribute("workExprList", vsmr.viewWorkExprList(resumeID));
                    request.setAttribute("referenceList", vsmr.viewReferenceList(resumeID));
                    request.setAttribute("proSkillList", vsmr.viewProSkillList(resumeID));
                    request.setAttribute("perSkillList", vsmr.viewPerSkillList(resumeID));
                    pageAction = "ViewResumeDetailsSYS";
                    break;
                case "goToDeleteResumeSYS":
                    long delResumeID = Long.parseLong(request.getParameter("hiddenResumeID"));
                    if (vsmr.deleteResume(delResumeID)) {
                        System.out.println("Selected Resume has been deleted successfully.");
                    } else {
                        System.out.println("Selected Resume cannot be deleted. Please try again later.");
                    }
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    request.setAttribute("resumeListSYS", (ArrayList) vsmr.viewUserResume(loggedInUsername));
                    pageAction = "UserResumeSYS";
                    break;
                case "goToEditResumeDetailsSYS":
                    long editResumeID = Long.parseLong(request.getParameter("hiddenResumeID"));
                    
                    request.setAttribute("hiddenResumeID", editResumeID);
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount());
                    request.setAttribute("basicDetailsVec", vsmr.viewResumeBasicDetails(editResumeID));
                    request.setAttribute("eduExprList", vsmr.viewEduExprList(editResumeID));
                    request.setAttribute("proExprList", vsmr.viewProjectExprList(editResumeID));
                    request.setAttribute("workExprList", vsmr.viewWorkExprList(editResumeID));
                    request.setAttribute("referenceList", vsmr.viewReferenceList(editResumeID));
                    request.setAttribute("proSkillList", vsmr.viewProSkillList(editResumeID));
                    request.setAttribute("perSkillList", vsmr.viewPerSkillList(editResumeID));
                    pageAction = "EditResumeDetailsSYS";
                    break;
                case "markNotificationSYS":
                    long msgContentID = Long.parseLong(request.getParameter("msgContentID"));
                    String msgSenderID = (String) request.getParameter("msgSenderID");
                    
                    responseMessage = usmr.markNotification(msgContentID, msgSenderID);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            log("Exception in UserProfileSysUserController: processRequest()");
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "User Profile System User Servlet"; }
    
    /* MISCELLANEOUS METHODS */
    private String getCookieUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String loggedInUsername = null;
        if(cookies!=null){
            for(Cookie c : cookies){
                if(c.getName().equals("username") && !c.getValue().equals("")){
                    loggedInUsername = c.getValue();
                }
            }
        }
        return loggedInUsername;
    }
    
    public String editJobOffer(HttpServletRequest request, String username){
        
        long jobID = Long.parseLong(request.getParameter("jobIDHidden"));
        
        String offerPrice = request.getParameter("jobOfferPrice");
        if(offerPrice.equals("")) { offerPrice = request.getParameter("hiddenOfferPrice"); }
        
        String offerComment = request.getParameter("jobOfferDescription");
        if(offerComment.equals("")) { offerComment = request.getParameter("hiddenOfferDescription"); }
        
        System.out.println(offerPrice + " " + offerComment);
        
        String message = esmr.editJobOfferPrice(jobID, username, offerPrice, offerComment);
        
        System.out.println(message);
        
        return message;
    }
    
    private String createResume(HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("userImage");
            fileName = (String) getFileName(filePart);
            if(fileName.contains("\\")) {
                fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
            }
                    
            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "voices"
                    + File.separator + "resume" + File.separator;
            
            InputStream inputStream = null;
            OutputStream outputStream = null;
            
            try {
                File outputFilePath = new File(imageDir + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);

                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fileName = "";
        }
        
        String userFullName = request.getParameter("userFullName");
        String contactNum = request.getParameter("contactNum");
        String emailAddr = request.getParameter("emailAddr");
        String postalAddr = request.getParameter("postalAddr");
        String summary = request.getParameter("summary");
        String awardStr = request.getParameter("awardStr");
        
        String eduExpr = request.getParameter("eduExprList");
        String[] eduExprList = eduExpr.split(";&",Integer.MAX_VALUE);
        String proExpr = request.getParameter("proExprList");
        String[] proExprList = proExpr.split(";&",Integer.MAX_VALUE);
        String skill = request.getParameter("skillList");
        String[] skillList = skill.split(";&",Integer.MAX_VALUE);
        String workExpr = request.getParameter("workExprList");
        String[] workExprList = workExpr.split(";&",Integer.MAX_VALUE);
        String reference = request.getParameter("referenceList");
        String[] referenceList = reference.split(";&",Integer.MAX_VALUE);
        
        String loggedInUsername = getCookieUsername(request);
        
        responseMessage = vsmr.createResume(userFullName, contactNum, emailAddr, postalAddr, summary, awardStr, eduExprList, proExprList, skillList, workExprList, referenceList, fileName, loggedInUsername, "create");
        return responseMessage;
    }
    
    private String updateResume(HttpServletRequest request) {
        String fileName = "";
        String imageUploadStatus = request.getParameter("imageUploadStatus");
        
        if(imageUploadStatus.equals("Uploaded")) {
            try {
                Part filePart = request.getPart("userImage");
                fileName = (String) getFileName(filePart);
                if(fileName.contains("\\")) {
                    fileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf("\\")+1), "");
                }

                String appPath = request.getServletContext().getRealPath("");
                String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                        + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
                String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                        + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "voices"
                        + File.separator + "resume" + File.separator;

                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    File outputFilePath = new File(imageDir + fileName);
                    inputStream = filePart.getInputStream();
                    outputStream = new FileOutputStream(outputFilePath);

                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    fileName = "";
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                fileName = "";
            }
        }
        else { fileName = request.getParameter("oldUserImage"); }
        
        long resumeID = Long.parseLong(request.getParameter("hiddenResumeID"));
        String userFullName = request.getParameter("userFullName");
        String contactNum = request.getParameter("contactNum");
        String emailAddr = request.getParameter("emailAddr");
        String postalAddr = request.getParameter("postalAddr");
        String summary = request.getParameter("summary");
        String awardStr = request.getParameter("awardStr");
        
        String eduExpr = request.getParameter("eduExprList");
        String[] eduExprList = eduExpr.split(";&",Integer.MAX_VALUE);
        String proExpr = request.getParameter("proExprList");
        String[] proExprList = proExpr.split(";&",Integer.MAX_VALUE);
        String skill = request.getParameter("skillList");
        String[] skillList = skill.split(";&",Integer.MAX_VALUE);
        String workExpr = request.getParameter("workExprList");
        String[] workExprList = workExpr.split(";&",Integer.MAX_VALUE);
        String reference = request.getParameter("referenceList");
        String[] referenceList = reference.split(";&",Integer.MAX_VALUE);
        
        String loggedInUsername = getCookieUsername(request);
        
        if(vsmr.deleteResume(resumeID)) {
            responseMessage = vsmr.createResume(userFullName, contactNum, emailAddr, postalAddr, summary, awardStr, eduExprList, proExprList, skillList, workExprList, referenceList, fileName, loggedInUsername, "update");
            return responseMessage;
        } else {
            return "There were some issues encountered while updating your resume. Please try again.";
        }
        
    }
    
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}