package negocioEjb;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import negocioEJBexcepcion.UsuarioException;

@Stateless
//@LocalBean
public class PersonasAutorizadasEJB implements GestionPersonasAutorizadas{
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(unitName ="BuzzerBeaters_ejb")
	private EntityManager em;
	
	@Override
	public void crearPersonaAutorizada(PersonaAutorizada pAutorizada) {
		// TODO

		em.persist(pAutorizada);

	}
	
	@Override
	public List<PersonaAutorizada> getPersonasAutorizadas() 
	{
		// TODO
		Query query = em.createQuery("SELECT p FROM PersonaAutorizada p");
		List<PersonaAutorizada> personasAutorizadas = query.getResultList();
		return personasAutorizadas;
	}


	@Override //usar merge
	//RF7: Modificar persona Autorizada
	public PersonaAutorizada modificarPersonaAutorizada(PersonaAutorizada persAut, String identificacion, 
			String nombre, String apellidos,
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
	//RF8: Dar de baja persona Autorizada
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
	
	@Override
	//RF6: Añadir personas autorizadas a cuentas de tipo empresa
	public void addAutorizadoEmpresa(PersonaAutorizada persAut, Autorizacion autorizacion) throws UsuarioException {
		PersonaAutorizada PerAutEntity = em.find(PersonaAutorizada.class, persAut);
		if(persAut == null) {
			throw new UsuarioException("La persona autorizada no existe en la base de datos");
		}else {
			if(PerAutEntity.getAutorizacion().contains(autorizacion)) {
				throw new UsuarioException("La autorización ya existe en la base de datos");
			}else {
				PerAutEntity.getAutorizacion().add(autorizacion);
			}
		}
		
		// TODO Auto-generated method stub
		
	}




}
