// Invertir el array. Dado int[] numeros = {1,2,3,4,5}; Crea otro array con el orden invertido.

import java.util.Arrays;

public class array7 {

    public static void main(String[] args) {
        // Declaro un array
        int[] array = {1, 2, 3, 4, 5};
        // Creo un array llamado invertido con la misma longitud que el array original
        int[] invertido = new int[array.length];
        // Recorro el array original y guardo los elementos en el array invertido
        for (int i = array.length - 1; i >= 0; i--) {
            invertido[i] = array[array.length - 1 - i];
        }
        System.out.println(Arrays.toString(invertido));
    }
}
