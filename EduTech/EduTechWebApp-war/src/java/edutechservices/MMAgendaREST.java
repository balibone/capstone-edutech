/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import commoninfraentities.UserEntity;
import edutechentities.group.GroupEntity;
import edutechentities.group.MMAgendaEntity;
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
@Path("agenda")
public class MMAgendaREST {
    
    @EJB
    CommonRESTMgrBean cmb;
    @EJB
    GroupRESTMgrBean etr;
    
    @GET @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MMAgendaEntity> getAllMMAgendas(@PathParam("id") String id) {
        return etr.getAllMMAgendas();
    }
    
    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MMAgendaEntity getOneMMAgenda(@PathParam("id") String id){
        return etr.getOneMMAgenda(Long.valueOf(id));
    }
    
    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MMAgendaEntity createMMAgenda(MMAgendaEntity agenda) {
        return etr.createMMAgenda(agenda);
    }
    
    @DELETE @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void deleteMMAgenda(@PathParam("id") String id) {
        etr.deleteMMAgenda(Long.valueOf(id));
    }
    
    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MMAgendaEntity editMMAgenda(@PathParam("id") String id, MMAgendaEntity replacement){
        return etr.editMMAgenda(Long.valueOf(id), replacement);
    }
    

    
}
