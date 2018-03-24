package Entitys;

import Entitys.Backlog;
import Entitys.Sprints;
import Entitys.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-24T15:06:02")
@StaticMetamodel(Proyectos.class)
public class Proyectos_ { 

    public static volatile SingularAttribute<Proyectos, Date> fechainicio;
    public static volatile SingularAttribute<Proyectos, String> descripcion;
    public static volatile SingularAttribute<Proyectos, Integer> idproyecto;
    public static volatile CollectionAttribute<Proyectos, Usuarios> usuariosCollection;
    public static volatile SingularAttribute<Proyectos, Integer> anho;
    public static volatile CollectionAttribute<Proyectos, Backlog> backlogCollection;
    public static volatile SingularAttribute<Proyectos, Date> fechafin;
    public static volatile CollectionAttribute<Proyectos, Sprints> sprintsCollection;
    public static volatile SingularAttribute<Proyectos, Integer> codproyecto;
    public static volatile SingularAttribute<Proyectos, String> nombre;

}