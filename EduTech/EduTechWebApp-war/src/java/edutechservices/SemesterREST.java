/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.common.SemesterEntity;
import edutechsessionbeans.CommonRESTMgrBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@Stateless
@Path("edutechentities.common.semesterentity")
public class SemesterREST{

    @PersistenceContext(unitName = "EduTechWebApp-warPU")
    private EntityManager em;

    @EJB
    CommonRESTMgrBean crmb;

    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(SemesterEntity entity) {
        crmb.createSemester(entity);
    }

    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, SemesterEntity entity) {
        crmb.editSemester(id, entity);
    }

    @DELETE @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        crmb.removeSemester(id);
    }

    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SemesterEntity find(@PathParam("id") Long id) {
        return crmb.findSemester(id);
    }

    @GET @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SemesterEntity> findAll() {
        return crmb.findAllSemesters();
    }

//    @GET @Path("{from}/{to}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<SemesterEntity> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET @Path("count") @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
    
}
