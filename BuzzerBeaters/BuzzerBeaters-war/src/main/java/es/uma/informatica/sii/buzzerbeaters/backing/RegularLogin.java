package es.uma.informatica.sii.buzzerbeaters.backing;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;
import negocioEJBexcepcion.WrongPasswordException;
import negocioEjb.GestionUsuarios;

@Named(value = "regularlogin")
@SessionScoped
public class RegularLogin implements Serializable{
	@Inject
	private GestionUsuarios gestionUsuario;
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
			gestionUsuario.Login(usuario.getUser(), usuario.getPassword());			
			}
		catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage("login:usuario", fm);
			e.printStackTrace();
			// TODO: handle exception
		} catch (WrongPasswordException e) {
			FacesMessage fm = new FacesMessage("La contraseña no es correcta");
			FacesContext.getCurrentInstance().addMessage("login:usuario", fm);
			e.printStackTrace();
		}
		return "paginaprincipal.xhtml";
	}
}
