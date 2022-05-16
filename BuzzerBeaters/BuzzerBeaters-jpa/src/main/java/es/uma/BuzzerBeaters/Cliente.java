package es.uma.BuzzerBeaters;


import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Cliente{
	
	@Id @GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String identification;
	@Column(nullable = false)
	private Boolean estado;

	@Column(name="Fecha_Alta")
	private Date fechaAlta;

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
	


	@OneToMany(mappedBy="cliente")
	private List<CuentaFintech> cuentasFintech;

	public Cliente() {
	}
	
	public Cliente(Long id, String identificacion, Boolean estado, Date fechaAlta, Date fechaBaja, String direccion, String ciudad, Integer codigopostal, String pais) {
		this.id = id;
		this.identification = identificacion;
		this.estado = estado;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.codigopostal = codigopostal;
		this.pais = pais;
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

	
	public List<CuentaFintech> getCuentasFintech() {
		return cuentasFintech;
	}

	public void setCuentasFintech(List<CuentaFintech> cuentasFintech) {
		this.cuentasFintech = cuentasFintech;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ciudad, codigopostal, cuentasFintech, direccion, estado, fechaAlta, fechaBaja, id,
				identification, pais);
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
				&& Objects.equals(cuentasFintech, other.cuentasFintech) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(estado, other.estado) && Objects.equals(fechaAlta, other.fechaAlta)
				&& Objects.equals(fechaBaja, other.fechaBaja) && Objects.equals(id, other.id)
				&& Objects.equals(identification, other.identification) && Objects.equals(pais, other.pais);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", identification=" + identification + ", estado=" + estado + ", fechaAlta="
				+ fechaAlta + ", fechaBaja=" + fechaBaja + ", direccion=" + direccion + ", ciudad=" + ciudad
				+ ", codigopostal=" + codigopostal + ", pais=" + pais + ", cuentasFintech=" + cuentasFintech + "]";
	}

	
}
