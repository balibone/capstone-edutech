/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechsessionbeans.CommonRESTMgrBean;
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

    @PersistenceContext(unitName = "EduTechWebApp-warPU")
    private EntityManager em;
    @EJB
    CommonRESTMgrBean etr;
    
    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ScheduleItemEntity entity) {
        etr.createScheduleItem(entity);
    }

    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, ScheduleItemEntity entity) {
        etr.editScheduleItem(id, entity);
    }

    @DELETE @Path("{id}")
    public void remove(@PathParam("id") String id) {
        etr.removeScheduleItem(id);
    }

    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ScheduleItemEntity find(@PathParam("id") String id) {
        return etr.findScheduleItem(id);
    }
    
    @GET @Path("user/{username}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> findUser(@PathParam("username") String username) {
        return etr.findUserScheduleItems(username);
    }
    
    @GET @Path("group/{groupId}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> findGroup(@PathParam("groupId") int groupId) {
        return etr.findGroupMeetings(groupId);
    }

    @GET @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> findAll() {
        return etr.findAllScheduleItems();
    }

    @GET @Path("count") @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return etr.countScheduleItems();
    }
    
}
