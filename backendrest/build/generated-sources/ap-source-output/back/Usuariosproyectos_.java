package back;

import back.Proyectos;
import back.Roles;
import back.Usuarios;
import back.UsuariosproyectosPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-02T18:46:41")
@StaticMetamodel(Usuariosproyectos.class)
public class Usuariosproyectos_ { 

    public static volatile SingularAttribute<Usuariosproyectos, Roles> idrol;
    public static volatile SingularAttribute<Usuariosproyectos, UsuariosproyectosPK> usuariosproyectosPK;
    public static volatile SingularAttribute<Usuariosproyectos, Proyectos> proyectos;
    public static volatile SingularAttribute<Usuariosproyectos, Usuarios> usuarios;

}