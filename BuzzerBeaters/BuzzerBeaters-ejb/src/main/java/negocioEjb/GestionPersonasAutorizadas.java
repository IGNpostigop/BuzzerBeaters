package negocioEjb;

import java.sql.Date;

import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Usuario;

public interface GestionPersonasAutorizadas {
	
	/**
	 * Este método debe insertar una persona autorizada en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 **/
	


	void insertarPersonaAutorizada(Usuario user, String identificacion, String nombre, String apellidos, Boolean estado,
			Date fechaNacimiento, Date fechaInicio, Date fechaFin, Empresa empresa);
	

}
