/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import edutechentities.module.PollEntity;
import edutechsessionbeans.CommonMgrBean;
import edutechsessionbeans.GroupMgrBean;
import edutechsessionbeans.ModuleRESTMgrBean;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
@Path("poll")
public class PollREST {
    
    @EJB
    CommonMgrBean cmb;
    @EJB
    GroupMgrBean etr;
    @EJB
    ModuleRESTMgrBean mmb;
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PollEntity> getAllPolls() {
        return mmb.getAllPolls();
    }
    
    @GET 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PollEntity getOnePoll(@PathParam("id") String id){
        return mmb.getOnePoll(Long.valueOf(id));
    }
    
    @POST 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PollEntity createPoll(PollEntity mm) {
        return mmb.createPoll(mm);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PollEntity> deletePoll(@PathParam("id") String id) {
        mmb.deletePoll(Long.valueOf(id));
        return mmb.getAllPolls();
    }
    
    @PUT 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PollEntity editPoll(@PathParam("id") String id, PollEntity replacement){
        return mmb.editPoll(Long.valueOf(id), replacement);
    }

}
