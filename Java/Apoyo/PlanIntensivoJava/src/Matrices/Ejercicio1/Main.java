package Matrices.Ejercicio1;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main(String[] args) {
	    int[][] matrizA = GestorMatriz.pedirMatriz("A", 3, 3);
	    int[][] matrizB = GestorMatriz.pedirMatriz("B", 3, 3);

	    System.out.println("\nMatriz A:");
	    GestorMatriz.mostrarMatriz(matrizA);

	    System.out.println("\nMatriz B:");
	    GestorMatriz.mostrarMatriz(matrizB);
	    
	    System.out.println("\nSuma de matrices: ");
	    int[][] suma = GestorMatriz.sumaMatriz(matrizA, matrizB);
	    GestorMatriz.mostrarMatriz(suma);
	   
	    System.out.println("\nEl mayor valor de la matriz: " + GestorMatriz.buscarMayor(matrizA));
	    System.out.println("\nEl mayor valor de la matriz: " + GestorMatriz.buscarMayor(matrizB));
	}
}
