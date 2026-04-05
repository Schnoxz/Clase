package Autodidacta.Arrays.Ejercicio3;

import java.util.Scanner;

public class Estadisticas {
	private static final Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		int[] array = solicitarArray();
		mayorMenor(array);
	}
	
	// Método que pide al usuario 5 números y los guarda en un array
	public static int[] solicitarArray() {
		int[] arrayUsuario = new int[5];
		for (int i = 0; i < arrayUsuario.length; i++) {
		System.out.print("Número " + (i + 1) + ": ");
		arrayUsuario[i] = teclado.nextInt();
	}
		return arrayUsuario;
}
	
	// Método que compara y muestra el mayor y menor número de la lista
	public static void mayorMenor(int[] arrayUsuario) {
		int mayor = arrayUsuario[0];
		int menor = arrayUsuario[0];
		// Comienzo con el 1 porque el 0 ya los he declarado como mayor y menor
		for (int i = 1; i < arrayUsuario.length; i++) {
			if (arrayUsuario[i] > mayor) {
				mayor = arrayUsuario[i];
			} else if (arrayUsuario[i] < menor) {
				menor = arrayUsuario[i];
			}
		}
		System.out.println("Mayor: " + mayor);
		System.out.println("Menor: " + menor);
	}
}	
