package es.uma.BuzzerBeaters;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Transaccion  {
	@Id @GeneratedValue
	private Long id;
	@Column (nullable = false)
	private Date fechaInstruccion;
	@Column (nullable = false)
	private Double cantidad;
	private Date fechaEjecucion;
	@Column (nullable = false)
	private String tipo;
	private double comision;
	private Boolean internacional;
	
	@ManyToOne
	private Cuenta cuenta_origen;
	@ManyToOne
	private Cuenta cuenta_destino;
	
	@ManyToOne
	private Divisa divisa_destino;
	@ManyToOne
	private Divisa divisa_origen;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaInstruccion() {
		return fechaInstruccion;
	}
	public void setFechaInstruccion(Date fechaInstruccion) {
		this.fechaInstruccion = fechaInstruccion;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public Boolean getInternacional() {
		return internacional;
	}
	public void setInternacional(Boolean internacional) {
		this.internacional = internacional;
	}
	public Cuenta getCuenta_origen() {
		return cuenta_origen;
	}
	public void setCuenta_origen(Cuenta cuenta_origen) {
		this.cuenta_origen = cuenta_origen;
	}
	public Cuenta getCuenta_destino() {
		return cuenta_destino;
	}
	public void setCuenta_destino(Cuenta cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}
	public Divisa getDivisa_destino() {
		return divisa_destino;
	}
	public void setDivisa_destino(Divisa divisa_destino) {
		this.divisa_destino = divisa_destino;
	}
	public Divisa getDivisa_origen() {
		return divisa_origen;
	}
	public void setDivisa_origen(Divisa divisa_origen) {
		this.divisa_origen = divisa_origen;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cantidad, comision, cuenta_destino, cuenta_origen, divisa_destino, divisa_origen,
				fechaEjecucion, fechaInstruccion, id, internacional, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaccion other = (Transaccion) obj;
		return Objects.equals(cantidad, other.cantidad)
				&& Double.doubleToLongBits(comision) == Double.doubleToLongBits(other.comision)
				&& Objects.equals(cuenta_destino, other.cuenta_destino)
				&& Objects.equals(cuenta_origen, other.cuenta_origen)
				&& Objects.equals(divisa_destino, other.divisa_destino)
				&& Objects.equals(divisa_origen, other.divisa_origen)
				&& Objects.equals(fechaEjecucion, other.fechaEjecucion)
				&& Objects.equals(fechaInstruccion, other.fechaInstruccion) && Objects.equals(id, other.id)
				&& Objects.equals(internacional, other.internacional) && Objects.equals(tipo, other.tipo);
	}
	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", fechaInstruccion=" + fechaInstruccion + ", cantidad=" + cantidad
				+ ", fechaEjecucion=" + fechaEjecucion + ", tipo=" + tipo + ", comision=" + comision
				+ ", internacional=" + internacional + ", cuenta_origen=" + cuenta_origen + ", cuenta_destino="
				+ cuenta_destino + ", divisa_destino=" + divisa_destino + ", divisa_origen=" + divisa_origen + "]";
	}
	

}
