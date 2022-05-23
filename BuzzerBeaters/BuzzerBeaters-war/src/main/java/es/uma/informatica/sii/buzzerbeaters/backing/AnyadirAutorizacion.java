package es.uma.informatica.sii.buzzerbeaters.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
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

	public List<PersonaAutorizada> getListaAutorizaciones() {
		return listaAutorizaciones;
	}

	public void setListaAutorizaciones(List<PersonaAutorizada> listaAutorizaciones) {
		this.listaAutorizaciones = listaAutorizaciones;
	}
	
	public String anyadirAutorizacion() {
		
		try {
			
			user = sesion.getUsuario();
			gestionPA.crearPersonaAutorizada(pa);
			return "paginaprincipalAdmin.xhtml";
			
		}catch(UsuarioException e) {//Revisar si esto esta correcto antes de entregar
			
			FacesMessage fm = new FacesMessage("La persona ya estaba autorizada");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion", fm);
			
		}
		
		return null;
		
	}

}
