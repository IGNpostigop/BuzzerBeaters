package negocioEjb;

import java.io.Closeable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.CuentaFintech;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteExistenteException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Stateless
public class ClientesEJB implements GestionClientes {
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters_ejb")
	private EntityManager em;
		

	@Override
	//RF2: Dar de alta a un cliente individual
	public void crearClienteIndividual(Usuario admin, Individual individual)
			throws UsuarioException, UserNotAdminException, ClienteExistenteException {

		Usuario administrador = em.find(Usuario.class, admin.getUser());

		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}

			individual.setFechaAlta(Date.valueOf(LocalDate.now()));
			individual.setEstado(true);
			em.persist(individual); 

	}
	
	@Override 
	//RF2: Dar de alta a un cliente empresa
	public void crearClienteEmpresa(Usuario admin, Cliente empresa)
			throws UsuarioException, UserNotAdminException, ClienteExistenteException  {

		Usuario administrador = em.find(Usuario.class, admin.getUser());

		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}

		//empresa.setFechaAlta(Date.valueOf(LocalDate.now()));
		empresa.setFechaAlta(Date.valueOf(LocalDate.now()));
		empresa.setEstado(true);
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
	//RF4: Dar de baja a un cliente
	public void bajaCliente(Usuario admin, Long clienteId) throws UsuarioException, ClienteNoEncontradoException, 
			ClienteDeBajaException, CuentaException, UserNotAdminException {
		// TODO Auto-generated method stub
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		
		Cliente clienteEntity = em.find(Cliente.class, clienteId);
		
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
			clienteEntity.setFechaBaja(Date.valueOf(LocalDate.now()));
			clienteEntity.setEstado(false);	
		}	
	}
	
	@Override
	public void activaCliente(Usuario admin, Long clienteId) throws UsuarioException, ClienteNoEncontradoException, 
			ClienteDeBajaException, UserNotAdminException {
		// TODO Auto-generated method stub
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		
		Cliente clienteEntity = em.find(Cliente.class, clienteId);
		
		if(clienteEntity == null) {
			throw new ClienteNoEncontradoException("El cliente no existe\n");
		}else if(clienteEntity.getEstado()) {
			throw new ClienteDeBajaException("El cliente ya está activo");
		}else {	
			clienteEntity.setEstado(true);
		}

	}
	
	@Override
	//RF3: Modificar los datos de un cliente individual
	public void modificarClienteIndividual(Usuario admin, Individual individual) throws UsuarioException, ClienteNoEncontradoException, UserNotAdminException {
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new  UserNotAdminException("El usuario no tiene permisos de admin");
		}

		em.merge(individual);
		
	}
	
	@Override
	//RF3: Modificar los datos de un cliente empresa
	public void modificarClienteEmpresa(Usuario admin, Empresa empresa) throws UsuarioException, ClienteNoEncontradoException, UserNotAdminException {
		
		Usuario administrador = em.find(Usuario.class, admin.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		em.merge(empresa);
		
	}

	
	
	@Override
	public Individual getClienteInd(Long id) throws ClienteNoEncontradoException{
		
		Individual cliente = em.find(Individual.class, id);
		
		if(cliente == null) {
			throw new ClienteNoEncontradoException();
		}
		
		return cliente;
	}
	
	@Override
	public Empresa getClienteEmp(Long id) throws ClienteNoEncontradoException{
		
		Empresa cliente = em.find(Empresa.class, id);
		
		if(cliente == null) {
			throw new ClienteNoEncontradoException();
		}
		
		return cliente;
	}
	

	@Override
	public Cliente getCliente(Long id) throws ClienteNoEncontradoException{
		
		Individual clienteind = em.find(Individual.class, id);
		Empresa clienteemp = em.find(Empresa.class, id);
		
		if(clienteind == null) 
		{
			
			if(clienteemp == null) 
			{
				throw new ClienteNoEncontradoException();
			}
			else
			{
				return clienteemp;
			}
			
		}else
		{
			return clienteind;
		}
		
		
	}
	
	
	
}


