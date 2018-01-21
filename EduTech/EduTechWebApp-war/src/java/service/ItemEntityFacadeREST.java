/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.ItemEntity;
import java.util.List;
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
@Path("entities.itementity")
public class ItemEntityFacadeREST extends AbstractFacade<ItemEntity> {

    @PersistenceContext(unitName
            = "EduTechWebApp-warPU")
    private EntityManager em;

    public ItemEntityFacadeREST() {
        super(ItemEntity.class);
    }

    @POST
    @Override
    @Consumes(
        MediaType.APPLICATION_JSON)
    public void create(ItemEntity entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes(
        MediaType.APPLICATION_JSON)
    public void edit(
            @PathParam("id") String id,
            ItemEntity entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(
            @PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public ItemEntity find(
            @PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces("application/json")
    public List<ItemEntity> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces(
        MediaType.APPLICATION_JSON)
    public List<ItemEntity> findRange(
            @PathParam("from") Integer from,
            @PathParam("to") Integer to) {
        return super.findRange(new int[]{from,
                to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
