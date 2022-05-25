package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.PooledAccount;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.CuentaConSaldo;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionCuentas;

@Named(value = "cerrarPooled")
@RequestScoped
public class CerrarPooled {
	
	@Inject
	InfoSesion sesion;
	
	@Inject
	GestionCuentas cerrarPAejb;
	
	
	private String iban;
	
	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public String cerrarPooled() {
		
		Usuario user = new Usuario();
		user = sesion.getUsuario();
		
		try {
			
			PooledAccount pa = cerrarPAejb.buscarPooled(this.getIban());
			
			cerrarPAejb.cerrarCuentaPooled(user, pa);
			
			return "paginaadmin.xhtml";
			
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage("cerrarPooled:botonCerrar", fm);
		} catch (UsuarioException e){
			FacesMessage fm = new FacesMessage("El usuario no exsite");
			FacesContext.getCurrentInstance().addMessage("cerrarPooled:botonCerrar", fm);
		}catch (UserNotAdminException e){
			FacesMessage fm = new FacesMessage("El usuario no es administrativo");
			FacesContext.getCurrentInstance().addMessage("cerrarPooled:botonCerrar", fm);
		}catch (CuentaConSaldo e){
			FacesMessage fm = new FacesMessage("La cuenta tiene saldo asociado");
			FacesContext.getCurrentInstance().addMessage("cerrarPooled:botonCerrar", fm);
		}
		return null;
	
	}

}
