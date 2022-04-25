package negocioEjb;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;



public class Usuarios {

	private static final Logger LOG = Logger.getLogger(Usuarios.class.getCanonicalName());

	private static final String USUARIOSEJB = "java:global/classes/UsuariosEJB";
	private static final String CLIENTESEJB = "java:global/classes/ClientesEJB";
	private static final String PERSONASAUTORIZADASEJB = "java:global/classes/PersonasAutorizadasEJB";
	private static final String CUENTASEJB = "java:global/classes/CuentasEJB";
	private static final String UNIDAD_PERSITENCIAPRUEBAS = "UsuariosTest";
	
	private GestionUsuarios GestUser;
	private GestionClientes GestCli;
	private GestionPersonasAutorizadas GestPerAut;
	private GestionCuentas GestCuent;
	
	@Before
	public void setup() throws NamingException  {
		GestUser = (GestionUsuarios) SuiteTest.ctx.lookup(USUARIOSEJB);
		GestCli = (GestionClientes) SuiteTest.ctx.lookup(CLIENTESEJB);
		GestPerAut = (GestionPersonasAutorizadas)SuiteTest.ctx.lookup(PERSONASAUTORIZADASEJB);
		GestCuent = (GestionCuentas) SuiteTest.ctx.lookup(CUENTASEJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIAPRUEBAS);
	}

}
