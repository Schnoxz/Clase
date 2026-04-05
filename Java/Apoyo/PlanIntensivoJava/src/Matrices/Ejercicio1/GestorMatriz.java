package Matrices.Ejercicio1;
import java.util.Scanner;

public class GestorMatriz {
	private static final Scanner teclado = new Scanner (System.in);
	private int[][] matriz;
	
	
	public GestorMatriz(int filas, int columnas) {
		this.matriz = new int[filas][columnas];
	}
	
	// Getters
	public int getValor(int fila, int columna) {
		return this.matriz[fila][columna];
	}
	
	// Setters
	
	public void setValor(int fila, int columna, int valor) {
		this.matriz[fila][columna] = valor;
	}
	
	// Método que muestra en formato tabla el contenido de la matriz
	public static void mostrarMatriz(int[][] matriz) {
		for (int[] fila : matriz) {
			for (int valor : fila) {
				System.out.printf("%4d", valor);
			}
			System.out.println(); // Mete un espaciado
		}
	}
	// Método que devuelve una matriz con la suma de elemento a elemento
	public static int[][] sumaMatriz(int[][] a, int [][] b) {
		int[][] resultado = new int[a.length][b.length];
		
		for (int i = 0; i < a.length; i++) {
	        for (int j = 0; j < a[i].length; j++) {
	        	resultado[i][j] = a[i][j] + b[i][j];
	        }
		}
		return resultado;
	}
	
	
	// Método que busca el valor mayor de toda la matriz
	public static int buscarMayor(int[][] matriz) {
		int mayor = matriz[0][0];
		
		for (int[] fila : matriz) {
			for (int valor : fila) {
				if (valor > mayor) {
					mayor = valor;
				}
			}
		}
		return mayor;
	}
	
	// Método que pide al usuario datos para introducir a las matrices
	public static int[][] pedirMatriz(String nombre, int filas, int columnas) {
		int[][] matriz = new int[filas][columnas];
		System.out.println("Introduce los valores de la matriz " + nombre + ":");	
	    for (int i = 0; i < filas; i++) {
	        for (int j = 0; j < columnas; j++) {
	            boolean valido;
	            do {
	                valido = true;
	                try {
	                    System.out.print("  " + nombre + "[" + i + "][" + j + "]: ");
	                    matriz[i][j] = teclado.nextInt();
	                } catch (Exception e) {
	                    System.out.println("  Error: solo se permiten números.");
	                    teclado.nextLine();
	                    valido = false;
	                }
	            } while (!valido);
	        }
	    }
	    return matriz;
	}
}
