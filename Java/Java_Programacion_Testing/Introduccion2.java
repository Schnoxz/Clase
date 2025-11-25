
import java.util.Scanner;

public class Introduccion2 {
  private static final Scanner teclado = new Scanner(System.in);
  public static void main(String[] args) {
    
    double base;
    int exponente;
    double resultado;

    System.out.print("Introduzca el numero base: ");
    base = teclado.nextDouble();
     
    do {
      System.out.print("Introduzca el exponente (debe ser 0 o mayor): ");
      exponente = teclado.nextInt();
    } while (exponente < 0);

   
    if (exponente == 0) {
      resultado = 1;             
    } else {
      resultado = 1;             
      for (int i = 0; i < exponente; i++) {
        resultado = resultado * base;
      }
    }


    System.out.println("El resultado es " + resultado);

    
  }
}
