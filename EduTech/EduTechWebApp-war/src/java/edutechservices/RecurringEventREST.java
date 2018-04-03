/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.common.RecurringEventEntity;
import edutechsessionbeans.CommonMgrBean;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Derian
 */
@RequestScoped
@Path("recurringevent")
public class RecurringEventREST{
    @EJB
    CommonMgrBean crmb;
    
    @POST 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public RecurringEventEntity createRecurringEvent(RecurringEventEntity entity) {
        return crmb.createRecurringEvent(entity);
    }

    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public RecurringEventEntity editRecurringEvent(@PathParam("id") Long id, RecurringEventEntity entity) {
        return crmb.editRecurringEvent(id,entity);
    }

    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<RecurringEventEntity> removeRecurringEvent(@PathParam("id") Long id) {
        crmb.removeRecurringEvent(id);
        return crmb.getAllRecurringEvents();
    }

    @GET 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public RecurringEventEntity getOneRecurringEvent(@PathParam("id") Long id) {
        return crmb.getOneRecurringEvent(id);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public List<RecurringEventEntity> getAllRecurringEvents() {
        return crmb.getAllRecurringEvents();
    }
    
}
