package es.uma.BuzzerBeaters;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class PersonaAutorizada {

	

	@Id @GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String identification;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellidos;
	@Column(nullable = false)
	private String direccion;
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;
	@Column(name="Estado")
	private Boolean estado;
	@Temporal(TemporalType.DATE)
	@Column(name="FechaInicio")
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	@Column(name="FechaFin")
	private Date fechaFin;

	@OneToMany(mappedBy="personaAutorizada")
	private List<Autorizacion> autorizacion;
	
	@OneToOne
	private Usuario usuarioPA;

	
}