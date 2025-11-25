// Numero mayor, menor y promedio. Lee n números. Muestra el número mayor, el menor y el promedio de todos.

import java.util.Scanner;

public class Ampliation4 {
	private static final Scanner teclado = new Scanner(System.in);
	 public static void main(String[] args) {
		 
	        // Solicito al usuario cuántos números desea introducir
	        System.out.print("¿Cuántos números desea introducir? ");
	        int cantidad = teclado.nextInt();

	        // Si es 0 o menor, la denomino como inválido
	        if (cantidad <= 0) {
	            System.out.println("Cantidad inválida. Debe ser mayor que cero.");
	            return;
	        }

	        // Solicito el primer número e inicio las variables
	        System.out.print("Número 1: ");
	        int numero = teclado.nextInt();

	        int mayor = numero;
	        int menor = numero;
	        double suma = numero;

	        // Bucle para leer el resto de los números
	        for (int i = 2; i <= cantidad; i++) {
	            System.out.print("Número " + i + ": ");
	            int valor = teclado.nextInt();
	            suma += valor;

	            // Actualizo el mayor y el menor si se cumple la condición
	            if (valor > mayor) mayor = valor;
	            if (valor < menor) menor = valor;
	        }

	        // Calculamos el promedio
	        double promedio = suma / cantidad;

	        // Muestra los resultados
	        System.out.println("\nResultados:");
	        System.out.println("Mayor: " + mayor);
	        System.out.println("Menor: " + menor);
	        System.out.println("Promedio: " + promedio);

    }
}
