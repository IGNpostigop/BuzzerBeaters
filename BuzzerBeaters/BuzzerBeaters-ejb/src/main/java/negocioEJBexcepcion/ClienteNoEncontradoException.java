package negocioEJBexcepcion;

public class ClienteNoEncontradoException extends Exception {
	
	public ClienteNoEncontradoException () {};
	
	public ClienteNoEncontradoException(String message) {
		super(message);
	}
	

}
