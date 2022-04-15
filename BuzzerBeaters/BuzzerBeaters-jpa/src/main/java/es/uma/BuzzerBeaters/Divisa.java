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
	@Override
	public int hashCode() {
		return Objects.hash(CambioEuro, abreviatura, nombre, simbolo);
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
				&& Objects.equals(abreviatura, other.abreviatura) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(simbolo, other.simbolo);
	}
	@Override
	public String toString() {
		return "Divisa [abreviatura=" + abreviatura + ", nombre=" + nombre + ", simbolo=" + simbolo + ", CambioEuro="
				+ CambioEuro + "]";
	}


}
