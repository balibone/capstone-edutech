package unifysessionbeans.systemuser;

import commoninfrastructureentities.UserEntity;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import commoninfrastructureentities.UserEntity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

import unifyentities.event.EventEntity;
import unifyentities.common.EventRequestEntity;
import unifyentities.event.EventRSVPEntity;
import unifyentities.event.EventReportEntity;

@Stateless
public class EventsSysUserMgrBean implements EventsSysUserMgrBeanRemote {

    @PersistenceContext
    private EntityManager em;

    private EventEntity eventEntity;
    private EventRequestEntity eventRequestEntity;
    private EventRSVPEntity eventRsvpEntity;
    private EventReportEntity eventReportEntity;

    private UserEntity userEntity;

    @Override
    public List<Vector> viewEventsList(String username) {
        Query q = em.createQuery("SELECT c FROM Event c WHERE c.eventStatus='Active' AND c.eventEndDateTime > CURRENT_TIMESTAMP ORDER BY c.eventStartDateTime DESC");
        List<Vector> eventList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy h:mm aaa");
        DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            EventEntity eventE = (EventEntity) o;

            Vector eventVec = new Vector();

            eventVec.add(eventE.getEventID());
            eventVec.add(eventE.getEventStatus());
            eventVec.add(eventE.getEventDescription());
            eventVec.add(df.format(eventE.getEventStartDateTime()));
            eventVec.add(df.format(eventE.getEventEndDateTime()));
            eventVec.add(eventE.getEventVenue());
            eventVec.add(df.format(eventE.getEventApprovalDate()));
            eventVec.add(eventE.getUserEntity().getUsername());

            //find listing posted time from current time
            if (eventE.getEventStartDateTime().getTime() - currentDate.getTime() < 0) {
                long diff = eventE.getEventEndDateTime().getTime() - currentDate.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays != 0) {
                    //dateString = diffDays + " day";
                    if (diffDays == 1) {
                        dateString = "ending in " + diffDays + " day";
                    } else {
                        dateString = "ending in " + diffDays + " days";
                    }
                } else if (diffHours != 0) {
                    //dateString = diffHours + " hour";
                    if (diffHours == 1) {
                        dateString = "ending in " + diffHours + " hour";
                    } else {
                        dateString = "ending in " + diffHours + " hours";
                    }
                } else if (diffMinutes != 0) {
                    //dateString = diffMinutes + " minute";
                    if (diffMinutes == 1) {
                        dateString = "ending in " + diffMinutes + " minute";
                    } else {
                        dateString = "ending in " + diffMinutes + " minutes";
                    }
                } else if (diffSeconds != 0) {
                    //dateString = diffSeconds + " second";
                    if (diffSeconds == 1) {
                        dateString = "ending in " + diffSeconds + " second";
                    } else {
                        dateString = "ending in " + diffSeconds + " seconds";
                    }
                }
            } else {
                long diff = eventE.getEventStartDateTime().getTime() - currentDate.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays != 0) {
                    //dateString = diffDays + " day";
                    if (diffDays == 1) {
                        dateString = "starting in " + diffDays + " day";
                    } else {
                        dateString = "starting in " + diffDays + " days";
                    }
                } else if (diffHours != 0) {
                    //dateString = diffHours + " hour";
                    if (diffHours == 1) {
                        dateString = "starting in " + diffHours + " hour";
                    } else {
                        dateString = "starting in " + diffHours + " hours";
                    }
                } else if (diffMinutes != 0) {
                    //dateString = diffMinutes + " minute";
                    if (diffMinutes == 1) {
                        dateString = "starting in " + diffMinutes + " minute";
                    } else {
                        dateString = "starting in " + diffMinutes + " minutes";
                    }
                } else if (diffSeconds != 0) {
                    //dateString = diffSeconds + " second";
                    if (diffSeconds == 1) {
                        dateString = "starting in " + diffSeconds + " second";
                    } else {
                        dateString = "starting in " + diffSeconds + " seconds";
                    }
                }
            }

            eventVec.add(dateString);
            eventVec.add(eventE.getEventTitle());
            eventVec.add(getEventRsvpCount(eventE.getEventID()));
            eventVec.add(df2.format(eventE.getEventStartDateTime()));

            eventList.add(eventVec);
        }
        System.out.println("ViewEventsList retrieved");
        return eventList;
    }

    @Override
    public List<Vector> viewMyEventsList(String username) {
        Query q = em.createQuery("SELECT c FROM Event c WHERE c.eventStatus='Active' AND c.userEntity.username = :username ORDER BY c.eventStartDateTime DESC");
        q.setParameter("username", username);
        List<Vector> eventList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy h:mm aaa");
        DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            EventEntity eventE = (EventEntity) o;

            Vector eventVec = new Vector();

            eventVec.add(eventE.getEventID());
            eventVec.add(eventE.getEventStatus());
            eventVec.add(eventE.getEventDescription());
            eventVec.add(df.format(eventE.getEventStartDateTime()));
            eventVec.add(df.format(eventE.getEventEndDateTime()));
            eventVec.add(eventE.getEventVenue());
            eventVec.add(df.format(eventE.getEventApprovalDate()));
            eventVec.add(eventE.getUserEntity().getUsername());

            //find listing posted time from current time
            if (eventE.getEventStartDateTime().getTime() - currentDate.getTime() < 0) {
                if (eventE.getEventEndDateTime().getTime() - currentDate.getTime() > 0) {
                    long diff = eventE.getEventEndDateTime().getTime() - currentDate.getTime();
                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    if (diffDays != 0) {
                        //dateString = diffDays + " day";
                        if (diffDays == 1) {
                            dateString = "ending in " + diffDays + " day";
                        } else {
                            dateString = "ending in " + diffDays + " days";
                        }
                    } else if (diffHours != 0) {
                        //dateString = diffHours + " hour";
                        if (diffHours == 1) {
                            dateString = "ending in " + diffHours + " hour";
                        } else {
                            dateString = "ending in " + diffHours + " hours";
                        }
                    } else if (diffMinutes != 0) {
                        //dateString = diffMinutes + " minute";
                        if (diffMinutes == 1) {
                            dateString = "ending in " + diffMinutes + " minute";
                        } else {
                            dateString = "ending in " + diffMinutes + " minutes";
                        }
                    } else if (diffSeconds != 0) {
                        //dateString = diffSeconds + " second";
                        if (diffSeconds == 1) {
                            dateString = "ending in " + diffSeconds + " second";
                        } else {
                            dateString = "ending in " + diffSeconds + " seconds";
                        }
                    }
                } else {
                    long diff = currentDate.getTime() - eventE.getEventEndDateTime().getTime();
                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    if (diffDays != 0) {
                        //dateString = diffDays + " day";
                        if (diffDays == 1) {
                            dateString = "ended " + diffDays + " day ago";
                        } else {
                            dateString = "ended " + diffDays + " days ago";
                        }
                    } else if (diffHours != 0) {
                        //dateString = diffHours + " hour";
                        if (diffHours == 1) {
                            dateString = "ended " + diffHours + " hour ago";
                        } else {
                            dateString = "ended " + diffHours + " hours ago";
                        }
                    } else if (diffMinutes != 0) {
                        //dateString = diffMinutes + " minute";
                        if (diffMinutes == 1) {
                            dateString = "ended " + diffMinutes + " minute ago";
                        } else {
                            dateString = "ended " + diffMinutes + " minutes ago";
                        }
                    } else if (diffSeconds != 0) {
                        //dateString = diffSeconds + " second";
                        if (diffSeconds == 1) {
                            dateString = "ended " + diffSeconds + " second ago";
                        } else {
                            dateString = "ended " + diffSeconds + " seconds ago";
                        }
                    }
                }
            } else {
                long diff = eventE.getEventStartDateTime().getTime() - currentDate.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays != 0) {
                    //dateString = diffDays + " day";
                    if (diffDays == 1) {
                        dateString = "starting in " + diffDays + " day";
                    } else {
                        dateString = "starting in " + diffDays + " days";
                    }
                } else if (diffHours != 0) {
                    //dateString = diffHours + " hour";
                    if (diffHours == 1) {
                        dateString = "starting in " + diffHours + " hour";
                    } else {
                        dateString = "starting in " + diffHours + " hours";
                    }
                } else if (diffMinutes != 0) {
                    //dateString = diffMinutes + " minute";
                    if (diffMinutes == 1) {
                        dateString = "starting in " + diffMinutes + " minute";
                    } else {
                        dateString = "starting in " + diffMinutes + " minutes";
                    }
                } else if (diffSeconds != 0) {
                    //dateString = diffSeconds + " second";
                    if (diffSeconds == 1) {
                        dateString = "starting in " + diffSeconds + " second";
                    } else {
                        dateString = "starting in " + diffSeconds + " seconds";
                    }
                }
            }
            eventVec.add(dateString);
            eventVec.add(eventE.getEventTitle());
            eventVec.add(getEventRsvpCount(eventE.getEventID()));
            eventVec.add(df2.format(eventE.getEventStartDateTime()));

            eventList.add(eventVec);
        }
        System.out.println("ViewEventsList retrieved");
        return eventList;
    }

    @Override
    public List<Vector> viewMyRsvpEventsList(String username) {
        Query q = em.createQuery("SELECT c FROM EventRSVP c WHERE c.userEntity.username = :username ORDER BY c.eventEntity.eventStartDateTime DESC");
        q.setParameter("username", username);
        List<Vector> eventList = new ArrayList<Vector>();

        Date currentDate = new Date();
        String dateString = "";

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy h:mm aaa");
        DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            EventRSVPEntity eventR = (EventRSVPEntity) o;

            Vector eventVec = new Vector();

            eventVec.add(eventR.getEventEntity().getEventID());
            eventVec.add(eventR.getEventEntity().getEventStatus());
            eventVec.add(eventR.getEventEntity().getEventDescription());
            eventVec.add(df.format(eventR.getEventEntity().getEventStartDateTime()));
            eventVec.add(df.format(eventR.getEventEntity().getEventEndDateTime()));
            eventVec.add(eventR.getEventEntity().getEventVenue());
            eventVec.add(df.format(eventR.getEventEntity().getEventApprovalDate()));
            eventVec.add(eventR.getEventEntity().getUserEntity().getUsername());

            //find listing posted time from current time
            if (eventR.getEventEntity().getEventStartDateTime().getTime() - currentDate.getTime() < 0) {
                if (eventR.getEventEntity().getEventEndDateTime().getTime() - currentDate.getTime() > 0) {
                    long diff = eventR.getEventEntity().getEventEndDateTime().getTime() - currentDate.getTime();
                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    if (diffDays != 0) {
                        //dateString = diffDays + " day";
                        if (diffDays == 1) {
                            dateString = "ending in " + diffDays + " day";
                        } else {
                            dateString = "ending in " + diffDays + " days";
                        }
                    } else if (diffHours != 0) {
                        //dateString = diffHours + " hour";
                        if (diffHours == 1) {
                            dateString = "ending in " + diffHours + " hour";
                        } else {
                            dateString = "ending in " + diffHours + " hours";
                        }
                    } else if (diffMinutes != 0) {
                        //dateString = diffMinutes + " minute";
                        if (diffMinutes == 1) {
                            dateString = "ending in " + diffMinutes + " minute";
                        } else {
                            dateString = "ending in " + diffMinutes + " minutes";
                        }
                    } else if (diffSeconds != 0) {
                        //dateString = diffSeconds + " second";
                        if (diffSeconds == 1) {
                            dateString = "ending in " + diffSeconds + " second";
                        } else {
                            dateString = "ending in " + diffSeconds + " seconds";
                        }
                    }
                } else {
                    long diff = currentDate.getTime() - eventR.getEventEntity().getEventEndDateTime().getTime();
                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    if (diffDays != 0) {
                        //dateString = diffDays + " day";
                        if (diffDays == 1) {
                            dateString = "ended " + diffDays + " day ago";
                        } else {
                            dateString = "ended " + diffDays + " days ago";
                        }
                    } else if (diffHours != 0) {
                        //dateString = diffHours + " hour";
                        if (diffHours == 1) {
                            dateString = "ended " + diffHours + " hour ago";
                        } else {
                            dateString = "ended " + diffHours + " hours ago";
                        }
                    } else if (diffMinutes != 0) {
                        //dateString = diffMinutes + " minute";
                        if (diffMinutes == 1) {
                            dateString = "ended " + diffMinutes + " minute ago";
                        } else {
                            dateString = "ended " + diffMinutes + " minutes ago";
                        }
                    } else if (diffSeconds != 0) {
                        //dateString = diffSeconds + " second";
                        if (diffSeconds == 1) {
                            dateString = "ended " + diffSeconds + " second ago";
                        } else {
                            dateString = "ended " + diffSeconds + " seconds ago";
                        }
                    }
                }
            } else {
                long diff = eventR.getEventEntity().getEventStartDateTime().getTime() - currentDate.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays != 0) {
                    //dateString = diffDays + " day";
                    if (diffDays == 1) {
                        dateString = "starting in " + diffDays + " day";
                    } else {
                        dateString = "starting in " + diffDays + " days";
                    }
                } else if (diffHours != 0) {
                    //dateString = diffHours + " hour";
                    if (diffHours == 1) {
                        dateString = "starting in " + diffHours + " hour";
                    } else {
                        dateString = "starting in " + diffHours + " hours";
                    }
                } else if (diffMinutes != 0) {
                    //dateString = diffMinutes + " minute";
                    if (diffMinutes == 1) {
                        dateString = "starting in " + diffMinutes + " minute";
                    } else {
                        dateString = "starting in " + diffMinutes + " minutes";
                    }
                } else if (diffSeconds != 0) {
                    //dateString = diffSeconds + " second";
                    if (diffSeconds == 1) {
                        dateString = "starting in " + diffSeconds + " second";
                    } else {
                        dateString = "starting in " + diffSeconds + " seconds";
                    }
                }
            }
            eventVec.add(dateString);
            eventVec.add(eventR.getEventEntity().getEventTitle());
            eventVec.add(getEventRsvpCount(eventR.getEventEntity().getEventID()));
            //check for rsvp status
            eventVec.add(checkEventRsvp(username, eventR.getEventEntity().getEventID()));
            eventVec.add(df2.format(eventR.getEventEntity().getEventStartDateTime()));

            eventList.add(eventVec);
        }
        
        return eventList;
    }
    
    @Override
    public List<Vector> viewMyEventsRequestList(String username) {
        Query q = em.createQuery("SELECT c FROM EventRequest c WHERE c.userEntity.username = :username ORDER BY c.eventRequestDate DESC");
        q.setParameter("username", username);
        List<Vector> eventRequestList = new ArrayList<Vector>();

        Date currentDate = new Date();
        //String dateString = "";

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy h:mm aaa");
        DateFormat df2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        for (Object o : q.getResultList()) {
            EventRequestEntity eventRequestE = (EventRequestEntity) o;

            Vector eventRequestVec = new Vector();

            eventRequestVec.add(eventRequestE.getEventRequestID());
            eventRequestVec.add(eventRequestE.getEventRequestStatus());
            eventRequestVec.add(eventRequestE.getEventRequestTitle());
            eventRequestVec.add(eventRequestE.getEventRequestDescription());
            eventRequestVec.add(df.format(eventRequestE.getEventRequestStartDateTime()));
            eventRequestVec.add(df.format(eventRequestE.getEventRequestEndDateTime()));
            eventRequestVec.add(eventRequestE.getEventRequestVenue());
            eventRequestVec.add(df.format(eventRequestE.getEventRequestDate()));
            //eventRequestVec.add(df.format(eventRequestE.getEventReviewedDate()));
            //eventRequestVec.add(eventRequestE.getUserEntity().getUsername());
            
            eventRequestVec.add(df2.format(eventRequestE.getEventRequestDate()));

            eventRequestList.add(eventRequestVec);
        }
        System.out.println("ViewEventsList retrieved");
        return eventRequestList;
    }

    @Override
    public Vector viewEventDetails(String username, String eventID) {
        Long eventIDLong = Long.parseLong(eventID);
        Query q = em.createQuery("SELECT c FROM Event c WHERE c.eventStatus='Active' AND c.eventID = :eventID");
        q.setParameter("eventID", eventIDLong);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy h:mm aaa");
        Date currentDate = new Date();

        Vector eventVec = new Vector();

        for (Object o : q.getResultList()) {
            EventEntity eventE = (EventEntity) o;

            eventVec.add(eventE.getEventID());
            eventVec.add(eventE.getEventStatus());
            eventVec.add(eventE.getEventDescription());
            eventVec.add(df.format(eventE.getEventStartDateTime()));
            eventVec.add(df.format(eventE.getEventEndDateTime()));
            eventVec.add(eventE.getEventVenue());
            eventVec.add(df.format(eventE.getEventApprovalDate()));
            eventVec.add(eventE.getUserEntity().getUsername());

            eventVec.add(eventE.getEventTitle());
            eventVec.add(getEventRsvpCount(eventE.getEventID()));

            //check for rsvp status
            eventVec.add(checkEventRsvp(username, eventE.getEventID()));

            if (eventE.getEventStartDateTime().getTime() - currentDate.getTime() < 0) {
                if (eventE.getEventEndDateTime().getTime() - currentDate.getTime() > 0) {
                    eventVec.add("Ongoing");
                }
                eventVec.add("Ended");
            } else {
                eventVec.add("Upcoming");
            }
            
            eventVec.add(eventE.getEventPoster());

        }
        System.out.println("ViewEventsList retrieved");
        return eventVec;

    }

    @Override
    public Vector viewEventRequestDetails(String username, String eventRequestID) {
        Long eventRequestIDLong = Long.parseLong(eventRequestID);
        Query q = em.createQuery("SELECT c FROM EventRequest c WHERE c.eventRequestID = :eventRequestID");
        q.setParameter("eventRequestID", eventRequestIDLong);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy h:mm aaa");
        //Date currentDate = new Date();

        Vector eventRequestVec = new Vector();

        for (Object o : q.getResultList()) {
            EventRequestEntity eventRequestE = (EventRequestEntity) o;

            eventRequestVec.add(eventRequestE.getEventRequestID());
            eventRequestVec.add(eventRequestE.getEventRequestStatus());
            eventRequestVec.add(eventRequestE.getEventRequestTitle());
            eventRequestVec.add(eventRequestE.getEventRequestDescription());
            eventRequestVec.add(df.format(eventRequestE.getEventRequestStartDateTime()));
            eventRequestVec.add(df.format(eventRequestE.getEventRequestEndDateTime()));
            eventRequestVec.add(eventRequestE.getEventRequestVenue());
            eventRequestVec.add(df.format(eventRequestE.getEventRequestDate()));
            if (eventRequestE.getEventReviewedDate() == null) {
                eventRequestVec.add("To Be Reviewed");
            } else {
                eventRequestVec.add(df.format(eventRequestE.getEventReviewedDate()));
            }

            eventRequestVec.add(eventRequestE.getUserEntity().getUsername());

            //check for event ID
            if(eventRequestE.getEventRequestStatus().equals("Approved")){
                eventRequestVec.add(eventRequestE.getEventEntity().getEventID());
            }
            else{
                eventRequestVec.add("");
            }
            eventRequestVec.add(eventRequestE.getEventRequestPoster());            
        }
        
        return eventRequestVec;

    }

    @Override
    public String createEvent(String username, String eventTitle, String eventDesc,
            String eventVenue, String eventStartDateTime, String eventEndDateTime, String fileName) {

        eventRequestEntity = new EventRequestEntity();

        //System.out.println(shoutPoster);
        userEntity = lookupUnifyUser(username);
        
        if(getDateFromString(eventStartDateTime).after(getDateFromString(eventEndDateTime))){
            return "End date is earlier than start date. Please resubmit request.";
        }

        if (eventRequestEntity.createEvent(eventTitle)) {
            eventRequestEntity.setUserEntity(userEntity);

            eventRequestEntity.setEventRequestDescription(eventDesc);
            eventRequestEntity.setEventRequestVenue(eventVenue);

            eventRequestEntity.setEventRequestStartDateTime(getDateFromString(eventStartDateTime));
            eventRequestEntity.setEventRequestEndDateTime(getDateFromString(eventEndDateTime));

            eventRequestEntity.setEventRequestVenueLat("0");
            eventRequestEntity.setEventRequestVenueLong("0");
            
            eventRequestEntity.setEventRequestPoster(fileName);

            em.persist(eventRequestEntity);

            return "Event has been submited for approval successfully!";
        } else {
            return "System is not feeling well and cannot submit your event request at the moment :( Please try again later.";
        }

    }

    @Override
    public String createRsvp(String username, String eventID) {

        eventRsvpEntity = new EventRSVPEntity();

        userEntity = lookupUnifyUser(username);

        eventEntity = lookupEvent(eventID);

        Long eventIDLong = Long.parseLong(eventID);

        if (lookupEventRsvpUser(username, eventIDLong) != null) {
            return "Event already RSVP";
        } else {
            eventRsvpEntity.addRSVP(userEntity, eventEntity);

            em.persist(eventRsvpEntity);

            return "RSVP to event successfully!";
        }

    }

    @Override
    public String removeRsvp(String username, String eventID) {

        eventRsvpEntity = new EventRSVPEntity();

        userEntity = lookupUnifyUser(username);

        eventEntity = lookupEvent(eventID);

        Long eventIDLong = Long.parseLong(eventID);

        if (lookupEventRsvpUser(username, eventIDLong) != null) {
            //unlike
            eventRsvpEntity = lookupEventRsvpUser(username, eventIDLong);

            UserEntity userE = eventRsvpEntity.getUserEntity();

            EventEntity eventE = eventRsvpEntity.getEventEntity();

            eventE.getEventRsvpSet().remove(eventRsvpEntity);
            userE.getEventRsvpSet().remove(eventRsvpEntity);

            em.merge(eventE);
            em.merge(userE);

            em.remove(eventRsvpEntity);
            em.flush();
            em.clear();
            return "RSVP to event removed successfully!";
        } else {

            return "Error removing RSVP to event. Please try again later.";
        }

    }

    @Override
    public String createEventReport(String username, String eventID, String reportContent) {

        eventReportEntity = new EventReportEntity();

        userEntity = lookupUnifyUser(username);

        eventEntity = lookupEvent(eventID);

        //Long eventIDLong = Long.parseLong(eventID);
        if (eventReportEntity.addNewReport(reportContent)) {
            eventReportEntity.setUserEntity(userEntity);
            eventReportEntity.setEventEntity(eventEntity);

            em.persist(eventReportEntity);
            return "Event has been reported successfully!";
        } else {
            return "System is not feeling well and cannot report this event at the moment :'(' Please try again later.";
        }

    }

    @Override
    public String deleteEvent(String username, String eventID) {

        if (lookupEvent(eventID) == null) {
            return "There are some issues with deleting this event. Please try again.";
        } else {
            eventEntity = lookupEvent(eventID);

            UserEntity userE = eventEntity.getUserEntity();

            em.merge(userE);

            em.remove(eventEntity);
            em.flush();
            em.clear();
            return "Event has been deleted successfully!";
        }
    }
    
    @Override
    public String deleteEventRequest(String username, String eventRequestID) {

        if (lookupEventRequest(eventRequestID) == null) {
            return "There are some issues with deleting this request. Please try again.";
        } else {
            eventRequestEntity = lookupEventRequest(eventRequestID);

            UserEntity userE = eventRequestEntity.getUserEntity();

            em.merge(userE);

            em.remove(eventRequestEntity);
            em.flush();
            em.clear();
            return "Event request has been deleted successfully!";
        }
    }
    
    @Override
    public String editEventRequest(String eventRequestID, String eventRequestTitle, String eventRequestDesc,
            String eventRequestVenue, String eventRequestStartDateTime, String eventRequestEndDateTime, String fileName) {

        if(getDateFromString(eventRequestStartDateTime).after(getDateFromString(eventRequestEndDateTime))){
            return "End date is earlier than start date. Please resubmit request.";
        }
        
        if (lookupEventRequest(eventRequestID) == null) {
            
            return "System is not feeling well and cannot edit your request at the moment :( Please try again later.";
            
        } else {
            eventRequestEntity = lookupEventRequest(eventRequestID);
            eventRequestEntity.setEventRequestTitle(eventRequestTitle);
            eventRequestEntity.setEventRequestDescription(eventRequestDesc);
            eventRequestEntity.setEventRequestVenue(eventRequestVenue);
            eventRequestEntity.setEventRequestStartDateTime(getDateFromString(eventRequestStartDateTime));
            eventRequestEntity.setEventRequestEndDateTime(getDateFromString(eventRequestEndDateTime));
            eventRequestEntity.setEventRequestPoster(fileName);
            
            em.merge(eventRequestEntity);
            
            return "Request for event: " + eventRequestTitle + " updated successfully!";
        }

    }

    public Long getEventRsvpCount(long eventID) {
        Long eventRsvpCount = new Long(0);
        Query q = em.createQuery("SELECT COUNT(l.rsvpID) FROM EventRSVP l WHERE l.eventEntity.eventID = :eventID");
        q.setParameter("eventID", eventID);
        try {
            eventRsvpCount = (Long) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("Exception in EventsSysUserMgrBean.getEventRsvpCount().getSingleResult()");
            ex.printStackTrace();
        }
        return eventRsvpCount;
    }

    public Date getDateFromString(String dateString) {
        String dateStringReplaced = dateString.replaceAll("T", " ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(dateStringReplaced);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date(parsed.getTime());
        return date;
    }

    public UserEntity lookupUnifyUser(String username) {
        UserEntity ue = new UserEntity();
        try {
            Query q = em.createQuery("SELECT u FROM SystemUser u WHERE u.username = :username");
            q.setParameter("username", username);
            ue = (UserEntity) q.getSingleResult();
            //System.out.println("FOUND USER");
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: User cannot be found. " + enfe.getMessage());
            em.remove(ue);
            ue = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: User does not exist. " + nre.getMessage());
            em.remove(ue);
            ue = null;
        }
        return ue;
    }

    public EventEntity lookupEvent(String eventID) {
        EventEntity ee = new EventEntity();
        Long eventIDLong = Long.parseLong(eventID);
        try {
            Query q = em.createQuery("SELECT u FROM Event u WHERE u.eventID = :eventID");
            q.setParameter("eventID", eventIDLong);
            ee = (EventEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Event cannot be found. " + enfe.getMessage());
            em.remove(ee);
            ee = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Event does not exist. " + nre.getMessage());
            em.remove(ee);
            ee = null;
        }
        return ee;
    }
    
    public EventRequestEntity lookupEventRequest(String eventRequestID) {
        EventRequestEntity ee = new EventRequestEntity();
        Long eventRequestIDLong = Long.parseLong(eventRequestID);
        try {
            Query q = em.createQuery("SELECT u FROM EventRequest u WHERE u.eventRequestID = :eventRequestID");
            q.setParameter("eventRequestID", eventRequestIDLong);
            ee = (EventRequestEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: Event request cannot be found. " + enfe.getMessage());
            em.remove(ee);
            ee = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: Event request does not exist. " + nre.getMessage());
            em.remove(ee);
            ee = null;
        }
        return ee;
    }

    public Boolean checkEventRsvp(String username, Long eventID) {

        eventEntity = new EventEntity();

        userEntity = lookupUnifyUser(username);

        if (lookupEventRsvpUser(username, eventID) != null) {
            return true;
        } else {
            return false;
        }

    }

    public EventRSVPEntity lookupEventRsvpUser(String username, long eventID) {
        EventRSVPEntity ere = new EventRSVPEntity();
        try {
            Query q = em.createQuery("SELECT l FROM EventRSVP l WHERE l.eventEntity.eventID = :eventID AND l.userEntity.username = :username");
            q.setParameter("eventID", eventID);
            q.setParameter("username", username);
            ere = (EventRSVPEntity) q.getSingleResult();
        } catch (EntityNotFoundException enfe) {
            System.out.println("ERROR: RSVP cannot be found. " + enfe.getMessage());
            em.remove(ere);
            ere = null;
        } catch (NoResultException nre) {
            System.out.println("ERROR: RSVP does not exist. " + nre.getMessage());
            em.remove(ere);
            ere = null;
        }
        return ere;
    }

}
