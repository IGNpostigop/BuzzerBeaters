package es.uma.BuzzerBeaters;

import java.util.Date;
//import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity

public class PersonaAutorizada {

	

	@Id @GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String identification;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellidos;
	@Column(nullable = false)
	private String direccion;
	private Date fecha_nacimiento;
	@Column(name="Estado")
	private Boolean estado;
	@Column(name="FechaInicio")
	private Date fechaInicio;
	@Column(name="FechaFin")
	private Date fechaFin;

	@OneToMany(mappedBy="personaAutorizada")
	private List<Autorizacion> autorizacion;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Usuario usuarioPA;

	
	public PersonaAutorizada() 
	{
	}
	
	
	public PersonaAutorizada(Long id, String identification, String nombre, String apellidos, String direccion,
			Date fecha_nacimiento, Boolean estado, Date fechaInicio, Date fechaFin, List<Autorizacion> autorizacion,
			Usuario usuarioPA) {
		super();
		this.id = id;
		this.identification = identification;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.fecha_nacimiento = fecha_nacimiento;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.autorizacion = autorizacion;
		this.usuarioPA = usuarioPA;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Autorizacion> getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(List<Autorizacion> autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Usuario getUsuarioPA() {
		return usuarioPA;
	}

	public void setUsuarioPA(Usuario usuarioPA) {
		this.usuarioPA = usuarioPA;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, autorizacion, direccion, estado, fechaFin, fechaInicio, fecha_nacimiento, id,
				identification, nombre, usuarioPA);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaAutorizada other = (PersonaAutorizada) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(autorizacion, other.autorizacion)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(estado, other.estado)
				&& Objects.equals(fechaFin, other.fechaFin) && Objects.equals(fechaInicio, other.fechaInicio)
				&& Objects.equals(fecha_nacimiento, other.fecha_nacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(identification, other.identification) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(usuarioPA, other.usuarioPA);
	}

	@Override
	public String toString() {
		return "PersonaAutorizada [id=" + id + ", identification=" + identification + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", direccion=" + direccion + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", autorizacion="
				+ autorizacion + ", usuarioPA=" + usuarioPA + "]";
	}

	
	
}