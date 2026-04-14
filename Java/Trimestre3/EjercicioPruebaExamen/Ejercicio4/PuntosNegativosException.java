package EjercicioPruebaExamen.Ejercicio4;

//Excepción  que se lanza cuando hay puntuaciones negativas
public class PuntosNegativosException extends Exception {
    public PuntosNegativosException(String mensaje) {
        super(mensaje);
    }
}