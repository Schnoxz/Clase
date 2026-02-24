// Invertir el array. Dado int[] numeros = {1,2,3,4,5}; Crea otro array con el orden invertido.

import java.util.Arrays;

public class array7 {
    
    public static void main(String[] args) {
        // Declaro un array 
        int[] array = {1, 2, 3, 4, 5};
        int[] invertido = new int[5];

        for (int i = array.length - 1; i >= 0; i--) {
            invertido[array.length -i - 1] = array [i];
        }
        System.out.println(Arrays.toString(invertido));
    }
}
