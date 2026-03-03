package SimulacroPractico;

public class NotaInvalidaException extends Exception {

    public NotaInvalidaException(Double nota) {
        super("La nota " + nota + " es invalida");
    }
}
