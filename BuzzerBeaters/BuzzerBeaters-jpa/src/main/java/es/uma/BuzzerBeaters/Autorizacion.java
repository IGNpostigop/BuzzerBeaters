package es.uma.BuzzerBeaters;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity

public class Autorizacion {
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@EmbeddedId
	private AutorizacionID id;
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Column (nullable = false)
	private String tipo;
	public AutorizacionID getId() {
		return id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setId(AutorizacionID id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autorizacion other = (Autorizacion) obj;
		return Objects.equals(id, other.id) && Objects.equals(tipo, other.tipo);
	}
	@Override
	public String toString() {
		return "Autorizacion [id=" + id + ", tipo=" + tipo + "]";
	}
	

	
	
	
}
