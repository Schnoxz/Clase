// Dado un array de enteros, pide un número por teclado y: Indica si está en el array Indica en qué posición aparece (si aparece)

import java.util.Scanner;

public class array4 {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Defino un array que contenga enteros
        int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // Pido un número por teclado
        System.out.println("Introduce un número: ");
        int numero = teclado.nextInt();
        // Recorro el array para ver si el número introducido está en el array
        // Uso un catch
        try {
            for (int i = 0; i < num.length; i++) {
                if (num[i] == numero) {
                    System.out.println("El número " + numero + " se encuentra en la posición " + i);
                }
            }
        } catch (Exception e) {
            for (int i = 0; i < num.length; i++) {
                if (num[i] == numero) {
                    System.out.println("El número " + numero + " se encuentra en la posición " + i);

                } else {
                    System.out.println("El número " + numero + " no se encuentra en el array");
                }
            }
        }
    }
}
