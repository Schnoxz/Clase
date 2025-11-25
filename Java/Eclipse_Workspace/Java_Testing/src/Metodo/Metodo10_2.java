// Segunda parte con el triángulo invertido
package Metodo;

import java.util.Scanner;

public class Metodo10_2 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int base;

        // Solicita al usuario una base impar
        System.out.print("Introduce la base (impar): ");
        base = teclado.nextInt();

        // Valida que la base sea impar
        while (base % 2 == 0) {
            System.out.print("La base debe ser impar. Introduce de nuevo: ");
            base = teclado.nextInt();
        }
        // Solicita el carácter del triángulo
        System.out.print("Introduce un carácter: ");
        char caracter = teclado.next().charAt(0);
        
        // Llamo al método para dibujar el triángulo
        Triangulo (base, caracter);
    }
    	
    	// Método que dibuja el triángulo 
    	private static void Triangulo (int base, char caracter) {
        
        // Genera el triángulo centrado
    	for (int i = 1; i <= base; i += 2) {
            int espacios = (base - i) / 2;

            // Imprime los espacios para centrar la línea
            for (int j = 0; j < espacios; j++) {
                System.out.print(" ");
            }

            // Imprime los caracteres 
            for (int j = 0; j < i; j++) {
                System.out.print(caracter);
            }

            // Salto de línea al final de cada fila
            System.out.println();
        }
    }
}
