package negocioEjb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uma.BuzzerBeaters.Usuario;
import es.uma.informatica.sii.anotaciones.Requisitos;
import negocioEJBexcepcion.UsuarioException;

public class TUsuario {
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "BuzzerBeaters_ejb";
	private static final String USUARIOSEJB = "java:global/classes/UsuariosEJB";
	private GestionUsuarios gestionUsuarios;



	@Before
	public void setup() throws NamingException {
		gestionUsuarios = (GestionUsuarios) SuiteTest.ctx.lookup(USUARIOSEJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);	
	}
	
	
	
	@Requisitos({"RF1"})
	@Test
	public void testInsertarUsuario() throws UsuarioException {
		final String nombre = "manolo";
		final String clave = "claveManolo";
		final boolean admin = false;
		
		
		try {
			gestionUsuarios.insertarUsuario(nombre, clave, admin);
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
		List<Usuario> usuarios = gestionUsuarios.getUsuarios();
		Usuario bdUser = usuarios.get(0);
		assertEquals(nombre, bdUser.getUser());
		assertEquals(clave, bdUser.getPassword());
		assertEquals(admin, bdUser.isAdministrador());
		
	}

}
