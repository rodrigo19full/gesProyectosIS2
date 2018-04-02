package EntitysDao;

import Entitys.Usuarios;
import Facades.AbstractFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsuariosDAO extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosDAO() {
        super(Usuarios.class);
    }
        
    public List<Object[]> getUsuarios(Usuarios idUsuario) {
        List<Object[]> oResultList = em.createNamedQuery("Usuarios.findByIdusuario").setParameter("idusuario", idUsuario).getResultList();
        if(oResultList.size()<=0){
            return null;
        }else{
            return oResultList;
        }
    }
     
    
    /*
	public Usuarios crearUsuarios(Usuarios user) throws SQLException, NamingException {
		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		user.setFechaCreacion(new Date());
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}
    
	public String modificarUsuario(Usuarios user) throws SQLException, NamingException {
		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.find(Usuarios.class, user.getIdUsuario());
		em.merge(user);
		em.getTransaction().commit();
		em.close();
		return "OK";
	}
	
	public String eliminarUsuario(Long id) {
	    try{
			em = entityManagerFactory.createEntityManager();
	    	em.getTransaction().begin();
	    	Usuarios usuarios = new Usuarios();
	    	usuarios = em.find(Usuarios.class, id);
			em.remove(usuarios);
	    	em.getTransaction().commit();
	    	em.close();
	        return "OK";
	    }catch (PersistenceException pe){
	        pe.printStackTrace();
	        return "ERROR";
	    }
	
    public Usuarios verificarUsuario(String correo) {
    	Usuarios usuarios = null;
        StringBuilder countQuery = new StringBuilder();
        countQuery.append("select id_usuario, nombres, apellidos, documento ");
        countQuery.append(" from public.usuarios");
        countQuery.append(" where correo_electronico ='"+correo+"'");
        List<Object[]> oResultList = em.createNativeQuery(countQuery.toString()).
                getResultList();
        if(oResultList.size()<=0){
      	  return null;
        }
        for (Object[] oResultArray : oResultList) {
        	String idUsuario = oResultArray[0].toString();

			em = entityManagerFactory.createEntityManager();
	    	em.getTransaction().begin();
	    	usuarios = new Usuarios();
	    	usuarios = em.find(Usuarios.class,  Long.parseLong(idUsuario));
        }
        return usuarios;
     }*/
}
