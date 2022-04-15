package es.uma.BuzzerBeaters;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Cuenta_referencia extends Cuenta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany
	private Depositada_EnID referenciadaDepositada_En;
	
	@Column (nullable = false)
	private String NombreBanco;
	private String Sucursal;
	private String Pais;
	@Column (nullable = false)
	private Double Saldo;
	@Temporal(TemporalType.DATE)
	private Date fecha_apertura;
	private Boolean Estado;
	public Depositada_EnID getReferenciadaDepositada_En() {
		return referenciadaDepositada_En;
	}
	public void setReferenciadaDepositada_En(Depositada_EnID referenciadaDepositada_En) {
		this.referenciadaDepositada_En = referenciadaDepositada_En;
	}
	public String getNombreBanco() {
		return NombreBanco;
	}
	public void setNombreBanco(String nombreBanco) {
		NombreBanco = nombreBanco;
	}
	public String getSucursal() {
		return Sucursal;
	}
	public void setSucursal(String sucursal) {
		Sucursal = sucursal;
	}
	public String getPais() {
		return Pais;
	}
	public void setPais(String pais) {
		Pais = pais;
	}
	public Double getSaldo() {
		return Saldo;
	}
	public void setSaldo(Double saldo) {
		Saldo = saldo;
	}
	public Date getFecha_apertura() {
		return fecha_apertura;
	}
	public void setFecha_apertura(Date fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Estado, NombreBanco, Pais, Saldo, Sucursal, fecha_apertura, referenciadaDepositada_En);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta_referencia other = (Cuenta_referencia) obj;
		return Objects.equals(Estado, other.Estado) && Objects.equals(NombreBanco, other.NombreBanco)
				&& Objects.equals(Pais, other.Pais) && Objects.equals(Saldo, other.Saldo)
				&& Objects.equals(Sucursal, other.Sucursal) && Objects.equals(fecha_apertura, other.fecha_apertura)
				&& Objects.equals(referenciadaDepositada_En, other.referenciadaDepositada_En);
	}
	@Override
	public String toString() {
		return "Cuenta_referencia [referenciadaDepositada_En=" + referenciadaDepositada_En + ", NombreBanco="
				+ NombreBanco + ", Sucursal=" + Sucursal + ", Pais=" + Pais + ", Saldo=" + Saldo + ", fecha_apertura="
				+ fecha_apertura + ", Estado=" + Estado + "]";
	}
	
	
	
	
	
	

}
