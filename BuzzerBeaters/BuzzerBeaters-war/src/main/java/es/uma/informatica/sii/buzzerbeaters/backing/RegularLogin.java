package es.uma.informatica.sii.buzzerbeaters.backing;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UserAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEJBexcepcion.WrongPasswordException;
import negocioEjb.GestionUsuarios;

@Named(value = "regularlogin")
@SessionScoped
public class RegularLogin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private GestionUsuarios usuarioEjb;
	
	@Inject
	private InfoSesion sesion;
	private Usuario usuario;
	
	public RegularLogin () {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String login()  {
		try {
			usuarioEjb.Login(usuario.getUser(), usuario.getPassword());	
			sesion.setUsuario(usuario);
			return "paginaprincipal.xhtml";
			}
		catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage("login:user", fm);
		

		} catch (WrongPasswordException e) {
			FacesMessage fm = new FacesMessage("La contraseña no es correcta");
			FacesContext.getCurrentInstance().addMessage("login:pass", fm);

		}
		catch (UserAdminException e) {
			FacesMessage fm = new FacesMessage("Usuario Administrativo, entre por el login de administrativos");
			FacesContext.getCurrentInstance().addMessage("login:pass", fm);
			// TODO: handle exception
		}
		return null;
		
	}
}
