package back;

import back.Notasus;
import back.Sprints;
import back.UsPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-02T18:46:41")
@StaticMetamodel(Us.class)
public class Us_ { 

    public static volatile SingularAttribute<Us, String> descripcion;
    public static volatile SingularAttribute<Us, Integer> estado;
    public static volatile CollectionAttribute<Us, Notasus> notasusCollection;
    public static volatile SingularAttribute<Us, Integer> tiempoejecucion;
    public static volatile SingularAttribute<Us, Integer> numerous;
    public static volatile SingularAttribute<Us, Integer> valornegocio;
    public static volatile SingularAttribute<Us, Sprints> sprints;
    public static volatile SingularAttribute<Us, Integer> horastrabajadas;
    public static volatile SingularAttribute<Us, String> nombre;
    public static volatile SingularAttribute<Us, UsPK> usPK;
    public static volatile SingularAttribute<Us, Integer> idusuario;
    public static volatile SingularAttribute<Us, Integer> valortecnico;

}