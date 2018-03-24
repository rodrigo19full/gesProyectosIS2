package service;

import Business.UsuariosBusiness;
import Entitys.Usuarios;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@Path("/rest/usuarios")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService {

    @Inject
    UsuariosBusiness usuarioLogic;

    @GET
    @Path("/obtener")
    public Response getUsuarios(@QueryParam("idUsuario") Usuarios idUsuario) throws Exception {
        if (idUsuario == null || idUsuario.equals("")) {
            throw new Exception("Se requiere idUsuario");
        }
        return Response.ok(usuarioLogic.getUsuarios(idUsuario)).build();
    }

}
