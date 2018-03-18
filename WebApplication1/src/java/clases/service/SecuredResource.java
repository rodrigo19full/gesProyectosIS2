/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.service;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Rodrigo Acuña
 */

@Path("secured")
public class SecuredResource {
    
    @GET
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public String securedMethod(){
        return "Se ha iniciado sesión";
    }
    
}
