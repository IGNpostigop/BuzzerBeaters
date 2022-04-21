package es.uma.BuzzerBeaters;

import java.util.Objects;

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
	
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private PersonaAutorizada personaAutorizada;
	
	
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
