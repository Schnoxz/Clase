// Un año es bisiesto si es:
// Divisible por 4.
// No divisible por 100.
// Salvo si es divisible por 400. (2000 y 2400 son bisiestos pues aún siendo divisibles por 100 lo son también por 400. Pero los años 1800, 1900, 2100, 2200 y 2300 no lo son porque solo son divisibles por 100).

package Metodo_2;
import java.util.*;

public class bisiesto {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
	
		System.out.println("Introduzca el año que desea comprobar: "); // Pido el año para comprobarlo
		int año = teclado.nextInt();
		
		esbisiesto(año); // Llamo al método
	}
		
		// Creo el método para comprobar si es bisiesto
		public static void esbisiesto (int año) {
		// Compruebo si el año es múltiplio de 4, elimino del rango los que son de 100 y uso la división entre 400 para distinguir de los que son múltiplos de 100, los que sí son bisiestos
			if ((año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)) {
				System.out.println("El año " + año + " es bisiesto");
			}else {
				System.out.println("El año " + año + " no es bisiesto ");
			}
	}
 }

