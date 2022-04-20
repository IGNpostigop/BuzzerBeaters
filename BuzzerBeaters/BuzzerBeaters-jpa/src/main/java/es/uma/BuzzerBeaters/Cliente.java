package es.uma.BuzzerBeaters;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Cliente extends usuario {
	
//	@Id@GeneratedValue
//	private Long id;
	@Column(nullable = false, unique = true)
	private String identification;
	@Column(nullable = false)
	private Boolean estado;
	@Column(name="Fecha_Alta")
	private Date fechaAlta;
	@Temporal(TemporalType.DATE)
	@Column(name="Fecha_Baja")
	private Date fechaBaja;
	@Column(name = "Direccion",nullable = false)
	private String direccion;
	@Column(name = "Ciudad", nullable = false)
	private String ciudad;
	@Column(name="CodigoPostal",nullable = false)
	private Integer codigopostal;
	@Column(name="Pais",nullable = false)
	private String pais;
//	@Column (nullable = false)
//	private String user;
//	@Column (nullable = false)
//	private String password;
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Integer getCodigopostal() {
		return codigopostal;
	}
	public void setCodigopostal(Integer codigopostal) {
		this.codigopostal = codigopostal;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ciudad, codigopostal, direccion, estado, fechaAlta, fechaBaja, identification, pais);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(codigopostal, other.codigopostal)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(estado, other.estado)
				&& Objects.equals(fechaAlta, other.fechaAlta) && Objects.equals(fechaBaja, other.fechaBaja)
				&& Objects.equals(identification, other.identification) && Objects.equals(pais, other.pais);
	}
	@Override
	public String toString() {
		return "Cliente [identification=" + identification + ", estado=" + estado + ", fechaAlta=" + fechaAlta
				+ ", fechaBaja=" + fechaBaja + ", direccion=" + direccion + ", ciudad=" + ciudad + ", codigopostal="
				+ codigopostal + ", pais=" + pais + "]";
	}


	
	
}
