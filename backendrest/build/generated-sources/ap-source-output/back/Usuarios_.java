package back;

import back.Usuariosproyectos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-02T18:46:41")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile CollectionAttribute<Usuarios, Usuariosproyectos> usuariosproyectosCollection;
    public static volatile SingularAttribute<Usuarios, Integer> estado;
    public static volatile SingularAttribute<Usuarios, Integer> cedula;
    public static volatile SingularAttribute<Usuarios, String> direccion;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, Integer> idusuario;
    public static volatile SingularAttribute<Usuarios, Date> fechanacimiento;
    public static volatile SingularAttribute<Usuarios, Date> fechacreacion;
    public static volatile SingularAttribute<Usuarios, String> userpass;
    public static volatile SingularAttribute<Usuarios, String> apellido;
    public static volatile SingularAttribute<Usuarios, String> usernombre;
    public static volatile SingularAttribute<Usuarios, String> telefono;
    public static volatile SingularAttribute<Usuarios, String> email;

}