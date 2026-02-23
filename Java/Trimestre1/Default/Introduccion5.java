//Realiza el control de acceso a una caja fuerte. La combinación será un número de 4 cifras. El programa nos pedirá la combinación para abrirla. 
//Si no acertamos, se nos mostrará el mensaje “Lo siento, esa no es la combinación” y si acertamos se nos dirá “La caja fuerte se ha abierto satisfactoriamente”
package Default;
import java.util.Scanner;

public class Introduccion5 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        final int COMBINACION = 8412; // Combinación secreta de la caja fuerte
        int intentos = 5;             // Número máximo de intentos permitidos
        int numUsuario;

        System.out.println("Adivina la combinación de 4 dígitos. Tienes " + intentos + " intentos.");

        while (intentos > 0) {
            System.out.print("Introduce la combinación: ");
            numUsuario = teclado.nextInt();

            // Verifica si el número tiene exactamente 4 cifras
            if (numUsuario < 1000 || numUsuario > 9999) {
                System.out.println("La combinación debe ser de 4 dígitos.");
                intentos--; 
            } else if (numUsuario == COMBINACION) {
                System.out.println("¡La caja fuerte se ha abierto satisfactoriamente!");
                break; // Finaliza el programa si la combinación es correcta
            } else {
                System.out.println("Lo siento, esa no es la combinación.");
                intentos--;
            }

            // Muestra los intentos restantes si aún quedan
            if (intentos > 0) {
                System.out.println("Te quedan " + intentos + " intento(s).");
            }
        }

        // Mensaje final si se agotan los intentos
        if (intentos == 0) {
            System.out.println("Se han agotado los intentos. La caja permanece cerrada.");
        }
    }
}
    
