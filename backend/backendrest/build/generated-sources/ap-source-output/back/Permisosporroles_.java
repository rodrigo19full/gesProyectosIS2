package back;

import back.Permisos;
import back.PermisosporrolesPK;
import back.Roles;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-01T22:56:46")
@StaticMetamodel(Permisosporroles.class)
public class Permisosporroles_ { 

    public static volatile SingularAttribute<Permisosporroles, Integer> estado;
    public static volatile SingularAttribute<Permisosporroles, PermisosporrolesPK> permisosporrolesPK;
    public static volatile SingularAttribute<Permisosporroles, Roles> roles;
    public static volatile SingularAttribute<Permisosporroles, Permisos> permisos;

}