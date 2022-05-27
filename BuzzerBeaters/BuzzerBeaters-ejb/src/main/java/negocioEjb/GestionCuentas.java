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
import negocioEJBexcepcion.CuentaConSaldo;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.SegregadaSinReferencia;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Local
public interface GestionCuentas {
	
	/**
	 * Este método debe permitir la apertura de una cuenta segregada.
	 * @throws CuentaException 
	 */
	public void aperturaCtaSegregated(Usuario admin, Segregada segregada, Cliente client, CuentaReferencia cr) throws UsuarioException, UserNotAdminException, SegregadaSinReferencia;

	
	/**
	 * Este método debe permitir la apertura de una cuenta pooled.
	 */


	public PooledAccount aperturaCtaPooled(Usuario admin, PooledAccount pooled, Cliente cliente, List<DepositadaEn> den) throws CuentaException, UsuarioException, UserNotAdminException ;
	
	public void cerrarCuenteSegregada (Usuario admin, Segregada seg) throws CuentaException, UsuarioException, UserNotAdminException;
	
	public void cerrarCuentaPooled (Usuario usuario, PooledAccount pooled) throws CuentaException, UsuarioException, UserNotAdminException, CuentaConSaldo;

	public CuentaReferencia getCuentaReferencia(String iban) throws CuentaException;
	
	public PooledAccount buscarPooled(String iban) throws CuentaException;

	public Segregada buscarSegregada(String iban) throws CuentaException;
	
	public void añadirCuentaReferencia(Usuario admin, CuentaReferencia referencia) throws CuentaException, UserNotAdminException, UsuarioException; 
}
