package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="Individual.findAll", query="SELECT i FROM Cuenta i")

public class Individual extends Cliente implements Serializable {
	
	/**
	 * 
	 */
	
	@Column (nullable = false)
	private String name;
	@Column (nullable = false)
	private String apellido;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fecha_nacimiento;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private Usuario usuarioIndividual;
	
	
	private static final long serialVersionUID = 1L;



	public Usuario getUsuarioIndividual() {
		return usuarioIndividual;
	}



	public void setUsuarioIndividual(Usuario usuarioIndividual) {
		this.usuarioIndividual = usuarioIndividual;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}



	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(apellido, fecha_nacimiento, name);
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
		Individual other = (Individual) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(fecha_nacimiento, other.fecha_nacimiento)
				&& Objects.equals(name, other.name);
	}



	@Override
	public String toString() {
		return "Individual [name=" + name + ", apellido=" + apellido + ", fecha_nacimiento=" + fecha_nacimiento + "]";
	}


}
