package back;

import back.Backlog;
import back.Sprints;
import back.Usuariosproyectos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-01T22:56:46")
@StaticMetamodel(Proyectos.class)
public class Proyectos_ { 

    public static volatile SingularAttribute<Proyectos, Date> fechainicio;
    public static volatile SingularAttribute<Proyectos, String> descripcion;
    public static volatile CollectionAttribute<Proyectos, Usuariosproyectos> usuariosproyectosCollection;
    public static volatile SingularAttribute<Proyectos, Integer> idproyecto;
    public static volatile SingularAttribute<Proyectos, Integer> anho;
    public static volatile CollectionAttribute<Proyectos, Backlog> backlogCollection;
    public static volatile SingularAttribute<Proyectos, Date> fechafin;
    public static volatile CollectionAttribute<Proyectos, Sprints> sprintsCollection;
    public static volatile SingularAttribute<Proyectos, Integer> codproyecto;
    public static volatile SingularAttribute<Proyectos, String> nombre;

}