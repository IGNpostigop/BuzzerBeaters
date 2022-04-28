package negocioEjb;

import java.sql.Date;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;

public interface GestionPersonasAutorizadas {
	
	/**
	 * Este método debe insertar una persona autorizada en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 * @throws UsuarioException 
	 **/
	


	void insertarPersonaAutorizada(Cliente cliente, String identificacion, String nombre, String apellidos, Boolean estado,
			 Empresa empresa) throws UsuarioException;
	
	/**
	 * Este metodo permitira modificar los datos de una persona autorizada
	 * @throws UsuarioException 
	 */
	
	void modificarPersonaAutorizada(PersonaAutorizada persAut, String identificacion, String nombre, String apellidos, Boolean estado,
			Date fechaNacimiento, Date fechaInicio, Date fechaFin) throws UsuarioException;
	
	
	/**
	 * Este metodo permitira la eliminacion de autorizados de una cuenta.
	 */
	
	void eliminarAutorizadoEmpresa(PersonaAutorizada persAut, Autorizacion autorizacion)throws UsuarioException;

	boolean consultarPersonaAutorizada(PersonaAutorizada aut)throws UsuarioException;
}