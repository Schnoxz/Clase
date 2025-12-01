// Contador de dígitos.  Pide al usuario un número entero y muestra cuántos dígitos tiene. 
package Default;
import java.util.Scanner;

public class Ampliation1 {
  private static final Scanner teclado = new Scanner(System.in);

  public static void main(String[] args) {
      int numero;
      int digitos = 0;

      // Solicito al usuario un número entero
      System.out.print("Introduce un número entero: ");
      numero = teclado.nextInt();

      // Convierte el número a positivo si es negativo
      if (numero < 0) {
          numero = -numero;
      }

      // Si el número es 0, tiene 1 dígito
      if (numero == 0) {
          digitos = 1;
      } else {
          // Cuenta los dígitos dividiendo sucesivamente entre 10
          do {
              numero /= 10;
              digitos++;
          } while (numero > 0);
      }

      // Muestra el resultado
      System.out.println("El número tiene " + digitos + " dígito(s).");
  }
}