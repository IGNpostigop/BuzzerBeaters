package negocioEjb;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		Date fechaAlta = Date.valueOf("2012/03/20");	
		Date fechaBaja = null;
		String direccion = "C/ Pito, 23";
		String ciudad = "Malaga";
		Integer codigoPostal = 29000;
		String pais = "España";
		
		Cliente cliente = new Cliente(Long.valueOf(1), identificacion, estado, fechaAlta, fechaBaja, direccion, ciudad, codigoPostal, pais);
		em.persist(cliente);
		
		Usuario usuario1 = new Usuario("fulanito", "suclave", false);
		Usuario usuario2 = new Usuario("manolito", "clave2", false);
		Usuario usuario3 = new Usuario("paquito", "clave3", true);
		em.persist(usuario1);
		em.persist(usuario2);
		em.persist(usuario3);
		
		
		PersonaAutorizada pa = new PersonaAutorizada(Long.valueOf(1), "22333333", "Fulanito", "DeTal", "C/Pito", Date.valueOf("2000-03-27"), 
				true, Date.valueOf("2020-1-27"),Date.valueOf("2022-12-27"), new ArrayList<Autorizacion>(), usuario1);
				
				
				
				
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}	
}
