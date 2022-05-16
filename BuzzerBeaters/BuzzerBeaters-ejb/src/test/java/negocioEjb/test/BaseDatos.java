package negocioEjb.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;

public class BaseDatos {

	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		String identificacion = "11111111F";
		Boolean estado = false;	
		Date fechaAlta = Date.valueOf("2012-03-20");	
		Date fechaBaja = null;
		String direccion = "C/ Pito, 23";
		String ciudad = "Malaga";
		Integer codigoPostal = 29000;
		String pais = "España";
		
		Cliente cliente = new Cliente();
		cliente.setIdentification(identificacion);
		cliente.setCiudad(ciudad);
		cliente.setEstado(estado);
		cliente.setFechaAlta(fechaAlta);
		cliente.setFechaBaja(fechaBaja);
		cliente.setDireccion(direccion);
		cliente.setCodigopostal(codigoPostal);
		cliente.setPais(pais);
		
		em.persist(cliente);
		
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		Usuario usuario3 = new Usuario();
		usuario1.setUser("Fulanito");
		usuario1.setPassword("pass");
		usuario1.setAdministrador(true);
		
		usuario2.setUser("Menganito");
		usuario2.setPassword("pass");
		usuario2.setAdministrador(false);
		
		usuario3.setUser("Paquito");
		usuario3.setPassword("pass");
		usuario3.setAdministrador(true);
		
		em.persist(usuario1);
		em.persist(usuario2);
		em.persist(usuario3);
		
		
		PersonaAutorizada pa = new PersonaAutorizada();
		
		pa.setId(Long.valueOf(1));
		pa.setIdentification("22333333");
		pa.setNombre("Fulanito");
		pa.setApellidos("DeTal");
		pa.setDireccion("C/Pito");
		pa.setFecha_nacimiento(Date.valueOf("2000-03-27"));
		pa.setEstado(true);
		pa.setFechaInicio(Date.valueOf("2020-1-27"));
		pa.setFechaFin(Date.valueOf("2022-12-27"));
		pa.setUsuarioPA(usuario2);
				
		em.persist(pa);		
				
				
		em.getTransaction().commit();
		
//		Query query = em.createQuery("SELECT u FROM USUARIO u");
//		List<Usuario> usuarios = query.getResultList();
//		for(int i =0; i< usuarios.size(); i++) {
//			System.out.println (usuarios.get(1).getUser());
//		}
//		
		em.close();
		emf.close();
		
	}	
}
