// Se lanza al registrar una persona que ya existe, controla duplicados de Personas
public class PersonaDuplicadaException extends Exception {
	public PersonaDuplicadaException(String mensaje) {
		super(mensaje);
	}
}
