package Autodidacta.Ejercicio2;

import java.util.Scanner;

public class Contador {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		
		contadorInfinito();
	}
		// Método que pide numeros en bucle y se van sumando hasta que se introduzca 0
		public static void contadorInfinito() {
			double numero = 0;
			double suma = 0;
			int cantidad = 0;
			do { 
				System.out.print("Introduzca un número: ");
				numero = teclado.nextInt();
				suma += numero;
				if (numero != 0) {
				cantidad++;
				}
			} while (numero != 0);
		System.out.println("Se ha interrumpido el contador (Has introducido 0) ");
		System.out.println("La suma total es: " + suma);
		System.out.println("La cantidad total es: " + cantidad);
	}
}