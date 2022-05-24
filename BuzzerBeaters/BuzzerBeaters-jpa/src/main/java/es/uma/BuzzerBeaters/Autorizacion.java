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
	@JoinColumn(name = "idEmpresa", nullable = false)
	private Empresa empresa;
	/////////////////////////////////////////////////////////////////////////////////////////////

	
	public Autorizacion(AutorizacionID id, String tipo, PersonaAutorizada personaAutorizada, Empresa empresa) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.personaAutorizada = personaAutorizada;
		this.empresa = empresa;
	}
	public Autorizacion() {
	
	}
	
	
	public AutorizacionID getId() {
		return id;
	}


	public void setId(AutorizacionID id) {
		this.id = id;
	}

	
	//Los que son de tipo "A" solo pueden ver las cuentas y datos asociados (como transacciones)
	//Los que son de tipo "B" pueden hacer lo que tipo A + pueden realizar operaciones (transacciones y cambios de divisa).
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empresa, id, personaAutorizada, tipo);
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
		return Objects.equals(empresa, other.empresa) && Objects.equals(id, other.id)
				&& Objects.equals(personaAutorizada, other.personaAutorizada) && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Autorizacion [id=" + id + ", tipo=" + tipo + ", personaAutorizada=" + personaAutorizada + ", empresa="
				+ empresa + "]";
	}


	
	
}
