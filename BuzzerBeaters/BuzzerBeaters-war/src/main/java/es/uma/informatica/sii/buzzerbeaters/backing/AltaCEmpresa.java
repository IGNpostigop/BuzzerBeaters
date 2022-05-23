package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteExistenteException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;

@Named(value = "altaCEmpresa")
@RequestScoped
public class AltaCEmpresa 
{
	@Inject
	private InfoSesion sesion;
	@Inject
	private GestionClientes clienteEmp;
	
	private Usuario user;
	private Empresa emp;
	
	public AltaCEmpresa()
	{
		user = new Usuario();
		emp = new Empresa();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Empresa getEmp() {
		return emp;
	}

	public void setEmp(Empresa emp) {
		this.emp = emp;
	}
	
	public String altaCEmpresa() {
		try {
			
			user = sesion.getUsuario();
			clienteEmp.crearClienteEmpresa(user, emp);
			return "paginaadmin.xhtml";
			
		}catch (ClienteExistenteException e) {
			FacesMessage fm = new FacesMessage("La empresa que se intenta crear ya existe");
			FacesContext.getCurrentInstance().addMessage("altaCEmpresa", fm);
		}catch(UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tiene permisos de administrativo");
			FacesContext.getCurrentInstance().addMessage("altaCEmpresa", fm);
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("No se ha encontrado el usuario de empresa");
			FacesContext.getCurrentInstance().addMessage("altaCEmpresa", fm);
		}
		
		return null;
		
	}
}
