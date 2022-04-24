package negocioEjb;

import java.sql.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Usuario;

public class PersonasAutorizadasEJB implements GestionPersonasAutorizadas{
	
	private static final Logger LOG = Logger.getLogger(UsuariosEJB.class.getCanonicalName());
	
	@PersistenceContext(name="BuzzerBeaters")
	private EntityManager em;


	@Override
	public void insertarPersonaAutorizada(Usuario user, String identificacion, String nombre, String apellidos,
			Boolean estado, Date fechaNacimiento, Date fechaInicio, Date fechaFin, Empresa empresa) {		
		// TODO Auto-generated method stub
		
	}

}
