/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.common.PostEntity;
import edutechentities.common.ScheduleItemEntity;
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
@Path("edutechentities.common.post")
public class PostREST {
    @PersistenceContext(unitName = "EduTechWebApp-warPU")
    private EntityManager em;
    @EJB
    CommonRESTMgrBean etr;
    
    
    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PostEntity> getPosts(@PathParam("id") String pageId) {
        return etr.getPosts(pageId);
    }
    
    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PostEntity entity) {
        etr.createPost(entity);
    }
    
    @DELETE @Path("{id}")
    public void remove(@PathParam("id") String id) {
        etr.removePost(id);
    }
    
    @PUT @Path("pin/{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void pin(@PathParam("id") String id) {
        etr.togglePinPost(id);
    }
    
    @POST @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void reply(@PathParam("id") String id, PostEntity entity) {
        etr.replyPost(id, entity);
    }

    
}
