/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.common.SemesterEntity;
import edutechsessionbeans.CommonMgrBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
 * @author Derian
 */
@RequestScoped
@Path("semester")
public class SemesterREST{

    @EJB
    CommonMgrBean crmb;

    @POST 
    @Produces({ MediaType.APPLICATION_JSON})
    @Consumes({ MediaType.APPLICATION_JSON})
    public SemesterEntity createSemester(SemesterEntity entity) {
        return crmb.createSemester(entity);
    }

    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public SemesterEntity editSemester(@PathParam("id") Long id, SemesterEntity entity) {
        return crmb.editSemester(id, entity);
    }

    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<SemesterEntity> deleteSemester(@PathParam("id") Long id) {
        crmb.deleteSemester(id);
        return crmb.getAllSemester();
    }

    @GET 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public SemesterEntity getOneSemester(@PathParam("id") Long id) {
        return crmb.getOneSemester(id);
    }

    @GET 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<SemesterEntity> getAllSemester() {
        return crmb.getAllSemester();
    }
    
    @GET
    @Path(("current"))
    @Produces(MediaType.APPLICATION_JSON)
    public SemesterEntity getCurrentSemester(){
        return crmb.getCurrentSemester();
    }
}
