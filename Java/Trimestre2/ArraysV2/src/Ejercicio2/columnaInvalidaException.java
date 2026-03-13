package src.Ejercicio2;

public class columnaInvalidaException extends Exception {

    public columnaInvalidaException(String message) {
        super("Columna inválida, debe estar entre 0 y 9: ");
    }
}
