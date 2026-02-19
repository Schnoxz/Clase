package MetodoString;
import java.util.*;

public class ValidarWeb10 {
	private static final Scanner teclado = new Scanner(System.in);
	
    public static void main(String[] args) {
        
        for (int i = 1; i <= 10; i++) {
            System.out.println("Introduzca la dirección web " + "( " + i + " de 10 ): ");
            String url = teclado.nextLine();

            if (webValida(url)) {
                System.out.println("-> Válida");
            } else {
                System.out.println("-> No válida");
            }
        }
    }
    
    // Método validar la direccion web usando otros métodos 
    public static boolean webValida(String url) {
        // Validamos el inicio
        if (!empiezaCon(url, "http://www.")) {
            return false;
        }
        
        // Validamos el final
        boolean esCom = terminaCon(url, ".com");
        boolean esEs = terminaCon(url, ".es");

        if (!esCom && !esEs) {
            return false;
        }

        
        // Calculamos dónde acaba el dominio según si era .com (4 chars) o .es (3 chars)
        int sufijo;
        if (esCom) sufijo = 4;
        else sufijo = 3;

        int inicioDominio = 11; // Longitud de "http://www."
        int finDominio = url.length() - sufijo;

        // Validar que existe dominio
        if (finDominio <= inicioDominio) return false;

        // Validar primera letra
        if (!Character.isLetterOrDigit(url.charAt(inicioDominio))) return false;

        // Validar resto del dominio
        for (int i = inicioDominio; i < finDominio; i++) {
            if (!Character.isLetterOrDigit(url.charAt(i))) return false;
        }

        return true;
    }

    // Método para comprobar el sufijo de la dirección web
    public static boolean terminaCon (String texto, String sufijo) {
        // Si el sufijo es más largo que el texto se devuelve como invalido
        if (texto.length() < sufijo.length()) {
            return false;
        }

        // Comparamos los caracteres finales del texto con los del sufijo
        int j = texto.length() - sufijo.length();
        
        for (int i = 0; i < sufijo.length(); i++) {
            if (texto.charAt(j) != sufijo.charAt(i)) {
                return false; // En cuanto uno no coincida, devolvemos false
            }
            j++; // Avanzamos en el texto
        }
        
        return true; // Si el bucle termina, es que todos coincidieron
    }

    // Método para comprobar el prefijo de la dirección web
    public static boolean empiezaCon(String texto, String prefijo) {
        if (texto.length() < prefijo.length()) {
            return false;
        }

        for (int i = 0; i < prefijo.length(); i++) {
            if (texto.charAt(i) != prefijo.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}