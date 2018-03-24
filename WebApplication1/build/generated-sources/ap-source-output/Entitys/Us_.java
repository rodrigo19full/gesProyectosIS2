package Entitys;

import Entitys.Adjuntous;
import Entitys.Historialus;
import Entitys.Notasus;
import Entitys.Sprints;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-24T15:06:02")
@StaticMetamodel(Us.class)
public class Us_ { 

    public static volatile SingularAttribute<Us, String> descripcion;
    public static volatile CollectionAttribute<Us, Adjuntous> adjuntousCollection;
    public static volatile SingularAttribute<Us, Integer> estado;
    public static volatile CollectionAttribute<Us, Historialus> historialusCollection;
    public static volatile CollectionAttribute<Us, Notasus> notasusCollection;
    public static volatile SingularAttribute<Us, Integer> tiempoejecucion;
    public static volatile SingularAttribute<Us, Integer> numerous;
    public static volatile SingularAttribute<Us, Integer> idus;
    public static volatile SingularAttribute<Us, Integer> horastrabajadas;
    public static volatile CollectionAttribute<Us, Sprints> sprintsCollection;
    public static volatile SingularAttribute<Us, String> nombre;
    public static volatile SingularAttribute<Us, Integer> idusuario;
    public static volatile SingularAttribute<Us, Integer> valornegocio;
    public static volatile SingularAttribute<Us, Integer> valortecnico;

}