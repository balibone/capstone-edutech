/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import commoninfraentities.UserEntity;
import edutechentities.common.AnnouncementEntity;
import edutechsessionbeans.CommonMgrBean;
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
@Path("announcement")
public class AnnouncementREST {
    
    @EJB
    CommonMgrBean cmb;
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AnnouncementEntity> getAllAnnouncements() {
        return cmb.getAllAnnouncements();
    }
    
    @GET 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AnnouncementEntity getOneAnnouncement(@PathParam("id") String id){
        return cmb.getOneAnnouncement(Long.valueOf(id));
    }
    
    @GET 
    @Path("user/{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AnnouncementEntity> getUserAnnouncements(@PathParam("id") String userId) {
        return cmb.getUserAnnouncements(userId);
    }
    
    @POST 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AnnouncementEntity createAnnouncement(AnnouncementEntity ann) {
        return cmb.createAnnouncement(ann);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteAnnouncement(@PathParam("id") String id) {
        cmb.deleteAnnouncement(id);
    }
    
    @PUT 
    @Path("{id}") 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AnnouncementEntity editAnnouncement(@PathParam("id") String id, AnnouncementEntity replacement){
        return cmb.editAnnouncement(id, replacement);
    }
    
    @PUT 
    @Path("seen/{id}") 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AnnouncementEntity addUserToSeenBy(@PathParam("id") String id, UserEntity user){
        return cmb.addUserToSeenBy(id,user);
    }
    

    
}
