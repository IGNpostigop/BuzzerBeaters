package negocioEjb.test;

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
import negocioEjb.GestionUsuarios;

public class TUsuario {
	
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "BuzzerBeatersE2Test";
	private static final String USUARIOSEJB = "java:global/classes/UsuariosEJB";
	private GestionUsuarios gestionUsuarios;



	@Before
	public void setup() throws NamingException {
		gestionUsuarios = (GestionUsuarios) SuiteTest.ctx.lookup(USUARIOSEJB);
		//BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);	
	}
	
	
	
	@Requisitos({"RF1"})
	@Test
	public void testInsertarUsuario() throws UsuarioException {
		final String nombre = "Fulanito";
		final String clave = "pass";
		final boolean admin = true;
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setUser(nombre);
		nuevoUsuario.setPassword(clave);
		nuevoUsuario.setAdministrador(admin);
		
		try {
			gestionUsuarios.insertarUsuario(nuevoUsuario);
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
