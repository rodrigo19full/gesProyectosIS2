package clases;

import clases.Proyectos;
import clases.Tareasbacklog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-18T15:33:20")
@StaticMetamodel(Backlog.class)
public class Backlog_ { 

    public static volatile SingularAttribute<Backlog, Integer> idbacklog;
    public static volatile SingularAttribute<Backlog, Proyectos> idproyecto;
    public static volatile CollectionAttribute<Backlog, Tareasbacklog> tareasbacklogCollection;

}