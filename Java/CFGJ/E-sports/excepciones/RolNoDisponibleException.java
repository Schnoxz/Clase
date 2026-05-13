package excepciones;
// Se lanza cuando se asigna un rol a un jugador mientras ese rol ya esté ocupado por otro, cumple con el principio de que no existan más de un rol por jugador en el equipo

public class RolNoDisponibleException extends Exception {
	public RolNoDisponibleException(String message) {
		super(message);
	}
}
