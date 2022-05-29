package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class AutorizacionID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idCliente;
	private Long idPersonaAutorizada;
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdPersonaAutorizada() {
		return idPersonaAutorizada;
	}
	public void setIdPersonaAutorizada(Long idPersonaAutorizada) {
		this.idPersonaAutorizada = idPersonaAutorizada;
	}
	@Override
	public String toString() {
		return "AutorizacionID [idCliente=" + idCliente + ", idPersonaAutorizada=" + idPersonaAutorizada + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idCliente, idPersonaAutorizada);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutorizacionID other = (AutorizacionID) obj;
		return Objects.equals(idCliente, other.idCliente)
				&& Objects.equals(idPersonaAutorizada, other.idPersonaAutorizada);
	}
	
	

}
