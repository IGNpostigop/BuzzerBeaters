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
	private Boolean estado; 
	private Date fn; 
	private Date fi; 
	private Date fin;
	
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
	public GestionPersonasAutorizadas getPas() {
		return pas;
	}
	public void setPas(GestionPersonasAutorizadas pas) {
		this.pas = pas;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Date getFn() {
		return fn;
	}
	public void setFn(Date fn) {
		this.fn = fn;
	}
	public Date getFi() {
		return fi;
	}
	public void setFi(Date fi) {
		this.fi = fi;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	} 
	
	public String modificaPA() {
		
		admin = sesion.getUsuario();
		
		try {
			pa = paEJB.getPA(id); 
			
			pa.setIdentification(identificacion);
			pa.setNombre(nombre);
			pa.setApellidos(apellidos);
			pa.setEstado(estado);
			pa.setFecha_nacimiento(fn);
			pa.setFechaInicio(fi);
			pa.setFechaFin(fin);
			
			paEJB.modificarPersonaAutorizada(admin, pa);
			
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
