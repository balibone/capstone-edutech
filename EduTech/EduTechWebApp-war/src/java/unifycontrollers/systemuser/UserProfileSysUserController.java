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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unifysessionbeans.systemuser.UserProfileSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.MarketplaceSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.VoicesSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.ErrandsSysUserMgrBeanRemote;

public class UserProfileSysUserController extends HttpServlet {
    @EJB
    private UserProfileSysUserMgrBeanRemote usmr;
    @EJB
    private MarketplaceSysUserMgrBeanRemote msmr;
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
            
            switch (pageAction) {
                case "goToUnifyUserAccountSYS":
                    request.setAttribute("itemOfferListSYS", (ArrayList) usmr.viewItemOfferList(loggedInUsername));
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "UserAccountSYS";
                    break;
                case "goToMarketplaceTransSYS":
                    request.setAttribute("itemTransListSYS", (ArrayList) usmr.viewItemTransaction(loggedInUsername));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "UserItemTransactionSYS";
                    break;
                case "goToMyJobListing":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("userJobListing", (ArrayList) esmr.viewUserJobList(username));
                    pageAction = "UserJobListingSYS";
                    break;
                case "goToViewMyJobOfferSYS":
                    request.setAttribute("message", " ");
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("myJobOfferList", (ArrayList)esmr.viewMyJobOffer(username));
                    pageAction = "ViewMyJobOfferSYS";
                    break;
                case "editMyJobOfferSYS":
                    String responseMessage = editJobOffer(request, username);
                    
                    System.out.println(responseMessage);
                    
                    //response.setContentType("text/plain");
                    //response.getWriter().write(responseMessage);
                    request.setAttribute("message", responseMessage);
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("myJobOfferList", (ArrayList)esmr.viewMyJobOffer(username));
                    pageAction = "ViewMyJobOfferSYS";
                    break;
                case "goToDeleteMyJobOfferSYS":
                    long offerID = Long.parseLong(request.getParameter("offerID"));
                    String returnResponse = esmr.deleteJobOffer(offerID);
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("myJobOfferList", (ArrayList)esmr.viewMyJobOffer(username));
                    pageAction = "ViewMyJobOfferSYS";
                    break;
                case "goToViewItemDetailsInModalSYS":
                    long itemID = Long.parseLong(request.getParameter("itemID"));
                    long itemTransID = Long.parseLong(request.getParameter("itemTransID"));
                    
                    request.setAttribute("transItemDetailsSYSVec", usmr.viewTransactionItemDetails(itemID, itemTransID, loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "ViewItemTransDetailsInModalSYS";
                    break;
                case "goToUserProfileSYS":
                    String itemSellerID = request.getParameter("itemSellerID");
                    request.setAttribute("userItemListSYS", usmr.viewUserItemList(loggedInUsername, itemSellerID));
                    
                    request.setAttribute("userProfileVec", usmr.viewUserProfileDetails(itemSellerID));
                    request.setAttribute("userItemListSYS", msmr.viewUserItemList(itemSellerID));
                    request.setAttribute("userJobListing", esmr.viewUserJobList(itemSellerID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "UserProfileSYS";
                    break;
                case "goToJobListingInUserProfile":
                    String user = request.getParameter("posterName");
                    request.setAttribute("userProfileVec", usmr.viewUserProfileDetails(user));
                    request.setAttribute("userJobListing", esmr.viewUserJobList(user));
                    pageAction = "JobListingInUserProfileSYS";
                    break;
                case "goToPendingItemOfferListSYS":
                    long urlitemID = Long.parseLong(request.getParameter("urlitemID"));
                    request.setAttribute("itemOfferUserListSYS", usmr.viewItemOfferUserList(loggedInUsername, urlitemID));
                    
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "PendingItemOfferSYS";
                    break;
                case "acceptAnItemOfferSYS":
                    long itemIDHidden = Long.parseLong(request.getParameter("itemIDHidden"));
                    long urlItemOfferID = Long.parseLong(request.getParameter("urlItemOfferID"));
                    responseMessage = usmr.acceptAnItemOffer(urlItemOfferID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemOfferUserListSYS", usmr.viewItemOfferUserList(loggedInUsername, itemIDHidden));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "PendingItemOfferSYS";
                    break;
                case "rejectAnItemOfferSYS":
                    long hiddenItemID = Long.parseLong(request.getParameter("hiddenItemID"));
                    long hiddenItemOfferID = Long.parseLong(request.getParameter("hiddenItemOfferID"));
                    responseMessage = usmr.rejectAnItemOffer(hiddenItemOfferID);
                    if (responseMessage.endsWith("!")) { request.setAttribute("successMessage", responseMessage); } 
                    else { request.setAttribute("errorMessage", responseMessage); }
                    
                    request.setAttribute("itemOfferUserListSYS", usmr.viewItemOfferUserList(loggedInUsername, hiddenItemID));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "PendingItemOfferSYS";
                    break;
                case "goToUserNotificationListSYS":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListSYS", usmr.viewUserMessageList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    pageAction = "UserNotificationListSYS";
                    break;
                case "goToMyBuyerOfferListSYS":
                    request.setAttribute("userBuyerOfferListSYS", (ArrayList) usmr.viewPersonalBuyerOfferList(loggedInUsername));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
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
                    double revisedOfferPrice = Double.parseDouble(request.getParameter("revisedItemOffer"));
                    
                    responseMessage = usmr.editPersonalItemOffer(itemOfferHidID, revisedOfferPrice);
                    response.setContentType("text/plain");
                    response.getWriter().write(responseMessage);
                    break;
                case "goToCompanyReview":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("companyReviewListSYS", (ArrayList) vsmr.viewUserCompanyReview(username));
                    pageAction = "UserCompanyReview";
                    break;
                case "goToDeleteReview":
                    long delReviewID = Long.parseLong(request.getParameter("hiddenReviewID"));
                    if (vsmr.deleteReview(delReviewID)) {
                        System.out.println("Selected Review has been deleted successfully.");
                    } else {
                        System.out.println("Selected Review cannot be deleted. Please try again later.");
                    }
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("companyReviewListSYS", (ArrayList) vsmr.viewUserCompanyReview(username));
                    pageAction = "UserCompanyReview";
                    break;
                case "goToCompanyRequest":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("companyRequestListSYS", (ArrayList) vsmr.viewUserCompanyRequest(username));
                    pageAction = "UserCompanyRequest";
                    break;
                case "goToResume":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("resumeListSYS", (ArrayList) vsmr.viewUserResume(username));
                    pageAction = "UserResume";
                    break;
                case "goToViewResumeDetails":
                    long resumeID = Long.parseLong(request.getParameter("hiddenResumeID"));
                    request.setAttribute("basicDetailsVec", vsmr.viewResumeBasicDetails(resumeID));
                    request.setAttribute("eduExprList", vsmr.viewEduExprList(resumeID));
                    request.setAttribute("workExprList", vsmr.viewWorkExprList(resumeID));
                    request.setAttribute("proExprList", vsmr.viewProjectExprList(resumeID));
                    pageAction = "ViewResumeDetailsSYS";
                    break;
                case "goToLogout":
                    Cookie cookieUsername = new Cookie("username", "");
                    cookieUsername.setPath("/");
                    cookieUsername.setMaxAge(0); 
                    response.addCookie(cookieUsername);
                    
                    Cookie cookieUserType = new Cookie("userType", "");
                    cookieUserType.setPath("/");
                    cookieUserType.setMaxAge(0);
                    response.addCookie(cookieUserType);
                    
                    String sessionInvalid = request.getParameter("sessionInvalid");
                    String sessionExpire = request.getParameter("sessionExpire");
                    if(sessionInvalid != null && sessionInvalid.equals("true")){
                        request.setAttribute("sysMessage", "<strong>Invalid session. Please login again.</strong>");
                    }
                    if(sessionExpire != null && sessionExpire.equals("true")){
                        request.setAttribute("sysMessage", "<strong>You session has expired. Please login again.</strong>");
                    }
                    pageAction = "IntegratedSPLogin";
                    break;
                default:
                    break;
            }
            System.out.println("dispatch: " + pageAction);
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
}