import java.util.Scanner;

public class Introduccion3 {
  private static final Scanner scanner = new Scanner(System.in);
  public static void main(String[] args) {
    

    System.out.print("Introduce un número entero: ");
    int numero = scanner.nextInt();
    int cifras = 0;

    // En el caso de que el número sea negativo, lo convertimos a positivo

    if (numero < 0) {
      numero = -numero;
    }

    // Usamos un bucle do-while para contar las cifras simplemente dividiendo el número entre 10

    do {
      numero /= 10;
      cifras++;
    } while (numero > 0);

    System.out.println("El número tiene " + cifras + " cifras.");

    
  }
}