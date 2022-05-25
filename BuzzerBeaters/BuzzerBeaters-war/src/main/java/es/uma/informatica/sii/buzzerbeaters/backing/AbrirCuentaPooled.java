package es.uma.informatica.sii.buzzerbeaters.backing;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.CuentaReferencia;
import es.uma.BuzzerBeaters.DepositadaEn;
import es.uma.BuzzerBeaters.DepositadaEnID;
import es.uma.BuzzerBeaters.PooledAccount;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEjb.GestionClientes;
import negocioEjb.GestionCuentas;

@Named(value = "abrirCuentaPooled")
@RequestScoped

public class AbrirCuentaPooled {
	
	@Inject
	InfoSesion sesion; 
	
	@Inject
	GestionCuentas cuentas; //ejb
	
	@Inject
	GestionClientes clientes; //ejb 
	
	private Long id;
	private PooledAccount poolAcc; 
	private Usuario user; 
	private String identificacion; 
	private String iban;
	private String swift;
	private List<DepositadaEn> depositos; 
	private Double saldo;
	
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
	public PooledAccount getPoolAcc() {
		return poolAcc;
	}
	public void setPoolAcc(PooledAccount poolAcc) {
		this.poolAcc = poolAcc;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public List<DepositadaEn> getDepositos() {
		return depositos;
	}
	public void setDepositos(List<DepositadaEn> depositos) {
		this.depositos = depositos;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	//-------------------------------//----
	public String cuentaPooled() {
		Usuario user = sesion.getUsuario(); 
		
		try {
			
			Cliente client = clientes.getCliente(this.id);
			
			PooledAccount poAc = new PooledAccount();
			
			poAc.setIban(this.getIban());
			
			poAc.setSwift(this.getSwift());
			
			poAc.setEstado(true);
			
			poAc.setFecha_apertura(Date.valueOf(LocalDate.now())); //DATE
			
			poAc.setFecha_cierre(null);
			
			poAc.setClasificacion("Pooled");
			
			
			//----------------//
			depositos = new ArrayList<>();
			
			DepositadaEn deposito = new DepositadaEn();
			
			DepositadaEnID depositoId = new DepositadaEnID();
			
			depositoId.setIBANpooled(this.getIban());
			
			depositoId.setIBANreferenciada(null);
			
			deposito.setId(depositoId);
			
			deposito.setSaldo(this.getSaldo());
			
			depositos.add(deposito);
			
			//----------//
			
			try {
				cuentas.aperturaCtaPooled(user, poAc, client, depositos);
			} catch (UsuarioException e) {
				FacesMessage fm = new FacesMessage("El usuario no existe");
				FacesContext.getCurrentInstance().addMessage("abrirCuentaPooled:abrirCuenta", fm);	
			}
			 catch (UserNotAdminException e) {
				FacesMessage fm = new FacesMessage("El usuario no tiene los privilegios suficientes");
				FacesContext.getCurrentInstance().addMessage("abrirCuentaPooled:abrirCuenta", fm);	
						
			} catch (CuentaException e) {
				FacesMessage fm = new FacesMessage("La cuenta ya existe");
				FacesContext.getCurrentInstance().addMessage("abrirCuentaPooled:abrirCuenta", fm);	
			}
			
			return "paginaadmin.xhtml";
			
		} catch (ClienteNoEncontradoException e) {
			FacesMessage fm = new FacesMessage("El cliente no existe");
			FacesContext.getCurrentInstance().addMessage("abrirCuentaPooled:abrirCuenta", fm);
		}
		
		return null;
		
	}
}
	
	

