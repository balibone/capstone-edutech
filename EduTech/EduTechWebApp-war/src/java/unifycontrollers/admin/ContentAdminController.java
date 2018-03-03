package unifycontrollers.admin;

import java.io.IOException;
import javax.ejb.EJB;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unifysessionbeans.admin.ContentAdminMgrBeanRemote;
import unifysessionbeans.admin.UserProfileAdminMgrBeanRemote;
import unifysessionbeans.admin.ErrandsAdminMgrBeanRemote;

public class ContentAdminController extends HttpServlet {

    @EJB
    private ContentAdminMgrBeanRemote camr;
    @EJB
    private UserProfileAdminMgrBeanRemote uamr;
    @EJB
    private ErrandsAdminMgrBeanRemote eamr;
    String responseMessage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            request.setAttribute("unifyUserCount", uamr.getUnifyUserCount());

            switch (pageAction) {
                case "goToTagListing":
                    request.setAttribute("tagsListCount", camr.getTagsListCount());
                    request.setAttribute("tagsList", (ArrayList) camr.viewTagListing());
                    pageAction = "TagListing";
                    break;
                case "goToNewTag":
                    pageAction = "NewTag";
                    break;
                case "createTag":
                    responseMessage = createTag(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }

                    request.setAttribute("tagsListCount", camr.getTagsListCount());
                    request.setAttribute("tagsList", (ArrayList) camr.viewTagListing());
                    pageAction = "TagListing";
                    break;
                case "goToEditTag":
                    String hiddenTag = request.getParameter("tagID");
                    request.setAttribute("tagDetailsVec", camr.viewTagDetails(hiddenTag));
                    pageAction = "EditTag";
                    break;
                case "updateTag":
                    responseMessage = updateTag(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }

                    request.setAttribute("tagsListCount", camr.getTagsListCount());
                    request.setAttribute("tagsList", (ArrayList) camr.viewTagListing());
                    pageAction = "TagListing";
                    break;
                case "deleteTag":
                    String tagID = request.getParameter("tagID");
                    responseMessage = camr.deleteTag(tagID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }

                    request.setAttribute("tagsListCount", camr.getTagsListCount());
                    request.setAttribute("tagsList", (ArrayList) camr.viewTagListing());
                    pageAction = "TagListing";
                    break;
                case "goToReportedListing":
                    pageAction = "ReportedListing";
                    break;
                case "goToReportedMarketplaceListing":
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    request.setAttribute("unresolvedItemReportCount", camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedItemReportCount", camr.getResolvedItemReportCount());
                    pageAction = "ReportedMarketplaceListing";
                    break;
                case "goToReportedMarketplaceDetails2":
                    String marketplaceReportIDView2 = request.getParameter("marketplaceView");
                    request.setAttribute("reportedMarketplaceVec", camr.viewMarketplaceDetails2(marketplaceReportIDView2));
                    pageAction = "ReportedMarketplaceDetails2";
                    break;
                case "goToReportedMarketplaceDetailsFromAllList":
                    String marketplaceReportIDView3 = request.getParameter("marketplaceView");
                    request.setAttribute("reportedMarketplaceVec", camr.viewMarketplaceDetails2(marketplaceReportIDView3));
                    pageAction = "ReportedMarketplaceDetailsFromAllList";
                    break;
                case "resolveMarketplaceReport":
                    if (updateMarketplaceReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    request.setAttribute("unresolvedItemReportCount", camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedItemReportCount", camr.getResolvedItemReportCount());
                    pageAction = "ReportedMarketplaceListing";
                    break;
                case "resolveMarketplaceReportFromAllList":
                    if (resolveOnlyMarketplaceReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "resolveDeleteMarketplaceReport":
                    String deleteItemID = request.getParameter("reportedItemID");
                    if (updateMarketplaceReport(request)) {
                        //if (camr.deleteItem(deleteItemID)) {
                        //    request.setAttribute("successMessage", "Selected item has been set to <b>resolved</b> and <b>item has been deleted</b>.");
                        //}
                        responseMessage = camr.deleteItem(deleteItemID);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Selected item cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    request.setAttribute("unresolvedItemReportCount", camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedItemReportCount", camr.getResolvedItemReportCount());
                    pageAction = "ReportedMarketplaceListing";
                    break;
                case "resolveDeleteMarketplaceReportFromAllList":
                    String deleteItemID2 = request.getParameter("reportedItemID");
                    if (resolveDeleteMarketplaceReport(request)) {
                        responseMessage = camr.deleteItem(deleteItemID2);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Selected item cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "resolveDelistMarketplaceReportFromAllList":
                    String deleteItemID3 = request.getParameter("reportedItemID");
                    if (resolveDelistMarketplaceReport(request)) {
                        responseMessage = camr.delistItem(deleteItemID3);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Selected item cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "unresolveMarketplaceReport":
                    if (updateMarketplaceReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been reverted to <b>unresolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    request.setAttribute("unresolvedItemReportCount", camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedItemReportCount", camr.getResolvedItemReportCount());
                    pageAction = "ReportedMarketplaceListing";
                    break;
                case "resolveErrandReport":
                    if (updateErrandReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("unresolvedErrandsReportCount", camr.getUnresolvedErrandsReportCount());
                    request.setAttribute("resolvedErrandsReportCount", camr.getResolvedErrandsReportCount());
                    pageAction = "ReportedErrandsListing";
                    break;
                case "resolveErrandReportFromAllList":
                    if (resolveOnlyErrandReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "resolveDeleteErrandReport":
                    String deleteJobID = request.getParameter("reportedErrandID");
                    if (updateErrandReport(request)) {
                        //if (camr.deleteJob(deleteJobID)) {
                        //    request.setAttribute("successMessage", "Selected job has been set to <b>resolved</b> and <b>job deleted</b>.");
                        //}
                        responseMessage = camr.deleteJob(deleteJobID);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }

                    } else {
                        request.setAttribute("errorMessage", "Selected job cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("unresolvedErrandsReportCount", camr.getUnresolvedErrandsReportCount());
                    request.setAttribute("resolvedErrandsReportCount", camr.getResolvedErrandsReportCount());
                    pageAction = "ReportedErrandsListing";
                    break;
                case "resolveDeleteErrandReportFromAllList":
                    String deleteJobID2 = request.getParameter("reportedErrandID");
                    if (resolveDeleteErrandReport(request)) {
                        //responseMessage = camr.deleteJob(deleteJobID2);
                        Long jobIDNum = Long.parseLong(deleteJobID2);
                        responseMessage = eamr.deleteAJob(jobIDNum);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }

                    } else {
                        request.setAttribute("errorMessage", "Selected job cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "unresolveErrandReport":
                    if (updateErrandReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been reverted to <b>unresolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("unresolvedErrandsReportCount", camr.getUnresolvedErrandsReportCount());
                    request.setAttribute("resolvedErrandsReportCount", camr.getResolvedErrandsReportCount());
                    pageAction = "ReportedErrandsListing";
                    break;
                case "goToReportedErrandDetails2":
                    String errandReportIDView2 = request.getParameter("errandView");
                    request.setAttribute("reportedErrandsVec", camr.viewErrandDetails2(errandReportIDView2));
                    pageAction = "ReportedErrandDetails2";
                    break;
                case "goToReportedErrandDetailsFromAllList":
                    String errandReportIDView3 = request.getParameter("errandView");
                    request.setAttribute("reportedErrandsVec", camr.viewErrandDetails2(errandReportIDView3));
                    pageAction = "ReportedErrandDetailsFromAllList";
                    break;
                case "goToReportedErrandsListing":
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("unresolvedErrandsReportCount", camr.getUnresolvedErrandsReportCount());
                    request.setAttribute("resolvedErrandsReportCount", camr.getResolvedErrandsReportCount());
                    pageAction = "ReportedErrandsListing";
                    break;
                case "goToReportedErrandsReviewListing":
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("unresolvedErrandsReviewReportCount", camr.getUnresolvedErrandsReviewReportCount());
                    request.setAttribute("resolvedErrandsReviewReportCount", camr.getResolvedErrandsReviewReportCount());
                    pageAction = "ReportedErrandsReviewListing";
                    break;
                case "goToReportedErrandReviewDetails":
                    String errandReviewReportIDView = request.getParameter("errandReviewView");
                    request.setAttribute("reportedErrandsReviewVec", camr.viewErrandReviewDetails(errandReviewReportIDView));
                    pageAction = "ReportedErrandReviewDetails";
                    break;
                case "goToReportedErrandReviewDetailsFromAllList":
                    String errandReviewReportIDView2 = request.getParameter("errandReviewView");
                    request.setAttribute("reportedErrandsReviewVec", camr.viewErrandReviewDetails(errandReviewReportIDView2));
                    pageAction = "ReportedErrandReviewDetailsFromAllList";
                    break;
                case "resolveErrandReviewReport":
                    if (updateErrandReviewReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("unresolvedErrandsReviewReportCount", camr.getUnresolvedErrandsReviewReportCount());
                    request.setAttribute("resolvedErrandsReviewReportCount", camr.getResolvedErrandsReviewReportCount());
                    pageAction = "ReportedErrandsReviewListing";
                    break;
                case "resolveErrandReviewReportFromAllList":
                    if (resolveOnlyErrandReviewReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "resolveDeleteErrandReviewReport":
                    String deleteJobReviewID = request.getParameter("reportedErrandID");
                    if (updateErrandReviewReport(request)) {
                        //if (camr.deleteJobReview(deleteJobReviewID)) {
                        //    request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b> and <b>job review deleted</b>.");
                        //}
                        responseMessage = camr.deleteJobReview(deleteJobReviewID);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("unresolvedErrandsReviewReportCount", camr.getUnresolvedErrandsReviewReportCount());
                    request.setAttribute("resolvedErrandsReviewReportCount", camr.getResolvedErrandsReviewReportCount());
                    pageAction = "ReportedErrandsReviewListing";
                    break;
                case "resolveDeleteErrandReviewReportFromAllList":
                    String deleteJobReviewID2 = request.getParameter("reportedErrandID");
                    if (resolveDeleteErrandReviewReport(request)) {
                        responseMessage = camr.deleteJobReview(deleteJobReviewID2);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "unresolveErrandReviewReport":
                    if (updateErrandReviewReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been reverted to <b>unresolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("unresolvedErrandsReviewReportCount", camr.getUnresolvedErrandsReviewReportCount());
                    request.setAttribute("resolvedErrandsReviewReportCount", camr.getResolvedErrandsReviewReportCount());
                    pageAction = "ReportedErrandsReviewListing";
                    break;
                case "goToReportedReviewListing":
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("unresolvedCompanyReviewReportCount", camr.getUnresolvedCompanyReviewReportCount());
                    request.setAttribute("resolvedCompanyReviewReportCount", camr.getResolvedCompanyReviewReportCount());
                    pageAction = "ReportedReviewListing";
                    break;
                case "goToReportedReviewDetails2":
                    String reviewReportIDView2 = request.getParameter("reviewView");
                    request.setAttribute("reportedReviewVec", camr.viewReviewDetails2(reviewReportIDView2));
                    pageAction = "ReportedReviewDetails2";
                    break;
                case "goToReportedReviewDetailsFromAllList":
                    String reviewReportIDView3 = request.getParameter("reviewView");
                    request.setAttribute("reportedReviewVec", camr.viewReviewDetails2(reviewReportIDView3));
                    pageAction = "ReportedReviewDetailsFromAllList";
                    break;
                case "resolveReviewReport":
                    if (updateReviewReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("unresolvedCompanyReviewReportCount", camr.getUnresolvedCompanyReviewReportCount());
                    request.setAttribute("resolvedCompanyReviewReportCount", camr.getResolvedCompanyReviewReportCount());
                    pageAction = "ReportedReviewListing";
                    break;
                case "resolveReviewReportFromAllList":
                    if (resolveOnlyReviewReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "resolveDeleteReviewReport":
                    String deleteReviewID = request.getParameter("reportedReviewID");
                    if (updateReviewReport(request)) {
                        //if (camr.deleteReview(deleteReviewID)) {
                        //    request.setAttribute("successMessage", "Selected report has been set to <b>resolved</b> and <b>review has been deleted</b>.");
                        //}
                        responseMessage = camr.deleteReview(deleteReviewID);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("unresolvedCompanyReviewReportCount", camr.getUnresolvedCompanyReviewReportCount());
                    request.setAttribute("resolvedCompanyReviewReportCount", camr.getResolvedCompanyReviewReportCount());
                    pageAction = "ReportedReviewListing";
                    break;
                case "resolveDeleteReviewReportFromAllList":
                    String deleteReviewID2 = request.getParameter("reportedReviewID");
                    if (resolveDeleteReviewReport(request)) {
                        responseMessage = camr.deleteReview(deleteReviewID2);
                        if (responseMessage.endsWith("!")) {
                            request.setAttribute("successMessage", responseMessage);
                        } else {
                            request.setAttribute("errorMessage", responseMessage);
                        }
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "unresolveReviewReport":
                    if (updateReviewReport(request)) {
                        request.setAttribute("successMessage", "Selected report has been reverted to <b>unresolved</b>.");
                    } else {
                        request.setAttribute("errorMessage", "Selected report cannot be updated. Please try again.");
                    }
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("unresolvedCompanyReviewReportCount", camr.getUnresolvedCompanyReviewReportCount());
                    request.setAttribute("resolvedCompanyReviewReportCount", camr.getResolvedCompanyReviewReportCount());
                    pageAction = "ReportedReviewListing";
                    break;
                case "goToAllReportedListing":
                    request.setAttribute("unresolvedContentReportCount", camr.getUnresolvedCompanyReviewReportCount() + camr.getUnresolvedErrandsReportCount() + camr.getUnresolvedErrandsReviewReportCount() + camr.getUnresolvedItemReportCount());
                    request.setAttribute("resolvedContentReportCount", camr.getResolvedCompanyReviewReportCount() + camr.getResolvedErrandsReportCount() + camr.getResolvedErrandsReviewReportCount() + camr.getResolvedItemReportCount());
                    request.setAttribute("reportErrandsList", (ArrayList) camr.viewReportedErrandsListing());
                    request.setAttribute("reportErrandsReviewList", (ArrayList) camr.viewReportedErrandsReviewListing());
                    request.setAttribute("reportReviewList", (ArrayList) camr.viewReportedReviewListing());
                    request.setAttribute("reportList", (ArrayList) camr.viewReportedMarketplaceListing());
                    pageAction = "AllReportedListing";
                    break;
                case "goToEventRequest":
                    request.setAttribute("eventRequestList", (ArrayList) camr.viewEventRequestListing());
                    request.setAttribute("pendingEventRequestCount", camr.getPendingEventRequestCount());
                    request.setAttribute("approvedEventRequestCount", camr.getApprovedEventRequestCount());
                    request.setAttribute("rejectedEventRequestCount", camr.getRejectedEventRequestCount());

                    pageAction = "EventRequest";
                    break;
                case "goToEventRequestDetails":
                    String eventRequestDetails = request.getParameter("requestView");
                    request.setAttribute("eventRequestVec", camr.viewEventRequestDetails(eventRequestDetails));
                    pageAction = "EventRequestDetails";
                    break;
                case "approveEventRequest":
                    String approveEventRequestID = request.getParameter("requestID");
                    //if (camr.approveEventRequest(approveEventRequestID)) {
                    //    request.setAttribute("successMessage", "Selected event has been set to <b>approved</b> and <b>event has been created</b>.");
                    //} else {
                    //    request.setAttribute("errorMessage", "Selected event cannot be updated. Please try again.");
                    //}
                    responseMessage = camr.approveEventRequest(approveEventRequestID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventRequestList", (ArrayList) camr.viewEventRequestListing());
                    request.setAttribute("pendingEventRequestCount", camr.getPendingEventRequestCount());
                    request.setAttribute("approvedEventRequestCount", camr.getApprovedEventRequestCount());
                    request.setAttribute("rejectedEventRequestCount", camr.getRejectedEventRequestCount());
                    pageAction = "EventRequest";
                    break;
                case "rejectEventRequest":
                    String rejectEventRequestID = request.getParameter("requestID");
                    //if (camr.rejectEventRequest(rejectEventRequestID)) {
                    //    request.setAttribute("successMessage", "Selected event has been <b>rejected</b>.");
                    //} else {
                    //    request.setAttribute("errorMessage", "Selected event cannot be updated. Please try again.");
                    //}
                    responseMessage = camr.rejectEventRequest(rejectEventRequestID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventRequestList", (ArrayList) camr.viewEventRequestListing());
                    request.setAttribute("pendingEventRequestCount", camr.getPendingEventRequestCount());
                    request.setAttribute("approvedEventRequestCount", camr.getApprovedEventRequestCount());
                    request.setAttribute("rejectedEventRequestCount", camr.getRejectedEventRequestCount());
                    pageAction = "EventRequest";
                    break;
                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            log("Exception in ContentReviewController: processRequest()");
            ex.printStackTrace();
        }

    }

    private String createTag(HttpServletRequest request) {
        String tagName = request.getParameter("tagName");
        String tagType = request.getParameter("tagType");
        return camr.createTag(tagName, tagType);
    }

    private String updateTag(HttpServletRequest request) {
        String tagID = request.getParameter("hiddenTagID");
        String tagName = request.getParameter("oldTagName");
        String newTagName = request.getParameter("tagName");
        String tagType = request.getParameter("oldTagType");
        String newTagType = request.getParameter("tagType");

        if (!newTagName.equals("")) {
            tagName = newTagName;
        }
        if (!newTagType.equals("")) {
            tagType = newTagType;
        }
        return camr.updateTag(tagID, tagName, tagType);
    }

    private boolean updateErrandReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        String reportCurrentStatus = request.getParameter("reportStatus");

        if (reportCurrentStatus.equals("Unresolved")) {
            responseMessage = camr.resolveErrand(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            //camr.resolveErrand(reportStatusUpdate);
            reportUpdateStatus = true;
        } else if (reportCurrentStatus.equals("Resolved")) {
            responseMessage = camr.unresolveErrand(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            //camr.unresolveErrand(reportStatusUpdate);
            reportUpdateStatus = true;
        }

        return reportUpdateStatus;
    }
    
    private boolean resolveOnlyErrandReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        
         try {
            responseMessage = camr.resolveOnlyErrand(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            //camr.resolveErrand(reportStatusUpdate);
            reportUpdateStatus = true;
        } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only errand report. ");
        }
         return reportUpdateStatus;
    }
    
    private boolean resolveDeleteErrandReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        
         try {
            responseMessage = camr.resolveDeleteErrand(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            //camr.resolveErrand(reportStatusUpdate);
            reportUpdateStatus = true;
        } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only errand report. ");
        }
         return reportUpdateStatus;
    }

    private boolean updateErrandReviewReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        String reportCurrentStatus = request.getParameter("reportStatus");

        if (reportCurrentStatus.equals("Unresolved")) {
            //camr.resolveErrandReview(reportStatusUpdate);
            responseMessage = camr.resolveErrandReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } else if (reportCurrentStatus.equals("Resolved")) {
            //camr.unresolveErrandReview(reportStatusUpdate);
            responseMessage = camr.unresolveErrandReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        }

        return reportUpdateStatus;
    }
    
    private boolean resolveOnlyErrandReviewReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        

        try {
            responseMessage = camr.resolveOnlyErrandReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        
       } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only errand review report. ");
        }
         return reportUpdateStatus;
    }
    
    private boolean resolveDeleteErrandReviewReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        

        try {
            responseMessage = camr.resolveDeleteErrandReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        
       } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only errand review report. ");
        }
         return reportUpdateStatus;
    }

    private boolean updateMarketplaceReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        String reportCurrentStatus = request.getParameter("reportStatus");

        if (reportCurrentStatus.equals("Unresolved")) {
            //camr.resolveMarketplace(reportStatusUpdate);
            responseMessage = camr.resolveMarketplace(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } else if (reportCurrentStatus.equals("Resolved")) {
            //camr.unresolveMarketplace(reportStatusUpdate);
            responseMessage = camr.unresolveMarketplace(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        }

        return reportUpdateStatus;
    }

    private boolean resolveOnlyMarketplaceReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");

        try {
            responseMessage = camr.resolveOnlyMarketplace(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only marketplace item report. ");
        }

        return reportUpdateStatus;
    }
    
    private boolean resolveDeleteMarketplaceReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");

        try {
            responseMessage = camr.resolveDeleteMarketplace(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only marketplace item. ");
        }

        return reportUpdateStatus;
    }
    
    private boolean resolveDelistMarketplaceReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");

        try {
            responseMessage = camr.resolveDelistMarketplace(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only marketplace item. ");
        }

        return reportUpdateStatus;
    }

    private boolean updateReviewReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
        String reportCurrentStatus = request.getParameter("reportStatus");

        if (reportCurrentStatus.equals("Unresolved")) {
            //camr.resolveReview(reportStatusUpdate);
            responseMessage = camr.resolveReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } else if (reportCurrentStatus.equals("Resolved")) {
            //camr.unresolveReview(reportStatusUpdate);
            responseMessage = camr.unresolveReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        }

        return reportUpdateStatus;
    }

    private boolean resolveOnlyReviewReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
       
       try {
            responseMessage = camr.resolveOnlyReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only review report. ");
        }

        return reportUpdateStatus;
    }
    
    private boolean resolveDeleteReviewReport(HttpServletRequest request) {
        boolean reportUpdateStatus = false;

        String reportStatusUpdate = request.getParameter("reportID");
       
       try {
            responseMessage = camr.resolveDeleteReview(reportStatusUpdate);
            if (responseMessage.endsWith("!")) {
                request.setAttribute("successMessage", responseMessage);
            } else {
                request.setAttribute("errorMessage", responseMessage);
            }
            reportUpdateStatus = true;
        } catch (Exception ex) {
            System.out.println("ERROR: Error resolving only review report. ");
        }

        return reportUpdateStatus;
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
    public String getServletInfo() {
        return "Content Admin Servlet";
    }
}
