package Autodidacta.Arrays.Ejercicio2;
import java.util.Scanner;

public class AnalizadorTexto {
	private static final Scanner teclado = new Scanner (System.in);
	
	public static void main (String[] args) {
		String frase = solicitarFrase();
		
		System.out.println("Número de caracteres: " + contadorCaracter(frase));
		System.out.println("Frase en mayúsculas: " +fraseEnMayuscula(frase));
		System.out.println("Contiene Java: " +contieneJava(frase));
		System.out.println("Número de palabras: " +contadorPalabras(frase));
	}
		
		
		// Método que solicita y valida la frase al usuario
		public static String solicitarFrase() {
			String frase;
			do {
				System.out.print("Introduzca una frase: ");
				frase = teclado.nextLine();
			} while (frase.trim().isEmpty());
			return frase;
		}
		
		public static int contadorCaracter(String frase) {
			int numCaracter = 0;
			for (int i = 0; i < frase.length(); i++) {
				numCaracter++;
			}
			return numCaracter;
		}
		
		public static String fraseEnMayuscula(String frase) {
			String fraseMayus = "";		
			for (int i = 0; i < frase.length(); i++) {
				char letra = frase.charAt(i);
				if (letra >= 'a' && letra <= 'z') {
					letra = (char) (letra - ('a' - 'A')); // Convertir a mayúscula
				}
				fraseMayus += letra;
			}
			return fraseMayus;
		}
		
		public static boolean contieneJava(String frase) {
			String java = "java";
			String fraseMinuscula = fraseEnMayuscula(frase).toLowerCase(); // Convertir la frase a minúscula para comparación
			for (int i = 0; i <= fraseMinuscula.length() - 4; i++) {
				boolean coincidencia = true;
				
				for (int j = 0; j < 4; j++) {
					if (fraseMinuscula.charAt(i + j) != java.charAt(j)) {
						coincidencia = false;
						break;
					}
				}
				
				if(coincidencia) {
					return true;
				}
			}
			return false;
		}
		
		public static int contadorPalabras(String frase) {
			int numPalabra = 0;
			for (int i = 0; i < frase.length(); i++) {
				if (frase.charAt(i) == ' ') {
					numPalabra++;
				}
			}
			return numPalabra + 1; // Se suma 1 para contar la última palabra
		}
}
