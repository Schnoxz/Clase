import java.util.Scanner;

public class Simulacro1 {
	private static final Scanner teclado = new Scanner(System.in);
	public static void main(String[] args) {
		
     System.out.print("Introduce número de filas para la pirámide: ");
     int filas = teclado.nextInt();
     
     if (filas <= 0) {
         System.out.println("El número debe ser positivo.");
         return;
     }


     int valor = 2;

     for (int i = 0; i < filas; i++) {
         // Espacios para centrar la pirámide aunque por la longitud al ir creciendo los caracteres, se descuadra
         for (int espacios = 0; espacios < filas - i - 1; espacios++) {
             System.out.printf(" ");
         }

         // Añado espacios entre cada numero de la misma fila
         for (int j = 0; j <= i; j++) {
             System.out.printf(valor + " ");
             valor += 2;
         }

         System.out.println();
     }
 }
}
 


