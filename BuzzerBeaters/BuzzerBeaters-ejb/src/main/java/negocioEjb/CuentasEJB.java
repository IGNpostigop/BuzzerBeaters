package negocioEjb;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.tools.javac.util.List;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Cuenta;
import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.Segregada;
import negocioEJBexcepcion.CuentaException;
import oracle.sql.DATE;

public class CuentasEJB implements GestionCuentas {
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManager em;
	
	
	@Override
	public void aperturaCtaSegregated(Cliente cliente, String clasificacion, CuentaReferencia cuenta, String comision) throws CuentaException {
		
		String Iban = cuenta.getIban();

		
		Segregada s = em.find(Segregada.class, Iban);
		if(s != null) {
				throw new CuentaException ("La cuenta ya existe");
		}else {	
			
			Segregada newSegregada = new Segregada();
			newSegregada.setCliente(cliente);
			newSegregada.setEstado(true);
			newSegregada.setFecha_apertura(Date.valueOf(LocalDate.now()));
			newSegregada.setClasificacion(clasificacion);
			newSegregada.setCuenta_referencia(cuenta);
			newSegregada.setComision(comision);
			em.persist(newSegregada);
		}
		

	}

	@Override
	public void aperturaCtaPooled(Cliente cliente, String clasificacion, List<Cuenta> cuentas) throws CuentaException{
		// TODO Auto-generated method stub

	}

}
