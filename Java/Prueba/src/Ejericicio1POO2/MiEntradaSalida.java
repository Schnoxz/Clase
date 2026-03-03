package Ejericicio1POO2; // Le ponemos un nombre de paquete genérico y profesional

import java.util.Scanner;

public class MiEntradaSalida {

    private static final Scanner teclado = new Scanner(System.in);

    // Método solicitarEntero
    public static int solicitarEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Formato incorrecto. Debes introducir un número entero.");
            }
        }
    }

    // Método solicitarEnteroPositivo
    public static int solicitarEnteroPositivo(String mensaje) {
        while (true) {
            try {
                int num = solicitarEntero(mensaje);
                if (num <= 0) {
                    throw new Exception("El número debe ser mayor que 0.");
                }
                return num;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Método solicitarEnteroEnRango
    public static int solicitarEnteroEnRango(String mensaje, int limiteInferior, int limiteSuperior) {
        while (true) {
            try {
                int num = solicitarEntero(mensaje + " [" + limiteInferior + " - " + limiteSuperior + "]: ");
                if (num < limiteInferior || num > limiteSuperior) {
                    throw new Exception("El número está fuera del rango permitido.");
                }
                return num;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Método solicitarCaracter
    public static char solicitarCaracter(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                String entrada = teclado.nextLine();
                if (entrada.length() != 1) {
                    throw new Exception("Debes introducir exactamente un carácter.");
                }
                return entrada.charAt(0);
            } catch (Exception e) {
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
