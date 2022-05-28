package es.uma.informatica.sii.buzzerbeaters.backing;

import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;

@Named(value = "modificarCIndividual")
@RequestScoped
public class ModificarCIndividual {
	
	@Inject
	private GestionClientes modificarEJB;
	@Inject
	private InfoSesion sesion;

	private Usuario user;
	private Individual ind;
	private String fecha_nacimiento;
	private Long id;
	private String nombre;
	private String apellido;
	private String identification;
	private String direccion;
	private String ciudad;
	private Integer codigopostal;
	private String pais;
	
	
	public ModificarCIndividual() 
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
	
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String modCIndividual() {
		

			user = sesion.getUsuario();
			
			try {
				
				ind = modificarEJB.getClienteInd(id);
				
					ind.setFecha_nacimiento(Date.valueOf(this.getFecha_nacimiento()));

					ind.setName(this.getNombre());

					ind.setCiudad(this.getCiudad());

					ind.setCodigopostal(this.getCodigopostal());

					ind.setDireccion(this.getDireccion());

					ind.setIdentification(this.getIdentification());

					ind.setPais(this.getPais());

					ind.setApellido(this.getApellido());
				

				modificarEJB.modificarClienteIndividual(user,ind);
				
			} catch (UsuarioException e) {
				FacesMessage fm = new FacesMessage("El usuario no existe");
				FacesContext.getCurrentInstance().addMessage("modificarCIndividual:botonInd", fm);	
			} catch (ClienteNoEncontradoException e) {
				FacesMessage fm = new FacesMessage("El cliente individual no existe");
				FacesContext.getCurrentInstance().addMessage("modificarCIndividual:botonInd", fm);	
			} catch (UserNotAdminException e) {
				FacesMessage fm = new FacesMessage("El usuario no tiene permisos de admin");
				FacesContext.getCurrentInstance().addMessage("modificarCIndividual:botonInd", fm);	
			}
			return "paginaadmin.xhtml";

	}
}
