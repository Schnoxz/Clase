package AmpliacionMetodoString;
import java.util.Scanner;

public class NombreCompletoCadena {
	private static final Scanner teclado = new Scanner(System.in);
	public static void main (String[] args) {
		
		System.out.print("Introduzca su nombre completo: ");
		String nombreCompleto = teclado.nextLine(); 
		separarNombre(nombreCompleto); // Llamo al metodo
	}
	// Método para dividir en String separados el nombreCompleto
	private static void separarNombre (String nombreCompleto) {
		// Declaro las variables que voy a usar para almacenar las distintas cadenas de texto
		String nombre = "";
		String apellido1 = "";
		String apellido2 = "";
		String temp = "";	// Creo una variable temporal para ir guardando la cadena de caracter que se vaya encontrando en el for
		int contador = 1; // Tengo que iniciar el contador en 1 para que cuente la ultima palabra y la primera, luego se rectifica en el print poniendo un -1 al contador 
		
		String nombreCom = nombreCompleto + " ";
		for (int i = 0; i < nombreCom.length(); i++) { // Recorro la cadena de caracter en caracter
			char c = nombreCom.charAt(i); 
			
			if (c != ' ') { // Si no es un espacio se forma la palabra
				temp += c;
			} else {
				if (!temp.isEmpty()) { // Si es un espacio la palabra ha terminado y uso el "isEmpty" para eliminar multiples espacios, si es que los hay
					if (contador == 1) {
						nombre = temp; // Asigno la primera cadena al nombre
					} else if (contador == 2) {
						apellido1 = temp; // Asigno la segunda cadena al primer apellido
					} else if (contador == 3) {
						apellido2 = temp; // Asigno la tercera cadena al segundo apellido
					}
					
					contador ++; // Sumo 1 palabra al contador
					temp = ""; // Reinicio el String temporal
				}
			}
		}
		
		if (contador == 4) { // Condicion para encontrar los 3 String porque el contador comienza en 1 y debe haber 3 palabras
			imprimir (nombre, apellido1, apellido2);
		} else {
			System.out.println("El nombre no es correcto, hay: " + (contador-1) + " palabras"); // Muestro por pantalla el error y cuantas palabras aparecen
		}
	}
	// Metodo para mostrar por pantalla el nombre completo separado por cada variable
	public static void imprimir (String nombre, String apellido1, String apellido2) {
		System.out.println("El nombre es: " + nombre);
		System.out.println("El primer apellido es: " + apellido1);
		System.out.println("El segundo apellido es: " + apellido2);
	}
}


