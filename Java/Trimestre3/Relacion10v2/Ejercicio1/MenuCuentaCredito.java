package Relacion10v2.Ejercicio1;

import java.util.Scanner;

public class MenuCuentaCredito {
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        CuentaCredito cuenta = new CuentaCredito("Ana García", 150);
        int opcion;

        do {
            System.out.println("\n══════ MENÚ CUENTA CRÉDITO ══════");
            System.out.println("1. Ingresar dinero");
            System.out.println("2. Sacar dinero");
            System.out.println("3. Mostrar saldo y crédito");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            opcion = leerEntero();
            switch (opcion) {
                case 1:
                    System.out.print("Cantidad a ingresar: ");
                    double ingreso = leerDecimal();
                    cuenta.ingresarDinero(ingreso);
                    break;
                case 2:
                    System.out.print("Cantidad a sacar: ");
                    double retirada = leerDecimal();
                    cuenta.sacarDinero(retirada);
                    break;
                case 3:
                    System.out.println(cuenta);
                    break;
                case 4:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static int leerEntero() {
        while (!teclado.hasNextInt()) {
            System.out.print("Introduce un número entero: ");
            teclado.next();
        }
        int valor = teclado.nextInt();
        teclado.nextLine();
        return valor;
    }

    private static double leerDecimal() {
        while (!teclado.hasNextDouble()) {
            System.out.print("Introduce un número: ");
            teclado.next();
        }
        double valor = teclado.nextDouble();
        teclado.nextLine();
        return valor;
    }
}
