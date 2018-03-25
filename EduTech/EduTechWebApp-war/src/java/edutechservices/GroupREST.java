/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import commoninfraentities.UserEntity;
import edutechentities.group.GroupEntity;
import edutechsessionbeans.GroupMgrBean;
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
 * @author nanda88
 */
@RequestScoped
@Path("group")
public class GroupREST {
    
    @EJB
    GroupMgrBean etr;
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GroupEntity> getAllGroups() {
        return etr.getAllGroups();
    }
    
    @POST 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public GroupEntity createGroup(GroupEntity group) {
        return etr.createGroup(group);
    }
    
    @POST 
    @Path("group/multiple")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public GroupEntity createAssignmentGroups(GroupEntity group) {
        return etr.createGroup(group);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GroupEntity> deleteGroup(@PathParam("id") String id) {
        etr.deleteGroup(id);
        return etr.getAllGroups();
    }
    
    @PUT 
    @Path("{id}") 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, GroupEntity entity) {
        etr.editGroup(id, entity);
    }
    
    @GET 
    @Path("user/{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<GroupEntity> getUserGroups(@PathParam("id") String id) {
        return etr.findUserGroups(id);
    }
    
    @GET 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public GroupEntity getGroupDetails(@PathParam("id") String id) {
        return etr.findGroup(id);
    }
    
    @GET 
    @Path("members/{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserEntity> getGroupMembers(@PathParam("id") String id) {
        return etr.findGroupMembers(id);
    }
    


    
}
