package negocioEjb;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;

public class ClientesEJB implements GestionClientes {
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	
	
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManager em;

	@Override
	public void insertarCliente(Usuario user, String identificacion, Boolean estado, String direccion, 
			String ciudad, Integer codigoPostal, String pais) throws UsuarioException {
		Cliente cliente = new Cliente();
		cliente.setUsuarioCliente(user);
		cliente.setIdentification(identificacion);
		cliente.setEstado(estado);
		cliente.setDireccion(direccion);
		cliente.setCiudad(ciudad);
		cliente.setCodigopostal(codigoPostal);
		cliente.setPais(pais);
		
		Cliente clienteEntity = em.find(Cliente.class, cliente);
		if(clienteEntity != null) {
			throw new UsuarioException("El cliente ya existe\n");
		}else {	
			em.persist(cliente);
		// TODO Auto-generated method stub
		}
		
	}
	@Override
	public void bajaCliente(Cliente cliente) throws UsuarioException {
		// TODO Auto-generated method stub
		Cliente clienteEntity = em.find(Cliente.class, cliente);
		if(clienteEntity == null) {
			throw new UsuarioException("El cliente no existe\n");
		}else {	
			clienteEntity.setEstado(false);
		}
		//em.persist(clienteEntity);	
	}
	
	@Override
	public void modificarCliente(Cliente cliente, String identificacion, Boolean estado, String direccion, 
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
			em.persist(cliente);
		// TODO Auto-generated method stub
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
	}


}
