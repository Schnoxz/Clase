package MetodoString;
import java.util.*;

public class CaracterMayus2 {
	private static final Scanner teclado = new Scanner (System.in);
	
    public static void main(String[] args) {
       
        // Pedimos la cadena al usuario
        System.out.print("Introduzca una cadena de caracteres: ");
        String cadena = teclado.nextLine();

        // Llamamos al método que imprime directamente los resultados
        contarCaracteres(cadena);
    }

    // Método que imprime los resultados directamente
    private static void contarCaracteres(String cadena) {
        int mayusculas = 0;
        int minusculas = 0;
        int numeros = 0;

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);

            if (Character.isUpperCase(c)) {
                mayusculas++;
            } else if (Character.isLowerCase(c)) {
                minusculas++;
            } else if (Character.isDigit(c)) {
                numeros++;
            }
        }

        // Mostramos resultados
        System.out.println("Número de letras mayúsculas: " + mayusculas);
        System.out.println("Número de letras minúsculas: " + minusculas);
        System.out.println("Número de caracteres numéricos: " + numeros);
    }
}
