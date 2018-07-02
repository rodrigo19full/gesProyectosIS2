/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.service;

import back.Notasus;
import back.NotasusPK;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author ro_fa
 */
@Stateless
@Path("back.notasus")
public class NotasusFacadeREST extends AbstractFacade<Notasus> {

    @PersistenceContext(unitName = "backendrestPU")
    private EntityManager em;

    private NotasusPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idnota=idnotaValue;idproyecto=idproyectoValue;idus=idusValue;idsprint=idsprintValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        back.NotasusPK key = new back.NotasusPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idnota = map.get("idnota");
        if (idnota != null && !idnota.isEmpty()) {
            key.setIdnota(new java.lang.Integer(idnota.get(0)));
        }
        java.util.List<String> idproyecto = map.get("idproyecto");
        if (idproyecto != null && !idproyecto.isEmpty()) {
            key.setIdproyecto(new java.lang.Integer(idproyecto.get(0)));
        }
        java.util.List<String> idus = map.get("idus");
        if (idus != null && !idus.isEmpty()) {
            key.setIdus(new java.lang.Integer(idus.get(0)));
        }
        java.util.List<String> idsprint = map.get("idsprint");
        if (idsprint != null && !idsprint.isEmpty()) {
            key.setIdsprint(new java.lang.Integer(idsprint.get(0)));
        }
        return key;
    }

    public NotasusFacadeREST() {
        super(Notasus.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Notasus entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Notasus entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        back.NotasusPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Notasus find(@PathParam("id") PathSegment id) {
        back.NotasusPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notasus> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notasus> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
}
