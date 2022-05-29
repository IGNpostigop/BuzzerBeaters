package es.uma.informatica.sii.buzzerbeaters.backing;

import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;

@Named(value = "modificarCEmpresa")
@RequestScoped
public class ModificarCEmpresa {
	
	@Inject
	private GestionClientes modificarEJB;
	@Inject
	private InfoSesion sesion;

	private Usuario user;
	private Empresa emp;
	private String razon_social;
	private Long id;
	private String identification;
	private String direccion;
	private String ciudad;
	private Integer codigopostal;
	private String pais;
	
	
	public ModificarCEmpresa() 
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

	public Empresa getemp() {
		return emp;
	}

	public void setemp(Empresa emp) {
		this.emp = emp;
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
	

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String modCEmpresa() {
		

			user = sesion.getUsuario();
			
			try {
				
				emp = modificarEJB.getClienteEmp(id);

					emp.setRazon_social(this.getRazon_social());

					emp.setCiudad(this.getCiudad());

					emp.setCodigopostal(this.getCodigopostal());

					emp.setDireccion(this.getDireccion());

					emp.setIdentification(this.getIdentification());

					emp.setPais(this.getPais());
				

				modificarEJB.modificarClienteEmpresa(user,emp);
				FacesMessage fm = new FacesMessage("Empresa modificada.");
				FacesContext.getCurrentInstance().addMessage("modificarCEmpresa:botonEmp", fm);
				return null;
				
			} catch (UsuarioException e) {
				FacesMessage fm = new FacesMessage("El usuario no existe");
				FacesContext.getCurrentInstance().addMessage("modificarCEmpresa:botonEmp", fm);	
			} catch (ClienteNoEncontradoException e) {
				FacesMessage fm = new FacesMessage("El cliente individual no existe");
				FacesContext.getCurrentInstance().addMessage("modificarCEmpresa:botonEmp", fm);	
			} catch (UserNotAdminException e) {
				FacesMessage fm = new FacesMessage("El usuario no tiene permisos de admin");
				FacesContext.getCurrentInstance().addMessage("modificarCEmpresa:botonEmp", fm);	
			}
			return "paginaadmin.xhtml";

	}
}
