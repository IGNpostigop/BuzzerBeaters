package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class DepositadaEn implements Serializable{
	/**
	 * 
	 */
	@EmbeddedId
	private DepositadaEnID id;
	private static final long serialVersionUID = 1L;
	
	private Double saldo;
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false)
	private PooledAccount cuentas_pooled;
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@ManyToOne
	@JoinColumn(name = "idPersonaAutorizada", nullable = false)
	private CuentaReferencia cuenta_banco;
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public DepositadaEnID getId() {
		return id;
	}
	public void setId(DepositadaEnID id) {
		this.id = id;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public PooledAccount getCuentas_pooled() {
		return cuentas_pooled;
	}
	public void setCuentas_pooled(PooledAccount cuentas_pooled) {
		this.cuentas_pooled = cuentas_pooled;
	}
	public CuentaReferencia getCuenta_banco() {
		return cuenta_banco;
	}
	public void setCuenta_banco(CuentaReferencia cuenta_banco) {
		this.cuenta_banco = cuenta_banco;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cuenta_banco, cuentas_pooled, id, saldo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepositadaEn other = (DepositadaEn) obj;
		return Objects.equals(cuenta_banco, other.cuenta_banco) && Objects.equals(cuentas_pooled, other.cuentas_pooled)
				&& Objects.equals(id, other.id) && Objects.equals(saldo, other.saldo);
	}
	@Override
	public String toString() {
		return "Depositada_en [id=" + id + ", saldo=" + saldo + ", cuentas_pooled=" + cuentas_pooled + ", cuenta_banco="
				+ cuenta_banco + "]";
	}
	

	
}
