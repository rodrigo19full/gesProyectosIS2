/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.service;

import back.Proyectos;
import back.Sprints;
import back.Usuarios;
import back.Usuariosproyectos;
import java.util.Date;
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

/**
 *
 * @author ro_fa
 */
@Stateless
@Path("proyectos")
public class ProyectosFacadeREST extends AbstractFacade<Proyectos> {

    @PersistenceContext(unitName = "backendrestPU")
    private EntityManager em;

    public ProyectosFacadeREST() {
        super(Proyectos.class);
    }

    @POST
    @Override
    @Path("crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Proyectos entity) {
        entity.setFechainicio(new Date());
        entity.setFechafin(new Date());
        super.create(entity);
    }

    @PUT
    @Path("editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Proyectos entity) {
        entity.setFechainicio(new Date());
        entity.setFechafin(new Date());
        super.edit(entity);
    }

    @DELETE
    @Path("eliminar/{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("listar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Proyectos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Path("listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proyectos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Proyectos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    //metodo get para listar proyectos del usuario
    @GET
    @Path("{idproyecto}/usuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuarios> listaUsuarios(@PathParam("idproyecto")Integer idproyecto) {
        Query q = em.createNamedQuery("Usuariosproyectos.findByIdproyecto", Usuariosproyectos.class);
        q.setParameter("idproyecto", idproyecto);
        return q.getResultList();
    }
    
    //metodo get para listar sprints del proyecto
    @GET
    @Path("{idproyecto}/sprints")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sprints> listaSprints(@PathParam("idproyecto")Integer idproyecto) {
        Query q = em.createNamedQuery("Sprints.findByIdproyecto", Sprints.class);
        q.setParameter("idproyecto", idproyecto);
        return q.getResultList();
    }
    
}
