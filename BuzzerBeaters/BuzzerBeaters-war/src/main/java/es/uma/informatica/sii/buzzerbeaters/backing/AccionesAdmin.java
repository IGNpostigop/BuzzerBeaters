package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteExistenteException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;
import negocioEjb.GestionUsuarios;

@Named (value = "accionesAdmin")
@RequestScoped

public class AccionesAdmin {
	
	@Inject 
	private GestionClientes clientesEjb;
	
	@Inject
	private InfoSesion sesion;
	
	private Usuario usuario;
	private Cliente cliente;
	private Individual individual;
	private Empresa empresa;
	
	public AccionesAdmin() {
	}
	
	public String altaClienteIndividual() {
		try {
			clientesEjb.crearClienteIndividual(usuario, individual);
			return "altaCliente.xhtml";
		}catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("El usuario no existe");
			FacesContext.getCurrentInstance().addMessage("botonAltaIndividual", fm);
		}catch (UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tiene privilegios de administrador");
			FacesContext.getCurrentInstance().addMessage("botonAltaIndividual", fm);
		}catch (ClienteExistenteException e) {
			FacesMessage fm = new FacesMessage("El cliente que se pretende dar de alta ya existe");
			FacesContext.getCurrentInstance().addMessage("botonAltaIndividual", fm);
		}
		return null;
	}
	
	public String altaClienteEmpresa() {
		try {
			clientesEjb.crearClienteEmpresa(usuario, empresa);
			return "altaCliente.xhtml";
		}catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("El usuario no existe");
			FacesContext.getCurrentInstance().addMessage("botonAltaEmpresa", fm);
		}catch (UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tiene privilegios de administrador");
			FacesContext.getCurrentInstance().addMessage("botonAltaEmpresa", fm);
		}catch (ClienteExistenteException e) {
			FacesMessage fm = new FacesMessage("El cliente que se pretende dar de alta ya existe");
			FacesContext.getCurrentInstance().addMessage("botonAltaEmpresa", fm);
		}

		return null;
		
	}
	
	public String modificarClienteIndividual () {
		try {
			clientesEjb.modificarClienteIndividual(usuario,  individual.getIdentification(), individual);
			return "modificaCliente.xhtml";
		}catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("El usuario no existe");
			FacesContext.getCurrentInstance().addMessage("modificacionClienteIndividual", fm);
		}catch (UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tiene privilegios de administrador");
			FacesContext.getCurrentInstance().addMessage("modificacionClienteIndividual", fm);
		}catch (ClienteNoEncontradoException e) {
			FacesMessage fm = new FacesMessage("El cliente que se pretende modificar no existe");
			FacesContext.getCurrentInstance().addMessage("modificacionClienteIndividual", fm);
		}

		return null;
		
	}
	
	public String modificarClienteEmpresa () {
		try {
			clientesEjb.modificarClienteEmpresa(usuario,  empresa.getIdentification(), empresa);
			return "modificaCliente.xhtml";
		}catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("El usuario no existe");
			FacesContext.getCurrentInstance().addMessage("modificacionClienteEmpresa", fm);
		}catch (UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tiene privilegios de administrador");
			FacesContext.getCurrentInstance().addMessage("modificacionClienteEmpresa", fm);
		}catch (ClienteNoEncontradoException e) {
			FacesMessage fm = new FacesMessage("El cliente que se pretende modificar no existe");
			FacesContext.getCurrentInstance().addMessage("modificacionClienteEmpresa", fm);
		}

		return null;
		
	}
	
//	public String bajaCliente () {
//		try {
//			clientesEjb.bajaCliente(usuario, cliente);
//			return "bajaCliente.xhtml";
//		}catch (UsuarioException e) {
//			FacesMessage fm = new FacesMessage("El usuario no existe");
//			FacesContext.getCurrentInstance().addMessage("bajaCliente", fm);
//		}catch (UserNotAdminException e) {
//			FacesMessage fm = new FacesMessage("El usuario no tiene privilegios de administrador");
//			FacesContext.getCurrentInstance().addMessage("bajaCliente", fm);
//		}catch (ClienteNoEncontradoException e) {
//			FacesMessage fm = new FacesMessage("El cliente que se pretende dar de baja no existe");
//			FacesContext.getCurrentInstance().addMessage("bajaCliente", fm);
//		}catch (ClienteDeBajaException e) {
//			FacesMessage fm = new FacesMessage("El cliente ya de baja");
//			FacesContext.getCurrentInstance().addMessage("bajaCliente", fm);
//		}catch (CuentaException e) {
//			FacesMessage fm = new FacesMessage("El cliente tiene alguna cuenta abierta");
//			FacesContext.getCurrentInstance().addMessage("bajaCliente", fm);
//		}
//		
//
//		return null;
//		
//	}
	
	
		
		
}
		

