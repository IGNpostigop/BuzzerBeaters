package negocioEjb;



import java.util.List;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Cuenta;
import es.uma.BuzzerBeaters.CuentaReferencia;
import negocioEJBexcepcion.CuentaException;

public interface GestionCuentas {
	
	/**
	 * Este método debe permitir la apertura de una cuenta segregada.
	 * @throws CuentaException 
	 */
	void aperturaCtaSegregated(Cliente cliente, String clasificacion, CuentaReferencia cuenta, String comision)
			throws CuentaException;

	
	/**
	 * Este método debe permitir la apertura de una cuenta pooled.
	 */


	public void aperturaCtaPooled(Cliente cliente, String clasificacion, List<Cuenta> cuentas) throws CuentaException;


}
