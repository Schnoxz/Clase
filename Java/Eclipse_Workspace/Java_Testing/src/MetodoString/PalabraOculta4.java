package MetodoString;
import java.util.Scanner;

public class PalabraOculta4 {
	private static final Scanner teclado = new Scanner(System.in);
   
	public static void main(String[] args) {
        
        System.out.print("Introduce la frase que va a contener la palabra escondida: ");
        String frase = teclado.nextLine().toLowerCase(); // Convertimos a minúsculas para evitar fallos tontos

        System.out.print("Introduce la palabra escondida:");
        String palabra = teclado.nextLine().toLowerCase();

        // Llamo al método booleano para decirnos si se ha encontrado o no
        if (esPalabraEscondida(frase, palabra)) {
            System.out.println("Encontrada: La palabra: " + "'" + palabra + "'");
        } else {
            System.out.println("No se encuentra.");
        }
    }

    // Método para buscar la palabra escondida
    public static boolean esPalabraEscondida(String frase, String palabra) {
        int j = 0; // Declaro una variable que va a ir por toda la cadena para encontrar los caracteres de la palabra oculta

        // Recorremos la frase letra por letra
        for (int i = 0; i < frase.length(); i++) {
            
            // Comparamos la letra actual de la frase con la letra que nos toca buscar
            if (frase.charAt(i) == palabra.charAt(j)) {
                j++; // Si se encuentra una, pasa a buscar la siguiente letra
            }

            // Verificamos si ya hemos encontrado todas las letras
            if (j == palabra.length()) {
                return true; // 
            }
        }

        // Si se acaba el bucle y no hemos retornado true, es que no completamos la palabra
        return false;
    }
}