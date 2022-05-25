package negocioEJBexcepcion;

public class CuentaConSaldo extends Exception {
	
	public CuentaConSaldo () {};
	
	public CuentaConSaldo(String message) {
		super(message);
	}
	

}
