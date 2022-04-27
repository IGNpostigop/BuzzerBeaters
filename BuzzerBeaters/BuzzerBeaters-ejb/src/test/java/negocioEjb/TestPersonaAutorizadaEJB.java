package negocioEjb;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import negocioEJBexcepcion.UsuarioException;

public class TestPersonaAutorizadaEJB {

	@Test
	public void testInsertarPersonaAutorizada() throws UsuarioException {
		PersonasAutorizadasEJB perAu = new PersonasAutorizadasEJB();
		Cliente cliente1 = new Cliente();
		Empresa emp = new Empresa();
		perAu.insertarPersonaAutorizada(cliente1,"h","h","h",true,null);
		PersonaAutorizada perAuCom = new PersonaAutorizada();
		perAuCom.setApellidos("h");
		perAuCom.setNombre("h");
		perAuCom.setIdentification("h");
		perAuCom.setEstado(true);
		
		
		Assert.assertFalse(perAu.consultarPersonaAutorizada(perAuCom));
	}

}
