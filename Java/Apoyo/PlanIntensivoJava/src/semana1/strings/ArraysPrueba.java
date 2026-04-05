package semana1.strings;

public class ArraysPrueba {
	public static void main (String[] args) {
		
		recorridoString();
		recorridoArrayNumerico();
		
	}
		
		public static void recorridoString() {
		// Prueba de creacion de ARRAY con STRING y recorrido con un for each
		String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
		System.out.println("\nRecorrido de dias");
		for (String dia : dias) {
			System.out.println(dia);
		}
	}
		
		public static void recorridoArrayNumerico() {
			// Prueba de creacion de ARRAY con NUMEROS y recorrido con un for each
		int[] numerosEnteros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		double[] numerosReales = {1.2, 2.3, 3.4, 4.5, 5.6, 6.7};
		
		System.out.println("\nRecorrido de numeros Enteros");
		for (int numero : numerosEnteros) {
			System.out.println(numero);
		}
		System.out.println("\nRecorrido de numeros Reales");
		for (double numeros : numerosReales) {
			System.out.println(numeros);
		}
	}
}
