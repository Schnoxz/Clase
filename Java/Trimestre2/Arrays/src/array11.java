// Método que modifica el array, Crea un metodo public static void multiplicarPorDos(int[] array) , Que multiplique todos los valores por 2. Comprueba que el array original cambia

import java.util.Arrays;

ç

public class array11 {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println("Array original: " + Arrays.toString(array));
        multiplicarPorDos(array);
        System.out.println("Array modificado: " + Arrays.toString(array));
    }

    public static void multiplicarPorDos(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= 2;
        }
    }
}
