package es.uma.informatica.sii.buzzerbeaters.backing;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import negocioEJBexcepcion.ClienteExistenteException;
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
	
	private PooledAccount poolAcc; 
	private Usuario user; 
	private String identificacion; 
	private String iban;
	private String ibanRefer; 
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
	
	//-------------------------------//
	public String cuentaPooled() {
		Usuario user = sesion.getUsuario(); 
		
		try {
			
			Cliente client = clientes.getCliente(this.getIdentificacion());
			
			PooledAccount pa = new PooledAccount();
			
			poolAcc.setIban(this.getIban());
			
			poolAcc.setSwift(this.getSwift());
			
			poolAcc.setEstado(true);
			
			poolAcc.setFecha_apertura(Date.valueOf(LocalDate.now())); //DATE
			
			poolAcc.setFecha_cierre(null);
			
			poolAcc.setClasificacion("Pooled");
			
			CuentaReferencia c  = cuentas.getCuentaReferencia(iban);
			
			//----------------//
			depositos = new ArrayList<>();
			
			DepositadaEn deposito = new DepositadaEn();
			
			DepositadaEnID depositoId = new DepositadaEnID();
			
			depositoId.setIBANpooled(this.getIban());
			
			depositoId.setIBANreferenciada(this.getIbanRefer());
			
			deposito.setId(depositoId);
			
			deposito.setSaldo(this.getSaldo());
			
			depositos.add(deposito);
			
			//----------//
			
			try {
				cuentas.aperturaCtaPooled(user, poolAcc);
			} catch (UsuarioException e) {
				FacesMessage fm = new FacesMessage("El usuario no existe");
				FacesContext.getCurrentInstance().addMessage("abrirSegregada:usuario", fm);	
			}
			 catch (UserNotAdminException e) {
				FacesMessage fm = new FacesMessage("El usuario no tiene los privilegios suficientes");
				FacesContext.getCurrentInstance().addMessage("abrirSegregada:usuario", fm);	
						
			} catch (CuentaException e) {
				FacesMessage fm = new FacesMessage("La cuenta ya existe");
				FacesContext.getCurrentInstance().addMessage("abrirSegregada:boton", fm);	
			}
			
			return "paginaadmin.xhtml";
			
		} catch (ClienteNoEncontradoException e) {
			FacesMessage fm = new FacesMessage("El cliente no existe");
			FacesContext.getCurrentInstance().addMessage("abrirSegregada:cliente", fm);
		} catch (CuentaException e) {
			FacesMessage fm = new FacesMessage("La cuenta referencia no existe");
			FacesContext.getCurrentInstance().addMessage("abrirSegregada:ibanReferencia", fm);

		
		return null;
		
		}
		return "paginaadmin.xhtml";
	}
}
	
	

