package negocioEjb;

//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.AutorizacionID;
import es.uma.BuzzerBeaters.CuentaFintech;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.AutorizacionExistenteException;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.PersonaAutorizadaException;
import negocioEJBexcepcion.PersonaAutorizadaSinAdmin;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Stateless
//@LocalBean
public class PersonasAutorizadasEJB implements GestionPersonasAutorizadas{
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(unitName ="BuzzerBeaters_ejb")
	private EntityManager em;
	
	@Override
	public void crearPersonaAutorizada(Usuario user,PersonaAutorizada pa) throws PersonaAutorizadaSinAdmin, UsuarioException {
		// TODO

		Usuario admin = em.find(Usuario.class, user.getUser());
		
		if (!admin.isAdministrador()) { 
			throw new PersonaAutorizadaSinAdmin("El usuario no es administrativo");
		}
		
		pa.setFechaInicio((Date.valueOf(LocalDate.now())));
		pa.setEstado(true);
		em.persist(pa);
		
//		Empresa emp = em.find(Empresa.class, cf.getCliente().getIdentification());
//
//		List<Autorizacion> autorizaciones = emp.getAutorizacion();
//		
//		for (PersonaAutorizada pa : listapa) {
//
//			Autorizacion aut = new Autorizacion();
//			aut.setTipo(null); //Se modificara despues cuando se elija el tipo
//
//			AutorizacionID idAut = new AutorizacionID();
//			idAut.setIdCliente(emp.getId());
//			idAut.setIdPersonaAutorizada(pa.getId());
//			
//			aut.setEmpresa(emp);
//			aut.setId(idAut);
//			aut.setPersonaAutorizada(pa);
//			em.persist(aut);
//			autorizaciones.add(aut);
//		}
//
//		em.merge(emp);
		

	}
	
	@Override
	public List<PersonaAutorizada> getPersonasAutorizadas() 
	{
		// TODO
		Query query = em.createQuery("SELECT p FROM PersonaAutorizada p");
		List<PersonaAutorizada> personasAutorizadas = query.getResultList();
		return personasAutorizadas;
		
	}


	/*@Override //usar merge
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
	}*/
	
	@Override
	public void modificarPersonaAutorizada(Usuario user, PersonaAutorizada pa) throws PersonaAutorizadaException, UsuarioException {
		Usuario admin = em.find(Usuario.class, user.getUser());
		
		if (!admin.isAdministrador()) { 
			throw new PersonaAutorizadaException("El usuario no es administrativo");
		}
		
		PersonaAutorizada PerAutEntity = em.find(PersonaAutorizada.class, pa.getId());
		
		if(PerAutEntity == null) {
			throw new UsuarioException("La persona autorizada no existe en la base de datos");
		}
		
		em.merge(pa);
		
		
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
	//RF8: Dar de baja persona Autorizada
	public void eliminarPersonaAutorizada(Usuario user, PersonaAutorizada pa) throws UsuarioException, UserNotAdminException
	{
		Usuario administrador = em.find(Usuario.class, user.getUser());
		
		if (administrador == null) { 
			throw new UsuarioException("El usuario no exsite");
		}

		if (!administrador.isAdministrador()) {
			throw new UserNotAdminException("El usuario no tiene los privilegios suficientes para la operación");
		}
		
		pa.setFechaFin(Date.valueOf(LocalDate.now()));
		pa.setEstado(false);	
		
		em.merge(pa);
	
		
	}
	
//	public void eliminarAutorizadoEmpresa(Usuario user, Long idPa, Long idEmpresa) throws UsuarioException, UserNotAdminException, 
//	PersonaAutorizadaException, ClienteDeBajaException, AutorizacionExistenteException, ClienteNoEncontradoException {
//		
//		Usuario admin = em.find(Usuario.class, user.getUser());
//		if (admin == null) {
//			throw new UsuarioException();
//		}
//		if (!admin.isAdministrador()) {
//			throw new UserNotAdminException();
//		}
//		
//		Autorizacion aut = new Autorizacion();
//		AutorizacionID autID = new AutorizacionID();
//		autID.setIdCliente(idEmpresa);
//		autID.setIdPersonaAutorizada(idPa);
//		
//		PersonaAutorizada perAutEntity = em.find(PersonaAutorizada.class, idPa);
//		Empresa empEntity = em.find(Empresa.class, idEmpresa);
//		aut.setEmpresa(empEntity);
//		aut.setId(autID);
//		aut.setPersonaAutorizada(perAutEntity);
//		
//		if(perAutEntity == null) {
//			throw new PersonaAutorizadaException("La persona autorizada no existe en la base de datos");
//		}
//		else if(empEntity == null) {
//			throw new ClienteNoEncontradoException();
//		}
//		else if(!empEntity.getEstado()) {
//			throw new ClienteDeBajaException();
//		}
//		else if(!perAutEntity.getAutorizacion().contains(aut)) {
//			throw new AutorizacionExistenteException();
//		}else {
//			List <Autorizacion> autorizaciones = empEntity.getAutorizacion();
//			autorizaciones.remove(aut);
//			empEntity.setAutorizacion(autorizaciones);
//			em.remove(aut);
//		}
//
//	}

	
	@Override
	//RF6: Añadir personas autorizadas a cuentas de tipo empresa
	public void addAutorizadoEmpresa(Usuario user, Long idPa, Long idEmpresa, String tipo) throws UsuarioException, UserNotAdminException, 
			PersonaAutorizadaException, ClienteDeBajaException, AutorizacionExistenteException, ClienteNoEncontradoException {
		
		Usuario admin = em.find(Usuario.class, user.getUser());
		if (admin == null) {
			throw new UsuarioException();
		}
		if (!admin.isAdministrador()) {
			throw new UserNotAdminException();
		}

		Autorizacion aut = new Autorizacion();
		AutorizacionID autID = new AutorizacionID();
		autID.setIdCliente(idEmpresa);
		autID.setIdPersonaAutorizada(idPa);
		
		PersonaAutorizada perAutEntity = em.find(PersonaAutorizada.class, idPa);
		Empresa empEntity = em.find(Empresa.class, idEmpresa);
		aut.setEmpresa(empEntity);
		aut.setId(autID);
		aut.setPersonaAutorizada(perAutEntity);
		aut.setTipo(tipo);
		
		if(perAutEntity == null) {
			throw new PersonaAutorizadaException("La persona autorizada no existe en la base de datos");
		}
		else if(empEntity == null) {
			throw new ClienteNoEncontradoException();
		}
		else if(!empEntity.getEstado()) {
			throw new ClienteDeBajaException();
		}
		else if(perAutEntity.getAutorizacion().contains(aut)) {
			throw new AutorizacionExistenteException();
		}else {
			List <Autorizacion> autorizaciones = empEntity.getAutorizacion();
			autorizaciones.add(aut);
			empEntity.setAutorizacion(autorizaciones);
			em.persist(aut);
		}

	}
	
	@Override
	public PersonaAutorizada getPA(Long id) throws PersonaAutorizadaException{
		
		PersonaAutorizada perAutEntity = em.find(PersonaAutorizada.class, id);
		
		if(perAutEntity == null) {
			throw new PersonaAutorizadaException("La persona autorizada no existe en la base de datos");
		}else {
			return perAutEntity;
		}
		
		
		
	}




}
