package Metodo_2;

import java.util.Scanner;

public class numeroEnRango {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		int resultado = solicitarNumeroEnRango(50, 43); // Llamo al método y establezco el rango
		System.out.println("Número valido: " + resultado);
	}
	
	// Método que solicita el número en rango 
	public static int solicitarNumeroEnRango (int limiteInferior, int limiteSuperior) {
		// Si los límites están invertidos se establecen correctamente
		if (limiteInferior > limiteSuperior) {
			int copia = limiteInferior;
			limiteInferior = limiteSuperior;
			limiteSuperior = copia;
	}
		
		int numero;
		// Entrada del número en rango y bucle si el número está fuera del mismo
		do {
			System.out.println("Introduce un número entre: " + limiteInferior + " y " + limiteSuperior);
			numero = teclado.nextInt();
			
			if (numero < limiteInferior || numero > limiteSuperior) {
				System.out.println("El número está fuera de rango, introdúzcalo de nuevo");
			}
		} while (numero < limiteInferior || numero > limiteSuperior);
		
		return numero; // Devuelvo el número cuando sea válido
 }
}

