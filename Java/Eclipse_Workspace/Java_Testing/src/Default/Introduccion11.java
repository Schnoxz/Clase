// Realizar un programa que solicite la base de un rombo (debe ser un número impar) así como un carácter. El programa pintará por pantalla un rombo con esa base de esta forma.
package Default;
import java.util.Scanner;

public class Introduccion11{
    private static final Scanner teclado = new Scanner(System.in);
    public static void main (String[] args){

        // Solicitamos base y comprobamos si es impar

        System.out.print("Introduce la base: ");
        int base = teclado.nextInt();

        while (base % 2 == 0) {
            System.out.print("La base debe ser impar. Introduce de nuevo: ");
            base = teclado.nextInt();
        }

        // Solicitamos caracter

        System.out.print("Introduce un carácter: ");
        char caracter = teclado.next().charAt(0);

        System.out.println();

        int mitad = base / 2;
        
        // Dibujamos la parte superior del rombo

        for (int i = 0; i <= mitad; i++) {
        
            // Espacios
            for (int j = 0; j < mitad - i; j++) {
                System.out.print(" ");
            }

            // Caracteres
            for (int j = 0; j < (2 * i + 1); j++) {
                System.out.print(caracter);
            }

            System.out.println();
}   
        // Dibujamos la parte inferior del rombo

        for (int i = mitad - 1; i >= 0; i--) {
        
            // Espacios
            for (int j = 0; j < mitad - i; j++) {
                System.out.print(" ");
            }

            // Caracteres
            for (int j = 0; j < (2 * i + 1); j++) {
                System.out.print(caracter);
            }

            System.out.println();
        }

}
}