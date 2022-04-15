package es.uma.BuzzerBeaters;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AdministrativoEbury 
{
	@Id
	private String dni;
	@Column (nullable = false)
	private String nombre;
	@Column (nullable = false)
	private String apellidos;
	@Temporal(TemporalType.DATE)
	private Date fecha_antiguedad;
	@Column (nullable = false)
	private String user;
	@Column (nullable = false)
	private String password;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getFecha_antiguedad() {
		return fecha_antiguedad;
	}
	public void setFecha_antiguedad(Date fecha_antiguedad) {
		this.fecha_antiguedad = fecha_antiguedad;
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
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, fecha_antiguedad, nombre, password, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministrativoEbury other = (AdministrativoEbury) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(dni, other.dni)
				&& Objects.equals(fecha_antiguedad, other.fecha_antiguedad) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "AdministrativoEbury [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", fecha_antiguedad=" + fecha_antiguedad + ", user=" + user + ", password=" + password + "]";
	}
	
	
	
	
}
