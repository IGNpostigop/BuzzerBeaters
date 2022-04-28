package negocioEjb;

import java.sql.Date;

import javax.ejb.Local;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;

@Local

public interface GestionUsuarios {
	/**
	 * Este método debe insertar un usuario en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 * @throws UsuarioException 
	 **/
	
	public void insertarUsuario (String user, String password, boolean administrador) throws UsuarioException;
	
	/**
	 * Este metodo debe eliminar el usuario que de pase por el campo user.
	 * En caso de no encontrarse debera lanzar la excepcion pertinente
	 * @throws UsuarioException 
	 */
	
	public void eliminarUsuario (Usuario user) throws UsuarioException;
	
	/**
	 * Este metodo debe cambiar el password del usuario del sistema
	 * En caso de no encontrarse el usuario se debera lanzar la excepción 
	 * pertinente
	 * @throws UsuarioException 
	 */
	public void resetUserPassword(Usuario user, String password) throws UsuarioException;
	

}