package es.uma.informatica.sii.buzzerbeaters.backing;

import java.util.ArrayList;
import java.util.List;

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

@Named (value = "eliminarAutorizados")
@RequestScoped

public class EliminarAutorizados {
	
	@Inject 
	private InfoSesion sesion;
	@Inject
	private GestionPersonasAutorizadas gestPA;
	
	private Usuario admin; 
	private Long idPA;
	private Long idEmpresa;
	
	private String tipo;
	

	public InfoSesion getSesion() {
		return sesion;
	}

	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}

	public GestionPersonasAutorizadas getGestPA() {
		return gestPA;
	}

	public void setGestPA(GestionPersonasAutorizadas gestPA) {
		this.gestPA = gestPA;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	public Long getIdPA() {
		return idPA;
	}

	public void setIdPA(Long idPA) {
		this.idPA = idPA;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String eliminarAutorizado() {
		
		try {
		
		
		admin = sesion.getUsuario();
		gestPA.eliminarAutorizadoEmpresa(admin, idPA, idEmpresa, tipo);
		FacesMessage fm = new FacesMessage("Baja Correcta");
		FacesContext.getCurrentInstance().addMessage("addAut:botoneliminarAutorizacion", fm);
		return null;
		
		}
		catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("El usuario no existe");
			FacesContext.getCurrentInstance().addMessage("removeAut:botoneliminarAutorizacion", fm);		
		}
		catch(UserNotAdminException e) {			
			FacesMessage fm = new FacesMessage("El cliente no es administrativo");
			FacesContext.getCurrentInstance().addMessage("removeAut:botoneliminarAutorizacion", fm);		
		}
		catch(PersonaAutorizadaException e) {			
			FacesMessage fm = new FacesMessage("Persona autorizada inexistente");
			FacesContext.getCurrentInstance().addMessage("removeAut:botoneliminarAutorizacion", fm);		
		}
		catch(ClienteDeBajaException e) {			
			FacesMessage fm = new FacesMessage("Empresa de baja");
			FacesContext.getCurrentInstance().addMessage("removeAut:botoneliminarAutorizacion", fm);		
		}
		catch(AutorizacionExistenteException e) {			
			FacesMessage fm = new FacesMessage("La no autorización existe");
			FacesContext.getCurrentInstance().addMessage("removeAut:botoneliminarAutorizacion", fm);		
		}
		catch(ClienteNoEncontradoException e) {			
			FacesMessage fm = new FacesMessage("Empresas no existe");
			FacesContext.getCurrentInstance().addMessage("removeAut:botoneliminarAutorizacion", fm);		
		}
		return null;
	}

}
