package negocioEjb;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.DepositadaEn;
import es.uma.BuzzerBeaters.PooledAccount;
import es.uma.BuzzerBeaters.Segregada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Stateless
public class CuentasEJB implements GestionCuentas {
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters_ejb")
	private EntityManager em;
	
	
	@Override
	//RF5: Apertura de cuenta segregada
	public void aperturaCtaSegregated(Usuario admin, Segregada segregada) throws CuentaException, UsuarioException, UserNotAdminException {
		Segregada segBd = em.find(Segregada.class, segregada.getIban());
		
		Usuario administrador = em.find(Usuario.class, admin.getUser());

		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		
		if(segBd != null) {
			throw new CuentaException("La cuenta ya existe");
		}else {
			em.persist(segregada);
		}
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
			em.merge(pooled);
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
	//RF9: Cerrar de cuenta pooled
	public void cerrarCuentaPooled(PooledAccount pooled) throws CuentaException {
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
			}else {
				throw new CuentaException("Algun depósito con saldo");
		}
	}
		
	}
	
	
	public CuentaReferencia getCuentaReferencia(String iban) throws CuentaException{
		{
			CuentaReferencia cuenta = em.find(CuentaReferencia.class, iban);
			
			if(cuenta == null) {
				
				throw new CuentaException();
			}
			
			return cuenta;
		}
	}
	
//	public CuentaReferencia getCuentaReferencia(String iban) throws CuentaException{
//		TypedQuery<CuentaReferencia> query = em.createQuery("SELECT c FROM CuentaReferencia c where c.iban = :ibanR", CuentaReferencia.class);
//		query.setParameter("ibanR", iban);
//		CuentaReferencia cuenta = query.getSingleResult();
//		
//		if(cuenta == null) {
//			
//			throw new CuentaException();
//		}
//		
//		return cuenta;
//	}
	

}
