package negocioEjb;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEJBexcepcion.WrongPasswordException;

@Stateless
public class UsuariosEJB implements GestionUsuarios{

	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManager em;
	

	@Override
	public void insertarUsuario(Usuario usuario) throws UsuarioException{
		Usuario usuarioEntity = em.find(Usuario.class, usuario);
		if (usuarioEntity != null) {
			throw new UsuarioException("El usuario ya existe");
		}
		else {
			em.persist(usuario);	
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
	
	@Override
	public List<Usuario> getUsuarios() 
	{
		// TODO
		Query query = em.createQuery("SELECT u FROM Usuario u");
		List<Usuario> Usuarios = query.getResultList();
		return Usuarios;
	}

	@Override
	public Usuario Login(String userName, String password) throws UsuarioException, WrongPasswordException {
		Usuario usuarioEntity = em.find(Usuario.class, userName);
		
		if(usuarioEntity == null) {
			throw new UsuarioException("El usuario no existe");
		}else if (!usuarioEntity.getPassword().equals(password)) {
			throw new WrongPasswordException("Contraseña incorrecta");			
		}else if (usuarioEntity.getIndividual().getFechaBaja() != null) {
			throw new UsuarioException("Cliente de baja");
		}

		return usuarioEntity;
	}
	
	@Override
	public Usuario AdminLogin(String adminName, String password) throws UsuarioException, WrongPasswordException, UserNotAdminException {
		Usuario adminEntity = em.find(Usuario.class, adminName);
		
		if(adminEntity == null) {
			throw new UsuarioException("El usuario no existe");
		}else if (!adminEntity.getPassword().equals(password)) {
			throw new WrongPasswordException("Contraseña incorrecta");			
		}else if (!adminEntity.isAdministrador()) {
			throw new UserNotAdminException("Usuario no es administrativo");
		}

		return adminEntity;
	}
	
    @Override
    public void compruebaLogin(Usuario u) throws UsuarioException, WrongPasswordException {
    	Usuario user = em.find(Usuario.class, u);
        if (user == null) {
            throw new UsuarioException();
        }

        if (!user.getPassword().equals(u.getPassword())) {
            throw new WrongPasswordException();
        }

    }

	@Override
    public Usuario refrescarUsuario(Usuario u) throws UsuarioException, WrongPasswordException {
    	compruebaLogin(u);
        Usuario user = em.find(Usuario.class, u);
        em.refresh(user);
        return user;
    }
}
