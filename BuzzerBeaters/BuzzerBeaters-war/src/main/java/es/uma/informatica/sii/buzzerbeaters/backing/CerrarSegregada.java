package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.PooledAccount;
import es.uma.BuzzerBeaters.Segregada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.CuentaConSaldo;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionCuentas;

@Named(value = "cerrarSegregada")
@RequestScoped

public class CerrarSegregada {
	
	@Inject
	InfoSesion sesion;
	
	@Inject
	GestionCuentas cerrarSejb;
	
	
	private String iban;


	public InfoSesion getSesion() {
		return sesion;
	}


	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}


	public GestionCuentas getCerrarSejb() {
		return cerrarSejb;
	}


	public void setCerrarSejb(GestionCuentas cerrarSejb) {
		this.cerrarSejb = cerrarSejb;
	}


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}

	public String cerrarSegregada() {
		
		Usuario user = new Usuario();
		user = sesion.getUsuario();
		
try {
			
			Segregada seg = cerrarSejb.buscarSegregada(this.getIban());
			
			cerrarSejb.cerrarCuenteSegregada(user, seg);
			
			FacesMessage fm = new FacesMessage("Cuenta segregada correctamente dada de baja.");
			FacesContext.getCurrentInstance().addMessage("cerrarSegregada:botonCerrar", fm);		
			return null;
			
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage("cerrarSegregada:botonCerrar", fm);
		} catch (UsuarioException e){
			FacesMessage fm = new FacesMessage("El usuario no exsite");
			FacesContext.getCurrentInstance().addMessage("cerrarSegregada:botonCerrar", fm);
		}catch (UserNotAdminException e){
			FacesMessage fm = new FacesMessage("El usuario no es administrativo");
			FacesContext.getCurrentInstance().addMessage("cerrarSegregada:botonCerrar", fm);
		}
		return null;
	 
	}
}
