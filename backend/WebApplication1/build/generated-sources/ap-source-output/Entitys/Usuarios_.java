package Entitys;

import Entitys.Personas;
import Entitys.Proyectos;
import Entitys.Roles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-24T15:06:02")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> nombreusuario;
    public static volatile SingularAttribute<Usuarios, Integer> estado;
    public static volatile SingularAttribute<Usuarios, Roles> idrol;
    public static volatile SingularAttribute<Usuarios, Date> fechacreacion;
    public static volatile CollectionAttribute<Usuarios, Proyectos> proyectosCollection;
    public static volatile SingularAttribute<Usuarios, String> userpass;
    public static volatile SingularAttribute<Usuarios, Personas> idpersona;
    public static volatile SingularAttribute<Usuarios, String> usernombre;
    public static volatile SingularAttribute<Usuarios, Integer> idusuario;

}