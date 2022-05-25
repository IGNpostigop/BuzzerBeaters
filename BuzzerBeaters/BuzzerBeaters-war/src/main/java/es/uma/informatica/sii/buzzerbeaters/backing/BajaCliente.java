package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;

@Named (value = "bajaCliente")
@RequestScoped
public class BajaCliente {
	
	@Inject 
	private InfoSesion sesion;
	
	@Inject 
	private GestionClientes clientesEjb;
	
	private Usuario usuario;
	private Long id;
	
	
	
	public BajaCliente () {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public String baja()  {
		try {
			usuario = sesion.getUsuario();
			clientesEjb.bajaCliente(usuario, id );	
			FacesMessage fm = new FacesMessage("Cliente correctamente dado de baja");
			FacesContext.getCurrentInstance().addMessage("bajaCliente:botonbaja", fm);		
			return "bajaCliente.xhtml";
			
		}catch (UsuarioException e){
			FacesMessage fm = new FacesMessage("El usuario no existe");
			FacesContext.getCurrentInstance().addMessage("bajaCliente:ident", fm);	
		}catch (UserNotAdminException e){
			FacesMessage fm = new FacesMessage("El usuario no tiene privilegios");
			FacesContext.getCurrentInstance().addMessage("bajaCliente:ident", fm);	
		}catch (ClienteNoEncontradoException e) {
			FacesMessage fm = new FacesMessage("Cliente no encontrado");
			FacesContext.getCurrentInstance().addMessage("bajaCliente:ident", fm);	
		}catch (ClienteDeBajaException e) {
			FacesMessage fm = new FacesMessage("El cliente ya se encuentra de baja");
			FacesContext.getCurrentInstance().addMessage("bajaCliente:ident", fm);	
		}catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("Cliente con cuentas abiertas");
			FacesContext.getCurrentInstance().addMessage("bajaCliente:ident", fm);	
		}
		return null;
		
	}
	

}
