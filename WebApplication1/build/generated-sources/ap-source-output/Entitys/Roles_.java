package Entitys;

import Entitys.Permisos;
import Entitys.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-24T15:06:02")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, Integer> idrol;
    public static volatile SingularAttribute<Roles, Integer> estado;
    public static volatile CollectionAttribute<Roles, Usuarios> usuariosCollection;
    public static volatile CollectionAttribute<Roles, Permisos> permisosCollection;
    public static volatile SingularAttribute<Roles, String> rol;

}