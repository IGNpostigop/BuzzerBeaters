package es.uma.informatica.sii.buzzerbeaters.backing;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEJBexcepcion.WrongPasswordException;
import negocioEjb.GestionUsuarios;

@Named(value = "adminLogin")
@RequestScoped

public class AdminLogin {
	
	@Inject
	private  GestionUsuarios usuarioEjb;
	
	@Inject
	private InfoSesion sesion;
	
	private Usuario usuario;
	
	public AdminLogin() {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario (Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String login() {
		
		try {
			usuarioEjb.AdminLogin(usuario.getUser(), usuario.getPassword());
			sesion.setUsuario(usuario);
			return "Ädministrativo.xhml";
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage("login:user", fm);
		}catch(WrongPasswordException e) {
			FacesMessage fm = new FacesMessage("Password incorrecto");
			FacesContext.getCurrentInstance().addMessage("login:pass", fm);		
		}catch(UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no es administrativo");
			FacesContext.getCurrentInstance().addMessage("login:tipo", fm);		
		}
		return null;
	}

}
