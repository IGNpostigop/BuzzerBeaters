package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteExistenteException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.ClientesEJB;
import negocioEjb.GestionClientes;

@Named(value = "altaCIndividual")
@RequestScoped
public class AltaCIndividual 
{
	@Inject
	private InfoSesion sesion;
	@Inject
	private GestionClientes clienteInd;
	
	private Usuario user;
	private Individual ind;
	
	public AltaCIndividual() 
	{
		user = new Usuario();
		ind = new Individual();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Individual getInd() {
		return ind;
	}

	public void setInd(Individual ind) {
		this.ind = ind;
	}
	
	
	public String altaCIndividual() {
		try {
			
			user = sesion.getUsuario();
			clienteInd.crearClienteIndividual(user, ind);
			return "paginaadmin.xhtml";
			
		}catch (ClienteExistenteException e) {
			FacesMessage fm = new FacesMessage("El cliente que se intenta crear ya existe");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual", fm);
		}catch(UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tienee permisos de administrativo");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual", fm);
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("No se ha encontrado el usuario");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual", fm);
		}
		
		return null;
		
	}
	

}

