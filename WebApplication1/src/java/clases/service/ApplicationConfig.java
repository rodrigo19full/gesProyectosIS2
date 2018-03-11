/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Rodrigo Acuña
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(clases.service.AdjuntousFacadeREST.class);
        resources.add(clases.service.BacklogFacadeREST.class);
        resources.add(clases.service.HistorialusFacadeREST.class);
        resources.add(clases.service.NotasusFacadeREST.class);
        resources.add(clases.service.PermisosFacadeREST.class);
        resources.add(clases.service.PersonasFacadeREST.class);
        resources.add(clases.service.ProyectosFacadeREST.class);
        resources.add(clases.service.RolesFacadeREST.class);
        resources.add(clases.service.SprintsFacadeREST.class);
        resources.add(clases.service.TareasbacklogFacadeREST.class);
        resources.add(clases.service.UsFacadeREST.class);
        resources.add(clases.service.UsuariosFacadeREST.class);
    }
    
}
