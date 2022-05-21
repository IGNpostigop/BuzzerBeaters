package negocioEjb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.BuzzerBeaters.Usuario;

@Singleton
@Startup
public class InicializaBBDD {
	private final String PERSISTENCE_CONTEXT = "BuzzerBeaters_ejb";
	
	
	@PersistenceContext(unitName = PERSISTENCE_CONTEXT)
	
	private EntityManager em;
	
	@PostConstruct
	
	private void iniicializar() {
		Usuario comprobacion = em.find(Usuario.class, "Fulanito");
		if (comprobacion != null) {
			return;
		}
		//iniciliza usuarios
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		Usuario usuario3 = new Usuario();
		usuario1.setUser("Fulanito");
		usuario1.setPassword("pass");
		usuario1.setAdministrador(true);
		
		usuario2.setUser("Menganito");
		usuario2.setPassword("pass");
		usuario2.setAdministrador(false);
		
		usuario3.setUser("Paquito");
		usuario3.setPassword("pass");
		usuario3.setAdministrador(true);
		
		em.persist(usuario1);
		em.persist(usuario2);
		em.persist(usuario3);
		//fin de inicializa usuarios
		
	}

}
