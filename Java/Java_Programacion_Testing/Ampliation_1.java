import java.util.Scanner;

public class Ampliation_1 {
  private static final Scanner scanner = new Scanner(System.in);
  public static void main (String[] args) {
    

    System.out.print("Introduce un número entero: ");
    int numero = scanner.nextInt();
    int digito = 0;


    // Usamos un bucle do-while para contar las digito simplemente dividiendo el número entre 10

    do {
      numero /= 10;
      digito++;
    } while (numero > 0);

    System.out.println("El número tiene " + digito + " dígitos.");

    
  }
}
