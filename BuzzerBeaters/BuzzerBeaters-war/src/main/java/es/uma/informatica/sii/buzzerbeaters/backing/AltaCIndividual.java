package es.uma.informatica.sii.buzzerbeaters.backing;



import java.text.ParseException;
import java.sql.Date;

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
import negocioEjb.GestionClientes;

@Named(value = "altaCIndividual")
@RequestScoped
public class AltaCIndividual 
{
	@Inject
	private InfoSesion sesion;
	@Inject
	private GestionClientes clienteInd;
	
	private Usuario admin;
	
	private Usuario userCliente;
	private Individual ind;
	
	private String nombreUsuario;
	private String pass;
	
	private String name;
	private String apellido;
	private String identificacion;
	private String direccion;
	private String ciudad;
	private Integer codigopostal;
	private String pais;
	private String fecha_nacimiento;


	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public AltaCIndividual() 
	{
		userCliente = new Usuario();
		ind = new Individual();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentification() {
		return identificacion;
	}

	public void setIdentification(String identification) {
		this.identificacion = identification;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getCodigopostal() {
		return codigopostal;
	}

	public void setCodigopostal(Integer codigopostal) {
		this.codigopostal = codigopostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public Individual getInd() 
	{
		return ind;
	}

	public void setInd(Individual ind) 
	{
		this.ind = ind;
	}
	
	
	public String altaCIndividualFun() {
		try {
			
			admin = sesion.getUsuario();
			
			ind.setApellido(this.getApellido());
			ind.setCiudad(this.getCiudad());
			ind.setCodigopostal(this.getCodigopostal());
			ind.setDireccion(this.getDireccion());
			ind.setIdentification(this.getIdentification());
			ind.setName(this.getName());
			ind.setPais(this.getPais());
			ind.setFecha_nacimiento(Date.valueOf(this.getFecha_nacimiento()));
			
		
			userCliente.setUser(nombreUsuario);
			userCliente.setPassword(pass);
			userCliente.setAdministrador(false);
			
			//Relacion
			ind.setUsuarioIndividual(userCliente);
			userCliente.setIndividual(ind);
			
			
			
			Long id = clienteInd.crearClienteIndividual(admin, ind);
			FacesMessage fm = new FacesMessage("El cliente se ha dado de alta correctamente con id "+id);
			FacesContext.getCurrentInstance().addMessage("altaCIndividual:botonAltaCIndiv", fm);
			return null;
			
		}catch (ClienteExistenteException e) {
			FacesMessage fm = new FacesMessage("El cliente que se intenta crear ya existe");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual:botonAltaCIndiv", fm);
		}catch(UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tiene permisos de administrativo");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual:botonAltaCIndiv", fm);
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("No se ha encontrado el usuario");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual:botonAltaCIndiv", fm);
		}
		
		return null;
		
	}
	

}

