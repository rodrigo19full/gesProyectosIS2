package clases;

import clases.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-18T15:33:20")
@StaticMetamodel(Personas.class)
public class Personas_ { 

    public static volatile SingularAttribute<Personas, Integer> estado;
    public static volatile SingularAttribute<Personas, Date> fechanacimiento;
    public static volatile SingularAttribute<Personas, Integer> cedula;
    public static volatile SingularAttribute<Personas, Integer> idpersona;
    public static volatile SingularAttribute<Personas, String> apellido;
    public static volatile CollectionAttribute<Personas, Usuarios> usuariosCollection;
    public static volatile SingularAttribute<Personas, String> direccion;
    public static volatile SingularAttribute<Personas, String> telefono;
    public static volatile SingularAttribute<Personas, String> nombre;
    public static volatile SingularAttribute<Personas, String> email;

}