package negocioEjb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.NamingException;

import org.eclipse.persistence.jpa.jpql.Assert.AssertException;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.UserException;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import es.uma.informatica.sii.anotaciones.Requisitos;
import negocioEJBexcepcion.UsuarioException;

public class PersonaAutorizadaEJB {
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "BuzzerBeaters_ejb";
	private static final String PERSONASAUTORIZADASEJB = "java:global/classes/PersonasAutorizadasEJB";
	private static final String USUARIOSEJB = "java:global/classes/UsuariosEJB";
	private GestionUsuarios gestionUsuarios;
	private GestionPersonasAutorizadas gestionPA;
	
	@Before
	public void setup() throws NamingException {//lookup
		
		gestionPA = (GestionPersonasAutorizadas) SuiteTest.ctx.lookup(PERSONASAUTORIZADASEJB);
		gestionUsuarios = (GestionUsuarios) SuiteTest.ctx.lookup(USUARIOSEJB);
		
	}
	
	
	
	private PersonaAutorizada personaEjemplo() throws ParseException 
	{
		Date d1 = Date.valueOf("2020-03-27");
		Date d2 = Date.valueOf("2020-03-28");
		Date d3 = Date.valueOf("2020-03-29");

		List<Autorizacion> autList = new ArrayList<Autorizacion>();
		Usuario user1 = new Usuario("ELFUL", "ANO", true);
		PersonaAutorizada pa = new PersonaAutorizada(Long.valueOf(1),"12345678A", "FULANITO","DE TAL","CALLE PITO", 
				d1, true, d2, d3, autList,user1);
		
		return pa;
	}
	
	@Requisitos({"RF6"}) 
	@Test
	public void testInsertarPersonaAutorizada() throws ParseException, UsuarioException {
		
		
		
		PersonaAutorizada pa = personaEjemplo();
		
		gestionPA.crearPersonaAutorizada(pa);
		
		List<PersonaAutorizada> personasAutorizadas = gestionPA.getPersonasAutorizadas();//Obtengo las personas autorizadas de la BD
		
		PersonaAutorizada pabd = personasAutorizadas.get(0);//Selecciono la primera que sera el sujeto de pruebas
		
		assertEquals(pa.getNombre(), pabd.getNombre());
		assertEquals(pa.getApellidos(), pabd.getApellidos());
		assertEquals(pa.getAutorizacion(), pabd.getAutorizacion());
		assertEquals(pa.getDireccion(), pabd.getDireccion());
		assertEquals(pa.getEstado(), pabd.getEstado());
		assertEquals(pa.getFecha_nacimiento(), pabd.getFecha_nacimiento());
		assertEquals(pa.getFechaFin(), pabd.getFechaFin());
		assertEquals(pa.getFechaInicio(), pabd.getFechaInicio());
		assertEquals(pa.getId(), pabd.getId());
		assertEquals(pa.getIdentification(), pabd.getIdentification());
		assertEquals(pa.getUsuarioPA(), pabd.getUsuarioPA());
	}
	

	@Requisitos({"RF7"}) 
	@Test(expected = UsuarioException.class)
	public void testModificarPersonaAutorizadaERROR() throws ParseException, UsuarioException
	{
		
		PersonaAutorizada pa = personaEjemplo();
		gestionPA.modificarPersonaAutorizada(pa, "12345678A", "FULANITO", "DE TAL", true, Date.valueOf("2020-03-27"),Date.valueOf("2020-03-28"),Date.valueOf("2020-03-29"));
		//pa no existe en la bb.dd. por lo que al intentar modificarla debería saltar la excepción UsuarioException
		Exception exception = assertThrows(UserException.class,()-> gestionPA.modificarPersonaAutorizada(pa,"identif","nombreprueba","apellidosprueba",true,Date.valueOf("1500-01-01"),Date.valueOf("1600-01-01"),Date.valueOf("1700-01-01")));
		assertEquals("La persona autorizada no existe en la base de datos", exception.getMessage());
	}


	
	@Requisitos({"RF7"}) 
	@Test
	public void testModificarPersonaAutorizadaIGUAL() throws ParseException, UsuarioException
	{
		
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
		PersonaAutorizada paBd=gestionPA.getPersonasAutorizadas().get(0);
		gestionPA.modificarPersonaAutorizada(paBd, paBd.getIdentification(), "NombreModificado", paBd.getApellidos(),paBd.getEstado(),
				paBd.getFecha_nacimiento(), paBd.getFechaInicio(), paBd.getFechaFin());
		
		assertEquals(paBd.getNombre(), "NombreModificado");
		
	}
	/*

	@Requisitos({ "RF7" })
	@Test
	public void testModificarPersonaAutorizada() throws ParseException, UsuarioException {
		
		PersonaAutorizada pa = personaEjemplo();
		paejb.crearPersonaAutorizada(pa);
		PersonaAutorizada modificada = paejb.modificarPersonaAutorizada(pa, "noidentif", "nonombreprueba","noapellidosprueba", true, Date.valueOf("1500-01-01"),Date.valueOf("1600-01-01"),Date.valueOf("1700-01-01"));
		
		assertNotEquals(personaEjemplo(), modificada);

	}
*/
	
	//@Requisitos({"RF8"}) ELIMINAR PERSONA AUTORIZADA DE CUENTA
	
}


























