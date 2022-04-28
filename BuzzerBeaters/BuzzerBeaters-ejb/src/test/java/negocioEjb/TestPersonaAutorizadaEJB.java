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
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
	
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
		Date f1 = dateformat.parse("2012/03/20");
		Date f2 = dateformat.parse("2022/04/22");
		Date f3 = dateformat.parse("2063/03/10");
		
		List<Autorizacion> autList = new ArrayList<Autorizacion>();
		Usuario user1 = new Usuario("user", "password", true);
		PersonaAutorizada pa = new PersonaAutorizada(new Long(1), "identificacion", "nombre","apellidos","direccion", f1, true, f2, f3, autList,user1);
		
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
	
}
