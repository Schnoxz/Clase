package Calculadora;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculadora {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Iniciando calculadora. (Introduce 0 y 0 para salir)");

        while (true) {
            try {
                System.out.print("Introduce un número: ");
                double a = teclado.nextDouble();

                System.out.print("Introduce otro número: ");
                double b = teclado.nextDouble();

                // Condición de salida: si ambos son 0, rompemos el bucle
                if (a == 0 && b == 0) {
                    System.out.println("Fin del programa.");
                    break;
                }

                // Llamamos al método, el cual puede lanzar la excepción
                double resultado = dividir(a, b);
                System.out.println(a + " / " + b + " = " + resultado);

            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un formato numérico válido.");
            } catch (DivisionPorCeroException e) {
                // Capturamos la excepción lanzada por el método dividir
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // El método declara en su firma que puede lanzar la excepción personalizada
    public static double dividir(double a, double b) throws DivisionPorCeroException {
        if (b == 0) {
            throw new DivisionPorCeroException("No se puede dividir por 0.");
        }
        return a / b;
    }
}
