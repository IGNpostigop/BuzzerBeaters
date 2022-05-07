package negocioEjb;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.sql.Date;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.informatica.sii.anotaciones.Requisitos;
import negocioEJBexcepcion.UsuarioException;

public class ClienteEJB {
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "BuzzerBeaters_ejb";
	private static final String CLIENTESEJB = "java:global/classes/ClientesEJB";
	private GestionClientes gestionClientes;
	

	
	@Before
	public void setup() throws NamingException {
		gestionClientes = (GestionClientes) SuiteTest.ctx.lookup(CLIENTESEJB);
		
	}
	
	/*
	 * @After public void teardown() { cliejb.close(); }
	 */
	
	
	private Cliente clienteEjemplo() throws ParseException {
	
		String identificacion = "11111111F";
		Boolean estado = false;	
		Date fechaAlta = Date.valueOf("2012-03-20");	
		Date fechaBaja = null;
		String direccion = "C/ Pito, 23";
		String ciudad = "Malaga";
		Integer codigoPostal = 29000;
		String pais = "España";
		return new Cliente(Long.valueOf(1), identificacion, estado, fechaAlta, fechaBaja, direccion, ciudad, codigoPostal, pais);
		
	}
	
	@Requisitos({"RF2"}) 
	@Test
	public void insertarCliente () throws ParseException {
		Cliente cli = clienteEjemplo();	
	
		try {
			gestionClientes.crearCliente(cli);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
		List<Cliente> clientes = gestionClientes.getClientes();
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
					
	}
	
	  
	  @Requisitos({"RF3"})
	  @Test 
	  public void testModificarCliente() throws ParseException{ 
		  try {
			  //gestionClientes.crearCliente(clienteEjemplo());
			  Cliente clibd, cli = gestionClientes.getClientes().get(0);
			  gestionClientes.modificarCliente(cli,"11111111F", false, "C/ Pito, 23", "Sevilla", 41000, "España");
			  clibd = gestionClientes.getClientes().get(0);
			  assertEquals(clibd.getCiudad(), "Sevilla");
			  
		  }catch (UsuarioException e) { 
				fail ("No debe lanzar excepción");
		  }
	  
	  }
	  
	@Requisitos({"RF3"}) //ELIMINAR CLIENTE

	@Test 
	public void testEliminarCliente() throws ParseException{ 
		try { 
			gestionClientes.crearCliente(clienteEjemplo());
			Cliente cli = gestionClientes.getClientes().get(0);
			gestionClientes.bajaCliente(cli);
			assertEquals(cli.getEstado(), false); 
			}
		catch (UsuarioException e) { 
			fail ("No debe lanzar excepción");
			}
		}

	

}
