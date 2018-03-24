package Entitys;

import Entitys.Roles;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-24T15:06:02")
@StaticMetamodel(Permisos.class)
public class Permisos_ { 

    public static volatile SingularAttribute<Permisos, String> permiso;
    public static volatile CollectionAttribute<Permisos, Roles> rolesCollection;
    public static volatile SingularAttribute<Permisos, Integer> idpermiso;

}