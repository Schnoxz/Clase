// Método que NO modifica, Crea un método que recorra el array con foreach y multiplique cada elemento por 2, ¿Cambia el array? Explica por qué.

import java.util.Arrays;

public class array12 {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        for (int x : array) {
            x = x * 2; // Un for each no permite modificar el array por su mera naturaleza, solo los recorre y guarda en una variable de ambito for
            System.out.println(Arrays.toString(array));
        }
    }
}
