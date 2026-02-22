import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = leerEntero(sc, "Introduce el primer entero: ");
        char op = leerOperacion(sc);

        int b = 0;
        boolean segundoOk = false;

        while (!segundoOk) {
            b = leerEntero(sc, "Introduce el segundo entero: ");

            if (op == '/' && b == 0) {
                System.out.println("Error: no se puede dividir entre 0. Vuelve a intentarlo.");
            } else {
                segundoOk = true;
            }
        }

        try {
            int resultado = calcular(a, b, op);
            System.out.println("Resultado: " + a + " " + op + " " + b + " = " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Error aritmético: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Fin del programa");
            sc.close();
        }
    }

    public static int leerEntero(Scanner sc, String mensaje) {
        boolean ok = false;
        int n = 0;

        while (!ok) {
            System.out.print(mensaje);
            try {
                n = sc.nextInt();
                sc.nextLine();
                ok = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida: debes escribir un entero.");
                sc.nextLine();
            }
        }
        return n;
    }

    public static char leerOperacion(Scanner sc) {
        boolean ok = false;
        char op = ' ';

        while (!ok) {
            System.out.print("Introduce la operación (+, -, *, /): ");
            String linea = sc.nextLine().trim();

            if (linea.length() == 1) {
                op = linea.charAt(0);
                if (op == '+' || op == '-' || op == '*' || op == '/') {
                    ok = true;
                } else {
                    System.out.println("Operación inválida. Usa + - * /");
                }
            } else {
                System.out.println("Debes introducir un solo carácter.");
            }
        }

        return op;
    }

    public static int calcular(int a, int b, char op) {
        int res;

        if (op == '+') res = a + b;
        else if (op == '-') res = a - b;
        else if (op == '*') res = a * b;
        else if (op == '/') res = a / b;
        else throw new IllegalArgumentException("Operación no reconocida: " + op);

        return res;
    }
}
