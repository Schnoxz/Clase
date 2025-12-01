// Realizar un programa que solicite la base de un triángulo (debe ser un número impar) así como un carácter. El programa pintará por pantalla un triángulo con esa base de esta forma.
package Default;
import java.util.Scanner;

public class Introduccion10 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int base;

        // Solicita al usuario una base impar para el triángulo
        System.out.print("Introduce la base (impar): ");
        base = teclado.nextInt();

        // Valida que la base sea impar; si no lo es, vuelve a pedirla
        while (base % 2 == 0) {
            System.out.print("La base debe ser impar. Introduce de nuevo: ");
            base = teclado.nextInt();
        }

        // Solicita el carácter con el que se dibujará el triángulo
        System.out.print("Introduce un carácter: ");
        char caracter = teclado.next().charAt(0);

        // Genera el triángulo línea por línea, aumentando la cantidad de caracteres en cada fila
        for (int i = 1; i <= base; i += 2) {
            for (int j = 0; j < i; j++) {
                System.out.print(caracter);
            }
            // Salto de línea al final de cada fila
            System.out.println();
        }
    }
}
