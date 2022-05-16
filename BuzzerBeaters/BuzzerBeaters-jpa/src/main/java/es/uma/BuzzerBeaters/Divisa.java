package es.uma.BuzzerBeaters;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Divisa {
	
	@Id
	private String abreviatura;
	@Column(nullable = false)
	private String nombre;
	private Character simbolo;
	private double CambioEuro;
	
	@OneToMany (mappedBy="divisa")
	private List<CuentaReferencia> cuenta_referencia;
	
	@OneToMany (mappedBy="divisa_origen")
	private List<Transaccion> divisaOrigenTransac;
	
	@OneToMany (mappedBy="divisa_destino")
	private List<Transaccion> divisaDestinoTransac;
	

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Character getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(Character simbolo) {
		this.simbolo = simbolo;
	}

	public double getCambioEuro() {
		return CambioEuro;
	}

	public void setCambioEuro(double cambioEuro) {
		CambioEuro = cambioEuro;
	}

	public List<CuentaReferencia> getCuenta_referencia() {
		return cuenta_referencia;
	}

	public void setCuenta_referencia(List<CuentaReferencia> cuenta_referencia) {
		this.cuenta_referencia = cuenta_referencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CambioEuro, abreviatura, cuenta_referencia, nombre, simbolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Divisa other = (Divisa) obj;
		return Double.doubleToLongBits(CambioEuro) == Double.doubleToLongBits(other.CambioEuro)
				&& Objects.equals(abreviatura, other.abreviatura)
				&& Objects.equals(cuenta_referencia, other.cuenta_referencia) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(simbolo, other.simbolo);
	}

	@Override
	public String toString() {
		return "Divisa [abreviatura=" + abreviatura + ", nombre=" + nombre + ", simbolo=" + simbolo + ", CambioEuro="
				+ CambioEuro + ", cuenta_referencia=" + cuenta_referencia + "]";
	}


}
