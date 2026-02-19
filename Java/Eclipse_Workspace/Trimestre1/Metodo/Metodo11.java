// Realizar un programa que solicite la base de un rombo (debe ser un número impar) así como un carácter. El programa pintará por pantalla un rombo con esa base de esta forma.
package Metodo;

import java.util.Scanner;

public class Metodo11{
    private static final Scanner teclado = new Scanner(System.in);
   
    public static void main (String[] args){
        // Solicitamos base y comprobamos si es impar
        System.out.print("Introduce la base (impar): ");
        int base = teclado.nextInt();

        while (base % 2 == 0) {
            System.out.print("La base debe ser impar. Introduce de nuevo: ");
            base = teclado.nextInt();
        }

        // Solicitamos caracter
        System.out.print("Introduce un carácter: ");
        char caracter = teclado.next().charAt(0);

        System.out.println();

        // Llamo al método para dibujar el rombo
        Rombo (base, caracter);
        
       }
        
         public static void Rombo (int base, char caracter) {
        	 int mitad = base/2;
        
        	   // Parte superior
             for (int i = 0; i <= mitad; i++) {
                 for (int j = 0; j < mitad - i; j++) {
                     System.out.print(" ");
                 }
                 for (int j = 0; j < (2 * i + 1); j++) {
                     System.out.print(caracter);
                 }
                 System.out.println();
             }

             // Parte inferior
             for (int i = mitad - 1; i >= 0; i--) {
                 for (int j = 0; j < mitad - i; j++) {
                     System.out.print(" ");
                 }
                 for (int j = 0; j < (2 * i + 1); j++) {
                     System.out.print(caracter);
                 }
                 System.out.println();
             }
         }
     }