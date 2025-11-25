import java.util.Scanner;

public class Ampliation_2 {
  private static final Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {

    System.out.print("Introduce diez números enteros: ");
    int sumaPares = 0;
    int sumaImpares = 0;

    for (int i = 0; i < 10; i++) {
      int numero = scanner.nextInt();

      if (numero % 2 == 0) {
        sumaPares += numero;
      } else {
        sumaImpares += numero;
      }
    }
    System.out.println("La suma de los números pares es: " + sumaPares);
    System.out.println("La suma de los números impares es: " + sumaImpares);

  }
}