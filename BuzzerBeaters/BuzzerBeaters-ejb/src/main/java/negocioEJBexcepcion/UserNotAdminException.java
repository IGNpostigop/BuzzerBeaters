package negocioEJBexcepcion;

public class UserNotAdminException extends Exception {
	
	public UserNotAdminException () {};
	
	public UserNotAdminException(String message) {
		super(message);
	}
	

}
