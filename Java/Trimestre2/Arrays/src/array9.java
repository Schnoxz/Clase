// Comparar arrays Comprueba si son iguales usando equals. Comprueba si son iguales usando Arrays.equals y Explica la diferencia

import java.util.Arrays;

public class array9 {

    public static void main(String[] args) {

        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {1, 2, 3, 4, 7};

        System.out.println("Usando el método .equals");
        // .equals() en un array comprueba si las dos variables son el mismo array comparando la memoria
        System.out.println("array1.equals(array2): " + array1.equals(array2));

        System.out.println("\nUsando Arrays.equals  ");
        // Arrays.equals() comprueba si el CONTENIDO de los arrays es el mismo pero comparando elemento a elemento de su interior
        System.out.println("Arrays.equals(array1, array2): " + Arrays.equals(array1, array2)); //
    }

}
