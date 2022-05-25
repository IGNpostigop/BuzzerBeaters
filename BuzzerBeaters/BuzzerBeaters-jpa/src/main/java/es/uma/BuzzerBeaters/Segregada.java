package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Segregada extends CuentaFintech implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String comision;
	
	@OneToOne
	private CuentaReferencia cuenta_referencia;

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public CuentaReferencia getCuenta_referencia() {
		return cuenta_referencia;
	}

	public void setCuenta_referencia(CuentaReferencia cuenta_referencia) {
		this.cuenta_referencia = cuenta_referencia;
	}

	public static long getSerialversionuid() {
		return  serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(comision, cuenta_referencia);
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
		Segregada other = (Segregada) obj;
		return Objects.equals(comision, other.comision) && Objects.equals(cuenta_referencia, other.cuenta_referencia);
	}

	@Override
	public String toString() {
		return "Segregada [comision=" + comision + ", cuenta_referencia=" + cuenta_referencia + "]";
	}
	
	
}
