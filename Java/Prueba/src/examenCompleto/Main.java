package examenCompleto;

import java.util.*;

public class Main {

    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Crea un array de Productos con 4 elementos dentro
        Producto[] Producto = new Producto[4];

        //  Se crean 4 arrays de Productos con sus respectivos datos
        Producto[0] = new Producto("Cinturon", 2, 20.0);
        Producto[1] = new Producto("Pantalon", 10, 30.0);
        Producto[2] = new Producto("Zapatos", 3, 50.0);
        Producto[3] = new Producto("Botas", 2, 70.0);

        //  Muestra los datos de los arrays con un toString y usa un for each
        for (Producto p : Producto) {
            System.out.println(p.toString());
        }

        // Intentar vender 5 Productos
        try {
            System.out.println("\nIntentando vender 5 unidades de " + Producto[0].getNombre() + ": ");
            Producto[0].vender(5);
            System.out.println("Venta realizada con exito.");
        } catch (StockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Venta que sí funciona
        try {
            System.out.println("\nIntentando vender 2 unidades de " + Producto[1].getNombre() + ": ");
            Producto[1].vender(2);
            System.out.println("Venta realizada con exito.");
        } catch (StockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Productos que contengan la letra a
        System.out.println("\nProductos que contienen la letra 'a': ");
        int contador = 0;
        for (Producto p : Producto) {
            // contains() distingue mayúsculas, usamos toLowerCase() para buscar en minúsculas
            if (p.getNombre().toLowerCase().contains("a")) {
                System.out.println("  -> " + p.getNombre());
                contador++;
            }
        }
        System.out.println("Total de productos con la letra 'a' :" + contador);

        // Ordenar productos por nombre con Array.sort y compareTo
        System.out.println("\nProductos ordenados alfabeticamente: ");
        Arrays.sort(Producto, (a, b) -> a.getNombre().compareTo(b.getNombre()));
        for (Producto p : Producto) {
            System.out.println(p.toString());
        }
    }
}
