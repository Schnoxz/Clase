
import java.util.Scanner;

public class Calculadora {

    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: introduce un número entero válido.");
            }
        }
    }

    public static char leerOperacion(Scanner sc) {
        while (true) {
            System.out.print("Introduce una operación (+, -, *, /): ");
            String linea = sc.nextLine().trim();
            if (linea.length() == 1) {
                char op = linea.charAt(0);
                if (op == '+' || op == '-' || op == '*' || op == '/') {
                    return op;
                }
            }
            System.out.println("Error: operación inválida. Usa +, -, * o /.");
        }
    }

    public static double calcular(int a, int b, char op) throws DivisionPorCeroException {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new DivisionPorCeroException();
                }
                return (double) a / b;
            default:
                return 0;
        }
    }
}
