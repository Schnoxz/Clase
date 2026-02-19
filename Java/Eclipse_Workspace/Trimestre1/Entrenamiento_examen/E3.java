package Entrenamiento_examen;
import java.util.*;

public class E3 {
	private static final Scanner teclado = new Scanner(System.in);
	public static void main (String[] args) {
		int num;
		int cifras = 0;
		
		System.out.println("Introduzca un número");
		num = teclado.nextInt();
		

		   do {
			      cifras++;
			      num /= 10;
			    } while (num > 0);

			    System.out.println("El número tiene " + cifras + " cifras.");
			
		}
		
		
	}


