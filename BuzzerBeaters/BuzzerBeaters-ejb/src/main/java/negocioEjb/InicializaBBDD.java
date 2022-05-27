package negocioEjb;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.Divisa;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.PooledAccount;
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
		if (comprobacion == null) { // si la base de datos está inicializada no la inicializa
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
	
			//inicializacion de PersonaAutorizada
			/*
			 * PersonaAutorizada pa = new PersonaAutorizada(); pa.setId(Long.valueOf(1));
			 * pa.setIdentification("22333333"); pa.setNombre("Fulanito");
			 * pa.setApellidos("DeTal"); pa.setDireccion("C/Pito");
			 * pa.setFecha_nacimiento(Date.valueOf("2000-03-27")); pa.setEstado(true);
			 * pa.setFechaInicio(Date.valueOf("2020-1-27"));
			 * pa.setFechaFin(Date.valueOf("2022-12-27")); pa.setUsuarioPA(usuario2);
			 * 
			 * em.persist(pa);
			 */
			//fin de inicilizacion de Persona Autorizada	
			
			//inicializacion de individual
		
			
			  Individual individual = new Individual(); String apellido = "DeTal"; String
			  identificacion = "11111111F"; Boolean estado = true; Date fechaAlta =
			  Date.valueOf("2012-03-20"); Date fechaNacimiento =Date.valueOf("2000-03-20");
			  String direccion = "C/ Pito, 23"; String ciudad = "Malaga"; Integer
			  codigoPostal = 29000; String pais = "España";
			  
			  individual.setName(usuario2.getUser());
			  individual.setIdentification(identificacion);
			  individual.setApellido(apellido); 
			  individual.setCiudad(ciudad);
			  individual.setCodigopostal(codigoPostal);
			  individual.setDireccion(direccion);
			  individual.setEstado(estado);
			  individual.setFecha_nacimiento(fechaNacimiento);
			  individual.setFechaAlta(fechaAlta);
			  individual.setDireccion(direccion); 
			  individual.setPais(pais);
			  individual.setUsuarioIndividual(usuario2);
			 
			  
			  em.persist(individual);
			 
			  
			  Divisa div = new Divisa();
			  div.setAbreviatura("EUR");
			  div.setCambioEuro(1.0);
			  div.setNombre("Euro");
			  div.setSimbolo('€');
			  div.setCuenta_referencia(null);
			  
//			  List<Divisa> divisas = new ArrayList<>();
//			  divisas.add(div);
			  
			  
			  CuentaReferencia cf = new CuentaReferencia();
			  cf.setDivisa(div);
			  cf.setEstado(true);
			  cf.setFecha_apertura(Date.valueOf("2013-10-5"));
			  cf.setIban("iban1234");
			  cf.setNombreBanco("uma");
			  cf.setPais("espana");
			  cf.setSaldo(0.0);
			  cf.setSucursal("sucur");
			  cf.setSwift("swiftseg");
			  cf.setReferenciadaDepositadaEn(null);
			  cf.setSegregada(null);
			  cf.setTransaccionesDestino(null);
			  cf.setTransaccionesOrigen(null);
			  
			  List<CuentaReferencia> referencias = new ArrayList<>();
			  referencias.add(cf);
			  
			  em.persist(cf);
			  
			  div.setCuenta_referencia(referencias);
			  em.persist(div);
			  
			  
			  PooledAccount pool = new PooledAccount();
			  pool.setClasificacion("Abierta");
			  pool.setCliente(individual);
			  pool.setEstado(true);
			  pool.setFecha_apertura(Date.valueOf("2001-02-13"));
			  pool.setFecha_cierre(null);
			  pool.setIban("iban");
			  pool.setPooledDepositadaEn(null);
			  pool.setSwift("switfff");
			  pool.setTransaccionesDestino(null);
			  pool.setTransaccionesOrigen(null);
			  
			  em.persist(pool);
			//fin inicilizacion de individual.
		}
		
	}

}
