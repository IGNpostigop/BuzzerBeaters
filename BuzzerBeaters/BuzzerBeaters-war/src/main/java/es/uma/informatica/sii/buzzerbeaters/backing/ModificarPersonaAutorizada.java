package es.uma.informatica.sii.buzzerbeaters.backing;

import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.PersonaAutorizadaException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionPersonasAutorizadas;

@Named(value = "modificarPA")
@RequestScoped

public class ModificarPersonaAutorizada {
	
	@Inject
	private InfoSesion sesion;
	
	@Inject 
	private GestionPersonasAutorizadas paEJB;
	
	private PersonaAutorizada pa; 
	private Usuario admin;
	private Long id; 
	private String identificacion; 
	private String nombre; 
	private String apellidos; 
	private String direccion; 
	private String fn; 
	
	public ModificarPersonaAutorizada() 
	{
		admin = new Usuario();
		pa = new PersonaAutorizada();
	}
	
	public InfoSesion getSesion() {
		return sesion;
	}
	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}
	
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	
	
	public GestionPersonasAutorizadas getPaEJB() {
		return paEJB;
	}

	public void setPaEJB(GestionPersonasAutorizadas paEJB) {
		this.paEJB = paEJB;
	}

	public PersonaAutorizada getPa() {
		return pa;
	}

	public void setPa(PersonaAutorizada pa) {
		this.pa = pa;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String modificarPAaccion() {
		
		admin = sesion.getUsuario();
		
		try {
			pa = paEJB.getPA(this.getId()); 
			pa.setIdentification(this.getIdentificacion());
			pa.setNombre(this.getNombre());
			pa.setApellidos(this.getApellidos());
			pa.setDireccion(this.getDireccion());
			pa.setFecha_nacimiento(Date.valueOf(this.getFn()));
			
			paEJB.modificarPersonaAutorizada(admin, pa);
			FacesMessage fm = new FacesMessage("Persona autorizada modificada.");
			FacesContext.getCurrentInstance().addMessage("modificarPA:boton", fm);
			return null;
			
		}catch (UsuarioException e) {
			FacesMessage fm = new FacesMessage("El usuario no existe");
			FacesContext.getCurrentInstance().addMessage("modificarPA:boton", fm);
		}catch(PersonaAutorizadaException e) {			
			FacesMessage fm = new FacesMessage("Persona autorizada inexistente");
			FacesContext.getCurrentInstance().addMessage("modificarPA:boton", fm);		
		}
		return "paginaadmin.xhtml";
	}
}
