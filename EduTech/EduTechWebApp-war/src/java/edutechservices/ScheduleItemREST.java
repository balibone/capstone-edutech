/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechsessionbeans.CommonMgrBean;
import edutechentities.common.ScheduleItemEntity;
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
@Path("scheduleitem")
public class ScheduleItemREST {

    @EJB
    CommonMgrBean etr;
    
    @POST 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public ScheduleItemEntity createScheduleItem(ScheduleItemEntity entity) {
//        System.out.println(entity.getStartDate());
        return etr.createScheduleItem(entity);
    }

    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public ScheduleItemEntity editScheduleItem(@PathParam("id") String id, ScheduleItemEntity entity) {
        return etr.editScheduleItem(id, entity);
    }

    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> removeScheduleItem(@PathParam("id") String id) {
        etr.removeScheduleItem(id);
        return etr.getAllScheduleItems();
    }

    @GET 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public ScheduleItemEntity findScheduleItem(@PathParam("id") String id) {
        return etr.findScheduleItem(id);
    }
    
    @GET 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> getAllScheduleItems(@PathParam("id") String id) {
        return etr.getAllScheduleItems();
    }
    
    @GET 
    @Path("user/{username}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> findUserScheduleItems(@PathParam("username") String username) {
        return etr.findUserScheduleItems(username);
    }
    
    @GET 
    @Path("group/{groupId}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> findGroupMeetings(@PathParam("groupId") int groupId) {
        return etr.findGroupMeetings(groupId);
    }
    
    @GET 
    @Path("members/{groupId}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> findGroupScheduleItems(@PathParam("groupId") int groupId) {
        return etr.findGroupScheduleItems(groupId);
    }
    
    @GET
    @Path("module/{moduleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ScheduleItemEntity> getModuleKeyDates(@PathParam("moduleId") String moduleId){
        return etr.getModuleKeyDates(moduleId);
    }
    
}
