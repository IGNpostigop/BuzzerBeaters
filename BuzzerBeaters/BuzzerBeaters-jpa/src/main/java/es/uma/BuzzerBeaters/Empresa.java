package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity

public class Empresa extends Cliente implements Serializable {
	
	@Column(name = "Razon_Social",nullable = false)
	private String razon_social;

	private static final long serialVersionUID = 1L;

	
	@OneToMany
	private AutorizacionID autorizaciones;


	public String getRazon_social() {
		return razon_social;
	}


	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}


	public AutorizacionID getAutorizaciones() {
		return autorizaciones;
	}


	public void setAutorizaciones(AutorizacionID autorizaciones) {
		this.autorizaciones = autorizaciones;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(autorizaciones, razon_social);
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
		Empresa other = (Empresa) obj;
		return Objects.equals(autorizaciones, other.autorizaciones) && Objects.equals(razon_social, other.razon_social);
	}


	@Override
	public String toString() {
		return "Empresa [razon_social=" + razon_social + ", autorizaciones=" + autorizaciones + "]";
	}
	
	
}