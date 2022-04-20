package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Persona_autorizada extends usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@Id @GeneratedValue
//	private Long id;
	@Column(nullable = false, unique = true)
	private String identification;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellidos;
	@Column(nullable = false)
	private String direccion;
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;
	@Column(name="Estado")
	private Boolean estado;
	@Temporal(TemporalType.DATE)
	@Column(name="FechaInicio")
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	@Column(name="FechaFin")
	private Date fechaFin;
//	@Column (nullable = false)
//	private String user;
//	@Column (nullable = false)
//	private String password;
	@OneToMany
	private AutorizacionID autorizaciones;
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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
		result = prime * result + Objects.hash(apellidos, autorizaciones, direccion, estado, fechaFin, fechaInicio,
				fecha_nacimiento, identification, nombre);
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
		Persona_autorizada other = (Persona_autorizada) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(autorizaciones, other.autorizaciones)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(estado, other.estado)
				&& Objects.equals(fechaFin, other.fechaFin) && Objects.equals(fechaInicio, other.fechaInicio)
				&& Objects.equals(fecha_nacimiento, other.fecha_nacimiento)
				&& Objects.equals(identification, other.identification) && Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Persona_autorizada [identification=" + identification + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", direccion=" + direccion + ", fecha_nacimiento=" + fecha_nacimiento + ", estado="
				+ estado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", autorizaciones="
				+ autorizaciones + "]";
	}

	
	
	

}