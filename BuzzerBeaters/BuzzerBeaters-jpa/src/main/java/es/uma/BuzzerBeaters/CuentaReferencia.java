package es.uma.BuzzerBeaters;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class CuentaReferencia extends Cuenta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany
	private DepositadaEnID referenciadaDepositada_En;
	
	@Column (nullable = false)
	private String NombreBanco;
	private String Sucursal;
	private String Pais;
	@Column (nullable = false)
	private Double Saldo;
	@Temporal(TemporalType.DATE)
	private Date fecha_apertura;
	private Boolean Estado;
	
	@ManyToOne
    private Divisa divisa;

	public DepositadaEnID getReferenciadaDepositada_En() {
		return referenciadaDepositada_En;
	}

	public void setReferenciadaDepositada_En(DepositadaEnID referenciadaDepositada_En) {
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

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(Estado, NombreBanco, Pais, Saldo, Sucursal, divisa, fecha_apertura,
				referenciadaDepositada_En);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaReferencia other = (CuentaReferencia) obj;
		return Objects.equals(Estado, other.Estado) && Objects.equals(NombreBanco, other.NombreBanco)
				&& Objects.equals(Pais, other.Pais) && Objects.equals(Saldo, other.Saldo)
				&& Objects.equals(Sucursal, other.Sucursal) && Objects.equals(divisa, other.divisa)
				&& Objects.equals(fecha_apertura, other.fecha_apertura)
				&& Objects.equals(referenciadaDepositada_En, other.referenciadaDepositada_En);
	}

	@Override
	public String toString() {
		return "Cuenta_referencia [referenciadaDepositada_En=" + referenciadaDepositada_En + ", NombreBanco="
				+ NombreBanco + ", Sucursal=" + Sucursal + ", Pais=" + Pais + ", Saldo=" + Saldo + ", fecha_apertura="
				+ fecha_apertura + ", Estado=" + Estado + ", divisa=" + divisa + "]";
	}
	
	
	

}