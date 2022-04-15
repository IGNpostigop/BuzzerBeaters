package es.uma.BuzzerBeaters;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Cuenta_fintech")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cuenta_fintech extends Cuenta implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Boolean estado;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_apertura;
    @Temporal(TemporalType.DATE)
    private Date fecha_cierre;

    private String clasificacion;

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha_apertura() {
		return fecha_apertura;
	}

	public void setFecha_apertura(Date fecha_apertura) {
		this.fecha_apertura = fecha_apertura;
	}

	public Date getFecha_cierre() {
		return fecha_cierre;
	}

	public void setFecha_cierre(Date fecha_cierre) {
		this.fecha_cierre = fecha_cierre;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clasificacion, estado, fecha_apertura, fecha_cierre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta_fintech other = (Cuenta_fintech) obj;
		return Objects.equals(clasificacion, other.clasificacion) && Objects.equals(estado, other.estado)
				&& Objects.equals(fecha_apertura, other.fecha_apertura)
				&& Objects.equals(fecha_cierre, other.fecha_cierre);
	}

	@Override
	public String toString() {
		return "Cuenta_fintech [estado=" + estado + ", fecha_apertura=" + fecha_apertura + ", fecha_cierre="
				+ fecha_cierre + ", clasificacion=" + clasificacion + "]";
	}

    
    
    

}
