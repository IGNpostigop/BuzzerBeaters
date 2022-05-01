package negocioEjb;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.informatica.sii.anotaciones.Requisitos;
import negocioEJBexcepcion.UsuarioException;

public class ClienteEJB {
	private ClientesEJB cliejb;
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
	
	@Before
	public void setup() {
		cliejb = new ClientesEJB();
	}
	
	@After
	public void teardown() {
		cliejb.close();
	}
	
	private Cliente clienteEjemplo() throws ParseException {
	
		String identificacion = "11111111F";
		Boolean estado = false;	
		Date fechaAlta = Date.valueOf("2012/03/20");	
		Date fechaBaja = null;
		String direccion = "C/ Pito, 23";
		String ciudad = "Malaga";
		Integer codigoPostal = 29000;
		String pais = "España";
		return new Cliente(Long.valueOf(1), identificacion, estado, fechaAlta, fechaBaja, direccion, ciudad, codigoPostal, pais);
		
	}
	
	@Requisitos({"RF2"}) 
	@Test
	public void insertarCliente (Cliente cliente) throws ParseException {
		Cliente cli = clienteEjemplo();	
		try {
			cliejb.crearCliente(cli);			
			List<Cliente> clientes = cliejb.getClientes();
			Cliente cliBd = clientes.get(0);
			assertEquals(cli.getCiudad(), cliBd.getCiudad());
			assertEquals(cli.getCodigopostal(), cliBd.getCodigopostal());
			assertEquals(cli.getDireccion(), cliBd.getDireccion());
			assertEquals(cli.getEstado(),cliBd.getEstado());
			assertEquals(cli.getFechaAlta(),cliBd.getFechaAlta());
			assertEquals(cli.getFechaBaja(), cliBd.getFechaBaja());
			assertEquals(cli.getIdentification(), cliBd.getIdentification());
			assertEquals(cli.getPais(), cliBd.getPais());
			assertEquals(cli.getId(), cliBd.getId());
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	@Requisitos({"RF3"})
	@Test (expected = UsuarioException.class)
	public void testModificarClienteERROR() throws ParseException, UsuarioException
	{
		Cliente cli = clienteEjemplo();
		cliejb.modificarCliente(cli, "11111111F", false, "C/ Pito, 23", "Malaga", 29000, "España");
	}
	
	@Requisitos({"RF3"})
	@Test
	public void testModificarClienteIgual() throws ParseException{
		try {
			Cliente cli = clienteEjemplo();
			cliejb.crearCliente(cli);
			Cliente modificado = cliejb.modificarCliente(cli, "11111111F", false, "C/ Pito, 23", "Malaga", 29000, "España");
			assertEquals(cli, modificado);
		
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
		
	}
	@Requisitos({"RF3"})
	@Test
	public void testModificarCliente() throws ParseException{
		try {
			Cliente cli = clienteEjemplo();
			cliejb.crearCliente(cli);
			Cliente modificado = cliejb.modificarCliente(cli, "22222222s", false, "C/ Pito, 24", "Milano", 41000, "Italia");
			assertNotEquals(cli, modificado);
		
		} catch (UsuarioException e) {
			fail("No debe lanzar excepcion");
		}
		
	}
	@Requisitos({"RF3"}) //ELIMINAR CLIENTE
	@Test
	public void testEliminarCliente() throws ParseException{
		try {
			Cliente cli = clienteEjemplo();
			cli.setEstado(false);
			cliejb.crearCliente(cli);
			assertEquals(cli.getEstado(), false);		
		}
		catch (UsuarioException e) {
			fail ("No debe lanzar excepción");
		}
	}
	

}
