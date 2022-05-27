package negocioEjb;

//import java.sql.Date;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.DepositadaEn;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.PooledAccount;
import es.uma.BuzzerBeaters.Segregada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaConSaldo;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.SegregadaSinReferencia;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Stateless
public class CuentasEJB implements GestionCuentas {
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters_ejb")
	private EntityManager em;
	
	
	@Override
	//RF5: Apertura de cuenta segregada
	public  void aperturaCtaSegregated(Usuario admin, Segregada segregada, Cliente client, CuentaReferencia cr) throws UsuarioException, UserNotAdminException, SegregadaSinReferencia {
		
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		CuentaReferencia cuentaRef = em.find(CuentaReferencia.class, cr.getIban());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) { 
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		if (cuentaRef==null) { 
			throw new SegregadaSinReferencia("La cuenta de referencia no existe");
		}
			segregada.setCliente(client);
			segregada.setCuenta_referencia(cuentaRef);
			em.persist(segregada);
	}

	@Override
	//RF5: Apertura de cuenta pooled
	public PooledAccount aperturaCtaPooled(Usuario admin, PooledAccount pooled, Cliente cliente, List<DepositadaEn> den) throws CuentaException, UsuarioException, UserNotAdminException{
		
		PooledAccount pooledBd = em.find(PooledAccount.class, pooled.getIban());
		
		Usuario administrador = em.find(Usuario.class, admin.getUser());

		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}

		if(pooledBd != null){
			throw new CuentaException("La cuenta ya existe"); 
		}else {
			pooled.setCliente(cliente);
			pooled.setPooledDepositadaEn(den);
			em.persist(pooled);
			return pooled;
		}
	}

	@Override
	//RF9: Cerrar de cuenta segregada
	public void cerrarCuenteSegregada(Segregada seg) throws CuentaException {
		Segregada segregadaBd = em.find(Segregada.class, seg.getIban());
		if(segregadaBd == null) {
			throw new CuentaException("La cuenta no existe"); 
		}else {
			if(segregadaBd.getCuenta_referencia().getSaldo()==0) {
				segregadaBd.setEstado(false);
			}else {
				throw new CuentaException("Cuenta con saldo");
			}
		}
		
	}

	@Override
	public PooledAccount buscarPooled(String iban) throws CuentaException {
	
		PooledAccount pooled = em.find(PooledAccount.class, iban);
		
		if(pooled == null) {
			
			throw new CuentaException();
		}
		
		return pooled;
	}
	
	
	@Override
	//RF9: Cerrar de cuenta pooled
	public void cerrarCuentaPooled(Usuario admin, PooledAccount pooled) throws CuentaException, UsuarioException, UserNotAdminException, CuentaConSaldo {
		
		Usuario administrador = em.find(Usuario.class, admin.getUser());

		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}

		
		PooledAccount pooledBd = em.find(PooledAccount.class, pooled.getIban());
		
		boolean sinsaldo = true;
		
		if(pooledBd == null){
			throw new CuentaException("La cuenta no existe"); 
		}else {
			List<DepositadaEn> depositos = pooledBd.getPooledDepositadaEn();
			for(DepositadaEn deposito:depositos) {
				if(deposito.getSaldo()!=0) {
					sinsaldo=false;
					break;
				}
			}
			if(sinsaldo) {
				pooledBd.setEstado(false);
				pooledBd.setFecha_cierre(new Date());
				//pooledBd.setFecha_cierre(Date.valueOf(LocalDate.now()));
			}else {
				throw new CuentaConSaldo("Algun depósito con saldo");
		}
	}
		
	}
	
	
	@Override
	public CuentaReferencia getCuentaReferencia(String iban) throws CuentaException{
		{
			CuentaReferencia cuenta = em.find(CuentaReferencia.class, iban);
			
			if(cuenta == null) {
				
				throw new CuentaException();
			}
			
			return cuenta;
		}
	}
	

	

}
