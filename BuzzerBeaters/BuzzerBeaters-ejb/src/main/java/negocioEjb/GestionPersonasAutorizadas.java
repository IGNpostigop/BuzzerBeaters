package negocioEjb;

import java.sql.Date;

public interface GestionPersonasAutorizadas {
	
	/**
	 * Este método debe insertar una persona autorizada en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 **/
	
	public void insertarPersonaAutorizada (String user, String password, boolean administrador, String identificacion, String nombre, String apellidos,
			Boolean estado, Date fechaNacimiento, Date fechaInicio, Date fechaFin, String empresa);
	
		

}
