// Suma de números pares e impares. Lee 10 números por teclado. Muestra la suma de los números pares y la suma de los impares por separado
package Default;
import java.util.Scanner;

public class Ampliation2 {
  private static final Scanner teclado = new Scanner(System.in);
  public static void main(String[] args) {
      int sumaPares = 0;
      int sumaImpares = 0;

      System.out.println("Introduce diez números enteros:");

      // Bucle para contar hasta 10 números, exactamente como nos pide el enunciado
      for (int i = 0; i < 10; i++) {
          int num = teclado.nextInt();

          // Verifica si el número es par o impar y acumula en la suma correspondiente
          if (num % 2 == 0) {
              sumaPares += num; // Acumulador de números pares
          } else {
              sumaImpares += num; // Acumulador de números impares
          }
      }

      // Muestra los resultados por separado
      System.out.println("La suma de los números pares es: " + sumaPares);
      System.out.println("La suma de los números impares es: " + sumaImpares);
  }
}