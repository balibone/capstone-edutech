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
    
    boolean firstVisit = true;
    String username = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            String pageAction = request.getParameter("pageTransit");
            System.out.println("pageAction: " + pageAction);
            
            switch (pageAction) {
                case "goToUnifyUserAccount":
                    if(firstVisit == true) { 
                        username = request.getParameter("userID");
                        firstVisit = false;
                    }
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("itemOfferListSYS", (ArrayList) msmr.viewItemOfferList(username));
                    pageAction = "UserAccountSYS";
                    break;
                case "goToMarketplaceTrans":
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("itemTransListSYS", (ArrayList) msmr.viewItemTransaction(username));
                    pageAction = "UserItemTransaction";
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
                    request.setAttribute("transItemDetailsSYSVec", msmr.viewTransactionItemDetails(itemID, itemTransID, username));
                    pageAction = "ViewItemTransDetailsInModalSYS";
                    break;
                case "goToUserProfile":
                    String itemSellerID = request.getParameter("posterID");
                    request.setAttribute("userProfileVec", usmr.viewUserProfileDetails(itemSellerID));
                    request.setAttribute("userItemListSYS", msmr.viewUserItemList(itemSellerID));
                    request.setAttribute("userJobListing", esmr.viewUserJobList(itemSellerID));
                    pageAction = "UserProfile";
                    break;
                case "goToJobListingInUserProfile":
                    String user = request.getParameter("posterName");
                    request.setAttribute("userProfileVec", usmr.viewUserProfileDetails(user));
                    request.setAttribute("userJobListing", esmr.viewUserJobList(user));
                    pageAction = "JobListingInUserProfileSYS";
                    break;
                case "goToPendingItemOfferList":
                    long urlitemID = Long.parseLong(request.getParameter("urlitemID"));
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("itemOfferUserListSYS", msmr.viewItemOfferUserList(username, urlitemID));
                    pageAction = "PendingItemOfferSYS";
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
                case "goToCancelRequest":
                    long delRequestID = Long.parseLong(request.getParameter("hiddenRequestID"));
                    if (vsmr.cancelRequest(delRequestID)) {
                        System.out.println("Selected request has been canceled successfully.");
                    } else {
                        System.out.println("Selected review cannot be canceled. Please try again later.");
                    }
                    request.setAttribute("userAccountVec", usmr.viewUserProfileDetails(username));
                    request.setAttribute("companyRequestListSYS", (ArrayList) vsmr.viewUserCompanyRequest(username));
                    pageAction = "UserCompanyRequest";
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
                    firstVisit = true;
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