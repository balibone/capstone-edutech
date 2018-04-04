/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edutechservices;

import edutechsessionbeans.CommonMgrBean;
import commoninfraentities.UserEntity;
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
 * @author Derian
 */
@RequestScoped
@Path("user")
public class UserREST {

    @PersistenceContext(unitName = "EduTechWebApp-warPU")
    private EntityManager em;
    @EJB
    CommonMgrBean etr;
    

    @POST 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public UserEntity createUser(UserEntity entity) {
        return etr.createUser(entity);
    }

    @PUT 
    @Path("{id}") 
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public UserEntity editUser(@PathParam("id") String id, UserEntity entity) {
        return etr.editUser(id, entity);
    }

    @DELETE 
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<UserEntity> deleteUser(@PathParam("id") String id) {
        etr.deleteUser(id);
        return etr.getAllUsers();
    }

    @GET 
    @Path("{username}") 
    @Produces({ MediaType.APPLICATION_JSON})
    public UserEntity getOneUser(@PathParam("username") String username) {
        return etr.getOneUser(username);
    }

    @GET 
    @Produces({ MediaType.APPLICATION_JSON})
    public List<UserEntity> getAllUsers() {
        return etr.getAllUsers();
    }


    
}
