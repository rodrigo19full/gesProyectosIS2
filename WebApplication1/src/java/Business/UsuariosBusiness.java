package Business;

import Entitys.Usuarios;
import Facades.UsuariosFacadeREST;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "usuarios")
@ApplicationScoped
public class UsuariosBusiness{

    @Inject
    UsuariosFacadeREST usuariosFacade;
    

    @PostConstruct
    public void init() {
        /*if (this.dao == null) {
            this.dao = usuariosDao;
        }*/
    }
    public List getUsuarios(Usuarios idUsuario) {
        return usuariosFacade.getUsuarios(idUsuario);
    }
    
    /*public Usuarios crearUsuarios(Usuarios user) throws SQLException, NamingException {
    	return usuariosServiciosDao.crearUsuarios(user);
        
    }
    
    public String modificarUsuario(Usuarios user) throws SQLException, NamingException {
    	return usuariosServiciosDao.modificarUsuario(user);
        
    }
    public String eliminarUsuario(Long id) throws SQLException, NamingException {
    	return usuariosServiciosDao.eliminarUsuario(id);
        
    }

    public Usuarios verificarUsuario(String correo) {
        return usuariosDao.verificarUsuario(correo);
    }*/

}
