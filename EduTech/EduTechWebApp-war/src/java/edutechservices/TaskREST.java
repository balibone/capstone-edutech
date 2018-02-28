/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.common.TaskEntity;
import edutechsessionbeans.CommonRESTMgrBean;
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
 * @author tengkuhafidz
 */
@RequestScoped
@Path("task")
public class TaskREST {
    @PersistenceContext(unitName = "EduTechWebApp-warPU")
    private EntityManager em;
    @EJB
    CommonRESTMgrBean etr;
    
    @GET @Path("user/{username}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<TaskEntity> getUserTasks(@PathParam("username") String username) {
        return etr.findUserTasks(username);
    }
    
    @GET @Path("group/{groupId}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<TaskEntity> getGroupTasks(@PathParam("groupId") int groupId) {
        return etr.findGroupTasks(groupId);
    }
    
    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(TaskEntity entity) {
        etr.createTask(entity);
    }
    
    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, TaskEntity entity) {
        etr.editTask(id, entity);
    }
         
    @PUT @Path("{id}/{progressCode}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateProgress(@PathParam("id") String id, @PathParam("progressCode") int progressCode) {
        etr.updateTaskProgress(id, progressCode);
    }
    
    @DELETE @Path("{id}")
    public void remove(@PathParam("id") String id) {
        etr.removeTask(id);
    }
    
    @PUT @Path("verify/{id}/{username}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void verify(@PathParam("id") String id, @PathParam("username") String username) {
        etr.verifyTask(id, username);
    }
    
}
