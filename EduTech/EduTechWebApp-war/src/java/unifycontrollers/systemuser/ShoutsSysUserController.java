package unifycontrollers.systemuser;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unifysessionbeans.systemuser.ShoutsSysUserMgrBeanRemote;

public class ShoutsSysUserController extends HttpServlet {

    @EJB
    private ShoutsSysUserMgrBeanRemote ssmr;
    String responseMessage = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            String pageAction = request.getParameter("pageTransit");
            System.out.println(pageAction);

            switch (pageAction) {
                case "goToViewShoutsListingSYS":
                    request.setAttribute("shoutsListSYS", (ArrayList) ssmr.viewShoutList());
                    pageAction = "ViewShoutsListingSYS";
                    break;
                case "goToNewShoutSYS":
                    pageAction = "NewShoutSYS";
                    break;
                case "goToCreateShout":
                    System.out.println("At (ShoutsSysUserController.createShoutSYS");
                    responseMessage = createShout(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }

                    pageAction = "NewShoutSYS";
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
        return "Shouts System User Servlet";
    }
}
