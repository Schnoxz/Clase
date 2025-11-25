package Entrenamiento_examen;
import java.util.Scanner;

public class E2 {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		int exponente;
		int base;
		int resultado = 1;
		
		
	do {
		System.out.println("Introduzca el numero base");
		base = teclado.nextInt();
		
		System.out.println("Introduzca el numero exponente");
	    exponente = teclado.nextInt();
	    
	    if (base <0 || exponente <0 ) {
	    System.out.println("Los números deben ser positivos, introdúzcalos de nuevo");
	    }	    
	    
	    
	
	} while (base <0 || exponente <0 );
	
	
	for (int producto = 1; producto <= exponente; producto ++) {
	resultado  = resultado * base;
	}
	System.out.println("El resultado de: " + base + "  elevado a " + exponente + "  es : " + resultado);
}}

