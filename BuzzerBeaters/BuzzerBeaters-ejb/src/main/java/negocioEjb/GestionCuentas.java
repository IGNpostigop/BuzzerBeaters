package negocioEjb;



import java.util.List;

import javax.ejb.Local;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Cuenta;
import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.DepositadaEn;
import es.uma.BuzzerBeaters.PooledAccount;
import es.uma.BuzzerBeaters.Segregada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Local
public interface GestionCuentas {
	
	/**
	 * Este método debe permitir la apertura de una cuenta segregada.
	 * @throws CuentaException 
	 */
	void aperturaCtaSegregated(Usuario admin, Segregada segregada, Cliente client, CuentaReferencia cr) throws CuentaException, UsuarioException, UserNotAdminException;

	
	/**
	 * Este método debe permitir la apertura de una cuenta pooled.
	 */


	public PooledAccount aperturaCtaPooled(Usuario admin, PooledAccount pooled, Cliente cliente, List<DepositadaEn> den) throws CuentaException, UsuarioException, UserNotAdminException ;
	
	public void cerrarCuenteSegregada (Segregada seg) throws CuentaException;
	
	public void cerrarCuentaPooled (PooledAccount pooled) throws CuentaException;

	public CuentaReferencia getCuentaReferencia(String iban) throws CuentaException;
}
