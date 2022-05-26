package es.uma.informatica.sii.buzzerbeaters.backing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.CuentaFintech;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.PersonaAutorizadaSinAdmin;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionPersonasAutorizadas;

@Named(value = "anyadirAutorizacion")
@RequestScoped
public class AnyadirAutorizacion 
{
	@Inject
	private InfoSesion sesion;
	@Inject
	private GestionPersonasAutorizadas gestionPA;
	
	private Usuario admin;
	private PersonaAutorizada pa;
	
	private CuentaFintech cf;
	private List<PersonaAutorizada> listaAutorizaciones;
	
	private Usuario userPA;
	private String nombreUsuario;
	private String pass;
	
	//private Long id; 
	private String identificacion; 
	private String nombre; 
	private String apellidos; 
	private String direccion; 
	private String fn; 
	private String fi; 
	//private String estado; 
	
	private SimpleDateFormat date; 
	
	AnyadirAutorizacion()
	{
		admin = new Usuario();
		pa = new PersonaAutorizada();
		date = new SimpleDateFormat("dd-MMM-yyyy")
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public PersonaAutorizada getPa() {
		return pa;
	}

	public void setPa(PersonaAutorizada pa) {
		this.pa = pa;
	}

	public CuentaFintech getCf() {
		return cf;
	}

	public void setCf(CuentaFintech cf) {
		this.cf = cf;
	}

	public List<PersonaAutorizada> getListaAutorizaciones() {
		return listaAutorizaciones;
	}

	public void setListaAutorizaciones(List<PersonaAutorizada> listaAutorizaciones) {
		this.listaAutorizaciones = listaAutorizaciones;
	}
	
	public String anyadirAutorizacion() {
		
		try {
			
			admin = sesion.getUsuario();
			
			userPA.setUser(nombreUsuario);
			userPA.setPassword(pass);
			userPA.setAdministrador(false);
			
			pa.setIdentification(identificacion);
			pa.setNombre(nombre);
			pa.setApellidos(apellidos);
			pa.setDireccion(direccion);
			
			Date fechNa = date.parse(fn); 
			pa.setFecha_nacimiento(fechNa); //Hay que importar java.util.Date en el jpa de persoAutorizada para que funcione
			
			Date fechIn = date.parse(fi); 
			pa.setFechaInicio(fechIn); //Hay que importar java.util.Date en el jpa de persoAutorizada para que funcione
			
			//pa.setEstado(true);
			
			gestionPA.crearPersonaAutorizada(admin,listaAutorizaciones,cf);
			return "paginaadmin.xhtml";
			
		}catch(UsuarioException e) {
			
			FacesMessage fm = new FacesMessage("El usuario existe");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonPA", fm);
			
		}catch(PersonaAutorizadaSinAdmin e) {
			
			FacesMessage fm = new FacesMessage("El cliente no es administrativo:botonPA");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonPA", fm);
			
		}
		
		return null;
		
	}

}
