package negocioEjb;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;


public class UsuariosEJB implements GestionUsuarios{

	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManager em;
	

	@Override
	public void insertarUsuario(String user, String password, boolean administrador) throws UsuarioException{
		Usuario usuarioEntity = em.find(Usuario.class, user);
		if (usuarioEntity != null) {
			throw new UsuarioException("El usuario ya existe");
		}
		else {
			Usuario nuevo = new Usuario();
			nuevo.setPassword(password);
			nuevo.setUser(user);
			nuevo.setAdministrador(administrador);
			em.persist(nuevo);	
		}		
	}

	@Override
	public void eliminarUsuario(Usuario user) throws UsuarioException {
		Usuario usuarioEntity = em.find(Usuario.class, user);	
		if (usuarioEntity == null) {
			throw new UsuarioException("El usuario no existe");
		}else {
			em.remove(usuarioEntity);
		}
	}

	@Override
	public void resetUserPassword(Usuario user, String password) throws UsuarioException {
		Usuario usuarioEntity = em.find(Usuario.class, user);	
		if (usuarioEntity == null) {
			throw new UsuarioException("El usuario no existe");
		}else {
			usuarioEntity.setPassword(password);
		}
		// TODO Auto-generated method stub
		
	}

}
