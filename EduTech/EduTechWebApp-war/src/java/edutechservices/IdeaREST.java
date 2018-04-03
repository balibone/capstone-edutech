/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import commoninfraentities.UserEntity;
import edutechentities.group.GroupEntity;
import edutechentities.group.IdeaEntity;
import edutechsessionbeans.CommonMgrBean;
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
@Path("idea")
public class IdeaREST {
    
    @EJB
    CommonMgrBean cmb;
    
    @GET 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<IdeaEntity> getAllIdeas() {
        return cmb.getAllIdeas();
    }
    
    @GET 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public IdeaEntity getOneIdea(@PathParam("id") String id){
        return cmb.getOneIdea(Long.valueOf(id));
    }
    
    
    @POST 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public IdeaEntity createIdea(IdeaEntity idea) {
        return cmb.createIdea(idea);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<IdeaEntity> deleteIdea(@PathParam("id") String id) {
        cmb.deleteIdea(id);
        return cmb.getAllIdeas();
    }
    
    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    public IdeaEntity editIdea(@PathParam("id") String id, IdeaEntity replacement) {
        return cmb.editIdea(id, replacement);
    }

    
}
