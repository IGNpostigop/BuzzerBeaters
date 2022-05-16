package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class PooledAccount extends CuentaFintech implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="cuentas_pooled")
	private List<DepositadaEn> pooledDepositadaEn;
	
	
	public List<DepositadaEn> getPooledDepositadaEn() {
		return pooledDepositadaEn;
	}
	public void setPooledDepositadaEn(List<DepositadaEn> pooledDepositadaEn) {
		this.pooledDepositadaEn = pooledDepositadaEn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(pooledDepositadaEn);
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
		PooledAccount other = (PooledAccount) obj;
		return Objects.equals(pooledDepositadaEn, other.pooledDepositadaEn);
	}
	@Override
	public String toString() {
		return "PooledAccount [pooledDepositadaEn=" + pooledDepositadaEn + "]";
	}
	
	

}
