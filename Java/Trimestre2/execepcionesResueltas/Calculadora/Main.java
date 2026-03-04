
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = Calculadora.leerEntero(sc, "Introduce el primer número: ");
        char op = Calculadora.leerOperacion(sc);
        int b = Calculadora.leerEntero(sc, "Introduce el segundo número: ");

        try {
            double resultado = Calculadora.calcular(a, b, op);
            System.out.println("Resultado: " + a + " " + op + " " + b + " = " + resultado);
        } catch (DivisionPorCeroException e) {
            System.out.println(e.getMessage());
        }
    }
}
