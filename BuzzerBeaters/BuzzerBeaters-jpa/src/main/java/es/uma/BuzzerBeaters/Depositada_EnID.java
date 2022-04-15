package es.uma.BuzzerBeaters;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Depositada_EnID 
{
	private String IBANreferenciada;
	private String IBANpooled;
	public String getIBANreferenciada() {
		return IBANreferenciada;
	}
	public void setIBANreferenciada(String iBANreferenciada) {
		IBANreferenciada = iBANreferenciada;
	}
	public String getIBANpooled() {
		return IBANpooled;
	}
	public void setIBANpooled(String iBANpooled) {
		IBANpooled = iBANpooled;
	}
	@Override
	public int hashCode() {
		return Objects.hash(IBANpooled, IBANreferenciada);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Depositada_EnID other = (Depositada_EnID) obj;
		return Objects.equals(IBANpooled, other.IBANpooled) && Objects.equals(IBANreferenciada, other.IBANreferenciada);
	}
	@Override
	public String toString() {
		return "Depositada_EnID [IBANreferenciada=" + IBANreferenciada + ", IBANpooled=" + IBANpooled + "]";
	}
	
	
	
}
