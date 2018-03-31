/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.common.PostEntity;
import edutechentities.common.ScheduleItemEntity;
import edutechsessionbeans.CommonMgrBean;
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
@Path("post")
public class PostREST {
    @PersistenceContext(unitName = "EduTechWebApp-warPU")
    private EntityManager em;
    @EJB
    CommonMgrBean etr;
    
    @GET  
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PostEntity> getAllPosts() {
        return etr.getAllPosts();
    }
    
    @GET  
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PostEntity getOnePost(@PathParam("id") String postId) {
        return etr.getOnePost(postId);
    }
    
    @GET 
    @Path("{pageId}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PostEntity> getPagePosts(@PathParam("pageId") String pageId) {
        return etr.findPagePosts(pageId);
    }
    
    @POST 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PostEntity createPost(PostEntity entity) {
        return etr.createPost(entity);
    }
    
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PostEntity replyPost(@PathParam("id") String id, PostEntity entity) {
        return etr.replyPost(id, entity);
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PostEntity> deletePost(@PathParam("id") String id) {
        etr.deletePost(id);
        return etr.getAllPosts();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PostEntity editPost(PostEntity post){
        return etr.editPost(post);
    }
    
    @PUT
    @Path("pin/{id}") 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PostEntity pinPost(@PathParam("id") String id) {
        return etr.togglePinPost(id);
    }
   
}
