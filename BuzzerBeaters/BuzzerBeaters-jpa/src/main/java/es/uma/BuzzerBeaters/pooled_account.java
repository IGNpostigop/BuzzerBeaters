package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class pooled_account extends Cuenta_fintech implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@OneToMany
	private Depositada_EnID pooledDepositada_En;
	public Depositada_EnID getPooledDepositada_En() {
		return pooledDepositada_En;
	}
	public void setPooledDepositada_En(Depositada_EnID pooledDepositada_En) {
		this.pooledDepositada_En = pooledDepositada_En;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(pooledDepositada_En);
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
		pooled_account other = (pooled_account) obj;
		return Objects.equals(pooledDepositada_En, other.pooledDepositada_En);
	}
	@Override
	public String toString() {
		return "pooled_account [pooledDepositada_En=" + pooledDepositada_En + "]";
	}

	

}
