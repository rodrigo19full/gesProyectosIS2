/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.service;

import back.Proyectos;
import back.Us;
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
@Path("usuarios")
public class UsuariosFacadeREST extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "backendrestPU")
    private EntityManager em;

    public UsuariosFacadeREST() {
        super(Usuarios.class);
    }

    @POST
    @Override
    @Path("crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Usuarios entity) {
        entity.setFechanacimiento(new Date());
        entity.setFechacreacion(new Date());
        super.create(entity);
    }

    @PUT
    @Path("editar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Usuarios entity) {
        entity.setFechanacimiento(new Date());
        entity.setFechacreacion(new Date());
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
    public Usuarios find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Path("listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuarios> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuarios> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    //metodo get para login
    @GET
    @Path("login/{usernombre}/{userpass}")
    @Produces(MediaType.APPLICATION_JSON)
    public String countRES(@PathParam("usernombre") String usernombre, @PathParam("userpass") String userpass) {
        return (super.login(usernombre,userpass));  
    }
    
    //metodo get para listar proyectos del usuario
    @GET
    @Path("{idusuario}/proyectos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Proyectos> listaProyectos(@PathParam("idusuario")Integer idusuario) {
        Query q = em.createNamedQuery("Usuariosproyectos.findByIdusuario", Usuariosproyectos.class);
        q.setParameter("idusuario", idusuario);
        return q.getResultList();
    }
    
     //metodo get para listar tareas del usuario
    @GET
    @Path("{idusuario}/tareas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Us> listaTareas(@PathParam("idusuario")Integer idusuario) {
        Query q = em.createNamedQuery("Us.findByIdusuario", Us.class);
        q.setParameter("idusuario", idusuario);
        return q.getResultList();
    }
    
}
