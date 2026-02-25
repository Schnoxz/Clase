// Probar clone() Crea un array, clónalo y modifica el original Muestra ambos arrays y explica qué ocurre

import java.util.Arrays;

public class array10 {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int[] arrayclonado = new int[array.length]; // Creo un clon de la estancia actual del original
        arrayclonado = array.clone();
        array[0] = 10; // Cambio el primer elemento del array original

        System.out.println("\n\nArray original: " + Arrays.toString(array)); // Muestro el array original
        // Nos muestra "Array original: [10, 2, 3, 4, 5]" porque le he cambiado el valor del primer elemento
        System.out.println("Array clonado: " + Arrays.toString(arrayclonado)); // Muestro el array clonado
        // Sin embargo el clon, era una estancia anterior a la modificacion del original, por lo tanto sigue siendo [1, 2, 3, 4, 5]
    }
}
