package Metodo_2;
import java.util.Scanner;

public class factorial {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		System.out.println("Introduzca la cantidad de números factoriales que quiere visualizar: ");  // Pido por pantalla el rango de números factoriales
		int n = teclado.nextInt(); // Lo guardo
		
		for (int i = 0; i<=n; i++) { // Creo un contador con un for para llegar hasta ese rango y llamo al método factor para cada uno
			System.out.println(i + "! =" + factor(i));
		}
	}
	
	public static int factor (int n) { // Creo el método factor
		// Si el rango que introducimos es negativo, mostramos un mensaje de error y devolvemos un -1
	if (n<0) { 
		System.out.println("ERROR ");
		return -1;
	}
	// Inicio el factorial en 1 para el número 0 y que prosiga
	int resultado = 1;
	// Multiplico el resultado por cada número y lo voy guardando para multiplicarlo por el siguiente número 
	for (int i = 1; i<=n; i++) {
		resultado *= i;
	}
	return resultado;
  }
}

