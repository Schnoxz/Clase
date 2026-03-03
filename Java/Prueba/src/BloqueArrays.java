// Este ejercicio es para aprender a trabajar con arrays contiene todas las operaciones que se pueden hacer con ellos

import java.util.*;

public class BloqueArrays {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Pido al usuario que introduzca 10 numeros y los guardo en un array
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Introduzca un numero (max 10): ");
            arr[i] = teclado.nextInt();
        }

        System.out.println("El numero mayor es: " + mayor(arr));
        System.out.println("El numero menor es: " + menor(arr));
        System.out.println("La media de este array es: " + media(arr));
        System.out.println("El array invertido es: " + Arrays.toString(invertir(arr))); // Como queremos mostrar una cadena necesitamos traducirla con el .toString, si no nos imprime la memoria reservada
        System.out.println("El array ordenado es: " + Arrays.toString(ordenar(arr)));

    }

    public static int mayor(int[] arr) { // Devuelve elemento mayor
        int maximo = arr[0]; // Inicializo la variable maximo con el primer elemento quien sera el que se compare con el resto de la cadena
        for (int i = 0; i < arr.length; i++) { // Creo un bucle for que recorre el array
            if (arr[i] > maximo) { // Comparo cada elemento con el maximo
                maximo = arr[i]; // Si se cumple la condicion se guarda el elemento como nuevo maximo
            }
        }
        return maximo; // Devuelvo el maximo
    }

    public static int menor(int[] arr) { // Devuelve el elemento menor
        int minimo = arr[0]; // Inicializo la variable maximo con el primer elemento quien sera el que se compare con el resto de la cadena
        for (int i = 0; i < arr.length; i++) { // Creo un bucle for que recorre el array
            if (arr[i] < minimo) { // Comparo cada elemento con el minimo
                minimo = arr[i]; // Si se cumple la condicion se guarda el elemento como nuevo minimo
            }
        }
        return minimo; // Devuelvo el maximo
    }

    public static double media(int[] arr) { // Devuelve la media
        double media = 0; // Inicializo la media en 0
        for (int i = 0; i < arr.length; i++) { // Recorro el array
            media += arr[i]; // Sumo todos los elementos
        }
        media /= arr.length; // Divido la media por la cantidad de elementos del array (longitud)
        return media; // Devuelvo la media
    }

    public static int[] invertir(int[] arr) { // Devuelve el array con los elemenos ordenados inversamente
        int[] arrInvertido = new int[arr.length]; // Creo un nuevo array llamado "arrInvertido" con la misma longitud que el arr que queremos invertir
        for (int i = 0; i < arr.length; i++) {
            arrInvertido[arr.length - 1 - i] = arr[i]; // Voy recorriendo el array original y guardo los elementos inversamente en el arrInvertido
        }
        return arrInvertido; // Devuelvo el arrInvertido

    }

    public static int[] ordenar(int[] arr) { // Ordena el array de menor a mayor usando Arrays.sort
        Arrays.sort(arr);
        return arr;
    }
}
