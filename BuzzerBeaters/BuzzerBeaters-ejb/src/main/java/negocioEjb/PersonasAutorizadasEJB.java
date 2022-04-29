package negocioEjb;

import java.io.Closeable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;

public class PersonasAutorizadasEJB implements GestionPersonasAutorizadas,Closeable{
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public PersonasAutorizadasEJB() {
		emf = Persistence.createEntityManagerFactory("BuzzerBeaters");
		em = emf.createEntityManager();
	}
	
	@Override
	public void close() {
		em.close();
		emf.close();
	}
	
	
	public void crearPersonaAutorizada(PersonaAutorizada pAutorizada) {
		// TODO
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		em.persist(pAutorizada);
		tx.commit();
	}
	
	public List<PersonaAutorizada> getPersonasAutorizadas() 
	{
		// TODO
		Query query = em.createQuery("SELECT p FROM PersonaAutorizada p");
		List<PersonaAutorizada> personasAutorizadas = query.getResultList();
		return personasAutorizadas;
	}


	@Override
	public PersonaAutorizada modificarPersonaAutorizada(PersonaAutorizada persAut, String identificacion, String nombre, String apellidos,
			Boolean estado, Date fechaNacimiento, Date fechaInicio, Date fechaFin) throws UsuarioException {
		// TODO Auto-generated method stub
		
		PersonaAutorizada PerAutEntity = em.find(PersonaAutorizada.class, persAut.getId());
		if(PerAutEntity == null) {
			throw new UsuarioException("La persona autorizada no existe en la base de datos");
		}else {
			PerAutEntity.setIdentification(identificacion);
			PerAutEntity.setNombre(nombre);
			PerAutEntity.setApellidos(apellidos);
			PerAutEntity.setEstado(estado);
			PerAutEntity.setFecha_nacimiento(fechaNacimiento);
			PerAutEntity.setFechaFin(fechaFin);
			PerAutEntity.setFechaInicio(fechaInicio);
		}
		
		return PerAutEntity;
	}
	

	@Override
	public void eliminarAutorizadoEmpresa(PersonaAutorizada persAut, Autorizacion autorizacion) throws UsuarioException {
		PersonaAutorizada PerAutEntity = em.find(PersonaAutorizada.class, persAut);
		if(persAut == null) {
			throw new UsuarioException("La persona autorizada no existe en la base de datos");
		}else {
			if(PerAutEntity.getAutorizacion().contains(autorizacion)) {
				PerAutEntity.getAutorizacion().remove(autorizacion);
			}else {
				throw new UsuarioException("La autorizacion que se pretende eliminar no existe en la persona autorizada");
			}
		}
		
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean consultarPersonaAutorizada(PersonaAutorizada aut) throws UsuarioException {
		PersonaAutorizada PerAutEntity = em.find(PersonaAutorizada.class, aut);
		boolean bol=true;
		if(PerAutEntity == null) {
			bol = false;
		}
		return bol;
	}
	


}
