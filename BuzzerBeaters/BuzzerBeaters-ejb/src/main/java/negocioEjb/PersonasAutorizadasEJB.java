package negocioEjb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;

public class PersonasAutorizadasEJB implements GestionPersonasAutorizadas{
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManager em;


	@Override
	public void insertarPersonaAutorizada(Cliente cliente, String identificacion, String nombre, String apellidos,
		Boolean estado, Date fechaNacimiento, Date fechaInicio, Date fechaFin, Empresa empresa) throws UsuarioException {		
		PersonaAutorizada personaAutorizada = new PersonaAutorizada();
		personaAutorizada.setApellidos(apellidos);
		personaAutorizada.setFecha_nacimiento(fechaNacimiento);
		personaAutorizada.setNombre(nombre);
		personaAutorizada.setIdentification(identificacion);
		personaAutorizada.setEstado(estado);
		personaAutorizada.setFechaInicio(fechaInicio);
		personaAutorizada.setFechaFin(fechaFin);
		Autorizacion aut = new Autorizacion();
		
		aut.setEmpresa(empresa);
		aut.setPersonaAutorizada(personaAutorizada);

		
		PersonaAutorizada PerAutEntity = em.find(PersonaAutorizada.class, personaAutorizada);
		if(PerAutEntity != null) {
			List <Autorizacion> autorizaciones = PerAutEntity.getAutorizacion();
			if(!autorizaciones.contains(aut)) {
				autorizaciones.add(aut);
				em.persist(PerAutEntity);
			}else {
				throw new UsuarioException("El cliente ya tiene la autorizacion que se intenta añadir\n");
			}
			
		}else {
			List <Autorizacion> autorizaciones = new ArrayList <>();
			autorizaciones.add(aut);
			personaAutorizada.setAutorizacion(autorizaciones);
			em.persist(personaAutorizada);

		}
		
	}


	@Override
	public void modificarPersonaAutorizada(PersonaAutorizada persAut, String identificacion, String nombre, String apellidos,
			Boolean estado, Date fechaNacimiento, Date fechaInicio, Date fechaFin) throws UsuarioException {
		// TODO Auto-generated method stub
		PersonaAutorizada PerAutEntity = em.find(PersonaAutorizada.class, persAut);
		if(persAut == null) {
			throw new UsuarioException("La persona autorizada no existe en la base de datos");
		}else {
			persAut.setApellidos(apellidos);
			persAut.setEstado(estado);
			persAut.setFecha_nacimiento(fechaNacimiento);
			persAut.setFechaFin(fechaFin);
			persAut.setFechaInicio(fechaInicio);
		}
		
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

}
