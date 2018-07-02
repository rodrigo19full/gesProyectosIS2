package back;

import back.Proyectos;
import back.SprintsPK;
import back.Us;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-02T18:46:41")
@StaticMetamodel(Sprints.class)
public class Sprints_ { 

    public static volatile SingularAttribute<Sprints, Integer> estado;
    public static volatile CollectionAttribute<Sprints, Us> usCollection;
    public static volatile SingularAttribute<Sprints, Integer> duracion;
    public static volatile SingularAttribute<Sprints, Proyectos> proyectos;
    public static volatile SingularAttribute<Sprints, SprintsPK> sprintsPK;
    public static volatile SingularAttribute<Sprints, String> nombre;

}