/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.service;

import back.Usuariosproyectos;
import back.UsuariosproyectosPK;
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
@Path("usuariosproyectos")
public class UsuariosproyectosFacadeREST extends AbstractFacade<Usuariosproyectos> {

    @PersistenceContext(unitName = "backendrestPU")
    private EntityManager em;

    private UsuariosproyectosPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idproyecto=idproyectoValue;idusuario=idusuarioValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        back.UsuariosproyectosPK key = new back.UsuariosproyectosPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idproyecto = map.get("idproyecto");
        if (idproyecto != null && !idproyecto.isEmpty()) {
            key.setIdproyecto(new java.lang.Integer(idproyecto.get(0)));
        }
        java.util.List<String> idusuario = map.get("idusuario");
        if (idusuario != null && !idusuario.isEmpty()) {
            key.setIdusuario(new java.lang.Integer(idusuario.get(0)));
        }
        return key;
    }

    public UsuariosproyectosFacadeREST() {
        super(Usuariosproyectos.class);
    }

    @POST
    @Override
    @Path("crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Usuariosproyectos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Usuariosproyectos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        back.UsuariosproyectosPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuariosproyectos find(@PathParam("id") PathSegment id) {
        back.UsuariosproyectosPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuariosproyectos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuariosproyectos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
