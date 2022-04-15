package es.uma.BuzzerBeaters;

import java.util.Date;
import java.util.List;
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
	@Temporal(TemporalType.DATE)
	private Date fechaInstruccion;
	@Column (nullable = false)
	private Double cantidad;
	@Temporal(TemporalType.DATE)
	private Date fechaEjecucion;
	@Column (nullable = false)
	private String tipo;
	private double comision;
	private Boolean internacional;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cuenta cuenta_origen;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cuenta cuenta_destino;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Divisa Divisa_receptor;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Divisa Divisa_origen;

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

	public Divisa getDivisa_receptor() {
		return Divisa_receptor;
	}

	public void setDivisa_receptor(Divisa divisa_receptor) {
		Divisa_receptor = divisa_receptor;
	}

	public Divisa getDivisa_origen() {
		return Divisa_origen;
	}

	public void setDivisa_origen(Divisa divisa_origen) {
		Divisa_origen = divisa_origen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Divisa_origen, Divisa_receptor, cantidad, comision, cuenta_destino, cuenta_origen,
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
		return Objects.equals(Divisa_origen, other.Divisa_origen)
				&& Objects.equals(Divisa_receptor, other.Divisa_receptor) && Objects.equals(cantidad, other.cantidad)
				&& Double.doubleToLongBits(comision) == Double.doubleToLongBits(other.comision)
				&& Objects.equals(cuenta_destino, other.cuenta_destino)
				&& Objects.equals(cuenta_origen, other.cuenta_origen)
				&& Objects.equals(fechaEjecucion, other.fechaEjecucion)
				&& Objects.equals(fechaInstruccion, other.fechaInstruccion) && Objects.equals(id, other.id)
				&& Objects.equals(internacional, other.internacional) && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", fechaInstruccion=" + fechaInstruccion + ", cantidad=" + cantidad
				+ ", fechaEjecucion=" + fechaEjecucion + ", tipo=" + tipo + ", comision=" + comision
				+ ", internacional=" + internacional + ", cuenta_origen=" + cuenta_origen + ", cuenta_destino="
				+ cuenta_destino + ", Divisa_receptor=" + Divisa_receptor + ", Divisa_origen=" + Divisa_origen + "]";
	}


}
