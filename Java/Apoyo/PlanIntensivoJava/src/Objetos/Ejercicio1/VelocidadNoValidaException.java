package Objetos.Ejercicio1;
	// Excepción personalizada para manejar velocidades no válidas
	public class VelocidadNoValidaException extends Exception {
	    public VelocidadNoValidaException(String mensaje) {
	        super(mensaje);
	    }
	}
