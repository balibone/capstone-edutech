/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechentities.common.ScheduleItemEntity;
import edutechentities.module.LessonEntity;
import edutechentities.module.ModuleEntity;
import edutechsessionbeans.ModuleMgrBean;
import java.util.ArrayList;
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
 * @author Derian
 */
@RequestScoped
@Path("module")
public class ModuleREST {
    
    @EJB
    ModuleMgrBean mrb;
    
    @GET 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ModuleEntity> getAllModules() {
        return mrb.getAllModules();
    }

    @GET 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public ModuleEntity getOneModule(@PathParam("id") String id) {
        return mrb.getOneModule(id);
    }
    
    @POST 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public void createModule(ModuleEntity entity) {
        mrb.createModule(entity);
    }

    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public void editModule(@PathParam("id") String id, ModuleEntity entity) {
        mrb.editModule(id,entity);
    }

    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ModuleEntity> deleteModule(@PathParam("id") String id) {
        mrb.deleteModule(id);
        return mrb.getAllModules();
    }

    @GET 
    @Path("lessons/{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<ScheduleItemEntity> getAllModuleLessons(@PathParam("id") String id) {
        return mrb.getAllModuleLessons(id);
    }
    
    @GET
    @Path("user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ModuleEntity> getUserModules(@PathParam("id") String userId){
        return mrb.getUserModules(userId);
    }

    @POST 
    @Path("massassign")
    @Consumes({ MediaType.APPLICATION_JSON})
    public void massAssignUsersToMods(ArrayList<ModuleAndUser> nominalRoll) {
        mrb.massAssignUsersToMods(nominalRoll);
    }
    
}
