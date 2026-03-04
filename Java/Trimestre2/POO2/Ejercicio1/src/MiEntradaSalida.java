
import java.util.Scanner;

public class MiEntradaSalida {

    private static final Scanner teclado = new Scanner(System.in);

    // Método solicitarEntero
    public static int solicitarEntero(String mensaje) {
        // Bucle infinito que muestgra el mensaje y lee la entrada hasta que se introduzca un número
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) { // Catch para cuando se introduce un texto en vez de un número
                System.out.println("Error: Formato incorrecto. Debes introducir un número entero.");
            }
        }
    }

    // Método solicitarEnteroPositivo
    public static int solicitarEnteroPositivo(String mensaje) {
        // Otro bucle infinito hasta que se introduzca un número positivo
        while (true) {
            try {
                int num = solicitarEntero(mensaje);
                if (num <= 0) {
                    throw new Exception("El número debe ser mayor que 0."); // Se lanza una excepción cuando se introduce un número negativo
                }
                return num;
            } catch (Exception e) { // Se muestra el mensaje de error
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Método solicitarEnteroEnRango
    public static int solicitarEnteroEnRango(String mensaje, int limiteInferior, int limiteSuperior) {
        // Mas bucles infinitos, hasta que se introduzca un número dentro del rango
        while (true) {
            try {
                // Se solicita el número y se comprueba si esta dentro del rango
                int num = solicitarEntero(mensaje + " [" + limiteInferior + " - " + limiteSuperior + "]: ");
                if (num < limiteInferior || num > limiteSuperior) {
                    throw new Exception("El número está fuera del rango permitido."); // Se lanza una excepción si no está en el rango
                }
                return num;
            } catch (Exception e) { // El catch lo pilla y muestra el mensaje de error
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Método solicitarCaracter
    public static char solicitarCaracter(String mensaje) {
        // Venga más bucles infinitos, hasta que se introduzca un unico carácter
        while (true) {
            System.out.print(mensaje);
            try {
                String entrada = teclado.nextLine();
                if (entrada.length() != 1) { // Condicion de que solo sea un caracter
                    throw new Exception("Debes introducir exactamente un carácter."); // Se lanza una excepción si se introduce mas de un carácter
                }
                return entrada.charAt(0); // Se devuelve el carácter en la primera posició
            } catch (Exception e) { // El catch lo pilla y muestra el mensaje de error
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Método solicitarCaracterSN
    public static char solicitarCaracterSN(String mensaje) {
        while (true) {
            try {
                char c = solicitarCaracter(mensaje);
                char cMayus = Character.toUpperCase(c);
                if (cMayus != 'S' && cMayus != 'N') {
                    throw new Exception("Solo se admiten los caracteres 'S' o 'N'.");
                }
                return cMayus;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Método solicitarCadena
    public static String solicitarCadena(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                String entrada = teclado.nextLine();
                if (entrada.trim().isEmpty()) {
                    throw new Exception("La cadena no puede estar vacía.");
                }
                return entrada;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
