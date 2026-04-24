import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ListaOrdenada {

    LinkedList<Integer> lista = new LinkedList<>();

    // Método para insertar en orden (sin duplicados)
    public void insertarEnOrden(Integer nuevo) {
        if (lista.contains(nuevo)) {
            System.out.println("El número " + nuevo + " ya existe. No se inserta.");
            return;
        }

        int pos = buscarSuSitio(nuevo);
        lista.add(pos, nuevo);
    }

    // Busca la posición correcta
    private int buscarSuSitio(Integer nuevo) {
        boolean encontradoPosicion = false;
        int pos = 0;

        Iterator<Integer> it = lista.iterator();
        Integer elemento;

        while (it.hasNext() && !encontradoPosicion) {
            elemento = it.next();

            if (nuevo < elemento) {
                encontradoPosicion = true;
            } else {
                pos++;
            }
        }

        return pos;
    }

    // Mostrar lista
    public void mostrarLista() {
        System.out.print("Lista: ");
        for (Integer num : lista) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaOrdenada lista = new ListaOrdenada();

        int opcion;
        
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar número");
            System.out.println("2. Mostrar lista");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce un número: ");
                    int num = sc.nextInt();
                    lista.insertarEnOrden(num);
                    break;

                case 2:
                    lista.mostrarLista();
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 3);

        sc.close();
    }
}