package es.uma.BuzzerBeaters;
import javax.persistence.InheritanceType;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Cuenta")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cuenta {
	
	@Id @Column (name = "IBAN")
	private String iban;
	
	@Column (name = "SWIFT")
	private String swift;

	@OneToMany (mappedBy="cuenta_origen")
	private List<Transaccion> transaccionesOrigen;
	@OneToMany (mappedBy="cuenta_destino")
	private List<Transaccion> transaccionesDestino;
	
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getSwift() {
		return swift;
	}
	public void setSwift(String swift) {
		this.swift = swift;
	}
	public List<Transaccion> getTransaccionesOrigen() {
		return transaccionesOrigen;
	}
	public void setTransaccionesOrigen(List<Transaccion> transaccionesOrigen) {
		this.transaccionesOrigen = transaccionesOrigen;
	}
	public List<Transaccion> getTransaccionesDestino() {
		return transaccionesDestino;
	}
	public void setTransaccionesDestino(List<Transaccion> transaccionesDestino) {
		this.transaccionesDestino = transaccionesDestino;
	}
	@Override
	public int hashCode() {
		return Objects.hash(iban, swift, transaccionesDestino, transaccionesOrigen);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(iban, other.iban) && Objects.equals(swift, other.swift)
				&& Objects.equals(transaccionesDestino, other.transaccionesDestino)
				&& Objects.equals(transaccionesOrigen, other.transaccionesOrigen);
	}
	@Override
	public String toString() {
		return "Cuenta [iban=" + iban + ", swift=" + swift + ", transaccionesOrigen=" + transaccionesOrigen
				+ ", transaccionesDestino=" + transaccionesDestino + "]";
	}
	


}
