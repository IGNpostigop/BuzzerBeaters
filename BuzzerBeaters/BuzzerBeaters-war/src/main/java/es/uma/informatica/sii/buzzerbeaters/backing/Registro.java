package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionUsuarios;

@Named (value = "registro")
@SessionScoped
public class Registro {
	@Inject
	private GestionUsuarios gestionUsuario;
	private Usuario usuario;
	private String passwordReg;
	
	public void RegistrarUsuario() {
		try {
			if(!usuario.getPassword().equals(passwordReg)) {
				FacesMessage fm = new FacesMessage("Las contraseñas no coinciden");
				FacesContext.getCurrentInstance().addMessage("registro:usuario",  fm);
				
			}
			gestionUsuario.insertarUsuario(usuario);
		} catch (UsuarioException  e) {
			FacesMessage fm = new FacesMessage("Ya existe el usuario");
			FacesContext.getCurrentInstance().addMessage("registro:usuario", fm);
		}
	}
}
