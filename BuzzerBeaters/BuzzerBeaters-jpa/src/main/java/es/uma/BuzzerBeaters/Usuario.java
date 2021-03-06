package es.uma.BuzzerBeaters;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Usuario {
	
	@Id
	private String user;
	@Column (nullable = false)
	private String password;
	@Column (nullable = false)
	private boolean administrador;
	
	@OneToOne(mappedBy="usuarioIndividual")
	private Individual individual; //cambiar a individual
	@OneToOne(mappedBy="usuarioPA", cascade = {CascadeType.PERSIST})
	private PersonaAutorizada personaAutorizada;
	
	
	public Usuario() {
	}

	
	public Usuario(String user, String password, boolean administrador) {
		super();
		this.user = user;
		this.password = password;
		this.administrador = administrador;
	}
	
	public Individual getIndividual() {
		return individual;
	}


	public void setIndividual(Individual individual) {
		this.individual = individual;
	}


	public PersonaAutorizada getPersonaAutorizada() {
		return personaAutorizada;
	}


	public void setPersonaAutorizada(PersonaAutorizada personaAutorizada) {
		this.personaAutorizada = personaAutorizada;
	}


	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	@Override
	public int hashCode() {
		return Objects.hash(administrador, password, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return administrador == other.administrador && Objects.equals(password, other.password)
				&& Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "usuario [user=" + user + ", password=" + password + ", administrador=" + administrador + "]";
	}
	
	

}
