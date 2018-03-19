/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import commoninfraentities.UserEntity;
import edutechentities.common.AttachmentEntity;
import edutechentities.group.GroupEntity;
import edutechentities.module.LessonEntity;
import edutechsessionbeans.CommonRESTMgrBean;
import edutechsessionbeans.GroupRESTMgrBean;
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
@Path("lesson")
public class LessonREST {
    
    @EJB
    CommonRESTMgrBean cmb;
    
    @GET 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<LessonEntity> getAllLessons() {
        return cmb.getAllLessons();
    }
    
    @GET 
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LessonEntity getOneLesson(@PathParam("id") String id){
        return cmb.getOneLesson(Long.valueOf(id));
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LessonEntity createLesson(LessonEntity lesson) {
        return cmb.createLesson(lesson);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteLesson(@PathParam("id") String id) {
        cmb.deleteLesson(id);
    }
    
    @PUT 
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public LessonEntity editLesson(@PathParam("id") String id, LessonEntity replacement) {
        return cmb.editLesson(id, replacement);
    }
    
    @POST
    @Path("attachment/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> uploadLessonAttachment(@PathParam("id") String id, AttachmentEntity att) {
        return cmb.uploadLessonAttachment(id, att);
    }
    
    @GET
    @Path("attachment/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> downloadAllLessonAttachments(@PathParam("id") String id) {
        return cmb.downloadAllLessonAttachments(id);
    }
}
