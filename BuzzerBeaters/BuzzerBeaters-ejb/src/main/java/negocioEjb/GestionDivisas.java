package negocioEjb;

import javax.ejb.Local;

import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.Transaccion;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Local
public interface GestionDivisas {
	
	public void cambioDivisasAdmin(Usuario user, Double cantidad, Transaccion trans, Long id, CuentaReferencia cr)throws UsuarioException, UserNotAdminException, ClienteNoEncontradoException, CuentaException;
}
