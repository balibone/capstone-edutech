/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import edutechentities.module.PollOptionEntity;
import edutechsessionbeans.CommonRESTMgrBean;
import edutechsessionbeans.GroupRESTMgrBean;
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
@Path("polloption")
public class PollOptionREST {
    
    @EJB
    CommonRESTMgrBean cmb;
    @EJB
    GroupRESTMgrBean etr;
    @EJB
    ModuleRESTMgrBean mmb;
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PollOptionEntity> getAllPollOptions() {
        return mmb.getAllPollOptions();
    }
    
    @GET 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PollOptionEntity getOnePollOption(@PathParam("id") String id){
        return mmb.getOnePollOption(Long.valueOf(id));
    }
    
    @POST 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PollOptionEntity createPollOptions(PollOptionEntity mm) {
        return mmb.createPollOptions(mm);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PollOptionEntity> deletePollOption(@PathParam("id") String id) {
        mmb.deletePollOption(Long.valueOf(id));
        return mmb.getAllPollOptions();
    }
    
    @PUT 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PollOptionEntity editPollOption(@PathParam("id") String id, PollOptionEntity replacement){
        return mmb.editPollOption(Long.valueOf(id), replacement);
    }

}
