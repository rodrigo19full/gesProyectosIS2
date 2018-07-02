package back;

import back.Permisosporroles;
import back.Usuariosproyectos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-01T22:56:46")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile CollectionAttribute<Roles, Permisosporroles> permisosporrolesCollection;
    public static volatile CollectionAttribute<Roles, Usuariosproyectos> usuariosproyectosCollection;
    public static volatile SingularAttribute<Roles, Integer> idrol;
    public static volatile SingularAttribute<Roles, Integer> estado;
    public static volatile SingularAttribute<Roles, String> rol;

}