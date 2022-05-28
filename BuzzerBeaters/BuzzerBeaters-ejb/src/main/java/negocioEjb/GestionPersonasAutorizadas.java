package negocioEjb;

import java.sql.Date;
//import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import es.uma.BuzzerBeaters.Autorizacion;
import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.CuentaFintech;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.PersonaAutorizada;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.AutorizacionExistenteException;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.PersonaAutorizadaException;
import negocioEJBexcepcion.PersonaAutorizadaSinAdmin;
import negocioEJBexcepcion.UserNotAdminException;
import negocioEJBexcepcion.UsuarioException;

@Local
public interface GestionPersonasAutorizadas {
	
	/**
	 * Este método debe insertar una persona autorizada en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 * @throws UsuarioException 
	 * @throws PersonaAutorizadaSinAdmin 
	 **/
	


	public void crearPersonaAutorizada(Usuario usuario, PersonaAutorizada pa) throws UsuarioException, PersonaAutorizadaSinAdmin;
	
	/**
	 * Este metodo permitira modificar los datos de una persona autorizada
	 * @throws UsuarioException 
	 */
	
	public void modificarPersonaAutorizada(Usuario user, PersonaAutorizada pa) throws PersonaAutorizadaException, UsuarioException;
	
	
	/**
	 * Este metodo permitira la eliminacion de autorizados de una cuenta.
	 */
	
	public void eliminarAutorizadoEmpresa(Usuario user, Long idPa, Long idEmpresa, String tipo) throws UsuarioException, UserNotAdminException, 
	PersonaAutorizadaException, ClienteDeBajaException, AutorizacionExistenteException, ClienteNoEncontradoException;

	public boolean consultarPersonaAutorizada(PersonaAutorizada aut)throws UsuarioException;

	public List<PersonaAutorizada> getPersonasAutorizadas();

	void addAutorizadoEmpresa(Usuario user, Long idPa, Long idEmpresa, String tipo) throws UsuarioException,UserNotAdminException, 
		PersonaAutorizadaException, ClienteDeBajaException, AutorizacionExistenteException, ClienteNoEncontradoException;

	PersonaAutorizada getPA(Long id) throws PersonaAutorizadaException;
}
