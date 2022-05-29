package es.uma.informatica.sii.buzzerbeaters.backing;

import java.sql.Date;
import java.time.LocalDate;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.Segregada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.SegregadaSinReferencia;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;
import negocioEjb.GestionCuentas;

@Named(value = "abrirCuentaSegregada")
@RequestScoped

public class AbrirCuentaSegregada {
	
	@Inject
	InfoSesion sesion; 
	
	@Inject
	GestionCuentas cuentas; //ejb
	
	@Inject
	GestionClientes clientes; //ejb 
	
	private Segregada cuenta;
	
	private Usuario usuario;
	
	private Long id;
	
	private String identificacion;
	
	private String iban;
	
	private String ibanRefer;
	
	private String swift;
	
	private String comision;
	


	AbrirCuentaSegregada()
	{
	}
	
	// setters y getters
	
	public InfoSesion getSesion() {
		return sesion;
	}

	public void setSesion(InfoSesion sesion) {
		this.sesion = sesion;
	}

	public GestionCuentas getCuentas() {
		return cuentas;
	}

	public void setCuentas(GestionCuentas cuentas) {
		this.cuentas = cuentas;
	}

	public GestionClientes getClientes() {
		return clientes;
	}

	public void setClientes(GestionClientes clientes) {
		this.clientes = clientes;
	}

	public Segregada getCuenta() {
		return cuenta;
	}

	public void setCuenta(Segregada cuenta) {
		this.cuenta = cuenta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIbanRefer() {
		return ibanRefer;
	}

	public void setIbanRefer(String ibanRefer) {
		this.ibanRefer = ibanRefer;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}
	
	public String abrirSegregada() {

		Usuario usuario = sesion.getUsuario();

		try {
		
		Cliente cliente = clientes.getCliente(this.id);
		cuenta = new Segregada();
		

	
			cuenta.setIban(this.getIban());
			cuenta.setSwift(this.getSwift());
			cuenta.setEstado(true);
			cuenta.setFecha_apertura(Date.valueOf(LocalDate.now())); // DATE
			cuenta.setClasificacion("Segregada");
			cuenta.setComision(this.getComision());		
			
			CuentaReferencia cr = cuentas.getCuentaReferencia(this.getIbanRefer());

			cuentas.aperturaCtaSegregated(usuario, cuenta, cliente ,cr);
			FacesMessage fm = new FacesMessage("Cuenta segregada abierta correctamente.");
			FacesContext.getCurrentInstance().addMessage("abreSegregada:boton", fm);
			return null;
			
				} catch (UsuarioException e) {
					FacesMessage fm = new FacesMessage("El usuario no existe");
					FacesContext.getCurrentInstance().addMessage("abreSegregada:boton", fm);
				} catch (UserNotAdminException e) {
					FacesMessage fm = new FacesMessage("El usuario no tiene permisos de administracion");
					FacesContext.getCurrentInstance().addMessage("abreSegregada:boton", fm);
				} catch (SegregadaSinReferencia e) {
					FacesMessage fm = new FacesMessage("El iban introoducido no coincide con la referencia asociada");
					FacesContext.getCurrentInstance().addMessage("abreSegregada:boton", fm);
				}catch (ClienteNoEncontradoException e) {
					FacesMessage fm = new FacesMessage("El cliente no existe");
					FacesContext.getCurrentInstance().addMessage("abreSegregada:boton", fm);
				}
				catch (CuentaException e) {
					FacesMessage fm = new FacesMessage("No existe la cuenta referencia");
					FacesContext.getCurrentInstance().addMessage("abreSegregada:boton", fm);
				}
		
		return null;

		}
	}






