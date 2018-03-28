/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import edutechentities.module.SubmissionEntity;
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

@RequestScoped
@Path("submission")
public class SubmissionREST {
    
    @EJB
    CommonRESTMgrBean cmb;
    @EJB
    GroupRESTMgrBean etr;
    @EJB
    ModuleRESTMgrBean mmb;
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SubmissionEntity> getAllSubmissions() {
        return mmb.getAllSubmissions();
    }
    
    @GET 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SubmissionEntity getOneSubmission(@PathParam("id") String id){
        return mmb.getOneSubmission(Long.valueOf(id));
    }
    
    @POST 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SubmissionEntity createSubmission(SubmissionEntity sub) {
        return mmb.createSubmission(sub);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SubmissionEntity> deleteSubmission(@PathParam("id") String id) {
        mmb.deleteSubmission(Long.valueOf(id));
        return mmb.getAllSubmissions();
    }
    
    @PUT 
    @Path("{id}") 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SubmissionEntity editSubmission(@PathParam("id") String id, SubmissionEntity replacement){
        return mmb.editSubmission(Long.valueOf(id), replacement);
    }

}
