package Calculadora;

// La clase queda mucho más limpia, limitándose a su función
public class DivisionPorCeroException extends Exception {

    public DivisionPorCeroException(String message) {
        super(message);
    }

}
