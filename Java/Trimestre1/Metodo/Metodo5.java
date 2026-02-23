//Realiza el control de acceso a una caja fuerte. La combinación será un número de 4 cifras. El programa nos pedirá la combinación para abrirla. 
//Si no acertamos, se nos mostrará el mensaje “Lo siento, esa no es la combinación” y si acertamos se nos dirá “La caja fuerte se ha abierto satisfactoriamente”
package Metodo;

import java.util.Scanner;

public class Metodo5 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        controlCajaFuerte();
    }

    // Método que contiene toda la lógica del control de acceso
    public static void controlCajaFuerte() {
        final int COMBINACION = 8412;
        int intentos = 5;
        int numUsuario;

        System.out.println("Adivina la combinación de 4 dígitos. Tienes " + intentos + " intentos.");

        while (intentos > 0) {
            System.out.print("Introduce la combinación: ");
            numUsuario = teclado.nextInt();

            if (numUsuario < 1000 || numUsuario > 9999) {
                System.out.println("La combinación debe ser de 4 dígitos.");
                intentos--;
            } else if (numUsuario == COMBINACION) {
                System.out.println("¡La caja fuerte se ha abierto satisfactoriamente!");
                break;
            } else {
                System.out.println("Lo siento, esa no es la combinación.");
                intentos--;
            }

            if (intentos > 0) {
                System.out.println("Te quedan " + intentos + " intento(s).");
            }
        }

        if (intentos == 0) {
            System.out.println("Se han agotado los intentos. La caja permanece cerrada.");
        }
    }
}
