package es.uma.informatica.sii.buzzerbeaters.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;

public class BaseDatos {

	public static void inicializaBaseDatos(String nombreUnidadPersistencia, Map<String, String> properties) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia, properties);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		String identificacion = "1111111f";
		String nombre = "Fulanito";
		String apellido = "DETAL";
		String ciudad = "Malaga";
		int codigoPostal = 29018;
		Boolean estado = false;	
		Date fechaNacimiento = Date.valueOf("2000-03-20");	
		Date fechaAlta = Date.valueOf("2012-03-20");	
		Date fechaBaja = null;
		String direccion = "C/ Pito, 23";;
		String pais = "España";
		
		Individual individual = new Individual();
		individual.setApellido(apellido);
		individual.setCiudad(ciudad);
		individual.setCodigopostal(codigoPostal);
		individual.setDireccion(direccion);
		individual.setEstado(true);
		individual.setFecha_nacimiento(fechaNacimiento);
		individual.setFechaAlta(fechaAlta);
		individual.setIdentification(identificacion);
		individual.setName(nombre);
		individual.setPais(pais);
		em.persist(individual);
		
		Usuario usuario1 = new Usuario("fulanito", "suclave", false);
		Usuario usuario2 = new Usuario("manolito", "clave2", false);
		Usuario usuario3 = new Usuario("paquito", "clave3", true);
		em.persist(usuario1);
		em.persist(usuario2);
		em.persist(usuario3);
		
		
		PersonaAutorizada pa = new PersonaAutorizada(Long.valueOf(1), "22333333", "Fulanito", "DeTal", "C/Pito", Date.valueOf("2000-03-27"), 
				true, Date.valueOf("2020-1-27"),Date.valueOf("2022-12-27"), new ArrayList<Autorizacion>(), usuario1);
				
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
