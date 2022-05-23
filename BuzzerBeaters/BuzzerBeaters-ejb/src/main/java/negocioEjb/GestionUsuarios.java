package negocioEjb;

import java.util.List;

import javax.ejb.Local;

import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;
import negocioEJBexcepcion.WrongPasswordException;

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
	
	public void insertarUsuario (Usuario user) throws UsuarioException;
	
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

	public List<Usuario> getUsuarios();
	
	public Usuario Login(String userName, String password) throws UsuarioException, WrongPasswordException;
	
	public Usuario AdminLogin(String adminName, String password) throws UsuarioException, WrongPasswordException, UserNotAdminException;
	

    public Usuario refrescarUsuario(Usuario u) throws UsuarioException, WrongPasswordException;
    
    
    public void compruebaLogin(Usuario u) throws UsuarioException, WrongPasswordException;
    

    }


