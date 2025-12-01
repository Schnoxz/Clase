// Usa bucles y switch para controlar el flujo. Debes realizar un menú de cajero automático.
package Default;
import java.util.Scanner;

public class Ampliation14 {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        double saldo = 0.0; 
        int opcion;

        // Bucle principal del menú
        do {
            // Muestro el menú de opciones
            System.out.println("\n--- MENÚ CAJERO AUTOMÁTICO ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Ingresar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();

            // Procesa la opción seleccionada
            switch (opcion) {
            case 1:
                System.out.println("Saldo actual: " + saldo + " €");
                break;

            case 2:
                System.out.print("Ingrese la cantidad a depositar: ");
                double ingreso = teclado.nextDouble();
                // Verifica que la cantidad sea positiva antes de sumarla al saldo
                if (ingreso > 0) {
                    saldo += ingreso;
                    System.out.println("Ingreso realizado correctamente.");
                } else {
                    System.out.println("Cantidad inválida.");
                }
                break;

            case 3:
                System.out.print("Ingrese la cantidad a retirar: ");
                double retiro = teclado.nextDouble();
                // Verifica que la cantidad sea válida y que haya suficiente saldo
                if (retiro > 0 && retiro <= saldo) {
                    saldo -= retiro;
                    System.out.println("Retiro realizado correctamente.");
                } else {
                    System.out.println("Fondos insuficientes o cantidad inválida.");
                }
                break;

            case 4:
                System.out.println("Gracias por usar el cajero. ¡Hasta pronto!");
                break;

            default:
                // Cualquier otra opción será no válida
                System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 4); // El bucle se repite hasta que el usuario elige salir
    }
}
