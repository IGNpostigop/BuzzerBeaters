package es.uma.informatica.sii.buzzerbeaters.backing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.CuentaFintech;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.PersonaAutorizadaSinAdmin;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionPersonasAutorizadas;

@Named(value = "anyadirAutorizacion")
@RequestScoped
public class AnyadirAutorizacion 
{
	@Inject
	private InfoSesion sesion;
	@Inject
	private GestionPersonasAutorizadas gestionPA;
	
	private Usuario admin;
	private PersonaAutorizada pa;
	
	private CuentaFintech cf;
	private List<PersonaAutorizada> listaAutorizaciones;
	
	private Usuario userPA;
	private String nombreUsuario;
	private String pass;
	
	//private Long id; 
	private String identificacion; 
	private String nombre; 
	private String apellidos; 
	private String direccion; 
	private String fn; 
	private String fi; 
	//private String estado; 
	
	private SimpleDateFormat date; 
	
	AnyadirAutorizacion()
	{
		admin = new Usuario();
		pa = new PersonaAutorizada();
		date = new SimpleDateFormat("dd-MMM-yyyy"); //Formato en el que se deben de meter las fechas
	}


	public InfoSesion getSesion() {
		return sesion;
	}


	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}


	public GestionPersonasAutorizadas getGestionPA() {
		return gestionPA;
	}


	public void setGestionPA(GestionPersonasAutorizadas gestionPA) {
		this.gestionPA = gestionPA;
	}


	public Usuario getAdmin() {
		return admin;
	}


	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}


	public PersonaAutorizada getPa() {
		return pa;
	}


	public void setPa(PersonaAutorizada pa) {
		this.pa = pa;
	}


	public CuentaFintech getCf() {
		return cf;
	}


	public void setCf(CuentaFintech cf) {
		this.cf = cf;
	}


	public List<PersonaAutorizada> getListaAutorizaciones() {
		return listaAutorizaciones;
	}


	public void setListaAutorizaciones(List<PersonaAutorizada> listaAutorizaciones) {
		this.listaAutorizaciones = listaAutorizaciones;
	}


	public Usuario getUserPA() {
		return userPA;
	}


	public void setUserPA(Usuario userPA) {
		this.userPA = userPA;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
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


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getFn() {
		return fn;
	}


	public void setFn(String fn) {
		this.fn = fn;
	}


	public String getFi() {
		return fi;
	}


	public void setFi(String fi) {
		this.fi = fi;
	}


	public SimpleDateFormat getDate() {
		return date;
	}


	public void setDate(SimpleDateFormat date) {
		this.date = date;
	}


	public String anyadirAutorizacion() throws ParseException {
		
		try {
			
			admin = sesion.getUsuario();
			
			userPA.setUser(nombreUsuario);
			userPA.setPassword(pass);
			userPA.setAdministrador(false);
			
			pa.setIdentification(identificacion);
			pa.setNombre(nombre);
			pa.setApellidos(apellidos);
			pa.setDireccion(direccion);
			
			Date fechNa = date.parse(fn); 
			pa.setFecha_nacimiento(fechNa); //Hay que importar java.util.Date en el jpa de persoAutorizada para que funcione
			
			Date fechIn = date.parse(fi); 
			pa.setFechaInicio(fechIn); //Hay que importar java.util.Date en el jpa de persoAutorizada para que funcione
			
			//pa.setEstado(true);
			
			gestionPA.crearPersonaAutorizada(admin,listaAutorizaciones,cf);
			return "paginaadmin.xhtml";
			
		}catch(UsuarioException e) {
			
			FacesMessage fm = new FacesMessage("El usuario existe");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonPA", fm);
			
		}catch(PersonaAutorizadaSinAdmin e) {
			
			FacesMessage fm = new FacesMessage("El cliente no es administrativo:botonPA");
			FacesContext.getCurrentInstance().addMessage("anyadirAutorizacion:botonPA", fm);
			
		}
		
		return null;
		
	}

}
