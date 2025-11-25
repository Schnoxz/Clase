//Tabla de multiplicar.  Pide un número al usuario y muestra su tabla de multiplicar del 1 al 10.

import java.util.Scanner;

public class Ampliation3 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        int num;

        // Solicito al usuario un número entero
        System.out.print("Introduzca un número para ver su tabla de multiplicar: ");
        num = teclado.nextInt();

        // Muestra la tabla de multiplicar del número introducido, desde 1 hasta 10
        System.out.println("\nTabla de multiplicar del " + num + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }

        // Salto de línea final 
        System.out.println();
    }
}
