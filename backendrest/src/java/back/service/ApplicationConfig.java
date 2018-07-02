/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ro_fa
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
        resources.add(back.service.BacklogFacadeREST.class);
        resources.add(back.service.NotasusFacadeREST.class);
        resources.add(back.service.PermisosFacadeREST.class);
        resources.add(back.service.PermisosporrolesFacadeREST.class);
        resources.add(back.service.ProyectosFacadeREST.class);
        resources.add(back.service.RolesFacadeREST.class);
        resources.add(back.service.SprintsFacadeREST.class);
        resources.add(back.service.TareasbacklogFacadeREST.class);
        resources.add(back.service.UsFacadeREST.class);
        resources.add(back.service.UsuariosFacadeREST.class);
        resources.add(back.service.UsuariosproyectosFacadeREST.class);
    }
    
}
