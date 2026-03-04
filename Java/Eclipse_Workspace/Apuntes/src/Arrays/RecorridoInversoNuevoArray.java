package Arrays;

public class RecorridoInversoNuevoArray {
	
	int[] original = {1, 2, 3, 4, 5};
	int[] invertido = new int[original.length];
	
	for (int i = 0; i < original.length; i++) {
		invertido[i] = original[original.length - 1 - i];
	}
	// Mostrar invertido
	for (int n : invertido) {
		System.out.print(n + " ");
	}
	// Salida: 5 4 3 2 1
	
	}
}
