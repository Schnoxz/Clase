// Realizar un programa que solicite el ancho y el alto de un rectángulo, así como el carácter con el que se quiere dibujar. El programa pintará un rectángulo de dichas dimensiones con el carácter.
package Default;
import java.util.Scanner;

public class Introduccion8 {
    private static final Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        int ancho, alto;
        char caracter;
     // Solicito al usuario el ancho de la figura
        System.out.print("Introduce el ancho de la figura: ");
        ancho = teclado.nextInt();
     // Solicito al usuario el alto de la figura
        System.out.print("Introduce el alto de la figura: ");
        alto = teclado.nextInt();
     // Solicito el carácter con el que se dibujará la figura
        System.out.print("Introduce el caracter de la figura: ");
        caracter = teclado.next().charAt(0);

        //Iniciamos un bucle for para las filas
     for (int i = 0; i < alto; i++) {
        //Iniciamos un bucle for para las columnas de la figura
        for (int x = 0; x < ancho; x++) {
            // Imprimimos el caracter 
            System.out.print(caracter);
        } // Creamos un salto de línea 
        System.out.println();
    }
}
}


