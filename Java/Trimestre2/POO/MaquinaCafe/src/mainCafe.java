
import java.util.*;

public class mainCafe {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        // Se crea el objeto llamando al constructor, inicializa el monedero en 0
        Cafe m1 = new Cafe(0);
        // He añadido una variable para confirmar que el usuario realmente quiere el producto
        int confirmar;
        int opcion;

        // Menú principal de la maquina
        do {
            System.out.println("\n Peruvian company ");
            System.out.println(" 1. Café solo (1 euro) ");
            System.out.println(" 2. Vaso de leche (0.80 euro) ");
            System.out.println(" 3. Café con leche (1.50 euro) ");
            System.out.println(" 4. Estado ");
            System.out.println(" 5. Salir ");
            System.out.println(" 6. Rellenar máquina");
            System.out.println(" 7. Vaciar monedero");

            opcion = teclado.nextInt();
            // Switch para navegar por el menú y llamada a los metodos
            switch (opcion) {
                case 1:
                    System.out.println("Pulse de nuevo para confirmar"); // Confirmación adicional al usuario
                    confirmar = teclado.nextInt();
                    if (confirmar == opcion) {
                        m1.servirSolo();
                    } else {
                        System.out.println("Cancelado");
                    }
                    break; // Lo pongo fuera de la condicion porque una vez que el usuario confirma, si dejo el break dentro, puede confirmar items ilimitados en un solo pago

                case 2:
                    System.out.println("Pulse de nuevo para confirmar");
                    confirmar = teclado.nextInt();
                    if (confirmar == opcion) {
                        m1.servirLeche();
                    } else {
                        System.out.println("Cancelado");
                    }
                    break;

                case 3:
                    System.out.println("Pulse de nuevo para confirmar");
                    confirmar = teclado.nextInt();
                    if (confirmar == opcion) {
                        m1.servirCafeLeche();
                    } else {
                        System.out.println("Cancelado");
                    }
                    break;

                case 4:
                    System.out.println("Pulse de nuevo para confirmar");
                    confirmar = teclado.nextInt();
                    if (confirmar == opcion) {
                        m1.recuentoMaquina();
                    } else {
                        System.out.println("Cancelado");
                    }
                    break;

                case 5:
                    System.out.println("Apagando...");
                    System.out.println(" <> <> <> <> <> ");
                    break;

                case 6:
                    System.out.println("Pulse de nuevo para autofill de la máquina");
                    confirmar = teclado.nextInt();
                    if (confirmar == opcion) {
                        m1.rellenarMaquina(50, 50, 80);
                    } else {
                        System.out.println("Cancelado");
                    }
                    break;

                case 7:
                    System.out.println("Pulse de nuevo para vaciar el monedero");
                    confirmar = teclado.nextInt();
                    if (confirmar == opcion) {
                        m1.vaciarMonedero();
                    } else {
                        System.out.println("Cancelado");
                    }
                    break;

                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 7);
    }
}
