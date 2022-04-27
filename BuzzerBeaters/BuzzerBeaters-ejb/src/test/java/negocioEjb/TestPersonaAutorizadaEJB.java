package negocioEjb;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;

public class TestPersonaAutorizadaEJB {

	private PersonasAutorizadasEJB paejb;
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Before
	public void setup() {
		paejb = new PersonasAutorizadasEJB();
	}
	
	@After
	public void teardown() {
		paejb.close();
	}
	
	
	@SuppressWarnings({ "deprecation", "removal" })
	@Test
	public void testInsertarPersonaAutorizada() throws ParseException {
		Date f1 = dateformat.parse("21/03/2012");
		Date f2 = dateformat.parse("21/04/2022");
		Date f3 = dateformat.parse("20/03/2022");
		
		List<Autorizacion> autList = new ArrayList<Autorizacion>();
		Usuario user1 = new Usuario("user", "password", true);
		PersonaAutorizada pa = new PersonaAutorizada(new Long(1), "identificacion", "nombre","apellidos","direccion", f1, true, f2, f3, autList,user1);
		
		paejb.crearPersonaAutorizada(pa);
		
		List<PersonaAutorizada> personasAutorizadas = paejb.getPersonasAutorizadas();//Obtengo las personas autorizadas de la BD
		
		PersonaAutorizada pabd = personasAutorizadas.get(0);//Selecciono la primera que sera el sujeto de pruebas
		assertEquals("Solo hay un elemento en la bd, debe cumplir este assert", 1, personasAutorizadas.size());
		
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
	
}
