/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import commoninfraentities.UserEntity;
import edutechentities.group.GroupEntity;
import edutechentities.module.AnswerEntity;
import edutechsessionbeans.CommonRESTMgrBean;
import edutechsessionbeans.GroupRESTMgrBean;
import edutechsessionbeans.ModuleRESTMgrBean;
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
@Path("answer")
public class AnswerREST {
    
    @EJB
    CommonRESTMgrBean cmb;
    @EJB
    GroupRESTMgrBean etr;
    @EJB
    ModuleRESTMgrBean mmb;
    
    @GET @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AnswerEntity> getAllAnswers() {
        return mmb.getAllAnswers();
    }
    
    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AnswerEntity getOneAnswer(@PathParam("id") String id){
        return mmb.getOneAnswer(Long.valueOf(id));
    }
    
    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AnswerEntity createAnswer(AnswerEntity mm) {
        return mmb.createAnswer(mm);
    }
    
    @DELETE @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteAnswer(@PathParam("id") String id) {
        mmb.deleteAnswer(Long.valueOf(id));
    }
    
    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AnswerEntity editAnswer(@PathParam("id") String id, AnswerEntity replacement){
        return mmb.editAnswer(Long.valueOf(id), replacement);
    }

}
