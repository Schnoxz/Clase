package Autodidacta.Arrays.Ejercicio1;
import java.util.Arrays;

public class GestorArray {

	public static void main (String[] args) {
		int[] original = {1, 2, 3, 4, 5};
		// Muestra el int[] original e invertido
		System.out.println("Original: " + Arrays.toString(original));
		System.out.println("Invertido: " + Arrays.toString(inversorArray(original)));

	}
	
	// Método que recibe int[] y devuelve otro int[] invertido
	public static int[] inversorArray(int[] original) {
		int[] invertido = new int [original.length];
		for (int i = original.length - 1; i >= 0; i--) {
			invertido[original.length - 1 -i] = original[i];
		}
		return invertido;
	}
}
