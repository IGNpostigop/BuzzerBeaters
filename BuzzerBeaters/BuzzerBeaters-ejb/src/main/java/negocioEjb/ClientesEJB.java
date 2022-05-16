package negocioEjb;

import java.io.Closeable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.CuentaFintech;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UsuarioException;

@Stateless
public class ClientesEJB implements GestionClientes {
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters_ejb")
	private EntityManager em;
		

	@Override
	public void crearClienteIndividual(Usuario admin, Cliente individual)
			throws UsuarioException {

		Individual clienteIndividualEntity = em.find(Individual.class, individual.getIdentification());

		Usuario administrador = em.find(Usuario.class, admin.getUser());

		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UsuarioException("El usuario no tiene los privilegios suficientes para la operación");
		}

		if (clienteIndividualEntity != null) {
			throw new UsuarioException("El cliente individual ya existe");
		}

		em.persist(individual);

	}
	
	@Override
	public void crearClienteEmpresa(Usuario admin, Cliente empresa)
			throws UsuarioException {

		Empresa clienteEmpresaEntity = em.find(Empresa.class, empresa.getIdentification());

		Usuario administrador = em.find(Usuario.class, admin.getUser());

		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UsuarioException("El usuario no tiene los privilegios suficientes para la operación");
		}

		if (clienteEmpresaEntity != null) {
			throw new UsuarioException("El cliente empresa ya existe");
		}

		em.persist(empresa);

	}
	@Override
	public List<Cliente> getClientes() 
	{
		// TODO
		Query query = em.createQuery("SELECT c FROM Cliente c");
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}
	
	@Override
	public void bajaCliente(Usuario admin, String idCliente) throws UsuarioException, ClienteNoEncontradoException, 
			ClienteDeBajaException, CuentaException {
		// TODO Auto-generated method stub
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UsuarioException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		
		Cliente clienteEntity = em.find(Cliente.class, idCliente);
		
		if(clienteEntity == null) {
			throw new ClienteNoEncontradoException("El cliente no existe\n");
		}else if(!clienteEntity.getEstado()) {
			throw new ClienteDeBajaException("El cliente ya está de baja");
		}else {
			List<CuentaFintech> cuentas = clienteEntity.getCuentasFintech();
			for(CuentaFintech cuentaFintech: cuentas) {
			
				if(cuentaFintech.getEstado()) {
					throw new CuentaException("cuenta "+ cuentaFintech.getIban() + " aun abierta");
				}
			}
			clienteEntity.setEstado(false);	
		}	
	}
	
	@Override
	public void activaCliente(Usuario admin, String idCliente) throws UsuarioException, ClienteNoEncontradoException, 
			ClienteDeBajaException {
		// TODO Auto-generated method stub
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UsuarioException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		
		Cliente clienteEntity = em.find(Cliente.class, idCliente);
		
		if(clienteEntity == null) {
			throw new ClienteNoEncontradoException("El cliente no existe\n");
		}else if(clienteEntity.getEstado()) {
			throw new ClienteDeBajaException("El cliente ya está activo");
		}else {	
			clienteEntity.setEstado(true);
		}

	}
	
	@Override
	public void modificarClienteIndividual(Usuario admin, String idCliente, Individual individual) throws UsuarioException, ClienteNoEncontradoException {
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UsuarioException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		Individual clienteIndividualEntity = em.find(Individual.class,idCliente);
		
		if(clienteIndividualEntity == null) {
			throw new ClienteNoEncontradoException("El cliente individual no existe");
			
			
		}
		em.merge(individual);
		
	}
	
	@Override
	public void modificarClienteEmpresal(Usuario admin, String idCliente, Empresa empresa) throws UsuarioException, ClienteNoEncontradoException {
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UsuarioException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		Empresa clienteEmpresaEntity = em.find(Empresa.class,idCliente);
		
		if(clienteEmpresaEntity == null) {
			throw new ClienteNoEncontradoException("El cliente individual no existe");
			
			
		}
		em.merge(empresa);
		
	}

}


