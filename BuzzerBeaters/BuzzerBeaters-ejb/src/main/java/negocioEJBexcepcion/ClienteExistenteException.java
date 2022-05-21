package negocioEJBexcepcion;

public class ClienteExistenteException extends Exception {
	
	public ClienteExistenteException () {};
	
	public ClienteExistenteException(String message) {
		super(message);
	}
	

}
