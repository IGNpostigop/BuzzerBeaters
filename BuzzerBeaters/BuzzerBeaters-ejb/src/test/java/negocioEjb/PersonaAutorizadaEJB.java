package negocioEjb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import es.uma.informatica.sii.anotaciones.Requisitos;
import negocioEJBexcepcion.UsuarioException;

public class PersonaAutorizadaEJB {

	private PersonasAutorizadasEJB paejb;
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
	
	@Before
	public void setup() {//lookup
		try {
			paejb = (PersonasAutorizadasEJB) SuiteTest.ctx.lookup("java:global/classes/PersonasAutorizadasEJB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//gestionProductos = (GestionProductos) SuiteTest.ctx.lookup(PRODUCTOS_EJB);
		//paejb = new PersonasAutorizadasEJB();
	}
	
	
	
	private PersonaAutorizada personaEjemplo() throws ParseException 
	{
		Date d1 = Date.valueOf("2020-03-27");
		Date d2 = Date.valueOf("2020-03-28");
		Date d3 = Date.valueOf("2020-03-29");

		List<Autorizacion> autList = new ArrayList<Autorizacion>();
		Usuario user1 = new Usuario("ELFUL", "ANO", true);
		PersonaAutorizada pa = new PersonaAutorizada(Long.valueOf(1),"12345678A", "FULANITO","DE TAL","CALLE PITO", d1, true, d2, d3, autList,user1);
		
		return pa;
	}
	
	@Requisitos({"RF6"}) 
	@Test
	public void testInsertarPersonaAutorizada() throws ParseException {
		
		
		PersonaAutorizada pa = personaEjemplo();
		
		paejb.crearPersonaAutorizada(pa);
		
		List<PersonaAutorizada> personasAutorizadas = paejb.getPersonasAutorizadas();//Obtengo las personas autorizadas de la BD
		
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
		paejb.modificarPersonaAutorizada(pa,"identif","nombreprueba","apellidosprueba",true,Date.valueOf("1500-01-01"),Date.valueOf("1600-01-01"),Date.valueOf("1700-01-01"));
		
	}

	
	@Requisitos({"RF7"}) 
	@Test
	public void testModificarPersonaAutorizadaIGUAL() throws ParseException, UsuarioException
	{
		PersonaAutorizada pa = personaEjemplo();
		paejb.crearPersonaAutorizada(pa);
		PersonaAutorizada modificada = paejb.modificarPersonaAutorizada(pa,"identif","nombreprueba","apellidosprueba",true,Date.valueOf("1500-01-01"),Date.valueOf("1600-01-01"),Date.valueOf("1700-01-01"));
		
		assertEquals(pa, modificada);
		
	}

	@Requisitos({ "RF7" })
	@Test
	public void testModificarPersonaAutorizada() throws ParseException, UsuarioException {
		
		PersonaAutorizada pa = personaEjemplo();
		paejb.crearPersonaAutorizada(pa);
		PersonaAutorizada modificada = paejb.modificarPersonaAutorizada(pa, "noidentif", "nonombreprueba","noapellidosprueba", true, Date.valueOf("1500-01-01"),Date.valueOf("1600-01-01"),Date.valueOf("1700-01-01"));
		
		assertNotEquals(personaEjemplo(), modificada);

	}

	
	//@Requisitos({"RF8"}) ELIMINAR PERSONA AUTORIZADA DE CUENTA
	
}


























