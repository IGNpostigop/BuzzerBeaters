package es.uma.BuzzerBeaters;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class AutorizacionID  {
	
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
	
	

}
