package Entitys;

import Entitys.Proyectos;
import Entitys.Us;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-24T15:06:02")
@StaticMetamodel(Sprints.class)
public class Sprints_ { 

    public static volatile SingularAttribute<Sprints, Integer> idsprint;
    public static volatile SingularAttribute<Sprints, Integer> estado;
    public static volatile SingularAttribute<Sprints, Proyectos> idproyecto;
    public static volatile CollectionAttribute<Sprints, Us> usCollection;
    public static volatile SingularAttribute<Sprints, Integer> duracion;
    public static volatile SingularAttribute<Sprints, String> nombre;

}