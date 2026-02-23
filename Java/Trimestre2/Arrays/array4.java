// Dado un array de enteros, pide un número por teclado y: Indica si está en el array Indica en qué posición aparece (si aparece)  

import java.util.Scanner;
public class array4 {

    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        // Defino un array que contenga enteros
        int[] num = {1, 2, 3 , 4, 5, 6 ,7, 8, 9, 10};
        // Pido un número por teclado
        System.out.println("Introduce un número: ");
        int numero = teclado.nextInt();
        // Recorro el array para ver si el número introducido está en el array
        // Como es una respuesta de si o no, meto un boolean
        boolean encontrado = false;
        for (int i = 0; i < num.length; i++) {
            if (num[i] == numero) { // Si el número introducido es igual a un número del array, se pone el boolean a true
                encontrado = true;
                System.out.println("El número " + numero + " está en el array en la posición " + i);
                break; // Si se encuentra el número, se sale del bucle
            }
       }
        }
    }

 


