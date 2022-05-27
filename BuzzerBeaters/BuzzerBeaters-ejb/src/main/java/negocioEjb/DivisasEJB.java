package negocioEjb;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.PooledAccount;
import es.uma.BuzzerBeaters.Transaccion;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

public class DivisasEJB {
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters_ejb")
	private EntityManager em;
	
	//RF18
	public void cambioDivisasAdmin(Usuario user, Double cantidad, Transaccion trans, Long id, CuentaReferencia cr) throws UsuarioException, UserNotAdminException, ClienteNoEncontradoException, CuentaException {
		
		Usuario admin = em.find(Usuario.class, user.getUser());
		
		if (admin == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!admin.isAdministrador()) { 
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		//Revisar si existe la persona autorizada
		PersonaAutorizada pa = em.find(PersonaAutorizada.class, id); 
		
		if(pa == null) {
			throw new ClienteNoEncontradoException("El usuario no existe");
		}
		
		//Revisar si existe una cuenta pooled
		PooledAccount pooled = em.find(PooledAccount.class, pa);
		
		if(pooled != null){
			throw new CuentaException("La cuenta ya existe"); 
		}
		
		
		
	}
}
