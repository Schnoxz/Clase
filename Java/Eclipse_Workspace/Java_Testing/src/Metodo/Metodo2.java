// Realizar un programa que solicite dos números, base y exponente, enteros positivos (incluido el 0), y que calcule la potencia (base elevado a exponente) a través de productos. Si los datos son incorrectos deberán volverse a solicitar.
package Metodo;

import java.util.Scanner;

public class Metodo2 {
  private static final Scanner teclado = new Scanner(System.in);

  public static void main(String[] args) {
    double base;
    int exponente;

    // Solicitar base
    System.out.print("Introduzca el número base: ");
    base = teclado.nextDouble();

    // Exponente mayor a 0
    do {
      System.out.print("Introduzca el exponente (debe ser 0 o mayor): ");
      exponente = teclado.nextInt();
    } while (exponente < 0);

    // Llamar al método para calcular la potencia
    double resultado = calcularPotencia(base, exponente);

    // Mostrar el resultado
    System.out.println("El resultado es " + resultado);
  }

  // Método que calcula la potencia usando productos
  public static double calcularPotencia(double base, int exponente) {
    double resultado = 1;

    for (int i = 0; i < exponente; i++) {
      resultado *= base;
    }

    return resultado;
  }
}
