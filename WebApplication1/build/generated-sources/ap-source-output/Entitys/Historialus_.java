package Entitys;

import Entitys.Us;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-24T15:06:02")
@StaticMetamodel(Historialus.class)
public class Historialus_ { 

    public static volatile SingularAttribute<Historialus, Date> fecha;
    public static volatile SingularAttribute<Historialus, Integer> idhistorial;
    public static volatile SingularAttribute<Historialus, Us> idus;
    public static volatile SingularAttribute<Historialus, String> comentario;
    public static volatile SingularAttribute<Historialus, Integer> idusuario;

}