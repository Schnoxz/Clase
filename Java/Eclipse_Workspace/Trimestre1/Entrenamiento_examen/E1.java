package Entrenamiento_examen;

import java.util.Scanner;

public class E1 {
	private static final Scanner teclado = new Scanner(System.in);
	public static void main (String[] args) {
		int num1, num2, num3;
		int menor, mayor, igual;
		
		
		System.out.println("Escriba el primer número");
		num1 = teclado.nextInt();
		System.out.println("Escriba el segundo número");
		num2 = teclado.nextInt();
		System.out.println("Escriba el tercer número");
		num3 = teclado.nextInt();
		
		   if (num1 <= num2 && num1 <= num3) {
	            menor = num1;
	            if (num2 <= num3) {
	                igual = num2;
	                mayor = num3;
	            } else {
	                igual = num3;
	                mayor = num2;
	            }
	        } else if (num2 <= num1 && num2 <= num3) {
	            menor = num2;
	            if (num1 <= num3) {
	                igual = num1;
	                mayor = num3;
	            } else {
	                igual = num3;
	                mayor = num1;
	            }
	        } else {
	            menor = num3;
	            if (num1 <= num2) {
	                igual = num1;
	                mayor = num2;
	            } else {
	                igual = num2;
	                mayor = num1;
	            }
	        }
		System.out.println("Los números ordenados de menor a mayor son: ");
		System.out.println( menor + " - " + igual + " - " + mayor );
		 
		
	}

}
