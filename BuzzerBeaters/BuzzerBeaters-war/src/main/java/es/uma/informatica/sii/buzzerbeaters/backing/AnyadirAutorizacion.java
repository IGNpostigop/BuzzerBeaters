package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.AutorizacionExistenteException;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.PersonaAutorizadaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionPersonasAutorizadas;

@Named (value = "anyadirAutorizacion")
@RequestScoped

public class AnyadirAutorizacion {
	@Inject 
	private InfoSesion sesion;
	@Inject
	private GestionPersonasAutorizadas gestPA;

	
	private Usuario admin;
	
	private Long idPA, idEmpresa;
	private String tipo;

	
	
	public AnyadirAutorizacion() {

	}



	public Long getIdPA() {
		return idPA;
	}



	public void setIdPA(Long idPA) {
		this.idPA = idPA;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public Long getIdEmpresa() {
		return idEmpresa;
	}



	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	
	public String addAutorizacion() {
		try {
			admin = sesion.getUsuario();
			gestPA.addAutorizadoEmpresa(admin, idPA, idEmpresa, tipo);
			FacesMessage fm = new FacesMessage("Alta Correcta");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonanyadirAutorizacion", fm);
			return null;
		}
		catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("El usuario existe");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonanyadirAutorizacion", fm);		
		}
		catch(UserNotAdminException e) {			
			FacesMessage fm = new FacesMessage("El cliente no es administrativo");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonanyadirAutorizacion", fm);		
		}
		catch(PersonaAutorizadaException e) {			
			FacesMessage fm = new FacesMessage("Persona autorizada inexistente");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonanyadirAutorizacion", fm);		
		}
		catch(ClienteDeBajaException e) {			
			FacesMessage fm = new FacesMessage("Empresa de baja");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonanyadirAutorizacion", fm);		
		}
		catch(AutorizacionExistenteException e) {			
			FacesMessage fm = new FacesMessage("La autorización ya existe");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonanyadirAutorizacion", fm);		
		}
		catch(ClienteNoEncontradoException e) {			
			FacesMessage fm = new FacesMessage("Empresas no existe");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonanyadirAutorizacion", fm);		
		}
		
	
		return null;
	}
	
}
