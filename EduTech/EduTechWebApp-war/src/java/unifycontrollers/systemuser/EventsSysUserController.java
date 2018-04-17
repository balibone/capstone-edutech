package unifycontrollers.systemuser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import unifysessionbeans.systemuser.ShoutsSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.EventsSysUserMgrBeanRemote;
import unifysessionbeans.systemuser.UserProfileSysUserMgrBeanRemote;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class EventsSysUserController extends HttpServlet {

    @EJB
    private ShoutsSysUserMgrBeanRemote ssmr;
    @EJB
    private UserProfileSysUserMgrBeanRemote usmr;
    @EJB
    private EventsSysUserMgrBeanRemote esmr;

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
                case "goToViewEventsListingSYS":
                    request.setAttribute("eventsListSYS", (ArrayList) esmr.viewEventsList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewEventsListingSYS";
                    break;
                case "goToViewMyEventsListingSYS":
                    request.setAttribute("eventsListSYS", (ArrayList) esmr.viewMyEventsList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewMyEventsListingSYS";
                    break;
                case "goToViewMyRsvpEventsListingSYS":
                    request.setAttribute("eventsListSYS", (ArrayList) esmr.viewMyRsvpEventsList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewMyRsvpEventsListingSYS";
                    break;
                case "goToNewEventModalSYS":
                    pageAction = "NewEventModalSYS";
                    break;
                case "goToNewEventSYS":
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "NewEventSYS";
                    break;
                case "goToCreateEvent":
                    responseMessage = createEvent(loggedInUsername, request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventsListSYS", (ArrayList) esmr.viewEventsList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewEventsListingSYS";
                    break;
                case "goToViewEventDetailsSYS":
                    String eventDetailsEventID = request.getParameter("hiddenEventID");
                    request.setAttribute("eventDetailsVec", esmr.viewEventDetails(loggedInUsername, eventDetailsEventID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewEventDetailsSYS";
                    break;
                case "goToRsvpToEventSYS":
                    String rsvpEventID = request.getParameter("hiddenEventID");

                    responseMessage = rsvpEvent(loggedInUsername, rsvpEventID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventDetailsVec", esmr.viewEventDetails(loggedInUsername, rsvpEventID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewEventDetailsSYS";
                    break;
                case "goToRemoveRsvpToEventSYS":
                    String removeRsvpEventID = request.getParameter("hiddenEventID");

                    responseMessage = removeRsvpEvent(loggedInUsername, removeRsvpEventID);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventDetailsVec", esmr.viewEventDetails(loggedInUsername, removeRsvpEventID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewEventDetailsSYS";
                    break;
                case "goToReportEventSubmit":
                    responseMessage = createEventReport(loggedInUsername, request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    String eventIDAfterReport = request.getParameter("hiddenEventID");
                    request.setAttribute("eventDetailsVec", esmr.viewEventDetails(loggedInUsername, eventIDAfterReport));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewEventDetailsSYS";
                    break;
                case "goToDeleteEventSYS":
                    responseMessage = deleteEvent(loggedInUsername, request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventsListSYS", (ArrayList) esmr.viewMyEventsList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewMyEventsListingSYS";
                    break;
                case "goToDeleteEventRequestSYS":
                    responseMessage = deleteEventRequest(loggedInUsername, request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventsRequestListSYS", (ArrayList) esmr.viewMyEventsRequestList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewMyRequestedEventsListingSYS";
                    break;
                case "goToViewMyRequestedEventsListingSYS":
                    request.setAttribute("eventsRequestListSYS", (ArrayList) esmr.viewMyEventsRequestList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewMyRequestedEventsListingSYS";
                    break;
                case "goToViewRequestedEventDetailsSYS":
                    String requestedEventDetailsEventID = request.getParameter("hiddenEventRequestID");
                    request.setAttribute("eventDetailsVec", esmr.viewEventRequestDetails(loggedInUsername, requestedEventDetailsEventID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewRequestedEventDetailsSYS";
                    break;
                 case "goToEditEventRequestSYS":
                    String editEventRequestID = request.getParameter("hiddenEventRequestID");
                    request.setAttribute("eventDetailsVec", esmr.viewEventRequestDetails(loggedInUsername, editEventRequestID));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "EditEventRequestSYS";
                    break;
                case "goToEditEventRequestSubmit":
                    responseMessage = editEventRequest(request);
                    if (responseMessage.endsWith("!")) {
                        request.setAttribute("successMessage", responseMessage);
                    } else {
                        request.setAttribute("errorMessage", responseMessage);
                    }
                    request.setAttribute("eventsRequestListSYS", (ArrayList) esmr.viewMyEventsRequestList(loggedInUsername));
                    request.setAttribute("userMessageListTopThreeSYS", usmr.viewUserMessageListTopThree(loggedInUsername));
                    request.setAttribute("unreadNotificationCount", usmr.getUnreadNotificationCount(loggedInUsername));
                    pageAction = "ViewMyRequestedEventsListingSYS";
                    break;

                default:
                    break;
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            log("Exception in EventsSysUserController: processRequest()");
            ex.printStackTrace();
        }

    }

    private String createEvent(String username, HttpServletRequest request) {
        String fileName = "";
        try {
            Part filePart = request.getPart("eventPoster");
            fileName = (String) getFileName(filePart);

            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "events"
                    + File.separator ;
            Files.createDirectories(Paths.get(imageDir));
            
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
        
        String eventTitle = request.getParameter("eventTitle");
        String eventDesc = request.getParameter("eventDesc");
        String eventVenue = request.getParameter("eventVenue");
        String eventStartDateTime = request.getParameter("eventStartDateTime");
        String eventEndDateTime = request.getParameter("eventEndDateTime");
        
        responseMessage = esmr.createEvent(username, eventTitle, eventDesc, 
                eventVenue, eventStartDateTime, eventEndDateTime, fileName);
       
        return responseMessage;
    }
    
    private String rsvpEvent(String username, String eventID) {

        responseMessage = esmr.createRsvp(username, eventID);

        return responseMessage;
    }
    
    private String removeRsvpEvent(String username, String eventID) {

        responseMessage = esmr.removeRsvp(username, eventID);

        return responseMessage;
    }
    
    private String createEventReport(String username, HttpServletRequest request) {
        String eventID = request.getParameter("hiddenEventID");
        String eventReportContent = request.getParameter("eventReportContent");

        responseMessage = esmr.createEventReport(username, eventID, eventReportContent);

        return responseMessage;
    }
    
    private String deleteEvent(String username, HttpServletRequest request) {

        String eventID = request.getParameter("hiddenEventID");
       
        responseMessage = esmr.deleteEvent(username, eventID);

        return responseMessage;
    }
    
    private String deleteEventRequest(String username, HttpServletRequest request) {

        String eventRequestID = request.getParameter("hiddenEventRequestID");
       
        responseMessage = esmr.deleteEventRequest(username, eventRequestID);

        return responseMessage;
    }

    private String editEventRequest(HttpServletRequest request) {
        String fileName = "";
        String imageUploadedCheck = request.getParameter("imageUploadedCheck");
        if(imageUploadedCheck.equals("Uploaded")){
        try {
            Part filePart = request.getPart("eventPoster");
            fileName = (String) getFileName(filePart);

            String appPath = request.getServletContext().getRealPath("");
            String truncatedAppPath = appPath.replace("dist" + File.separator + "gfdeploy" + File.separator
                    + "EduTech" + File.separator + "EduTechWebApp-war_war", "");
            String imageDir = truncatedAppPath + "EduTechWebApp-war" + File.separator + "web" + File.separator
                    + "uploads" + File.separator + "unify" + File.separator + "images" + File.separator + "events"
                    + File.separator ;
            Files.createDirectories(Paths.get(imageDir));
            
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
        } else {
            fileName = request.getParameter("hiddenEventPoster");
        }
        
        String eventRequestID = request.getParameter("eventRequestID");
        String eventRequestTitle = request.getParameter("eventTitle");
        String eventRequestDesc = request.getParameter("eventDesc");
        String eventRequestVenue = request.getParameter("eventVenue");
        String eventRequestStartDateTime = request.getParameter("eventStartDateTime");
        String eventRequestEndDateTime = request.getParameter("eventEndDateTime");
        
        responseMessage = esmr.editEventRequest(eventRequestID, eventRequestTitle, 
                eventRequestDesc, eventRequestVenue, eventRequestStartDateTime, 
                eventRequestEndDateTime, fileName);

        return responseMessage;
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
        return "Events System User Servlet";
    }
}
