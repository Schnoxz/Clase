//Realizar un programa que solicite un número entero y determine el número de cifras que tiene dicho número

package Metodo;

import java.util.Scanner;

public class Metodo3 {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.print("Introduce un número entero: ");
    int numero = scanner.nextInt();

    int cifras = contarCifras(numero);

    System.out.println("El número tiene " + cifras + " cifras.");
  }

  // Método que cuenta las cifras de un número entero
  public static int contarCifras(int numero) {
    int contador = 0;

    // Contamos cifras dividiendo entre 10
    do {
      numero /= 10;
      contador++;
    } while (numero > 0);

    return contador;
  }
}
