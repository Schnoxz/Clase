package Relacion10v2.Ejercicio3;

import java.util.Scanner;
// Menú para gestionar el alquiler de vehículos, con opciones para dar de alta vehículos y calcular precios de alquiler.
public class Main {
    private static final int MAX_VEHICULOS = 200; // Máximo de vehículos en la flota
    private static Vehiculo[] flota = new Vehiculo[MAX_VEHICULOS]; // Array para almacenar los vehículos
    private static int numVehiculos = 0;
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n══════ MENÚ ALQUILER ══════");
            System.out.println("1. Alta de vehículo");
            System.out.println("2. Calcular precio de alquiler");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    altaVehiculo();
                    break;
                case 2:
                    calcularAlquiler();
                    break;
                case 3:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    // Método para dar de alta un nuevo vehículo en la flota
    private static void altaVehiculo() {
        if (numVehiculos >= MAX_VEHICULOS) {
            System.out.println("Flota llena, no se pueden añadir más vehículos.");
            return;
        }
        System.out.println("Tipo: 1-Coche  2-Microbús  3-Furgoneta");
        int tipo = leerEntero();

        System.out.print("Matrícula: ");
        String matricula = teclado.nextLine();

        System.out.println("Gama: 1-Baja  2-Media  3-Alta");
        int gamaOpcion = leerEntero();
        String gama = gamaOpcion == 1 ? "baja" : gamaOpcion == 2 ? "media" : "alta";

        switch (tipo) {
            case 1:
                System.out.println("Combustible: 1-Gasolina  2-Diesel");
                String combustible = leerEntero() == 1 ? "gasolina" : "diesel";
                flota[numVehiculos++] = new Coche(matricula, gama, combustible);
                break;
            case 2:
                System.out.print("Número de plazas: ");
                int plazas = leerEntero();
                flota[numVehiculos++] = new Microbus(matricula, gama, plazas);
                break;
            case 3:
                System.out.print("PMA (kg): ");
                double pma = leerDecimal();
                flota[numVehiculos++] = new Furgoneta(matricula, gama, pma);
                break;
            default:
                System.out.println("Tipo no válido.");
                return;
        }
        System.out.println("Vehículo dado de alta correctamente.");
    }

    // Método para calcular el precio de alquiler de un vehículo dado su matrícula y número de días
    private static void calcularAlquiler() {
        System.out.print("Matrícula: ");
        String matricula = teclado.nextLine();

        for (int i = 0; i < numVehiculos; i++) {
            if (flota[i].getMatricula().equalsIgnoreCase(matricula)) {
                System.out.print("Número de días: ");
                int dias = leerEntero();
                double precio = flota[i].calcularPrecioAlquiler(dias);
                System.out.printf("Precio de alquiler de %s durante %d días: %.2f€%n",
                        flota[i], dias, precio);
                return;
            }
        }
        System.out.println("Vehículo no encontrado.");
    }

    // Métodos para leer enteros y decimales con validación
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
