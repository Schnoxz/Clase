package Entrenamiento_examen;
import java.util.*;

public class E4 {
	private static final Scanner teclado = new Scanner(System.in);
	public static void main (String[] args) {
		int random = (int)(Math.random() * 100) +1;
		int intentos = 5;
		int numUsuario;
		
		
		while(intentos >0 ) {
			System.out.println("Introduzca su número");
			numUsuario=teclado.nextInt();
			
			if (numUsuario == 0) {
				intentos --;
				System.out.println("No puede ser 0, no está en el rango permitido");
				System.out.println("Te quedan " + intentos  + " intento (s) restantes");
				continue;
			}
			
			if (numUsuario <1 || numUsuario >100) {
				System.out.println("Debe estar en el rango permitido");
			}
			
			if (numUsuario == random) {
				System.out.println("Has adivinado el número");
				break;
				
			} else if (numUsuario < random ) {
			System.out.println("Es mayor");
			} else { 
			System.out.println("Es menor");
			}
			
			  intentos--;
	          System.out.println("Te quedan " + intentos + " intento(s).");
				
		}
		 if (intentos == 0) 
	      System.out.println("Se acabaron los intentos, el número secreto es: " + random);{
	}
	}
}
