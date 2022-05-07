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
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;

@Stateless
public class ClientesEJB implements GestionClientes {
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManager em;
		

	@Override
	public void crearCliente(Cliente cliente) throws UsuarioException {

		Cliente clienteEntity = em.find(Cliente.class, cliente.getId());
		if(clienteEntity != null) {
			throw new UsuarioException("El cliente ya existe\n");
		}else {	
			em.persist(cliente);
		}
		
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
	public void bajaCliente(Cliente cliente) throws UsuarioException {
		// TODO Auto-generated method stub
		Cliente clienteEntity = em.find(Cliente.class, cliente.getId());
		if(clienteEntity == null) {
			throw new UsuarioException("El cliente no existe\n");
		}else {	
			clienteEntity.setEstado(false);
		}
		//em.persist(clienteEntity);	
	}
	
	@Override
	public Cliente modificarCliente(Cliente cliente, String identificacion, Boolean estado, String direccion, 
			String ciudad, Integer codigoPostal, String pais) throws UsuarioException {
		Cliente clienteEntity = em.find(Cliente.class, cliente);
		if(clienteEntity == null) {
			throw new UsuarioException("El cliente no existe\n");
		}else {	
		clienteEntity.setIdentification(identificacion);
		clienteEntity.setEstado(estado);
		clienteEntity.setDireccion(direccion);
		clienteEntity.setCiudad(ciudad);
		clienteEntity.setCodigopostal(codigoPostal);
		clienteEntity.setPais(pais);
		
		// TODO Auto-generated method stub
		}
		return clienteEntity;
	}
}


//	@Override
//	public void setClienteIdentificacion(String cliente, String identificacion) throws UsuarioException {
//		// TODO Auto-generated method stub
//		Cliente clienteEntity = em.find(Cliente.class, cliente);
//		if(clienteEntity == null) {
//			throw new UsuarioException("El cliente no existe\n");
//		}else {	
//			clienteEntity.setIdentification(identificacion);
//		}
//	}
//
//	@Override
//	public void setClienteDireccion(String cliente, String direccion, int codigoPostal, String pais, String ciudad) {
//		// TODO Auto-generated method stub
//		Cliente clienteEntity = em.find(Cliente.class, cliente);
//		if(clienteEntity == null) {
//			throw new UsuarioException("El cliente no existe\n");
//		}else {	
//			clienteEntity.setDireccion(direccion);
//		}
//		

