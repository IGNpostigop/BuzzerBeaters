package negocioEjb;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.UsuarioException;

public interface GestionClientes {
	/**
	 * Este método debe insertar un cliente en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 * @throws UsuarioException 
	 **/

	public void crearCliente(Cliente cliente) throws UsuarioException;
	
//	/**
//	 * Este método debe permitir modificar la identificación de un cliente
//	 * Debe comprobar que el cliente existe. En caso contrario debe lanzar una excepción
//	 * @throws UsuarioException 
//	 * 
//	 */
//	public void setClienteIdentificacion (String cliente, String identificacion) throws UsuarioException;
//	
//	/**
//	 * Este método debe permitir modificar la dirección de un cliente, país, código Postal y ciudad
//	 * Debe comprobar que el cliente existe. En caso contrario debe lanzar una excepción
//	 */
//	
//	
//	public void setClienteDireccion (String cliente, String direccion, int codigoPostal, String pais, String ciudad);
	
	/**
	 * Este método dará de baja un cliente modificando su estado de activo a inactivo.
	 * Debe comprobar que el cliente existe. En caso contrario debe lanzar una excepción
	 * @throws UsuarioException 
	 */
	public void bajaCliente (Cliente cliente) throws UsuarioException;
	
	/**
	 * Este método debe permitir modificar los atributos de un cliente
	 */

	public Cliente modificarCliente(Cliente cliente, String identificacion, Boolean estado, String direccion, String ciudad,
			Integer codigoPostal, String pais) throws UsuarioException;

}
