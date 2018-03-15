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
@Path("attachment")
public class AttachmentREST {
    
    @EJB
    CommonRESTMgrBean cmb;
    
    @GET @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AttachmentEntity> getAllAttachments() {
        return cmb.getAllAttachments();
    }
    
    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AttachmentEntity getOneAttachment(@PathParam("id") String id){
        return cmb.getOneAttachment(Long.valueOf(id));
    }
    
    
    @POST @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AttachmentEntity createAttachment(AttachmentEntity attachment) {
        return cmb.createAttachment(attachment);
    }
    
    @DELETE @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteAttachment(@PathParam("id") String id) {
        cmb.deleteAttachment(id);
    }
    
    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AttachmentEntity editAttachment(@PathParam("id") String id, AttachmentEntity replacement) {
        return cmb.editAttachment(id, replacement);
    }

    
}
