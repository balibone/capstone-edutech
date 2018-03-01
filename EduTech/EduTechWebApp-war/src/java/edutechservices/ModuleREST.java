/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.module.ModuleEntity;
import edutechsessionbeans.ModuleRESTMgrBean;
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
@Path("module")
public class ModuleREST {
    
    @EJB
    ModuleRESTMgrBean mrb;

    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ModuleEntity entity) {
        mrb.createModule(entity);
    }

    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, ModuleEntity entity) {
        mrb.editModule(id,entity);
    }

    @DELETE @Path("{id}")
    public void remove(@PathParam("id") String id) {
        mrb.removeModule(id);
    }

    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ModuleEntity find(@PathParam("id") String id) {
        return mrb.findModule(id);
    }

    @GET @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ModuleEntity> findAll() {
        return mrb.findAllModules();
    }

    //FOR FUTURE REFERENCE IF NEEDED TO IMPLEMENT
//    @GET @Path("{from}/{to}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<ModuleEntity> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return 
//    }
//
//    @GET @Path("count") @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//    }

    
}
