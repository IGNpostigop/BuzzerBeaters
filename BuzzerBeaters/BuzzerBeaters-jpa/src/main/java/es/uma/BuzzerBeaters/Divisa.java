package es.uma.BuzzerBeaters;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Divisa {
	
	@Id
	private String abreviatura;
	@Column(nullable = false)
	private String nombre;
	private Character simbolo;
	private double CambioEuro;
	
	@OneToMany (mappedBy="divisa")
	private List<CuentaReferencia> cuenta_referencia;


}
