package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity

public class Empresa extends Cliente implements Serializable {
	
	@Column(name = "Razon_Social",nullable = false)
	private String razon_social;

	private static final long serialVersionUID = 1L;

	
	@OneToMany(mappedBy="empresa")
	private List<Autorizacion> autorizacion;


	public String getRazon_social() {
		return razon_social;
	}


	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}


	public List<Autorizacion> getAutorizacion() {
		return autorizacion;
	}


	public void setAutorizacion(List<Autorizacion> autorizacion) {
		this.autorizacion = autorizacion;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(autorizacion, razon_social);
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
		return Objects.equals(autorizacion, other.autorizacion) && Objects.equals(razon_social, other.razon_social);
	}


	@Override
	public String toString() {
		return "Empresa [razon_social=" + razon_social + ", autorizacion=" + autorizacion + "]";
	}



}