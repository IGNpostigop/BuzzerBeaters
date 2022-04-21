package es.uma.BuzzerBeaters;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity

public class Autorizacion {
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@EmbeddedId
	private AutorizacionID id;
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Column (nullable = false)
	private String tipo;
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@ManyToOne
	@MapsId("idPersonaAutorizada")
	@JoinColumn(name = "idAutorizada", nullable = false)
	private PersonaAutorizada personaAutorizada;
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@ManyToOne
	@MapsId("idCliente")
	@JoinColumn(name = "idCliente", nullable = false)
	private Cliente cliente;
	/////////////////////////////////////////////////////////////////////////////////////////////

	public AutorizacionID getId() {
		return id;
	}

	public void setId(AutorizacionID id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public PersonaAutorizada getPersonaAutorizada() {
		return personaAutorizada;
	}

	public void setPersonaAutorizada(PersonaAutorizada personaAutorizada) {
		this.personaAutorizada = personaAutorizada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, id, personaAutorizada, tipo);
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
		return Objects.equals(cliente, other.cliente) && Objects.equals(id, other.id)
				&& Objects.equals(personaAutorizada, other.personaAutorizada) && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Autorizacion [id=" + id + ", tipo=" + tipo + ", personaAutorizada=" + personaAutorizada + ", cliente="
				+ cliente + "]";
	}

	
	
	
}
