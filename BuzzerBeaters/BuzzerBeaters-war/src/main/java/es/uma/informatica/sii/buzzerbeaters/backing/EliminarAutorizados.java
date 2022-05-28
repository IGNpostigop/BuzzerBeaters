package es.uma.informatica.sii.buzzerbeaters.backing;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.AutorizacionID;
import es.uma.BuzzerBeaters.DepositadaEn;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionPersonasAutorizadas;

@Named (value = "eliminarAutorizados")
@RequestScoped

public class EliminarAutorizados {
	
	@Inject 
	private InfoSesion sesion;
	@Inject
	private GestionPersonasAutorizadas gestPA;
	
	private Usuario admin; 
	private Long idPA;
	private Long idEmpresa;
	
	private String tipo;
	
	
	private List<PersonaAutorizada> auts; 

	public InfoSesion getSesion() {
		return sesion;
	}

	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}

	public GestionPersonasAutorizadas getGestPA() {
		return gestPA;
	}

	public void setGestPA(GestionPersonasAutorizadas gestPA) {
		this.gestPA = gestPA;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	public Long getIdPA() {
		return idPA;
	}

	public void setIdPA(Long idPA) {
		this.idPA = idPA;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String eliminarAutorizado() {
		
		try {
		Autorizacion auto = new Autorizacion();
		AutorizacionID autoId = new AutorizacionID();
		autoId.setIdCliente(idPA);
		
		PersonaAutorizada pa = new PersonaAutorizada();
		
		pa.setId(idPA); 
		
		admin = sesion.getUsuario();
		gestPA.eliminarAutorizadoEmpresa(pa, auto);
	}
	catch(UsuarioException e) {
		FacesMessage fm = new FacesMessage("El usuario existe");
		FacesContext.getCurrentInstance().addMessage("addAut:botonAut", fm);		
	}

}
