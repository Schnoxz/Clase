
import java.util.Scanner;

public class mainLinea {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        Linea miLinea = null; // Si no pongo null no lo recoge en el switch
        boolean creada = false;

        // 1. Bucle de creación de lineas, no sale hasta que no cree una
        while (creada == false) {
            try {
                System.out.println("Introduce X e Y del punto A:");
                int x1 = teclado.nextInt();
                int y1 = teclado.nextInt();

                System.out.println("Introduce X e Y del punto B:");
                int x2 = teclado.nextInt();
                int y2 = teclado.nextInt();

                Punto pA = new Punto(x1, y1);
                Punto pB = new Punto(x2, y2);

                miLinea = new Linea(pA, pB);
                creada = true; // ¡Éxito!
                System.out.println("Linea creada");

            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir números.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Menú de movimientos
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Mover Izquierda");
            System.out.println("2. Mover Derecha");
            System.out.println("3. Mover Arriba");
            System.out.println("4. Mover Abajo");
            System.out.println("5. Ver Linea");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = teclado.nextInt();
            } catch (Exception e) {
                opcion = 0; // Opción inválida
            }

            // Variable para la distancia que se pide al usuario en cada movimiento
            int distancia = 0;

            switch (opcion) {
                case 1:
                    System.out.print("¿Cuánta distancia hacia la Izquierda?: ");
                    distancia = teclado.nextInt();
                    miLinea.moverIzq(distancia); // Pasamos la distancia
                    System.out.println("Movimiento realizado.");
                    break;

                case 2:
                    System.out.print("¿Cuánta distancia hacia la Derecha?: ");
                    distancia = teclado.nextInt();
                    miLinea.moverDer(distancia);
                    System.out.println("Movimiento realizado.");
                    break;

                case 3:
                    System.out.print("¿Cuánta distancia hacia Arriba?: ");
                    distancia = teclado.nextInt();
                    miLinea.moverArriba(distancia);
                    System.out.println("Movimiento realizado.");
                    break;

                case 4:
                    System.out.print("¿Cuánta distancia hacia Abajo?: ");
                    distancia = teclado.nextInt();
                    miLinea.moverAbajo(distancia);
                    System.out.println("Movimiento realizado.");
                    break;

                case 5:
                    System.out.println("Estado actual: " + miLinea.verLinea());
                    break;

                case 6:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción incorrecta, elige entre 1 y 6.");
            }

        } while (opcion != 6);
    }
}
