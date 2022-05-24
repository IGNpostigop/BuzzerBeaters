package es.uma.informatica.sii.buzzerbeaters.backing;

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
	
	private Usuario user;
	private PersonaAutorizada pa;
	private CuentaFintech cf;
	private List<PersonaAutorizada> listaAutorizaciones;
	
	AnyadirAutorizacion()
	{
		user = new Usuario();
		pa = new PersonaAutorizada();
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
			
			user = sesion.getUsuario();
			gestionPA.crearPersonaAutorizada(user,listaAutorizaciones,cf);
			return "paginaadmin.xhtml";
			
		}catch(UsuarioException e) {
			
			FacesMessage fm = new FacesMessage("El usuario existe");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion", fm);
			
		}catch(PersonaAutorizadaSinAdmin e) {
			
			FacesMessage fm = new FacesMessage("El cliente no es administrativo");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion", fm);
			
		}
		
		return null;
		
	}

}
