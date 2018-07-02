/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.service;

import back.Sprints;
import back.SprintsPK;
import back.Us;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author ro_fa
 */
@Stateless
@Path("sprints")
public class SprintsFacadeREST extends AbstractFacade<Sprints> {

    @PersistenceContext(unitName = "backendrestPU")
    private EntityManager em;

    private SprintsPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idsprint=idsprintValue;idproyecto=idproyectoValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        back.SprintsPK key = new back.SprintsPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idsprint = map.get("idsprint");
        if (idsprint != null && !idsprint.isEmpty()) {
            key.setIdsprint(new java.lang.Integer(idsprint.get(0)));
        }
        java.util.List<String> idproyecto = map.get("idproyecto");
        if (idproyecto != null && !idproyecto.isEmpty()) {
            key.setIdproyecto(new java.lang.Integer(idproyecto.get(0)));
        }
        return key;
    }

    public SprintsFacadeREST() {
        super(Sprints.class);
    }

    @POST
    @Override
    @Path("crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Sprints entity) {
        Query q = em.createNamedQuery("Sprints.ultimoId", Sprints.class);
        Integer ids =  (Integer) q.setMaxResults(1).getSingleResult();
        ids = ids + 1;
        Integer idp = (Integer) entity.getSprintsPK().getIdproyecto();
        SprintsPK idsp = new SprintsPK(ids,idp);
        entity.setSprintsPK(idsp);
        super.create(entity);
    }

    @PUT
    @Path("editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") PathSegment id, Sprints entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("eliminar/{id}")
    public void remove(@PathParam("id") PathSegment id) {
        back.SprintsPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("listar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sprints find(@PathParam("id") PathSegment id) {
        back.SprintsPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Path("listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sprints> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprints> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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
    
    //metodo get para listar las US del sprint
    @GET
    @Path("{idsprint}/us")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Us> listaUS(@PathParam("idsprint")Integer idsprint) {
        Query q = em.createNamedQuery("Us.findByIdsprint", Us.class);
        q.setParameter("idsprint", idsprint);
        return q.getResultList();
    }
    
}
