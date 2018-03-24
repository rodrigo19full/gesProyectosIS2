/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author lramirez
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
        resources.add(Facades.AdjuntousFacadeREST.class);
        resources.add(Facades.BacklogFacadeREST.class);
        resources.add(Facades.HistorialusFacadeREST.class);
        resources.add(Facades.NotasusFacadeREST.class);
        resources.add(Facades.PermisosFacadeREST.class);
        resources.add(Facades.PersonasFacadeREST.class);
        resources.add(Facades.ProyectosFacadeREST.class);
        resources.add(Facades.RolesFacadeREST.class);
        resources.add(Facades.SprintsFacadeREST.class);
        resources.add(Facades.TareasbacklogFacadeREST.class);
        resources.add(Facades.UsFacadeREST.class);
        resources.add(Facades.UsuariosFacadeREST.class);
        resources.add(service.UsuarioService.class);
    }
    
}
