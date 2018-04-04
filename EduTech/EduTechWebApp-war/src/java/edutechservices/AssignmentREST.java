/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

//import edutechentities.common.GroupEntity;
import edutechentities.module.AssignmentEntity;
import edutechsessionbeans.CommonMgrBean;
import edutechsessionbeans.GroupMgrBean;
import edutechsessionbeans.ModuleMgrBean;
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

@RequestScoped
@Path("assignment")
public class AssignmentREST {
    
    @EJB
    CommonMgrBean cmb;
    @EJB
    GroupMgrBean etr;
    @EJB
    ModuleMgrBean mmb;
    
    @GET 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<AssignmentEntity> getAllAssignments() {
        return mmb.getAllAssignments();
    }
    
    @GET 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity getOneAssignment(@PathParam("id") String id){
        return mmb.getOneAssignment(Long.valueOf(id));
    }
    
    @POST 
    @Path("individual")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity createIndividualAssignment(AssignmentEntity ass) {
//        return ass;
        return mmb.createIndividualAssignment(ass);
    }
    
    @POST 
    @Path("group/{numOfGroups}/{groupSize}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity createGroupAssignment(AssignmentEntity ass, @PathParam("numOfGroups") String numOfGroups,
            @PathParam("groupSize") String groupSize) {
        return mmb.createGroupAssignment(ass, numOfGroups, groupSize);
    }
    
    @DELETE 
    @Path("{id}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<AssignmentEntity> deleteAssignment(@PathParam("id") String id) {
        mmb.deleteAssignment(Long.valueOf(id));
        return mmb.getAllAssignments();
    }
    
    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public AssignmentEntity editAssignment(@PathParam("id") String id, AssignmentEntity replacement){
        return mmb.editAssignment(Long.valueOf(id), replacement);
    }

    @GET
    @Path("module/{moduleCode}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<AssignmentEntity> getModuleAssignments(@PathParam("moduleCode") String moduleCode){
        return etr.getModuleAssignments(moduleCode);
    }
}
