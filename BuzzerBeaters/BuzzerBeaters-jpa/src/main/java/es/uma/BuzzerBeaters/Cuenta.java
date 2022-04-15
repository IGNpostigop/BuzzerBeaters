package es.uma.BuzzerBeaters;
import javax.persistence.InheritanceType;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;


@Entity
@Table(name="Cuenta")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cuenta {
	@Id
	@Column (name = "IBAN")
	private String iban;
	@Column (name = "SWIFT")
	private String swift;
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
	@Override
	public int hashCode() {
		return Objects.hash(iban, swift);
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
		return Objects.equals(iban, other.iban) && Objects.equals(swift, other.swift);
	}
	@Override
	public String toString() {
		return "Cuenta [iban=" + iban + ", swift=" + swift + "]";
	}
	

}
