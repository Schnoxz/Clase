// Crea un array de 5 números enteros.  Asigna valores manualmente y muéstralos por pantalla usando:  a) for tradicional   b) foreach

public class array1 {

    public static void main(String[] args) {
        int[] array = new int[5]; // Creo el array con 5 elementos
        // Asigno los valores escribiendo el nombre del array y la posicion del elemento que estoy introduciendo
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        array[3] = 4;
        array[4] = 5;

        // For tradicional que recorre el array
        for (int i = 0; i < array.length; i++) {
            System.out.println("For tradicional: " + array[i]);
        }

        // For each
        for (int x : array) {
            System.out.println("For each: " + x);
        }
    }
}
