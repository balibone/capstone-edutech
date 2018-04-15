package unifysessionbeans.systemuser;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface EventsSysUserMgrBeanRemote {
    
    public List<Vector> viewEventsList(String username);
    public List<Vector> viewMyEventsList(String username);
    public List<Vector> viewMyEventsRequestList(String username);
    public List<Vector> viewMyRsvpEventsList(String username);
    public String createEvent(String username, String eventTitle, String eventDesc, 
            String eventVenue, String eventStartDateTime, String eventEndDateTime);
    public Vector viewEventDetails(String username, String eventID);
    public Vector viewEventRequestDetails(String username, String eventRequestID);
    public String createRsvp(String username, String eventID);
    public String removeRsvp(String username, String eventID);
    public String createEventReport(String username, String eventID, String reportContent);
    public String deleteEvent(String username, String eventID);
    public String deleteEventRequest(String username, String eventID);
    public String editEventRequest(String eventRequestID, String eventTitle, String eventDesc, 
            String eventVenue, String eventStartDateTime, String eventEndDateTime);
}