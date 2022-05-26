package es.uma.informatica.sii.buzzerbeaters.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteExistenteException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;

@Named(value = "altaCEmpresa")
@RequestScoped
public class AltaCEmpresa 
{
	@Inject
	private InfoSesion sesion;
	@Inject
	private GestionClientes clienteEmp;
	
	private Usuario admin;
	private Empresa emp;
	private Usuario userEmpresa;
	private String nombreUsuario;
	private String pass;
	
	private String razonSocial;
	private String identificacion;
	private String direccion;
	private String ciudad;
	private Integer codigopostal;
	private String pais;
	
	
	public AltaCEmpresa()
	{
		userEmpresa = new Usuario();
		emp = new Empresa();
	}



	public InfoSesion getSesion() {
		return sesion;
	}






	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}






	public GestionClientes getClienteEmp() {
		return clienteEmp;
	}






	public void setClienteEmp(GestionClientes clienteEmp) {
		this.clienteEmp = clienteEmp;
	}






	public Usuario getAdmin() {
		return admin;
	}






	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}






	public Empresa getEmp() {
		return emp;
	}






	public void setEmp(Empresa emp) {
		this.emp = emp;
	}






	public Usuario getUserEmpresa() {
		return userEmpresa;
	}






	public void setUserEmpresa(Usuario userEmpresa) {
		this.userEmpresa = userEmpresa;
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






	public String getRazonSocial() {
		return razonSocial;
	}






	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}






	public String getIdentificacion() {
		return identificacion;
	}






	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
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






	public String altaCEmpresaFun() {
		try {
			
			admin = sesion.getUsuario();
			
			emp.setRazon_social(razonSocial);
			emp.setCiudad(ciudad);
			emp.setCodigopostal(codigopostal);
			emp.setDireccion(direccion);
			emp.setIdentification(identificacion);
			emp.setPais(pais);
			
			userEmpresa.setUser(nombreUsuario);
			userEmpresa.setPassword(pass);
			userEmpresa.setAdministrador(false);
			
			
			clienteEmp.crearClienteEmpresa(admin, emp);
			return "paginaadmin.xhtml";
			
		}catch (ClienteExistenteException e) {
			FacesMessage fm = new FacesMessage("La empresa que se intenta crear ya existe");
			FacesContext.getCurrentInstance().addMessage("altaCEmpresa:botonEmpresa", fm);
		}catch(UserNotAdminException e) {
			FacesMessage fm = new FacesMessage("El usuario no tiene permisos de administrativo");
			FacesContext.getCurrentInstance().addMessage("altaCEmpresa:botonEmpresa", fm);
		}catch(UsuarioException e) {
			FacesMessage fm = new FacesMessage("No se ha encontrado el usuario de empresa");
			FacesContext.getCurrentInstance().addMessage("altaCEmpresa:botonEmpresa", fm);
		}
		
		return null;
		
	}
}
