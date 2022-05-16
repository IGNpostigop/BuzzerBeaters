package negocioEjb;

import java.util.List;

import javax.ejb.Local;

import es.uma.BuzzerBeaters.Cliente;
import es.uma.BuzzerBeaters.Empresa;
import es.uma.BuzzerBeaters.Individual;
import es.uma.BuzzerBeaters.Usuario;
import negocioEJBexcepcion.ClienteDeBajaException;
import negocioEJBexcepcion.ClienteNoEncontradoException;
import negocioEJBexcepcion.CuentaException;
import negocioEJBexcepcion.UsuarioException;

@Local
public interface GestionClientes {
	/**
	 * Este método debe insertar un cliente en la base de datos
	 * Debe asegurarse de que los campos user y password no esten vacios
	 * Si el usuario que se intenta introducir ya existe en la base de datos
	 * debe lanzar una excepcion adecuada.
	 * user, password y administrador no pueden ser nulos.
	 * @throws UsuarioException 
	 **/
	public void crearClienteIndividual(Usuario admin, Cliente individual)
			throws UsuarioException;
	/*
	 * Lo mismo que anterriormente pero para empresas
	 */
	
	public void crearClienteEmpresa(Usuario admin, Cliente individual) throws UsuarioException;
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
	public void bajaCliente (Usuario admin, String idCliente) throws UsuarioException, ClienteNoEncontradoException, 
			ClienteDeBajaException, CuentaException;
	
	/**
	 * @param admin
	 * @param idCliente
	 * @throws UsuarioException
	 * @throws ClienteNoEncontradoException
	 * @throws ClienteDeBajaException
	 * Este método activará un cliente anteriormente de baja
	 */
	
	public void activaCliente(Usuario admin, String idCliente) throws UsuarioException, ClienteNoEncontradoException, 
		ClienteDeBajaException;
	
	/**
	 * Este método debe permitir modificar los atributos de un cliente
	 * @throws ClienteNoEncontradoException 
	 */

	public void modificarClienteIndividual(Usuario admin, String idCliente, Individual individual) throws UsuarioException,
			ClienteNoEncontradoException ;
	
	/**
	 * Igual que el anterior pero para clientes empresas
	 * @param admin
	 * @param idCliente
	 * @param empresa
	 * @throws UsuarioException
	 * @throws ClienteNoEncontradoException
	 */
	
	
	public void modificarClienteEmpresal(Usuario admin, String idCliente, Empresa empresa) throws UsuarioException, 
			ClienteNoEncontradoException;

	public List<Cliente> getClientes();

	

}
