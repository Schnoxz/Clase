package MetodoString;

import java.util.*;

public class SumaNumerosCadena9 {
	private static final Scanner teclado = new Scanner (System.in);
	
	 public static void main (String [] args) {
		 System.out.println("Indroduzca una frase con números entre ella: ");
		 String frase = teclado.nextLine();
		 String limpia = frase.trim();
		 
		 int resultado = sumar(limpia);
		 
		 System.out.println("El total de la suma de los números que aparecen en la frase es: " + resultado);
	 }

	 
	 // Método para contar números que se encuentren dentro de una frase
	 private static int sumar(String limpia) {
		 int total = 0;
		 int numero = 0;
		 boolean esNum = false;
		 
		 for (int i = 0; i < limpia.length(); i++) {
			  char c = limpia.charAt(i);
			  
			  if (Character.isDigit(c)) {
				  esNum = true; 
			  int numReal = c - '0'; // Paso de caracter a número (char to int) restando '0'
			  numero = (numero * 10) + numReal;	// Con esta operación lo que hacemos es  si detrás de ese dígito existe otro logramos poder sumar como unidad total, ejemplo: 62 != 6+2; 62 == 60+2
			 
			  if (i == limpia.length() -1) {   // Condición para poder pillar el ultimo numero de la cadena, ya que después puede no haber otro espacio para verificarlo así que le quitamos 1 al espacio total de la cadena
				  total = total + numero;
			  }
			 			
		} else {
			// Si no hay otrodígito detrás cerramos la operación consolidadndo el dígito anterior
			if (esNum) {
				total = total + numero;
				numero = 0; // Reinicio el sistema para que al contar el siguiente número no tenga datos residuales del anterior
				esNum = false;
			}
        }
    }
		 // Se encuentra un problema si al final de la cadena existe un número, no se cuenta, así que uso otra vez la verificación de si es número para poder sumarlo
		 return total;
   }
}
		 
	

		 
	
		

