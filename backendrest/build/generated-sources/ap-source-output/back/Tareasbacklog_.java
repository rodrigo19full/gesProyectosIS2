package back;

import back.Backlog;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-02T18:46:41")
@StaticMetamodel(Tareasbacklog.class)
public class Tareasbacklog_ { 

    public static volatile SingularAttribute<Tareasbacklog, String> descripcion;
    public static volatile SingularAttribute<Tareasbacklog, Integer> estado;
    public static volatile SingularAttribute<Tareasbacklog, Integer> idtarea;
    public static volatile SingularAttribute<Tareasbacklog, Backlog> idbacklog;
    public static volatile SingularAttribute<Tareasbacklog, Integer> valornegocio;
    public static volatile SingularAttribute<Tareasbacklog, String> nombre;

}