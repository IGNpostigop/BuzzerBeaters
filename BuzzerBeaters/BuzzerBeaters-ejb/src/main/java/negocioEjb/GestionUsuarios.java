package negocioEjb;

import java.sql.Date;

import javax.ejb.Local;

@Local

public interface GestionUsuarios {
	/**
	 * Este método debe insertar un usuario en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 **/
	
	public void insertarUsuario (String user, String password, boolean administrador);
	
	/**
	 * Este metodo debe eliminar el usuario que de pase por el campo user.
	 * En caso de no encontrarse debera lanzar la excepcion pertinente
	 */
	
	public void eliminarUsuario (String user);
	
	/**
	 * Este metodo debe cambiar el password del usuario del sistema
	 * En caso de no encontrarse el usuario se debera lanzar la excepción 
	 * pertinente
	 */
	public void resetUserPassword(String user, String password);
	



}
