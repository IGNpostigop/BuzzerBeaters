package es.uma.informatica.sii.buzzerbeaters.backing;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	private Usuario user;
	private Individual ind;
	
	private String name;
	private String apellido;
	private Long id;
	private String identification;
	private String direccion;
	private String ciudad;
	private Integer codigopostal;
	private String pais;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
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

	public AltaCIndividual() 
	{
		user = new Usuario();
		ind = new Individual();
	}

	public Usuario getUser() 
	{
		return user;
	}

	public void setUser(Usuario user) 
	{
		this.user = user;
	}

	public Individual getInd() 
	{
		return ind;
	}

	public void setInd(Individual ind) 
	{
		this.ind = ind;
	}
	
	
	public String altaCIndividual() {
		try {
			
			user = sesion.getUsuario();
			
			ind.setApellido(this.getApellido());
			ind.setCiudad(this.getCiudad());
			ind.setCodigopostal(this.getCodigopostal());
			ind.setDireccion(this.getDireccion());
			ind.setIdentification(this.getIdentification());
			ind.setId(this.getId());
			ind.setName(this.getName());
			ind.setPais(this.getPais());
			ind.setUsuarioIndividual(user);
			
			clienteInd.crearClienteIndividual(user, ind);
			return "paginaadmin.xhtml";
			
		}catch (ClienteExistenteException e) {
			FacesMessage fm = new FacesMessage("El cliente que se intenta crear ya existe");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual:botonAltaCIndiv", fm);
		}catch(UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tienee permisos de administrativo");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual:botonAltaCIndiv", fm);
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("No se ha encontrado el usuario");
			FacesContext.getCurrentInstance().addMessage("altaCIndividual:botonAltaCIndiv", fm);
		}
		
		return "paginaadmin.xhtml";
		
	}
	

}

