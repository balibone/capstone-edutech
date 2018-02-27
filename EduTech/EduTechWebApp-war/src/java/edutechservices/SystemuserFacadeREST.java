/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechsessionbeans.CommonRESTMgrBean;
import commoninfraentities.UserEntity;
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
@Path("commoninfraentities.systemuser")
public class SystemuserFacadeREST {

    @PersistenceContext(unitName = "EduTechWebApp-warPU")
    private EntityManager em;
    @EJB
    CommonRESTMgrBean etr;
    

    @POST @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UserEntity entity) {
        etr.createUser(entity);
    }

    @PUT @Path("{id}") @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, UserEntity entity) {
        etr.editUser(id, entity);
    }

    @DELETE @Path("{id}")
    public void remove(@PathParam("id") String id) {
        etr.removeUser(id);
    }

    @GET @Path("{id}") @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserEntity find(@PathParam("id") String id) {
        return etr.findUser(id);
    }

    @GET @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserEntity> findAll() {
        return etr.findAllUsers();
    }

    @GET @Path("count") @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return etr.countUsers();
    }
    
}
