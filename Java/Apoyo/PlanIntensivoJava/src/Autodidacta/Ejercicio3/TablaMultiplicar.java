package Autodidacta.Ejercicio3;

import java.util.Scanner;

public class TablaMultiplicar {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		
		multiplicacion();
	}
	
	// Método que pide al usuario un numero y muestre su tabla de multiplicar
	public static void multiplicacion() {
		System.out.print("Introduce un número para ver su tabla de multiplicar: ");
		int numero = teclado.nextInt();
		for (int i = 1; i <= 10; i++) {
		int total = numero * i;
		System.out.println(numero + "x" + i + "=" + total);
		}
	}
}
