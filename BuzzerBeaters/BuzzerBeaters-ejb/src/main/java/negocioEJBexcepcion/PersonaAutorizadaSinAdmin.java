package negocioEJBexcepcion;

public class PersonaAutorizadaSinAdmin extends Exception {
	
	public PersonaAutorizadaSinAdmin () {};
	
	public PersonaAutorizadaSinAdmin(String message) {
		super(message);
	}
	

}
