// Realizar un programa que solicite dos números, base y exponente, enteros positivos (incluido el 0), y que calcule la potencia (base elevado a exponente) a través de productos. Si los datos son incorrectos deberán volverse a solicitar.

import java.util.Scanner; /*Importo las utilidades */

public class Introduccion2 {
  private static final Scanner teclado = new Scanner(System.in); //Declaro la entrada por teclado //
  public static void main(String[] args) {
    // Declaro las variables //
    double base;
    int exponente;
    double resultado;

    System.out.print("Introduzca el numero base: "); //Uso un print para pedir al usuario el numero base //
    base = teclado.nextDouble();
     
    // Establezco un do while para que el programa prosiga mientras el exponente introducido sea mayor o igual a 0 
    do {
      System.out.print("Introduzca el exponente (debe ser 0 o mayor): ");
      exponente = teclado.nextInt();
    } while (exponente < 0);

 // Establezco condiciones con if y else, en el caso de que sea 0, nos devuelve un 1 como resultado, si es mayor a 0 entra en un bucle for donde añadiría +1 al exponente hasta llegar al indicado por el usuario 
    if (exponente == 0) {
      resultado = 1;             
    } else {
      resultado = 1;             
      for (int i = 0; i < exponente; i++) {
        resultado = resultado * base;
      }
    }


    System.out.println("El resultado es " + resultado); //Enseñamos por pantalla el resultado//

    
  }
}
